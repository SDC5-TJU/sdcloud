package scs.service.monitor.containers.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import scs.dao.monitor.DAOmapper.TableContainerresourceusageMapper;
import scs.pojo.TableContainerresourceusage;
import scs.service.monitor.containers.ContainerMonitor;
import scs.util.format.DataFormats;

@Service("containerMonitor")
public class ContainerMonitorImpl implements ContainerMonitor {

	// public static String DOCKER_COMMAND = "sudo docker info";
	@Autowired
	public TableContainerresourceusageMapper containerresourceusageMapper;

	public int testInsert(ArrayList<TableContainerresourceusage> containersList) {
		if (containersList == null) {
			return -1;
		}
		Iterator<TableContainerresourceusage> iterator = containersList.iterator();
		int sum = 0;
		while (iterator.hasNext()) {
			TableContainerresourceusage record = iterator.next();
			containerresourceusageMapper.insertSelective(record);
			sum++;
		}
		return sum;
	}

	public ContainerMonitorImpl() {
		// TODO Auto-generated constructor stub
	}

	public InputStream getCommandInfoStream(String hostname, String username, String password, String command) {
		String host = hostname;
		String user = username;
		String passwd = password;

		try {
			/* Create a connection instance */
			Connection conn = new Connection(host);

			/* Now connect */
			conn.connect();

			/*
			 * Authenticate. If you get an IOException saying something like
			 * "Authentication method password not supported by the server at this stage."
			 * then please check the FAQ.
			 */
			boolean isAuthenticated = conn.authenticateWithPassword(user, passwd);

			if (isAuthenticated == false)
				throw new IOException("Authentication failed.");

			/* Create a session */

			Session sess = conn.openSession();

			sess.execCommand(command);

			/*
			 * This basic example does not handle stderr, which is sometimes
			 * dangerous (please read the FAQ).
			 */
			InputStream stdout = new StreamGobbler(sess.getStdout());

			return stdout;

		} catch (IOException e) {
			e.printStackTrace(System.err);
			System.exit(2);
			return null;
		}
	};

	public InputStream getContainerInfoStream(String hostname, String username, String password) {
		String command = "sudo docker stats --no-stream --format \"{{.Name}}:{{.CPUPerc}}:{{.MemUsage}}:{{.MemPerc}}:{{.NetIO}}:{{.BlockIO}}\"";
		return getCommandInfoStream(hostname, username, password, command);
	}

	public float[] getContainerNetInfoStream(String hostname, String username, String password, String containerName) {
		String command = "cat /proc/`docker inspect --format='{{ .State.Pid }}' \"" + containerName + "\"`/net/dev";
		InputStream commandInfoStream = getCommandInfoStream(hostname, username, password, command);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(commandInfoStream));
		boolean checked = false;
		float[] netIO = new float[2];
		while (!checked && true) {
			String line;
			try {
				line = bufferedReader.readLine();
				if (line == null)
					break;
				if (line.contains("eth1")) {
					checked = true;
					String[] netSplit = line.split("\\s+");
					netIO[0] = Float.parseFloat(netSplit[2]) / 1024f / 1024f / 1024f;
					netIO[1] = Float.parseFloat(netSplit[10]) / 1024f / 1024f / 1024f;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return netIO;
	}

	public ArrayList<TableContainerresourceusage> getContainersPOJO(String hostname, String username, String password,
			InputStream in) {
		if (in == null) {
			return null;
		}
		BufferedReader reader = null;
		ArrayList<TableContainerresourceusage> arrayList = new ArrayList<>();
		try {
			reader = new BufferedReader(new InputStreamReader(in));
			// container类
			Date date = new Date();
			while (true) {
				String line = reader.readLine();
				if (line == null)
					break;
				TableContainerresourceusage record = new TableContainerresourceusage();
				String[] split = line.split(":");

				record.setContainername(split[0]);

				NumberFormat percentInstance = NumberFormat.getPercentInstance();

				Number parse1 = percentInstance.parse(split[1]);
				record.setCpuusagerate(DataFormats.getInstance().subFloat(parse1.floatValue(), 2));

				String mem = split[2].split("\\s")[0];
				if ("K".equalsIgnoreCase(mem.substring(mem.length() - 3,mem.length() - 2))) {
					mem = mem.substring(0, mem.length() - 3);
					record.setMemusageamount(DataFormats.getInstance().subFloat(Float.parseFloat(mem) / 1024f, 2));
				}else if ("G".equalsIgnoreCase(mem.substring(mem.length() - 3,mem.length() - 2))) {
					mem = mem.substring(0, mem.length() - 3);
					record.setMemusageamount(DataFormats.getInstance().subFloat(Float.parseFloat(mem) * 1024f, 2));
				}else if ("M".equalsIgnoreCase(mem.substring(mem.length() - 3,mem.length() - 2))) {
					mem = mem.substring(0, mem.length() - 3);
					record.setMemusageamount(DataFormats.getInstance().subFloat(Float.parseFloat(mem), 2));
				}else {
					mem = mem.substring(0, mem.length() - 1);
					record.setMemusageamount(DataFormats.getInstance().subFloat(Float.parseFloat(mem) / 1024f / 1024f, 2));
				}
				

				Number parse3 = percentInstance.parse(split[3]);
				record.setMemusagerate(parse3.floatValue());

				// 容器net 资源监控 start
				String[] netArray = split[4].split("/");
				if (netArray[0].trim().endsWith("MB")) {
					record.setNetinput(
							Float.parseFloat(netArray[0].trim().substring(0, netArray[0].trim().length() - 2)));
				} else if (netArray[0].trim().endsWith("kB")) {
					record.setNetinput(
							Float.parseFloat(netArray[0].trim().substring(0, netArray[0].trim().length() - 2)) / 1024f);
				} else if (netArray[0].trim().endsWith("GB")) {
					record.setNetinput(
							Float.parseFloat(netArray[0].trim().substring(0, netArray[0].trim().length() - 2)) * 1024f);
				} else if (netArray[0].trim().endsWith("B") && !(netArray[0].trim().endsWith("GB")
						|| netArray[0].trim().endsWith("kB") || netArray[0].trim().endsWith("MB"))) {
					record.setNetinput(
							Float.parseFloat(netArray[0].trim().substring(0, netArray[0].trim().length() - 1)) / 1024f
									/ 1024f);
				}
				if (netArray[1].trim().endsWith("MB")) {
					record.setNetoutput(
							Float.parseFloat(netArray[1].trim().substring(0, netArray[1].trim().length() - 2)));
				} else if (netArray[1].trim().endsWith("kB")) {
					record.setNetoutput(
							Float.parseFloat(netArray[1].trim().substring(0, netArray[1].trim().length() - 2)) / 1024f);
				} else if (netArray[1].trim().endsWith("GB")) {
					record.setNetoutput(
							Float.parseFloat(netArray[1].trim().substring(0, netArray[1].trim().length() - 2)) * 1024f);
				} else if (netArray[1].trim().endsWith("B") && !(netArray[1].trim().endsWith("GB")
						|| netArray[1].trim().endsWith("kB") || netArray[1].trim().endsWith("MB"))) {
					record.setNetoutput(
							Float.parseFloat(netArray[1].trim().substring(0, netArray[1].trim().length() - 1)) / 1024f
									/ 1024f);
				}
//				record.setNetinput(
//						Float.parseFloat(netArray[0].trim().substring(0, netArray[0].trim().length() - 2)) * 1024f);
//				record.setNetoutput(Float.parseFloat(netArray[1].trim().substring(0, netArray[1].trim().length() - 1))
//						/ 1024f / 1024f);
//				float[] containerNetInfoStream = getContainerNetInfoStream(hostname, username, password, split[0]);
//				record.setNetinput(containerNetInfoStream[1]);
//				record.setNetoutput(containerNetInfoStream[0]);
				// 容器net 资源监控 end
				String[] ioArray = split[5].split("/");
				if (ioArray[0].trim().endsWith("MB")) {
					record.setIoinput(Float.parseFloat(ioArray[0].trim().substring(0, ioArray[0].trim().length() - 2)));
				} else if (ioArray[0].trim().endsWith("kB")) {
					record.setIoinput(
							Float.parseFloat(ioArray[0].trim().substring(0, ioArray[0].trim().length() - 2)) / 1024f);
				} else if (ioArray[0].trim().endsWith("GB")) {
					record.setIoinput(
							Float.parseFloat(ioArray[0].trim().substring(0, ioArray[0].trim().length() - 2)) * 1024f);
				} else if (ioArray[0].trim().endsWith("B") && !(ioArray[0].trim().endsWith("GB")
						|| ioArray[0].trim().endsWith("kB") || ioArray[0].trim().endsWith("MB"))) {
					record.setIoinput(Float.parseFloat(ioArray[0].trim().substring(0, ioArray[0].trim().length() - 1))
							/ 1024f / 1024f);
				}
				if (ioArray[1].trim().endsWith("MB")) {
					record.setIooutput(
							Float.parseFloat(ioArray[1].trim().substring(0, ioArray[1].trim().length() - 2)));
				} else if (ioArray[1].trim().endsWith("kB")) {
					record.setIooutput(
							Float.parseFloat(ioArray[1].trim().substring(0, ioArray[1].trim().length() - 2)) / 1024f);
				} else if (ioArray[1].trim().endsWith("GB")) {
					record.setIooutput(
							Float.parseFloat(ioArray[1].trim().substring(0, ioArray[1].trim().length() - 2)) * 1024f);
				} else if (ioArray[1].trim().endsWith("B") && !(ioArray[1].trim().endsWith("GB")
						|| ioArray[1].trim().endsWith("kB") || ioArray[1].trim().endsWith("MB"))) {
					record.setIooutput(Float.parseFloat(ioArray[1].trim().substring(0, ioArray[1].trim().length() - 1))
							/ 1024f / 1024f);
				}
				record.setCollecttime(date);
				arrayList.add(record);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return arrayList;
	}
}

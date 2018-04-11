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
import scs.pojo.TableAppresourceusage;
import scs.pojo.TableContainerresourceusage;
import scs.service.monitor.app.AppMonitor;
import scs.service.monitor.containers.ContainerMonitor;
import scs.util.repository.Repository;

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

	public InputStream getContainerInfoStream(String hostname, String username, String password) {
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

			sess.execCommand(
					"sudo docker stats --no-stream --format \"{{.Name}}:{{.CPUPerc}}:{{.MemUsage}}:{{.MemPerc}}:{{.NetIO}}:{{.BlockIO}}\"");

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
	}

	public ArrayList<TableContainerresourceusage> getContainersPOJO(InputStream in) {
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
				record.setCpuusagerate(parse1.floatValue());

				String mem = split[2].split("\\s")[0];
				mem = mem.substring(0, mem.length() - 3);
				record.setMemusageamount(Float.parseFloat(mem));

				Number parse3 = percentInstance.parse(split[3]);
				record.setMemusagerate(parse3.floatValue());

				record.setBlockio(0f);
				record.setIousagerate(0f);
				record.setNetusagerate(0f);
				
				record.setCollecttime(date);
				
				arrayList.add(record);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return arrayList;
	}
}

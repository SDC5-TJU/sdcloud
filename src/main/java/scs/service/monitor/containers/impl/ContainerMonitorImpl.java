package scs.service.monitor.containers.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
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
import scs.util.tools.SSHConnection;

@Service("containerMonitor")
public class ContainerMonitorImpl implements ContainerMonitor {
	private SSHConnection connection=SSHConnection.getInstance();
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
	}

	private InputStream getCommandInfoStream(String hostname, String username, String password, String command) {
		try { 
			//Connection conn = new Connection(host);

			//conn.connect();

			//boolean isAuthenticated = conn.authenticateWithPassword(user, passwd);

			//if (isAuthenticated == false)
			//	throw new IOException("Authentication failed.");

			Connection conn=SSHConnection.getInstance().getConn(hostname);
			Session sess = conn.openSession();
			sess.execCommand(command);

			InputStream stdout = new StreamGobbler(sess.getStdout()); 
			return stdout;

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(2);
			return null;
		} 
	}

	public InputStream getContainerInfoStream(String hostname, String username, String password) {
		String command = "sudo docker stats --no-stream --format \"{{.Name}}:{{.CPUPerc}}:{{.MemUsage}}:{{.MemPerc}}:{{.NetIO}}:{{.BlockIO}}\"";
		return getCommandInfoStream(hostname, username, password, command);
	}

	public float[] getContainerNetInfoStream(String hostname, String username, String password, String containerName) {
		String command = "cat /proc/`docker inspect --format='{{ .State.Pid }}' \"" + containerName + "\"`/net/dev";
		Connection conn=connection.getConn(hostname);
		Session sess=null;
		try {
			sess = conn.openSession();
			sess.execCommand(command);  
			InputStream in = new StreamGobbler(sess.getStdout());  
			LineNumberReader input = new LineNumberReader(new InputStreamReader(in));   
			String line = null; 
			int i=0;
			float[] netIO = new float[2];
			while ((line = input.readLine()) != null) {
				i++;
				if(i==5){ 
					String[] netSplit = line.split("\\s+");
					netIO[0] = Float.parseFloat(netSplit[2])/1024f; 
					netIO[1] = Float.parseFloat(netSplit[10])/1024f; 
					break;
				} 
			}
			return netIO;
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(sess!=null)
				sess.close(); 
		}
		return null; 
	}
//	public Map<String,TwoTuple<Float,Float>> getContainerNetUsage(){
//		Map<String,TwoTuple<Float,Float>> containerNetUseMap=new HashMap<String,TwoTuple<Float,Float>>();
//		Set<String> containerNameSet=Repository.containerInfoMap.keySet();
//		String command="";
//		String hostName="192.168.1.128";
//		for(String containerName:containerNameSet){  
//			System.out.println(Repository.containerInfoMap.get(containerName).getContainerHostName());
//			if(Repository.containerInfoMap.get(containerName).getContainerHostName().contains("6")){
//				hostName="192.168.1.147";
//			}
//			long start=System.currentTimeMillis();
//			 
//			Connection conn=connection.getConn(hostName);
//			Session sess=null;
//			try {
//				sess=conn.openSession();
//				command="cat /proc/`docker inspect --format='{{ .State.Pid }}' \""+containerName+"\"`/net/dev";
//				sess.execCommand(command);  
//				InputStream is = new StreamGobbler(sess.getStdout());//获得标准输出流
//		        BufferedReader brs = new BufferedReader(new InputStreamReader(is));
//				 
//				
//		        long start1=System.currentTimeMillis();
//				System.out.println("start1="+(start1-start));
//				String line = null; 
//				int i=0;
//				float[] netIO = new float[2];
//				 
//				line = brs.readLine();  
//				long start2=System.currentTimeMillis();
//				System.out.println("start2="+(start2-start1));
//				while (line != null) {  
//					i++;
//					if(i==5){ 
//						String[] netSplit = line.split("\\s+");
//						netIO[0] = Long.parseLong(netSplit[2])>>20; 
//						netIO[1] = Long.parseLong(netSplit[10])>>20; 
//						break;
//					}  
//					line=brs.readLine();  
//				} 
//				 
//				containerNetUseMap.put(containerName,new TwoTuple<Float,Float>(netIO[0],netIO[1]));
//			} catch (IOException e) {
//				e.printStackTrace();
//			}finally{
//				if(sess!=null)
//					sess.close(); 
//			}  
//			long end=System.currentTimeMillis();
//			System.out.println("for="+(end-start));
//		}
//		return containerNetUseMap; 
//	}
	
	
	public ArrayList<TableContainerresourceusage> getContainersPOJO(String hostname, String username, String password,
			InputStream input) {
	//	Map<String,TwoTuple<Float,Float>> containerNetUseMap=this.getContainerNetUsage(); 
		String command = "sudo docker stats --no-stream --format \"{{.Name}}:{{.CPUPerc}}:{{.MemUsage}}:{{.MemPerc}}:{{.NetIO}}:{{.BlockIO}}\"";
		Session sess=null;
		try {
			Connection conn=connection.getConn(hostname);
			sess = conn.openSession();
			sess.execCommand(command); 
//			InputStream in = new StreamGobbler(sess.getStdout()); 
			if (input == null) {
				System.out.println("docker stats没有取到值");
			}
			BufferedReader bfReader = new BufferedReader(new InputStreamReader(input));
			Date date = new Date();
			ArrayList<TableContainerresourceusage> arrayList = new ArrayList<>();
			String line = null; 
			while ((line = bfReader.readLine()) != null) {
				TableContainerresourceusage record = new TableContainerresourceusage(); 
				String[] split = line.split(":");

				record.setContainername(split[0]);
				NumberFormat percentInstance = NumberFormat.getPercentInstance();

				Number parse1 = percentInstance.parse(split[1]);
				record.setCpuusagerate(DataFormats.getInstance().subFloat(parse1.floatValue(), 4));

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
				record.setMemusagerate(DataFormats.getInstance().subFloat(parse3.floatValue(), 4));

				// 容器net 资源监控 start
				/*String[] netArray = split[4].split("/");
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
				}*/
				//record.setNetinput(Float.parseFloat(netArray[0].trim().substring(0, netArray[0].trim().length() - 2)) * 1024f);
				//record.setNetoutput(Float.parseFloat(netArray[1].trim().substring(0, netArray[1].trim().length() - 1))/ 1024f / 1024f);

				// 容器net 资源监控 end
				/*
				 * 采集容器io使用
				 */
				
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
				//record.setNetinput(containerNetUseMap.get(split[0]).second);
				//record.setNetoutput(containerNetUseMap.get(split[0]).first);
				
				//float[] containerNetInfoStream = getContainerNetInfoStream(hostname, username, password, split[0]);
				//record.setNetinput(containerNetInfoStream[1]);
				//record.setNetoutput(containerNetInfoStream[0]);  
				record.setNetinput(0.0f);
				record.setNetoutput(0.0f);  
				record.setCollecttime(date);
				arrayList.add(record);
			} 
 
			return arrayList;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(sess!=null)
				sess.close(); 
		}
		return null;
	}

}

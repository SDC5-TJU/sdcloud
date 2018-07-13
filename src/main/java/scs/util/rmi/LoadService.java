package scs.util.rmi; 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;


public class LoadService {
	private static LoadService loadService=null;
	private LoadService(){}
	public synchronized static LoadService getInstance() {
		if (loadService == null) {
			loadService = new LoadService();
		}
		return loadService;
	}  
	public void service(String ip,int port) {
		try {
			killProcess(getProcessId(port));//清除之前的端口占用

			System.setProperty("java.rmi.server.hostname",ip);
			LocateRegistry.createRegistry(port);
			LoadInterface load = new LoadInterfaceImpl();  
			Naming.rebind("rmi://"+ip+":"+port+"/load", load);
			System.out.println("load "+ip+":"+port+" rmi 服务端开启 ");

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO: handle exception
		}
	}

	/**
	 * 查找pid
	 * @param port 端口号
	 * @return pid
	 */ 
	private String getProcessId(int port){
		String processIdStr="99999999";
		String[] cmd = {"/bin/sh","-c","netstat -apn|grep "+port}; 
		String lastStr="";
		String line = null,err;
		try { 
			Process process = Runtime.getRuntime().exec(cmd); 
			BufferedReader br = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			InputStreamReader isr = new InputStreamReader(process.getInputStream());
			LineNumberReader input = new LineNumberReader(isr); 
			while (((err = br.readLine()) != null||(line = input.readLine()) != null)) {
				if(err==null){
					System.out.println(line);
					lastStr=line;
				}else{
					System.out.println(err);
				}
			}
		}catch (IOException e) { 
			e.printStackTrace(); 
		}  
		if(lastStr!=null&&lastStr.contains("EN")){
			processIdStr= lastStr.trim().split("EN")[1].trim().replace("/java","");
		}
		return processIdStr;
	}
	/**
	 * 杀掉进程
	 * @param pid pid号
	 */
	private void killProcess(String pid){ 
		System.out.println("kill "+pid);  
		String line = null,err;
		try { 
			Process process = Runtime.getRuntime().exec("kill -9 "+pid); 
			BufferedReader br = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			InputStreamReader isr = new InputStreamReader(process.getInputStream());
			LineNumberReader input = new LineNumberReader(isr); 
			while (((err = br.readLine()) != null||(line = input.readLine()) != null)) {
				if(err==null){
					System.out.println(line); 
				}else{
					System.out.println(err);
				}
			}
		}catch (IOException e) { 
			e.printStackTrace(); 
		}  
	}

}

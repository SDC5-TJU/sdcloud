package scs.util.jobSchedul.jobImpl.scimark;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException; 
import scs.util.rmi.ScimarkInterface;

public class ExecuteThread extends Thread{ 
	private String rmiUrl; 
	private int intensity; 

	/**
	 * 线程构造方法
	 * @param httpclient httpclient对象
	 * @param url 要访问的链接
	 * @param timeRecords 响应时间记录数组
	 */
	public ExecuteThread(String rmiUrl,int intensity){ 
		this.rmiUrl=rmiUrl;
		this.intensity=intensity;
	}

	@Override
	public void run(){  
		// System.out.println(name+" start "+sd.format(System.currentTimeMillis()));
		try {
			ScimarkInterface scimark = (ScimarkInterface) Naming.lookup(rmiUrl);
			int i=scimark.execute(intensity); 
			System.out.println("thread"+i+"-----");
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
		 

	}



}

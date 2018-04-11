package scs.util.jobSchedul.jobImpl.webSearch;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import scs.pojo.TwoTuple;
import scs.util.rmi.WorkerInterface;

public class ExecuteThread extends Thread{ 
	private List<TwoTuple<Long, Integer>> list;
	private String rmiUrl;
	private String applicationName; 
	private int requestCount; 
	private int warmUpCount;
	private String pattern;
	private int intensity;

	/**
	 * 线程构造方法
	 * @param httpclient httpclient对象
	 * @param url 要访问的链接
	 * @param timeRecords 响应时间记录数组
	 */
	public ExecuteThread(List<TwoTuple<Long, Integer>> list,String rmiUrl,String applicationName,int requestCount,int warmUpCount,String pattern,int intensity){ 
		this.list=list; 
		this.rmiUrl=rmiUrl;
		this.applicationName=applicationName;
		this.requestCount=requestCount;
		this.warmUpCount=warmUpCount;
		this.pattern=pattern;
		this.intensity=intensity;
	}

	@Override
	public void run(){  
		// System.out.println(name+" start "+sd.format(System.currentTimeMillis()));
		try {
			WorkerInterface worker=(WorkerInterface) Naming.lookup(rmiUrl);
			List<TwoTuple<Long, Integer>> curList=worker.execute(applicationName,requestCount,warmUpCount,pattern,intensity);
			list.addAll(curList);
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

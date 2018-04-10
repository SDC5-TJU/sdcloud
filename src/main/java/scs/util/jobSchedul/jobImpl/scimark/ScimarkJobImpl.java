package scs.util.jobSchedul.jobImpl.scimark;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors; 
import scs.pojo.AppConfigBean; 
import scs.util.jobSchedul.JobInterface; 
import scs.util.repository.Repository;
import scs.util.rmi.ScimarkInterface; 
/**
 * cassandra应用控制实现类
 * @author 
 *
 */
public class ScimarkJobImpl implements JobInterface{ 
	private AppConfigBean config;   
	private String[] rmiUrl=new String[4];  
	private static ScimarkJobImpl instance=null; 
	private ScimarkJobImpl(){}
	public synchronized static ScimarkJobImpl getInstance() {
		if (instance == null) {  
			instance = new ScimarkJobImpl();
		}  
		return instance;
	}
	@Override
	public void init(int isBase) {
		Properties prop = new Properties();
		InputStream is = ScimarkJobImpl.class.getResourceAsStream("/conf/sys.properties");
		try {
			prop.load(is);
		} catch (IOException e) { 
			e.printStackTrace();
		} 
		this.rmiUrl[0]=prop.getProperty("rmi_scimark01_url").trim();//读取scimark rmi的路径 
		this.rmiUrl[1]=prop.getProperty("rmi_scimark02_url").trim();//读取scimark rmi的路径 
		this.rmiUrl[2]=prop.getProperty("rmi_scimark03_url").trim();//读取scimark rmi的路径 
		this.rmiUrl[3]=prop.getProperty("rmi_scimark04_url").trim();//读取scimark rmi的路径 
		this.config=Repository.appConfigMap.get("scimark");
		// TODO Auto-generated method stub

	}

	@Override
	public void start(int isBase) { 
		long start=System.currentTimeMillis(); 
		ExecutorService executor = Executors.newFixedThreadPool(4);
		for(int i=0;i<4;i++){
			System.out.println(rmiUrl[i]);
			executor.execute(new ExecuteThread(rmiUrl[i],Integer.parseInt(config.getIntensity())));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		executor.shutdown();//停止提交任务

		//检测全部的线程是否都已经运行结束
		long nowTime=System.currentTimeMillis();
		while(!executor.isTerminated()&&((nowTime-start)<300000)){
			try {
				Thread.sleep(5000);
			} catch(InterruptedException e){
				e.printStackTrace();
			}
			nowTime=System.currentTimeMillis();
		}
		executor.shutdownNow();  
		System.out.println("结束啦");
	}

	@Override
	public void shutdown() {

	}


	@Override
	public int processResult(int isBase) {
		// TODO Auto-generated method stub

		return 0;
	}



}

package scs.util.jobSchedul.jobImpl.webSearch;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import scs.pojo.AppConfigBean;
import scs.pojo.TwoTuple;
import scs.util.jobSchedul.JobInterface;
import scs.util.jobSchedul.jobImpl.webServer.ExecuteThread;
import scs.util.jobSchedul.jobImpl.webServer.WebServerJobImpl;
import scs.util.repository.Repository;
import scs.util.rmi.WorkerInterface; 
/**
 * cassandra应用控制实现类
 * @author 
 *
 */
public class WebSearchJobImpl implements JobInterface{
	private final int WORKER_COUNT=2;
	private AppConfigBean config;   
	private String[] rmiUrl=new String[WORKER_COUNT];  //rmi url worker01 
 
	private static WebSearchJobImpl instance=null; 
	private WebSearchJobImpl(){}
	public synchronized static WebSearchJobImpl getInstance() {
		if (instance == null) {  
			instance = new WebSearchJobImpl();
		}  
		return instance;
	}
	@Override
	public void init(int isBase) {
		Properties prop = new Properties();
		InputStream is = WebServerJobImpl.class.getResourceAsStream("/conf/sys.properties");
		try {
			prop.load(is);
		} catch (IOException e) { 
			e.printStackTrace();
		}
		this.rmiUrl[0]=prop.getProperty("rmi_webSearch01_url").trim();//读取worker rmi url的路径 
		this.rmiUrl[1]=prop.getProperty("rmi_webSearch02_url").trim();//读取worker rmi url的路径 
		this.config=Repository.appConfigMap.get("webSearch");

		if(isBase==1){
			Repository.webSearchBaseDataList.clear();//基准测试 
			System.out.println("清空webSearch Basedata size="+Repository.webSearchBaseDataList.size());
		}else{
			Repository.webSearchDataList.clear();//正式测试
			System.out.println("清空webSearch data size="+Repository.webSearchDataList.size());
		} 
	}
	@Override
	public void start(int isBase) {
		String applicationName=config.getApplicationName();
		int requestCount=Integer.parseInt(config.getRequestCount());
		int warmUpCount=Integer.parseInt(config.getWarmUpCount());
		String pattern=config.getPattern();
		int intensity=Integer.parseInt(config.getIntensity()); 
		
		long start=System.currentTimeMillis();
		List<TwoTuple<Long, Integer>> list=new ArrayList<TwoTuple<Long, Integer>>();
		ExecutorService executor = Executors.newFixedThreadPool(WORKER_COUNT);
		for(int i=0;i<WORKER_COUNT;i++){
			System.out.println(rmiUrl[i]);
			executor.execute(new ExecuteThread(list,rmiUrl[i],applicationName,requestCount,warmUpCount,pattern,intensity));
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
		 	if(list.size()!=0){
				if(isBase==1){
					Repository.webSearchBaseDataList.addAll(list);//基准测试 
					System.out.println("赋值 webSearch Basedata size="+Repository.webSearchBaseDataList.size());
				}else{
					Repository.webSearchDataList.addAll(list);//正式测试
					System.out.println("赋值 webSearch data size="+Repository.webSearchDataList.size());
				}  
			}
	}

	@Override
	public void shutdown() {

	} 

	@Override
	public int processResult(int isBase) {
		int resultSize=0;
		// TODO Auto-generated method stub 
		if(isBase==1){
			resultSize=Repository.webSearchBaseDataList.size();//基准测试 
		}else{
			resultSize=Repository.webSearchDataList.size();//正式测试
		}  
		return resultSize;
	} 
}

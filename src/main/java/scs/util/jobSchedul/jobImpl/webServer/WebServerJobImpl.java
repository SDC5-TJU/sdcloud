package scs.util.jobSchedul.jobImpl.webServer;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Properties; 
import scs.pojo.AppConfigBean;
import scs.pojo.TwoTuple;
import scs.util.jobSchedul.JobInterface; 
import scs.util.repository.Repository;
import scs.util.rmi.WorkerInterface; 
/**
 * WebServer应用控制实现类
 * @author 
 *
 */
public class WebServerJobImpl implements JobInterface{
	private AppConfigBean config;   
	private String rmiUrl;  //rmi url
	
	private static WebServerJobImpl instance=null; 
	private WebServerJobImpl(){}
	public synchronized static WebServerJobImpl getInstance() {
		if (instance == null) {  
			instance = new WebServerJobImpl();
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
		this.rmiUrl=prop.getProperty("rmi_worker_url").trim();//读取cassandra脚本的路径 
		this.config=Repository.appConfigMap.get("webServer");  
		if(isBase==1){
			Repository.webServerBaseDataList.clear();//基准测试 
			System.out.println("清空webServer Basedata size="+Repository.webServerBaseDataList.size());
		}else{
			Repository.webServerDataList.clear();//正式测试
			System.out.println("清空webServer data size="+Repository.webServerDataList.size());
		} 
	}
	@Override
	public void start(int isBase) {  
		String applicationName=config.getApplicationName();
		int requestCount=Integer.parseInt(config.getRequestCount());
		int warmUpCount=Integer.parseInt(config.getWarmUpCount());
		String pattern=config.getPattern();
		int intensity=Integer.parseInt(config.getIntensity()); 
		try {
			System.out.println(rmiUrl);
			WorkerInterface worker=(WorkerInterface) Naming.lookup(rmiUrl);
			List<TwoTuple<Long, Integer>> list=worker.execute(applicationName,requestCount,warmUpCount,pattern,intensity);
			System.out.println("result="+list.size());
			if(list.size()!=0){
				if(isBase==1){
					Repository.webServerBaseDataList.addAll(list);//基准测试 
					System.out.println("赋值 webServer Basedata size="+Repository.webServerBaseDataList.size());
				}else{
					Repository.webServerDataList.addAll(list);//正式测试
					System.out.println("赋值 webServer data size="+Repository.webServerDataList.size());
				}  
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			resultSize=Repository.webServerBaseDataList.size();//基准测试 
		}else{
			resultSize=Repository.webServerDataList.size();//正式测试
		}  
		return resultSize;
	}
 
}
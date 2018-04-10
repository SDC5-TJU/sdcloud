package scs.util.jobSchedul.jobImpl.webSearch;

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
import scs.util.jobSchedul.jobImpl.webServer.WebServerJobImpl;
import scs.util.repository.Repository;
import scs.util.rmi.WorkerInterface; 
/**
 * cassandra应用控制实现类
 * @author 
 *
 */
public class WebSearchJobImpl implements JobInterface{
	private AppConfigBean config;   
	private String rmiUrl;

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
		this.rmiUrl=prop.getProperty("rmi_worker_url").trim();//读取cassandra脚本的路径 
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
		try { 
			WorkerInterface worker =(WorkerInterface) Naming.lookup(rmiUrl);
			List<TwoTuple<Long, Integer>> list=worker.execute(applicationName,requestCount,warmUpCount,pattern,intensity);
			if(list.size()!=0){
				if(isBase==1){
					Repository.webSearchBaseDataList.addAll(list);//基准测试 
					System.out.println("赋值 webSearch Basedata size="+Repository.webSearchBaseDataList.size());
				}else{
					Repository.webSearchDataList.addAll(list);//正式测试
					System.out.println("赋值 webSearch data size="+Repository.webSearchDataList.size());
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
			resultSize=Repository.webSearchBaseDataList.size();//基准测试 
		}else{
			resultSize=Repository.webSearchDataList.size();//正式测试
		}  
		return resultSize;
	} 
}

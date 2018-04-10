package scs.util.jobSchedul.jobImpl.memcached;

import java.io.IOException;
import java.io.InputStream; 
import java.util.Properties; 
import scs.pojo.AppConfigBean;
import scs.util.jobSchedul.JobInterface; 
import scs.util.repository.Repository;
/**
 * memcached应用控制实现类
 * @author yanan
 *
 */
public class MemcachedJobImpl implements JobInterface{
	private AppConfigBean config;
	private String cmd; 
	private String scriptDir,resultDir;
	private int warmUpTime;

	private static MemcachedJobImpl instance=null;
	private MemcachedJobImpl(){}
	public synchronized static MemcachedJobImpl getInstance() {
		if (instance == null) {  
			instance = new MemcachedJobImpl();
		}  
		return instance;
	}
 
	@Override
	public void init(int isBase) {  
		Properties prop = new Properties();
		InputStream is = MemcachedJobImpl.class.getResourceAsStream("/conf/sys.properties");
		try {
			prop.load(is);
		} catch (IOException e) { 
			e.printStackTrace();
		}
		this.scriptDir=prop.getProperty("sdc_script_dir").trim();//读取memcached脚本的路径
		this.resultDir=prop.getProperty("mem_result_dir").trim();//读取memcached结果的路径
		this.config=Repository.appConfigMap.get("memcached");
		if(isBase==1){
			Repository.memcachedBaseDataList.clear();//基准测试 
			System.out.println("清空mem Basedata size="+Repository.memcachedBaseDataList.size());
		}else{
			Repository.memcachedDataList.clear();//正式测试
			System.out.println("清空mem data size="+Repository.memcachedDataList.size());
		}
		
		
	}
	@Override
	public void start(int isBase) {
		int requestCount=Integer.parseInt(config.getRequestCount());//请求总数
		int warmUpCount=Integer.parseInt(config.getWarmUpCount());//预热次数
		int intensity=Integer.parseInt(config.getIntensity()); //每秒钟请求数QPS
		warmUpTime=warmUpCount/intensity+1;
		int requestTime=requestCount/intensity+1; 
		cmd="sh "+scriptDir+"memcached/StartContainer.sh "+intensity; 
		System.out.println(cmd);
		try {
			Runtime.getRuntime().exec(cmd); 
		}catch (IOException e) { 
			e.printStackTrace();
		}
		/**
		 * 睡眠等待请求总数达到指定值
		 */
		int sleepTime=(warmUpTime+requestTime+20)*1000; //20秒是客户端连接时间
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) { 
			e.printStackTrace();
		} 
		this.shutdown();//在请求数量达到后关闭memcached 客户端访问进程

	}

	@Override
	public void shutdown() {
		cmd="sh "+scriptDir+"memcached/ShutContainer.sh";  
		System.out.println(cmd);
		try {
			Runtime.getRuntime().exec(cmd);
		}catch (IOException e) { 
			e.printStackTrace();
		}

	}
	@Override
	public int processResult(int isBase) {
		// TODO Auto-generated method stub
		int resultSize=0;
		try {
			resultSize=ResultProcess.getInstance().process(resultDir,warmUpTime,isBase);//解析结果文件
		} catch (IOException e) { 
			e.printStackTrace();
		}
		return resultSize;
	}

}

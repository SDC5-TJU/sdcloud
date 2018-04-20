package scs.util.jobSchedul;
 
import scs.util.format.DateFormats;
import scs.util.jobSchedul.jobImpl.bonnie.BonnieJobImpl;
import scs.util.jobSchedul.jobImpl.cassandra.CassandraJobImpl;
import scs.util.jobSchedul.jobImpl.hadoop.HadoopJobImpl;
import scs.util.jobSchedul.jobImpl.memcached.MemcachedJobImpl;
import scs.util.jobSchedul.jobImpl.scimark.ScimarkJobImpl;
import scs.util.jobSchedul.jobImpl.silo.SiloJobImpl;
import scs.util.jobSchedul.jobImpl.webSearch.WebSearchJobImpl;
import scs.util.jobSchedul.jobImpl.webServer.WebServerJobImpl;
import scs.util.repository.Repository;
import scs.util.repository.RepositoryDao;
/**
 * 应用调度配置驱动类
 * @author yanan
 *
 */
public class JobSchedulDriver {
	private static JobSchedulDriver driver=null;
	private JobSchedulDriver(){}
	public synchronized static JobSchedulDriver getInstance() {
		if (driver == null) {  
			driver = new JobSchedulDriver();
		}  
		return driver;
	}
	/**
	 * 外部调用方法
	 * @param applicationName 应用名称
	 * @param isBase 是否为基准测试 0|1
	 * @return 结果集数量
	 */
	public int execute(String appName,int isBase){
		int resultSize=0;  
		if(Repository.appStatusMap.get(appName)==true) return 0;//如果应用正在运行,说明系统异常,返回0  
	
		Repository.appStatusMap.put(appName,true);//标记该作业执行状态为正在执行  
		RepositoryDao.updateExecuteRecord(appName,DateFormats.getInstance().getNowDate(),"开始",isBase);
		JobInterface instance=this.getJobExecuteInstance(appName);
		instance.init(isBase);//初始化各种参数,读取配置文件等
		instance.start(isBase);
		resultSize=instance.processResult(isBase);//返回结果数量给调用者 
		/*try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		if(Repository.appConfigMap.get(appName).getApplicationType().equals("autoStop")){
			Repository.appStatusMap.put(appName,false);//执行完毕,标记该作业执行状态为未执行
			RepositoryDao.updateExecuteRecord(appName,DateFormats.getInstance().getNowDate(),"结束",isBase);
		} 
		return resultSize;

	}
	/**
	 * 外部调用方法
	 * @param applicationName 应用名称
	 * @param isBase 是否为基准测试 0|1
	 * @return 结果集数量
	 */
	public int shutdown(String appName){
		if(Repository.appStatusMap.get(appName)==false) return 1;//如果应用正在运行,说明没有运行,返回1 
	 
		JobInterface instance=this.getJobExecuteInstance(appName);
		instance.shutdown(); 
		/*try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		Repository.appStatusMap.put(appName,false);//执行完毕,标记该作业执行状态为未执行
		RepositoryDao.updateExecuteRecord(appName,DateFormats.getInstance().getNowDate(),"结束",-1);
		
		return 1;

	}
	/**
	 * 
	 * @param applicationName
	 * @return
	 */
	private JobInterface getJobExecuteInstance(String appName){
		JobInterface instance=null;
		switch (appName){
		case "memcached":
			instance=MemcachedJobImpl.getInstance();
			break; 
		case "webServer":
			instance=WebServerJobImpl.getInstance();
			break;
		case "webSearch":
			instance=WebSearchJobImpl.getInstance();
			break;
		case "silo":
			instance=SiloJobImpl.getInstance();
			break; 
		case "hadoop":
			instance=HadoopJobImpl.getInstance();
			break;
		case "scimark":
			instance=ScimarkJobImpl.getInstance();
			break;
		case "bonnie":
			instance=BonnieJobImpl.getInstance();
			break;
		case "cassandra":
			instance=CassandraJobImpl.getInstance();
			break;
		}
		return instance; 
	}


}

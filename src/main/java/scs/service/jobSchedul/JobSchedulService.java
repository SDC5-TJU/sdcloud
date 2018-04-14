package scs.service.jobSchedul;
  
/**
 * 登录service层接口
 * @author YangYanan
 * @desc
 * @date 2017-8-18
 */
public interface JobSchedulService {  
	public int executeMemcachedApp(int isBase); 
	public int executeWebSearchApp(int isBase);
	public int executeWebServerApp(int isBase);
	public int executeSiloApp(int isBase);
	public int executeCassandraApp(int isBase);
	
	public int executeBonnieApp();  
	public int shutdownBonnieApp(); 
	
	public int executeScimarkApp(); 
	
	public int executeHadoopApp();
	public int executeCassandraApp();
	
	public int getWebSearchQueryTime();
	public int getWebServerQueryTime();
	public String getAppStatus();
}

package scs.service.jobSchedul;

import java.util.List;

import scs.pojo.AppConfigBean;

/**
 * 登录service层接口
 * @author YangYanan
 * @desc
 * @date 2017-8-18
 */
public interface JobSchedulService { 
	public List<AppConfigBean> getEnableAppConfigList();
	public int executeMemcachedApp(int isBase); 
	public int executeWebSearchApp(int isBase);
	public int executeWebServerApp(int isBase);
	public int executeSiloApp(int isBase);
	
	public int executeBonnieApp();  
	public int shutdownBonnieApp(); 
	
	public int executeScimarkApp();
	public int shutdownScimarkApp(); 
	
	public int executeHadoopApp();
	public int executeCassandraApp();
	
	public int getWebSearchQueryTime();
	public int getWebServerQueryTime();
	public String getAppStatus();
}

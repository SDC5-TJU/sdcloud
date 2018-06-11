package scs.service.jobSchedul;

import java.util.List;

import scs.pojo.TwoTuple;
import scs.pojo.XapianDataBean;

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
	public int executeXapianApp(int isBase);
	public int executeRedisApp(int isBase);
	public int executeCassandraApp(int isBase);
	
	public int executeBonnieApp();  
	public int shutdownBonnieApp(); 
	
	public int executeScimarkApp(); 
	public int executeDwarfApp();
	public int executeHadoopApp();

	public int getWebSearchQueryTime();
	public int getWebServerQueryTime();
	public String getAppStatus();
	
	public List<XapianDataBean> getRiscvXapianResult(String filePath);
	public List<TwoTuple<Long, Integer>> getRiscvRedisResult(String filePath);
}

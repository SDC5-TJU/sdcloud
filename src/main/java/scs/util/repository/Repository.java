package scs.util.repository;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import scs.pojo.AppConfigBean;
import scs.pojo.AppInfoBean;
import scs.pojo.ContainerInfoBean;
import scs.pojo.MemcachedDataBean;
import scs.pojo.SiloDataBean;
import scs.pojo.TwoTuple; 
 
public class Repository{ 
	private static Repository repository=null;
	private Repository(){}
	public synchronized static Repository getInstance() {
		if (repository == null) {  
			repository = new Repository();
		}  
		return repository;
	}  
	/**
	 * 作业调度模块配置信息变量
	 */
	public static Map<String,AppConfigBean> appConfigMap=new HashMap<String,AppConfigBean>();
	public static Map<String,AppInfoBean> appInfoMap=new HashMap<String,AppInfoBean>();
	public static Map<String,Boolean> appStatusMap=new HashMap<String,Boolean>();
	public static Map<String,ContainerInfoBean> containerInfoMap=new HashMap<String,ContainerInfoBean>();
	
	/**
	 * 应用数据存储数组
	 */
	public static List<MemcachedDataBean> memcachedDataList=new ArrayList<MemcachedDataBean>(); 
	public static List<MemcachedDataBean> memcachedBaseDataList=new ArrayList<MemcachedDataBean>(); 
	public static List<SiloDataBean> siloDataList=new ArrayList<SiloDataBean>(); 
	public static List<SiloDataBean> siloBaseDataList=new ArrayList<SiloDataBean>();
	public static List<TwoTuple<Long, Integer>> webServerDataList=new ArrayList<TwoTuple<Long, Integer>>();
	public static List<TwoTuple<Long, Integer>> webServerBaseDataList=new ArrayList<TwoTuple<Long, Integer>>();
	public static List<TwoTuple<Long, Integer>> webSearchDataList=new ArrayList<TwoTuple<Long, Integer>>();
	public static List<TwoTuple<Long, Integer>> webSearchBaseDataList=new ArrayList<TwoTuple<Long, Integer>>();
	
	static {
		containerInfoMap=RepositoryDao.initContainerInfoMap();//初始化容器信息map 
		System.out.println("初始化 containerInfoMap size="+containerInfoMap.size());
		appInfoMap=RepositoryDao.initAppInfoMap();//初始化app信息map 
		System.out.println("初始化 appInfoMap  size="+appInfoMap.size());
		Set<String> appNameSet = appInfoMap.keySet(); //取出所有应用的名称
		for(String appName:appNameSet){ 
			appStatusMap.put(appName,false);//系统初始化,所有应用默认为未执行
			System.out.println("初始化app执行状态 "+appName+"=false");
		}
		
	}
	/**
	 * 对外提供的app状态查询接口
	 * @return JSON字符串
	 */
	public String getAppStatus(){
		return JSONArray.fromObject(appStatusMap).toString();
	}
	
	

}

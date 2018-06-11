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
import scs.pojo.QueryData;
import scs.pojo.SiloDataBean;
import scs.pojo.SystemInfoBean;
import scs.pojo.TableAppresourceusage;
import scs.pojo.TableContainerresourceusage;
import scs.pojo.TableSystemresourceusage;
import scs.pojo.TwoTuple;
import scs.pojo.XapianDataBean; 
 /**
  * 系统静态仓库类
  * 通过静态变量的形式为系统运行中需要用到的数据提供内存型存储
  * 包括一些系统参数，应用运行数据，控制标志等
  * @author yanan
  *
  */
public class Repository{ 
	private static Repository repository=null;
	private Repository(){}
	public synchronized static Repository getInstance() {
		if (repository == null) {
			repository = new Repository();
		}
		return repository;
	}  
	public static int curTestRecordId=0; //当前测试记录
	public static float EAThreshold=100; //体验可用性99分位数阈值
	public static boolean onlineDataFlag=false; //在线请求查询标志
	/**
	 * 作业调度模块配置信息变量
	 */
	public static Map<String,AppConfigBean> appConfigMap=new HashMap<String,AppConfigBean>(); //应用配置信息map
	public static Map<String,Boolean> appStatusMap=new HashMap<String,Boolean>(); //应用执行状态map

	public static Map<String,AppInfoBean> appInfoMap=new HashMap<String,AppInfoBean>(); //应用信息map
	public static Map<String,ContainerInfoBean> containerInfoMap=new HashMap<String,ContainerInfoBean>();//容器信息map
	public static Map<String,SystemInfoBean> systemInfoMap=new HashMap<String,SystemInfoBean>(); //系统信息map
	
	/**
	 * 在线应用数据存储数组
	 * Base代表基准测试(即无干扰下)
	 */
	public static List<MemcachedDataBean> memcachedDataList=new ArrayList<MemcachedDataBean>(); //memcached应用正式负载测试数据存储数组
	public static List<MemcachedDataBean> memcachedBaseDataList=new ArrayList<MemcachedDataBean>(); //memcached应用基准负载测试数据存储数组
	public static List<SiloDataBean> siloDataList=new ArrayList<SiloDataBean>(); 
	public static List<SiloDataBean> siloBaseDataList=new ArrayList<SiloDataBean>();
	public static List<XapianDataBean> xapianDataList=new ArrayList<XapianDataBean>(); 
	public static List<XapianDataBean> xapianBaseDataList=new ArrayList<XapianDataBean>();
	
	//下面的几个应用采用java rmi远程调用机制 发出的负载,数据采用List<TwoTuple<Long, Integer>>格式返回
	public static List<TwoTuple<Long, Integer>> webServerDataList=new ArrayList<TwoTuple<Long, Integer>>();
	public static List<TwoTuple<Long, Integer>> webServerBaseDataList=new ArrayList<TwoTuple<Long, Integer>>();
	public static List<TwoTuple<Long, Integer>> webSearchDataList=new ArrayList<TwoTuple<Long, Integer>>();
	public static List<TwoTuple<Long, Integer>> webSearchBaseDataList=new ArrayList<TwoTuple<Long, Integer>>();
	public static List<TwoTuple<Long, Integer>> cassandraDataList=new ArrayList<TwoTuple<Long, Integer>>();
	public static List<TwoTuple<Long, Integer>> cassandraBaseDataList=new ArrayList<TwoTuple<Long, Integer>>();
	public static List<TwoTuple<Long, Integer>> redisDataList=new ArrayList<TwoTuple<Long, Integer>>();
	public static List<TwoTuple<Long, Integer>> redisBaseDataList=new ArrayList<TwoTuple<Long, Integer>>();
	
	public static List<QueryData> onlineDataList=new ArrayList<QueryData>();
	
	/*
	 * 物理机/容器/服务 资源监控数组
	 */
	/*
	 * String:containerName
	 */
	public static Map<String,TableContainerresourceusage> containerRealUsageMap=new HashMap<String,TableContainerresourceusage>();
	/*
	 * String:applicationName
	 */
	public static Map<String,TableAppresourceusage> appRealUsageMap=new HashMap<String,TableAppresourceusage>();
	/*
	 * String:hostname
	 */
	public static Map<String,TableSystemresourceusage> systemRealUsageMap=new HashMap<String,TableSystemresourceusage>();
	/*
	 * String:hostname
	 */
	public static float[] bandwidth128 = {0,0f,0,0f};
	public static long time128 = 0l;
	public static float[] bandwidth147 = {0,0f,0,0f};
	public static long time147 = 0l;
	/*
	 * String:hostname
	 */
	public static float[][] cache128 = new float[3][2];
	public static float[][] cache147 = new float[3][2];
	
	
	public static int cronFlag = 0; //默认关闭监控
	public static int PhysicalMachine128 = 1; //物理机1
	public static int PhysicalMachine147 = 2; //物理机2
	
	/**
	 * 静态块
	 */
	static {
//		containerInfoMap=RepositoryDao.initContainerInfoMap();//初始化容器信息map 
//		System.out.println("初始化 containerInfoMap size="+containerInfoMap.size());
//		appInfoMap=RepositoryDao.initAppInfoMap();//初始化app信息map 
//		System.out.println("初始化 appInfoMap  size="+appInfoMap.size());
//		Set<String> appNameSet = appInfoMap.keySet(); //取出所有应用的名称
//		for(String appName:appNameSet){ 
//			appStatusMap.put(appName,false);//系统初始化,所有应用默认为未执行
//			System.out.println("初始化app执行状态 "+appName+"=false");
//		}
//		systemInfoMap=RepositoryDao.initSystemInfoMap();
	}
	/**
	 * 对外提供的app状态查询接口
	 * {app1:"true",app2:"true"...}
	 * 应用正在运行则表示为true,否则为false
	 * @return JSON字符串
	 */
	public String getAppStatus(){
		return JSONArray.fromObject(appStatusMap).toString();
	}
	
	

}

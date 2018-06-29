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
import scs.util.rmi.LoadService; 
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

	/**
	 * 物理机/容器/服务 资源监控数组
	 */
	public static Map<String,TableContainerresourceusage> containerRealUsageMap=new HashMap<String,TableContainerresourceusage>();//容器资源监控数据
	public static Map<String,TableAppresourceusage> appRealUsageMap=new HashMap<String,TableAppresourceusage>();//应用资源监控数据
	public static Map<String,TableSystemresourceusage> systemRealUsageMap=new HashMap<String,TableSystemresourceusage>();//系统资源监控数据

	public static float[] bandwidth128 = {0,0f,0,0f};//内存带宽监控数据 物理机128
	public static long time128 = 0l;
	public static float[] bandwidth147 = {0,0f,0,0f};//内存带宽监控数据 物理机147
	public static long time147 = 0l;

	public static float[][] cache128 = new float[3][2];//llc缓存监控数据 物理机128
	public static float[][] cache147 = new float[3][2];//llc缓存监控数据 物理机147

	public static int cronFlag = 0; //监控开启标志，0默认关闭监控
	public static int PhysicalMachine128 = 1; //物理机1
	public static int PhysicalMachine147 = 2; //物理机2


	/**
	 * Heracles系统在线负载生成器模块变量
	 */
	public static boolean onlineDataFlag=false; //在线请求查询标志,false默认关闭
	public static int onlineRequestIntensity=10; //在线请求每秒钟请求数 QPS
	private static int windowOnLineDataListCount=0;//窗口数组计数器,默认0开始,每次计数+1
	public static List<QueryData> onlineDataList=new ArrayList<QueryData>();//负载产生器一直填充的数组
	public static List<QueryData> tempOnlineDataList=new ArrayList<QueryData>();//计算每秒钟的请求响应时间均值时存储数据的临时数组
	private static List<QueryData> windowOnlineDataList=new ArrayList<QueryData>();//窗口请求数据记录数组,循环记录每秒钟的数据平均值
	private static List<QueryData> tempWindowOnlineDataList=new ArrayList<QueryData>();//窗口请求数据记录数组,循环记录每秒钟的数据平均值

	public static QueryData latestOnlineData=new QueryData();

	/**
	 * 静态块
	 */
	static {
//				containerInfoMap=RepositoryDao.initContainerInfoMap();//初始化容器信息map 
//				System.out.println("初始化 containerInfoMap size="+containerInfoMap.size());
//				appInfoMap=RepositoryDao.initAppInfoMap();//初始化app信息map 
//				System.out.println("初始化 appInfoMap  size="+appInfoMap.size());
//				Set<String> appNameSet = appInfoMap.keySet(); //取出所有应用的名称
//				for(String appName:appNameSet){ 
//					appStatusMap.put(appName,false);//系统初始化,所有应用默认为未执行
//					System.out.println("初始化app执行状态 "+appName+"=false");
//				}
//				systemInfoMap=RepositoryDao.initSystemInfoMap();
		QueryData data=new QueryData();
		for(int i=0;i<60;i++){ 
			windowOnlineDataList.add(data);
		}
		//LoadService.getInstance().service("192.168.1.129", 22222);//开启rmi服务端
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
	/**
	 * 向窗口数组里添加一个新的数据 
	 * 大小60,循环赋值
	 * @param data 新数据
	 */
	public void addWindowOnlineDataList(QueryData data){
		latestOnlineData=data;
		windowOnlineDataList.set(windowOnLineDataListCount%60,data);
		windowOnLineDataListCount++;
		//System.out.println(windowOnlineDataList.size()+" "+windowOnlineDataList.get(windowOnLineDataListCount%5).getGenerateTime()+" "+windowOnLineDataListCount%5);
	}

	/**
	 * 计算查询时间的度量值
	 * @return 返回float类型的数组 {平均值,最大值}
	 */
	public float[] getQueryTimeMetric(){
		tempWindowOnlineDataList.clear();
		tempWindowOnlineDataList=Repository.windowOnlineDataList;
		int size=tempWindowOnlineDataList.size();//size 应该为固定值60
		int avgQueryTime=0;
		int maxQueryTime=0;
		for(QueryData item:tempWindowOnlineDataList){
			if(item.getQueryTime()>maxQueryTime){
				maxQueryTime=item.getQueryTime();
			}
			avgQueryTime+=item.getQueryTime();
		}
		avgQueryTime=avgQueryTime/size;
		float[] result=new float[2];
		result[0]=(float) avgQueryTime;
		result[1]=(float) maxQueryTime;

		return result;
	}
	/**
	 * 计算查询时间的度量值
	 * @return 返回float类型平均值
	 */
	public float getAvgQueryTime(){  
		tempWindowOnlineDataList.clear();
		tempWindowOnlineDataList=Repository.windowOnlineDataList;
		int size=tempWindowOnlineDataList.size();//size 应该为固定值60
		int avgQueryTime=0;
		for(QueryData item:tempWindowOnlineDataList){
			avgQueryTime+=item.getQueryTime();
		}
		avgQueryTime=avgQueryTime/size; 
		
		return (float)avgQueryTime; 
	} 
}

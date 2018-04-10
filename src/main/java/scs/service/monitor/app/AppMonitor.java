package scs.service.monitor.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import scs.pojo.AppInfoBean;
import scs.pojo.TableAppresourceusage;
import scs.pojo.TableContainerresourceusage;

/**
 * @author jztong
 * 不查询数据库，只匹配数值
 */
public interface AppMonitor {
	
	/**
	 * @author jztong
	 * @param appContainerNames 存放每个application对应哪些containerName, key为应用名，value为该应用的部署信息
	 * @return 存放每个application对应哪些containerName, key为应用名，value为container名字的列表
	 */
	public Map<String, List<String>> getAPPName(Map<String,AppInfoBean> appContainerNames);
	
	/**
	 * @author jztong
	 * @param containerresourceusages 存放每个container（除3个client端外，共35个）的资源使用情况的列表
	 * @param appContainerNames 存放每个application对应哪些containerName, key为应用名，value为container名字的列表
	 * @return 返回每个应用的统计资源使用情况【平均】, key为应用名, value为该应用当前的资源使用情况统计
	 */
	public Map<String, TableAppresourceusage> aggregateAPP(ArrayList<TableContainerresourceusage> containerresourceusages,Map<String, List<String>> appContainerNames);
	
	/**
	 * 
	 * @param apps 每个应用(8个)的统计资源使用情况【平均】, key为应用名, value为该应用当前的资源使用情况统计
	 * @return 成功插入的数据条数
	 */
	public int testInsert(Map<String, TableAppresourceusage> apps);
	
}

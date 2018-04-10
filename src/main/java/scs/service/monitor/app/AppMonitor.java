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
	
	public Map<String, List<String>> getAPPName(Map<String,AppInfoBean> appContainerNames);
	
	public Map<String, TableAppresourceusage> aggregateAPP(ArrayList<TableContainerresourceusage> containerresourceusages,Map<String, List<String>> appContainerNames);
	
	public int testInsert(Map<String, TableAppresourceusage> apps);
	
}

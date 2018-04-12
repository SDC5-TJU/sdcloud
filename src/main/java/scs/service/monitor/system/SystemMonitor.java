package scs.service.monitor.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import scs.pojo.SystemInfoBean;
import scs.pojo.TableSystemresourceusage;

public interface SystemMonitor {
	
	/**
	 * 
	 * @author jztong
	 * @param hostnameMap 物理主机名列表
	 * @return 物理主机的最新资源利用情况列表
	 */
	public ArrayList<TableSystemresourceusage> getSystemDataList(Map<String, SystemInfoBean> hostnameMap);
	
	/**
	 * @author jztong
	 * @param tableSystemresourceusageList
	 * @return 成功插入的条数
	 */
	public int testInsert(ArrayList<TableSystemresourceusage> tableSystemresourceusageList);
	
	public List<TableSystemresourceusage> testSelect(int number);
	
	public int testSelect2(int number);
	
	public TableSystemresourceusage getRealSystemResourceFromStaticData(int number);
}

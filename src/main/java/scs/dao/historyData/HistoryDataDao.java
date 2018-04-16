package scs.dao.historyData;

import java.util.List;  
import scs.pojo.AppResouceUsageBean;
import scs.pojo.SystemResourceUsageBean;

public interface HistoryDataDao {
	public List<SystemResourceUsageBean> searchSysResourceUsage(String hostName,String startTime,String endTime);
	public List<AppResouceUsageBean> searchContainerResourceUsage(String containerName,String startTime,String endTime);
	public List<AppResouceUsageBean> searchAppResourceUsage(String applicationName,String startTime,String endTime);
	
}

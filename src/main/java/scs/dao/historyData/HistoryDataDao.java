package scs.dao.historyData;

import java.util.List; 
import scs.pojo.AppResouceUsageBean;
import scs.pojo.ContainerResourceUsageBean;
import scs.pojo.ExecuteRecordBean;
import scs.pojo.MemcachedDataBean;
import scs.pojo.SiloDataBean;
import scs.pojo.SystemResourceUsageBean;
import scs.pojo.TwoTuple;
import scs.pojo.XapianDataBean; 

public interface HistoryDataDao {
	public List<SystemResourceUsageBean> searchSysResourceUsage(String hostName,String startTime,String endTime);
	public List<ContainerResourceUsageBean> searchContainerResourceUsage(String containerName,String startTime,String endTime);
	public List<AppResouceUsageBean> searchAppResourceUsage(String applicationName,String startTime,String endTime);
	public List<MemcachedDataBean> searchMemcachedData(int testRecordId,int isBase);
	public List<SiloDataBean> searchSiloData(int testRecordId,int isBase);
	public List<XapianDataBean> searchXapianData(int testRecordId,int isBase);
	public List<TwoTuple<Long, Integer>> searchWebServerData(int testRecordId,int isBase);
	public List<TwoTuple<Long, Integer>> searchWebSearchData(int testRecordId,int isBase);
	public List<TwoTuple<Long, Integer>> searchCassandraData(int testRecordId,int isBase);
	public List<TwoTuple<Long, Integer>> searchRedisData(int testRecordId,int isBase);
	public List<ExecuteRecordBean> searchExecuteRecord(String startTime,String endTime);
}

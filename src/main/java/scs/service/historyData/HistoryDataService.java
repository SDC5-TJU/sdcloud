package scs.service.historyData;

import java.util.List;

import scs.pojo.MemcachedDataBean;
import scs.pojo.SiloDataBean;
import scs.pojo.TwoTuple;

/**
 * 登录service层接口
 * @author YangYanan
 * @desc
 * @date 2017-8-18
 */
public interface HistoryDataService {
	public List<String> searchSysResourceUsage(String hostName,String startTime,String endTime);
	public List<String> searchContainerResourceUsage(String containerName,String startTime,String endTime);
	public List<String> searchAppResourceUsage(String applicationName,String startTime,String endTime,boolean needTime);
	public List<MemcachedDataBean> searchMemcachedData(int testRecordId,int isBase);
	public List<SiloDataBean> searchSiloData(int testRecordId,int isBase);
	public List<TwoTuple<Long, Integer>> searchWebServerData(int testRecordId,int isBase);
	public List<TwoTuple<Long, Integer>> searchWebSearchData(int testRecordId,int isBase);
	public List<TwoTuple<Long, Integer>> searchCassandraData(int testRecordId,int isBase);
}

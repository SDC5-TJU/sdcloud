package scs.service.historyData;

import java.util.List;

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
	
}

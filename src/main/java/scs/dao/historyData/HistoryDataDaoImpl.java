package scs.dao.historyData;
 

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import scs.dao.MySQLBaseDao;
import scs.pojo.AppResouceUsageBean;
import scs.pojo.SystemResourceUsageBean;

 

@Repository
public class HistoryDataDaoImpl extends MySQLBaseDao implements HistoryDataDao {

	@Override
	public List<SystemResourceUsageBean> searchSysResourceUsage(String hostName, String startTime,
			String endTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppResouceUsageBean> searchContainerResourceUsage(String containerName, String startTime,
			String endTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppResouceUsageBean> searchAppResourceUsage(String applicationName, String startTime,
			String endTime) {
		// TODO Auto-generated method stub
		return null;
	}

	 



}

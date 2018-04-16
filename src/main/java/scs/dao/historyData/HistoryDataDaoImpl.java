package scs.dao.historyData;
  
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import scs.dao.MySQLBaseDao;
import scs.pojo.AppConfigBean;
import scs.pojo.AppResouceUsageBean;
import scs.pojo.ContainerResourceUsageBean;
import scs.pojo.SystemResourceUsageBean;
import scs.util.format.DateFormats;

@Repository
public class HistoryDataDaoImpl extends MySQLBaseDao implements HistoryDataDao {

	@Override
	public List<SystemResourceUsageBean> searchSysResourceUsage(String hostName, String startTime,String endTime) {
		String sql="select cpuUsageRate,memUsageRate,netUsageRate,ioUsageRate,collectTime from table_systemresourceusage where hostName=? and collectTime>=? and collectTime<=?";
		List<SystemResourceUsageBean> list=jt.query(sql,new Object[]{hostName,startTime,endTime},new ResultSetExtractor<List<SystemResourceUsageBean>>() {
			public List<SystemResourceUsageBean> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<SystemResourceUsageBean> list = new ArrayList<SystemResourceUsageBean>();
				while (rs.next()) {
					SystemResourceUsageBean bean=new SystemResourceUsageBean();
					bean.setCpuUsageRate(rs.getFloat(1));
					bean.setMemUsageRate(rs.getFloat(2));
					bean.setIoUsageRate(rs.getFloat(3));
					bean.setNetUsageRate(rs.getFloat(4));
					bean.setCollectTime(DateFormats.getInstance().dateStringToTime(rs.getString(5)));
					list.add(bean);
				}
				return list;
			}
		});
		return list;
	}

	@Override
	public List<ContainerResourceUsageBean> searchContainerResourceUsage(String hostName, String startTime,String endTime) {
		String sql="select cpuUsageRate,memUsageRate,netUsageRate,ioUsageRate,collectTime from table_containerresourceusage where hostName=? and collectTime>=? and collectTime<=?";
		List<ContainerResourceUsageBean> list=jt.query(sql,new Object[]{hostName,startTime,endTime},new ResultSetExtractor<List<ContainerResourceUsageBean>>() {
			public List<ContainerResourceUsageBean> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<ContainerResourceUsageBean> list = new ArrayList<ContainerResourceUsageBean>();
				while (rs.next()) {
					ContainerResourceUsageBean bean=new ContainerResourceUsageBean();
					bean.setCpuUsageRate(rs.getFloat(1));
					bean.setMemUsageRate(rs.getFloat(2));
					bean.setIoUsageRate(rs.getFloat(3));
					bean.setNetUsageRate(rs.getFloat(4));
					bean.setCollectTime(DateFormats.getInstance().dateStringToTime(rs.getString(5)));
					list.add(bean);
				}
				return list;
			}
		});
		return list;
	}

	@Override
	public List<AppResouceUsageBean> searchAppResourceUsage(String appName, String startTime,
			String endTime) {
		String sql="select cpuUsageRate,memUsageRate,netUsageRate,ioUsageRate,collectTime from table_appresourceusage where applicationName=? and collectTime>=? and collectTime<=?";
		List<AppResouceUsageBean> list=jt.query(sql,new Object[]{appName,startTime,endTime},new ResultSetExtractor<List<AppResouceUsageBean>>() {
			public List<AppResouceUsageBean> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<AppResouceUsageBean> list = new ArrayList<AppResouceUsageBean>();
				while (rs.next()) {
					AppResouceUsageBean bean=new AppResouceUsageBean();
					bean.setCpuUsageRate(rs.getFloat(1));
					bean.setMemUsageRate(rs.getFloat(2));
					bean.setIoUsageRate(rs.getFloat(3));
					bean.setNetUsageRate(rs.getFloat(4));
					bean.setCollectTime(DateFormats.getInstance().dateStringToTime(rs.getString(5)));
					list.add(bean);
				}
				return list;
			}
		});
		return list;
	}

	 



}

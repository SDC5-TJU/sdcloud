package scs.dao.historyData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import scs.dao.MySQLBaseDao; 
import scs.pojo.AppResouceUsageBean;
import scs.pojo.ContainerResourceUsageBean;
import scs.pojo.ExecuteRecordBean;
import scs.pojo.MemcachedDataBean;
import scs.pojo.SiloDataBean;
import scs.pojo.SystemResourceUsageBean;
import scs.pojo.TwoTuple;
import scs.pojo.XapianDataBean;
import scs.util.format.DataFormats;
import scs.util.format.DateFormats;

@Repository
public class HistoryDataDaoImpl extends MySQLBaseDao implements HistoryDataDao {
    private DataFormats format=DataFormats.getInstance();
	@Override
	public List<SystemResourceUsageBean> searchSysResourceUsage(String hostName, String startTime,String endTime) {
		String sql="select cpuUsageRate,memUsageRate,netUsageRate,ioUsageRate,collectTime from table_systemresourceusage where hostName=? and collectTime>=? and collectTime<=? order by collectTime asc";
		List<SystemResourceUsageBean> list=jt.query(sql,new Object[]{hostName,startTime,endTime},new ResultSetExtractor<List<SystemResourceUsageBean>>() {
			public List<SystemResourceUsageBean> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<SystemResourceUsageBean> list = new ArrayList<SystemResourceUsageBean>();
				while (rs.next()) {
					SystemResourceUsageBean bean=new SystemResourceUsageBean();
					bean.setCpuUsageRate(format.subFloat(rs.getFloat(1)*100,2));
					bean.setMemUsageRate(format.subFloat(rs.getFloat(2),2));
					bean.setIoUsageRate(format.subFloat(rs.getFloat(3),2));
					bean.setNetUsageRate(format.subFloat(rs.getFloat(4),2));
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
		String sql="select cpuUsageRate,memUsageRate,netInput,netOutput,ioInput,ioOutput,collectTime from table_containerresourceusage where containerName=? and collectTime>=? and collectTime<=? order by collectTime asc";
		List<ContainerResourceUsageBean> list=jt.query(sql,new Object[]{hostName,startTime,endTime},new ResultSetExtractor<List<ContainerResourceUsageBean>>() {
			public List<ContainerResourceUsageBean> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<ContainerResourceUsageBean> list = new ArrayList<ContainerResourceUsageBean>();
				while (rs.next()) {
					ContainerResourceUsageBean bean=new ContainerResourceUsageBean();
					bean.setCpuUsageRate(format.subFloat(rs.getFloat(1)*0.625f,2));
					bean.setMemUsageRate(format.subFloat(rs.getFloat(2)*50,2));
					bean.setNetInput(format.subFloat(rs.getFloat(3),2));
					bean.setNetOutput(format.subFloat(rs.getFloat(4),2));
					bean.setIoInput(format.subFloat(rs.getFloat(5),2));
					bean.setIoOutput(format.subFloat(rs.getFloat(6),2));
					bean.setCollectTime(DateFormats.getInstance().dateStringToTime(rs.getString(7)));
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
		String sql="select cpuUsageRate,memUsageRate,netInput,netOutput,ioInput,ioOutput,collectTime from table_appresourceusage where applicationName=? and collectTime>=? and collectTime<=? order by collectTime asc";
		List<AppResouceUsageBean> list=jt.query(sql,new Object[]{appName,startTime,endTime},new ResultSetExtractor<List<AppResouceUsageBean>>() {
			public List<AppResouceUsageBean> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<AppResouceUsageBean> list = new ArrayList<AppResouceUsageBean>();
				while (rs.next()) {
					AppResouceUsageBean bean=new AppResouceUsageBean();
					bean.setCpuUsageRate(format.subFloat(rs.getFloat(1)*0.625f,2));//*100/80/2 换算成百分比 除以cpu总核心数 除以2是两台物理机
					bean.setMemUsageRate(format.subFloat(rs.getFloat(2)*50,2));// *100/2
					bean.setNetInput(format.subFloat(rs.getFloat(3),2));
					bean.setNetOutput(format.subFloat(rs.getFloat(4),2));
					bean.setIoInput(format.subFloat(rs.getFloat(5),2));
					bean.setIoOutput(format.subFloat(rs.getFloat(6),2));
					bean.setCollectTime(DateFormats.getInstance().dateStringToTime(rs.getString(7)));
					list.add(bean);
				}
				return list;
			}
		});
		return list;
	}

	@Override
	public List<MemcachedDataBean> searchMemcachedData(int testRecordId, int isBase) {
		String sql="select rps,requests,gets,sets,hits,misses,avg_lat,nintyTh,nintyFiveTh,nintyNineTh,min,max,std from data_memcached_base where testRecordId=?";
		if(isBase==0){
			sql="select rps,requests,gets,sets,hits,misses,avg_lat,nintyTh,nintyFiveTh,nintyNineTh,min,max,std from data_memcached where testRecordId=?";
		}
		List<MemcachedDataBean> list=jt.query(sql,new Object[]{testRecordId},new ResultSetExtractor<List<MemcachedDataBean>>() {
			public List<MemcachedDataBean> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<MemcachedDataBean> list = new ArrayList<MemcachedDataBean>();
				while (rs.next()) {
					MemcachedDataBean bean=new MemcachedDataBean();
					bean.setRps(rs.getInt(1));
					bean.setRequests(rs.getInt(2));
					bean.setGets(rs.getInt(3));
					bean.setSets(rs.getInt(4));
					bean.setHits(rs.getInt(5));
					bean.setMisses(rs.getInt(6));
					bean.setAvg_lat(rs.getFloat(7));
					bean.setNintyTh(rs.getFloat(8));
					bean.setNintyFiveTh(rs.getFloat(9));
					bean.setNintyNineTh(rs.getFloat(10));
					bean.setMin(rs.getFloat(11));
					bean.setMax(rs.getFloat(12));
					bean.setStd(rs.getFloat(13));
					list.add(bean);
				}
				return list;
			}
		});
		return list;
	}

	@Override
	public List<SiloDataBean> searchSiloData(int testRecordId, int isBase) {
		String sql="select queryTime,totalTime from data_silo_base where testRecordId=?";
		if(isBase==0){
			sql="select queryTime,totalTime from data_silo where testRecordId=?";
		}
		List<SiloDataBean> list=jt.query(sql,new Object[]{testRecordId},new ResultSetExtractor<List<SiloDataBean>>() {
			public List<SiloDataBean> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<SiloDataBean> list = new ArrayList<SiloDataBean>();
				while(rs.next()){
					SiloDataBean bean=new SiloDataBean();
				    bean.setQueryTime(rs.getFloat(1));
					bean.setTotalTime(rs.getFloat(2));
					list.add(bean);
				}
				return list;
			}
		});
		return list;
	}

	@Override
	public List<XapianDataBean> searchXapianData(int testRecordId, int isBase){
		String sql="select queryTime,totalTime from data_xapian_base where testRecordId=?";
		if(isBase==0){
			sql="select queryTime,totalTime from data_xapian where testRecordId=?";
		}
		List<XapianDataBean> list=jt.query(sql,new Object[]{testRecordId},new ResultSetExtractor<List<XapianDataBean>>() {
			public List<XapianDataBean> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<XapianDataBean> list = new ArrayList<XapianDataBean>();
				while(rs.next()){
					XapianDataBean bean=new XapianDataBean();
					bean.setQueryTime(rs.getFloat(1));
					bean.setTotalTime(rs.getFloat(2));
					list.add(bean);
				}
				return list;
			}
		});
		return list;
	}

	@Override
	public List<TwoTuple<Long, Integer>> searchWebServerData(int testRecordId, int isBase) {
		String sql="select generateTime,queryTime from data_webserver_base where testRecordId=? order by generateTime asc";
		if(isBase==0){
			sql="select generateTime,queryTime from data_webserver where testRecordId=? order by generateTime asc";
		}
		List<TwoTuple<Long,Integer>> list=jt.query(sql,new Object[]{testRecordId},new ResultSetExtractor<List<TwoTuple<Long,Integer>>>() {
			public List<TwoTuple<Long,Integer>> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<TwoTuple<Long,Integer>> list = new ArrayList<TwoTuple<Long,Integer>>();
				while (rs.next()) {
					TwoTuple<Long,Integer> bean=new TwoTuple<Long,Integer>(rs.getLong(1),rs.getInt(2));
					list.add(bean);
				}
				return list;
			}
		});
		return list;
	}

	@Override
	public List<TwoTuple<Long, Integer>> searchWebSearchData(int testRecordId, int isBase) {
		String sql="select generateTime,queryTime from data_websearch_base where testRecordId=? order by generateTime asc";
		if(isBase==0){
			sql="select generateTime,queryTime from data_websearch where testRecordId=? order by generateTime asc";
		}
		List<TwoTuple<Long,Integer>> list=jt.query(sql,new Object[]{testRecordId},new ResultSetExtractor<List<TwoTuple<Long,Integer>>>() {
			public List<TwoTuple<Long,Integer>> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<TwoTuple<Long,Integer>> list = new ArrayList<TwoTuple<Long,Integer>>();
				while (rs.next()) {
					TwoTuple<Long,Integer> bean=new TwoTuple<Long,Integer>(rs.getLong(1),rs.getInt(2));
					list.add(bean);
				}
				return list;
			}
		});
		return list;
	}

	@Override
	public List<TwoTuple<Long,Integer>> searchCassandraData(int testRecordId, int isBase) {
		String sql="select generateTime,queryTime from data_cassandra_base where testRecordId=? order by generateTime asc";
		if(isBase==0){
			sql="select generateTime,queryTime from data_cassandra where testRecordId=? order by generateTime asc";
		}
		List<TwoTuple<Long,Integer>> list=jt.query(sql,new Object[]{testRecordId},new ResultSetExtractor<List<TwoTuple<Long,Integer>>>() {
			public List<TwoTuple<Long,Integer>> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<TwoTuple<Long,Integer>> list = new ArrayList<TwoTuple<Long,Integer>>();
				while (rs.next()) {
					TwoTuple<Long,Integer> bean=new TwoTuple<Long,Integer>(rs.getLong(1),rs.getInt(2));
					list.add(bean);
				}
				return list;
			}
		});
		return list;
	}

	@Override
	public List<TwoTuple<Long, Integer>> searchRedisData(int testRecordId, int isBase) {
		String sql="select generateTime,queryTime from data_redis_base where testRecordId=? order by generateTime asc";
		if(isBase==0){
			sql="select generateTime,queryTime from data_redis where testRecordId=? order by generateTime asc";
		}
		List<TwoTuple<Long,Integer>> list=jt.query(sql,new Object[]{testRecordId},new ResultSetExtractor<List<TwoTuple<Long,Integer>>>() {
			public List<TwoTuple<Long,Integer>> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<TwoTuple<Long,Integer>> list = new ArrayList<TwoTuple<Long,Integer>>();
				while (rs.next()) {
					TwoTuple<Long,Integer> bean=new TwoTuple<Long,Integer>(rs.getLong(1),rs.getInt(2));
					list.add(bean);
				}
				return list;
			}
		});
		return list;
	}

	@Override
	public List<ExecuteRecordBean> searchExecuteRecord(String startTime, String endTime) {
		String sql="select applicationName,eventTime,action,isBase from table_executeRecord where eventTime>? and eventTime<?";
		List<ExecuteRecordBean> list=jt.query(sql,new Object[]{startTime,endTime},new ResultSetExtractor<List<ExecuteRecordBean>>() {
			public List<ExecuteRecordBean> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<ExecuteRecordBean> list = new ArrayList<ExecuteRecordBean>();
				while (rs.next()) {
					ExecuteRecordBean bean=new ExecuteRecordBean();
					bean.setApplicationName(rs.getString(1));
					bean.setEventTime(rs.getString(2));
					bean.setAction(rs.getString(3));
					bean.setIsBase(rs.getInt(4)); 
					list.add(bean);
				}
				return list;
			}
		});
		return list;
	}
}

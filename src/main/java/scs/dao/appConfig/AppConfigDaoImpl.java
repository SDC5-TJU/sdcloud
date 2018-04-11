package scs.dao.appConfig;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import scs.pojo.AppConfigBean;
import scs.dao.MySQLBaseDao;

@Repository
public class AppConfigDaoImpl extends MySQLBaseDao implements AppConfigDao {

	@Override
	public int addAppConfig(Map<String,AppConfigBean> map) {
		String sql="insert into table_appconfig(applicationName,applicationType,requestCount,warmUpCount,pattern,intensity,testRecordId,enable) values(?,?,?,?,?,?,?,?)";
		int result=0;
		for (AppConfigBean bean : map.values()) {  
			result+=this.jt.update(sql,new Object[]{bean.getApplicationName(),bean.getApplicationType(),bean.getRequestCount(),bean.getWarmUpCount(),bean.getPattern(),bean.getIntensity(),bean.getTestRecordId(),bean.getEnable()});
		}
		return result;
	}

	@Override
	public int modifyAppConfig(Map<String,AppConfigBean> map) {
		String sql="update table_appconfig set requestCount=?,warmUpCount=?,pattern=?,intensity=?,enable=? where testRecordId=? and applicationName=?";
		int result=0;
		for (AppConfigBean bean : map.values()) {  
			result+=this.jt.update(sql,new Object[]{bean.getRequestCount(),bean.getWarmUpCount(),bean.getPattern(),bean.getIntensity(),bean.getEnable(),bean.getTestRecordId(),bean.getApplicationName()});
		}
		return result;
	}

	@Override
	public List<AppConfigBean> getAppConfig(int testRecordId) {
		String sql="select applicationName,applicationType,requestCount,warmUpCount,pattern,intensity,enable from table_appconfig where testRecordId=?";
		List<AppConfigBean> list=jt.query(sql,new Object[]{testRecordId},new ResultSetExtractor<List<AppConfigBean>>() {
			public List<AppConfigBean> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<AppConfigBean> list = new ArrayList<AppConfigBean>();
				while (rs.next()) {
					AppConfigBean bean=new AppConfigBean();
					bean.setApplicationName(rs.getString(1));
					bean.setApplicationType(rs.getString(2));
					bean.setRequestCount(rs.getString(3));
					bean.setWarmUpCount(rs.getString(4));
					bean.setPattern(rs.getString(5));
					bean.setIntensity(rs.getString(6));
					bean.setEnable(rs.getInt(7));
					bean.setTestRecordId(testRecordId);
					list.add(bean);
				}
				return list;
			}
		});
		return list;
	}
}

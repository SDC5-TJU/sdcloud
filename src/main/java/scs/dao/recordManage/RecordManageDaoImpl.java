package scs.dao.recordManage;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import scs.pojo.TestRecordBean;
import scs.dao.MySQLBaseDao;

@Repository
public class RecordManageDaoImpl extends MySQLBaseDao implements RecordManageDao {

	@Override
	public int addRecord(int recordId,String recordDesc) {
		String sql="insert into table_testrecord(autoId,recordDesc) values(?,?)"; 
		return this.jt.update(sql,new Object[]{recordId,recordDesc}); 
	}

	@Override
	public List<TestRecordBean> searchRecord(int start,int pageSize) {
		String sql="select autoId,recordDesc,startTime,endTime from table_testrecord limit ?,?";
		List<TestRecordBean> list=jt.query(sql,new Object[]{start,pageSize},new ResultSetExtractor<List<TestRecordBean>>() {
			public List<TestRecordBean> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<TestRecordBean> list = new ArrayList<TestRecordBean>();
				while (rs.next()) {
					TestRecordBean bean=new TestRecordBean();
					bean.setAutoId(rs.getInt(1));
					bean.setRecordDesc(rs.getString(2));
					bean.setStartTime(rs.getString(3));
					bean.setEndTime(rs.getString(4));
					list.add(bean);
				}
				return list;
			}
		});
		return list;
	}

	@Override
	public int getLatestRecordId() {
		if(this.getRecordCount()==0)
			return 0;
		String sql="select autoId from table_testrecord order by autoId desc limit 0,1";
		return this.jt.queryForObject(sql,Integer.class);
	}

	@Override
	public int getRecordCount() {
		String sql="select count(0) from table_testrecord";
		return this.jt.queryForObject(sql,Integer.class);
	}

	@Override
	public String modifyStartTime(int testRecordId,String startTime) { 
		String result="";
		String sql="select startTime from table_testrecord where autoId=?";
		String curStartTime=this.jt.queryForObject(sql,new Object[]{testRecordId},String.class);
		if(curStartTime==null||curStartTime.equals("")){
			sql="update table_testrecord set startTime=? where autoId=?";
			if(this.jt.update(sql,new Object[]{startTime,testRecordId})!=0){
				result=startTime;
			}
		}else{
			result=curStartTime.substring(0,19);
		}
		return result;
	}

	@Override
	public String modifyEndTime(int testRecordId,String endTime) {
		String result="";
		String sql="select endTime from table_testrecord where autoId=?";
		String curStartTime=this.jt.queryForObject(sql,new Object[]{testRecordId},String.class);
		if(curStartTime==null||curStartTime.equals("")){
			sql="update table_testrecord set endTime=? where autoId=?";
			if(this.jt.update(sql,new Object[]{endTime,testRecordId})!=0){
				result=endTime;
			}
		}else{
			result=curStartTime.substring(0,19);
		}
		return result;
	}

	@Override
	public TestRecordBean getRecordById(int testRecordId) {
		String sql="select startTime,endTime from table_testrecord where autoId=?";
		TestRecordBean bean=jt.query(sql,new Object[]{testRecordId},new ResultSetExtractor<TestRecordBean>() {
			public TestRecordBean extractData(ResultSet rs) throws SQLException, DataAccessException {
				TestRecordBean bean=new TestRecordBean();
				if (rs.next()) {  
					bean.setStartTime(rs.getString(1));
					bean.setEndTime(rs.getString(2)); 
				}
				return bean;
			}
		});
		return bean;
	}






}

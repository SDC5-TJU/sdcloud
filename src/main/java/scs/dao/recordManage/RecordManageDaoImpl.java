package scs.dao.recordManage;

import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import scs.pojo.TableContainerresourceusage;
import scs.pojo.TableContainerresourceusagestart;
import scs.pojo.TestRecordBean;
import scs.pojo.TwoTuple;
import scs.service.monitor.containers.ContainerMonitor;
import scs.dao.MySQLBaseDao;
import scs.dao.monitor.DAOmapper.TableContainerresourceusagestartMapper;

@Repository
public class RecordManageDaoImpl extends MySQLBaseDao implements RecordManageDao {

	@Autowired
	public TableContainerresourceusagestartMapper mapper;

	@Autowired
	@Qualifier("containerMonitor")
	public ContainerMonitor containerMonitor;

	@Override
	public int addRecord(int recordId, String recordDesc) {
		String sql = "insert into table_testrecord(autoId,recordDesc) values(?,?)";
		return this.jt.update(sql, new Object[] { recordId, recordDesc });
	}

	@Override
	public List<TestRecordBean> searchRecord(int start, int pageSize) {
		String sql = "select autoId,recordDesc,startTime,endTime from table_testrecord limit ?,?";
		List<TestRecordBean> list = jt.query(sql, new Object[] { start, pageSize },
				new ResultSetExtractor<List<TestRecordBean>>() {
					public List<TestRecordBean> extractData(ResultSet rs) throws SQLException, DataAccessException {
						List<TestRecordBean> list = new ArrayList<TestRecordBean>();
						while (rs.next()) {
							TestRecordBean bean = new TestRecordBean();
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
		if (this.getRecordCount() == 0)
			return 0;
		String sql = "select autoId from table_testrecord order by autoId desc limit 0,1";
		return this.jt.queryForObject(sql, Integer.class);
	}

	@Override
	public int getRecordCount() {
		String sql = "select count(0) from table_testrecord";
		return this.jt.queryForObject(sql, Integer.class);
	}

	@Override
	public String modifyStartTime(int testRecordId, String startTime) {
		String result = "";
		String sql = "select startTime from table_testrecord where autoId=?";
		String curStartTime = this.jt.queryForObject(sql, new Object[] { testRecordId }, String.class);
		if (curStartTime == null || curStartTime.equals("")) {
			sql = "update table_testrecord set startTime=? where autoId=?";
			if (this.jt.update(sql, new Object[] { startTime, testRecordId }) != 0) {
				result = startTime;
				// 代码段开始
				// 读取容器资源使用信息的调用位置 to do
			/*	String[] hosts = { "192.168.1.128", "192.168.1.147" };
				String hostname = "192.168.1.128";
				String username = "tank";
				String password = "tanklab";
				int len = hosts.length;
				// combineList 是38个容器的数据
				ArrayList<TableContainerresourceusage> combineList = new ArrayList<>();
				for (int i = 0; i < len; i++) {
					hostname = hosts[i];
					InputStream containerInfoStream = containerMonitor.getContainerInfoStream(hostname, username,
							password);
					combineList.addAll(
							containerMonitor.getContainersPOJO(hostname, username, password, containerInfoStream));
				}
				// 封装新表记录
				TableContainerresourceusagestart tableContainerresourceusagestart = null;
				for (int i = 0; i < combineList.size(); i++) {
					tableContainerresourceusagestart = new TableContainerresourceusagestart();
					try {
						tableContainerresourceusagestart
								.setCollecttime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(startTime));
					} catch (ParseException e) {
						System.out.println("日期格式转换错误");
					}
					tableContainerresourceusagestart.setTestrecord((byte) testRecordId);
					tableContainerresourceusagestart.setCpuusagerate(combineList.get(i).getCpuusagerate());
					tableContainerresourceusagestart.setContainername(combineList.get(i).getContainername());
					tableContainerresourceusagestart.setIoinput(combineList.get(i).getIoinput());
					tableContainerresourceusagestart.setIooutput(combineList.get(i).getIooutput());
					tableContainerresourceusagestart.setNetinput(combineList.get(i).getNetinput());
					tableContainerresourceusagestart.setNetoutput(combineList.get(i).getNetoutput());
					tableContainerresourceusagestart.setMemusageamount(combineList.get(i).getMemusageamount());
					tableContainerresourceusagestart.setMemusagerate(combineList.get(i).getMemusagerate());
					// 插入表格
					mapper.insert(tableContainerresourceusagestart);
				}*/
				// 代码段结束
			}
		} else {
			result = curStartTime.substring(0, 19);
		}
		return result;
	}

	@Override
	public String modifyEndTime(int testRecordId, String endTime) {
		String result = "";
		String sql = "select endTime from table_testrecord where autoId=?";
		String curStartTime = this.jt.queryForObject(sql, new Object[] { testRecordId }, String.class);
		if (curStartTime == null || curStartTime.equals("")) {
			sql = "update table_testrecord set endTime=? where autoId=?";
			if (this.jt.update(sql, new Object[] { endTime, testRecordId }) != 0) {
				result = endTime;
			}
		} else {
			result = curStartTime.substring(0, 19);
		}
		return result;
	}

	@Override
	public TestRecordBean getRecordById(int testRecordId) {
		String sql = "select startTime,endTime from table_testrecord where autoId=?";
		TestRecordBean bean = jt.query(sql, new Object[] { testRecordId }, new ResultSetExtractor<TestRecordBean>() {
			public TestRecordBean extractData(ResultSet rs) throws SQLException, DataAccessException {
				TestRecordBean bean = new TestRecordBean();
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

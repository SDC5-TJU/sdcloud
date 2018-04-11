package scs.dao.recordManage;
 
import java.util.Date;
import java.util.List;

import scs.pojo.TestRecordBean;

public interface RecordManageDao {
	public int addRecord(int recordId,String recordDesc);
	public int modifyStartTime(int testRecordId,Date startTime); 
	public int modifyEndTime(int testRecordId,Date startTime);
	public List<TestRecordBean> searchRecord(int start,int pageSize);
	public int getRecordCount();
	public TestRecordBean getRecordById(int testRecordId);
	public int getLatestRecordId();
}

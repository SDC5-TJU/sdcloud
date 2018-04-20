package scs.dao.recordManage;
  
import java.util.List; 
import scs.pojo.TestRecordBean;

public interface RecordManageDao {
	public int addRecord(int recordId,String recordDesc);
	public String modifyStartTime(int testRecordId,String startTime); 
	public String modifyEndTime(int testRecordId,String startTime);
	public List<TestRecordBean> searchRecord(int start,int pageSize);
	public int getRecordCount();
	public TestRecordBean getRecordById(int testRecordId);
	public int getLatestRecordId(); 
}

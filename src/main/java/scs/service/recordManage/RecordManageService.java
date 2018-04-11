package scs.service.recordManage;
 
import java.util.Date;
import java.util.List;

import scs.pojo.TestRecordBean;

/**
 * 登录service层接口
 * @author YangYanan
 * @desc
 * @date 2017-8-18
 */
public interface RecordManageService {
	public int addRecord(String recordDesc);
	public int modifyStartTime(int testRecordId,Date startTime); 
	public int modifyEndTime(int testRecordId,Date startTime);
	public int getRecordCount();
	public TestRecordBean getRecordById(int testRecordId);
	public List<TestRecordBean> searchRecord(int start,int pageSize);
 
}

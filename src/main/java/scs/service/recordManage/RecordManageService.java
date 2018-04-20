package scs.service.recordManage;
 
import java.util.Date;
import java.util.List;

import scs.pojo.TestRecordBean;
import scs.pojo.TwoTuple;

/**
 * 登录service层接口
 * @author YangYanan
 * @desc
 * @date 2017-8-18
 */
public interface RecordManageService {
	public int addRecord(String recordDesc);
	public String modifyStartTime(int testRecordId,String startTime); 
	public String modifyEndTime(int testRecordId,String startTime);
	public int getRecordCount();
	public TestRecordBean getRecordById(int testRecordId);
	public List<TestRecordBean> searchRecord(int start,int pageSize); 
	
}

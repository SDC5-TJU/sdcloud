package scs.pojo;

import java.util.Date;

/**
 * 测试记录实体类
 * @author yanan
 *
 */
public class TestRecordBean {
	private int autoId;  //数据库自增id
	private String recordDesc; //记录描述
	private String startTime;  //开始时间
	private String endTime;    //结束时间

	public TestRecordBean(){}

	public TestRecordBean(int autoId,String recordDesc,String startTime,String endTime){
		this.autoId=autoId;
		this.recordDesc=recordDesc;
		this.startTime=startTime;
		this.endTime=endTime;
	}

	public int getAutoId() {
		return autoId;
	}
	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}
	public String getRecordDesc() {
		return recordDesc;
	}
	public void setRecordDesc(String recordDesc) {
		this.recordDesc = recordDesc;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		if(startTime!=null&&startTime.length()>=19)
			this.startTime = startTime.substring(0,19);
		else
			this.startTime = startTime; 
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		if(endTime!=null&&endTime.length()>=19)
			this.endTime = endTime.substring(0,19);
		else
			this.endTime = endTime; 
	}

	@Override
	public String toString() {
		return "TestRecordBean [autoId=" + autoId + ", recordDesc=" + recordDesc + ", startTime=" + startTime
				+ ", endTime=" + endTime + "]";
	}
}

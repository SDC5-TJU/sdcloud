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
	private Date startTime;  //开始时间
	private Date endTime;    //结束时间
	
	public TestRecordBean(){}
	
	public TestRecordBean(int autoId,String recordDesc,Date startTime,Date endTime){
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
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "TestRecordBean [autoId=" + autoId + ", recordDesc=" + recordDesc + ", startTime=" + startTime
				+ ", endTime=" + endTime + "]";
	}
}

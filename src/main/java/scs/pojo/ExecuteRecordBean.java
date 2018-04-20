package scs.pojo;

public class ExecuteRecordBean {	
	private String applicationName;
	private String eventTime;
	private String action;
	private int isBase; 
	
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public String getEventTime() {
		return eventTime;
	}
	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public int getIsBase() {
		return isBase;
	}
	public void setIsBase(int isBase) {
		this.isBase = isBase;
	}
	@Override
	public String toString() {
		return "ExecuteRecordBean [applicationName=" + applicationName + ", eventTime=" + eventTime + ", action="
				+ action + ", isBase=" + isBase + "]";
	}
	 

}

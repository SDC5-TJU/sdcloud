package scs.pojo;

public class XapianDataBean {
	private float queueTime;
	private float queryTime;
	private float totalTime;
	
	public XapianDataBean(){};
	public XapianDataBean(float queueTime, float queryTime, float totalTime) {
		super();
		this.queueTime = queueTime;
		this.queryTime = queryTime;
		this.totalTime = totalTime;
	}
	public float getQueueTime() {
		return queueTime;
	}
	public void setQueueTime(float queueTime) {
		this.queueTime = queueTime;
	}
	public float getQueryTime() {
		return queryTime;
	}
	public void setQueryTime(float queryTime) {
		this.queryTime = queryTime;
	}
	public float getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(float totalTime) {
		this.totalTime = totalTime;
	}
	@Override
	public String toString() {
		return "SiloDataBean [queueTime=" + queueTime + ", queryTime=" + queryTime + ", totalTime=" + totalTime + "]";
	}
	 
 

}

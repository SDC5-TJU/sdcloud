package scs.pojo.heracles;

public class QueryData{

	private long generateTime;
	private int queryTime;
	private int qps; 

	public QueryData(long generateTime, int queryTime) {
		super();
		this.generateTime = generateTime;
		this.queryTime = queryTime;
	}
	public QueryData() {
	}
	public long getGenerateTime() {
		return generateTime;
	}
	public void setGenerateTime(long generateTime) {
		this.generateTime = generateTime;
	}
	public int getQueryTime() {
		return queryTime;
	}
	public void setQueryTime(int queryTime) {
		this.queryTime = queryTime;
	}
	public int getQps(){
		return qps;
	}
	public void setQps(int qps){
		this.qps=qps;
	}

}
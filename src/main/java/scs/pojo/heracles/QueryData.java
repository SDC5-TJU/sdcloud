package scs.pojo.heracles;

import scs.util.format.DataFormats;

public class QueryData{

	private long generateTime;
	private float queryTime;
	private int qps; 

	public QueryData(long generateTime, int queryTime) {
		super();
		this.generateTime = generateTime;
		this.queryTime = queryTime;
	}
	public QueryData(QueryData data) { 
		this.generateTime = data.getGenerateTime();
		this.queryTime = data.getQueryTime();
		this.qps = data.getQps();
	}
	public QueryData() {
	}
	public long getGenerateTime() {
		return generateTime;
	}
	public void setGenerateTime(long generateTime) {
		this.generateTime = generateTime;
	}
	public float getQueryTime() {
		return queryTime;
	}
	public void setQueryTime(float queryTime) {
		this.queryTime = queryTime;
	}
	public int getQps(){
		return qps;
	}
	public void setQps(int qps){
		this.qps=qps;
	}

}
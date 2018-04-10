package scs.pojo;
/**
 * 应用启动参数配置类
 * @author yanan
 *
 */
public class AppConfigBean {

	private String applicationName;  //应用名称
	private String applicationType;  //应用类型
	private String requestCount;  //请求总数 只针对在线应用
	private String warmUpCount;   //预热总数 只针对在线应用
	private String pattern;    //请求策略  
	private String intensity;  //请求强度 高/中/低
	private int testRecordId;  //测试记录id
	private int enable;  //是否启用
	
	public AppConfigBean(){};
	
	public AppConfigBean(String applicationName, String applicationType, String requestCount, 
			String warmUpCount,String pattern, String intensity, int testRecordId,int enable) {
		this.applicationName = applicationName;
		this.applicationType = applicationType;
		this.requestCount = requestCount;
		this.warmUpCount = warmUpCount;
		this.pattern = pattern;
		this.intensity = intensity;
		this.testRecordId = testRecordId;
		this.enable = enable;
	}

	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public String getApplicationType() {
		return applicationType;
	}
	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}
	public String getRequestCount() {
		return requestCount;
	}
	public void setRequestCount(String requestCount) {
		this.requestCount = requestCount;
	}
	public String getWarmUpCount() {
		return warmUpCount;
	}
	public void setWarmUpCount(String warmUpCount) {
		this.warmUpCount = warmUpCount;
	}
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public String getIntensity() {
		return intensity;
	}
	public void setIntensity(String intensity) {
		this.intensity = intensity;
	}
	public int getTestRecordId() {
		return testRecordId;
	}
	public void setTestRecordId(int testRecordId) {
		this.testRecordId = testRecordId;
	}

	public int getEnable() {
		return enable;
	}

	public void setEnable(int enable) {
		this.enable = enable;
	}

}

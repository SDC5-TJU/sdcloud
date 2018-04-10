package scs.pojo;
/**
 * 应用资源使用实体类
 * @author yanan
 *
 */
public class AppResouceUsageBean extends ResourceUsage{
	private String applicationName; //应用名称

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
}

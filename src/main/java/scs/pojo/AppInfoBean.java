package scs.pojo;

import java.util.List;
/**
 * 应用信息实体类
 * @author yanan
 *
 */
public class AppInfoBean {
	private String applicationName; //应用名称
	private List<String> containerNames; //应用所在的容器集群，为容器名称的数组
	private String masterContainerName;  //主服务容器名称,master容器对外提供服务
	private String applicationType; //online or offline
	
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public List<String> getContainerNames() {
		return containerNames;
	}
	public void setContainerNames(List<String> containerNames) {
		this.containerNames = containerNames;
	}
	public String getMasterContainerName() {
		return masterContainerName;
	}
	public void setMasterContainerName(String masterContainerName) {
		this.masterContainerName = masterContainerName;
	}
	public String getApplicationType() {
		return applicationType;
	}
	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}
}

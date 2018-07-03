package scs.pojo;
/**
 * 物理机系统资源使用实体类
 * @author yanan
 *
 */
public class SystemResourceUsageBean extends ResourceUsage{
	private String hostname;
	public SystemResourceUsageBean(){ 
	}
	public SystemResourceUsageBean(SystemResourceUsageBean bean){ 
		this.cpuUsageRate=bean.getCpuUsageRate(); 
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
}

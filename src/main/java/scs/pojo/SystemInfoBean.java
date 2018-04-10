package scs.pojo;
/**
 * 物理机系统信息实体类
 * @author yanan
 *
 */
public class SystemInfoBean { 
	private String hostName;  //主机名 
	private String hostIp;    //主机IP 192.168.1.0/24
	private String hostDockerIp;  //主机docker0 IP 172.192.0.0/16
	private int containersCount;     //容器数量 
	 
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getHostIp() {
		return hostIp;
	}
	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}
	public String getHostDockerIp() {
		return hostDockerIp;
	}
	public void setHostDockerIp(String hostDockerIp) {
		this.hostDockerIp = hostDockerIp;
	}
	public int getContainersCount() {
		return containersCount;
	}
	public void setContainersCount(int containersCount) {
		this.containersCount = containersCount;
	}
	 
}

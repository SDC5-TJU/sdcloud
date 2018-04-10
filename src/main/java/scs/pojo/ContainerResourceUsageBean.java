package scs.pojo;
/**
 * 容器资源使用实体类
 * @author yanan
 *
 */
public class ContainerResourceUsageBean extends ResourceUsage{
	private String containerName; //容器名称
	/*private int pid;  //容器进程pid
 
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}*/
	public String getContainerName() {
		return containerName;
	}
	public void setContainerName(String containerName) {
		this.containerName = containerName;
	}

}
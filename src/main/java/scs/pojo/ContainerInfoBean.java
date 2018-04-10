package scs.pojo;
/**
 * 容器信息实体类
 * @author yanan
 *
 */
public class ContainerInfoBean {
	private String containerId;   //容器自分配的字符串id,业务处理可能用不到,但保留该项
	private String containerName; //容器名称,业务处理统一使用containerName做查询
	private String containerIp;   //容器ip 172.192.0.0/16
	private String containerHostName; //容器所在宿主机名称 
	private String containerCommand;  //容器启动时执行的命令
	private String containerImageName;//容器引用的镜像名称
 
	public String getContainerId() {
		return containerId;
	}
	public void setContainerId(String containerId) {
		this.containerId = containerId;
	}
	public String getContainerName() {
		return containerName;
	}
	public void setContainerName(String containerName) {
		this.containerName = containerName;
	}
	public String getContainerIp() {
		return containerIp;
	}
	public void setContainerIp(String containerIp) {
		this.containerIp = containerIp;
	}
	public String getContainerHostName() {
		return containerHostName;
	}
	public void setContainerHostName(String containerHostName) {
		this.containerHostName = containerHostName;
	}
	public String getContainerCommand() {
		return containerCommand;
	}
	public void setContainerCommand(String containerCommand) {
		this.containerCommand = containerCommand;
	}
	public String getContainerImageName() {
		return containerImageName;
	}
	public void setContainerImageName(String containerImageName) {
		this.containerImageName = containerImageName;
	}
}
 
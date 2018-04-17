package scs.pojo;
  
/**
 * 资源使用抽象类
 * @author yanan
 *
 */
public abstract class ResourceUsage {
	protected int autoId;    //数据库自增id
	protected Long collectTime;     //收集时间
	protected Float cpuUsageRate;   //%
	protected Float memUsageRate;   //%
	protected Float memUsageAmount; //MB
	protected Float netUsageRate;   //B
	protected Float ioUsageRate;    //B
	protected Float ioInput;   //磁盘写入数据量
	protected Float ioOutput;  //磁盘写出数据量
	protected Float netInput;  //网络写入数据量
	protected Float netOutput; //网络写出数据量 
	
	public int getAutoId() {
		return autoId;
	}
	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}
	public Long getCollectTime() {
		return collectTime;
	}
	public void setCollectTime(Long collectTime) {
		this.collectTime = collectTime;
	}
	public Float getCpuUsageRate() {
		return cpuUsageRate;
	}
	public void setCpuUsageRate(Float cpuUsageRate) {
		this.cpuUsageRate = cpuUsageRate;
	}
	public Float getMemUsageRate() {
		return memUsageRate;
	}
	public void setMemUsageRate(Float memUsageRate) {
		this.memUsageRate = memUsageRate;
	}
	public Float getMemUsageAmount() {
		return memUsageAmount;
	}
	public void setMemUsageAmount(Float memUsageAmount) {
		this.memUsageAmount = memUsageAmount;
	}
	public Float getNetUsageRate() {
		return netUsageRate;
	}
	public void setNetUsageRate(Float netUsageRate) {
		this.netUsageRate = netUsageRate;
	}
	public Float getIoUsageRate() {
		return ioUsageRate;
	}
	public void setIoUsageRate(Float ioUsageRate) {
		this.ioUsageRate = ioUsageRate;
	}
	public Float getIoInput() {
		return ioInput;
	}
	public void setIoInput(Float ioInput) {
		this.ioInput = ioInput;
	}
	public Float getIoOutput() {
		return ioOutput;
	}
	public void setIoOutput(Float ioOutput) {
		this.ioOutput = ioOutput;
	}
	public Float getNetInput() {
		return netInput;
	}
	public void setNetInput(Float netInput) {
		this.netInput = netInput;
	}
	public Float getNetOutput() {
		return netOutput;
	}
	public void setNetOutput(Float netOutput) {
		this.netOutput = netOutput;
	}
	 
}

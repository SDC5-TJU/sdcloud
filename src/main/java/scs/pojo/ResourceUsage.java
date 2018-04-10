package scs.pojo;

import java.sql.Date;

/**
 * 资源使用抽象类
 * @author yanan
 *
 */
public abstract class ResourceUsage {
	protected int autoId;    //数据库自增id
	protected Date collectTime;     //收集时间
	protected Float cpuUsageRate;   //%
	protected Float memUsageRate;   //%
	protected Float memUsageAmount; //MB
	protected Float netUsageRate;   //B
	protected Float ioUsageRate;    //B
	protected Float blockIo;        //MB
	
	public int getAutoId() {
		return autoId;
	}
	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}
	public Date getCollectTime() {
		return collectTime;
	}
	public void setCollectTime(Date collectTime) {
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
	public Float getBlockIo() {
		return blockIo;
	}
	public void setBlockIo(Float blockIo) {
		this.blockIo = blockIo;
	}
}

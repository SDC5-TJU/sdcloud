package scs.pojo.riscv;

import java.io.Serializable;

public class RiscvLLCPOJO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long collectTime;
	
	private double bandwidth;
	
	private double llcRequest;
	
	private double llcMisses;
	
	private double missesPercent;
	
	private double llcUsedCapacity;
	
	private double llcUsedPercent;

	public long getCollectTime() {
		return collectTime;
	}

	public void setCollectTime(long collectTime) {
		this.collectTime = collectTime;
	}

	public double getBandwidth() {
		return bandwidth;
	}

	public void setBandwidth(double bandwidth) {
		this.bandwidth = bandwidth;
	}

	public double getLlcRequest() {
		return llcRequest;
	}

	public void setLlcRequest(double llcRequest) {
		this.llcRequest = llcRequest;
	}

	public double getLlcMisses() {
		return llcMisses;
	}

	public void setLlcMisses(double llcMisses) {
		this.llcMisses = llcMisses;
	}

	public double getMissesPercent() {
		return missesPercent;
	}

	public void setMissesPercent(double missesPercent) {
		this.missesPercent = missesPercent;
	}

	public double getLlcUsedCapacity() {
		return llcUsedCapacity;
	}

	public void setLlcUsedCapacity(double llcUsedCapacity) {
		this.llcUsedCapacity = llcUsedCapacity;
	}

	public double getLlcUsedPercent() {
		return llcUsedPercent;
	}

	public void setLlcUsedPercent(double llcUsedPercent) {
		this.llcUsedPercent = llcUsedPercent;
	}

	@Override
	public String toString() {
		return "RiscvLLCPOJO [bandwidth=" + bandwidth + ", llcRequest=" + llcRequest + ", llcMisses=" + llcMisses
				+ ", missesPercent=" + missesPercent + ", llcUsedCapacity=" + llcUsedCapacity + ", llcUsedPercent="
				+ llcUsedPercent + "]";
	}
	
}

package scs.pojo.riscv;

public class RiscvRedisRealDataBean {
	private int qps;
	private int mean;
	private Long collectTime;
	private int min;
	private int fiftyTh;
	private int eightyTh;
	private int nintyTh;
	private int nintyNineTh;
	private int nintyNinePointNineTh;
	private int max;
	
	public int getQps() {
		return qps;
	}
	public void setQps(int qps) {
		this.qps = qps;
	}
	public int getMean() {
		return mean;
	}
	public void setMean(int mean) {
		this.mean = mean;
	}
	public Long getCollectTime() {
		return collectTime;
	}
	public void setCollectTime(Long collectTime) {
		this.collectTime = collectTime;
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getFiftyTh() {
		return fiftyTh;
	}
	public void setFiftyTh(int fiftyTh) {
		this.fiftyTh = fiftyTh;
	}
	public int getEightyTh() {
		return eightyTh;
	}
	public void setEightyTh(int eightyTh) {
		this.eightyTh = eightyTh;
	}
	public int getNintyTh() {
		return nintyTh;
	}
	public void setNintyTh(int nintyTh) {
		this.nintyTh = nintyTh;
	}
	public int getNintyNineTh() {
		return nintyNineTh;
	}
	public void setNintyNineTh(int nintyNineTh) {
		this.nintyNineTh = nintyNineTh;
	}
	public int getNintyNinePointNineTh() {
		return nintyNinePointNineTh;
	}
	public void setNintyNinePointNineTh(int nintyNinePointNineTh) {
		this.nintyNinePointNineTh = nintyNinePointNineTh;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RiscvRedisRealDataBean [qps=");
		builder.append(qps);
		builder.append(", mean=");
		builder.append(mean);
		builder.append(", collectTime=");
		builder.append(collectTime);
		builder.append(", min=");
		builder.append(min);
		builder.append(", fiftyTh=");
		builder.append(fiftyTh);
		builder.append(", eightyTh=");
		builder.append(eightyTh);
		builder.append(", nintyTh=");
		builder.append(nintyTh);
		builder.append(", nintyNineTh=");
		builder.append(nintyNineTh);
		builder.append(", nintyNinePointNineTh=");
		builder.append(nintyNinePointNineTh);
		builder.append(", max=");
		builder.append(max);
		builder.append("]");
		return builder.toString();
	}
}

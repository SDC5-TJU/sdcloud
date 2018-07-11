package scs.pojo.riscv;
/**
 * 课题2的数据实体类
 * @author LengZhenYu
 *
 */
public class DataProject2Bean {
	private float ceph;
	private int tnumCeph;
	private float gecko;
	private int tnumGecko;
	private Long collectTime;
	
	public float getCeph() {
		return ceph;
	}
	public void setCeph(float ceph) {
		this.ceph = ceph;
	}
	public int getTnumCeph() {
		return tnumCeph;
	}
	public void setTnumCeph(int tnumCeph) {
		this.tnumCeph = tnumCeph;
	}	
	public float getGecko() {
		return gecko;
	}
	public void setGecko(float gecko) {
		this.gecko = gecko;
	}
	public int getTnumGecko() {
		return tnumGecko;
	}
	public void setTnumGecko(int tnumGecko) {
		this.tnumGecko = tnumGecko;
	}		
	public Long getCollectTime() {
		return collectTime;
	}
	public void setCollectTime(Long collectTime) {
		this.collectTime = collectTime;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DataProject2 [ceph=");
		builder.append(ceph);
		builder.append(", Tnum Ceph=");
		builder.append(tnumCeph);		
		builder.append(", gecko=");
		builder.append(gecko);
		builder.append(", Tnum Gecko=");
		builder.append(tnumGecko);
		builder.append(", collectTime=");
		builder.append(collectTime);
		builder.append("]");
		return builder.toString();
	}
}

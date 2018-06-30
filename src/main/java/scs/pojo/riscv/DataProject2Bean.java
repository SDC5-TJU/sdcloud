package scs.pojo.riscv;
/**
 * 课题2的数据实体类
 * @author LengZhenYu
 *
 */
public class DataProject2Bean {
	private float ceph;
	private float gecko;
	private Long collectTime;
	
	public float getCeph() {
		return ceph;
	}
	public void setCeph(float ceph) {
		this.ceph = ceph;
	}
	public float getGecko() {
		return gecko;
	}
	public void setGecko(float gecko) {
		this.gecko = gecko;
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
		builder.append(", gecko=");
		builder.append(gecko);
		builder.append(", collectTime=");
		builder.append(collectTime);
		builder.append("]");
		return builder.toString();
	}
}

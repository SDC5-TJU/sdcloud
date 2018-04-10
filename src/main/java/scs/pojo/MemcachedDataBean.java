package scs.pojo;
/**
 * memcached应用响应数据实体类
 * @author yanan
 *
 */
public class MemcachedDataBean {
	
	private float timeDiff;
	private float rps; //每秒钟请求数
	private float requests; //请求次数
	private float gets; //读操作数
	private float sets; //写操作数
	private float hits; //命中次数
	private float misses; //未命中次数
	private float avg_lat; //平均延迟
	private float nintyTh; //90th
	private float nintyFiveTh; //95th
	private float nintyNineTh; //99th
	private float std; //标准差
	private float min; //最小值
	private float max; //最大值
	private float avgGetSize; //平均读操作数据大小
	
	public MemcachedDataBean() {}
	
	public MemcachedDataBean(float timeDiff, float rps, float requests, float gets, float sets, float hits, float misses, float avg_lat, float nintyTh, float nintyFiveTh, float nintyNineTh, float std, float min, float max, float avgGetSize) {
		this.timeDiff = timeDiff;
		this.rps = rps;
		this.requests = requests;
		this.gets = gets;
		this.sets = sets;
		this.hits = hits;
		this.misses = misses;
		this.avg_lat = avg_lat;
		this.nintyTh = nintyTh;
		this.nintyFiveTh = nintyFiveTh;
		this.nintyNineTh = nintyNineTh;
		this.std = std;
		this.min = min;
		this.max = max;
		this.avgGetSize = avgGetSize;
	} 
	
	public float getTimeDiff() {
		return timeDiff;
	}
	public void setTimeDiff(float timeDiff) {
		this.timeDiff = timeDiff;
	}
	public float getRps() {
		return rps;
	}
	public void setRps(float rps) {
		this.rps = rps;
	}
	public float getRequests() {
		return requests;
	}
	public void setRequests(float requests) {
		this.requests = requests;
	}
	public float getGets() {
		return gets;
	}
	public void setGets(float gets) {
		this.gets = gets;
	}
	public float getSets() {
		return sets;
	}
	public void setSets(float sets) {
		this.sets = sets;
	}
	public float getHits() {
		return hits;
	}
	public void setHits(float hits) {
		this.hits = hits;
	}
	public float getMisses() {
		return misses;
	}
	public void setMisses(float misses) {
		this.misses = misses;
	}
	public float getAvg_lat() {
		return avg_lat;
	}
	public void setAvg_lat(float avg_lat) {
		this.avg_lat = avg_lat;
	}
	public float getNintyTh() {
		return nintyTh;
	}
	public void setNintyTh(float nintyTh) {
		this.nintyTh = nintyTh;
	}
	public float getNintyFiveTh() {
		return nintyFiveTh;
	}
	public void setNintyFiveTh(float nintyFiveTh) {
		this.nintyFiveTh = nintyFiveTh;
	}
	public float getNintyNineTh() {
		return nintyNineTh;
	}
	public void setNintyNineTh(float nintyNineTh) {
		this.nintyNineTh = nintyNineTh;
	}
	public float getStd() {
		return std;
	}
	public void setStd(float std) {
		this.std = std;
	}
	public float getMin() {
		return min;
	}
	public void setMin(float min) {
		this.min = min;
	}
	public float getMax() {
		return max;
	}
	public void setMax(float max) {
		this.max = max;
	}
	public float getAvgGetSize() {
		return avgGetSize;
	}
	public void setAvgGetSize(float avgGetSize) {
		this.avgGetSize = avgGetSize;
	}

	@Override
	public String toString() {
		return "MemcachedDataBean [timeDiff=" + timeDiff + ", rps=" + rps + ", requests=" + requests + ", gets=" + gets
				+ ", sets=" + sets + ", hits=" + hits + ", misses=" + misses + ", avg_lat=" + avg_lat + ", nintyTh="
				+ nintyTh + ", nintyFiveTh=" + nintyFiveTh + ", nintyNineTh=" + nintyNineTh + ", std=" + std + ", min="
				+ min + ", max=" + max + ", avgGetSize=" + avgGetSize + "]";
	}
}

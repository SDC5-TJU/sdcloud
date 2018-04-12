package scs.pojo;

import java.util.List; 
/**
 * 在线应用响应时间统计结果实体类
 * @author yanan
 *
 */
public class TimeResultBean {
	private float nintyth; //响应时间90分位数
	private float nintyFiveTh; //响应时间95分位数
	private float nintyNineTh; //响应时间99分位数
	private List<TwoTuple<Float,Float>> CDF;//累积概率分布<x,y>数组
	private float var; //响应时间方差
	private float mean;//响应时间平均值
	private float min; //响应时间最小值
	private float max; //响应时间最大值
	private float missRate;//缺失率
	
	public TimeResultBean(){}
	public TimeResultBean(float nintyth, float nintyFiveTh, float nintyNineTh, List<TwoTuple<Float, Float>> cDF,
			float var, float mean, float min, float max, float missRate) {
		this.nintyth = nintyth;
		this.nintyFiveTh = nintyFiveTh;
		this.nintyNineTh = nintyNineTh;
		CDF = cDF;
		this.var = var;
		this.mean = mean;
		this.min = min;
		this.max = max;
		this.missRate = missRate;
	}
	public float getNintyth() {
		return nintyth;
	}
	public void setNintyth(float nintyth) {
		this.nintyth = nintyth;
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
	public List<TwoTuple<Float, Float>> getCDF() {
		return CDF;
	}
	public void setCDF(List<TwoTuple<Float, Float>> cDF) {
		CDF = cDF;
	}
	public float getVar() {
		return var;
	}
	public void setVar(float var) {
		this.var = var;
	}
	public float getMean() {
		return mean;
	}
	public void setMean(float mean) {
		this.mean = mean;
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
	public float getMissRate() {
		return missRate;
	}
	public void setMissRate(float missRate) {
		this.missRate = missRate;
	}
	@Override
	public String toString() {
		return "TimeResultBean [nintyth=" + nintyth + ", nintyFiveTh=" + nintyFiveTh + ", nintyNineTh=" + nintyNineTh
				+ ", CDF=" + CDF + ", var=" + var + ", mean=" + mean + ", min=" + min + ", max=" + max + "]";
	}

}

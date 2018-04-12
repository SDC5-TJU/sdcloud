package scs.pojo;

import java.util.List; 
/**
 * 在线应用响应时间统计结果实体类
 * @author yanan
 *
 */
public class TimeResultDiffBean {
	/*private float nintyth; //响应时间90分位数
	private float nintyFiveTh; //响应时间95分位数
	private float nintyNineTh; //响应时间99分位数 
	private float var; //响应时间方差
	private float mean;//响应时间平均值
	private float min; //响应时间最小值
	private float max; //响应时间最大值
	private float baseNintyth; //基准值响应时间90分位数
	private float baseNintyFiveTh; //基准值响应时间95分位数
	private float baseNintyNineTh; //基准值响应时间99分位数 
	private float baseVar; //基准值响应时间方差
	private float baseMean;//基准值响应时间平均值
	private float baseM; //基准值响应时间最小值
	private TimeResultBean.javafloat baseMax; //基准值响应时间最大值*/
	private TimeResultBean baseResult;
	private TimeResultBean result;
	private float nintythDiff; //响应时间90分位数差异
	private float nintyFiveThDiff; //响应时间95分位数差异
	private float nintyNineThDiff; //响应时间99分位数 差异
	private float varDiff; //响应时间方差差异
	private float meanDiff;//响应时间平均值差异
	private float minDiff; //响应时间最小值差异
	private float maxDiff; //响应时间最大值差异
	
	public TimeResultDiffBean(){}
	public TimeResultDiffBean(TimeResultBean baseResult, TimeResultBean result, float nintythDiff,
			float nintyFiveThDiff, float nintyNineThDiff, float varDiff, float meanDiff, float minDiff, float maxDiff) {
		super();
		this.baseResult = baseResult;
		this.result = result;
		this.nintythDiff = nintythDiff;
		this.nintyFiveThDiff = nintyFiveThDiff;
		this.nintyNineThDiff = nintyNineThDiff;
		this.varDiff = varDiff;
		this.meanDiff = meanDiff;
		this.minDiff = minDiff;
		this.maxDiff = maxDiff;
	}
	public TimeResultBean getBaseResult() {
		return baseResult;
	}
	public void setBaseResult(TimeResultBean baseResult) {
		this.baseResult = baseResult;
	}
	public TimeResultBean getResult() {
		return result;
	}
	public void setResult(TimeResultBean result) {
		this.result = result;
	}
	public float getNintythDiff() {
		return nintythDiff;
	}
	public void setNintythDiff(float nintythDiff) {
		this.nintythDiff = nintythDiff;
	}
	public float getNintyFiveThDiff() {
		return nintyFiveThDiff;
	}
	public void setNintyFiveThDiff(float nintyFiveThDiff) {
		this.nintyFiveThDiff = nintyFiveThDiff;
	}
	public float getNintyNineThDiff() {
		return nintyNineThDiff;
	}
	public void setNintyNineThDiff(float nintyNineThDiff) {
		this.nintyNineThDiff = nintyNineThDiff;
	}
	public float getVarDiff() {
		return varDiff;
	}
	public void setVarDiff(float varDiff) {
		this.varDiff = varDiff;
	}
	public float getMeanDiff() {
		return meanDiff;
	}
	public void setMeanDiff(float meanDiff) {
		this.meanDiff = meanDiff;
	}
	public float getMinDiff() {
		return minDiff;
	}
	public void setMinDiff(float minDiff) {
		this.minDiff = minDiff;
	}
	public float getMaxDiff() {
		return maxDiff;
	}
	public void setMaxDiff(float maxDiff) {
		this.maxDiff = maxDiff;
	}
	@Override
	public String toString() {
		return "TimeResultDiffBean [baseResult=" + baseResult + ", result=" + result + ", nintythDiff=" + nintythDiff
				+ ", nintyFiveThDiff=" + nintyFiveThDiff + ", nintyNineThDiff=" + nintyNineThDiff + ", varDiff="
				+ varDiff + ", meanDiff=" + meanDiff + ", minDiff=" + minDiff + ", maxDiff=" + maxDiff + "]";
	}
	 
}

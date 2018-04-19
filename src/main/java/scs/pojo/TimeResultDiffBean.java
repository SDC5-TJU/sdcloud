package scs.pojo;
 

import scs.util.format.DataFormats; 
/**
 * 在线应用响应时间统计结果实体类
 * @author yanan
 *
 */
public class TimeResultDiffBean {
	private DataFormats format=DataFormats.getInstance();
	private String baseTimeStr;
	private String timeStr;
	private String baseCDFStr;
	private String CDFStr;
	private float nintyThDiff; //响应时间90分位数差异
	private float nintyFiveThDiff; //响应时间95分位数差异
	private float nintyNineThDiff; //响应时间99分位数 差异
	private float varDiff; //响应时间方差差异
	private float meanDiff;//响应时间平均值差异
	private float minDiff; //响应时间最小值差异
	private float maxDiff; //响应时间最大值差异
	private float missRateDiff;//缺失率差异 
	
	public TimeResultDiffBean(){}

	public DataFormats getFormat() {
		return format;
	}

	public void setFormat(DataFormats format) {
		this.format = format;
	}

	public String getBaseTimeStr() {
		return baseTimeStr;
	}

	public void setBaseTimeStr(String baseTimeStr) {
		this.baseTimeStr = baseTimeStr;
	}

	public String getTimeStr() {
		return timeStr;
	}

	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
	}

	public String getBaseCDFStr() {
		return baseCDFStr;
	}

	public void setBaseCDFStr(String baseCDFStr) {
		this.baseCDFStr = baseCDFStr;
	}

	public String getCDFStr() {
		return CDFStr;
	}

	public void setCDFStr(String cDFStr) {
		CDFStr = cDFStr;
	}

	public float getNintyThDiff() {
		return nintyThDiff;
	}

	public void setNintyThDiff(float nintyThDiff) {
		this.nintyThDiff = nintyThDiff;
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

	public float getMissRateDiff() {
		return missRateDiff;
	}

	public void setMissRateDiff(float missRateDiff) {
		this.missRateDiff = missRateDiff;
	}
 
 
}
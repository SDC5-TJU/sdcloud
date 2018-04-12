package scs.util.tools;

import java.util.List;

import scs.pojo.TimeResultBean;
import scs.pojo.TimeResultDiffBean;
import scs.pojo.TwoTuple;

public class ResultDiffParse {
	/**
	 * 单例模式
	 */
	private static ResultDiffParse parse=null;
	private ResultDiffParse(){}
	public synchronized static ResultDiffParse getInstance() {
		if (parse == null) {  
			parse = new ResultDiffParse();
		}  
		return parse;
	}
	/**
	 * private float nintyth; //响应时间90分位数
	private float nintyFiveTh; //响应时间95分位数
	private float nintyNineTh; //响应时间99分位数
	private List<TwoTuple<Float,Float>> CDF;//累积概率分布<x,y>数组
	private float var; //响应时间方差
	private float mean;//响应时间平均值
	private float min; //响应时间最小值
	private float max; //响应时间最大值
	 * @param baseBean
	 * @param bean
	 */
	public TimeResultDiffBean getResultDiff(TimeResultBean baseBean,TimeResultBean bean){
		TimeResultDiffBean diffBean=new TimeResultDiffBean();
		float nintythDiff=0;
		float nintyFiveThDiff=0;
		float nintyNineThDiff=0;
		float varDiff=0;
		float meanDiff=0;
		float minDiff=0;
		float maxDiff=0;
		//计算90分位数
		if(baseBean.getNintyth()!=0){
			nintythDiff=(bean.getNintyth()-baseBean.getNintyth())/baseBean.getNintyth()*100;
		}  
		diffBean.setNintythDiff(nintythDiff);
		//计算95分位数
		if(baseBean.getNintyFiveTh()!=0){
			nintyFiveThDiff=(bean.getNintyFiveTh()-baseBean.getNintyFiveTh())/baseBean.getNintyFiveTh()*100;
		}  
		diffBean.setNintyFiveThDiff(nintyFiveThDiff);
		//计算99分位数
		if(baseBean.getNintyNineTh()!=0){
			nintyNineThDiff=(bean.getNintyNineTh()-baseBean.getNintyNineTh())/baseBean.getNintyNineTh()*100;
		}  
		diffBean.setNintyNineThDiff(nintyNineThDiff);
		//计算90分位数
		if(baseBean.getVar()!=0){
			varDiff=(bean.getVar()-baseBean.getVar())/baseBean.getVar()*100;
		}  
		diffBean.setNintythDiff(nintythDiff);
		//计算90分位数
		if(baseBean.getNintyth()!=0){
			nintythDiff=(bean.getNintyth()-baseBean.getNintyth())/baseBean.getNintyth()*100;
		}  
		diffBean.setNintythDiff(nintythDiff);
		//计算90分位数
		if(baseBean.getNintyth()!=0){
			nintythDiff=(bean.getNintyth()-baseBean.getNintyth())/baseBean.getNintyth()*100;
		}  
		diffBean.setNintythDiff(nintythDiff);
		return diffBean;
	}

}

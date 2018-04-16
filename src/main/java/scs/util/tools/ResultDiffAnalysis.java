package scs.util.tools;

import java.util.List; 
import scs.pojo.SiloDataBean;
import scs.pojo.TimeResultBean;
import scs.pojo.TimeResultDiffBean;
import scs.pojo.TwoTuple; 

public class ResultDiffAnalysis {
	/**
	 * 单例模式
	 */
	private static ResultDiffAnalysis parse=null;
	private ResultDiffAnalysis(){}
	public synchronized static ResultDiffAnalysis getInstance() {
		if (parse == null) {  
			parse = new ResultDiffAnalysis();
		}  
		return parse;
	}
	 
	/**
	 * 计算干扰和无干扰下的在线应用响应差异
	 * @param baseTimeList
	 * @param timeList
	 * @param baseBean
	 * @param bean
	 * @return
	 */
	public TimeResultDiffBean getResultDiff(List<TwoTuple<Long,Integer>> baseTimeList,List<TwoTuple<Long,Integer>> timeList,TimeResultBean baseBean,TimeResultBean bean){
		TimeResultDiffBean diffBean=new TimeResultDiffBean();
		float nintythDiff=0;
		float nintyFiveThDiff=0;
		float nintyNineThDiff=0;
		float varDiff=0;
		float meanDiff=0;
		float minDiff=0;
		float maxDiff=0;
		float missRateDiff=0;
		//计算90分位数
		if(baseBean.getNintyTh()!=0){
			nintythDiff=(bean.getNintyTh()-baseBean.getNintyTh())/baseBean.getNintyTh()*100;
		}  
		diffBean.setNintyThDiff(nintythDiff);
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
		//计算方差
		if(baseBean.getVar()!=0){
			varDiff=(bean.getVar()-baseBean.getVar())/baseBean.getVar()*100;
		}  
		diffBean.setVarDiff(varDiff);
		//计算平均值
		if(baseBean.getMean()!=0){
			meanDiff=(bean.getMean()-baseBean.getMean())/baseBean.getMean()*100;
		}  
		diffBean.setMeanDiff(meanDiff);
		//计算最小值差异
		if(baseBean.getMin()!=0){
			minDiff=(bean.getMin()-baseBean.getMin())/baseBean.getMin()*100;
		}  
		diffBean.setMinDiff(minDiff);
		//计算最大值差异
		if(baseBean.getMax()!=0){
			maxDiff=(bean.getMax()-baseBean.getMax())/baseBean.getMax()*100;
		}  
		diffBean.setMaxDiff(maxDiff);
		//计算缺失率差异
		if(baseBean.getMissRate()!=0){
			missRateDiff=(bean.getMissRate()-baseBean.getMissRate())/baseBean.getMissRate()*100;
		}
		diffBean.setMissRateDiff(missRateDiff);
		/*
		 * 绘制无干扰下延迟分布曲线
		 */ 
		StringBuffer strName=new StringBuffer();
		StringBuffer strData=new StringBuffer();
		StringBuffer HSeries=new StringBuffer();
		strName.append("{name:'无干扰下访问延迟分布',lineWidth:0.5,"); 
		strData.append("data:[");
		int size=baseTimeList.size()-1;
		for(int i=0;i<size;i++){
			if(baseTimeList.get(i).second!=65535){
				strData.append("[").append(baseTimeList.get(i).first).append(",").append(baseTimeList.get(i).second).append("],");
			}else{
				strData.append("[").append(baseTimeList.get(i).first).append(",").append(-1).append("],");	
			}
		}
		if(baseTimeList.get(size).second!=65535){
			strData.append("[").append(baseTimeList.get(size).first).append(",").append(baseTimeList.get(size).second).append("]]");
		}else{
			strData.append("[").append(baseTimeList.get(size).first).append(",").append(-1).append("]]");
		}
		HSeries.append(strName).append(strData).append("}");
		diffBean.setBaseTimeStr(HSeries.toString()); 
		/*
		 * 绘制干扰下延迟分布曲线
		 */
		strName.setLength(0);
		strData.setLength(0);
		HSeries.setLength(0);
		strName.append("{name:'干扰下访问延迟分布',lineWidth:0.5,"); 
		strData.append("data:[");   
		size=timeList.size()-1;
		for(int i=0;i<size;i++){
			if(timeList.get(i).second!=65535){
				strData.append("[").append(timeList.get(i).first).append(",").append(timeList.get(i).second).append("],");
			}else{
				strData.append("[").append(timeList.get(i).first).append(",").append(-1).append("],");	
			}
		}
		if(timeList.get(size).second!=65535){
			strData.append("[").append(timeList.get(size).first).append(",").append(timeList.get(size).second).append("]]");
		}else{
			strData.append("[").append(timeList.get(size).first).append(",").append(-1).append("]]");
		}
		HSeries.append(strName).append(strData).append("}");
		diffBean.setTimeStr(HSeries.toString());

		/*
		 * 绘制无干扰下cdf曲线
		 */
		strName.setLength(0);
		strData.setLength(0);
		HSeries.setLength(0);
		strName.append("{name:'无干扰下cdf分布',"); 
		strData.append("data:[");   
		List<TwoTuple<Float,Float>> baseCDFList=baseBean.getCDF();
		size=baseCDFList.size()-1;
		for(int i=0;i<size;i++){ 
			strData.append("[").append(baseCDFList.get(i).first).append(",").append(baseCDFList.get(i).second).append("],");
		}
		strData.append("[").append(baseCDFList.get(size).first).append(",").append(baseCDFList.get(size).second).append("]]");

		HSeries.append(strName).append(strData).append("}");
		diffBean.setBaseCDFStr(HSeries.toString());
		
		/*
		 * 绘制干扰下cdf曲线
		 */
		strName.setLength(0);
		strData.setLength(0);
		HSeries.setLength(0);
		strName.append("{name:'干扰下cdf分布',");
		strData.append("data:[");
		List<TwoTuple<Float,Float>> CDFList=bean.getCDF();
		size=CDFList.size()-1;
		for(int i=0;i<size;i++){
			strData.append("[").append(CDFList.get(i).first).append(",").append(CDFList.get(i).second).append("],");
		}
		strData.append("[").append(CDFList.get(size).first).append(",").append(CDFList.get(size).second).append("]]");
		HSeries.append(strName).append(strData).append("}");
		diffBean.setCDFStr(HSeries.toString());
		
		return diffBean;
	}
	/**
	 * 计算silo干扰和无干扰下的在线应用响应差异
	 * @param baseTimeList
	 * @param timeList
	 * @param baseBean
	 * @param bean
	 * @return
	 */
	public TimeResultDiffBean getsiloResultDiff(List<SiloDataBean> baseTimeList,List<SiloDataBean> timeList,TimeResultBean baseBean,TimeResultBean bean){
		TimeResultDiffBean diffBean=new TimeResultDiffBean();
		float nintythDiff=0;
		float nintyFiveThDiff=0;
		float nintyNineThDiff=0;
		float varDiff=0;
		float meanDiff=0;
		float minDiff=0;
		float maxDiff=0;
		float missRateDiff=0;
		//计算90分位数
		if(baseBean.getNintyTh()!=0){
			nintythDiff=(bean.getNintyTh()-baseBean.getNintyTh())/baseBean.getNintyTh()*100;
		}  
		diffBean.setNintyThDiff(nintythDiff);
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
		//计算方差
		if(baseBean.getVar()!=0){
			varDiff=(bean.getVar()-baseBean.getVar())/baseBean.getVar()*100;
		}  
		diffBean.setVarDiff(varDiff);
		//计算平均值
		if(baseBean.getMean()!=0){
			meanDiff=(bean.getMean()-baseBean.getMean())/baseBean.getMean()*100;
		}  
		diffBean.setMeanDiff(meanDiff);
		//计算最小值差异
		if(baseBean.getMin()!=0){
			minDiff=(bean.getMin()-baseBean.getMin())/baseBean.getMin()*100;
		}  
		diffBean.setMinDiff(minDiff);
		//计算最大值差异
		if(baseBean.getMax()!=0){
			maxDiff=(bean.getMax()-baseBean.getMax())/baseBean.getMax()*100;
		}  
		diffBean.setMaxDiff(maxDiff);
		//计算缺失率差异
		if(baseBean.getMissRate()!=0){
			missRateDiff=(bean.getMissRate()-baseBean.getMissRate())/baseBean.getMissRate()*100;
		}
		diffBean.setMissRateDiff(missRateDiff);
		/*
		 * 绘制无干扰下延迟分布曲线
		 */ 
		StringBuffer strName=new StringBuffer();
		StringBuffer strData=new StringBuffer();
		StringBuffer HSeries=new StringBuffer();
		strName.append("{name:'无干扰下访问延迟分布',lineWidth:0.5,");
		strData.append("data:[");
		int size=baseTimeList.size()-1;
		for(int i=0;i<size;i++){
			strData.append(baseTimeList.get(i).getQueryTime()).append(",");
		}
		strData.append(baseTimeList.get(size).getQueryTime()).append("]");
		HSeries.append(strName).append(strData).append("}");
		diffBean.setBaseTimeStr(HSeries.toString()); 
		/*
		 * 绘制干扰下延迟分布曲线
		 */
		strName.setLength(0);
		strData.setLength(0);
		HSeries.setLength(0);
		strName.append("{name:'干扰下访问延迟分布',lineWidth:0.5,"); 
		strData.append("data:[");
		size=timeList.size()-1;
		for(int i=0;i<size;i++){
			strData.append(timeList.get(i).getQueryTime()).append(",");
		}
		strData.append(timeList.get(size).getQueryTime()).append("]");
		HSeries.append(strName).append(strData).append("}");
		diffBean.setTimeStr(HSeries.toString()); 
		/*
		 * 绘制无干扰下cdf曲线
		 */
		strName.setLength(0);
		strData.setLength(0);
		HSeries.setLength(0);
		strName.append("{name:'无干扰下cdf分布',"); 
		strData.append("data:[");   
		List<TwoTuple<Float,Float>> baseCDFList=baseBean.getCDF();
		size=baseCDFList.size()-1;
		for(int i=0;i<size;i++){ 
			strData.append("[").append(baseCDFList.get(i).first).append(",").append(baseCDFList.get(i).second).append("],");
		}
		strData.append("[").append(baseCDFList.get(size).first).append(",").append(baseCDFList.get(size).second).append("]]");
	
		HSeries.append(strName).append(strData).append("}");
		diffBean.setBaseCDFStr(HSeries.toString());
		
		/*
		 * 绘制干扰下cdf曲线
		 */
		strName.setLength(0);
		strData.setLength(0);
		HSeries.setLength(0);
		strName.append("{name:'干扰下cdf分布',");
		strData.append("data:[");
		List<TwoTuple<Float,Float>> CDFList=bean.getCDF();
		size=CDFList.size()-1;
		for(int i=0;i<size;i++){
			strData.append("[").append(CDFList.get(i).first).append(",").append(CDFList.get(i).second).append("],");
		}
		strData.append("[").append(CDFList.get(size).first).append(",").append(CDFList.get(size).second).append("]]");
		HSeries.append(strName).append(strData).append("}");
		diffBean.setCDFStr(HSeries.toString());
		
		return diffBean;
	}
	 /**
		 * 计算silo干扰和无干扰下的在线应用响应差异
		 * @param baseTimeList
		 * @param timeList
		 * @param baseBean
		 * @param bean
		 * @return
		 */
		public TimeResultDiffBean getMemResultDiff(TimeResultBean baseBean,TimeResultBean bean){
			TimeResultDiffBean diffBean=new TimeResultDiffBean();
			float nintythDiff=0;
			float nintyFiveThDiff=0;
			float nintyNineThDiff=0;
			float varDiff=0;
			float meanDiff=0;
			float minDiff=0;
			float maxDiff=0;
			float missRateDiff=0;
			//计算90分位数
			if(baseBean.getNintyTh()!=0){
				nintythDiff=(bean.getNintyTh()-baseBean.getNintyTh())/baseBean.getNintyTh()*100;
			}  
			diffBean.setNintyThDiff(nintythDiff);
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
			//计算方差
			if(baseBean.getVar()!=0){
				varDiff=(bean.getVar()-baseBean.getVar())/baseBean.getVar()*100;
			}  
			diffBean.setVarDiff(varDiff);
			//计算平均值
			if(baseBean.getMean()!=0){
				meanDiff=(bean.getMean()-baseBean.getMean())/baseBean.getMean()*100;
			}  
			diffBean.setMeanDiff(meanDiff);
			//计算最小值差异
			if(baseBean.getMin()!=0){
				minDiff=(bean.getMin()-baseBean.getMin())/baseBean.getMin()*100;
			}  
			diffBean.setMinDiff(minDiff);
			//计算最大值差异
			if(baseBean.getMax()!=0){
				maxDiff=(bean.getMax()-baseBean.getMax())/baseBean.getMax()*100;
			}  
			diffBean.setMaxDiff(maxDiff);
			//计算缺失率差异
			if(baseBean.getMissRate()!=0){
				missRateDiff=(bean.getMissRate()-baseBean.getMissRate())/baseBean.getMissRate()*100;
			}
			diffBean.setMissRateDiff(missRateDiff);
			 
			return diffBean;
		}
}

//package cn.tju.scs;
package scs.util.tools; 
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import scs.pojo.AppConfigBean;
import scs.pojo.MemcachedDataBean;
import scs.pojo.SiloDataBean;
import scs.pojo.TimeResultBean;
import scs.pojo.TwoTuple;
import scs.util.repository.Repository;
/**
 * 请求结果处理类
 * @author Juane
 *
 */
//加一个缺失率，65535
// 适配器--名字
class ResultParser {
	/*
	 * 计算如下值：
	 float nintyth; //响应时间90分位数
	 float nintyFiveTh; //响应时间95分位数
	 float nintyNineTh; //响应时间99分位数
	 List<TwoTuple<Float,Float>> CDF;//累积概率分布<x,y>数组
	 float var; //响应时间方差
	 float mean;//响应时间平均值
	 float min; //响应时间最小值
	 float max; //响应时间最大值
	 float miss; // 响应缺失值
	*/
	public TimeResultBean  calculate(List<TwoTuple<Long, Integer>> list){
		
		TimeResultBean bean = new TimeResultBean();
		
		
		//对list 排序 计算需要计算的各分位数
		// 将需要进行分位数计算的数据单独取出来存储在 newlist 中
		List<Integer> newlist = new ArrayList<Integer>();
		Integer temp = null;
		Integer sum = 0;
		for(int i = 0; i < list.size(); i++) {
			temp = list.get(i).second;
			newlist.add(temp);
			sum += temp;
		}
		
		// 对newlist进行排序
		Collections.sort(newlist);
		//System.out.println(newlist);
		//size
		int size = newlist.size();
		System.out.println("SIZE IS "+ size);
		//max
		int max =  (int) newlist.get(size-1);
		float max1 = max;
		bean.setMax(max1);
		
		//min
		int min = (int) newlist.get(0);
		float min1 =min;
		bean.setMin(min1);
		
		//mean
		float sum1 = (float)sum;
		float mean = sum1 / size;
		//System.out.println("SUM IS "+sum1 + " MEAN IS: " + mean);
		bean.setMean(mean);
		//var
		float var =0.0f;		
		for(int i = 0; i <size; i++ ) {
			int temp1 = (int) newlist.get(i);
			float temp2 = temp1;
			var = var + (temp2 - mean)*(temp2- mean);
		}
		var = var / size;
		bean.setVar(var);
		
		//nintyth
		float nintyth = 0.0f;
		int j = (int)((size) * 0.9);
		//float g = (float) ((size + 1 )*0.9 - j);
		//nintyth = g * (float)(int)newlist.get(j) + (1-g)* (float)(int)newlist.get(j-1);
		float xj = (float)(int)newlist.get(j);
		float xj_1 = (float)(int)newlist.get(j-1);
		nintyth = (float) ((size * 0.9 - j)*( xj - xj_1) + xj_1);
		bean.setNintyTh(nintyth);
		
		//nintyFiveTh
		float nintyFiveTh = 0.0f;
		 j = (int)((size) * 0.95);
		//float g = (float) ((size + 1 )*0.95 - j);
		xj = (float)(int)newlist.get(j);
		xj_1 = (float)(int)newlist.get(j-1);
		nintyFiveTh = (float) ((size * 0.95 - j)*( xj - xj_1) + xj_1);
			
		 //nintyFiveTh = g * (float)(int)newlist.get(j) + (1-g)* (float)(int)newlist.get(j-1);
		bean.setNintyFiveTh(nintyFiveTh);
		
		//nintyNineTh
		float nintyNineTh = 0.0f;
		 j = (int)((size) * 0.99);
		// g = (float) ((size + 1 )*0.99 - j);
		// nintyNineTh = g * (float)(int)newlist.get(j) + (1-g)* (float)(int)newlist.get(j-1);
		xj = (float)(int)newlist.get(j);
		xj_1 = (float)(int)newlist.get(j-1);
		nintyNineTh = (float) ((size * 0.95 - j)*( xj - xj_1) + xj_1);
			
		 bean.setNintyNineTh(nintyNineTh);
		
		//CDF
		 bean.setCDF(getCDF(newlist));
		/**
		  * List<TwoTuple<Float,Float>> cdf = new ArrayList();
		  * float percent;
		  *	//Integer counts = 1;
		  * int sumcount = 0;
		  * for(int i=0; i < size-1; i++) {
		  * sumcount++;
		  *
		  * if((int)newlist.get(i) ==(int)newlist.get(i+1)) {
		  *	//System.out.println(i + " is =" + newlist.get(i) +" "+newlist.get(i+1));
		  * }else {		
		  * 	//System.out.println(i + " is " + newlist.get(i) +" "+newlist.get(i+1));
		  *	
		  *	//float percent = (float)counts/ (float)size;
		  *	percent = (float)sumcount/ (float)size;
		  *	cdf.add(new TwoTuple<Float,Float>((float)(int)newlist.get(i),percent));
		  *	//counts = 1;
		  *	
		  * }
		  * }
		  *  percent = 1.0f;
		  *  cdf.add(new TwoTuple<Float,Float>((float)(int)newlist.get(size-1),percent));
		  *
		  */
		
		
		// miss
		float miss = 0.0f;
		int missCount =0;
		for(int i = 0; i < size; i++){
			if(newlist.get(i) == 65535){
				missCount++;
			}
		}
		miss = missCount / size;
		bean.setMissRate(miss);
		
		return bean;
	}
	public TimeResultBean calculateM( List<MemcachedDataBean> memcache){
		TimeResultBean bean = new TimeResultBean();
		int size = memcache.size();
		float sumMax = 0.0f, max;
		float sumMean  =  0.0f, mean;
		float sumMin = 0.0f, min;
		float sumMiss = 0.0f,miss;
		float sumHits = 0.0f;
		float sumNF = 0.0f,nintyFiveTh;
		float sumNN = 0.0f, nintyNinTh;
		float sumN = 0.0f, nintyTh;
		float sumS = 0.0f, var;
		for(int i = 0; i < size; i++){
			sumMax += memcache.get(i).getMax();
			sumMean += memcache.get(i).getAvg_lat();
			sumMin += memcache.get(i).getMin();
			sumMiss += memcache.get(i).getMisses();
			sumHits += memcache.get(i).getHits();
			sumNF += memcache.get(i).getNintyFiveTh();
			sumNN += memcache.get(i).getNintyNineTh();
			sumN += memcache.get(i).getNintyTh();
			sumS += memcache.get(i).getStd(); 
		}
		max = sumMax/size;
		bean.setMax(max);
	
		mean = sumMean / size;
		bean.setMean(mean);
		
		min = sumMin / size;
		bean.setMin(min);
		
		miss = (sumMiss)/(sumMiss + sumHits);
		bean.setMissRate(miss);
		
		nintyFiveTh = sumNF/size;
		bean.setNintyFiveTh(nintyFiveTh);
		
		nintyNinTh = sumNN/size;
		bean.setNintyNineTh(nintyNinTh);
		
		nintyTh = sumN / size;
		bean.setNintyNineTh(nintyTh);
		
		var = (sumS/size) * (sumS/size);
		bean.setVar(var);
		
//		bean.setMax(memcache.getMax());
//		bean.setMean(memcache.getAvg_lat());
//		bean.setMin(memcache.getMin());
//		float miss = memcache.getMisses() / (memcache.getMisses() + memcache.getHits());
//		bean.setMiss(miss);
//		bean.setNintyFiveTh(memcache.getNintyFiveTh());
//		bean.setNintyNineTh(memcache.getNintyNineTh());
//		bean.setNintyth(memcache.getNintyTh());
//		float var, stand = memcache.getStd();
//		var = stand * stand;
//		bean.setVar(var);
		return bean;
	}
	public TimeResultBean calculateS(List<SiloDataBean> silo){
		TimeResultBean bean = new TimeResultBean();
		List<Float> newlist = new ArrayList();
		for(int i =0; i < silo.size(); i++){
			newlist.add(silo.get(i).getQueryTime());
		}
		Collections.sort(newlist);
		bean.setMax(getMax(newlist));
		bean.setMean(getMean(newlist));
		bean.setMin(getMin(newlist));
		bean.setNintyFiveTh(getNintyFiveTh(newlist));
		bean.setNintyNineTh(getNintyNinth(newlist));
		bean.setNintyNineTh(getNintyTh(newlist));
		bean.setCDF(getCDF(newlist));
		bean.setVar(getVar(newlist));
		//miss
		AppConfigBean app = new AppConfigBean();
		String countRequest = Repository.appConfigMap.get("silo").getRequestCount();
		int count = Integer.parseInt(countRequest) * 2;
		float miss  = 1- count/(float)newlist.size();
		bean.setMissRate(miss);
		return bean;
	}
	
	public List<TwoTuple<Float,Float>>  getCDF(List newlist){
		List<TwoTuple<Float,Float>> cdf = new ArrayList();
		float percent;
		//Integer counts = 1;
		int sumcount = 0;
		int size = newlist.size();
		for(int i=0; i < size-1; i++) {
			sumcount++;
			
			if((int)newlist.get(i) ==(int)newlist.get(i+1)) {
				//System.out.println(i + " is =" + newlist.get(i) +" "+newlist.get(i+1));
			}else {		
				//System.out.println(i + " is " + newlist.get(i) +" "+newlist.get(i+1));
				
				//float percent = (float)counts/ (float)size;
				percent = (float)sumcount/ (float)size;
				cdf.add(new TwoTuple<Float,Float>((float)(int)newlist.get(i),percent));
				//counts = 1;
				
			}
		}
		percent = 1.0f;
		cdf.add(new TwoTuple<Float,Float>((float)(int)newlist.get(size-1),percent));
		
		return cdf;
	}
	public float getMax(List newlist){
		//Collections.sort(newlist);
		float max = (float) newlist.get(newlist.size()-1);
		return max;
	}
	public float getMin(List  newlist){
		//Collections.sort(newlist);
		float min = (float) newlist.get(0);
		return min;
	}
	public float getMean(List  newlist){
		float temp,mean, sum=0.0f;
		for(int i = 0; i < newlist.size(); i++) {
			temp = (float) newlist.get(i);
			sum += temp;
		}
		mean = sum/newlist.size();
		return mean;
	}

	public float getVar(List newlist){
		float var =0.0f;
		int size = newlist.size();
		float mean = getMean(newlist);
		for(int i = 0; i <size; i++ ) {
			int temp1 = (int) newlist.get(i);
			float temp2 = temp1;
			var = var + (temp2 - mean)*(temp2- mean);
		}
		var = var / size;
		return var;
	}
	public float getNintyTh(List newlist){
		//Collections
		int size = newlist.size();
		float nintyth;
		int j = (int)((size) * 0.9);
		float xj = (float)(int)newlist.get(j);
		float xj_1 = (float)(int)newlist.get(j-1);
		nintyth = (float) ((size * 0.9 - j)*( xj - xj_1) + xj_1);
		return nintyth;
	}
	public float getNintyFiveTh(List  newlist){
		float nintyFiveTh = 0.0f;
		int size = newlist.size();
		int j = (int)((size) * 0.95);
		//float g = (float) ((size + 1 )*0.95 - j);
		float xj = (float)(int)newlist.get(j);
		float xj_1 = (float)(int)newlist.get(j-1);
		nintyFiveTh = (float) ((size * 0.95 - j)*( xj - xj_1) + xj_1);
		return nintyFiveTh;
	}
	public float getNintyNinth(List  newlist){
		float nintyNineTh = 0.0f;
		int size = newlist.size();
		int j = (int)((size) * 0.99);
		//float g = (float) ((size + 1 )*0.95 - j);
		float xj = (float)(int)newlist.get(j);
		float xj_1 = (float)(int)newlist.get(j-1);
		nintyNineTh = (float) ((size * 0.99 - j)*( xj - xj_1) + xj_1);
		return nintyNineTh;
	}
	}

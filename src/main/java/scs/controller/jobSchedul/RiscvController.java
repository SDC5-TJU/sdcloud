package scs.controller.jobSchedul;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.sf.json.JSONArray;
import scs.pojo.SystemResourceUsageBean; 
import scs.pojo.TimeResultBean;
import scs.pojo.TimeResultDiffBean;
import scs.pojo.TwoTuple;
import scs.pojo.XapianDataBean;
import scs.pojo.riscv.CDFBean;
import scs.pojo.riscv.DataProject2Bean;
import scs.pojo.riscv.RiscvLLCGroup;
import scs.pojo.riscv.RiscvLLCPOJO;
import scs.pojo.riscv.RiscvRedisRealDataBean;
import scs.service.appConfig.AppConfigService;
import scs.service.historyData.HistoryDataService;
import scs.service.jobSchedul.JobSchedulService;
import scs.service.monitor.riscv.ReadRiscvFiles;
import scs.service.monitor.riscv.impl.ReadRiscvFilesImpl; 
import scs.service.recordManage.RecordManageService;
import scs.util.format.DataFormats;
import scs.util.jobSchedul.jobImpl.webServer.WebServerJobImpl; 
import scs.util.repository.Repository;
import scs.util.tools.AdapterForResult;
import scs.util.tools.ReadRiscvServiceDataFile;
import scs.util.tools.ResultDiffAnalysis; 

/**
 * 应用执行控制类
 * 用于控制不同的应用开启和关闭
 * @author  
 *
 */
@Controller
public class RiscvController { 

	@Resource JobSchedulService service; 
	@Resource AppConfigService aService;
	@Resource RecordManageService rService;
	@Resource HistoryDataService hService;

	private DataFormats dataFormat=DataFormats.getInstance();
	private ReadRiscvFiles read=new ReadRiscvFilesImpl();

	@RequestMapping("/getRiscvXapianResult.do")
	public String getRiscvXapianResult(HttpServletRequest request,HttpServletResponse response,Model model){

		List<XapianDataBean> resultBaseList=new ArrayList<XapianDataBean>();
		List<XapianDataBean> resultList=new ArrayList<XapianDataBean>();
		List<XapianDataBean> resultLableList=new ArrayList<XapianDataBean>();

		try{
			Properties prop = new Properties();
			InputStream is = WebServerJobImpl.class.getResourceAsStream("/conf/sys.properties");
			try {
				prop.load(is);
			} catch (IOException e) { 
				e.printStackTrace();
			}
			String riscvDataPath=prop.getProperty("riscv_xapian_data_path").trim();
			File file=new File(riscvDataPath+"lats1.txt");
			if(file.exists()){
				resultBaseList=service.getRiscvXapianResult(riscvDataPath+"lats1.txt");
			}
			file=new File(riscvDataPath+"lats2.txt");
			if(file.exists()){
				resultList=service.getRiscvXapianResult(riscvDataPath+"lats2.txt");
			}
			file=new File(riscvDataPath+"lats3.txt");
			if(file.exists()){
				resultLableList=service.getRiscvXapianResult(riscvDataPath+"lats3.txt");
			}
			file=new File(riscvDataPath+"xapian.qps");
			if(file.exists()){
				String qps=ReadRiscvServiceDataFile.getInstance().readQpsFile(riscvDataPath+"xapian.qps");
				model.addAttribute("qps",qps);
			}
			/*
			 * 判断是否缺少数据 (基准和正式都要有才可以进行差异值计算)
			 */
			String appName="xapian";

			//计算基准数据和正式数据的各项指标
			Repository.EAThreshold=Float.MAX_VALUE;
			TimeResultBean resultBaseIndex= AdapterForResult.adapter(appName,resultBaseList); 
			Repository.EAThreshold=resultBaseIndex.getNintyNineTh();
			TimeResultBean resultIndex=AdapterForResult.adapter(appName,resultList);
			TimeResultBean resultLabelIndex=AdapterForResult.adapter(appName,resultLableList);
			model.addAttribute("BaseResult",resultBaseIndex);
			model.addAttribute("Result",resultIndex);
			model.addAttribute("LabelResult",resultLabelIndex);
			//计算各项指标差异值
			TimeResultDiffBean diffBean1=ResultDiffAnalysis.getInstance().getXapianResultDiff(resultBaseList,resultList,resultBaseIndex,resultIndex);
			model.addAttribute("diffBean1",diffBean1);
			TimeResultDiffBean diffBean2=ResultDiffAnalysis.getInstance().getXapianResultDiff(resultList,resultLableList,resultIndex,resultLabelIndex);
			model.addAttribute("diffBean2",diffBean2); 
			diffBean2.setCDFStr(diffBean2.getCDFStr().replace("干扰下","隔离后"));
			model.addAttribute("CDFStrs",diffBean1.getBaseCDFStr()+","+diffBean1.getCDFStr()+","+diffBean2.getCDFStr()); 
			model.addAttribute("appName",appName);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "demo_riscv_latency";
	}
	@RequestMapping("/getRiscvRedisResult.do")
	public String getRiscvRedisResult(HttpServletRequest request,HttpServletResponse response,Model model,
			@RequestParam(value="type",required=true) String type){
		List<TwoTuple<Long,Integer>> resultBaseList=new ArrayList<TwoTuple<Long,Integer>>();
		List<TwoTuple<Long,Integer>> resultList=new ArrayList<TwoTuple<Long,Integer>>();
		List<TwoTuple<Long,Integer>> resultLableList=new ArrayList<TwoTuple<Long,Integer>>();

		try{
			Properties prop = new Properties();
			InputStream is = WebServerJobImpl.class.getResourceAsStream("/conf/sys.properties");
			try {
				prop.load(is);
			} catch (IOException e) { 
				e.printStackTrace();
			}
			String riscvDataPath=prop.getProperty("riscv_redis_data_path").trim();
			File file=new File(riscvDataPath+type+"1.lat");
			if(file.exists()){
				resultBaseList=service.getRiscvRedisResult(riscvDataPath+type+"1.lat"); 
			}
			file=new File(riscvDataPath+type+"2.lat");
			if(file.exists()){
				resultList=service.getRiscvRedisResult(riscvDataPath+type+"2.lat"); 
			}
			file=new File(riscvDataPath+type+"3.lat");
			if(file.exists()){
				resultLableList=service.getRiscvRedisResult(riscvDataPath+type+"3.lat"); 
			}

			file=new File(riscvDataPath+type+".qps");
			if(file.exists()){
				String qps=ReadRiscvServiceDataFile.getInstance().readQpsFile(riscvDataPath+type+".qps");
				model.addAttribute("qps",qps);
			}
			/*
			 * 判断是否缺少数据 (基准和正式都要有才可以进行差异值计算)
			 */
			String appName="redis";

			//计算基准数据和正式数据的各项指标
			Repository.EAThreshold=Float.MAX_VALUE;
			TimeResultBean resultBaseIndex= AdapterForResult.adapter(appName,resultBaseList); 
			Repository.EAThreshold=resultBaseIndex.getNintyNineTh();
			TimeResultBean resultIndex=AdapterForResult.adapter(appName,resultList);
			TimeResultBean resultLabelIndex=AdapterForResult.adapter(appName,resultLableList);
			model.addAttribute("BaseResult",resultBaseIndex);
			model.addAttribute("Result",resultIndex);
			model.addAttribute("LabelResult",resultLabelIndex);
			//计算各项指标差异值 
			TimeResultDiffBean diffBean1=ResultDiffAnalysis.getInstance().getResultDiff(resultBaseList,resultList,resultBaseIndex,resultIndex);
			model.addAttribute("diffBean1",diffBean1);
			TimeResultDiffBean diffBean2=ResultDiffAnalysis.getInstance().getResultDiff(resultList,resultLableList,resultIndex,resultLabelIndex);
			model.addAttribute("diffBean2",diffBean2); 
			diffBean2.setCDFStr(diffBean2.getCDFStr().replace("干扰下","隔离后"));
			model.addAttribute("CDFStrs",diffBean1.getBaseCDFStr()+","+diffBean1.getCDFStr()+","+diffBean2.getCDFStr()); 
			model.addAttribute("appName",appName+type);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "demo_riscv_latency";
	}

	/**
	 * 进入Riscv资源监控页面
	 * @param request
	 * @param response
	 * @param model
	 * @param riscvId 板子id 0-7
	 * @param level 显示数据等级 1 2 3 分别对应 cpu mem llc和内存带宽  后者包括前者
	 * @return
	 */
	@RequestMapping("/goRiscvUsage.do")
	public String goRiscvUsage(HttpServletRequest request,HttpServletResponse response,Model model,
			@RequestParam(value="riscvId",required=true) int riscvId,
			@RequestParam(value="level",required=false) String level){
		level=level==null?"3":level;
		int Level=Integer.parseInt(level);
		Properties prop = new Properties();
		InputStream is = WebServerJobImpl.class.getResourceAsStream("/conf/sys.properties");
		try {
			prop.load(is);
		} catch (IOException e) { 
			e.printStackTrace();
		} 
		Long curTime=System.currentTimeMillis();
		List<Double> dataList=new ArrayList<Double>();
		StringBuffer strName=new StringBuffer();
		StringBuffer strData=new StringBuffer();
		StringBuffer HSeries=new StringBuffer();
		/**
		 * cpu
		 */
		if(Level>=1){
			while(dataList.size()<Repository.windowSize){//小于60个点 需要睡眠等待
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dataList=read.readRiscvWindowResourceUsageFile(prop.getProperty("riscv_monitor_path").trim()+"cpu_usage_"+riscvId+".csv");
			}
			strName.setLength(0);
			strData.setLength(0);
			HSeries.setLength(0);
			strName.append("{name:'cpuUsage',type:'area',"); 
			strData.append("data:[");		
			for(int i=Repository.windowSize-1;i>0;i--){
				SystemResourceUsageBean bean=new SystemResourceUsageBean();
				bean.setCollectTime(curTime-i*1000);
				bean.setCpuUsageRate((float)(dataList.get(Repository.windowSize-1-i)*100));
				//Repository.getInstance().addRiscvWindowCpuUsageDataList(bean); 
				strData.append("[").append(curTime-i*1000).append(",").append(dataList.get(Repository.windowSize-1-i)*100).append("],");
			}
			strData.append("[").append(curTime).append(",").append(dataList.get(Repository.windowSize-1)*100).append("]]");
			HSeries.append(strName).append(strData).append(",marker: {enabled: false}}");
			model.addAttribute("cpuStr",HSeries.toString()); 
		}else{
			model.addAttribute("cpuStr","{name:'cpuUsage',type:'area',data:[]}"); 
		}
		
		if(Level>=2){
			/**
			 * mem
			 */
			dataList.clear();
			while(dataList.size()<Repository.windowSize){//小于60个点 需要睡眠等待
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dataList=read.readRiscvWindowResourceUsageFile(prop.getProperty("riscv_monitor_path").trim()+"mem_"+riscvId+".csv");
			}
			strName.setLength(0);
			strData.setLength(0);
			HSeries.setLength(0);
			strName.append("{name:'memUsage',type:'area',"); 
			strData.append("data:[");		
			for(int i=Repository.windowSize-1;i>0;i--){
				strData.append("[").append(curTime-i*1000).append(",").append(dataList.get(Repository.windowSize-1-i)*100).append("],");
			}
			strData.append("[").append(curTime).append(",").append(dataList.get(Repository.windowSize-1)*100).append("]]");
			HSeries.append(strName).append(strData).append(",marker: {enabled: false}}");
			model.addAttribute("memStr",HSeries.toString()); 
		}else{
			model.addAttribute("memStr","{name:'memUsage',type:'area',data:[]}"); 
		}
		if(Level>=3){
			/**
			 * llc and mem bandwidth
			 */ 
			ArrayList<RiscvLLCGroup> dataList2=new ArrayList<RiscvLLCGroup>();
			while(dataList2.size()<Repository.windowSize){//小于60个点 需要睡眠等待
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dataList2=read.readLLC(prop.getProperty("riscv_monitor_path").trim()+"llc_mb_"+riscvId+".csv",true);
			}

			Map<Integer,RiscvLLCPOJO> map=new HashMap<Integer,RiscvLLCPOJO>();
			List<RiscvLLCPOJO> AvgList=new ArrayList<RiscvLLCPOJO>(); 
			double missRate=0;
			double bandwidth=0;
			for(int i=0;i<dataList.size();i++){
				map=dataList2.get(i).getMap();
				Set<Integer> keySet=map.keySet();
				missRate=0;
				bandwidth=0;
				for(Integer j:keySet){
					missRate+=map.get(j).getMissesPercent();
					bandwidth+=map.get(j).getBandwidth();
				}
				RiscvLLCPOJO bean=new RiscvLLCPOJO();
				bean.setBandwidth(bandwidth/map.size());
				bean.setLlcMisses(missRate/map.size()); 
				AvgList.add(bean);
			}
			curTime=System.currentTimeMillis();
			//llc
			strName.setLength(0);
			strData.setLength(0);
			HSeries.setLength(0);
			strName.append("{name:'llcMissRate',type:'area',"); 
			strData.append("data:[");		
			for(int i=Repository.windowSize-1;i>0;i--){
				strData.append("[").append(curTime-i*1000).append(",").append(AvgList.get(Repository.windowSize-1-i).getLlcMisses()).append("],");
			}
			strData.append("[").append(curTime).append(",").append(AvgList.get(Repository.windowSize-1).getLlcMisses()).append("]]");
			HSeries.append(strName).append(strData).append(",marker: {enabled: false}}");
			model.addAttribute("llcStr",HSeries.toString()); 
			//内存带宽
			strName.setLength(0);
			strData.setLength(0);
			HSeries.setLength(0);
			strName.append("{name:'memBandWidth',type:'area',"); 
			strData.append("data:[");		
			for(int i=Repository.windowSize-1;i>0;i--){
				strData.append("[").append(curTime-i*1000).append(",").append(AvgList.get(Repository.windowSize-1-i).getBandwidth()).append("],");
			}
			strData.append("[").append(curTime).append(",").append(AvgList.get(Repository.windowSize-1).getBandwidth()).append("]]");
			HSeries.append(strName).append(strData).append(",marker: {enabled: false}}");
			model.addAttribute("memBDStr",HSeries.toString()); 
		}else{
			model.addAttribute("llcStr","{name:'llcMissRate',type:'area',data:[]}"); 
			model.addAttribute("memBDStr","{name:'memBandWidth',type:'area',data:[]}"); 
		}
		model.addAttribute("riscvId",riscvId);

		return "demo_riscv_monitor";
	}
	/**
	 * 查询最新的mem使用数据
	 * @param request
	 * @param response
	 * @param riscvId 板子id
	 */
	@RequestMapping("/getRiscvMemUsage.do")
	public void getRiscvMemUsage(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="riscvId",required=true) int riscvId){
		try {
			Properties prop = new Properties();
			InputStream is = WebServerJobImpl.class.getResourceAsStream("/conf/sys.properties");
			try {
				prop.load(is);
			} catch (IOException e) { 
				e.printStackTrace();
			} 
			SystemResourceUsageBean bean=new SystemResourceUsageBean();
			bean.setCollectTime(System.currentTimeMillis());
			bean.setMemUsageRate((float)read.readRiscvLatestResourceUsageFile(prop.getProperty("riscv_monitor_path").trim()+"mem_"+riscvId+".csv")*100);

			response.getWriter().write(JSONArray.fromObject(bean).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 查询最新的cpu使用数据
	 * @param request
	 * @param response
	 * @param riscvId 板子id
	 */
	@RequestMapping("/getRiscvCpuUsage.do")
	public void getRiscvCpuUsage(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="riscvId",required=true) int riscvId){
		try {
			Properties prop = new Properties();
			InputStream is = WebServerJobImpl.class.getResourceAsStream("/conf/sys.properties");
			try {
				prop.load(is);
			} catch (IOException e) { 
				e.printStackTrace();
			} 
			SystemResourceUsageBean bean=new SystemResourceUsageBean();
			bean.setCollectTime(System.currentTimeMillis());
			bean.setCpuUsageRate((float)read.readRiscvLatestResourceUsageFile(prop.getProperty("riscv_monitor_path").trim()+"cpu_usage_"+riscvId+".csv")*100);

			//Repository.getInstance().addRiscvWindowCpuUsageDataList(bean);

			response.getWriter().write(JSONArray.fromObject(bean).toString().replace("}",",\"cpuAvgUsageRate\":"+dataFormat.subFloat(Repository.getInstance().getRiscvAvgCpuUsage(),2)+"}"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 查询最新的llc和内存带宽使用数据
	 * @param request
	 * @param response
	 * @param riscvId 板子id
	 */
	@RequestMapping("/getRiscvLLCdRAM.do")
	public void getRiscvLLCdRAM(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="riscvId",required=true) int riscvId){
		try {
			Properties prop = new Properties();
			InputStream is = WebServerJobImpl.class.getResourceAsStream("/conf/sys.properties");
			try {
				prop.load(is);
			} catch (IOException e) { 
				e.printStackTrace();
			} 
			ArrayList<RiscvLLCGroup> list=read.readLLC(prop.getProperty("riscv_monitor_path").trim()+"llc_mb_"+riscvId+".csv",false);
			Map<Integer, RiscvLLCPOJO> map=list.get(0).getMap();
			Set<Integer> keySet=map.keySet();
			double missRate=0;
			double bandwidth=0;
			for(Integer i:keySet){
				missRate+=map.get(i).getMissesPercent();
				bandwidth+=map.get(i).getBandwidth();
			}
			RiscvLLCPOJO bean=new RiscvLLCPOJO();
			bean.setBandwidth(bandwidth/map.size());
			bean.setLlcMisses(missRate/map.size()); 
			bean.setCollectTime(System.currentTimeMillis());

			response.getWriter().write(JSONArray.fromObject(bean).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 进入riscv redis的实时查询页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/goRiscvRedisRealQueryData.do")
	public String goRiscvRedisRealQueryData(HttpServletRequest request,HttpServletResponse response,Model model,
			@RequestParam(value="type",required=true) String type){

		Properties prop = new Properties();
		InputStream is = WebServerJobImpl.class.getResourceAsStream("/conf/sys.properties");
		try {
			prop.load(is);
		} catch (IOException e) { 
			e.printStackTrace();
		} 
		Long curTime=System.currentTimeMillis();
		List<RiscvRedisRealDataBean> dataList=new ArrayList<RiscvRedisRealDataBean>();
		while(dataList.size()<Repository.windowSize){//小于60个点 需要睡眠等待
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) { 
				e.printStackTrace();
			}
			dataList=read.readRiscvWindowQueryTimeFile(prop.getProperty("riscv_redis_real_data_path").trim()+"redis_cluster_latency_"+type+".csv");
		}
		StringBuffer strName=new StringBuffer();
		StringBuffer strData=new StringBuffer();
		StringBuffer HSeries=new StringBuffer();
		//绘制min曲线
		strName.append("{name:'min',"); 
		strData.append("data:[");		
		for(int i=Repository.windowSize-1;i>0;i--){
			strData.append("[").append(curTime-i*1000).append(",").append(dataList.get(Repository.windowSize-1-i).getMin()).append("],");
		}
		strData.append("[").append(curTime).append(",").append(dataList.get(Repository.windowSize-1).getMin()).append("]]");
		HSeries.append(strName).append(strData).append(",marker: {enabled: false}},");

		//绘制均值曲线
		strName.setLength(0);strData.setLength(0);
		strName.append("{name:'mean',"); 
		strData.append("data:[");		
		for(int i=Repository.windowSize-1;i>0;i--){
			strData.append("[").append(curTime-i*1000).append(",").append(dataList.get(Repository.windowSize-1-i).getMean()).append("],");
		}
		strData.append("[").append(curTime).append(",").append(dataList.get(Repository.windowSize-1).getMean()).append("]]");
		HSeries.append(strName).append(strData).append(",marker: {enabled: false}},");
		//50th
		strName.setLength(0);strData.setLength(0);
		strName.append("{name:'50th',");
		strData.append("data:[");
		for(int i=Repository.windowSize-1;i>0;i--){
			strData.append("[").append(curTime-i*1000).append(",").append(dataList.get(Repository.windowSize-1-i).getFiftyTh()).append("],");
		}
		strData.append("[").append(curTime).append(",").append(dataList.get(Repository.windowSize-1).getFiftyTh()).append("]]");
		HSeries.append(strName).append(strData).append(",marker: {enabled: false}},");
		//80th
		strName.setLength(0);strData.setLength(0);
		strName.append("{name:'80th',");
		strData.append("data:[");
		for(int i=Repository.windowSize-1;i>0;i--){
			strData.append("[").append(curTime-i*1000).append(",").append(dataList.get(Repository.windowSize-1-i).getEightyTh()).append("],");
		}
		strData.append("[").append(curTime).append(",").append(dataList.get(Repository.windowSize-1).getEightyTh()).append("]]");
		HSeries.append(strName).append(strData).append(",marker: {enabled: false}},");
		//90th
		strName.setLength(0);strData.setLength(0);
		strName.append("{name:'90th',");
		strData.append("data:[");
		for(int i=Repository.windowSize-1;i>0;i--){
			strData.append("[").append(curTime-i*1000).append(",").append(dataList.get(Repository.windowSize-1-i).getNintyTh()).append("],");
		}
		strData.append("[").append(curTime).append(",").append(dataList.get(Repository.windowSize-1).getNintyTh()).append("]]");
		HSeries.append(strName).append(strData).append(",marker: {enabled: false}},");

		//绘制99th曲线
		strName.setLength(0);
		strData.setLength(0);  
		strName.append("{name:'99th',"); 
		strData.append("data:[");		
		for(int i=Repository.windowSize-1;i>0;i--){
			strData.append("[").append(curTime-i*1000).append(",").append(dataList.get(Repository.windowSize-1-i).getNintyNineTh()).append("],");
		}
		strData.append("[").append(curTime).append(",").append(dataList.get(Repository.windowSize-1).getNintyNineTh()).append("]]");
		HSeries.append(strName).append(strData).append(",marker: {enabled: false}},");

		//99.9th
		strName.setLength(0);strData.setLength(0);
		strName.append("{name:'99.9th',");
		strData.append("data:[");
		for(int i=Repository.windowSize-1;i>0;i--){
			strData.append("[").append(curTime-i*1000).append(",").append(dataList.get(Repository.windowSize-1-i).getNintyNinePointNineTh()).append("],");
		}
		strData.append("[").append(curTime).append(",").append(dataList.get(Repository.windowSize-1).getNintyNinePointNineTh()).append("]]");
		HSeries.append(strName).append(strData).append(",marker: {enabled: false}},");

		//max
		strName.setLength(0);strData.setLength(0);
		strName.append("{name:'max',");
		strData.append("data:[");
		for(int i=Repository.windowSize-1;i>0;i--){
			strData.append("[").append(curTime-i*1000).append(",").append(dataList.get(Repository.windowSize-1-i).getMax()).append("],");
		}
		strData.append("[").append(curTime).append(",").append(dataList.get(Repository.windowSize-1).getMax()).append("]]");
		HSeries.append(strName).append(strData).append(",marker: {enabled: false}}");

		model.addAttribute("str",HSeries.toString()); 
		model.addAttribute("type",type);
		return "demo_riscv_real_latency_redis";
	}
	/**
	 * 查询最新的redis的响应时间数据
	 * @param request
	 * @param response 
	 */
	@RequestMapping("/getRiscvRedisRealQueryData.do")
	public void getRiscvRedisRealQueryData(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="type",required=true) String type){
		try {
			Properties prop = new Properties();
			InputStream is = WebServerJobImpl.class.getResourceAsStream("/conf/sys.properties");
			try {
				prop.load(is);
			} catch (IOException e) {
				e.printStackTrace();
			}
			RiscvRedisRealDataBean bean= read.readRiscvLatestQueryTimeFile(prop.getProperty("riscv_redis_real_data_path").trim()+"redis_cluster_latency_"+type+".csv");
			bean.setCollectTime(System.currentTimeMillis());

			response.getWriter().write(JSONArray.fromObject(bean).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private Random rand=new Random();

	/**
	 * 课题3
	 * 进入监控页面绘制CDF
	 */
	@RequestMapping(value="/goMonitor3.do")
	public String goMonitor3(HttpServletRequest request,HttpServletResponse response,Model model) {
		Properties prop = new Properties();
		InputStream is = WebServerJobImpl.class.getResourceAsStream("/conf/sys.properties");
		try {
			prop.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String filePath=prop.getProperty("riscv_project3_path").trim();
		int size=Integer.parseInt(prop.getProperty("riscv_project3_series_size").trim());
		StringBuffer strName=new StringBuffer();
		StringBuffer strData=new StringBuffer();
		StringBuffer HSeries=new StringBuffer();
		strName.append("{name:'高优先级@RISC-V Linux',color:Highcharts.getOptions().colors[2],");
		CDFBean df=read.readRiscvCDFfile(filePath+"cdf.txt", filePath+"cdf_x86.txt", filePath+"conn_h.txt", filePath+"conn_h_x86.txt");

		strData.append("data:[");
		 
		for(int i=1;i<size-1;i++){ //拼接字符串 
			strData.append("[").append(String.valueOf(df.getHigh_x86()[i])).append(",").append(String.valueOf(df.getCdf()[i])).append("],");
		}
		strData.append("[").append(String.valueOf(df.getHigh_x86()[size-1])).append(",").append(String.valueOf(df.getCdf()[size-1])).append("]]");
		HSeries.append(strName).append(strData).append("},"); 

		strName.setLength(0);
		strData.setLength(0); 
		strName.append("{name:'高优先级@RISC-V-LNS',color:Highcharts.getOptions().colors[3],");
		strData.append("data:[");
		for(int i=1;i<size-1;i++){
			strData.append("[").append(String.valueOf(df.getHigh()[i])).append(",").append(String.valueOf(df.getCdf()[i])).append("],");
		}
		strData.append("[").append(String.valueOf(df.getHigh()[size-1])).append(",").append(String.valueOf(df.getCdf()[size-1])).append("]]");

		HSeries.append(strName).append(strData).append("}");

		model.addAttribute("cdf_high",HSeries.toString());//封装字符串发送到前端页面

		strName.setLength(0);
		strData.setLength(0); 
		HSeries.setLength(0);
		strName.append("{name:'低优先级@RISC-V Linux',color:Highcharts.getOptions().colors[2],");
		strData.append("data:[");
		for(int i=1;i<size-1;i++){ //拼接字符串 
			strData.append("[").append(String.valueOf(df.getLow_x86()[i])).append(",").append(String.valueOf(df.getCdf()[i])).append("],");
		}
		strData.append("[").append(String.valueOf(df.getLow_x86()[size-1])).append(",").append(String.valueOf(df.getCdf()[size-1])).append("]]");
		HSeries.append(strName).append(strData).append("},"); 

		strName.setLength(0);
		strData.setLength(0); 
		strName.append("{name:'低优先级@RISC-V-LNS',color:Highcharts.getOptions().colors[3],");
		strData.append("data:[");
		for(int i=1;i<size-1;i++){
			strData.append("[").append(String.valueOf(df.getLow()[i])).append(",").append(String.valueOf(df.getCdf()[i])).append("],");
		}
		strData.append("[").append(String.valueOf(df.getLow()[size-1])).append(",").append(String.valueOf(df.getCdf()[size-1])).append("]]");

		HSeries.append(strName).append(strData).append("}");

		model.addAttribute("cdf_low",HSeries.toString());//封装字符串发送到前端页面
		return "monitor_3";
	}
	/**
	 * 课题3
	 * 进入监控页面绘制CDF
	 */
	@RequestMapping(value="/getMonitor3.do")
	public void getMonitor3(HttpServletRequest request,HttpServletResponse response,Model model) {
		Properties prop = new Properties();
		InputStream is = WebServerJobImpl.class.getResourceAsStream("/conf/sys.properties");
		try {
			prop.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String filePath=prop.getProperty("riscv_project3_path").trim();
		int size=Integer.parseInt(prop.getProperty("riscv_project3_series_size").trim());
		StringBuffer strName=new StringBuffer();
		StringBuffer strData=new StringBuffer();
		StringBuffer HSeries=new StringBuffer();
		strName.append("{name:'高优先级@RISC-V Linux',color:Highcharts.getOptions().colors[2],");
		CDFBean df=read.readRiscvCDFfile(filePath+"cdf.txt", filePath+"cdf_x86.txt", filePath+"conn_h.txt", filePath+"conn_h_x86.txt");

		strData.append("data:[");
	 
		for(int i=1;i<size-1;i++){ //拼接字符串 
			strData.append("[").append(String.valueOf(df.getHigh_x86()[i])).append(",").append(String.valueOf(df.getCdf()[i])).append("],");
		}
		strData.append("[").append(String.valueOf(df.getHigh_x86()[size-1])).append(",").append(String.valueOf(df.getCdf()[size-1])).append("]]");
		HSeries.append(strName).append(strData).append("}#"); 

		strName.setLength(0);
		strData.setLength(0); 
		strName.append("{name:'高优先级@RISC-V-LNS',color:Highcharts.getOptions().colors[3],");
		strData.append("data:[");
		for(int i=1;i<size-1;i++){
			strData.append("[").append(String.valueOf(df.getHigh()[i])).append(",").append(String.valueOf(df.getCdf()[i])).append("],");
		}
		strData.append("[").append(String.valueOf(df.getHigh()[size-1])).append(",").append(String.valueOf(df.getCdf()[size-1])).append("]]");
		HSeries.append(strName).append(strData).append("}#"); 

		strName.setLength(0);
		strData.setLength(0); 
		strName.append("{name:'低优先级@RISC-V Linux',color:Highcharts.getOptions().colors[2],");
		strData.append("data:[");
		for(int i=1;i<size-1;i++){ //拼接字符串 
			strData.append("[").append(String.valueOf(df.getLow_x86()[i])).append(",").append(String.valueOf(df.getCdf()[i])).append("],");
		}
		strData.append("[").append(String.valueOf(df.getLow_x86()[size-1])).append(",").append(String.valueOf(df.getCdf()[size-1])).append("]]");
		HSeries.append(strName).append(strData).append("}#"); 

		strName.setLength(0);
		strData.setLength(0); 
		strName.append("{name:'低优先级@RISC-V-LNS',color:Highcharts.getOptions().colors[3],");
		strData.append("data:[");
		for(int i=1;i<size-1;i++){
			strData.append("[").append(String.valueOf(df.getLow()[i])).append(",").append(String.valueOf(df.getCdf()[i])).append("],");
		}
		strData.append("[").append(String.valueOf(df.getLow()[size-1])).append(",").append(String.valueOf(df.getCdf()[size-1])).append("]]");

		HSeries.append(strName).append(strData).append("}");

		try {
			response.getWriter().write(HSeries.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	/**
	 * 进入课题2的展示页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/goMonitor2.do")
	public String goMonitor2(HttpServletRequest request,HttpServletResponse response,Model model){

		Properties prop = new Properties();
		InputStream is = WebServerJobImpl.class.getResourceAsStream("/conf/sys.properties");
		try {
			prop.load(is);
		} catch (IOException e) { 
			e.printStackTrace();
		} 
		Long curTime=System.currentTimeMillis();
		List<DataProject2Bean> dataList=new ArrayList<DataProject2Bean>();
		StringBuffer strName=new StringBuffer();
		StringBuffer strData=new StringBuffer();
		StringBuffer HSeries=new StringBuffer();
		while(dataList.size()<Repository.windowSize){//小于60个点 需要睡眠等待
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) { 
				e.printStackTrace();
			}
			dataList=read.readRiscvWindowDataFile(prop.getProperty("riscv_project2_file").trim());
		}
		//绘制均值曲线
		strName.append("{name:'Ceph',"); 
		strData.append("data:[");		
		for(int i=Repository.windowSize-1;i>0;i--){
			strData.append("[").append(curTime-i*1000).append(",").append(dataList.get(Repository.windowSize-1-i).getCeph()).append("],");
		}
		strData.append("[").append(curTime).append(",").append(dataList.get(Repository.windowSize-1).getCeph()).append("]]");
		HSeries.append(strName).append(strData).append(",marker: {enabled: false}},");

		//绘制99th曲线
		strName.setLength(0);
		strData.setLength(0);  
		strName.append("{name:'Gecko',"); 
		strData.append("data:[");		
		for(int i=Repository.windowSize-1;i>0;i--){
			strData.append("[").append(curTime-i*1000).append(",").append(dataList.get(Repository.windowSize-1-i).getGecko()).append("],");
		}
		strData.append("[").append(curTime).append(",").append(dataList.get(Repository.windowSize-1).getGecko()).append("]]");
		HSeries.append(strName).append(strData).append(",marker: {enabled: false}}");
		model.addAttribute("cephGeckoStr",HSeries.toString()); 

		return "monitor_2";
	}
	/**
	 * 查询最新课题2的数据
	 * @param request
	 * @param response 
	 */
	@RequestMapping("/getMonitor2.do")
	public void getMonitor2(HttpServletRequest request,HttpServletResponse response){
		try {
			Properties prop = new Properties();
			InputStream is = WebServerJobImpl.class.getResourceAsStream("/conf/sys.properties");
			try {
				prop.load(is);
			} catch (IOException e) {
				e.printStackTrace();
			}
			DataProject2Bean bean= read.readRiscvLatestDataFile(prop.getProperty("riscv_project2_file").trim());
			bean.setCollectTime(System.currentTimeMillis());

			response.getWriter().write(JSONArray.fromObject(bean).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}

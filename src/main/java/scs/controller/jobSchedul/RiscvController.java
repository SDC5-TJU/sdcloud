package scs.controller.jobSchedul;
 
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties; 
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.sf.json.JSONArray;  
import scs.pojo.RiscvLLCGroup;
import scs.pojo.RiscvLLCPOJO; 
import scs.pojo.SystemResourceUsageBean; 
import scs.pojo.TimeResultBean;
import scs.pojo.TimeResultDiffBean;
import scs.pojo.TwoTuple;
import scs.pojo.XapianDataBean;
import scs.service.appConfig.AppConfigService;
import scs.service.historyData.HistoryDataService;
import scs.service.jobSchedul.JobSchedulService;
import scs.service.monitor.riscv.ReadRiscvFiles;
import scs.service.monitor.riscv.impl.ReadRiscvFilesImpl; 
import scs.service.recordManage.RecordManageService;
import scs.util.jobSchedul.jobImpl.webServer.WebServerJobImpl; 
import scs.util.repository.Repository;
import scs.util.tools.AdapterForResult;
import scs.util.tools.ReadFile;
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
			file=new File(riscvDataPath+".qps");
			if(file.exists()){
				String qps=ReadFile.getInstance().readRedisQpsFile(riscvDataPath+".qps");
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
			File file=new File(riscvDataPath+type+"1.lats");
			if(file.exists()){
				resultBaseList=service.getRiscvRedisResult(riscvDataPath+type+"1.lats"); 
			}
			file=new File(riscvDataPath+type+"2.lats");
			if(file.exists()){
				resultList=service.getRiscvRedisResult(riscvDataPath+type+"2.lats"); 
			}
			file=new File(riscvDataPath+type+"3.lats");
			if(file.exists()){
				resultLableList=service.getRiscvRedisResult(riscvDataPath+type+"3.lats"); 
			}

			file=new File(riscvDataPath+type+".qps");
			if(file.exists()){
				String qps=ReadFile.getInstance().readRedisQpsFile(riscvDataPath+type+".qps");
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
	private ReadRiscvFiles read=new ReadRiscvFilesImpl();
	@RequestMapping("/goRiscvUsage.do")
	public String goRiscvUsage(HttpServletRequest request,HttpServletResponse response,Model model,
			@RequestParam(value="riscvId",required=true) int riscvId){

		Properties prop = new Properties();
		InputStream is = WebServerJobImpl.class.getResourceAsStream("/conf/sys.properties");
		try {
			prop.load(is);
		} catch (IOException e) { 
			e.printStackTrace();
		} 
		/**
		 * mem
		 */
		Long curTime=System.currentTimeMillis();
		List<Double> dataList=new ArrayList<Double>();
		while(dataList.size()<60){//小于60个点 需要睡眠等待
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dataList=read.read60(prop.getProperty("riscv_monitor_path").trim()+"mem_"+riscvId+".csv");
		}

		StringBuffer strName=new StringBuffer();
		StringBuffer strData=new StringBuffer();
		StringBuffer HSeries=new StringBuffer();
		strName.append("{name:'memUsage',type:'area',"); 
		strData.append("data:[");		
		for(int i=59;i>0;i--){
			strData.append("[").append(curTime-i*1000).append(",").append(dataList.get(59-i)*100).append("],");
		}
		strData.append("[").append(curTime).append(",").append(dataList.get(59)*100).append("]]");
		HSeries.append(strName).append(strData).append(",marker: {enabled: false}}");
		model.addAttribute("memStr",HSeries.toString()); 

		/**
		 * cpu
		 */
		curTime=System.currentTimeMillis();
		dataList=new ArrayList<Double>();
		while(dataList.size()<60){//小于60个点 需要睡眠等待
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dataList=read.read60(prop.getProperty("riscv_monitor_path").trim()+"cpu_usage_"+riscvId+".csv");
		}
		strName.setLength(0);
		strData.setLength(0);
		HSeries.setLength(0);
		strName.append("{name:'cpuUsage',type:'area',"); 
		strData.append("data:[");		
		for(int i=59;i>0;i--){
			strData.append("[").append(curTime-i*1000).append(",").append(dataList.get(59-i)*100).append("],");
		}
		strData.append("[").append(curTime).append(",").append(dataList.get(59)*100).append("]]");
		HSeries.append(strName).append(strData).append(",marker: {enabled: false}}");
		model.addAttribute("cpuStr",HSeries.toString()); 
		/**
		 * llc and mem bandwidth
		 */
		ArrayList<RiscvLLCGroup> dataList2=new ArrayList<RiscvLLCGroup>();
		while(dataList2.size()<60){//小于60个点 需要睡眠等待
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
		strName.setLength(0);
		strData.setLength(0);
		HSeries.setLength(0);
		strName.append("{name:'llcMissRate',type:'area',"); 
		strData.append("data:[");		
		for(int i=59;i>0;i--){
			strData.append("[").append(curTime-i*1000).append(",").append(AvgList.get(59-i).getLlcMisses()).append("],");
		}
		strData.append("[").append(curTime).append(",").append(AvgList.get(59).getLlcMisses()).append("]]");
		HSeries.append(strName).append(strData).append(",marker: {enabled: false}}");
		model.addAttribute("llcStr",HSeries.toString()); 
		//内存带宽
		strName.setLength(0);
		strData.setLength(0);
		HSeries.setLength(0);
		strName.append("{name:'memBandWidth',type:'area',"); 
		strData.append("data:[");		
		for(int i=59;i>0;i--){
			strData.append("[").append(curTime-i*1000).append(",").append(AvgList.get(59-i).getBandwidth()).append("],");
		}
		strData.append("[").append(curTime).append(",").append(AvgList.get(59).getBandwidth()).append("]]");
		HSeries.append(strName).append(strData).append(",marker: {enabled: false}}");
		model.addAttribute("memBDStr",HSeries.toString()); 

		model.addAttribute("riscvId",riscvId);
		return "demo_riscv_monitor";
	}
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
			bean.setMemUsageRate((float)read.readRiscvMemory(prop.getProperty("riscv_monitor_path").trim()+"mem_"+riscvId+".csv")*100);

			response.getWriter().write(JSONArray.fromObject(bean).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
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
			bean.setCpuUsageRate((float)read.readRiscvMemory(prop.getProperty("riscv_monitor_path").trim()+"cpu_usage_"+riscvId+".csv")*100);

			response.getWriter().write(JSONArray.fromObject(bean).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
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


}
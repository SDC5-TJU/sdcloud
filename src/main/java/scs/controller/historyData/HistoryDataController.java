package scs.controller.historyData;

import org.apache.log4j.Logger; 
import java.util.ArrayList;
import java.util.List;
import java.util.Set; 
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest; 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam; 
import scs.service.historyData.HistoryDataService;
import scs.service.recordManage.RecordManageService;
import scs.util.repository.Repository; 

/**
 * 历史查询控制类
 * @author yanan
 *
 */
@Controller
public class HistoryDataController {
	private static Logger logger = Logger.getLogger(HistoryDataController.class.getName());

	@Resource HistoryDataService service;
	@Resource RecordManageService rService;
	/**
	 * 查询物理机历史数据 cpu mem io net使用率
	 * @param request
	 * @param model
	 * @param startTime 开始时间
	 * @param endTime 截止时间
	 * @return highcharts series字符串
	 */
	@RequestMapping("/searchSysHistoryData.do")
	public String searchSysHistoryData(HttpServletRequest request,Model model, 
			@RequestParam(value="startTime",required=false) String startTime,
			@RequestParam(value="endTime",required=false) String endTime){
		try{ 
			List<String> chartStrList=new ArrayList<String>();
			/*
			 * 绘制物理机的cpu 内存 io net使用率图
			 */
			Set<String> hostNameSet=Repository.systemInfoMap.keySet(); 
			for(String hostName:hostNameSet){
				chartStrList.addAll(service.searchSysResourceUsage(hostName,startTime,endTime));
			}
			if(chartStrList.size()==0){
				for(int i=0;i<4;i++){ //cpu mem io net 
					model.addAttribute("sysUsageStr"+i,"");
				}
			}else{
				for(int i=0;i<4;i++){ //cpu mem io net 
					model.addAttribute("sysUsageStr"+i,chartStrList.get(i)+","+chartStrList.get(i+4));
				}
			}

		}catch(Exception e){
			logger.error("add Operator error"+e);
			e.printStackTrace();
		}
		return "historyData";
	}
	/**
	 * 查询容器历史数据 cpu mem使用率  io net使用量
	 * @param request
	 * @param model
	 * @param startTime 开始时间
	 * @param endTime 截止时间
	 * @return highcharts series字符串
	 */
	@RequestMapping("/searchContainerHistoryData.do")
	public String searchContainerHistoryData(HttpServletRequest request,Model model, 
			@RequestParam(value="startTime",required=false) String startTime,
			@RequestParam(value="endTime",required=false) String endTime){
		try{ 
			List<String> chartStrList=new ArrayList<String>();
			Set<String> hostNameSet=Repository.systemInfoMap.keySet(); 
			for(String hostName:hostNameSet){
				chartStrList.addAll(service.searchContainerResourceUsage(hostName,startTime,endTime));
			}
			for(int i=0;i<4;i++){ //cpu mem io net 
				model.addAttribute("containerUsageStr"+i,chartStrList.get(i)+","+chartStrList.get(i+4));
			}
			
		}catch(Exception e){
			logger.error("add Operator error"+e);
			e.printStackTrace();
		}
		return "historyData";
	}
	/**
	 * 查询应用的历史数据 cpu mem使用率  io net使用量
	 * @param request
	 * @param model
	 * @param startTime 开始时间
	 * @param endTime 截止时间
	 * @return highcharts series字符串
	 */
	@RequestMapping("/searchAppHistoryData.do")
	public String searchAppHistoryData(HttpServletRequest request,Model model,
			@RequestParam(value="startTime",required=false) String startTime,
			@RequestParam(value="endTime",required=false) String endTime){
		try{ 
			List<String> chartStrList=new ArrayList<String>();
			Set<String> appNameSet=Repository.appInfoMap.keySet(); 
			int count=0;
			boolean needTime=false;
			for(String appName:appNameSet){
				count++;
				needTime=count==appNameSet.size()?true:false;
				chartStrList.addAll(service.searchAppResourceUsage(appName,startTime,endTime,needTime));
			}
			for(int i=0;i<2;i++){ //cpu mem 
				model.addAttribute("appUsageStr"+i,chartStrList.get(i)+","+chartStrList.get(i+2)+","+chartStrList.get(i+4)+","+chartStrList.get(i+6)+","+chartStrList.get(i+8)+","+chartStrList.get(i+10)+","+chartStrList.get(i+12)+","+chartStrList.get(i+14));
			} 
			model.addAttribute("appUsageStr"+2,chartStrList.get(16));
		 
		}catch(Exception e){
			logger.error("add Operator error"+e);
			e.printStackTrace();
		}
		return "historyData";
	}
}

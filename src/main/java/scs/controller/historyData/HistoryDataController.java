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

	@RequestMapping("/test.do")
	public String test(HttpServletRequest request){

		return "test";
	}
	@RequestMapping("/searchSysHistoryDataBefore.do")
	public String searchSysHistoryData(HttpServletRequest request){

		return "sysHistoryData";
	}
	@RequestMapping("/searchAppHistoryDataBefore.do")
	public String searchAppHistoryData(HttpServletRequest request){

		return "appHistoryData";
	}
	@RequestMapping("/searchContainerHistoryDataBefore.do")
	public String searchContainerHistoryData(HttpServletRequest request){

		return "containerHistoryData";
	}
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
			for(int i=0;i<4;i++){ //cpu mem io net 
				model.addAttribute("sysUsageStr"+i,chartStrList.get(i)+","+chartStrList.get(i+4));
			}
		}catch(Exception e){
			logger.error("add Operator error"+e);
			e.printStackTrace();
		} 
		model.addAttribute("startTime",startTime);
		model.addAttribute("endTime",endTime);
		return "sysHistoryData";
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
			/*List<String> chartStrList=new ArrayList<String>();
			Set<String> appNameSet=Repository.appInfoMap.keySet(); 
			int count=0;
			boolean needTime=false;
			for(String appName:appNameSet){
				count++;
				needTime=count==appNameSet.size()?true:false;
				chartStrList.addAll(service.searchContainerResourceUsage(appName,startTime,endTime,needTime));
			} 
			for(int i=0;i<6;i++){ //组合8个应用的cpu mem ioInput ioOutput netInput netOutput曲线
				model.addAttribute("appUsageStr"+i,chartStrList.get(i)+","+chartStrList.get(i+6)+","+chartStrList.get(i+12)+","+chartStrList.get(i+18)+","+chartStrList.get(i+24)+","+chartStrList.get(i+30)+","+chartStrList.get(i+36)+","+chartStrList.get(i+42));
			} 
			model.addAttribute("appUsageStr"+2,chartStrList.get(48));//绘制时间轴

			 */
		}catch(Exception e){
			logger.error("add Operator error"+e);
			e.printStackTrace();
		}
		model.addAttribute("startTime",startTime);
		model.addAttribute("endTime",endTime);
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
			for(int i=0;i<6;i++){ //组合8个应用的cpu mem ioInput ioOutput netInput netOutput曲线
				model.addAttribute("appUsageStr"+i,chartStrList.get(i)+","+chartStrList.get(i+6)+","+chartStrList.get(i+12)+","+chartStrList.get(i+18)+","+chartStrList.get(i+24)+","+chartStrList.get(i+30)+","+chartStrList.get(i+36)+","+chartStrList.get(i+42));
			} 
			model.addAttribute("appUsageStr"+6,chartStrList.get(48));//绘制时间轴

		}catch(Exception e){
			logger.error("add Operator error"+e);
			e.printStackTrace();
		}
		model.addAttribute("startTime",startTime);
		model.addAttribute("endTime",endTime);
		return "appHistoryData";
	}
}

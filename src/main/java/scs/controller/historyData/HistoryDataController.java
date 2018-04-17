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

import scs.pojo.TestRecordBean;
import scs.service.historyData.HistoryDataService;
import scs.service.recordManage.RecordManageService;
import scs.util.repository.Repository; 

/**
 * 容器部署管理类
 * @author  
 *
 */
@Controller
public class HistoryDataController {
	private static Logger logger = Logger.getLogger(HistoryDataController.class.getName());

	@Resource HistoryDataService service;
	@Resource RecordManageService rService;

	
	@RequestMapping("/searchHistoryData.do")
	public String searchSysHistoryData(HttpServletRequest request,Model model, 
			@RequestParam(value="testRecordId",required=true) int testRecordId){
		try{
			TestRecordBean record=rService.getRecordById(testRecordId);
			List<String> chartStrList=new ArrayList<String>();
			/*
			 * 绘制物理机的cpu 内存 io net使用率图
			 */
			Set<String> hostNameSet=Repository.systemInfoMap.keySet(); 
			for(String hostName:hostNameSet){
				chartStrList.addAll(service.searchSysResourceUsage(hostName,record.getStartTime(),record.getEndTime()));
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
			
			/*
			 * 绘制8个应用的cpu 内存堆叠图
			 */
			chartStrList.clear();
			Set<String> appNameSet=Repository.appInfoMap.keySet(); 
			int count=0;
			boolean needTime=false;
			for(String appName:appNameSet){
				count++;
				needTime=count==appNameSet.size()?true:false;
				chartStrList.addAll(service.searchAppResourceUsage(appName,record.getStartTime(),record.getEndTime(),needTime));
			}
			if(chartStrList.size()==0){
				for(int i=0;i<2;i++){ //cpu mem 
					model.addAttribute("appUsageStr"+i,"");
				} 
				model.addAttribute("appUsageStr"+2,"");//绘制时间轴
			}else{
				for(int i=0;i<2;i++){ //cpu mem 
					model.addAttribute("appUsageStr"+i,chartStrList.get(i)+","+chartStrList.get(i+2)+","+chartStrList.get(i+4)+","+chartStrList.get(i+6)+","+chartStrList.get(i+8)+","+chartStrList.get(i+10)+","+chartStrList.get(i+12)+","+chartStrList.get(i+14));
				} 
				model.addAttribute("appUsageStr"+2,chartStrList.get(16));//绘制时间轴
			}
			
			
		}catch(Exception e){
			logger.error("add Operator error"+e);
			e.printStackTrace();
		}
		return "historyData";
	}
	@RequestMapping("/searchContainerHistoryData.do")
	public String searchContainerHistoryData(HttpServletRequest request,Model model, 
			@RequestParam(value="testRecordId",required=true) int testRecordId){
		try{
			TestRecordBean record=rService.getRecordById(testRecordId);
			List<String> chartStrList=new ArrayList<String>();
			Set<String> hostNameSet=Repository.systemInfoMap.keySet(); 
			for(String hostName:hostNameSet){
				chartStrList.addAll(service.searchContainerResourceUsage(hostName,record.getStartTime(),record.getEndTime()));
			}
			for(int i=0;i<4;i++){ //cpu mem io net
				System.out.println(chartStrList.get(i)+","+chartStrList.get(i+4));
				model.addAttribute("containerUsageStr"+i,chartStrList.get(i)+","+chartStrList.get(i+4));
			}
			
		}catch(Exception e){
			logger.error("add Operator error"+e);
			e.printStackTrace();
		}
		return "historyData";
	}
	@RequestMapping("/searchAppHistoryData.do")
	public String searchAppHistoryData(HttpServletRequest request,Model model, 
			@RequestParam(value="testRecordId",required=true) int testRecordId){
		try{
			TestRecordBean record=rService.getRecordById(testRecordId);
			List<String> chartStrList=new ArrayList<String>();
			Set<String> appNameSet=Repository.appInfoMap.keySet(); 
			int count=0;
			boolean needTime=false;
			for(String appName:appNameSet){
				count++;
				needTime=count==appNameSet.size()?true:false;
				chartStrList.addAll(service.searchAppResourceUsage(appName,record.getStartTime(),record.getEndTime(),needTime));
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

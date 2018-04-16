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
	public String searchContainerDeploy(HttpServletRequest request,Model model, 
			@RequestParam(value="testRecordId",required=true) int testRecordId){
		try{
			TestRecordBean record=rService.getRecordById(testRecordId);
			List<String> chartStrList=new ArrayList<String>();
			Set<String> hostNameSet=Repository.systemInfoMap.keySet(); 
			for(String hostName:hostNameSet){
				chartStrList.addAll(service.searchSysResourceUsage(hostName,record.getStartTime(),record.getEndTime()));
			}
			for(int i=0;i<4;i++){
				System.out.println(chartStrList.get(i)+","+chartStrList.get(i+4));
				model.addAttribute("sysUsageStr"+i,chartStrList.get(i)+","+chartStrList.get(i+4));
			}
			
		}catch(Exception e){
			logger.error("add Operator error"+e);
			e.printStackTrace();
		}
		return "historyData";
	}
 
}

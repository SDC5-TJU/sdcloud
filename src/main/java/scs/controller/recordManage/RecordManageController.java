package scs.controller.recordManage;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import scs.pojo.AppConfigBean;
import scs.pojo.TestRecordBean;
import scs.service.appConfig.AppConfigService;
import scs.service.historyData.HistoryDataService;
import scs.service.recordManage.RecordManageService;
import scs.util.format.DateFormats;
import scs.util.repository.Repository; 

/**
 * 评测记录管理控制类
 * 新的测试记录添加,历史记录查询等
 * @author yanan
 *
 */
@Controller
public class RecordManageController {
	private static Logger logger = Logger.getLogger(RecordManageController.class.getName());

	@Resource RecordManageService service;
	@Resource AppConfigService aService;
	@Resource HistoryDataService hService;

	@RequestMapping("/index.do")
	public String index(HttpServletRequest request,Model model){	 
		return "index";
	}
	@RequestMapping("/addRecordBefore.do")
	public String addRecordBefore(HttpServletRequest request,HttpServletResponse response){
		return "deviceAdd";
	}
	@RequestMapping("/addRecord.do")
	public void addRecord(HttpServletRequest request,HttpServletResponse response, 
			@RequestParam(value="recordDesc",required=false) String recordDesc){
		try{ 
			int result=service.addRecord(recordDesc);//只需要填写记录描述和开始时间
			response.getWriter().print(result);
		}catch(Exception e){
			logger.error("add Operator error"+e);
			e.printStackTrace();
		}
	}
	@RequestMapping("/modifyStartTime.do")
	public void modifyStartTime(HttpServletRequest request,HttpServletResponse response, 
			@RequestParam(value="testRecordId",required=true) int testRecordId){
		try{
			String result=service.modifyStartTime(testRecordId,DateFormats.getInstance().getNowDate());//添加当前系统时间
			response.getWriter().print(result);
		}catch(Exception e){
			logger.error("add Operator error"+e);
			e.printStackTrace();
		}
	}
	@RequestMapping("/modifyEndTime.do")
	public void modifyEndTime(HttpServletRequest request,HttpServletResponse response, 
			@RequestParam(value="testRecordId",required=true) int testRecordId){
		try{
			String result=service.modifyEndTime(testRecordId,DateFormats.getInstance().getNowDate());//添加当前系统时间
			response.getWriter().print(result);
		}catch(Exception e){
			logger.error("add Operator error"+e);
			e.printStackTrace();
		}
	}
	@RequestMapping("/searchRecord.do")
	public String searchRecord(HttpServletRequest request,Model model, 
			@RequestParam(value="page",required=false) int page){
		try{ 
			int totalCount=service.getRecordCount();  //查询该用户拥有的设备总数
			int pageSize=10;						  			   				   //每页显示大小
			int maxPage=(totalCount%pageSize==0)?totalCount/pageSize:totalCount/pageSize+1;//最大页数
			page=(page<=0)?1:page;			   					               //当前第几页
			int start=(page-1)*pageSize;	
			List<TestRecordBean> recordList=service.searchRecord(start, pageSize);
			model.addAttribute("recordList",recordList);
			model.addAttribute("totalCount",totalCount);
			model.addAttribute("curPage",page);
			model.addAttribute("maxPage",maxPage);
			page=maxPage==0?0:page;
			if(page>1){
				model.addAttribute("prePageHref","searchRecord.do?page="+(page-1));
			}
			if(page<maxPage){
				model.addAttribute("nextPageHref","searchRecord.do?page="+(page+1));
			}
		}catch(Exception e){
			logger.error("search record error"+e);
			e.printStackTrace();
		}
		return "recordManage";
	}
	@RequestMapping("/resultAnalysis.do")
	public String resultAnalysis(HttpServletRequest request,Model model,
			@RequestParam(value="testRecordId",required=true) int testRecordId){
		List<AppConfigBean> appConfiglist=aService.getAppConfig(testRecordId);
		for(AppConfigBean bean:appConfiglist){ 
			model.addAttribute(bean.getApplicationName(),bean); 
		}
		TestRecordBean record=service.getRecordById(testRecordId);
		List<String> chartStrList=new ArrayList<String>();
		/*
		 * 绘制物理机的cpu 内存 io net使用率图
		 */
		Set<String> hostNameSet=Repository.systemInfoMap.keySet(); 
		for(String hostName:hostNameSet){
			chartStrList.addAll(hService.searchSysResourceUsage(hostName,record.getStartTime(),record.getEndTime()));
		}
		for(int i=0;i<4;i++){ //组合2个物理机的cpu mem io net曲线 
			model.addAttribute("sysUsageStr"+i,chartStrList.get(i)+","+chartStrList.get(i+4));
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
			chartStrList.addAll(hService.searchAppResourceUsage(appName,record.getStartTime(),record.getEndTime(),needTime));
		}

		for(int i=0;i<6;i++){ //组合8个应用的cpu mem ioInput ioOutput netInput netOutput曲线
			model.addAttribute("appUsageStr"+i,chartStrList.get(i)+","+chartStrList.get(i+6)+","+chartStrList.get(i+12)+","+chartStrList.get(i+18)+","+chartStrList.get(i+24)+","+chartStrList.get(i+30)+","+chartStrList.get(i+36)+","+chartStrList.get(i+42));
		} 
		model.addAttribute("appUsageStr"+6,chartStrList.get(48));//绘制时间轴

		model.addAttribute("testRecordId",testRecordId);
		return "resultAnalysis";
	}
}

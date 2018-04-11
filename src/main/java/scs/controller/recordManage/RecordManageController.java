package scs.controller.recordManage;

import org.apache.log4j.Logger;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import scs.pojo.TestRecordBean;
import scs.service.recordManage.RecordManageService; 

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
	public void updateStartTime(HttpServletRequest request,HttpServletResponse response, 
			@RequestParam(value="testRecordId",required=true) int testRecordId){
		try{
			int result=service.modifyStartTime(testRecordId,new Date());//添加当前系统时间
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
			int result=service.modifyEndTime(testRecordId,new Date());//添加当前系统时间
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
		return "RecordManage";
	}
	@RequestMapping("/index.do")
	public String index(HttpServletRequest request,Model model){	 
		return "index";
	}
}

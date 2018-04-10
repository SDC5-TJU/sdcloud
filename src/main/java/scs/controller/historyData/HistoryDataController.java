package scs.controller.historyData;

import org.apache.log4j.Logger; 

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest; 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam; 
import scs.service.historyData.HistoryDataService; 

/**
 * 容器部署管理类
 * @author  
 *
 */
@Controller
public class HistoryDataController {
	private static Logger logger = Logger.getLogger(HistoryDataController.class.getName());

	@Resource HistoryDataService service;

	
	@RequestMapping("/example.do")
	public String searchContainerDeploy(HttpServletRequest request,Model model, 
			@RequestParam(value="example",required=true) int example){
		try{
			 

		}catch(Exception e){
			logger.error("add Operator error"+e);
			e.printStackTrace();
		}
		return "searchContainerDeploy";
	}
 
}

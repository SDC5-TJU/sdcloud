package scs.controller.appConfig;

import org.apache.log4j.Logger;
 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import scs.pojo.AppConfigBean;
import scs.service.appConfig.AppConfigService; 

/**
 * 应用配置控制类
 * 用于不同在线和离线应用配置
 * 在线应用:请求次数、负载策略、强度、预热次数等
 * 离线应用:负载策略、强度、执行方式等
 * @author yanan
 *
 */
@Controller
public class AppConfigController {
	
	private static Logger logger = Logger.getLogger(AppConfigController.class.getName());

	@Resource AppConfigService service;
 
	/**
	 * 根据testRecordId查询该次测试记录的应用配置情况
	 * @param request
	 * @param model
	 * @param testRecordId 测试记录id
	 * @return 应用配置实体类数组
	 */
	@RequestMapping("/getAppConfig.do")
	public String getAppConfig(HttpServletRequest request,Model model, 
			@RequestParam(value="testRecordId",required=true) int testRecordId){
		try{
			List<AppConfigBean> appConfiglist=service.getAppConfig(testRecordId);
			for(AppConfigBean bean:appConfiglist){ 
				model.addAttribute(bean.getApplicationName(),bean); 
			}
		}catch(Exception e){
			logger.error("add Operator error"+e);
			e.printStackTrace();
		}
		return "appConfig";
	}
	/**
	 * 修改应用配置信息
	 * 这里在线应用webserver websearh silo memcached cassandra具有查询总数，预热总数，请求模式，请求间隔单位
	 * 离线应用 hadoop bonnie scimark没有查询和预热，为统一字段，便于存储保留这两项，两项赋空值
	 * @param request
	 * @param response
	 * @param applicationName 所有应用的应用名称数组,依次填充
	 * @param applicationType 所有应用的应用类型数组,依次填充
	 * @param requestCount 所有应用的请求总数数组,依次填充
	 * @param warmUpCount 所有应用的预热总数数组,依次填充
	 * @param pattern 所有应用的应用启动模式数组,依次填充
	 * @param intensity 所有应用的强度数组,依次填充
	 * @param testRecordId 该次测试记录的id数组,依次填充
	 * @param enable 所有应用的是否启用标志数组,依次填充
	 */
	@RequestMapping("/modifyAppConfig.do")
	public String modifyAppConfig(HttpServletRequest request,HttpServletResponse response,Model model,
			@RequestParam(value="applicationName",required=true) String[] applicationName, 
			@RequestParam(value="applicationType",required=true) String[] applicationType, 
			@RequestParam(value="requestCount",required=false) String[] requestCount,
			@RequestParam(value="warmUpCount",required=false) String[] warmUpCount,
			@RequestParam(value="pattern",required=true) String[] pattern,
			@RequestParam(value="intensity",required=true) String[] intensity,
			@RequestParam(value="testRecordId",required=true) int[] testRecordId,
			@RequestParam(value="enable",required=true) int[] enable){
		try{ 
			Map<String,AppConfigBean> map=new HashMap<String,AppConfigBean>();
			for(int i=0;i<applicationName.length;i++){
				AppConfigBean bean=new AppConfigBean(applicationName[i],applicationType[i],requestCount[i],warmUpCount[i],pattern[i],intensity[i],testRecordId[i],enable[i]);
				map.put(applicationName[i],bean); 
			}
			int result=service.modifyAppConfig(map); 
			model.addAttribute("result",result);
		}catch(Exception e){
			logger.error("add Operator error"+e);
			e.printStackTrace();
		} 
		return "redirect:getAppConfig.do?testRecordId="+testRecordId[0]; 

	}
}

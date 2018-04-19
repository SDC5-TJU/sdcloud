package scs.controller.jobSchedul;

import org.apache.log4j.Logger;
 
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
  
import scs.pojo.AppConfigBean; 
import scs.pojo.TestRecordBean;
import scs.pojo.TimeResultBean;
import scs.pojo.TimeResultDiffBean; 
import scs.service.appConfig.AppConfigService;
import scs.service.jobSchedul.JobSchedulService;
import scs.service.recordManage.RecordManageService;
import scs.util.repository.Repository;
import scs.util.tools.AdapterForResult;
import scs.util.tools.ResultDiffAnalysis; 

/**
 * 应用执行控制类
 * 用于控制不同的应用开启和关闭
 * @author  
 *
 */
@Controller
public class JobSchedulController {
	private static Logger logger = Logger.getLogger(JobSchedulController.class.getName());

	@Resource JobSchedulService service; 
	@Resource AppConfigService aService;
	@Resource RecordManageService rService;
	Random rand=new Random();
	
	@RequestMapping("/jobSchedulBefore.do")
	public String jobSchedulBefore(HttpServletRequest request,HttpServletResponse response,Model model,
			@RequestParam(value="testRecordId",required=true) int testRecordId){
		List<AppConfigBean> appConfiglist=aService.getAppConfig(testRecordId);
		for(AppConfigBean bean:appConfiglist){  
			model.addAttribute(bean.getApplicationName(),bean);
		}
		TestRecordBean recordBean=rService.getRecordById(testRecordId);
		model.addAttribute("recordBean",recordBean);
		StringBuffer strName=new StringBuffer();
		StringBuffer strData=new StringBuffer();
		StringBuffer HSeries=new StringBuffer();
		strName.append("{name:'solrCloud',");//type: 'area',
		strData.append("data:[");
		int time=rand.nextInt(100);
		for(int i=0;i<59;i++){
			strData.append("[").append(System.currentTimeMillis()).append(",").append(time).append("],");
		}
		strData.append("[").append(System.currentTimeMillis()).append(",").append(time).append("]]");
		HSeries.append(strName).append(strData).append(",marker: {enabled: true}}");

		model.addAttribute("solrCloudStr",HSeries.toString());
		/**
		 * 图2 webServer
		 */
		strName.setLength(0);
		strData.setLength(0);
		HSeries.setLength(0);
		strName.append("{name:'webServer',");//type: 'area',
		strData.append("data:[");
		time=rand.nextInt(500);
		for(int i=0;i<59;i++){
			strData.append("[").append(System.currentTimeMillis()).append(",").append(time).append("],");
		}
		strData.append("[").append(System.currentTimeMillis()).append(",").append(time).append("]]");
		HSeries.append(strName).append(strData).append(",marker: {enabled: true}}");
		model.addAttribute("webServerStr",HSeries.toString());
		return "jobControl";

	}
	@RequestMapping("/executeMemcachedApp.do")
	public void executeMemcachedApp(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="isBase",required=true) int isBase){
		try{  
			int result=service.executeMemcachedApp(isBase);
			response.getWriter().print(result); 
		}catch(Exception e){
			logger.error("executeMemcachedApp error"+e);
			e.printStackTrace();
		}

	}
	@RequestMapping("/executeWebSearchApp.do")
	public void executeWebSearchApp(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="isBase",required=true) int isBase){
		try{  
			int result=service.executeWebSearchApp(isBase);
			response.getWriter().print(result); 
		}catch(Exception e){
			logger.error("executeWebSearchApp error"+e);
			e.printStackTrace();
		}

	}
	@RequestMapping("/executeWebServerApp.do")
	public void executeWebServerApp(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="isBase",required=true) int isBase){
		try{  
			int result=service.executeWebServerApp(isBase);
			response.getWriter().print(result); 
		}catch(Exception e){
			logger.error("add Operator error"+e);
			e.printStackTrace();
		}

	}
	@RequestMapping("/executeSiloApp.do")
	public void executeSiloApp(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="isBase",required=true) int isBase){
		try{ 
			int result=service.executeSiloApp(isBase);
			response.getWriter().print(result);

		}catch(Exception e){
			logger.error("add Operator error"+e);
			e.printStackTrace();
		}

	}
	@RequestMapping("/executeCassandraApp.do")
	public void executeCassandraApp(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="isBase",required=true) int isBase){
		try{ 
			int result=service.executeCassandraApp(isBase);
			response.getWriter().print(result);

		}catch(Exception e){
			logger.error("add Operator error"+e);
			e.printStackTrace();
		}

	}
	@RequestMapping("/executeBonnieApp.do")
	public void executeBonnieApp(HttpServletRequest request,HttpServletResponse response){
		try{ 
			int result=service.executeBonnieApp();
			response.getWriter().print(result);

		}catch(Exception e){
			logger.error("add Operator error"+e);
			e.printStackTrace();
		}

	}
	@RequestMapping("/shutdownBonnieApp.do")
	public void shutdownBonnieApp(HttpServletRequest request,HttpServletResponse response){
		try{ 
			int result=service.shutdownBonnieApp();
			response.getWriter().print(result);

		}catch(Exception e){
			logger.error("add Operator error"+e);
			e.printStackTrace();
		}

	}
	@RequestMapping("/executeScimarkApp.do")
	public void executeScimarkApp(HttpServletRequest request,HttpServletResponse response){
		try{ 
			int result=service.executeScimarkApp();
			response.getWriter().print(result);

		}catch(Exception e){
			logger.error("add Operator error"+e);
			e.printStackTrace();
		}

	}
 
	@RequestMapping("/executeHadoopApp.do")
	public void executeHadoopApp(HttpServletRequest request,HttpServletResponse response){
		try{ 
			int result=service.executeHadoopApp();
			response.getWriter().print(result);
		}catch(Exception e){
			logger.error("add Operator error"+e);
			e.printStackTrace();
		}

	}
	@RequestMapping("/getAppStatus.do")
	public void getAppStatus(HttpServletRequest request,HttpServletResponse response){
		try{ 
			String resultStr=service.getAppStatus();
			response.getWriter().print(resultStr);
			//[{"memcached":false,"scimark":false,"bonnie":false,"webSearch":false,"cassandra":false,"webServer":false,"hadoop":false,"silo":false}]
		}catch(Exception e){
			logger.error("add Operator error"+e);
			e.printStackTrace();
		}
	}

	@RequestMapping("/getMemcachedResult.do")
	public String getMemcachedResult(HttpServletRequest request,HttpServletResponse response,Model model,
			@RequestParam(value="testRecordId",required=true) int testRecordId){
		try{ 
			if(Repository.memcachedBaseDataList.size()==0){
				model.addAttribute("message","尚未进行memcached基准测试");
				return "redirect:resultAnalysis.do?testRecordId="+testRecordId;
			}else if(Repository.memcachedDataList.size()==0){
				model.addAttribute("message","尚未进行memcached正式测试");
				return "redirect:resultAnalysis.do?testRecordId="+testRecordId;
			}
			TimeResultBean memBaseResult=AdapterForResult.adapter("memcached", Repository.memcachedBaseDataList);
			TimeResultBean memResult = AdapterForResult.adapter("memcached",Repository.memcachedDataList);//new TimeResultBean();
 		
			model.addAttribute("MemcachedResult",memResult);
			model.addAttribute("MemcachedBaseResult",memBaseResult);
		 
			TimeResultDiffBean diffBean=ResultDiffAnalysis.getInstance().getMemResultDiff(memBaseResult,memResult);
			model.addAttribute("diffBean",diffBean);
			List<AppConfigBean> appConfiglist=aService.getAppConfig(testRecordId);
			for(AppConfigBean bean:appConfiglist){ 
				model.addAttribute(bean.getApplicationName(),bean); 
			}
			model.addAttribute("testRecordId",testRecordId);
		}catch(Exception e){
			logger.error("add Operator error"+e);
			e.printStackTrace();
		}
		return "memResultAnalysis";
	}
	@RequestMapping("/getWebServerResult.do")
	public String getWebServerResult(HttpServletRequest request,HttpServletResponse response,Model model,
			@RequestParam(value="testRecordId",required=true) int testRecordId){
		try{ 
			if(Repository.webServerBaseDataList.size()==0){
				model.addAttribute("message","尚未进行webServer基准测试");
				return "redirect:resultAnalysis.do?testRecordId="+testRecordId;
			}else if(Repository.webServerDataList.size()==0){
				model.addAttribute("message","尚未进行webServer正式测试");
				return "redirect:resultAnalysis.do?testRecordId="+testRecordId;
			}
			TimeResultBean webServerBaseResult= AdapterForResult.adapter("webServer",Repository.webServerBaseDataList);//new TimeResultBean();
			TimeResultBean webServerResult=AdapterForResult.adapter("webServer",Repository.webServerDataList);
			 
			model.addAttribute("webServerResult",webServerResult);
			model.addAttribute("webServerBaseResult",webServerBaseResult);
 	 
			TimeResultDiffBean diffBean=ResultDiffAnalysis.getInstance().getResultDiff(Repository.webServerBaseDataList,Repository.webServerDataList,webServerBaseResult,webServerResult);
			model.addAttribute("diffBean",diffBean);
			List<AppConfigBean> appConfiglist=aService.getAppConfig(testRecordId);
			for(AppConfigBean bean:appConfiglist){ 
				model.addAttribute(bean.getApplicationName(),bean); 
			}
			model.addAttribute("testRecordId",testRecordId);
		}catch(Exception e){
			logger.error("add Operator error"+e);
			e.printStackTrace();
		}
		return "webServerResultAnalysis";
	}
	@RequestMapping("/getWebSearchResult.do")
	public String getWebSearchResult(HttpServletRequest request,HttpServletResponse response,Model model,
			@RequestParam(value="testRecordId",required=true) int testRecordId){
		try{ 
			if(Repository.webSearchBaseDataList.size()==0){
				model.addAttribute("message","尚未进行webSearch基准测试");
				return "redirect:resultAnalysis.do?testRecordId="+testRecordId;
			}else if(Repository.webSearchDataList.size()==0){
				model.addAttribute("message","尚未进行webSearch正式测试");
				return "redirect:resultAnalysis.do?testRecordId="+testRecordId;
			}
			TimeResultBean webSearchBaseResult=AdapterForResult.adapter("webSearch",Repository.webSearchBaseDataList);//new TimeResultBean();
			TimeResultBean webSearchResult=AdapterForResult.adapter("webSearch",Repository.webSearchDataList);//new TimeResultBean();
			
			model.addAttribute("webSearchResult",webSearchResult);
			model.addAttribute("webSearchBaseResult",webSearchBaseResult);
		
			TimeResultDiffBean diffBean=ResultDiffAnalysis.getInstance().getResultDiff(Repository.webSearchBaseDataList,Repository.webSearchDataList,webSearchBaseResult,webSearchResult);
			model.addAttribute("diffBean",diffBean);
			List<AppConfigBean> appConfiglist=aService.getAppConfig(testRecordId);
			for(AppConfigBean bean:appConfiglist){ 
				model.addAttribute(bean.getApplicationName(),bean); 
			}
			model.addAttribute("testRecordId",testRecordId);
		}catch(Exception e){ 
			e.printStackTrace();
		}
		return "webSearchResultAnalysis";
	}
	@RequestMapping("/getCassandraResult.do")
	public String getCassandraResult(HttpServletRequest request,HttpServletResponse response,Model model,
			@RequestParam(value="testRecordId",required=true) int testRecordId){
		try{ 
			if(Repository.cassandraBaseDataList.size()==0){
				model.addAttribute("message","尚未进行cassandra基准测试");
				return "redirect:resultAnalysis.do?testRecordId="+testRecordId;
			}else if(Repository.cassandraDataList.size()==0){
				model.addAttribute("message","尚未进行cassandra正式测试");
				return "redirect:resultAnalysis.do?testRecordId="+testRecordId;
			}
			TimeResultBean cassandraBaseResult=AdapterForResult.adapter("cassandra",Repository.cassandraBaseDataList);//new TimeResultBean();
			TimeResultBean cassandraResult=AdapterForResult.adapter("cassandra",Repository.cassandraDataList);//new TimeResultBean();
			
			model.addAttribute("cassandraResult",cassandraResult);
			model.addAttribute("cassandraBaseResult",cassandraBaseResult);
		
			TimeResultDiffBean diffBean=ResultDiffAnalysis.getInstance().getResultDiff(Repository.cassandraBaseDataList,Repository.cassandraDataList,cassandraBaseResult,cassandraResult);
			model.addAttribute("diffBean",diffBean);
			List<AppConfigBean> appConfiglist=aService.getAppConfig(testRecordId);
			for(AppConfigBean bean:appConfiglist){ 
				model.addAttribute(bean.getApplicationName(),bean); 
			}
			model.addAttribute("testRecordId",testRecordId);
		}catch(Exception e){ 
			e.printStackTrace();
		}
		return "cassandraResultAnalysis";
	}
	@RequestMapping("/getSiloResult.do")
	public String getSiloResult(HttpServletRequest request,HttpServletResponse response,Model model,
			@RequestParam(value="testRecordId",required=true) int testRecordId){
		try{ 
			if(Repository.siloBaseDataList.size()==0){
				model.addAttribute("message","尚未进行silo基准测试");
				return "redirect:resultAnalysis.do?testRecordId="+testRecordId;
			}else if(Repository.siloDataList.size()==0){
				model.addAttribute("message","尚未进行silo正式测试");
				return "redirect:resultAnalysis.do?testRecordId="+testRecordId;
			}
			TimeResultBean siloBaseResult= AdapterForResult.adapter("silo",Repository.siloBaseDataList);//new TimeResultBean();
	    	TimeResultBean siloResult= AdapterForResult.adapter("silo",Repository.siloDataList);//new TimeResultBean();
 	
			model.addAttribute("siloResult",siloResult);
			model.addAttribute("siloBaseResult",siloBaseResult);
 	
			TimeResultDiffBean diffBean=ResultDiffAnalysis.getInstance().getsiloResultDiff(Repository.siloBaseDataList,Repository.siloDataList,siloBaseResult,siloResult);
			model.addAttribute("diffBean",diffBean);
			List<AppConfigBean> appConfiglist=aService.getAppConfig(testRecordId);
			for(AppConfigBean bean:appConfiglist){ 
				model.addAttribute(bean.getApplicationName(),bean); 
			}
			model.addAttribute("testRecordId",testRecordId);
		}catch(Exception e){
			logger.error("add Operator error"+e);
			e.printStackTrace();
		}
		return "siloResultAnalysis";
	}
 
	@RequestMapping("/getWebSearchQueryTime.do")
	public void getWebSearchQueryTime(HttpServletRequest request,HttpServletResponse response,Model model){
		try{ 
			int time=service.getWebSearchQueryTime();
			response.getWriter().print(time);   
		}catch(Exception e){
			logger.error("add Operator error"+e);
			e.printStackTrace();
		}
	}
	@RequestMapping("/getWebServerQueryTime.do")
	public void getWebServerQueryTime(HttpServletRequest request,HttpServletResponse response,Model model){
		try{ 
			int time=service.getWebServerQueryTime();
			response.getWriter().print(time);   
		}catch(Exception e){
			logger.error("add Operator error"+e);
			e.printStackTrace();
		}
	}

}

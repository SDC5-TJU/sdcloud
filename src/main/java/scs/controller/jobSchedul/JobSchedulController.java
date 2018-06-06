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
import scs.pojo.MemcachedDataBean;
import scs.pojo.SiloDataBean;
import scs.pojo.TestRecordBean;
import scs.pojo.TimeResultBean;
import scs.pojo.TimeResultDiffBean;
import scs.pojo.TwoTuple;
import scs.pojo.XapianDataBean;
import scs.service.appConfig.AppConfigService;
import scs.service.historyData.HistoryDataService;
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
	private Random rand=new Random();
	
	@Resource JobSchedulService service; 
	@Resource AppConfigService aService;
	@Resource RecordManageService rService;
	@Resource HistoryDataService hService;
	
	
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
		model.addAttribute("appConfigSize",Repository.appConfigMap.size());
		model.addAttribute("testRecordId",testRecordId);
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
			e.printStackTrace();
		}

	}
	@RequestMapping("/executeXapianApp.do")
	public void executeXapianApp(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="isBase",required=true) int isBase){
		try{
			int result=service.executeXapianApp(isBase);
			response.getWriter().print(result);

		}catch(Exception e){
			e.printStackTrace();
		}

	}
	@RequestMapping("/executeRedisApp.do")
	public void executeRedisApp(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="isBase",required=true) int isBase){
		try{ 
			int result=service.executeRedisApp(isBase);
			response.getWriter().print(result);

		}catch(Exception e){
			
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
			
			e.printStackTrace();
		}

	}
	@RequestMapping("/executeBonnieApp.do")
	public void executeBonnieApp(HttpServletRequest request,HttpServletResponse response){
		try{ 
			int result=service.executeBonnieApp();
			response.getWriter().print(result);

		}catch(Exception e){
			
			e.printStackTrace();
		}

	}
	@RequestMapping("/shutdownBonnieApp.do")
	public void shutdownBonnieApp(HttpServletRequest request,HttpServletResponse response){
		try{ 
			int result=service.shutdownBonnieApp();
			response.getWriter().print(result);

		}catch(Exception e){
			
			e.printStackTrace();
		}

	}
	@RequestMapping("/executeScimarkApp.do")
	public void executeScimarkApp(HttpServletRequest request,HttpServletResponse response){
		try{ 
			int result=service.executeScimarkApp();
			response.getWriter().print(result);

		}catch(Exception e){
			
			e.printStackTrace();
		}

	}
	@RequestMapping("/executeDwarfApp.do")
	public void executeDwarfApp(HttpServletRequest request,HttpServletResponse response){
		try{ 
			int result=service.executeDwarfApp();
			response.getWriter().print(result);

		}catch(Exception e){
			
			e.printStackTrace();
		}

	}
 
	@RequestMapping("/executeHadoopApp.do")
	public void executeHadoopApp(HttpServletRequest request,HttpServletResponse response){
		try{ 
			int result=service.executeHadoopApp();
			response.getWriter().print(result);
		}catch(Exception e){
			
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
			
			e.printStackTrace();
		}
	}

	@RequestMapping("/getMemcachedResult.do")
	public String getMemcachedResult(HttpServletRequest request,HttpServletResponse response,Model model,
			@RequestParam(value="testRecordId",required=true) int testRecordId){
		try{  
			List<MemcachedDataBean> resultBaseList=null;
			List<MemcachedDataBean> resultList=null;
			if(testRecordId==Repository.curTestRecordId){
				//如果要查看结果的测试记录id等于当前的测试记录,直接把静态仓库里的数据提出 
				resultBaseList=Repository.memcachedBaseDataList;
				resultList=Repository.memcachedDataList;
			}else{
				//否则,从数据库中读取历史记录 
				resultBaseList=hService.searchMemcachedData(testRecordId,1);//读取基准数据
				resultList=hService.searchMemcachedData(testRecordId,0);//读取测试数据
			}
			/*
			 * 判断是否缺少数据 (基准和正式都要有才可以进行差异值计算)
			 */
			if(resultBaseList.size()==0){
				model.addAttribute("message","尚未进行memcached基准测试");
				return "memResultAnalysis";
			}else if(resultList.size()==0){
				model.addAttribute("message","尚未进行memcached正式测试");
				return "memResultAnalysis";
			}
			//计算基准数据和正式数据的各项指标
			TimeResultBean resultBaseIndex=AdapterForResult.adapter("memcached",resultBaseList);
			TimeResultBean resultIndex = AdapterForResult.adapter("memcached",resultList);//new TimeResultBean();
			model.addAttribute("MemcachedBaseResult",resultBaseIndex);
			model.addAttribute("MemcachedResult",resultIndex);
			//计算各项指标差异值
			TimeResultDiffBean diffBean=ResultDiffAnalysis.getInstance().getMemResultDiff(resultBaseIndex,resultIndex);
			model.addAttribute("diffBean",diffBean);
			//封装appconfig配置信息
			List<AppConfigBean> appConfiglist=aService.getAppConfig(testRecordId);
			for(AppConfigBean bean:appConfiglist){ 
				model.addAttribute(bean.getApplicationName(),bean); 
			}
			model.addAttribute("testRecordId",testRecordId);
		}catch(Exception e){
			
			e.printStackTrace();
		}
		return "memResultAnalysis";
	}
	@RequestMapping("/getWebServerResult.do")
	public String getWebServerResult(HttpServletRequest request,HttpServletResponse response,Model model,
			@RequestParam(value="testRecordId",required=true) int testRecordId){
		try{ 
			List<TwoTuple<Long, Integer>> resultBaseList=null;
			List<TwoTuple<Long, Integer>> resultList=null;
			if(testRecordId==Repository.curTestRecordId){
				//如果要查看结果的测试记录id等于当前的测试记录,直接把静态仓库里的数据提出 
				resultBaseList=Repository.webServerBaseDataList;
				resultList=Repository.webServerDataList;
			}else{
				//否则,从数据库中读取历史记录 
				resultBaseList=hService.searchWebServerData(testRecordId, 1);//读取基准数据
				resultList=hService.searchWebServerData(testRecordId,0);//读取测试数据
			}
			String appName="webServer";
			if(resultBaseList.size()==0){
				model.addAttribute("message","尚未进行"+appName+"基准测试");
				return "webServerResultAnalysis";
			}else if(resultList.size()==0){
				model.addAttribute("message","尚未进行"+appName+"正式测试");
				return "webServerResultAnalysis";
			} 
			//计算基准数据和正式数据的各项指标
			Repository.EAThreshold=Float.MAX_VALUE;
			TimeResultBean resultBaseIndex= AdapterForResult.adapter(appName,resultBaseList); 
			Repository.EAThreshold=resultBaseIndex.getNintyNineTh();
			TimeResultBean resultIndex=AdapterForResult.adapter(appName,resultList);
			model.addAttribute(appName+"BaseResult",resultBaseIndex);
			model.addAttribute(appName+"Result",resultIndex);
			//计算各项指标差异值
			TimeResultDiffBean diffBean=ResultDiffAnalysis.getInstance().getResultDiff(resultBaseList,resultList,resultBaseIndex,resultIndex);
			model.addAttribute("diffBean",diffBean);
			//封装appconfig配置信息
			List<AppConfigBean> appConfiglist=aService.getAppConfig(testRecordId);
			for(AppConfigBean bean:appConfiglist){ 
				model.addAttribute(bean.getApplicationName(),bean); 
			}
			model.addAttribute("testRecordId",testRecordId);
		}catch(Exception e){ 
			e.printStackTrace();
		}
		return "webServerResultAnalysis";
	}
	@RequestMapping("/getWebSearchResult.do")
	public String getWebSearchResult(HttpServletRequest request,HttpServletResponse response,Model model,
			@RequestParam(value="testRecordId",required=true) int testRecordId){
		try{ 
			List<TwoTuple<Long, Integer>> resultBaseList=null;
			List<TwoTuple<Long, Integer>> resultList=null;
			if(testRecordId==Repository.curTestRecordId){
				//如果要查看结果的测试记录id等于当前的测试记录,直接把静态仓库里的数据提出 
				resultBaseList=Repository.webSearchBaseDataList;
				resultList=Repository.webSearchDataList;
			}else{
				//否则,从数据库中读取历史记录 
				resultBaseList=hService.searchWebSearchData(testRecordId,1);//读取基准数据
				resultList=hService.searchWebSearchData(testRecordId,0);//读取测试数据
			}
			/*
			 * 判断是否缺少数据 (基准和正式都要有才可以进行差异值计算)
			 */
			String appName="webSearch";
			if(resultBaseList.size()==0){
				model.addAttribute("message","尚未进行"+appName+"基准测试");
				return "webSearchResultAnalysis"; 
			}else if(resultList.size()==0){
				model.addAttribute("message","尚未进行"+appName+"正式测试");
				return "webSearchResultAnalysis"; 
			} 
			//计算基准数据和正式数据的各项指标
			Repository.EAThreshold=Float.MAX_VALUE;
			TimeResultBean resultBaseIndex= AdapterForResult.adapter(appName,resultBaseList);
			Repository.EAThreshold=resultBaseIndex.getNintyNineTh();
			TimeResultBean resultIndex=AdapterForResult.adapter(appName,resultList);
			model.addAttribute(appName+"BaseResult",resultBaseIndex);
			model.addAttribute(appName+"Result",resultIndex);
			//计算各项指标差异值
			TimeResultDiffBean diffBean=ResultDiffAnalysis.getInstance().getResultDiff(resultBaseList,resultList,resultBaseIndex,resultIndex);
			model.addAttribute("diffBean",diffBean);
			//封装appconfig配置信息
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
			List<TwoTuple<Long, Integer>> resultBaseList=null;
			List<TwoTuple<Long, Integer>> resultList=null;
			if(testRecordId==Repository.curTestRecordId){
				//如果要查看结果的测试记录id等于当前的测试记录,直接把静态仓库里的数据提出 
				resultBaseList=Repository.cassandraBaseDataList;
				resultList=Repository.cassandraDataList;
			}else{
				//否则,从数据库中读取历史记录 
				resultBaseList=hService.searchCassandraData(testRecordId,1);//读取基准数据
				resultList=hService.searchCassandraData(testRecordId,0);//读取测试数据
			}
			/*
			 * 判断是否缺少数据 (基准和正式都要有才可以进行差异值计算)
			 */
			String appName="cassandra";
			if(resultBaseList.size()==0){
				model.addAttribute("message","尚未进行"+appName+"基准测试");
				return "cassandraResultAnalysis";
			}else if(resultList.size()==0){
				model.addAttribute("message","尚未进行"+appName+"正式测试");
				return "cassandraResultAnalysis";
			} 
			//计算基准数据和正式数据的各项指标
			Repository.EAThreshold=Float.MAX_VALUE;
			TimeResultBean resultBaseIndex= AdapterForResult.adapter(appName,resultBaseList);
			Repository.EAThreshold=resultBaseIndex.getNintyNineTh();
			TimeResultBean resultIndex=AdapterForResult.adapter(appName,resultList);
			model.addAttribute(appName+"BaseResult",resultBaseIndex);
			model.addAttribute(appName+"Result",resultIndex);
			//计算各项指标差异值
			TimeResultDiffBean diffBean=ResultDiffAnalysis.getInstance().getResultDiff(resultBaseList,resultList,resultBaseIndex,resultIndex);
			model.addAttribute("diffBean",diffBean);
			//封装appconfig配置信息
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
	@RequestMapping("/getRedisResult.do")
	public String getRedisResult(HttpServletRequest request,HttpServletResponse response,Model model,
			@RequestParam(value="testRecordId",required=true) int testRecordId){
		try{ 
			List<TwoTuple<Long, Integer>> resultBaseList=null;
			List<TwoTuple<Long, Integer>> resultList=null;
			if(testRecordId==Repository.curTestRecordId){
				//如果要查看结果的测试记录id等于当前的测试记录,直接把静态仓库里的数据提出 
				resultBaseList=Repository.redisBaseDataList;
				resultList=Repository.redisDataList;
			}else{
				//否则,从数据库中读取历史记录 
				resultBaseList=hService.searchRedisData(testRecordId,1);//读取基准数据
				resultList=hService.searchRedisData(testRecordId,0);//读取测试数据
			}
			/*
			 * 判断是否缺少数据 (基准和正式都要有才可以进行差异值计算)
			 */
			String appName="redis";
			if(resultBaseList.size()==0){
				model.addAttribute("message","尚未进行"+appName+"基准测试");
				return "redisResultAnalysis";
			}else if(resultList.size()==0){
				model.addAttribute("message","尚未进行"+appName+"正式测试");
				return "redisResultAnalysis";
			} 
			//计算基准数据和正式数据的各项指标
			Repository.EAThreshold=Float.MAX_VALUE;
			TimeResultBean resultBaseIndex= AdapterForResult.adapter(appName,resultBaseList);
			Repository.EAThreshold=resultBaseIndex.getNintyNineTh();
			TimeResultBean resultIndex=AdapterForResult.adapter(appName,resultList);
			model.addAttribute(appName+"BaseResult",resultBaseIndex);
			model.addAttribute(appName+"Result",resultIndex);
			//计算各项指标差异值
			TimeResultDiffBean diffBean=ResultDiffAnalysis.getInstance().getResultDiff(resultBaseList,resultList,resultBaseIndex,resultIndex);
			model.addAttribute("diffBean",diffBean);
			//封装appconfig配置信息
			List<AppConfigBean> appConfiglist=aService.getAppConfig(testRecordId);
			for(AppConfigBean bean:appConfiglist){
				model.addAttribute(bean.getApplicationName(),bean);
			}
			model.addAttribute("testRecordId",testRecordId);
		}catch(Exception e){ 
			e.printStackTrace();
		}
		return "redisResultAnalysis";
	}
	@RequestMapping("/getSiloResult.do")
	public String getSiloResult(HttpServletRequest request,HttpServletResponse response,Model model,
			@RequestParam(value="testRecordId",required=true) int testRecordId){
		try{ 
			List<SiloDataBean> resultBaseList=null;
			List<SiloDataBean> resultList=null;
			if(testRecordId==Repository.curTestRecordId){
				//如果要查看结果的测试记录id等于当前的测试记录,直接把静态仓库里的数据提出 
				resultBaseList=Repository.siloBaseDataList;
				resultList=Repository.siloDataList;
			}else{
				//否则,从数据库中读取历史记录 
				resultBaseList=hService.searchSiloData(testRecordId,1);//读取基准数据
				resultList=hService.searchSiloData(testRecordId,0);//读取测试数据
			}
			/*
			 * 判断是否缺少数据 (基准和正式都要有才可以进行差异值计算)
			 */
			String appName="silo";
			if(resultBaseList.size()==0){
				model.addAttribute("message","尚未进行"+appName+"基准测试");
				return "siloResultAnalysis";
			}else if(resultList.size()==0){
				model.addAttribute("message","尚未进行"+appName+"正式测试");
				return "siloResultAnalysis";
			} 
			//计算基准数据和正式数据的各项指标
			Repository.EAThreshold=Float.MAX_VALUE;
			TimeResultBean resultBaseIndex= AdapterForResult.adapter(appName,resultBaseList); 
			Repository.EAThreshold=resultBaseIndex.getNintyNineTh();
			TimeResultBean resultIndex=AdapterForResult.adapter(appName,resultList);
			model.addAttribute(appName+"BaseResult",resultBaseIndex);
			model.addAttribute(appName+"Result",resultIndex);
			//计算各项指标差异值
			TimeResultDiffBean diffBean=ResultDiffAnalysis.getInstance().getSiloResultDiff(resultBaseList,resultList,resultBaseIndex,resultIndex);
			model.addAttribute("diffBean",diffBean);
			//封装appconfig配置信息
			List<AppConfigBean> appConfiglist=aService.getAppConfig(testRecordId);
			for(AppConfigBean bean:appConfiglist){ 
				model.addAttribute(bean.getApplicationName(),bean); 
			}
			model.addAttribute("testRecordId",testRecordId);
		}catch(Exception e){ 
			e.printStackTrace();
		}
		return "siloResultAnalysis";
	}
	@RequestMapping("/getXapianResult.do")
	public String getXapianResult(HttpServletRequest request,HttpServletResponse response,Model model,
			@RequestParam(value="testRecordId",required=true) int testRecordId){
		try{
			List<XapianDataBean> resultBaseList=null;
			List<XapianDataBean> resultList=null;
			if(testRecordId==Repository.curTestRecordId){
				//如果要查看结果的测试记录id等于当前的测试记录,直接把静态仓库里的数据提出 
				resultBaseList=Repository.xapianBaseDataList;
				resultList=Repository.xapianDataList;
			}else{
				//否则,从数据库中读取历史记录 
				resultBaseList=hService.searchXapianData(testRecordId,1);//读取基准数据
				resultList=hService.searchXapianData(testRecordId,0);//读取测试数据
			}
			/*
			 * 判断是否缺少数据 (基准和正式都要有才可以进行差异值计算)
			 */
			String appName="xapian";
			if(resultBaseList.size()==0){
				model.addAttribute("message","尚未进行"+appName+"基准测试");
				return "xapianResultAnalysis";
			}else if(resultList.size()==0){
				model.addAttribute("message","尚未进行"+appName+"正式测试");
				return "xapianResultAnalysis";
			} 
			//计算基准数据和正式数据的各项指标
			Repository.EAThreshold=Float.MAX_VALUE;
			TimeResultBean resultBaseIndex= AdapterForResult.adapter(appName,resultBaseList); 
			Repository.EAThreshold=resultBaseIndex.getNintyNineTh();
			TimeResultBean resultIndex=AdapterForResult.adapter(appName,resultList);
			model.addAttribute(appName+"BaseResult",resultBaseIndex);
			model.addAttribute(appName+"Result",resultIndex);
			//计算各项指标差异值
			TimeResultDiffBean diffBean=ResultDiffAnalysis.getInstance().getXapianResultDiff(resultBaseList,resultList,resultBaseIndex,resultIndex);
			model.addAttribute("diffBean",diffBean);
			//封装appconfig配置信息
			List<AppConfigBean> appConfiglist=aService.getAppConfig(testRecordId);
			for(AppConfigBean bean:appConfiglist){ 
				model.addAttribute(bean.getApplicationName(),bean); 
			}
			model.addAttribute("testRecordId",testRecordId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "xapianResultAnalysis";
	}
	@RequestMapping("/getWebSearchQueryTime.do")
	public void getWebSearchQueryTime(HttpServletRequest request,HttpServletResponse response,Model model){
		try{ 
			int time=service.getWebSearchQueryTime();
			response.getWriter().print(time);   
		}catch(Exception e){
			
			e.printStackTrace();
		}
	}
	@RequestMapping("/getWebServerQueryTime.do")
	public void getWebServerQueryTime(HttpServletRequest request,HttpServletResponse response,Model model){
		try{ 
			int time=service.getWebServerQueryTime();
			response.getWriter().print(time);   
		}catch(Exception e){
			
			e.printStackTrace();
		}
	}

}

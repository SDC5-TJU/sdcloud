package scs.controller.jobSchedul;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.sf.json.JSONArray;
import scs.pojo.AppConfigBean;
import scs.pojo.MemcachedDataBean; 
import scs.pojo.RiscvLLCGroup;
import scs.pojo.RiscvLLCPOJO;
import scs.pojo.SiloDataBean;
import scs.pojo.SystemResourceUsageBean;
import scs.pojo.TestRecordBean;
import scs.pojo.TimeResultBean;
import scs.pojo.TimeResultDiffBean;
import scs.pojo.TwoTuple;
import scs.pojo.XapianDataBean;
import scs.service.appConfig.AppConfigService;
import scs.service.historyData.HistoryDataService;
import scs.service.jobSchedul.JobSchedulService;
import scs.service.monitor.riscv.ReadRiscvFiles;
import scs.service.monitor.riscv.impl.ReadRiscvFilesImpl; 
import scs.service.recordManage.RecordManageService;
import scs.util.jobSchedul.jobImpl.webServer.WebServerJobImpl;
import scs.util.loadGen.genDriver.WebSearchDriver;
import scs.util.loadGen.recordDriver.RecordDriver;
import scs.util.repository.Repository;
import scs.util.tools.AdapterForResult;
import scs.util.tools.ReadFile;
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
	/**
	 * jobControl页面web搜索的观察窗口
	 * 每次返回一次查询的时间,在曲线上绘制出来
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getWebSearchQueryTime.do")
	public void getWebSearchQueryTime(HttpServletRequest request,HttpServletResponse response){
		try{ 
			int time=service.getWebSearchQueryTime();
			response.getWriter().print(time);   
		}catch(Exception e){

			e.printStackTrace();
		}
	}
	/**
	 * jobControl页面webServer的观察窗口
	 * 每次返回一次查询的时间,在曲线上绘制出来
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getWebServerQueryTime.do")
	public void getWebServerQueryTime(HttpServletRequest request,HttpServletResponse response){
		try{ 
			int time=service.getWebServerQueryTime();
			response.getWriter().print(time);   
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@RequestMapping("/startOnlineQuery.do")
	public void startOnlineQuery(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="intensity",required=true) int intensity){
		try{
			System.out.println("start");  
			Repository.onlineDataFlag=true;
			if(intensity<=0)
				intensity=1;
			Repository.onlineRequestIntensity=intensity;
			RecordDriver.getInstance().execute();
			WebSearchDriver.getInstance().executeJob("possion");

		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@RequestMapping("/setIntensity.do")
	public void setIntensity(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="intensity",required=true) int intensity){
		try{ 
			if(intensity<=0)
				intensity=1;
			Repository.onlineRequestIntensity=intensity;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@RequestMapping("/stopOnlineQuery.do")
	public void stopOnlineQuery(HttpServletRequest request,HttpServletResponse response){
		try{
			Repository.onlineDataFlag=false; 
			System.out.println("stop");  

		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@RequestMapping("/goOnlineQuery.do")
	public String goOnlineQuery(HttpServletRequest request,HttpServletResponse response,Model model){
		StringBuffer strName=new StringBuffer();
		StringBuffer strData=new StringBuffer();
		StringBuffer HSeries=new StringBuffer();
		strName.append("{name:'queryTime',"); 
		strData.append("data:[");
		int size= 60;
		for(int i=0;i<size;i++){
			strData.append("[").append(System.currentTimeMillis()).append(",").append(0).append("],");
		}
		strData.append("[").append(System.currentTimeMillis()).append(",").append(0).append("]]");
		HSeries.append(strName).append(strData).append("}");
		model.addAttribute("seriesStr",HSeries.toString()); 

		return "onlineData";
	}


	@RequestMapping("/getOnlineQueryTime.do")
	public void getOnlineQueryTime(HttpServletRequest request,HttpServletResponse response){
		try{
			response.getWriter().write(JSONArray.fromObject(Repository.latestOnlineData).toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	@RequestMapping("/getRiscvXapianResult.do")
	public String getRiscvXapianResult(HttpServletRequest request,HttpServletResponse response,Model model){

		List<XapianDataBean> resultBaseList=new ArrayList<XapianDataBean>();
		List<XapianDataBean> resultList=new ArrayList<XapianDataBean>();
		List<XapianDataBean> resultLableList=new ArrayList<XapianDataBean>();

		try{
			Properties prop = new Properties();
			InputStream is = WebServerJobImpl.class.getResourceAsStream("/conf/sys.properties");
			try {
				prop.load(is);
			} catch (IOException e) { 
				e.printStackTrace();
			}
			String riscvDataPath=prop.getProperty("riscv_xapian_data_path").trim();
			File file=new File(riscvDataPath+"lats1.txt");
			if(file.exists()){
				resultBaseList=service.getRiscvXapianResult(riscvDataPath+"lats1.txt");
			}
			file=new File(riscvDataPath+"lats2.txt");
			if(file.exists()){
				resultList=service.getRiscvXapianResult(riscvDataPath+"lats2.txt");
			}
			file=new File(riscvDataPath+"lats3.txt");
			if(file.exists()){
				resultLableList=service.getRiscvXapianResult(riscvDataPath+"lats3.txt");
			}
			file=new File(riscvDataPath+".qps");
			if(file.exists()){
				String qps=ReadFile.getInstance().readRedisQpsFile(riscvDataPath+".qps");
				model.addAttribute("qps",qps);
			}
			/*
			 * 判断是否缺少数据 (基准和正式都要有才可以进行差异值计算)
			 */
			String appName="xapian";

			//计算基准数据和正式数据的各项指标
			Repository.EAThreshold=Float.MAX_VALUE;
			TimeResultBean resultBaseIndex= AdapterForResult.adapter(appName,resultBaseList); 
			Repository.EAThreshold=resultBaseIndex.getNintyNineTh();
			TimeResultBean resultIndex=AdapterForResult.adapter(appName,resultList);
			TimeResultBean resultLabelIndex=AdapterForResult.adapter(appName,resultLableList);
			model.addAttribute("BaseResult",resultBaseIndex);
			model.addAttribute("Result",resultIndex);
			model.addAttribute("LabelResult",resultLabelIndex);
			//计算各项指标差异值
			TimeResultDiffBean diffBean1=ResultDiffAnalysis.getInstance().getXapianResultDiff(resultBaseList,resultList,resultBaseIndex,resultIndex);
			model.addAttribute("diffBean1",diffBean1);
			TimeResultDiffBean diffBean2=ResultDiffAnalysis.getInstance().getXapianResultDiff(resultList,resultLableList,resultIndex,resultLabelIndex);
			model.addAttribute("diffBean2",diffBean2); 
			diffBean2.setCDFStr(diffBean2.getCDFStr().replace("干扰下","隔离后"));
			model.addAttribute("CDFStrs",diffBean1.getBaseCDFStr()+","+diffBean1.getCDFStr()+","+diffBean2.getCDFStr()); 
			model.addAttribute("appName",appName);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "demo_riscv_latency";
	}
	@RequestMapping("/getRiscvRedisResult.do")
	public String getRiscvRedisResult(HttpServletRequest request,HttpServletResponse response,Model model,
			@RequestParam(value="type",required=true) String type){
		List<TwoTuple<Long,Integer>> resultBaseList=new ArrayList<TwoTuple<Long,Integer>>();
		List<TwoTuple<Long,Integer>> resultList=new ArrayList<TwoTuple<Long,Integer>>();
		List<TwoTuple<Long,Integer>> resultLableList=new ArrayList<TwoTuple<Long,Integer>>();

		try{
			Properties prop = new Properties();
			InputStream is = WebServerJobImpl.class.getResourceAsStream("/conf/sys.properties");
			try {
				prop.load(is);
			} catch (IOException e) { 
				e.printStackTrace();
			}
			String riscvDataPath=prop.getProperty("riscv_redis_data_path").trim();
			File file=new File(riscvDataPath+type+"1.lats");
			if(file.exists()){
				resultBaseList=service.getRiscvRedisResult(riscvDataPath+type+"1.lats"); 
			}
			file=new File(riscvDataPath+type+"2.lats");
			if(file.exists()){
				resultList=service.getRiscvRedisResult(riscvDataPath+type+"2.lats"); 
			}
			file=new File(riscvDataPath+type+"3.lats");
			if(file.exists()){
				resultLableList=service.getRiscvRedisResult(riscvDataPath+type+"3.lats"); 
			}

			file=new File(riscvDataPath+type+".qps");
			if(file.exists()){
				String qps=ReadFile.getInstance().readRedisQpsFile(riscvDataPath+type+".qps");
				model.addAttribute("qps",qps);
			}
			/*
			 * 判断是否缺少数据 (基准和正式都要有才可以进行差异值计算)
			 */
			String appName="redis";

			//计算基准数据和正式数据的各项指标
			Repository.EAThreshold=Float.MAX_VALUE;
			TimeResultBean resultBaseIndex= AdapterForResult.adapter(appName,resultBaseList); 
			Repository.EAThreshold=resultBaseIndex.getNintyNineTh();
			TimeResultBean resultIndex=AdapterForResult.adapter(appName,resultList);
			TimeResultBean resultLabelIndex=AdapterForResult.adapter(appName,resultLableList);
			model.addAttribute("BaseResult",resultBaseIndex);
			model.addAttribute("Result",resultIndex);
			model.addAttribute("LabelResult",resultLabelIndex);
			//计算各项指标差异值 
			TimeResultDiffBean diffBean1=ResultDiffAnalysis.getInstance().getResultDiff(resultBaseList,resultList,resultBaseIndex,resultIndex);
			model.addAttribute("diffBean1",diffBean1);
			TimeResultDiffBean diffBean2=ResultDiffAnalysis.getInstance().getResultDiff(resultList,resultLableList,resultIndex,resultLabelIndex);
			model.addAttribute("diffBean2",diffBean2); 
			diffBean2.setCDFStr(diffBean2.getCDFStr().replace("干扰下","隔离后"));
			model.addAttribute("CDFStrs",diffBean1.getBaseCDFStr()+","+diffBean1.getCDFStr()+","+diffBean2.getCDFStr()); 
			model.addAttribute("appName",appName+type);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "demo_riscv_latency";
	}
	private ReadRiscvFiles read=new ReadRiscvFilesImpl();
	@RequestMapping("/goRiscvUsage.do")
	public String goRiscvUsage(HttpServletRequest request,HttpServletResponse response,Model model,
			@RequestParam(value="riscvId",required=true) int riscvId){

		Properties prop = new Properties();
		InputStream is = WebServerJobImpl.class.getResourceAsStream("/conf/sys.properties");
		try {
			prop.load(is);
		} catch (IOException e) { 
			e.printStackTrace();
		} 
		/**
		 * mem
		 */
		Long curTime=System.currentTimeMillis();
		List<Double> dataList=new ArrayList<Double>();
		while(dataList.size()<60){//小于60个点 需要睡眠等待
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dataList=read.read60(prop.getProperty("riscv_monitor_path").trim()+"mem_"+riscvId+".csv");
		}

		StringBuffer strName=new StringBuffer();
		StringBuffer strData=new StringBuffer();
		StringBuffer HSeries=new StringBuffer();
		strName.append("{name:'memUsage',type:'area',"); 
		strData.append("data:[");		
		for(int i=59;i>0;i--){
			strData.append("[").append(curTime-i*1000).append(",").append(dataList.get(59-i)*100).append("],");
		}
		strData.append("[").append(curTime).append(",").append(dataList.get(59)*100).append("]]");
		HSeries.append(strName).append(strData).append(",marker: {enabled: false}}");
		model.addAttribute("memStr",HSeries.toString()); 

		/**
		 * cpu
		 */
		curTime=System.currentTimeMillis();
		dataList=new ArrayList<Double>();
		while(dataList.size()<60){//小于60个点 需要睡眠等待
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dataList=read.read60(prop.getProperty("riscv_monitor_path").trim()+"cpu_usage_"+riscvId+".csv");
		}
		strName.setLength(0);
		strData.setLength(0);
		HSeries.setLength(0);
		strName.append("{name:'cpuUsage',type:'area',"); 
		strData.append("data:[");		
		for(int i=59;i>0;i--){
			strData.append("[").append(curTime-i*1000).append(",").append(dataList.get(59-i)*100).append("],");
		}
		strData.append("[").append(curTime).append(",").append(dataList.get(59)*100).append("]]");
		HSeries.append(strName).append(strData).append(",marker: {enabled: false}}");
		model.addAttribute("cpuStr",HSeries.toString()); 
		/**
		 * llc and mem bandwidth
		 */
		ArrayList<RiscvLLCGroup> dataList2=new ArrayList<RiscvLLCGroup>();
		while(dataList2.size()<60){//小于60个点 需要睡眠等待
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dataList2=read.readLLC(prop.getProperty("riscv_monitor_path").trim()+"llc_mb_"+riscvId+".csv",true);
		}

		Map<Integer,RiscvLLCPOJO> map=new HashMap<Integer,RiscvLLCPOJO>();
		List<RiscvLLCPOJO> AvgList=new ArrayList<RiscvLLCPOJO>(); 
		double missRate=0;
		double bandwidth=0;
		for(int i=0;i<dataList.size();i++){
			map=dataList2.get(i).getMap();
			Set<Integer> keySet=map.keySet();
			missRate=0;
			bandwidth=0;
			for(Integer j:keySet){
				missRate+=map.get(j).getMissesPercent();
				bandwidth+=map.get(j).getBandwidth();
			}
			RiscvLLCPOJO bean=new RiscvLLCPOJO();
			bean.setBandwidth(bandwidth/map.size());
			bean.setLlcMisses(missRate/map.size()); 
			AvgList.add(bean);
		}
		curTime=System.currentTimeMillis();
		strName.setLength(0);
		strData.setLength(0);
		HSeries.setLength(0);
		strName.append("{name:'llcMissRate',type:'area',"); 
		strData.append("data:[");		
		for(int i=59;i>0;i--){
			strData.append("[").append(curTime-i*1000).append(",").append(AvgList.get(59-i).getLlcMisses()).append("],");
		}
		strData.append("[").append(curTime).append(",").append(AvgList.get(59).getLlcMisses()).append("]]");
		HSeries.append(strName).append(strData).append(",marker: {enabled: false}}");
		model.addAttribute("llcStr",HSeries.toString()); 
		//内存带宽
		strName.setLength(0);
		strData.setLength(0);
		HSeries.setLength(0);
		strName.append("{name:'memBandWidth',type:'area',"); 
		strData.append("data:[");		
		for(int i=59;i>0;i--){
			strData.append("[").append(curTime-i*1000).append(",").append(AvgList.get(59-i).getBandwidth()).append("],");
		}
		strData.append("[").append(curTime).append(",").append(AvgList.get(59).getBandwidth()).append("]]");
		HSeries.append(strName).append(strData).append(",marker: {enabled: false}}");
		model.addAttribute("memBDStr",HSeries.toString()); 

		model.addAttribute("riscvId",riscvId);
		return "demo_riscv_monitor";
	}
	@RequestMapping("/getRiscvMemUsage.do")
	public void getRiscvMemUsage(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="riscvId",required=true) int riscvId){
		try {
			Properties prop = new Properties();
			InputStream is = WebServerJobImpl.class.getResourceAsStream("/conf/sys.properties");
			try {
				prop.load(is);
			} catch (IOException e) { 
				e.printStackTrace();
			} 
			SystemResourceUsageBean bean=new SystemResourceUsageBean();
			bean.setCollectTime(System.currentTimeMillis());
			bean.setMemUsageRate((float)read.readRiscvMemory(prop.getProperty("riscv_monitor_path").trim()+"mem_"+riscvId+".csv")*100);

			response.getWriter().write(JSONArray.fromObject(bean).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("/getRiscvCpuUsage.do")
	public void getRiscvCpuUsage(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="riscvId",required=true) int riscvId){
		try {
			Properties prop = new Properties();
			InputStream is = WebServerJobImpl.class.getResourceAsStream("/conf/sys.properties");
			try {
				prop.load(is);
			} catch (IOException e) { 
				e.printStackTrace();
			} 
			SystemResourceUsageBean bean=new SystemResourceUsageBean();
			bean.setCollectTime(System.currentTimeMillis());
			bean.setCpuUsageRate((float)read.readRiscvMemory(prop.getProperty("riscv_monitor_path").trim()+"cpu_usage_"+riscvId+".csv")*100);

			response.getWriter().write(JSONArray.fromObject(bean).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@RequestMapping("/getRiscvLLCdRAM.do")
	public void getRiscvLLCdRAM(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="riscvId",required=true) int riscvId){
		try {
			Properties prop = new Properties();
			InputStream is = WebServerJobImpl.class.getResourceAsStream("/conf/sys.properties");
			try {
				prop.load(is);
			} catch (IOException e) { 
				e.printStackTrace();
			} 
			ArrayList<RiscvLLCGroup> list=read.readLLC(prop.getProperty("riscv_monitor_path").trim()+"llc_mb_"+riscvId+".csv",false);
			Map<Integer, RiscvLLCPOJO> map=list.get(0).getMap();
			Set<Integer> keySet=map.keySet();
			double missRate=0;
			double bandwidth=0;
			for(Integer i:keySet){
				missRate+=map.get(i).getMissesPercent();
				bandwidth+=map.get(i).getBandwidth();
			}
			RiscvLLCPOJO bean=new RiscvLLCPOJO();
			bean.setBandwidth(bandwidth/map.size());
			bean.setLlcMisses(missRate/map.size()); 
			bean.setCollectTime(System.currentTimeMillis());

			response.getWriter().write(JSONArray.fromObject(bean).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}

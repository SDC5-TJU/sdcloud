package scs.controller.jobSchedul;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
 
import net.sf.json.JSONArray;
import scs.pojo.AppConfigBean;
import scs.pojo.SiloDataBean;
import scs.pojo.SystemResourceUsageBean;
import scs.pojo.TestRecordBean;
import scs.pojo.TimeResultBean;
import scs.pojo.TimeResultDiffBean;
import scs.pojo.TwoTuple;
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
			//jane
			TimeResultBean memBaseResult=AdapterForResult.adapter("memcached", Repository.memcachedBaseDataList);
//		
//			memBaseResult.setNintyTh(326);
//			memBaseResult.setNintyFiveTh(388);
//			memBaseResult.setNintyNineTh(466);
//			memBaseResult.setMin((float)26.0);
//			memBaseResult.setMax((float)688.0);
//			memBaseResult.setMean((float)134.0);
//			memBaseResult.setVar((float)1213.0);
//			memBaseResult.setMissRate((float)10.0);
//			memBaseResult.setGetSetRate(0.8f); 
//			memBaseResult.setRps(200000);
//			
			//jane
			TimeResultBean memResult = AdapterForResult.adapter("memcached",Repository.memcachedDataList);//new TimeResultBean();
//			  
//			memResult.setNintyTh(526);
//			memResult.setNintyFiveTh(588);
//			memResult.setNintyNineTh(866);
//			memResult.setMin((float)23.0);
//			memResult.setMax((float)888.0);
//			memResult.setMean((float)234.0);
//			memResult.setVar((float)2484.0);
//			memResult.setMissRate((float)10.0);
//			memResult.setGetSetRate(0.8f); 
//			memResult.setRps(200000);
//			
//			model.addAttribute("MemcachedResult",memResult);
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
			TimeResultBean webServerBaseResult= AdapterForResult.adapter("webServer",Repository.webServerBaseDataList);//new TimeResultBean();
			List<TwoTuple<Float,Float>> baseCdfList=new ArrayList<TwoTuple<Float,Float>>();
			for(int i=0;i<=10;i++){
				baseCdfList.add(new TwoTuple<Float,Float>((float)i,(float)(i*0.1)));
			} 
//			webServerBaseResult.setCDF(baseCdfList);
//			webServerBaseResult.setNintyTh(326);
//			webServerBaseResult.setNintyFiveTh(388);
//			webServerBaseResult.setNintyNineTh(466);
//			webServerBaseResult.setMin((float)26.0);
//			webServerBaseResult.setMax((float)688.0);
//			webServerBaseResult.setMean((float)134.0);
//			webServerBaseResult.setVar((float)1213.0);
//			webServerBaseResult.setMissRate((float)10.0);
//			
			TimeResultBean webServerResult=AdapterForResult.adapter("webServer",Repository.webServerDataList);
			List<TwoTuple<Float,Float>> cdfList=new ArrayList<TwoTuple<Float,Float>>();
			for(int i=0;i<=10;i++){
				cdfList.add(new TwoTuple<Float,Float>((float)i,(float)(i*i*0.01)));
			} 
//			webServerResult.setCDF(cdfList);
//			webServerResult.setNintyTh(526);
//			webServerResult.setNintyFiveTh(588);
//			webServerResult.setNintyNineTh(866);
//			webServerResult.setMin((float)23.0);
//			webServerResult.setMax((float)888.0);
//			webServerResult.setMean((float)234.0);
//			webServerResult.setVar((float)2484.0);
//			webServerResult.setMissRate((float)10.0);
//			
			model.addAttribute("webServerResult",webServerResult);
			model.addAttribute("webServerBaseResult",webServerBaseResult);
			Repository.webServerBaseDataList.clear();
			Repository.webServerDataList.clear();
			for(int i=0;i<100;i++){
				Repository.webServerBaseDataList.add(new TwoTuple<Long,Integer>(System.currentTimeMillis(),new Random().nextInt(200)));
				Repository.webServerDataList.add(new TwoTuple<Long,Integer>(System.currentTimeMillis(),new Random().nextInt(800)));
				Thread.sleep(10);
			} 
			Thread.sleep(10);
			Repository.webServerBaseDataList.add(new TwoTuple<Long,Integer>(System.currentTimeMillis(),65535));
			Repository.webServerDataList.add(new TwoTuple<Long,Integer>(System.currentTimeMillis(),65535));
			 
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
			TimeResultBean webSearchBaseResult=AdapterForResult.adapter("webSearch",Repository.webSearchBaseDataList);//new TimeResultBean();
			List<TwoTuple<Float,Float>> baseCdfList=new ArrayList<TwoTuple<Float,Float>>();
			for(int i=0;i<=10;i++){
				baseCdfList.add(new TwoTuple<Float,Float>((float)i,(float)(i*0.1)));
			} 
//			webSearchBaseResult.setCDF(baseCdfList);
//			webSearchBaseResult.setNintyTh(326);
//			webSearchBaseResult.setNintyFiveTh(388);
//			webSearchBaseResult.setNintyNineTh(466);
//			webSearchBaseResult.setMin((float)26.0);
//			webSearchBaseResult.setMax((float)688.0);
//			webSearchBaseResult.setMean((float)134.0);
//			webSearchBaseResult.setVar((float)1213.0);
//			webSearchBaseResult.setMissRate((float)10.0);
//			
			TimeResultBean webSearchResult=AdapterForResult.adapter("webSearch",Repository.webSearchDataList);//new TimeResultBean();
			List<TwoTuple<Float,Float>> cdfList=new ArrayList<TwoTuple<Float,Float>>();
			for(int i=0;i<=10;i++){
				cdfList.add(new TwoTuple<Float,Float>((float)i,(float)(i*i*0.01)));
			} 
//			webSearchResult.setCDF(cdfList);
//			webSearchResult.setNintyTh(526);
//			webSearchResult.setNintyFiveTh(588);
//			webSearchResult.setNintyNineTh(866);
//			webSearchResult.setMin((float)23.0);
//			webSearchResult.setMax((float)888.0);
//			webSearchResult.setMean((float)234.0);
//			webSearchResult.setVar((float)2484.0);
//			webSearchResult.setMissRate((float)10.0);
//			
			model.addAttribute("webSearchResult",webSearchResult);
			model.addAttribute("webSearchBaseResult",webSearchBaseResult);
			Repository.webSearchBaseDataList.clear();
			Repository.webSearchDataList.clear();
			for(int i=0;i<100;i++){
				Repository.webSearchBaseDataList.add(new TwoTuple<Long,Integer>(System.currentTimeMillis(),new Random().nextInt(200)));
				Repository.webSearchDataList.add(new TwoTuple<Long,Integer>(System.currentTimeMillis(),new Random().nextInt(800)));
				Thread.sleep(10);
			} 
			Thread.sleep(10);
			Repository.webSearchBaseDataList.add(new TwoTuple<Long,Integer>(System.currentTimeMillis(),65535));
			Repository.webSearchDataList.add(new TwoTuple<Long,Integer>(System.currentTimeMillis(),65535));
			 
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
	@RequestMapping("/getSiloResult.do")
	public String getSiloResult(HttpServletRequest request,HttpServletResponse response,Model model,
			@RequestParam(value="testRecordId",required=true) int testRecordId){
		try{ 
			TimeResultBean siloBaseResult= AdapterForResult.adapter("silo",Repository.siloBaseDataList);//new TimeResultBean();
			List<TwoTuple<Float,Float>> baseCdfList=new ArrayList<TwoTuple<Float,Float>>();
			for(int i=0;i<=10;i++){
				baseCdfList.add(new TwoTuple<Float,Float>((float)i,(float)(i*0.1)));
			} 
//			siloBaseResult.setCDF(baseCdfList);
//			siloBaseResult.setNintyTh(326);
//			siloBaseResult.setNintyFiveTh(388);
//			siloBaseResult.setNintyNineTh(466);
//			siloBaseResult.setMin((float)26.0);
//			siloBaseResult.setMax((float)688.0);
//			siloBaseResult.setMean((float)134.0);
//			siloBaseResult.setVar((float)1213.0);
//			siloBaseResult.setMissRate((float)10.0);
//			
			TimeResultBean siloResult= AdapterForResult.adapter("silo",Repository.siloDataList);//new TimeResultBean();
			List<TwoTuple<Float,Float>> cdfList=new ArrayList<TwoTuple<Float,Float>>();
			for(int i=0;i<=10;i++){
				cdfList.add(new TwoTuple<Float,Float>((float)i,(float)(i*i*0.01)));
			} 
//			siloResult.setCDF(cdfList);
//			siloResult.setNintyTh(526);
//			siloResult.setNintyFiveTh(588);
//			siloResult.setNintyNineTh(866);
//			siloResult.setMin((float)23.0);
//			siloResult.setMax((float)888.0);
//			siloResult.setMean((float)234.0);
//			siloResult.setVar((float)2484.0);
//			siloResult.setMissRate((float)10.0);
//			
			model.addAttribute("siloResult",siloResult);
			model.addAttribute("siloBaseResult",siloBaseResult);
			Repository.siloBaseDataList.clear();
			Repository.siloDataList.clear();
			for(int i=0;i<100;i++){
				Repository.siloBaseDataList.add(new SiloDataBean(233f,new Random().nextInt(500),255f));
				Repository.siloDataList.add(new SiloDataBean(233f,new Random().nextInt(1000),255f));
				Thread.sleep(10);
			} 
			Thread.sleep(10);
			Repository.siloBaseDataList.add(new SiloDataBean(233f,new Random().nextInt(500),255f));
			Repository.siloDataList.add(new SiloDataBean(233f,new Random().nextInt(1000),255f));
			
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

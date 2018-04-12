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
import scs.pojo.SystemResourceUsageBean;
import scs.pojo.TestRecordBean;
import scs.pojo.TimeResultBean;
import scs.pojo.TwoTuple;
import scs.service.appConfig.AppConfigService;
import scs.service.jobSchedul.JobSchedulService;
import scs.service.recordManage.RecordManageService; 

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
	public void getMemcachedResult(HttpServletRequest request,HttpServletResponse response,Model model){
		try{ 
			String resultStr=service.getAppStatus();
			response.getWriter().print(resultStr);  
		}catch(Exception e){
			logger.error("add Operator error"+e);
			e.printStackTrace();
		}
	}
	@RequestMapping("/getWebServerResult.do")
	public void getWebServerResult(HttpServletRequest request,HttpServletResponse response,Model model){
		try{ 
			TimeResultBean webServerBaseResult=new TimeResultBean();
			List<TwoTuple<Float,Float>> cdfList=new ArrayList<TwoTuple<Float,Float>>();
			for(int i=0;i<10;i++){
				cdfList.add(new TwoTuple<Float,Float>((float)i,(float)(i*0.1)));
			} 
			webServerBaseResult.setCDF(cdfList);
			webServerBaseResult.setNintyth(326);
			webServerBaseResult.setNintyFiveTh(388);
			webServerBaseResult.setNintyNineTh(466);
			webServerBaseResult.setMin((float)26.0);
			webServerBaseResult.setMax((float)688.0);
			webServerBaseResult.setMean((float)134.0);
			webServerBaseResult.setVar((float)1213.0);
			
			TimeResultBean webServerResult=new TimeResultBean();
			cdfList.clear();
			for(int i=0;i<10;i++){
				cdfList.add(new TwoTuple<Float,Float>((float)i,(float)(i*i*0.01)));
			} 
			webServerResult.setCDF(cdfList);
			webServerResult.setNintyth(526);
			webServerResult.setNintyFiveTh(588);
			webServerResult.setNintyNineTh(866);
			webServerResult.setMin((float)23.0);
			webServerResult.setMax((float)888.0);
			webServerResult.setMean((float)234.0);
			webServerResult.setVar((float)2484.0);
			
			
			
			String resultStr=service.getAppStatus();
			response.getWriter().print(resultStr);  
		}catch(Exception e){
			logger.error("add Operator error"+e);
			e.printStackTrace();
		}
	}
	@RequestMapping("/getWebSearchResult.do")
	public void getWebSearchResult(HttpServletRequest request,HttpServletResponse response,Model model){
		try{ 
			String resultStr=service.getAppStatus();
			response.getWriter().print(resultStr);  
		}catch(Exception e){
			logger.error("add Operator error"+e);
			e.printStackTrace();
		}
	}
	@RequestMapping("/getSiloResult.do")
	public void getSiloResult(HttpServletRequest request,HttpServletResponse response,Model model){
		try{ 
			String resultStr=service.getAppStatus();
			response.getWriter().print(resultStr);  
			model.addAttribute("bonnie",resultStr);
		}catch(Exception e){
			logger.error("add Operator error"+e);
			e.printStackTrace();
		}
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
 
	@RequestMapping("/getCpuUseRate.do")
	public void getCpuUseRate(HttpServletRequest request,HttpServletResponse response,Model model){
		try{  
			response.getWriter().print(rand.nextInt(100));   
		}catch(Exception e){
			logger.error("add Operator error"+e);
			e.printStackTrace();
		}
	}
	@RequestMapping("/getMemUseRate.do")
	public void getMemUseRate(HttpServletRequest request,HttpServletResponse response,Model model){
		try{ 
			 
			response.getWriter().print(rand.nextInt(100));   
		}catch(Exception e){
			logger.error("add Operator error"+e);
			e.printStackTrace();
		}
	}
	@RequestMapping("/getNetUseRate.do")
	public void getNetUseRate(HttpServletRequest request,HttpServletResponse response,Model model){
		try{ 
			 
			response.getWriter().print(rand.nextInt(100));   
		}catch(Exception e){
			logger.error("add Operator error"+e);
			e.printStackTrace();
		}
	}
	@RequestMapping("/getIoUseRate.do")
	public void getIoUseRate(HttpServletRequest request,HttpServletResponse response,Model model){
		try{ 
			 
			response.getWriter().print(rand.nextInt(100));   
		}catch(Exception e){
			logger.error("add Operator error"+e);
			e.printStackTrace();
		}
	}
	@RequestMapping("/getPhyResourceUse.do")
	public void getPhyResourceUse(HttpServletRequest request,HttpServletResponse response,Model model){
		try{ 
			SystemResourceUsageBean bean=new SystemResourceUsageBean();
			bean.setCollectTime(System.currentTimeMillis()); 
			bean.setCpuUsageRate((float) (rand.nextInt(100)));
			bean.setMemUsageRate((float) rand.nextInt(100));
			bean.setIoUsageRate((float) rand.nextInt(100));
			bean.setNetUsageRate((float) rand.nextInt(100));
			
			response.getWriter().print(JSONArray.fromObject(bean));    
		}catch(Exception e){
			logger.error("add Operator error"+e);
			e.printStackTrace();
		}
	}
	@RequestMapping("/test.do")
	public String test(HttpServletRequest request,HttpServletResponse response,Model model){
		try{
			/**
			 * 图1 solrCloud
			 */
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

			model.addAttribute("solrCloud",HSeries.toString());
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
			model.addAttribute("webServer",HSeries.toString());

			/**
			 *   图3 cpu
			 */
			strName.setLength(0);
			strData.setLength(0);
			HSeries.setLength(0);
			strName.append("{name:'cpu',type:'area',");//type: 'area',
			strData.append("data:[");
			double usedRate=rand.nextInt(100);
			for(int i=0;i<59;i++){
				strData.append("[").append(System.currentTimeMillis()).append(",").append(usedRate).append("],");
			}
			strData.append("[").append(System.currentTimeMillis()).append(",").append(usedRate).append("]]");
			HSeries.append(strName).append(strData).append(",marker: {enabled: false}}");
			model.addAttribute("cpu",HSeries.toString());
			/**
			 * 图 4 memory
			 */
			strName.setLength(0);
			strData.setLength(0);
			HSeries.setLength(0);
			strName.append("{name:'memory',type:'area',");//type: 'area',
			strData.append("data:[");
			usedRate=rand.nextInt(100);
			for(int i=0;i<59;i++){
				strData.append("[").append(System.currentTimeMillis()).append(",").append(usedRate).append("],");
			}
			strData.append("[").append(System.currentTimeMillis()).append(",").append(usedRate).append("]]");
			HSeries.append(strName).append(strData).append(",marker: {enabled: false}}");
			model.addAttribute("memory",HSeries.toString());
			/**
			 *   图5 IO
			 */
			strName.setLength(0);
			strData.setLength(0);
			HSeries.setLength(0);
			strName.append("{name:'io',type:'area',");//type: 'area',
			strData.append("data:[");
			usedRate=rand.nextInt(100);
			for(int i=0;i<59;i++){
				strData.append("[").append(System.currentTimeMillis()).append(",").append(usedRate).append("],");
			}
			strData.append("[").append(System.currentTimeMillis()).append(",").append(usedRate).append("]]");
			HSeries.append(strName).append(strData).append(",marker: {enabled: false}}");
			model.addAttribute("io",HSeries.toString());
			/**
			 *   图6 net
			 */
			strName.setLength(0);
			strData.setLength(0);
			HSeries.setLength(0);
			strName.append("{name:'net',type:'area',");//type: 'area',
			strData.append("data:[");
			usedRate=rand.nextInt(100);
			for(int i=0;i<59;i++){
				strData.append("[").append(System.currentTimeMillis()).append(",").append(usedRate).append("],");
			}
			strData.append("[").append(System.currentTimeMillis()).append(",").append(usedRate).append("]]");
			HSeries.append(strName).append(strData).append(",marker: {enabled: false}}");
			model.addAttribute("net",HSeries.toString());
			/**
			 * cpu使用柱状图
			 */
			/*strName.setLength(0);
			strData.setLength(0);
			HSeries.setLength(0);
			sclist=Sysinformation.cpuBase();
			int size=sclist.size();
			HSeries.append("{name:'未使用',color:'#90ed7d',data:[");
			for(int i=0;i<size-1;i++){
				HSeries.append(sclist.get(i).getIdle()).append(",");
			}
			HSeries.append(sclist.get(size-1).getIdle()).append("]},");
			HSeries.append("{name:'应用使用',color:'#f7a35c',data:[");
			for(int i=0;i<size-1;i++){
				HSeries.append(sclist.get(i).getUser()).append(",");
			}
			HSeries.append(sclist.get(size-1).getUser()).append("]},");
			HSeries.append("{name:'系统使用',color:'#8085e8',data:[");
			for(int i=0;i<size-1;i++){
				HSeries.append(sclist.get(i).getSys()).append(",");
			}
			HSeries.append(sclist.get(size-1).getSys()).append("]}");
			model.addAttribute("cpuColumn",HSeries.toString());
	 
			strName.setLength(0);
			strData.setLength(0);
			HSeries.setLength(0);
			for(int i=0;i<size-1;i++){
				HSeries.append("'cpu").append(i+1).append("',");
			}
			HSeries.append("'cpu").append(size).append("'");
			model.addAttribute("cpuAxis",HSeries.toString());
			*/
		}catch(Exception e){
			logger.error("login error"+e);
			e.printStackTrace();
		}
		return "test";
	}

}

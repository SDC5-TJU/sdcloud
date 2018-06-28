package scs.controller.jobSchedul;
  
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.sf.json.JSONArray;  
import scs.util.loadGen.loadDriver.WebSearchDriver;
import scs.util.loadGen.recordDriver.RecordDriver;
import scs.util.repository.Repository; 
/**
 * 应用执行控制类
 * 用于控制不同的应用开启和关闭
 * @author  
 *
 */
@Controller
public class HeraclesOnlineController { 
  
	/**
	 * 开启Heracles在线查询负载
	 * @param request
	 * @param response
	 * @param intensity 每秒钟请求数
	 */
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
	/**
	 * 中途设置Heracles在线服务的负载QPS
	 * @param request
	 * @param response
	 * @param intensity 每秒查询次数
	 */
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
	/**
	 * 停止Heracles在线服务的负载
	 * @param request
	 * @param response
	 */
	@RequestMapping("/stopOnlineQuery.do")
	public void stopOnlineQuery(HttpServletRequest request,HttpServletResponse response){
		try{
			Repository.onlineDataFlag=false; 
			System.out.println("stop");  

		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 进入在线服务实时响应时间曲线的页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
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

	/**
	 * 获取最新请求的延迟时间
	 * 页面ajax请求 返回json格式
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getOnlineQueryTime.do")
	public void getOnlineQueryTime(HttpServletRequest request,HttpServletResponse response){
		try{
			response.getWriter().write(JSONArray.fromObject(Repository.latestOnlineData).toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}


}

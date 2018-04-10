package scs.controller.containerDeploy;

import org.apache.log4j.Logger;
 
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest; 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
 
import scs.pojo.ContainerInfoBean; 
import scs.service.containerDeploy.ContainerDeployService; 

/**
 * 容器部署管理类
 * @author  
 *
 */
@Controller
public class ContainerDeployController {
	private static Logger logger = Logger.getLogger(ContainerDeployController.class.getName());

	@Resource ContainerDeployService service;

	/**
	 * 查询容器部署情况
	 * @param request
	 * @param model
	 * @param page 页码
	 * @return 应用配置实体类数组
	 */
	@RequestMapping("/searchContainerDeploy.do")
	public String searchContainerDeploy(HttpServletRequest request,Model model, 
			@RequestParam(value="page",required=true) int page){
		try{
			List<ContainerInfoBean> containerDeploylist=service.searchContainerDeploy(page);
			model.addAttribute("containerDeploylist",containerDeploylist);

		}catch(Exception e){
			logger.error("add Operator error"+e);
			e.printStackTrace();
		}
		return "searchContainerDeploy";
	}
 
}

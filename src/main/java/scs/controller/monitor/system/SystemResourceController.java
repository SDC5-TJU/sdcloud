package scs.controller.monitor.system;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONArray;
import scs.controller.recordManage.RecordManageController;
import scs.pojo.TableSystemresourceusage;
import scs.service.monitor.system.SystemMonitor;
import scs.util.repository.Repository;

@Controller("sysController")
public class SystemResourceController {

	private static Logger logger = Logger.getLogger(RecordManageController.class.getName());

	@Autowired
	@Qualifier("systemMonitor")
	public SystemMonitor systemMonitor;
	

	/**
	 * @author jztong
	 * @param number
	 *            根据主机名对应的标号查找静态数组最近一次的查询信息
	 * @return 由最近一次的资源使用信息封装起来的数据对象
	 */
	@RequestMapping(value = "/getPhyResourceUse.do", method = RequestMethod.GET)
	@ResponseBody
	public String getSystemReal(@RequestParam("no") int number) {
		TableSystemresourceusage realSystemData = systemMonitor.getRealSystemResourceFromStaticData(number);
		return JSONArray.fromObject(realSystemData).toString();
	}

	/**
	 * 
	 * @return
	 * @author jztong 前端根据
	 */
	@RequestMapping("/monitor/cronFlag.do")
	@ResponseBody
	public String JsonChangeCronFlag(@RequestBody int flag) {
		// 返回操作结果
		if (flag == 1 && Repository.cronFlag == 0) {
			// 关闭采集
			Repository.cronFlag = 1;
			return "已开启";
		} else if (flag == 0 && Repository.cronFlag == 1) {
			return "已关闭";
		}
		return null;
	}

	/**
	 * @author jztong
	 * @param number
	 *            根据主机名对应的标号查找数据库最近60次的监控信息
	 * @return 拼接字符串
	 */
	@RequestMapping(value = "/phyMoniter.do", method = RequestMethod.GET)
	public String initPhyMonitor(@RequestParam("no") int number, Model model) {
		try {
			List<TableSystemresourceusage> testSelect = systemMonitor.testSelect(number);
			StringBuffer strCpuName = new StringBuffer();
			StringBuffer strCpuData = new StringBuffer();
			StringBuffer HCpuSeries = new StringBuffer();
			StringBuffer strMemName = new StringBuffer();
			StringBuffer strMemData = new StringBuffer();
			StringBuffer HMemSeries = new StringBuffer();
			StringBuffer strIoName = new StringBuffer();
			StringBuffer strIoData = new StringBuffer();
			StringBuffer HIoSeries = new StringBuffer();
			StringBuffer strNetName = new StringBuffer();
			StringBuffer strNetData = new StringBuffer();
			StringBuffer HNetSeries = new StringBuffer();
			HCpuSeries.setLength(0);
			HMemSeries.setLength(0);
			HIoSeries.setLength(0);
			HNetSeries.setLength(0);
			strCpuName.append("{name:'cpu',type:'area',");// type: 'area',
			strMemName.append("{name:'mem',type:'area',");// type: 'area',
			strIoName.append("{name:'io',type:'area',");// type: 'area',
			strNetName.append("{name:'net',type:'area',");// type: 'area',
			strCpuData.append("data:[");
			strMemData.append("data:[");
			strIoData.append("data:[");
			strNetData.append("data:[");
			for (int i = 0; i < testSelect.size(); i++) {
				float cpuusagerate = testSelect.get(i).getCpuusagerate();
				float iousagerate = testSelect.get(i).getIousagerate();
				float netusagerate = testSelect.get(i).getNetusagerate();
				float memusagerate = testSelect.get(i).getMemusagerate();
				Date collecttime = testSelect.get(i).getCollecttime();
				strCpuData.append("[").append(collecttime.getTime()).append(",").append(cpuusagerate).append("],");
				strMemData.append("[").append(collecttime.getTime()).append(",").append(memusagerate).append("],");
				strIoData.append("[").append(collecttime.getTime()).append(",").append(iousagerate).append("],");
				strNetData.append("[").append(collecttime.getTime()).append(",").append(netusagerate).append("],");
			}
			strCpuData.replace(strCpuData.length() - 1, strCpuData.length(), "]");
			strMemData.replace(strMemData.length() - 1, strMemData.length(), "]");
			strIoData.replace(strIoData.length() - 1, strIoData.length(), "]");
			strNetData.replace(strNetData.length() - 1, strNetData.length(), "]");
			HCpuSeries.append(strCpuName).append(strCpuData).append(",marker: {enabled: false}}");
			HMemSeries.append(strMemName).append(strMemData).append(",marker: {enabled: false}}");
			HIoSeries.append(strIoName).append(strIoData).append(",marker: {enabled: false}}");
			HNetSeries.append(strNetName).append(strNetData).append(",marker: {enabled: false}}");
			model.addAttribute("cpu", HCpuSeries.toString());
			model.addAttribute("memory", HMemSeries.toString());
			model.addAttribute("io", HIoSeries.toString());
			model.addAttribute("net", HNetSeries.toString());
		} catch (Exception e) {
			logger.error("login error" + e);
			e.printStackTrace();
		}
		return "physicalMoniter" + number;
	}
}

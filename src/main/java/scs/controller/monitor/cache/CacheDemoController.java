package scs.controller.monitor.cache;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONArray;
import scs.controller.recordManage.RecordManageController;
import scs.service.monitor.pqos.PqosResourceMonitor;
import scs.service.monitor.system.RMISystemMonitorInterface;
import scs.service.monitor.system.SystemMonitor;
import scs.util.repository.Repository;

@Controller("cacheDemoController")
public class CacheDemoController {

	private static Logger logger = Logger.getLogger(RecordManageController.class.getName());

	@Autowired
	@Qualifier("pqosMonitor")
	public PqosResourceMonitor pqosResourceMonitor;

	@RequestMapping(value = "/getCacheUse.do")
	@ResponseBody
	public JSONArray getCacheUse() {
		RMISystemMonitorInterface systemMonitorInterface;
		float[][] cacheData = null;
		try {
			systemMonitorInterface = (RMISystemMonitorInterface) Naming
					.lookup("rmi://192.168.1.128:33334/rmiSystemMonitor");
			cacheData = systemMonitorInterface.systemCachePercentData();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (RemoteException e1) {
			System.out.println("异常");
			logger.info(e1);
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			e1.printStackTrace();
		}
		JSONArray json = JSONArray.fromObject(cacheData);
		return json;
	}

	@RequestMapping(value = "/getPqos.do")
	@ResponseBody
	public String getPqos(@RequestParam("no") int number) {
		float[][] misses = null;
		float[] bandwidth = null;
		if (number == Repository.PhysicalMachine128) {
			misses = Repository.cache128;
			bandwidth = Repository.bandwidth128;
		} else if (number == Repository.PhysicalMachine147) {
			misses = Repository.cache147;
			bandwidth = Repository.bandwidth147;
		}

		long time = new Date().getTime();
		StringBuffer strJson = new StringBuffer();
		strJson.append(
				"{time:" + time + ",mbl:" + bandwidth[0] + ",mbr:" + bandwidth[1] + ",llc:" + misses[2][1] + "}");
		return strJson.toString();
	}

	@RequestMapping("/cacheMonitorDemo.do")
	public String getContainerMonitor() {
		return "cacheMonitorDemo";
	}
}

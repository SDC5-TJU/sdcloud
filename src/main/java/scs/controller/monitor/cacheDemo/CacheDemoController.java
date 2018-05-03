package scs.controller.monitor.cacheDemo;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONArray;
import scs.controller.recordManage.RecordManageController;
import scs.service.monitor.system.RMISystemMonitorInterface;

@Controller("cacheDemoController")
public class CacheDemoController {
	private static Logger logger = Logger.getLogger(RecordManageController.class.getName());

	@RequestMapping(value="/getCacheUse.do")
	@ResponseBody
	public JSONArray getCacheUse() {
			RMISystemMonitorInterface systemMonitorInterface;
			String[][] cacheData = null;
			try {
				systemMonitorInterface = (RMISystemMonitorInterface) Naming.lookup("rmi://" + "192.168.1.128" + ":33334/rmiSystemMonitor");
				cacheData = systemMonitorInterface.cacheData();
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (RemoteException e1) {
				System.out.println("异常");
				// TODO Auto-generated catch block
				logger.info(e1);
				e1.printStackTrace();
			} catch (NotBoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JSONArray json = JSONArray.fromObject(cacheData);
			return json;
	}
	
	@RequestMapping("/cacheMonitorDemo.do")
	public String getContainerMonitor(){
		return "cacheMonitorDemo";
	}
}

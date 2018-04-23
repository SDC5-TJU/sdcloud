package scs.controller.monitor.app;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONArray;
import scs.controller.recordManage.RecordManageController;
import scs.pojo.TableAppresourceusage;
import scs.pojo.TableContainerresourceusage;
import scs.service.monitor.app.AppMonitor;
import scs.service.monitor.containers.ContainerMonitor;
import scs.util.repository.Repository;

@Controller("appController")
public class AppController {
	private static Logger logger = Logger.getLogger(RecordManageController.class.getName());

	@Autowired
	@Qualifier("containerMonitor")
	public AppMonitor appMonitor;

	@RequestMapping(value="/getAppResourceUse.do")
	@ResponseBody
	public JSONArray getContainerReal() {
		try {
			Map<String, TableAppresourceusage> appRealUsageMap = Repository.appRealUsageMap;

			Set<Entry<String, TableAppresourceusage>> entrySet = appRealUsageMap.entrySet();
			Iterator<Entry<String, TableAppresourceusage>> iterator = entrySet.iterator();
			ArrayList<TableAppresourceusage> arrayList = new ArrayList<>();
			while (iterator.hasNext()) {
				arrayList.add(iterator.next().getValue());
			}
			JSONArray json = JSONArray.fromObject(arrayList);
			return json;
		} catch (Exception e) {
			logger.error("login error" + e);
			e.printStackTrace();
		}

		return null;
	}
	
	@RequestMapping("/appMonitor.do")
	public String getContainerMonitor(){
		return "appMonitor";
	}
}

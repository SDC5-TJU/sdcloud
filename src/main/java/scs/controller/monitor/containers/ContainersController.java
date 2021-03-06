package scs.controller.monitor.containers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONArray;
import scs.controller.recordManage.RecordManageController;
import scs.pojo.TableContainerresourceusage;
import scs.service.monitor.containers.ContainerMonitor;
import scs.util.format.DataFormats;
import scs.util.repository.Repository;

@Controller("containerController")
public class ContainersController {

	private static Logger logger = Logger.getLogger(RecordManageController.class.getName());

	@Autowired
	@Qualifier("containerMonitor")
	public ContainerMonitor containerMonitor;

	@RequestMapping(value="/getContainerResourceUse.do")
	@ResponseBody
	public JSONArray getContainerReal() {
		try {
			Map<String, TableContainerresourceusage> containerRealUsageMap = Repository.containerRealUsageMap;

			Set<Entry<String, TableContainerresourceusage>> entrySet = containerRealUsageMap.entrySet();
			Iterator<Entry<String, TableContainerresourceusage>> iterator = entrySet.iterator();
			ArrayList<TableContainerresourceusage> arrayList = new ArrayList<>();
			while (iterator.hasNext()) {
				TableContainerresourceusage value = iterator.next().getValue();
				value.setCpuusagerate(DataFormats.getInstance().subFloat(value.getCpuusagerate(),2));
				value.setMemusagerate(DataFormats.getInstance().subFloat(value.getMemusagerate(),2));
				arrayList.add(value);
			}
			JSONArray json = JSONArray.fromObject(arrayList);
			return json;
		} catch (Exception e) {
			logger.error("login error" + e);
			e.printStackTrace();
		}

		return null;
	}
	
	@RequestMapping("/containerMonitor.do")
	public String getContainerMonitor(){
		return "containerMonitor";
	}
	
}

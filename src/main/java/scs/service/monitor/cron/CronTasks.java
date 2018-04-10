package scs.service.monitor.cron;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import scs.pojo.TableAppresourceusage;
import scs.pojo.TableContainerresourceusage;
import scs.service.monitor.app.AppMonitor;
import scs.service.monitor.containers.ContainerMonitor;
import scs.util.repository.Repository;

@Service
public class CronTasks {
	
	@Autowired
	@Qualifier("containerMonitor")
	public ContainerMonitor containerMonitor;
	
	@Autowired
	@Qualifier("appMonitor")
	public AppMonitor appMonitor;
	
	
	
	public void testCron() {
		Map<String, List<String>> appNames = appMonitor.getAPPName(Repository.appInfoMap);
		if (Repository.cronFlag != 1) {
			System.out.println("停止");
			return;
		}
		String[] hosts = {"192.168.1.128","192.168.1.147"};
		String hostname = "192.168.1.128";
		String username = "tank";
		String password = "tanklab";
		int len = hosts.length;
		ArrayList<TableContainerresourceusage> combineList = new ArrayList<>();
		for (int i = 0; i < len; i++) {
			hostname = hosts[i];
			InputStream containerInfoStream = containerMonitor.getContainerInfoStream(hostname, username, password);
			ArrayList<TableContainerresourceusage> containersPOJO = containerMonitor.getContainersPOJO(containerInfoStream);
			System.out.println(containersPOJO.size());
			combineList.addAll(containersPOJO);
			System.out.println(combineList.size());
			containerMonitor.testInsert(containersPOJO);
			Iterator<TableContainerresourceusage> iterator = containersPOJO.iterator();
			// 添加进全局 containerRealUsageMap 变量
			while (iterator.hasNext()) {
				TableContainerresourceusage tableContainerresourceusage = (TableContainerresourceusage) iterator.next();
				Repository.containerRealUsageMap.put(tableContainerresourceusage.getContainername(), tableContainerresourceusage);
			}
		}
		System.out.println(combineList.size());
		//统计
		
		Map<String, TableAppresourceusage> aggregateAPPResourceUsage = appMonitor.aggregateAPP(combineList, appNames);
		appMonitor.testInsert(aggregateAPPResourceUsage);
		
		System.out.println(new Date().toString());
		Repository.cronFlag = 0;
	}
	
	
}

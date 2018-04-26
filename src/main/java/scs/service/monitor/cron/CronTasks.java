package scs.service.monitor.cron;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import scs.pojo.TableAppresourceusage;
import scs.pojo.TableContainerresourceusage;
import scs.pojo.TableSystemresourceusage;
import scs.service.monitor.app.AppMonitor;
import scs.service.monitor.containers.ContainerMonitor;
import scs.service.monitor.system.SystemMonitor;
import scs.util.repository.Repository;

@Service
public class CronTasks {

	@Autowired
	@Qualifier("containerMonitor")
	public ContainerMonitor containerMonitor;

	@Autowired
	@Qualifier("appMonitor")
	public AppMonitor appMonitor;

	@Autowired
	@Qualifier("systemMonitor")
	public SystemMonitor systemMonitor;

	public void testCron() {
		if (Repository.cronFlag != 1) {
			return;
		}
		// Repository.cronFlag = 0;
		String[] hosts = { "192.168.1.128", "192.168.1.147" };
		String hostname = "192.168.1.128";
		String username = "tank";
		String password = "tanklab";
		int len = hosts.length;
		ArrayList<TableContainerresourceusage> combineList = new ArrayList<>();
		for (int i = 0; i < len; i++) {
			hostname = hosts[i];
			long start=System.currentTimeMillis();
			InputStream containerInfoStream = containerMonitor.getContainerInfoStream(hostname, username, password);
			System.out.println((System.currentTimeMillis()-start)/1000.0);
			ArrayList<TableContainerresourceusage> containersPOJO = containerMonitor.getContainersPOJO(hostname,
					username, password, containerInfoStream);
			combineList.addAll(containersPOJO);
			containerMonitor.testInsert(containersPOJO);
			Iterator<TableContainerresourceusage> iterator = containersPOJO.iterator();
			// 添加进全局 containerRealUsageMap 变量
			while (iterator.hasNext()) {
				TableContainerresourceusage tableContainerresourceusage = (TableContainerresourceusage) iterator.next();
				// containerName：Hadoop1 container类对象最新的资源使用情况
				Repository.containerRealUsageMap.put(tableContainerresourceusage.getContainername(),
						tableContainerresourceusage);
			}
			
		}
		// 统计
		Map<String, List<String>> appNames = appMonitor.getAPPName(Repository.appInfoMap);
		Map<String, TableAppresourceusage> aggregateAPPResourceUsage = appMonitor.aggregateAPP(combineList, appNames);
		appMonitor.testInsert(aggregateAPPResourceUsage);
		// 添加进全局 appRealUsageMap 变量
		Repository.appRealUsageMap.clear();
		Repository.appRealUsageMap = aggregateAPPResourceUsage;
	}

	public void testCronSystem() {
		if (Repository.cronFlag != 1) {
			return;
		}
		// 调用systemresourceusage
		ArrayList<TableSystemresourceusage> systemDataList = systemMonitor.getSystemDataList(Repository.systemInfoMap);
		systemMonitor.testInsert(systemDataList);

		for (TableSystemresourceusage tableSystemresourceusage : systemDataList) {
			Repository.systemRealUsageMap.put(tableSystemresourceusage.getHostname(), tableSystemresourceusage);
		}
	}
}

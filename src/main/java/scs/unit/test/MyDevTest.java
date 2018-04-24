package scs.unit.test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import scs.pojo.TableAppresourceusage;
import scs.pojo.TableContainerresourceusage;
import scs.pojo.TableSystemresourceusage;
import scs.service.monitor.app.AppMonitor;
import scs.service.monitor.containers.impl.ContainerMonitorImpl;
import scs.service.monitor.system.SystemMonitor;
import scs.util.repository.Repository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:scs/unit/test/applicationContext.xml" })
public class MyDevTest {

	// @Resource(name="baseMonitor")
	// private BaseInfoService baseInfoService;
	//
	@Autowired
	@Qualifier("containerMonitor")
	private ContainerMonitorImpl containerMonitor;

	@Autowired
	@Qualifier("appMonitor")
	public AppMonitor appMonitor;

	@Autowired
	@Qualifier("systemMonitor")
	public SystemMonitor systemMonitor;

	// @Test
	// public void testBase(){
	// Map<String, Float> memStat = baseInfoService.getMemStat();
	// System.out.println(memStat.get("RAM"));
	// System.out.println(memStat.get("memUsed"));
	// }

	@Test
	public void testDb() {
		// String hostname = "192.168.1.128";
		// String username = "tank";
		// String password = "tanklab";
		// InputStream containerInfoStream =
		// containerMonitor.getContainerInfoStream(hostname, username,
		// password);
		// ArrayList<TableContainerresourceusage> containersPOJO =
		// containerMonitor.getContainersPOJO(containerInfoStream);
		// System.out.println(containerMonitor.testInsert(containersPOJO));

		String[] hosts = { "192.168.1.128", "192.168.1.147" };
		String hostname = "192.168.1.128";
		String username = "tank";
		String password = "tanklab";
		int len = hosts.length;
		ArrayList<TableContainerresourceusage> combineList = new ArrayList<>();
		for (int i = 0; i < len; i++) {
			hostname = hosts[i];
			InputStream containerInfoStream = containerMonitor.getContainerInfoStream(hostname, username, password);
			ArrayList<TableContainerresourceusage> containersPOJO = containerMonitor.getContainersPOJO(hostname,
					username, password, containerInfoStream);
			combineList.addAll(containersPOJO);
			containerMonitor.testInsert(containersPOJO);
			Iterator<TableContainerresourceusage> iterator = containersPOJO.iterator();
			// 添加进全局 containerRealUsageMap 变量
			while (iterator.hasNext()) {
				TableContainerresourceusage tableContainerresourceusage = (TableContainerresourceusage) iterator.next();
				Repository.containerRealUsageMap.put(tableContainerresourceusage.getContainername(),
						tableContainerresourceusage);
			}
		}
		System.out.println(new Date());
		// 统计
		Map<String, List<String>> appNames = appMonitor.getAPPName(Repository.appInfoMap);
		Map<String, TableAppresourceusage> aggregateAPPResourceUsage = appMonitor.aggregateAPP(combineList, appNames);
		appMonitor.testInsert(aggregateAPPResourceUsage);
		System.out.println(new Date());
	}

	@Test
	public void testCron() {
		String[] hosts = { "192.168.1.128", "192.168.1.147" };
		String hostname = "192.168.1.128";
		String username = "tank";
		String password = "tanklab";
		int len = hosts.length;
		ArrayList<TableContainerresourceusage> combineList = new ArrayList<>();
		for (int i = 0; i < len; i++) {
			hostname = hosts[i];
			InputStream containerInfoStream = containerMonitor.getContainerInfoStream(hostname, username, password);
			ArrayList<TableContainerresourceusage> containersPOJO = containerMonitor.getContainersPOJO(hostname,
					username, password, containerInfoStream);
			combineList.addAll(containersPOJO);
			//containerMonitor.testInsert(containersPOJO);
			Iterator<TableContainerresourceusage> iterator = containersPOJO.iterator();
			// 添加进全局 containerRealUsageMap 变量
//			while (iterator.hasNext()) {
//				TableContainerresourceusage tableContainerresourceusage = (TableContainerresourceusage) iterator.next();
//				// containerName：Hadoop1 container类对象最新的资源使用情况
//				Repository.containerRealUsageMap.put(tableContainerresourceusage.getContainername(),
//						tableContainerresourceusage);
//			}
		}
		// 统计
//		Map<String, List<String>> appNames = appMonitor.getAPPName(Repository.appInfoMap);
//		Map<String, TableAppresourceusage> aggregateAPPResourceUsage = appMonitor.aggregateAPP(combineList, appNames);
//		appMonitor.testInsert(aggregateAPPResourceUsage);
//		// 添加进全局 appRealUsageMap 变量
//		Repository.appRealUsageMap = aggregateAPPResourceUsage;

		// //调用systemresourceusage
		// ArrayList<TableSystemresourceusage> systemDataList =
		// systemMonitor.getSystemDataList(Repository.systemInfoMap);
		// systemMonitor.testInsert(systemDataList);
		//
		// for (TableSystemresourceusage tableSystemresourceusage :
		// systemDataList) {
		// Repository.systemRealUsageMap.put(tableSystemresourceusage.getHostname(),
		// tableSystemresourceusage);
		// }
	}

	@Test
	public void testSelect60() {
		// int testSelect = systemMonitor.testSelect2(1);
		// System.out.println(testSelect);

	}

}

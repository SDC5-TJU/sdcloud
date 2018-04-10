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
import scs.service.monitor.app.AppMonitor;
import scs.service.monitor.containers.impl.ContainerMonitorImpl;
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

//	 @Test
//	 public void testBase(){
//	 Map<String, Float> memStat = baseInfoService.getMemStat();
//	 System.out.println(memStat.get("RAM"));
//	 System.out.println(memStat.get("memUsed"));
//	 }

	@Test
	public void testDb() {
//		String hostname = "192.168.1.128";
//		String username = "tank";
//		String password = "tanklab";
//		InputStream containerInfoStream = containerMonitor.getContainerInfoStream(hostname, username, password);
//		ArrayList<TableContainerresourceusage> containersPOJO = containerMonitor.getContainersPOJO(containerInfoStream);
//		System.out.println(containerMonitor.testInsert(containersPOJO));
		
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
			combineList.addAll(containersPOJO);
			containerMonitor.testInsert(containersPOJO);
			Iterator<TableContainerresourceusage> iterator = containersPOJO.iterator();
			// 添加进全局 containerRealUsageMap 变量
			while (iterator.hasNext()) {
				TableContainerresourceusage tableContainerresourceusage = (TableContainerresourceusage) iterator.next();
				Repository.containerRealUsageMap.put(tableContainerresourceusage.getContainername(), tableContainerresourceusage);
			}
		}
		System.out.println(new Date());
		//统计
		Map<String, List<String>> appNames = appMonitor.getAPPName(Repository.appInfoMap);
		Map<String, TableAppresourceusage> aggregateAPPResourceUsage = appMonitor.aggregateAPP(combineList, appNames);
		appMonitor.testInsert(aggregateAPPResourceUsage);
		System.out.println(new Date());
	}
	
	
	public static void main(String[] args) {
		
	}
}

package scs.unit.test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import scs.dao.monitor.DAOmapper.TableContainerresourceusageMapper;
import scs.pojo.TableContainerresourceusage;
import scs.service.j_service.BaseInfoService;
import scs.service.monitor.containers.ContainerMonitor;
import scs.service.monitor.containers.impl.ContainerMonitorImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:scs/unit/test/applicationContext.xml" })
public class MyDevTest {

	// @Resource(name="baseMonitor")
	// private BaseInfoService baseInfoService;
	//
	@Autowired
	@Resource(name = "containerMonitor")
	private ContainerMonitorImpl containerMonitor;

//	 @Test
//	 public void testBase(){
//	 Map<String, Float> memStat = baseInfoService.getMemStat();
//	 System.out.println(memStat.get("RAM"));
//	 System.out.println(memStat.get("memUsed"));
//	 }

	@Test
	public void testDb() {
		String hostname = "192.168.1.128";
		String username = "tank";
		String password = "tanklab";
		InputStream containerInfoStream = containerMonitor.getContainerInfoStream(hostname, username, password);
		ArrayList<TableContainerresourceusage> containersPOJO = containerMonitor.getContainersPOJO(containerInfoStream);
		System.out.println(containerMonitor.testInsert(containersPOJO));
	}

}

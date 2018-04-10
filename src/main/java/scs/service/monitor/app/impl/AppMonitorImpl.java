package scs.service.monitor.app.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

import scs.dao.monitor.DAOmapper.TableAppresourceusageMapper;
import scs.pojo.AppInfoBean;
import scs.pojo.TableAppresourceusage;
import scs.pojo.TableContainerresourceusage;
import scs.service.monitor.app.AppMonitor;

@Service("appMonitor")
public class AppMonitorImpl implements AppMonitor {
	
	@Autowired
	public TableAppresourceusageMapper appresourceusageMapper;

	@Override
	public Map<String, List<String>> getAPPName(Map<String, AppInfoBean> appContainerNames) {

		Map<String, List<String>> simpleContainerNamesMap = new HashMap<>();

		Set<Entry<String, AppInfoBean>> entrySet = appContainerNames.entrySet();
		Iterator<Entry<String, AppInfoBean>> iterator = entrySet.iterator();
		while (iterator.hasNext()) {
			Map.Entry<java.lang.String, scs.pojo.AppInfoBean> entry = (Map.Entry<java.lang.String, scs.pojo.AppInfoBean>) iterator
					.next();
			String key = entry.getKey();
			List<String> containerNames = entry.getValue().getContainerNames();
			simpleContainerNamesMap.put(key, containerNames);
		}

		return simpleContainerNamesMap;

	}

	@Override
	public Map<String, TableAppresourceusage> aggregateAPP(ArrayList<TableContainerresourceusage> containerresourceusages,
			Map<String, List<String>> appContainerNames) {
		// 8个服务的名称
		Set<Entry<String, List<String>>> entries = appContainerNames.entrySet();
		/*
		 * String:服务名称，applicationName
		 * TableAppresourceusage：服务资源统计
		 */
		Map<String, TableAppresourceusage> simpleAggregate = new HashMap<>();
		Iterator<Entry<String, List<String>>> iteratorEntries = entries.iterator();
		while (iteratorEntries.hasNext()) {
			Map.Entry<java.lang.String, java.util.List<java.lang.String>> entry = (Map.Entry<java.lang.String, java.util.List<java.lang.String>>) iteratorEntries
					.next();
			String appName = entry.getKey();
			TableAppresourceusage appresourceusage = new TableAppresourceusage();
			appresourceusage.setApplicationname(appName);
			List<String> containersList = entry.getValue();
			int sum = 0;
			float cpuusage = 0.0f;
			float memusage = 0.0f;
			float memAmount = 0.0f;
			float iousage = 0.0f;
			float blockiousage = 0.0f;
			float netusage = 0.0f;
			// containerresourceusages 为统一了两台物理服务器的列表
			for (TableContainerresourceusage container : containerresourceusages) {
				
				if (containersList.contains(container.getContainername())) {
					sum++;
					cpuusage += container.getCpuusagerate();
					memusage += container.getMemusagerate();
					memAmount += container.getMemusageamount();
					iousage += container.getIousagerate();
					blockiousage += container.getBlockio();
					netusage += container.getNetusagerate();
					appresourceusage.setCollecttime(container.getCollecttime());
				}
				//求平均值
				appresourceusage.setCpuusagerate(cpuusage	/sum);
				appresourceusage.setMemusageamount(memAmount/sum);
				appresourceusage.setMemusagerate(memusage 	/sum);
				appresourceusage.setIousagerate(iousage 	/sum);
				appresourceusage.setBlockio(blockiousage 	/sum);
				appresourceusage.setNetusagerate(netusage 	/sum);
			}
			
			simpleAggregate.put(appName, appresourceusage);
		}
		
		return simpleAggregate;
		
	}

	@Override
	public int testInsert(Map<String, TableAppresourceusage> apps) {
		if (apps == null) {
			return -1;
		}
		Set<Entry<String, TableAppresourceusage>> entrySet = apps.entrySet();
		Iterator<Entry<String, TableAppresourceusage>> iterator = entrySet.iterator();
		
		int sum = 0;
		while (iterator.hasNext()) {
			Entry<String, TableAppresourceusage> record = iterator.next();
			appresourceusageMapper.insertSelective(record.getValue());
			sum++;
		}
		return sum;
	}

}

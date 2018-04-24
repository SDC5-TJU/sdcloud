package scs.service.monitor.system.impl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

import scs.dao.monitor.DAOmapper.TableSystemresourceusageMapper;
import scs.pojo.SystemInfoBean;
import scs.pojo.TableSystemresourceusage;
import scs.pojo.TableSystemresourceusageExample;
import scs.service.monitor.system.RMISystemMonitorInterface;
import scs.service.monitor.system.SystemMonitor;
import scs.util.repository.Repository;

@Service("systemMonitor")
public class SystemMonitorImpl implements SystemMonitor {

	@Autowired
	public TableSystemresourceusageMapper tableSystemresourceusageMapper;

	@Override
	public ArrayList<TableSystemresourceusage> getSystemDataList(Map<String, SystemInfoBean> hostnameMap) {
		ArrayList<TableSystemresourceusage> tableSystemresourceusageArray = new ArrayList<>();
		if (hostnameMap.size() == 3) {
			hostnameMap.remove("node29SDC");
		}
		Set<Entry<String, SystemInfoBean>> entrySet = hostnameMap.entrySet();
		Iterator<Entry<String, SystemInfoBean>> iterator = entrySet.iterator();
		Date currentTime = new Date();
		while (iterator.hasNext()) {
			Entry<String, SystemInfoBean> next = iterator.next();
			try {
				String addr = "rmi://" + next.getValue().getHostIp() + ":33334/rmiSystemMonitor";
				// System.out.println(addr);
				RMISystemMonitorInterface systemMonitorInterface = (RMISystemMonitorInterface) Naming.lookup(addr);
				TableSystemresourceusage systemData = systemMonitorInterface.getSystemData(next.getKey(), "eth0",
						currentTime);
				tableSystemresourceusageArray.add(systemData);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (NotBoundException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				// TODO: handle exception
				System.out.println("RMI空指针");
			}
		}
		return tableSystemresourceusageArray;
	}

	@Override
	public int testInsert(ArrayList<TableSystemresourceusage> tableSystemresourceusageList) {
		if (tableSystemresourceusageList == null) {
			return -1;
		}
		int sum = 0;
		for (int i = 0; i < tableSystemresourceusageList.size(); i++) {
			tableSystemresourceusageMapper.insertSelective(tableSystemresourceusageList.get(i));
			sum++;
		}
		return sum;
	}

	@Override
	public List<TableSystemresourceusage> testSelect(int number) {
		String hostName = "";
		if (number == Repository.PhysicalMachine128) {
			hostName = "node28SDC";
		} else if (number == Repository.PhysicalMachine147) {
			hostName = "node26SDC";
		} else {
			return null;
		}
		TableSystemresourceusageExample example = new TableSystemresourceusageExample();
		// order by collectTime DESC
		example.setOrderByClause("collectTime DESC");
		// limit 60
		example.setLimit(60);
		// where hostName = "node28SDC/node26SDC"
		example.createCriteria().andHostnameEqualTo(hostName);
		List<TableSystemresourceusage> selectByExample = tableSystemresourceusageMapper.selectByExample(example);
		Collections.reverse(selectByExample);
		return selectByExample;
	}

	public int testSelect2(int number) {
		String hostName = "";
		if (number == Repository.PhysicalMachine128) {
			hostName = "node28SDC";
		} else if (number == Repository.PhysicalMachine147) {
			hostName = "node26SDC";
		} else {
			return -1;
		}
		TableSystemresourceusageExample example = new TableSystemresourceusageExample();
		// order by collectTime DESC
		example.setOrderByClause("collectTime DESC");
		// limit 60
		example.setLimit(60);
		// where hostName = "node28SDC/node26SDC"
		example.createCriteria().andHostnameEqualTo(hostName);
		List<TableSystemresourceusage> selectByExample = tableSystemresourceusageMapper.selectByExample(example);
		return selectByExample.size();
	}

	@Override
	public TableSystemresourceusage getRealSystemResourceFromStaticData(int number) {
		String hostName = "";
		if (number == Repository.PhysicalMachine128) {
			hostName = "node28SDC";
		} else if (number == Repository.PhysicalMachine147) {
			hostName = "node26SDC";
		} else {
			return null;
		}
		TableSystemresourceusage tableSystemresourceusage = Repository.systemRealUsageMap.get(hostName);
		return tableSystemresourceusage;
	}
}

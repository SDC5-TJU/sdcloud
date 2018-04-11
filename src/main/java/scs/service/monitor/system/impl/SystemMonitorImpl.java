package scs.service.monitor.system.impl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

import scs.dao.monitor.DAOmapper.TableSystemresourceusageMapper;
import scs.pojo.SystemInfoBean;
import scs.pojo.TableSystemresourceusage;
import scs.service.monitor.system.RMISystemMonitorInterface;
import scs.service.monitor.system.SystemMonitor;

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
				System.out.println(addr);
				RMISystemMonitorInterface systemMonitorInterface = (RMISystemMonitorInterface) Naming
						.lookup(addr);
				TableSystemresourceusage systemData = systemMonitorInterface.getSystemData(next.getKey(), "eth0", currentTime);
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
		for(int i = 0; i<tableSystemresourceusageList.size();i++){
			tableSystemresourceusageMapper.insertSelective(tableSystemresourceusageList.get(i));
			sum++;
		}
		return sum;
	}
}

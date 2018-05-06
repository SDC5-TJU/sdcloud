package scs.service.monitor.pqos.impl;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;
import scs.pojo.SystemInfoBean;
import scs.service.monitor.pqos.PqosResourceMonitor;
import scs.service.monitor.system.RMISystemMonitorInterface;
import scs.service.monitor.system.impl.SystemMonitorImpl;
import scs.util.repository.Repository;

@Service("pqosMonitor")
public class PqosResourceMonitorImpl implements PqosResourceMonitor {

	@Override
	public void updateCachemiss(Map<String, SystemInfoBean> hostnameMap) {
		if (hostnameMap.size() == 3) {
			hostnameMap.remove("node29SDC");
		}
		Set<Entry<String, SystemInfoBean>> entrySet = hostnameMap.entrySet();
		Iterator<Entry<String, SystemInfoBean>> iterator = entrySet.iterator();
		float[][] systemCachePercentData = null;
		while (iterator.hasNext()) {
			Entry<String, SystemInfoBean> next = iterator.next();
			try {
				String hostIp = next.getValue().getHostIp();
				String addr = "rmi://" + hostIp + ":33334/rmiSystemMonitor";
				RMISystemMonitorInterface systemMonitorInterface = (RMISystemMonitorInterface) Naming.lookup(addr);
				// systemMonitorInterface.systemCachePercentData();
				synchronized (SystemMonitorImpl.class) {
					systemCachePercentData = systemMonitorInterface.systemCachePercentData();
				}
				if (hostIp.endsWith("147")) {
					Repository.cache147 = systemCachePercentData;
				}else if (hostIp.endsWith("128")) {
					Repository.cache128 = systemCachePercentData;
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (NotBoundException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void updateBamdwidthUsage(Map<String, SystemInfoBean> hostnameMap) {
		if (hostnameMap.size() == 3) {
			hostnameMap.remove("node29SDC");
		}
		Set<Entry<String, SystemInfoBean>> entrySet = hostnameMap.entrySet();
		Iterator<Entry<String, SystemInfoBean>> iterator = entrySet.iterator();
		float[] pqosBandwidthSum = null;
		while (iterator.hasNext()) {
			Entry<String, SystemInfoBean> next = iterator.next();
			try {
				String hostIp = next.getValue().getHostIp();
				String addr = "rmi://" + hostIp + ":33334/rmiSystemMonitor";
				RMISystemMonitorInterface systemMonitorInterface = (RMISystemMonitorInterface) Naming.lookup(addr);
				// systemMonitorInterface.systemCachePercentData();
				synchronized (SystemMonitorImpl.class) {
					systemMonitorInterface.execPqos();
					pqosBandwidthSum = systemMonitorInterface.pqosBandwidthSum();
				}
				if (hostIp.endsWith("147")) {
					Repository.bandwidth147 = pqosBandwidthSum;
				}else if (hostIp.endsWith("128")) {
					Repository.bandwidth128 = pqosBandwidthSum;
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (RemoteException e) {
				e.printStackTrace();
			} catch (NotBoundException e) {
				e.printStackTrace();
			}
		}
	}

}

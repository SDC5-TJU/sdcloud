package scs.service.monitor.pqos;

import java.util.Map;

import scs.pojo.SystemInfoBean;

public interface PqosResourceMonitor {
	
	public void updateCachemiss(Map<String, SystemInfoBean> hostnameMap);
	
	public void updateBamdwidthUsage(Map<String, SystemInfoBean> hostnameMap);
	
}

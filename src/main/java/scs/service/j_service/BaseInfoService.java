package scs.service.j_service;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service("baseMonitor")
public class BaseInfoService {
	
	public Map<String, String> getCpuStat(){
		Map<String, String> cpuStat = null;
		CpuMsg msg = null;
		try {
			msg = (CpuMsg) Naming.lookup("rmi://192.168.1.147:33334/cpuMsg");
			Map<String, String> cpuStaticInfo = msg.cpuInfos();
			cpuStat = new HashMap<>(cpuStaticInfo);
			cpuStat.put("cpuAvg", Float.toString(msg.cpuAvg()));
			cpuStat.put("cpuAvg", Float.toString(msg.cpuAvgIdle()));
			
			return cpuStat;
		} catch (MalformedURLException e) {
			System.out.println("出现MalformedURLException");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cpuStat = new HashMap<>();
	}
	
	public Map<String, Float> getMemStat(){
		Map<String, Float> memStat = new HashMap<>();
		MemMsg msg = null;
		try {
			msg = (MemMsg) Naming.lookup("rmi://Node26SDC:33334/memMsg");
			memStat.put("memUsed", msg.getMemUsedPercent());
			memStat.put("RAM", (float) msg.getRam());
			
			return memStat;
		} catch (MalformedURLException e) {
			System.out.println("出现MalformedURLException");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return memStat;
	}
	
}

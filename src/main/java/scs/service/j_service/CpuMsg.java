package scs.service.j_service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface CpuMsg extends Remote{
	
	/**
	 * @author jztong
	 * @return 当前平台cpu平均利用率【user + sys】
	 */
	public float cpuAvg() throws RemoteException;
	
	/**
	 * 
	 * @return 当前平台cpu User%的平均值
	 */
	public float cpuAvgUsers() throws RemoteException;
	
	/**
	 * 
	 * @return 当前平台cpu wait%的平均值
	 */
	public float cpuAvgWaits() throws RemoteException;
	
	/**
	 * 
	 * @return 当前平台cpu sys%的平均值
	 */
	public float cpuAvgSystem() throws RemoteException;
	
	/**
	 * 
	 * @return 当前平台cpu Idle%的平均值
	 */
	public float cpuAvgIdle() throws RemoteException;
	
	/**
	 * 
	 * @return 所有cpu当前的 usr%
	 */
	public float[] cpusUsersPercent() throws RemoteException;
	
	/**
	 * 
	 * @return 所有cpu当前的 sys%
	 */
	public float[] cpusSysPercent() throws RemoteException;
	
	/**
	 * 
	 * @return 所有cpu当前的 idle%
	 */
	public float[] cpusIdlePercent() throws RemoteException;
	
	/**
	 * 
	 * @return cpu型号信息（静态数据）
	 */
	public Map<String, String> cpuInfos() throws RemoteException;
	
}

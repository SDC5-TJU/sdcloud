package scs.service.j_service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DiskIO extends Remote{
	
	
	/**
	 * 
	 * @return 硬盘1秒发送的字节数
	 */
	public long read() throws RemoteException;
	
	/**
	 * 
	 * @return 硬盘1秒接收的字节数
	 */
	public long write() throws RemoteException;
	
	/**
	 * 
	 * @return 硬盘容量
	 */
	public long getCapacity() throws RemoteException;
	
}

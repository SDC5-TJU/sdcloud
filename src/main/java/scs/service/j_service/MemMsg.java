package scs.service.j_service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MemMsg extends Remote{
	
	/**
	 * 
	 * @return 内存的使用百分比，包括应用程序占用的buffer
	 */
	public float getMemUsedPercent() throws RemoteException;
	
	/**
	 * 
	 * @return 缓存的字节数 以kB为单位
	 */
	public long getCached() throws RemoteException;
	
	/**
	 * 
	 * @return 缓冲区大小字节数 以kB为单位
	 */
	public long getBuffers() throws RemoteException;
	
	/**
	 * 
	 * @return RAM的容量，以MB为单位
	 */
	public int getRam() throws RemoteException;
	
}

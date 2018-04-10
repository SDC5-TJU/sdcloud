package scs.service.j_service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface NetIO extends Remote{
	
	/**
	 * 
	 * @return 指定网卡一秒内的发送的字节数
	 */
	public long send(String iface) throws RemoteException;
	
	/**
	 * 
	 * @return 指定网卡一秒内的接收的字节数
	 */
	public long recv(String iface) throws RemoteException;
	
	/**
	 * 
	 * @return 指定网卡一秒内的发送的字节数
	 */
	public long sendPacket(String iface) throws RemoteException;
	
	/**
	 * 
	 * @return 指定网卡一秒内的接收的数据包数
	 */
	public long recvPacket(String iface) throws RemoteException;
	
	/**
	 * 
	 * @return 所有网卡一秒内的发送的数据包数
	 */
	public long[] sendAll() throws RemoteException;
	
	/**
	 * 
	 * @return 所有网卡一秒内的接收的字节数
	 */
	public long[] recvAll() throws RemoteException;
	
	/**
	 * 
	 * @return 指定网卡一秒内的发送的字节数
	 */
	public long[] sendAllPacket() throws RemoteException;
	
	/**
	 * 
	 * @return 指定网卡一秒内的接收的字节数
	 */
	public long[] recvAllPacket() throws RemoteException;
}

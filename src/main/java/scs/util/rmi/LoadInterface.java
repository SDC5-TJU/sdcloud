package scs.util.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException; 
/**
 * rmi远程调用接口类 server端实现该类 客户端不用实现,只负责调用该接口
 * 该接口被Agent的节点上的rmi程序调用
 * @author yanan
 *
 */
public interface LoadInterface extends Remote{  
	public float getLcAvgLatency() throws RemoteException; 
	public float getLcCurLatency() throws RemoteException; 
	public int setIntensity(int intensity) throws RemoteException; 
	public int getRealIntensity() throws RemoteException;
	public void execRedisLoader(int flag) throws RemoteException;
}

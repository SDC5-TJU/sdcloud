package scs.util.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * rmi远程调用接口类 server端实现该类 客户端不用实现,只负责调用该接口
 * 该接口负责调用scimark的四个节点上的rmi程序
 * @author yanan
 *
 */
public interface ScimarkInterface extends Remote {
	public int execute(int threadCount) throws RemoteException;
}

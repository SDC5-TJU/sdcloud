package scs.util.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import scs.pojo.TwoTuple;
 
/**
 * rmi远程调用接口类 server端实现该类 客户端不用实现,只负责调用该接口
 * 该接口负责webSearch,webServer和redis的rmi远程接口调用
 * 注：因为上述三个应用的负载生成器返回数据类型一致，所以可以共用一个WorkerInterface接口
 * @author yanan
 *
 */
public interface WorkerInterface extends Remote{ 
	public List<TwoTuple<Long, Integer>> execute(String applicationName,int requestCount,int warmUpCount,String strategy,int intensity)throws RemoteException;
}

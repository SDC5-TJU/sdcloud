package scs.util.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import scs.pojo.TwoTuple;
 
/**
 * rmi worker调用接口
 * @author yanan
 *
 */
public interface WorkerInterface extends Remote{ 
	public List<TwoTuple<Long, Integer>> execute(String applicationName,int requestCount,int warmUpCount,String strategy,int intensity)throws RemoteException;
}

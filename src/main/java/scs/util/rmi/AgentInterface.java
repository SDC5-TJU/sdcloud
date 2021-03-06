package scs.util.rmi;

import java.rmi.Remote; 
 
/**
 * rmi远程调用接口类 server端实现该类 客户端不用实现,只负责调用该接口
 * 该接口负责调用Agent的节点上的rmi程序
 * @author yanan
 *
 */
public interface AgentInterface extends Remote{ 
	public void start(int controlLevel);
	public void stop();
	public int receiveSignal(int signal);
	public float getLcCpuAvgLoad();
}

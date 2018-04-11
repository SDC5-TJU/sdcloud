package scs.service.monitor.system;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

import scs.pojo.TableSystemresourceusage;

public interface RMISystemMonitorInterface extends Remote{
	
	/**
	 * @author jztong
	 * @param hostname 接收主机名
	 * @param iface 接收网卡名
	 * @param date 为了保持数据一致，发出查询的时间，取决于web系统方法调用的时间，不取决于容器节点的物理机查询时间
	 * @return 封装当前物理机对象最新数据
	 * @throws RemoteException
	 */
	public TableSystemresourceusage getSystemData(String hostname, String iface, Date date) throws RemoteException;
	
	/**
	 * @author jztong
	 * @return 返回cpu的使用率，使用combined值，即 system % + user%
	 */
	public float cpuData();
	
	/**
	 * @author jztong
	 * @return 返回2个对象的数组，arr[0] 为使用率， arr[1]为使用量
	 */
	public float[] memData();
	
	/**
	 * @author jztong
	 * @return 返回磁盘io的使用率, 该值为读和写的相加值
	 */
	public float ioData();
	
	/**
	 * @author jztong
	 * @param iface 网卡名
	 * @return 返回网口io的使用率, 该值为读和写的相加值
	 */
	public float netData(String iface);
	
}

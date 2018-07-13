package scs.util.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import scs.util.loadGen.loadDriver.RedisDriver;
import scs.util.repository.Repository; 

public class LoadInterfaceImpl extends UnicastRemoteObject implements LoadInterface {

	private static final long serialVersionUID = 1L;

	public LoadInterfaceImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public float getLcAvgLatency() {
		// TODO Auto-generated method stub
		return Repository.getInstance().getOnlineAvgQueryTime();
	}

	@Override
	public float getLcCurLatency() throws RemoteException {
		// TODO Auto-generated method stub
		return Repository.latestOnlineData.getQueryTime();
	}

	@Override
	public int setIntensity(int intensity){
		// TODO Auto-generated method stub
		Repository.onlineRequestIntensity=intensity;
		return Repository.onlineRequestIntensity;
	}

	@Override
	public int getRealIntensity() throws RemoteException {
		// TODO Auto-generated method stub
		return Repository.realRequestIntensity;
	}

	@Override
	public void execRedisLoader(int flag) throws RemoteException {
		// TODO Auto-generated method stub
		if(flag==1){
			Repository.onlineDataFlag=true;
		}else{
			Repository.onlineDataFlag=false;
		}
		RedisDriver.getInstance().executeJob(null);
	}


}

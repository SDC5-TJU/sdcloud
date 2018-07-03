package scs.util.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

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

	 
}

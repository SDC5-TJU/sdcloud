package scs.util.rmi; 

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
 

public class LoadService {
	private static LoadService loadService=null;
	private LoadService(){}
	public synchronized static LoadService getInstance() {
		if (loadService == null) {
			loadService = new LoadService();
		}
		return loadService;
	}  
	public void service(String ip,int port) {
		try { 
			LocateRegistry.createRegistry(port);
			LoadInterface load = new LoadInterfaceImpl();  
			Naming.rebind("rmi://"+ip+":"+port+"/load", load);
			System.out.println("load "+ip+":"+port+" rmi 服务端开启");

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO: handle exception
		}
	}
}

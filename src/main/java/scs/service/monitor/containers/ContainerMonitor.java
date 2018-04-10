package scs.service.monitor.containers;

import java.io.InputStream;
import java.util.ArrayList;

import scs.pojo.TableContainerresourceusage;

public interface ContainerMonitor {
	
	public int testInsert(ArrayList<TableContainerresourceusage> containersList);
	public InputStream getContainerInfoStream(String hostname, String username, String password);
	public ArrayList<TableContainerresourceusage> getContainersPOJO(InputStream in);
}

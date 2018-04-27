package scs.util.tools;

import java.io.IOException;

import ch.ethz.ssh2.Connection;
import scs.util.repository.Repository;

public class SSHConnection {
	private Connection node26conn=null; 
	private Connection node28conn=null; 
	/**
	 * 单例模式
	 */
	private static SSHConnection connection=null;
	private SSHConnection(){
		try { 
			this.initNode26Conn();
			this.initNode28Conn();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public synchronized static SSHConnection getInstance() {
		if (connection == null) {  
			connection = new SSHConnection();
		}  
		return connection;
	}
	private void initNode26Conn() throws IOException{
		node26conn=new Connection("192.168.1.147");  
		node26conn.connect(); 
		boolean result1=node26conn.authenticateWithPassword("tank","tanklab"); 
		if (result1) {
			System.out.println("147认证成功");
		}else{
			System.out.println("147认证失败");
		}

	}
	private void initNode28Conn() throws IOException{
		node28conn=new Connection("192.168.1.128");  
		node28conn.connect(); 
		boolean result1=node28conn.authenticateWithPassword("tank","tanklab"); 
		if (result1) {
			System.out.println("128认证成功");
		}else{
			System.out.println("128认证失败");
		}
	}
	public Connection getConn(String hostName){
		if(hostName!=null&&hostName.equals("192.168.1.147")){
			if(node26conn==null&&Repository.cronFlag==1){
				try {
					System.out.println("147重新认证");
					this.initNode26Conn();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return node26conn;
		}else{
			if(node28conn==null&&Repository.cronFlag==1){
				try {
					System.out.println("128重新认证");
					this.initNode28Conn();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return node28conn;
		}
	}  
	public void closeConn(){
		node26conn.close(); 
		node26conn=null; 
		node28conn.close();
		node28conn=null; 
	}
}

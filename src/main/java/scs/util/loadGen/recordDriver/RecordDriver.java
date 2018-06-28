package scs.util.loadGen.recordDriver;
  
public class RecordDriver{
	/**
	 * 单例代码块
	 */
	private static RecordDriver driver=null;
	public RecordDriver(){};
	public synchronized static RecordDriver getInstance() {
		if (driver == null) {  
			driver = new RecordDriver();
		}  
		return driver;
	}
	/**
	 * 开启记录线程
	 */
	public void execute() {
		System.out.println("--- record thread startup-----"); 
		new RecordExecThread(1000).start();//start 是异步执行
	}






}
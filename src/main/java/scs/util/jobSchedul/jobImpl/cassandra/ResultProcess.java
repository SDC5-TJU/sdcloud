package scs.util.jobSchedul.jobImpl.cassandra;

import java.io.IOException;   

public class ResultProcess {	 

	private static ResultProcess resultProcess=null;
	private ResultProcess(){}
	public synchronized static ResultProcess getInstance() {
		if (resultProcess == null) {  
			resultProcess = new ResultProcess();
		}  
		return resultProcess;
	} 

	public int process() throws IOException {  
		
		return 0;  

	}  

}

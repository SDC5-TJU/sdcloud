package scs.util.loadGen.recordDriver;
   
import scs.pojo.heracles.QueryData;
import scs.util.repository.Repository; 

public class RecordExecThread extends Thread{
 

	/**
	 * 线程构造方法
	 * @param httpclient httpclient对象
	 * @param url 要访问的链接 
	 */
	private int executeInterval;
	public RecordExecThread(int executeInterval){
		this.executeInterval=executeInterval;
	}

	@Override
	public void run(){  
		int sum=0;
		QueryData data=new QueryData();
		Repository instance=Repository.getInstance();
		while(Repository.onlineDataFlag){
			Repository.tempOnlineDataList.clear();
			while(Repository.onlineDataList.size()==0){
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Repository.tempOnlineDataList.addAll(Repository.onlineDataList); 
			Repository.onlineDataList.clear();
			sum=0;
			for(QueryData item:Repository.tempOnlineDataList){
				sum+=item.getQueryTime();
			}
			sum=sum/Repository.tempOnlineDataList.size();//由于while循环的存在,所以分母不可能为0
	 
			data.setGenerateTime(Repository.tempOnlineDataList.get(Repository.tempOnlineDataList.size()-1).getGenerateTime());
			data.setQueryTime(sum);
			data.setQps(Repository.tempOnlineDataList.size());
		
			instance.addWindowOnlineDataList(data);
			
			try {
				Thread.sleep(executeInterval);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("--- record thread shutdown-----");
		
	}



}

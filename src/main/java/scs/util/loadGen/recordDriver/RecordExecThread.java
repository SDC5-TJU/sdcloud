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
		int avgCurSecond=0;
	
		Repository instance=Repository.getInstance();
		while(Repository.onlineDataFlag){
			while(Repository.onlineDataList.size()==0){
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			Repository.tempOnlineDataList.clear();
			Repository.tempOnlineDataList.addAll(Repository.onlineDataList); 
			Repository.onlineDataList.clear(); 
			avgCurSecond=0;
			for(QueryData item:Repository.tempOnlineDataList){
				avgCurSecond+=item.getQueryTime();
			}
			avgCurSecond=avgCurSecond/Repository.tempOnlineDataList.size();//由于while循环的存在,所以分母不可能为0
			QueryData data=new QueryData();
			//data.setGenerateTime(Repository.tempOnlineDataList.get(Repository.tempOnlineDataList.size()-1).getGenerateTime());
			data.setGenerateTime(System.currentTimeMillis());
			data.setQueryTime(avgCurSecond);
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

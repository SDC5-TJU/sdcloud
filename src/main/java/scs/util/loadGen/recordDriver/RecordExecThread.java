package scs.util.loadGen.recordDriver;
   
import java.util.ArrayList; 
import scs.pojo.heracles.QueryData;
import scs.util.format.DataFormats;
import scs.util.repository.Repository; 

public class RecordExecThread extends Thread{
 
	private Repository instance=Repository.getInstance();
	private DataFormats dataFormats=DataFormats.getInstance();
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
		//int avgCurSecond=0;
		ArrayList<Float> queryTimeList=new ArrayList<Float>();
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
			//avgCurSecond=0;
			for(QueryData item:Repository.tempOnlineDataList){
			    //avgCurSecond+=item.getQueryTime();
				queryTimeList.add(item.getQueryTime());
			}
			//avgCurSecond=avgCurSecond/Repository.tempOnlineDataList.size();//由于while循环的存在,所以分母不可能为0
			QueryData data=new QueryData();
			//data.setGenerateTime(Repository.tempOnlineDataList.get(Repository.tempOnlineDataList.size()-1).getGenerateTime());
			data.setGenerateTime(System.currentTimeMillis());
			data.setQueryTime(dataFormats.subFloat(dataFormats.percentile(queryTimeList,0.99f),2));//计算99分位数
			data.setQps(Repository.tempOnlineDataList.size());
		
			instance.addWindowOnlineDataList(data);
			
			try {
				Thread.sleep(executeInterval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} 
	}
	  
}

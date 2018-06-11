package scs.util.loadGen.driver;

import java.util.ArrayList;
import java.util.List; 
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors; 

import scs.pojo.TwoTuple;
import scs.util.loadGen.strategy.PatternInterface;
import scs.util.repository.Repository;
import scs.util.tools.HttpClientPool; 

public class WebSearchDriver extends AbstractJobDriver{
	/**
	 * 单例代码块
	 */
	private static WebSearchDriver driver=null;
	public WebSearchDriver(){initVariables();}
	public synchronized static WebSearchDriver getInstance() {
		if (driver == null) {  
			driver = new WebSearchDriver();
		}  
		return driver;
	}

	@Override
	public void initVariables() {
		httpclient=HttpClientPool.getInstance().getConnection();
		queryItemsStr="http://localhost:8080/sdcloud/";
	}


	/**
	 * 执行评测作业
	 * @param requestCount 请求总次数
	 * @param warmUpCount 预热次数
	 * @param pattern 请求模式
	 * @param intensity 请求强度
	 * @return 请求结果<请求发出时间,响应耗时>
	 */
	@Override
	public List<TwoTuple<Long, Integer>> executeJob(int requestCount,String strategy,int intensity) {
		List<TwoTuple<Long,Integer>> timeRecords=new ArrayList<TwoTuple<Long,Integer>>();//采集时间的列表
	
		PatternInterface pattern=this.choosePattern(strategy);//选择访问策略
		ExecutorService executor = Executors.newCachedThreadPool();

		/*
		 * 测试阶段,采集时间
		 */
		//while(requestCount>0){
		while(Repository.onlineDataFlag==true&&Repository.onlineDataList.size()<2000){
			executor.execute(new ExecuteThread(httpclient,queryItemsStr));
			requestCount--;
			try {
				Thread.sleep(pattern.getIntervalTime()*intensity);
			} catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		executor.shutdown();//停止提交任务

		//检测全部的线程是否都已经运行结束
		
		while(!executor.isTerminated()){
			try {
				Thread.sleep(5000);
			} catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		executor.shutdownNow();  
		System.out.println("---thread shutdown-----");

		return timeRecords;

	}
	 





}
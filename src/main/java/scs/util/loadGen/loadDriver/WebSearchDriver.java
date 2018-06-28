package scs.util.loadGen.loadDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors; 

import scs.pojo.TwoTuple;
import scs.util.loadGen.strategy.PatternInterface;
import scs.util.repository.Repository;
import scs.util.tools.HttpClientPool; 
/**
 * webSearch服务请求类
 * @author yanan
 *
 */
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
		queryItemsStr="http://192.168.1.109:18080/Ibeacon/test2.action?rand=";
	}


	/**
	 * 按毫秒级时间间隔开环发送请求
	 * 多线程-开环
	 * @param strategy 请求模式 possion
	 * @return 请求结果<请求发出时间,响应耗时>
	 */
	@Override
	public List<TwoTuple<Long, Integer>> executeJob(String strategy) {
		System.out.println("--- generate thread start-----");
		Random rand=new Random();
		List<TwoTuple<Long,Integer>> timeRecords=new ArrayList<TwoTuple<Long,Integer>>();//采集时间的列表
	
		PatternInterface pattern=this.choosePattern(strategy);//选择访问策略
		ExecutorService executor = Executors.newCachedThreadPool();

		 /**
		  *  onlineDataFlag标志为true时执行,
		  */
		while(Repository.onlineDataFlag==true){
			while(Repository.onlineDataList.size()>=3000){ //同时如果请求数据达到3000后没有处理,则保护内存不再提交请求
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) { 
					e.printStackTrace();
				}
			}
			executor.execute(new LoadExecThread(httpclient,queryItemsStr+rand.nextInt(99999999)));//防止客户端缓存
			try {
				Thread.sleep(333*pattern.getIntervalTime()/Repository.onlineRequestIntensity);
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
		System.out.println("--- generate thread shutdown-----");

		return timeRecords;

	}
	 





}
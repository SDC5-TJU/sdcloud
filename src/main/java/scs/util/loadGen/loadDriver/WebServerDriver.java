package scs.util.loadGen.loadDriver;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors; 

import scs.util.loadGen.strategy.PatternInterface;
import scs.util.repository.Repository;
import scs.util.tools.HttpClientPool; 
/**
 * webSearch服务请求类
 * @author yanan
 *
 */
public class WebServerDriver extends AbstractJobDriver{
	/**
	 * 单例代码块
	 */
	private static WebServerDriver driver=null;
	public WebServerDriver(){initVariables();}
	public synchronized static WebServerDriver getInstance() {
		if (driver == null) {  
			driver = new WebServerDriver();
		}  
		return driver;
	}

	@Override
	public void initVariables() {
		httpclient=HttpClientPool.getInstance().getConnection();
		//queryItemsStr="http://192.168.1.109:18080/Ibeacon/test2.action?rand=";
		queryItemsStr="http://192.168.1.128:18080/servlet/TPCW_product_detail_servlet?I_ID=";
	}


	/**
	 * 按毫秒级时间间隔开环发送请求
	 * 多线程-开环
	 * @param strategy 请求模式 possion
	 * @return 请求结果<请求发出时间,响应耗时>
	 */
	@Override
	public void executeJob(String strategy) {
		System.out.println("--- generate thread start-----");
		Random rand=new Random();

		PatternInterface pattern=this.choosePattern(strategy);//选择访问策略
		ExecutorService executor = Executors.newCachedThreadPool();

		/**
		 *  onlineDataFlag标志为true时执行,
		 */
		Repository.onlineQueryThreadRunning=true;
		while(Repository.onlineDataFlag==true){
			while(Repository.onlineDataList.size()>=3000){ //同时如果请求数据达到3000后没有处理,则保护内存不再提交请求
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) { 
					e.printStackTrace();
				}
			}
			executor.execute(new LoadExecThread(httpclient,queryItemsStr+rand.nextInt(9999)));//防止客户端缓存
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
		Repository.onlineQueryThreadRunning=false;
		System.out.println("--- generate thread shutdown-----"); 
	}






}
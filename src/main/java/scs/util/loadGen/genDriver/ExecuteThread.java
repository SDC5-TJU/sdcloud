package scs.util.loadGen.genDriver;
   

import java.util.Random;

import org.apache.http.impl.client.CloseableHttpClient; 
import scs.pojo.QueryData; 
import scs.util.repository.Repository;
import scs.util.tools.HttpClientPool; 
/**
 * 请求发送线程,发送请求并记录时间
 * @author yanan
 *
 */
public class ExecuteThread extends Thread{
	private CloseableHttpClient httpclient;//httpclient对象
	private String url;//请求的url

	/**
	 * 线程构造方法
	 * @param httpclient httpclient对象
	 * @param url 要访问的链接 
	 */
	public ExecuteThread(CloseableHttpClient httpclient,String url){
		this.httpclient=httpclient;
		this.url=url; 
	}

	@Override
	public void run(){  
//		try {
//			Thread.sleep(new Random().nextInt(300));
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		//Repository.onlineDataList.add(new QueryData(System.currentTimeMillis(),new Random().nextInt(100)));
		
		Repository.onlineDataList.add(new QueryData(System.currentTimeMillis(),HttpClientPool.getResponseTime(httpclient,url)));
	}



}

package scs.util.loadGen.driver;

import java.util.Random;

import org.apache.http.impl.client.CloseableHttpClient;

import scs.pojo.QueryData; 
import scs.util.repository.Repository;
import scs.util.tools.HttpClientPool; 

public class ExecuteThread extends Thread{
	private CloseableHttpClient httpclient;
	private String url;

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
		//Repository.onlineDataList.add(new TwoTuple<Long,Integer>(System.currentTimeMillis(),HttpClientPool.getResponseTime(httpclient,url)));
		Repository.onlineDataList.add(new QueryData(System.currentTimeMillis(),HttpClientPool.getResponseTime(httpclient,url)));
		
	}



}

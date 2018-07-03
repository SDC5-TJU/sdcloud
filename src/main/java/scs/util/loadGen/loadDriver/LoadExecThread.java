package scs.util.loadGen.loadDriver;
    
import org.apache.http.impl.client.CloseableHttpClient;

import scs.pojo.heracles.QueryData;
import scs.util.repository.Repository;
import scs.util.tools.HttpClientPool; 
/**
 * 请求发送线程,发送请求并记录时间
 * @author yanan
 *
 */
public class LoadExecThread extends Thread{
	private CloseableHttpClient httpclient;//httpclient对象
	private String url;//请求的url

	/**
	 * 线程构造方法
	 * @param httpclient httpclient对象
	 * @param url 要访问的链接 
	 */
	public LoadExecThread(CloseableHttpClient httpclient,String url){
		this.httpclient=httpclient;
		this.url=url;
	}

	@Override
	public void run(){
		Repository.onlineDataList.add(new QueryData(System.currentTimeMillis(),HttpClientPool.getResponseTime(httpclient,url)));
	}



}

package scs.util.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient; 
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;

import scs.util.jobSchedul.jobImpl.bonnie.BonnieJobImpl;
 /**
  * httpclient池配置类
  * @author yanan
  *
  */
public class HttpClientPool {
	private PoolingHttpClientConnectionManager poolConnManager;
	private final int maxTotalPool = 5;
	private final int maxConPerRoute = 5;
	private final int socketTimeout = 15000;
	private final int connectionRequestTimeout = 15000;
	private final int connectTimeout = 15000;
	private static String serverBaseURL="";
	private static String searchBaseURL="";
	private static String url=""; 
	private static Random rand=new Random();
	/**
	 * 单例模式
	 */
	private static HttpClientPool httpClientDemo=null;
	private HttpClientPool(){
		this.init();
	}
	public synchronized static HttpClientPool getInstance() {
		if (httpClientDemo == null) {  
			httpClientDemo = new HttpClientPool();
		}  
		return httpClientDemo;
	}
	
	@SuppressWarnings("static-access")
	public void init(){  
		try {  
			SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(null,  
					new TrustSelfSignedStrategy())  
					.build();   
			@SuppressWarnings("deprecation")
			HostnameVerifier hostnameVerifier = SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;  
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(  
					sslcontext,hostnameVerifier);  
			Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()  
					.register("http", PlainConnectionSocketFactory.getSocketFactory())  
					.register("https", sslsf)  
					.build();  
			poolConnManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);  
			// Increase max total connection to 200  
			poolConnManager.setMaxTotal(maxTotalPool);  
			// Increase default max connection per route to 20  
			poolConnManager.setDefaultMaxPerRoute(maxConPerRoute);  
			SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(socketTimeout).build();  
			poolConnManager.setDefaultSocketConfig(socketConfig);  
		} catch (Exception e) {  
				e.printStackTrace();
		}  
		Properties prop = new Properties();
		InputStream is = BonnieJobImpl.class.getResourceAsStream("/conf/sys.properties");
		try {
			prop.load(is);
		} catch (IOException e) { 
			e.printStackTrace();
		}
		this.serverBaseURL=prop.getProperty("serverBaseURL").trim();//读取webServer观察链接的路径
		this.searchBaseURL=prop.getProperty("searchBaseURL").trim();//读取webSearch观察链接的路径
	}
	/**
	 * 获取一个可用的连接
	 * @return
	 */
	public CloseableHttpClient getConnection(){  
		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(connectionRequestTimeout)  
				.setConnectTimeout(connectTimeout).setSocketTimeout(socketTimeout).build();  
		CloseableHttpClient httpClient = HttpClients.custom()  
				.setConnectionManager(poolConnManager).setDefaultRequestConfig(requestConfig).build();  
		/*if(poolConnManager!=null&&poolConnManager.getTotalStats()!=null){  
			System.out.println("now client pool "+poolConnManager.getTotalStats().toString());  
		}  */
		return httpClient;  
	} 

	/**
	 * 计算响应时间
	 * @param httpclient对象
	 * @param URL 要访问的链接
	 * @return 响应耗费的毫秒数
	 */
	public static int getResponseTime(CloseableHttpClient httpclient,int type){
		if(type==0){
			 url=serverBaseURL+RandomString.generateString(2);
		}else{
			url=searchBaseURL+rand.nextInt(9999);
		} 
		int costTime=65535;
		long begin=0L,end=0L;
		HttpGet httpget=new HttpGet(url);
		try {
			begin=System.currentTimeMillis();
			HttpResponse response=httpclient.execute(httpget); 
			if(response.getStatusLine().getStatusCode()==200){
				end=System.currentTimeMillis();//状态码为200代表成功响应,计算耗时
			}
			costTime=(int)(end-begin);
		}catch(IOException e){
			e.printStackTrace();
			costTime=65535;
		}finally{
			httpget.releaseConnection();//释放连接
		}
		return costTime;
	}

}

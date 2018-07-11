//package scs.util.tools; 
//import java.io.IOException;
//import java.util.LinkedHashSet;
//import java.util.Set;
//
//import org.apache.http.HttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.CloseableHttpClient;
//
//import redis.clients.jedis.HostAndPort;
//import redis.clients.jedis.JedisCluster;
//import redis.clients.jedis.JedisPoolConfig; 
//
//public class RedisConnPool {
//	private JedisCluster cluster =null;
//	/**
//	 * 单例模式
//	 */
//	private static RedisConnPool redisConnPool=null;
//	private RedisConnPool(){ 
//		 initConn();
//	}
//	public synchronized static RedisConnPool getInstance() {
//		if (redisConnPool == null) {  
//			redisConnPool = new RedisConnPool();
//		}  
//		return redisConnPool;
//	}
//	public void initConn() { 
//		JedisPoolConfig poolConfig = new JedisPoolConfig();
//		
//		// 最大连接数
//		poolConfig.setMaxTotal(5000);
//		// 最大空闲数
//		poolConfig.setMaxIdle(500);
//		// 最大允许等待时间，如果超过这个时间还未获取到连接，则会报JedisException异常：
//		// Could not get a resource from the pool
//		poolConfig.setMaxWaitMillis(1000);
//		Set<HostAndPort> nodes = new LinkedHashSet<HostAndPort>();
//		nodes.add(new HostAndPort("172.17.192.93",9001));
//		nodes.add(new HostAndPort("172.17.192.91",9001));
//		nodes.add(new HostAndPort("172.17.192.39",9001));
//		nodes.add(new HostAndPort("172.17.192.38",9001));
//		nodes.add(new HostAndPort("172.17.192.37",9001));
//		nodes.add(new HostAndPort("172.17.192.92",9001));
//		cluster = new JedisCluster(nodes,poolConfig);
//		 
//		/*String name = cluster.get("name");
//		System.out.println(name);
//		cluster.set("age", "18");
//		System.out.println(cluster.get("age"));
//		try {
//			cluster.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}*/
//	}
//	/**
//	 * 获取连接
//	 * @return
//	 */
//	public JedisCluster getConn(){
//		if(cluster==null){
//			this.initConn();
//			return cluster;
//		}else{
//			return cluster;
//		}
//	}
//	/**
//	 * 计算响应时间
//	 * @param httpclient对象
//	 * @param URL 要访问的链接
//	 * @return 响应耗费的毫秒数
//	 */
//	public static int getResponseTime(JedisCluster cluster,String URL){
//		int costTime=65535;
//		long begin=0L,end=0L;
//		HttpGet httpget=new HttpGet(URL);
//		try {
//			begin=System.currentTimeMillis();
//			HttpResponse response=httpclient.execute(httpget); 
//			if(response.getStatusLine().getStatusCode()==200){
//				end=System.currentTimeMillis();//状态码为200代表成功响应,计算耗时
//			}
//			costTime=(int)(end-begin);
//		}catch(IOException e){
//			e.printStackTrace();
//			costTime=65535;
//		}finally{
//			httpget.releaseConnection();//释放连接
//		}
//		return costTime;
//	}
//}
//

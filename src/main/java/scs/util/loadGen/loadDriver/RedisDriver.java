package scs.util.loadGen.loadDriver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader; 
import java.util.Properties;

import scs.pojo.heracles.QueryData;
import scs.util.repository.Repository; 
/**
 * webSearch服务请求类
 * @author yanan
 *
 */
public class RedisDriver extends AbstractJobDriver{
	private String scriptDir="";
	/**
	 * 单例代码块
	 */
	private static RedisDriver driver=null;
	public RedisDriver(){initVariables();}
	public synchronized static RedisDriver getInstance() {
		if (driver == null) {  
			driver = new RedisDriver();
		}  
		return driver;
	}

	@Override
	protected void initVariables() {
		// TODO Auto-generated method stub
	}
	/**
	 * 按毫秒级时间间隔开环发送请求
	 * 多线程-开环
	 * @param strategy 请求模式 possion
	 * @return 请求结果<请求发出时间,响应耗时>
	 */
	@Override
	public void executeJob(String strategy) {
		Properties prop = new Properties();
		InputStream is = RedisDriver.class.getResourceAsStream("/conf/sys.properties");
		try {
			prop.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.scriptDir=prop.getProperty("sdc_script_dir").trim();//读取脚本的路径
		if(Repository.onlineDataFlag==true){
			this.startQuery("sh "+scriptDir+"redis/StartContainer.sh 9999999999 "+Repository.onlineRequestIntensity);
	
		}else{
			this.stopQuery("sh "+scriptDir+"redis/StopContainer.sh");
		}
	}
	/**
	 * 开启查询
	 * @param scriptPath 脚本路径
	 */
	private void startQuery(String scriptPath){ 
		try { 
			System.out.println(scriptPath);
			Repository instance=Repository.getInstance();
			Process process = Runtime.getRuntime().exec(scriptPath); 
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is); 
			BufferedReader br = new BufferedReader(isr);
			String line=null;
			String[] split=new String[2];
			Repository.onlineQueryThreadRunning=true;
			while((line = br.readLine()) != null ) {
					split=line.trim().split("  ");
					QueryData data=new QueryData();
					data.setGenerateTime(System.currentTimeMillis());
					data.setQps((int)Float.parseFloat((split[0])));
					data.setQueryTime((int)Float.parseFloat(split[1]));
					instance.addWindowOnlineDataList(data);
			}
			Repository.onlineQueryThreadRunning=false;
			br.close(); 
			isr.close();
			is.close(); 
		} catch (IOException e) {
			e.printStackTrace();
		}    
		System.out.println("--- generate thread shutdown-----");
	}
	/**
	 * 关闭查询
	 * @param scriptPath
	 */
	private void stopQuery(String scriptPath){
		try { 
			String line = null,err;
			Process process = Runtime.getRuntime().exec(scriptPath); 
			BufferedReader br = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			InputStreamReader isr = new InputStreamReader(process.getInputStream());
			LineNumberReader input = new LineNumberReader(isr);   
			while (((err = br.readLine()) != null||(line = input.readLine()) != null)) {
				if(err==null){
					//System.out.println(line); 
				}else{
					System.out.println(err);
				}
			} 
		} catch (IOException e) {
			e.printStackTrace();
		}    
	}
  
}
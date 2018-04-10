package scs.util.jobSchedul.jobImpl.silo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Properties; 
import scs.pojo.AppConfigBean;
import scs.util.jobSchedul.JobInterface;  
import scs.util.repository.Repository; 
/**
 * silo应用控制实现类
 * @author 
 *
 */
public class SiloJobImpl implements JobInterface{
	private AppConfigBean config;
	private String cmd;
	private String scriptDir,resultDir;  
	
	private static SiloJobImpl instance=null; 
	private SiloJobImpl(){}
	public synchronized static SiloJobImpl getInstance() {
		if (instance == null) {  
			instance = new SiloJobImpl();
		}  
		return instance;
	}
	@Override
	public void init(int isBase) {
		Properties prop = new Properties();
		InputStream is = SiloJobImpl.class.getResourceAsStream("/conf/sys.properties");
		try {
			prop.load(is);
		} catch (IOException e) { 
			e.printStackTrace();
		}
		this.scriptDir=prop.getProperty("sdc_script_dir").trim();//读取silo脚本的路径
		this.resultDir=prop.getProperty("silo_result_dir").trim();//读取silo结果的路径
		this.config=Repository.appConfigMap.get("silo");
		if(isBase==1){
			Repository.siloBaseDataList.clear();//基准测试 
			System.out.println("清空silo Basedata size="+Repository.siloBaseDataList.size());
		}else{
			Repository.siloDataList.clear();//正式测试
			System.out.println("清空silo data size="+Repository.siloDataList.size());
		}
		// TODO Auto-generated method stub

	}
	@Override
	public void start(int isBase) {
		String requestCount=config.getRequestCount();//请求总数
		String warmUpCount=config.getWarmUpCount();//预热次数
		int intensity=Integer.parseInt(config.getIntensity()); //每秒钟请求数QPS
		cmd = "sh " + scriptDir+ "silo/StartContainer.sh "+requestCount+" "+warmUpCount+" "+intensity;
		System.out.println(cmd+"++");
		try {
			String line = null,err;
			Process process = Runtime.getRuntime().exec(cmd); 
			BufferedReader br = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			InputStreamReader isr = new InputStreamReader(process.getInputStream());
			LineNumberReader input = new LineNumberReader(isr); 
			while (((err = br.readLine()) != null||(line = input.readLine()) != null)) {
				if(err==null){
					System.out.println(line);
				}else{
					System.out.println(err);
				}
			}
		}catch (IOException e) { 
			e.printStackTrace();
		}
		System.out.println(cmd+"--");

	}

	@Override
	public void shutdown() {}


	@Override
	public int processResult(int isBase) {
		int resultSize=0;
		try {
			resultSize=ResultProcess.getInstance().process(resultDir,isBase);//解析结果文件
		} catch (IOException e) { 
			e.printStackTrace();
		}
		if(isBase==1){ 
			System.out.println("silo Basedata size="+Repository.siloBaseDataList.size());
		}else{ 
			System.out.println("silo data size="+Repository.siloDataList.size());
		}
		return resultSize;
	}

}

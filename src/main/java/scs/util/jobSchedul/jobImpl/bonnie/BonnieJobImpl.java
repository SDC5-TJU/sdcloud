package scs.util.jobSchedul.jobImpl.bonnie;

import java.io.IOException;
import java.io.InputStream; 
import java.util.Properties; 
import scs.pojo.AppConfigBean;
import scs.util.jobSchedul.JobInterface; 
import scs.util.repository.Repository; 
/**
 * bonnie应用控制实现类
 * @author 
 *
 */
public class BonnieJobImpl implements JobInterface{
	private AppConfigBean config;
	private String cmd;
	private String scriptDir; 
	
	private static BonnieJobImpl instance=null; 
	private BonnieJobImpl(){}
	public synchronized static BonnieJobImpl getInstance() {
		if (instance == null) {  
			instance = new BonnieJobImpl();
		}  
		return instance;
	}
	 
	@Override
	public void init(int isBase) {
		Properties prop = new Properties();
		InputStream is = BonnieJobImpl.class.getResourceAsStream("/conf/sys.properties");
		try {
			prop.load(is);
		} catch (IOException e) { 
			e.printStackTrace();
		}
		this.scriptDir=prop.getProperty("sdc_script_dir").trim();//读取bonnie脚本的路径 
		this.config=Repository.appConfigMap.get("bonnie");
		// TODO Auto-generated method stub

	}

	@Override
	public void start(int isBase) {
		// TODO Auto-generated method stub
		cmd = "sh " + scriptDir + "bonnie/StartContainer.sh " 
				+(Integer.parseInt(config.getIntensity())<<1)+" "+Integer.parseInt(config.getIntensity());
		
		try {
			Runtime.getRuntime().exec(cmd); 
		}catch (IOException e) { 
			e.printStackTrace();
		}
		System.out.println(cmd);
	}

	@Override
	public void shutdown() {
		cmd = "sh " + scriptDir + "bonnie/ShutContainer.sh" ;
		
		try {
			Runtime.getRuntime().exec(cmd); 
		}catch (IOException e) { 
			e.printStackTrace();
		}
		System.out.println(cmd);
	}


	@Override
	public int processResult(int isBase) {
		// TODO Auto-generated method stub

		return 0;
	}

}

package scs.util.jobSchedul.jobImpl.dwarf;

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
 * bonnie应用控制实现类
 * @author 
 *
 */
public class DwarfJobImpl implements JobInterface{
	private AppConfigBean config;
	private String cmd;
	private String scriptDir; 
	
	private static DwarfJobImpl instance=null; 
	private DwarfJobImpl(){}
	public synchronized static DwarfJobImpl getInstance() {
		if (instance == null) {  
			instance = new DwarfJobImpl();
		}  
		return instance;
	}
	 
	@Override
	public void init(int isBase) {
		Properties prop = new Properties();
		InputStream is = DwarfJobImpl.class.getResourceAsStream("/conf/sys.properties");
		try {
			prop.load(is);
		} catch (IOException e) { 
			e.printStackTrace();
		}
		this.scriptDir=prop.getProperty("sdc_script_dir").trim();//读取sdc脚本的路径 
		this.config=Repository.appConfigMap.get("dwarf");
		// TODO Auto-generated method stub

	}

	@Override
	public void start(int isBase) {
		// TODO Auto-generated method stub
		cmd = "sh " + scriptDir + "dwarf/StartContainer.sh "+Integer.parseInt(config.getIntensity());
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
	public void shutdown() {
		 
	}


	@Override
	public int processResult(int isBase) {
		// TODO Auto-generated method stub

		return 0;
	}

}

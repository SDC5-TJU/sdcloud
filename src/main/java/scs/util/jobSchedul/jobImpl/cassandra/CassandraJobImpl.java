package scs.util.jobSchedul.jobImpl.cassandra;

import java.io.IOException;
import java.io.InputStream; 
import java.util.Properties; 
import scs.pojo.AppConfigBean;
import scs.util.jobSchedul.JobInterface;
import scs.util.jobSchedul.jobImpl.bonnie.BonnieJobImpl;
import scs.util.repository.Repository; 
/**
 * cassandra应用控制实现类
 * @author 
 *
 */
public class CassandraJobImpl implements JobInterface{
	private AppConfigBean config;
	private String cmd;
	private String scriptDir; 
	
	private static CassandraJobImpl instance=null; 
	private CassandraJobImpl(){}
	public synchronized static CassandraJobImpl getInstance() {
		if (instance == null) {  
			instance = new CassandraJobImpl();
		}  
		return instance;
	}
	@Override
	public void init(int isBase) {
		Properties prop = new Properties();
		InputStream is = CassandraJobImpl.class.getResourceAsStream("/conf/sys.properties");
		try {
			prop.load(is);
		} catch (IOException e) { 
			e.printStackTrace();
		}
		this.scriptDir=prop.getProperty("sdc_script_dir").trim();//读取cassandra脚本的路径 
		this.config=Repository.appConfigMap.get("cassandra");
		// TODO Auto-generated method stub

	}
	@Override
	public void start(int isBase) {
		// TODO Auto-generated method stub

	}

	@Override
	public void shutdown() {}


	@Override
	public int processResult(int isBase) {
		// TODO Auto-generated method stub

		return 0;
	}

}

package scs.service.jobSchedul;
  
import javax.annotation.Resource; 
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.stereotype.Service; 
import scs.dao.jobSchedul.JobSchedulDao;
import scs.dao.recordManage.RecordManageDao; 
import scs.util.jobSchedul.JobSchedulDriver;
import scs.util.repository.Repository;
import scs.util.tools.HttpClientPool;


@Service
public class JobSchedulServiceImpl implements JobSchedulService {
	@Resource JobSchedulDao dao; 
	@Resource RecordManageDao rDao;
	JobSchedulDriver driver=JobSchedulDriver.getInstance();
	CloseableHttpClient httpclient=HttpClientPool.getInstance().getConnection();;
	

	@Override
	public int executeMemcachedApp(int isBase) {
		int result=0;
		if(driver.execute("memcached",isBase)!=0){//结果集不为0 代表执行成功,静态数组已经赋值,接下来插入数据库
			int testRecordId=rDao.getLatestRecordId();
			if(isBase==1){
				result=dao.addMemcachedData(Repository.memcachedBaseDataList,testRecordId,isBase);
			}else{
				result=dao.addMemcachedData(Repository.memcachedDataList,testRecordId,isBase);
			}
		}
		return result;
	}

	@Override
	public int executeWebSearchApp(int isBase) {
		int result=0;
		if(driver.execute("webSearch",isBase)!=0){
			int testRecordId=rDao.getLatestRecordId();
			if(isBase==1){
				result=dao.addWebSearchData(Repository.webSearchBaseDataList,testRecordId,isBase);
			}else{
				result=dao.addWebSearchData(Repository.webSearchDataList,testRecordId,isBase);
			}
		}
		return result;
	}

	@Override
	public int executeWebServerApp(int isBase) {
		int result=0;
		if(driver.execute("webServer",isBase)!=0){
			int testRecordId=rDao.getLatestRecordId();
			if(isBase==1){
				result=dao.addWebServerData(Repository.webServerBaseDataList,testRecordId,isBase);
			}else{
				result=dao.addWebServerData(Repository.webServerDataList,testRecordId,isBase);
			}
		}
		return result;
	}

	@Override
	public int executeSiloApp(int isBase) {
		int result=0;
		if(driver.execute("silo",isBase)!=0){
			int testRecordId=rDao.getLatestRecordId();
			if(isBase==1){
				result=dao.addSiloData(Repository.siloBaseDataList,testRecordId,isBase);
			}else{
				result=dao.addSiloData(Repository.siloDataList,testRecordId,isBase);
			}
		}
		return result;
	}
	@Override
	public int executeCassandraApp(int isBase) {
		int result=0;
		if(driver.execute("cassandra",isBase)!=0){
			int testRecordId=rDao.getLatestRecordId();
			if(isBase==1){
				result=dao.addCassandraData(Repository.cassandraBaseDataList,testRecordId,isBase);
			}else{
				result=dao.addCassandraData(Repository.cassandraDataList,testRecordId,isBase);
			}
		}
		return result;
	}
	@Override
	public int executeBonnieApp() {
		return driver.execute("bonnie",-1); 
	}

	@Override
	public int shutdownBonnieApp() {
		// TODO Auto-generated method stub
		return driver.shutdown("bonnie");
	}

	@Override
	public int executeScimarkApp() {
		return driver.execute("scimark",-1); 
	}

	@Override
	public int executeHadoopApp() {
		// TODO Auto-generated method stub
		return driver.execute("hadoop",-1);
	}

	@Override
	public int executeCassandraApp() {
		// TODO Auto-generated method stub
		return driver.execute("cassandra",-1);
	}

	@Override
	public String getAppStatus() {
		// TODO Auto-generated method stub
		return  Repository.getInstance().getAppStatus();
	}

	@Override
	public int getWebSearchQueryTime() {
		// TODO Auto-generated method stub
		return HttpClientPool.getResponseTime(httpclient,1);
	}

	@Override
	public int getWebServerQueryTime() {
		// TODO Auto-generated method stub
		return HttpClientPool.getResponseTime(httpclient,0);
	}

	
 
}

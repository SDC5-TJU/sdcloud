package scs.service.jobSchedul;
  
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource; 
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.stereotype.Service; 
import scs.dao.jobSchedul.JobSchedulDao;
import scs.dao.recordManage.RecordManageDao;
import scs.pojo.TwoTuple;
import scs.pojo.XapianDataBean;
import scs.util.jobSchedul.JobSchedulDriver; 
import scs.util.repository.Repository;
import scs.util.tools.HttpClientPool;
import scs.util.tools.ReadRiscvServiceDataFile;


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
	public int executeXapianApp(int isBase) {
		int result=0;
		if(driver.execute("xapian",isBase)!=0){
			int testRecordId=rDao.getLatestRecordId();
			if(isBase==1){
				result=dao.addXapianData(Repository.xapianBaseDataList,testRecordId,isBase);
			}else{
				result=dao.addXapianData(Repository.xapianDataList,testRecordId,isBase);
			}
		}
		return result;
	}
	@Override
	public int executeRedisApp(int isBase) {
		int result=0;
		if(driver.execute("redis",isBase)!=0){
			int testRecordId=rDao.getLatestRecordId();
			if(isBase==1){
				result=dao.addRedisData(Repository.redisBaseDataList,testRecordId,isBase);
			}else{
				result=dao.addRedisData(Repository.redisDataList,testRecordId,isBase);
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
	public int executeDwarfApp() {
		return driver.execute("dwarf",-1); 
	}

	@Override
	public int executeHadoopApp() {
		// TODO Auto-generated method stub
		return driver.execute("hadoop",-1);
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
	
	@Override
	public List<XapianDataBean> getRiscvXapianResult(String filePath){
		List<XapianDataBean> list=new ArrayList<XapianDataBean>();
		try {
			list=ReadRiscvServiceDataFile.getInstance().readXapianFile(filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	@Override
	public List<TwoTuple<Long, Integer>> getRiscvRedisResult(String filePath){
		List<TwoTuple<Long, Integer>> list=new ArrayList<TwoTuple<Long, Integer>>();
		try {
			list=ReadRiscvServiceDataFile.getInstance().readRedisFile(filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
 
}

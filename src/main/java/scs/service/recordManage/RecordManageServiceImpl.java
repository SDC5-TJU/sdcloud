package scs.service.recordManage;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import scs.pojo.AppConfigBean;
import scs.pojo.TestRecordBean;
import scs.util.repository.Repository;
import scs.dao.appConfig.AppConfigDao;
import scs.dao.recordManage.RecordManageDao;


@Service
public class RecordManageServiceImpl implements RecordManageService {
	@Resource RecordManageDao dao;
	@Resource AppConfigDao aDao;

	@Override
	public int addRecord(String recordDesc){
		// TODO Auto-generated method stub
		int latestRecordId=dao.getLatestRecordId();
		latestRecordId++;
		int result=dao.addRecord(latestRecordId,recordDesc);
		if(result!=0){
			Map<String,AppConfigBean> map=new HashMap<String,AppConfigBean>();
			AppConfigBean webSearch=new AppConfigBean("webSearch","autoStop","500","200","泊松","10",latestRecordId,1);
			AppConfigBean webServer=new AppConfigBean("webServer","autoStop","500","200","泊松","10",latestRecordId,1);
			AppConfigBean silo=new AppConfigBean("silo","autoStop","20000","20000","均匀","500",latestRecordId,1);
			AppConfigBean memcached=new AppConfigBean("memcached","autoStop","200000","200000","均匀","20000",latestRecordId,1);
			AppConfigBean hadoop=new AppConfigBean("hadoop","autoStop","","","IO密集型","4",latestRecordId,1);
			AppConfigBean cassandra=new AppConfigBean("cassandra","autoStop","","","","",latestRecordId,1);
			AppConfigBean bonnie=new AppConfigBean("bonnie","manuStop","","","循环","4096",latestRecordId,1);
			AppConfigBean scimark=new AppConfigBean("scimark","autoStop","","","循环","2",latestRecordId,1);
			map.put(webSearch.getApplicationName(),webSearch);
			map.put(webServer.getApplicationName(),webServer);
			map.put(silo.getApplicationName(),silo);
			map.put(memcached.getApplicationName(),memcached);
			map.put(hadoop.getApplicationName(),hadoop);
			map.put(cassandra.getApplicationName(),cassandra);
			map.put(bonnie.getApplicationName(),bonnie);
			map.put(scimark.getApplicationName(),scimark); 
			result=aDao.addAppConfig(map)==0?0:result;
			if(result!=0){ 
				Repository.appConfigMap=map;
				System.out.println("添加测试记录 appConfig size="+Repository.appConfigMap.size());
			}
		}
		return result;
	}

 

	@Override
	public int getRecordCount() {
		// TODO Auto-generated method stub
		return dao.getRecordCount();
	}

	@Override
	public List<TestRecordBean> searchRecord(int start, int pageSize) {
		// TODO Auto-generated method stub
		return dao.searchRecord(start,pageSize);
	}



	@Override
	public int modifyStartTime(int testRecordId,Date startTime) {
		// TODO Auto-generated method stub
		return dao.modifyStartTime(testRecordId, startTime);
	}



	@Override
	public int modifyEndTime(int testRecordId,Date startTime) {
		// TODO Auto-generated method stub
		return dao.modifyEndTime(testRecordId, startTime);
	}




}

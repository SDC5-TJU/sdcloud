package scs.service.recordManage;

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
			AppConfigBean webSearch=new AppConfigBean("webSearch","autoStop","2000","1000","泊松","10",latestRecordId,1);
			AppConfigBean webServer=new AppConfigBean("webServer","autoStop","2000","1000","泊松","10",latestRecordId,1);
			AppConfigBean memcached=new AppConfigBean("memcached","autoStop","200000","200000","均匀","20000",latestRecordId,1);
			AppConfigBean silo=new AppConfigBean("silo","autoStop","20000","20000","均匀","500",latestRecordId,1);
			AppConfigBean xapian=new AppConfigBean("xapian","autoStop","2000","2000","均匀","200",latestRecordId,1);
			AppConfigBean redis=new AppConfigBean("redis","autoStop","2000","2000","均匀","10",latestRecordId,1);
			AppConfigBean cassandra=new AppConfigBean("cassandra","autoStop","500","500","泊松","10",latestRecordId,1);
			
			AppConfigBean bonnie=new AppConfigBean("bonnie","manuStop","","","循环","4096",latestRecordId,1);
			AppConfigBean scimark=new AppConfigBean("scimark","autoStop","","","单次","4",latestRecordId,1);
			AppConfigBean hadoop=new AppConfigBean("hadoop","autoStop","","","IO密集型","4",latestRecordId,1);
			AppConfigBean dwarf=new AppConfigBean("dwarf","autoStop","","","单次","8",latestRecordId,1);
			
			map.put(webSearch.getApplicationName(),webSearch);
			map.put(webServer.getApplicationName(),webServer);
			map.put(silo.getApplicationName(),silo);
			map.put(xapian.getApplicationName(),xapian);
			map.put(memcached.getApplicationName(),memcached);
			map.put(redis.getApplicationName(),redis);
			map.put(cassandra.getApplicationName(),cassandra);
			map.put(bonnie.getApplicationName(),bonnie);
			map.put(scimark.getApplicationName(),scimark);
			map.put(hadoop.getApplicationName(),hadoop);
			map.put(dwarf.getApplicationName(),dwarf);
			
			result=aDao.addAppConfig(map)==0?0:result;
			if(result!=0){
				Repository.appConfigMap=map;//更新app配置数组
				Repository.curTestRecordId=latestRecordId;//更新当前测试记录
				System.out.println("添加测试记录 appConfig size="+Repository.appConfigMap.size()+"当前测试记录："+Repository.curTestRecordId);
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
	public String modifyStartTime(int testRecordId,String startTime) {
		// TODO Auto-generated method stub
		return dao.modifyStartTime(testRecordId, startTime);
	}



	@Override
	public String modifyEndTime(int testRecordId,String startTime) {
		// TODO Auto-generated method stub
		return dao.modifyEndTime(testRecordId, startTime);
	}



	@Override
	public TestRecordBean getRecordById(int testRecordId) {
		// TODO Auto-generated method stub
		return dao.getRecordById(testRecordId);
	}

 
}

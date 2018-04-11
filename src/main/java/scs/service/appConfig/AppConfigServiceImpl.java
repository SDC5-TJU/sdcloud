package scs.service.appConfig;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import scs.dao.appConfig.AppConfigDao;
import scs.pojo.AppConfigBean;
import scs.util.repository.Repository;


@Service
public class AppConfigServiceImpl implements AppConfigService {
	
	@Resource AppConfigDao dao;
	@Override
	public int addAppConfig(Map<String,AppConfigBean> map) {
		// TODO Auto-generated method stub
		return dao.addAppConfig(map);
	}

	@Override
	public int modifyAppConfig(Map<String,AppConfigBean> map) {
		// TODO Auto-generated method stub
		int result=dao.modifyAppConfig(map);
		if(result!=0){ 
			Repository.appConfigMap.clear();  
			Repository.appConfigMap=map;  
		}
		return result;
	}

	@Override
	public List<AppConfigBean> getAppConfig(int testRecordId) {
		// TODO Auto-generated method stub
		return dao.getAppConfig(testRecordId);
	}

  
}

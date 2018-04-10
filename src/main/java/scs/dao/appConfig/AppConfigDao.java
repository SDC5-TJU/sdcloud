package scs.dao.appConfig;

import java.util.List;
import java.util.Map;

import scs.pojo.AppConfigBean;

public interface AppConfigDao {
	public int addAppConfig(Map<String,AppConfigBean> map);
	public List<AppConfigBean> getAppConfig(int testRecordId);
	public int modifyAppConfig(Map<String,AppConfigBean> map);
}

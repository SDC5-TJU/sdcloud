package scs.service.appConfig;

import java.util.List;
import java.util.Map;

import scs.pojo.AppConfigBean;

/**
 * 登录service层接口
 * @author YangYanan
 * @desc
 * @date 2017-8-18
 */
public interface AppConfigService {
	public int addAppConfig(Map<String,AppConfigBean> map);
	public List<AppConfigBean> getAppConfig(int testRecordId);
	public int modifyAppConfig(Map<String,AppConfigBean> map);
}

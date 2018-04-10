package scs.service.containerDeploy;

import java.util.List;

import scs.pojo.ContainerInfoBean;

/**
 * 登录service层接口
 * @author YangYanan
 * @desc
 * @date 2017-8-18
 */
public interface ContainerDeployService {
	public List<ContainerInfoBean> searchContainerDeploy(int page);
	
}

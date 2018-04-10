package scs.dao.containerDeploy;

import java.util.List;

import scs.pojo.ContainerInfoBean;

public interface ContainerDeployDao {
	public List<ContainerInfoBean> searchContainerDeploy(int page);
}

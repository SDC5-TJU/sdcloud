package scs.dao.containerDeploy;
 

import java.util.List;

import org.springframework.stereotype.Repository;

import scs.pojo.ContainerInfoBean;
import scs.dao.MySQLBaseDao; 

@Repository
public class ContainerDeployDaoImpl extends MySQLBaseDao implements ContainerDeployDao {

	@Override
	public List<ContainerInfoBean> searchContainerDeploy(int page) {
		// TODO Auto-generated method stub
		return null;
	}
}

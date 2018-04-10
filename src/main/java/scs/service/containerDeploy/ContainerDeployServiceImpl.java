package scs.service.containerDeploy;

import java.util.List;

import javax.annotation.Resource; 

import org.springframework.stereotype.Service;

import scs.pojo.ContainerInfoBean;
import scs.dao.containerDeploy.ContainerDeployDao;


@Service
public class ContainerDeployServiceImpl implements ContainerDeployService {
	@Resource ContainerDeployDao dao;
	
	@Override
	public List<ContainerInfoBean> searchContainerDeploy(int page) {
		// TODO Auto-generated method stub
		return dao.searchContainerDeploy(page);
	}

  
}

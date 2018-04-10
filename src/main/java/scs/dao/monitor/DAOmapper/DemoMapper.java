package scs.dao.monitor.DAOmapper;

import org.apache.ibatis.annotations.Select;

public interface DemoMapper {
	
	@Select(value = "select count(*) from demoTable")
	public int DemoDaoMethod();
	
}

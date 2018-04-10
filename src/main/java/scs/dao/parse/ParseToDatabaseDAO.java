package scs.dao.parse;

import org.apache.ibatis.annotations.Insert;

public interface ParseToDatabaseDAO {
	
	@Insert(value="insert into FILE_TABLE values()")
	public int saveOneRecordMethod(double[] data);
	
}

package scs.service.parse;

import scs.util.parse.LatsTxtParser;

public class ParseServiceImpl implements ParseService {
	
	//入库的dao层对象
	
	@Override
	public boolean save(String filePath,int rows) {
		LatsTxtParser.parse(filePath, rows);
		return false;
	}

	@Override
	public double[][] getData(String filePath,int rows) {
		double[][] parse = LatsTxtParser.parse(filePath, rows);
		return parse == null ? null : parse;
	}

}

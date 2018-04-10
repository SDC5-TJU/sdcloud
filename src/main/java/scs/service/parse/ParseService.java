package scs.service.parse;


public interface ParseService {
	
	/**
	 * 将从lats.txt解析完成的二维数据，保存入库
	 * @author jztong
	 * @param data：插入的数据
	 * @return 插入操作的执行结果
	 */
	public boolean save(String filePath,int rows);
	
	/**
	 * 将从lats.txt解析完成的二维数据直接返回
	 * @author jztong
	 * @return 二维数组数据
	 */
	public double[][] getData(String filePath,int rows);
	
}

package scs.util.jobSchedul.jobImpl.memcached;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;  
import scs.pojo.MemcachedDataBean;
import scs.util.repository.Repository; 

public class ResultProcess {	 

	private static ResultProcess resultProcess=null;
	private ResultProcess(){}
	public synchronized static ResultProcess getInstance() {
		if (resultProcess == null) {  
			resultProcess = new ResultProcess();
		}  
		return resultProcess;
	} 
	/**
	 * 结果处理函数
	 * @param fileName 读取的结果文件名称
	 * @param warmUpLineCount 预测次数,结果中要减去的
	 * @return 结果数
	 * @throws IOException
	 */
	public int process(String fileName,int warmUpLineCount,int isBase) throws IOException {  
		int resultSize=0;
		warmUpLineCount++;//抛掉第一行 timediff异常数据值
		File file = new File(fileName);  
		int parseLineNum=0;
		BufferedReader reader = null;  
		boolean flag=false;
		try {  
			reader = new BufferedReader(new FileReader(file)); 
			String tempString = null; 
			String[] numbers=new String[15];
			while ((tempString = reader.readLine()) != null) {  
				if(flag==true) {
					parseLineNum++;
					if(parseLineNum>warmUpLineCount){//抛掉预热值
						numbers=tempString.split(","); 
						if(numbers.length==15){
							MemcachedDataBean bean=new MemcachedDataBean();
							bean.setTimeDiff(Float.parseFloat(numbers[0]));
							bean.setRps(Float.parseFloat(numbers[1]));
							bean.setRequests(Float.parseFloat(numbers[2]));
							bean.setGets(Float.parseFloat(numbers[3]));
							bean.setSets(Float.parseFloat(numbers[4]));
							bean.setHits(Float.parseFloat(numbers[5]));
							bean.setMisses(Float.parseFloat(numbers[6]));
							bean.setAvg_lat(Float.parseFloat(numbers[7]));
							bean.setNintyTh(Float.parseFloat(numbers[8]));
							bean.setNintyFiveTh(Float.parseFloat(numbers[9]));
							bean.setNintyNineTh(Float.parseFloat(numbers[10]));
							bean.setStd(Float.parseFloat(numbers[11]));
							bean.setMin(Float.parseFloat(numbers[12]));
							bean.setMax(Float.parseFloat(numbers[13]));
							bean.setAvgGetSize(Float.parseFloat(numbers[14]));
							if(isBase==1){ 
								Repository.memcachedBaseDataList.add(bean);//如果是基准测试 
							}else{ 
								Repository.memcachedDataList.add(bean);//不是基准测试
							}
							resultSize++; 
						}
					}
					flag=false;
				}
				if(tempString.trim().startsWith("time")) {
					flag=true;
				}
			} 
		} catch (IOException e) {  
			e.printStackTrace();  
		} finally {  
			reader.close();   
		}
		return resultSize;  

	}  

}

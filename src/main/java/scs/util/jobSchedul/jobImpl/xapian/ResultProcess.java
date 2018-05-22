package scs.util.jobSchedul.jobImpl.xapian;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException; 

import scs.pojo.XapianDataBean;
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

	public int process(String filePath,int isBase) throws IOException {  
		int resultSize=0;
		/**
		 * 读取lats1.txt
		 */
		File file = new File(filePath); 
		BufferedReader reader = null;
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
			reader = new BufferedReader(inputStreamReader);
			String line = "";
			String split = "\\|";  
			/*
			 * 跳过前两行
			 */
			reader.readLine();
			reader.readLine();
			while ((line = reader.readLine()) != null) {  
				String[] lineArr = line.split(split);
				if(lineArr.length==3){
					XapianDataBean bean=new XapianDataBean(Float.parseFloat(lineArr[0]),Float.parseFloat(lineArr[1]),Float.parseFloat(lineArr[2]));
					if(isBase==1){ 
						Repository.xapianBaseDataList.add(bean);//如果是基准测试 
					}else{ 
						Repository.xapianDataList.add(bean);//不是基准测试
					} 
					resultSize++;
				}

			}


			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			/**
			 * 读取第二个lats2.txt
			 */
			filePath=filePath.replace("1","2");
			file = new File(filePath); 
			System.out.println(filePath);
			fileInputStream = new FileInputStream(file);
			inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
			reader = new BufferedReader(inputStreamReader);
			/*
			 * 跳过前两行
			 */
			reader.readLine();
			reader.readLine();
			while ((line = reader.readLine()) != null) {  
				String[] lineArr = line.split(split);
				if(lineArr.length==3){
					XapianDataBean bean=new XapianDataBean(Float.parseFloat(lineArr[0]),Float.parseFloat(lineArr[1]),Float.parseFloat(lineArr[2]));
					if(isBase==1){ 
						Repository.xapianBaseDataList.add(bean);//如果是基准测试 
					}else{ 
						Repository.xapianDataList.add(bean);//不是基准测试
					} 
					resultSize++;
				}

			}

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return resultSize;
	}  

}

package scs.util.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import scs.pojo.TwoTuple;
import scs.pojo.XapianDataBean; 
/**
 * 供X86下的sdcloud系统使用
 * @author yanan
 *
 */
public class ReadRiscvServiceDataFile {
	private static ReadRiscvServiceDataFile readFile=null;
	private ReadRiscvServiceDataFile(){}
	public synchronized static ReadRiscvServiceDataFile getInstance() {
		if (readFile == null) {  
			readFile = new ReadRiscvServiceDataFile();
		}  
		return readFile;
	} 
	/**
	 * 单纯读取xapian的lats.txt文件
	 * @param filePath
	 * @return 返回list数组
	 * @throws IOException
	 */
	public List<XapianDataBean> readXapianFile(String filePath) throws IOException {  
		List<XapianDataBean> list=new ArrayList<XapianDataBean>();

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
					list.add(bean);
				}
			}

			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
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
		return list;
	}  
	/**
	 * 单纯读取redis的lats.txt文件
	 * @param filePath
	 * @return 返回list数组
	 * @throws IOException
	 */
	public List<TwoTuple<Long,Integer>> readRedisFile(String filePath) throws IOException {  
		List<TwoTuple<Long,Integer>> list=new ArrayList<TwoTuple<Long,Integer>>();

		File file = new File(filePath); 
		BufferedReader reader = null;
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
			reader = new BufferedReader(inputStreamReader);
			String line = "";
			long i=0L;
			while ((line = reader.readLine()) != null) {  
				i++;
				TwoTuple<Long,Integer> time=new TwoTuple<Long,Integer>(i,Integer.parseInt(line.trim()));
				list.add(time);
			}

			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
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
		return list;
	}  
	/**
	 * 读取xapian和redis的qps文件
	 * @param filePath
	 * @return 返回list数组
	 * @throws IOException
	 */
	public String readQpsFile(String filePath) throws IOException {  
		String line ="-1";
		File file = new File(filePath); 
		BufferedReader reader = null;
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
			reader = new BufferedReader(inputStreamReader);
			line= reader.readLine();

			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
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
		return line;
	}  
	
}

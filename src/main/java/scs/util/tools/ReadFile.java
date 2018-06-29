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

import scs.pojo.CDFBean;
import scs.pojo.TwoTuple;
import scs.pojo.XapianDataBean; 
/**
 * 供X86下的sdcloud系统使用
 * @author yanan
 *
 */
public class ReadFile {
	private static ReadFile readFile=null;
	private ReadFile(){}
	public synchronized static ReadFile getInstance() {
		if (readFile == null) {  
			readFile = new ReadFile();
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
	 * 单纯读取redis的qps文件
	 * @param filePath
	 * @return 返回list数组
	 * @throws IOException
	 */
	public String readRedisQpsFile(String filePath) throws IOException {  
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
	
	public CDFBean readCDFFile(String cdfFilePath, String cdf_x86FilePath, String connFilePath, String conn_x86FilePath) throws IOException {  
		List<Float> list0 = new ArrayList<Float>();
		List<Float> list1 = new ArrayList<Float>();
		List<Float> list2 = new ArrayList<Float>();
		List<Float> list3 = new ArrayList<Float>();

		List<Float> list_x860 = new ArrayList<Float>();
		List<Float> list_x861 = new ArrayList<Float>();
		List<Float> list_x862 = new ArrayList<Float>();
        List<Float> list_x863 = new ArrayList<Float>();

		File file1 = new File(cdfFilePath); 
        File file2 = new File(cdf_x86FilePath); 
        File file3 = new File(connFilePath); 
        File file4 = new File(conn_x86FilePath); 
		BufferedReader reader1 = null;
		BufferedReader reader2 = null;
		BufferedReader reader3 = null;
		BufferedReader reader4 = null;
        float conn=0, conn_x86=0;
                  
		try {
			FileInputStream fileInputStream1 = new FileInputStream(file1);
			InputStreamReader inputStreamReader1 = new InputStreamReader(fileInputStream1, "UTF-8");
			reader1 = new BufferedReader(inputStreamReader1);

			FileInputStream fileInputStream2 = new FileInputStream(file2);
			InputStreamReader inputStreamReader2 = new InputStreamReader(fileInputStream2, "UTF-8");
			reader2 = new BufferedReader(inputStreamReader2);

			FileInputStream fileInputStream3 = new FileInputStream(file3);
			InputStreamReader inputStreamReader3 = new InputStreamReader(fileInputStream3, "UTF-8");
			reader3 = new BufferedReader(inputStreamReader3);
			
            FileInputStream fileInputStream4 = new FileInputStream(file4);
			InputStreamReader inputStreamReader4 = new InputStreamReader(fileInputStream4, "UTF-8");
			reader4 = new BufferedReader(inputStreamReader4);

			String line = "";
			String split = "(:|;)( )*";  
			/*
			 * 璺宠繃鍓嶄袱琛?
			 */
			//reader.readLine();
			//reader.readLine();
                  //read cdf file
			while ((line = reader1.readLine()) != null) {  
				String[] lineArr = line.split(split);
				if(lineArr.length==4){
					Float col0 = Float.parseFloat(lineArr[0]);
                    Float col1 = Float.parseFloat(lineArr[1]);
                    Float col2 = Float.parseFloat(lineArr[2]);
				    Float col3 = Float.parseFloat(lineArr[3]);
					list0.add(col0);
					list1.add(col1);
					list2.add(col2);
					list3.add(col3);

				}
			}
		      
			if (reader1 != null) {
				try {
					reader1.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

                  //read cdf_x86 file
			while ((line = reader2.readLine()) != null) {  
				String[] lineArr = line.split(split);
				if(lineArr.length==4){
					Float col0 = Float.parseFloat(lineArr[0]);
                    Float col1 = Float.parseFloat(lineArr[1]);
                    Float col2 = Float.parseFloat(lineArr[2]);
				    Float col3 = Float.parseFloat(lineArr[3]);
					list_x860.add(col0);
					list_x861.add(col1);
					list_x862.add(col2);
					list_x863.add(col3);

				}
			}
		      
			if (reader2 != null) {
				try {
					reader2.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

                  //read connection file
			if ((line = reader3.readLine()) != null) {  				            
				conn = Float.parseFloat(line);
			}
		      
			if (reader3 != null) {
				try {
					reader3.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}


                  //read connection_x86 file
			if ((line = reader4.readLine()) != null) {  				            
				conn_x86 = Float.parseFloat(line);
			}
		      
			if (reader4 != null) {
				try {
					reader4.close();
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
			if (reader1 != null || reader2 != null  || reader3 != null) {
				try {
					reader1.close();
					reader2.close();
					reader3.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		float[][] flist = new float[8][list0.size()];
		for(int i = 0; i < list0.size(); i++){
			flist[0][i] = list0.get(i).floatValue();
			flist[1][i] = list1.get(i).floatValue();
			flist[2][i] = list2.get(i).floatValue();
			flist[3][i] = list3.get(i).floatValue();
			
            flist[4][i] = list_x860.get(i).floatValue();
			flist[5][i] = list_x861.get(i).floatValue();
			flist[6][i] = list_x862.get(i).floatValue();
			flist[7][i] = list_x863.get(i).floatValue();
		}
		
		CDFBean cdf = new CDFBean(flist[0], flist[2], flist[3], conn, flist[6], flist[7], conn_x86);
		
		return cdf;
	}  
	 
}

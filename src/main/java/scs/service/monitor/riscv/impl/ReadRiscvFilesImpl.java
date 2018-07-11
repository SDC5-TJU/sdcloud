package scs.service.monitor.riscv.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map; 

import org.springframework.stereotype.Service;

import scs.pojo.riscv.CDFBean;
import scs.pojo.riscv.DataProject2Bean;
import scs.pojo.riscv.RiscvLLCGroup;
import scs.pojo.riscv.RiscvLLCPOJO;
import scs.pojo.riscv.RiscvRedisRealDataBean;
import scs.service.monitor.riscv.ReadRiscvFiles;
import scs.util.repository.Repository;

@Service("RiscvFileMonitor")
public class ReadRiscvFilesImpl implements ReadRiscvFiles {

	@Override
	public double readRiscvLatestResourceUsageFile(String filePath) { 
		BufferedReader readWorker = null;
		double parseDouble = 0;
		try {
			readWorker = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
			String lastLine = "";
			String mString;

			try {
				while ((mString = readWorker.readLine()) != null) {
					lastLine = mString;
				}
				parseDouble = Double.parseDouble(lastLine);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			System.out.println("没有该文件");
			e.printStackTrace();
		}

		return parseDouble;
	}

	@Override
	public ArrayList<Double> readRiscvWindowResourceUsageFile(String filePath) {
		int totalLine=0;
		BufferedReader readWorker = null;

		try {
			readWorker = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
			String mString="";

			try {
				while ((mString = readWorker.readLine()) != null) {
					totalLine++;
				} 
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			System.out.println("没有该文件");
			e.printStackTrace();
		}
		ArrayList<Double> result = new ArrayList<>();
		int startLine=totalLine-Repository.windowSize+1;
		try {
			readWorker = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
			String mString="";
			int currentLine=0;
			try {
				while ((mString = readWorker.readLine()) != null) {
					currentLine++;
					if(currentLine>=startLine){
						result.add(Double.parseDouble(mString));
						if(result.size()==Repository.windowSize){
							break;
						}
					}
				} 
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			System.out.println("没有该文件");
			e.printStackTrace();
		}
		//		if (filePath == null || filePath == "") {
		//			System.out.println("没有参数");
		//		} else if (!filePath.startsWith("/")) {
		//			System.out.println("不合法的目录");
		//		}
		//
		//		if (!filePath.endsWith("/")) {
		//			filePath += "/";
		//		}

		return result;
	}



	@Override
	public ArrayList<RiscvLLCGroup> readLLC(String filePath, boolean readSixty) {

		int totalLine=0;
		BufferedReader readWorker = null;
		try {
			readWorker = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
			String mString = "";
			try {
				while ((mString = readWorker.readLine()) != null) {
					totalLine++;
				}
				if (totalLine % 4 != 0) {
					System.out.println("不是4的倍数，llc数据文件有错误");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			System.out.println("没有该文件");
			e.printStackTrace();
		}
		int startLine = totalLine - 3;
		if (readSixty == true && totalLine > (Repository.windowSize<<2)) {
			startLine = totalLine -(Repository.windowSize<<2)+1;
		}else if (readSixty == true) {
			System.out.println("不够60个点");
		}
		RiscvLLCGroup group = new RiscvLLCGroup();
		ArrayList<RiscvLLCGroup> list = new ArrayList<>();
		try {
			readWorker = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
			String mString="";
			int currentLine=0;

			try {
				RiscvLLCPOJO llcpojo = new RiscvLLCPOJO();
				Map<Integer,RiscvLLCPOJO> dataMap = new HashMap<>();
				while ((mString = readWorker.readLine()) != null) {
					currentLine++;
					if(currentLine>=startLine){
						String[] dataArr = mString.split(",");
						if (dataArr[0].equals("0") || dataArr[0].equals("1") || dataArr[0].equals("2")) {
							llcpojo.setBandwidth(Double.parseDouble(dataArr[1]));
							llcpojo.setLlcRequest(Double.parseDouble(dataArr[2]));
							llcpojo.setLlcMisses(Double.parseDouble(dataArr[3]));
							if(dataArr[4]==null||dataArr[4].equals("nan")){
								llcpojo.setMissesPercent(0);
							}else{
								llcpojo.setMissesPercent(Double.parseDouble(dataArr[4]));
							}
							llcpojo.setLlcUsedCapacity(Double.parseDouble(dataArr[5]));
							llcpojo.setLlcUsedPercent(Double.parseDouble(dataArr[6]));
							dataMap.put(Integer.parseInt(dataArr[0]), llcpojo);			
						}else if (dataArr[0].equals("3")) {
							llcpojo.setBandwidth(Double.parseDouble(dataArr[1]));
							llcpojo.setLlcRequest(Double.parseDouble(dataArr[2]));
							llcpojo.setLlcMisses(Double.parseDouble(dataArr[3]));
							if(dataArr[4]==null||dataArr[4].equals("nan")){
								llcpojo.setMissesPercent(0);
							}else{
								llcpojo.setMissesPercent(Double.parseDouble(dataArr[4]));
							}
							llcpojo.setLlcUsedCapacity(Double.parseDouble(dataArr[5]));
							llcpojo.setLlcUsedPercent(Double.parseDouble(dataArr[6]));
							dataMap.put(Integer.parseInt(dataArr[0]), llcpojo);
							group.setMap(dataMap);
							list.add(group);
							dataMap = new HashMap<>();
							llcpojo = new RiscvLLCPOJO();
							group = new RiscvLLCGroup();
						}
					}
				} 
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			System.out.println("没有该文件");
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public RiscvRedisRealDataBean readRiscvLatestQueryTimeFile(String filePath) {  
		RiscvRedisRealDataBean bean=new RiscvRedisRealDataBean();
		BufferedReader readWorker = null;
		try {
			readWorker = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
			String lastLine = "";
			String mString; 
			try {
				while ((mString = readWorker.readLine()) != null) {
					lastLine = mString;
				} 
				String[] split=lastLine.split(",");
				bean.setQps((int)Float.parseFloat(split[0].trim()));
				bean.setMean((int)Float.parseFloat(split[1].trim()));
				bean.setMin(Integer.parseInt(split[2].trim()));
				bean.setFiftyTh(Integer.parseInt(split[3].trim()));
				bean.setEightyTh(Integer.parseInt(split[4].trim()));
				bean.setNintyTh(Integer.parseInt(split[5].trim()));
				bean.setNintyNineTh(Integer.parseInt(split[6].trim()));
				bean.setNintyNinePointNineTh(Integer.parseInt(split[7].trim()));
				bean.setMax(Integer.parseInt(split[8].trim())); 
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			System.out.println("没有该文件");
			e.printStackTrace();
		}

		return bean;
	}

	@Override
	public ArrayList<RiscvRedisRealDataBean> readRiscvWindowQueryTimeFile(String filePath) {
		int totalLine=0;
		BufferedReader readWorker = null;
		try {
			readWorker = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
			String mString="";
			try {
				while ((mString = readWorker.readLine()) != null) {
					totalLine++;
				} 
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			System.out.println("没有该文件");
			e.printStackTrace();
		}
		ArrayList<RiscvRedisRealDataBean> result = new ArrayList<RiscvRedisRealDataBean>();
		int startLine=totalLine-Repository.windowSize+1;
		try {
			readWorker = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
			String mString="";
			int currentLine=0;
			try {
				while ((mString = readWorker.readLine()) != null) {
					currentLine++;
					if(currentLine>=startLine){
						RiscvRedisRealDataBean bean=new RiscvRedisRealDataBean();
						String[] split=mString.split(",");
						bean.setQps((int)Float.parseFloat(split[0].trim()));
						bean.setMean((int)Float.parseFloat(split[1].trim()));
						bean.setMin(Integer.parseInt(split[2].trim()));
						bean.setFiftyTh(Integer.parseInt(split[3].trim()));
						bean.setEightyTh(Integer.parseInt(split[4].trim()));
						bean.setNintyTh(Integer.parseInt(split[5].trim()));
						bean.setNintyNineTh(Integer.parseInt(split[6].trim()));
						bean.setNintyNinePointNineTh(Integer.parseInt(split[7].trim()));
						bean.setMax(Integer.parseInt(split[8].trim()));
						result.add(bean); 
						if(result.size()==Repository.windowSize){
							break;
						}
					}
				} 
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			System.out.println("没有该文件");
			e.printStackTrace();
		} 
		return result;
	} 
	
	@Override
	public DataProject2Bean readRiscvLatestDataFile(String filePath) {
		// TODO Auto-generated method stub
		DataProject2Bean bean=new DataProject2Bean();
		BufferedReader readWorker = null;
		try {
			readWorker = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
			String lastLine = "";
			String mString; 
			try {
				while ((mString = readWorker.readLine()) != null) {
					lastLine = mString;
				} 
				String[] split=lastLine.split(",");
				bean.setCeph(Float.parseFloat(split[0].trim()));
				bean.setGecko(Float.parseFloat(split[1].trim()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			System.out.println("没有该文件");
			e.printStackTrace();
		}
	
		return bean;
	}

	@Override
	public ArrayList<DataProject2Bean> readRiscvWindowDataFile(String filePath) {
		int totalLine=0;
		BufferedReader readWorker = null;
		try {
			readWorker = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
			String mString="";
			try {
				while ((mString = readWorker.readLine()) != null) {
					totalLine++;
				} 
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			System.out.println("没有该文件");
			e.printStackTrace();
		}
		ArrayList<DataProject2Bean> result = new ArrayList<DataProject2Bean>();
		int startLine=totalLine-Repository.windowSize+1;
		try {
			readWorker = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
			String mString="";
			int currentLine=0;
			try {
				while ((mString = readWorker.readLine()) != null) {
					currentLine++;
					if(currentLine>=startLine){
						DataProject2Bean bean=new DataProject2Bean();
						String[] split=mString.split(",");
						bean.setCeph(Float.parseFloat(split[0].trim()));
						bean.setGecko(Float.parseFloat(split[1].trim()));
						result.add(bean); 
						if(result.size()==Repository.windowSize){
							break;
						}
					}
				} 
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			System.out.println("没有该文件");
			e.printStackTrace();
		} 
		return result;
	}

	@Override
	public ArrayList<DataProject2Bean> readx86WindowDataFile(String filePath) {
		int totalLine=0;
		BufferedReader readWorker = null;
		try {
			readWorker = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
			String mString="";
			try {
				while ((mString = readWorker.readLine()) != null) {
					totalLine++;
				} 
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			System.out.println("没有该文件");
			e.printStackTrace();
		}
		ArrayList<DataProject2Bean> result = new ArrayList<DataProject2Bean>();
		int startLine=totalLine-Repository.windowSize*10+1;
		try {
			readWorker = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
			String mString="";
			int currentLine=0;
			try {
				while ((mString = readWorker.readLine()) != null) {
					currentLine++;
					if(currentLine>=startLine){
						DataProject2Bean bean=new DataProject2Bean();
						String[] split=mString.split(",");
						bean.setCeph(Float.parseFloat(split[0].trim()));
						bean.setTnumCeph(Integer.parseInt(split[1].trim()));
						bean.setGecko(Float.parseFloat(split[2].trim()));
						bean.setTnumGecko(Integer.parseInt(split[3].trim()));
						result.add(bean); 
						if(result.size()==Repository.windowSize*10){
							break;
						}
					}
				} 
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			System.out.println("没有该文件");
			e.printStackTrace();
		} 
		return result;
	}

	@Override
	public DataProject2Bean readx86LatestDataFile(String filePath) {
		// TODO Auto-generated method stub
		DataProject2Bean bean=new DataProject2Bean();
		BufferedReader readWorker = null;
		try {
			readWorker = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
			String lastLine = "";
			String mString; 
			try {
				while ((mString = readWorker.readLine()) != null) {
					lastLine = mString;
				} 
				String[] split=lastLine.split(",");
				bean.setCeph(Float.parseFloat(split[0].trim()));
				bean.setTnumCeph(Integer.parseInt(split[1].trim()));
				bean.setGecko(Float.parseFloat(split[2].trim()));
				bean.setTnumGecko(Integer.parseInt(split[3].trim()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			System.out.println("没有该文件");
			e.printStackTrace();
		}
		return bean;
	}
	/**
	 * 课题3
	 * @param cdfFilePath
	 * read cdf(high, low
	 * @return float[3][15]: cdf, high, low
	 */
	@Override
	public float[][] readRiscvCDFfile(String cdfFilePath) {  
		List<Float> list0 = new ArrayList<Float>(15);
		List<Float> list1 = new ArrayList<Float>(15);
		List<Float> list2 = new ArrayList<Float>(15);
		List<Float> list3 = new ArrayList<Float>(15);


		float[][] flist = new float[3][15]; 
		 
		File file1 = new File(cdfFilePath); 
		BufferedReader reader1 = null;
        
                  
		try {
			FileInputStream fileInputStream1 = new FileInputStream(file1);
			InputStreamReader inputStreamReader1 = new InputStreamReader(fileInputStream1, "UTF-8");
			reader1 = new BufferedReader(inputStreamReader1);

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
			for(int i = 0; i < list0.size(); i++){
				flist[0][i] = list0.get(i).floatValue();
				//flist[1][i] = list1.get(i).floatValue();
				flist[1][i] = list2.get(i).floatValue();
				flist[2][i] = list3.get(i).floatValue();
			}
			if (reader1 != null) {
				try {
					reader1.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
					
			//CDFBean cdf = new CDFBean(flist[0], flist[2], flist[3], conn, flist[6], flist[7], conn_x86);
			
			return flist;
		}


	}  

	
	/**
	 * 课题3
	 * @param connFilePath
	 * read connection
	 * @return int connection
	 */
	@Override
	public float readRiscvConnfile(String connFilePath) {  
		float conn=0; 
		File file1 = new File(connFilePath); 
		BufferedReader reader1 = null;
        String line = "";
                  
		try {
			FileInputStream fileInputStream1 = new FileInputStream(file1);
			InputStreamReader inputStreamReader1 = new InputStreamReader(fileInputStream1, "UTF-8");
			reader1 = new BufferedReader(inputStreamReader1);
 
			      //read connection file
			if ((line = reader1.readLine()) != null) {  				            
				conn = Integer.parseInt(line);
			}
		      

		      
			if (reader1 != null) {
				try {
					reader1.close();
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
			if (reader1 != null) {
				try {
					reader1.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
					
			//CDFBean cdf = new CDFBean(flist[0], flist[2], flist[3], conn, flist[6], flist[7], conn_x86);
			
			return conn;
		}


	}  
	/**
	 * 单元测试主函数
	 * @param args
	 */
	public static void main(String[] args) {
		//ReadRiscvFilesImpl impl=new ReadRiscvFilesImpl();  
	}
	

	
	 
}

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
		int startLine=totalLine-59;
		try {
			readWorker = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
			String mString="";
			int currentLine=0;
			try {
				while ((mString = readWorker.readLine()) != null) {
					currentLine++;
					if(currentLine>=startLine){
						result.add(Double.parseDouble(mString));
						if(result.size()==60){
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
		if (readSixty == true && totalLine > 240) {
			startLine = totalLine - 239;
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
		int startLine=totalLine-59;
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
						if(result.size()==60){
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
		int startLine=totalLine-59;
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
						if(result.size()==60){
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

	/**
	 * 课题3
	 * @param cdfFilePath
	 * @param cdf_x86FilePath
	 * @param connFilePath
	 * @param conn_x86FilePath
	 * @return
	 */
	@Override
	public CDFBean readRiscvCDFfile(String cdfFilePath, String cdf_x86FilePath, String connFilePath, String conn_x86FilePath) {  
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
	 
	
	/**
	 * 单元测试主函数
	 * @param args
	 */
	public static void main(String[] args) {
		//ReadRiscvFilesImpl impl=new ReadRiscvFilesImpl();  
	}
}

package scs.service.monitor.riscv.impl;

import java.io.BufferedReader; 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader; 
import java.util.ArrayList;
import java.util.HashMap; 
import java.util.Map; 

import org.springframework.stereotype.Service;

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
				bean.setQps(Integer.parseInt(split[0].trim()));
				bean.setMean(Integer.parseInt(split[1].trim()));
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
						bean.setQps(Integer.parseInt(split[0].trim()));
						bean.setMean(Integer.parseInt(split[1].trim()));
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
	public ArrayList<DataProject2Bean> readRiscvWindowDataFile(String filePath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataProject2Bean readRiscvLatestDataFile(String filePath) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * 单元测试主函数
	 * @param args
	 */
	public static void main(String[] args) {
		ReadRiscvFilesImpl impl=new ReadRiscvFilesImpl();  
	}
}

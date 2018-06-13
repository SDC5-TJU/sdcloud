package scs.service.monitor.riscv.impl;
 
import java.io.BufferedReader; 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.stereotype.Service;

import scs.pojo.RiscvLLCGroup;
import scs.pojo.RiscvLLCPOJO;
import scs.service.monitor.riscv.ReadRiscvFiles;

@Service("RiscvFileMonitor")
public class ReadRiscvFilesImpl implements ReadRiscvFiles {

	@Override
	public double readRiscvMemory(String filePath) {
//		if (filePath == null || filePath == "") {
//			System.out.println("没有参数");
//		} else if (!filePath.startsWith("/")) {
//			System.out.println("不合法的目录");
//		}
//
//		if (!filePath.endsWith("/")) {
//			filePath += "/";
//		}
//
//		filePath += "mem.csv";

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
	public ArrayList<Double> read60(String filePath) {
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
	
	
	
	public static void main(String[] args) {
//		System.out.println(new ReadRiscvFilesImpl().readRiscvMemory("H://cpu_usage.csv"));
		
		//测试llc & bandwidth
//		ArrayList<RiscvLLCGroup> readLLC = new ReadRiscvFilesImpl().readLLC("/Users/jztong/Desktop/llc_mb.csv", false);
//		Map<Integer, RiscvLLCPOJO> map = readLLC.get(0).getMap();
//		Set<Entry<Integer, RiscvLLCPOJO>> entrySet = map.entrySet();
//		Iterator<Entry<Integer, RiscvLLCPOJO>> iterator = entrySet.iterator();
//		while(iterator.hasNext()){
//			Entry<Integer, RiscvLLCPOJO> next = iterator.next();
//			System.out.println(next.getKey());
//			System.out.println(next.getValue().toString());
//		}
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
							llcpojo.setMissesPercent(Double.parseDouble(dataArr[4]));
							llcpojo.setLlcUsedCapacity(Double.parseDouble(dataArr[5]));
							llcpojo.setLlcUsedPercent(Double.parseDouble(dataArr[6]));
							dataMap.put(Integer.parseInt(dataArr[0]), llcpojo);			
						}else if (dataArr[0].equals("3")) {
							llcpojo.setBandwidth(Double.parseDouble(dataArr[1]));
							llcpojo.setLlcRequest(Double.parseDouble(dataArr[2]));
							llcpojo.setLlcMisses(Double.parseDouble(dataArr[3]));
							llcpojo.setMissesPercent(Double.parseDouble(dataArr[4]));
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
	
}

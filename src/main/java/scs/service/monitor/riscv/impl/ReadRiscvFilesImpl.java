package scs.service.monitor.riscv.impl;
 
import java.io.BufferedReader; 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader; 
import java.util.ArrayList;
import org.springframework.stereotype.Service;

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
		System.out.println(new ReadRiscvFilesImpl().readRiscvMemory("H://cpu_usage.csv"));
		 
	}

}

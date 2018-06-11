package scs.service.monitor.riscv.impl;

import java.awt.RadialGradientPaint;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

import scs.service.monitor.riscv.ReadRiscvFiles;

@Service("RiscvFileMonitor")
public class ReadRiscvFilesImpl implements ReadRiscvFiles {

	@Override
	public double readRiscvMemory(String filePath) {
		if (filePath == null || filePath == "") {
			System.out.println("没有参数");
		} else if (!filePath.startsWith("/")) {
			System.out.println("不合法的目录");
		}

		if (!filePath.endsWith("/")) {
			filePath += "/";
		}

		filePath += "mem.csv";

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
		if (filePath == null || filePath == "") {
			System.out.println("没有参数");
		} else if (!filePath.startsWith("/")) {
			System.out.println("不合法的目录");
		}

		if (!filePath.endsWith("/")) {
			filePath += "/";
		}
		RandomAccessFile file = null;
		ArrayList<Double> result = new ArrayList<>();
		try {
			file = new RandomAccessFile(filePath + "mem.csv", "r");
			long len = file.length();
			long start = file.getFilePointer();
			long nextend = start + len - 1;
			String line;
			int count = 0;
			file.seek(nextend);
			int c = -1;
			while (nextend > start) {
				c = file.read();
				if (c == '\n' || c == '\r') {
					line = file.readLine();
					if (line != null && count < 60) {
						count++;
						result.add(Double.parseDouble(line));
					} else {
						break;
					}
					nextend--;
				}
				nextend--;
				file.seek(nextend);
				if (nextend == 0) {// 当文件指针退至文件开始处，输出第一行
					line = file.readLine();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (file != null) file.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
//	public static void main(String[] args) {
//		ArrayList<Double> readRiskvMemory = new ReadRiskvFilesImpl().read60("/Users/jztong/Desktop/");
//		System.out.println(readRiskvMemory.size());
//		for (Double double1 : readRiskvMemory) {
//			System.out.println(double1);
//		}
//	}

}

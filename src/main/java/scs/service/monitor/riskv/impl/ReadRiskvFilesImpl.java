package scs.service.monitor.riskv.impl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import scs.service.monitor.riskv.ReadRiskvFiles;

public class ReadRiskvFilesImpl implements ReadRiskvFiles {

	@Override
	public double readRiskvMemory(String filePath) {
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
	
//	public static void main(String[] args) {
//		double readRiskvMemory = new ReadRiskvFilesImpl().readRiskvMemory("/Users/jztong/Desktop/");
//		System.out.println(readRiskvMemory);
//	}

}

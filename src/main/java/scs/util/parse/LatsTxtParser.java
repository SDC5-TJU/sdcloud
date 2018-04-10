package scs.util.parse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import org.junit.Test;

public class LatsTxtParser {
	public static double[][] parse(String filePath, int rows) {

		if (filePath == null || filePath == "") {
			return null;
		}

		File file = new File(filePath);
		if (!file.exists()) {
			return null;
		}
		BufferedReader reader = null;
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
			reader = new BufferedReader(inputStreamReader);
			String line = "";
			String split = "\\|";
			double[][] data = new double[rows][3];
			int row = 0;
			/*
			 * 跳过前两行
			 */
			reader.readLine();
			reader.readLine();
			while ((line = reader.readLine()) != null && row < rows) {

				// line.replaceAll("\\s*", "");
				String[] lineArr = line.split(split);
				// String newLine = "[";
				for (int i = 0; i < lineArr.length; i++) {
					lineArr[i] = lineArr[i].trim();
					data[row][i] = Double.parseDouble(lineArr[i]);
					// if (i == lineArr.length - 1) {
					// newLine += lineArr[i] + "]";
					// continue;
					// }
					// newLine += lineArr[i] + ",";
				}

				row++;
				// System.out.println(newLine);
			}
			System.out.println(data.length);
			for (int i = 0; i < data.length; i++) {
				System.out.println("第" + (i + 1) + "行 :" + Arrays.toString(data[i]));
			}
			return data;
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

		return null;

	}
	
	@Test
	public void testParse() {
		LatsTxtParser.parse("/Users/jztong/Desktop/lats.txt", 20000);
	}

}

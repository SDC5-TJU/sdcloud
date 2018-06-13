package scs.service.monitor.riscv;

import java.util.ArrayList;

import scs.pojo.RiscvLLCGroup;

public interface ReadRiscvFiles {
	
	public double readRiscvMemory(String filePath);
	
	public ArrayList<Double> read60(String filePath);
	
	/**
	 * 
	 * @param filePath 
	 * @param readSixty true表示查60个点，false表示查最新
	 * @return
	 */
	public ArrayList<RiscvLLCGroup> readLLC(String filePath, boolean readSixty);
	
}

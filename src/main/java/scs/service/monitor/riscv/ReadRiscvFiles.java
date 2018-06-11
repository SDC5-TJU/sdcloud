package scs.service.monitor.riscv;

import java.util.ArrayList;

public interface ReadRiscvFiles {
	
	public double readRiscvMemory(String filePath);
	
	public ArrayList<Double> read60(String filePath);
	
}

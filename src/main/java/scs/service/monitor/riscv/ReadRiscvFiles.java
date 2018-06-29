package scs.service.monitor.riscv;

import java.util.ArrayList;

import scs.pojo.RiscvLLCGroup;
import scs.pojo.RiscvRedisRealDataBean;

public interface ReadRiscvFiles {

	public double readRiscvResourceUsageFile(String filePath);

	public ArrayList<Double> readRiscvWindowResourceUsageFile(String filePath);//读取窗口大小(60)数量的数据

	public ArrayList<RiscvLLCGroup> readLLC(String filePath, boolean readSixty);

	public RiscvRedisRealDataBean readRiscvQueryTimeFile(String filePath);

	public ArrayList<RiscvRedisRealDataBean> readRiscvWindowQueryTimeFile(String filePath);//读取窗口大小(60)数量的数据
}

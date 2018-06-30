package scs.service.monitor.riscv;

import java.util.ArrayList;

import scs.pojo.riscv.DataProject2Bean;
import scs.pojo.riscv.RiscvLLCGroup;
import scs.pojo.riscv.RiscvRedisRealDataBean;

public interface ReadRiscvFiles {

	public double readRiscvLatestResourceUsageFile(String filePath);//读取riscv资源使用数据(cpu和内存共用该函数),返回最新的1条数据
	public ArrayList<Double> readRiscvWindowResourceUsageFile(String filePath);//读取riscv资源使用数据(cpu和内存共用该函数),返回窗口大小(60)数量的数据

	public ArrayList<RiscvLLCGroup> readLLC(String filePath, boolean readSixty);//读取riscv LLC和内存带宽资源使用数据,readSixty=true 返回窗口大小(60)的数据,false 返回最新的1条数据

	public RiscvRedisRealDataBean readRiscvLatestQueryTimeFile(String filePath);//读取riscv查询时间数据,返回最新的1条数据
	public ArrayList<RiscvRedisRealDataBean> readRiscvWindowQueryTimeFile(String filePath);//读取riscv查询时间数据,返回窗口大小(60)数量的数据
	
	public ArrayList<DataProject2Bean> readRiscvWindowDataFile(String filePath);//课题2 读取riscv 文件数据,返回窗口大小(60)数量的数据
	public DataProject2Bean readRiscvLatestDataFile(String filePath);//课题2 读取riscv 文件数据,返回最新的1条数据
}

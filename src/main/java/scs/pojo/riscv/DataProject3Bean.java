package scs.pojo.riscv;
/**
 * 课题3的数据实体类
 * @author  
 *
 */
public class DataProject3Bean {
	private String seriesStr; //页面曲线字符串
	private float highLinux99th; //高优先级Linux 99th数
	private float highLNS99th; //高优先级LNS 99th数
	private float lowLinux99th; //低优先级Linux 99th数
	private float lowLNS99th; //低优先级LNS 99th数
	
	public String getSeriesStr() {
		return seriesStr;
	}
	public void setSeriesStr(String seriesStr) {
		this.seriesStr = seriesStr;
	}
	public float getHighLinux99th() {
		return highLinux99th;
	}
	public void setHighLinux99th(float highLinux99th) {
		this.highLinux99th = highLinux99th;
	}
	public float getHighLNS99th() {
		return highLNS99th;
	}
	public void setHighLNS99th(float highLNS99th) {
		this.highLNS99th = highLNS99th;
	}
	public float getLowLinux99th() {
		return lowLinux99th;
	}
	public void setLowLinux99th(float lowLinux99th) {
		this.lowLinux99th = lowLinux99th;
	}
	public float getLowLNS99th() {
		return lowLNS99th;
	}
	public void setLowLNS99th(float lowLNS99th) {
		this.lowLNS99th = lowLNS99th;
	}
	 
}

package scs.pojo;

import java.io.Serializable;
import java.util.Arrays;

public class CombinationPOJO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private TableSystemresourceusage systemresourceusageInfo;
	
	private String[][] cacheUsageInfo;

	public TableSystemresourceusage getSystemresourceusageInfo() {
		return systemresourceusageInfo;
	}

	public void setSystemresourceusageInfo(TableSystemresourceusage systemresourceusageInfo) {
		this.systemresourceusageInfo = systemresourceusageInfo;
	}

	public String[][] getCacheUsageInfo() {
		return cacheUsageInfo;
	}

	public void setCacheUsageInfo(String[][] cacheUsageInfo) {
		this.cacheUsageInfo = cacheUsageInfo;
	}

	@Override
	public String toString() {
		return "CombinationPOJO [systemresourceusageInfo=" + systemresourceusageInfo + ", cacheUsageInfo="
				+ Arrays.toString(cacheUsageInfo[0]) + "]";
	}

	public CombinationPOJO(TableSystemresourceusage systemresourceusageInfo, String[][] cacheUsageInfo) {
		super();
		this.systemresourceusageInfo = systemresourceusageInfo;
		this.cacheUsageInfo = cacheUsageInfo;
	}

	public CombinationPOJO() {
		super();
	}
	
}

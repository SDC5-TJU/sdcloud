package scs.pojo;

import java.util.Arrays;

public class CombinationPOJO {
	
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
				+ Arrays.toString(cacheUsageInfo) + "]";
	}
	
}

package scs.pojo.riscv;

import java.io.Serializable;
import java.util.Map;

public class RiscvLLCGroup implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<Integer, RiscvLLCPOJO> map;
	
	public Map<Integer, RiscvLLCPOJO> getMap() {
		return map;
	}

	public void setMap(Map<Integer, RiscvLLCPOJO> map) {
		this.map = map;
	}

}

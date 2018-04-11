package scs.pojo;

import java.io.Serializable;
import java.util.Date;

public class TableSystemresourceusage implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private Integer autoid;

    private String hostname;

    private Float cpuusagerate;

    private Float memusagerate;

    private Float memusageamount;

    private Float netusagerate;

    private Float iousagerate;

    private Float blockio;

    private Date collecttime;

    public Integer getAutoid() {
        return autoid;
    }

    public void setAutoid(Integer autoid) {
        this.autoid = autoid;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname == null ? null : hostname.trim();
    }

    public Float getCpuusagerate() {
        return cpuusagerate;
    }

    public void setCpuusagerate(Float cpuusagerate) {
        this.cpuusagerate = cpuusagerate;
    }

    public Float getMemusagerate() {
        return memusagerate;
    }

    public void setMemusagerate(Float memusagerate) {
        this.memusagerate = memusagerate;
    }

    public Float getMemusageamount() {
        return memusageamount;
    }

    public void setMemusageamount(Float memusageamount) {
        this.memusageamount = memusageamount;
    }

    public Float getNetusagerate() {
        return netusagerate;
    }

    public void setNetusagerate(Float netusagerate) {
        this.netusagerate = netusagerate;
    }

    public Float getIousagerate() {
        return iousagerate;
    }

    public void setIousagerate(Float iousagerate) {
        this.iousagerate = iousagerate;
    }

    public Float getBlockio() {
        return blockio;
    }

    public void setBlockio(Float blockio) {
        this.blockio = blockio;
    }

    public Date getCollecttime() {
        return collecttime;
    }

    public void setCollecttime(Date collecttime) {
        this.collecttime = collecttime;
    }
}
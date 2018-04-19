package scs.pojo;

import java.util.Date;

public class TableContainerresourceusage {
    private Integer autoid;

    private String containername;

    private Float cpuusagerate;

    private Float memusagerate;

    private Float memusageamount;

    private Float netinput;

    private Float ioinput;

    private Float iooutput;

    private Float netoutput;

    private Date collecttime;

    public Integer getAutoid() {
        return autoid;
    }

    public void setAutoid(Integer autoid) {
        this.autoid = autoid;
    }

    public String getContainername() {
        return containername;
    }

    public void setContainername(String containername) {
        this.containername = containername == null ? null : containername.trim();
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

    public Float getNetinput() {
        return netinput;
    }

    public void setNetinput(Float netinput) {
        this.netinput = netinput;
    }

    public Float getIoinput() {
        return ioinput;
    }

    public void setIoinput(Float ioinput) {
        this.ioinput = ioinput;
    }

    public Float getIooutput() {
        return iooutput;
    }

    public void setIooutput(Float iooutput) {
        this.iooutput = iooutput;
    }

    public Float getNetoutput() {
        return netoutput;
    }

    public void setNetoutput(Float netoutput) {
        this.netoutput = netoutput;
    }

    public Date getCollecttime() {
        return collecttime;
    }

    public void setCollecttime(Date collecttime) {
        this.collecttime = collecttime;
    }
}
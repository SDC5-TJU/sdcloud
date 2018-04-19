package scs.pojo;

import java.util.Date;

public class TableAppresourceusage {
    private Integer autoid;

    private String applicationname;

    private Float cpuusagerate;

    private Float memusagerate;

    private Float memusageamount;

    private Float netinput;

    private Float netoutput;

    private Float ioinput;

    private Float iooutput;

    private Date collecttime;

    public Integer getAutoid() {
        return autoid;
    }

    public void setAutoid(Integer autoid) {
        this.autoid = autoid;
    }

    public String getApplicationname() {
        return applicationname;
    }

    public void setApplicationname(String applicationname) {
        this.applicationname = applicationname == null ? null : applicationname.trim();
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

    public Float getNetoutput() {
        return netoutput;
    }

    public void setNetoutput(Float netoutput) {
        this.netoutput = netoutput;
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

    public Date getCollecttime() {
        return collecttime;
    }

    public void setCollecttime(Date collecttime) {
        this.collecttime = collecttime;
    }
}
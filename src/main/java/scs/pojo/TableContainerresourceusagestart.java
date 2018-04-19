package scs.pojo;

import java.util.Date;

public class TableContainerresourceusagestart {
    private Integer autoid;

    private String containername;

    private Float cpuusagerate;

    private Float memusagerate;

    private Float memusageamount;

    private Float ioinput;

    private Float iooutput;

    private Float netinput;

    private Float netoutput;

    private Date collecttime;

    private Byte testrecord;

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

    public Date getCollecttime() {
        return collecttime;
    }

    public void setCollecttime(Date collecttime) {
        this.collecttime = collecttime;
    }

    public Byte getTestrecord() {
        return testrecord;
    }

    public void setTestrecord(Byte testrecord) {
        this.testrecord = testrecord;
    }
}
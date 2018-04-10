package scs.pojo;

public class TableContainerinfo {
    private String containername;

    private String containerid;

    private String containerip;

    private String containerhostname;

    private String containercommand;

    private String containerimagename;

    public String getContainername() {
        return containername;
    }

    public void setContainername(String containername) {
        this.containername = containername == null ? null : containername.trim();
    }

    public String getContainerid() {
        return containerid;
    }

    public void setContainerid(String containerid) {
        this.containerid = containerid == null ? null : containerid.trim();
    }

    public String getContainerip() {
        return containerip;
    }

    public void setContainerip(String containerip) {
        this.containerip = containerip == null ? null : containerip.trim();
    }

    public String getContainerhostname() {
        return containerhostname;
    }

    public void setContainerhostname(String containerhostname) {
        this.containerhostname = containerhostname == null ? null : containerhostname.trim();
    }

    public String getContainercommand() {
        return containercommand;
    }

    public void setContainercommand(String containercommand) {
        this.containercommand = containercommand == null ? null : containercommand.trim();
    }

    public String getContainerimagename() {
        return containerimagename;
    }

    public void setContainerimagename(String containerimagename) {
        this.containerimagename = containerimagename == null ? null : containerimagename.trim();
    }
}
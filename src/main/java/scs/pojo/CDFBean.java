package scs.pojo;


public class CDFBean{
	private float[] cdf;
	private long time;
//	private float[] total;
	private float[] high;
	private float[] high_x86;
	private float[] low;
	private float[] low_x86;
	private float   connection;
	private float   connection_x86;

	public CDFBean(float[] cdf, float[] high, float[] low, float connection, float[] high_x86, float[] low_x86, float connection_x86){
		this.cdf = cdf;
		this.high = high;
		this.low = low;
        //this.average = average;
        this.connection = connection;
        this.connection_x86 = connection_x86;
        this.high_x86 = high_x86;
        this.low_x86 = low_x86;
	}
	public void setTime(long t){
            this.time = t;
      }
	public void setCdf(float[] cdf){
            this.cdf = cdf;
      }
	public void setHigh(float[] high){
            this.high = high;
      }
	public void setHigh_x86(float[] high_x86){
            this.high_x86 = high_x86;
      }
	public void setLow(float[] low){
            this.low = low;
      }
	public void setLow_x86(float[] low_x86){
            this.low_x86 = low_x86;
      }
    public void setConnection_x86(int conn_x86){
            this.connection_x86 = conn_x86;
    }
    public void setConnection(int conn){
            this.connection = conn;
    }
	
	public long getTime(){
		return time;
	}
	public float[] getCdf(){
		return cdf;
	}
	public float[] getHigh(){
		return high;
	}
	public float[] getLow(){
		return low;
	}
	
	public float[] getHigh_x86(){
		return high_x86;
	}
	public float[] getLow_x86(){
		return low_x86;
	}
    public float getConnection(){
        return connection;
    }

    public float getConnection_x86(){
        return connection_x86;
    }
}

package scs.dao.jobSchedul;

import java.util.List;  
import scs.pojo.MemcachedDataBean;
import scs.pojo.SiloDataBean;
import scs.pojo.TwoTuple;
import scs.pojo.XapianDataBean;
 
public interface JobSchedulDao{   
	public int addMemcachedData(List<MemcachedDataBean> list,int testRecordId,int isBase);
	public int addWebServerData(List<TwoTuple<Long,Integer>> list,int testRecordId,int isBase); 
	public int addWebSearchData(List<TwoTuple<Long,Integer>> list,int testRecordId,int isBase); 
	public int addCassandraData(List<TwoTuple<Long,Integer>> list,int testRecordId,int isBase); 
	public int addRedisData(List<TwoTuple<Long,Integer>> list,int testRecordId,int isBase); 
	public int addSiloData(List<SiloDataBean> list,int testRecordId,int isBase);  
	public int addXapianData(List<XapianDataBean> list,int testRecordId,int isBase); 
}

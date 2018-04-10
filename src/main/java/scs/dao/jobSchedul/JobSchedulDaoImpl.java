package scs.dao.jobSchedul;

import java.util.List; 
import org.springframework.stereotype.Repository; 
import scs.dao.MySQLBaseDao; 
import scs.pojo.MemcachedDataBean;
import scs.pojo.SiloDataBean;
import scs.pojo.TwoTuple;
@Repository
public class JobSchedulDaoImpl extends MySQLBaseDao implements JobSchedulDao{

	@Override
	public int addMemcachedData(List<MemcachedDataBean> list,int testRecordId,int isBase) {
		// TODO Auto-generated method stub
		String sql="";
		if(isBase==1){
			sql="insert into data_memcached_base(timeDiff,rps,requests,gets,sets,hits,misses,avg_lat,nintyTh,nintyFiveTh,nintyNineTh,min,max,std,avgGetSize,testRecordId) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		}else{
			sql="insert into data_memcached(timeDiff,rps,requests,gets,sets,hits,misses,avg_lat,nintyTh,nintyFiveTh,nintyNineTh,min,max,std,avgGetSize,testRecordId) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		}
		int result=0;
		for(MemcachedDataBean bean:list){
			result+=this.jt.update(sql,new Object[]{bean.getTimeDiff(),bean.getRps(),bean.getRequests(),bean.getGets(),bean.getSets(),bean.getHits(),bean.getMisses(),bean.getAvg_lat(),bean.getNintyTh(),bean.getNintyFiveTh(),
					bean.getNintyNineTh(),bean.getMin(),bean.getMax(),bean.getStd(),bean.getAvgGetSize(),testRecordId});
		}
		return result;
	}

	@Override
	public int addWebSearchData(List<TwoTuple<Long, Integer>> list,int testRecordId,int isBase) {
		// TODO Auto-generated method stub
		String sql="";
		if(isBase==1){
			sql="insert into data_websearch_base(generateTime,queryTime,testRecordId) values(?,?,?)";
		}else{
			sql="insert into data_websearch(generateTime,queryTime,testRecordId) values(?,?,?)";
		}
		int result=0;
		for(TwoTuple<Long, Integer> item:list){
			result+=this.jt.update(sql,new Object[]{item.first,item.second,testRecordId});
		}
		return result;
	}

	@Override
	public int addWebServerData(List<TwoTuple<Long, Integer>> list,int testRecordId,int isBase) {
		// TODO Auto-generated method stub
		String sql="";
		if(isBase==1){
			sql="insert into data_webserver_base(generateTime,queryTime,testRecordId) values(?,?,?)";
		}else{
			sql="insert into data_webserver(generateTime,queryTime,testRecordId) values(?,?,?)";
		}
		int result=0;
		for(TwoTuple<Long, Integer> item:list){
			result+=this.jt.update(sql,new Object[]{item.first,item.second,testRecordId});
		}
		return result;
	}

	@Override
	public int addSiloData(List<SiloDataBean> list,int testRecordId,int isBase) {
		// TODO Auto-generated method stub
		String sql="";
		if(isBase==1){
			sql="insert into data_silo_base(queueTime,queryTime,totalTime,testRecordId) values(?,?,?,?)";
		}else{
			sql="insert into data_silo(queueTime,queryTime,totalTime,testRecordId) values(?,?,?,?)";
		}
		int result=0;
		for(SiloDataBean bean:list){
			result+=this.jt.update(sql,new Object[]{bean.getQueueTime(),bean.getQueryTime(),bean.getTotalTime(),testRecordId});
		}
		return result;
	}
 

	 
}

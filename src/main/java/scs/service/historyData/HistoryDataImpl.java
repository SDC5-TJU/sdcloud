package scs.service.historyData;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource; 
import org.springframework.stereotype.Service; 
import scs.dao.historyData.HistoryDataDao;
import scs.pojo.AppResouceUsageBean;
import scs.pojo.ContainerResourceUsageBean;
import scs.pojo.ExecuteRecordBean;
import scs.pojo.MemcachedDataBean;
import scs.pojo.SiloDataBean;
import scs.pojo.SystemResourceUsageBean;
import scs.pojo.TwoTuple;
import scs.util.format.DateFormats;

@Service
public class HistoryDataImpl implements HistoryDataService {
	@Resource HistoryDataDao dao;

	@Override
	public List<String> searchSysResourceUsage(String hostName, String startTime, String endTime) {
		if(startTime==null||startTime.equals("")){
			startTime="1970-01-01 00:00:00";
		}
		if(endTime==null||endTime.equals("")){
			endTime=DateFormats.getInstance().getNowDate();
		} 
		// TODO Auto-generated method stub
		List<String> chartStrList=new ArrayList<String>();
		List<SystemResourceUsageBean> list=dao.searchSysResourceUsage(hostName, startTime, endTime);
		if(list==null||list.size()==0){
			chartStrList.add("{name:'"+hostName+"',data:[],marker:{enabled:false}}");//添加cpu使用空值曲线
			chartStrList.add("{name:'"+hostName+"',data:[],marker:{enabled:false}}");//添加mem使用空值曲线
			chartStrList.add("{name:'"+hostName+"',data:[],marker:{enabled:false}}");//添加io使用空值曲线
			chartStrList.add("{name:'"+hostName+"',data:[],marker:{enabled:false}}");//添加net使用空值曲线
			return chartStrList;
		}
		/*
		 * 绘制物理机CPU使用率曲线
		 */
		StringBuffer strName=new StringBuffer();
		StringBuffer strData=new StringBuffer();
		StringBuffer HSeries=new StringBuffer();
		strName.append("{name:'").append(hostName).append("',lineWidth:1,");
		strData.append("data:["); 

		int size=list.size()-1;
		for(int i=0;i<size;i++){
			strData.append("[").append(list.get(i).getCollectTime()).append(",").append(list.get(i).getCpuUsageRate()).append("],");
		}
		strData.append("[").append(list.get(size).getCollectTime()).append(",").append(list.get(size).getCpuUsageRate()).append("]]");
		HSeries.append(strName).append(strData).append(",marker:{enabled:false}}");
		chartStrList.add(HSeries.toString());
		/*
		 * 绘制物理机内存使用率曲线
		 */
		strName.setLength(0);
		strData.setLength(0);
		HSeries.setLength(0);
		strName.append("{name:'").append(hostName).append("',lineWidth:1,");
		strData.append("data:["); 
		for(int i=0;i<size;i++){
			strData.append("[").append(list.get(i).getCollectTime()).append(",").append(list.get(i).getMemUsageRate()).append("],");
		}
		strData.append("[").append(list.get(size).getCollectTime()).append(",").append(list.get(size).getMemUsageRate()).append("]]");
		HSeries.append(strName).append(strData).append(",marker:{enabled:false}}");
		chartStrList.add(HSeries.toString());
		/*
		 * 绘制物理机IO使用率曲线
		 */
		strName.setLength(0);
		strData.setLength(0);
		HSeries.setLength(0);
		strName.append("{name:'").append(hostName).append("',lineWidth:1,");
		strData.append("data:["); 
		for(int i=0;i<size;i++){
			strData.append("[").append(list.get(i).getCollectTime()).append(",").append(list.get(i).getIoUsageRate()).append("],");
		}
		strData.append("[").append(list.get(size).getCollectTime()).append(",").append(list.get(size).getIoUsageRate()).append("]]");
		HSeries.append(strName).append(strData).append(",marker:{enabled:false}}");
		chartStrList.add(HSeries.toString());
		/*
		 * 绘制物理机Net使用率曲线
		 */
		strName.setLength(0);
		strData.setLength(0);
		HSeries.setLength(0);
		strName.append("{name:'").append(hostName).append("',lineWidth:1,");
		strData.append("data:["); 
		for(int i=0;i<size;i++){
			strData.append("[").append(list.get(i).getCollectTime()).append(",").append(list.get(i).getNetUsageRate()).append("],");
		}
		strData.append("[").append(list.get(size).getCollectTime()).append(",").append(list.get(size).getNetUsageRate()).append("]]");
		HSeries.append(strName).append(strData).append(",marker:{enabled:false}}");
		chartStrList.add(HSeries.toString());

		strName.setLength(0);
		strData.setLength(0);
		HSeries.setLength(0);
		return chartStrList;
	}

	@Override
	public List<String> searchContainerResourceUsage(String containerName, String startTime, String endTime) {
		if(startTime==null||startTime.equals("")){
			startTime="1970-01-01 00:00:00";
		}
		if(endTime==null||endTime.equals("")){
			endTime=DateFormats.getInstance().getNowDate();
		}
		List<String> chartStrList=new ArrayList<String>();
		List<ContainerResourceUsageBean> list=dao.searchContainerResourceUsage(containerName, startTime, endTime);
		if(list==null||list.size()==0){
			chartStrList.add("{name:'"+containerName+"',data:[],marker:{enabled:false}}");//添加cpu使用空值曲线
			chartStrList.add("{name:'"+containerName+"',data:[],marker:{enabled:false}}");//添加mem使用空值曲线
			chartStrList.add("{name:'"+containerName+"',data:[],marker:{enabled:false}}");//添加io input使用空值曲线
			chartStrList.add("{name:'"+containerName+"',data:[],marker:{enabled:false}}");//添加io output使用空值曲线 
			chartStrList.add("{name:'"+containerName+"',data:[],marker:{enabled:false}}");//添加net input使用空值曲线
			chartStrList.add("{name:'"+containerName+"',data:[],marker:{enabled:false}}");//添加net output使用空值曲线
			return chartStrList;
		}
		/*
		 * 绘制容器CPU使用率曲线
		 */
		StringBuffer strName=new StringBuffer();
		StringBuffer strData=new StringBuffer();
		StringBuffer HSeries=new StringBuffer();
		strName.append("{name:'").append(containerName).append("',");
		strData.append("data:[");
		int size=list.size()-1;
		for(int i=0;i<size;i++){
			strData.append("[").append(list.get(i).getCollectTime()).append(",").append(list.get(i).getCpuUsageRate()).append("],");
		}
		strData.append("[").append(list.get(size).getCollectTime()).append(",").append(list.get(size).getCpuUsageRate()).append("]]");
		HSeries.append(strName).append(strData).append(",marker:{enabled:false}}");
		chartStrList.add(HSeries.toString());
		/*
		 * 绘制容器内存使用率曲线
		 */
		strName.setLength(0);
		strData.setLength(0);
		HSeries.setLength(0);
		strName.append("{name:'").append(containerName).append("',");
		strData.append("data:["); 
		for(int i=0;i<size;i++){
			strData.append("[").append(list.get(i).getCollectTime()).append(",").append(list.get(i).getMemUsageRate()).append("],");
		}
		strData.append("[").append(list.get(size).getCollectTime()).append(",").append(list.get(size).getMemUsageRate()).append("]]");
		HSeries.append(strName).append(strData).append(",marker:{enabled:false}}");
		chartStrList.add(HSeries.toString());
		/*
		 * 绘制容器io input使用量曲线
		 */
		strName.setLength(0);
		strData.setLength(0);
		HSeries.setLength(0);
		strName.append("{name:'").append(containerName).append("',");
		strData.append("data:["); 
		for(int i=0;i<size;i++){
			strData.append("[").append(list.get(i).getCollectTime()).append(",").append(list.get(i).getIoInput()).append("],");
		}
		strData.append("[").append(list.get(size).getCollectTime()).append(",").append(list.get(size).getIoInput()).append("]]");
		HSeries.append(strName).append(strData).append(",marker:{enabled:false}}");
		chartStrList.add(HSeries.toString());
		/*
		 * 绘制容器io output使用量曲线
		 */
		strName.setLength(0);
		strData.setLength(0);
		HSeries.setLength(0);
		strName.append("{name:'").append(containerName).append("',");
		strData.append("data:["); 
		for(int i=0;i<size;i++){
			strData.append("[").append(list.get(i).getCollectTime()).append(",").append(list.get(i).getIoOutput()).append("],");
		}
		strData.append("[").append(list.get(size).getCollectTime()).append(",").append(list.get(size).getIoOutput()).append("]]");
		HSeries.append(strName).append(strData).append(",marker:{enabled:false}}");
		chartStrList.add(HSeries.toString());
		/*
		 * 绘制容器net input使用量曲线
		 */
		strName.setLength(0);
		strData.setLength(0);
		HSeries.setLength(0);
		strName.append("{name:'").append(containerName).append("',");
		strData.append("data:["); 
		for(int i=0;i<size;i++){
			strData.append("[").append(list.get(i).getCollectTime()).append(",").append(list.get(i).getNetInput()).append("],");
		}
		strData.append("[").append(list.get(size).getCollectTime()).append(",").append(list.get(size).getNetInput()).append("]]");
		HSeries.append(strName).append(strData).append(",marker:{enabled:false}}");
		chartStrList.add(HSeries.toString());
		/*
		 * 绘制容器net output使用量曲线
		 */
		strName.setLength(0);
		strData.setLength(0);
		HSeries.setLength(0);
		strName.append("{name:'").append(containerName).append("',");
		strData.append("data:["); 
		for(int i=0;i<size;i++){
			strData.append("[").append(list.get(i).getCollectTime()).append(",").append(list.get(i).getNetOutput()).append("],");
		}
		strData.append("[").append(list.get(size).getCollectTime()).append(",").append(list.get(size).getNetOutput()).append("]]");
		HSeries.append(strName).append(strData).append(",marker:{enabled:false}}");
		chartStrList.add(HSeries.toString());

		strName.setLength(0);
		strData.setLength(0);
		HSeries.setLength(0);
		return chartStrList;
	}

	@Override
	public List<String> searchAppResourceUsage(String applicationName, String startTime, String endTime,boolean needTime) {
		if(startTime==null||startTime.equals("")){
			startTime="1970-01-01 00:00:00";
		}
		if(endTime==null||endTime.equals("")){
			endTime=DateFormats.getInstance().getNowDate();
		}
		List<String> chartStrList=new ArrayList<String>();
		List<AppResouceUsageBean> list=dao.searchAppResourceUsage(applicationName, startTime, endTime);
		if(list==null||list.size()==0){
			chartStrList.add("{name:'"+applicationName+"',data:[]}");//添加cpu使用率空值曲线
			chartStrList.add("{name:'"+applicationName+"',data:[]}");//添加mem使用率空值曲线
			chartStrList.add("{name:'"+applicationName+"',data:[]}");//添加io input使用空值曲线
			chartStrList.add("{name:'"+applicationName+"',data:[]}");//添加io output使用空值曲线
			chartStrList.add("{name:'"+applicationName+"',data:[]}");//添加net input使用空值曲线
			chartStrList.add("{name:'"+applicationName+"',data:[]}");//添加net output使用空值曲线
			if(needTime==true){
				chartStrList.add("[]");//拼接一个空的时间轴
			}
			return chartStrList;
		}
		/*
		 * 绘制应用CPU使用率曲线
		 */
		StringBuffer strName=new StringBuffer();
		StringBuffer strData=new StringBuffer();
		StringBuffer HSeries=new StringBuffer();
		strName.append("{name:'").append(applicationName).append("',");
		strData.append("data:[");
		int size=list.size()-1;

		for(int i=0;i<size;i++){
			strData.append(list.get(i).getCpuUsageRate()).append(",");
		}
		strData.append(list.get(size).getCpuUsageRate()).append("]");
		HSeries.append(strName).append(strData).append("}");
		chartStrList.add(HSeries.toString());
		/*
		 * 绘制应用内存使用率曲线
		 */
		strName.setLength(0);
		strData.setLength(0);
		HSeries.setLength(0);
		strName.append("{name:'").append(applicationName).append("',");
		strData.append("data:["); 
		for(int i=0;i<size;i++){
			strData.append(list.get(i).getMemUsageRate()).append(",");
		}
		strData.append(list.get(size).getMemUsageRate()).append("]");
		HSeries.append(strName).append(strData).append("}");
		chartStrList.add(HSeries.toString());
		/*
		 * 绘制应用IO input使用量曲线
		 */
		strName.setLength(0);
		strData.setLength(0);
		HSeries.setLength(0);
		strName.append("{name:'").append(applicationName).append("',");
		strData.append("data:["); 
		for(int i=0;i<size;i++){
			strData.append(list.get(i).getIoInput()).append(",");
		}
		strData.append(list.get(size).getIoInput()).append("]");
		HSeries.append(strName).append(strData).append("}");
		chartStrList.add(HSeries.toString());
		/*
		 * 绘制应用IO output使用量曲线
		 */
		strName.setLength(0);
		strData.setLength(0);
		HSeries.setLength(0);
		strName.append("{name:'").append(applicationName).append("',");
		strData.append("data:[");
		for(int i=0;i<size;i++){
			strData.append(list.get(i).getIoOutput()).append(",");
		}
		strData.append(list.get(size).getIoOutput()).append("]");
		HSeries.append(strName).append(strData).append("}");
		chartStrList.add(HSeries.toString());
		/*
		 * 绘制应用Net input使用量曲线
		 */
		strName.setLength(0);
		strData.setLength(0);
		HSeries.setLength(0);
		strName.append("{name:'").append(applicationName).append("',");
		strData.append("data:["); 
		for(int i=0;i<size;i++){
			strData.append(list.get(i).getNetInput()).append(",");
		}
		strData.append(list.get(size).getNetInput()).append("]");
		HSeries.append(strName).append(strData).append("}");
		chartStrList.add(HSeries.toString());
		/*
		 * 绘制应用Net output使用量曲线
		 */
		strName.setLength(0);
		strData.setLength(0);
		HSeries.setLength(0);
		strName.append("{name:'").append(applicationName).append("',");
		strData.append("data:["); 
		for(int i=0;i<size;i++){
			strData.append(list.get(i).getNetOutput()).append(",");
		}
		strData.append(list.get(size).getNetOutput()).append("]");
		HSeries.append(strName).append(strData).append("}");
		chartStrList.add(HSeries.toString());
		/*
		 * 拼接时间轴
		 */
		if(needTime==true){
			strName.setLength(0);
			strData.setLength(0);
			HSeries.setLength(0);
			strData.append("["); 
			DateFormats format=DateFormats.getInstance();
			for(int i=0;i<size;i++){
				strData.append("'").append(format.LongToDate(list.get(i).getCollectTime())).append("',");
			}
			strData.append("'").append(format.LongToDate(list.get(size).getCollectTime())).append("']");
			chartStrList.add(strData.toString()); 
		} 

		strName.setLength(0);
		strData.setLength(0);
		HSeries.setLength(0);
		return chartStrList;
	}

	@Override
	public List<MemcachedDataBean> searchMemcachedData(int testRecordId, int isBase) {
		// TODO Auto-generated method stub
		return dao.searchMemcachedData(testRecordId, isBase);
	}

	@Override
	public List<SiloDataBean> searchSiloData(int testRecordId, int isBase) {
		// TODO Auto-generated method stub
		return dao.searchSiloData(testRecordId, isBase);
	}

	@Override
	public List<TwoTuple<Long, Integer>> searchWebServerData(int testRecordId, int isBase) {
		// TODO Auto-generated method stub
		return dao.searchWebServerData(testRecordId, isBase);
	}

	@Override
	public List<TwoTuple<Long, Integer>> searchWebSearchData(int testRecordId, int isBase) {
		// TODO Auto-generated method stub
		return dao.searchWebSearchData(testRecordId, isBase);
	}

	@Override
	public List<TwoTuple<Long, Integer>> searchCassandraData(int testRecordId, int isBase) {
		// TODO Auto-generated method stub
		return dao.searchCassandraData(testRecordId, isBase);
	}

	@Override
	public List<List<ExecuteRecordBean>> searchExecuteRecord(String startTime, String endTime) {
		if(startTime==null||startTime.equals("")){
			startTime="1970-01-01 00:00:00";
		}
		if(endTime==null||endTime.equals("")){
			endTime=DateFormats.getInstance().getNowDate();
		} 
		// TODO Auto-generated method stub
		List<List<ExecuteRecordBean>> pairList=new ArrayList<List<ExecuteRecordBean>>();
		List<ExecuteRecordBean> list=dao.searchExecuteRecord(startTime, endTime);
		for(int i=0;i<list.size();i++){
			boolean findPair=false;
			if(list.size()==1||list.get(i).getAction().equals("结束")){
				//如果不巧是一个结束节点，或者队列长度为1，则直接添加
				List<ExecuteRecordBean> itemList=new ArrayList<ExecuteRecordBean>();
				itemList.add(list.get(i)); 
				pairList.add(itemList);
				list.remove(i);
				i--;
				continue;
			}
			for(int j=i+1;j<list.size();j++){
				//pand
				if(checkIsPair(list.get(i),list.get(j))==true){
					List<ExecuteRecordBean> itemList=new ArrayList<ExecuteRecordBean>();
					itemList.add(list.get(i));itemList.add(list.get(j)); 
					pairList.add(itemList);
					list.remove(j);
					list.remove(i);
					i--;
					findPair=true;
					break;
				}
			}
			if(findPair==false){
				//如果没有找到pair，直接把自己单独添加进去
				List<ExecuteRecordBean> itemList=new ArrayList<ExecuteRecordBean>();
				itemList.add(list.get(i)); 
				pairList.add(itemList);
				list.remove(i);
				i--;
			}
		} 
		return pairList;
	}
	private boolean checkIsPair(ExecuteRecordBean a,ExecuteRecordBean b){
		if(a.getApplicationName().equals(b.getApplicationName())){//应用名字相同
			if(a.getIsBase()==b.getIsBase()){//同种类型
				if(b.getAction().equals("结束")){//而且还是结束
					//那么就是一对
					return true;
				}
			}
		}
		return false;
	}


}

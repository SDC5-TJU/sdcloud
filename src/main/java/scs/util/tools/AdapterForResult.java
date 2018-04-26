package scs.util.tools;

import java.util.List;

import scs.pojo.TimeResultBean;
/**
 * 结果处理适配类
 * @author Juane
 *
 */
public class AdapterForResult {
	static ResultParser result = new ResultParser();
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static  TimeResultBean adapter(String name,List list){
		TimeResultBean bean = new TimeResultBean();
		switch(name){
		case "memcached":
			bean = result.calculateM(list);
			break;			
		case "silo":
			bean = result.calculateS(list);
			break;
		case "webServer":
			bean = result.calculate(list);
			break;
		case "webSearch":
			bean = result.calculate(list);
			break;
		case "cassandra":
			bean = result.calculate(list);
			break;
		}
		return bean;
	}
}

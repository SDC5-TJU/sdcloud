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
			bean = result.calculateM(list);//因为memcached返回的数据格式是特定的,所以需要单独处理
			break;			
		case "silo":
			bean = result.calculateS(list);//因为silo和xapian返回的数据格式也是特定的,所以也需要单独处理
			break;
		case "xapian":
			bean = result.calculateX(list);
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
		case "redis":
			bean = result.calculate(list);
			break;
		}
		return bean;
	}
}

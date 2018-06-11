package scs.util.loadGen.driver;
 
import java.util.List;
import java.util.Random;
import org.apache.http.impl.client.CloseableHttpClient;
import scs.pojo.TwoTuple;
import scs.util.loadGen.strategy.IndexDistriPattern;
import scs.util.loadGen.strategy.PatternInterface;
import scs.util.loadGen.strategy.PossionDistriPattern;
import scs.util.loadGen.strategy.RandomDistriPattern;
import scs.util.loadGen.strategy.UniformDistriPattern; 
 

public abstract class AbstractJobDriver {
	protected String queryItemsStr;//查询词列表
	protected Random random=new Random();  
	protected CloseableHttpClient httpclient;

	protected abstract void initVariables();//初始化变量
	/**
	 * 执行评测作业
	 * @param requestCount 请求总次数
	 * @param warmUpCount 预热次数
	 * @param pattern 请求模式
	 * @param intensity 请求强度
	 * @return 请求结果<请求发出时间,响应耗时>
	 */
	public abstract List<TwoTuple<Long,Integer>> executeJob(int requestCount,String pattern,int intensity);//执行请求作业
	/**
	 * 选择该次在线类应用负载的模式
	 * @param pattern 参数 "index" "possion" "random" ...
	 * @return 对应的模式对象
	 */
	protected PatternInterface choosePattern(String pattern){
		PatternInterface pi;
		switch (pattern){
		case "possion":
			pi=new PossionDistriPattern();
			break;
		case "index":
			pi=new IndexDistriPattern();
			break;
		case "random":
			pi=new RandomDistriPattern();
			break;
		case "fixed":
			pi=new UniformDistriPattern();
			break;
		default:pi=new UniformDistriPattern();
		}
		return pi;
	}
}

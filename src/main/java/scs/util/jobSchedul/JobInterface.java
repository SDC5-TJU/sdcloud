package scs.util.jobSchedul;
/**
 * 应用执行控制接口
 * scs.util.jobSchedul包中的实现类实现各个应用的调用接口
 * @author yanan
 *
 */
public interface JobInterface {
	public void init(int isBase); //初始化应用,读取配置参数
	public void start(int isBase);//开始执行应用
	public void shutdown(); //终止应用执行(如果需要的话)
	public int processResult(int isBase);//处理数据结果集(如果有的话)
}
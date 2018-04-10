package scs.util.jobSchedul;
/**
 * 应用执行控制接口
 * @author yanan
 *
 */
public interface JobInterface {
	public void init(int isBase);
	public void start(int isBase);
	public void shutdown();
	public int processResult(int isBase);
}
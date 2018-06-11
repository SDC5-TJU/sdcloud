package scs.util.tools;

import java.io.IOException;
import java.util.List;

import scs.pojo.TwoTuple;

public class Test {
	public static void main(String[] args) throws IOException{
		List<TwoTuple<Long, Integer>> list=ReadFile.getInstance().readRedisFile("H://SET.lats");
		System.out.println(list.size());
	}

}

package scs.util.tools;

import java.io.IOException;
import scs.util.loadGen.genDriver.WebSearchDriver;
import scs.util.loadGen.recordDriver.RecordDriver;
import scs.util.repository.Repository;

public class Test {
	public static void main(String[] args) throws IOException{ 
		Repository.onlineDataFlag=true;

		new RecordDriver().execute(); 
		WebSearchDriver driver=new WebSearchDriver();
		driver.executeJob("possion");
		
		 
		Repository.onlineDataFlag=true;
	}

}

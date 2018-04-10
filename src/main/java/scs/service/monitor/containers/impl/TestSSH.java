package scs.service.monitor.containers.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public class TestSSH {

	public static void main(String[] args) {

//		for (int i = 0; i < 5; i++) {
//			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			long lt = new Long(System.currentTimeMillis());
//			Date date = new Date(lt);
//			System.out.println(simpleDateFormat.format(date));
			InputStream dockerDataStream = dockerDataStream("192.168.1.147","tank","tanklab");
			process(dockerDataStream);
//			dockerMonitor();
//		}
	}

	private static void dockerMonitor() {
		String hostname = "192.168.1.147";
		String username = "tank";
		String password = "tanklab";

		BufferedReader br = null;
		try {
			/* Create a connection instance */
			Connection conn = new Connection(hostname);

			/* Now connect */

			conn.connect();

			/*
			 * Authenticate. If you get an IOException saying something like
			 * "Authentication method password not supported by the server at this stage."
			 * then please check the FAQ.
			 */

			boolean isAuthenticated = conn.authenticateWithPassword(username, password);

			if (isAuthenticated == false)
				throw new IOException("Authentication failed.");

			/* Create a session */

			Session sess = conn.openSession();

			sess.execCommand("sudo docker stats --no-stream --format \"table {{.Container}}\t{{.CPUPerc}}\t{{.MemUsage}}\"");

			System.out.println("Here is some information about the remote host:");

			/*
			 * This basic example does not handle stderr, which is sometimes
			 * dangerous (please read the FAQ).
			 */

			InputStream stdout = new StreamGobbler(sess.getStdout());

			br = new BufferedReader(new InputStreamReader(stdout));

			while (true) {
				String line = br.readLine();
				if (line == null)
					break;
				System.out.println(line);
			}

			/* Show exit status, if available (otherwise "null") */

			System.out.println("ExitCode: " + sess.getExitStatus());

			/* Close this session */

			sess.close();

			/* Close the connection */

			conn.close();

		} catch (IOException e) {
			e.printStackTrace(System.err);
			System.exit(2);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private static InputStream dockerDataStream(String hostname, String username, String password) {
		String host = hostname;
		String user = username;
		String passwd = password;

		try {
			/* Create a connection instance */
			Connection conn = new Connection(host);

			/* Now connect */

			conn.connect();

			/*
			 * Authenticate. If you get an IOException saying something like
			 * "Authentication method password not supported by the server at this stage."
			 * then please check the FAQ.
			 */

			boolean isAuthenticated = conn.authenticateWithPassword(user, passwd);

			if (isAuthenticated == false)
				throw new IOException("Authentication failed.");

			/* Create a session */

			Session sess = conn.openSession();

			sess.execCommand("sudo docker stats --no-stream --format \"{{.Name}}:{{.CPUPerc}}:{{.MemUsage}}:{{.MemPerc}}:{{.NetIO}}:{{.BlockIO}}\"");

			System.out.println("Here is some information about the remote host:");

			/*
			 * This basic example does not handle stderr, which is sometimes
			 * dangerous (please read the FAQ).
			 */

			InputStream stdout = new StreamGobbler(sess.getStdout());
			
			return stdout;

		} catch (IOException e) {
			e.printStackTrace(System.err);
			System.exit(2);
			return null;
		} 
	}
	
	private static int process(InputStream in){
		if (in == null) {
			return -1;
		}
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(in));
			//container类
			while (true) {
				String line = reader.readLine();
				if (line == null)
					break;
				
				String[] split = line.split(":");
				
				System.out.println(Arrays.toString(split));
			}
		} catch (Exception e) {
		}
		
		return 0;
	}
}

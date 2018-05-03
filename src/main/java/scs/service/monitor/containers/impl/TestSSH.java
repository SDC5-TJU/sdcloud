package scs.service.monitor.containers.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public class TestSSH {

	public static void dockerMonitor() {
		String hostname = "192.168.1.128";
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
//			sess.execCommand("sudo docker stats --no-stream --format \"table {{.Container}}\t{{.CPUPerc}}\t{{.MemUsage}}\"");
			sess.execCommand("sudo perf stat -e cache-misses -C 0 sleep 1");
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
	
	public static void main(String[] args) {
		TestSSH.dockerMonitor();
	}
}

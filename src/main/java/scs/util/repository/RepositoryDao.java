package scs.util.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import scs.pojo.AppInfoBean;
import scs.pojo.ContainerInfoBean;
import scs.pojo.SystemInfoBean;
import scs.util.tools.DatabaseDriver;

public class RepositoryDao {
	private static Connection conn = null;
	private static PreparedStatement pst = null;
	private static ResultSet rs = null;

	/**
	 * 初始化查询容器信息表
	 * 
	 * @return map
	 */
	public static Map<String, ContainerInfoBean> initContainerInfoMap() {
		Map<String, ContainerInfoBean> map = new HashMap<String, ContainerInfoBean>();

		try {
			conn = DatabaseDriver.getInstance().getConn();
			String sql = "select containerId,containerName,containerIp,containerHostName,containerCommand,containerImageName from table_containerinfo";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				ContainerInfoBean cib = new ContainerInfoBean();
				cib.setContainerId(rs.getString(1));
				cib.setContainerName(rs.getString(2));
				cib.setContainerIp(rs.getString(3));
				cib.setContainerHostName(rs.getString(4));
				cib.setContainerCommand(rs.getString(5));
				cib.setContainerImageName(rs.getString(6));
				map.put(cib.getContainerName(), cib);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseDriver.getInstance().closeResultSet(rs);
			DatabaseDriver.getInstance().closePreparedStatement(pst);
			DatabaseDriver.getInstance().closeConnection(conn);
		}
		return map;
	}

	/**
	 * 初始化查询appinfo信息
	 * 
	 * @return map
	 */
	public static Map<String, AppInfoBean> initAppInfoMap() {
		Map<String, AppInfoBean> map = new HashMap<String, AppInfoBean>();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = DatabaseDriver.getInstance().getConn();
			String sql = "select applicationName,containerNames,masterContainerName,applicationType from table_appinfo;";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				AppInfoBean aib = new AppInfoBean();
				aib.setApplicationName(rs.getString(1));
				String[] containerNames = rs.getString(2).trim().split(",");
				List<String> nameList = new ArrayList<String>();
				for (String name : containerNames) {
					nameList.add(name);
				}
				aib.setContainerNames(nameList);
				aib.setMasterContainerName(rs.getString(3));
				aib.setApplicationType(rs.getString(4));
				map.put(aib.getApplicationName(), aib);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseDriver.getInstance().closeResultSet(rs);
			DatabaseDriver.getInstance().closePreparedStatement(pst);
			DatabaseDriver.getInstance().closeConnection(conn);
		}
		return map;

	}

	/**
	 * 初始化查询物理机信息表
	 * 
	 * @author jztong
	 * @return map
	 */
	public static Map<String, SystemInfoBean> initSystemInfoMap() {
		Map<String, SystemInfoBean> map = new HashMap<String, SystemInfoBean>();
		try {
			conn = DatabaseDriver.getInstance().getConn();
			String sql = "select hostName,hostIp,hostDockerIp,containersCount from table_systeminfo";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				SystemInfoBean sib = new SystemInfoBean();
				sib.setHostName(rs.getString(1));
				sib.setHostIp(rs.getString(2));
				sib.setHostDockerIp(rs.getString(3));
				sib.setContainersCount(rs.getInt(4));
				map.put(sib.getHostName(), sib);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseDriver.getInstance().closeResultSet(rs);
			DatabaseDriver.getInstance().closePreparedStatement(pst);
			DatabaseDriver.getInstance().closeConnection(conn);
		}
		return map;
	}
	public static int updateExecuteRecord(String appName,String eventTime,String action,int isBase) {
		System.out.println(appName+" "+action+" isbase="+isBase);
		int result=0;
		try {
			conn = DatabaseDriver.getInstance().getConn();
			String sql = "insert into table_executeRecord(applicationName,eventTime,action,isBase) values(?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1,appName);
			pst.setString(2,eventTime);
			pst.setString(3,action);
			pst.setInt(4,isBase);
			result= pst.executeUpdate(); 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { 
			DatabaseDriver.getInstance().closePreparedStatement(pst);
			DatabaseDriver.getInstance().closeConnection(conn);
		}
		return result;
	}
}

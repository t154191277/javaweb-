package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.jsp.jstl.sql.Result;
import javax.servlet.jsp.jstl.sql.ResultSupport;

import util.Constant;

/**
 * @since 2016.3.18
 * @author Administrator
 *
 */
public class DBDao {
	
	/**
	 * string that operating DB
	 */
	private String sqlStr;
	
	private List<String> valuesList;
	
	private Connection mConnection;

	public void setSqlStr(String sqlStr) {
		this.sqlStr = sqlStr;
	}


	public void setValuesList(List<String> valuesList) {
		this.valuesList = valuesList;
	}

	public DBDao() {
		super();
		mConnection = getConnection();
	}


	private Connection getConnection() {
		Connection conn = null;
		String dbURL =  Constant.DriverURL + ";" +"DatabaseName=" + Constant.DBName;
		try{
			Class.forName(Constant.DriverName);
			conn = DriverManager.getConnection(dbURL, Constant.DBUserName, Constant.DBUserPwd);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * close stream
	 * @param conn
	 * @param sm
	 * @param rs
	 */
	private void close(Connection conn,PreparedStatement sm,ResultSet rs) {
		if (conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (sm!=null){
			try {
				sm.close();
			} catch (SQLException e) {
			e.printStackTrace();
			}
		}
		if (rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
			e.printStackTrace();
			}
		}
	}
	
	/**
	 * Create operation
	 * @return Result
	 */
	public Result executeQuery(){
		PreparedStatement ps = null;
		ResultSet rs = null;
		Result r = null;
		try {
			ps = mConnection.prepareStatement(sqlStr);
			if(valuesList!=null && valuesList.size()!=0){
				this.setValues(ps,valuesList);
			}
			rs = ps.executeQuery();
			r = ResultSupport.toResult(rs); //转换为Result关闭conn之后还能使用		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			this.close(mConnection, ps, rs);
		}
		return r;
	}
	
	/**
	 * Update Read Delete operation
	 * @return result int
	 */
	public int executeUpdate(){
		PreparedStatement ps = null;	
		int result = -1;
		try {
			ps = mConnection.prepareStatement(sqlStr);
			if(valuesList!=null && valuesList.size()!=0){
				this.setValues(ps,valuesList);
			}
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			this.close(mConnection, ps, null);
		}
		return result;
	}
	
	private void setValues(PreparedStatement ps,List values){
		for (int i = 0;i < values.size();i++){
			try {
				ps.setObject(i + 1, values.get(i));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}

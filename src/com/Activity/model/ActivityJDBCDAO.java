package com.Activity.model;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.List;

public class ActivityJDBCDAO implements ActivityDAO_interface {
	private static String DRIVER;
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "system";
	String pwd = "oracle";

	private static final String INSERT_STMT = "INSERT INTO Activity (actID,actName,actStart,actEnd)"
			+ "VALUES('A'||LPAD(to_char(act_seq.nextval),4,'0'),?,?,?)";

	private static final String UPDATE = "UPDATE Activity set actName=?,actStart=?,actEnd=? where actID=?";

	private static final String GET_ALL_STMT = "SELECT * FROM Activity ORDER by actID";

	private static final String GET_BY_PK = "SELECT * FROM Activity where actID=?";

	private static final String GET_BY_NAME = "SELECT * FROM Activity where actName=?";

	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void insert(ActivityVO activityVO) {
		Connection con = null;
		PreparedStatement pstmt =null;
		
		try {
			
			con = DriverManager.getConnection(url, userid, pwd);
			pstmt =con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, activityVO.getActName());
			pstmt.setDate(2,activityVO.getActStart());
			pstmt.setDate(3, activityVO.getActEnd());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(con!= null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		
	}

	@Override
	public void update(ActivityVO activityVO) {

	}

	@Override
	public ActivityVO findByPK(String actID) {

		return null;
	}

	@Override
	public ActivityVO findByName(String actName) {

		return null;
	}

	@Override
	public List<ActivityVO> getAll() {

		return null;
	}
	
	
	
}

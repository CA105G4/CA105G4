package com.Activity.model;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;

public class ActivityJDBCDAO implements ActivityDAO_interface {
	private static final String DRIVER= "oracle.jdbc.driver.OracleDriver";
	 
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "system";
	private static final String PWD = "oracle";

	private static final String INSERT_SQL = "INSERT INTO Activity (actID,actName,actStart,actEnd)"
			+ "VALUES('A'||LPAD(to_char(act_seq.nextval),4,'0'),?,?,?)";

	private static final String UPDATE = "UPDATE Activity set actName=?,actStart=?,actEnd=? where actID=?";

	private static final String FIND_ALL_STMT = "SELECT * FROM Activity ORDER by actID";

	private static final String FIND_BY_PK = "SELECT * FROM Activity where actID=?";

	private static final String FIND_BY_NAME = "SELECT * FROM Activity where actName=?";

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
			
			con = DriverManager.getConnection(URL, USER, PWD);
			pstmt =con.prepareStatement(INSERT_SQL);
			
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
			Connection con =null;
			PreparedStatement pstmt =null;
		
			try {
				con=DriverManager.getConnection(URL,USER, PWD);
				pstmt=con.prepareStatement(UPDATE);
				
				pstmt.setString(1, activityVO.getActName());
				pstmt.setDate(2,activityVO.getActStart());
				pstmt.setDate(3, activityVO.getActEnd());
				
				pstmt.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (Exception e) {
						e.printStackTrace(System.err);
					}
				}
			}
			
			
		
	}

	@Override
	public ActivityVO findByPK(String actID) {
		ActivityVO actVO =null;
		Connection con =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		
		
		try {
			con=DriverManager.getConnection(URL,USER,PWD);
			pstmt =con.prepareStatement(FIND_BY_PK);
			
			pstmt.setString(1, actID);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
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
	
	public static void main(String[] args) {
		
		ActivityVO actVO =new ActivityVO();
		Date d1 =  Date.valueOf("2018-12-25");
		Date d2 =  Date.valueOf("2018-12-31");
		actVO.setActName("聖誕老公公");
		actVO.setActStart(d1);
		actVO.setActEnd(d2);
		
		ActivityJDBCDAO actJDAO= new ActivityJDBCDAO();
		actJDAO.insert(actVO);
		System.out.println("新增成功");
		
	}
	
}

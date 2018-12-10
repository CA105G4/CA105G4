package com.activity.model;

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

	private static final String FIND_BY_PK = "SELECT actName,actStart,actEnd FROM Activity where actID=?";

	private static final String FIND_BY_NAME = "SELECT actID,actStart,actEnd FROM Activity where actName=?";

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
				pstmt.setString(4, activityVO.getActID());
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
			rs = pstmt.executeQuery();
			
			while(rs!=null) {
				actVO =new ActivityVO();
				actVO.setActName(rs.getString("actName"));
				actVO.setActStart(rs.getDate("actStart"));
				actVO.setActEnd(rs.getDate("actEnd"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		
		}
			
		return actVO;
	}

	@Override
	public ActivityVO findByName(String actName) {
		ActivityVO actVO =null;
		Connection con =null;
		PreparedStatement pstmt =null;
		
		ResultSet rs =null;
		
		try {
			con=DriverManager.getConnection(URL, USER, PWD);
			pstmt =con.prepareStatement(FIND_BY_NAME);
			pstmt.setString(2,actName);
			rs= pstmt.executeQuery();
			
			while(rs !=null) {
				actVO = new ActivityVO();
				actVO.setActID(rs.getString("actID"));
				actVO.setActStart(rs.getDate("actStart"));
				actVO.setActEnd(rs.getDate("actEnd"));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return actVO;
	}

	@Override
	public List<ActivityVO> getAll() {
				
		return null;
	}
	
	
}

package com.workExchangeRecord.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import tool.BLOB;

public class WorkExchangeRecordDAO implements WorkExchangeRecordDAO_interface{

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CA105G4DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

    private static final String INSERT_SQL = 
    		"INSERT INTO WorkExchangeRecord (weID, memID, werState, orderID, weApp) "
    		+ "VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_SQL = "UPDATE WorkExchangeRecord set werState = ? where weID = ? and memID = ?";
    private static final String UPDATE_BYWE_SQL = "UPDATE WorkExchangeRecord set werState = ? where weID = ?";
    private static final String GET_ALL_SQL = "SELECT * from WorkExchangeRecord";
    private static final String GET_ONE_SQL = "SELECT weID, memID, werState, orderID, weApp from WorkExchangeRecord where weID = ? and memID = ?";
    private static final String GET_ALL_BYWE_SQL = "SELECT * from WorkExchangeRecord where weID= ?";
    

	@Override
	public void insert(WorkExchangeRecordVO workExchangeRecordVO) {
		Connection con = null; 
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_SQL);
			
			pstmt.setInt(1, workExchangeRecordVO.getWeID());
			pstmt.setString(2, workExchangeRecordVO.getMemID());
			pstmt.setInt(3, workExchangeRecordVO.getWerState());
			pstmt.setString(4, workExchangeRecordVO.getOrderID());
			pstmt.setBytes(5, workExchangeRecordVO.getWeApp());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}


	@Override
	public void update(WorkExchangeRecordVO workExchangeRecordVO) {
		Connection con = null; 
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_SQL);
			
			pstmt.setInt(1, workExchangeRecordVO.getWerState());
			pstmt.setInt(2, workExchangeRecordVO.getWeID());
			pstmt.setString(3, workExchangeRecordVO.getMemID());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void updateByWE(WorkExchangeRecordVO workExchangeRecordVO) {
		Connection con = null; 
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_BYWE_SQL);
			
			pstmt.setInt(1, workExchangeRecordVO.getWerState());
			pstmt.setInt(2, workExchangeRecordVO.getWeID());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}


	@Override
	public WorkExchangeRecordVO findByPrimaryKey(Integer werID, String memID) {
		
		WorkExchangeRecordVO workExchangeRecordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_SQL);
			
			pstmt.setInt(1, werID);
			pstmt.setString(2, memID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				workExchangeRecordVO = new WorkExchangeRecordVO();
				workExchangeRecordVO.setWeID(rs.getInt("weID"));
				workExchangeRecordVO.setMemID(rs.getString("memID"));
				workExchangeRecordVO.setWerState(rs.getInt("werState"));
				workExchangeRecordVO.setOrderID(rs.getString("orderID"));
				workExchangeRecordVO.setWeApp(rs.getBytes("weApp"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if(pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return workExchangeRecordVO;
	}



	@Override
	public List<WorkExchangeRecordVO> getAll() {
		
		List<WorkExchangeRecordVO> list = new ArrayList<WorkExchangeRecordVO>();
		WorkExchangeRecordVO workExchangeRecordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_SQL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				workExchangeRecordVO = new WorkExchangeRecordVO();
				workExchangeRecordVO.setWeID(rs.getInt("weID"));
				workExchangeRecordVO.setMemID(rs.getString("memID"));
				workExchangeRecordVO.setWerState(rs.getInt("werState"));
				workExchangeRecordVO.setOrderID(rs.getString("orderID"));
				workExchangeRecordVO.setWeApp(rs.getBytes("weApp"));
				
				list.add(workExchangeRecordVO);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if(pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}	
		return list;
	}
	
	@Override
	public List<WorkExchangeRecordVO> getAllByWE(Integer weID) {
		List<WorkExchangeRecordVO> list = new ArrayList<WorkExchangeRecordVO>();
		WorkExchangeRecordVO workExchangeRecordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BYWE_SQL);
			pstmt.setInt(1, weID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				workExchangeRecordVO = new WorkExchangeRecordVO();
				workExchangeRecordVO.setWeID(rs.getInt("weID"));
				workExchangeRecordVO.setMemID(rs.getString("memID"));
				workExchangeRecordVO.setWerState(rs.getInt("werState"));
				workExchangeRecordVO.setOrderID(rs.getString("orderID"));
				workExchangeRecordVO.setWeApp(rs.getBytes("weApp"));
				
				list.add(workExchangeRecordVO);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if(pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}	
		return list;
	}


}

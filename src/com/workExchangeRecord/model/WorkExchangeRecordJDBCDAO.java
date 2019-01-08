package com.workExchangeRecord.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tool.BLOB;

public class WorkExchangeRecordJDBCDAO implements WorkExchangeRecordDAO_interface{

	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "CA105G4"; 
	private static final String PASSWORD = "123456"; 

    private static final String INSERT_SQL = 
    		"INSERT INTO WorkExchangeRecord (weID, memID, werState, orderID, weApp) "
    		+ "VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_SQL = "UPDATE WorkExchangeRecord set werState = ? where weID = ? and memID = ?";
    private static final String UPDATE_BYWE_SQL = "UPDATE WorkExchangeRecord set werState = ? where weID = ?";
    private static final String GET_ALL_SQL = "SELECT * from WorkExchangeRecord";
    private static final String GET_ONE_SQL = "SELECT weID, memID, werState, orderID, weApp from WorkExchangeRecord where weID = ? and memID = ?";
    private static final String GET_ALL_BYWE_SQL = "SELECT * from WorkExchangeRecord where weID= ?";
    
    static {
    	try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}    	
    }

	@Override
	public void insert(WorkExchangeRecordVO workExchangeRecordVO) {
		Connection con = null; 
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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

	public void updateByWE(WorkExchangeRecordVO workExchangeRecordVO) {
		Connection con = null; 
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
			con = DriverManager.getConnection(URL, USER, PASSWORD);
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
	
	public List<WorkExchangeRecordVO> getAllByWE(Integer weID) {
		List<WorkExchangeRecordVO> list = new ArrayList<WorkExchangeRecordVO>();
		WorkExchangeRecordVO workExchangeRecordVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_BYWE_SQL);
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
	
	public static void main(String[] args) {
		WorkExchangeRecordJDBCDAO dao = new WorkExchangeRecordJDBCDAO();
		
		//新增
//		WorkExchangeRecordVO workExchangeRecordVO1 = new WorkExchangeRecordVO();
//		workExchangeRecordVO1.setWeID(1002);
//		workExchangeRecordVO1.setMemID("M0003");
//		workExchangeRecordVO1.setWerState(1);
//		workExchangeRecordVO1.setOrderID("20181212-000007");
//		workExchangeRecordVO1.setWeApp(new BLOB().writeBlob("images/fishing.jpg"));
//		dao.insert(workExchangeRecordVO1);
//		System.out.println("Successfully Insert!!!!!");
		
		//修改
//		dao.update(2, 1002, "M0003");
//		System.out.println("Successfully Update!!!!");
		
		//查找一筆
//		WorkExchangeRecordVO workExchangeRecordVO4 = dao.findByPrimaryKey(1002,"M0002");
//		System.out.println(workExchangeRecordVO4.getWeID());
//		System.out.println(workExchangeRecordVO4.getMemID());
//		System.out.println(workExchangeRecordVO4.getWerState());
//		System.out.println(workExchangeRecordVO4.getOrderID());
//		new BLOB().readBlob(workExchangeRecordVO4.getWeApp(), "fishing.jpg");
		
		//查找全部
//		List<WorkExchangeRecordVO> list = dao.getAll();
//		for(WorkExchangeRecordVO wl : list) {
//			System.out.println(wl.getWeID());
//			System.out.println(wl.getMemID());
//			System.out.println(wl.getWerState());
//			System.out.println(wl.getOrderID());
//			new BLOB().readBlob(wl.getWeApp(), "fishing.jpg");
//			System.out.println("--------------------------------");
//		}
		
		//依需求查詢
//		List<WorkExchangeRecordVO> list = dao.getAllByWE(1001);
//		for(WorkExchangeRecordVO wl : list) {
//			System.out.println(wl.getWeID());
//			System.out.println(wl.getMemID());
//			System.out.println(wl.getWerState());
//			System.out.println(wl.getOrderID());
////			new BLOB().readBlob(wl.getWeApp(), "fishing.jpg");
//			System.out.println("--------------------------------");
//		}
	}
}

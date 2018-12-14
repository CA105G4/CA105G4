package com.workExchange.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tool.BLOB;



public class WorkExchangeJDBCDAO implements WorkExchangeDAO_interface{
	
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "CA105G4"; 
	private static final String PASSWORD = "123456"; 

    private static final String INSERT_SQL = 
    		"INSERT INTO WorkExchange (weID, memID, empID, weRoomID, weName, weContent, wePic, weRoomPic, weStart, weEnd) "
    		+ "VALUES (we_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_SQL = "UPDATE WorkExchange set weName = ? where weID = ?";
    private static final String DELETE_SQL = "DELETE from WorkExchange where weID = ?";
    private static final String GET_ALL_SQL = "SELECT * from WorkExchange";
    private static final String GET_ONE_SQL = "SELECT weID, memID, empID, weRoomID, weName, weContent, wePic, weRoomPic, weStart, weEnd from WorkExchange where weID = ?";
    
    static {
    	try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}    	
    }


	@Override
	public void insert(WorkExchangeVO workExchangeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_SQL);
			
			pstmt.setString(1, workExchangeVO.getMemID());
			pstmt.setString(2, workExchangeVO.getEmpID());
			pstmt.setString(3, workExchangeVO.getWeRoomID());
			pstmt.setString(4, workExchangeVO.getWeName());
			pstmt.setString(5, workExchangeVO.getWeContent());
			pstmt.setBytes(6, workExchangeVO.getWePic());
			pstmt.setBytes(7, workExchangeVO.getWeRoomPic());
			pstmt.setDate(8, workExchangeVO.getWeStart());
			pstmt.setDate(9, workExchangeVO.getWeEnd());
			
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
	public void update(WorkExchangeVO workExchangeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_SQL);
			
			pstmt.setString(1, workExchangeVO.getWeName());
			pstmt.setInt(2, workExchangeVO.getWeID());
			
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
	public void delete(WorkExchangeVO workExchangeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_SQL);
			
			pstmt.setInt(1, workExchangeVO.getWeID());
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
	public WorkExchangeVO findByPrimaryKey(Integer weID) {
		
		WorkExchangeVO workExchangeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_SQL);
			
			pstmt.setInt(1, weID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				workExchangeVO = new WorkExchangeVO();
				workExchangeVO.setWeID(rs.getInt("weID"));
				workExchangeVO.setMemID(rs.getString("memID"));
				workExchangeVO.setEmpID(rs.getString("empID"));
				workExchangeVO.setWeRoomID(rs.getString("weRoomID"));
				workExchangeVO.setWeName(rs.getString("weName"));
				workExchangeVO.setWeContent(rs.getString("weContent"));
				workExchangeVO.setWePic(rs.getBytes("wePic"));
				workExchangeVO.setWeRoomPic(rs.getBytes("weRoomPic"));
				workExchangeVO.setWeStart(rs.getDate("weStart"));				
				workExchangeVO.setWeEnd(rs.getDate("weEnd"));				
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
		return workExchangeVO;
	}


	@Override
	public List<WorkExchangeVO> getAll() {
		
		List<WorkExchangeVO> list = new ArrayList<WorkExchangeVO>();
		WorkExchangeVO workExchangeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_SQL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				workExchangeVO = new WorkExchangeVO();
				workExchangeVO.setWeID(rs.getInt("weID"));
				workExchangeVO.setMemID(rs.getString("memID"));
				workExchangeVO.setEmpID(rs.getString("empID"));
				workExchangeVO.setWeRoomID(rs.getString("weRoomID"));
				workExchangeVO.setWeName(rs.getString("weName"));
				workExchangeVO.setWeContent(rs.getString("weContent"));
				workExchangeVO.setWePic(rs.getBytes("wePic"));
				workExchangeVO.setWeRoomPic(rs.getBytes("weRoomPic"));
				workExchangeVO.setWeStart(rs.getDate("weStart"));				
				workExchangeVO.setWeEnd(rs.getDate("weEnd"));
				
				list.add(workExchangeVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
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
		return list;
	}
	
	
	
	public static void main(String[] args) {
		WorkExchangeJDBCDAO dao = new WorkExchangeJDBCDAO();
		
		//新增
//		WorkExchangeVO workExchangeVO1 = new WorkExchangeVO();
//		workExchangeVO1.setMemID("M0003");
//		workExchangeVO1.setEmpID("E0003");
//		workExchangeVO1.setWeRoomID("R00043");
//		workExchangeVO1.setWeName("釣魚");
//		workExchangeVO1.setWeContent("最近魚價上漲，懇請一名天才小釣手，幫忙釣魚給大家吃!!");
//		workExchangeVO1.setWePic(( new BLOB()).writeBlob("images/fishing.jpg"));
//		workExchangeVO1.setWeRoomPic((new BLOB().writeBlob("images/fishing.jpg")));
//		workExchangeVO1.setWeStart(java.sql.Date.valueOf("2018-12-20"));
//		workExchangeVO1.setWeEnd(java.sql.Date.valueOf("2018-12-31"));
//		dao.insert(workExchangeVO1);
//		System.out.println("Successfully Insert!!");
		
		//修改
//		WorkExchangeVO workExchangeVO2 = new WorkExchangeVO();
//		workExchangeVO2.setWeID(1021);
//		workExchangeVO2.setWeName("天才小釣手");
//		dao.update(workExchangeVO2);
//		System.out.println("Successfully Update!!!!");
		
		//刪除
//		WorkExchangeVO workExchangeVO3 = new WorkExchangeVO();
//		workExchangeVO3.setWeID(1021);
//		dao.delete(workExchangeVO3);
//		System.out.println("Successfully Delete!!");
		
		//查找一筆
//		WorkExchangeVO workExchangeVO4 = dao.findByPrimaryKey(1022);
//		System.out.println(workExchangeVO4.getWeID());
//		System.out.println(workExchangeVO4.getMemID());
//		System.out.println(workExchangeVO4.getEmpID());
//		System.out.println(workExchangeVO4.getWeRoomID());
//		System.out.println(workExchangeVO4.getWeName());
//		System.out.println(workExchangeVO4.getWeContent());
//		new BLOB().readBlob(workExchangeVO4.getWePic(), "fishing.jpg");
//		System.out.println(workExchangeVO4.getWeRoomPic());
//		System.out.println(workExchangeVO4.getWeStart());
//		System.out.println(workExchangeVO4.getWeEnd());
		
		//查找全部
		List<WorkExchangeVO> list = dao.getAll();
		for(WorkExchangeVO wl : list) {
			System.out.println(wl.getWeID());
			System.out.println(wl.getMemID());
			System.out.println(wl.getEmpID());
			System.out.println(wl.getWeRoomID());
			System.out.println(wl.getWeName());
			System.out.println(wl.getWeContent());
//			new BLOB().readBlob(workExchangeVO4.getWePic(), "fishing.jpg");
			System.out.println(wl.getWeStart());
			System.out.println(wl.getWeEnd());
			System.out.println("-------------------------------------");
		}				
	}
}

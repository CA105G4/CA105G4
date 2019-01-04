package com.messageReport.model;

import java.util.*;
import java.sql.*;

public class MessageReportJDBCDAO implements MessageReportDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA105G4";
	String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO messagereport (mrid,artid,msgid,mrreason,mrstate) VALUES (msgrep_seq.NEXTVAL, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT mrid,artid,msgid,mrreason,mrstate FROM messagereport order by mrid";
	private static final String GET_ONE_STMT = 
		"SELECT mrid,artid,msgid,mrreason,mrstate FROM messagereport where mrid = ?";
	private static final String DELETE = 
		"DELETE FROM messagereport where mrid = ?";
	private static final String UPDATE = 
		"UPDATE messagereport set artid=?, msgid=?, mrreason=?, mrstate=? where mrid = ?";
	private static final String UPDATE_MESSAGE = 
		"UPDATE message set msgstate=? where msgid = ?";
	
	@Override
	public void insert(MessageReportVO messageReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, messageReportVO.getArtid());
			pstmt.setInt(2, messageReportVO.getMsgid());
			pstmt.setString(3, messageReportVO.getMrreason());
			pstmt.setInt(4, messageReportVO.getMrstate());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
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
	public void update(MessageReportVO messageReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, messageReportVO.getArtid());
			pstmt.setInt(2, messageReportVO.getMsgid());
			pstmt.setString(3, messageReportVO.getMrreason());
			pstmt.setInt(4, messageReportVO.getMrstate());
			pstmt.setInt(5, messageReportVO.getMrid());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
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
	public void delete(Integer mrid) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, mrid);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
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
	public MessageReportVO findByPrimaryKey(Integer mrid) {
		MessageReportVO msgreVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, mrid);

			rs =pstmt.executeQuery();
			
			while(rs.next()) {
				msgreVO = new MessageReportVO();
				msgreVO.setMrid(rs.getInt("mrid"));
				msgreVO.setArtid(rs.getInt("artid"));
				msgreVO.setMsgid(rs.getInt("msgid"));
				msgreVO.setMrreason(rs.getString("mrreason"));
				msgreVO.setMrstate(rs.getInt("mrstate"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
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
		
		return msgreVO;
	}

	@Override
	public List<MessageReportVO> getAll() {
		List<MessageReportVO> list = new ArrayList<MessageReportVO>();
		MessageReportVO messageReportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs =pstmt.executeQuery();
			
			while(rs.next()) {
				messageReportVO = new MessageReportVO();
				messageReportVO.setMrid(rs.getInt("mrid"));
				messageReportVO.setArtid(rs.getInt("artid"));
				messageReportVO.setMsgid(rs.getInt("msgid"));
				messageReportVO.setMrreason(rs.getString("mrreason"));
				messageReportVO.setMrstate(rs.getInt("mrstate"));
				list.add(messageReportVO);
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
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
		return list;
	}
	
	@Override
	public void updateMessageStatus(MessageReportVO messageReportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, messageReportVO.getArtid());
			pstmt.setInt(2, messageReportVO.getMsgid());
			pstmt.setString(3, messageReportVO.getMrreason());
			pstmt.setInt(4, messageReportVO.getMrstate());
			pstmt.setInt(5, messageReportVO.getMrid());
			
//			System.out.println(messageReportVO.getMsgid());
			pstmt2 = con.prepareStatement(UPDATE_MESSAGE);
			pstmt2.setInt(1, 1);
			pstmt2.setInt(2, messageReportVO.getMsgid());
			
			con.setAutoCommit(false);
			
			pstmt.executeUpdate();
			pstmt2.executeUpdate();
			
			System.out.println("Message Update Operation success!");
			
			con.commit();
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			try {
				// 發生例外即進行rollback動作
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
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
	
	
	public static void main(String[] args) {
		MessageReportJDBCDAO dao = new MessageReportJDBCDAO();
		
		
		//insert
//		MessageReportVO messageReportVO1 = new MessageReportVO();
//		messageReportVO1.setArtid(1003);
//		messageReportVO1.setMsgid(1004);
//		messageReportVO1.setMrreason("測試123ABC");
//		messageReportVO1.setMrstate(0);
//		dao.insert(messageReportVO1);
//		System.out.println("insert success");
		
		//update
//		MessageReportVO messageReportVO2 = new MessageReportVO();
//		messageReportVO2.setMrid(1006);
//		messageReportVO2.setArtid(1003);
//		messageReportVO2.setMsgid(1004);
//		messageReportVO2.setMrreason("測試456ABC");
//		messageReportVO2.setMrstate(0);
//		dao.update(messageReportVO2);
//		System.out.println("update success");
		
		//delete
//		dao.delete(1006);
//		System.out.println("delete success");
		
		//select one
//		MessageReportVO messageReportVO3 = dao.findByPrimaryKey(1001);
//		System.out.print(messageReportVO3.getMrid() + ",");
//		System.out.print(messageReportVO3.getArtid() + ",");
//		System.out.print(messageReportVO3.getMsgid() + ",");
//		System.out.print(messageReportVO3.getMrreason() + ",");
//		System.out.println(messageReportVO3.getMrstate());
//		System.out.println("---------------------");
		
		//select all
		List<MessageReportVO> list = dao.getAll();
		for(MessageReportVO messageReportVO : list) {
			System.out.print(messageReportVO.getMrid() + ",");
			System.out.print(messageReportVO.getArtid() + ",");
			System.out.print(messageReportVO.getMsgid() + ",");
			System.out.print(messageReportVO.getMrreason() + ",");
			System.out.print(messageReportVO.getMrstate());
			System.out.println();
		}
	}


}

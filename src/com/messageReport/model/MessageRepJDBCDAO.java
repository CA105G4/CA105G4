package com.messageReport.model;

import java.util.*;
import java.sql.*;

public class MessageRepJDBCDAO implements MessageRepDAO_interface{
	
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
	
	@Override
	public void insert(MessageRepVO messageRepVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, messageRepVO.getArtid());
			pstmt.setInt(2, messageRepVO.getMsgid());
			pstmt.setString(3, messageRepVO.getMrreason());
			pstmt.setInt(4, messageRepVO.getMrstate());

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
	public void update(MessageRepVO messageRepVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, messageRepVO.getArtid());
			pstmt.setInt(2, messageRepVO.getMsgid());
			pstmt.setString(3, messageRepVO.getMrreason());
			pstmt.setInt(4, messageRepVO.getMrstate());
			pstmt.setInt(5, messageRepVO.getMrid());

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
	public MessageRepVO findByPrimaryKey(Integer mrid) {
		MessageRepVO msgreVO = null;
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
				msgreVO = new MessageRepVO();
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
	public List<MessageRepVO> getAll() {
		List<MessageRepVO> list = new ArrayList<MessageRepVO>();
		MessageRepVO msgreVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs =pstmt.executeQuery();
			
			while(rs.next()) {
				msgreVO = new MessageRepVO();
				msgreVO.setMrid(rs.getInt("mrid"));
				msgreVO.setArtid(rs.getInt("artid"));
				msgreVO.setMsgid(rs.getInt("msgid"));
				msgreVO.setMrreason(rs.getString("mrreason"));
				msgreVO.setMrstate(rs.getInt("mrstate"));
				list.add(msgreVO);
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

	
	public static void main(String[] args) {
		MessageRepJDBCDAO dao = new MessageRepJDBCDAO();
		
		
		//insert
//		MessageRepVO msgreVO1 = new MessageRepVO();
//		msgreVO1.setArtid(1003);
//		msgreVO1.setMsgid(1004);
//		msgreVO1.setMrreason("測試123ABC");
//		msgreVO1.setMrstate(0);
//		dao.insert(msgreVO1);
//		System.out.println("insert success");
		
		//update
//		MessageRepVO msgreVO2 = new MessageRepVO();
//		msgreVO2.setMrid(1006);
//		msgreVO2.setArtid(1003);
//		msgreVO2.setMsgid(1004);
//		msgreVO2.setMrreason("測試456ABC");
//		msgreVO2.setMrstate(0);
//		dao.update(msgreVO2);
//		System.out.println("update success");
		
		//delete
//		dao.delete(1006);
//		System.out.println("delete success");
		
		//select one
//		MessageRepVO msgreVO3 = dao.findByPrimaryKey(1001);
//		System.out.print(msgreVO3.getMrid() + ",");
//		System.out.print(msgreVO3.getArtid() + ",");
//		System.out.print(msgreVO3.getMsgid() + ",");
//		System.out.print(msgreVO3.getMrreason() + ",");
//		System.out.println(msgreVO3.getMrstate());
//		System.out.println("---------------------");
		
		//select all
//		List<MessageRepVO> list = dao.getAll();
//		for(MessageRepVO msgre : list) {
//			System.out.print(msgre.getMrid() + ",");
//			System.out.print(msgre.getArtid() + ",");
//			System.out.print(msgre.getMsgid() + ",");
//			System.out.print(msgre.getMrreason() + ",");
//			System.out.print(msgre.getMrstate());
//			System.out.println();
//		}
	}
}

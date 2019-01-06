package com.message.model;

import java.util.*;

import com.article.model.ArticleVO;

import java.sql.*;

public class MessageJDBCDAO implements MessageDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA105G4";
	String passwd = "123456";

	private static final String INSERT_STMT = 
		"INSERT INTO message (msgid,artid,msgmemid,msgcontent,msgdate,msgstate) VALUES (msg_seq.NEXTVAL, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT msgid,artid,msgmemid,msgcontent,to_char(msgdate,'yyyy-mm-dd') msgdate,msgstate FROM message order by msgid";
	private static final String GET_ONE_STMT = 
		"SELECT msgid,artid,msgmemid,msgcontent,to_char(msgdate,'yyyy-mm-dd') msgdate,msgstate FROM message where msgid = ?";
	private static final String DELETE = 
		"DELETE FROM message where msgid = ?";
	private static final String UPDATE = 
		"UPDATE message set artid=?, msgmemid=?, msgcontent=?, msgdate=?, msgstate=? where msgid = ?";
	private static final String GET_ARTICLE_MESSAGE = 
		"SELECT msgid,artid,msgmemid,msgcontent,to_char(msgdate,'yyyy-mm-dd') msgdate,msgstate FROM message where artid = ?  and msgstate = 0  order by msgid desc";
	
	
	@Override
	public void insert(MessageVO messageVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, messageVO.getArtid());
			pstmt.setString(2, messageVO.getMsgmemid());
			pstmt.setString(3, messageVO.getMsgcontent());
			pstmt.setDate(4, messageVO.getMsgdate());
			pstmt.setInt(5, messageVO.getMsgstate());

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
	public void update(MessageVO messageVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, messageVO.getArtid());
			pstmt.setString(2, messageVO.getMsgmemid());
			pstmt.setString(3, messageVO.getMsgcontent());
			pstmt.setDate(4, messageVO.getMsgdate());
			pstmt.setInt(5, messageVO.getMsgstate());
			pstmt.setInt(6, messageVO.getMsgid());

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
	public void delete(Integer msgid) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);


			pstmt.setInt(1, msgid);

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
	public MessageVO findByPrimaryKey(Integer msgid) {
		
		MessageVO messageVO =null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);


			pstmt.setInt(1, msgid);

			rs =pstmt.executeQuery();
			
			while(rs.next()) {
				messageVO = new MessageVO();
				messageVO.setMsgid(rs.getInt("msgid"));
				messageVO.setArtid(rs.getInt("artid"));
				messageVO.setMsgmemid(rs.getString("msgmemid"));
				messageVO.setMsgcontent(rs.getString("msgcontent"));
				messageVO.setMsgdate(rs.getDate("msgdate"));
				messageVO.setMsgstate(rs.getInt("msgstate"));
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
		return messageVO;
	}

	@Override
	public List<MessageVO> getAll() {
		List<MessageVO> list = new ArrayList<MessageVO>();
		MessageVO messageVO =null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);


			rs =pstmt.executeQuery();
			
			while(rs.next()) {
				messageVO = new MessageVO();
				messageVO.setMsgid(rs.getInt("msgid"));
				messageVO.setArtid(rs.getInt("artid"));
				messageVO.setMsgmemid(rs.getString("msgmemid"));
				messageVO.setMsgcontent(rs.getString("msgcontent"));
				messageVO.setMsgdate(rs.getDate("msgdate"));
				messageVO.setMsgstate(rs.getInt("msgstate"));
				list.add(messageVO);
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
	public List<MessageVO> findByArticle(Integer artid) {
		List<MessageVO> list = new ArrayList<MessageVO>();
		MessageVO messageVO =null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ARTICLE_MESSAGE);

			pstmt.setInt(1, artid);
			rs =pstmt.executeQuery();
			
			while(rs.next()) {
				messageVO = new MessageVO();
				messageVO.setMsgid(rs.getInt("msgid"));
				messageVO.setArtid(rs.getInt("artid"));
				messageVO.setMsgmemid(rs.getString("msgmemid"));
				messageVO.setMsgcontent(rs.getString("msgcontent"));
				messageVO.setMsgdate(rs.getDate("msgdate"));
				messageVO.setMsgstate(rs.getInt("msgstate"));
				list.add(messageVO);
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
		
		MessageJDBCDAO dao = new MessageJDBCDAO();
		
		//insert
//		MessageVO msgVO1 = new MessageVO();
//		msgVO1.setArtid(1003);
//		msgVO1.setMsgmemid("M0003");
//		msgVO1.setMsgcontent("測試ABC123");
//		msgVO1.setMsgdate(java.sql.Date.valueOf("2018-12-09"));
//		msgVO1.setMsgstate(0);
//		dao.insert(msgVO1);
//		System.out.println("insert success");
		
		//update
//		MessageVO msgVO2 = new MessageVO();
//		msgVO2.setMsgid(1006);
//		msgVO2.setArtid(1003);
//		msgVO2.setMsgmemid("M0003");
//		msgVO2.setMsgcontent("測試ABC456");
//		msgVO2.setMsgdate(java.sql.Date.valueOf("2018-12-09"));
//		msgVO2.setMsgstate(0);
//		dao.update(msgVO2);
//		System.out.println("update success");
		
		//delete
		
//		dao.delete(1006);
//		System.out.println("delete success");
		
//		//select one
//		MessageVO msgVO3 = dao.findByPrimaryKey(1001);
//		System.out.print(msgVO3.getMsgid() + ",");
//		System.out.print(msgVO3.getArtid() + ",");
//		System.out.print(msgVO3.getMsgmemid() + ",");
//		System.out.print(msgVO3.getMsgcontent() + ",");
//		System.out.print(msgVO3.getMsgdate() + ",");
//		System.out.println(msgVO3.getMsgstate() + ",");
//		System.out.println("---------------------");
		
		
		//select all
//		List<MessageVO> list = dao.getAll();
//		for(MessageVO msg : list) {
//			System.out.print(msg.getMsgid() + ",");
//			System.out.print(msg.getArtid() + ",");
//			System.out.print(msg.getMsgmemid() + ",");
//			System.out.print(msg.getMsgcontent() + ",");
//			System.out.print(msg.getMsgdate() + ",");
//			System.out.print(msg.getMsgstate());
//			System.out.println("");
//		}
	}

	
	
}

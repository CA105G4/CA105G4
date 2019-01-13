package com.message.model;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.article.model.ArticleVO;
import java.sql.*;

public class MessageDAO implements MessageDAO_interface{
	private static DataSource ds =null;	
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CA105G4DB");
		} catch (NamingException e) {
			e.printStackTrace();
		} 
	}
	
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

				con=ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setInt(1, messageVO.getArtid());
				pstmt.setString(2, messageVO.getMsgmemid());
				pstmt.setString(3, messageVO.getMsgcontent());
				pstmt.setDate(4, messageVO.getMsgdate());
				pstmt.setInt(5, messageVO.getMsgstate());

				pstmt.executeUpdate();

				// Handle any driver errors
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

				con=ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setInt(1, messageVO.getArtid());
				pstmt.setString(2, messageVO.getMsgmemid());
				pstmt.setString(3, messageVO.getMsgcontent());
				pstmt.setDate(4, messageVO.getMsgdate());
				pstmt.setInt(5, messageVO.getMsgstate());
				pstmt.setInt(6, messageVO.getMsgid());

				pstmt.executeUpdate();

				// Handle any driver errors
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

				con=ds.getConnection();
				pstmt = con.prepareStatement(DELETE);


				pstmt.setInt(1, msgid);

				pstmt.executeUpdate();

				// Handle any driver errors
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

				con=ds.getConnection();
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

				con=ds.getConnection();
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

				con=ds.getConnection();
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
}

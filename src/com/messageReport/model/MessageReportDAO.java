package com.messageReport.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class MessageReportDAO implements MessageReportDAO_interface{
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

				con=ds.getConnection();
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setInt(1, messageReportVO.getArtid());
				pstmt.setInt(2, messageReportVO.getMsgid());
				pstmt.setString(3, messageReportVO.getMrreason());
				pstmt.setInt(4, messageReportVO.getMrstate());

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
		public void update(MessageReportVO messageReportVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con=ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setInt(1, messageReportVO.getArtid());
				pstmt.setInt(2, messageReportVO.getMsgid());
				pstmt.setString(3, messageReportVO.getMrreason());
				pstmt.setInt(4, messageReportVO.getMrstate());
				pstmt.setInt(5, messageReportVO.getMrid());

				pstmt.executeUpdate();

				// Handle any driver errors
			}catch (SQLException se) {
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

				con=ds.getConnection();
				pstmt = con.prepareStatement(DELETE);

				pstmt.setInt(1, mrid);

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
		public MessageReportVO findByPrimaryKey(Integer mrid) {
			MessageReportVO msgreVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {

				con=ds.getConnection();
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

				con=ds.getConnection();
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

				con=ds.getConnection();
				pstmt = con.prepareStatement(UPDATE);

				pstmt.setInt(1, messageReportVO.getArtid());
				pstmt.setInt(2, messageReportVO.getMsgid());
				pstmt.setString(3, messageReportVO.getMrreason());
				pstmt.setInt(4, messageReportVO.getMrstate());
				pstmt.setInt(5, messageReportVO.getMrid());
				
//				System.out.println(messageReportVO.getMsgid());
				pstmt2 = con.prepareStatement(UPDATE_MESSAGE);
				pstmt2.setInt(1, 1);
				pstmt2.setInt(2, messageReportVO.getMsgid());
				
				con.setAutoCommit(false);
				
				pstmt.executeUpdate();
				pstmt2.executeUpdate();
				
				System.out.println("Message Update Operation success!");
				
				con.commit();
				// Handle any driver errors
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
}

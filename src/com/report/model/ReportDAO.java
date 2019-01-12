package com.report.model;

import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.sql.*;

public class ReportDAO implements ReportDAO_interface{
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
			"INSERT INTO report (repid,artid,repreason,repmemid,repdate,repstate) VALUES (ARTREP_seq.nextval, ?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT repid,artid,repreason,repmemid,to_char(repdate,'yyyy-mm-dd') repdate,repstate FROM report order by repid";
		private static final String GET_ONE_STMT = 
			"SELECT repid,artid,repreason,repmemid,to_char(repdate,'yyyy-mm-dd') repdate,repstate FROM report where repid = ?";
		private static final String DELETE = 
			"DELETE FROM report where repid = ?";
		private static final String UPDATE = 
			"UPDATE report set artid=?, repreason=?, repmemid=?, repdate=?, repstate=? where repid = ?";
		private static final String UPDATE_ARTICLE = 
			"UPDATE article set artstate=? where artid = ?";
		
	@Override
	public void insert(ReportVO reportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con=ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1,reportVO.getArtid());
			pstmt.setString(2,reportVO.getRepreason() );
			pstmt.setString(3,reportVO.getRepmemid() );
			pstmt.setDate(4,reportVO.getRepdate() );
			pstmt.setInt(5,reportVO.getRepstate() );

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
	public void update(ReportVO reportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con=ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1,reportVO.getArtid());
			pstmt.setString(2,reportVO.getRepreason() );
			pstmt.setString(3,reportVO.getRepmemid() );
			pstmt.setDate(4,reportVO.getRepdate() );
			pstmt.setInt(5,reportVO.getRepstate() );
			pstmt.setInt(6,reportVO.getRepid() );

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
	public void delete(Integer repid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con=ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1,repid);

			pstmt.executeUpdate();

			// Handle any driver errors
		}  catch (SQLException se) {
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
	public ReportVO findByPrimaryKey(Integer repid) {
		ReportVO reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1,repid);

			rs =pstmt.executeQuery();

			while(rs.next()) {
				reportVO = new ReportVO();
				reportVO.setRepid(rs.getInt("repid"));;
				reportVO.setArtid(rs.getInt("artid"));
				reportVO.setRepreason(rs.getString("repreason"));
				reportVO.setRepmemid(rs.getString("repmemid"));
				reportVO.setRepdate(rs.getDate("repdate"));
				reportVO.setRepstate(rs.getInt("repstate"));
			}
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
		return reportVO;
	}

	@Override
	public List<ReportVO> getAll() {
		List<ReportVO> list = new ArrayList<ReportVO>();
		ReportVO reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con=ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs =pstmt.executeQuery();

			while(rs.next()) {
				reportVO = new ReportVO();
				reportVO.setRepid(rs.getInt("repid"));;
				reportVO.setArtid(rs.getInt("artid"));
				reportVO.setRepreason(rs.getString("repreason"));
				reportVO.setRepmemid(rs.getString("repmemid"));
				reportVO.setRepdate(rs.getDate("repdate"));
				reportVO.setRepstate(rs.getInt("repstate"));
				list.add(reportVO);
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
	public void updateArticleStatus(ReportVO reportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		try {

			con=ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1,reportVO.getArtid());
			pstmt.setString(2,reportVO.getRepreason() );
			pstmt.setString(3,reportVO.getRepmemid() );
			pstmt.setDate(4,reportVO.getRepdate() );
			pstmt.setInt(5,reportVO.getRepstate() );
			pstmt.setInt(6,reportVO.getRepid() );
			
			pstmt2 = con.prepareStatement(UPDATE_ARTICLE);
			pstmt2.setInt(1, 0);
			pstmt2.setInt(2, reportVO.getArtid());
			
			con.setAutoCommit(false);
			
			pstmt.executeUpdate();
			pstmt2.executeUpdate();
			System.out.println("Article Update Operation success!");	
			con.commit();
			// Handle any driver errors
		}catch (SQLException se) {
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

package com.report.model;

import java.util.*;
import java.sql.*;

public class ReportDAO implements ReportDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "ca105g4";
	String passwd = "1234";

	
	
		private static final String INSERT_STMT = 
			"INSERT INTO report (repid,artid,artreason,repmemid,repdate,repstate) VALUES (ARTREP_seq.nextval, ?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT repid,artid,artreason,repmemid,to_char(repdate,'yyyy-mm-dd') repdate,repstate FROM report order by repid";
		private static final String GET_ONE_STMT = 
			"SELECT repid,artid,artreason,repmemid,to_char(repdate,'yyyy-mm-dd') repdate,repstate FROM report where repid = ?";
		private static final String DELETE = 
			"DELETE FROM report where repid = ?";
		private static final String UPDATE = 
			"UPDATE report set artid=?, artreason=?, repmemid=?, repdate=?, repstate=? where repid = ?";
		
	@Override
	public void insert(ReportVO reportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1,reportVO.getArtid());
			pstmt.setString(2,reportVO.getArtreason() );
			pstmt.setString(3,reportVO.getRepmemid() );
			pstmt.setDate(4,reportVO.getRepdate() );
			pstmt.setInt(5,reportVO.getRepstate() );

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
	public void update(ReportVO reportVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1,reportVO.getArtid());
			pstmt.setString(2,reportVO.getArtreason() );
			pstmt.setString(3,reportVO.getRepmemid() );
			pstmt.setDate(4,reportVO.getRepdate() );
			pstmt.setInt(5,reportVO.getRepstate() );
			pstmt.setInt(6,reportVO.getRepid() );

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
	public void delete(Integer repid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1,repid);

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
	public ReportVO findByPrimaryKey(Integer repid) {
		ReportVO reportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1,repid);

			rs =pstmt.executeQuery();

			while(rs.next()) {
				reportVO = new ReportVO();
				reportVO.setRepid(rs.getInt("repid"));;
				reportVO.setArtid(rs.getInt("artid"));
				reportVO.setArtreason(rs.getString("artreason"));
				reportVO.setRepmemid(rs.getString("repmemid"));
				reportVO.setRepdate(rs.getDate("repdate"));
				reportVO.setRepstate(rs.getInt("repstate"));
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs =pstmt.executeQuery();

			while(rs.next()) {
				reportVO = new ReportVO();
				reportVO.setRepid(rs.getInt("repid"));;
				reportVO.setArtid(rs.getInt("artid"));
				reportVO.setArtreason(rs.getString("artreason"));
				reportVO.setRepmemid(rs.getString("repmemid"));
				reportVO.setRepdate(rs.getDate("repdate"));
				reportVO.setRepstate(rs.getInt("repstate"));
				list.add(reportVO);
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
			ReportDAO dao = new ReportDAO();
			
			//insert
//			ReportVO reportVO1 = new ReportVO();
//			reportVO1.setArtid(1001);
//			reportVO1.setArtreason("測試ABC123");
//			reportVO1.setRepmemid("M0001");
//			reportVO1.setRepdate(java.sql.Date.valueOf("2018-12-09"));
//			reportVO1.setRepstate(0);
//			dao.insert(reportVO1);
//			System.out.println("insert success");
			
			
			//update
//			ReportVO reportVO2 = new ReportVO();
//			reportVO2.setRepid(1006);
//			reportVO2.setArtid(1001);
//			reportVO2.setArtreason("測試ABC456");
//			reportVO2.setRepmemid("M0001");
//			reportVO2.setRepdate(java.sql.Date.valueOf("2018-12-09"));
//			reportVO2.setRepstate(0);
//			dao.update(reportVO2);
//			System.out.println("update success");
			
			//delete
//			dao.delete(1006);
//			System.out.println("delete success");
			
			//select pk
//			ReportVO reportVO3 = dao.findByPrimaryKey(1001);
//			System.out.print(reportVO3.getRepid() + ",");
//			System.out.print(reportVO3.getArtid()+ ",");
//			System.out.print(reportVO3.getArtreason()+ ",");
//			System.out.print(reportVO3.getRepmemid()+ ",");
//			System.out.print(reportVO3.getRepdate()+ ",");
//			System.out.println(reportVO3.getRepstate());
//			System.out.println("----------------");
			
			
			//select all
//			List<ReportVO> list = dao.getAll();
//			for(ReportVO rep : list) {
//				System.out.print(rep.getRepid() + ",");
//				System.out.print(rep.getArtid()+ ",");
//				System.out.print(rep.getArtreason()+ ",");
//				System.out.print(rep.getRepmemid()+ ",");
//				System.out.print(rep.getRepdate()+ ",");
//				System.out.print(rep.getRepstate());
//				System.out.println();
//			}
		}
}

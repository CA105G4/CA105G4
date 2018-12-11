package com.article.model;

import java.util.*;
import java.sql.*;

public class ArtJDBCDAO implements ArtDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA105G4";
	String passwd = "1234";
	
	private static final String INSERT_STMT = 
			"INSERT INTO article (artid,memid,artpic,artexp,artstate,artdate) VALUES (art_seq.NEXTVAL, ?, ?, ?,?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT artid,memid,artpic,artexp,artstate,to_char(artdate,'yyyy-mm-dd') artdate FROM article order by artid";
		private static final String GET_ONE_STMT = 
			"SELECT artid,memid,artpic,artexp,artstate,to_char(artdate,'yyyy-mm-dd') artdate FROM article where artid = ?";
		private static final String DELETE = 
			"DELETE FROM article where artid = ?";
		private static final String UPDATE = 
			"UPDATE article set memid=?, artpic=?, artexp=?, artstate=?,artdate=? where artid = ?";
		
	@Override
	public void insert(ArtVO artVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, artVO.getMemid());
			pstmt.setBytes(2, artVO.getArtpic());
			pstmt.setString(3, artVO.getArtexp());
			pstmt.setInt(4, artVO.getArtstate());
			pstmt.setDate(5, artVO.getArtdate());

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
	public void update(ArtVO artVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, artVO.getMemid());
			pstmt.setBytes(2, artVO.getArtpic());
			pstmt.setString(3, artVO.getArtexp());
			pstmt.setInt(4, artVO.getArtstate());
			pstmt.setDate(5, artVO.getArtdate());
			pstmt.setInt(6, artVO.getArtid());

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
	public void delete(Integer artid) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, artid);

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
	public ArtVO findByPrimaryKey(Integer artid) {
		ArtVO artVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, artid);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				artVO = new ArtVO();
				artVO.setArtid(rs.getInt("artid"));
				artVO.setMemid(rs.getString("memid"));
				artVO.setArtpic(rs.getBytes("artpic"));
				artVO.setArtexp(rs.getString("artexp"));
				artVO.setArtstate(rs.getInt("artstate"));
				artVO.setArtdate(rs.getDate("artdate"));
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
		return artVO;
	}

	@Override
	public List<ArtVO> getAll() {
		List<ArtVO> list = new ArrayList<ArtVO>();
		ArtVO artVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				artVO = new ArtVO();
				artVO.setArtid(rs.getInt("artid"));
				artVO.setMemid(rs.getString("memid"));
				artVO.setArtpic(rs.getBytes("artpic"));
				artVO.setArtexp(rs.getString("artexp"));
				artVO.setArtstate(rs.getInt("artstate"));
				artVO.setArtdate(rs.getDate("artdate"));
				list.add(artVO);
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
		 ArtJDBCDAO dao = new ArtJDBCDAO();
		 
		 
		 //insert
//		 ArtVO artVO1 = new ArtVO();
//		 artVO1.setMemid("M0001");
//		 artVO1.setArtpic(null);
//		 artVO1.setArtexp("測試ABC123");
//		 artVO1.setArtstate(0);
//		 artVO1.setArtdate(java.sql.Date.valueOf("2018-12-09"));
//		 dao.insert(artVO1);
//		 System.out.println("insert success");
		 
		 //update
//		 ArtVO artVO2 = new ArtVO();
//		 artVO2.setArtid(1006);
//		 artVO2.setMemid("M0001");
//		 artVO2.setArtpic(null);
//		 artVO2.setArtexp("測試ABC456");
//		 artVO2.setArtstate(0);
//		 artVO2.setArtdate(java.sql.Date.valueOf("2018-12-09"));
//		 dao.update(artVO2);
//		 System.out.println("update success");
		 
		 //delete
//		 dao.delete(1006);
//		 System.out.println("delete success");
		 
		 //select one
//		 ArtVO artVO3 = dao.findByPrimaryKey(1001);
//		 System.out.print( artVO3.getArtid()+ ",");
//		 System.out.print( artVO3.getMemid()+ ",");
//		 System.out.print( artVO3.getArtpic()+ ",");
//		 System.out.print( artVO3.getArtexp()+ ",");
//		 System.out.print( artVO3.getArtstate()+ ",");
//		 System.out.println( artVO3.getArtdate());
//		 System.out.println("---------------------");
		 
		 //select all
//		 List<ArtVO> list = dao.getAll();
//		 for (ArtVO art : list) {
//			System.out.print( art.getArtid()+ ",");
//			System.out.print( art.getMemid()+ ",");
//			System.out.print( art.getArtpic()+ ",");
//			System.out.print( art.getArtexp()+ ",");
//			System.out.print( art.getArtstate()+ ",");
//			System.out.print( art.getArtdate());
//			System.out.println();
//		}
	}

}

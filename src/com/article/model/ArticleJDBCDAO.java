package com.article.model;

import java.util.*;
import java.sql.*;

public class ArticleJDBCDAO implements ArticleDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA105G4";
	String passwd = "123456";
	
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
		private static final String GET_UNIQUE_MEMBER = 
			"SELECT distinct memid FROM article order by memid";
		private static final String GET_MEMBER_ARTICLE = 
			"SELECT artid,memid,artpic,artexp,artstate,to_char(artdate,'yyyy-mm-dd') artdate FROM article where memid = ?";
		private static final String GET_LATEST_THREE_STMT = 
				"SELECT artid,memid,artpic,artexp,artstate,to_char(artdate,'yyyy-mm-dd') artdate FROM article where  rownum <=3 and artstate = 1 order by artid desc ";
		
	@Override
	public void insert(ArticleVO articleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, articleVO.getMemid());
			pstmt.setBytes(2, articleVO.getArtpic());
			pstmt.setString(3, articleVO.getArtexp());
			pstmt.setInt(4, articleVO.getArtstate());
			pstmt.setDate(5, articleVO.getArtdate());

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
	public void update(ArticleVO articleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, articleVO.getMemid());
			pstmt.setBytes(2, articleVO.getArtpic());
			pstmt.setString(3, articleVO.getArtexp());
			pstmt.setInt(4, articleVO.getArtstate());
			pstmt.setDate(5, articleVO.getArtdate());
			pstmt.setInt(6, articleVO.getArtid());

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
	public ArticleVO findByPrimaryKey(Integer artid) {
		ArticleVO artVO = null;
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
				artVO = new ArticleVO();
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
	public List<ArticleVO> getAll() {
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO artVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				artVO = new ArticleVO();
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
	@Override
	public List<ArticleVO> UniqueMember() {
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO artVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_UNIQUE_MEMBER);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				artVO = new ArticleVO();
				artVO.setMemid(rs.getString("memid"));
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
	
	
	@Override
	public List<ArticleVO> findByMember(String memid) {
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO artVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_MEMBER_ARTICLE);
			pstmt.setString(1, memid);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				artVO = new ArticleVO();
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
	
	@Override
	public List<ArticleVO> getLatestThree() {
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO artVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_LATEST_THREE_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				artVO = new ArticleVO();
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
		 ArticleJDBCDAO dao = new ArticleJDBCDAO();
		 
		 
		 //insert
//		 ArticleVO articleVO1 = new ArticleVO();
//		 articleVO1.setMemid("M0001");
//		 articleVO1.setArtpic(null);
//		 articleVO1.setArtexp("測試ABC123");
//		 articleVO1.setArtstate(0);
//		 articleVO1.setArtdate(java.sql.Date.valueOf("2018-12-09"));
//		 dao.insert(articleVO1);
//		 System.out.println("insert success");
		 
		 //update
//		 ArticleVO articleVO2 = new ArticleVO();
//		 articleVO2.setArtid(1006);
//		 articleVO2.setMemid("M0001");
//		 articleVO2.setArtpic(null);
//		 articleVO2.setArtexp("測試ABC456");
//		 articleVO2.setArtstate(0);
//		 articleVO2.setArtdate(java.sql.Date.valueOf("2018-12-09"));
//		 dao.update(articleVO2);
//		 System.out.println("update success");
		 
		 //delete
//		 dao.delete(1006);
//		 System.out.println("delete success");
		 
//		 //select one
//		 ArticleVO articleVO3 = dao.findByPrimaryKey(1001);
//		 System.out.print( articleVO3.getArtid()+ ",");
//		 System.out.print( articleVO3.getMemid()+ ",");
//		 System.out.print( articleVO3.getArtpic()+ ",");
//		 System.out.print( articleVO3.getArtexp()+ ",");
//		 System.out.print( articleVO3.getArtstate()+ ",");
//		 System.out.println( articleVO3.getArtdate());
//		 System.out.println("---------------------");
		 
		 //select all
//		 List<ArticleVO> list = dao.getAll();
//		 for (ArticleVO art : list) {
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

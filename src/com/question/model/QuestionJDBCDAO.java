package com.question.model;

import java.util.*;
import java.sql.*;

public class QuestionJDBCDAO implements QuestionDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "CA105G4";
	String passwd = "1234";
	
	private static final String INSERT_STMT = 
			"INSERT INTO question (quesid,memid,empid,quescontent) VALUES (ques_seq.NEXTVAL, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT quesid,memid,empid,quescontent FROM question order by quesid";
		private static final String GET_ONE_STMT = 
			"SELECT quesid,memid,empid,quescontent FROM question where quesid = ?";
		private static final String DELETE = 
			"DELETE FROM question where quesid = ?";
		private static final String UPDATE = 
			"UPDATE question set memid=?, empid=?, quescontent=? where quesid = ?";
	@Override
	public void insert(QuestionVO questionVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, questionVO.getMemid());
			pstmt.setString(2, questionVO.getEmpid());
			pstmt.setString(3, questionVO.getQuescontent());

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
	public void update(QuestionVO questionVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, questionVO.getMemid());
			pstmt.setString(2, questionVO.getEmpid());
			pstmt.setString(3, questionVO.getQuescontent());
			pstmt.setInt(4, questionVO.getQuesid());

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
	public void delete(Integer quesid) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, quesid);

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
	public QuestionVO findByPrimaryKey(Integer quesid) {
		QuestionVO questionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, quesid);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVo 也稱為 Domain objects
				questionVO = new QuestionVO();
				questionVO.setQuesid(rs.getInt("quesid"));
				questionVO.setMemid(rs.getString("memid"));
				questionVO.setEmpid(rs.getString("empid"));
				questionVO.setQuescontent(rs.getString("quescontent"));
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
		return questionVO;
	}

	@Override
	public List<QuestionVO> getAll() {
		List<QuestionVO> list = new ArrayList<QuestionVO>();
		QuestionVO questionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVo 也稱為 Domain objects
				questionVO = new QuestionVO();
				questionVO.setQuesid(rs.getInt("quesid"));
				questionVO.setMemid(rs.getString("memid"));
				questionVO.setEmpid(rs.getString("empid"));
				questionVO.setQuescontent(rs.getString("quescontent"));
				list.add(questionVO);
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
		QuestionJDBCDAO dao = new QuestionJDBCDAO();
		
		//insert
//		QuestionVO questionVO1 = new QuestionVO();
//		questionVO1.setMemid("M0002");
//		questionVO1.setEmpid("E0002");
//		questionVO1.setQuescontent("TEST測試123");
//		dao.insert(questionVO1);
//		System.out.println("insert success");
		
		//update
//		QuestionVO questionVO2 = new QuestionVO();
//		questionVO2.setQuesid(1006);
//		questionVO2.setMemid("M0002");
//		questionVO2.setEmpid("E0002");
//		questionVO2.setQuescontent("TEST測試456");
//		dao.update(questionVO2);
//		System.out.println("update success");
		
		//delete
//		dao.delete(1006);
//		System.out.println("delete success");
		
		//select one
//		QuestionVO questionVO3 = dao.findByPrimaryKey(1005);
//		System.out.print(questionVO3.getQuesid()+ ",");
//		System.out.print(questionVO3.getMemid() + ",");
//		System.out.print(questionVO3.getEmpid() + ",");
//		System.out.print(questionVO3.getQuescontent());
//		System.out.println("---------------------");

		//select one
//		List<QuestionVO> list = dao.getAll();
//		for(QuestionVO ques : list) {
//		System.out.print(ques.getQuesid()+ ",");
//		System.out.print(ques.getMemid() + ",");
//		System.out.print(ques.getEmpid() + ",");
//		System.out.print(ques.getQuescontent());
//		System.out.println("---------------------");
//		}
	}
}

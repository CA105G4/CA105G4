package com.android.branch.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class BranchDAO implements BranchDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CA105G4DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

//	private static final String INSERT_SQL = "INSERT INTO Branch(braID,braName,braIntro,braPic,braTel,braVideo,braAddr,braLng,braLat,braState)"
//			+ " VALUES('B'||LPAD(to_char(bra_seq.nextval),2,'0'),?,?,?,?,?,?,?,?,?)";
//	private static final String UPDATE = "UPDATE Branch SET braName=?,braIntro=?,braPic=?,braTel=?,braVideo=?,braAddr=?,braLng=?,braLat=?,braState=? WHERE braID=?";
//
//	//private static final String DELETE = "DELETE FROM Branch WHERE braID=?";
//	private static final String FIND_BY_PK = "SELECT * FROM Branch WHERE braID=?";
//	private static final String FIND_ALL_STMT = "SELECT * FROM Branch where braId != 'B01' ORDER BY braID";

    private static final String INSERT_SQL = 
    		"INSERT INTO branch (braID, braName, braIntro, braPic, braTel, braVideo, braAddr, braLng, braLat) "
    		+ "VALUES ('B'||LPAD(to_char(bra_seq.NEXTVAL), 2, '0'), ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_SQL = "UPDATE branch set braName = ? where braID = ?";
    private static final String GET_ALL_SQL = "SELECT * from branch where braId != 'B00'";
    private static final String GET_ONE_SQL = "SELECT braID, braName, braIntro, braPic, braTel, braAddr, braLng, braLat, braState from branch where braID = ?"; 
	
	@Override
	public void insert(BranchVO branchVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_SQL);

			pstmt.setString(1, branchVO.getBraName());
			pstmt.setString(2, branchVO.getBraIntro());
			pstmt.setBytes(3, branchVO.getBraPic());
			pstmt.setString(4, branchVO.getBraTel());
			pstmt.setBytes(5, branchVO.getBraVideo());
			pstmt.setString(6, branchVO.getBraAddr());
			pstmt.setDouble(7, branchVO.getBraLng());
			pstmt.setDouble(8, branchVO.getBraLat());
			pstmt.setInt(9, branchVO.getBraState());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void update(BranchVO branchVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_SQL);

			pstmt.setString(1, branchVO.getBraName());
			pstmt.setString(2, branchVO.getBraIntro());
			pstmt.setBytes(3, branchVO.getBraPic());
			pstmt.setString(4, branchVO.getBraTel());
			pstmt.setBytes(5, branchVO.getBraVideo());
			pstmt.setString(6, branchVO.getBraAddr());
			pstmt.setDouble(7, branchVO.getBraLng());
			pstmt.setDouble(8, branchVO.getBraLat());
			pstmt.setInt(9, branchVO.getBraState());
			pstmt.setString(10, branchVO.getBraID());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public BranchVO findByPK(String braID) {
		BranchVO braVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_SQL);

			pstmt.setString(1, braID);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				braVO = new BranchVO();

				braVO.setBraID(rs.getString("braID"));
				braVO.setBraName(rs.getString("braName"));
				braVO.setBraIntro(rs.getString("braIntro"));
				braVO.setBraPic(rs.getBytes("braPic"));
				braVO.setBraTel(rs.getString("braTel"));
				braVO.setBraVideo(rs.getBytes("braVideo"));
				braVO.setBraAddr(rs.getString("braAddr"));
				braVO.setBraLng(rs.getDouble("braLng"));
				braVO.setBraLat(rs.getDouble("braLat"));
				braVO.setBraState(rs.getInt("braState"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return braVO;
	}

	@Override
	public List<BranchVO> getAll() {
		BranchVO braVO = new BranchVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		List<BranchVO> list = new ArrayList<BranchVO>();
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_SQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				braVO = new BranchVO();
				braVO.setBraID(rs.getString("braID"));
				braVO.setBraName(rs.getString("braName"));
				braVO.setBraIntro(rs.getString("braIntro"));
				braVO.setBraPic(rs.getBytes("braPic"));
				braVO.setBraTel(rs.getString("braTel"));
				braVO.setBraVideo(rs.getBytes("braVideo"));
				braVO.setBraAddr(rs.getString("braAddr"));
				braVO.setBraLng(rs.getDouble("braLng"));
				braVO.setBraLat(rs.getDouble("braLat"));
				braVO.setBraState(rs.getInt("braState"));

				list.add(braVO);

			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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

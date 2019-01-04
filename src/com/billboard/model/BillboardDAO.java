package com.billboard.model;

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


public class BillboardDAO implements BillboardDAO_interface {

	private static final String INSERT_SQL = "INSERT INTO Billboard(bbID,url,pic,bbStart,bbEnd) "
			+ "VALUES(bb_seq.nextval,?,?,?,?)";
	private static final String UPDATE = "UPDATE Billboard set url=?, pic=?, bbStart=?, bbEnd=? where bbID=?";
	private static final String DELETE = "DELETE FROM Billboard WHERE bbID=?";
	private static final String GET_ONE_SQL = "SELECT bbID,url,pic,bbStart,bbEnd FROM Billboard WHERE bbID=?";
	private static final String GET_ALL_SQL = "SELECT * FROM Billboard ORDER BY bbID ";

	private static DataSource ds = null;

	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CA105G4DB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(BillboardVO billboardVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_SQL);
			pstmt.setString(1, billboardVO.geturl());
			pstmt.setBytes(2, billboardVO.getpic());
			pstmt.setDate(3, billboardVO.getbbStart());
			pstmt.setDate(4, billboardVO.getbbEnd());

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
	public void update(BillboardVO billboardVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, billboardVO.geturl());
			pstmt.setBytes(2, billboardVO.getpic());
			pstmt.setDate(3, billboardVO.getbbStart());
			pstmt.setDate(4, billboardVO.getbbEnd());
			pstmt.setInt(5, billboardVO.getbbID());

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
	public void delete(Integer bbID) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1,  bbID);

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
	public BillboardVO findByPK(Integer bbID) {
		BillboardVO billboardVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_SQL);

			pstmt.setInt(1, bbID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				billboardVO = new BillboardVO();

				billboardVO.setbbID(rs.getInt("bbID"));
				billboardVO.seturl(rs.getString("url"));
				billboardVO.setpic(rs.getBytes("pic"));
				billboardVO.setbbStart(rs.getDate("bbStart"));
				billboardVO.setbbEnd(rs.getDate("bbEnd"));
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

		return billboardVO;
	}

	@Override
	public List<BillboardVO> getAll() {
		List<BillboardVO> list = new ArrayList<BillboardVO>();
		BillboardVO billboardVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_SQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				billboardVO = new BillboardVO();

				billboardVO.setbbID(rs.getInt("bbID"));
				billboardVO.seturl(rs.getString("URL"));
				billboardVO.setpic(rs.getBytes("pic"));
				billboardVO.setbbStart(rs.getDate("bbStart"));
				billboardVO.setbbEnd(rs.getDate("bbEnd"));

				list.add(billboardVO);
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

		return list;
	}
}

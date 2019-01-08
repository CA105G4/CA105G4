package com.android.branch.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BranchJDBCDAO implements BranchDAO_interface{

	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "CA105G4"; 
	private static final String PASSWORD = "123456"; 

    private static final String INSERT_SQL = 
    		"INSERT INTO branch (braID, braName, braIntro, braPic, braTel, braVideo, braAddr, braLng, braLat) "
    		+ "VALUES ('B'||LPAD(to_char(bra_seq.NEXTVAL), 2, '0'), ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_SQL = "UPDATE branch set braName = ? where braID = ?";
    private static final String GET_ALL_SQL = "SELECT * from branch where braId != 'B01'";
    private static final String GET_ONE_SQL = "SELECT braID, braName, braIntro, braPic, braTel, braAddr, braLng, braLat, braState from branch where braID = ?";    
    
    static {
    	try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}    	
    }
    
	@Override
	public void insert(BranchVO branchVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_SQL);
			
			pstmt.setString(1, branchVO.getBraName());
			pstmt.setString(2, branchVO.getBraIntro());
			pstmt.setBytes(3, branchVO.getBraPic());
			pstmt.setString(4, branchVO.getBraTel());
			pstmt.setBytes(5, branchVO.getBraVideo());
			pstmt.setString(6, branchVO.getBraAddr());
			pstmt.setDouble(7, branchVO.getBraLng());
			pstmt.setDouble(8, branchVO.getBraLat());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(con != null) {
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
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_SQL);
			
			pstmt.setString(1, branchVO.getBraName());
			pstmt.setString(2, branchVO.getBraID());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(con != null) {
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
		BranchVO branchVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_SQL);
			
			pstmt.setString(1, braID);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				branchVO = new BranchVO();
				
				branchVO.setBraID(rs.getString("braID"));
				branchVO.setBraName(rs.getString("braName"));
				branchVO.setBraIntro(rs.getString("braIntro"));
				branchVO.setBraPic(rs.getBytes("braPic"));
				branchVO.setBraTel(rs.getString("braTel"));
				branchVO.setBraAddr(rs.getString("braAddr"));
				branchVO.setBraLng(rs.getDouble("braLng"));
				branchVO.setBraLat(rs.getDouble("braLat"));
				branchVO.setBraState(rs.getInt("braState"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	
		return branchVO;
	}

	@Override
	public List<BranchVO> getAll() {
		List<BranchVO> list = new ArrayList<BranchVO>();
		BranchVO branchVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_SQL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				branchVO = new BranchVO();
				
				branchVO.setBraID(rs.getString("braID"));
				branchVO.setBraName(rs.getString("braName"));
				branchVO.setBraIntro(rs.getString("braIntro"));
				branchVO.setBraTel(rs.getString("braTel"));
				branchVO.setBraAddr(rs.getString("braAddr"));
				branchVO.setBraLng(rs.getDouble("braLng"));
				branchVO.setBraLat(rs.getDouble("braLat"));
				branchVO.setBraState(rs.getInt("braState"));
				
				list.add(branchVO);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	
		return list;
	}

	public static void main(String[] args) {
		BranchJDBCDAO dao = new BranchJDBCDAO();
		
		// 新增測試
//		BranchVO branchVO1 = new BranchVO();
//		branchVO1.setBraName("翔翔");
//		branchVO1.setBraIntro("test");
//		
//		branchVO1.setBraPic(new BLOB().writeBlob("images/logo5.png"));
//		
//		branchVO1.setBraTel("0946987321");
//		branchVO1.setBraVideo(null);
//		branchVO1.setBraAddr("桃園中壢區資策會");
//		branchVO1.setBraLng(121.555);
//		branchVO1.setBraLat(20.456);
//		
//		dao.insert(branchVO1);
//		System.out.println("新增成功");
//		System.out.println("------------------");
		
		// 修改
//		BranchVO branchVO2 = new BranchVO();
//		branchVO2.setBraID("B10");
//		branchVO2.setBraName("鈺翔");
//		dao.update(branchVO2);
//		System.out.println("修改成功");
//		System.out.println("--------------------");		
		
		
		List<String> braName = new ArrayList<String>();
		
		List<BranchVO> list = dao.getAll();
		
		braName.add("全部");
		
		for(BranchVO branchVO : list) {
			braName.add(branchVO.getBraName());
		}
		System.out.println(braName);
		
		// 查詢單筆
//		BranchVO branchVO3 = dao.findByPK("B03");
//		
//		System.out.println(branchVO3.getBraID());
//		System.out.println(branchVO3.getBraName());
//		System.out.println(branchVO3.getBraIntro());
//		System.out.println("圖片已輸出");
//		new BLOB().readBlob(branchVO3.getBraPic(), "input/logo5.png");  // 輸出至input 資料夾  
//		System.out.println(branchVO3.getBraTel());
//		System.out.println(branchVO3.getBraAddr());
//		System.out.println(branchVO3.getBraLng());
//		System.out.println(branchVO3.getBraLat());
//		System.out.println(branchVO3.getBraState());
//		
//		System.out.println("查詢單筆成功");
//		System.out.println("------------------");
		
		// 查詢全部
//		List<BranchVO> list = dao.getAll();
//		for(BranchVO aBranch : list) {
//			System.out.println(aBranch.getBraID());
//			System.out.println(aBranch.getBraName());
//			System.out.println(aBranch.getBraIntro());
//			System.out.println(aBranch.getBraTel());
//			System.out.println(aBranch.getBraAddr());
//			System.out.println(aBranch.getBraLng());
//			System.out.println(aBranch.getBraLat());
//			System.out.println(aBranch.getBraState());
//			System.out.println();
//		}
//		System.out.println("查詢全部成功");
//		System.out.println("-------------------");
	}
}

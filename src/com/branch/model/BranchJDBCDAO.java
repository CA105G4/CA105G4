package com.branch.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BranchJDBCDAO implements BranchDAO_interface{

	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "Test_Project"; 
	private static final String PASSWORD = "123456"; 

    private static final String INSERT_SQL = 
    		"INSERT INTO branch (braID, braName, braIntro, braPic, braTel, braVideo, braAddr, braLng, braLat) "
    		+ "VALUES ('B'||LPAD(to_char(bra_seq.NEXTVAL), 2, '0'), ?, ?, ?, ?, ?, ?, ?, ?)";
//    private static final String 
    
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
		
	}

	@Override
	public BranchVO findByPK(String braID) {
		return null;
	}

	@Override
	public List<BranchVO> getAll() {
		return null;
	}

	public static void main(String[] args) {
		BranchJDBCDAO dao = new BranchJDBCDAO();
		
		// 新增測試
		BranchVO branchVO1 = new BranchVO();
		branchVO1.setBraName("翔翔");
		branchVO1.setBraIntro("test");
		
		ByteArrayOutputStream baos = null;
		try {
			File file = new File("logo5.png");
			FileInputStream fis = new FileInputStream(file);
			baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[4096];
			int i;
			while((i = fis.read(buffer)) != -1) {
				baos.write(buffer, 0, i);
			}
			baos.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		branchVO1.setBraPic(baos.toByteArray());
		
		branchVO1.setBraTel("0946987321");
		branchVO1.setBraVideo(null);
		branchVO1.setBraAddr("桃園中壢區資策會");
		branchVO1.setBraLng(121.555);
		branchVO1.setBraLat(20.456);
		
		dao.insert(branchVO1);
		System.out.println("新增成功");
		
		
	}
}

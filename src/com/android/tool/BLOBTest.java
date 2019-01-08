package com.android.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*使用說明： 
		1:填入帳號密碼
		2:修改SQL command
		3:填入擺放圖片的目錄
		4:修改主鍵SET的資料型態及主鍵值*/

public class BLOBTest {
	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver"; 
        String url = "jdbc:oracle:thin:@localhost:1521:xe"; 
        String user = "CA105G4";    //資料庫帳號
        String password = "123456"; //資料庫密碼
        
        final String SQL = "UPDATE roomtype set rtpic = ? where rtid = ?";
//        final String SQL = "UPDATE coupon set cpnPic = ? where cpnid = ?";  //優惠券
//        final String path = "D:\\@@ 資策會(iii)\\教室裡的檔案\\Java_大吳\\SL330_JDBC課程分享_XE\\javawork_jdbc_XE\\資料流處理\\photo\\picFrom\\2835-2.gif";  //房型
        final String path = "D:\\@@ 資策會(iii)\\教室裡的檔案\\專題\\logo1.jpg";     // png 有問題 => 爛
        
        BLOB blob = new BLOB();
//        blob.batchUpdateBLOB(SQL, path, 10, "C00", 0);   //SQL, 路徑, 數量, 編號起始 => 優惠券
        blob.batchUpdateBLOB(SQL, path, 10, "RT", 0);   //SQL, 路徑, 數量, 編號起始 => 房型
        System.out.println("insert successful");
        
//        Connection con = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			Class.forName(driver); 
//			con = DriverManager.getConnection(url, user, password);
//			pstmt =  con.prepareStatement(SQL);
//			
//			pstmt.setBytes(1, BLOB.writeBlob(path));
//			pstmt.setString(2, "M0004");
//			
//			pstmt.executeUpdate();
//			
//			System.out.println("insert successful");
//		} catch (ClassNotFoundException e1) {
//			e1.printStackTrace();
//		} catch (SQLException e1) {
//			e1.printStackTrace();
//		} finally {
//	        if(pstmt != null) {
//	            try {
//	                pstmt.close();
//	            }   
//	            catch(SQLException e) {
//	                e.printStackTrace();
//	            }
//	        }
//	        if(con != null) {
//	            try {
//	            	con.close();
//	            }   
//	            catch(SQLException e) {
//	                e.printStackTrace();
//	            }
//	        }
//        }
    } 
}

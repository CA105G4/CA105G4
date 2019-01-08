package com.android.tool;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BLOB {
	public byte[] writeBlob(String path){
		
		ByteArrayOutputStream baos = null;
		try {
			File file = new File(path);
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
		return baos.toByteArray();
	}
	
	public void batchUpdateBLOB(String SQL, String path, int num, String ch, int id) {
		String driver = "oracle.jdbc.driver.OracleDriver"; 
        String url = "jdbc:oracle:thin:@localhost:1521:xe"; 
        String user = "CA105G4";    //資料庫帳號
        String password = "123456"; //資料庫密碼 
          
        Connection con = null;
        PreparedStatement pstmt = null;
        
        try {
			Class.forName(driver); 
			con = DriverManager.getConnection(url, user, password);
			pstmt =  con.prepareStatement(SQL);
			
			for(int i = 1; i <= num; i++) {
				
				String numId = ch + ((i > 9) ? i : "0" + (id + i));
				
				System.out.println(numId);
				pstmt.setBytes(1, writeBlob(path));
				pstmt.setString(2, numId);
				
				pstmt.addBatch();
				pstmt.clearParameters();
			}
			pstmt.executeBatch();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void readBlob(byte[] bytes, String fileName) {
		try {
			FileOutputStream fos = new FileOutputStream(fileName);
			fos.write(bytes);
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

package tool;

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

public class InsertBLOB {
	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver"; 
        String url = "jdbc:oracle:thin:@localhost:1521:xe"; 
        //資料庫帳號
        String user = "CA105G4";
        //資料庫密碼
        String password = "123456"; 
        
        File file;
		File[] files;
        try { 
            Class.forName(driver); 
            Connection conn = null;
            PreparedStatement pstmt = null;
            try {
                conn = DriverManager.getConnection(
                                   url, user, password);
                //SQL COMMAND
                pstmt =  conn.prepareStatement("update roomtype set rtpic = ? where rtid = ?");
                //擺放圖片的資料夾目錄路徑
                file = new File("");
                files = file.listFiles();
                for(int i = 0 ; i < files.length ; i++) {
                	File path = files[i];
                	FileInputStream in = new FileInputStream(path);
                	pstmt.setBinaryStream(1, in, path.length());
                	//主鍵的設定
                	pstmt.setInt(2, 100 + i );
                	pstmt.addBatch();
                }
                pstmt.executeBatch();
                System.out.println("insert successful");
            }
            catch(SQLException e) { 
                e.printStackTrace(); 
            }  
            catch(IOException e) { 
                e.printStackTrace(); 
            } 
            finally {
                if(pstmt != null) {
                    try {
                        pstmt.close();
                    }   
                    catch(SQLException e) {
                        e.printStackTrace();
                    }
                }
                if(conn != null) {
                    try {
                    	conn.close();
                    }   
                    catch(SQLException e) {
                        e.printStackTrace();
                    }
                }
            } 
        }
        catch(ClassNotFoundException e) { 
            System.out.println("找不到驅動程式"); 
            e.printStackTrace(); 
        } 
    } 
}

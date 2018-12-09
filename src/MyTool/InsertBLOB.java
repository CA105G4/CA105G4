package MyTool;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertBLOB {
	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver"; 
        String url = "jdbc:oracle:thin:@localhost:1521:xe"; 
        String user = "Test_Project"; 
        String password = "123456"; 
        
        try { 
            Class.forName(driver); 

            Connection conn = null;
            PreparedStatement pstmt = null;
            
            try {
                conn = DriverManager.getConnection(url, user, password);
                pstmt =  conn.prepareStatement("update coupon set cpnpic = ? where cpnid = ?");
//                pstmt =  conn.prepareStatement("insert into coupon (cpnid) values (?)");
//                pstmt =  conn.prepareStatement("select * from coupon");
//                for(int i = 1 ;i < 5;i++) {
                File file = new File("C:\\logo5.png");
                FileInputStream in = new FileInputStream(file);
                pstmt.setBinaryStream(1, in, in.available()); 
                pstmt.setString(2, "C0001");
//                pstmt.setString(1, "C1002");
//                ResultSet rs = pstmt.executeQuery();
//                rs.next();
//                System.out.println(rs.getString(1));
                
    			pstmt.executeUpdate();  
    			
//                pstmt.addBatch();
//                }
//                pstmt.executeBatch();
//    			pstmt.executeUpdate();
                System.out.println("insert successful");
            }
            catch(Exception e) { 
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
            System.out.println("§ä¤£¨ìÅX°Ê"); 
            e.printStackTrace(); 
        } 
    } 
}

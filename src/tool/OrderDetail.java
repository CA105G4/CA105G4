package tool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.sql.Date;

public class OrderDetail
{
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "Test_Project";
	private static final String PASSWORD = "123456";
	
	public static void main(String[] args) 
	{
		int[][] array = {{26, 27, 28, 29, 30},    // 5��
						 {10, 5, 6, 8, 7},
						 {8, 4, 5, 7, 10}};
		
//		Calendar cal = new Calendar()
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			// step1  
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("載入驅動");
			
			// step2
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("建立連線");
			
			// step3   
			stmt = con.createStatement(); 
			rs = stmt.executeQuery("select checkin, checkout from OrderDetail");
//			rs = stmt.executeQuery("select * from OrderDetail");
			
			ResultSetMetaData rsmd = rs.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();

			for (int i = 1; i <= numberOfColumns; i++)
				System.out.print(rsmd.getColumnName(i) + " ");

			Date checkin, checkout;
			
			System.out.println();
			while (rs.next()) 
			{
				checkin = rs.getDate(1);
				checkout = rs.getDate(2);
				System.out.println(checkin);
				System.out.println(checkout);
				
//				String date = checkin.get
				
				long millisCheckin = checkin.getTime();
				long millisCheckout = checkout.getTime();
				long total = millisCheckout - millisCheckin;
				
				System.out.println(millisCheckin);
				System.out.println(millisCheckout);
				System.out.println(total);
				
				int day = (int)(total / 1000 / 60 / 60 / 24);
				
				System.out.println(day);
				
//				for (int i = 1; i <= numberOfColumns; i++)
//					System.out.print(rs.getString(i) + " ");
//				int odid = rs.getInt(1);
//				int roomid = rs.getInt(2);
//				int ordid = rs.getInt(3);
//				int states = rs.getInt(4);
//				Date checkin = rs.getDate(5);
//				Date checkout = rs.getDate(6);
//				int rtid = rs.getInt(7);
//				String rtname = rs.getString(8);
//				String evaluate = rs.getString(9);
//				System.out.println("irdid = " + odid);
//				System.out.println("roomid = " + roomid);
//				System.out.println("ordid = " + ordid);
//				System.out.println("states = " + states);
//				System.out.println("checkin = " + checkin);
//				System.out.println("checkout = " + checkout);
//				System.out.println("rtid = " + rtid);
//				System.out.println("rtname = " + rtname);
//				System.out.println("evaluate = " + evaluate);		
//				System.out.println();
			}
			System.out.println("執行完成");

			
//			System.out.println("test1");
//			if(rs.next())
//			{
//			Date checkin = rs.getDate(1);
//			System.out.println(checkin);
			
//			int odid = rs.getInt(1);
//			int roomid = rs.getInt(2);
//			int ordid = rs.getInt(3);
//			int states = rs.getInt(4);
//			Date checkin = rs.getDate(5);
//			Date checkout = rs.getDate(6);
//			int rtid = rs.getInt(7);
//			String rtname = rs.getString(8);
//			String evaluate = rs.getString(9);
//			System.out.println("irdid = " + odid);
//			System.out.println("roomid = " + roomid);
//			System.out.println("ordid = " + ordid);
//			System.out.println("states = " + states);
//			System.out.println("checkin = " + checkin);
//			System.out.println("checkout = " + checkout);
//			System.out.println("rtid = " + rtid);
//			System.out.println("rtname = " + rtname);
//			System.out.println("evaluate = " + evaluate);
//			}
//			System.out.println("test2");
		
		} catch(ClassNotFoundException ce){
				ce.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null)
			{
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(stmt != null)
			{
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			if(con != null)
			{
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();	
				}
			}
		}
	}
}

package com.android.tool;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class ImageUtil {

	private static final String FIND_IMAGE = "SELECT ? FROM ? where ? = ?";    // 找圖片
	
	//連線池
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CA105G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public static byte[] shrink(byte[] srcImageData, int scaleSize) {
		
		int sampleSize = 1;   //縮小比例, 4代表除以4
		int imageWidth = 0; 
		int imageHeight = 0;
		
		ByteArrayInputStream bais = new ByteArrayInputStream(srcImageData);
		
		// 如果imageSize為0、負數(代表錯誤)或1(1代表與原圖一樣大小)，就直接回傳原圖資料
		if(scaleSize <= 1) {
			return srcImageData;
		}
		
		try {
			BufferedImage srcBufferedImage = ImageIO.read(bais);
			// 如果無法識別圖檔格式(TYPE_CUSTOM)就回傳TYPE_INT_ARGB；否則回傳既有格式
			
			int type = srcBufferedImage.getType() == BufferedImage.TYPE_CUSTOM 
					? BufferedImage.TYPE_INT_RGB : srcBufferedImage.getType();
			imageWidth = srcBufferedImage.getWidth();       // 取得照片寬度
			imageHeight = srcBufferedImage.getHeight();     // 取得照片高度
			if(imageWidth == 0 || imageHeight == 0) {
				return srcImageData;
			}
			// 只要圖檔較長的一邊超過指定長度(desireSize)，就計算欲縮小的比例
			// 並將圖檔寬高都除以欲縮小比例
			int longer = Math.max(imageWidth, imageHeight); // 回傳最大值(寬或高)
			if(longer > scaleSize) {                        // 
				sampleSize = longer / scaleSize;            // 取得縮圖比例
				imageWidth = srcBufferedImage.getWidth() / sampleSize;   //取得寬度 / 比例
				imageHeight = srcBufferedImage.getHeight() / sampleSize; //取得高度 / 比例
			}
			BufferedImage scaleBufferedImage = new BufferedImage(imageWidth, imageHeight, type);
			Graphics graphics = scaleBufferedImage.createGraphics();
			graphics.drawImage(srcBufferedImage, 0, 0, imageWidth, imageHeight, null);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(scaleBufferedImage, "jpg", baos);
			
			return baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			return srcImageData;
		}
	}	
	
	public static byte[] getImage(String columnName, String tableName, String columnId, String id) {
		
		final String FIND_IMAGE = "SELECT " + columnName + " FROM " + tableName + " where " + columnId + " = ?";    // 找圖片
		
		byte[] image = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(ConData.URL, ConData.USER, ConData.PASSWORD);
			pstmt = con.prepareStatement(FIND_IMAGE);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				image = rs.getBytes(1);
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
		return image;      // 沒有圖片為null
	}
	
}

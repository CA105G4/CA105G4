package com.activity.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ActivityJNDIDAO implements ActivityDAO_interface{
	private static DataSource ds =null;	
	
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/CA105G4DB");
		} catch (NamingException e) {
			e.printStackTrace();
		} 
	}
	
	private static final String INSERT_STMT ="INSERT INTO Activity (actID,actName,actStart,actEnd) "
																					+ "VALUES('A'||LPAD(to_char(act_seq.nextval),4,'0'),?,?,?)";
	private static final String UPDATE ="UPDATE Activity SET actName=?, actStart=?, actEnd=? WHERE actID=?";
	private static final String DELETE = "DELETE FROM Activity WHERE actID=?"; 
	private static final String FIND_ONE_STMT ="SELECT * FROM Activity WHERE actID=?";
	private static final String FIND_ALL_STMT ="SELECT * FROM Avtivity ORDER BY actID";
	
	
	@Override
	public void insert(ActivityVO activityVO) {
		Connection con =null;
		PreparedStatement pstmt = null;
		
		try {
			con=ds.getConnection();
			pstmt =con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, activityVO.getActName());
			pstmt.setDate(2, activityVO.getActStart());
			pstmt.setDate(3, activityVO.getActEnd());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(ActivityVO activityVO) {
		
	}

	@Override
	public ActivityVO findByPK(String actID) {
		return null;
	}

	@Override
	public ActivityVO findByName(String actName) {
		return null;
	}

	@Override
	public List<ActivityVO> getAll() {
		return null;
	}
	
	public static void main(String[] args) {
		ActivityJNDIDAO dao = new ActivityJNDIDAO();
		
		ActivityVO vo =new ActivityVO();
		Date d1 =Date.valueOf("2019-02-02");Date d2 =Date.valueOf("2019-03-02");
		
		vo.setActName("測試用促銷活動");vo.setActStart(d1);vo.setActEnd(d2);
		
		dao.insert(vo);
		System.out.println("測試新增成功");
	}
	
}

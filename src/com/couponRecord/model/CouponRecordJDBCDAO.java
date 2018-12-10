package com.couponRecord.model;

import java.util.List;

public class CouponRecordJDBCDAO implements CouponRecord_interface{

	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "Test_Project"; 
	private static final String PASSWORD = "123456"; 
	
    private static final String INSERT_SQL = "INSERT INTO coupon VALUES ('C'||LPAD(to_char(cpn_seq.NEXTVAL), 4, '0'), ?, ?, ?, ?)";
    private static final String UPDATE_SQL = "UPDATE coupon set appQuantity = ? where cpnID = ?";
    private static final String GET_ALL_SQL = "SELECT * from coupon";
    private static final String GET_ONE_SQL = "SELECT * from coupon where cpnID = ?";
    
	
    static {
    	try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}    	
    }

	@Override
	public void insert(CouponRecordVO couponRecordVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(CouponRecordVO couponRecordVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CouponRecordVO> findByPK(String cpnID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) {
		
	}
}

package com.room.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomJDBCDAO implements RoomDAO_interface{
	
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "CA105G4";
	private static final String PASSWORD = "123456";
	
	private static final String INSERT_SQL = 
			 "Insert into Room (roomID, roomTypeID, braID, roomNo, roomState, memName)" 
			+ "values ('R'||LPAD(to_char(room_seq.nextVal),5,'0'), ?, ?, ?, ? ,?)";
	private static final String UPDATE_SQL = "Update Room set roomState = ? where roomID = ?";
	private static final String GET_ONE_SQL = "Select roomID, roomTypeID, braID, roomNo, roomState, memName from Room where roomID = ?";
	private static final String GET_ALL_SQL = "Select * from Room";
	
	static {
		try {
			Class.forName(DRIVER);
		} catch(ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	
	
	@Override
	public void insert(RoomVO roomVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_SQL);
			
			pstmt.setString(1, roomVO.getRoomTypeID());
			pstmt.setString(2, roomVO.getBraID());
			pstmt.setInt(3, roomVO.getRoomNo());
			pstmt.setInt(4, roomVO.getRoomState());
			pstmt.setString(5, roomVO.getMemName());
			
			pstmt.executeUpdate();
			
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
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
	public void update(RoomVO roomVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_SQL);
			
			pstmt.setInt(1, roomVO.getRoomState());
			pstmt.setString(2, roomVO.getRoomID());
			
			pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
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
	public RoomVO findByPrimaryKey(String roomID) {
		RoomVO roomVO= null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_SQL);
			
			pstmt.setString(1, roomID);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				roomVO = new RoomVO();
				roomVO.setRoomID(rs.getString("roomID"));
				roomVO.setRoomTypeID(rs.getString("roomTypeID"));
				roomVO.setBraID(rs.getString("braID"));
				roomVO.setRoomNo(rs.getInt("roomNo"));
				roomVO.setRoomState(rs.getInt("roomState"));
				roomVO.setMemName(rs.getString("memName"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
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
		return roomVO;
	}

	@Override
	public List<RoomVO> getAll() {
		List<RoomVO> list = new ArrayList<RoomVO>();
		RoomVO roomVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_SQL);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				roomVO = new RoomVO();
				roomVO.setRoomID(rs.getString("roomID"));
				roomVO.setRoomTypeID(rs.getString("roomTypeID"));
				roomVO.setBraID(rs.getString("braID"));
				roomVO.setRoomNo(rs.getInt("roomNo"));
				roomVO.setRoomState(rs.getInt("roomState"));
				roomVO.setMemName(rs.getString("memName"));
				
				list.add(roomVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
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
	
	public static void main(String args[]) {
		RoomJDBCDAO dao = new RoomJDBCDAO();
		//新增測試
//		RoomVO roomVO1 = new RoomVO();
//		roomVO1.setRoomTypeID("RT10");
//		roomVO1.setBraID("B02");
//		roomVO1.setRoomNo(150);
//		roomVO1.setRoomState(2);
//		roomVO1.setMemName("Ivan");
//		dao.insert(roomVO1);
//		System.out.println("Successfully Insert!");
		
		//修改
//		RoomVO roomVO2 = new RoomVO();
//		roomVO2.setRoomID("R00043");
//		roomVO2.setRoomState(1);
//		dao.update(roomVO2);
//		System.out.println("Successfully Update!");
		
		//查詢單筆
//		RoomVO roomVO3 = dao.findByPrimaryKey("R00030");
//		System.out.println(roomVO3.getRoomTypeID());
//		System.out.println(roomVO3.getBraID());
//		System.out.println(roomVO3.getRoomNo());
//		System.out.println(roomVO3.getRoomState());
//		System.out.println(roomVO3.getMemName());
//		System.out.println("Successfully Search!");
		
		//查詢全部
		List<RoomVO> rl = dao.getAll();
		for(RoomVO room : rl) {
			System.out.println(room.getRoomID());
			System.out.println(room.getRoomTypeID());
			System.out.println(room.getBraID());
			System.out.println(room.getRoomNo());
			System.out.println(room.getRoomState());
			System.out.println(room.getMemName());
			System.out.println("====================");
		}
		System.out.println("Successfully Search!");
	}	
}

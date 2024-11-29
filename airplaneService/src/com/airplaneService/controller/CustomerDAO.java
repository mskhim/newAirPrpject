package com.airplaneService.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.airplaneService.model.CustomerVO;

public class CustomerDAO
{
	// String no, String name, String birth, String phone, Date regist, int right,  String id, String pw, int count
	public final String CUSTOMER_INSERT = "INSERT INTO CUSTOMER(NO, NAME, BIRTH, PHONE, REGIST, ID, PW) VALUES(?, ?, ?, ?, SYSDATE, ?, ?)";
	public final String CUSTOMER_SELECT = "SELECT * FROM CUSTOMER ORDER BY NO";
	public final String CUSTOMER_CHECK_SELECT = "SELECT * FROM CUSTOMER WHERE ID=? AND PHONE=?";
    // public final String LANDPRICE_UPDATE = "UPDATE LANDPRICE SET GPSLATI = ?, GPSLONG = ?, NODEID = ?, NODENM = ? WHERE NODENO = ?";
	public final String CUSTOMER_UPDATE = "UPDATE CUSTOMER SET NAME=?, BIRTH=?, PHONE=?, REGIST=SYSDATE WHERE ID=?";
    public final String CUSTOMER_DELETE = "DELETE FROM CUSTOMER WHERE ID=? AND PHONE=?";
    // public final String LANDPRICE_SORT = "SELECT * FROM LANDPRICE ORDER BY NODENM";
    public final String CUSTOMER_COUNT_SELECT = "SELECT C_COUNT FROM CUSTOMER";
	
    public boolean insertDB(CustomerVO abcvo) throws SQLException	// 1번 메뉴 <만 14세 이상 가입하기>
	{
		//Conection
		boolean successFlag = false; 
		Connection con = null;
		PreparedStatement pstmt = null;

		// 1 Load, 2. connect
		con = DBUtility.dbCon();

		pstmt = con.prepareStatement(CUSTOMER_INSERT);
		pstmt.setString(1, abcvo.getNo());
		pstmt.setString(2, abcvo.getName());
		pstmt.setString(3, abcvo.getBirth());
		pstmt.setString(4, abcvo.getPhone());
		// pstmt.setDate(5, abcvo.getRegist());	//
		// pstmt.setInt(5, abcvo.getRight());	//
		pstmt.setString(5, abcvo.getId());
		pstmt.setString(6, abcvo.getPw());
		// pstmt.setInt(9, abcvo.getCcount());	//
		
		int result = pstmt.executeUpdate();
		
		DBUtility.dbClose(con, pstmt);
		successFlag = (result != 0) ? true : false;
		return successFlag; 
	}
    
    public ArrayList <CustomerVO> selectCheckDB(String idValue, String phoneValue)	// 3번 메뉴 <내 가입정보 확인하기>
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<CustomerVO> AirBookingCustomerVOList = new ArrayList<CustomerVO>();

		try
		{
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(CUSTOMER_CHECK_SELECT);
			pstmt.setString(1, idValue);
			pstmt.setString(2, phoneValue);
			rs = pstmt.executeQuery();

			if (rs.next())
			{
				do
				{
//					pstmt.setString(1, abcvo.getNo());
//					pstmt.setString(2, abcvo.getName());
//					pstmt.setString(3, abcvo.getBirth());
//					pstmt.setString(4, abcvo.getPhone());
//					//pstmt.setDate(5, abcvo.getRegist());	//
//					pstmt.setInt(5, abcvo.getRight());	//
//					pstmt.setString(6, abcvo.getId());
//					pstmt.setString(7, abcvo.getPw());
//					// pstmt.setInt(9, abcvo.getCcount());	//
					
					String no = rs.getString("NO");
					String name = rs.getString("NAME");
					String birth = rs.getString("BIRTH");
					// String phone = rs.getString("PHONE");
					Date regist = rs.getDate("REGIST");
					/////////
					// int right = rs.getInt("RIGHT");
					////////
					// String id = rs.getString("ID");
					String pw = rs.getString("PW");
					// int cCount = rs.getInt("C_COUNT");

					CustomerVO abcvo = new CustomerVO();
					
					abcvo.setNo(no);
					abcvo.setName(name);
					abcvo.setBirth(birth);
					abcvo.setPhone(phoneValue);
					abcvo.setRegist(regist);
					// abcvo.setRight(right);
					abcvo.setId(idValue);
					abcvo.setPw(pw);

					AirBookingCustomerVOList.add(abcvo);
				} while (rs.next());
			} else
			{
				AirBookingCustomerVOList = null;
			}
		} catch (SQLException e)
		{
			System.out.println(e.toString());
		} finally
		{
			DBUtility.dbClose(con, pstmt, rs);
		}
		return AirBookingCustomerVOList;
	}
    
    // 아마 이건 관리자모드에서 사용 가능할 것 같고, 사용자는 3번메뉴리스트를 따로 만들어야 할듯. CHECK SELECT
	public ArrayList <CustomerVO> selectDB()	// 3번 메뉴 <내 가입정보 확인하기>
	{
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList <CustomerVO> AirBookingCustomerVOList = new ArrayList <CustomerVO>();

		con = DBUtility.dbCon();
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(CUSTOMER_SELECT);

			if(rs.next())
			{
				do
				{					
					String no = rs.getString("NO");
					String name = rs.getString("NAME");
					String birth = rs.getString("BIRTH");
					String phone = rs.getString("PHONE");
					Date regist = rs.getDate("REGIST");
					// int right = rs.getInt("RIGHT");
					String id = rs.getString("ID");
					String pw = rs.getString("PW");
					// int cCount = rs.getInt("C_COUNT");

					CustomerVO abcvo = new CustomerVO(no, name, birth, phone, regist, id, pw);
					
					AirBookingCustomerVOList.add(abcvo);
				} while (rs.next());
			} else
			{
				AirBookingCustomerVOList = null; 
			}
		} catch (SQLException e)
		{
			System.out.println(e.toString());
		} finally
		{
			DBUtility.dbClose(con, stmt, rs);
		}
		return AirBookingCustomerVOList;
	}

	public boolean updateDB(CustomerVO abcvo) throws SQLException	// 4번 메뉴 <내 가입정보 수정하기>
	{
		boolean successFlag = false; 
		
		Connection con = null;
		PreparedStatement pstmt = null;

		con = DBUtility.dbCon();

		// NAME=?, BIRTH=?, PHONE=?, REGIST=SYSDATE, ID=?, PW=?, COUNT=?
		pstmt = con.prepareStatement(CUSTOMER_UPDATE);
		pstmt.setString(1, abcvo.getName());
		pstmt.setString(2, abcvo.getBirth());
		pstmt.setString(3, abcvo.getPhone());
		pstmt.setString(4, abcvo.getId());	//
		
		int result = pstmt.executeUpdate();
		
		DBUtility.dbClose(con, pstmt);
		
		successFlag = (result != 0) ? true : false;
		
		return successFlag;
	}

	public boolean deleteDB(CustomerVO abcvo)	// 5번 메뉴 <회원 탈퇴하기>
	{
		boolean successFlag =false; 
		Connection con = null;
		PreparedStatement pstmt = null;

		try
		{
			con = DBUtility.dbCon();
			// ID, PHONE을 모두 입력받음.
			pstmt = con.prepareStatement(CUSTOMER_DELETE);
			pstmt.setString(1, abcvo.getId());
			pstmt.setString(2, abcvo.getPhone());
			
			int result = pstmt.executeUpdate();
			successFlag = (result != 0) ? true : false ;
			
		} catch (SQLException e)
		{
			System.out.println(e.toString());
		} finally
		{
			DBUtility.dbClose(con, pstmt);
		}
		return successFlag; 
	}

	// public ArrayList <AirBookingCustomerVO> selectCountDB()	// 6번 메뉴 <예약 현황(Count) 확인하기>
	public String selectCountDB()
	{
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		ArrayList <CustomerVO> AirBookingCustomerVOList = new ArrayList <CustomerVO>();
		
		CustomerVO abcvo = new CustomerVO();

		con = DBUtility.dbCon();
		
		try
		{
			stmt = con.createStatement();
			rs = stmt.executeQuery(CUSTOMER_COUNT_SELECT);

			if(rs.next())
			{
				do
				{					
					int count = rs.getInt("C_COUNT");

					// AirBookingCustomerVO abcvo = new AirBookingCustomerVO(count);
					abcvo = new CustomerVO(count);
					abcvo.setCcount(count);
					
					// AirBookingCustomerVOList.add(abcvo);
				} while (rs.next());
			} else
			{
				AirBookingCustomerVOList = null; 
			}
		} catch (SQLException e)
		{
			System.out.println(e.toString());
		} finally
		{
			DBUtility.dbClose(con, stmt, rs);
		}
		return abcvo.countPrint();
	}
}
package com.airplaneService.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.airplaneService.model.FlightVO;

public class FlightDAO {
	public static String FLIGHT_SELECT = "SELECT * FROM FLIGHT";
	public static String FLIGHT_SELECT_BY_NO = "SELECT * FROM FLIGHT WHERE NO = TO_CHAR(?,'FM00000')";//추가
	public static String FLIGHT_INSERT = "INSERT INTO FLIGHT(NO, PLANE_NO, ARRIVAL_COUNTRY_NO, DEPARTURE_HOUR) VALUES(TO_CHAR(?,'FM00000'),TO_CHAR(?,'FM00000'),?,?)";
	public static String FLIGHT_UPDATE = "UPDATE FLIGHT SET PLANE_NO = TO_CHAR(?,'FM00000'), ARRIVAL_COUNTRY_NO = TO_CHAR(?,'FM00000'), DEPARTURE_HOUR = ? WHERE NO = TO_CHAR(?,'FM00000')";
	public static String FLIGHT_DELETE = "DELETE FROM FLIGHT WHERE NO = TO_CHAR(?,'FM00000')";

	public ArrayList<FlightVO> selectDB() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<FlightVO> flightList = new ArrayList<FlightVO>();

		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(FLIGHT_SELECT);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				do {
					String no = rs.getString("NO");
					String planeNo = rs.getString("PLANE_NO");
					String departureCountryNo = rs.getString("DEPARTURE_COUNTRY_NO");
					String arrivalCountryNo = rs.getString("ARRIVAL_COUNTRY_NO");
					int price = rs.getInt("PRICE");
					Timestamp departureHour = rs.getTimestamp("DEPARTURE_HOUR");
					Timestamp arrivalHour = rs.getTimestamp("ARRIVAL_HOUR");
					FlightVO svo = new FlightVO(no, planeNo, departureCountryNo, arrivalCountryNo, price, departureHour,
							arrivalHour);
					flightList.add(svo);
				} while (rs.next());
			} else {
				flightList = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtility.dbClose(con, pstmt, rs);
		}
		return flightList;
	}
	
	public ArrayList<FlightVO> searchDB() {
		return null;
	}

	public boolean insertDB(FlightVO fvo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		boolean successFlag = false;

		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(FLIGHT_INSERT);
			pstmt.setString(1, fvo.getNo());
			pstmt.setString(2, fvo.getPlaneNo());
			pstmt.setString(3, fvo.getArrivalCountryNo());
			pstmt.setTimestamp(4, fvo.getDepartureHour());

			int count = pstmt.executeUpdate();

			successFlag = (count != 0) ? true : false;

		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt);
		}
		return successFlag;

	}

	public boolean updateDB(FlightVO fvo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		boolean successFlag = false;

		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(FLIGHT_UPDATE);

			pstmt.setString(1, fvo.getPlaneNo());
			pstmt.setString(2, fvo.getArrivalCountryNo());
			pstmt.setTimestamp(3, fvo.getDepartureHour());
			pstmt.setString(4, fvo.getNo());

			int count = pstmt.executeUpdate();

			successFlag = (count != 0) ? true : false;

		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt);
		}
		return successFlag;

	}

	public boolean deleteDB(FlightVO fvo) {
		Connection con = null;
		PreparedStatement pstmt = null;

		boolean successFlag = false;

		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(FLIGHT_DELETE);

			pstmt.setString(1, fvo.getNo());

			int count = pstmt.executeUpdate();

			successFlag = (count != 0) ? true : false;

		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			DBUtility.dbClose(con, pstmt);
		}
		return successFlag;
	}

	//추가
	public FlightVO selectByNoDB(FlightVO fvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(FLIGHT_SELECT_BY_NO);
			pstmt.setString(1, String.valueOf(fvo.getNo()));
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					String no = rs.getString("NO");
					String planeNo = rs.getString("PLANE_NO");
					String departureCountryNo = rs.getString("DEPARTURE_COUNTRY_NO");
					String arrivalCountryNo = rs.getString("ARRIVAL_COUNTRY_NO");
					int price = rs.getInt("PRICE");
					Timestamp departureHour = rs.getTimestamp("DEPARTURE_HOUR");
					Timestamp arrivalHour = rs.getTimestamp("ARRIVAL_HOUR");
					fvo = new FlightVO(no, planeNo, departureCountryNo, arrivalCountryNo, price, departureHour,
							arrivalHour);
				} while (rs.next());
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtility.dbClose(con, pstmt, rs);
		}
		return fvo;
	}

}

package com.airplaneService.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.airplaneService.model.FlightJoinVO;
import com.airplaneService.model.FlightVO;

public class FlightDAO {
	public static String FLIGHT_SELECT_BY_NO = "SELECT F.NO, F.PLANE_NO, F.DEPARTURE_COUNTRY_NO, C.NAME,F.PRICE, F.DEPARTURE_HOUR,F.ARRIVAL_HOUR FROM FLIGHT F JOIN COUNTRY C ON C.NO=F.ARRIVAL_COUNTRY_NO WHERE F.NO = TO_CHAR(?,'FM00000')";//추가
	public static String FLIGHT_SELECT = "SELECT F.NO, PLANE_NO, DEPARTURE_COUNTRY_NO, C.NAME,F.PRICE, F.DEPARTURE_HOUR,F.ARRIVAL_HOUR FROM FLIGHT F JOIN COUNTRY C ON C.NO=F.ARRIVAL_COUNTRY_NO";
//	public static String FLIGHT_SELECT_COUNTRY = "SELECT * FROM FLIGHT WHERE ARRIVAL_COUNTRY_NO = TO_CHAR(?,'FM00000')";
	public static String FLIGHT_INSERT = "INSERT INTO FLIGHT(NO, PLANE_NO, ARRIVAL_COUNTRY_NO, DEPARTURE_HOUR) VALUES((SELECT TO_CHAR(NVL(MAX(NO),0)+1,'FM00000') FROM FLIGHT),TO_CHAR(?,'FM00000'),TO_CHAR(?,'FM00000'),?)";
	public static String FLIGHT_UPDATE = "UPDATE FLIGHT SET PLANE_NO = TO_CHAR(?,'FM00000'), ARRIVAL_COUNTRY_NO = TO_CHAR(?,'FM00000'), DEPARTURE_HOUR = ? WHERE NO = TO_CHAR(?,'FM00000');";
	public static String FLIGHT_DELETE = "DELETE FROM FLIGHT WHERE NO = TO_CHAR(?,'FM00000')";
	public static String FLIGHT_SELECT_JOIN_COUNTRY = "SELECT F.NO as no, PLANE_NO, DEPARTURE_COUNTRY_NO, ARRIVAL_COUNTRY_NO, PRICE, C.NAME as name, C.DISTANCE as distance, DEPARTURE_HOUR, ARRIVAL_HOUR FROM FLIGHT F INNER JOIN COUNTRY C ON C.NO = F.ARRIVAL_COUNTRY_NO WHERE F.ARRIVAL_COUNTRY_NO = TO_CHAR(?,'FM00000')";
	
	
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
					int price = rs.getInt("PRICE");
					Timestamp departureHour = rs.getTimestamp("DEPARTURE_HOUR");
					Timestamp arrivalHour = rs.getTimestamp("ARRIVAL_HOUR");
					String arvCountry = rs.getString("NAME");
					FlightVO fvo = new FlightVO(no, planeNo, price, departureHour, arrivalHour, arvCountry, arvCountry);
					flightList.add(fvo);
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
			pstmt.setString(1, fvo.getPlaneNo());
			pstmt.setString(2, fvo.getArrivalCountryNo());
			pstmt.setTimestamp(3, fvo.getDepartureHour());
			int count = pstmt.executeUpdate();

			successFlag = (count != 0) ? true : false;

		} catch (SQLException e) {
			e.printStackTrace();
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
					int price = rs.getInt("PRICE");
					Timestamp departureHour = rs.getTimestamp("DEPARTURE_HOUR");
					Timestamp arrivalHour = rs.getTimestamp("ARRIVAL_HOUR");
					String arvCountry = rs.getString("NAME");
					fvo = new FlightVO(no, planeNo, price, departureHour, arrivalHour, arvCountry, arvCountry);
				} while (rs.next());
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtility.dbClose(con, pstmt, rs);
		}
		return fvo;
	}

	
	public ArrayList<FlightJoinVO> selectCountryDB(FlightJoinVO fjvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<FlightJoinVO> flightList = new ArrayList<FlightJoinVO>();
		try {
			con = DBUtility.dbCon();
			pstmt = con.prepareStatement(FLIGHT_SELECT_JOIN_COUNTRY);
			pstmt.setString(1, fjvo.getArrivalCountryNo());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					String no = rs.getString("NO");
					String planeNo = rs.getString("PLANE_NO");
					String departureCountryNo = rs.getString("DEPARTURE_COUNTRY_NO");
					String arrivalCountryNo = rs.getString("ARRIVAL_COUNTRY_NO");
					int price = rs.getInt("PRICE");
					String name = rs.getString("NAME");
					int distance = rs.getInt("DISTANCE");
					Timestamp departureHour = rs.getTimestamp("DEPARTURE_HOUR");
					Timestamp arrivalHour = rs.getTimestamp("ARRIVAL_HOUR");

					FlightJoinVO fjVo = new FlightJoinVO(no, planeNo, departureCountryNo, arrivalCountryNo, price, departureHour, arrivalHour, distance, name);

					flightList.add(fjVo);
				} while (rs.next());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtility.dbClose(con, pstmt, rs);
		}
		return flightList;
	}
}

package com.airplaneService.model;

import java.sql.Timestamp;

public class FlightJoinVO {
	private String no;
	private String planeNo;
	private String departureCountryNo;
	private String arrivalCountryNo;
	private int price;
	private String name;
	private int distance;
	private Timestamp departureHour;
	private Timestamp arrivalHour;

	public FlightJoinVO() {
		super();
	}

	public FlightJoinVO(String no, String planeNo, String departureCountryNo, String arrivalCountryNo, int price,
			Timestamp departureHour, Timestamp arrivalHour, int distance, String name) {
		super();
		this.no = no;
		this.planeNo = planeNo;
		this.departureCountryNo = departureCountryNo;
		this.arrivalCountryNo = arrivalCountryNo;
		this.price = price;
		this.departureHour = departureHour;
		this.arrivalHour = arrivalHour;
		this.distance = distance;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getPlaneNo() {
		return planeNo;
	}

	public void setPlaneNo(String planeNo) {
		this.planeNo = planeNo;
	}

	public String getDepartureCountryNo() {
		return departureCountryNo;
	}

	public void setDepartureCountryNo(String departureCountryNo) {
		this.departureCountryNo = departureCountryNo;
	}

	public String getArrivalCountryNo() {
		return arrivalCountryNo;
	}

	public void setArrivalCountryNo(String arrivalCountryNo) {
		this.arrivalCountryNo = arrivalCountryNo;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Timestamp getDepartureHour() {
		return departureHour;
	}

	public void setDepartureHour(Timestamp departureHour) {
		this.departureHour = departureHour;
	}

	public Timestamp getArrivalHour() {
		return arrivalHour;
	}

	public void setArrivalHour(Timestamp arrivalHour) {
		this.arrivalHour = arrivalHour;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	// 헤더 메서드
	public static String getHeader() {
	    return String.format(
	        "%-10s | %-10s | %-10s | %-20s | %-10s | %-15s | %-15s",
	        "No", "Plane No", "Price", "Name", "Distance", "Dep. Hour", "Arv. Hour"
	    );
	}

	// toString 메서드
	@Override
	public String toString() {
	    return String.format(
	        "%-10s | %-10s | %-10d | %-20s | %-10s | %-15s | %-15s",
	        no,
	        planeNo,
	        price,
	        name,
	        distance,
	        departureHour,
	        arrivalHour
	    );
	}


}

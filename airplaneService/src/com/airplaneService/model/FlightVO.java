package com.airplaneService.model;

import java.sql.Timestamp;

public class FlightVO {
	private String no;
	private String planeNo;
	private String departureCountryNo;
	private String arrivalCountryNo;
	private int price;
	private Timestamp departureHour;
	private Timestamp arrivalHour;

	public FlightVO(String no, String planeNo, String departureCountryNo, String arrivalCountryNo, int price,
			Timestamp departureHour, Timestamp arrivalHour) {
		super();
		this.no = no;
		this.planeNo = planeNo;
		this.departureCountryNo = departureCountryNo;
		this.arrivalCountryNo = arrivalCountryNo;
		this.price = price;
		this.departureHour = departureHour;
		this.arrivalHour = arrivalHour;
	}

	public FlightVO(String no, String planeNo, String departureCountryNo, String arrivalCountryNo, int price,
			Timestamp departureHour) {
		super();
		this.no = no;
		this.planeNo = planeNo;
		this.departureCountryNo = departureCountryNo;
		this.arrivalCountryNo = arrivalCountryNo;
		this.price = price;
		this.departureHour = departureHour;
	}

	public FlightVO(String no, String planeNo, String arrivalCountryNo, Timestamp departureHour) {
		super();
		this.no = no;
		this.planeNo = planeNo;
		this.arrivalCountryNo = arrivalCountryNo;
		this.departureHour = departureHour;
	}

	public FlightVO() {
		super();
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

	@Override
	public String toString() {
		return "flightVO [no=" + no + ", planeNo=" + planeNo + ", departureCountryNo=" + departureCountryNo
				+ ", arrivalCountryNo=" + arrivalCountryNo + ", price=" + price + ", departureHour=" + departureHour
				+ ", arrivalHour=" + arrivalHour + "]";
	}

}

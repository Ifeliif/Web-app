package model;

import java.sql.Date;

public class Member_Reserve {
	Date reserve_date;
	String line_id;
	String departure_stop_id;
	String arrival_stop_id;
	String start_time;
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	String end_time;
	int fee;
	String bus_id;
	public Date getReserve_date() {
		return reserve_date;
	}
	public void setReserve_date(Date reserve_date) {
		this.reserve_date = reserve_date;
	}
	public String getLine_id() {
		return line_id;
	}
	public void setLine_id(String line_id) {
		this.line_id = line_id;
	}
	public String getDeparture_stop_id() {
		return departure_stop_id;
	}
	public void setDeparture_stop_id(String departure_stop_id) {
		this.departure_stop_id = departure_stop_id;
	}
	public String getArrival_stop_id() {
		return arrival_stop_id;
	}
	public void setArrival_stop_id(String arrival_stop_id) {
		this.arrival_stop_id = arrival_stop_id;
	}
	public int getFee() {
		return fee;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}
	public String getBus_id() {
		return bus_id;
	}
	public void setBus_id(String bus_id) {
		this.bus_id = bus_id;
	}

}

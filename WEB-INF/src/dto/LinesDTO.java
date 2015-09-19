package dto;

//import java.util.Date;
import java.sql.Date;

public class LinesDTO {
	private String line_id;
	private String arrival_stop_id;
	private String departure_stop_id;
	private int fee;
	private String bus_id;
	private Date start_date;
	private Date end_date;
	private String start_time;
	private String end_time;
	public String getLine_id() {
		return line_id;
	}
	public void setLine_id(String line_id) {
		this.line_id = line_id;
	}
	public String getArrival_stop_id() {
		return arrival_stop_id;
	}
	public void setArrival_stop_id(String arrival_stop_id) {
		this.arrival_stop_id = arrival_stop_id;
	}
	public String getDeparture_stop_id() {
		return departure_stop_id;
	}
	public void setDeparture_stop_id(String departure_stop_id) {
		this.departure_stop_id = departure_stop_id;
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
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
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


}
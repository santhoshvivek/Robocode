package com.utd.robocode.dto;

import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
Robot table
*/

@XmlRootElement
public class AccessRights implements java.io.Serializable{
	
	private int ar_id;
	private int ar_robot_id;
	private int ar_domain_id;
	private int ar_role_id;
	private int ar_right_id;
	private Date created_date;
	private Date updated_date;
	
	public int getAr_id() {
		return ar_id;
	}
	public void setAr_id(int ar_id) {
		this.ar_id = ar_id;
	}
	public int getAr_robot_id() {
		return ar_robot_id;
	}
	public void setAr_robot_id(int ar_robot_id) {
		this.ar_robot_id = ar_robot_id;
	}
	public int getAr_domain_id() {
		return ar_domain_id;
	}
	public void setAr_domain_id(int ar_domain_id) {
		this.ar_domain_id = ar_domain_id;
	}
	public int getAr_role_id() {
		return ar_role_id;
	}
	public void setAr_role_id(int ar_role_id) {
		this.ar_role_id = ar_role_id;
	}
	public int getAr_right_id() {
		return ar_right_id;
	}
	public void setAr_right_id(int ar_right_id) {
		this.ar_right_id = ar_right_id;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public Date getUpdated_date() {
		return updated_date;
	}
	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}
	
	

	
	
}




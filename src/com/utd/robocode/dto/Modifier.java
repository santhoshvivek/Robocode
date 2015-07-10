package com.utd.robocode.dto;
import java.util.Date;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

public class Modifier implements java.io.Serializable{
	
	private String user_name;
	private Integer ru_domain_id;
	private Date updated_date;
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public Integer getRu_domain_id() {
		return ru_domain_id;
	}
	public void setRu_domain_id(Integer ru_domain_id) {
		this.ru_domain_id = ru_domain_id;
	}
	public Date getUpdated_date() {
		return updated_date;
	}
	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}
	
}
	
	
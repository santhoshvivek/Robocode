package com.utd.robocode.dto;

import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
Robot table
*/

@XmlRootElement
public class Rights implements java.io.Serializable{
	
	private String right_id;
	private String right_name;
	
	public String getRight_id() {
		return right_id;
	}
	public void setRight_id(String right_id) {
		this.right_id = right_id;
	}
	public String getRight_name() {
		return right_name;
	}
	public void setRight_name(String right_name) {
		this.right_name = right_name;
	}
	
	
	
}




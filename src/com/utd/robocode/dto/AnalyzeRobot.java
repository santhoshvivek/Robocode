package com.utd.robocode.dto;

import java.util.Date;

public class AnalyzeRobot {
	public Integer mr_id;
	public Integer getMr_id() {
		return mr_id;
	}
	public void setMr_id(Integer mr_id) {
		this.mr_id = mr_id;
	}
	public Integer compare_robot_id_1;
	public Integer compare_domain_id_1;
	public Integer compare_robot_id_2;
	public Integer compare_domain_id_2;
	public Float percentage;
	public Date created_date;
	public Date updated_date;
	
	public Integer getCompare_robot_id_1() {
		return compare_robot_id_1;
	}
	public void setCompare_robot_id_1(Integer compare_robot_id_1) {
		this.compare_robot_id_1 = compare_robot_id_1;
	}
	public Integer getCompare_domain_id_1() {
		return compare_domain_id_1;
	}
	public void setCompare_domain_id_1(Integer compare_domain_id_1) {
		this.compare_domain_id_1 = compare_domain_id_1;
	}
	public Integer getCompare_robot_id_2() {
		return compare_robot_id_2;
	}
	public void setCompare_robot_id_2(Integer compare_robot_id_2) {
		this.compare_robot_id_2 = compare_robot_id_2;
	}
	public Integer getCompare_domain_id_2() {
		return compare_domain_id_2;
	}
	public void setCompare_domain_id_2(Integer compare_domain_id_2) {
		this.compare_domain_id_2 = compare_domain_id_2;
	}
	public Float getPercentage() {
		return percentage;
	}
	public void setPercentage(Float percentage) {
		this.percentage = percentage;
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

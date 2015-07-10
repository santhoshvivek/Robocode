package com.utd.robocode.dto;

import java.util.Date;

public class InterDomain {
	private Integer idm_id;
	private Integer idm_domain_id;
	private Integer idm_role_id;
	private Integer idm_other_domain_id;
	private Integer idm_other_domain_role_id;
	private Date created_date;
	private Date updated_date;
	
	public Integer getIdm_id() {
		return idm_id;
	}
	public void setIdm_id(Integer idm_id) {
		this.idm_id = idm_id;
	}
	public Integer getIdm_domain_id() {
		return idm_domain_id;
	}
	public void setIdm_domain_id(Integer idm_domain_id) {
		this.idm_domain_id = idm_domain_id;
	}
	public Integer getIdm_role_id() {
		return idm_role_id;
	}
	public void setIdm_role_id(Integer idm_role_id) {
		this.idm_role_id = idm_role_id;
	}
	public Integer getIdm_other_domain_id() {
		return idm_other_domain_id;
	}
	public void setIdm_other_domain_id(Integer idm_other_domain_id) {
		this.idm_other_domain_id = idm_other_domain_id;
	}
	public Integer getIdm_other_domain_role_id() {
		return idm_other_domain_role_id;
	}
	public void setIdm_other_domain_role_id(Integer idm_other_domain_role_id) {
		this.idm_other_domain_role_id = idm_other_domain_role_id;
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

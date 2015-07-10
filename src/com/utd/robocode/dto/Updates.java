package com.utd.robocode.dto;

import java.util.Date;

public class Updates {
        
        private Integer ru_id;
        private Integer ru_robot_id;
        private Integer ru_user_id;
        private Integer ru_domain_id;
        private Date created_date;
        private Date updated_date;
		
        public Integer getRu_id() {
			return ru_id;
		}
		public void setRu_id(Integer ru_id) {
			this.ru_id = ru_id;
		}
		public Integer getRu_robot_id() {
			return ru_robot_id;
		}
		public void setRu_robot_id(Integer ru_robot_id) {
			this.ru_robot_id = ru_robot_id;
		}
		public Integer getRu_user_id() {
			return ru_user_id;
		}
		public void setRu_user_id(Integer ru_user_id) {
			this.ru_user_id = ru_user_id;
		}
		public Integer getRu_domain_id() {
			return ru_domain_id;
		}
		public void setRu_domain_id(Integer ru_domain_id) {
			this.ru_domain_id = ru_domain_id;
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
package ru.tsniimash.metrix.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "SYSTEM_LOGIN")
public class SystemLogin implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8753274798951980524L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID",nullable=false)
	private long id;

	@ManyToOne
	@JoinColumn(name = "USER_FK",nullable=false)
	private User user;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LOGIN_TIMESTAMP",nullable=false)
	private Date login_timestamp;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getTimestamp() {
		return login_timestamp;
	}

	public void setTimestamp(Date login_timestamp) {
		this.login_timestamp = login_timestamp;
	}	
}
package ru.tsniimash.metrix.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "USER")
public class User implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8753274798951980524L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID",nullable=false)
	private long id;

	@Column(name = "NAME")
	private String name;

	@NaturalId
	@Column(name = "EMAIL",nullable=false)
	private String email;
	
	@Column(name = "PASSWORD",nullable=false)
	private String password;
	
	@Transient
	private String confirmPassword;
	
	@Column(name = "NEWSLETTER")
	private boolean newsletter;
	
	@Column(name = "SEX", nullable = false)
	private String sex;
	
	@Column(name = "COUNTRY")
	private String country;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "REGISTRATION_DATETIME",nullable = false)
	private Date registration_datetime;	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public boolean isNewsletter() {
		return newsletter;
	}

	public void setNewsletter(boolean newsletter) {
		this.newsletter = newsletter;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Date getRegistration_datetime() {
		return registration_datetime;
	}

	public void setRegistration_datetime(Date registration_datetime) {
		this.registration_datetime = registration_datetime;
	}
	
}

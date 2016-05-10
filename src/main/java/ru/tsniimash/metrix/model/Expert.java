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

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "EXPERT")
public class Expert implements Serializable
{
	private static final long serialVersionUID = -2467214736463334420L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID",nullable=false)
	private long id;
	
	@Column(name = "NAME")
	private String name;
	
	@NaturalId
	@Column(name = "EMAIL", nullable = false)
	private String email;
	
	@ManyToOne
	@JoinColumn(name = "USER_FK", nullable = false)
	private User user;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED",nullable=false)
	private Date created;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MODIFIED")
	private Date modified;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public long getId()
	{
		return id;
	}
}

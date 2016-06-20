package ru.tsniimash.metrix.model;

import java.math.BigDecimal;
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
import javax.persistence.Transient;

@Entity
@Table(name = "GRADE")
public class Grade
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID",nullable=false)
	private long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED",nullable=false)
	private Date created;
	
	@Column(name = "GRADE",nullable=false)
	private BigDecimal grade;
	
	@ManyToOne
	@JoinColumn(name = "USER_FK", nullable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "EXPERT_FK", nullable = false)
	private Expert expert;
	
	@ManyToOne
	@JoinColumn(name = "PROJECT_FK", nullable = false)
	private Project project;
	
	@ManyToOne
	@JoinColumn(name = "CRITERION_FK", nullable = false)
	private Criterion criterion;
	
	@Transient
	private String criterionIdString;

	public Date getCreated()
	{
		return created;
	}

	public void setCreated(Date created)
	{
		this.created = created;
	}

	public BigDecimal getGrade()
	{
		return grade;
	}

	public void setGrade(BigDecimal grade)
	{
		this.grade = grade;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public Expert getExpert()
	{
		return expert;
	}

	public void setExpert(Expert expert)
	{
		this.expert = expert;
	}

	public Project getProject()
	{
		return project;
	}

	public void setProject(Project project)
	{
		this.project = project;
	}

	public Criterion getCriterion()
	{
		return criterion;
	}

	public void setCriterion(Criterion criterion)
	{
		this.criterion = criterion;
	}

	public long getId()
	{
		return id;
	}

	public String getCriterionIdString()
	{
		return criterionIdString;
	}

	public void setCriterionIdString(String criterionIdString)
	{
		this.criterionIdString = criterionIdString;
	}
}

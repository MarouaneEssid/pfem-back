package com.talan.pfemanager.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="subject")
public class Subject implements  Serializable{
    private static final long serialVersionUID = 1L;
    @Id 
    @GeneratedValue( strategy=GenerationType.IDENTITY )
    private int id;
    private String title;
    @Column(length = 4095)
    private String description;
    @Column(name = "publication_date")
    private Date publicationDate;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;   
    @ManyToMany
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(
      name = "Technology_subject_associations", 
      joinColumns = @JoinColumn(name = "subject_id"), 
      inverseJoinColumns = @JoinColumn(name = "technology_id"))
    private Set<Technology> technologies;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "user_id")
	private User owner;

    
    public Subject() {}
    public Subject(String title, String description, Date publicationDate,Date startDate,Date endDate,Set<Technology> technologies, User owner ) {
        this.title = title;
        this.description = description;
        this.publicationDate = publicationDate;
        this.startDate  = startDate;
        this.endDate = endDate;
        this.technologies = technologies;
        this.owner = owner;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Date getPublicationDate() {
        return publicationDate;
    }
    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public Set<Technology> getTechnologies() {
        return technologies;
    }
    public void setTechnologies(Set<Technology> technologies) {
        this.technologies = technologies;
    }
    
	public User getUser() {
		return owner;
	}
	public void setUser(User user) {
		this.owner = user;
	}
	@Override
	public String toString() {
		return "Subject [id=" + id + ", title=" + title + ", description=" + description + ", publicationDate="
				+ publicationDate + ", startDate=" + startDate + ", endDate=" + endDate + ", technologies="
				+ technologies + ", user=" + owner + "]";
	}

    
}

package com.talan.pfemanager.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import com.talan.pfemanager.entity.Technology;

public class SubjectDTO implements  Serializable {
	private static final long serialVersionUID = 1L;
		private int id;
	    private String title;
	    private String description;
	    private Date publicationDate;
	    private Date startDate;
	    private Date endDate;
	    private Set<Technology> technologies;
	    private UserDTO owner;
	    
	    public SubjectDTO() {}
	    public SubjectDTO(String title, String description, Date publicationDate,Date startDate,Date endDate,Set<Technology> technologies,UserDTO owner ) {
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
		
		public UserDTO getUserDTO() {
			return owner;
		}
		public void setUserDTO(UserDTO user) {
			this.owner = user;
		}
		@Override
		public String toString() {
			return "SubjectDTO [id=" + id + ", title=" + title + ", description=" + description + ", publicationDate="
					+ publicationDate + ", startDate=" + startDate + ", endDate=" + endDate + ", technologies="
					+ technologies + ", user=" + owner + "]";
		}

	    
}

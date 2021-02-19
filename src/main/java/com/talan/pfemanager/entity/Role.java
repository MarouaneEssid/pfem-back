package com.talan.pfemanager.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="role")
public class Role implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    private int id;
    @Column(unique=true, nullable=false)
    private String name;
    
    public Role(int id, String name) {
		this.id = id;
		this.name = name;
	}

    public Role() {
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}

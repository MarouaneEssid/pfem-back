package com.talan.pfemanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.talan.pfemanager.entity.Application;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {
	List<Application> findApplicationByUserId(int id);
	@Query(
			  value = "SELECT a.* FROM application a, subject s WHERE a.subject_id = s.id AND s.user_id = ?1", 
			  nativeQuery = true)
	List<Application> findByCollab (Integer id);

}

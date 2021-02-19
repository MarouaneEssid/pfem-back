package com.talan.pfemanager.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.talan.pfemanager.entity.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer>{

	List<Subject> findByTechnologiesIdIn(List<Integer> ids);
}

package com.talan.pfemanager.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.talan.pfemanager.dto.SubjectDTO;
import com.talan.pfemanager.entity.Subject;
import com.talan.pfemanager.helper.SubjectHelper;
import com.talan.pfemanager.repository.SubjectRepository;
import com.talan.pfemanager.service.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService {
	@Autowired
	SubjectRepository subjectRepository;

	public String save(SubjectDTO subjectDto) {
		subjectRepository.save(SubjectHelper.subjectDtoToEntity(subjectDto));
		return "{\"success\": \"subject created\"}";
	}

	@Override
	public List<SubjectDTO> findAll() {
		List<Subject> findAll = (List<Subject>) subjectRepository.findAll();
		return SubjectHelper.subjectEntityToDto(findAll);

	}

	@Override
	public SubjectDTO findById(int id) throws NullPointerException {
		Optional<Subject> subject = subjectRepository.findById(id);
		if (subject.isPresent()) {
			return SubjectHelper.subjectEntityToDto(subject.get());
		}
		throw new NullPointerException("subject not found");
	}

	@Override
	public String deleteSubjectById(int id) {
		subjectRepository.deleteById(id);
		return "{\"success\": \"subject deleted\"}";
	}

	@Override
	public String updateSubject(SubjectDTO subjectdto, int id) {
		Optional<Subject> subject = subjectRepository.findById(id);
		if (subject.isPresent()) {
			subjectRepository.save(SubjectHelper.subjectDtoToEntity(subjectdto));
			return "{\"success\": \"subject updated successfully\"}";
		}
		return "{\"erreur\": \"could not find subject " + id + "\"}";
	}

	@Override
	public String deleteAll() {
		subjectRepository.deleteAll();
		return "{\"success\": \"subjects deleted\"}";
	}

	@Override
	public List<Subject> findByTechnologiesIdIn(List<Integer> ids) {
		return subjectRepository.findByTechnologiesIdIn(ids);
	}

}

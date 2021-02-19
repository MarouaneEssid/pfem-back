package com.talan.pfemanager.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.talan.pfemanager.dto.TechnologyDTO;
import com.talan.pfemanager.entity.Technology;
import com.talan.pfemanager.helper.TechnologyHelper;
import com.talan.pfemanager.repository.TechnologyRepository;
import com.talan.pfemanager.service.TechnologyService;

@Service
public class TechnologyServiceImpl implements TechnologyService {

	@Autowired
	TechnologyRepository technologyRepository;

	@Override
	public TechnologyDTO addTechnology(TechnologyDTO technologyDTO) {
		return TechnologyHelper.entityToDto(technologyRepository.save(TechnologyHelper.dtoToEntity(technologyDTO)));
	}

	@Override
	public List<TechnologyDTO> getTechnologies() {
		List<TechnologyDTO> allTechnologyDTO = new ArrayList<TechnologyDTO>();
		technologyRepository.findAll()
				.forEach((technology) -> allTechnologyDTO.add(TechnologyHelper.entityToDto(technology)));
		return allTechnologyDTO;
	}

	@Override
	public TechnologyDTO getTechnology(int id) throws NullPointerException {
		Optional<Technology> technology = technologyRepository.findById(id);
		if (technology.isPresent()) {
			return TechnologyHelper.entityToDto(technology.get());
		}
		throw new NullPointerException("Technology not found");
	}

	@Override
	public void deleteTechnology(int id) {
		technologyRepository.deleteById(id);
	}

	@Override
	public void deleteAllTechnologies() {
		technologyRepository.deleteAll();
	}

}

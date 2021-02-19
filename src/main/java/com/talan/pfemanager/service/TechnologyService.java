package com.talan.pfemanager.service;

import java.util.List;

import com.talan.pfemanager.dto.TechnologyDTO;

public interface TechnologyService {
	TechnologyDTO addTechnology(TechnologyDTO technologyDTO);

	List<TechnologyDTO> getTechnologies();

	TechnologyDTO getTechnology(int id);

	void deleteTechnology (int id);
	
	void deleteAllTechnologies();


}

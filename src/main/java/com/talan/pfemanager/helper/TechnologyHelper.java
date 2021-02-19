package com.talan.pfemanager.helper;

import com.talan.pfemanager.dto.TechnologyDTO;
import com.talan.pfemanager.entity.Technology;

public class TechnologyHelper {

	public static TechnologyDTO entityToDto(Technology technology) {
		TechnologyDTO technologyDTO = new TechnologyDTO();
		technologyDTO.setId(technology.getId());
		technologyDTO.setName(technology.getName());
		return technologyDTO;
	}

	public static Technology dtoToEntity(TechnologyDTO technologyDTO) {
		Technology technology = new Technology();
		technology.setId(technologyDTO.getId());
		technology.setName(technologyDTO.getName());
		return technology;
	}

}

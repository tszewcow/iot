package org.iot.server.service.impl;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.iot.server.document.AutomaticMobileSet;
import org.iot.server.mapper.AutomaticMobileSetMapper;
import org.iot.server.repository.AutomaticMobileSetRepository;
import org.iot.server.service.AutomaticMobileSetService;
import org.iot.server.to.AutomaticMobileSetTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutomaticMobileSetServiceImpl implements AutomaticMobileSetService {

	private final AutomaticMobileSetRepository automaticMobileSetRepository;
	private final AutomaticMobileSetMapper automaticMobileSetMapper;

	@Autowired
	public AutomaticMobileSetServiceImpl(AutomaticMobileSetRepository automaticMobileSetRepository,
			AutomaticMobileSetMapper automaticMobileSetMapper) {
		this.automaticMobileSetRepository = automaticMobileSetRepository;
		this.automaticMobileSetMapper = automaticMobileSetMapper;
	}

	@Override
	public List<AutomaticMobileSetTo> getAllAutomaticMobileSets() {
		List<AutomaticMobileSet> automaticMobileSetTo = automaticMobileSetRepository.findAll();
		return automaticMobileSetMapper.mapDocuments2Tos(automaticMobileSetTo);
	}

	@Override
	public List<AutomaticMobileSetTo> getAutomaticMobileSets(final String building, final int floor) {
		final Predicate<AutomaticMobileSet> buildingFloorFilter = ams -> ams.getBuilding().equals(building) && ams.getFloor() == floor;  
		final List<AutomaticMobileSet> automaticMobileSetTo = automaticMobileSetRepository.findAll().stream().filter(buildingFloorFilter).collect(Collectors.toList());
		return automaticMobileSetMapper.mapDocuments2Tos(automaticMobileSetTo);
	}
	
	@Override
	public AutomaticMobileSetTo addAutomaticMobileSet(AutomaticMobileSetTo automaticMobileSetTo) {
		AutomaticMobileSet automaticMobileSet = automaticMobileSetMapper.mapTo2Document(automaticMobileSetTo);
		automaticMobileSetRepository.save(automaticMobileSet);
		return automaticMobileSetMapper.mapDocument2To(automaticMobileSet);
	}

	@Override
	public void deleteAutomaticMobileSet(String id) {
		automaticMobileSetRepository.delete(id);
	}

	@Override
	public AutomaticMobileSetTo updateAutomaticMobileSet(AutomaticMobileSetTo automaticMobileSetTo) {
		AutomaticMobileSet automaticMobileSet = automaticMobileSetMapper.mapTo2Document(automaticMobileSetTo);
		automaticMobileSetRepository.save(automaticMobileSet);
		return automaticMobileSetMapper.mapDocument2To(automaticMobileSet);
	}
}

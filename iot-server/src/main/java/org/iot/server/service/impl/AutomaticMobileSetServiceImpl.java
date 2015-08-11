package org.iot.server.service.impl;

import java.util.List;

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
	public void addautomaticMobileSet(AutomaticMobileSetTo automaticMobileSetTo) {
		AutomaticMobileSet automaticMobileSet = automaticMobileSetMapper.mapTo2Document(automaticMobileSetTo);
		automaticMobileSetRepository.save(automaticMobileSet);
	}
}

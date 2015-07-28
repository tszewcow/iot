package org.iot.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.iot.server.service.AutomaticMobileSetService;
import org.iot.server.to.AutomaticMobileSetTo;
import org.springframework.stereotype.Service;

@Service
public class AutomaticMobileSetServiceImpl implements AutomaticMobileSetService {
	
	public List<AutomaticMobileSetTo> getAllAutomaticMobileSets() {		
		List<AutomaticMobileSetTo> automaticMobileSets = new ArrayList<>();
		
		AutomaticMobileSetTo automaticMobileSet = createExampleAutomaticMobileSet();
		AutomaticMobileSetTo automaticMobileSet1 = createExampleAutomaticMobileSet1();
		
		automaticMobileSets.add(automaticMobileSet);
		automaticMobileSets.add(automaticMobileSet1);
		
		return automaticMobileSets;
	}
	

	private AutomaticMobileSetTo createExampleAutomaticMobileSet() {
		AutomaticMobileSetTo automaticMobileSet = new AutomaticMobileSetTo();		
		automaticMobileSet.setProject("Hermes");
		automaticMobileSet.setGuardian("Janusz Janusz");
		automaticMobileSet.setBuilding("MT II");
		automaticMobileSet.setFloor("8");
		automaticMobileSet.setRoom("8.132");
		automaticMobileSet.setCoordinates("145x15");
		
		return automaticMobileSet;
	}	
	private AutomaticMobileSetTo createExampleAutomaticMobileSet1() {
		AutomaticMobileSetTo automaticMobileSet1 = new AutomaticMobileSetTo();		
		automaticMobileSet1.setProject("Ikar");
		automaticMobileSet1.setGuardian("Mirek Mirek");
		automaticMobileSet1.setBuilding("MT II");
		automaticMobileSet1.setFloor("11");
		automaticMobileSet1.setRoom("11.12");
		automaticMobileSet1.setCoordinates("154x456");
		
		return automaticMobileSet1;
	}	
}

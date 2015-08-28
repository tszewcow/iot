package org.iot.server.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.iot.server.document.AutomaticMobileSet;
import org.iot.server.to.AutomaticMobileSetTo;
import org.springframework.stereotype.Component;

@Component
public class AutomaticMobileSetMapper {

	public AutomaticMobileSetTo mapDocument2To(AutomaticMobileSet automaticMobileSet) {

		AutomaticMobileSetTo automaticMobileSetTo = new AutomaticMobileSetTo();

		automaticMobileSetTo.setId(automaticMobileSet.getId());
		automaticMobileSetTo.setProject(automaticMobileSet.getProject());
		automaticMobileSetTo.setGuardian(automaticMobileSet.getGuardian());
		automaticMobileSetTo.setBuilding(automaticMobileSet.getBuilding());
		automaticMobileSetTo.setFloor(automaticMobileSet.getFloor());
		automaticMobileSetTo.setRoom(automaticMobileSet.getRoom());
		automaticMobileSetTo.setxAutomaticMobileSet(automaticMobileSet.getxAutomaticMobileSet());
		automaticMobileSetTo.setyAutomaticMobileSet(automaticMobileSet.getyAutomaticMobileSet());
		automaticMobileSetTo.setMacAutomaticMobileSet(automaticMobileSet.getMacAutomaticMobileSet());
		automaticMobileSetTo.setIsActual(automaticMobileSet.getIsActual());
		automaticMobileSetTo.setIpAutomaticMobileSet(automaticMobileSet.getIpAutomaticMobileSet());

		return automaticMobileSetTo;
	}

	public List<AutomaticMobileSetTo> mapDocuments2Tos(List<AutomaticMobileSet> automaticMobileSets) {

		return automaticMobileSets.stream().map(this::mapDocument2To).collect(Collectors.toList());
	}

	public AutomaticMobileSet mapTo2Document(AutomaticMobileSetTo automaticMobileSetTo) {

		AutomaticMobileSet automaticMobileSet = new AutomaticMobileSet();

		automaticMobileSet.setId(automaticMobileSetTo.getId());
		automaticMobileSet.setProject(automaticMobileSetTo.getProject());
		automaticMobileSet.setGuardian(automaticMobileSetTo.getGuardian());
		automaticMobileSet.setBuilding(automaticMobileSetTo.getBuilding());
		automaticMobileSet.setFloor(automaticMobileSetTo.getFloor());
		automaticMobileSet.setRoom(automaticMobileSetTo.getRoom());
		automaticMobileSet.setxAutomaticMobileSet(automaticMobileSetTo.getxAutomaticMobileSet());
		automaticMobileSet.setyAutomaticMobileSet(automaticMobileSetTo.getyAutomaticMobileSet());
		automaticMobileSet.setMacAutomaticMobileSet(automaticMobileSetTo.getMacAutomaticMobileSet());
		automaticMobileSet.setIsActual(automaticMobileSetTo.getIsActual());
		automaticMobileSet.setIpAutomaticMobileSet(automaticMobileSetTo.getIpAutomaticMobileSet());

		return automaticMobileSet;
	}

}

package org.iot.server.service;

import java.util.List;

import org.iot.server.to.AutomaticMobileSetTo;

public interface AutomaticMobileSetService {

	List<AutomaticMobileSetTo> getAllAutomaticMobileSets();

	AutomaticMobileSetTo addAutomaticMobileSet(AutomaticMobileSetTo automaticMobileSetTo);

	void deleteAutomaticMobileSet(String id);

	AutomaticMobileSetTo updateAutomaticMobileSet(AutomaticMobileSetTo request);
}

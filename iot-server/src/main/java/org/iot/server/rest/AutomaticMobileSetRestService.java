package org.iot.server.rest;

import java.util.List;

import org.iot.server.service.AutomaticMobileSetService;
import org.iot.server.to.AutomaticMobileSetTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutomaticMobileSetRestService {

	private final AutomaticMobileSetService automaticMobileSetService;

	@Autowired
	public AutomaticMobileSetRestService(AutomaticMobileSetService automaticMobileSetService) {
		this.automaticMobileSetService = automaticMobileSetService;
	}

	@RequestMapping(value = "/automaticmobilesets", method = RequestMethod.GET)
	public List<AutomaticMobileSetTo> getAllAutomaticMobileSets() {
		return automaticMobileSetService.getAllAutomaticMobileSets();
	}

	@RequestMapping(value = "/automaticmobileset-add", method = RequestMethod.POST)
	public void addAutomaticmobileset(@RequestBody AutomaticMobileSetTo request) {
		automaticMobileSetService.addautomaticMobileSet(request);
	}
}

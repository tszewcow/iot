package org.iot.server.rest;

import java.util.List;

import org.iot.server.service.AutomaticMobileSetService;
import org.iot.server.to.AutomaticMobileSetTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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

	@RequestMapping(value = "/services/automaticmobileset", method = RequestMethod.GET)
	public List<AutomaticMobileSetTo> getAllAutomaticMobileSets() {
		return automaticMobileSetService.getAllAutomaticMobileSets();
	}

	@RequestMapping(value = "/services/automaticmobileset", method = RequestMethod.POST)
	public AutomaticMobileSetTo addAutomaticMobileSet(@RequestBody AutomaticMobileSetTo request) {
		return automaticMobileSetService.addAutomaticMobileSet(request);
	}

	@RequestMapping(value = "/services/automaticmobileset/{id}", method = RequestMethod.DELETE)
	public void deleteAutomaticMobileSet(@PathVariable("id") String id) {
		automaticMobileSetService.deleteAutomaticMobileSet(id);
	}

	@RequestMapping(value = "/services/automaticmobileset", method = RequestMethod.PUT)
	public AutomaticMobileSetTo updateAutomaticMobileSet(@RequestBody AutomaticMobileSetTo request) {
		return automaticMobileSetService.updateAutomaticMobileSet(request);
	}
}

package org.iot.server.rest;

import java.util.List;

import org.iot.server.service.BeaconService;
import org.iot.server.service.LocationFloorService;
import org.iot.server.to.BeaconStatusTo;
import org.iot.server.to.BeaconTo;
import org.iot.server.validator.BeaconValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class BeaconRestService {

    private final BeaconService beaconService;
    private final RequestToBeaconStatusToConverter requestToBeaconStatusToConverter;
    private final LocationFloorService locationFloorService;

    @Autowired
    public BeaconRestService(BeaconService beaconService,
                             RequestToBeaconStatusToConverter requestToBeaconStatusToConverter, LocationFloorService locationFloorService) {
        this.beaconService = beaconService;
        this.requestToBeaconStatusToConverter = requestToBeaconStatusToConverter;
        this.locationFloorService = locationFloorService;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new BeaconValidator(locationFloorService));
    }

    @RequestMapping(value = "/services/beacons", method = RequestMethod.GET)
    public List<BeaconTo> getAllBeacons() {
        return beaconService.getAllBeacons();
    }

    @RequestMapping(value = "/services/beacons/{building}/{floor}", method = RequestMethod.GET)
    public List<BeaconTo> getBeaconsOnFloor(@PathVariable("building") String building, @PathVariable("floor") int floor) {
        return beaconService.getBeacons(building, floor);
    }

    @RequestMapping(value = "/beacon-status", method = RequestMethod.POST)
    public void reportStatus(@RequestBody String request) {
        BeaconStatusTo beaconStatusTo = requestToBeaconStatusToConverter.convert(request);
        beaconService.registerStatus(beaconStatusTo);
    }

    @RequestMapping(value = "/beacons-statuses", method = RequestMethod.GET)
    public List<BeaconStatusTo> getAllBeaconsStatuses() {
        return beaconService.getAllBeaconsStatuses();
    }

    // TODO TK: add validation here
    @RequestMapping(value = "/services/beacon", method = RequestMethod.POST)
    public ResponseEntity<BeaconTo> addBeacon(@Valid @RequestBody BeaconTo request) {
//		return beaconService.addBeacon(request);

        BeaconTo addedBeacon = beaconService.addBeacon(request);

        return new ResponseEntity<>(addedBeacon, HttpStatus.OK);
    }

    @RequestMapping(value = "/services/beacon/{id}", method = RequestMethod.DELETE)
    public void deleteBeacon(@PathVariable("id") String id) {
        beaconService.deleteBeacon(id);
    }

    // TODO TK: add validation here
    @RequestMapping(value = "/services/beacon", method = RequestMethod.PUT)
    public BeaconTo updateBeacon(@RequestBody BeaconTo request) {
        return beaconService.updateBeacon(request);
    }
}

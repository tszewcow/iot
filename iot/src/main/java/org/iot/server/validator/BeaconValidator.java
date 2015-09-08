package org.iot.server.validator;
/**
 * @COPYRIGHT (C) 2015 Schenker AG
 *
 * All rights reserved
 */

import org.iot.server.service.LocationFloorService;
import org.iot.server.to.BeaconTo;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * TODO The class BeaconValidator is supposed to be documented...
 */
public class BeaconValidator implements Validator {

    private LocationFloorService locationFloorService;

    public BeaconValidator(LocationFloorService locationFloorService) {
        this.locationFloorService = locationFloorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return BeaconTo.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        validateEmptyFields(errors);
        BeaconTo beaconTo = (BeaconTo) target;
    }

    private void validateEmptyFields(Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "beacon.name.empty");
        ValidationUtils.rejectIfEmpty(errors, "mac", "beacon.mac.empty");
    }
}

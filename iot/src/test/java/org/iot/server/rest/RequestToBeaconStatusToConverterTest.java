package org.iot.server.rest;

import static org.junit.Assert.assertEquals;

import org.iot.server.to.BeaconStatusTo;
import org.junit.Ignore;
import org.junit.Test;

public class RequestToBeaconStatusToConverterTest {

	private final RequestToBeaconStatusToConverter converter = new RequestToBeaconStatusToConverter();

	@Test
	public void convertShouldConvertRequestStringToBeaconStatusTo() {
		// given
		String requestString = "\"Major=65535"
				+ "&UUDI=7e1c699dd541dd5ee61ea67d9479c28c"
				+ "&MAC=20%3Afa%3Abb%3A01%3A77%3Ae0"
				+ "&macAutomaticMobileSet=12%3A12%3A12%3A12%3A12%3A12"
				+ "&RSSI=-50"
				+ "&Minor=189"
				+ "&MeasuredStrenght=-61\"";
		// when
		BeaconStatusTo beaconStatusTo = converter.convert(requestString);
		// then
		assertEquals(65535, beaconStatusTo.getMajor());
		assertEquals("7e1c699dd541dd5ee61ea67d9479c28c", beaconStatusTo.getUuid());
		assertEquals("12:12:12:12:12:12", beaconStatusTo.getMacAutomaticMobileSet());
		assertEquals("20:fa:bb:01:77:e0", beaconStatusTo.getMac());
		assertEquals(-50, beaconStatusTo.getRssi());
		assertEquals("189", beaconStatusTo.getMinor());
		assertEquals(-61, beaconStatusTo.getMeasuredStrenght());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void convertShouldThrowExeptionWhenRequestStringIsNotValid() {
		// given
		String requestString = "\"Major=65535"
				+ "&UUDI=7e1c699dd541dd5ee61ea67d9479c28c"
				+ "&MeasuredStrenght=-61"
				+ "&Additional=string\"";
		
		converter.convert(requestString);
	}

	// then
	@Test(expected = IllegalArgumentException.class)
	public void convertShouldThrowExceptionWhenRequestStringIsNull() {
		// given
		String requestString = null;
		// when
		converter.convert(requestString);
	}

	// then
	@Test(expected = IllegalArgumentException.class)
	public void convertShouldThrowExceptionWhenRequestStringIsEmpty() {
		// given
		String requestString = "";
		// when
		converter.convert(requestString);
	}
	
}

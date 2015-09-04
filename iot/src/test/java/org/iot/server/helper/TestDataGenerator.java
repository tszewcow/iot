package org.iot.server.helper;

import java.math.BigDecimal;
import java.util.Random;

import org.iot.server.document.AutomaticMobileSet;
import org.iot.server.document.Beacon;
import org.iot.server.document.BeaconStatus;
import org.iot.server.document.User;
import org.iot.server.to.AutomaticMobileSetTo;
import org.iot.server.to.BeaconStatusTo;
import org.iot.server.to.BeaconTo;
import org.iot.server.to.UserTo;

public class TestDataGenerator {
	public static AutomaticMobileSet getAutomaticMobileSet(int number) {
		AutomaticMobileSet automaticMobileSet = new AutomaticMobileSet();

		automaticMobileSet.setId(String.valueOf(number));
		automaticMobileSet.setProject("project".concat(String.valueOf(number)));
		automaticMobileSet.setGuardian("guardian".concat(String.valueOf(number)));
		automaticMobileSet.setBuilding("building".concat(String.valueOf(number)));
		automaticMobileSet.setFloor(number);
		automaticMobileSet.setRoom(number);
		automaticMobileSet.setxAutomaticMobileSet(number);
		automaticMobileSet.setyAutomaticMobileSet(number);
		automaticMobileSet.setMacAutomaticMobileSet("mac".concat(String.valueOf(number)));
		automaticMobileSet.setIsActual(randomBoolean());
		automaticMobileSet.setIpAutomaticMobileSet("ip".concat(String.valueOf(number)));

		return automaticMobileSet;
	}

	public static AutomaticMobileSetTo getAutomaticMobileSetTo(int number) {
		AutomaticMobileSetTo automaticMobileSetTo = new AutomaticMobileSetTo();

		automaticMobileSetTo.setId(String.valueOf(number));
		automaticMobileSetTo.setProject("project".concat(String.valueOf(number)));
		automaticMobileSetTo.setGuardian("guardian".concat(String.valueOf(number)));
		automaticMobileSetTo.setBuilding("building".concat(String.valueOf(number)));
		automaticMobileSetTo.setFloor(number);
		automaticMobileSetTo.setRoom(number);
		automaticMobileSetTo.setxAutomaticMobileSet(number);
		automaticMobileSetTo.setyAutomaticMobileSet(number);
		automaticMobileSetTo.setMacAutomaticMobileSet("mac".concat(String.valueOf(number)));
		automaticMobileSetTo.setIsActual(randomBoolean());
		automaticMobileSetTo.setIpAutomaticMobileSet("ip".concat(String.valueOf(number)));

		return automaticMobileSetTo;
	}

	public static Beacon getBeacon(int number) {
		Beacon beacon = new Beacon();

		beacon.setId(String.valueOf(number));
		beacon.setName("name".concat(String.valueOf(number)));
		beacon.setMac("mac".concat(String.valueOf(number)));
		beacon.setUuidNormal("uuidNormal".concat(String.valueOf(number)));
		beacon.setUuidSecure("uuidSecure".concat(String.valueOf(number)));
		beacon.setUuidService("uuidService".concat(String.valueOf(number)));
		beacon.setBuilding("building".concat(String.valueOf(number)));
		beacon.setFloor(number);
		beacon.setRoom(number);
		beacon.setxBeacon(number);
		beacon.setyBeacon(number);

		return beacon;
	}

	public static BeaconTo getBeaconTo(int number) {
		BeaconTo beaconTo = new BeaconTo();

		beaconTo.setId(String.valueOf(number));
		beaconTo.setName("name".concat(String.valueOf(number)));
		beaconTo.setMac("mac".concat(String.valueOf(number)));
		beaconTo.setUuidNormal("uuidNormal".concat(String.valueOf(number)));
		beaconTo.setUuidSecure("uuidSecure".concat(String.valueOf(number)));
		beaconTo.setUuidService("uuidService".concat(String.valueOf(number)));
		beaconTo.setBuilding("building".concat(String.valueOf(number)));
		beaconTo.setFloor(number);
		beaconTo.setRoom(number);
		beaconTo.setxBeacon(number);
		beaconTo.setyBeacon(number);

		return beaconTo;
	}

	public static BeaconStatus getBeaconStatus(int number) {
		BeaconStatus beaconStatus = new BeaconStatus();

		beaconStatus.setId(String.valueOf(number));
		beaconStatus.setMac("mac".concat(String.valueOf(number)));
		beaconStatus.setMajor(number);
		beaconStatus.setMinor("minor".concat(String.valueOf(number)));
		beaconStatus.setRssi(number);
		beaconStatus.setMeasuredStrenght(number);
		beaconStatus.setUuid("uuid".concat(String.valueOf(number)));
		beaconStatus.setMacAutomaticMobileSet("mac".concat(String.valueOf(number)));
		beaconStatus.setDistance(number);

		return beaconStatus;
	}

	public static BeaconStatusTo getBeaconStatusTo(int number) {
		BeaconStatusTo beaconStatusTo = new BeaconStatusTo();

		beaconStatusTo.setId(String.valueOf(number));
		beaconStatusTo.setMac("mac".concat(String.valueOf(number)));
		beaconStatusTo.setMajor(number);
		beaconStatusTo.setMinor("minor".concat(String.valueOf(number)));
		beaconStatusTo.setRssi(number);
		beaconStatusTo.setMeasuredStrenght(number);
		beaconStatusTo.setUuid("uuid".concat(String.valueOf(number)));
		beaconStatusTo.setMacAutomaticMobileSet("mac".concat(String.valueOf(number)));
		beaconStatusTo.setDistance(number);

		return beaconStatusTo;
	}
	
	public static User getUser(int number) {
		User user = new User();
		
		user.setId(String.valueOf(number));
		user.setFirstName("Jan ".concat(String.valueOf(number)));
		user.setLastName("Kowalski ".concat(String.valueOf(number)));
		user.setEmail("jan.kowalski".concat(String.valueOf(number)).concat("@wp.pl"));
		user.setPassword("qwer1234!!".concat(String.valueOf(number)));
		user.setSalt("qazx".concat(String.valueOf(number)));
		user.setCreatedOn("123".concat(String.valueOf(number)));
		user.setLastLogin("123".concat(String.valueOf(number)));
		user.setUserRole("user".concat(String.valueOf(number)));
		user.setActive(true);
		
		return user;
	}

	public static UserTo getUserTo(int number) {
		UserTo userTo = new UserTo();
		
		userTo.setId(String.valueOf(number));
		userTo.setFirstName("Jan ".concat(String.valueOf(number)));
		userTo.setLastName("Kowalski ".concat(String.valueOf(number)));
		userTo.setEmail("jan.kowalski".concat(String.valueOf(number)).concat("@wp.pl"));
		userTo.setPassword("qwer1234!!".concat(String.valueOf(number)));
		userTo.setSalt("qazx".concat(String.valueOf(number)));
		userTo.setCreatedOn("123".concat(String.valueOf(number)));
		userTo.setLastLogin("123".concat(String.valueOf(number)));
		userTo.setUserRole("user".concat(String.valueOf(number)));
		userTo.setActive(true);
		
		return userTo;
	}
	
	public static String randomString() {
		return String.valueOf(new Random().nextInt(1000000) + 1);
	}

	public static float randomFloat() {
		return roundFloat(new Random().nextFloat() * 100, 2);
	}

	public static double randomDouble() {
		return roundDouble(new Random().nextDouble() * 100, 2);
	}

	public static boolean randomBoolean() {
		return new Random().nextFloat() > 0.5 ? true : false;
	}

	private static float roundFloat(float d, int decimalPlace) {
		return BigDecimal.valueOf(d).setScale(decimalPlace, BigDecimal.ROUND_HALF_UP).floatValue();
	}

	private static double roundDouble(double d, int decimalPlace) {
		return BigDecimal.valueOf(d).setScale(decimalPlace, BigDecimal.ROUND_HALF_UP).floatValue();
	}
}

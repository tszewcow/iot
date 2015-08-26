package org.iot.server.testHelp;

import java.math.BigDecimal;
import java.util.Random;

import org.iot.server.document.AutomaticMobileSet;
import org.iot.server.document.Beacon;
import org.iot.server.document.BeaconStatus;
import org.iot.server.to.AutomaticMobileSetTo;
import org.iot.server.to.BeaconStatusTo;
import org.iot.server.to.BeaconTo;

public class TestDataGenerator 
{
	public static AutomaticMobileSet getAutomaticMobileSet()
	{
		AutomaticMobileSet automaticMobileSet = new AutomaticMobileSet();
		
		automaticMobileSet.setId(randomString());
		automaticMobileSet.setProject(randomString());
		automaticMobileSet.setGuardian(randomString());
		automaticMobileSet.setBuilding(randomString());
		automaticMobileSet.setFloor(new Random().nextInt(11)+1);
		automaticMobileSet.setRoom(randomDouble());
		automaticMobileSet.setxAutomaticMobileSet(randomFloat());
		automaticMobileSet.setyAutomaticMobileSet(randomFloat());
		automaticMobileSet.setMacAutomaticMobileSet(randomString());
		automaticMobileSet.setIsActual(randomBoolean());
		
		return automaticMobileSet;
	}	
	
	public static AutomaticMobileSetTo getAutomaticMobileSetTo()
	{
		AutomaticMobileSetTo automaticMobileSetTo = new AutomaticMobileSetTo();
		
		automaticMobileSetTo.setId(randomString());
		automaticMobileSetTo.setProject(randomString());
		automaticMobileSetTo.setGuardian(randomString());
		automaticMobileSetTo.setBuilding(randomString());
		automaticMobileSetTo.setFloor(new Random().nextInt(11)+1);
		automaticMobileSetTo.setRoom(randomDouble());
		automaticMobileSetTo.setxAutomaticMobileSet(randomFloat());
		automaticMobileSetTo.setyAutomaticMobileSet(randomFloat());
		automaticMobileSetTo.setMacAutomaticMobileSet(randomString());
		automaticMobileSetTo.setIsActual(randomBoolean());
		
		return automaticMobileSetTo;
	}
	
	
	public static Beacon getBeacon()
	{
		Beacon beacon = new Beacon();

		beacon.setId(randomString());
		beacon.setName(randomString());
		beacon.setMac(randomString());
		beacon.setUuidNormal(randomString());
		beacon.setUuidSecure(randomString());
		beacon.setUuidService(randomString());
		beacon.setBuilding(randomString());
		beacon.setFloor(new Random().nextInt(11)+1);
		beacon.setRoom(randomDouble());
		beacon.setxBeacon(randomFloat());
		beacon.setyBeacon(randomFloat());

		return beacon;
	}
	
	public static BeaconTo getBeaconTo()
	{
		BeaconTo beaconTo = new BeaconTo();

		beaconTo.setId(randomString());
		beaconTo.setName(randomString());
		beaconTo.setMac(randomString());
		beaconTo.setUuidNormal(randomString());
		beaconTo.setUuidSecure(randomString());
		beaconTo.setUuidService(randomString());
		beaconTo.setBuilding(randomString());
		beaconTo.setFloor(new Random().nextInt(11)+1);
		beaconTo.setRoom(randomDouble());
		beaconTo.setxBeacon(randomFloat());
		beaconTo.setyBeacon(randomFloat());

		return beaconTo;
	}
	
	public static BeaconStatus getBeaconStatus()
	{
		BeaconStatus beaconStatus = new BeaconStatus();

		Random rand = new Random();
		
		beaconStatus.setMac(randomString());
		beaconStatus.setMajor(rand.nextInt(1000));
		beaconStatus.setMinor(randomString());
		beaconStatus.setRssi(rand.nextInt(1000));
		beaconStatus.setMeasuredStrenght(rand.nextInt(1000));
		beaconStatus.setUuid(randomString());
		beaconStatus.setMacAutomaticMobileSet(randomString());
		beaconStatus.setDistance(randomDouble());

		return beaconStatus;
	}
	
	public static BeaconStatusTo getBeaconStatusTo()
	{
		BeaconStatusTo beaconStatusTo = new BeaconStatusTo();

		Random rand = new Random();
		
		beaconStatusTo.setMac(randomString());
		beaconStatusTo.setMajor(rand.nextInt(1000));
		beaconStatusTo.setMinor(randomString());
		beaconStatusTo.setRssi(rand.nextInt(1000));
		beaconStatusTo.setMeasuredStrenght(rand.nextInt(1000));
		beaconStatusTo.setUuid(randomString());
		beaconStatusTo.setMacAutomaticMobileSet(randomString());
		beaconStatusTo.setDistance(randomDouble());

		return beaconStatusTo;
	}
	
	public static String randomString()
	{
		return String.valueOf(new Random().nextInt(1000000) + 1);
	}
	
	public static float randomFloat()
	{
		return roundFloat(new Random().nextFloat()*100, 2);
	}
	
	public static double randomDouble()
	{
		return roundDouble(new Random().nextDouble()*100, 2);
	}
	
	public static boolean randomBoolean()
	{
		return new Random().nextFloat() > 0.5 ? true : false;
	}
	
	private static float roundFloat(float d, int decimalPlace)
	{
		return BigDecimal.valueOf(d).setScale(decimalPlace,BigDecimal.ROUND_HALF_UP).floatValue();
	}
	
	private static double roundDouble(double d, int decimalPlace)
	{
		return BigDecimal.valueOf(d).setScale(decimalPlace,BigDecimal.ROUND_HALF_UP).floatValue();
	}
}

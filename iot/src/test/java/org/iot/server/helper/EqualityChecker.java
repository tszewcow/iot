package org.iot.server.helper;

import org.iot.server.document.AutomaticMobileSet;
import org.iot.server.document.Beacon;
import org.iot.server.document.BeaconStatus;
import org.iot.server.document.User;
import org.iot.server.to.AutomaticMobileSetTo;
import org.iot.server.to.BeaconStatusTo;
import org.iot.server.to.BeaconTo;
import org.iot.server.to.UserTo;

public class EqualityChecker
{
	public static boolean checkEquality(AutomaticMobileSet ams, AutomaticMobileSetTo mappedAms)
	{
		return 	ams.getId().equals(mappedAms.getId()) &&
				ams.getProject().equals(mappedAms.getProject()) &&
				ams.getGuardian().equals(mappedAms.getGuardian()) &&
				ams.getBuilding().equals(mappedAms.getBuilding()) &&
				ams.getFloor() == mappedAms.getFloor() &&
				ams.getRoom() == mappedAms.getRoom() &&
				ams.getxAutomaticMobileSet() == mappedAms.getxAutomaticMobileSet() &&
				ams.getyAutomaticMobileSet() == mappedAms.getyAutomaticMobileSet() &&
				ams.getMacAutomaticMobileSet().equals(mappedAms.getMacAutomaticMobileSet()) &&
				ams.getIsActual() == mappedAms.getIsActual();
	}
	
	public static boolean checkEquality(AutomaticMobileSet amsOne, AutomaticMobileSet amsTwo)
	{
		return 	amsOne.getId().equals(amsTwo.getId()) &&
				amsOne.getProject().equals(amsTwo.getProject()) &&
				amsOne.getGuardian().equals(amsTwo.getGuardian()) &&
				amsOne.getBuilding().equals(amsTwo.getBuilding()) &&
				amsOne.getFloor() == amsTwo.getFloor() &&
				amsOne.getRoom() == amsTwo.getRoom() &&
				amsOne.getxAutomaticMobileSet() == amsTwo.getxAutomaticMobileSet() &&
				amsOne.getyAutomaticMobileSet() == amsTwo.getyAutomaticMobileSet() &&
				amsOne.getMacAutomaticMobileSet().equals(amsTwo.getMacAutomaticMobileSet()) &&
				amsOne.getIsActual() == amsTwo.getIsActual();
	}
	
	public static boolean checkEquality(AutomaticMobileSetTo mappedAmsOne, AutomaticMobileSetTo mappedAmsTwo)
	{
		return 	mappedAmsOne.getId().equals(mappedAmsTwo.getId()) &&
				mappedAmsOne.getProject().equals(mappedAmsTwo.getProject()) &&
				mappedAmsOne.getGuardian().equals(mappedAmsTwo.getGuardian()) &&
				mappedAmsOne.getBuilding().equals(mappedAmsTwo.getBuilding()) &&
				mappedAmsOne.getFloor() == mappedAmsTwo.getFloor() &&
				mappedAmsOne.getRoom() == mappedAmsTwo.getRoom() &&
				mappedAmsOne.getxAutomaticMobileSet() == mappedAmsTwo.getxAutomaticMobileSet() &&
				mappedAmsOne.getyAutomaticMobileSet() == mappedAmsTwo.getyAutomaticMobileSet() &&
				mappedAmsOne.getMacAutomaticMobileSet().equals(mappedAmsTwo.getMacAutomaticMobileSet()) &&
				mappedAmsOne.getIsActual() == mappedAmsTwo.getIsActual();
	}
	
	
	
	
	
	public static boolean checkEquality(Beacon beacon, BeaconTo mappedBeacon)
	{
		return 	beacon.getId().equals(mappedBeacon.getId()) &&
				beacon.getName().equals(mappedBeacon.getName()) &&
				beacon.getMac().equals(mappedBeacon.getMac()) &&
				beacon.getUuidNormal().equals(mappedBeacon.getUuidNormal()) &&
				beacon.getUuidSecure().equals(mappedBeacon.getUuidSecure()) &&
				beacon.getUuidService().equals(mappedBeacon.getUuidService()) &&
				beacon.getBuilding().equals(mappedBeacon.getBuilding()) &&
				beacon.getFloor() == mappedBeacon.getFloor() &&
				beacon.getRoom() == mappedBeacon.getRoom() &&
				beacon.getxBeacon() == mappedBeacon.getxBeacon() &&
				beacon.getyBeacon() == mappedBeacon.getyBeacon();
	}
	
	public static boolean checkEquality(Beacon beaconOne, Beacon beaconTwo)
	{
		return 	beaconOne.getId().equals(beaconTwo.getId()) &&
				beaconOne.getName().equals(beaconTwo.getName()) &&
				beaconOne.getMac().equals(beaconTwo.getMac()) &&
				beaconOne.getUuidNormal().equals(beaconTwo.getUuidNormal()) &&
				beaconOne.getUuidSecure().equals(beaconTwo.getUuidSecure()) &&
				beaconOne.getUuidService().equals(beaconTwo.getUuidService()) &&
				beaconOne.getBuilding().equals(beaconTwo.getBuilding()) &&
				beaconOne.getFloor() == beaconTwo.getFloor() &&
				beaconOne.getRoom() == beaconTwo.getRoom() &&
				beaconOne.getxBeacon() == beaconTwo.getxBeacon() &&
				beaconOne.getyBeacon() == beaconTwo.getyBeacon();
	}
	
	public static boolean checkEquality(BeaconTo mappedBeaconOne, BeaconTo mappedBeaconTwo)
	{
		return 	mappedBeaconOne.getId().equals(mappedBeaconTwo.getId()) &&
				mappedBeaconOne.getName().equals(mappedBeaconTwo.getName()) &&
				mappedBeaconOne.getMac().equals(mappedBeaconTwo.getMac()) &&
				mappedBeaconOne.getUuidNormal().equals(mappedBeaconTwo.getUuidNormal()) &&
				mappedBeaconOne.getUuidSecure().equals(mappedBeaconTwo.getUuidSecure()) &&
				mappedBeaconOne.getUuidService().equals(mappedBeaconTwo.getUuidService()) &&
				mappedBeaconOne.getBuilding().equals(mappedBeaconTwo.getBuilding()) &&
				mappedBeaconOne.getFloor() == mappedBeaconTwo.getFloor() &&
				mappedBeaconOne.getRoom() == mappedBeaconTwo.getRoom() &&
				mappedBeaconOne.getxBeacon() == mappedBeaconTwo.getxBeacon() &&
				mappedBeaconOne.getyBeacon() == mappedBeaconTwo.getyBeacon();
	}
	
	
	
	
	
	
	public static boolean checkEquality(BeaconStatus beaconStatus, BeaconStatusTo mappedBeaconStatus)
	{
		return 	beaconStatus.getId().equals(mappedBeaconStatus.getId()) &&
				beaconStatus.getMac().equals(mappedBeaconStatus.getMac()) &&
				beaconStatus.getMajor() == mappedBeaconStatus.getMajor() &&
				beaconStatus.getMinor().equals(mappedBeaconStatus.getMinor()) &&
				beaconStatus.getRssi() == mappedBeaconStatus.getRssi() &&
				beaconStatus.getMeasuredStrenght() == mappedBeaconStatus.getMeasuredStrenght() &&
				beaconStatus.getUuid().equals(mappedBeaconStatus.getUuid()) &&
//				beaconStatus.getMacAutomaticMobileSet().equals(mappedBeaconStatus.getMacAutomaticMobileSet()) && //odkomentowac
				beaconStatus.getDistance() == mappedBeaconStatus.getDistance();
	}
	
	public static boolean checkEquality(BeaconStatus beaconStatusOne, BeaconStatus beaconStatusTwo)
	{
		return 	beaconStatusOne.getId().equals(beaconStatusTwo.getId()) &&
				beaconStatusOne.getMac().equals(beaconStatusTwo.getMac()) &&
				beaconStatusOne.getMajor() == beaconStatusTwo.getMajor() &&
				beaconStatusOne.getMinor().equals(beaconStatusTwo.getMinor()) &&
				beaconStatusOne.getRssi() == beaconStatusTwo.getRssi() &&
				beaconStatusOne.getMeasuredStrenght() == beaconStatusTwo.getMeasuredStrenght() &&
				beaconStatusOne.getUuid().equals(beaconStatusTwo.getUuid()) &&
//				beaconStatus.getMacAutomaticMobileSet().equals(mappedBeaconStatus.getMacAutomaticMobileSet()) && //odkomentowac
				beaconStatusOne.getDistance() == beaconStatusTwo.getDistance();
	}
	
	public static boolean checkEquality(BeaconStatusTo beaconStatusToOne, BeaconStatusTo beaconStatusToTwo)
	{
		return 	beaconStatusToOne.getId().equals(beaconStatusToTwo.getId()) &&
				beaconStatusToOne.getMac().equals(beaconStatusToTwo.getMac()) &&
				beaconStatusToOne.getMajor() == beaconStatusToTwo.getMajor() &&
				beaconStatusToOne.getMinor().equals(beaconStatusToTwo.getMinor()) &&
				beaconStatusToOne.getRssi() == beaconStatusToTwo.getRssi() &&
				beaconStatusToOne.getMeasuredStrenght() == beaconStatusToTwo.getMeasuredStrenght() &&
				beaconStatusToOne.getUuid().equals(beaconStatusToTwo.getUuid()) &&
//				beaconStatus.getMacAutomaticMobileSet().equals(mappedBeaconStatus.getMacAutomaticMobileSet()) && //odkomentowac
				beaconStatusToOne.getDistance() == beaconStatusToTwo.getDistance();
	}
	
	public static boolean checkEquality(User user, UserTo userTo)
	{
		boolean passwordsEqual = (user.getPassword() != null && userTo.getPassword() != null && user.getPassword().equals(userTo.getPassword())) ||
								(user.getPassword() == null && userTo.getPassword() == null);
		
		return 	user.getId().equals(userTo.getId()) && 
				user.getFirstName().equals(userTo.getFirstName()) &&
				user.getLastName().equals(userTo.getLastName()) &&
				user.getEmail().equals(userTo.getEmail()) &&
				passwordsEqual &&									//we cant get pswd from database so the check i tricky
				user.getSalt().equals(userTo.getSalt()) &&
				user.getCreatedOn().equals(userTo.getCreatedOn()) &&
				user.getLastLogin().equals(userTo.getLastLogin()) &&
				user.getUserRole().equals(userTo.getUserRole()) &&
				user.isActive() == userTo.isActive();
	}

	public static boolean checkEquality(User userOne, User userTwo)
	{
		boolean passwordsEqual = (userOne.getPassword() != null && userTwo.getPassword() != null && userOne.getPassword().equals(userTwo.getPassword())) ||
				(userOne.getPassword() == null && userTwo.getPassword() == null);

		return 	userOne.getId().equals(userTwo.getId()) &&
				userOne.getFirstName().equals(userTwo.getFirstName()) &&
				userOne.getLastName().equals(userTwo.getLastName()) &&
				userOne.getEmail().equals(userTwo.getEmail()) &&
				passwordsEqual &&									//we cant get pswd from database so the check i tricky
				userOne.getSalt().equals(userTwo.getSalt()) &&
				userOne.getCreatedOn().equals(userTwo.getCreatedOn()) &&
				userOne.getLastLogin().equals(userTwo.getLastLogin()) &&
				userOne.getUserRole().equals(userTwo.getUserRole()) &&
				userOne.isActive() == userTwo.isActive();
	}
	
	public static boolean checkEquality(UserTo userOne, UserTo userTwo)
	{
		boolean passwordsEqual = (userOne.getPassword() != null && userTwo.getPassword() != null && userOne.getPassword().equals(userTwo.getPassword())) ||
				(userOne.getPassword() == null && userTwo.getPassword() == null);
		
		return 	userOne.getId().equals(userTwo.getId()) &&
				userOne.getFirstName().equals(userTwo.getFirstName()) &&
				userOne.getLastName().equals(userTwo.getLastName()) &&
				userOne.getEmail().equals(userTwo.getEmail()) &&
				passwordsEqual &&									//we cant get pswd from database so the check i tricky
				userOne.getSalt().equals(userTwo.getSalt()) &&
				userOne.getCreatedOn().equals(userTwo.getCreatedOn()) &&
				userOne.getLastLogin().equals(userTwo.getLastLogin()) &&
				userOne.getUserRole().equals(userTwo.getUserRole()) &&
				userOne.isActive() == userTwo.isActive();
	}
}

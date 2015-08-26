package org.iot.server.mapper;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.iot.server.document.Beacon;
import org.iot.server.testHelp.TestDataGenerator;
import org.iot.server.to.BeaconTo;
import org.junit.Test;

public class BeaconMapperTest {

	private BeaconMapper beaconMapper = new BeaconMapper();

	@Test
	public void mapDocument2To_MappedValuesEqual()
	{
		Beacon beacon = TestDataGenerator.getBeacon();
		
		BeaconTo mappedBeacon = beaconMapper.mapDocument2To(beacon);
		
		assertEquals(true, checkEquality(beacon, mappedBeacon));
	}

	@Test
	public void mapDocument2To_MappedValuesNotEqual()
	{
		Beacon beacon = TestDataGenerator.getBeacon();
		
		BeaconTo mappedBeacon = beaconMapper.mapDocument2To(beacon);
		
		mappedBeacon.setId(mappedBeacon.getId().concat("1"));
		
		assertEquals(false, checkEquality(beacon, mappedBeacon));
	}
	
	@Test
	public void mapList2ToList_MappedValuesEqual()
	{
		List<Beacon> beaconList = new ArrayList<Beacon>();

		int listSize = 10;
		
		for(int x=0;x<listSize;x++)
			beaconList.add(TestDataGenerator.getBeacon());
		
		List<BeaconTo> mappedList = beaconMapper.mapDocuments2Tos(beaconList);
		
		for(int x=0;x<listSize;x++)
			assertEquals(true, checkEquality(beaconList.get(x), mappedList.get(x)));
	}

	@Test
	public void mapTo2Document_MappedValuesEqual()
	{
		BeaconTo beaconTo = TestDataGenerator.getBeaconTo();
		
		Beacon demappedBeacon = beaconMapper.mapTo2Document(beaconTo);
		
		assertEquals(true, checkEquality(demappedBeacon, beaconTo));	
	}

	private boolean checkEquality(Beacon beacon, BeaconTo mappedBeacon)
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
}

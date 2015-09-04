package org.iot.server.mapper;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.iot.server.document.Beacon;
import org.iot.server.helper.EqualityChecker;
import org.iot.server.helper.TestDataGenerator;
import org.iot.server.to.BeaconTo;
import org.junit.Test;

public class BeaconMapperTest {

	private BeaconMapper beaconMapper = new BeaconMapper();

	@Test
	public void mapDocument2To_MappedValuesEqual()
	{
		Beacon beacon = TestDataGenerator.getBeacon(1);
		
		BeaconTo mappedBeacon = beaconMapper.mapDocument2To(beacon);
		
		assertEquals(true, EqualityChecker.checkEquality(beacon, mappedBeacon));
	}

	@Test
	public void mapDocument2To_MappedValuesNotEqual()
	{
		Beacon beacon = TestDataGenerator.getBeacon(1);
		
		BeaconTo mappedBeacon = beaconMapper.mapDocument2To(beacon);
		
		mappedBeacon.setId(mappedBeacon.getId().concat("1"));
		
		assertEquals(false, EqualityChecker.checkEquality(beacon, mappedBeacon));
	}
	
	@Test
	public void mapList2ToList_MappedValuesEqual()
	{
		List<Beacon> beaconList = new ArrayList<Beacon>();

		int listSize = 10;
		
		for(int x=0;x<listSize;x++)
			beaconList.add(TestDataGenerator.getBeacon(x));
		
		List<BeaconTo> mappedList = beaconMapper.mapDocuments2Tos(beaconList);
		
		for(int x=0;x<listSize;x++)
			assertEquals(true, EqualityChecker.checkEquality(beaconList.get(x), mappedList.get(x)));
	}

	@Test
	public void mapTo2Document_MappedValuesEqual()
	{
		BeaconTo beaconTo = TestDataGenerator.getBeaconTo(1);
		
		Beacon demappedBeacon = beaconMapper.mapTo2Document(beaconTo);
		
		assertEquals(true, EqualityChecker.checkEquality(demappedBeacon, beaconTo));	
	}
}

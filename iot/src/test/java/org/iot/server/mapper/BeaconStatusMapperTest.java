package org.iot.server.mapper;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.iot.server.document.BeaconStatus;
import org.iot.server.helper.EqualityChecker;
import org.iot.server.helper.TestDataGenerator;
import org.iot.server.to.BeaconStatusTo;
import org.junit.Test;

public class BeaconStatusMapperTest {

	private BeaconStatusMapper beaconStatusMapper = new BeaconStatusMapper();

	@Test
	public void mapDocument2To_MappedValuesEqual()
	{
		BeaconStatus beaconStatus = TestDataGenerator.getBeaconStatus(1);
		
		BeaconStatusTo mappedBeaconStatus = beaconStatusMapper.mapDocument2To(beaconStatus);
		
		assertEquals(true, EqualityChecker.checkEquality(beaconStatus, mappedBeaconStatus));
	}

	@Test
	public void mapDocument2To_MappedValuesNotEqual()
	{
		BeaconStatus beaconStatus = TestDataGenerator.getBeaconStatus(1);
		
		BeaconStatusTo mappedBeaconStatus = beaconStatusMapper.mapDocument2To(beaconStatus);
		
		mappedBeaconStatus.setMac(mappedBeaconStatus.getMac().concat("1"));
		
		assertEquals(false, EqualityChecker.checkEquality(beaconStatus, mappedBeaconStatus));
	}
	
	@Test
	public void mapList2ToList_MappedValuesEqual()
	{
		List<BeaconStatus> beaconStatusList = new ArrayList<BeaconStatus>();

		int listSize = 10;
		
		for(int x=0;x<listSize;x++)
			beaconStatusList.add(TestDataGenerator.getBeaconStatus(x));
		
		List<BeaconStatusTo> mappedList = beaconStatusMapper.mapDocuments2Tos(beaconStatusList);
		
		for(int x=0;x<listSize;x++)
			assertEquals(true, EqualityChecker.checkEquality(beaconStatusList.get(x), mappedList.get(x)));
	}

	@Test
	public void mapTo2Document_MappedValuesEqual()
	{
		BeaconStatusTo beaconStatusTo = TestDataGenerator.getBeaconStatusTo(1);
		
		BeaconStatus demappedBeaconStatus = beaconStatusMapper.mapTo2Document(beaconStatusTo);
		
		assertEquals(true, EqualityChecker.checkEquality(demappedBeaconStatus, beaconStatusTo));	
	}
}

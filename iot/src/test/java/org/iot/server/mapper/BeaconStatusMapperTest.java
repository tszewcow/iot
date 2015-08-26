package org.iot.server.mapper;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.iot.server.document.BeaconStatus;
import org.iot.server.testHelp.TestDataGenerator;
import org.iot.server.to.BeaconStatusTo;
import org.junit.Test;

public class BeaconStatusMapperTest {

	private BeaconStatusMapper beaconStatusMapper = new BeaconStatusMapper();

	@Test
	public void mapDocument2To_MappedValuesEqual()
	{
		BeaconStatus beaconStatus = TestDataGenerator.getBeaconStatus();
		
		BeaconStatusTo mappedBeaconStatus = beaconStatusMapper.mapDocument2To(beaconStatus);
		
		assertEquals(true, checkEquality(beaconStatus, mappedBeaconStatus));
	}

	@Test
	public void mapDocument2To_MappedValuesNotEqual()
	{
		BeaconStatus beaconStatus = TestDataGenerator.getBeaconStatus();
		
		BeaconStatusTo mappedBeaconStatus = beaconStatusMapper.mapDocument2To(beaconStatus);
		
		mappedBeaconStatus.setMac(mappedBeaconStatus.getMac().concat("1"));
		
		assertEquals(false, checkEquality(beaconStatus, mappedBeaconStatus));
	}
	
	@Test
	public void mapList2ToList_MappedValuesEqual()
	{
		List<BeaconStatus> beaconStatusList = new ArrayList<BeaconStatus>();

		int listSize = 10;
		
		for(int x=0;x<listSize;x++)
			beaconStatusList.add(TestDataGenerator.getBeaconStatus());
		
		List<BeaconStatusTo> mappedList = beaconStatusMapper.mapDocuments2Tos(beaconStatusList);
		
		for(int x=0;x<listSize;x++)
			assertEquals(true, checkEquality(beaconStatusList.get(x), mappedList.get(x)));
	}

	@Test
	public void mapTo2Document_MappedValuesEqual()
	{
		BeaconStatusTo beaconStatusTo = TestDataGenerator.getBeaconStatusTo();
		
		BeaconStatus demappedBeaconStatus = beaconStatusMapper.mapTo2Document(beaconStatusTo);
		
		assertEquals(true, checkEquality(demappedBeaconStatus, beaconStatusTo));	
	}

	private boolean checkEquality(BeaconStatus beaconStatus, BeaconStatusTo mappedBeaconStatus)
	{
		return 	beaconStatus.getMac().equals(mappedBeaconStatus.getMac()) &&
				beaconStatus.getMajor() == mappedBeaconStatus.getMajor() &&
				beaconStatus.getMinor().equals(mappedBeaconStatus.getMinor()) &&
				beaconStatus.getRssi() == mappedBeaconStatus.getRssi() &&
				beaconStatus.getMeasuredStrenght() == mappedBeaconStatus.getMeasuredStrenght() &&
				beaconStatus.getUuid().equals(mappedBeaconStatus.getUuid()) &&
//				beaconStatus.getMacAutomaticMobileSet().equals(mappedBeaconStatus.getMacAutomaticMobileSet()) && //odkomentowac
				beaconStatus.getDistance() == mappedBeaconStatus.getDistance();
	}
}

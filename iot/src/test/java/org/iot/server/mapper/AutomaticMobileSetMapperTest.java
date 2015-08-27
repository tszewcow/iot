package org.iot.server.mapper;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.iot.server.document.AutomaticMobileSet;
import org.iot.server.testHelp.TestDataGenerator;
import org.iot.server.to.AutomaticMobileSetTo;
import org.junit.Test;

public class AutomaticMobileSetMapperTest {

	private AutomaticMobileSetMapper automaticMobileSetMapper = new AutomaticMobileSetMapper();

	@Test
	public void mapDocument2To_MappedValuesEqual() {

		AutomaticMobileSet automaticMobileSet = TestDataGenerator.getAutomaticMobileSet();
		
		AutomaticMobileSetTo mappedSet = automaticMobileSetMapper.mapDocument2To(automaticMobileSet);
		
		assertEquals(true, checkEquality(automaticMobileSet, mappedSet));
	}

	@Test
	public void mapDocument2To_MappedValuesNotEqual() {

		AutomaticMobileSet automaticMobileSet = TestDataGenerator.getAutomaticMobileSet();
		
		AutomaticMobileSetTo mappedSet = automaticMobileSetMapper.mapDocument2To(automaticMobileSet);
		
		mappedSet.setId(mappedSet.getId().concat("1"));
		
		assertEquals(false, checkEquality(automaticMobileSet, mappedSet));
	}
	
	@Test
	public void mapList2ToList_MappedValuesEqual()
	{
		List<AutomaticMobileSet> amsList = new ArrayList<AutomaticMobileSet>();

		int listSize = 10;
		
		for(int x=0;x<listSize;x++)
			amsList.add(TestDataGenerator.getAutomaticMobileSet());
		
		List<AutomaticMobileSetTo> mappedList = automaticMobileSetMapper.mapDocuments2Tos(amsList);
		
		for(int x=0;x<listSize;x++)
			assertEquals(true, checkEquality(amsList.get(x), mappedList.get(x)));
	}

	@Test
	public void mapTo2Document_MappedValuesEqual()
	{
		AutomaticMobileSetTo automaticMobileSetTo = TestDataGenerator.getAutomaticMobileSetTo();
		
		AutomaticMobileSet demappedSet = automaticMobileSetMapper.mapTo2Document(automaticMobileSetTo);
		
		assertEquals(true, checkEquality(demappedSet, automaticMobileSetTo));	
	}
	
	
	
	
	
	private boolean checkEquality(AutomaticMobileSet ams, AutomaticMobileSetTo mappedAms)
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
}

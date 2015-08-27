package org.iot.server.mapper;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.iot.server.document.AutomaticMobileSet;
import org.iot.server.helper.EqualityChecker;
import org.iot.server.helper.TestDataGenerator;
import org.iot.server.to.AutomaticMobileSetTo;
import org.junit.Test;

public class AutomaticMobileSetMapperTest {

	private AutomaticMobileSetMapper automaticMobileSetMapper = new AutomaticMobileSetMapper();

	@Test
	public void mapDocument2To_MappedValuesEqual() {

		AutomaticMobileSet automaticMobileSet = TestDataGenerator.getAutomaticMobileSet();
		
		AutomaticMobileSetTo mappedSet = automaticMobileSetMapper.mapDocument2To(automaticMobileSet);
		
		assertEquals(true, EqualityChecker.checkEquality(automaticMobileSet, mappedSet));
	}

	@Test
	public void mapDocument2To_MappedValuesNotEqual() {

		AutomaticMobileSet automaticMobileSet = TestDataGenerator.getAutomaticMobileSet();
		
		AutomaticMobileSetTo mappedSet = automaticMobileSetMapper.mapDocument2To(automaticMobileSet);
		
		mappedSet.setId(mappedSet.getId().concat("1"));
		
		assertEquals(false, EqualityChecker.checkEquality(automaticMobileSet, mappedSet));
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
			assertEquals(true, EqualityChecker.checkEquality(amsList.get(x), mappedList.get(x)));
	}

	@Test
	public void mapTo2Document_MappedValuesEqual()
	{
		AutomaticMobileSetTo automaticMobileSetTo = TestDataGenerator.getAutomaticMobileSetTo();
		
		AutomaticMobileSet demappedSet = automaticMobileSetMapper.mapTo2Document(automaticMobileSetTo);
		
		assertEquals(true, EqualityChecker.checkEquality(demappedSet, automaticMobileSetTo));	
	}
	
	
	
	
	

}

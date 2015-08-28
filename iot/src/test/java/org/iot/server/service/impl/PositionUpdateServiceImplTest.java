package org.iot.server.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.iot.server.repository.AutomaticMobileSetRepository;
import org.iot.server.repository.BeaconRepository;
import org.iot.server.repository.BeaconStatusRepository;
import org.iot.server.to.AutomaticMobileSetTo;
import org.iot.server.to.BeaconStatusTo;
import org.iot.server.to.BeaconTo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PositionUpdateServiceImplTest
{
	@Captor
    private ArgumentCaptor<List<BeaconTo>> beaconToListCaptor;
	@Captor
    private ArgumentCaptor<List<BeaconStatusTo>> beaconStatusToListCaptor;
	@Captor
    private ArgumentCaptor<List<AutomaticMobileSetTo>> automaticMobileSetToListCaptor;
	
	
	@Mock
	private PositionUpdater positionUpdater;
	@Mock
	private BeaconStatusRepository beaconStatusRepository;
	@Mock
	private BeaconRepository beaconRepository;
	@Mock
	private AutomaticMobileSetRepository automaticMobileSetRepository;
	
	@InjectMocks
	private PositionUpdateServiceImpl positionUpdateServiceImpl;
	
	
	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testInitialization()
	{
		assertEquals(true, positionUpdateServiceImpl != null);
	}
	
	@Test
	public void testUpdatePositions()
	{
		positionUpdateServiceImpl.updatePositions();

		verify(beaconRepository).findAll();
		verify(beaconStatusRepository).findAll();
		verify(automaticMobileSetRepository).findAll();

		verify(positionUpdater).updateAutomaticMobileSetPositions(beaconToListCaptor.capture(),
															beaconStatusToListCaptor.capture(),
															automaticMobileSetToListCaptor.capture());

		assertNotNull(beaconToListCaptor.getValue());
		assertNotNull(beaconStatusToListCaptor.getValue());
		assertNotNull(automaticMobileSetToListCaptor.getValue());
	}
}

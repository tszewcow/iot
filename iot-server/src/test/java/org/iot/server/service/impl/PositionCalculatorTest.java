package org.iot.server.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.iot.server.service.impl.PositionCalculator.Pair;
import org.iot.server.to.PositionTo;
import org.junit.Ignore;
import org.junit.Test;

public class PositionCalculatorTest {

	private final PositionCalculator positionCalculator = new PositionCalculator();

	@Ignore
	@Test
	public void calculatePositionShouldreturnNotNullResult() {
		// given
		List<Pair<PositionTo, Float>> points = new ArrayList<>();
		points.add(new Pair<>(new PositionTo(0.101f, 1f), 5f));
		points.add(new Pair<>(new PositionTo(3.99f, 4.89f), 4f));
		// when
		PositionTo result = positionCalculator.calculatePosition(points);
		// then
		assertNotNull(result);
	}

	// @Ignore
	@Test
	public void shouldCalculatePositionCorrectly() {
		// given
		List<Pair<PositionTo, Float>> points = new ArrayList<>();
		points.add(new Pair<>(new PositionTo(5f, 0f), 5f));
		points.add(new Pair<>(new PositionTo(0f, 5f), 4f));
		points.add(new Pair<>(new PositionTo(5f, 7f), 3f));
		// when
		PositionTo result = positionCalculator.calculatePosition(points);
		// then
		assertEquals(1f, result.getX(), 0);
		assertEquals(2f, result.getY(), 0);
	}

}

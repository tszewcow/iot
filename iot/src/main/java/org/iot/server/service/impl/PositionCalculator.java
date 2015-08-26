package org.iot.server.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.iot.server.to.PositionTo;
import org.springframework.stereotype.Component;

import math.geom2d.Point2D;
import math.geom2d.conic.Circle2D;

@Component
class PositionCalculator {

	public PositionTo calculatePosition(List<Pair<PositionTo, Float>> circles) {

		List<Circle2D> circles2d = new ArrayList<>(circles.size());
		List<Point2D> intersectionPoints = new ArrayList<>();

		getCircles2d(circles, circles2d);
		getIntersectiosnPoints(intersectionPoints, circles2d);

		Point2D centroid = Point2D.centroid(intersectionPoints);

		return new PositionTo((float) centroid.x(), (float) centroid.y());
	}

	protected List<Circle2D> getCircles2d(List<Pair<PositionTo, Float>> circles, List<Circle2D> circles2d) {

		for (Pair<PositionTo, Float> circle : circles) {
			PositionTo ab = circle.getA();
			Float r = circle.getB();
			Circle2D circle2d = new Circle2D(ab.getX(), ab.getY(), r);
			circles2d.add(circle2d);
		}
		return circles2d;
	}

	protected List<Point2D> getIntersectiosnPoints(List<Point2D> intersectionPoints, List<Circle2D> circles2d) {

		if (circles2d.size() > 1) {
			for (int i = 0; i < circles2d.size(); i++) {
				for (int j = i + 1; j < circles2d.size(); j++) {
					Collection<Point2D> cirlesIntersectionPoints = circles2d.get(i).intersections(circles2d.get(j));
					intersectionPoints.addAll(cirlesIntersectionPoints);
				}
			}
		} /*
			 * else { throw new IllegalArgumentException(
			 * "Should more beacons than only one"); }
			 */

		return intersectionPoints;
	}

	public static class Pair<A, B> {
		private A a;
		private B b;

		public Pair() {
		}

		public Pair(A a, B b) {
			this.a = a;
			this.b = b;
		}

		public A getA() {
			return a;
		}

		public void setA(A a) {
			this.a = a;
		}

		public B getB() {
			return b;
		}

		public void setB(B b) {
			this.b = b;
		}
	}

}

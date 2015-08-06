package org.iot.server.service.impl;

import java.util.List;

import org.iot.server.to.PositionTo;
import org.springframework.stereotype.Component;

@Component
class PositionCalculator {

	public PositionTo calculatePosition(List<Pair<PositionTo, Float>> points) {

		return new PositionTo();
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

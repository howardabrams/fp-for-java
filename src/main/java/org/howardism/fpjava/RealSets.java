package org.howardism.fpjava;

/**
 * A collection of utility functions for dealing with with {@link RealSet}.
 *
 */

public class RealSets {
	
	public static RealSet singleton(final int i) {
		return new RealSet() {
			public boolean contains(final int n) { return n == i; }
		};
	}

	public static RealSet evens() {
		return new RealSet() {
			public boolean contains(final int n) { return n % 2 == 0; }
		};
	}
	public static RealSet odds() {
		return new RealSet() {
			public boolean contains(final int n) { return n % 2 == 1; }
		};
	}

	public static RealSet union(final RealSet a, final RealSet b) {
		return new RealSet() {
			public boolean contains(final int n) {
				return a.contains(n) || b.contains(n);
			}
		};
	}

	public static RealSet intersection(final RealSet a, final RealSet b) {
		return new RealSet() {
			public boolean contains(final int n) {
				return a.contains(n) && b.contains(n);
			}
		};
	}
}

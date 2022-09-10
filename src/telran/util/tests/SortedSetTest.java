package telran.util.tests;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import telran.util.Collection;

public abstract class SortedSetTest extends SetTests {

	@Test
	@Override
	void toArrayTest() {
		Arrays.sort(expected);
		super.toArrayTest();
	}
}

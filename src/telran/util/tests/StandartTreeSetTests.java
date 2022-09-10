package telran.util.tests;

import telran.util.Collection;
import telran.util.StandartTreeSet;

public class StandartTreeSetTests extends SortedSetTest {

	@Override
	protected Collection<Integer> createCollection() {
	
		return new StandartTreeSet<Integer>();
	}
	
	

}

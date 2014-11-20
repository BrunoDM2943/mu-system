package testCases.sprint1;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses(value = {
		AcessorioDaoImplTest.class,
		ClienteDaoImplTest.class,
		FabricanteDaoImplTest.class,
		MidiaDaoImplTest.class
		
})
public class Sprint1AllTests {

}

package testCases;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import testCases.frameWork.FrameWorkAllTests;
import testCases.sprint1.Sprint1AllTests;

@RunWith(Suite.class)
@SuiteClasses(value = {
		Sprint1AllTests.class,
		FrameWorkAllTests.class
		
})
public class AppAllTests {

}

package husaccttest.analyse;

import husaccttest.analyse.java.JavaTestSuite;
import husaccttest.analyse.java.benchmark.BenchmarkTestSuite;
import husaccttest.analyse.java.blackbox.TestDependencyFilters;
import husaccttest.analyse.java.blackbox.TestDomainDependencies;
import husaccttest.analyse.java.blackbox.TestDomainModule;
import husaccttest.analyse.java.blackbox.TestIndirect;
import husaccttest.analyse.java.blackbox.TestLanguage;
import husaccttest.analyse.java.recognation.RecognationTestSuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	JavaTestSuite.class
})
public class AnalyseTestSuite {
}

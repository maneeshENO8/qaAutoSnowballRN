package com.openkey.basetest;

import com.openkey.setups.CapabilitiesManager;
import com.openkey.utils.AllureReporting;
import com.openkey.utils.DataBaseHandler;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import org.testng.annotations.*;

import java.io.IOException;
import java.sql.SQLException;

@CucumberOptions(
        plugin = {"pretty","io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm","com.openkey.utils.HooksManager"},
        monochrome = true,
        tags = "@AppLaunchRNAndroidApp or @AppLoginRNAndroidApp",// or @OpenKeyDoorLockReactNativeApp", //or @DownloadMobileKey or ,
        features = "src/main/com.openkey.resources/features",
        glue = {"com.openkey.steps","com/openkey/utils"},
        publish = true,
        dryRun = false
)

public class TestRunner extends CapabilitiesManager {

    private TestNGCucumberRunner testNGCucumberRunner;
    static AllureReporting allureReporting ;
static DataBaseHandler dataBaseHandler;
    public TestRunner() throws IOException {

        allureReporting = new AllureReporting();
        dataBaseHandler=new DataBaseHandler();
    }

    @BeforeClass(alwaysRun = true)
    public void setUpClass() {

        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());

    }

    @BeforeTest(alwaysRun = true)

    public void cleanOldReportsData() throws IOException {

        allureReporting.cleanUpAllureDirectory();

    }


    @Test(groups = "cucumber", description = "Run Cucumber Features.", dataProvider = "scenarios")
    public void scenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {

        testNGCucumberRunner.runScenario(pickleWrapper.getPickle());

    }

    @DataProvider
    public Object[][] scenarios() {

        return testNGCucumberRunner.provideScenarios();

    }

@AfterTest (alwaysRun = true)

public void createpropFile() throws IOException, SQLException, ClassNotFoundException {

        allureReporting.envFileWriter();
    allureReporting.executorFileWriter();
    dataBaseHandler.setupConnection();

    }


    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws IOException {


        testNGCucumberRunner.finish();

    }

}

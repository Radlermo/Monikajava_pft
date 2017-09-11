package pl.stqa.pft.addressbook.tests;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pl.stqa.pft.addressbook.appmanager.ApplicationManager;

public class TestBase {

  protected final ApplicationManager app = new ApplicationManager();
  FirefoxDriver wd;

    @BeforeMethod
  public void setUp() throws Exception {
    app.init();

  }

  @AfterMethod
  public void tearDown() {
    app.stop();
  }

}

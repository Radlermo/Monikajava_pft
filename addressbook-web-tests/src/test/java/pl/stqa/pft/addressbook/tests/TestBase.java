package pl.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import pl.stqa.pft.addressbook.appmanager.ApplicationManager;
import pl.stqa.pft.addressbook.model.ContactData;
import pl.stqa.pft.addressbook.model.Contacts;
import pl.stqa.pft.addressbook.model.GroupData;
import pl.stqa.pft.addressbook.model.Groups;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;


public class TestBase {

  Logger logger = LoggerFactory.getLogger(TestBase.class);

  protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));


    @BeforeSuite
  public void setUp() throws Exception {
    app.init();
      }

  @AfterSuite (alwaysRun = true)
  public void tearDown() {
    app.stop();
  }

  //logowanie startu testu
  @BeforeMethod
  public void logTestStart(Method m, Object[] p){
    logger.info("Start test " + m.getName() + " with parameters " + Arrays.asList(p));
        }
  //logowanie zakończenia testu
  @AfterMethod(alwaysRun = true)
  public void logTestStop(Method m) {
    logger.info("Stop test " + m.getName());
  }

  public void verifyGroupListInUi() {
    if (Boolean.getBoolean("verifyUI")) {
      Groups dbGroups = app.db().groups();
      Groups uiGroups = app.group().all();
      assertThat(uiGroups, equalTo(dbGroups.stream()
              .map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
              .collect(Collectors.toSet())));
    }
  }

  public void VerifyContactListInUi() {
    if (Boolean.getBoolean("verifyUI")) {
      Contacts dbContacts = app.db().contacts();
      Contacts uiContacts = app.contact().allContacts();
      assertThat(uiContacts, equalTo(dbContacts.stream()
              .map((g) -> new ContactData().withId(g.getId()).withFirstname(g.getFirstname()).withLastname(g.getLastname()).withAddress(g.getAddress())
                      .withAllPhones(g.getAllPhones()).withAllEmails(g.getAllEmails()))
              .collect(Collectors.toSet())));
    }
  }
}

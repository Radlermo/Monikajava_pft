package pl.stqa.pft.addressbook;

import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;

public class GroupCreationTest extends TestBase {

  @Test
  public void testGroupCreation() {
    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("test", "test2", "test3"));
    submitGroupCreation();
    returnToGroupPage();
  }

}

package pl.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pl.stqa.pft.addressbook.model.ContactData;
import org.openqa.selenium.NoSuchElementException;

import java.util.ArrayList;
import java.util.List;


public class ContactHelper extends HelperBase{

  private int id;

  public ContactHelper(WebDriver wd) {
    super (wd);
  }

  public void goToContactCreation() {
    click(By.linkText("add new"));
  }

  public void click(By locator) {
    wd.findElement(locator).click();
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("home"), contactData.getTelephonehome());
    type(By.name("email"), contactData.getEmail());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    }else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void type(By locator, String text) {
    click(locator);
    wd.findElement(locator).clear();
    wd.findElement(locator).sendKeys(text);
  }

  public void goToHomePage() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
    /*click(By.linkText("home page")); */

  }

  public void selectContact(int index) {
     wd.findElements(By.name("selected[]")).get(index).click();

    /*click(By.name("selected[]")); */
    /*click(By.id("6")); */
}


   public void initContactModification(int index) {
   /*wd.findElement(By.xpath(String.format("//input[@id=' ']/../..//img[@title='Edit']", index ))).click();*/
     wd.findElement(By.xpath("//table[@id='maintable']/tbody/tr["+index+"]/td[8]/a/img")).click();
     }

  public void submitContactModification() {
    click(By.xpath("//div[@id='content']/form[1]/input[22]"));
  }


  public void deleteContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void alertAccept() {
    wd.switchTo().alert().accept();
  }

  public void createContact(ContactData contact, boolean creation) {
    goToContactCreation();
    fillContactForm(contact, true);
    goToHomePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

 public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();

    /* List<WebElement> elements = wd.findElements(By)*/

   /* List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      String name = element.getText();
     /* int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));*/
      /*int id = Integer.parseInt(elements.get(0).findElements(By.tagName("TD")).get(1).getText());*/
     /*int id = Integer.parseInt(elements.get(0).findElements(By.tagName("td")).get(0).findElement(By.tagName("input")).getAttribute("id"));*/
      List<WebElement> elements = wd.findElements(By.name("entry"));
      for (WebElement element: elements) {
      /*String name = element.getText();*/
      List<WebElement> cells = element.findElements(By.tagName("td"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
     int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("id"));


      ContactData contact = new ContactData(id, "Monika6", "Radler8", null, null, null);
      contacts.add(contact);
    }


    return contacts;
  }
}

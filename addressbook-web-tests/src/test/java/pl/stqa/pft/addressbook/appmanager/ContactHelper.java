package pl.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pl.stqa.pft.addressbook.model.ContactData;
import pl.stqa.pft.addressbook.model.Contacts;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


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
   index += 2;
   click(By.xpath("html/body/div/div[4]/form[2]/table/tbody/tr[" + index + "]/td[1]/input"));
     }

  private void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[id='" + id + "']")).click();

  }

  public void selectContactToEditById(int id) {
    wd.findElement(By.xpath("//input[@id='"+ id + "']/../..//img[@title='Edit']")).click();
  }

  public void initContactModification(int index) {
    index += 2;
    click(By.xpath("html/body/div/div[4]/form[2]/table/tbody/tr[" + index + "]/td[8]/a/img"));
  }

 /*public void initContactModificationByid(int id) {
   id += 2;
    //click(By.xpath("html/body/div/div[4]/form[2]/table/tbody/tr[" + id + "]/td[8]/a/img"));
    click(By.xpath("html/body/div/div[4]/form[2]/table/tbody/tr[" + id + "]/td[8]/a/img"));
  }*/
  public void initContactModificationt() {
    click(By.name("update"));
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

  public void create(ContactData contact, boolean creation) {
    goToContactCreation();
    fillContactForm(contact, true);
    goToHomePage();
  }

  public void modify(ContactData contact) {
    selectContactToEditById(contact.getId());
    //initContactModificationByid(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
  }

  public void delete(int index) {
    selectContact(index);
    deleteContact();
    alertAccept();
  }
  public void delete(ContactData contact) {
      selectContactById(contact.getId());
      deleteContact();
      alertAccept();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }



 public Contacts allContacts() {
   Contacts contacts = new Contacts();

      List<WebElement> elements = wd.findElements(By.name("entry"));
      for (WebElement element: elements) {
      /*String name = element.getText();*/
      List<WebElement> cells = element.findElements(By.tagName("td"));

      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("id"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();

      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
    }

    return contacts;
  }


}

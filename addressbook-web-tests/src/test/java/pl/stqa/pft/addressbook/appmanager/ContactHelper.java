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
    type(By.name("address"), contactData.getAddress());
    type(By.name("email"), contactData.getEmail());
    attach(By.name("photo"), contactData.getPhoto());

    if (creation) {
      //new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        if (contactData.getGroups().size() > 0) {
            Assert.assertTrue(contactData.getGroups().size() == 1);
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
        }
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
    contactCache = null;
    goToHomePage();
  }

  public void modify(ContactData contact) {
    selectContactToEditById(contact.getId());
    //initContactModificationByid(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    contactCache = null;
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
      contactCache = null;
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

    private Contacts contactCache = null;

 public Contacts allContacts() {
     if (contactCache !=null) {
         return new Contacts(contactCache);
     }

 contactCache = new Contacts();
      List<WebElement> elements = wd.findElements(By.name("entry"));
      for (WebElement element: elements) {
      /*String name = element.getText();*/
      List<WebElement> cells = element.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("id"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String address = cells.get(3).getText();
      String allEmails = cells.get(4).getText();
      String allPhones = cells.get(5).getText();
      contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname).withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address));
    }

    return new Contacts(contactCache);
  }


    public ContactData infoFromEditForm(ContactData contact) {
     initContactModificationById(contact.getId());
     String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
     String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
     String telephonehome = wd.findElement(By.name("home")).getAttribute("value");
     String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
     String work = wd.findElement(By.name("work")).getAttribute("value");
     String email = wd.findElement(By.name("email")).getAttribute("value");
     String email2 = wd.findElement(By.name("email2")).getAttribute("value");
     String email3 = wd.findElement(By.name("email3")).getAttribute("value");
     String address = wd.findElement(By.name("address")).getAttribute("value");
     wd.navigate().back();
     return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname).withTelephonehome(telephonehome).withMobilePhone(mobile).withWorkPhone(work)
             .withEmail(email).withEmail2(email2).withEmail3(email3).withAddress(address);
 }


    private void initContactModificationById (int id) {
     WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s'", id)));
     WebElement row = checkbox.findElement(By.xpath("./../.."));
     List<WebElement> cells = row.findElements(By.tagName("td"));
     cells.get(7).findElement(By.tagName("a")).click();
     // można też użyć innych sposobów szukania checkboxa
     //wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click();
     //wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a", id))).click();
     //wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();

    }

    public ContactData infoFromDetailsForm(ContactData contact) {
        initContactDetailsById(contact.getId());
        String allDetails = wd.findElement(By.xpath("//div[@id='content']")).getText();
        wd.navigate().back();
        return new ContactData().withAllDetails(allDetails);
    }

    private void initContactDetailsById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='view.php?id=%s']", id))).click();

    }

}

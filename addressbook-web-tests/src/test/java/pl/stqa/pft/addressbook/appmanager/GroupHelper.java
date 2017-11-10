package pl.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import pl.stqa.pft.addressbook.model.GroupData;
import pl.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupHelper extends HelperBase {

  public GroupHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToGroupPage() {
    click(By.linkText("group page"));

  }

  public void submitGroupCreation() {
    click(By.name("submit"));
  }

  public void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.getName());
    click(By.name("group_header"));
    type(By.name("group_header"), groupData.getHeader());
    type(By.name("group_footer"), groupData.getFooter());
  }

  public void initGroupCreation() {
    click(By.name("new"));
  }

  public void deleteSelectedGroup() {
    click(By.name("delete"));
  }

  public void selectGroup(int index) {
    wd.findElements(By.name("selected[]")).get(index).click(); /*get(index).click - zaznaczy się grupa, którą wybierzemy po indeksie*/
      /* click(By.name("selected[]")); */
      }
  public void selectGroupById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id +"']")).click();
  }


  public void initGroupModification() {
    click(By.name("edit"));
  }

  public void submitGroupModification() {
    click(By.name("update"));
  }

  public void create(GroupData group) {
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    groupCache = null;
    returnToGroupPage();
  }


  public void modify(GroupData group) {
    selectGroupById(group.getId());
    initGroupModification();
    fillGroupForm(group);
    submitGroupModification();
    groupCache = null;
    returnToGroupPage();}


  /* public void delete(int index) {
    selectGroup(index);
    deleteSelectedGroup();
    returnToGroupPage();
  }*/

  public void delete(GroupData group) {
    selectGroupById(group.getId());
    deleteSelectedGroup();
    groupCache = null;
    returnToGroupPage();
  }
  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getGroupCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  //listy
  public List<GroupData> list() {
    List<GroupData> groups = new ArrayList<GroupData>();/* tworzymy listę*/
    /*wypełniamy listę*/
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    /*przejście po elementach w cyklu*/
    for (WebElement element : elements) {
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      groups.add(new GroupData().withId(id).withName(name));
    }

    return groups;
  }

  //keszowanie - zapamiętywanie wyniku jakiejś operacji po to żeby nie sprawdzać tego drugi raz w innym miejscu
  private Groups groupCache = null;

//zbiory
  public Groups all() {
    if (groupCache !=null) {
      return new Groups(groupCache);//jeśli cache nie jest pusty to trzeba zwrócić jego kopię
    }
    groupCache = new Groups();/* tworzymy listę*/
    /*wypełniamy listę*/
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    /*przejście po elementach w cyklu*/
    for (WebElement element : elements) {
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      groupCache.add(new GroupData().withId(id).withName(name));
    }

    return new Groups(groupCache);
  }



}

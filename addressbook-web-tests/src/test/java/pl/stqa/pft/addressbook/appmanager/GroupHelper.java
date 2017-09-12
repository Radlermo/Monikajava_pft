package pl.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import pl.stqa.pft.addressbook.model.GroupData;

public class GroupHelper extends HelperBase {

  public GroupHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void returnToGroupPage() {
    click(By.linkText("group page"));
    click(By.cssSelector("span.group"));
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

  public void selectGroup() {
      if (!wd.findElement(By.xpath("//div[@id='content']/form/span[2]/input")).isSelected()) {
        click(By.xpath("//div[@id='content']/form/span[2]/input"));
      }
  }

  public void initGroupModification() {
    click(By.name("edit"));

  }

  public void submitGroupModification() {
    click(By.name("update"));
  }
}

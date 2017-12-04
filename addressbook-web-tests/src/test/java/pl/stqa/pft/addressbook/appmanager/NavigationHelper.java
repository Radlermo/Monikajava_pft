package pl.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void groupPage() {
        if (isElementPresent(By.tagName("h1"))
                && wd.findElement(By.tagName("h1")).getText().equals("Groups")
                && isElementPresent(By.name("new"))) {
            return;
        }
        click(By.linkText("groups"));


    }

    public void goToHomePage() {
        //click(By.xpath("//div[@id='content']/form/input[21]"));
        wd.findElement(By.linkText("home")).click();
            }

    public void addNew() { //sprawdż czy na stronie kontaktów istnieje grupa, jeśli tak utwórz kontact
        if (isElementPresent(By.tagName("h1"))
                && wd.findElement(By.tagName("h1")).getText().equals("Edit / add address book entry")
                && (isElementPresent(By.name("new_group")))) {
            return;
        }
        click(By.linkText("add new"));
    }
}

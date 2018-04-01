package ru.testqa.novotelecom.mantis.appmanager;

import org.openqa.selenium.By;

import static org.openqa.selenium.By.name;

public class ChangePasswordHelper extends HelperBase {

  public ChangePasswordHelper(ApplicationManager app) {
    super(app);
  }

  public void login(String username, String password) {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(name("username"), username);
    type(name("password"), password);
    click(By.cssSelector("input[value='Login']"));
  }

  public void changePassword () {
    click(By.linkText("Manage"));
    click(By.linkText("Manage Users"));
    click(By.linkText("user1"));
    click(By.cssSelector("input[value='Reset Password']"));
  }

  public void finish(String confirmationLink, String newpass) {
    wd.get(confirmationLink);
    type(name("password"), newpass);
    type(name("password_confirm"), newpass);
    click(By.cssSelector("input[value='Update User']"));
  }
}

package ru.testqa.novotelecom.addressbook.tests;

import org.testng.annotations.Test;
import ru.testqa.novotelecom.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {
    app.getNavigationHelper().goToContactPage();
    if (! app.getContactHelper().isThereAContact()) {
    app.getContactHelper().createContact(new ContactData("Иван", "Иванов", "ул.Мира 5", "333-33-33", "test1@mail.ru", "test2@mail.ru", "test3@mail.ru", "test1"), true);
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().returnToContactPage();
  }

  @Test
  public void testContactDeletionAlert() {
    app.getNavigationHelper().goToContactPage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Иван", "Иванов", "ул.Мира 5", "333-33-33", "test1@mail.ru", "test2@mail.ru", "test3@mail.ru", "test1"), true);
    }
    app.getContactHelper().clickElementForDelete();
    app.getContactHelper().clickButtonDelete();
    app.getContactHelper().returnToContactPage();
  }

}

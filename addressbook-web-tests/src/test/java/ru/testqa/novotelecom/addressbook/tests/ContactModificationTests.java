package ru.testqa.novotelecom.addressbook.tests;

import org.testng.annotations.Test;
import ru.testqa.novotelecom.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getContactHelper().goToContactCreate();
    app.getContactHelper().returnToContactPage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Иван", "Иванов", "ул.Мира 5", "333-33-33", "test1@mail.ru", "test2@mail.ru", "test3@mail.ru", "test1"), true);
    }
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Иван", "Иванов", "ул.Мира 5", "333-33-33", "test1@mail.ru", "test2@mail.ru", "test3@mail.ru", null),false);
    app.getContactHelper().submitContactModification();
  }
}

package ru.testqa.novotelecom.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.testqa.novotelecom.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {
    if (! app.getContactHelper().isThereAContact()) {
    app.getContactHelper().createContact(new ContactData("1Иван", "1Иванов", "ул.Мира 5", "333-33-33", "test1@mail.ru", "test2@mail.ru", "test3@mail.ru", "test1"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().returnToContactPage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }

  @Test
  public void testContactDeletionAlert() {
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("1Иван", "1Иванов", "ул.Мира 5", "333-33-33", "test1@mail.ru", "test2@mail.ru", "test3@mail.ru", "test1"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().clickElementForDelete();
    app.getContactHelper().clickButtonDelete();
    app.getContactHelper().returnToContactPage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }

}

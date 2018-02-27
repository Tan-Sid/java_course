package ru.testqa.novotelecom.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.testqa.novotelecom.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    app.getContactHelper().goToContactCreate();
    app.getContactHelper().returnToContactPage();

    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("1Иван", "1Иванов", "ул.Мира 5", "333-33-33", "test1@mail.ru", "test2@mail.ru", "test3@mail.ru", "test1"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContactModification(before.size()-1);
    ContactData contact = new ContactData("1Иван", "2Иванов", "ул.Мира 5", "333-33-33", "test1@mail.ru", "test2@mail.ru", "test3@mail.ru", null);
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToContactPage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}

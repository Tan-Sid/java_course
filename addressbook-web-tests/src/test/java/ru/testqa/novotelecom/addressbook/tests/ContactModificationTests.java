package ru.testqa.novotelecom.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.testqa.novotelecom.addressbook.model.ContactData;
import ru.testqa.novotelecom.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().contactPage();
    if (app.db().contacts().size() == 0){
      app.contact().create(new ContactData()
              .withFirstname("1Иван").withLastname("1Иванов").withAddress("ул.Мира 5")
              .withHomePhone("333-33-33").withMobilePhone("7-999(999-99-99)").withWorkPhone("4444")
              .withEmail1("test1@mail.ru").withEmail2("test2@mail.ru").withEmail3("test3@mail.ru")
              .withGroup("test1"));
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstname("1Иван").withLastname("1Иванов").withAddress("ул.Мира 5")
            .withHomePhone("333-33-33").withMobilePhone("7-999(999-99-99)").withWorkPhone("4444")
            .withEmail1("test1@mail.ru").withEmail2("test2@mail.ru").withEmail3("test3@mail.ru")
            .withGroup(null);
    app.contact().modify(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));
    verifyContactListInUI();
  }
}

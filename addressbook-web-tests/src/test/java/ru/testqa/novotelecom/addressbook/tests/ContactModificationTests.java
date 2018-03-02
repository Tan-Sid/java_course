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
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname("1Иван").withLastname("1Иванов").withAddress("ул.Мира 5").withTelephone("333-33-33").withEmail1("test1@mail.ru").withGroup("test1"));
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstname("1Иван").withLastname("1Иванов").withAddress("ул.Мира 5").withTelephone("333-33-33").withEmail1("test1@mail.ru").withGroup(null);
    app.contact().modify(contact);
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));
  }
}

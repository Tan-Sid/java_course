package ru.testqa.novotelecom.addressbook.tests;

import org.testng.annotations.Test;
import ru.testqa.novotelecom.addressbook.model.ContactData;
import ru.testqa.novotelecom.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() {
    Contacts before = app.contact().all();
    app.contact().createPage();
    ContactData contact = new ContactData()
            .withFirstname("1Иван").withLastname("1Иванов").withAddress("ул.Мира 5").withAddress2("ул.Ленина 1")
            .withHomePhone("333-33-33").withMobilePhone("7-999(999-99-99)").withWorkPhone("4444")
            .withEmail1("test1@mail.ru").withEmail2("test2@mail.ru").withEmail3("test3@mail.ru")
            .withGroup("test1");
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

  @Test
  public void testBadContactCreation() {
    Contacts before = app.contact().all();
    app.contact().createPage();
    ContactData contact = new ContactData()
            .withFirstname("1Иван'").withLastname("1Иванов").withAddress("ул.Мира 5").withAddress2("ул.Ленина 1")
            .withHomePhone("333-33-33").withMobilePhone("7-999(999-99-99)").withWorkPhone("4444")
            .withEmail1("test1@mail.ru").withEmail2("test2@mail.ru").withEmail3("test3@mail.ru")
            .withGroup("test1");
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));
  }


}

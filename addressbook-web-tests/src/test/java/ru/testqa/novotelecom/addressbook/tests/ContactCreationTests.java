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
            .withFirstname("1Иван").withLastname("1Иванов").withAddress("ул.Мира 5").withTelephone("333-33-33").withEmail1("test1@mail.ru").withGroup("test1");
    app.contact().create(contact);
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

}

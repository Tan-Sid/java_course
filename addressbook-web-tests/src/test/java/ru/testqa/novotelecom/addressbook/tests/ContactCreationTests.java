package ru.testqa.novotelecom.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.testqa.novotelecom.addressbook.model.ContactData;

import java.util.Set;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() {
    Set<ContactData> before = app.contact().all();
    app.contact().createPage();
    ContactData contact = new ContactData()
            .withFirstname("1Иван").withLastname("1Иванов").withAddress("ул.Мира 5").withTelephone("333-33-33").withEmail1("test1@mail.ru").withGroup("test1");
    app.contact().create(contact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);
  }

}

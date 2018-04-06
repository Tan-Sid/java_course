package ru.testqa.novotelecom.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.testqa.novotelecom.addressbook.model.ContactData;
import ru.testqa.novotelecom.addressbook.model.Contacts;
import ru.testqa.novotelecom.addressbook.model.GroupData;
import ru.testqa.novotelecom.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class ContactDeleteFromGroupTests extends TestBase {

  @BeforeMethod
  public void checkPreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData()
              .withName("test1")
              .withHeader("header")
              .withFooter("footer"));
    }
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname("1Иван").withLastname("1Иванов").withAddress("ул.Мира 5")
              .withHomePhone("333-33-33").withMobilePhone("79(999-999-999)").withWorkPhone("4444")
              .withEmail1("test1@mail.ru").withEmail2("test2@mail.ru").withEmail3("test3@mail.ru"));
    }

    app.goTo().contactPage();
  }

  @Test
  public void testContactDeleteFromGroup() {
    Contacts before = app.db().contacts();
    ContactData deleteContact = before.iterator().next();
    Groups group = app.db().groups();
    GroupData group1 = group.iterator().next();

    if (deleteContact.getGroups().isEmpty()) {
      ContactData addContact = before.iterator().next();
      app.contact().addInGroup(addContact);
    }

    app.contact().deleteFromGroup(deleteContact, group1);

    Contacts after = app.db().contacts();
    assertThat(app.contact().count(), equalTo(before.size()-1));
    assertThat(after, equalTo(before));
    verifyContactListInUI();
  }

}

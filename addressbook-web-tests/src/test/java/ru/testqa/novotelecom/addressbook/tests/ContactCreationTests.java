package ru.testqa.novotelecom.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.testqa.novotelecom.addressbook.model.ContactData;
import ru.testqa.novotelecom.addressbook.model.Contacts;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{

  @DataProvider
  public Iterator<Object[]> validContactsFromXml() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")));
    String line = reader.readLine();
    String xml = "";
    while (line != null){
      xml += line;
      line = reader.readLine();
    }
    XStream xStream = new XStream();
    xStream.processAnnotations(ContactData.class);
    List<ContactData> contacts = (List<ContactData>)xStream.fromXML(xml);
    return contacts.stream().map((c)-> new Object[]{c}).collect(Collectors.toList()).iterator();
  }

  @Test (dataProvider = "validContactsFromXml")
  public void testContactCreation(ContactData contact) {
      Contacts before = app.contact().all();
      app.contact().createPage();
      app.contact().create(contact);
      assertThat(app.contact().count(), equalTo(before.size() + 1));
      Contacts after = app.contact().all();
      assertThat(after, equalTo(
              before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

  @Test (enabled = false)
  public void testBadContactCreation() {
    Contacts before = app.contact().all();
    app.contact().createPage();
    ContactData contact = new ContactData()
            .withFirstname("1Иван'").withLastname("1Иванов").withAddress("ул.Мира 5")
            .withHomePhone("333-33-33").withMobilePhone("7-999(999-99-99)").withWorkPhone("4444")
            .withEmail1("test1@mail.ru").withEmail2("test2@mail.ru").withEmail3("test3@mail.ru")
            .withGroup("test1");
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));
  }


}

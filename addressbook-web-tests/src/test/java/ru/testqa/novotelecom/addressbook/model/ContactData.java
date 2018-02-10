package ru.testqa.novotelecom.addressbook.model;

public class ContactData {
  private final String firstname;
  private final String lastname;
  private final String address;
  private final String telephone;
  private final String email1;
  private final String email2;
  private final String email3;

  public ContactData(String firstname, String lastname, String address, String telephone, String email1, String email2, String email3) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
    this.telephone = telephone;
    this.email1 = email1;
    this.email2 = email2;
    this.email3 = email3;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getAddress() {
    return address;
  }

  public String getTelephone() {
    return telephone;
  }

  public String getEmail1() {
    return email1;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }
}

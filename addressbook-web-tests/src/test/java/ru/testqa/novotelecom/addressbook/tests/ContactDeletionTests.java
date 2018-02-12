package ru.testqa.novotelecom.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().goToContactPage();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().returnToContactPage();
    }

    @Test
    public void testContactDeletionAlert(){
        app.getNavigationHelper().goToContactPage();
        app.getContactHelper().clickElementForDelete();
        app.getContactHelper().clickButtonDelete();
        app.getContactHelper().returnToContactPage();
    }

}

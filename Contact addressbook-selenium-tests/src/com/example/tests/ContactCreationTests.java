package com.example.tests;


import java.util.regex.Pattern;
import org.junit.*;



public class ContactCreationTests extends TestBase  {
 
	@org.testng.annotations.Test
  public void testValidGroupCanBeCreated() throws Exception {
	  ContactObject validContact = new ContactObject("fdd", "4468", "454564564", "4546", "454646", "45456561", "fhgfd", "fdgfdfvdvfd", "1", "January", "4545", "gyfg", "hfuhdihdf", "fghdgfdgfd");
	  createContact(validContact);
  }
  @org.testng.annotations.Test
  public void testContactWithEmptyDataCanBeCreated() throws Exception {
	  ContactObject emptyContact = new ContactObject("", "", "", "", "", "", "", "", "-", "-", "", "", "", "");
	  createContact(emptyContact);
  }
}

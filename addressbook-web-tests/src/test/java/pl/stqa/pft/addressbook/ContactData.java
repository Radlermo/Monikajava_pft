package pl.stqa.pft.addressbook;

public class ContactData {
  private final String firstname;
  private final String lastname;
  private final String telephonehome;
  private final String email;

  public ContactData(String firstname, String lastname, String telephonehome, String email) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.telephonehome = telephonehome;
    this.email = email;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getTelephonehome() {
    return telephonehome;
  }

  public String getEmail() {
    return email;
  }
}

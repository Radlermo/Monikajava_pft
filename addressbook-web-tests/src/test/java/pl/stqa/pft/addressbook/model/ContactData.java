package pl.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "addressbook")
@XStreamAlias("contact")
public class ContactData {

  @Id
  @Column(name = "id")
  @XStreamOmitField
  private int id = Integer.MAX_VALUE;

  @Column(name="firstname")
  @Expose
  private String firstname;

  @Column(name="lastname")
  @Expose
  private String lastname;

  @Column(name="address")
  @Type(type = "text")
  @Expose
  private String address;

  @Column(name="home")
  @Type(type = "text")
  @Expose
  private String telephonehome;

  @Column(name="mobile")
  @Type(type = "text")
  @Expose
  private String mobile;

  @Column(name="work")
  @Type(type = "text")
  @Expose
  private String work;

  @Column(name="email")
  @Type(type = "text")
  @Expose
  private String email;

  @Column(name="email2")
  @Type(type = "text")
  @Expose
  private String email2;

  @Column(name="email3")
  @Type(type = "text")
  @Expose
  private String email3;



  @Transient
  private String allEmails;

  @ManyToMany(fetch = FetchType.EAGER)//eager - chciwy, dzięki temu wyciągamy więcej informacji z bazy danych za jednym razem
  @JoinTable(name = "address_in_groups",//tabela która wykorzystywana jest dla związku pomiędzy grupami a kontaktami
          joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))//id - id kontaktu, group_id - id grupy
  public Set<GroupData> groups = new HashSet<GroupData>();


  @Transient //@Transient żeby pole było pominięte i nie wyciągane z bazy danych
  private String allPhones;

  @Transient
  private String allDetails;

  @Column(name = "photo")
  @Type(type = "text")
  @Expose
  private String photo;

  public File getPhoto() {
    return new File(photo);
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public String getAllDetails() {
    return allDetails;
  }

  public ContactData withAllDetails(String allDetails) {
    this.allDetails = allDetails;
    return this;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
    }

  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withTelephonehome(String telephonehome) {
    this.telephonehome = telephonehome;
    return this;
  }

  public ContactData withMobilePhone(String mobile) {
    this.mobile = mobile;
    return this;
  }
  public ContactData withWorkPhone(String work) {
    this.work = work;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }
  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }
  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }
  public ContactData inGroup(GroupData group) {
    groups.add(group);
    return this;
  }


  public int getId() { return id; }

  public String getAllPhones() { return allPhones;  }

  public String getFirstname() {
    return firstname;
  }

  public String getAddress() {
    return address;
  }

  public String getLastname() {
    return lastname;
  }

  public String getTelephonehome() {
    return telephonehome;
  }

  public String getMobile() {
    return mobile;
  }

  public String getWork() {
    return work;
  }

  public String getEmail() {
    return email;
  }

  public String getEmail2() { return email2;  }

  public String getEmail3() { return email3;  }

  public String getAllEmails() { return allEmails;  }
  public Groups getGroups() {    return new Groups(groups);
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            ", address='" + address + '\'' +
            ", telephonehome='" + telephonehome + '\'' +
            ", email='" + email + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
    if (address != null ? !address.equals(that.address) : that.address != null) return false;
    if (telephonehome != null ? !telephonehome.equals(that.telephonehome) : that.telephonehome != null) return false;
    if (mobile != null ? !mobile.equals(that.mobile) : that.mobile != null) return false;
    if (work != null ? !work.equals(that.work) : that.work != null) return false;
    if (allEmails != null ? !allEmails.equals(that.allEmails) : that.allEmails != null) return false;
    if (allPhones != null ? !allPhones.equals(that.allPhones) : that.allPhones != null) return false;
    return photo != null ? photo.equals(that.photo) : that.photo == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    result = 31 * result + (address != null ? address.hashCode() : 0);
    result = 31 * result + (telephonehome != null ? telephonehome.hashCode() : 0);
    result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
    result = 31 * result + (work != null ? work.hashCode() : 0);
    result = 31 * result + (allEmails != null ? allEmails.hashCode() : 0);
    result = 31 * result + (allPhones != null ? allPhones.hashCode() : 0);
    result = 31 * result + (photo != null ? photo.hashCode() : 0);
    return result;
  }



}

package pl.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class Contacts extends ForwardingSet<ContactData> {

    private Set<ContactData> delegate; //delegujemy metode

    public Contacts(Contacts contacts) {
        this.delegate = new HashSet<ContactData>(contacts.delegate); // bierzemy zbiór z istniejącego objektu, który jest przekazywany jako parametr, budujemy nowy zbiór zawierający
        //te same elementy (new HashSet...) i przypisujemy go jako atrybut w objekt
    }
    //pusty konstruktor dla zbioru public Contacts all() z klasy Contact Helper
    public Contacts() {
        this.delegate = new HashSet<ContactData>();
    }
    @Override
    protected Set<ContactData> delegate() {
        return delegate;
    }
//dodajemy swoje metody które będą używane w testach
    //metoda na dodanie kontactu
    public Contacts withAdded(ContactData contact) {
        Contacts contacts = new Contacts(this); //buduje kopie istniejącego objektu
        contacts.add(contact);
        return contacts;
    }
    //metoda na usuwanie kontactu
    public Contacts without(ContactData contact) {
        Contacts contacts = new Contacts(this); //buduje kopie istniejącego objektu
        contacts.remove(contact);
        return contacts;
    }

}

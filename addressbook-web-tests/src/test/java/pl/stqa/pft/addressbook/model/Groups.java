package pl.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Groups extends ForwardingSet<GroupData> {

    private Set<GroupData> delegate;//delegujemy metode

    public Groups(Groups groups) {
        this.delegate = new HashSet<GroupData>(groups.delegate);// bierzemy zbiór z istniejącego objektu, który jest przekazywany jako parametr, budujemy nowy zbiór zawierający
        //te same elementy (new HashSet...) i przypisujemy go jako atrybut w objekt
    }

    //pusty konstruktor dla zbioru public Groups all() z klasy Group Helper
    public Groups() {
        this.delegate= new HashSet<GroupData>();
    }

    public Groups(Collection<GroupData> groups) {
        this.delegate = new HashSet<GroupData>(groups);
    }


    @Override
    protected Set<GroupData> delegate() {
        return delegate; //zwracamy metode delegate
    }
//dodajemy swoje metody które będą używane w testach
    //metoda na dodanie grupy
    public Groups withAdded(GroupData group) {
        Groups groups = new Groups(this);//buduje kopie istniejącego objektu
        groups.add(group);
        return groups;
    }
    //metoda na usunięcie grupy
    public Groups without(GroupData group) {
        Groups groups = new Groups(this);//buduje kopie istniejącego objektu
        groups.remove(group);
        return groups;
    }
}

package mainpack;

import java.util.ArrayList;
import java.util.Iterator;

public class PeopleDB implements Iterable<Person> {
    private ArrayList<Person> people = new ArrayList<>();

    public PeopleDB() {
    }

    public PeopleDB(ArrayList<Person> people) {
        this.people = people;
    }

    @Override
    public Iterator<Person> iterator() {
        return people.iterator();
    }

    public ArrayList<Person> filterByFullName(String fullName) {
        ArrayList<Person> filteredPeople = new ArrayList<>();

        for(Person p: people) {
            if(p.getFullName().equals(fullName))
                filteredPeople.add(p);
        }

        return filteredPeople;
    }

    public ArrayList<Person> filterBySurname(String surname) {
        ArrayList<Person> filteredPeople = new ArrayList<>();

        for(Person p: people) {
            if(p.getSurname().equals(surname))
                filteredPeople.add(p);
        }

        return filteredPeople;
    }

    public Person filterByPesel(String pesel) {
        for(Person p: people) {
            if(p.getPesel().equals(pesel))
                return p;
        }

        return null;
    }
}

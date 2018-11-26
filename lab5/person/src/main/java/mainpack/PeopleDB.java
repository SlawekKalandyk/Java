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

    public PeopleDB filterByName(String name) {
        PeopleDB filteredPeople = new PeopleDB();

        for(Person p: people) {
            if(p.getName().equals(name))
                filteredPeople.add(p);
        }

        return filteredPeople;
    }

    public PeopleDB filterBySurname(String surname) {
        PeopleDB filteredPeople = new PeopleDB();

        for(Person p: people) {
            if(p.getSurname().equals(surname))
                filteredPeople.add(p);
        }

        return filteredPeople;
    }

    public PeopleDB filterByPesel(String pesel) {
        PeopleDB singlePersonDB = new PeopleDB();
        for(Person p: people) {
            if(p.getPesel().equals(pesel)) {
                singlePersonDB.add(p);
                return singlePersonDB;
            }
        }

        return null;
    }

    public PeopleDB filterByPhone(String phone) {
        PeopleDB filteredPeople = new PeopleDB();

        for(Person p: people) {
            if(p.getPhone().equals(phone))
                filteredPeople.add(p);
        }

        return filteredPeople;
    }

    public void add(Person newPerson) {
        people.add(newPerson);
    }
}

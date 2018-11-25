package mainpack;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class PeopleDBTest {

    @Test
    public void canIterateOverPeopleDB() {
        ArrayList<Person> people = new ArrayList<>();
        String[] names = {"A B", "C D", "E F"};
        String[] phones = {"745 473 232", "923 823 273", "837 232 832"};
        String[] pesels = {"95030737584", "93850286638", "96523912351"};

        for(int i = 0; i < 3; ++i)
            people.add(new Person(names[i], phones[i], pesels[i]));

        PeopleDB peopleDB = new PeopleDB(people);

        int j = 0;
        for(Person p: peopleDB) {
            assertEquals(p.getFullName(), names[j]);
            assertEquals(p.getPhone(), phones[j]);
            assertEquals(p.getPesel(), pesels[j]);
            ++j;
        }
    }
}

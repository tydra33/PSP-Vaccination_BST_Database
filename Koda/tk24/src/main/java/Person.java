class PersonByName implements java.util.Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        String ime1 = p1.getSurname() + ", " + p1.getName();
        String ime2 = p2.getSurname() + ", " + p2.getName();
        return ime1.compareToIgnoreCase(ime2);
    }
}

class PersonById implements java.util.Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        String s1 = p1.getId();
        String s2 = p2.getId();
        return s1.compareToIgnoreCase(s2);
    }
}

public class Person implements java.io.Serializable {
    protected String id;
    protected String name;
    protected String surname;
    protected String age;
    protected String vaccine;

    public Person(String Id, String name, String surname, String age, String vaccine) {
        this.id = Id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.vaccine = vaccine;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return id + "-" + name + ", " + surname + "-" + age + "-" + vaccine;
    }
}

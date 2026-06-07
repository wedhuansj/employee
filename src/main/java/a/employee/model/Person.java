package a.employee.model;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Person {
    @Id
    protected String id;
    protected String name;
    protected int age;
    protected String gender;
    protected String address;
    public Person() {}
    public Person(String id, String name, int age, String gender, String address) {
        this.id = id;
        this.address = address;
        this.gender = gender;
        this.age = age;
        this.name = name;
    }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public abstract void displayInfo();
}

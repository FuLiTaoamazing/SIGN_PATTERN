package com.flt.builder;

public class Person {
    private int age;
    private String name;
    private int height;
    private double weight;
    private String idCard;

    private Person() {

    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", idCard='" + idCard + '\'' +
                '}';
    }

    public static class PersonBuilder {
        private Person person = new Person();

        public PersonBuilder basicInfo(int age, String name, int height) {
            person.age = age;
            person.name = name;
            person.height = height;
            return this;
        }

        public PersonBuilder builderWeight(double weight) {
            person.weight = weight;
            return this;
        }

        public PersonBuilder builderIdCard(String idCard) {
            person.idCard = idCard;
            return this;
        }

        public Person build() {
            return person;
        }
    }

    public static void main(String[] args) {
        Person person = new PersonBuilder().basicInfo(22, "flt", 180).builderIdCard("4000501041041").builderWeight(90.6).build();
        System.out.println(person);
    }

}

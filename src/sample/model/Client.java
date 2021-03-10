package sample.model;

import java.time.LocalDate;
import java.util.Date;

public class Client {

    private long id;

    private String lastName;

    private String name;

    private String surName;

    private Date dateOfBirth;

    private  int inn;

    private String familyStatus;

    private String typeOfEducation;

    private Boolean vip;

    public Client(String lastName, String name, String surName, Date dateOfBirth, int inn, String familyStatus, String typeOfEducation, Boolean vip) {
        this.lastName = lastName;
        this.name = name;
        this.surName = surName;
        this.dateOfBirth = dateOfBirth;
        this.inn = inn;
        this.familyStatus = familyStatus;
        this.typeOfEducation = typeOfEducation;
        this.vip = vip;
    }

    public Client(long id, String lastName, String name, String surName, Date dateOfBirth, int inn, String familyStatus, String typeOfEducation, Boolean vip) {
        this.id = id;
        this.lastName = lastName;
        this.name = name;
        this.surName = surName;
        this.dateOfBirth = dateOfBirth;
        this.inn = inn;
        this.familyStatus = familyStatus;
        this.typeOfEducation = typeOfEducation;
        this.vip = vip;
    }

    public long getId() {
        return id;
    }

    public Client() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getInn() {
        return inn;
    }

    public void setInn(int inn) {
        this.inn = inn;
    }

    public String getFamilyStatus() {
        return familyStatus;
    }

    public void setFamilyStatus(String familyStatus) {
        this.familyStatus = familyStatus;
    }

    public String getTypeOfEducation() {
        return typeOfEducation;
    }

    public void setTypeOfEducation(String typeOfEducation) {
        this.typeOfEducation = typeOfEducation;
    }

    public Boolean getVip() {
        return vip;
    }

    public void setVip(Boolean vip) {
        this.vip = vip;
    }

    @Override
    public String toString() {
        return "Client{" +
                "lastName='" + lastName + '\'' +
                ", name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", inn=" + inn +
                ", familyStatus='" + familyStatus + '\'' +
                ", typeOfEducation='" + typeOfEducation + '\'' +
                ", vip=" + vip +
                '}';
    }
}

/*
 * Copyright (c) 2020.    @author:KamalitaBiswas
 */

import java.util.Objects;

public class Member {
    Member(String mother,String name,String gender){
        setName(name);
        setMother(mother);
        setGender(gender);
    }
    Member(String mother,String name,String gender,boolean _isMarried,String spouse){
        setName(name);
        setMother(mother);
        setGender(gender);
        isMarried=_isMarried;
        if(isMarried) {
            setSpouse(spouse);
        }
    }
    public boolean isMarried() {
        return isMarried;
    }

    public void setMarried(boolean married) {
        isMarried = married;
    }

    public String getSpouse() {
        return spouse;
    }
    public void setSpouse(String spouse) {
        this.spouse = spouse;
    }

    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    private boolean isMarried;
    private String spouse;
    private String mother;
    private String name;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    private String gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return  name.equals(member.name) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

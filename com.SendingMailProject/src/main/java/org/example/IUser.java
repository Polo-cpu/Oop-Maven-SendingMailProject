package org.example;

public interface IUser {


    void add(String name, int number, int absenteeism,String databasename);
    int checkAbsenteeism(int number,String databasename);
    void delete(int number,String databasename);


}

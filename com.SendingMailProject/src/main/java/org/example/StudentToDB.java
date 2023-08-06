package org.example;

import com.SendMail.SendMail;

public class StudentToDB implements ICustomer{
    private final MyDb mydatabase = new MyDb("student_info");
    private static final SendMail mail = new SendMail(023200000,"sendToMail");
    private String name;
    private int number;
    private String student_class;
    private int absenteeism;



    public StudentToDB(String name, int number, String student_class, int absenteeism) {
        this.name = name;
        this.number = number;
        this.student_class = student_class;
        this.absenteeism = absenteeism;
    }
    public StudentToDB(){

    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public String getStudent_class() {
        return student_class;
    }

    public int getAbsenteeism() {
        return absenteeism;
    }


    @Override
    public void add() {
        mydatabase.add(getName(),getNumber(),getAbsenteeism(),"student_info");

    }

    @Override
    public boolean isAbsent(int number, int absenteeism) {
        if(mydatabase.updateAbsenteeism(number,absenteeism,"student_info")){
            return true;
        }
        mydatabase.closeConnection();
        return false;
    }

    @Override
    public void print() {
        mydatabase.printAllData("student_info");
        mydatabase.closeConnection();
    }

    @Override
    public void sendto() {
        mail.send(getNumber(),"sendtomail@hotmail.com");
    }

    public static void main(String[] args) {
        ICustomer student0 = new StudentToDB("ahmet",171,"11/A",0);
        ICustomer student1 = new StudentToDB("ayse",172,"11/A",0);
        ICustomer student2 = new StudentToDB("cenk",173,"11/A",0);
        student0.add();
        student1.add();
        student2.add();
    }
}

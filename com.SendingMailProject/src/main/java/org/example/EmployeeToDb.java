package org.example;

import com.SendMail.SendMail;

public class EmployeeToDb extends StudentToDB implements ICustomer{

    private final MyDb mydatabase = new MyDb("employee_info");
    private static final SendMail mail = new SendMail(023200000,"employeeEmail");
    public EmployeeToDb(String name, int number, String department, int absenteeism) {
        super(name, number, department, absenteeism);
    }
    public EmployeeToDb(){

    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public int getNumber() {
        return super.getNumber();
    }

    @Override
    public int getAbsenteeism() {
        return super.getAbsenteeism();
    }
    @Override
    public void add(){
        mydatabase.add(getName(),getNumber(),getAbsenteeism(),"employee_info");
    }

    @Override
    public boolean isAbsent(int number, int absenteeism) {
        if(mydatabase.updateAbsenteeism(number,absenteeism,"employee_info")){

            return true;
        }
        mydatabase.closeConnection();
        return false;
    }

    @Override
    public void print() {
        mydatabase.printAllData("employee_info");
        mydatabase.closeConnection();
    }

    @Override
    public void sendto() {

    }

    public static void main(String[] args) {
        ICustomer employee0 = new EmployeeToDb("ahmet",5,"Engineering",0);
        ICustomer employee1 = new EmployeeToDb("mahmut",6,"Engineering",0);
        ICustomer employee2 = new EmployeeToDb("can",7,"Engineering",0);
        employee0.add();
        employee1.add();
        employee2.add();
    }
}

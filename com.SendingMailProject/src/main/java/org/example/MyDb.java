package org.example;

import java.sql.*;

public class MyDb implements IUser {
    private Connection connection;
    private Statement statement;

    public MyDb(String databaseName) {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:"+databaseName+".db");
            statement = connection.createStatement();
            String createTableQuery = "CREATE TABLE IF NOT EXISTS " + databaseName + "(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "Name TEXT NOT NULL," +
                    "Number INT NOT NULL,"+
                    "Absenteeism INT NOT NULL)";
            statement.executeUpdate(createTableQuery);
            System.out.println("Table created successfully!");

        } catch (SQLException e) {
            System.err.println("Database Error : " + e.getMessage());
        }
    }

    public void add(String name, int number, int absenteeism,String databasename) {
        try {
            String insertQuery = "INSERT INTO "+ databasename +"(Name, Number, Absenteeism) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, number);
            preparedStatement.setInt(3, absenteeism);
            preparedStatement.executeUpdate();
            System.out.println("Successfully added");
        } catch (SQLException e) {
            System.err.println("AddError : " + e.getMessage());
        }
    }
    public void update(String newName, int newNumber, int absenteeism,String databasename) {
        try {
            String updateQuery = "UPDATE " + databasename + " SET Name = ?, Number = ? WHERE Absenteeism = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, newName);
            preparedStatement.setInt(2, newNumber);
            preparedStatement.setInt(3, absenteeism);
            preparedStatement.executeUpdate();
            System.out.println("Updated successfully ");
        } catch (SQLException e) {
            System.err.println("UpdateError: " + e.getMessage());
        }
    }
    @Override
    public int checkAbsenteeism(int number,String databasename){
        int absenteeism = 0;
        try {
            String myQuery = "SELECT * FROM " + databasename + " WHERE Number = 'number' ";
            ResultSet resultSet = statement.executeQuery(myQuery);
            int studentNum = resultSet.getInt("Number");
            int studentAbs = resultSet.getInt("Absenteeism");
            studentAbs = absenteeism;
        }catch(SQLException e){
            System.err.println("CheckError : " +e.getMessage());
        }
        return absenteeism;
    }
    public boolean updateAbsenteeism(int number, int absenteeism,String databasename){
        boolean flag = false;
        try {
            String updateQuery = "UPDATE " + databasename + " SET Absenteeism = ? WHERE number = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setInt(1, absenteeism);
            preparedStatement.setInt(1, number);
            preparedStatement.executeUpdate();
            System.out.println("absenteeism of student is successfully updated : Number - " + number);
            flag = true;
        } catch (SQLException e) {
            System.err.println("UpdateError :  " +e.getMessage());
            flag = false;
        }
        return flag;
    }
    @Override
    public void delete(int number,String databasename) {
        try {
            String deleteQuery = "DELETE FROM " + databasename + " WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, number);
            preparedStatement.executeUpdate();
            System.out.println("Deleted successfully: Number -" +number );
        } catch (SQLException e) {
            System.err.println("DeleteError : " + e.getMessage());
        }
    }
    public void printAllData(String databaseName) {
        try {
            String selectQuery = "SELECT * FROM " + databaseName;
            ResultSet resultSet = statement.executeQuery(selectQuery);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String names = resultSet.getString("Name");
                String numbers = resultSet.getString("Number");
                int absenteeisms = resultSet.getInt("Absenteeism");
                System.out.println("ID: " + id + ", Name: " + names + ", Number: " + numbers + ", Absenteeism : " + absenteeisms);
            }
        } catch (SQLException e) {
            System.err.println("PrintError: " + e.getMessage());
        }
    }

    public void closeConnection() {
        try {
            if (statement != null)
                statement.close();
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            System.err.println("CloseError: " + e.getMessage());
        }
    }
}



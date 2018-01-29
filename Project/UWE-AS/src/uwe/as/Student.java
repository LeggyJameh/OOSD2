/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uwe.as;

/**
 *
 * @author 
 */
public class Student extends User {
    private String idNumber;
    
    public void Student(String idNumber)
    {
        this.idNumber = idNumber;
    }
    
    public void createApplication()
    {
        
    }
    
    public String getStudentNumber()
    {
        return this.idNumber;
    }
    
    public void modifyStudentNumber(String newNumber)
    {
        this.idNumber = newNumber;
    }
}

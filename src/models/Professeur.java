/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import views.LoginSuccess;
import database.DB;

/**
 *
 * @author rachad
 */
public class Professeur extends Adherent {
    private String cin;
    private String matiere;
    

    public Professeur(String cin, String nom, String prenom, String matiere, int age,String pass) throws SQLException{
        
        super(nom,prenom,age,pass);
        super.setNombreEmpruntsMax(5);
        this.cin=cin;
        this.matiere=matiere;
        Connection db  = DB.getConnection();
        Statement stm = null;
        try {
            stm= (Statement) db.createStatement();
		} catch (NullPointerException e) {
            System.out.println("Null Pointer Exception"); 
		}
        String sql = "INSERT INTO PROFESSEUR(id,cin,matier) VALUES('";
        sql = sql + + this.getId() + "','" + cin + "','" + matiere + "');";
        System.out.println(sql);
        LoginSuccess.success(this);
        int rs = stm.executeUpdate(sql);
        
        super.setNombreEmpruntsMax(5);
    }
    
    public Professeur(int id,String cin, String nom, String prenom, String matiere, int age,String pass,boolean a) throws SQLException{
        
    	super(nom,prenom,age,pass,id,false);
        super.setNombreEmpruntsMax(5);
        this.cin=cin;
        this.matiere=matiere;
    }
   
    public String getCin() {
        return cin;
    }
    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    } 
   
}

/*public String toString(){
return "Le professeur"+super.toString()+
        " il  enseigne la matiere "+ 
        matiere+" et possede le cin "+ cin;
}*/
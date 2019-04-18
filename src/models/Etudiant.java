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

public class Etudiant extends Adherent {
    private String cne;
    private String filier;
    
    public Etudiant(String cne, String nom,String prenom,String filier,int age,String password) throws SQLException{
        super(nom,prenom,age,password);
        this.cne=cne;
        this.filier=filier;
        Connection db  = DB.getConnection();
        Statement stm = null;
        try {
            stm= (Statement) db.createStatement();
		} catch (NullPointerException e) {
            System.out.println("Null Pointer Exception"); 
		}
        String sql = "INSERT INTO ETUDIANT(id_etu,cne,filier) VALUES('";
        sql = sql + + this.getId() + "','" + cne + "','" + filier + "');";
        System.out.println(sql);
        LoginSuccess.success(this);
        int rs = stm.executeUpdate(sql);
        
        super.setNombreEmpruntsMax(5);
    }
    
    public Etudiant(String cne, String nom,String prenom,String filier,int age,String password,int id,boolean b){
        super(nom,prenom,age,password,id,false);
        this.cne=cne;
        this.filier=filier;
    }

    public String getCne() {
        return cne;
    }
    public String getFiliere() {
        return filier;
    }
    public void setFiliere(String filier) {
        this.filier = filier;
    }
    
    /*
     *     public String toString(){
        return "l'edutiant "+super.toString()+
                " qui appartient a la filiere "+ filiere+
                " a le cne "+ cne;
    }
     * */
    
}

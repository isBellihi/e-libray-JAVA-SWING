/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;

/**
 *
 * @author rachad
 */
public class Magazine extends Document{
    private String periodicite;
    final private Date dt;    
    
    public Magazine(String titre,String[] auteur, String editeur,int edition,
            int nombreExemplaires, String isbn, String periodicite, Date dt){
        super( titre,auteur,editeur,edition, nombreExemplaires,  isbn);
        this.periodicite=periodicite;
        this.dt=dt;
    }
    
    public String toString(){
       return "La magazine possede les informations suivantes"+
                super.toString()+"\n"+
                "La periodecite="+periodicite+"\n"+
                "Le date d'edition="+dt+"\n";
           
    }

    public String getPeriodicite() {
        return periodicite;
    }

    public Date getDt() {
        return dt;
    }


    public void setPeriodicite(String periodicite) {
        this.periodicite = periodicite;
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author rachad
 */
public class Document {
    final private String titre;
    final private String auteur;
    final private String editeur;
    final private int edition;
    private int nombreExemplaires;
    final private String isbn;
    
      Document(String titre,String auteur, String editeur,int edition,
            int nombreExemplaires, String isbn ){
        this.titre=titre;
        this.auteur=auteur;
        this.editeur=editeur;
        this.nombreExemplaires=nombreExemplaires;
        this.isbn=isbn;
        this.edition=edition;  
    }
    /*public String toString(){
        String auteurListe="";
        for(int i=0;i<auteur.length;i++){
            if (auteur[i]!=null)
                    auteurListe=auteurListe+" "+auteur[i];
        }
                
        return "isbn="+isbn+"\n"+
                " titre="+titre+"\n"+
                " auteur="+auteurListe+"\n"+
                " editeur="+editeur+"\n"+
                " edition="+edition+"\n"+
                " nombre d'exemplaires="+nombreExemplaires;   
    }*/
    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public String getEditeur() {
        return editeur;
    }

    public int getEdition() {
        return edition;
    }

    public int getNombreExemplaires() {
        return nombreExemplaires;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setNombreExemplaires(int nombreExemplaires) {
        this.nombreExemplaires = nombreExemplaires;
    }
    
    public void AjouterExemplaires(int n){
        nombreExemplaires=nombreExemplaires+n;
    }
    
    public void SupprimerExemplaires(int n){
         if (nombreExemplaires>=n)
             nombreExemplaires=nombreExemplaires-n;
    }
    
     public void finalize()
     {
          System.out.println("Objet nettoyé de la mémoire");   
     }
    
    
}

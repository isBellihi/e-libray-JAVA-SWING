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
public class Livre extends Document {
    
    final private int nombreChapitres;
    final private int nombrePages;
    final private String langue;
    final private int tome;
    final private String type;
    public Livre(String titre, String[] auteur, String editeur, 
            int edition, int nombreExemplaires, String isbn,
            int nombreChapitres, int nombrePages, String langue, int tome, String type) {
        
        super(titre, auteur, editeur, edition, nombreExemplaires, isbn);
        this.nombreChapitres=nombreChapitres;
        this.nombrePages=nombrePages;
        this.langue=langue;   
        this.tome=tome;
        this.type=type;
    }
    public String toString(){
        return "Le livre possede les informations suivantes"+
                super.toString()+"\n"+
                "nombre de chapitres="+nombreChapitres+"\n"+
                "nombre de pages="+nombrePages+"\n"+
                 "langue="+langue+"\n"+
                "le tome="+tome+"\n"+
                "et le type="+type+"\n";
    } 
    public int getNombreChapitres() {
        return nombreChapitres;
    }
    public int getNombrePages() {
        return nombrePages;
    }
    public String getLangue() {
        return langue;
    }
    public int getTome() {
        return tome;
    }
    public String getType() {
        return type;
    } 
   
    public static void main(String[] args){
        String[] auteurs={"auteur1"};
        Livre l=new Livre("Programamtion java",auteurs, "eyrolles", 2009, 10,"kljbcs00080",12, 200, "fr", 1, "science");
        
        System.out.println(l);
        
    }
    
}

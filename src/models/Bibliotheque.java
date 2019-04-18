package models;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rachad
 */
public class Bibliotheque {
    
    ArrayList<Adherent> ListeAdherent;
    ArrayList<Document> ListeDocument;
    
    public Bibliotheque(){
    	ListeAdherent = new ArrayList();
    }
    
    public String toString(){
       String ch="";
       ch=ch+"**************************************\n"  ;       
       ch=ch+"********  Liste Des Documents ********\n"  ;  
       ch=ch+"**************************************\n"  ;
       for(int i=0;i<ListeDocument.size();i++){
           ch=ch+"\t Document "+i+":\n";
           ch=ch+"\t"+ListeDocument.get(i).toString();}
       ch=ch+"\n**************************************\n";       
       ch=ch+"********  Liste Des Adherents ********\n"  ;  
       ch=ch+"**************************************\n"  ;
       for(int i=0;i<ListeAdherent.size();i++){
           ch=ch+"\t Adherent "+i+":\n";
           ch=ch+"\t"+ListeAdherent.get(i).toString();
       } 
       return ch;
    }
   
    Document[] getDocumentByEditeur(String editeur){
        //Compter le nombre des documents de l'editeur
        int n=0;
        for(int i=0;i<ListeDocument.size();i++){
          if(ListeDocument.get(i).getEditeur().equals(editeur)){
              n++; }
        }     
        //retourner les documents de l'editeur
        Document[] td=new  Document[n];
        int j=0;
        for(int i=0;i<ListeDocument.size();i++){
          if(ListeDocument.get(i).getEditeur().equals(editeur)){
              td[j]=ListeDocument.get(i);
              j++;  
          }
        }
        return td;
    }
    Document[] getDocumentByAuteur(String auteur){
           //Compter le nombre des documents de l'auteur
        int n=0;
        for(int i=0;i<ListeDocument.size();i++){
            for(int j=0;j<ListeDocument.get(i).getAuteur().length;j++)
                 if(ListeDocument.get(i).getAuteur()[j].equals(auteur)){
                      n++; }
        }     
        //retourner les documents de l'auteur
        Document[] td=new  Document[n];
        int k=0;
        for(int i=0;i<ListeDocument.size();i++){
            for(int j=0;j<ListeDocument.get(i).getAuteur().length;j++)
                 if(ListeDocument.get(i).getAuteur()[j].equals(auteur)){
                        td[k]=ListeDocument.get(i);
                                 k++;  
          }
        }
        return td; 
    }
    int getDocumentByIsbn(String isbn){
        for(int i=0;i<ListeDocument.size();i++){
          if(ListeDocument.get(i).getIsbn().equals(isbn)){
              return i;}
        }
        return -1;
    }
    int getDocumentByTitre(String titre){
        for(int i=0;i<ListeDocument.size();i++){
          if(ListeDocument.get(i).getTitre().equals(titre)){
              return i;}
        }
        return -1;    
    }
    
    int  getEtudiantByCne(String cne){
      
        for(int i=0;i<ListeAdherent.size();i++){
          if( ListeAdherent.get(i) instanceof Etudiant &&  ((Etudiant)ListeAdherent.get(i)).getCne().equals(cne))
              return i;
        }
        return -1; 
    }
     public int getProfesseurByCin(String cin){
        for(int i=0;i<ListeAdherent.size();i++){
          if( ListeAdherent.get(i) instanceof Professeur &&  ((Professeur)ListeAdherent.get(i)).getCin().equals(cin))
              return i;
        }
        return -1; 
    }
     
    boolean SupprimerDocument(String titre) {
        if (getDocumentByTitre( titre)!=-1){
            for(int i=getDocumentByTitre( titre);i<ListeDocument.size();i++){
                //ListeDocument.get(i) = ListeDocument.get(i+1);
            	ListeDocument.remove(i);
            }
            return true;
        }
        else
            return false;
    }
    boolean SupprimerEtudiant(String cne) {
   if (getEtudiantByCne( cne)!=-1){
            for(int i=getEtudiantByCne(cne);i<ListeAdherent.size();i++){
                ListeAdherent.remove(i);
            }
            return true;
        }
        else
            return false;
    }
    boolean SupprimerProfesseur(String cin) {
      if (getProfesseurByCin(cin)!=-1){
            for(int i=getProfesseurByCin(cin);i<ListeAdherent.size();i++){
                ListeAdherent.remove(i);
            }
            return true;
        }
        else
            return false;
    }
    
    boolean  AjouterDocument(Document d){
        if (getDocumentByIsbn(d.getIsbn())!=-1){
            ListeDocument.get(getDocumentByIsbn(d.getIsbn())).AjouterExemplaires(1);
            return true;
        }
        else{
        	ListeDocument.add(d);
        	return true;
        }      
    }
    
    public boolean AjouterAdherent(Adherent a){;
        if( (a instanceof Etudiant) && getEtudiantByCne(((Etudiant)a).getCne())==-1){
                ListeAdherent.add((Etudiant)a);
                return true;
        }
        else {
        	if ( (a instanceof Professeur) && getProfesseurByCin(((Professeur)a).getCin())==-1){
                ListeAdherent.add((Professeur)a);
                return true;
        }else
            return false;
        }
            
    }

	Scanner sc = new Scanner(System.in);

    
	public boolean inscription(){
		int c;
	    int nombreEmpruntsMax = 4;
	    int nombreEmprunts = 2;
	    String nom = "ISS",prenom="BELL";
	    int age=20;
	    char[] password = generePassword();
	    
		do {
			System.out.println("Vous voulez inscrire comme etudiant ou bien comme professeur :");
			System.out.println("Etudiant taper 0 et pour professeur taper 1.");
			c = sc.nextInt();
		} while (c!=0 && c!=1);
		
		if(c==1){ //Professeur
		     String cin = "J342311";
		     String matiere = "POO";
		     Professeur p = new Professeur(cin, nom, prenom, matiere, age);
		     p.setPassword(password);
			System.out.println("Merci pour l'inscription : votre mot de pass est :" + p.getPassword());
			System.out.println(p);
		     return this.AjouterAdherent(p);
		     
		}else{
		     String cne = "1312342311";
		     String filiere = "IWIM";
		     Etudiant etu = new Etudiant(cne, nom, prenom, filiere, age);
		     etu.setPassword(password);
			
			System.out.print("Merci pour l'inscription : votre mot de pass est :");
			for (int i = 0; i < etu.getPassword().length; i++) {
				System.out.println(etu.getPassword()[i]);
			}
		     return this.AjouterAdherent(etu);
		}
	} 
	private char[] generePassword(){
		char[] pass = new char[8];
		Random r = new Random();
		for(int i=0;i<8;i++){
			do{
				pass[i] = (char)r.nextInt(120);
			}while(pass[i]<=70 || pass[i]>=100);
			System.out.print(pass[i]);
		}
		System.out.println();
		return pass;
	}
	
	/******************************************************************/
	public Adherent Authentifier(){
		//System.out.println("Saisir votre CIN");

		int c = 2;
		String id;
		String pass;
		int pos;
		char[] pp = new char[8];

		do{
			System.out.println("Vous voulez se connecter comme etudiant ou bien comme professeur :");
			System.out.println("Etudiant taper 0 et pour professeur taper 1.");
			c = sc.nextInt();
			//c=1;
		}while(c!=0 && c!=1);
		if(c==1){
			System.out.println("Taper votre CIN : ");
			sc.next();
			id=sc.nextLine();
			System.out.println("Taper votre mot de pass : ");
			pass=sc.nextLine();
			pos = getProfesseurByCin(id);
			
		if(pos==-1 || !(ListeAdherent.get(pos).getPassword().equals(pass))){
			System.out.println("le CIN ou bien le mot de pass est incorrect");
			
			return null;
		}else{
			Professeur p = (Professeur)ListeAdherent.get(pos);
			System.out.println("vous ete mainteanat connecté.");
			
			return p;
		}
		}else{
			System.out.println("Taper votre CNE : ");
			id=sc.nextLine();
			System.out.println("Taper votre mot de pass : ");
			//pass=sc.nextLine();
			for (int i = 0; i < 8; i++) {
				pp[i] = (char)sc.nextByte();
			}
			pos = getEtudiantByCne(id);
		if(pos==-1 || !ListeAdherent.get(pos).getPassword().equals(pp)){
			
			System.out.println("le CIN ou bien le mot de pass est incorrect");
			return null;
		}else{
			Etudiant etu = (Etudiant)ListeAdherent.get(pos);
			
			System.out.println("vous ete mainteanat connecté.");
			return etu;
		}
		}
	}
}

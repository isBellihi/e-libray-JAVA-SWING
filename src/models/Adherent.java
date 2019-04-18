/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.DB;


/**
 *
 * @author rachad
 */
public class Adherent implements Emprunter {
   
    final private int id;
    final private String nom;
    final private String prenom;
    final private int age;
    private int nombreEmpruntsMax;
    private  int nombreEmprunts;
    private String password = "";
    
    public Adherent(String nom,String prenom,int age,String password,int id,boolean b){
        this.id = id;
    	this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.age = age;
         nombreEmpruntsMax=5;
         nombreEmprunts=0;
    }
    
    public Adherent(String nom,String prenom,int age,String password) throws SQLException{
       this.nom = nom;
       this.prenom = prenom;
       this.password = password;
       this.age = age;
        nombreEmpruntsMax=5;
        nombreEmprunts=0;
        Connection db  = DB.getConnection();
        Statement stm = null;
        try {
            stm= (Statement) db.createStatement();
		} catch (NullPointerException e) {
            System.out.println("Null Pointer Exception"); 
		}
        String sql = "INSERT INTO ADHERENT(nom,prenom,age,nbrEmpruntMax,nbrEmprunt,password) VALUES('";
        sql = sql + nom + "','" + prenom + "','" + age + "'," + 5 +"," + 0 + ",'" + password + "');";
        String getId = "SELECT MAX(ID) FROM ADHERENT";
        System.out.println(sql);
        stm.executeUpdate(sql);
        stm.close();
        stm = (Statement)db.createStatement();
        ResultSet res =  stm.executeQuery(getId);
        int n = 0;
        while (res.next()) {
			n = res.getInt(1);
		}
        this.id = n;
        System.out.println("id de cet adherent : " + this.id);
        stm.close();
        }
 
    
    Document getDocumentByIsbn(String isbn) throws SQLException{
    	Connection db = DB.getConnection();
    	Statement st = (Statement)db.createStatement();
    	String str = "select * from document";
    	ResultSet res = st.executeQuery(str);
    	while (res.next()) {
			if(res.getString(7).equals(isbn)){
				Document doc = new Document(res.getString(2), res.getString(3), res.getString(4), res.getInt(5), res.getInt(6), isbn);
				return doc;
			}
		}
    	return null;
    }
    
    //return id de l'adherent qui emprunter le documenet de isbn = isbn passer en parametre
    boolean getEmprunt(String isbn) throws SQLException{
    	Connection db = DB.getConnection();
    	Statement st = (Statement)db.createStatement();
    	String str =null;
    	st = (Statement)db.createStatement();
    	str = "select * from emprunte";
    	ResultSet res = st.executeQuery(str);
    	while (res.next()) {
			if(res.getString(1).equals(isbn) && res.getInt(2) == this.id){
				return true;
			}
		}
    	return false;
    }
    
    public boolean emprunter(String isbn) throws SQLException{
    	Connection db = DB.getConnection();
    	Statement st = (Statement)db.createStatement();
    	String str = "select count(*),nbrExem from emprunte,document where emprunte.isbn = document.isbn and emprunte.isbn = '" + isbn + "'";
    	str += " group by 'empruntre.isbn';";
    	ResultSet res = st.executeQuery(str);
    	int a=1;
    	while(res.next()){
    		if(res.getInt(1) == res.getInt(2)) a=0;
    	}
    	if(a==0){
    		System.out.println("ce document n'est plus disponible");
    		return false;
    	}
    	st.close();
    if (this.nombreEmpruntsMax>this.nombreEmprunts){
    	if(getDocumentByIsbn(isbn)!=null){
    		db = DB.getConnection();
            String up = "insert into emprunte values('" + isbn + "' , " + this.id + ");" ;
            st = (Statement)db.createStatement();
            try {
            	st.executeUpdate(up);
			} catch (SQLException e) {
				// TODO: handle exception
            	System.out.println("Vous avez deja emprunter ce Document");
            	return false;
			}
            st.close();
        	System.out.println("update success 'emprunter'");
            this.nombreEmprunts++;
            //UPDATE adherent SET nbrEmpruntMax = nbrEmpruntMax + 1 WHERE ID =1
            up = "UPDATE ADHERENT SET nbremprunt = nbremprunt + 1 where id =" + this.id;
            st = (Statement)db.createStatement();
            st.executeUpdate(up);
            st.close();
            return true;
    	}else{
    		System.out.println("pas de document de ce isbn");
    	}
    }
    System.out.println("update faild 'emprunter'");
    return false;
    }
    
    public boolean rendre(String isbn) throws SQLException{
    if (this.nombreEmprunts>0){
    	if(getEmprunt(isbn)){
        	System.out.println("update success 'rendre'");
            this.nombreEmprunts--;
            String up = "update ADHERENT SET nbremprunt = nbremprunt - 1 where id =" + this.id;
            Connection db = DB.getConnection();
            Statement st = (Statement)db.createStatement();
            st.executeUpdate(up);
            st.close();
            up = "delete from emprunte where isbn = '" + isbn + "';" ;
            st = (Statement)db.createStatement();
            st.executeUpdate(up);
            st.close();
            return true;
    	}else{
    		System.out.println("pas d'emprunt de ce isbn");
    	}
    }
    System.out.println("update faild 'rendre'");
    return false;
    }
    
    public int getId(){
    	return id;
    }
    
    public String toString(){
        return  super.toString()+" a effectue "+nombreEmprunts+
                " emprunts"; 
    }

    
    
    public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getNombreEmpruntsMax() {
        return nombreEmpruntsMax;
    }

    public int getNombreEmprunts() {
        return nombreEmprunts;
    }

    public void setNombreEmpruntsMax(int nombreEmpruntsMax) {
        this.nombreEmpruntsMax = nombreEmpruntsMax;
    }
    


	public String getNom() {
		return nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public int getAge() {
		return age;
	}


	public void setNombreEmprunts(int nombreEmprunts) {
		this.nombreEmprunts = nombreEmprunts;
	}
    
    
}

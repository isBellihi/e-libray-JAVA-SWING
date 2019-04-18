package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import models.Etudiant;
import database.DB;

public class EtudiantLogin extends JFrame{

	private JLabel contentPane;
	
	private EtudiantLogin(){
		setTitle("Application Bibiotheque");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		//nouveau();
		login();
	}
	
	private EtudiantLogin(int n){
		setTitle("Application Bibiotheque");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		nouveau();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			EtudiantLogin form = new EtudiantLogin();
			form.setVisible(true);
	}
	
	public static void main1(){
		int a = 1;
		EtudiantLogin form = new EtudiantLogin(a);
		form.setVisible(true);	
	}
	
	private void login(){
		//contentPane = new JPanel();
		contentPane = new JLabel(new ImageIcon("F:\\Eclipse\\ENSIAS\\images\\bibliotheque2.jpg"));
	    //contentPane.setBackground(Color.ORANGE);        
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		//this.setLayout(null);
		contentPane.setLayout(null);
		
		JLabel bienvenue = new JLabel("Bienvenue au Bibiotheque électronique : ");
		bienvenue.setFont(new Font("Tahoma", Font.BOLD, 22));
		bienvenue.setForeground(Color.RED);
		bienvenue.setBounds(10, 10, 550, 50);
		contentPane.add(bienvenue);
		
		JLabel cne = new JLabel("CNE  : ");
		cne.setFont(new Font("Tahoma", Font.BOLD, 22));
		cne.setForeground(Color.WHITE);
		JTextField textCNE = new JTextField();
		cne.setBounds(90, 100, 150, 50);
		textCNE.setBounds(260,100,150,40);
		contentPane.add(cne); contentPane.add(textCNE);

		JLabel pass = new JLabel("Password : ");
		pass.setFont(new Font("Tahoma", Font.BOLD, 22));
		pass.setForeground(Color.WHITE);
		pass.setBounds(90,170,150,50);
		JTextField passText = new JTextField();
		passText.setBounds(260,170,150,40);
		contentPane.add(pass);contentPane.add(passText);
		
		JButton log = new JButton("Login");
		log.setBounds(260,250,100,40);
		contentPane.add(log);
		
		JButton nouveau = new JButton("Nouveau");
		nouveau.setBounds(370,250,100,40);
		contentPane.add(nouveau);
		
		nouveau.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//dispose();
				String[] str = new String[1];
				str[0] = "nouveau";
				dispose();
				EtudiantLogin.main1();
			}
		});
		
		log.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
		        Connection db  = DB.getConnection();
		        Statement stm = null;
		        try {
		            stm= (Statement) db.createStatement();
			        String getId = "SELECT cne,password FROM view1";
			        stm = (Statement)db.createStatement();
			        ResultSet res =  stm.executeQuery(getId);
			        int n = 0;
			        while (res.next()) {
						if(res.getString(1).equals(textCNE.getText()) && res.getString(2).equals(passText.getText())){
							String sql = "SELECT CNE,NOM,PRENOM,FILIER,AGE,PASSWORD,ID,nbrEmpruntMax,nbrEmprunt FROM VIEW1 where CNE =  '" + textCNE.getText().toString();
							sql = sql + "' and password = '" + passText.getText() + "';" ;
							//String sql = "SELECT CNE,NOM,PRENOM,FILIER,AGE,PASSWORD FROM VIEW1 where CNE = 1412021379 and PASSWORD = password";
							Connection d = DB.getConnection();
							Statement st = d.createStatement();
							ResultSet rs = st.executeQuery(sql);
							//Etudiant(String cne, String nom,String prenom,String filier,int age,String password)
							System.out.println("okkk");
							rs.next();
							System.out.println(rs.getString(1) + " " + rs.getString(6));
								Etudiant etu = new Etudiant(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)
										, rs.getInt(5), rs.getString(6),rs.getInt(7),false);
								etu.setNombreEmprunts(rs.getInt(9));
								etu.setNombreEmpruntsMax(rs.getInt(8));
								dispose();
								System.out.println("appel 1ogin");
								LoginSuccess.success(etu);
								break;
						}
					}
			        stm.close();
				} catch (SQLException e) {
		            System.out.println("Null Pointer Exception"); 
				}
			}
		});
		
		
	}
	
	private void nouveau(){
		contentPane = new JLabel(new ImageIcon("F:\\Eclipse\\ENSIAS\\ProjetBiblio\\src\\images\\bibliotheque2.jpg"));
	    //contentPane.setBackground(Color.ORANGE);        
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel bienvenue = new JLabel("Bienvenue au Bibiotheque électronique : ");
		bienvenue.setFont(new Font("Tahoma", Font.PLAIN, 22));
		bienvenue.setForeground(Color.RED);
		bienvenue.setBounds(10, 10, 550, 50);
		contentPane.add(bienvenue);
		
		JLabel nom = new JLabel("Nom :");
		JTextField textNom = new JTextField();
		nom.setBounds(50, 90, 70, 50);
		textNom.setBounds(120,90,150,40);
		contentPane.add(nom);contentPane.add(textNom);
		
		JLabel prenom = new JLabel("Prenom :");
		JTextField textPrenom = new JTextField();
		prenom.setBounds(310, 90, 70, 50);
		textPrenom.setBounds(380,90,150,40);
		contentPane.add(prenom);contentPane.add(textPrenom);
		
		JTextField age = new JTextField(100);
		JLabel textAge = new JLabel("age : ");
		textAge.setBounds(50, 200, 70, 50);
		age.setBounds(120,200,150,40);
		contentPane.add(age);contentPane.add(textAge);
		
		JLabel filier = new JLabel("Nom :");
        String[] filiers = { "GL", "IWIM", "SSI", "EL","ISEM" };
        JComboBox<String> filierList = new JComboBox<String>(filiers);
        filierList.setVisible(true);
        filier.setBounds(310, 200, 70, 50);
        filierList.setBounds(380, 200, 150, 40);
        contentPane.add(filierList);contentPane.add(filier);

		JTextField cne = new JTextField(100);
		JLabel l1 = new JLabel("CNE : ");
		l1.setBounds(50, 310, 100, 50);
		cne.setBounds(120, 310, 150, 40);
		contentPane.add(cne);contentPane.add(l1);
		
		JTextField pass = new JTextField(8);
		JLabel l2 = new JLabel("Password :");
		l2.setBounds(310, 310, 100, 50);
		pass.setBounds(390, 310, 140, 40);
		contentPane.add(l2);contentPane.add(pass);
		
		JButton submit = new JButton("Submit");
		submit.setFont(new Font("Tahoma", Font.PLAIN, 22));
		submit.setForeground(Color.GREEN);
		submit.setBounds(200, 380, 160, 40);
		contentPane.add(submit);

		
		//contentPane.add(l1);contentPane.add(l2);
		//contentPane.add(cne);
		//contentPane.add(pass);contentPane.add(submit);
		
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					  try  
					  {  
							Etudiant etu = new Etudiant(cne.getText(),
									textNom.getText(),textPrenom.getText(),
									filierList.getSelectedItem().toString(),
									Integer.parseInt(age.getText()),pass.getText());
							dispose();
							System.out.println("appel inscrire");
							//LoginSuccess.success(etu);
					  }catch(NumberFormatException nfe){  
					    System.out.println("erreur");  
					  }  
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
	}

}

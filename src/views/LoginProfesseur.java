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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import models.Professeur;
import database.DB;

public class LoginProfesseur extends JFrame{

	private static JLabel contentPane;
	
	private LoginProfesseur(){
		setTitle("Application Bibiotheque");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		intWindow();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LoginProfesseur form = new LoginProfesseur();
		form.setVisible(true);

	}
	
	private void intWindow(){
		JLabel contentPane = new JLabel(new ImageIcon("F:\\Eclipse\\ENSIAS\\images\\admin.jpg"));
	    //contentPane.setBackground(Color.ORANGE);        
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		JLabel bienvenue = new JLabel("Bienvenue au Bibiotheque électronique : ");
		bienvenue.setFont(new Font("Tahoma", Font.BOLD, 18));
		bienvenue.setForeground(Color.RED);
		
		JTextField cin = new JTextField(100);
		JLabel l1 = new JLabel("CIN : ");
		l1.setFont(new Font("Tahoma", Font.BOLD, 18));
		l1.setForeground(Color.GREEN);
		//l1.setBounds(150, 100, 200, 50);
		
		JTextField pass = new JTextField(8);
		JLabel l2 = new JLabel("mot de pass : ");
		l2.setFont(new Font("Tahoma", Font.BOLD, 18));
		l2.setForeground(Color.GREEN);
		l1.setBounds(100, 100, 200, 50);
		l2.setBounds(100, 180, 200, 50);

		
		JButton submit = new JButton("Submit");
		submit.setFont(new Font("Tahoma", Font.PLAIN, 22));
		submit.setForeground(Color.GREEN);

		
		contentPane.setLayout(null);
		bienvenue.setBounds(10, 10, 550, 50);
		cin.setBounds(300,100,160,40);
		
		pass.setBounds(300, 180, 160, 40);
		submit.setBounds(200, 270, 160, 40);
		contentPane.add(l1);contentPane.add(l2);
		contentPane.add(bienvenue);contentPane.add(cin);
		contentPane.add(pass);contentPane.add(submit);
		
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
			        Connection db  = DB.getConnection();
			        Statement stm = null;
			        try {
			            stm= (Statement) db.createStatement();
				        String getId = "SELECT cin,password FROM view2";
				        stm = (Statement)db.createStatement();
				        ResultSet res =  stm.executeQuery(getId);
				        int n = 0;
				        while (res.next()) {
							if(res.getString(1).equals(cin.getText()) && res.getString(2).equals(pass.getText())){
								String sql = "SELECT CIN,NOM,PRENOM,AGE,PASSWORD,ID,nbrEmpruntMax,nbrEmprunt,matier FROM VIEW2 where CNE =  '" + cin.getText().toString();
								sql = sql + "' and password = '" + pass.getText() + "';" ;
								//String sql = "SELECT CNE,NOM,PRENOM,FILIER,AGE,PASSWORD FROM VIEW1 where CNE = 1412021379 and PASSWORD = password";
								Connection d = DB.getConnection();
								Statement st = d.createStatement();
								ResultSet rs = st.executeQuery(sql);
								//Etudiant(String cne, String nom,String prenom,String filier,int age,String password)
								rs.next();
								System.out.println(rs.getString(1) + " " + rs.getString(6));
								//String cin, String nom, String prenom, String matiere, int age,String pass,boolean a
									Professeur etu = new Professeur(rs.getInt("id"),rs.getString("cin"), rs.getString("nom"), rs.getString("prenom"), rs.getString("matier")
											, rs.getInt("age"), rs.getString("password"),false);
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

}

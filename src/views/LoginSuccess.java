package views;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import models.Adherent;
import models.Etudiant;

public class LoginSuccess {
	private JFrame success ;
	private Etudiant etu;
	
	private LoginSuccess(Adherent etu){
		success = new JFrame();
		JLabel contentPane;
		success.setTitle("Application Bibliotheque/compte Etudiant");
		success.setLocationRelativeTo(null);
		success.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		success.setBounds(100, 100, 600, 500);
		//contentPane = new JPanel();
		contentPane = new JLabel(new ImageIcon("F:\\Eclipse\\ENSIAS\\images\\bibliotheque2.jpg"));
	    //contentPane.setBackground(Color.ORANGE);        
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		success.setContentPane(contentPane);
		
		JPanel bien = new JPanel();
		bien.setLayout(new FlowLayout());
		JLabel bien1 = new JLabel("Bienvenue Monsieur    ");
		JLabel bien2 = new JLabel(etu.getNom());
		JLabel bien3 = new JLabel("    sur votre compte Bibiotheque : ");
		bien1.setFont(new Font("Tahoma", Font.BOLD, 18));
		bien2.setFont(new Font("Tahoma", Font.BOLD, 20));
		bien3.setFont(new Font("Tahoma", Font.BOLD, 18));
		bien1.setForeground(Color.RED);
		bien2.setForeground(Color.BLUE);
		bien3.setForeground(Color.RED);
		bien.add(bien1);bien.add(bien2);bien.add(bien3);
		
		JButton empButton = new JButton("Emprunter :");
		empButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton rendButton = new JButton("Rendre : ");
		
		JLabel isbnLabel = new JLabel("Veuliez saisi le code de document : ");
		isbnLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		isbnLabel.setForeground(Color.WHITE);
		JTextField isbn = new JTextField();
		
		
		isbnLabel.setBounds(10, 200, 500, 50);
		contentPane.add(isbnLabel);
		
		JLabel code = new JLabel("isbn :");
		code.setFont(new Font("Tahoma", Font.BOLD,18));
		code.setForeground(Color.WHITE);
		code.setBounds(220, 250, 75, 50);
		contentPane.add(code);
		
		isbn.setBounds(300, 260, 200, 40);
		contentPane.add(isbn);
		
		rendButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.setLayout(null);
		success.setLayout(null);
		bien.setBounds(10, 10, 550, 100);
		empButton.setBounds(100,125,160,40);
		rendButton.setBounds(300, 125, 160, 40);
		//contentPane.setLayout(new BoxLayout(contentPane, 1));
		//success.getContentPane().setLayout(new BorderLayout());
		contentPane.add(bien);
		contentPane.add(empButton);
		contentPane.add(rendButton);
		success.setContentPane(contentPane);
		success.setVisible(true);
		
		rendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					etu.rendre(isbn.getText());
					isbn.setText("");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		empButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					etu.emprunter(isbn.getText());
					isbn.setText("");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	
	
	public static void success(Adherent ad){
		System.out.println("success login");
		LoginSuccess login = new LoginSuccess(ad);
	}
	
	
}

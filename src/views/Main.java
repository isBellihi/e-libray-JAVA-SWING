package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main{
	static JFrame principale;


	public static void main(String[] args) {
		principale = new JFrame();
		//JPanel contentPane;
		principale.setTitle("Application Bibiotheque/se connecter");
		principale.setLocationRelativeTo(null);
		principale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		principale.setBounds(100, 100, 600, 500);
		//contentPane = new JPanel();
		JLabel contentPane = new JLabel(new ImageIcon("F:\\Eclipse\\ENSIAS\\images\\bibliotheque2.jpg"));
	    //contentPane.setBackground(Color.ORANGE);        
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		principale.setContentPane(contentPane);
		
		
		//principale.add(background);
		
		JLabel bienvenue = new JLabel("Bienvenue au Bibiotheque électronique : ");
		bienvenue.setFont(new Font("Tahoma", Font.PLAIN, 22));
		bienvenue.setForeground(Color.RED);
		
		JButton etuButton = new JButton("Etudiant :");
		etuButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
		etuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EtudiantLogin.main(null);
				principale.dispose();
				principale = null;
			}
		});
		
		JButton profButton = new JButton("Professeur : ");
		profButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginProfesseur.main(null);
				principale.dispose();
				principale = null;
			}
		});
		profButton.setFont(new Font("Tahoma", Font.PLAIN, 22));
		contentPane.setLayout(null);
		principale.setLayout(null);
		bienvenue.setBounds(10, 10, 550, 50);
		etuButton.setBounds(150,100,160,40);
		profButton.setBounds(150, 180, 160, 40);
		//contentPane.setLayout(new BoxLayout(contentPane, 1));
		//principale.getContentPane().setLayout(new BorderLayout());
		contentPane.add(bienvenue);
		contentPane.add(etuButton);
		contentPane.add(profButton);
		principale.setContentPane(contentPane);
		principale.setVisible(true);
	}
}

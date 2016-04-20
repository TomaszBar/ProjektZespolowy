import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Button;

public class Rejestracja_Windows extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID1 = 1L;
	private JLabel jlNazwa_progrmu, jlWersja, jlPrawa, jlEmail,lblPodajImi,lblPodajNazwisko,lblPodajAdres,lblPodajNrTel,lblLogin,lblHaso;
	private JLabel lBorder, lIcon;
	private JButton JB_OK,JB_Anuluj;
	private Font font1 = new Font("TimesRoman", Font.PLAIN, 14);
	private Font font2 = new Font("TimesRoman", Font.PLAIN, 12);
	private Font font3 = new Font("TimesRoman", Font.BOLD, 12);
	private Font font4 = new Font("TimesRoman", Font.PLAIN, 12);
	private Border line = null;
	public int uprawnienia_uzytkownika;
	public int numer_uzytkownika;
	public boolean zamknij_rejstracje=false;
	
	
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField JT_Imie;
	private JTextField JT_Nazwisko;
	private JTextField JT_Adres;
	private JTextField JT_Numer_telefonu;
	private JTextField JT_Login;
	private JPasswordField JPF_Hasło;

	static ArrayList<BaseUser> listaUżytkowników = new ArrayList<>();
	
	LogowanieAlgorithm log = new LogowanieAlgorithm();
	private JTextField JT_Email;
	//Rejestracja_Windows rej = new Rejestracja_Windows();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Rejestracja_Windows rej = new Rejestracja_Windows();
					rej.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Rejestracja_Windows() {
		this.setTitle("Rejestracja");
		//this.setModal(true);
		this.setSize(345,560);
		this.setResizable(false);
		
		// obsluga zdarzenia okna
		this.addWindowListener	(new WindowAdapter(){ 
			// obsluga wcisniecia przycisku zamkniecia okna
                        @Override
			public void windowClosing(WindowEvent e){ 
				setVisible(false);
				zamknij_rejstracje = true;
				
				dispose();
				System.exit(0);
			}	
		});	
		
		// pobranie rozmiarow aplikacji
		Dimension dialogSize = getSize();		
		// pobranie rozdzielczosci pulpitu
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();  
		if(dialogSize.height > screenSize.height) 
			dialogSize.height = screenSize.height;
		if(dialogSize.width > screenSize.width)
			dialogSize.height = screenSize.width;
			
		// rozmieszczenie aplikacji na srodku ekranu
		setLocation((screenSize.width-dialogSize.width)/2,   
						(screenSize.height-dialogSize.height)/2);
		
		Container contentPane = getContentPane();
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(null);
		
		try {
			ImageIcon ikonainformacjeautor = new ImageIcon("fitt.jpg");
			lIcon = new JLabel();
			lIcon.setIcon(new ImageIcon("C:\\Users\\Gregory\\Documents\\JavaEclipse\\Fitnes\\image\\fitt (Copy).jpg"));
		} 
		catch(Exception e) { 
			lIcon = new JLabel();
		}
		jlNazwa_progrmu = new JLabel("Fitt Fitness");
		jlNazwa_progrmu.setFont(font1);
		jlNazwa_progrmu.setHorizontalAlignment(SwingConstants.CENTER);
		jlWersja = new JLabel("wersja 2.2");
		jlWersja.setFont(font1);
		jlWersja.setHorizontalAlignment(SwingConstants.CENTER);
		jlPrawa = new JLabel("Copyright (C) by 2015");
		jlPrawa.setFont(font2);
		jlPrawa.setHorizontalAlignment(SwingConstants.CENTER);
		jlEmail = new JLabel("e-mail: wolosg7@gmail.com");
		jlEmail.setFont(font4);
		lBorder = new JLabel(""); 
		JB_OK = new JButton("Ok");
		JB_OK.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				String imie = JT_Imie.getText();
				String nazwisko = JT_Nazwisko.getText();
				String adres = JT_Adres.getText();
				String email = JT_Email.getText();
				String numer_telfonu = JT_Numer_telefonu.getText();
				char[] hasło = JPF_Hasło.getPassword();
				String haslo = new String(hasło);
				String login = JT_Login.getText();
				//System.out.println(imie+nazwisko+adres+numer_telfonu+haslo+login);
				String [] rejestracja  = log.Rejestracja(imie, nazwisko, adres, email, numer_telfonu, login, haslo);
				
				
				
				
	  	         } 
		});
		
		line = new EtchedBorder(EtchedBorder.LOWERED);
		jlNazwa_progrmu.setBounds(52,11,126,30);
		jlWersja.setBounds(71,37,86,30);
		jlPrawa.setBounds(185,492,147,20);
		lBorder.setBounds(1,477,dialogSize.width-14,2);
		jlEmail.setBounds(11,492,165,20);
		JB_OK.setBounds(51,411,60,25);
		
		JB_Anuluj = new JButton("Anuluj\r\n");
		JB_Anuluj.setBounds(186, 411, 74, 25);
		JB_Anuluj.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				 
				
				//zamknij_rejstracje = true;
				//System.out.println(zamknij_rejstracje);
				dispose();
				System.exit(0);
				//JOptionPane.showMessageDialog(null,"Opcja jeszcze nie dostępna ! ","Informacja",JOptionPane.INFORMATION_MESSAGE);
	  	         } 
		});
		getContentPane().add(JB_Anuluj);
		
		lblPodajImi = new JLabel("Podaj Imię");
		lblPodajImi.setBounds(51, 118, 80, 16);
		getContentPane().add(lblPodajImi);
		
		lblPodajNazwisko = new JLabel("Podaj Nazwisko");
		lblPodajNazwisko.setBounds(52, 158, 94, 16);
		getContentPane().add(lblPodajNazwisko);
		
		lblPodajAdres = new JLabel("Podaj Adres");
		lblPodajAdres.setBounds(52, 198, 94, 16);
		getContentPane().add(lblPodajAdres);
		
		lblPodajNrTel = new JLabel("Podaj nr. Tel");
		lblPodajNrTel.setBounds(52, 244, 105, 16);
		getContentPane().add(lblPodajNrTel);
		
		lblLogin = new JLabel("Login :");
		lblLogin.setBounds(51, 324, 56, 16);
		getContentPane().add(lblLogin);
		
		lblHaso = new JLabel("Hasło :");
		lblHaso.setBounds(51, 365, 56, 16);
		getContentPane().add(lblHaso);
		
		JT_Imie = new JTextField();
		JT_Imie.setBounds(169, 115, 116, 22);
		getContentPane().add(JT_Imie);
		JT_Imie.setColumns(10);
		
		JT_Nazwisko = new JTextField();
		JT_Nazwisko.setBounds(169, 155, 116, 22);
		getContentPane().add(JT_Nazwisko);
		JT_Nazwisko.setColumns(10);
		
		JT_Adres = new JTextField();
		JT_Adres.setBounds(169, 195, 116, 22);
		getContentPane().add(JT_Adres);
		JT_Adres.setColumns(10);
		
		JT_Numer_telefonu = new JTextField();
		JT_Numer_telefonu.setBounds(169, 241, 116, 22);
		getContentPane().add(JT_Numer_telefonu);
		JT_Numer_telefonu.setColumns(10);
		
		JT_Login = new JTextField();
		JT_Login.setBounds(169, 321, 116, 22);
		getContentPane().add(JT_Login);
		JT_Login.setColumns(10);
		
		
		lBorder.setBorder(line);
		contentPane.add(jlNazwa_progrmu);
		contentPane.add(jlWersja);
		contentPane.add(jlPrawa);
		contentPane.add(jlEmail);
		contentPane.add(lBorder);
		contentPane.add(JB_OK);
		contentPane.add(lIcon);
		
		lIcon.setBounds(179,11,116,76);
		
		JPF_Hasło = new JPasswordField();
		JPF_Hasło.setBounds(169, 362, 116, 22);
		getContentPane().add(JPF_Hasło);
		
		JT_Email = new JTextField();
		JT_Email.setBounds(169, 276, 116, 22);
		getContentPane().add(JT_Email);
		JT_Email.setColumns(10);
		
		JLabel lblPodajAdresMail = new JLabel("Podaj Adres @");
		lblPodajAdresMail.setBounds(51, 281, 106, 16);
		getContentPane().add(lblPodajAdresMail);

	}
}

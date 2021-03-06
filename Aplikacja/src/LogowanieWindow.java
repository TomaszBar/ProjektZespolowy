
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
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

/**
 * Program <code>RejestrLB</code> Klasa <code>AboutWindow</code> definiujaca
 * okno z informacja o autorze.
 * 
 * @author
 * @version 1.0 15/12/2010
 */
public class LogowanieWindow extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JLabel jlNazwa_progrmu, jlWersja, jlPrawa, jlEmail;
	private JLabel lBorder, lIcon;
	private JButton jBOk;
	private Font font1 = new Font("TimesRoman", Font.PLAIN, 14);
	private Font font2 = new Font("TimesRoman", Font.PLAIN, 12);
	private Font font3 = new Font("TimesRoman", Font.BOLD, 12);
	private Font font4 = new Font("TimesRoman", Font.PLAIN, 12);
	private Border line = null;
	private JLabel lblLogin;
	private JLabel lblHaso;
	private JTextField textField;
	private JButton btnRejstracja;
	private JPasswordField passwordField;
	public static String uprawnienia_uzytkownika;
	static int numer_uzytkownika;
	public boolean zamknij;

	LogowanieAlgorithm log = new LogowanieAlgorithm();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {

					UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");

					LogowanieWindow log = new LogowanieWindow();
					log.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Konstruktor bezparametryczny klasy <code>AboutWindow</code>
	 */
	public LogowanieWindow() throws SQLException {
		this.setTitle("Logowanie");
		this.setModal(true);
		this.setSize(360, 260);
		this.setResizable(false);

		// obsluga zdarzenia okna
		this.addWindowListener(new WindowAdapter() {
			// obsluga wcisniecia przycisku zamkniecia okna
			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				zamknij = true;
				dispose();
				System.exit(0);
			}
		});

		// pobranie rozmiarow aplikacji
		Dimension dialogSize = getSize();
		// pobranie rozdzielczosci pulpitu
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		if (dialogSize.height > screenSize.height)
			dialogSize.height = screenSize.height;
		if (dialogSize.width > screenSize.width)
			dialogSize.height = screenSize.width;

		// rozmieszczenie aplikacji na srodku ekranu
		setLocation((screenSize.width - dialogSize.width) / 2, (screenSize.height - dialogSize.height) / 2);

		Container contentPane = getContentPane();
		contentPane.setBackground(Color.white);
		contentPane.setLayout(null);

		try {
			ImageIcon ikonainformacjeautor = new ImageIcon("fitt.jpg");
			lIcon = new JLabel();
			lIcon.setIcon(new ImageIcon("C:\\Users\\Gregory\\Documents\\JavaEclipse\\Fitnes\\image\\fitt (Copy).jpg"));
		} catch (Exception e) {
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
		jBOk = new JButton("Ok");
		jBOk.addActionListener(this);
		line = new EtchedBorder(EtchedBorder.LOWERED);
		jlNazwa_progrmu.setBounds(52, 11, 126, 30);
		jlWersja.setBounds(71, 37, 86, 30);
		jlPrawa.setBounds(183, 194, 147, 20);
		lBorder.setBounds(5, 185, dialogSize.width - 14, 2);
		jlEmail.setBounds(10, 194, 200, 20);
		jBOk.setBounds(81, 153, 60, 25);

		lBorder.setBorder(line);
		contentPane.add(jlNazwa_progrmu);
		contentPane.add(jlWersja);
		contentPane.add(jlPrawa);
		contentPane.add(jlEmail);
		contentPane.add(lBorder);
		contentPane.add(jBOk);
		contentPane.add(lIcon);

		lblLogin = new JLabel("Login");
		lblLogin.setBounds(29, 78, 46, 14);
		getContentPane().add(lblLogin);

		lblHaso = new JLabel("Hasło");
		lblHaso.setBounds(29, 117, 46, 14);
		getContentPane().add(lblHaso);

		textField = new JTextField();
		textField.setBounds(71, 75, 86, 20);
		getContentPane().add(textField);
		textField.setColumns(10);

		btnRejstracja = new JButton("Rejstracja");
		btnRejstracja.setBounds(204, 17, 106, 23);
		contentPane.add(btnRejstracja);
		btnRejstracja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				setVisible(false);

				Rejestracja_Windows rejstracja = null;
				try {
					rejstracja = new Rejestracja_Windows(2, "Rejestracja");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				rejstracja.setVisible(true);

				// TODO Rejestracja

			}
		});
		lIcon.setBounds(204, 51, 126, 119);

		passwordField = new JPasswordField();
		passwordField.setBounds(71, 114, 86, 20);
		getContentPane().add(passwordField);

	}

	/**
	 * Publiczna metoda z interfejsu <code>ActionListener</code> obslugujaca
	 * zdarzenie akcji <code>ActionEvent</code>
	 */
	public void actionPerformed(ActionEvent AE) {
		if (AE.getSource() == jBOk) {

			String login = textField.getText();
			char[] hasło = passwordField.getPassword();

			String haslo = new String(hasło);

			// System.out.println(login + haslo);

			setVisible(false);

			boolean poprawność = false;
			try {
				poprawność = log.Logowanie(login, haslo);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// System.err.println(poprawność);
			if (poprawność == true) {
				numer_uzytkownika = log.numer;
				// System.err.println("Numer z logowania :"+numer_uzytkownika);
				uprawnienia_uzytkownika = log.uprawnienia;
				// System.err.println(uprawnienia_uzytkownika);
				AppUser frame = null;
				try {
					frame = new AppUser(numer_uzytkownika, uprawnienia_uzytkownika);
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException
						| UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frame.setVisible(true);

			} else {

				JOptionPane.showMessageDialog(null, " Pdane Hasło lub Login jest nie popdrawne !", "Błąd Logowania",
						JOptionPane.WARNING_MESSAGE);
				setVisible(true);
			}

		}

	}
}

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
/** 
* Program <code>RejestrLB</code>
* Klasa <code>AboutWindow</code> definiujaca okno 
* z informacja o autorze.
* @author 	 	
* @version 1.0	15/12/2010
*/
public class AboutWindow extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JLabel jlNazwa_progrmu, jlWersja, jlPrawa,jlUczelnia, jlEmail;
	private JLabel lBorder, lIcon;
	private JButton jBOk;
	private Font font1 = new Font("TimesRoman", Font.PLAIN, 22);
	private Font font2 = new Font("TimesRoman", Font.PLAIN, 12);
	private Font font3 = new Font("TimesRoman", Font.BOLD, 12);
	private Font font4 = new Font("TimesRoman", Font.PLAIN, 12);
	private Border line = null;
	
	/**
	 * Konstruktor bezparametryczny klasy <code>AboutWindow</code>
	 */
	public AboutWindow() {
		this.setTitle("Informacje o programie");
		this.setModal(true);
		this.setSize(370,261);
		this.setResizable(false);
		
		// obsluga zdarzenia okna
		this.addWindowListener	(new WindowAdapter(){ 
			// obsluga wcisniecia przycisku zamkniecia okna
                        @Override
			public void windowClosing(WindowEvent e){ 
				setVisible(false);				
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
		contentPane.setBackground(Color.white);
		contentPane.setLayout(null);
		
		try {
			ImageIcon ikonainformacjeautor = new ImageIcon("fitt.jpg");
			lIcon = new JLabel();
			lIcon.setIcon(new ImageIcon("image\\fitt.jpg"));
//			lIcon = new JLabel(new ImageIcon(
//				getClass().getResource("author_logo.jpg")));
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
		jlUczelnia = new JLabel("Politechnika Koszalinska - WEiI");
		jlUczelnia.setFont(font3);
		jlUczelnia.setHorizontalAlignment(SwingConstants.CENTER);
		jlEmail = new JLabel("e-mail: wolosg7@gmail.com");
		jlEmail.setFont(font4);
		lBorder = new JLabel(""); 
		jBOk = new JButton("Ok");
		jBOk.addActionListener(this);
		line = new EtchedBorder(EtchedBorder.LOWERED);
		
		lIcon.setBounds(0,-121,559,576);
		jlNazwa_progrmu.setBounds(10,23,210,30);
		jlWersja.setBounds(20,50,210,30);
		jlPrawa.setBounds(10,79,210,20);
                
		jlUczelnia.setBounds(10,126,186,20);
		lBorder.setBounds(5,185,dialogSize.width-14,2);
		jlEmail.setBounds(10,194,200,20);
		jBOk.setBounds(dialogSize.width-75,192,60,25);
		
		lBorder.setBorder(line);
		contentPane.add(jlNazwa_progrmu);
		contentPane.add(jlWersja);
		contentPane.add(jlPrawa);
		contentPane.add(jlUczelnia);
		contentPane.add(jlEmail);
		contentPane.add(lBorder);
		contentPane.add(jBOk);
		contentPane.add(lIcon);
	}
	/**
	 * Publiczna metoda z interfejsu <code>ActionListener</code>
	 * obslugujaca zdarzenie akcji <code>ActionEvent</code>
	 */
	public void actionPerformed(ActionEvent AE) {
		if(AE.getSource() == jBOk) {
			
			setVisible(false);
		}
	}
}

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

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
public class HelpWindow extends JDialog implements ActionListener 
{

	private static final long serialVersionUID = 1L;
	private JLabel jlNazwa_progrmu, jlWersja, jlPrawa, jlEmail;
	private JLabel lBorder, lIcon;
        private JTextArea jtaTekst;
        private JScrollPane jspRolka;
	private JButton jbOk;
	private Font font1 = new Font("TimesRoman", Font.PLAIN, 22);
	private Font font2 = new Font("TimesRoman", Font.PLAIN, 12);
	private Font font3 = new Font("TimesRoman", Font.BOLD, 15);
	private Font font4 = new Font("TimesRoman", Font.PLAIN, 12);
	private Border line = null;
        //private String lokalizacja = "pomoc.txt";
	 FileInputStream fin = null;
	/**
	 * Konstruktor bezparametryczny klasy <code>AboutWindow</code>
	 */
	public HelpWindow() throws UnsupportedEncodingException {
		this.setTitle("Pomoc");
		this.setModal(true);
		this.setSize(600,540);
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
			ImageIcon ikonainformacjeautor = new ImageIcon("image\\ikona-informacji-2_21010494.jpeg");
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
		jlWersja.setFont(font3);
		jlWersja.setHorizontalAlignment(SwingConstants.CENTER);
		jlPrawa = new JLabel("Copyright (C) by 2015");
		jlPrawa.setFont(font2);
		jlPrawa.setHorizontalAlignment(SwingConstants.CENTER);
                jtaTekst = new JTextArea();
                jtaTekst.setEditable(false);
                jtaTekst.setBackground(Color.white);
                jspRolka = new JScrollPane(jtaTekst);
                jspRolka.setViewportView(jtaTekst);
		jlEmail = new JLabel("e-mail: wolosg7@gmail.com");
		jlEmail.setFont(font4);
		lBorder = new JLabel(""); 
		jbOk = new JButton("Ok");
		jbOk.addActionListener(this);
		line = new EtchedBorder(EtchedBorder.LOWERED);
		
		lIcon.setBounds(0,-108,618,630);
		jlNazwa_progrmu.setBounds(10,21,210,30);
		jlWersja.setBounds(10,46,210,30);
		jlPrawa.setBounds(10,74,210,20);
		jspRolka.setBounds(135, 140, 450, 330);
		lBorder.setBounds(5,475,dialogSize.width-14,2);
		jlEmail.setBounds(10,484,200,20);
		jbOk.setBounds(dialogSize.width-75,482,60,25);
		
                	 // new BufferedReader(new InputStreamReader(in,"UTF-8"));
		
                
		lBorder.setBorder(line);
		contentPane.add(jlNazwa_progrmu);
		contentPane.add(jlWersja);
		contentPane.add(jlPrawa);
		contentPane.add(jspRolka);
		contentPane.add(jlEmail);
		contentPane.add(lBorder);
		contentPane.add(jbOk);
		contentPane.add(lIcon);
                
                odczyt_z_pliku();
              
}
        public void odczyt_z_pliku() throws UnsupportedEncodingException
     {
        String line = "";
        FileInputStream fin = null;
        try{
            fin = new FileInputStream("pomoc.txt");
            
        }catch (FileNotFoundException e)
        {
            System.err.println("Nie odnaleziono porzadądanego pliku. \n "+e);

            System.exit(-1);
        }
        BufferedReader reader  = new BufferedReader(new InputStreamReader((fin),"UTF-8"));
        try {
            while((line = reader.readLine())!=null){
                
              jtaTekst.append(line+"\n");
             // System.out.println(line);
            }
        }catch (IOException e)
        {
            System.err.println("Błąd wyjścia /wejścia");
        }
        
     }
        
        /**
	 * Publiczna metoda z interfejsu <code>ActionListener</code>
	 * obslugujaca zdarzenie akcji <code>ActionEvent</code>
	 */
	public void actionPerformed(ActionEvent AE)
        {
		if(AE.getSource() == jbOk) 
                {
			setVisible(false);
		}
	}
}
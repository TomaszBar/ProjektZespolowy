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
import java.util.List;
import java.sql.Connection;

//import ga.RowNumberTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Dodawanie_ProduktuWindows extends JDialog implements ActionListener {

	private JPanel contentPane;
	private JButton jBOk,Anuluj;
	
	String Nazwa_Produktu;
	String Kalorie;
	String Tłuszcze;
	String Węglowodany;
	String Białko;
	int wariant;

	JLabel lIcon,jlNazwa_progrmu,lBorder,jlWersja,jlPrawa;
	public boolean zamknij;
	
	private Font font1 = new Font("TimesRoman", Font.PLAIN, 14);
	private Font font2 = new Font("TimesRoman", Font.PLAIN, 12);
	private Font font3 = new Font("TimesRoman", Font.BOLD, 12);
	private Font font4 = new Font("TimesRoman", Font.PLAIN, 12);
	private Border line = null;
	private JTextField JTF_Nazwa;
	private JLabel lblKalorie;
	private JTextField JTF_Kalorie;
	private JLabel lblBiako;
	private JTextField JTF_Białko;
	private JLabel lblTuszcze;
	private JTextField JTF_Tłuszcze;
	private JLabel lblWglowodany;
	private JTextField JTF_Węglowoadny;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dodawanie_ProduktuWindows frame = new Dodawanie_ProduktuWindows();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Dodawanie_ProduktuWindows() {
		
		this.setTitle("Wprowadzanie Produktu");
		this.setModal(true);
		this.setSize(301,454);
		this.setResizable(false);

		// obsluga zdarzenia okna
		this.addWindowListener	(new WindowAdapter(){ 
			// obsluga wcisniecia przycisku zamkniecia okna
                        @Override
			public void windowClosing(WindowEvent e){ 
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
			lIcon.setIcon(new ImageIcon("C:\\Users\\Gregory\\Documents\\JavaEclipse\\Fitnes\\image\\Wprowadz.png"));
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
		jlPrawa = new JLabel("Copyright (C) by 2016");
		jlPrawa.setFont(font2);
		jlPrawa.setHorizontalAlignment(SwingConstants.CENTER);
		jlNazwa_progrmu.setBounds(70,11,86,30);
		jlWersja.setBounds(74,39,86,30);
		jlPrawa.setBounds(49,68,147,20);
		lBorder = new JLabel(""); 
		
		jBOk = new JButton("Ok");
		jBOk.addActionListener((ActionListener) this);
		line = new EtchedBorder(EtchedBorder.LOWERED);
		jlNazwa_progrmu.setBounds(52,11,126,30);
		jBOk.setBounds(52,375,60,25);
		lBorder.setBounds(12,358,271,4);
		
		lBorder.setBorder(line);
		contentPane.add(jlNazwa_progrmu);
		contentPane.add(jlWersja);
		contentPane.add(jlPrawa);
		contentPane.add(lBorder);
		contentPane.add(jBOk);
		contentPane.add(lIcon);
	
		Anuluj = new JButton("Anuluj");
		Anuluj.setBounds(154, 376, 106, 23);
		contentPane.add(Anuluj);
		Anuluj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				JTF_Nazwa.setText("");
				JTF_Kalorie.setText("");
				JTF_Białko.setText("");
				JTF_Tłuszcze.setText("");
				JTF_Węglowoadny.setText("");
				wariant=1;
				dispose();

			}
		});
		
		
		lIcon.setBounds(191,11,104,73);
		
		JLabel lblNazwaProduktu = new JLabel("Nazwa Produktu");
		lblNazwaProduktu.setBounds(26, 126, 116, 16);
		getContentPane().add(lblNazwaProduktu);
		
		JTF_Nazwa = new JTextField();
		JTF_Nazwa.setBounds(154, 123, 116, 22);
		getContentPane().add(JTF_Nazwa);
		JTF_Nazwa.setColumns(10);
		
		lblKalorie = new JLabel("Kalorie");
		lblKalorie.setBounds(26, 170, 56, 16);
		getContentPane().add(lblKalorie);
		
		JTF_Kalorie = new JTextField();
		JTF_Kalorie.setBounds(154, 167, 116, 22);
		getContentPane().add(JTF_Kalorie);
		JTF_Kalorie.setColumns(10);
		
		lblBiako = new JLabel("Białko");
		lblBiako.setBounds(26, 213, 56, 16);
		getContentPane().add(lblBiako);
		
		JTF_Białko = new JTextField();
		JTF_Białko.setBounds(154, 210, 116, 22);
		getContentPane().add(JTF_Białko);
		JTF_Białko.setColumns(10);
		
		lblTuszcze = new JLabel("Tłuszcze");
		lblTuszcze.setBounds(26, 265, 56, 16);
		getContentPane().add(lblTuszcze);
		
		JTF_Tłuszcze = new JTextField();
		JTF_Tłuszcze.setBounds(154, 262, 116, 22);
		getContentPane().add(JTF_Tłuszcze);
		JTF_Tłuszcze.setColumns(10);
		
		lblWglowodany = new JLabel("Węglowodany");
		lblWglowodany.setBounds(26, 314, 86, 16);
		getContentPane().add(lblWglowodany);
		
		JTF_Węglowoadny = new JTextField();
		JTF_Węglowoadny.setBounds(154, 311, 116, 22);
		getContentPane().add(JTF_Węglowoadny);
		JTF_Węglowoadny.setColumns(10);
	}
	public void actionPerformed(ActionEvent AE) {
		
		
		
		if(AE.getSource() == jBOk) {
			
			wariant=2;
			Nazwa_Produktu="";
			Nazwa_Produktu = JTF_Nazwa.getText();
			Kalorie = JTF_Kalorie.getText();
			Białko = JTF_Białko.getText();
			Tłuszcze = JTF_Tłuszcze.getText();
			Węglowodany = JTF_Węglowoadny.getText();
			
			setVisible(false);
			JOptionPane.showMessageDialog(null, " Zaostał dodany produkt '" + Nazwa_Produktu+"' o kalori "+Kalorie, "Informacja", JOptionPane.INFORMATION_MESSAGE);
			
			
			
		}
		
		
	}
}

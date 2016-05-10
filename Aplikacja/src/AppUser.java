import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Shape;
import java.awt.SystemColor;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.WatchEvent;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.AbstractButton;
import javax.swing.AbstractListModel;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.AncestorListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.NumberFormatter;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.l2fprod.common.swing.JTaskPaneGroup;
import com.l2fprod.common.swing.PercentLayout;

import javax.swing.JCheckBoxMenuItem;

public class AppUser extends JFrame implements ActionListener{

	/**
	 * Atrybut statczny stalu okreslajacy szerokosc ramki
	 */
	private static final int WIDTH = 1141;
	/**
	 * Atrybut statyczny staly okreslajacy wysokosc ramki
	 */
	private static final int HEIGHT = 662;	
	/**
	*Atrybut bedacy warstwa contentPane ramki - na nim beda tworzone komponenty GUI
	*/
	
	
	/**
	*Atrybut bedacy menu Temat
	*/
	JComboBox comboBox_do_godziny,comboBox_od_godziny,comboBoxWybierzdzien;
	
	private JMenu menuTematy = new JMenu("Tematy");
	
	private JRadioButtonMenuItem rdbtnmntmMetal = new JRadioButtonMenuItem("Metal");
	private JRadioButtonMenuItem rdbtnArcyl = new JRadioButtonMenuItem("Acryl");
	private JRadioButtonMenuItem rdbtnAero = new JRadioButtonMenuItem("Aero");
	private JRadioButtonMenuItem rdbtnAluminum = new JRadioButtonMenuItem("Aluminum");
	private JRadioButtonMenuItem rdbtnBernstein = new JRadioButtonMenuItem("Bernstein");
	private JRadioButtonMenuItem rdbtnFast = new JRadioButtonMenuItem("Fast");
	private JRadioButtonMenuItem rdbtnGraphite = new JRadioButtonMenuItem("Graphite");
	private JRadioButtonMenuItem rdbtnHifi = new JRadioButtonMenuItem("HiFi");
	private JRadioButtonMenuItem rdbtnLuna = new JRadioButtonMenuItem("Luna");
	private JRadioButtonMenuItem rdbtnMcwin = new JRadioButtonMenuItem("McWin");
	private JRadioButtonMenuItem rdbtnMint = new JRadioButtonMenuItem("Mint");
	private JRadioButtonMenuItem rdbtnNoire = new JRadioButtonMenuItem("Noire");
	private JRadioButtonMenuItem rdbtnSmart = new JRadioButtonMenuItem("Smart");
	private JRadioButtonMenuItem rdbtnTexture = new JRadioButtonMenuItem("Texture");
	
	private JCheckBox chckbbiegszybki = new JCheckBox("bieg szybki");
	private JCheckBox chckbxZamiataniePodogi = new JCheckBox("zamiatanie podłogi");
	private JCheckBox chckbxZawieszeniFiranek = new JCheckBox("zawieszeni firanek");
	private JCheckBox chckbxZmieniniePocieli = new JCheckBox("zmieninie pościeli");
	private JCheckBox chckbxZmywanieNaczy = new JCheckBox("zmywanie naczyń");
	private JCheckBox chckbxBoks = new JCheckBox("boks");
	private JCheckBox chckbxGraWTenisa = new JCheckBox("gra w tenisa");
	private JCheckBox chckbxbiegwolny = new JCheckBox("bieg wolny");
	private JCheckBox chckbxGraWSiatkwke = new JCheckBox("gra w siatkówke");
	private JCheckBox chckbxJedzenie = new JCheckBox("jedzenie");
	private JCheckBox chckbxPrasowanie = new JCheckBox("prasowanie");
	private JCheckBox chckbxLeenie = new JCheckBox("leżenie");
	private JCheckBox chckbxRozwieszaniePrania = new JCheckBox("rozwieszanie prania");
	private JCheckBox chckbxStepaerobik = new JCheckBox("step-aerobik");
	private JCheckBox chckbxCzoganieSi = new JCheckBox("czołganie się");
	private JCheckBox chckbxpiewanie = new JCheckBox("śpiewanie");
	private JCheckBox chckbxGimnastykaLekka = new JCheckBox("gimnastyka lekka");
	private JCheckBox chckbxCzytanieNaGos = new JCheckBox("czytanie na głos");
	private JCheckBox chckbxJazdaMotocyklem = new JCheckBox("jazda motocyklem");
	private JCheckBox chckbxGimnastykaForsowna = new JCheckBox("gimnastyka forsowna");
	private JCheckBox chckbxJazdaNaywach = new JCheckBox("jazda na łyżwach");
	private JCheckBox chckbxMycieOkien = new JCheckBox("mycie okien");
	private JCheckBox chckbxJazdaNaRowerze = new JCheckBox("jazda na rowerze (10km/h)");
	private JCheckBox chckbxOdkurzenieMebli = new JCheckBox("odkurzenie mebli");
	private JCheckBox chckbxOdnierzanie = new JCheckBox("odśnierzanie");
	private JCheckBox chckbxSpacerSzybki = new JCheckBox("spacer szybki");
	private JCheckBox chckbxWbieganiePoSchodach = new JCheckBox("wbieganie po schodach");
	private JCheckBox chckbxSiedzenie = new JCheckBox("siedzenie");
	private JCheckBox chckbxSen = new JCheckBox("sen");
	private JCheckBox chckbxTrzepanieDywanw = new JCheckBox("trzepanie dywanów");
	private JCheckBox chckbxGraWGolfa = new JCheckBox("gra w golfa");
	private JCheckBox chckbxJazdaNaNartach = new JCheckBox("jazda na nartach");
	private JCheckBox chckbxJazdaNaRowerze_1 = new JCheckBox("jazda na rowerze (20km/h)");
	private JCheckBox chckbxOdkurzenieOdkurzaczem = new JCheckBox("odkurzenie odkurzaczem");
	private JCheckBox chckbxTaniec = new JCheckBox("taniec");
	private JCheckBox chckbxSpacerWolny = new JCheckBox("spacer wolny");
	private JCheckBox chckbxJazdaSamochdem = new JCheckBox("jazda samochdem");
	private JCheckBox chckbxPisanieNaMaszynie = new JCheckBox("pisanie na maszynie");
	private JCheckBox chckbxWchdzeniePoSchodach = new JCheckBox("wchdzenie po schodach");
	private JCheckBox chckbxWiosowanie = new JCheckBox("wiosłowanie");
	private JCheckBox chckbxUbieranieSi = new JCheckBox("ubieranie się i rozbieranie ");
	private JCheckBox chckbxStanie = new JCheckBox("stanie");
	private JCheckBox chckbxPywanie = new JCheckBox("pływanie");
	private JCheckBox chckbxStanieNaBaczno = new JCheckBox("stanie na baczność");
	
	private JComboBox comboBox_Wybierz_plec;
	
	private static int spalanie, kolumna,wiersz,w, dzien,wk,k,wybór_kategori,wybór_sortowania;
	private JSpinner spinner_kolumny,spinner_wiersz,spinner_ile,spinner_czas,spinner_zakres;
	private JPanel contentPane;
	private String nazwa,sciezka_Alergia,sciezka_Dieta;
	private JTextField jtfWynik_BMR,jtfSpalanie_trenig ;
	int wzrost,waga,wiek,czas,zakres,kalorie;
	
	private String userLogin;
	
	private  TextArea textArea;
	
	private JTable table,table_trenig ;
	
	private JLabel lblZakres,lblWyfietlimie,lblWyczas,lblWywietlNazwisko,lblWypw,lblWywitlkod,lblSrbmr,lblWyfcprim,lblMojeBmr,lblWyrmin,lblWyrmax,lblWypetle,lblWyfc;
	
	private JProgressBar progressBar_1;
	
	//private JPanel panel_Górny;
	
	private Font font1 = new Font("TimesRoman", Font.PLAIN, 14);
	private Font font2 = new Font("TimesRoman", Font.PLAIN, 12);
	private Font font3 = new Font("TimesRoman", Font.BOLD, 12);
	private Font font4 = new Font("TimesRoman", Font.PLAIN, 12);
	private Border line = null;
	
	LogowanieWindow win = new LogowanieWindow();
	LogowanieAlgorithm algoLog = new LogowanieAlgorithm();
	
	static String sciezka;
	
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	int tab[][]= new int [20][20];
	int[] tab_numery = new int [1200];
	
 //Wątek rozpoczynający prgram
	Runnable runners_Start;
    Thread threads_Start;
	

	/**
	 * Launch the application.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args)  {
		
		
		
		 
		
			SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
					AppUser frame = new AppUser();
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
			
		});
			
			
	}

	
	/**
	 * Create the frame.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public AppUser() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		
		setDefaultCloseOperation(AppUser.EXIT_ON_CLOSE);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("image\\fitt (Copy).jpg"));//ustawienie ikony programu
		this.setSize(new Dimension(1410, 979));//ustwienie wymiarów
		this.setLocationRelativeTo(null); //rozmieszczenie na środku ekranu
		this.setTitle("Fit Fitness");
		this. setVisible(true);
		//Wątki progrmu 
		 runners_Start = new Start();
	     threads_Start = new Thread(runners_Start);
	     
		SwingUtilities.updateComponentTreeUI(this);
		
		this.addWindowListener(new WindowAdapter() 
		{

			public void windowOpened(WindowEvent e)//metoda uaktywniana kiedy okno się pojawi na ekranie
			{
//				try {
//					read_Baza("produkty");
//				} catch (UnsupportedEncodingException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
			}

			public void windowClosing(WindowEvent e)
			{
				//Close_Window();
			}
		});

		
		win.setVisible(true);
		boolean zamknij = win.zamknij;
		
		if (zamknij==true)
		{
			Close_Window();
		}
		else{
			
			int x = win.uprawnienia_uzytkownika;
			
			
			if(x==1)
			{
				int numer_uzytk = win.numer_uzytkownika;
				
				String []tablica_dane = algoLog.Dane(numer_uzytk);
				int []tablica_dane_liczbowe = algoLog.Dane_liczbowe(numer_uzytk);
							
				StartDietaTrening(tablica_dane[1],tablica_dane[2],tablica_dane[3],tablica_dane[4],tablica_dane[5],tablica_dane[6],tablica_dane_liczbowe[1],tablica_dane_liczbowe[2]);
				
			}else if(x==2)
			{
				int numer_uzytk = win.numer_uzytkownika;
				
				String []tablica_dane = algoLog.Dane(numer_uzytk);
				int []tablica_dane_liczbowe = algoLog.Dane_liczbowe(numer_uzytk);				
				
				StartDietaTrening(tablica_dane[1],tablica_dane[2],tablica_dane[3],tablica_dane[4],tablica_dane[5],tablica_dane[6],tablica_dane_liczbowe[1],tablica_dane_liczbowe[2]);
				
											
			}else if(x==3){ 
				
				int numer_uzytk = win.numer_uzytkownika;
				
				String []tablica_dane = algoLog.Dane(numer_uzytk);
				int []tablica_dane_liczbowe = algoLog.Dane_liczbowe(numer_uzytk);		
				
				StartDietaTrening(tablica_dane[1],tablica_dane[2],tablica_dane[3],tablica_dane[4],tablica_dane[5],tablica_dane[6],tablica_dane_liczbowe[1],tablica_dane_liczbowe[2]);
	
			}
		}
		
		wczytajDaneZPliku();
		
		//StartDietaTrening(tablica_dane[1],tablica_dane[2],tablica_dane[3],tablica_dane[4],tablica_dane[5],tablica_dane[6],tablica_dane_liczbowe[1],tablica_dane_liczbowe[2]);

//		String []tablica_dane = algoLog.Dane(1);
//		int []tablica_dane_liczbowe = algoLog.Dane_liczbowe(1);
//		StartDietaTrening(tablica_dane[1],tablica_dane[2],tablica_dane[3],tablica_dane[4],tablica_dane[5],tablica_dane[6],tablica_dane_liczbowe[1],tablica_dane_liczbowe[2]);

		
		//UIManager.put("Table.showGrid", false);//do tabeli opcjonalnie 

		//Menu 
		JMenuBar menu = new JMenuBar();
		setJMenuBar(menu);
		
		JMenu menuPlik = new JMenu("Plik");
		
		menu.add(menuPlik);
		JMenuItem mtStart = new JMenuItem("Start");
		mtStart.setIcon(new ImageIcon("image\\miniStart.png"));
		mtStart.setHorizontalAlignment(SwingConstants.LEFT);
		menuPlik.add(mtStart);
		
		JMenuItem mtZapiszRaport = new JMenuItem("Zapisz raport");
		mtZapiszRaport.setIcon(new ImageIcon("image\\miniZapisz.png"));
		menuPlik.add(mtZapiszRaport);
		
		JSeparator separator_plik = new JSeparator();
		menuPlik.add(separator_plik);
		
		JMenuItem mtWyjcie = new JMenuItem("Wyjście");
		mtWyjcie.setIcon(new ImageIcon("image\\miniZamknij.png"));
		menuPlik.add(mtWyjcie);
		
		JMenu menuOpcje = new JMenu("Opcje");
		menu.add(menuOpcje);
		
		JMenuItem mntmEdytujDane = new JMenuItem("Edytuj dane");
		menuOpcje.add(mntmEdytujDane);
		
		JMenuItem mntmSprawdPostpy = new JMenuItem("Sprawdż postępy");
		menuOpcje.add(mntmSprawdPostpy);
		
		JSeparator separator = new JSeparator();
		menuOpcje.add(separator);
		
		JCheckBoxMenuItem chckbxmntmZmienaDieta = new JCheckBoxMenuItem("Zmiena dieta");
		menuOpcje.add(chckbxmntmZmienaDieta);
		
		JSeparator separator_1 = new JSeparator();
		menuOpcje.add(separator_1);
		
		JCheckBoxMenuItem chckbxmntmNewCheckItem = new JCheckBoxMenuItem("Dieta 1");
		buttonGroup.add(chckbxmntmNewCheckItem);
		menuOpcje.add(chckbxmntmNewCheckItem);
		
		JCheckBoxMenuItem chckbxmntmDieta = new JCheckBoxMenuItem("Dieta 2 ");
		menuOpcje.add(chckbxmntmDieta);
		
		JCheckBoxMenuItem chckbxmntmDieta_1 = new JCheckBoxMenuItem("Dieta 3");
		menuOpcje.add(chckbxmntmDieta_1);
		
		JCheckBoxMenuItem chckbxmntmDieta_2 = new JCheckBoxMenuItem("Dieta 4");
		menuOpcje.add(chckbxmntmDieta_2);
		
		JSeparator separator_2 = new JSeparator();
		menuOpcje.add(separator_2);
		
		JCheckBox chckbxAlergia = new JCheckBox("Alergia 1");
		menuOpcje.add(chckbxAlergia);
		
		JCheckBox chckbxAlergia_1 = new JCheckBox("Alergia 2");
		menuOpcje.add(chckbxAlergia_1);
		
		JCheckBox chckbxAlergia_2 = new JCheckBox("Alergia 3");
		menuOpcje.add(chckbxAlergia_2);
		
		JCheckBox chckbxAlergia_3 = new JCheckBox("Alergia 4");
		menuOpcje.add(chckbxAlergia_3);
		
		JCheckBox chckbxAlregia = new JCheckBox("Alregia 5");
		menuOpcje.add(chckbxAlregia);
		
		JCheckBox chckbxAlergia_4 = new JCheckBox("Alergia 6");
		menuOpcje.add(chckbxAlergia_4);
		
		//menuTematy = new JMenu("Tematy");
		menu.add(menuTematy);
		
		menuTematy.add(rdbtnmntmMetal);
		rdbtnmntmMetal.addActionListener(this);
		
		JSeparator separator_tematy = new JSeparator();
		menuTematy.add(separator_tematy);
		
		menuTematy.add(rdbtnArcyl);
		rdbtnArcyl.addActionListener(this);
		
		menuTematy.add(rdbtnAero);
		rdbtnAero.addActionListener(this);
		rdbtnAluminum.setSelected(true);
		
		menuTematy.add(rdbtnAluminum);
		rdbtnAluminum.addActionListener(this);
		
		menuTematy.add(rdbtnBernstein);
		rdbtnBernstein.addActionListener(this);
		
		menuTematy.add(rdbtnFast);
		rdbtnFast.addActionListener(this);
		
		menuTematy.add(rdbtnGraphite);
		rdbtnGraphite.addActionListener(this);
		
		menuTematy.add(rdbtnHifi);
		rdbtnHifi.addActionListener(this);
		
		menuTematy.add(rdbtnLuna);
		rdbtnLuna.addActionListener(this);
		
		menuTematy.add(rdbtnMcwin);
		rdbtnMcwin.addActionListener(this);
		
		menuTematy.add(rdbtnMint);
		rdbtnMint.addActionListener(this);
		
		menuTematy.add(rdbtnNoire);
		rdbtnNoire.addActionListener(this);
		
		menuTematy.add(rdbtnSmart);
		rdbtnSmart.addActionListener(this);
		
		menuTematy.add(rdbtnTexture);
		rdbtnTexture.addActionListener(this);
		
		ButtonGroup group_tematy = new ButtonGroup();
		
		group_tematy.add(rdbtnAero);
		group_tematy.add(rdbtnAluminum);
		group_tematy.add(rdbtnArcyl);
		group_tematy.add(rdbtnBernstein);
		group_tematy.add(rdbtnFast);
		group_tematy.add(rdbtnGraphite);
		group_tematy.add(rdbtnHifi);
		group_tematy.add(rdbtnLuna);
		group_tematy.add(rdbtnMint);
		group_tematy.add(rdbtnMcwin);
		group_tematy.add(rdbtnmntmMetal);
		group_tematy.add(rdbtnNoire);
		group_tematy.add(rdbtnSmart);
		group_tematy.add(rdbtnTexture);
		group_tematy.setSelected(rdbtnAluminum.getModel(), true);
		
		ButtonGroup group_cwiczenia = new ButtonGroup();
		
		group_cwiczenia.add(chckbbiegszybki);
		group_cwiczenia.add(chckbxBoks);
		group_cwiczenia.add(chckbxCzoganieSi);
		group_cwiczenia.add(chckbxCzytanieNaGos);
		group_cwiczenia.add(chckbxGimnastykaForsowna);
		group_cwiczenia.add(chckbxGimnastykaLekka);
		group_cwiczenia.add(chckbxGraWGolfa);
		group_cwiczenia.add(chckbxGraWSiatkwke);
		group_cwiczenia.add(chckbxGraWTenisa);
		group_cwiczenia.add(chckbxJazdaMotocyklem);
		group_cwiczenia.add(chckbxJazdaNaNartach);
		group_cwiczenia.add(chckbxJazdaNaRowerze);
		group_cwiczenia.add(chckbxJazdaNaRowerze_1);
		group_cwiczenia.add(chckbxJazdaNaywach);
		group_cwiczenia.add(chckbxJedzenie);
		group_cwiczenia.add(chckbxJazdaSamochdem);
		group_cwiczenia.add(chckbxLeenie);
		group_cwiczenia.add(chckbxMycieOkien);
		group_cwiczenia.add(chckbxOdkurzenieMebli);
		group_cwiczenia.add(chckbxOdkurzenieOdkurzaczem);
		group_cwiczenia.add(chckbxOdnierzanie);
		group_cwiczenia.add(chckbxPisanieNaMaszynie);
		group_cwiczenia.add(chckbxPrasowanie);
		group_cwiczenia.add(chckbxPywanie);
		group_cwiczenia.add(chckbxRozwieszaniePrania);
		group_cwiczenia.add(chckbxSen);
		group_cwiczenia.add(chckbxSiedzenie);
		group_cwiczenia.add(chckbxSpacerSzybki);
		group_cwiczenia.add(chckbxSpacerWolny);
		group_cwiczenia.add(chckbxStanie);
		group_cwiczenia.add(chckbxStanieNaBaczno);
		group_cwiczenia.add(chckbxStepaerobik);
		group_cwiczenia.add(chckbxTaniec);
		group_cwiczenia.add(chckbxTrzepanieDywanw);
		group_cwiczenia.add(chckbxUbieranieSi);
		group_cwiczenia.add(chckbxWbieganiePoSchodach);
		group_cwiczenia.add(chckbxWchdzeniePoSchodach);
		group_cwiczenia.add(chckbxWiosowanie);
		group_cwiczenia.add(chckbxZamiataniePodogi);
		group_cwiczenia.add(chckbxZawieszeniFiranek);
		group_cwiczenia.add(chckbxZmieniniePocieli);
		group_cwiczenia.add(chckbxZmywanieNaczy);
		group_cwiczenia.add(chckbxbiegwolny);
		group_cwiczenia.add(chckbxpiewanie);
		
		
		
		
		
		
		this.repaint();
		
		
		JMenu menuPomoc = new JMenu("Pomoc");
		menu.add(menuPomoc);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Pomoc");
		mntmNewMenuItem.setIcon(new ImageIcon("image\\miniPomoc.png"));
		menuPomoc.add(mntmNewMenuItem);
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HelpWindow pomocprogram = null;
				try {
					pomocprogram = new HelpWindow();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				pomocprogram.setVisible(true);
			}
		});
		
		JMenuItem mntmInforamcjeOProgramie = new JMenuItem("Inforamcje o programie");
		mntmInforamcjeOProgramie.setIcon(new ImageIcon("image\\miniInformacja.png"));
		menuPomoc.add(mntmInforamcjeOProgramie);
		mntmInforamcjeOProgramie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AboutWindow infoprogram = new AboutWindow();
				infoprogram.setVisible(true);
			}
		});
		
				
	}
	
	private void drukujTabele(Document doc, JTable tab) throws DocumentException  {
		
            PdfPTable pdfTable = new PdfPTable(tab.getColumnCount());
            pdfTable.setWidthPercentage(100);
            pdfTable.setSpacingBefore(10);
            pdfTable.setSpacingAfter(10);            
            
            com.itextpdf.text.Font f = FontFactory.getFont(FontFactory.HELVETICA, 8);            
            
            for (int i = 0; i < tab.getColumnCount(); i++) {
                pdfTable.addCell(new Phrase(tab.getColumnName(i), f));
            }
            //extracting data from the JTable and inserting it to PdfPTable
            String txt;
            
            for (int rows = 0; rows < tab.getRowCount(); rows++) {
                for (int cols = 0; cols < tab.getColumnCount(); cols++) {
	            	txt = Objects.toString(tab.getModel().getValueAt(rows, cols), "");
                    pdfTable.addCell(new Phrase(txt, f));
                }
            }
            doc.add(pdfTable);		
		
	}
	
	private void przygotujWydruki(){
					
        try {
        	
    		JFileChooser fileChooser = new JFileChooser();
    		fileChooser.setDialogTitle("Podaj nazwę pliku");

            FileNameExtensionFilter pdfFilter = new FileNameExtensionFilter("Pliki PDF (*.pdf)", "pdf");
            fileChooser.addChoosableFileFilter(pdfFilter);
            fileChooser.setFileFilter(pdfFilter);    		    		
    		 
    		int userSelection = fileChooser.showSaveDialog(this);
    		 
    		if (userSelection == JFileChooser.APPROVE_OPTION) {
    		    File plik = fileChooser.getSelectedFile();
    		    
                Document doc = new Document(PageSize.A4.rotate(), 20,20,20,20);
                PdfWriter.getInstance(doc, new FileOutputStream(plik));

                doc.open();
                
                doc.add(new Paragraph("Treningi")); 	            
                drukujTabele(doc, table_trenig);
                doc.add(new Paragraph("Dieta"));            
                drukujTabele(doc, table);            
                
                doc.close();
    		        		    
    		    System.out.println("Plik zapisany: " + plik.getAbsolutePath());
    		}        	
        	
        } catch (Exception e) {
        	e.printStackTrace();
        }            
		
	}
	
	private void drukujTabele2() {
		
	    Document document = new Document();
	    boolean shapes = true;
		
	    try{
	    	
	        PdfWriter writer;
	        if (shapes)
	           writer = PdfWriter.getInstance(document, new FileOutputStream(userLogin+".pdf"));
	        else
	           writer = PdfWriter.getInstance(document,	new FileOutputStream(userLogin+".pdf"));
	        document.open();
	        PdfContentByte cb = writer.getDirectContent();
	        PdfTemplate tp = cb.createTemplate(1500, 500);
	        Graphics2D g2;
	        if (shapes)
	           g2 = tp.createGraphicsShapes(1500, 500);
	        else
	           g2 = tp.createGraphics(1500, 500);
	        table_trenig.print(g2);
	        g2.dispose();
	        cb.addTemplate(tp, 30, 300);	    	
	    	
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    }
	    
	    document.close();	    
	    
	}
	
	private void spinnerTylkoCyfry(JSpinner spn){
		
        JFormattedTextField txt = ((JSpinner.NumberEditor) spn.getEditor()).getTextField();
        ((NumberFormatter) txt.getFormatter()).setAllowsInvalid(false);			
		
	}
   
	private void zapiszDaneDoPliku(){
      	
    	zapiszTabele(userLogin + ".dieta.fit", table);
    	zapiszTabele(userLogin + ".trening.fit", table_trenig);        	
	}
	
	private void zapiszTabele(String nazwaPliku, JTable tab) {
		
		try{
		
	        FileWriter fw = new FileWriter(new File(nazwaPliku));
	        BufferedWriter bw = new BufferedWriter(fw);
	        String komorka;

	        for(int i = 0; i < tab.getRowCount(); i++){
	            for(int j = 0; j < tab.getColumnCount(); j++){
	            	komorka = Objects.toString(tab.getModel().getValueAt(i, j), "");	            	
	                bw.write(komorka);
	                bw.write("\t");
	            }
	            bw.write("\n");
	        }
	        bw.close();
	        fw.close();
	        
		}
        catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void wczytajDaneZPliku(){
		      	
    	wczytajTabele(userLogin + ".dieta.fit", table);
    	wczytajTabele(userLogin + ".trening.fit", table_trenig);
    	
	}	
	
	private void wczytajTabele(String nazwaPliku, JTable tab) {
		
		try{
			
			File f = new File(nazwaPliku);
			if(!f.exists())
				return;			
		
	        FileReader fr = new FileReader(f);
	        BufferedReader br = new BufferedReader(fr);
	        String dane;

	        for(int i = 0; i < tab.getRowCount(); i++){
	            dane = br.readLine();
	            String[] komorki = dane.split("\\t", -1);	                   	
	            for(int j = 1; j < tab.getColumnCount(); j++){
	                tab.getModel().setValueAt(komorki[j], i, j);
	            }
	        }
	        br.close();
	        fr.close();
	        
		}
        catch (Exception e) {
			e.printStackTrace();
		}
		
	}	
		
	
	public void StartDietaTrening(String imie,String nazwisko,String adres,String email,String login,String haslo,int kod,int nr_tel){

		userLogin = login;	
			
		contentPane = (JPanel) this.getContentPane();//utworzenie panelu glownego bedacego ContentPane ramki
		contentPane.setLayout(new BorderLayout());//ustawienie menedzera ukladu BorderLayout
	
		//Panele aplikacji 
		JPanel panel_Górny = new JPanel();
		JPanel panel_Centralny = new JPanel();
		JPanel panel_Lewy = new JPanel();
		JPanel panel_Prawy = new JPanel();
		JPanel panel_Dolny = new JPanel();
		
		//Ustawienie paneli 
		contentPane.add(panel_Górny, BorderLayout.NORTH);
		contentPane.add(panel_Centralny,BorderLayout.CENTER);
		panel_Centralny.setLayout(new BorderLayout(0, 0));
		
		 
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel_Centralny.add(tabbedPane);
		
		JInternalFrame Start = new JInternalFrame("Witamy "+imie);
		tabbedPane.addTab("Start", null, Start, null);
		Start.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel_Centralny_Start = new JPanel();
		panel_Centralny_Start.setPreferredSize(new Dimension(110, 110));
		Start.getContentPane().add(panel_Centralny_Start);
		GridBagLayout gbl_panel_Centralny_Start = new GridBagLayout();
		gbl_panel_Centralny_Start.columnWidths = new int[]{392, 150, 30, 209, 0};
		gbl_panel_Centralny_Start.rowHeights = new int[]{127, 16, 39, 16, 16, 16, 16, 53, 20, 30, 36, 0};
		gbl_panel_Centralny_Start.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_Centralny_Start.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_Centralny_Start.setLayout(gbl_panel_Centralny_Start);
		
		JLabel lblDaneOsobowe = new JLabel("Dane osobowe");
		lblDaneOsobowe.setFont(new Font("Dialog", Font.BOLD, 12));
		GridBagConstraints gbc_lblDaneOsobowe = new GridBagConstraints();
		gbc_lblDaneOsobowe.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblDaneOsobowe.insets = new Insets(0, 0, 5, 0);
		gbc_lblDaneOsobowe.gridx = 3;
		gbc_lblDaneOsobowe.gridy = 1;
		panel_Centralny_Start.add(lblDaneOsobowe, gbc_lblDaneOsobowe);
		
		JLabel lblImie = new JLabel("Imie ");
		GridBagConstraints gbc_lblImie = new GridBagConstraints();
		gbc_lblImie.anchor = GridBagConstraints.NORTH;
		gbc_lblImie.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblImie.insets = new Insets(0, 0, 5, 5);
		gbc_lblImie.gridx = 1;
		gbc_lblImie.gridy = 3;
		panel_Centralny_Start.add(lblImie, gbc_lblImie);
		
		JLabel lblWysimi = new JLabel(imie);
		GridBagConstraints gbc_lblWysimi = new GridBagConstraints();
		gbc_lblWysimi.anchor = GridBagConstraints.NORTH;
		gbc_lblWysimi.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblWysimi.insets = new Insets(0, 0, 5, 0);
		gbc_lblWysimi.gridx = 3;
		gbc_lblWysimi.gridy = 3;
		panel_Centralny_Start.add(lblWysimi, gbc_lblWysimi);
		
		JLabel lblNazwisko = new JLabel("Nazwisko");
		GridBagConstraints gbc_lblNazwisko = new GridBagConstraints();
		gbc_lblNazwisko.anchor = GridBagConstraints.NORTH;
		gbc_lblNazwisko.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNazwisko.insets = new Insets(0, 0, 5, 5);
		gbc_lblNazwisko.gridx = 1;
		gbc_lblNazwisko.gridy = 4;
		panel_Centralny_Start.add(lblNazwisko, gbc_lblNazwisko);
		
		JLabel lblWysnazwisko = new JLabel(nazwisko);
		GridBagConstraints gbc_lblWysnazwisko = new GridBagConstraints();
		gbc_lblWysnazwisko.anchor = GridBagConstraints.NORTH;
		gbc_lblWysnazwisko.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblWysnazwisko.insets = new Insets(0, 0, 5, 0);
		gbc_lblWysnazwisko.gridx = 3;
		gbc_lblWysnazwisko.gridy = 4;
		panel_Centralny_Start.add(lblWysnazwisko, gbc_lblWysnazwisko);
		
		JLabel lblNumerTelrfonu = new JLabel("Numer telrfonu:");
		GridBagConstraints gbc_lblNumerTelrfonu = new GridBagConstraints();
		gbc_lblNumerTelrfonu.anchor = GridBagConstraints.NORTH;
		gbc_lblNumerTelrfonu.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNumerTelrfonu.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumerTelrfonu.gridx = 1;
		gbc_lblNumerTelrfonu.gridy = 5;
		panel_Centralny_Start.add(lblNumerTelrfonu, gbc_lblNumerTelrfonu);
		
		JLabel lblWysnumer = new JLabel(""+nr_tel);
		GridBagConstraints gbc_lblWysnumer = new GridBagConstraints();
		gbc_lblWysnumer.anchor = GridBagConstraints.NORTH;
		gbc_lblWysnumer.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblWysnumer.insets = new Insets(0, 0, 5, 0);
		gbc_lblWysnumer.gridx = 3;
		gbc_lblWysnumer.gridy = 5;
		panel_Centralny_Start.add(lblWysnumer, gbc_lblWysnumer);
		
		JLabel lblAdres = new JLabel("Adres:");
		GridBagConstraints gbc_lblAdres = new GridBagConstraints();
		gbc_lblAdres.anchor = GridBagConstraints.NORTH;
		gbc_lblAdres.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblAdres.insets = new Insets(0, 0, 5, 5);
		gbc_lblAdres.gridx = 1;
		gbc_lblAdres.gridy = 6;
		panel_Centralny_Start.add(lblAdres, gbc_lblAdres);
		
		JLabel lblWysadres = new JLabel(adres);
		GridBagConstraints gbc_lblWysadres = new GridBagConstraints();
		gbc_lblWysadres.anchor = GridBagConstraints.NORTH;
		gbc_lblWysadres.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblWysadres.insets = new Insets(0, 0, 5, 0);
		gbc_lblWysadres.gridx = 3;
		gbc_lblWysadres.gridy = 6;
		panel_Centralny_Start.add(lblWysadres, gbc_lblWysadres);
		
		JButton btnEdytujDane = new JButton("Edytuj Dane");
		GridBagConstraints gbc_btnEdytujDane = new GridBagConstraints();
		gbc_btnEdytujDane.anchor = GridBagConstraints.NORTH;
		gbc_btnEdytujDane.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEdytujDane.insets = new Insets(0, 0, 5, 5);
		gbc_btnEdytujDane.gridx = 1;
		gbc_btnEdytujDane.gridy = 8;
		panel_Centralny_Start.add(btnEdytujDane, gbc_btnEdytujDane);
		
		JButton btnWyslijWiadomosc = new JButton("Wyslij Wiadomosc");
		GridBagConstraints gbc_btnWyslijWiadomosc = new GridBagConstraints();
		gbc_btnWyslijWiadomosc.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnWyslijWiadomosc.insets = new Insets(0, 0, 5, 0);
		gbc_btnWyslijWiadomosc.gridx = 3;
		gbc_btnWyslijWiadomosc.gridy = 8;
		panel_Centralny_Start.add(btnWyslijWiadomosc, gbc_btnWyslijWiadomosc);
		
		JButton btnOdczytajWiadomosc = new JButton("Odczytaj Wiadomosc");
		GridBagConstraints gbc_btnOdczytajWiadomosc = new GridBagConstraints();
		gbc_btnOdczytajWiadomosc.fill = GridBagConstraints.VERTICAL;
		gbc_btnOdczytajWiadomosc.gridwidth = 3;
		gbc_btnOdczytajWiadomosc.gridx = 1;
		gbc_btnOdczytajWiadomosc.gridy = 10;
		panel_Centralny_Start.add(btnOdczytajWiadomosc, gbc_btnOdczytajWiadomosc);
		
		JPanel panel_Gorny_menu = new JPanel();
		panel_Gorny_menu.setPreferredSize(new Dimension(110, 110));
		Start.getContentPane().add(panel_Gorny_menu, BorderLayout.NORTH);
		
		JPanel panel_Lewy_menu = new JPanel();
		panel_Lewy_menu.setPreferredSize(new Dimension(110, 110));
		Start.getContentPane().add(panel_Lewy_menu, BorderLayout.WEST);
		
		JPanel panel_Dol_menu = new JPanel();
		panel_Dol_menu.setPreferredSize(new Dimension(110, 110));
		Start.getContentPane().add(panel_Dol_menu, BorderLayout.SOUTH);
		
		JPanel panel_Prawy_menu = new JPanel();
		panel_Prawy_menu.setPreferredSize(new Dimension(110, 110));
		Start.getContentPane().add(panel_Prawy_menu, BorderLayout.EAST);
		
		JInternalFrame Dieta = new JInternalFrame("Plan posiłków");
		tabbedPane.addTab("Dieta", null, Dieta, null);
		
		JPanel panelwprowadzania = new JPanel();
		panelwprowadzania.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		Dieta.getContentPane().add(panelwprowadzania, BorderLayout.WEST);
		
		GridBagLayout gbl_panelwprowadzania = new GridBagLayout();
		gbl_panelwprowadzania.columnWidths = new int[]{55, 47, 0};
		gbl_panelwprowadzania.rowHeights = new int[]{0, 0, 10, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 0, 0, 0, 20, 0};
		gbl_panelwprowadzania.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_panelwprowadzania.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelwprowadzania.setLayout(gbl_panelwprowadzania);
		
		JLabel lblWprowadDane = new JLabel("Wprowadż dane");
		GridBagConstraints gbc_lblWprowadDane = new GridBagConstraints();
		gbc_lblWprowadDane.gridwidth = 2;
		gbc_lblWprowadDane.insets = new Insets(0, 0, 5, 0);
		gbc_lblWprowadDane.gridx = 0;
		gbc_lblWprowadDane.gridy = 0;
		panelwprowadzania.add(lblWprowadDane, gbc_lblWprowadDane);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.gridwidth = 2;
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 1;
		panelwprowadzania.add(separator, gbc_separator);
		
		JLabel label = new JLabel("  Płeć  ");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.VERTICAL;
		gbc_label.anchor = GridBagConstraints.WEST;
		gbc_label.gridwidth = 2;
		gbc_label.insets = new Insets(0, 0, 5, 0);
		gbc_label.gridx = 0;
		gbc_label.gridy = 2;
		panelwprowadzania.add(label, gbc_label);
		
		comboBox_Wybierz_plec = new JComboBox();
		comboBox_Wybierz_plec.setModel(new DefaultComboBoxModel(new String[] {"Wybierz", "Kobieta", "Mężczyzna"}));
		comboBox_Wybierz_plec.setMaximumRowCount(10);
		comboBox_Wybierz_plec.setEditable(true);
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridwidth = 2;
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_1.gridx = 0;
		gbc_comboBox_1.gridy = 3;
		panelwprowadzania.add(comboBox_Wybierz_plec, gbc_comboBox_1);
		
		JLabel label_1 = new JLabel("  Wiek");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.WEST;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 4;
		panelwprowadzania.add(label_1, gbc_label_1);
		
		JSpinner spinner_wiek = new JSpinner();
		spinnerTylkoCyfry(spinner_wiek);
		
		GridBagConstraints gbc_spinner_4 = new GridBagConstraints();
		gbc_spinner_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_4.gridwidth = 2;
		gbc_spinner_4.insets = new Insets(0, 0, 5, 0);
		gbc_spinner_4.gridx = 0;
		gbc_spinner_4.gridy = 5;
		panelwprowadzania.add(spinner_wiek, gbc_spinner_4);
		
		JLabel label_2 = new JLabel(" Wzrost (cm)");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.anchor = GridBagConstraints.WEST;
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 0;
		gbc_label_2.gridy = 6;
		panelwprowadzania.add(label_2, gbc_label_2);
		
		JSpinner spinner_wzrost = new JSpinner();
		spinnerTylkoCyfry(spinner_wzrost);
		GridBagConstraints gbc_spinner_5 = new GridBagConstraints();
		gbc_spinner_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_5.gridwidth = 2;
		gbc_spinner_5.insets = new Insets(0, 0, 5, 0);
		gbc_spinner_5.gridx = 0;
		gbc_spinner_5.gridy = 7;
		panelwprowadzania.add(spinner_wzrost, gbc_spinner_5);
		
		JLabel label_3 = new JLabel(" Waga (kg)");
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.anchor = GridBagConstraints.WEST;
		gbc_label_3.insets = new Insets(0, 0, 5, 5);
		gbc_label_3.gridx = 0;
		gbc_label_3.gridy = 8;
		panelwprowadzania.add(label_3, gbc_label_3);
		
		JSpinner spinner_waga = new JSpinner();
		spinnerTylkoCyfry(spinner_waga);
		GridBagConstraints gbc_spinner_6 = new GridBagConstraints();
		gbc_spinner_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_6.gridwidth = 2;
		gbc_spinner_6.insets = new Insets(0, 0, 5, 0);
		gbc_spinner_6.gridx = 0;
		gbc_spinner_6.gridy = 9;
		panelwprowadzania.add(spinner_waga, gbc_spinner_6);
		
		JSeparator separator_2 = new JSeparator();
		GridBagConstraints gbc_separator_2 = new GridBagConstraints();
		gbc_separator_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_2.gridwidth = 2;
		gbc_separator_2.insets = new Insets(0, 0, 5, 0);
		gbc_separator_2.gridx = 0;
		gbc_separator_2.gridy = 12;
		panelwprowadzania.add(separator_2, gbc_separator_2);
		
		JLabel lblNewLabel = new JLabel(" Forma aktywności");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 13;
		panelwprowadzania.add(lblNewLabel, gbc_lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setEditable(true);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Wybierz", "1 na tydzień", "2 na tydzień", "3 na tydzień", "4 na tydzień"}));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 2;
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 14;
		panelwprowadzania.add(comboBox, gbc_comboBox);
		
		JLabel lblCoChceszZrobi = new JLabel(" Co chcesz zrobić ?");
		GridBagConstraints gbc_lblCoChceszZrobi = new GridBagConstraints();
		gbc_lblCoChceszZrobi.insets = new Insets(0, 0, 5, 5);
		gbc_lblCoChceszZrobi.gridx = 0;
		gbc_lblCoChceszZrobi.gridy = 15;
		panelwprowadzania.add(lblCoChceszZrobi, gbc_lblCoChceszZrobi);
		
		JComboBox comboBox_co_chcesz  = new JComboBox();
		comboBox_co_chcesz.setModel(new DefaultComboBoxModel(new String[] {"Wybierz", "Schdnąć", "Przytyć"}));
		comboBox_co_chcesz.setEditable(true);
		GridBagConstraints gbc_comboBox_co_chcesz = new GridBagConstraints();
		gbc_comboBox_co_chcesz.gridwidth = 2;
		gbc_comboBox_co_chcesz.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_co_chcesz.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_co_chcesz.gridx = 0;
		gbc_comboBox_co_chcesz.gridy = 16;
		panelwprowadzania.add(comboBox_co_chcesz, gbc_comboBox_co_chcesz);
		
		JLabel lblIlekg = new JLabel(" Ile (kg) ?");
		GridBagConstraints gbc_lblIlekg = new GridBagConstraints();
		gbc_lblIlekg.anchor = GridBagConstraints.WEST;
		gbc_lblIlekg.insets = new Insets(0, 0, 5, 5);
		gbc_lblIlekg.gridx = 0;
		gbc_lblIlekg.gridy = 17;
		panelwprowadzania.add(lblIlekg, gbc_lblIlekg);
		
		JSpinner spinner = new JSpinner();
		spinnerTylkoCyfry(spinner);
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.gridwidth = 2;
		gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner.insets = new Insets(0, 0, 5, 0);
		gbc_spinner.gridx = 0;
		gbc_spinner.gridy = 18;
		panelwprowadzania.add(spinner, gbc_spinner);
		
		JLabel lblWybrDiety = new JLabel(" Wybór diety");
		GridBagConstraints gbc_lblWybrDiety = new GridBagConstraints();
		gbc_lblWybrDiety.anchor = GridBagConstraints.WEST;
		gbc_lblWybrDiety.insets = new Insets(0, 0, 5, 5);
		gbc_lblWybrDiety.gridx = 0;
		gbc_lblWybrDiety.gridy = 19;
		panelwprowadzania.add(lblWybrDiety, gbc_lblWybrDiety);
		
		JComboBox comboBox_wybierz_diete = new JComboBox();
		comboBox_wybierz_diete.setModel(new DefaultComboBoxModel(new String[] {"Wybierz"}));
		comboBox_wybierz_diete.setEditable(true);
		GridBagConstraints gbc_comboBox_wybierz_dietę = new GridBagConstraints();
		gbc_comboBox_wybierz_dietę.gridwidth = 2;
		gbc_comboBox_wybierz_dietę.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_wybierz_dietę.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_wybierz_dietę.gridx = 0;
		gbc_comboBox_wybierz_dietę.gridy = 20;
		panelwprowadzania.add(comboBox_wybierz_diete, gbc_comboBox_wybierz_dietę);
		
		lblZakres = new JLabel(" Zakres (mbr)");
		GridBagConstraints gbc_lblZakres = new GridBagConstraints();
		gbc_lblZakres.anchor = GridBagConstraints.WEST;
		gbc_lblZakres.insets = new Insets(0, 0, 5, 5);
		gbc_lblZakres.gridx = 0;
		gbc_lblZakres.gridy = 21;
		panelwprowadzania.add(lblZakres, gbc_lblZakres);
		
		spinner_zakres = new JSpinner();
		spinner_zakres.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		spinnerTylkoCyfry(spinner_zakres);
		GridBagConstraints gbc_spinner_1 = new GridBagConstraints();
		gbc_spinner_1.gridwidth = 2;
		gbc_spinner_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_1.insets = new Insets(0, 0, 5, 0);
		gbc_spinner_1.gridx = 0;
		gbc_spinner_1.gridy = 22;
		panelwprowadzania.add(spinner_zakres, gbc_spinner_1);
		
		JLabel lblCzassek = new JLabel(" Czas (sek)");
		GridBagConstraints gbc_lblCzassek = new GridBagConstraints();
		gbc_lblCzassek.anchor = GridBagConstraints.WEST;
		gbc_lblCzassek.insets = new Insets(0, 0, 5, 5);
		gbc_lblCzassek.gridx = 0;
		gbc_lblCzassek.gridy = 23;
		panelwprowadzania.add(lblCzassek, gbc_lblCzassek);
		
		spinner_czas = new JSpinner();
		spinnerTylkoCyfry(spinner_czas);
		GridBagConstraints gbc_spinner_czas = new GridBagConstraints();
		gbc_spinner_czas.gridwidth = 2;
		gbc_spinner_czas.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_czas.insets = new Insets(0, 0, 5, 0);
		gbc_spinner_czas.gridx = 0;
		gbc_spinner_czas.gridy = 24;
		panelwprowadzania.add(spinner_czas, gbc_spinner_czas);
		
		JSeparator separator_3 = new JSeparator();
		GridBagConstraints gbc_separator_3 = new GridBagConstraints();
		gbc_separator_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_3.anchor = GridBagConstraints.NORTH;
		gbc_separator_3.gridwidth = 2;
		gbc_separator_3.insets = new Insets(0, 0, 5, 0);
		gbc_separator_3.gridx = 0;
		gbc_separator_3.gridy = 25;
		panelwprowadzania.add(separator_3, gbc_separator_3);
		
		JLabel label_5 = new JLabel(" BMR (kcal)");
		GridBagConstraints gbc_label_5 = new GridBagConstraints();
		gbc_label_5.anchor = GridBagConstraints.WEST;
		gbc_label_5.insets = new Insets(0, 0, 5, 5);
		gbc_label_5.gridx = 0;
		gbc_label_5.gridy = 26;
		panelwprowadzania.add(label_5, gbc_label_5);
		
		jtfWynik_BMR = new JTextField();
		jtfWynik_BMR.setEnabled(false);
		jtfWynik_BMR.setEditable(false);
		jtfWynik_BMR.setColumns(10);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridwidth = 2;
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.gridx = 0;
		gbc_textField_1.gridy = 27;
		panelwprowadzania.add(jtfWynik_BMR, gbc_textField_1);
		//Symulacja
		JButton JB_Symulacja = new JButton("Symulacja\r\n");
		JB_Symulacja.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				 
	             int[][] tab_genow = new int [100][15];
	             int [] tab_FC = new int [100];
	             int[][] tab_genow_selekcja = new int [100][15];
	             int[][] tab_genow_krzyzowanie = new int [100][15];
	             int kalorie_wyszukane;
	             int pomoc = 0;
	             int itertor = 0;
				// TODO Auto-generated method stub
	             double D_wzrost = Double.parseDouble(spinner_wzrost.getValue().toString());//wzrost = (int)jsWzrost.getValue();
                 wzrost = (int)D_wzrost;
                 double D_waga = (int)Double.parseDouble(spinner_waga.getValue().toString());
                 waga = (int)D_waga;
                 int D_wiek = Integer.parseInt(spinner_wiek.getValue().toString());
                 wiek = D_wiek;
                 
                 
                 GeneticAlgorithm gen = new GeneticAlgorithm(wzrost,waga,wiek);//,nr,produkt,n2);
                 
                 String wybór_plec = comboBox_Wybierz_plec.getSelectedItem().toString();
                 
                 
	             if(wybór_plec.equals("Kobieta")){ kalorie = (int)gen.symulacja_Kobiet();}
	             if(wybór_plec.equals("Mężczyzna")){ kalorie = (int)gen.symulacja_Kobiet();}
	            
	             //kolejne zmiany kalori!!!
	             
	             threads_Start.start();
	             
	             
	         } 
	         
			
			
		});
		GridBagConstraints gbc_JB_Symulacja = new GridBagConstraints();
		gbc_JB_Symulacja.fill = GridBagConstraints.BOTH;
		gbc_JB_Symulacja.gridwidth = 2;
		gbc_JB_Symulacja.insets = new Insets(0, 0, 5, 0);
		gbc_JB_Symulacja.gridx = 0;
		gbc_JB_Symulacja.gridy = 28;
		panelwprowadzania.add(JB_Symulacja, gbc_JB_Symulacja);
		
		JButton JB_Zapisz = new JButton("    Zapisz");
		GridBagConstraints gbc_JB_Zapisz = new GridBagConstraints();
		gbc_JB_Zapisz.fill = GridBagConstraints.BOTH;
		gbc_JB_Zapisz.gridwidth = 2;
		gbc_JB_Zapisz.insets = new Insets(0, 0, 5, 0);
		gbc_JB_Zapisz.gridx = 0;
		gbc_JB_Zapisz.gridy = 29;
		panelwprowadzania.add(JB_Zapisz, gbc_JB_Zapisz);
		JB_Zapisz.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				 
				zapiszDaneDoPliku();
	  	         } 
		});
		JButton JB_Drukuj = new JButton("  Drukuj");
		GridBagConstraints gbc_JB_Drukuj = new GridBagConstraints();
		gbc_JB_Drukuj.insets = new Insets(0, 0, 5, 0);
		gbc_JB_Drukuj.fill = GridBagConstraints.BOTH;
		gbc_JB_Drukuj.gridwidth = 2;
		gbc_JB_Drukuj.gridx = 0;
		gbc_JB_Drukuj.gridy = 30;
		panelwprowadzania.add(JB_Drukuj, gbc_JB_Drukuj);
		JB_Drukuj.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				 
				przygotujWydruki();
	         } 
		});
		JPanel paneltabela = new JPanel();
		Dieta.getContentPane().add(paneltabela, BorderLayout.CENTER);
		paneltabela.setLayout(new BorderLayout(12, 0));
		
		table = new JTable();
		table.setEnabled(false);
		table.setRowHeight(35);
		//table.setSize(new Dimension(211, 200));
		table.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"Poniedzia\u0142ek", "Wtorek", "\u015Aroda", "Czwartek", "Pi\u0105tek", "Sobota", "Niedziela"
			}
		));
		
		//table.setShowGrid(false);
		//Informaje
		JScrollPane jspTabela = new JScrollPane(table);
		jspTabela.setPreferredSize(new Dimension(300, 550));
	
		paneltabela.add(jspTabela, BorderLayout.NORTH);
		
		JPanel panel_lewa_tabela = new JPanel();
		jspTabela.setRowHeaderView(panel_lewa_tabela);
		
		JPanel panel_progres = new JPanel();
		paneltabela.add(panel_progres, BorderLayout.SOUTH);
		panel_progres.setLayout(new CardLayout(0, 0));
		
		progressBar_1 = new JProgressBar();
		progressBar_1.setForeground(Color.GREEN);
		panel_progres.add(progressBar_1);
		
		JPanel panel_lewa_dane = new JPanel();
		paneltabela.add(panel_lewa_dane, BorderLayout.WEST);
		
		JPanel panel_dane = new JPanel();
		paneltabela.add(panel_dane, BorderLayout.CENTER);
		GridBagLayout gbl_panel_dane = new GridBagLayout();
		gbl_panel_dane.columnWidths = new int[]{194, 209, 84, 39, 55, 153, 55, 53, 393, 0};
		gbl_panel_dane.rowHeights = new int[]{16, 0, 0, 2, 16, 16, 16, 20, 16, 16, 16, 0};
		gbl_panel_dane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_dane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_dane.setLayout(gbl_panel_dane);
		
		JLabel lblInforacjeSzczgowe = new JLabel("INFORMACJE SZCZEGÓŁOWE");
		lblInforacjeSzczgowe.setFont(font1);
		GridBagConstraints gbc_lblInforacjeSzczgowe = new GridBagConstraints();
		gbc_lblInforacjeSzczgowe.anchor = GridBagConstraints.WEST;
		gbc_lblInforacjeSzczgowe.fill = GridBagConstraints.VERTICAL;
		gbc_lblInforacjeSzczgowe.insets = new Insets(0, 0, 5, 5);
		gbc_lblInforacjeSzczgowe.gridwidth = 3;
		gbc_lblInforacjeSzczgowe.gridx = 2;
		gbc_lblInforacjeSzczgowe.gridy = 0;
		panel_dane.add(lblInforacjeSzczgowe, gbc_lblInforacjeSzczgowe);
		
		JSeparator separator_6 = new JSeparator();
		GridBagConstraints gbc_separator_6 = new GridBagConstraints();
		gbc_separator_6.gridwidth = 3;
		gbc_separator_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator_6.insets = new Insets(0, 0, 5, 5);
		gbc_separator_6.gridx = 2;
		gbc_separator_6.gridy = 1;
		panel_dane.add(separator_6, gbc_separator_6);
		
		JSeparator separator_5 = new JSeparator();
		GridBagConstraints gbc_separator_5 = new GridBagConstraints();
		gbc_separator_5.gridwidth = 9;
		gbc_separator_5.insets = new Insets(0, 0, 5, 0);
		gbc_separator_5.gridx = 0;
		gbc_separator_5.gridy = 2;
		panel_dane.add(separator_5, gbc_separator_5);
		
		JLabel lblImi = new JLabel("Imię");
		GridBagConstraints gbc_lblImi = new GridBagConstraints();
		gbc_lblImi.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblImi.anchor = GridBagConstraints.NORTH;
		gbc_lblImi.insets = new Insets(0, 0, 5, 5);
		gbc_lblImi.gridx = 0;
		gbc_lblImi.gridy = 4;
		panel_dane.add(lblImi, gbc_lblImi);
		
		lblWyfietlimie = new JLabel(imie);
		GridBagConstraints gbc_lblWyfietlimie = new GridBagConstraints();
		gbc_lblWyfietlimie.anchor = GridBagConstraints.NORTH;
		gbc_lblWyfietlimie.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblWyfietlimie.insets = new Insets(0, 0, 5, 5);
		gbc_lblWyfietlimie.gridx = 1;
		gbc_lblWyfietlimie.gridy = 4;
		panel_dane.add(lblWyfietlimie, gbc_lblWyfietlimie);
		
		JLabel lblPoniedziaek = new JLabel("Poniedziałek");
		GridBagConstraints gbc_lblPoniedziaek = new GridBagConstraints();
		gbc_lblPoniedziaek.anchor = GridBagConstraints.NORTH;
		gbc_lblPoniedziaek.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPoniedziaek.insets = new Insets(0, 0, 5, 5);
		gbc_lblPoniedziaek.gridx = 2;
		gbc_lblPoniedziaek.gridy = 4;
		panel_dane.add(lblPoniedziaek, gbc_lblPoniedziaek);
		
		JLabel lblPonbmr = new JLabel();
		GridBagConstraints gbc_lblPonbmr = new GridBagConstraints();
		gbc_lblPonbmr.anchor = GridBagConstraints.NORTH;
		gbc_lblPonbmr.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPonbmr.insets = new Insets(0, 0, 5, 5);
		gbc_lblPonbmr.gridx = 4;
		gbc_lblPonbmr.gridy = 4;
		panel_dane.add(lblPonbmr, gbc_lblPonbmr);
		
		JLabel lblCzas = new JLabel("Czas");
		GridBagConstraints gbc_lblCzas = new GridBagConstraints();
		gbc_lblCzas.anchor = GridBagConstraints.NORTH;
		gbc_lblCzas.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblCzas.insets = new Insets(0, 0, 5, 5);
		gbc_lblCzas.gridx = 6;
		gbc_lblCzas.gridy = 4;
		panel_dane.add(lblCzas, gbc_lblCzas);
		
		lblWyczas = new JLabel();
		GridBagConstraints gbc_lblWyczas = new GridBagConstraints();
		gbc_lblWyczas.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblWyczas.insets = new Insets(0, 0, 5, 0);
		gbc_lblWyczas.gridx = 8;
		gbc_lblWyczas.gridy = 4;
		panel_dane.add(lblWyczas, gbc_lblWyczas);
		
		JLabel lblNazwiskoo = new JLabel("Nazwisko");
		GridBagConstraints gbc_lblNazwiskoo = new GridBagConstraints();
		gbc_lblNazwiskoo.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNazwiskoo.anchor = GridBagConstraints.NORTH;
		gbc_lblNazwiskoo.insets = new Insets(0, 0, 5, 5);
		gbc_lblNazwiskoo.gridx = 0;
		gbc_lblNazwiskoo.gridy = 5;
		panel_dane.add(lblNazwiskoo, gbc_lblNazwiskoo);
		
		lblWywietlNazwisko = new JLabel(nazwisko);
		GridBagConstraints gbc_lblWywietlNazwisko = new GridBagConstraints();
		gbc_lblWywietlNazwisko.anchor = GridBagConstraints.NORTH;
		gbc_lblWywietlNazwisko.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblWywietlNazwisko.insets = new Insets(0, 0, 5, 5);
		gbc_lblWywietlNazwisko.gridx = 1;
		gbc_lblWywietlNazwisko.gridy = 5;
		panel_dane.add(lblWywietlNazwisko, gbc_lblWywietlNazwisko);
		
		JLabel lblWtorek = new JLabel("Wtorek");
		GridBagConstraints gbc_lblWtorek = new GridBagConstraints();
		gbc_lblWtorek.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblWtorek.insets = new Insets(0, 0, 5, 5);
		gbc_lblWtorek.gridx = 2;
		gbc_lblWtorek.gridy = 5;
		panel_dane.add(lblWtorek, gbc_lblWtorek);
		
		JLabel lblWtobmr = new JLabel();
		GridBagConstraints gbc_lblWtobmr = new GridBagConstraints();
		gbc_lblWtobmr.anchor = GridBagConstraints.NORTH;
		gbc_lblWtobmr.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblWtobmr.insets = new Insets(0, 0, 5, 5);
		gbc_lblWtobmr.gridx = 4;
		gbc_lblWtobmr.gridy = 5;
		panel_dane.add(lblWtobmr, gbc_lblWtobmr);
		
		JLabel lblPw = new JLabel("PW");
		GridBagConstraints gbc_lblPw = new GridBagConstraints();
		gbc_lblPw.anchor = GridBagConstraints.NORTH;
		gbc_lblPw.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPw.insets = new Insets(0, 0, 5, 5);
		gbc_lblPw.gridx = 6;
		gbc_lblPw.gridy = 5;
		panel_dane.add(lblPw, gbc_lblPw);
		
		lblWypw = new JLabel();
		GridBagConstraints gbc_lblWypw = new GridBagConstraints();
		gbc_lblWypw.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblWypw.insets = new Insets(0, 0, 5, 0);
		gbc_lblWypw.gridx = 8;
		gbc_lblWypw.gridy = 5;
		panel_dane.add(lblWypw, gbc_lblWypw);
		
		JLabel lblKodGentyczny = new JLabel("Kod gentyczny");
		GridBagConstraints gbc_lblKodGentyczny = new GridBagConstraints();
		gbc_lblKodGentyczny.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblKodGentyczny.anchor = GridBagConstraints.NORTH;
		gbc_lblKodGentyczny.insets = new Insets(0, 0, 5, 5);
		gbc_lblKodGentyczny.gridx = 0;
		gbc_lblKodGentyczny.gridy = 6;
		panel_dane.add(lblKodGentyczny, gbc_lblKodGentyczny);
		
		lblWywitlkod = new JLabel(""+kod);
		GridBagConstraints gbc_lblWywitlkod = new GridBagConstraints();
		gbc_lblWywitlkod.anchor = GridBagConstraints.NORTH;
		gbc_lblWywitlkod.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblWywitlkod.insets = new Insets(0, 0, 5, 5);
		gbc_lblWywitlkod.gridx = 1;
		gbc_lblWywitlkod.gridy = 6;
		panel_dane.add(lblWywitlkod, gbc_lblWywitlkod);
		
		JLabel lblroda = new JLabel("Środa");
		GridBagConstraints gbc_lblroda = new GridBagConstraints();
		gbc_lblroda.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblroda.insets = new Insets(0, 0, 5, 5);
		gbc_lblroda.gridx = 2;
		gbc_lblroda.gridy = 6;
		panel_dane.add(lblroda, gbc_lblroda);
		
		lblSrbmr = new JLabel();
		GridBagConstraints gbc_lblSrbmr = new GridBagConstraints();
		gbc_lblSrbmr.anchor = GridBagConstraints.NORTH;
		gbc_lblSrbmr.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblSrbmr.insets = new Insets(0, 0, 5, 5);
		gbc_lblSrbmr.gridx = 4;
		gbc_lblSrbmr.gridy = 6;
		panel_dane.add(lblSrbmr, gbc_lblSrbmr);
		
		JLabel lblFcPrim = new JLabel("FC prim");
		GridBagConstraints gbc_lblFcPrim = new GridBagConstraints();
		gbc_lblFcPrim.anchor = GridBagConstraints.NORTH;
		gbc_lblFcPrim.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblFcPrim.insets = new Insets(0, 0, 5, 5);
		gbc_lblFcPrim.gridx = 6;
		gbc_lblFcPrim.gridy = 6;
		panel_dane.add(lblFcPrim, gbc_lblFcPrim);
		
		lblWyfcprim = new JLabel();
		GridBagConstraints gbc_lblWyfcprim = new GridBagConstraints();
		gbc_lblWyfcprim.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblWyfcprim.insets = new Insets(0, 0, 5, 0);
		gbc_lblWyfcprim.gridx = 8;
		gbc_lblWyfcprim.gridy = 6;
		panel_dane.add(lblWyfcprim, gbc_lblWyfcprim);
		
		JLabel lblTwojeBmr = new JLabel("Twoje BMR");
		GridBagConstraints gbc_lblTwojeBmr = new GridBagConstraints();
		gbc_lblTwojeBmr.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblTwojeBmr.anchor = GridBagConstraints.NORTH;
		gbc_lblTwojeBmr.insets = new Insets(0, 0, 5, 5);
		gbc_lblTwojeBmr.gridx = 0;
		gbc_lblTwojeBmr.gridy = 7;
		panel_dane.add(lblTwojeBmr, gbc_lblTwojeBmr);
		
		lblMojeBmr = new JLabel();
		GridBagConstraints gbc_lblMojeBmr = new GridBagConstraints();
		gbc_lblMojeBmr.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblMojeBmr.insets = new Insets(0, 0, 5, 5);
		gbc_lblMojeBmr.gridx = 1;
		gbc_lblMojeBmr.gridy = 7;
		panel_dane.add(lblMojeBmr, gbc_lblMojeBmr);
		
		JLabel lblCzwartek = new JLabel("Czwartek");
		GridBagConstraints gbc_lblCzwartek = new GridBagConstraints();
		gbc_lblCzwartek.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblCzwartek.insets = new Insets(0, 0, 5, 5);
		gbc_lblCzwartek.gridx = 2;
		gbc_lblCzwartek.gridy = 7;
		panel_dane.add(lblCzwartek, gbc_lblCzwartek);
		
		JLabel lblCzbmr = new JLabel();
		GridBagConstraints gbc_lblCzbmr = new GridBagConstraints();
		gbc_lblCzbmr.anchor = GridBagConstraints.NORTH;
		gbc_lblCzbmr.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblCzbmr.insets = new Insets(0, 0, 5, 5);
		gbc_lblCzbmr.gridx = 4;
		gbc_lblCzbmr.gridy = 7;
		panel_dane.add(lblCzbmr, gbc_lblCzbmr);
		
		JLabel lblRmin = new JLabel("Rmin");
		GridBagConstraints gbc_lblRmin = new GridBagConstraints();
		gbc_lblRmin.anchor = GridBagConstraints.SOUTH;
		gbc_lblRmin.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblRmin.insets = new Insets(0, 0, 5, 5);
		gbc_lblRmin.gridx = 6;
		gbc_lblRmin.gridy = 7;
		panel_dane.add(lblRmin, gbc_lblRmin);
		
		lblWyrmin = new JLabel();
		GridBagConstraints gbc_lblWyrmin = new GridBagConstraints();
		gbc_lblWyrmin.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblWyrmin.insets = new Insets(0, 0, 5, 0);
		gbc_lblWyrmin.gridx = 8;
		gbc_lblWyrmin.gridy = 7;
		panel_dane.add(lblWyrmin, gbc_lblWyrmin);
		
		JLabel lbAdres = new JLabel("Adres");
		GridBagConstraints gbc_lbAdres = new GridBagConstraints();
		gbc_lbAdres.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbAdres.anchor = GridBagConstraints.NORTH;
		gbc_lbAdres.insets = new Insets(0, 0, 5, 5);
		gbc_lbAdres.gridx = 0;
		gbc_lbAdres.gridy = 8;
		panel_dane.add(lbAdres, gbc_lbAdres);
		
		JLabel lblWywietladres = new JLabel(adres);
		GridBagConstraints gbc_lblWywietladres = new GridBagConstraints();
		gbc_lblWywietladres.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblWywietladres.insets = new Insets(0, 0, 5, 5);
		gbc_lblWywietladres.gridx = 1;
		gbc_lblWywietladres.gridy = 8;
		panel_dane.add(lblWywietladres, gbc_lblWywietladres);
		
		JLabel lblPitek = new JLabel("Piątek");
		GridBagConstraints gbc_lblPitek = new GridBagConstraints();
		gbc_lblPitek.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblPitek.insets = new Insets(0, 0, 5, 5);
		gbc_lblPitek.gridx = 2;
		gbc_lblPitek.gridy = 8;
		panel_dane.add(lblPitek, gbc_lblPitek);
		
		JLabel lblPibmr = new JLabel();
		GridBagConstraints gbc_lblPibmr = new GridBagConstraints();
		gbc_lblPibmr.anchor = GridBagConstraints.NORTH;
		gbc_lblPibmr.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPibmr.insets = new Insets(0, 0, 5, 5);
		gbc_lblPibmr.gridx = 4;
		gbc_lblPibmr.gridy = 8;
		panel_dane.add(lblPibmr, gbc_lblPibmr);
		
		JLabel lblRmax = new JLabel("Rmax");
		GridBagConstraints gbc_lblRmax = new GridBagConstraints();
		gbc_lblRmax.anchor = GridBagConstraints.NORTH;
		gbc_lblRmax.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblRmax.insets = new Insets(0, 0, 5, 5);
		gbc_lblRmax.gridx = 6;
		gbc_lblRmax.gridy = 8;
		panel_dane.add(lblRmax, gbc_lblRmax);
		
		lblWyrmax = new JLabel();
		GridBagConstraints gbc_lblWyrmax = new GridBagConstraints();
		gbc_lblWyrmax.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblWyrmax.insets = new Insets(0, 0, 5, 0);
		gbc_lblWyrmax.gridx = 8;
		gbc_lblWyrmax.gridy = 8;
		panel_dane.add(lblWyrmax, gbc_lblWyrmax);
		
		JLabel lblNR_tel = new JLabel("Nr. telefonu");
		GridBagConstraints gbc_lblNR_tel = new GridBagConstraints();
		gbc_lblNR_tel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNR_tel.anchor = GridBagConstraints.NORTH;
		gbc_lblNR_tel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNR_tel.gridx = 0;
		gbc_lblNR_tel.gridy = 9;
		panel_dane.add(lblNR_tel, gbc_lblNR_tel);
		
		JLabel lblWywietlNr = new JLabel(""+nr_tel);
		GridBagConstraints gbc_lblWywietlNr = new GridBagConstraints();
		gbc_lblWywietlNr.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblWywietlNr.insets = new Insets(0, 0, 5, 5);
		gbc_lblWywietlNr.gridx = 1;
		gbc_lblWywietlNr.gridy = 9;
		panel_dane.add(lblWywietlNr, gbc_lblWywietlNr);
		
		JLabel lblSobota = new JLabel("Sobota");
		GridBagConstraints gbc_lblSobota = new GridBagConstraints();
		gbc_lblSobota.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblSobota.insets = new Insets(0, 0, 5, 5);
		gbc_lblSobota.gridx = 2;
		gbc_lblSobota.gridy = 9;
		panel_dane.add(lblSobota, gbc_lblSobota);
		
		JLabel lblSobbmr = new JLabel();
		GridBagConstraints gbc_lblSobbmr = new GridBagConstraints();
		gbc_lblSobbmr.anchor = GridBagConstraints.NORTH;
		gbc_lblSobbmr.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblSobbmr.insets = new Insets(0, 0, 5, 5);
		gbc_lblSobbmr.gridx = 4;
		gbc_lblSobbmr.gridy = 9;
		panel_dane.add(lblSobbmr, gbc_lblSobbmr);
		
		JLabel lblPentle = new JLabel("Pentle");
		GridBagConstraints gbc_lblPentle = new GridBagConstraints();
		gbc_lblPentle.anchor = GridBagConstraints.NORTH;
		gbc_lblPentle.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPentle.insets = new Insets(0, 0, 5, 5);
		gbc_lblPentle.gridx = 6;
		gbc_lblPentle.gridy = 9;
		panel_dane.add(lblPentle, gbc_lblPentle);
		
		lblWypetle = new JLabel();
		GridBagConstraints gbc_lblWypetle = new GridBagConstraints();
		gbc_lblWypetle.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblWypetle.insets = new Insets(0, 0, 5, 0);
		gbc_lblWypetle.gridx = 8;
		gbc_lblWypetle.gridy = 9;
		panel_dane.add(lblWypetle, gbc_lblWypetle);
		
		JLabel lblNiedziela = new JLabel("Niedziela");
		GridBagConstraints gbc_lblNiedziela = new GridBagConstraints();
		gbc_lblNiedziela.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblNiedziela.insets = new Insets(0, 0, 0, 5);
		gbc_lblNiedziela.gridx = 2;
		gbc_lblNiedziela.gridy = 10;
		panel_dane.add(lblNiedziela, gbc_lblNiedziela);
		
		JLabel lblNiebmr = new JLabel();
		GridBagConstraints gbc_lblNiebmr = new GridBagConstraints();
		gbc_lblNiebmr.anchor = GridBagConstraints.NORTH;
		gbc_lblNiebmr.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNiebmr.insets = new Insets(0, 0, 0, 5);
		gbc_lblNiebmr.gridx = 4;
		gbc_lblNiebmr.gridy = 10;
		panel_dane.add(lblNiebmr, gbc_lblNiebmr);
		
		JLabel lblFcSuma = new JLabel("FC' ");
		GridBagConstraints gbc_lblFcSuma = new GridBagConstraints();
		gbc_lblFcSuma.anchor = GridBagConstraints.NORTH;
		gbc_lblFcSuma.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblFcSuma.insets = new Insets(0, 0, 0, 5);
		gbc_lblFcSuma.gridx = 6;
		gbc_lblFcSuma.gridy = 10;
		panel_dane.add(lblFcSuma, gbc_lblFcSuma);
		
		lblWyfc = new JLabel();
		GridBagConstraints gbc_lblWyfc = new GridBagConstraints();
		gbc_lblWyfc.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblWyfc.gridx = 8;
		gbc_lblWyfc.gridy = 10;
		panel_dane.add(lblWyfc, gbc_lblWyfc);
		
		JPanel panel_gora = new JPanel();
		Dieta.getContentPane().add(panel_gora, BorderLayout.NORTH);
		
		JPanel panel_dol = new JPanel();
		Dieta.getContentPane().add(panel_dol, BorderLayout.SOUTH);
		
		JInternalFrame Trening = new JInternalFrame("Plan ćwiczeń");
		tabbedPane.addTab("Trening", null, Trening, null);
		JPanel panel_Trenig_menu = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_Trenig_menu.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		flowLayout.setAlignOnBaseline(true);
		Trening .getContentPane().add(panel_Trenig_menu, BorderLayout.NORTH);
		
		flowLayout.setAlignment(FlowLayout.LEFT);
		flowLayout.setAlignOnBaseline(true);
		Trening .getContentPane().add(panel_Trenig_menu, BorderLayout.NORTH);
		
		JPanel panel_Trenig_west = new JPanel();
		Trening.getContentPane().add(panel_Trenig_west, BorderLayout.WEST);
		
		JPanel panel_Trenig_center = new JPanel();
		Trening.getContentPane().add(panel_Trenig_center, BorderLayout.CENTER);
		panel_Trenig_center.setLayout(new BorderLayout(0, 0));
		
		 ActionListener akcja_zaznaczenia_bieg_szybki = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        boolean selected = abstractButton.getModel().isSelected();
		        if(selected==true)
		        {
		        	spalanie = 1100;
		        	nazwa = "Bieg szybki ";
		        	
		        }
		       
		      }
		    };
		 ActionListener akcja_zaznaczenia_Zamiatanie_Podogi = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        boolean selected = abstractButton.getModel().isSelected();
		        if(selected==true)
		        {
		        	spalanie = 175;
		        	nazwa = "Zamiatanie pogłogi ";
		        	
		        }
		       
		      }
		    };
		 ActionListener akcja_zaznaczenia_Zawieszeni_Firanek = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        boolean selected = abstractButton.getModel().isSelected();
		        if(selected==true)
		        {
		        	spalanie = 410;
		        	nazwa = "Zawieszanie firanek ";
		        	
		        }
		       
		      }
		    };
		 ActionListener akcja_zaznaczenia_Zmieninie_Pocieli = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        boolean selected = abstractButton.getModel().isSelected();
		        if(selected==true)
		        {
		        	spalanie = 185;
		        	nazwa = "Zawieszanie pościeli ";
		        	
		        }
		       
		      }
		    };
		ActionListener akcja_zaznaczenia_Zmywanie_Naczy = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        boolean selected = abstractButton.getModel().isSelected();
		        if(selected==true)
		        {
		        	spalanie = 185;
		        	nazwa = "Zawieszanie pościeli ";
		        	
		        }
		       
		      }
		    };
		ActionListener combo_wybierz_dzien = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        
		    	  if(comboBoxWybierzdzien.getSelectedIndex() == 1){dzien=1;}
		    	  if(comboBoxWybierzdzien.getSelectedIndex() == 2){dzien=2;}
		    	  if(comboBoxWybierzdzien.getSelectedIndex() == 3){dzien=3;}
		    	  if(comboBoxWybierzdzien.getSelectedIndex() == 4){dzien=4;}
		    	  if(comboBoxWybierzdzien.getSelectedIndex() == 5){dzien=5;}
		    	  if(comboBoxWybierzdzien.getSelectedIndex() == 6){dzien=6;}
		    	  if(comboBoxWybierzdzien.getSelectedIndex() == 7){dzien=7;}
		    	 
		      }
		    };
		 ActionListener akcja_zaznaczenia_bieg_wolny = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        boolean selected = abstractButton.getModel().isSelected();
		        if(selected==true)
		        {
		        	spalanie = 123;
		        	nazwa = "Bieg wolny ";
		        	
		        }
		       
		      }
		    };
		ActionListener akcja_zaznaczenia_Gra_WSiatkwke = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        boolean selected = abstractButton.getModel().isSelected();
		        if(selected==true)
		        {
		        	spalanie = 630;
		        	nazwa = "Gra w siatke";
		        	
		        }
		       
		      }
		    };
		ActionListener akcja_zaznaczenia_Jedzenie = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        boolean selected = abstractButton.getModel().isSelected();
		        if(selected==true)
		        {
		        	spalanie = 90;
		        	nazwa = "Jedzenie";
		        	
		        }
		       
		      }
		    };
		
		ActionListener akcja_zaznaczenia_Prasowanie = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        boolean selected = abstractButton.getModel().isSelected();
		        if(selected==true)
		        {
		        	spalanie = 125;
		        	nazwa = "Prasowanie";
		        	
		        }
		       
		      }
		    };
		ActionListener akcja_zaznaczenia_Stepaerobik = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        boolean selected = abstractButton.getModel().isSelected();
		        if(selected==true)
		        {
		        	spalanie = 550;
		        	nazwa = "Step-aerobik";
		        	
		        }
		       
		      }
		    };
		ActionListener akcja_zaznaczenia_Boks = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        boolean selected = abstractButton.getModel().isSelected();
		        if(selected==true)
		        {
		        	spalanie = 850;
		        	nazwa = "Boks";
		        	
		        }
		       
		      }
		    };
		ActionListener akcja_zaznaczenia_Gra_W_Tenisa = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        boolean selected = abstractButton.getModel().isSelected();
		        if(selected==true)
		        {
		        	spalanie = 920;
		        	nazwa = "Gra w tenisa";
		        	
		        }
		       
		      }
		    };
		ActionListener akcja_zaznaczenia_Leenie = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        boolean selected = abstractButton.getModel().isSelected();
		        if(selected==true)
		        {
		        	spalanie = 70;
		        	nazwa = "Leżenie";
		        	
		        }
		       
		      }
		    };
		ActionListener akcja_zaznaczenia_Rozwieszanie_Prania = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        boolean selected = abstractButton.getModel().isSelected();
		        if(selected==true)
		        {
		        	spalanie = 290;
		        	nazwa = "Rozwieszanie prania";
		        	
		        }
		       
		      }
		    };
		ActionListener akcja_zaznaczenia_Spiewanie = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        boolean selected = abstractButton.getModel().isSelected();
		        if(selected==true)
		        {
		        	spalanie = 130;
		        	nazwa = "Śpiewanie";
		        	
		        }
		       
		      }
		    };
		ActionListener combo_od = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        
		    	  if(comboBox_od_godziny.getSelectedIndex() == 1){w=1;}
		    	  if(comboBox_od_godziny.getSelectedIndex() == 2){w=2;}
		    	  if(comboBox_od_godziny.getSelectedIndex() == 3){w=3;}
		    	  if(comboBox_od_godziny.getSelectedIndex() == 4){w=4;}
		    	  if(comboBox_od_godziny.getSelectedIndex() == 5){w=5;}
		    	  if(comboBox_od_godziny.getSelectedIndex() == 6){w=6;}
		    	  if(comboBox_od_godziny.getSelectedIndex() == 7){w=7;}
		    	  if(comboBox_od_godziny.getSelectedIndex() == 8){w=8;}
		    	  if(comboBox_od_godziny.getSelectedIndex() == 9){w=9;}
		    	  if(comboBox_od_godziny.getSelectedIndex() == 10){w=10;}
		    	  if(comboBox_od_godziny.getSelectedIndex() == 11){w=11;}
		    	  if(comboBox_od_godziny.getSelectedIndex() == 12){w=12;}
		    	  if(comboBox_od_godziny.getSelectedIndex() == 13){w=13;}
		    	  if(comboBox_od_godziny.getSelectedIndex() == 14){w=14;}
		    	  if(comboBox_od_godziny.getSelectedIndex() == 15){w=15;}
		    	  if(comboBox_od_godziny.getSelectedIndex() == 16){w=16;}
		       
		      }
		    };
		ActionListener akcja_zaznaczenia_Czolganie_sie = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        boolean selected = abstractButton.getModel().isSelected();
		        if(selected==true)
		        {
		        	spalanie = 780;
		        	nazwa = "Czołganie się";
		        	
		        }
		       
		      }
		    };
		ActionListener akcja_zaznaczenia_Jazda_Motocyklem = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        boolean selected = abstractButton.getModel().isSelected();
		        if(selected==true)
		        {
		        	spalanie = 190;
		        	nazwa = "Jazda motocyklem";
		        	
		        }
		       
		      }
		    };
		ActionListener akcja_zaznaczenia_Mycie_Okien = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        boolean selected = abstractButton.getModel().isSelected();
		        if(selected==true)
		        {
		        	spalanie = 250;
		        	nazwa = "Mycie okien";
		        	
		        }
		       
		      }
		    };
		ActionListener akcja_zaznaczenia_Sen = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        boolean selected = abstractButton.getModel().isSelected();
		        if(selected==true)
		        {
		        	spalanie = 70;
		        	nazwa = "Sen";
		        	
		        }
		       
		      }
		    };
		ActionListener akcja_zaznaczenia_Taniec= new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        boolean selected = abstractButton.getModel().isSelected();
		        if(selected==true)
		        {
		        	spalanie = 390;
		        	nazwa = "Taniec";
		        	
		        }
		       
		      }
		    };
		
		ActionListener combo_do = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        
		    	  if(comboBox_do_godziny.getSelectedIndex() == 1){wk=1;}
		    	  if(comboBox_do_godziny.getSelectedIndex() == 2){wk=2;}
		    	  if(comboBox_do_godziny.getSelectedIndex() == 3){wk=3;}
		    	  if(comboBox_do_godziny.getSelectedIndex() == 4){wk=4;}
		    	  if(comboBox_do_godziny.getSelectedIndex() == 5){wk=5;}
		    	  if(comboBox_do_godziny.getSelectedIndex() == 6){wk=6;}
		    	  if(comboBox_do_godziny.getSelectedIndex() == 7){wk=7;}
		    	  if(comboBox_do_godziny.getSelectedIndex() == 8){wk=8;}
		    	  if(comboBox_do_godziny.getSelectedIndex() == 9){wk=9;}
		    	  if(comboBox_do_godziny.getSelectedIndex() == 10){wk=10;}
		    	  if(comboBox_do_godziny.getSelectedIndex() == 11){wk=11;}
		    	  if(comboBox_do_godziny.getSelectedIndex() == 12){wk=12;}
		    	  if(comboBox_do_godziny.getSelectedIndex() == 13){wk=13;}
		    	  if(comboBox_do_godziny.getSelectedIndex() == 14){wk=14;}
		    	  if(comboBox_do_godziny.getSelectedIndex() == 15){wk=15;}
		    	  if(comboBox_do_godziny.getSelectedIndex() == 16){wk=16;}
		       
		      }
		    };
		ActionListener akcja_zaznaczenia_Czytanie_na_głos= new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        boolean selected = abstractButton.getModel().isSelected();
		        if(selected==true)
		        {
		        	spalanie = 100;
		        	nazwa = "Czytanie na głos";
		        	
		        }
		       
		      }
		    };
		ActionListener akcja_zaznaczenia_Jazda_Naywach= new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        boolean selected = abstractButton.getModel().isSelected();
		        if(selected==true)
		        {
		        	spalanie = 725;
		        	nazwa = "Jazda na łyżwach";
		        	
		        }
		       
		      }
		    };
		ActionListener akcja_zaznaczenia_Odkurzenie_Mebli= new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        boolean selected = abstractButton.getModel().isSelected();
		        if(selected==true)
		        {
		        	spalanie = 215;
		        	nazwa = "Odkurzanie mebli";
		        	
		        }
		       
		      }
		    };
		ActionListener akcja_zaznaczenia_Sedzenie= new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        boolean selected = abstractButton.getModel().isSelected();
		        if(selected==true)
		        {
		        	spalanie = 90;
		        	nazwa = "Siedzenie";
		        	
		        }
		       
		      }
		    };
		ActionListener akcja_zaznaczenia_Trzepanie_Dywanw= new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        boolean selected = abstractButton.getModel().isSelected();
		        if(selected==true)
		        {
		        	spalanie = 350;
		        	nazwa = "Trzepanie dywanów";
		        	
		        }
		       
		      }
		    };
		ActionListener akcja_zaznaczenia_Gimnastyka_Forsowna = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        boolean selected = abstractButton.getModel().isSelected();
		        if(selected==true)
		        {
		        	spalanie = 300;
		        	nazwa = "Gimnastyka forsowa";
		        }
		       
		      }
		    };
		ActionListener akcja_zaznaczenia_Jazda_Na_Nartach = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        boolean selected = abstractButton.getModel().isSelected();
		        if(selected==true)
		        {
		        	spalanie = 620;
		        	nazwa = "Jazda na nartach";
		        }
		       
		      }
		    };
		ActionListener akcja_zaznaczenia_Odkurzenie_Odkurzaczem = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        boolean selected = abstractButton.getModel().isSelected();
		        if(selected==true)
		        {
		        	spalanie = 230;
		        	nazwa = "Odkurzanie odkurzaczem";
		        }
		       
		      }
		    };
		ActionListener akcja_zaznaczenia_Spacer_Wolny = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        boolean selected = abstractButton.getModel().isSelected();
		        if(selected==true)
		        {
		        	spalanie = 215;
		        	nazwa = "Spacer wolny";
		        }
		       
		      }
		    };
		ActionListener akcja_zaznaczenia_Ubieranie_Si = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        boolean selected = abstractButton.getModel().isSelected();
		        if(selected==true)
		        {
		        	spalanie = 115;
		        	nazwa = "Ubieranie się";
		        }
		       
		      }
		    };
		ActionListener akcja_zaznaczenia_Gimnastyka_Lekka = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        boolean selected = abstractButton.getModel().isSelected();
		        if(selected==true)
		        {
		        	spalanie = 180;
		        	nazwa = "Gimnastyka lekka";
		        }
		       
		      }
		    };
		ActionListener akcja_zaznaczenia_Jazda_Na_Rowerze = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        boolean selected = abstractButton.getModel().isSelected();
		        if(selected==true)
		        {
		        	spalanie = 260;
		        	nazwa = "Jazda na rowerze";	
		        }
		       
		      }
		    };
		ActionListener akcja_zaznaczenia_Odnierzanie = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        boolean selected = abstractButton.getModel().isSelected();
		        if(selected==true)
		        {
		        	spalanie = 430;
		        	nazwa = "Odśnierzanie";
		        }
		       
		      }
		    };
		ActionListener akcja_zaznaczenia_chckbxSpacer_Szybki = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        boolean selected = abstractButton.getModel().isSelected();
		        if(selected==true)
		        {
		        	spalanie = 310;
		        	nazwa = "Spacer szybki";
		        }
		       
		      }
		    };
		ActionListener akcja_zaznaczenia_chckbxWbieganie_Po_Schodach = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        boolean selected = abstractButton.getModel().isSelected();
		        if(selected==true)
		        {
		        	spalanie = 980;
		        	nazwa = "Wbieganie po schodach";
		        }
		       
		      }
		    };
		ActionListener akcja_zaznaczenia_chckbxGra_W_Golfa = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        boolean selected = abstractButton.getModel().isSelected();
		        if(selected==true)
		        {
		        	spalanie = 390;
		        	nazwa = "Gra w golfa";
		        }
		       
		      }
		    };
		ActionListener akcja_zaznaczenia_Jazda_Na_Rowerze_1 = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        boolean selected = abstractButton.getModel().isSelected();
		        if(selected==true)
		        {
		        	spalanie = 650;
		        	nazwa = "Jazda na rowerze";
		        }
		       
		      }
		    };
		ActionListener akcja_zaznaczenia_Pisanie_Na_Maszynie = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        boolean selected = abstractButton.getModel().isSelected();
		        if(selected==true)
		        {
		        	spalanie = 115;
		        	nazwa = "Pisanie na maszynie";
		        }
		       
		      }
		    };
		ActionListener akcja_zaznaczenia_Stanie = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        boolean selected = abstractButton.getModel().isSelected();
		        if(selected==true)
		        {
		        	spalanie = 100;
		        	nazwa = "Stanie";	
		        }
		       
		      }
		    };
		ActionListener akcja_zaznaczenia_Wiosowanie = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        boolean selected = abstractButton.getModel().isSelected();
		        if(selected==true)
		        {
		        	spalanie = 340;
		        	nazwa = "Wiosłowanie";
		        }
		       
		      }
		    };
		ActionListener akcja_zaznaczenia_Jazda_Samochdem = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        boolean selected = abstractButton.getModel().isSelected();
		        if(selected==true)
		        {
		        	spalanie = 120;
		        	nazwa = "Jazda samochodem";
		        }
		       
		      }
		    };
		ActionListener akcja_zaznaczenia_Pywanie = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        boolean selected = abstractButton.getModel().isSelected();
		        if(selected==true)
		        {
		        	spalanie = 520;
		        	nazwa = "Pływanie";
		        }
		       
		      }
		    };
		ActionListener akcja_zaznaczenia_Stanie_Na_Baczn = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        boolean selected = abstractButton.getModel().isSelected();
		        if(selected==true)
		        {
		        	spalanie = 110;
		        	nazwa = "Stanie na baczno";
		        }
		       
		      }
		    };
		ActionListener akcja_zaznaczenia_Wchdzenie_Po_Schodach = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
		        boolean selected = abstractButton.getModel().isSelected();
		        if(selected==true)
		        {
		        	spalanie = 500;
		        	nazwa = "Wchodzenie po schodach";
		        }
		       
		      }
		    };
		ActionListener akcja_Ustaw = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		       // tutaj pojedyncze
		        kolumna = Integer.parseInt(spinner_kolumny.getValue().toString());
				wiersz = Integer.parseInt(spinner_wiersz.getValue().toString());
				int ile_czasu = Integer.parseInt(spinner_ile.getValue().toString());
			    table_trenig.setValueAt(nazwa, wiersz-1, kolumna);
			 
				spalanie=(spalanie*(ile_czasu/60));
				
				tab[kolumna][wiersz] = spalanie;
				
				int liczbaSuma = 0;
				for (int i = 0; i < tab.length; i++) {
					for (int j = 0; j < tab[i].length; j++)
					{
						liczbaSuma += tab[i][j];
						jtfSpalanie_trenig.setText(""+liczbaSuma);
					}
				}
				
		      }

		    };
		
		table_trenig =  new JTable();
		//table_trenig.setEnabled(false);
		//table.setRowHeight(25);//rozmiar wiersza nie chcę dziać !!!
		table_trenig.setSurrendersFocusOnKeystroke(true);
		table_trenig.setModel(new DefaultTableModel(
			new Object[][] {
				{"07:00", null, null, null, null, null, null, null},
				{"08:00", null, null, null, null, null, null, null},
				{"09:00", null, null, null, null, null, null, null},
				{"10:00", null, null, null, null, null, null, null},
				{"11:00", null, null, null, null, null, null, null},
				{"12:00", null, null, null, null, null, null, null},
				{"13:00", null, null, null, null, null, null, null},
				{"14:00", null, null, null, null, null, null, null},
				{"15:00", null, null, null, null, null, null, null},
				{"16:00", null, null, null, null, null, null, null},
				{"17:00", null, null, null, null, null, null, null},
				{"18:00", null, null, null, null, null, null, null},
				{"19:00", null, null, null, null, null, null, null},
				{"20:00", null, null, null, null, null, null, null},
				{"21:00", null, null, null, null, null, null, null},
				{"22:00", null, null, null, null, null, null, null},
				{"23:00", null, null, null, null, null, null, null},
			},
			new String[] {
				"Godzina", "Poniedzia\u0142ek", "Wtorek", "\u015Aroda", "Czwartek", "Pi\u0105tek", "Sobota", "Niedziela"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		JScrollPane jsptabelatrenig = new JScrollPane(table_trenig);
		panel_Trenig_center.add(jsptabelatrenig, BorderLayout.NORTH);
		jsptabelatrenig.setPreferredSize(new Dimension(945, 380));
		
		JPanel panel_t1 = new JPanel();
		panel_t1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_Trenig_center.add(panel_t1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_t1 = new GridBagLayout();
		gbl_panel_t1.columnWidths = new int[]{145, 146, 161, 175, 37, 177, 81, 1, 36, 151, 31, 139, 0};
		gbl_panel_t1.rowHeights = new int[]{122, 13, 2, 10, 5, 7, 8, 5, 10, 2, 9, 3, 29, 27, 26, 31, 24, 24, 97, 0};
		gbl_panel_t1.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_t1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_t1.setLayout(gbl_panel_t1);
		
		JSeparator separator7 = new JSeparator();
		separator7.setOrientation(SwingConstants.VERTICAL);
		GridBagConstraints gbc_separator7 = new GridBagConstraints();
		gbc_separator7.fill = GridBagConstraints.BOTH;
		gbc_separator7.insets = new Insets(0, 0, 0, 5);
		gbc_separator7.gridheight = 19;
		gbc_separator7.gridx = 7;
		gbc_separator7.gridy = 0;
		panel_t1.add(separator7, gbc_separator7);
		
		JLabel lblWybierzFormAktywnoci_1 = new JLabel("Wybierz formę aktywności");
		GridBagConstraints gbc_lblWybierzFormAktywnoci_1 = new GridBagConstraints();
		gbc_lblWybierzFormAktywnoci_1.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblWybierzFormAktywnoci_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblWybierzFormAktywnoci_1.gridx = 0;
		gbc_lblWybierzFormAktywnoci_1.gridy = 5;
		panel_t1.add(lblWybierzFormAktywnoci_1, gbc_lblWybierzFormAktywnoci_1);
		GridBagConstraints gbc_chckbxZmywanieNaczy = new GridBagConstraints();
		gbc_chckbxZmywanieNaczy.anchor = GridBagConstraints.NORTH;
		gbc_chckbxZmywanieNaczy.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxZmywanieNaczy.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxZmywanieNaczy.gridx = 0;
		gbc_chckbxZmywanieNaczy.gridy = 6;
		panel_t1.add(chckbxZmywanieNaczy, gbc_chckbxZmywanieNaczy);
		
		    chckbxZmywanieNaczy.addActionListener(akcja_zaznaczenia_Zmywanie_Naczy);
		GridBagConstraints gbc_chckbxZawieszeniFiranek = new GridBagConstraints();
		gbc_chckbxZawieszeniFiranek.anchor = GridBagConstraints.NORTHWEST;
		gbc_chckbxZawieszeniFiranek.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxZawieszeniFiranek.gridx = 1;
		gbc_chckbxZawieszeniFiranek.gridy = 6;
		panel_t1.add(chckbxZawieszeniFiranek, gbc_chckbxZawieszeniFiranek);
		
		chckbxZawieszeniFiranek.addActionListener(akcja_zaznaczenia_Zawieszeni_Firanek);
		GridBagConstraints gbc_chckbxWchdzeniePoSchodach = new GridBagConstraints();
		gbc_chckbxWchdzeniePoSchodach.anchor = GridBagConstraints.NORTHWEST;
		gbc_chckbxWchdzeniePoSchodach.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxWchdzeniePoSchodach.gridx = 2;
		gbc_chckbxWchdzeniePoSchodach.gridy = 6;
		panel_t1.add(chckbxWchdzeniePoSchodach, gbc_chckbxWchdzeniePoSchodach);
		chckbxWchdzeniePoSchodach.addActionListener(akcja_zaznaczenia_Wchdzenie_Po_Schodach);
		GridBagConstraints gbc_chckbxJazdaMotocyklem = new GridBagConstraints();
		gbc_chckbxJazdaMotocyklem.anchor = GridBagConstraints.NORTHWEST;
		gbc_chckbxJazdaMotocyklem.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxJazdaMotocyklem.gridx = 3;
		gbc_chckbxJazdaMotocyklem.gridy = 6;
		panel_t1.add(chckbxJazdaMotocyklem, gbc_chckbxJazdaMotocyklem);
		chckbxJazdaMotocyklem.addActionListener(akcja_zaznaczenia_Jazda_Motocyklem);
		GridBagConstraints gbc_chckbxStanieNaBaczno = new GridBagConstraints();
		gbc_chckbxStanieNaBaczno.anchor = GridBagConstraints.NORTHWEST;
		gbc_chckbxStanieNaBaczno.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxStanieNaBaczno.gridx = 5;
		gbc_chckbxStanieNaBaczno.gridy = 6;
		panel_t1.add(chckbxStanieNaBaczno, gbc_chckbxStanieNaBaczno);
		chckbxStanieNaBaczno.addActionListener(akcja_zaznaczenia_Stanie_Na_Baczn);
		
		JLabel lblWybierzDyspozycjnao = new JLabel("Wybierz dyspozycjnaość");
		GridBagConstraints gbc_lblWybierzDyspozycjnao = new GridBagConstraints();
		gbc_lblWybierzDyspozycjnao.fill = GridBagConstraints.BOTH;
		gbc_lblWybierzDyspozycjnao.insets = new Insets(0, 0, 5, 5);
		gbc_lblWybierzDyspozycjnao.gridx = 9;
		gbc_lblWybierzDyspozycjnao.gridy = 6;
		panel_t1.add(lblWybierzDyspozycjnao, gbc_lblWybierzDyspozycjnao);
		
		JLabel lblDokadneUstawienia = new JLabel("Dokładne ustawienia");
		GridBagConstraints gbc_lblDokadneUstawienia = new GridBagConstraints();
		gbc_lblDokadneUstawienia.fill = GridBagConstraints.BOTH;
		gbc_lblDokadneUstawienia.insets = new Insets(0, 0, 5, 0);
		gbc_lblDokadneUstawienia.gridx = 11;
		gbc_lblDokadneUstawienia.gridy = 6;
		panel_t1.add(lblDokadneUstawienia, gbc_lblDokadneUstawienia);
		GridBagConstraints gbc_chckbxTrzepanieDywanw = new GridBagConstraints();
		gbc_chckbxTrzepanieDywanw.anchor = GridBagConstraints.NORTH;
		gbc_chckbxTrzepanieDywanw.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxTrzepanieDywanw.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxTrzepanieDywanw.gridx = 0;
		gbc_chckbxTrzepanieDywanw.gridy = 7;
		panel_t1.add(chckbxTrzepanieDywanw, gbc_chckbxTrzepanieDywanw);
		chckbxTrzepanieDywanw.addActionListener(akcja_zaznaczenia_Trzepanie_Dywanw);
		GridBagConstraints gbc_chckbxbiegwolny = new GridBagConstraints();
		gbc_chckbxbiegwolny.anchor = GridBagConstraints.NORTH;
		gbc_chckbxbiegwolny.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxbiegwolny.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxbiegwolny.gridx = 1;
		gbc_chckbxbiegwolny.gridy = 7;
		panel_t1.add(chckbxbiegwolny, gbc_chckbxbiegwolny);
		
		chckbxbiegwolny.addActionListener(akcja_zaznaczenia_bieg_wolny);
		GridBagConstraints gbc_chckbxPisanieNaMaszynie = new GridBagConstraints();
		gbc_chckbxPisanieNaMaszynie.anchor = GridBagConstraints.NORTHWEST;
		gbc_chckbxPisanieNaMaszynie.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxPisanieNaMaszynie.gridx = 2;
		gbc_chckbxPisanieNaMaszynie.gridy = 7;
		panel_t1.add(chckbxPisanieNaMaszynie, gbc_chckbxPisanieNaMaszynie);
		chckbxPisanieNaMaszynie.addActionListener(akcja_zaznaczenia_Pisanie_Na_Maszynie);
		GridBagConstraints gbc_chckbxJazdaNaRowerze_1 = new GridBagConstraints();
		gbc_chckbxJazdaNaRowerze_1.anchor = GridBagConstraints.NORTH;
		gbc_chckbxJazdaNaRowerze_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxJazdaNaRowerze_1.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxJazdaNaRowerze_1.gridx = 3;
		gbc_chckbxJazdaNaRowerze_1.gridy = 7;
		panel_t1.add(chckbxJazdaNaRowerze_1, gbc_chckbxJazdaNaRowerze_1);
		chckbxJazdaNaRowerze_1.addActionListener(akcja_zaznaczenia_Jazda_Na_Rowerze_1);
		GridBagConstraints gbc_chckbxSpacerWolny = new GridBagConstraints();
		gbc_chckbxSpacerWolny.anchor = GridBagConstraints.NORTHWEST;
		gbc_chckbxSpacerWolny.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxSpacerWolny.gridx = 5;
		gbc_chckbxSpacerWolny.gridy = 7;
		panel_t1.add(chckbxSpacerWolny, gbc_chckbxSpacerWolny);
		chckbxSpacerWolny.addActionListener(akcja_zaznaczenia_Spacer_Wolny);
		
		comboBoxWybierzdzien = new JComboBox();
		comboBoxWybierzdzien.setModel(new DefaultComboBoxModel(new String[] {"Wybierz", "Poniedziełek", "Wtorek ", "Środa ", "Czwartek", "Piątek", "Sobota ", "Niedziela", "Cały tydzień"}));
		GridBagConstraints gbc_comboBoxWybierzdzien = new GridBagConstraints();
		gbc_comboBoxWybierzdzien.anchor = GridBagConstraints.NORTH;
		gbc_comboBoxWybierzdzien.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxWybierzdzien.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxWybierzdzien.gridx = 9;
		gbc_comboBoxWybierzdzien.gridy = 7;
		panel_t1.add(comboBoxWybierzdzien, gbc_comboBoxWybierzdzien);
		comboBoxWybierzdzien.addActionListener(combo_wybierz_dzien);
		
		JLabel lblKolumny = new JLabel("Dzień");
		GridBagConstraints gbc_lblKolumny = new GridBagConstraints();
		gbc_lblKolumny.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblKolumny.insets = new Insets(0, 0, 5, 0);
		gbc_lblKolumny.gridx = 11;
		gbc_lblKolumny.gridy = 7;
		panel_t1.add(lblKolumny, gbc_lblKolumny);
		GridBagConstraints gbc_chckbxTaniec = new GridBagConstraints();
		gbc_chckbxTaniec.anchor = GridBagConstraints.NORTH;
		gbc_chckbxTaniec.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxTaniec.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxTaniec.gridx = 0;
		gbc_chckbxTaniec.gridy = 8;
		panel_t1.add(chckbxTaniec, gbc_chckbxTaniec);
		chckbxTaniec.addActionListener(akcja_zaznaczenia_Taniec);
		GridBagConstraints gbc_chckbxOdkurzenieMebli = new GridBagConstraints();
		gbc_chckbxOdkurzenieMebli.anchor = GridBagConstraints.NORTH;
		gbc_chckbxOdkurzenieMebli.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxOdkurzenieMebli.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxOdkurzenieMebli.gridx = 1;
		gbc_chckbxOdkurzenieMebli.gridy = 8;
		panel_t1.add(chckbxOdkurzenieMebli, gbc_chckbxOdkurzenieMebli);
		chckbxOdkurzenieMebli.addActionListener(akcja_zaznaczenia_Odkurzenie_Mebli);
		GridBagConstraints gbc_chckbxJazdaNaywach = new GridBagConstraints();
		gbc_chckbxJazdaNaywach.anchor = GridBagConstraints.NORTH;
		gbc_chckbxJazdaNaywach.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxJazdaNaywach.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxJazdaNaywach.gridx = 2;
		gbc_chckbxJazdaNaywach.gridy = 8;
		panel_t1.add(chckbxJazdaNaywach, gbc_chckbxJazdaNaywach);
		chckbxJazdaNaywach.addActionListener(akcja_zaznaczenia_Jazda_Naywach);
		GridBagConstraints gbc_chckbxOdnierzanie = new GridBagConstraints();
		gbc_chckbxOdnierzanie.anchor = GridBagConstraints.NORTHWEST;
		gbc_chckbxOdnierzanie.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxOdnierzanie.gridx = 3;
		gbc_chckbxOdnierzanie.gridy = 8;
		panel_t1.add(chckbxOdnierzanie, gbc_chckbxOdnierzanie);
		chckbxOdnierzanie.addActionListener(akcja_zaznaczenia_Odnierzanie);
		GridBagConstraints gbc_chckbxLeenie = new GridBagConstraints();
		gbc_chckbxLeenie.anchor = GridBagConstraints.NORTH;
		gbc_chckbxLeenie.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxLeenie.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxLeenie.gridx = 5;
		gbc_chckbxLeenie.gridy = 8;
		panel_t1.add(chckbxLeenie, gbc_chckbxLeenie);
		chckbxLeenie.addActionListener(akcja_zaznaczenia_Leenie);
		comboBox_od_godziny = new JComboBox();
		comboBox_od_godziny.setModel(new DefaultComboBoxModel(new String[] {"od godziny", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00"}));
		GridBagConstraints gbc_comboBox_od_godziny = new GridBagConstraints();
		gbc_comboBox_od_godziny.anchor = GridBagConstraints.NORTH;
		gbc_comboBox_od_godziny.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_od_godziny.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_od_godziny.gridx = 9;
		gbc_comboBox_od_godziny.gridy = 8;
		panel_t1.add(comboBox_od_godziny, gbc_comboBox_od_godziny);
		comboBox_od_godziny.addActionListener(combo_od);
		
		spinner_kolumny = new JSpinner();
		spinner_kolumny.setModel(new SpinnerNumberModel(1, 1, 7, 1));
		spinnerTylkoCyfry(spinner_kolumny);
		GridBagConstraints gbc_spinner_kolumny = new GridBagConstraints();
		gbc_spinner_kolumny.anchor = GridBagConstraints.NORTH;
		gbc_spinner_kolumny.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_kolumny.insets = new Insets(0, 0, 5, 0);
		gbc_spinner_kolumny.gridx = 11;
		gbc_spinner_kolumny.gridy = 8;
		panel_t1.add(spinner_kolumny, gbc_spinner_kolumny);
		GridBagConstraints gbc_chckbxJazdaNaNartach = new GridBagConstraints();
		gbc_chckbxJazdaNaNartach.anchor = GridBagConstraints.NORTH;
		gbc_chckbxJazdaNaNartach.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxJazdaNaNartach.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxJazdaNaNartach.gridx = 0;
		gbc_chckbxJazdaNaNartach.gridy = 9;
		panel_t1.add(chckbxJazdaNaNartach, gbc_chckbxJazdaNaNartach);
		chckbxJazdaNaNartach.addActionListener(akcja_zaznaczenia_Jazda_Na_Nartach);
		GridBagConstraints gbc_chckbxGraWTenisa = new GridBagConstraints();
		gbc_chckbxGraWTenisa.anchor = GridBagConstraints.NORTH;
		gbc_chckbxGraWTenisa.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxGraWTenisa.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxGraWTenisa.gridx = 1;
		gbc_chckbxGraWTenisa.gridy = 9;
		panel_t1.add(chckbxGraWTenisa, gbc_chckbxGraWTenisa);
		chckbxGraWTenisa.addActionListener(akcja_zaznaczenia_Gra_W_Tenisa);
		GridBagConstraints gbc_chckbxCzoganieSi = new GridBagConstraints();
		gbc_chckbxCzoganieSi.anchor = GridBagConstraints.NORTH;
		gbc_chckbxCzoganieSi.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxCzoganieSi.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxCzoganieSi.gridx = 2;
		gbc_chckbxCzoganieSi.gridy = 9;
		panel_t1.add(chckbxCzoganieSi, gbc_chckbxCzoganieSi);
		chckbxCzoganieSi.addActionListener(akcja_zaznaczenia_Czolganie_sie);
		GridBagConstraints gbc_chckbxPywanie = new GridBagConstraints();
		gbc_chckbxPywanie.anchor = GridBagConstraints.NORTH;
		gbc_chckbxPywanie.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxPywanie.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxPywanie.gridx = 3;
		gbc_chckbxPywanie.gridy = 9;
		panel_t1.add(chckbxPywanie, gbc_chckbxPywanie);
		chckbxPywanie.addActionListener(akcja_zaznaczenia_Pywanie);
		GridBagConstraints gbc_chckbxBoks = new GridBagConstraints();
		gbc_chckbxBoks.anchor = GridBagConstraints.NORTH;
		gbc_chckbxBoks.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxBoks.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxBoks.gridx = 5;
		gbc_chckbxBoks.gridy = 9;
		panel_t1.add(chckbxBoks, gbc_chckbxBoks);
		chckbxBoks.addActionListener(akcja_zaznaczenia_Boks);
		//String[] s;
		//s = new String[] {"do godziny", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00"};
		comboBox_do_godziny = new JComboBox();
		comboBox_do_godziny.setModel(new DefaultComboBoxModel(new String[] {"do godziny", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00"}));
		GridBagConstraints gbc_comboBox_do_godziny = new GridBagConstraints();
		gbc_comboBox_do_godziny.anchor = GridBagConstraints.NORTH;
		gbc_comboBox_do_godziny.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_do_godziny.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_do_godziny.gridx = 9;
		gbc_comboBox_do_godziny.gridy = 9;
		panel_t1.add(comboBox_do_godziny, gbc_comboBox_do_godziny);
		comboBox_do_godziny.addActionListener(combo_do);
		
		JLabel lblWiersze = new JLabel("Godzina");
		GridBagConstraints gbc_lblWiersze = new GridBagConstraints();
		gbc_lblWiersze.anchor = GridBagConstraints.WEST;
		gbc_lblWiersze.insets = new Insets(0, 0, 5, 0);
		gbc_lblWiersze.gridx = 11;
		gbc_lblWiersze.gridy = 9;
		panel_t1.add(lblWiersze, gbc_lblWiersze);
		GridBagConstraints gbc_chckbxSen = new GridBagConstraints();
		gbc_chckbxSen.anchor = GridBagConstraints.NORTH;
		gbc_chckbxSen.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxSen.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxSen.gridx = 0;
		gbc_chckbxSen.gridy = 10;
		panel_t1.add(chckbxSen, gbc_chckbxSen);
		chckbxSen.addActionListener(akcja_zaznaczenia_Sen);
		GridBagConstraints gbc_chckbxZmieniniePocieli = new GridBagConstraints();
		gbc_chckbxZmieniniePocieli.anchor = GridBagConstraints.NORTH;
		gbc_chckbxZmieniniePocieli.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxZmieniniePocieli.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxZmieniniePocieli.gridx = 1;
		gbc_chckbxZmieniniePocieli.gridy = 10;
		panel_t1.add(chckbxZmieniniePocieli, gbc_chckbxZmieniniePocieli);
		
		    chckbxZmieniniePocieli.addActionListener(akcja_zaznaczenia_Zmieninie_Pocieli );
		GridBagConstraints gbc_chckbxGimnastykaLekka = new GridBagConstraints();
		gbc_chckbxGimnastykaLekka.anchor = GridBagConstraints.NORTH;
		gbc_chckbxGimnastykaLekka.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxGimnastykaLekka.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxGimnastykaLekka.gridx = 2;
		gbc_chckbxGimnastykaLekka.gridy = 10;
		panel_t1.add(chckbxGimnastykaLekka, gbc_chckbxGimnastykaLekka);
		chckbxGimnastykaLekka.addActionListener(akcja_zaznaczenia_Gimnastyka_Lekka);
		GridBagConstraints gbc_chckbxOdkurzenieOdkurzaczem = new GridBagConstraints();
		gbc_chckbxOdkurzenieOdkurzaczem.anchor = GridBagConstraints.NORTHWEST;
		gbc_chckbxOdkurzenieOdkurzaczem.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxOdkurzenieOdkurzaczem.gridx = 3;
		gbc_chckbxOdkurzenieOdkurzaczem.gridy = 10;
		panel_t1.add(chckbxOdkurzenieOdkurzaczem, gbc_chckbxOdkurzenieOdkurzaczem);
		chckbxOdkurzenieOdkurzaczem.addActionListener(akcja_zaznaczenia_Odkurzenie_Odkurzaczem);
		GridBagConstraints gbc_chckbxSpacerSzybki = new GridBagConstraints();
		gbc_chckbxSpacerSzybki.anchor = GridBagConstraints.NORTHWEST;
		gbc_chckbxSpacerSzybki.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxSpacerSzybki.gridx = 5;
		gbc_chckbxSpacerSzybki.gridy = 10;
		panel_t1.add(chckbxSpacerSzybki, gbc_chckbxSpacerSzybki);
		chckbxSpacerSzybki.addActionListener(akcja_zaznaczenia_chckbxSpacer_Szybki);
		
		JLabel lblIleCzasuChcesz = new JLabel("Ile czasu chcesz ? (min)");
		GridBagConstraints gbc_lblIleCzasuChcesz = new GridBagConstraints();
		gbc_lblIleCzasuChcesz.fill = GridBagConstraints.BOTH;
		gbc_lblIleCzasuChcesz.insets = new Insets(0, 0, 5, 5);
		gbc_lblIleCzasuChcesz.gridx = 9;
		gbc_lblIleCzasuChcesz.gridy = 10;
		panel_t1.add(lblIleCzasuChcesz, gbc_lblIleCzasuChcesz);
		spinner_wiersz = new JSpinner();
		spinner_wiersz.setModel(new SpinnerNumberModel(1, 1, 16+1, 1));
		spinnerTylkoCyfry(spinner_wiersz);
		GridBagConstraints gbc_spinner_wiersz = new GridBagConstraints();
		gbc_spinner_wiersz.anchor = GridBagConstraints.NORTH;
		gbc_spinner_wiersz.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_wiersz.insets = new Insets(0, 0, 5, 0);
		gbc_spinner_wiersz.gridx = 11;
		gbc_spinner_wiersz.gridy = 10;
		panel_t1.add(spinner_wiersz, gbc_spinner_wiersz);
		GridBagConstraints gbc_chckbxpiewanie = new GridBagConstraints();
		gbc_chckbxpiewanie.anchor = GridBagConstraints.NORTH;
		gbc_chckbxpiewanie.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxpiewanie.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxpiewanie.gridx = 0;
		gbc_chckbxpiewanie.gridy = 11;
		panel_t1.add(chckbxpiewanie, gbc_chckbxpiewanie);
		chckbxpiewanie.addActionListener(akcja_zaznaczenia_Spiewanie);
		GridBagConstraints gbc_chckbbiegszybki = new GridBagConstraints();
		gbc_chckbbiegszybki.anchor = GridBagConstraints.NORTH;
		gbc_chckbbiegszybki.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbbiegszybki.insets = new Insets(0, 0, 5, 5);
		gbc_chckbbiegszybki.gridx = 1;
		gbc_chckbbiegszybki.gridy = 11;
		panel_t1.add(chckbbiegszybki, gbc_chckbbiegszybki);
		
		chckbbiegszybki.addActionListener(akcja_zaznaczenia_bieg_szybki);
		GridBagConstraints gbc_chckbxSiedzenie = new GridBagConstraints();
		gbc_chckbxSiedzenie.anchor = GridBagConstraints.NORTH;
		gbc_chckbxSiedzenie.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxSiedzenie.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxSiedzenie.gridx = 2;
		gbc_chckbxSiedzenie.gridy = 11;
		panel_t1.add(chckbxSiedzenie, gbc_chckbxSiedzenie);
		chckbxSiedzenie.addActionListener(akcja_zaznaczenia_Sedzenie);
		GridBagConstraints gbc_chckbxUbieranieSi = new GridBagConstraints();
		gbc_chckbxUbieranieSi.anchor = GridBagConstraints.NORTHWEST;
		gbc_chckbxUbieranieSi.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxUbieranieSi.gridx = 3;
		gbc_chckbxUbieranieSi.gridy = 11;
		panel_t1.add(chckbxUbieranieSi, gbc_chckbxUbieranieSi);
		chckbxUbieranieSi.addActionListener(akcja_zaznaczenia_Ubieranie_Si);
		GridBagConstraints gbc_chckbxJazdaSamochdem = new GridBagConstraints();
		gbc_chckbxJazdaSamochdem.anchor = GridBagConstraints.NORTHWEST;
		gbc_chckbxJazdaSamochdem.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxJazdaSamochdem.gridx = 5;
		gbc_chckbxJazdaSamochdem.gridy = 11;
		panel_t1.add(chckbxJazdaSamochdem, gbc_chckbxJazdaSamochdem);
		chckbxJazdaSamochdem.addActionListener(akcja_zaznaczenia_Jazda_Samochdem);
		
		spinner_ile = new JSpinner();
		spinner_ile.setModel(new SpinnerNumberModel(new Integer(60), new Integer(1), null, new Integer(1)));
		spinnerTylkoCyfry(spinner_ile);
		GridBagConstraints gbc_spinner_ile = new GridBagConstraints();
		gbc_spinner_ile.anchor = GridBagConstraints.SOUTH;
		gbc_spinner_ile.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner_ile.insets = new Insets(0, 0, 5, 5);
		gbc_spinner_ile.gridx = 9;
		gbc_spinner_ile.gridy = 11;
		panel_t1.add(spinner_ile, gbc_spinner_ile);
		GridBagConstraints gbc_chckbxPrasowanie = new GridBagConstraints();
		gbc_chckbxPrasowanie.anchor = GridBagConstraints.SOUTH;
		gbc_chckbxPrasowanie.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxPrasowanie.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxPrasowanie.gridx = 0;
		gbc_chckbxPrasowanie.gridy = 12;
		panel_t1.add(chckbxPrasowanie, gbc_chckbxPrasowanie);
		
		    chckbxPrasowanie.addActionListener(akcja_zaznaczenia_Prasowanie);
		GridBagConstraints gbc_chckbxCzytanieNaGos = new GridBagConstraints();
		gbc_chckbxCzytanieNaGos.anchor = GridBagConstraints.SOUTH;
		gbc_chckbxCzytanieNaGos.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxCzytanieNaGos.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxCzytanieNaGos.gridx = 1;
		gbc_chckbxCzytanieNaGos.gridy = 12;
		panel_t1.add(chckbxCzytanieNaGos, gbc_chckbxCzytanieNaGos);
		chckbxCzytanieNaGos.addActionListener(akcja_zaznaczenia_Czytanie_na_głos);
		GridBagConstraints gbc_chckbxGraWSiatkwke = new GridBagConstraints();
		gbc_chckbxGraWSiatkwke.anchor = GridBagConstraints.SOUTH;
		gbc_chckbxGraWSiatkwke.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxGraWSiatkwke.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxGraWSiatkwke.gridx = 2;
		gbc_chckbxGraWSiatkwke.gridy = 12;
		panel_t1.add(chckbxGraWSiatkwke, gbc_chckbxGraWSiatkwke);
		
		    chckbxGraWSiatkwke.addActionListener(akcja_zaznaczenia_Gra_WSiatkwke);
		GridBagConstraints gbc_chckbxGraWGolfa = new GridBagConstraints();
		gbc_chckbxGraWGolfa.anchor = GridBagConstraints.SOUTH;
		gbc_chckbxGraWGolfa.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxGraWGolfa.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxGraWGolfa.gridx = 3;
		gbc_chckbxGraWGolfa.gridy = 12;
		panel_t1.add(chckbxGraWGolfa, gbc_chckbxGraWGolfa);
		chckbxGraWGolfa.addActionListener(akcja_zaznaczenia_chckbxGra_W_Golfa);
		GridBagConstraints gbc_chckbxMycieOkien = new GridBagConstraints();
		gbc_chckbxMycieOkien.anchor = GridBagConstraints.SOUTH;
		gbc_chckbxMycieOkien.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxMycieOkien.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxMycieOkien.gridx = 5;
		gbc_chckbxMycieOkien.gridy = 12;
		panel_t1.add(chckbxMycieOkien, gbc_chckbxMycieOkien);
		chckbxMycieOkien.addActionListener(akcja_zaznaczenia_Mycie_Okien);
		
		JButton btnZatwierd_1 = new JButton("Zatwierdż");
		btnZatwierd_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					
				//tutaj pentla
				for (int k = w; k <= wk; k++){

					kolumna = dzien;
					wiersz = k;
					int ile_czasu = Integer.parseInt(spinner_ile.getValue().toString());
				    table_trenig.setValueAt(nazwa, wiersz, kolumna+1);
				 
					spalanie=(spalanie*(ile_czasu/60));
					
					tab[kolumna][wiersz] = spalanie;
					
					int liczbaSuma = 0;
					for (int i = 0; i < tab.length; i++) {
						for (int j = 0; j < tab[i].length; j++)
						{
							liczbaSuma += tab[i][j];
							jtfSpalanie_trenig.setText(""+liczbaSuma);
						}
					}
  
				
				}
				
				
			}
		});
		GridBagConstraints gbc_btnZatwierd_1 = new GridBagConstraints();
		gbc_btnZatwierd_1.anchor = GridBagConstraints.NORTH;
		gbc_btnZatwierd_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnZatwierd_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnZatwierd_1.gridx = 9;
		gbc_btnZatwierd_1.gridy = 12;
		panel_t1.add(btnZatwierd_1, gbc_btnZatwierd_1);
		
		JButton btnUsatw = new JButton("Ustaw");
		GridBagConstraints gbc_btnUsatw = new GridBagConstraints();
		gbc_btnUsatw.anchor = GridBagConstraints.NORTH;
		gbc_btnUsatw.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnUsatw.insets = new Insets(0, 0, 5, 0);
		gbc_btnUsatw.gridx = 11;
		gbc_btnUsatw.gridy = 12;
		panel_t1.add(btnUsatw, gbc_btnUsatw);
		btnUsatw.addActionListener(akcja_Ustaw);
		
		GridBagConstraints gbc_chckbxRozwieszaniePrania = new GridBagConstraints();
		gbc_chckbxRozwieszaniePrania.anchor = GridBagConstraints.SOUTH;
		gbc_chckbxRozwieszaniePrania.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxRozwieszaniePrania.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxRozwieszaniePrania.gridx = 0;
		gbc_chckbxRozwieszaniePrania.gridy = 13;
		panel_t1.add(chckbxRozwieszaniePrania, gbc_chckbxRozwieszaniePrania);
		chckbxRozwieszaniePrania.addActionListener(akcja_zaznaczenia_Rozwieszanie_Prania);
		GridBagConstraints gbc_chckbxGimnastykaForsowna = new GridBagConstraints();
		gbc_chckbxGimnastykaForsowna.anchor = GridBagConstraints.SOUTHWEST;
		gbc_chckbxGimnastykaForsowna.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxGimnastykaForsowna.gridx = 1;
		gbc_chckbxGimnastykaForsowna.gridy = 13;
		panel_t1.add(chckbxGimnastykaForsowna, gbc_chckbxGimnastykaForsowna);
		chckbxGimnastykaForsowna.addActionListener(akcja_zaznaczenia_Gimnastyka_Forsowna);
		GridBagConstraints gbc_chckbxZamiataniePodogi = new GridBagConstraints();
		gbc_chckbxZamiataniePodogi.anchor = GridBagConstraints.SOUTHWEST;
		gbc_chckbxZamiataniePodogi.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxZamiataniePodogi.gridx = 2;
		gbc_chckbxZamiataniePodogi.gridy = 13;
		panel_t1.add(chckbxZamiataniePodogi, gbc_chckbxZamiataniePodogi);
		
		 chckbxZamiataniePodogi.addActionListener(akcja_zaznaczenia_Zamiatanie_Podogi);
		GridBagConstraints gbc_chckbxWiosowanie = new GridBagConstraints();
		gbc_chckbxWiosowanie.anchor = GridBagConstraints.SOUTH;
		gbc_chckbxWiosowanie.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxWiosowanie.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxWiosowanie.gridx = 3;
		gbc_chckbxWiosowanie.gridy = 13;
		panel_t1.add(chckbxWiosowanie, gbc_chckbxWiosowanie);
		chckbxWiosowanie.addActionListener(akcja_zaznaczenia_Wiosowanie);
		GridBagConstraints gbc_chckbxJedzenie = new GridBagConstraints();
		gbc_chckbxJedzenie.anchor = GridBagConstraints.SOUTH;
		gbc_chckbxJedzenie.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxJedzenie.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxJedzenie.gridx = 5;
		gbc_chckbxJedzenie.gridy = 13;
		panel_t1.add(chckbxJedzenie, gbc_chckbxJedzenie);
		
		    chckbxJedzenie.addActionListener(akcja_zaznaczenia_Jedzenie);
		
		
		JButton btnWyczy = new JButton("Wyczyść");
		// wyczysc
		btnWyczy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (int i = 1; i < 8; i++){
					for (int k = 0; k< 17; k++){
						table_trenig.setValueAt("", k, i);
					}
				}
				for (int i = 0; i<20;i++){
					for (int k = 0; k<20;k++){
					tab[i][k] = 0;
					}	
				}
				jtfSpalanie_trenig.setText("");
				
			}
		});
		GridBagConstraints gbc_btnWyczy = new GridBagConstraints();
		gbc_btnWyczy.anchor = GridBagConstraints.NORTH;
		gbc_btnWyczy.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnWyczy.insets = new Insets(0, 0, 5, 5);
		gbc_btnWyczy.gridx = 9;
		gbc_btnWyczy.gridy = 13;
		panel_t1.add(btnWyczy, gbc_btnWyczy);
		//Usun
		JButton btnUsu = new JButton("Usuń");
		btnUsu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
					kolumna = Integer.parseInt(spinner_kolumny.getValue().toString());
					wiersz = Integer.parseInt(spinner_wiersz.getValue().toString());
				    table_trenig.setValueAt("", wiersz-1, kolumna);
					
					tab[kolumna][wiersz] = 0;
					
					int liczbaSuma = 0;
					for (int i = 0; i < tab.length; i++) {
						for (int j = 0; j < tab[i].length; j++)
						{
							liczbaSuma += tab[i][j];
							jtfSpalanie_trenig.setText(""+liczbaSuma);
						}
					}
					
			}
		});
		GridBagConstraints gbc_btnUsu = new GridBagConstraints();
		gbc_btnUsu.anchor = GridBagConstraints.NORTH;
		gbc_btnUsu.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnUsu.insets = new Insets(0, 0, 5, 0);
		gbc_btnUsu.gridx = 11;
		gbc_btnUsu.gridy = 13;
		panel_t1.add(btnUsu, gbc_btnUsu);
		GridBagConstraints gbc_chckbxStepaerobik = new GridBagConstraints();
		gbc_chckbxStepaerobik.anchor = GridBagConstraints.NORTH;
		gbc_chckbxStepaerobik.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxStepaerobik.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxStepaerobik.gridx = 0;
		gbc_chckbxStepaerobik.gridy = 14;
		panel_t1.add(chckbxStepaerobik, gbc_chckbxStepaerobik);
		
		    chckbxStepaerobik.addActionListener(akcja_zaznaczenia_Stepaerobik);
		GridBagConstraints gbc_chckbxStanie = new GridBagConstraints();
		gbc_chckbxStanie.anchor = GridBagConstraints.NORTH;
		gbc_chckbxStanie.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxStanie.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxStanie.gridx = 1;
		gbc_chckbxStanie.gridy = 14;
		panel_t1.add(chckbxStanie, gbc_chckbxStanie);
		chckbxStanie.addActionListener(akcja_zaznaczenia_Stanie);
		GridBagConstraints gbc_chckbxWbieganiePoSchodach = new GridBagConstraints();
		gbc_chckbxWbieganiePoSchodach.anchor = GridBagConstraints.NORTH;
		gbc_chckbxWbieganiePoSchodach.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxWbieganiePoSchodach.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxWbieganiePoSchodach.gridx = 2;
		gbc_chckbxWbieganiePoSchodach.gridy = 14;
		panel_t1.add(chckbxWbieganiePoSchodach, gbc_chckbxWbieganiePoSchodach);
		chckbxWbieganiePoSchodach.addActionListener(akcja_zaznaczenia_chckbxWbieganie_Po_Schodach);
		GridBagConstraints gbc_chckbxJazdaNaRowerze = new GridBagConstraints();
		gbc_chckbxJazdaNaRowerze.anchor = GridBagConstraints.NORTHWEST;
		gbc_chckbxJazdaNaRowerze.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxJazdaNaRowerze.gridx = 3;
		gbc_chckbxJazdaNaRowerze.gridy = 14;
		panel_t1.add(chckbxJazdaNaRowerze, gbc_chckbxJazdaNaRowerze);
		chckbxJazdaNaRowerze.addActionListener(akcja_zaznaczenia_Jazda_Na_Rowerze);
		
		JLabel lblPrognozowaneSpalnieKalori = new JLabel("Prognoza  kalori");
		GridBagConstraints gbc_lblPrognozowaneSpalnieKalori = new GridBagConstraints();
		gbc_lblPrognozowaneSpalnieKalori.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrognozowaneSpalnieKalori.gridx = 9;
		gbc_lblPrognozowaneSpalnieKalori.gridy = 14;
		panel_t1.add(lblPrognozowaneSpalnieKalori, gbc_lblPrognozowaneSpalnieKalori);
		
		jtfSpalanie_trenig = new JTextField();
		GridBagConstraints gbc_jtfSpalanie_trenig = new GridBagConstraints();
		gbc_jtfSpalanie_trenig.insets = new Insets(0, 0, 5, 0);
		gbc_jtfSpalanie_trenig.fill = GridBagConstraints.BOTH;
		gbc_jtfSpalanie_trenig.gridx = 11;
		gbc_jtfSpalanie_trenig.gridy = 14;
		panel_t1.add(jtfSpalanie_trenig, gbc_jtfSpalanie_trenig);
		jtfSpalanie_trenig.setColumns(10);
		
		
		JPanel panel_trenig_opcjonalnie = new JPanel();
		Trening.getContentPane().add(panel_trenig_opcjonalnie, BorderLayout.SOUTH);
		panel_trenig_opcjonalnie.setLayout(null);
		
		JPanel panel_trenig_opcjonalnie_1 = new JPanel();
		panel_trenig_opcjonalnie_1.setBounds(0, 0, 10, 10);
		panel_trenig_opcjonalnie.add(panel_trenig_opcjonalnie_1);
		panel_trenig_opcjonalnie_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_trenig_opcjonalnie_2 = new JPanel();
		Trening.getContentPane().add(panel_trenig_opcjonalnie_2, BorderLayout.EAST);
		
		
		
		//JTabbedPane Baza_produktow = new JTabbedPane(JTabbedPane.TOP);
		panel_Centralny.add(tabbedPane);
				
		JInternalFrame Baza = new JInternalFrame("Produkty");
		tabbedPane.addTab("Baza produktów", null, Baza, null);
		Baza.getContentPane().setLayout(new BorderLayout(0, 0));
		panel_Centralny.add(tabbedPane);

		JPanel panel_głowny_Baza = new JPanel();
		Baza.getContentPane().add(panel_głowny_Baza);
		panel_głowny_Baza.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_polnoc_Baza = new JPanel();
		FlowLayout flowLayout_Baza_Produktow = (FlowLayout) panel_polnoc_Baza.getLayout();
		flowLayout_Baza_Produktow .setAlignment(FlowLayout.LEFT);
		panel_głowny_Baza.add(panel_polnoc_Baza, BorderLayout.NORTH);
		
		JPanel panel_central_Baza_Produktow = new JPanel();
		panel_głowny_Baza.add(panel_central_Baza_Produktow, BorderLayout.CENTER);
		panel_central_Baza_Produktow.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_Roboczy_Główny_Baza = new JPanel();
		panel_central_Baza_Produktow.add(panel_Roboczy_Główny_Baza);
		panel_Roboczy_Główny_Baza.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_Pomocniczy_Baza = new JPanel();
		panel_Roboczy_Główny_Baza.add(panel_Pomocniczy_Baza);
		panel_Pomocniczy_Baza.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_lewy = new JPanel();
		panel_lewy.setPreferredSize(new Dimension(260, 10));
		panel_Pomocniczy_Baza.add(panel_lewy, BorderLayout.WEST);
		panel_lewy.setLayout(new BorderLayout(0, 0));
		
		JTaskPaneGroup taskPaneGroup_Wyszukiwanie_produktow = new JTaskPaneGroup();
		panel_lewy.add(taskPaneGroup_Wyszukiwanie_produktow, BorderLayout.CENTER);
		taskPaneGroup_Wyszukiwanie_produktow.setTitle("Wyszukiwarka produktów");
		taskPaneGroup_Wyszukiwanie_produktow.setOpaque(true);
		taskPaneGroup_Wyszukiwanie_produktow.setRequestFocusEnabled(false);
		BorderLayout bl_taskPaneGroup_Wyszukiwanie_produktow = (BorderLayout) taskPaneGroup_Wyszukiwanie_produktow.getLayout();
		bl_taskPaneGroup_Wyszukiwanie_produktow.setVgap(5);
		taskPaneGroup_Wyszukiwanie_produktow.setPreferredSize(new Dimension(210, 440));
		taskPaneGroup_Wyszukiwanie_produktow.setAnimated(false);
		taskPaneGroup_Wyszukiwanie_produktow.getContentPane().setLayout(null);
		
		JTaskPaneGroup taskPaneGroup_Dodaj_Usun = new JTaskPaneGroup();
		taskPaneGroup_Dodaj_Usun.setBounds(0, 628, 260, 154);
		taskPaneGroup_Dodaj_Usun.setTitle("Dodaj / Usuń produkt");
		taskPaneGroup_Wyszukiwanie_produktow.getContentPane().add(taskPaneGroup_Dodaj_Usun);
		GridBagLayout gridBagLayout_1 = new GridBagLayout();
		gridBagLayout_1.columnWidths = new int[]{204, 0};
		gridBagLayout_1.rowHeights = new int[]{16, 20, 20, 20, 0};
		gridBagLayout_1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		taskPaneGroup_Dodaj_Usun.getContentPane().setLayout(gridBagLayout_1);
		
		JLabel lblWybierzKategorie = new JLabel("Wybierz kategorie");
		GridBagConstraints gbc_lblWybierzKategorie = new GridBagConstraints();
		gbc_lblWybierzKategorie.anchor = GridBagConstraints.NORTH;
		gbc_lblWybierzKategorie.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblWybierzKategorie.insets = new Insets(0, 0, 5, 0);
		gbc_lblWybierzKategorie.gridx = 0;
		gbc_lblWybierzKategorie.gridy = 0;
		taskPaneGroup_Dodaj_Usun.getContentPane().add(lblWybierzKategorie, gbc_lblWybierzKategorie);
		
		JComboBox comboBox_Wybierz_Kategorie = new JComboBox();
		comboBox_Wybierz_Kategorie.setModel(new DefaultComboBoxModel(new String[] {"Mięsa i wędliny", "Nabiał", "Owoce", "Drób", "Fast-Food", "Grzyby", "Napoję i alkohole", "Orzechy i nasiona", "Przetwory zbożowę", "Przyprawy i sosy", "Ryba i owocę morza", "Słodyczę i przekąski", "Tłuszczę", "Warzywa", "Wszystkie"}));
		GridBagConstraints gbc_comboBox_Wybierz_Kategorie = new GridBagConstraints();
		gbc_comboBox_Wybierz_Kategorie .anchor = GridBagConstraints.NORTH;
		gbc_comboBox_Wybierz_Kategorie .fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_Wybierz_Kategorie .insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_Wybierz_Kategorie .gridx = 0;
		gbc_comboBox_Wybierz_Kategorie .gridy = 1;
		taskPaneGroup_Dodaj_Usun.getContentPane().add(comboBox_Wybierz_Kategorie, gbc_comboBox_Wybierz_Kategorie);
		ActionListener comboBox_Wybierz_Kategorie_akcja = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        //Akcja kategoria
		    	  if(comboBox_Wybierz_Kategorie.getSelectedIndex() == 0){
		    		  JOptionPane.showMessageDialog(null, " Nie została wybrana kategoria, wiec został wyrana domyślna 'wszystkie' ", "Informacja", JOptionPane.INFORMATION_MESSAGE);
		    		   sciezka = "Baza\\produkty.csv";
		    	  }
		    	  if(comboBox_Wybierz_Kategorie.getSelectedIndex() == 1){ sciezka = "Baza\\miesa.csv";}
		    	  if(comboBox_Wybierz_Kategorie.getSelectedIndex() == 2){ sciezka = "Baza\\nabial.csv";}
		    	  if(comboBox_Wybierz_Kategorie.getSelectedIndex() == 3){ sciezka = "Baza\\owoce.csv";}
		    	  if(comboBox_Wybierz_Kategorie.getSelectedIndex() == 4){ sciezka = "Baza\\drob.csv";}
		    	  if(comboBox_Wybierz_Kategorie.getSelectedIndex() == 5){ sciezka = "Baza\\fast.csv";}
		    	  if(comboBox_Wybierz_Kategorie.getSelectedIndex() == 6){ sciezka = "Baza\\grzyby.csv";}
		    	  if(comboBox_Wybierz_Kategorie.getSelectedIndex() == 7){ sciezka = "Baza\\napoje.csv";}
		    	  if(comboBox_Wybierz_Kategorie.getSelectedIndex() == 8){ sciezka = "Baza\\orzechy.csv";}
		    	  if(comboBox_Wybierz_Kategorie.getSelectedIndex() == 9){ sciezka = "Baza\\zbozowe.csv";}
		    	  if(comboBox_Wybierz_Kategorie.getSelectedIndex() == 10){ sciezka = "Baza\\przyprawy.csv";}
		    	  if(comboBox_Wybierz_Kategorie.getSelectedIndex() == 11){ sciezka = "Baza\\ryba.csv";}
		    	  if(comboBox_Wybierz_Kategorie.getSelectedIndex() == 12){ sciezka = "Baza\\slodycze.csv";}
		    	  if(comboBox_Wybierz_Kategorie.getSelectedIndex() == 13){ sciezka = "Baza\\tluszcze.csv";}
		    	  if(comboBox_Wybierz_Kategorie.getSelectedIndex() == 14){ sciezka = "Baza\\warzywa.csv";}
		    	  if(comboBox_Wybierz_Kategorie.getSelectedIndex() == 15){ sciezka = "Baza\\produkty.csv";}
		    	     
		      }
		    };
		comboBox_Wybierz_Kategorie.addActionListener(comboBox_Wybierz_Kategorie_akcja);
		
		JButton jbDodaj_Produkt = new JButton("Dodaj");
		GridBagConstraints gbc_jbDodaj_Produkt  = new GridBagConstraints();
		gbc_jbDodaj_Produkt.anchor = GridBagConstraints.NORTH;
		gbc_jbDodaj_Produkt.fill = GridBagConstraints.HORIZONTAL;
		gbc_jbDodaj_Produkt.insets = new Insets(0, 0, 5, 0);
		gbc_jbDodaj_Produkt.gridx = 0;
		gbc_jbDodaj_Produkt.gridy = 2;
		taskPaneGroup_Dodaj_Usun.getContentPane().add(jbDodaj_Produkt, gbc_jbDodaj_Produkt);
		ActionListener doda_produkt = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        //dodaj produkt
		    	  GeneticAlgorithm.dodaj_Dane(sciezka);
		      }
		    };
		jbDodaj_Produkt.addActionListener(doda_produkt);
		
		JButton jbUsuń_Produkt = new JButton("Usuń");
		GridBagConstraints gbc_jbUsuń_Produkt = new GridBagConstraints();
		gbc_jbUsuń_Produkt.anchor = GridBagConstraints.NORTH;
		gbc_jbUsuń_Produkt.fill = GridBagConstraints.HORIZONTAL;
		gbc_jbUsuń_Produkt.gridx = 0;
		gbc_jbUsuń_Produkt.gridy = 3;
		taskPaneGroup_Dodaj_Usun.getContentPane().add(jbUsuń_Produkt, gbc_jbUsuń_Produkt);
		ActionListener usun_produkt = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        //usun produkt
		    	  GeneticAlgorithm.usun_Dane(sciezka);
		      }
		    };
		jbUsuń_Produkt.addActionListener(usun_produkt);
		
		JLabel lblKategoriaProduktw = new JLabel("Kategoria produktów");
		lblKategoriaProduktw.setBounds(27, 0, 122, 24);
		taskPaneGroup_Wyszukiwanie_produktow.getContentPane().add(lblKategoriaProduktw);
		
		JList list = new JList();
		list.setBounds(21, 26, 211, 306);
		taskPaneGroup_Wyszukiwanie_produktow.getContentPane().add(list);
		list.setBackground(SystemColor.activeCaptionBorder);
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Mięsa i wędliny", "Nabiał", "Owoce", "Drób", "Fast-Food", "Grzyby", "Napoję i alkohole", "Orzechy i nasiona", "Przetwory zbożowę", "Przyprawy i sosy", "Ryba i owocę morza", "Słodyczę i przekąski", "Tłuszczę", "Warzywa", "Wszystkie"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		JButton btnSzukaj = new JButton("Wyświetl");
		btnSzukaj.setBounds(27, 345, 192, 20);
		taskPaneGroup_Wyszukiwanie_produktow.getContentPane().add(btnSzukaj);
		ActionListener wyswietl_Baze = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		    	  int c;
					c=list.getSelectedIndex();
					switch(c){//switch zawierajacy obsluge wybranej w liscie operacji
						
						case 0:String mieso = "miesa";
						sciezka = "Baza\\miesa.csv";
						try {
							read_Baza(mieso);
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
								break;
						case 1:String Nabiał = "nabial";
						sciezka = "Baza\\nabial.csv";
						try {
							read_Baza(Nabiał);
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
								break;
						case 2:String Owoce = "owoce";
						sciezka = "Baza\\owoce.csv";
						try {
							read_Baza(Owoce);
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
								break;
						
						case 3:String Drób = "drob";
						sciezka = "Baza\\drob.csv";
						try {
							read_Baza(Drób);
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
								break;
						case 4:String Fast_Food = "fast";
						sciezka = "Baza\\fast.csv";
						try {
							read_Baza(Fast_Food);
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
								break;
						case 5:String Grzyby = "grzyby";
						sciezka = "Baza\\grzyby.csv";
						try {
							read_Baza(Grzyby);
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
								break;
						case 6:String Napoję_i_alkohole = "napoje";
						sciezka = "Baza\\napoje.csv";
						try {
							read_Baza(Napoję_i_alkohole);
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
								break;
						case 7:String Orzechy_i_nasiona = "orzechy";
						sciezka = "Baza\\orzechy.csv";
						try {
							read_Baza(Orzechy_i_nasiona);
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
								break;
						case 8:String Przetwory_zbożowę = "zbozowe";
						sciezka = "Baza\\zbozowe.csv";
						try {
							read_Baza(Przetwory_zbożowę);
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
								break;
						case 9:String Przyprawy_i_sosy = "przyprawy";
						sciezka = "Baza\\przyprawy.csv";
						try {
							read_Baza(Przyprawy_i_sosy);
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
								break;
						case 10:String Ryba_i_owocę_morza = "ryba";
						sciezka = "Baza\\ryba.csv";
						try {
							read_Baza(Ryba_i_owocę_morza);
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
								break;
						case 11:String Słodyczę_i_przekąski = "slodycze";
						sciezka = "Baza\\slodycze.csv";
						try {
							read_Baza(Słodyczę_i_przekąski);
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
								break;
						case 12:String Tłuszczę = "tluszcze";
						sciezka = "Baza\\tluszcze.csv";
						try {
							read_Baza(Tłuszczę);
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
								break;
						case 13:String Warzywa = "warzywa";
						sciezka = "Baza\\warzywa.csv";
						try {
							read_Baza(Warzywa);
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
								break;
						case 14:String Wszystkie = "produkty";
						sciezka = "Baza\\produkty.csv";
						try {
							read_Baza(Wszystkie);
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
								break;
						
						default:break;
					}
		    	  
		      }
		    };
		btnSzukaj.addActionListener(wyswietl_Baze);
		
		JButton btnWyczysc = new JButton("Wyczyść");
		btnWyczysc.setBounds(27, 378, 192, 20);
		taskPaneGroup_Wyszukiwanie_produktow.getContentPane().add(btnWyczysc);
		ActionListener wyczysc_Baze = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		    	
		    	  textArea.setText("");
		      }
		    };
		btnWyczysc.addActionListener(wyczysc_Baze);
		
		JTaskPaneGroup taskPaneGroup_Wyszukaj_z_kategori = new JTaskPaneGroup();
		taskPaneGroup_Wyszukaj_z_kategori.setBounds(0, 425, 260, 203);
		taskPaneGroup_Wyszukiwanie_produktow.getContentPane().add(taskPaneGroup_Wyszukaj_z_kategori);
		taskPaneGroup_Wyszukaj_z_kategori.setOpaque(true);
		taskPaneGroup_Wyszukaj_z_kategori.setRequestFocusEnabled(false);
		taskPaneGroup_Wyszukaj_z_kategori.getContentPane().setPreferredSize(new Dimension(210, 420));
		taskPaneGroup_Wyszukaj_z_kategori.setPreferredSize(new Dimension(230, 417));
		BorderLayout bl_taskPaneGroup_Wyszukaj_z_kategori = (BorderLayout) taskPaneGroup_Wyszukaj_z_kategori.getLayout();
		bl_taskPaneGroup_Wyszukaj_z_kategori.setVgap(5);
		taskPaneGroup_Wyszukaj_z_kategori.setAnimated(false);
		taskPaneGroup_Wyszukaj_z_kategori.setTitle("Sortuj");
		taskPaneGroup_Wyszukaj_z_kategori.getContentPane().setLayout(null);
		
		JLabel lbWartosc = new JLabel("Wartości");
		lbWartosc.setBounds(12, 13, 185, 19);
		taskPaneGroup_Wyszukaj_z_kategori.getContentPane().add(lbWartosc);
		
		JComboBox comboBox_Kategoria = new JComboBox();
		comboBox_Kategoria.setBounds(12, 34, 202, 22);
		taskPaneGroup_Wyszukaj_z_kategori.getContentPane().add(comboBox_Kategoria);
		comboBox_Kategoria.setModel(new DefaultComboBoxModel(new String[] {"Wybierz","Kalorie", "Tłuszcze", "Białko","Węglowodany"}));
		ActionListener comboBox_Wybierz_Kategorie_sortuj_akcja = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        //Akcja kategoria
		    	  if(comboBox_Kategoria.getSelectedIndex() == 0){
		    		  JOptionPane.showMessageDialog(null, " Nie została wybrana kategoria, wiec został wyrana domyślna 'wszystkie' ", "Informacja", JOptionPane.INFORMATION_MESSAGE);  
		    	  }
		    	  if(comboBox_Kategoria .getSelectedIndex() == 1){wybór_kategori = 1; }
		    	  if(comboBox_Kategoria .getSelectedIndex() == 2){wybór_kategori = 2; }
		    	  if(comboBox_Kategoria .getSelectedIndex() == 3){wybór_kategori = 3; }
		    	  if(comboBox_Kategoria .getSelectedIndex() == 4){wybór_kategori = 4; }  
		      }
		    };
		    comboBox_Kategoria .addActionListener(comboBox_Wybierz_Kategorie_sortuj_akcja);
		
		JLabel lbSortuj = new JLabel("Sortuj");
		lbSortuj.setBounds(12, 69, 212, 16);
		taskPaneGroup_Wyszukaj_z_kategori.getContentPane().add(lbSortuj);
		
		JComboBox comboBox_Wybór = new JComboBox();
		comboBox_Wybór.setBounds(12, 98, 202, 22);
		taskPaneGroup_Wyszukaj_z_kategori.getContentPane().add(comboBox_Wybór);
		comboBox_Wybór.setModel(new DefaultComboBoxModel(new String[] {"Wybierz", "Największe", "Najmniejszę"}));
		ActionListener comboBox_Wybierz_sortuj_akcja = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		        //Akcja kategoria
		    	  if(comboBox_Wybór.getSelectedIndex() == 0){
		    		  JOptionPane.showMessageDialog(null, " Nie została wybrana kategoria, wiec został wyrana domyślna 'wszystkie' ", "Informacja", JOptionPane.INFORMATION_MESSAGE);   
		    	  }
		    	  if(comboBox_Wybór.getSelectedIndex() == 1){sciezka = "Baza\\produkty.csv";
		    		  tab_numery = GeneticAlgorithm.Sortuj_rosnąco(sciezka, wybór_kategori); }
		    	  if(comboBox_Wybór.getSelectedIndex() == 2){sciezka = "Baza\\produkty.csv";
		    		  tab_numery = GeneticAlgorithm.Sortuj_malejaco(sciezka,wybór_kategori); }
		    	  
		      }
		    };
		    comboBox_Wybór.addActionListener(comboBox_Wybierz_sortuj_akcja);
		JButton btnZnaj = new JButton("Znajż");
		btnZnaj.setBounds(12, 133, 202, 25);
		taskPaneGroup_Wyszukaj_z_kategori.getContentPane().add(btnZnaj);
		ActionListener Znaj_akcja = new ActionListener() {
		      public void actionPerformed(ActionEvent actionEvent) {
		    	  
		    	 String linia[] = GeneticAlgorithm.Wyprowadż_po_sortowaniu(sciezka,tab_numery);
			       
			             textArea.append(linia+"\n");
		      } 
		      
		    };
		    btnZnaj.addActionListener(Znaj_akcja);
		    
		JPanel panel_srodek_Baza = new JPanel();
		panel_Pomocniczy_Baza.add(panel_srodek_Baza, BorderLayout.CENTER);
		panel_srodek_Baza.setLayout(new BorderLayout(0, 0));
		
		JTaskPaneGroup taskPaneGroup_INformacje = new JTaskPaneGroup();
		panel_srodek_Baza.add(taskPaneGroup_INformacje, BorderLayout.CENTER);
		taskPaneGroup_INformacje.setTitle("Informacje");
		taskPaneGroup_INformacje.getContentPane().setPreferredSize(new Dimension(250, 21));
		taskPaneGroup_INformacje.getContentPane().setLayout(new BorderLayout(0, 0));
		//Panel Alergie
		JPanel panel_Alergie = new JPanel();
		panel_Alergie.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_Alergie.setPreferredSize(new Dimension(100, 50));
		taskPaneGroup_INformacje.getContentPane().add(panel_Alergie, BorderLayout.NORTH);
		panel_Alergie.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JComboBox comboBox_Alergie = new JComboBox();
		comboBox_Alergie.setModel(new DefaultComboBoxModel(new String[] {"Wybierz alergia", "Alergia 1", "Alergia 2", "Alergia 3", "Alergia 4", "Alergia 5"}));
		panel_Alergie.add(comboBox_Alergie);
		
		
		JButton Zatwierd_alergie = new JButton("Zatwierdź");
		Zatwierd_alergie .setPreferredSize(new Dimension(122, 20));
		panel_Alergie.add(Zatwierd_alergie);
		TextArea textArea_Alergie = new TextArea();
		taskPaneGroup_INformacje.getContentPane().add(textArea_Alergie, BorderLayout.CENTER);
		//Panel Diety
		JPanel panel_diety = new JPanel();
		panel_diety.setPreferredSize(new Dimension(250, 420));
		taskPaneGroup_INformacje.getContentPane().add(panel_diety, BorderLayout.SOUTH);
		panel_diety.setLayout(new BorderLayout(0, 0));
		
		TextArea textArea_1 = new TextArea();
		panel_diety.add(textArea_1, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(100, 50));
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_diety.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JComboBox comboBox_Diete = new JComboBox();
		comboBox_Diete.setModel(new DefaultComboBoxModel(new String[] {"Wybierz Diete", "Dieta  1", "Dieta 2", "Dieta 3", "Dieta 4"}));
		panel.add(comboBox_Diete);
		
		JButton Zatwierd_diete = new JButton("Zatwierdź");
		Zatwierd_diete.setPreferredSize(new Dimension(122, 20));
		panel.add(Zatwierd_diete);
		
		JPanel panel_prawa = new JPanel();
		panel_głowny_Baza.add(panel_prawa, BorderLayout.EAST);
		
		JPanel panel_Baza_produktów = new JPanel();
		panel_Baza_produktów.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		panel_Baza_produktów.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel_Baza_produktów.setPreferredSize(new Dimension(520, 300));
		panel_głowny_Baza.add(panel_Baza_produktów, BorderLayout.WEST);
		panel_Baza_produktów.setLayout(new BorderLayout(0, 15));
		
		JLabel lblBazaProduktw = new JLabel("Baza produktów");
		lblBazaProduktw.setAlignmentY(Component.TOP_ALIGNMENT);
		lblBazaProduktw.setFont(new Font("Dialog", Font.BOLD, 12));
		lblBazaProduktw.setHorizontalAlignment(SwingConstants.CENTER);
		lblBazaProduktw.setHorizontalTextPosition(SwingConstants.CENTER);
		panel_Baza_produktów.add(lblBazaProduktw, BorderLayout.NORTH);
		
		textArea = new TextArea();
		panel_Baza_produktów.add(textArea, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_Baza_produktów.add(panel_1, BorderLayout.SOUTH);
		
		
		Baza.setVisible(true);
		
		contentPane.add(panel_Lewy, BorderLayout.WEST);
		contentPane.add(panel_Prawy, BorderLayout.EAST);
		contentPane.add(panel_Dolny, BorderLayout.SOUTH);
		
		//Ustawienie Layout paneli
		panel_Górny.setLayout(new BorderLayout(0, 0));
		panel_Lewy.setLayout(new BorderLayout(0,0));
		panel_Dolny.setLayout(new BorderLayout(0, 0));
		
		//Pasek narzędziowy(panel górny)
		
		JToolBar Pasek_Narzęiowy_Główny = new JToolBar();
		Pasek_Narzęiowy_Główny.setRollover(true);
		panel_Górny.add(Pasek_Narzęiowy_Główny);
		
		JButton menuStrart = new JButton("");
		menuStrart.setToolTipText("Start - uruchamia symulacje");
		menuStrart.setIcon(new ImageIcon("image\\Start.png"));
		Pasek_Narzęiowy_Główny.add(menuStrart);
		
		JButton menuZapisz = new JButton("");
		menuZapisz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 
				zapiszDaneDoPliku();
				
			}
		});
		menuZapisz.setIcon(new ImageIcon("image\\Zapisz.png"));
		Pasek_Narzęiowy_Główny.add(menuZapisz);
		
		JButton menuWyjście = new JButton("");
		menuWyjście.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Close_Window();
			}
		});
		menuWyjście.setIcon(new ImageIcon("image\\Zamknij.png"));
		Pasek_Narzęiowy_Główny.add(menuWyjście);
		
		Pasek_Narzęiowy_Główny.addSeparator();
		
		JButton menuDrukuj = new JButton("");
		menuDrukuj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 
				przygotujWydruki();
				
			}
		});
		
		JButton menuDodaj = new JButton("");
		menuDodaj.setIcon(new ImageIcon("image\\dodaj.png"));
		Pasek_Narzęiowy_Główny.add(menuDodaj);
		
		JButton menuUsuń = new JButton("");
		menuUsuń.setIcon(new ImageIcon("image\\Usuń.png"));
		Pasek_Narzęiowy_Główny.add(menuUsuń);
		menuDrukuj.setIcon(new ImageIcon("image\\Drukuj.png"));
		Pasek_Narzęiowy_Główny.add(menuDrukuj);
		
		Pasek_Narzęiowy_Główny.addSeparator();
		
		JButton menuInformacje = new JButton("");
		menuInformacje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AboutWindow infoprogram = new AboutWindow();
				infoprogram.setVisible(true);
			}
		});
		menuInformacje.setIcon(new ImageIcon("image\\miniInformacja.png"));
		Pasek_Narzęiowy_Główny.add(menuInformacje);
		
		JButton menuPomocy = new JButton("");
		menuPomocy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HelpWindow pomocprogram = null;
				try {
					pomocprogram = new HelpWindow();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				pomocprogram.setVisible(true);
			}
		});
		menuPomocy.setIcon(new ImageIcon("image\\miniPomoc.png"));
		Pasek_Narzęiowy_Główny.add(menuPomocy);
		
	}
	
	public void Close_Window() {
			int opcja = JOptionPane.showConfirmDialog(null,
					"Czy na pewno chcesz wyjść ?", "Pytanie ",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (opcja == JOptionPane.YES_OPTION) {
				
				dispose();
				System.exit(0);
			}
			if (opcja == JOptionPane.NO_OPTION) {
				this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			}
		}
	
	public void read_Baza(String nazwa) throws UnsupportedEncodingException{
			
	        String line = "";
	        FileInputStream fin = null;
	        try{
	            fin = new FileInputStream("Baza\\"+nazwa+".csv");
	            
	        }catch (FileNotFoundException e)
	        {
	        	//JOptionPane.showInternalMessageDialog(null,"Niestety nie moge utworzyć pliku !", "Informacja", JOptionPane.WARNING_MESSAGE);
	        	//System.out.println("jesrt");
	            System.err.println("Nie odnaleziono porzadądanego pliku. \n "+e);

	            System.exit(-1);
	        }
	        BufferedReader reader  = new BufferedReader(new InputStreamReader((fin),"UTF-8"));
	        try {
	            while((line = reader.readLine())!=null){
	                
	             textArea.append(line.replace(",", "\t")+"\n");
	              //System.out.println(line);
	            }
	        }catch (IOException e)
	        {
	            System.err.println("Błąd wyjścia /wejścia");
	        }
	        
	     }

		public void actionPerformed(ActionEvent event){
		Object żródło = event.getSource();
		
		if(żródło==rdbtnmntmMetal){
			
			
				try {
					
					UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
					
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				SwingUtilities.updateComponentTreeUI(this);
				this.repaint();
	
		}else if(żródło==rdbtnArcyl){
			
			try {
				UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}else if(żródło==rdbtnAero){
			try {
				UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(żródło==rdbtnAluminum){
			try {
				UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(żródło==rdbtnBernstein){
			try {
				UIManager.setLookAndFeel("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(żródło==rdbtnFast){
			try {
				UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel");
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(żródło==rdbtnGraphite){
			try {
				UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}else if(żródło==rdbtnHifi){
			try {
				UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(żródło==rdbtnLuna){
			try {
				UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}else if(żródło==rdbtnMcwin){
			
			try {
				UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}else if(żródło==rdbtnMint){
			try {
				UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(żródło==rdbtnNoire){
			try {
				UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(żródło==rdbtnSmart){
			try {
				UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}else if(żródło==rdbtnTexture){
			try {
				UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
		}
		SwingUtilities.updateComponentTreeUI(this);
		table.setRowHeight(35);
		//table_trenig.setRowHeight(20);
		this.repaint();
		
		table.repaint();
		
	}
		public String [] Beginning(int kalorie) throws IOException{
			 
			 GeneticAlgorithm gen = new GeneticAlgorithm(wzrost,waga,wiek);
			 
			 int[][] tab_genow = new int [100][15];
             int[] tab_genów_suma = new int [100];
             int[][] tab_genow_selekcja = new int [100][15];
             int[][] tab_genow_selekcja_zamiana = new int [100][15];
             int[] tab_genów_suma_selekcja = new int [100]; 
             int[][] tab_genow_krzyzowanie = new int [100][15];
             int[][] tab_genow_krzyzowanie_zamiana = new int [100][15];
             int[] FC_tab_geny = new int [100];
             int[] FC_tab_geny_selekcja = new int [100];
             int FC = 0;
             int FCbez = 0;
             double FCprimsuma = 0;
             float PW = 0;
             double Rmin = 0;
             double Rmax = 0;
             int[][] tab_genow_mutacja = new int [100][15]; 
             int [][] tab_genow_mutacja_zamiana = new int [100][15];
             int numer_osobnika = 0;
             int numer_osobnika_selekcja = 0;
             int[] kod_osobnika  = new int [15];
             int [] kod_osobnika_selkcja = new int [15];
             int kalorie_wyszukane = 0;
             int itertor = 0;
             String[] teksty = new String[15];
             String[] info = null;
			
           //Dane pozostałe 
             int D_zakres = Integer.parseInt(spinner_zakres.getValue().toString());
             zakres = D_zakres;
             int D_czas  = Integer.parseInt(spinner_czas.getValue().toString());
             czas = D_czas;
             //System.out.print(czas);
           //Czas - Start pętli 
             long timeStart = System.currentTimeMillis();

                 
                  jtfWynik_BMR.setText(""+kalorie);
                   for (int wiersze = 0; wiersze < 100; wiersze++) {
                     for (int kolumny = 0; kolumny < 15; kolumny++) {
                         
                             tab_genow = gen.Inicjacja();
                             
                                   }
                       }   
                  
                 for (int wiersze = 0; wiersze < 100; wiersze++) {
                       tab_genów_suma = gen.Ocena(kalorie,tab_genow);
                       
                        for (int wiersz = 0; wiersz < 100; wiersz++) {
                     
                         
                          FC_tab_geny = gen.Wartosc_FC(kalorie,tab_genów_suma);
                          FCbez = gen.FC_(kalorie, tab_genów_suma);
                          PW = gen.PW(FC_tab_geny);
                          Rmin = gen.Rmin(FC_tab_geny);
                          Rmax = gen.Rmax(FC_tab_geny);
                          FCprimsuma = gen.FCprimsuma(FC_tab_geny);
                                   }
                        
                       if(((tab_genów_suma[wiersze]==kalorie)||(((tab_genów_suma[wiersze]<kalorie+zakres)&&(tab_genów_suma[wiersze]>kalorie-zakres)))))
                       {
                            kalorie_wyszukane = tab_genów_suma[wiersze]; 
                            numer_osobnika = wiersze;
                         
                       }
                      }
                 if(((kalorie_wyszukane==kalorie)||(((kalorie_wyszukane<kalorie+zakres)&&(kalorie_wyszukane>kalorie-zakres)))))
                      {
                             for(int kolumny = 0; kolumny < 15; kolumny++)
                             {
                                  kod_osobnika = gen.Kod_osobnika(numer_osobnika, tab_genow);
                             }

                              teksty = gen.wprowadz_Wynik(kod_osobnika);
                      }
                 else
                      {
                      //zmiena przechowująca czas    
                      int timeSecund = 0;
                     
                      
                  do {
                         
                      for (int wiersze = 0; wiersze < 100; wiersze++) {
                       tab_genów_suma = gen.Ocena(kalorie,tab_genow);
                   
                      }
                      
                      for (int wiersze = 0; wiersze < 100; wiersze++) {
                     
                         
                          FC_tab_geny = gen.Wartosc_FC(kalorie,tab_genów_suma);
                          FCbez = gen.FC_(kalorie, tab_genów_suma);
                          PW = gen.PW(FC_tab_geny);
                          Rmin = gen.Rmin(FC_tab_geny);
                          Rmax = gen.Rmax(FC_tab_geny);
                          FCprimsuma = gen.FCprimsuma(FC_tab_geny);
                                   }
                      
                       for (int wiersze = 0; wiersze < 100; wiersze++) {
                     
                         
                   tab_genow_selekcja =  gen.Selekcja(FC_tab_geny);
                    
                                   
                       }
                        for (int wiersze = 0; wiersze < 100; wiersze++) {
                     for (int kolumny = 0; kolumny < 15; kolumny++) {
                         
                   tab_genów_suma_selekcja =  gen.Ocena(kalorie,tab_genow_selekcja);
                          
                                   }
                       }
                        for (int wiersze = 0; wiersze < 100; wiersze++) {
                     
                         
                          FC_tab_geny_selekcja = gen.Wartosc_FC(kalorie,tab_genów_suma_selekcja);
                        
                                   }
                         
                     for (int kolumny = 0; kolumny < 15; kolumny++) {
                         
                   kod_osobnika_selkcja = gen.Najlepszy(FC_tab_geny,FC_tab_geny_selekcja,tab_genow,tab_genow_selekcja);
                   FC = gen.LiczbaFC(FC_tab_geny, FC_tab_geny_selekcja, tab_genow, tab_genow_selekcja);
                  
                                   }
                      //zapisuje najlepsze kalorie jakie znalazł
                      kalorie_wyszukane = gen.Najlepszy_kalorie(kod_osobnika_selkcja);
                      //w przpadku gdy prgram zakonczy szybciej działanie
                      teksty = gen.wprowadz_Wynik(kod_osobnika_selkcja);///wprowadzone wyniku
                      
                      
                         for (int wiersze = 0; wiersze < 100; wiersze++) {
                     for (int kolumny = 0; kolumny < 15; kolumny++) {
                         
                   tab_genow_selekcja_zamiana =  gen.Zamiana(tab_genow_selekcja,kod_osobnika_selkcja);
                          
                                   }
                       }
                      
                        
                       for (int wiersze = 0; wiersze < 100; wiersze++) {
                     for (int kolumny = 0; kolumny < 15; kolumny++) {
                         
                          tab_genow_krzyzowanie = gen.Krzyzowanie(tab_genow_selekcja_zamiana);
                                   }
                       }
                       
                         for (int wiersze = 0; wiersze < 100; wiersze++) {
                     for (int kolumny = 0; kolumny < 15; kolumny++) {
                         
                   tab_genow_krzyzowanie_zamiana =  gen.Zamiana(tab_genow_krzyzowanie,kod_osobnika_selkcja);
                          
                                   }
                       }
                        for (int wiersze = 0; wiersze < 100; wiersze++) {
                     for (int kolumny = 0; kolumny < 15; kolumny++) {
                         
                        tab_genow_mutacja = gen.Mutacja(kalorie,tab_genow_krzyzowanie_zamiana);
                                   }
                       } 
                         for (int wiersze = 0; wiersze < 100; wiersze++) {
                     for (int kolumny = 0; kolumny < 15; kolumny++) {
                         
                   tab_genow_mutacja_zamiana =  gen.Zamiana(tab_genow_krzyzowanie,kod_osobnika_selkcja);
                          
                                   }
                       }
                       for (int wiersze = 0; wiersze < 100; wiersze++) {
                           
                       tab_genów_suma = gen.Ocena(kalorie,tab_genow_mutacja_zamiana);
                       
                       if(((((tab_genów_suma[wiersze]==kalorie))||((tab_genów_suma[wiersze]<kalorie+zakres)&&(tab_genów_suma[wiersze]>kalorie-zakres))||(timeSecund==czas))))
                       {
                            kalorie_wyszukane = tab_genów_suma[wiersze]; 
                            numer_osobnika = wiersze;
                       }
                      }
                 if((((kalorie_wyszukane==kalorie)||(kalorie_wyszukane<kalorie+zakres)&&(kalorie_wyszukane>kalorie-zakres))||(timeSecund==czas)))
                      {
                          for(int kolumny = 0; kolumny < 15; kolumny++)
                          {
                               kod_osobnika = gen.Kod_osobnika(numer_osobnika, tab_genow_selekcja);
                          }
                              
                           teksty = gen.wprowadz_Wynik(kod_osobnika);

                      } 
                        
                        else {
                             for (int wiersze = 0; wiersze < 100; wiersze++) {
                     for (int kolumny = 0; kolumny < 15; kolumny++) {
                         
                          tab_genow = tab_genow_mutacja_zamiana;
                                   }
                       }
                             
                        }
                 
                  //czas w programie   
                  long timeEnd = System.currentTimeMillis();
                  //czas w jaki upłynoł w mili sekundach
                  long time = timeEnd-timeStart;
                  //czas w sekundach
                  timeSecund =(int) (+ time/1000.0);
                //  System.err.println("Czas : "+timeSecund);
                 
                  //licznik pentli 
                  itertor++;
                  //zbiera do tablicy info podczas działania progrmu
                  info = gen.wprowadz_Dane(wiek,wzrost,waga,PW,FC,Rmin,Rmax,FCbez,FCprimsuma);

                 // jtaTekstINformację.setText("Pentla : "+itertor+"\n"+info[0]+" --> "+wybór+"\n"+"Wiek :"+info[1]+"\n"+"Wzrost : "+info[2]+"\n"+"Waga :"+info[3]+"\n"+"Dane wygenerowane MBR --> "+kalorie+"\n"+"Prawdopodobieństwo [PW] --> "+info[4]+"\n"+"Funkcja przystosowania osobnika  --> "+info[5]+"\n"+"Rmin : "+info[6]+"\n"+"Rmax :"+info[7]+"\n"+"FC'  --> "+info[8]+"\n"+"FC' suma --> "+info[9]+"\n"+"Kalorie wyszukane --> "+kalorie_wyszukane+"\n");
                  //pasek prgresu
                  lblWypetle.setText(""+itertor);
                  lblWypw.setText(info[4]);
                  lblWyfcprim.setText(info[8]);
                  lblWyfc.setText(info[5]);
                  lblWyrmax.setText(info[7]);
                  lblWyrmin.setText(info[6]);
                  lblMojeBmr.setText(kalorie+"");
                  lblWyczas.setText(""+timeSecund);
                  
                  
                  progressBar_1.setMaximum(czas);
                  progressBar_1.setValue(timeSecund);
                 
                 // System.out.println(kalorie_wyszukane);
              
                  } while (((!((kalorie_wyszukane<=kalorie+zakres)&&(kalorie_wyszukane>=kalorie-zakres)))&&!(timeSecund==czas)&&(kalorie_wyszukane!=kalorie)));
                         
                    
                  
                 
                     }
				return teksty;
     
 
}
		  public class Start extends Thread {

			  
			  public void run()
			   { 

			      
			          String poniedzialek [] = null;
			      try {
			          
			          poniedzialek = Beginning(kalorie);
			          
			      } catch (IOException ex) {
			          Logger.getLogger(JFrame.class.getName()).log(Level.SEVERE, null, ex);
			      }
			           

			      									for(int i = 0; i<15; i++)
			                                           {
			                                             
			    	  								table.setValueAt(poniedzialek[i], 0+i, 0);
			                                          }
			             
			           String wtorek [] = null;
			      try {
			          wtorek = Beginning(kalorie);
			      } catch (IOException ex) {
			          Logger.getLogger(JFrame.class.getName()).log(Level.SEVERE, null, ex);
			      }
			        
			                                        for(int i = 0; i<15; i++)
			                                           {
			                                             
			                                        	table.setValueAt(wtorek[i], 0+i, 1);
			                                          }
			                                         
			           String sroda [] = null;
			      try {
			          sroda = Beginning(kalorie);
			      } catch (IOException ex) {
			          Logger.getLogger(JFrame.class.getName()).log(Level.SEVERE, null, ex);
			      }
			          
			                                        for(int i = 0; i<15; i++)
			                                           {
			                                             
			                                        	table.setValueAt(sroda[i], 0+i, 2);
			                                          }                        
			                                        
			           String czwartek [] = null;
			      try {
			          czwartek = Beginning(kalorie);
			      } catch (IOException ex) {
			          Logger.getLogger(JFrame.class.getName()).log(Level.SEVERE, null, ex);
			      }
			          
			                                        for(int i = 0; i<15; i++)
			                                           {
			                                             
			                                        	table.setValueAt(czwartek[i], 0+i, 3);
			                                          }                     
			           String piatek [] = null;
			      try {
			          piatek = Beginning(kalorie);
			      } catch (IOException ex) {
			          Logger.getLogger(JFrame.class.getName()).log(Level.SEVERE, null, ex);
			      }
			          
			                                        for(int i = 0; i<15; i++)
			                                           {
			                                             
			                                        	table.setValueAt(piatek[i], 0+i, 4);
			                                          }
			           String sobota [] = null;
			      try {
			          sobota = Beginning(kalorie);
			      } catch (IOException ex) {
			          Logger.getLogger(JFrame.class.getName()).log(Level.SEVERE, null, ex);
			      }
			         
			                                        for(int i = 0; i<15; i++)
			                                           {
			                                             
			                                        	table.setValueAt(sobota[i], 0+i, 5);
			                                          }
			           String niedziela [] = null;
			      try {
			          niedziela = Beginning(kalorie);
			      } catch (IOException ex) {
			          Logger.getLogger(JFrame.class.getName()).log(Level.SEVERE, null, ex);
			      }
			          
			                                        for(int i = 0; i<15; i++)
			                                           {
			                                             
			                                              table.setValueAt(niedziela[i], 0+i, 6);
			                                          }
			      
			  } 
			      }
		
		
}
	

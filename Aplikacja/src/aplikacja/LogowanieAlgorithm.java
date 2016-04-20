import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;


public class LogowanieAlgorithm {
	
	
	static ArrayList<BaseUser> listaUżytkowników= new ArrayList<>();

	//Zmiene do scieżki bazy produktów
    static String sciezka;
    static String naglowki;
    boolean poprawne_Login;
    boolean poprawne_Hasło;
    int numer_uzytkownika;
    int uprawnienia;
    
	  public LogowanieAlgorithm() {
		 
		// TODO Auto-generated constructor stub
	}
	        
	    
	   public static ArrayList<BaseUser> czytaj_Plik() {
	        sciezka = "C:\\Users\\Gregory\\Documents\\JavaEclipse\\Fitnes\\Baza\\uzytkownik.csv";
	        Path sciezkaDoPliku = Paths.get(sciezka);
	        // Lista będzie przechowywała kolejne linie odczytane z pliku jako String
	        ArrayList<String> odczyt = new ArrayList<String>();
	        try {
	            // Wczytanie wszystkich linii do listy
	            odczyt = (ArrayList) Files.readAllLines(sciezkaDoPliku);
	        } catch (IOException ex) {
	            JOptionPane.showInternalMessageDialog(null,"Niestety nie moge utworzyć pliku !", "Informacja", JOptionPane.WARNING_MESSAGE);
	        }

	        // Pobranie pierwszej linii z nagłówkami...
	        naglowki = odczyt.get(0);
	        // ... następnie usunięcie jej
	        odczyt.remove(0);

	    //Pobiera po serii danych, odpowiadających linii w pliku 
	        // a następnie tworzy obiekty typu Takson i wypełnia je danymi
	        ArrayList<BaseUser> taksony = new ArrayList<>();
	        for (String linia : odczyt) {
	            // Dzielenie na poszczególne pola (oodzielone przecinkami)
	            String[] l = linia.split(",");
	            BaseUser takson;
	            takson = new BaseUser(
	                    Integer.parseInt(l[0]), // nr uztykownika
	                    l[1], // imie
	                    l[2], // nazwisko,
	                    l[3], //adres
	                    l[4], //email
	                    l[5],//nr telefonu
	                    Integer.parseInt(l[6]),// kod genetyczny
	                    l[7],//login
	                    l[8],//hasło
	                    Integer.parseInt(l[9])//uprawnienia		
	            );
	            taksony.add(takson);
	        }

	        return taksony;
	    }

	    //************* Metoda wypisanie danych *************// 
	    
	    public static void wypisz_Dane() {
	        
	        listaUżytkowników = czytaj_Plik();
	        // Zamiana przecinków na tabulatory w nagłówku - bardziej przejrzyste
	        System.out.println(naglowki.replace(",", "\t"));
	        System.out.println("===============================================");
	        // Pobieranie każdego taksonu i wywołanie metody toString przyjmującej
	        // jako argument rodzaj reparatora do wyświetlenia, tu jest to tabulator
	        for (BaseUser takson : listaUżytkowników) {
	            System.out.println(takson.toString("\t"));
	        }
	    }

	    //************* Metoda dodanie nowego produktu *************// 
	    
	    public static void dodaj_Dane() {
	        listaUżytkowników = czytaj_Plik();
	        // Numer nowego taksonu powinien być o 1 większy niż
	        // najwyższy obecnie istniejący
	        int nowyId = znajdz_MaxId() + 1;
	        
	        
	        String produkty = JOptionPane.showInputDialog("Podaj produkt :");
	        String nazwa_kalorie = JOptionPane.showInputDialog("Podaj kalorie :");
	        int kalorie = Integer.parseInt(nazwa_kalorie);
	        // Tworzenie obiektu, wykorzystanie konstruktora przyjmującego
	        // jako argument numer
	        BaseUser nowy = new BaseUser(nowyId);
	        //Usatwimy nowe produkt i kalorie
	      //  nowy.setProdukt(produkty);
	       // nowy.setKalorie(kalorie);
	        
	        
	       
	        
	                listaUżytkowników.add(nowy);
	                
	        sciezka = "C:\\Users\\Gregory\\Documents\\JavaEclipse\\Fitnes\\Baza\\uzytkownik.csv";
	        Path sciezkaDoPliku = Paths.get(sciezka);        
	        
	        ArrayList<String> out = new ArrayList<String>();
	        out.add(naglowki);
	        
	        for (BaseUser takson : listaUżytkowników) {
	            out.add(takson.toString(","));
	        }
	        try {
	            Files.write(sciezkaDoPliku, out);
	            JOptionPane.showMessageDialog(null, " Zaostał dodany produkt '" + produkty+"' o kalori "+kalorie, "Informacja", JOptionPane.INFORMATION_MESSAGE);
	        } catch (IOException ex) {
	            JOptionPane.showInternalMessageDialog(null,"Niestety nie moge utworzyć pliku !", "Informacja", JOptionPane.WARNING_MESSAGE);
	        }
	        
	    }
	    
	    //************* Metoda wyszukuje największy numer produktu *************// 
	           // przyda się do wyznaczenia numeru dla nowego produktu

	    public static int znajdz_MaxId() {
	        int max = 0;
	        for (BaseUser t : listaUżytkowników) {
	            if (t.getNr() > max) {
	                max = t.getNr();
	            }
	        }
	        return max;
	    }

	    //************* Metoda usuwanie z listy produktu *************//
	    
	    static void usun_Dane(){
	        listaUżytkowników = czytaj_Plik();
	        String numer_produktu = JOptionPane.showInputDialog("Podaj numer uzytkownika, który chcesz usunąć");
	        int numer = Integer.parseInt(numer_produktu);
	        sciezka = "C:\\Users\\Gregory\\Documents\\JavaEclipse\\Fitnes\\Baza\\uzytkownik.csv";
	        Path sciezkaDoPliku = Paths.get(sciezka);
	        
	        boolean usuniety = false;

	        // Wyszukanie taksonu
	        for (int i = 0; i < listaUżytkowników.size(); i++) {
	            BaseUser takson = listaUżytkowników.get(i);
	            int numerTaksonu = takson.getNr();
	            if (numerTaksonu == numer) {
	                listaUżytkowników.remove(i);
	                // zaznacz jeśli dokonane zostało usunięcie
	                usuniety = true;
	                break;
	            }
	        }
	         ArrayList<String> out = new ArrayList<String>();
	        out.add(naglowki);
	        
	        for (BaseUser takson : listaUżytkowników) {
	            out.add(takson.toString(","));
	        }
	        try {
	            Files.write(sciezkaDoPliku, out);
	        } catch (IOException ex) {
	            
	            JOptionPane.showInternalMessageDialog(null,"Niestety nie moge utworzyć pliku !", "Informacja", JOptionPane.WARNING_MESSAGE);
	        }
	        
	        if (usuniety) {
	            JOptionPane.showMessageDialog(null, "Produkt nr. " + numer + " usuniety ", "Informacja", JOptionPane.INFORMATION_MESSAGE);
	            
	        } else {
	             JOptionPane.showMessageDialog(null, "Nie ma takiego produktu o numerze " + numer, "Informacja", JOptionPane.WARNING_MESSAGE);
	        }

	    }
	    
	    //************* Metoda zapis danych *************// 

	    public static void zapisz_Dane() {

	        Path sciezkaDoPliku = Paths.get(sciezka);

	        System.out.println("Podaj ścieżkę i plik do zapisu \n"
	                + "Jeśli chcesz zapisać do bierzącego pliku: \n"
	                + sciezka + "\n wciśnij enter");

	        Scanner skaner = new Scanner(System.in);
	        String sc = skaner.nextLine();

	        // Jeśli użytkownik wpisał ścieżkę
	        if (sc.length() > 0) {
	            sciezkaDoPliku = Paths.get(sc);
	        }

	        ArrayList<String> out = new ArrayList<String>();
	        out.add(naglowki);
	        for (BaseUser takson : listaUżytkowników) {
	            out.add(takson.toString(","));
	        }
	        try {
	            Files.write(sciezkaDoPliku, out);
	        } catch (IOException ex) {
	            JOptionPane.showInternalMessageDialog(null,"Niestety nie moge utworzyć pliku !", "Informacja", JOptionPane.WARNING_MESSAGE);
	        }
	    }

	    //************* Metoda sprawdzające poprawność logowania *************// 
	    
	    public boolean Logowanie(String Login,String Hasło)
	    {
	    	int numer;
	    	String login = null,hasło = null;
	    	
	    	listaUżytkowników = czytaj_Plik();
	    	
	    	
	    		 for (BaseUser logwanie : listaUżytkowników){
	    		       
	    		        login = logwanie.getLogin();
	    		       
	    		        if (Login.equals(login))
	    		        {
	    		        	 poprawne_Login = true;
	    		        }else{
	    		        	
	    		        	 poprawne_Login = false;
	    		        }
	    		        		
	    		        }
	    		 
	    		
   	
	    		 for (BaseUser logowanie : listaUżytkowników){
	    		        
	    		        hasło = logowanie.getHaslo();
	    		        
	    		        if (Hasło.equals(hasło))
	    		        {
	    		        	 poprawne_Hasło = true;
	    		        }else{
	    		        	
	    		        	 poprawne_Hasło = false;
	    		        }
	    		        
	    		        }
	    		 
	    	boolean zgoda = (poprawne_Hasło && poprawne_Login);
	    	
	    	return zgoda;
	    }
	    
	    //************* Metoda sprawdzające uprawnienia logowania *************// 
	    
	    int Uprawnienia(String Login,String Hasło)
	    {
	    	
	    	
	    	String login = null,hasło = null;
	    	
	    	 for (BaseUser logowanie : listaUżytkowników){
  		       
 		        login = logowanie.getLogin();
 		       
 		        if (Login.equals(login))
 		        {
 		        	 poprawne_Login = true;
 		        	 uprawnienia = logowanie.getUprawnienia();
 		        	 
 		        }else{
 		        	
 		        	 poprawne_Login = false;
 		        }
 		        		
 		        }
 		 
 		

 		 for (BaseUser logowanie : listaUżytkowników){
 		        
 		        hasło = logowanie.getHaslo();
 		        
 		        if (Hasło.equals(hasło))
 		        {
 		        	 poprawne_Hasło = true;
 		        	 uprawnienia = logowanie.getUprawnienia();
 		        }else{
 		        	
 		        	 poprawne_Hasło = false;
 		        }
 		        
 		        }
	    	
	    	
	    	return uprawnienia;
	    }
	    
	    //************* Metoda sprawdzające numer logowania *************// 
	    
	    int Numer_uzytkownika(String Login,String Hasło){
	    	String login = null,hasło = null;
	    	
	    	 for (BaseUser logowanie : listaUżytkowników){
 		       
		        login = logowanie.getLogin();
		       
		        if (Login.equals(login))
		        {
		        	 poprawne_Login = true;
		        	 numer_uzytkownika = logowanie.getNr();
		        	 
		        }else{
		        	
		        	 poprawne_Login = false;
		        }
		        		
		        }
		 
		

		 for (BaseUser logowanie : listaUżytkowników){
		        
		        hasło = logowanie.getHaslo();
		        
		        if (Hasło.equals(hasło))
		        {
		        	 poprawne_Hasło = true;
		        	 numer_uzytkownika = logowanie.getNr();
		        }else{
		        	
		        	 poprawne_Hasło = false;
		        }
		        
		        }
	    	
	    	
	    	return numer_uzytkownika;
	    }
	    
	    //************* Metoda zbierająca dane poza logowaniu *************// 
	    
	    @SuppressWarnings("null")
		String [] Dane(int numer){
	    	
	    	String[] dane = new String[7];
	    	
	    	 for (BaseUser dane_uzytkownikow : listaUżytkowników)
	    	 {
	    		 int numer_pomocniczy = dane_uzytkownikow.getNr();
	    		 
	    		 if(numer==numer_pomocniczy)
	    		 {
	    			 dane[1] = dane_uzytkownikow.getImie();
	    			 dane[2] = dane_uzytkownikow.getNazwisko();
	    			 dane[3] = dane_uzytkownikow.getAdres();
	    			 dane[4] = dane_uzytkownikow.getEmail();
	    			 dane[5] = dane_uzytkownikow.getLogin();
	    			 dane[6] = dane_uzytkownikow.getHaslo();
	    			 
	    		 }
	    	 }
	    	
	    	return dane;
	    }
	    
	    //************* Metoda zbierająca dane poza logowaniu *************// 
	    
	    int []Dane_liczbowe(int numer){
	    	
	    	int [] dane = new int [5];
	    	 for (BaseUser dane_uzytkownikow : listaUżytkowników)
	    	 {
	    		 int numer_pomocniczy = dane_uzytkownikow.getNr();
	    		 
	    		 if(numer==numer_pomocniczy)
	    		 {
	    			 dane[1] = dane_uzytkownikow.getKod_gentyczny();
	    			 String numer_tel = dane_uzytkownikow.getNumer_telefonu();
	    			 int tel = Integer.valueOf(numer_tel);
	    			 dane[2] = tel;
	    		 }
	    	 }
	    	
	    	
	    	return dane;
	    }
	    
	    //************* Metoda zbierająca dane poza logowaniu *************// 
	    
	    public String [] Rejestracja(String imie,String nazwisko,String adres,String email,String nr_tlefonu,String login,String haslo){
	    	
	    	String[] tab_rejestracja = new String [10]; 
	    	
	    	 listaUżytkowników = czytaj_Plik();
		        // Numer nowego taksonu powinien być o 1 większy niż
		        // najwyższy obecnie istniejący
		        int nowyId = znajdz_MaxId() + 1;
		        
		        
		       
		        // Tworzenie obiektu, wykorzystanie konstruktora przyjmującego
		        // jako argument numer
		        BaseUser nowy = new BaseUser(nowyId);
		        //Usatwimy nowe produkt i kalorie
		        nowy.setImie(imie);
		        nowy.setNazwisko(nazwisko);
		        nowy.setAderes(adres);
		        nowy.setEmail(email);
		        nowy.setNumer(nr_tlefonu);
		        nowy.setLogin(login);
		        nowy.setHaslo(haslo);
		        nowy.setUprawnienia(1);

		        listaUżytkowników.add(nowy);
		                
		        sciezka = "C:\\Users\\Gregory\\Documents\\JavaEclipse\\Fitnes\\Baza\\uzytkownik.csv";
		        Path sciezkaDoPliku = Paths.get(sciezka);        
		        
		        ArrayList<String> out = new ArrayList<String>();
		        out.add(naglowki);
		        
		        for (BaseUser takson : listaUżytkowników) {
		            out.add(takson.toString(","));
		        }
		        try {
		            Files.write(sciezkaDoPliku, out);
		            JOptionPane.showMessageDialog(null, " Zaostał dodany poprawnie użytkownik '" +login, "Informacja", JOptionPane.INFORMATION_MESSAGE);
		        } catch (IOException ex) {
		            JOptionPane.showInternalMessageDialog(null,"Niestety nie moge utworzyć profilu !", "Informacja", JOptionPane.WARNING_MESSAGE);
		        }
		        
	    	
	    	
	    	
	    	return tab_rejestracja;
	    }
	    
	    
	public static void main(String[] args) {

	}

}
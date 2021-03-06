
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author Greg
 */

public class GeneticAlgorithm extends JFrame {

    //Do przcowywania bzay produktów

    static ArrayList<BaseProduct> listaProduktów = new ArrayList<BaseProduct>();
    //Zmiene do kalori
    private static int wzrost;
    private static int waga;
    private static int wiek;
   
    //Zmiene do scieżki bazy produktów
   // static String sciezka;
    static String sciezka = "C:\\Users\\Gregory\\Documents\\JavaEclipse\\Fitnes\\Baza\\produkty.csv";
    static String naglowki;
    //Zmiene do wyprwadzenia tekstu
    static String[] tekst = new String[15];
    static String osobniki;
    //Zmiene do wygenrowania 100osobników
    private static int ile_osobników = 100;
    private static int ile_gnenów = 15;
    private static int [][]tablica_genów = new int[ile_osobników][ile_gnenów];// Tworzymy tablicę króra przechowa 100 0sbników 
    private static int [] kod_osobnika = new int[15];// Tworzymy tablicę króra przechowa ID kod osobinika
    private static int [] kod_najlepszego_osobnika = new int[15];
    private static int [] suma_kalori = new int[100];
    private static int [] suma_kalori_mutacji = new int [100];
    private static int [] FC_wartosc_bez_minus = new int [100];
    private static double [] FC_wartosc_bez_prim = new double [100];
    private static int [][] tablica_selekcja = new int [100][15];
    private static double [] PW = new double[100];
    private static double [] Rmin = new double[100];
    private static double [] Rmax = new double[100];
    private static int ile_powtorzen = 16;
    private static int ile_juz_wylosowano = 0;
    private static int[] wylosowane_pieczywa = new int[ile_powtorzen + 1];
    private static int[] wylosowane_owoce = new int[ile_powtorzen + 1];
    private static int[] wylosowane_miesa = new int[ile_powtorzen + 1];
    
    private static int[]kalorie_produktu = new int [1200];
    private static int[]numery = new int [1200];
    private static  int tab_numer []= new int [1200];
    private static int numer =0;
    /**
     *
     * @param wzrost
     * @param waga
     * @param wiek
     
     */
    
    public GeneticAlgorithm(int wzrost, int waga, int wiek) {
        this.wzrost = wzrost;
        this.waga = waga;
        this.wiek = wiek;
        
    }

    //************* Metoda wczytująca plik z danymi *************// 
    
    public static ArrayList<BaseProduct> czytaj_Plik(String sciezka) {
        // sciezka = "C:\\Users\\Gregory\\Documents\\JavaEclipse\\Fitnes\\Baza\\produkty.csv";
        
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
        ArrayList<BaseProduct> taksony = new ArrayList<>();
        for (String linia : odczyt) {
            // Dzielenie na poszczególne pola (oodzielone przecinkami)
            String[] l = linia.split(",");
            BaseProduct takson;
            takson = new BaseProduct(
                    Integer.parseInt(l[0]), // nr produktu
                    l[1], // produkt
                    Integer.parseInt(l[2]) // kalorie
            );
            taksony.add(takson);
        }
       // Collections.sort(taksony);
        return taksony;
    }

    //************* Metoda wypisanie danych *************// 
    
    public static void wypisz_Dane(String sciezka) {
        
        listaProduktów = czytaj_Plik(sciezka);
        // Zamiana przecinków na tabulatory w nagłówku - bardziej przejrzyste
        System.out.println(naglowki.replace(",", "\t"));
        System.out.println("===============================================");
        // Pobieranie każdego taksonu i wywołanie metody toString przyjmującej
        // jako argument rodzaj reparatora do wyświetlenia, tu jest to tabulator
        for (BaseProduct takson : listaProduktów) {
            System.out.println(takson.toString("\t"));
        }
    }

    //************* Metoda dodanie nowego produktu *************// 
    
    public static void dodaj_Dane(String sciezka) {
    	
        listaProduktów = czytaj_Plik(sciezka);
        // Numer nowego taksonu powinien być o 1 większy niż
        // najwyższy obecnie istniejący
        int nowyId = znajdz_MaxId() + 1;
        
        
        String produkty = JOptionPane.showInputDialog("Podaj produkt :");
        String nazwa_kalorie = JOptionPane.showInputDialog("Podaj kalorie :");
        int kalorie = Integer.parseInt(nazwa_kalorie);
        // Tworzenie obiektu, wykorzystanie konstruktora przyjmującego
        // jako argument numer
        BaseProduct nowy = new BaseProduct(nowyId);
        //Usatwimy nowe produkt i kalorie
        nowy.setProdukt(produkty);
        nowy.setKalorie(kalorie);
        
        
       
        
                listaProduktów.add(nowy);
                
                
        //if w zależności od perametru        
                
       // sciezka = "C:\\Users\\Gregory\\Documents\\JavaEclipse\\Fitnes\\Baza\\produkty.csv";
        Path sciezkaDoPliku = Paths.get(sciezka);        
        
        ArrayList<String> out = new ArrayList<String>();
        out.add(naglowki);
        
        for (BaseProduct takson : listaProduktów) {
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
        for (BaseProduct t : listaProduktów) {
            if (t.getNr() > max) {
                max = t.getNr();
            }
        }
        return max;
    }

    //************* Metoda usuwanie z listy produktu *************//
    
    static void usun_Dane(String sciezka){
    	
        listaProduktów = czytaj_Plik(sciezka);
        String numer_produktu = JOptionPane.showInputDialog("Podaj numer taksonu, który chcesz usunąć");
        int numer = Integer.parseInt(numer_produktu);
       // sciezka = "C:\\Users\\Gregory\\Documents\\JavaEclipse\\Fitnes\\Baza\\produkty.csv";
        Path sciezkaDoPliku = Paths.get(sciezka);
        
        boolean usuniety = false;

        // Wyszukanie taksonu
        for (int i = 0; i < listaProduktów.size(); i++) {
            BaseProduct takson = listaProduktów.get(i);
            int numerTaksonu = takson.getNr();
            if (numerTaksonu == numer) {
                listaProduktów.remove(i);
                // zaznacz jeśli dokonane zostało usunięcie
                usuniety = true;
                break;
            }
        }
         ArrayList<String> out = new ArrayList<String>();
        out.add(naglowki);
        
        for (BaseProduct takson : listaProduktów) {
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
        for (BaseProduct takson : listaProduktów) {
            out.add(takson.toString(","));
        }
        try {
            Files.write(sciezkaDoPliku, out);
        } catch (IOException ex) {
            JOptionPane.showInternalMessageDialog(null,"Niestety nie moge utworzyć pliku !", "Informacja", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    /**
     * <*Pdstawowe tempo metabolizmu - BMR
     *<Harris-Benedict - wzór ten został stworzony w 1919 roku i ze względu na zmiany stylu życia, ma tendencję do zawyżania 
     *<zapotrzebowania kalorycznego o ok. 5%. Najczęściej wykorzystywany wzór. Przeznaczony jest głównie dla osób młodych i otyłych.
     *<Więcej informacji znajdziesz na: http://calcoolator.pl/dzienny_kalkulator_kalorii.html © calcoolator.pl
     */
    
     //************* Metoda wylicza podstawowe tempo BMR dla Kobiet *************//

    public float symulacja_Kobiet() {
        float kcal = (int) (655.5 + (9.563 * waga) + (1.85 * wzrost) - (4.676 * wiek));
        return kcal;
    }
    
    //************* Metoda wylicza podstawowe tempo BMR dla Mężczyzn  *************//
    
    public float symulacja_Meżczyzn() {
        float kcal = (int) (66.5 + (13.75 * waga) + (5.003 * wzrost) - (6.775 * wiek));
        return kcal;
    }
    
    //************* Metoda grneruje kod osobnika *************//
    
    public static int[] Kod_osobnika (int najlepszy_numer_ID,int tablica_genów[][]){
        
    //************* Zapis najlepszego osobnika kodu ID  *************//
        
        int[] kod_najlepszego_osobnika = new int [15]; 
        
        for (int kolumny = 0; kolumny < 15; kolumny++) {
            
            kod_najlepszego_osobnika[kolumny] = tablica_genów[najlepszy_numer_ID][kolumny];
            //System.err.print(kod_najlepszego_osobnika[kolumny]+", ");
        }
        
        return kod_najlepszego_osobnika;
    }
    
    //************* Metoda zapamiętuje najlepszego *************//
    
    public static int[] Najlepszy (int FC_wartosc[],int FC_wartosc_selekcja[],int tablica_genow[][],int tablica_selekcja[][] ) {
         
        int min = FC_wartosc[0];
        int najlepszy_numer_ID_min = 0;
        int najlepszy_numer_ID_selekt = 0;
        int min_selekcja = FC_wartosc_selekcja[0];
        
        for(int wiersze  = 1 ;wiersze <100; wiersze++)
        
             if (min > FC_wartosc[wiersze]) 
             {
                min = FC_wartosc[wiersze];                          
                najlepszy_numer_ID_min = wiersze; 
               // System.err.println("FC min "+min);
             }
        
        
        
        for(int wiersze = 0;wiersze < 100;wiersze++)
        {
            if(min_selekcja > FC_wartosc_selekcja[wiersze])
            {
                min_selekcja = FC_wartosc_selekcja[wiersze];
                najlepszy_numer_ID_selekt = wiersze;
               // System.err.println("FC selekt "+min_selekcja);
               
            }
            
        }
        
        int[] kod_najlepszego_osobnika = new int [15];
        
        if (min < min_selekcja)
        {
            for (int kolumny = 0; kolumny < 15; kolumny++) {
            
            kod_najlepszego_osobnika[kolumny] = tablica_genow[najlepszy_numer_ID_min][kolumny];
            //System.err.print(kod_najlepszego_osobnika[kolumny]+", ");
        }
            
        }else{
            for (int kolumny = 0; kolumny < 15; kolumny++) {
            
            kod_najlepszego_osobnika[kolumny] = tablica_selekcja[najlepszy_numer_ID_selekt][kolumny];
           // System.err.print(kod_najlepszego_osobnika[kolumny]+", ");
        }
        }
        
        
          
     return kod_najlepszego_osobnika;
           
    }
    
    //************* Metoda zapamiętuje najlepszego kalorie *************//
    
    public static int Najlepszy_kalorie(int kod_najlepszego[]){
        
        //************* Zapisanie z tablicy genów do tab.kalorie_produktu *************//
        
        int[]kalorie_produktu = new int [1200];
        int numer = 0;
        for (BaseProduct suma : listaProduktów){
        numer = suma.getNr();
        kalorie_produktu [numer] = suma.getKalorie();
       // System.err.println("kal "+ kalorie_produktu[numer]);
        }
        
    //************* Suma kolori osobnikanajlepszego *************//    
        int suma_najlepszy = 0;
        int[] ID_wierszy = new int[100];
        for (int wiersze = 0; wiersze < 100; wiersze++) {suma_kalori[wiersze]=0;}
         
        
            for (int kolumny = 0; kolumny < 15; kolumny++) {
                
                ID_wierszy[kolumny] = kod_najlepszego[kolumny];
                suma_najlepszy += kalorie_produktu[ID_wierszy[kolumny]];
            
           //  System.out.println(tablica_najlepszy[kolumny]+"");
        }
        return suma_najlepszy;
    }
    
    //************* Metoda oblicza FC *************//
    
    public static int [] Wartosc_FC (int kcal,int suma_kalori[]){

         //FC - funkcja aktywacji (FUNKCJA PRZYSTOSOWANIA CHROMOSOMU)  

         int[] FC_wartosc_bez = new int [100];
        
        for (int wiersze = 0; wiersze < 100; wiersze++) {
           
           
             //Funkcja aktywacji jest watościa bezwgledna
             FC_wartosc_bez[wiersze] =  (int) Math.abs(suma_kalori[wiersze]-kcal);//wartość bezwgledna
             // System.out.println("Watrosc bezwzgledna -->  "+FC_wartosc_bez[wiersze]+" , kalorie wprowadzone -> "+ kcal);
        }
        
        return FC_wartosc_bez;
    }
    
    //************* Metoda dokonuje zamiany *************//
    
    public static int [][] Zamiana(int tablica_selekcja[][],int kod_najlepszego_osobnika []){
        
        for (int wiersze = 0; wiersze < 100; wiersze++) {
            for (int kolumny = 0; kolumny < 15; kolumny++) {
                
                tablica_selekcja[1][kolumny] = kod_najlepszego_osobnika[kolumny];
            }
        }
        return tablica_selekcja;
    }
    
    //************* Metoda zbiera FC - przystosowanie osobnika *************//  
    
    public static int LiczbaFC(int FC_wartosc[],int FC_wartosc_selekcja[],int tablica_genow[][],int tablica_selekcja[][]){
       
        int min = FC_wartosc[0];
        int najlepszy_numer_ID_min = 0;
        int najlepszy_numer_ID_selekt = 0;
        int min_selekcja = FC_wartosc_selekcja[0];
        int liczba_FC;
        
        for(int wiersze  = 1 ;wiersze <100; wiersze++)
        
             if (min > FC_wartosc[wiersze]) 
             {
                min = FC_wartosc[wiersze];                          
                najlepszy_numer_ID_min = wiersze; 
               // System.err.println("FC min "+min);
             }
        
        
        
        for(int wiersze = 0;wiersze < 100;wiersze++)
        {
            if(min_selekcja > FC_wartosc_selekcja[wiersze])
            {
                min_selekcja = FC_wartosc_selekcja[wiersze];
                najlepszy_numer_ID_selekt = wiersze;
               // System.err.println("FC selekt "+min_selekcja);
               
            }
            
        }
        
        int[] kod_najlepszego_osobnika = new int [15];
        
        if (min < min_selekcja)
        {
            liczba_FC=min;
            
        }else{
            liczba_FC = min_selekcja;
        }
        
        return liczba_FC;
    }
    
    //************* Metoda zbiera PW  *************//
    
     public static float PW (int FC_wartosc_bez[]){
         int max = FC_wartosc_bez[0];
        int najlepszy_numer_ID = 0;
        float suma_FC_wartosc_bez_prim = 0;
        float suma_PW = 0;
        int[] FC_wartosc_bez_minus = new int [100];
        double[] FC_wartosc_bez_prim = new double[100];
        double[] PW = new double[100];
        double[] Rmin = new double[100];
        double[]  Rmax = new double[100];
        int[][] tablica_selekcja = new int [100][15];
        
        //************* Wyszukanie maksymalnej wartości FC *************//
        
        for(int wiersze = 0;wiersze < 100;wiersze++)
        {
            
            FC_wartosc_bez_minus[wiersze] = -(FC_wartosc_bez[wiersze]);
            //System.err.println("Wartości bez minus :"+FC_wartosc_bez_minus[wiersze]);
             if (max<FC_wartosc_bez[wiersze]) 
             {
                max = FC_wartosc_bez[wiersze];                          
                najlepszy_numer_ID = wiersze; 
             }
        }
        
    //************* Dodanie do FC max_plus *************//
        
        double max_plus = max + 0.1;
        
        for(int wiersze = 0;wiersze < 100;wiersze++)
        {
             FC_wartosc_bez_prim[wiersze] =  FC_wartosc_bez_minus[wiersze] + max_plus; 

        }    
        for(int wiersze = 0;wiersze < 100;wiersze++)
        {
             suma_FC_wartosc_bez_prim += FC_wartosc_bez_prim[wiersze];
        }  
    //************* Oblicznie wartości PW *************//      
        
        for(int wiersze = 0;wiersze < 100;wiersze++)
        {
            PW[wiersze] = FC_wartosc_bez_prim[wiersze]/suma_FC_wartosc_bez_prim;
            suma_PW += PW[wiersze]; 
        }   // System.out.print("suma PW -> "+suma_PW);
        return suma_PW;
    }
     
    //************* Metoda zbiera Rmin  *************//
     
    public static double Rmin (int FC_wartosc_bez[]){
        
        double Liczba_Rmin = 0;
        int max = FC_wartosc_bez[0];
        int najlepszy_numer_ID = 0;
        float suma_FC_wartosc_bez_prim = 0;
        float suma_PW = 0;
        int[] FC_wartosc_bez_minus = new int [100];
        double[] FC_wartosc_bez_prim = new double[100];
        double[] PW = new double[100];
        double[] Rmin = new double[100];
        double[]  Rmax = new double[100];
        int[][] tablica_selekcja = new int [100][15];
    //************* Wyszukanie maksymalnej wartości FC *************//
        
        for(int wiersze = 0;wiersze < 100;wiersze++)
        {
            
            FC_wartosc_bez_minus[wiersze] = -(FC_wartosc_bez[wiersze]);
            //System.err.println("Wartości bez minus :"+FC_wartosc_bez_minus[wiersze]);
             if (max<FC_wartosc_bez[wiersze]) 
             {
                max = FC_wartosc_bez[wiersze];                          
                najlepszy_numer_ID = wiersze; 
             }
        }
        
    //************* Dodanie do FC max_plus *************//
        
        double max_plus = max + 0.1;
        
        for(int wiersze = 0;wiersze < 100;wiersze++)
        {
             FC_wartosc_bez_prim[wiersze] =  FC_wartosc_bez_minus[wiersze] + max_plus; 

        }    
        for(int wiersze = 0;wiersze < 100;wiersze++)
        {
             suma_FC_wartosc_bez_prim += FC_wartosc_bez_prim[wiersze];
        }  
    //************* Oblicznie wartości PW *************//      
        
        for(int wiersze = 0;wiersze < 100;wiersze++)
        {
            PW[wiersze] = FC_wartosc_bez_prim[wiersze]/suma_FC_wartosc_bez_prim;
            suma_PW += PW[wiersze]; 
        }   // System.out.print("suma PW -> "+suma_PW);
       
    //************* Oblicznie wartości Rmin*************//   
        
        Rmin[0] = 0;// do pierwszy elemeny zerowy
        
        for (int wiersze  = 1; wiersze < 100; wiersze++)
        {
            Rmin[wiersze] = Rmin[wiersze-1] + PW[wiersze-1];
            // System.err.println("Rmin -["+wiersze+"]-> "+Rmin[wiersze]);
            Liczba_Rmin = Rmin[wiersze];
        }
        return Liczba_Rmin;
    }
    
    //************* Metoda zbiera Rmax  *************//
    
     public static double Rmax (int FC_wartosc_bez[]){
         
        double Liczba_Rmax = 0;
        int max = FC_wartosc_bez[0];
        int najlepszy_numer_ID = 0;
        float suma_FC_wartosc_bez_prim = 0;
        float suma_PW = 0;
        int[] FC_wartosc_bez_minus = new int [100];
        double[] FC_wartosc_bez_prim = new double[100];
        double[] PW = new double[100];
        double[] Rmin = new double[100];
        double[]  Rmax = new double[100];
        int[][] tablica_selekcja = new int [100][15];
    //************* Wyszukanie maksymalnej wartości FC *************//
        
        for(int wiersze = 0;wiersze < 100;wiersze++)
        {
            
            FC_wartosc_bez_minus[wiersze] = -(FC_wartosc_bez[wiersze]);
            //System.err.println("Wartości bez minus :"+FC_wartosc_bez_minus[wiersze]);
             if (max<FC_wartosc_bez[wiersze]) 
             {
                max = FC_wartosc_bez[wiersze];                          
                najlepszy_numer_ID = wiersze; 
             }
        }
        
    //************* Dodanie do FC max_plus *************//
        
        double max_plus = max + 0.1;
        
        for(int wiersze = 0;wiersze < 100;wiersze++)
        {
             FC_wartosc_bez_prim[wiersze] =  FC_wartosc_bez_minus[wiersze] + max_plus; 

        }    
        for(int wiersze = 0;wiersze < 100;wiersze++)
        {
             suma_FC_wartosc_bez_prim += FC_wartosc_bez_prim[wiersze];
        }  
    //************* Oblicznie wartości PW *************//      
        
        for(int wiersze = 0;wiersze < 100;wiersze++)
        {
            PW[wiersze] = FC_wartosc_bez_prim[wiersze]/suma_FC_wartosc_bez_prim;
            suma_PW += PW[wiersze]; 
        }   // System.out.print("suma PW -> "+suma_PW);
       
    //************* Oblicznie wartości Rmin*************//   
        
        Rmin[0] = 0;// do pierwszy elemeny zerowy
        
        for (int wiersze  = 1; wiersze < 100; wiersze++)
        {
            Rmin[wiersze] = Rmin[wiersze-1] + PW[wiersze-1];
            // System.err.println("Rmin -["+wiersze+"]-> "+Rmin[wiersze]);
        }
        
    //************* Oblicznie wartości Rmax *************//    
        
        Rmax[0] = PW[0];// do pierwszy elemeny z tablicy PW[0]
        int [] numer_osobnika = new int [100];
        
         for (int wiersze  = 1; wiersze < 100; wiersze++)
        {
            Rmax[wiersze] = Rmax[wiersze-1] + PW[wiersze];
            Liczba_Rmax = Rmax[wiersze];
        }
        return Liczba_Rmax;
    }  
     
      //************* Metoda zbirera FC'  *************//    
     
     public static int FC_(int kcal,int suma_kalori[]){
    
         int FC_ = 0;
      int[] FC_wartosc_bez = new int [100];
        
        for (int wiersze = 0; wiersze < 100; wiersze++) {
           
           
             
             FC_wartosc_bez[wiersze] =  (int) Math.abs(suma_kalori[wiersze]-kcal);//wartość bezwgledna
             FC_ = FC_wartosc_bez[wiersze];
        }
    
    
    return FC_;
}
     
    //************* Metoda zbiera FC' suma  *************//
     
     public static double FCprimsuma (int FC_wartosc_bez[]){
         
        double FCprim = 0; 
        int max = FC_wartosc_bez[0];
        int najlepszy_numer_ID = 0;
        float suma_FC_wartosc_bez_prim = 0;
        float suma_PW = 0;
        int[] FC_wartosc_bez_minus = new int [100];
        double[] FC_wartosc_bez_prim = new double[100];
        double[] PW = new double[100];
        double[] Rmin = new double[100];
        double[]  Rmax = new double[100];
        int[][] tablica_selekcja = new int [100][15];
    //************* Wyszukanie maksymalnej wartości FC *************//
        
        for(int wiersze = 0;wiersze < 100;wiersze++)
        {
            
            FC_wartosc_bez_minus[wiersze] = -(FC_wartosc_bez[wiersze]);
            //System.err.println("Wartości bez minus :"+FC_wartosc_bez_minus[wiersze]);
             if (max<FC_wartosc_bez[wiersze]) 
             {
                max = FC_wartosc_bez[wiersze];                          
                najlepszy_numer_ID = wiersze; 
             }
        }
        
    //************* Dodanie do FC max_plus *************//
        
        double max_plus = max + 0.1;
        
        for(int wiersze = 0;wiersze < 100;wiersze++)
        {
             FC_wartosc_bez_prim[wiersze] =  FC_wartosc_bez_minus[wiersze] + max_plus; 

        }    
        for(int wiersze = 0;wiersze < 100;wiersze++)
        {
             suma_FC_wartosc_bez_prim += FC_wartosc_bez_prim[wiersze];
             FCprim = suma_FC_wartosc_bez_prim;
        }  
        
        
        return FCprim;
    }
     
    //************* Metoda generuję osobników  *************//       
     
    public int[][] Inicjacja() {

        boolean losowane_ok;

        Random genertor = new Random();
        /*
                for (int i = 1; i <= ile_powtorzen; i++) {
        
                    do {
                       int liczba_losowa_pieczywa = genertor.nextInt(15) + 1;
                       losowane_ok = true;
                       for (int j = 1; j <= ile_juz_wylosowano; j++) {
        
                            if (liczba_losowa_pieczywa == wylosowane_pieczywa[j]) {
                                losowane_ok = false;
                            }
                        }
                       if (losowane_ok = true) {
                            ile_juz_wylosowano++;
                           wylosowane_pieczywa[ile_juz_wylosowano] = liczba_losowa_pieczywa;
                        }
                    } while (losowane_ok != true);
               }
        */
     
        for (int wiersze = 0; wiersze < 100; wiersze++) {
            for (int kolumny = 0; kolumny < 15; kolumny++) {
                if (kolumny <= 1) {
                      //napoje (1-133)     
                    tablica_genów[wiersze][kolumny] = genertor.nextInt(133) + 1;
                }
                if (kolumny >= 2) {
                       //nasiona(134-169)   
                    tablica_genów[wiersze][kolumny] = genertor.nextInt(35) + 133;
                }
                 if (kolumny >= 3) {
                       //owoce(170-324) 
                    tablica_genów[wiersze][kolumny] = genertor.nextInt(154) + 170;
                }      
                 if (kolumny >= 4) {
                       //przyprawy(482-554)
                    tablica_genów[wiersze][kolumny] = genertor.nextInt(72) + 482;
                }
                 if (kolumny >= 5) {
                        //mieso(555-606) 
                    tablica_genów[wiersze][kolumny] = genertor.nextInt(51) + 555;
                }
                  if (kolumny >= 6) {
                       //fsatfud(607-625) 
                    tablica_genów[wiersze][kolumny] = genertor.nextInt(19) + 607;
                }
                  if (kolumny >= 7) {
                       //grzyby(626-634)  
                    tablica_genów[wiersze][kolumny] = genertor.nextInt(8) + 626;
                }
                  if (kolumny >= 8) {
                        //mieso(635-738)  
                    tablica_genów[wiersze][kolumny] = genertor.nextInt(103) + 635;
                }
                  if (kolumny >= 9) {
                        //nabiał(739-792)  
                    tablica_genów[wiersze][kolumny] = genertor.nextInt(53) + 739;
                }
                  if (kolumny >= 10) {
                       //ryby(793-873)  
                    tablica_genów[wiersze][kolumny] = genertor.nextInt(80) + 793;
                }
                  if (kolumny >= 12) {
                       //słodycze(874-951)    
                    tablica_genów[wiersze][kolumny] = genertor.nextInt(77) + 874;
                }
                  if (kolumny >= 13) {
                       //Masło(952-967)   
                    tablica_genów[wiersze][kolumny] = genertor.nextInt(15) + 952;
                } if (kolumny >= 14) {
                      //Warzywa(968-1100)     
                    tablica_genów[wiersze][kolumny] = genertor.nextInt(132) + 960;
                }
                 if (kolumny >= 15) {
                       //pieczywo(325-482)    
                    tablica_genów[wiersze][kolumny] = genertor.nextInt(157) + 325;
                }
            }

        }
    /*
                // Wyświtlenie w konsli
     for(int wiersze = 0; wiersze < 100; wiersze++) {
      for(int kolumny = 0; kolumny < 15; kolumny++) {
        System.out.print(tablica_genów[wiersze][kolumny]+" " );
      }
      System.out.println(" ");
    }

    */  
        return tablica_genów;
    }
    
    //************* Metoda ocenia osocniki i zapisuje najlepszy  *************//
    
    public static int[] Ocena(int kcal,int tablica_genów[][]) throws IOException {
       
        listaProduktów = czytaj_Plik(sciezka);
     
    //************* Zapisanie z tablicy genów do tab.kalorie_produktu *************//
        
        int[]kalorie_produktu = new int [1200];
        int numer = 0;
        for (BaseProduct suma : listaProduktów){
        numer = suma.getNr();
        kalorie_produktu [numer] = suma.getKalorie();
       // System.err.println("kal "+ kalorie_produktu[numer]);
        }
        
    //************* Suma kolori poszczególnych osobników *************//    
        
        int[] ID_wierszy = new int[100];
        for (int wiersze = 0; wiersze < 100; wiersze++) {suma_kalori[wiersze]=0;}
         
        for (int wiersze = 0; wiersze < 100; wiersze++) {
            for (int kolumny = 0; kolumny < 15; kolumny++) {
                
                ID_wierszy[wiersze] = tablica_genów[wiersze][kolumny];
                suma_kalori[wiersze] += kalorie_produktu[ID_wierszy[wiersze]];
            }
            // System.out.println(suma_kalori[wiersze]+"");
        }
    
       
        return suma_kalori;
    }
   
    //************* Metoda Slekcji Ruletkowej operatr MIN  *************// 

    public static int[][] Selekcja(int FC_wartosc_bez[]) {
       // PW - wspołczynik prawdopodobienstwa
        int max = FC_wartosc_bez[0];
        int najlepszy_numer_ID = 0;
        float suma_FC_wartosc_bez_prim = 0;
        float suma_PW = 0;
        int[] FC_wartosc_bez_minus = new int [100];
        double[] FC_wartosc_bez_prim = new double[100];
        double[] PW = new double[100];
        double[] Rmin = new double[100];
        double[]  Rmax = new double[100];
        int[][] tablica_selekcja = new int [100][15];
        
    //************* Wyszukanie maksymalnej wartości FC *************//
        
        for(int wiersze = 0;wiersze < 100;wiersze++)
        {
            
            FC_wartosc_bez_minus[wiersze] = -(FC_wartosc_bez[wiersze]);
            //System.err.println("Wartości bez minus :"+FC_wartosc_bez_minus[wiersze]);
             if (max<FC_wartosc_bez[wiersze]) 
             {
                max = FC_wartosc_bez[wiersze];                          
                najlepszy_numer_ID = wiersze; 
             }
        }
        
    //************* Dodanie do FC max_plus *************//
        
        double max_plus = max + 0.1;
        
        for(int wiersze = 0;wiersze < 100;wiersze++)
        {
             FC_wartosc_bez_prim[wiersze] =  FC_wartosc_bez_minus[wiersze] + max_plus; 

        }    
        for(int wiersze = 0;wiersze < 100;wiersze++)
        {
             suma_FC_wartosc_bez_prim += FC_wartosc_bez_prim[wiersze];
        }  
    //************* Oblicznie wartości PW *************//      
        
        for(int wiersze = 0;wiersze < 100;wiersze++)
        {
            PW[wiersze] = FC_wartosc_bez_prim[wiersze]/suma_FC_wartosc_bez_prim;
            suma_PW += PW[wiersze]; 
        }   // System.out.print("suma PW -> "+suma_PW);
       
    //************* Oblicznie wartości Rmin*************//   
        
        Rmin[0] = 0;// do pierwszy elemeny zerowy
        
        for (int wiersze  = 1; wiersze < 100; wiersze++)
        {
            Rmin[wiersze] = Rmin[wiersze-1] + PW[wiersze-1];
            // System.err.println("Rmin -["+wiersze+"]-> "+Rmin[wiersze]);
        }
        
    //************* Oblicznie wartości Rmax *************//    
        
        Rmax[0] = PW[0];// do pierwszy elemeny z tablicy PW[0]
        int [] numer_osobnika = new int [100];
        
         for (int wiersze  = 1; wiersze < 100; wiersze++)
        {
            Rmax[wiersze] = Rmax[wiersze-1] + PW[wiersze];
            // System.err.println("Rmax -["+wiersze+"]-> "+Rmax[wiersze]);
        }
    //************* Losowanie Ruletka z przedzialu [0;1] *************//    
         
        Random generator_ruletka = new Random();
        double [] strzal = new double[100];
        int  numer = 0;
        
        do{
              for (int wiersze  = 0; wiersze < 100; wiersze++)
        {
            strzal[wiersze] = generator_ruletka.nextDouble();
            //System.out.println("strzal --> "+strzal[wiersze]);     
        }
        
        for (int wiersze  = 0; wiersze < 100; wiersze++)
        {
             
             
            if ((strzal[wiersze]>= Rmin[wiersze])&&(strzal[wiersze]<=Rmax[wiersze]))
            {
                numer_osobnika[wiersze] = wiersze;
                numer++;
                // System.err.println("numer  = "+numer);
                // System.err.println(" strzal => "+wiersze);
            }
        }
       
            
        } while (numer<=100);
         
       for (int wiersze = 0; wiersze < 100; wiersze++) {
            for (int kolumny = 0; kolumny < 15; kolumny++) {
                
                tablica_selekcja[wiersze][kolumny] = tablica_genów[numer_osobnika[wiersze]][kolumny];
            }
           
        }
       //Wyświtlenie w konsli tablicy po selkcji 
       /*
       for (int wiersze = 0; wiersze < 100; wiersze++) {
            for (int kolumny = 0; kolumny < 15; kolumny++) {
                
                System.out.print(" "+ tablica_selekcja[wiersze][kolumny]);
            }
             System.out.println("");
        }
       */
         
         
        return tablica_selekcja;
    }
    
    //************* Metoda Krzyzowanie osobników   *************//
    
    public static int[][] Krzyzowanie(int tablica_selkt[][])   {
         
        /**
        * < W klasycznym algorytmie genetycznym stosuje sie dwa podstawowe operatory genetyczne: operator
        krzyzowania oraz operator mutacji. Nalezy jednak zaznaczyc, ze operator
        mutacji odgrywa zdecydowanie drugoplanowa role do operatora krzyzowania. Oznacza
        to, ze krzyzowanie w klasycznym algorytmie genetycznym wystepuje prawie
        zawsze, podczas gdy mutacja dosc rzadko. Prawdopodobienstwo wystapienia krzyzowanie
        przyjmuje sie zwykle duze (na ogół (0,5 <=pc<=1)
        
        pc - wspólczynik krzyzowania
        */
        Random generator_pc = new Random();
        
        float [] wspolczynik_pc = new float[100]; 
        float wspolczynik_krzyzowania = (float) 0.7;
        
    //************* Wylosanie współczynika krzyżowania " pc " *************//
        
          for (int wiersze = 0; wiersze < 100; wiersze++) {
           
                             
                wspolczynik_pc[wiersze] = generator_pc.nextFloat(); 
                
           //  System.err.println(wspolczynik_pc[wiersze]+"<-");
          }
          
    //************* Zapis Krzyzowanie osobników   *************//
   
          float []testowa_tablica_krzyzywanie = new float[100];
          float  []testowa_tablica_pozostale = new float[100];
          int[] numer_osobnika_krzyzowanie = new int [100];
          int [] numer_osobnika_pozostale  = new int [100];
          int numer_tab_k = -1;
          int numer_tab_p = -1;
          
          for (int wiersze = 0; wiersze < 100; wiersze++) {
           
               if (wspolczynik_krzyzowania > wspolczynik_pc[wiersze])
               {
                   numer_tab_k++;
                   testowa_tablica_krzyzywanie[numer_tab_k] = wspolczynik_pc[wiersze];
                   numer_osobnika_krzyzowanie[numer_tab_k] = wiersze; //? co jesli jest nie parzysta 
                   
               }else{
                   numer_tab_p++;
                   testowa_tablica_pozostale[numer_tab_p] = wspolczynik_pc[wiersze];
                   numer_osobnika_pozostale[numer_tab_p] = wiersze;
                   
               }
               
            }
          
         if (numer_tab_k%2==0){
   
         }else{
                numer_tab_k++; 
                numer_osobnika_krzyzowanie[numer_tab_k] = numer_tab_k;
            }
         
           float []tablica_krzyzowania  = new float[numer_tab_k];
           
          for (int wiersze = 0; wiersze < numer_tab_k; wiersze++ )
          {
              tablica_krzyzowania[wiersze] = testowa_tablica_krzyzywanie[wiersze];
             // System.out.println("[ "+wiersze+" ]"+tablica_krzyzowania[wiersze]);
          }
          tablica_krzyzowania[numer_tab_k-1] = testowa_tablica_krzyzywanie[generator_pc.nextInt(numer_tab_k-1)];
          
          for (int wiersze = 0; wiersze < numer_tab_k; wiersze++ )
          {
            //  System.out.println("[ "+wiersze+" ]"+tablica_krzyzowania[wiersze]);
          }
          /**
        * < Na tym etapie chromosomy z populacji rodzicielskiej kojarza w pary.
         Dokonuje sie tego w sposób losowy, zgodnie z prawdopodobienstwem krzyzowania
         pc. Nastepnie dla kazdej pary wybranych w ten sposób rodziców losuje sie pozycje
         genu (locus) w chromosomie okreslajaca tzw. punkt krzyzowania. Jezeli chromosom
         kazdego rodzica składa sie z L genów, to oczywiscie punkt krzyzowania lk jest liczba
         naturalna mniejsza od L. Zatem wybór punktu krzyzowania sprowadza sie do
         wylosowania liczby z przedziału [1, L − 1]. W wyniku krzyzowania chromosomów
         rodzicielskich otrzymuje sie nastepujaca pare potomków
        
        Locus - jest to pozycja wskazuje - miejsce położenia genu w łańcuchu,czyli chromosomie
        * L- liczba genów
       
          */
         
         int liczba_genów = 15;
         int Locus = 0;
        
         Random generator_Locus = new Random();
          
         for (int wiersze = 0; wiersze < numer_tab_k;wiersze = wiersze+2){
             for(int kolumny = 0; kolumny < 15; kolumny++ ){
                 Locus = generator_Locus.nextInt(liczba_genów-1)+1;
                 
                 if(Locus == kolumny)
                 {
                     for(int x = kolumny; x < 15 ;x++)
                     {
                        int indx = numer_osobnika_krzyzowanie[wiersze]; 
                        int gen = tablica_selekcja[indx][x];
                        tablica_selekcja[indx][x] = tablica_selekcja[indx+1][x];//tu jest jakiś bląd czasami
                        tablica_selekcja[indx+1][x] = gen;
                         //System.err.println("numer tabk --------->" + numer_tab_k);
                     }
                 }
                            
             }
        }
         
         /*
                    Wyświtlenie Tablicy selkscja
         for (int wiersze = 0; wiersze < numer_tab_k;wiersze = wiersze+2){
         // System.out.println(numer_osobnika_krzyzowanie[wiersze] + " " + numer_osobnika_krzyzowanie[wiersze+1]);
         }
         
         for (int wiersze = 0; wiersze < 100; wiersze++)
         {
         for(int kolumny = 0; kolumny < 15; kolumny++ )
         {
         if(kolumny == 14){
         System.out.println(tablica_selekcja[wiersze][kolumny]);
         }else
         System.out.print(tablica_selekcja[wiersze][kolumny] + " ");
         }
         }
         */

        return tablica_selekcja;
    }
    
    //************* Metoda Mutuje osobniki   *************//
    
    public static int[][] Mutacja(int kcal,int tablica_selekt[][]){
        
         /**
        * <  Natomiast zakłada sie bardzo małe prawdopodobienstwo zaistnienia mutacji (czesto 0 < pm < 0, 1 ).
        Wynika to takze z analogii do swiata organizmów zywych, gdzie mutacje zachodza
        niezwykle rzadko.
        W algorytmie genetycznym mutacja chromosomu moze byc dokonywana na populacji
        rodziców przed operacja krzyzowania lub na populacji potomków utworzonych
        w wyniku krzyzowania
        W klasycznym algorytmie genetycznym cała poprzednia populacja chromosomów zastepowana jest przez tak 
        samo liczna nowa populacje potomków.
        
        *  pm - wspólczynik mutacji

          */
       
        
          Random generator_pm = new Random();
        
        float [][] wspolczynik_pm = new float[100][15]; 
        float wspolczynik_mutacji = (float) 0.2;
        
    //************* Wylosanie współczynika mutacji " pm " *************//
        
          for (int wiersze = 0; wiersze < 100; wiersze++) {
               for (int kolumny = 0; kolumny < 15; kolumny++){
           
                             
                wspolczynik_pm[wiersze][kolumny] = generator_pm.nextFloat(); 
                
           //  System.err.println(wspolczynik_pc[wiersze]+"<-");
                 }
          }   
    //*************  Mutacja osobników   *************//
  
           /**
        * <  N Operator mutacji, zgodnie z prawdopodobienstwem mutacji pm, dokonujemy zmiany
        wartosci genu w chromosomie na przeciwna (np. z 0 na 1 lub z 1 na 0). Przykładowo,
        jesli w nastepujacym chromosomie [100110101010] mutacji podlega gen na
        pozycji 7, to jego wartosc wynoszaca 1 zmieniamy na 0 i otrzymujemy nastepujacy
        chromosom [100110001010]. Jak juz wczesniej wspomniano, prawdopodobienstwo
        zaistnienia mutacji jest zwykle bardzo małe i oczywiscie od niego zalezy, czy dany
        gen w chromosomie podlega mutacji, czy tez nie. Dokonanie mutacji zgodnie z prawdopodobienstwem
        pm polega np. na losowaniu liczby z przedziału [0, 1] dla kazdego
        genu i wybraniu do mutacji tych genów, dla których wylosowana liczba jest mniejsza
        lub równa prawdopodobienstwu pm.
          */

          for (int wiersze = 0; wiersze < 100; wiersze++) {
            for (int kolumny = 0; kolumny < 15; kolumny++){
               if (wspolczynik_mutacji <= wspolczynik_pm[wiersze][kolumny])
               {
                  if (kolumny <= 1) {
                      //napoje (1-133)     
                    tablica_selekcja[wiersze][kolumny] = generator_pm.nextInt(133) + 1;
                }
                if (kolumny >= 2) {
                       //nasiona(134-169)   
                    tablica_selekcja[wiersze][kolumny] = generator_pm.nextInt(35) + 133;
                }
                 if (kolumny >= 3) {
                       //owoce(170-324) 
                    tablica_selekcja[wiersze][kolumny] = generator_pm.nextInt(154) + 170;
                }      
                 if (kolumny >= 4) {
                       //przyprawy(482-554)
                    tablica_selekcja[wiersze][kolumny] = generator_pm.nextInt(72) + 482;
                }
                 if (kolumny >= 5) {
                        //mieso(555-606) 
                    tablica_selekcja[wiersze][kolumny] = generator_pm.nextInt(51) + 555;
                }
                  if (kolumny >= 6) {
                       //fsatfud(607-625) 
                    tablica_selekcja[wiersze][kolumny] = generator_pm.nextInt(19) + 607;
                }
                  if (kolumny >= 7) {
                       //grzyby(626-634)  
                    tablica_selekcja[wiersze][kolumny] = generator_pm.nextInt(8) + 626;
                }
                  if (kolumny >= 8) {
                        //mieso(635-738)  
                    tablica_selekcja[wiersze][kolumny] = generator_pm.nextInt(103) + 635;
                }
                  if (kolumny >= 9) {
                        //nabiał(739-792)  
                    tablica_selekcja[wiersze][kolumny] = generator_pm.nextInt(53) + 739;
                }
                  if (kolumny >= 10) {
                       //ryby(793-873)  
                    tablica_selekcja[wiersze][kolumny] = generator_pm.nextInt(80) + 793;
                }
                  if (kolumny >= 12) {
                       //słodycze(874-951)    
                    tablica_selekcja[wiersze][kolumny] = generator_pm.nextInt(77) + 874;
                }
                  if (kolumny >= 13) {
                       //Masło(952-967)   
                    tablica_selekcja[wiersze][kolumny] = generator_pm.nextInt(15) + 952;
                } if (kolumny >= 14) {
                      //Warzywa(968-1100)     
                    tablica_selekcja[wiersze][kolumny] = generator_pm.nextInt(132) + 968;
                }
                 if (kolumny >= 15) {
                       //pieczywo(325-482)    
                    tablica_selekcja[wiersze][kolumny] = generator_pm.nextInt(157) + 325;
                }
                  
                   
               }else{
                  
               }
               
            }
          }
                          
        return tablica_selekcja; 
    }
    
 

    //************* Metoda wyprowadza wynik  *************//
    
    public static String[] wprowadz_Wynik (int kod_najlepszego_osobnika []) {
            
        listaProduktów= czytaj_Plik(sciezka);
             
        String tekst_prdukty = "";  //ciag String przychwyjacy liste produktów danego osobnika
        for (int kolumny = 0; kolumny < 15; kolumny++) {

            int liczba_wskazujaca = kod_najlepszego_osobnika[kolumny];
             liczba_wskazujaca--;
           // System.out.println(""+liczba_wskazujaca );
            
            BaseProduct takson_wczytaj = listaProduktów.get(liczba_wskazujaca);

            tekst[kolumny] = takson_wczytaj.getPprodukt();

            tekst_prdukty = new StringBuffer(tekst_prdukty).append(tekst[kolumny] + ", ").toString();
        }
           
     //  System.err.println(" lista produktów : " +tekst_prdukty);

        return tekst;
    }
    
     //************* Metoda wyprowadza dnae  *************//
    
    public static String[] wprowadz_Dane (int wiek,int wzrost,int waga,float pw,int fc,double Rmin,double Rmax,int FCbez,double FCprim ) {
        
        
        //String [] tekst_dane = new String[100];
        String [] teksty = new String[20];
        teksty [0] = "Wprowadzono dane";
        teksty [1] = " "+wiek;
        teksty [2] = " "+wzrost;
        teksty [3] = " "+waga;
        teksty [4] = " "+pw;
        teksty [5] = " "+fc;
        teksty [6] = " "+Rmin;
        teksty [7] = " "+Rmax;
        teksty [8] = " "+FCbez;
        teksty [9] = " "+FCprim;
       
        return teksty;
        
        
    }
    
    //************* Metoda Główna  *************//
    
    public static void main(String[] args) {

        GeneticAlgorithm genetic = new GeneticAlgorithm(wzrost, waga, wiek);
    }

}

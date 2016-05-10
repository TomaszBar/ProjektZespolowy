import java.math.BigInteger;
import java.security.MessageDigest;

public  class BaseUser {
  static String tekst;
  
      
      private int nr;
      private String imie,nazwisko,adres,email,Login,Hasło;
      private int Nr_telefonu,kod_genetyczny,Uprawnienie;
     
      public BaseUser(int nr, String imie,String nazwisko,String adres,String email, int Nr_telefonu,int kod_genetyczny,String Login,String Hasło,int Uprawnienie){
           this.nr = nr;
           this.imie = imie;
           this.nazwisko = nazwisko;
           this.adres = adres;
           this.email = email;
           this.Nr_telefonu = Nr_telefonu;
           this.kod_genetyczny = kod_genetyczny;
           this.Login = Login;
           this.Hasło = Hasło;
           this.Uprawnienie = Uprawnienie;
           
      }
   
       public BaseUser(int nr){
      this.nr = nr;
  }
  
 

  public int getNr() {
      return nr;
  }

  public void setNr(int nr) {
      this.nr = nr;
  }

  
  public String getImie() {
      return imie;
  }

  public void setImie(String imie) {
      this.imie = imie;
  }

  public String getNazwisko() {
      return nazwisko;
  }

  public void setNazwisko(String nazwisko) {
      this.nazwisko = nazwisko;
  }
  
  public String getAdres() {
      return adres;
  }

  public void setAderes(String adres) {
      this.adres = adres;
  }
  public String getEmail() {
      return email;
  }

  public void setEmail(String email) {
      this.email = email;
  }
  
  public int getNumer_telefonu() {
      return Nr_telefonu;
  }

  public void setNumer(int Nr_telefonu) {
      this.Nr_telefonu = Nr_telefonu;
  }
  
  public int getKod_gentyczny() {
      return kod_genetyczny;
  }

  public void setKod_genetyczny(int kod_genetyczny) {
      this.kod_genetyczny = kod_genetyczny;
  }
  
  public String getLogin() {
      return Login;
  }

  public void setLogin(String Login) {
      this.Login = Login;
  }
  public String getHaslo() {
      return Hasło;
  }

  public void setHaslo(String Hasło) {
      this.Hasło = Hasło;
  }
  public void setUprawnienia(int Uprawnienie){
	  this.Uprawnienie = Uprawnienie;
  }
  public int getUprawnienia(){
	  return Uprawnienie;
  }
  
public String toString(String string) {
	// TODO Auto-generated method stub
	return null;
}
 
  }
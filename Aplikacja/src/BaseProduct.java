

public  class BaseProduct {
  static String tekst;
  
      
      private int nr;
      private String produkt;
      private int kalorie;
     
      public BaseProduct(int nr, String produkt, int kalorie){
           this.nr = nr;
           this.produkt = produkt;
           this.kalorie = kalorie;
        
      }
   
       public BaseProduct(int nr){
      this.nr = nr;
  }
  
  public String toString(String separator) {
      return getNr() + separator + getPprodukt()+ separator + 
              getKalorie();
  }
  

  public int getNr() {
      return nr;
  }

  public void setNr(int nr) {
      this.nr = nr;
  }

  public String getPprodukt() {
      return produkt;
  }

  public void setProdukt(String produkt) {
      this.produkt = produkt;
  }

  public int getKalorie() {
      return kalorie;
  }

  public void setKalorie(int kalorie) {
      this.kalorie = kalorie;
  }

 
  }
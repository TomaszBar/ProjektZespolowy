import java.util.Arrays;
import java.util.Comparator;

public class BaseProduct implements Comparable<BaseProduct> {
	
	 static String tekst;
  
      
      private int nr;
      private String produkt;
      private int kalorie;
     
      public BaseProduct(int nr, String produkt, int kalorie){
    	  
    	   super();
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
  public int compareTo(BaseProduct produkt) {
		
		int compareKalorie = ((BaseProduct) produkt).getKalorie(); 
		
		//ascending order
		return this.kalorie - compareKalorie;
		
		//descending order
		//return compareQuantity - this.quantity;
		
	}
	
	public static Comparator<BaseProduct> FruitKalorieComparator 
                        = new Comparator<BaseProduct>() {

	    public int compare(BaseProduct produkt1, BaseProduct produkt2) {
	    	
	      String kalorie1 = produkt1.getPprodukt().toUpperCase();
	      String kalorie2 = produkt2.getPprodukt().toUpperCase();
	      
	      //ascending order
	      return kalorie1.compareTo(kalorie2);
	      
	      //descending order
	      //return fruitName2.compareTo(fruitName1);
	    }

	  
	    
	};

	//Arrays.sort(t, t.FruitKalorieComparator);
 
  }
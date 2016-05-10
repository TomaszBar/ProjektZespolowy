import java.awt.Font;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class LogowanieAlgorithm {

	Connection con = DriverManager.getConnection("jdbc:sybase:Tds:localhost:2638", "DBA", "sql");

	// Zmiene do scieżki bazy produktów

	boolean poprawne_Login = false;
	boolean poprawne_Hasło = false;
	int numer;
	String uprawnienia;
	int wzrost, waga, wiek, czas, zakres, kalorie, wybór, numer_produktu, Nowy;
	String Uprawnienia = "Użytkownik";
	private String nowy_numer;
	static int Numer_uztkownika;
	/**
	 * Atrybut do sortowania wartości w tabeli
	 */
	private TableRowSorter<TableModel> sorter;

	public LogowanieAlgorithm() throws SQLException {

		// TODO Auto-generated constructor stub
	}

	// ************* Metoda sprawdzające poprawność logowania *************//

	public boolean Logowanie(String Login, String Hasło) throws SQLException {
		Statement stmt = con.createStatement();
		String login = null, hasło = null;

		ResultSet rs = stmt.executeQuery("SELECT * from Uzytkownicy");
		int i = 0;
		while (rs.next()) {
			login = rs.getString(8);

			if (Login.equals(login)) {
				poprawne_Login = true;
				numer = rs.getInt(1);
				uprawnienia = rs.getString(7);
			}
		}

		rs = stmt.executeQuery("SELECT * from Uzytkownicy");

		while (rs.next()) {
			hasło = rs.getString(9);

			if (Hasło.equals(hasło)) {
				poprawne_Hasło = true;
				numer = rs.getInt(1);
				uprawnienia = rs.getString(7);
				// System.err.println(uprawnienia+numer_uzytkownika);
			}
		}

		boolean zgoda = ((poprawne_Hasło) && (poprawne_Login));

		// System.out.println("Zgoda : "+zgoda);
		return zgoda;
	}

	// ************* Metoda zbierająca dane poza logowaniu *************//

	@SuppressWarnings("null")
	String[] Dane(int numer) throws SQLException {

		Statement stmt = con.createStatement();

		String[] dane = new String[8];

		ResultSet rs = stmt.executeQuery("SELECT * from Uzytkownicy");
		int i = 0;
		while (rs.next()) {
			int numer_pomocniczy = rs.getInt(1);

			if (numer == numer_pomocniczy) {

				dane[1] = rs.getString(2);// imie
				dane[2] = rs.getString(3);// nazwisko
				dane[3] = rs.getString(4);// aderes
				dane[4] = rs.getString(6);// email
				dane[5] = rs.getString(8);// login
				dane[6] = rs.getString(9);// haslo
				dane[7] = rs.getString(5);// tel

			}
		}

		return dane;
	}

	// ************* Metoda rejstracja *************//

	public JTable Rejestracja(String imie, String nazwisko, String adres, String email, String nr_tlefonu, String login,
			String haslo) throws SQLException {

		Connection con = DriverManager.getConnection("jdbc:sybase:Tds:localhost:2638", "DBA", "sql");
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT count(0) from Uzytkownicy");
		JTable result;

		int iterator_wierszy = 0;
		rs.next();
		iterator_wierszy = rs.getInt(1);

		rs = stmt.executeQuery("SELECT * from Uzytkownicy");
		while (rs.next()) {
			numer_produktu = rs.getInt(1);

			Nowy = numer_produktu + 1;
			nowy_numer = Integer.toString(Nowy);
			// System.out.println("Nowy :"+nowy_numer);

		}
		int numer_uzytkownika_nowy = iterator_wierszy + 1;

		/**
		 * Create the Table
		 */

		String[] colNames = new String[] { "NR", "imie", "Nazwisko", "Adres", "Telefon", "Email", "Uprawnienia",
				"Login", "Hasło" };

		AbstractTableModel model = new DefaultTableModel(colNames, iterator_wierszy) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		result = new JTable(model);
		result.getColumnModel().getColumn(0).setPreferredWidth(80);
		result.getColumnModel().getColumn(1).setPreferredWidth(80);
		result.setRowHeight(30);
		result.setFont(new Font("Arial", 1, 16));

		sorter = new TableRowSorter<>(result.getModel());

		result.setRowSorter(sorter);

		List<RowSorter.SortKey> sortKeys = new ArrayList<>();

		int columnIndexToSort = 0;
		sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));

		sorter.setSortKeys(sortKeys);

		String Uprawnienia = "Użytkownik";
		// 'Login'
		/**
		 * Create the Load Baza.
		 */

		String sql = "INSERT INTO Uzytkownicy(Numer,Imie,Nazwisko,Aderes,Telefon,Email,Uprawnienia,Logi,Haslo)Values ("
				+ nowy_numer + ",'" + imie + "','" + nazwisko + "','" + adres + "'," + nr_tlefonu + ",'" + email + "','"
				+ Uprawnienia + "','" + login + "','" + haslo + "')";
		// System.out.println(sql);
		stmt = con.createStatement();
		stmt.execute(sql);

		return result;

	}

	// ************* Metoda edycja *************//

	public JTable Edytuj(int numer, String imie, String nazwisko, String adres, String email, String nr_tlefonu,
			String login, String haslo, String Uprawnienia) throws SQLException {

		// Connection con = DriverManager.getConnection(
		// "jdbc:sybase:Tds:localhost:2638", "DBA", "sql");
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT count(0) from Uzytkownicy");
		JTable result;

		int iterator_wierszy = 0;
		rs.next();
		iterator_wierszy = rs.getInt(1);

		rs = stmt.executeQuery("SELECT * from Uzytkownicy");

		/**
		 * Create the Table
		 */

		String[] colNames = new String[] { "NR", "imie", "Nazwisko", "Adres", "Telefon", "Email", "Uprawnienia",
				"Login", "Hasło" };

		AbstractTableModel model = new DefaultTableModel(colNames, iterator_wierszy) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		result = new JTable(model);
		result.getColumnModel().getColumn(0).setPreferredWidth(80);
		result.getColumnModel().getColumn(1).setPreferredWidth(80);
		result.setRowHeight(30);
		result.setFont(new Font("Arial", 1, 16));

		sorter = new TableRowSorter<>(result.getModel());

		result.setRowSorter(sorter);

		List<RowSorter.SortKey> sortKeys = new ArrayList<>();

		int columnIndexToSort = 0;
		sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));

		sorter.setSortKeys(sortKeys);

		/**
		 * Create the Load Baza.
		 */
		String sql = "UPDATE  Uzytkownicy SET Imie='" + imie + "',Nazwisko='" + nazwisko + "',Aderes='" + adres
				+ "',Telefon=" + nr_tlefonu + ",Email='" + email + "',Logi='" + login + "',Haslo='" + haslo
				+ "'WHERE Numer=" + numer;
		stmt = con.createStatement();
		stmt.execute(sql);

		return result;
	}

	public static void main(String[] args) throws SQLException {

		// new LogowanieAlgorithm();

	}

}

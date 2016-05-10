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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingConstants;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class ListaUserWindows extends JDialog implements ActionListener {

	private JPanel contentPane;
	private JButton Usuń, Anuluj;
	private JTable uzytkownik;

	/**
	 * Atrybut bedacy do komunikacj z bazą danych
	 */
	Connection con = DriverManager.getConnection("jdbc:sybase:Tds:localhost:2638", "DBA", "sql");
	/**
	 * Atrybut do sortowania wartości w tabeli
	 */
	private TableRowSorter<TableModel> sorter;

	JLabel lIcon, jlNazwa_progrmu, lBorder, jlWersja, jlPrawa;
	public boolean zamknij;

	LogowanieAlgorithm log = new LogowanieAlgorithm();
	LogowanieWindow win = new LogowanieWindow();

	private Font font1 = new Font("TimesRoman", Font.PLAIN, 14);
	private Font font2 = new Font("TimesRoman", Font.PLAIN, 12);
	private Font font3 = new Font("TimesRoman", Font.BOLD, 12);
	private Font font4 = new Font("TimesRoman", Font.PLAIN, 12);
	private Border line = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaUserWindows frame = new ListaUserWindows();
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
	public ListaUserWindows() throws SQLException {
		this.setTitle("Lista Użytkowników");
		this.setModal(true);
		this.setSize(1300, 452);
		this.setResizable(false);

		// obsluga zdarzenia okna
		this.addWindowListener(new WindowAdapter() {
			// obsluga wcisniecia przycisku zamkniecia okna
			@Override
			public void windowClosing(WindowEvent e) {
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
		if (dialogSize.height > screenSize.height)
			dialogSize.height = screenSize.height;
		if (dialogSize.width > screenSize.width)
			dialogSize.height = screenSize.width;

		// rozmieszczenie aplikacji na srodku ekranu
		setLocation((screenSize.width - dialogSize.width) / 2, (screenSize.height - dialogSize.height) / 2);

		Container contentPane = getContentPane();
		contentPane.setBackground(Color.white);
		contentPane.setLayout(null);

		try {
			ImageIcon ikonainformacjeautor = new ImageIcon("fitt.jpg");
			lIcon = new JLabel();
			lIcon.setIcon(new ImageIcon("C:\\Users\\Gregory\\Documents\\JavaEclipse\\Fitnes\\image\\Wprowadz.png"));
		} catch (Exception e) {
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
		jlNazwa_progrmu.setBounds(70, 11, 86, 30);
		jlWersja.setBounds(74, 39, 86, 30);
		jlPrawa.setBounds(49, 68, 147, 20);
		lBorder = new JLabel("");

		JPanel JPanelBAZA = new JPanel();
		JPanelBAZA.setBounds(24, 116, 1250, 229);
		getContentPane().add(JPanelBAZA);
		JPanelBAZA.setLayout(new BorderLayout(0, 0));

		JPanel panel_Tabela = new JPanel();
		JPanelBAZA.add(panel_Tabela, BorderLayout.CENTER);

		jlNazwa_progrmu.setBounds(52, 11, 126, 30);
		lBorder.setBounds(12, 358, 985, 4);

		lBorder.setBorder(line);
		contentPane.add(jlNazwa_progrmu);
		contentPane.add(jlWersja);
		contentPane.add(jlPrawa);
		contentPane.add(lBorder);
		contentPane.add(lIcon);

		JTable rezultat = Create_Tabela();
		JScrollPane Baza_Użytkoników = new JScrollPane(rezultat);
		Baza_Użytkoników.setPreferredSize(new Dimension(345, 545));
		JPanelBAZA.add(Baza_Użytkoników, BorderLayout.CENTER);

		Usuń = new JButton("Usuń");
		Usuń.setBounds(24, 375, 106, 23);
		contentPane.add(Usuń);
		Usuń.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					Usuń();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				JPanelBAZA.removeAll();
				JTable rezultat = null;
				try {
					rezultat = Create_Tabela();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JScrollPane Baza_Użytkoników = new JScrollPane(rezultat);
				Baza_Użytkoników.setPreferredSize(new Dimension(345, 545));
				JPanelBAZA.add(Baza_Użytkoników, BorderLayout.CENTER);

				revalidate();
				repaint();

			}
		});

		lIcon.setBounds(191, 11, 150, 73);

		Anuluj = new JButton("Anuluj");
		Anuluj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int numer = win.numer_uzytkownika;
				String uprawnienia = win.uprawnienia_uzytkownika;

				AppUser user = null;
				try {
					try {
						user = new AppUser(numer, uprawnienia);
					} catch (SQLException e) {

						e.printStackTrace();
					}
				} catch (ClassNotFoundException e) {

					e.printStackTrace();
				} catch (InstantiationException e) {

					e.printStackTrace();
				} catch (IllegalAccessException e) {

					e.printStackTrace();
				} catch (UnsupportedLookAndFeelException e) {

					e.printStackTrace();
				}
				setVisible(false);
				user.setVisible(true);

			}

		});
		Anuluj.setBounds(154, 375, 97, 25);
		getContentPane().add(Anuluj);
	}

	public JTable Create_Tabela() throws SQLException {

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT count(0) from Uzytkownicy");

		int iterator_wierszy = 0;
		rs.next();
		iterator_wierszy = rs.getInt(1);

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
		uzytkownik = new JTable(model);
		uzytkownik.getColumnModel().getColumn(0).setPreferredWidth(80);
		uzytkownik.getColumnModel().getColumn(1).setPreferredWidth(180);
		uzytkownik.getColumnModel().getColumn(2).setPreferredWidth(180);
		uzytkownik.getColumnModel().getColumn(3).setPreferredWidth(180);
		uzytkownik.getColumnModel().getColumn(4).setPreferredWidth(180);
		uzytkownik.getColumnModel().getColumn(5).setPreferredWidth(180);
		uzytkownik.getColumnModel().getColumn(6).setPreferredWidth(180);
		uzytkownik.setRowHeight(30);
		uzytkownik.setFont(new Font("Arial", 1, 16));

		sorter = new TableRowSorter<>(uzytkownik.getModel());

		uzytkownik.setRowSorter(sorter);

		List<RowSorter.SortKey> sortKeys = new ArrayList<>();

		int columnIndexToSort = 0;
		sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));

		sorter.setSortKeys(sortKeys);

		// JS_Tabela_Baza = new JScrollPane(result);

		/**
		 * Create the Load Baza.
		 */

		stmt = con.createStatement();
		rs = stmt.executeQuery("SELECT * from Uzytkownicy");

		int it = 0;
		while (rs.next()) {
			uzytkownik.setValueAt(rs.getInt(1), it, 0);
			uzytkownik.setValueAt(rs.getString(2), it, 1);
			uzytkownik.setValueAt(rs.getString(3), it, 2);
			uzytkownik.setValueAt(rs.getString(4), it, 3);
			uzytkownik.setValueAt(rs.getString(5), it, 4);
			uzytkownik.setValueAt(rs.getString(6), it, 5);
			uzytkownik.setValueAt(rs.getString(7), it, 6);
			uzytkownik.setValueAt(rs.getString(8), it, 7);
			uzytkownik.setValueAt(rs.getString(9), it, 8);
			it++;
		}

		class IntComparator implements Comparator {
			public int compare(Object o1, Object o2) {
				Integer int1 = (Integer) o1;
				Integer int2 = (Integer) o2;
				return int1.compareTo(int2);
			}

			public boolean equals(Object o2) {
				return this.equals(o2);
			}
		}
		sorter.setComparator(0, new IntComparator());
		sorter.setComparator(2, new IntComparator());
		sorter.setComparator(3, new IntComparator());
		sorter.setComparator(4, new IntComparator());
		sorter.setComparator(5, new IntComparator());

		return uzytkownik;
	}

	public void Usuń() throws SQLException {

		int numer = uzytkownik.getSelectedRow();
		String n = uzytkownik.getValueAt(numer, 0).toString();

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT count(0) from Uzytkownicy");

		int iterator_wierszy = 0;
		rs.next();
		iterator_wierszy = rs.getInt(1);

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
		uzytkownik = new JTable(model);
		uzytkownik.getColumnModel().getColumn(0).setPreferredWidth(80);
		uzytkownik.getColumnModel().getColumn(1).setPreferredWidth(180);
		uzytkownik.getColumnModel().getColumn(2).setPreferredWidth(180);
		uzytkownik.getColumnModel().getColumn(3).setPreferredWidth(180);
		uzytkownik.getColumnModel().getColumn(4).setPreferredWidth(180);
		uzytkownik.getColumnModel().getColumn(5).setPreferredWidth(180);
		uzytkownik.getColumnModel().getColumn(6).setPreferredWidth(180);
		uzytkownik.setRowHeight(30);
		uzytkownik.setFont(new Font("Arial", 1, 16));

		sorter = new TableRowSorter<>(uzytkownik.getModel());

		uzytkownik.setRowSorter(sorter);

		List<RowSorter.SortKey> sortKeys = new ArrayList<>();

		int columnIndexToSort = 0;
		sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));

		sorter.setSortKeys(sortKeys);

		// JS_Tabela_Baza = new JScrollPane(result);

		/**
		 * Create the Load Baza.
		 */

		stmt = con.createStatement();
		rs = stmt.executeQuery("SELECT * from Uzytkownicy");

		int it = 0;
		while (rs.next()) {
			uzytkownik.setValueAt(rs.getInt(1), it, 0);
			uzytkownik.setValueAt(rs.getString(2), it, 1);
			uzytkownik.setValueAt(rs.getString(3), it, 2);
			uzytkownik.setValueAt(rs.getString(4), it, 3);
			uzytkownik.setValueAt(rs.getString(5), it, 4);
			uzytkownik.setValueAt(rs.getString(6), it, 5);
			uzytkownik.setValueAt(rs.getString(7), it, 6);
			uzytkownik.setValueAt(rs.getString(8), it, 7);
			uzytkownik.setValueAt(rs.getString(9), it, 8);
			it++;
		}

		class IntComparator implements Comparator {
			public int compare(Object o1, Object o2) {
				Integer int1 = (Integer) o1;
				Integer int2 = (Integer) o2;
				return int1.compareTo(int2);
			}

			public boolean equals(Object o2) {
				return this.equals(o2);
			}
		}
		sorter.setComparator(0, new IntComparator());
		sorter.setComparator(2, new IntComparator());
		sorter.setComparator(3, new IntComparator());
		sorter.setComparator(4, new IntComparator());
		sorter.setComparator(5, new IntComparator());

		int i = JOptionPane.showConfirmDialog(this,
				"Czy na pewno chcesz usunąć Użytkownika " + uzytkownik.getColumnName(0) + " = " + n, "Potwierdź",
				JOptionPane.YES_NO_OPTION);

		if (i == JOptionPane.YES_OPTION) {
			String sql = "Delete from Uzytkownicy   where Numer=" + n;

			try {
				stmt.execute(sql);
				((DefaultTableModel) model).removeRow(numer);
				revalidate();
				repaint();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, "Wystąpił błąd poczas usuwania.", "Błąd",
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}

		// return uzytkownik;

	}

	public void actionPerformed(ActionEvent AE) {

	}
}

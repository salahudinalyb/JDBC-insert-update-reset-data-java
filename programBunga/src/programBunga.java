
import java.sql.*;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;


public class programBunga extends JFrame {

	private JPanel contentPane;
	private JTextField vId;
	private JTextField vNama;
	private JTextField vKeterangan;
	private JTextField vJumlah;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					programBunga frame = new programBunga();
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
	public programBunga() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Id Bunga");
		lblNewLabel.setBounds(47, 70, 66, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nama Bunga");
		lblNewLabel_1.setBounds(47, 95, 81, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Keterangan");
		lblNewLabel_2.setBounds(47, 120, 81, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Jumlah");
		lblNewLabel_3.setBounds(47, 145, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		vId = new JTextField();
		vId.setBounds(137, 67, 81, 20);
		contentPane.add(vId);
		vId.setColumns(10);
		
		vNama = new JTextField();
		vNama.setBounds(138, 92, 117, 20);
		contentPane.add(vNama);
		vNama.setColumns(10);
		
		vKeterangan = new JTextField();
		vKeterangan.setBounds(138, 117, 136, 20);
		contentPane.add(vKeterangan);
		vKeterangan.setColumns(10);
		
		vJumlah = new JTextField();
		vJumlah.setBounds(137, 142, 86, 20);
		contentPane.add(vJumlah);
		vJumlah.setColumns(10);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				vId.setText("");
				vNama.setText("");
				vKeterangan.setText("");
				vJumlah.setText("");
			}
		});
		btnReset.setBounds(47, 191, 89, 23);
		contentPane.add(btnReset);
		
		JButton btnSimpan = new JButton("Simpan");
		btnSimpan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection conn = null;
				 try {
				  String username = "root";
				  String password = "";
				  String url = "jdbc:mysql://localhost:3306/jdbc";
				  Class.forName("com.mysql.jdbc.Driver").newInstance ();
				  conn = DriverManager.getConnection(url, username, password);
				  Statement stmt =conn.createStatement();
				  String tableName = " jdbc.db_bunga ";
				  String sqlStmt = "INSERT INTO "+tableName+"VALUES('"+vId.getText()+"','"+vNama.getText()+"','"+vKeterangan.getText()+"','"+vJumlah.getText()+"')";
				  int updateCount = stmt.executeUpdate (sqlStmt);
				  stmt.close();
				  conn.close();
				 } catch (Exception e) {
				  System.err.println (e.getMessage());  
				  }
			}
		});
		btnSimpan.setBounds(166, 191, 89, 23);
		contentPane.add(btnSimpan);
		
		final JButton btnCari;
		btnCari = new JButton("Cari");
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection conn = null;
				 try {
				    String userName = "root";
				    String password = "";
				    String url = "jdbc:mysql://localhost:3306/jdbc";
				    Class.forName("com.mysql.jdbc.Driver").newInstance ();
				    conn = DriverManager.getConnection(url, userName, password);
				    Statement stmt =conn.createStatement();
				    String tableName = "jdbc.db_bunga ";
				    String sqlStmt = " UPDATE "+tableName+"SET Nama= '"+vNama.getText()+"',Keterangan= '"+vKeterangan.getText()+"',Jumlah= '"+vJumlah.getText()+"' WHERE Id= '"+vId.getText()+"'";
				    ResultSet rSet = stmt.executeQuery(sqlStmt);
				     stmt.close();
				     conn.close();
				    vId.setEnabled(true);
					vNama.setEnabled(true);
					vKeterangan.setEnabled(true);
					vJumlah.setEnabled(true);
					btnSimpan.setEnabled(true);
					btnReset.setEnabled(true);
					btnEdit.setEnabled(false);
					btnCari.setEnabled(true);
					
					vId.setText("");
					vNama.setText("");
					vKeterangan.setText("");
					vJumlah.setText("");
					
					JOptionPane.showMessageDialog(null,"data berhasil diupdate!");
				  } catch (Exception e) {
				    System.err.println (e.getMessage());
				    JOptionPane.showMessageDialog(null,"data gagal diupdate!, karena"+e.getMessage());
				  }
			}
		});
		btnEdit.setBounds(281, 191, 89, 23);
		contentPane.add(btnEdit);
		
		btnCari.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnCari.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Connection conn = null;
				 try {
				  String username = "root";
				  String password = "";
				  String url = "jdbc:mysql://localhost:3306/jdbc";
				  Class.forName("com.mysql.jdbc.Driver").newInstance ();
				  conn = DriverManager.getConnection(url, username, password);
				  Statement stmt =conn.createStatement();
				  String tableName = " jdbc.db_bunga ";
				  String sqlStmt = " SELECT * FROM "+tableName+"WHERE Id= '"+vId.getText()+"'";
				  int updateCount = stmt.executeUpdate (sqlStmt);
				  ResultSet rSet = stmt.executeQuery(sqlStmt);
				  while (rSet.next()) {
				  vId.setText(rSet.getString("Id"));
				  vNama.setText(rSet.getString("Nama"));
				  vKeterangan.setText(rSet.getString("Keterangan"));
				  vJumlah.setText(rSet.getString("Jumlah"));
				  }
				  stmt.close();
				  conn.close();
				  vId.setEnabled(false);
				  vNama.setEnabled(true);
				  vKeterangan.setEnabled(true);
				  vJumlah.setEnabled(true);
				  btnSimpan.setEnabled(false);
				  btnReset.setEnabled(false);
				  btnCari.setEnabled(false);
				  
				 } catch (Exception e) {
				  System.err.println (e.getMessage());  
				  }
			}
		});
		btnCari.setBounds(306, 60, 70, 23);
		contentPane.add(btnCari);
		
		JButton btnHapus = new JButton("Hapus");
		btnHapus.setBounds(305, 87, 71, 23);
		contentPane.add(btnHapus);
		
		JLabel lblNewLabel_4 = new JLabel("Program Toko Bunga");
		lblNewLabel_4.setBounds(137, 11, 125, 30);
		contentPane.add(lblNewLabel_4);
	}
}

//JButton btnHapus = new JButton("Hapus");
//btnHapus.setBounds(288, 91, 71, 23);
//contentPane.add(btnHapus);

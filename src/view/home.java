package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controler.Controller;
import Model.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.HeadlessException;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class home extends JFrame {

	private JPanel contentPane;
	private String STD;
	public void home1(String STD) throws HeadlessException {
		this.STD = STD;
	
		try {
			home frame = new home(STD);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
			}
		});
	}

	/**
	 * Create the frame.
	 * @param STD 
	 */
	public home(String STD) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\DELL\\Downloads\\RoundPink2.png"));
		lblNewLabel.setBounds(23, 42, 120, 120);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("HELLO");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_1.setBounds(176, 42, 148, 41);
		contentPane.add(lblNewLabel_1);
		
		JLabel lbUsername = new JLabel("");
		lbUsername.setForeground(new Color(255, 255, 255));
		lbUsername.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbUsername.setBounds(176, 85, 239, 41);
		contentPane.add(lbUsername);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(41, 262, 352, 243);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lbPN = new JLabel("");
		lbPN.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lbPN.setBounds(25, 71, 264, 41);
		panel.add(lbPN);
		
		JLabel lbBL = new JLabel("");
		lbBL.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbBL.setBounds(25, 122, 229, 41);
		panel.add(lbBL);
		
		JButton btnNewButton = new JButton("Transfer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				transfer a = new transfer(STD);
				a.show();
				dispose();
				
			}
		});
		btnNewButton.setBackground(new Color(255, 128, 192));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBounds(40, 188, 137, 34);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Sources");
		lblNewLabel_2.setForeground(new Color(255, 128, 192));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_2.setBounds(25, 27, 167, 34);
		panel.add(lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deposit a = new deposit(STD);
				a.show();
				dispose();
			}
		});
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\DELL\\Downloads\\deposit icon.jpg"));
		btnNewButton_1.setBounds(0, 596, 143, 86);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				withdraw a = new withdraw(STD);
				a.show();
				dispose();
			}
		});
		btnNewButton_2.setBackground(new Color(255, 255, 255));
		btnNewButton_2.setIcon(new ImageIcon("C:\\Users\\DELL\\Downloads\\withdraw.jpg"));
		btnNewButton_2.setBounds(154, 596, 136, 86);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.setToolTipText("Setting");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setting a = new setting(STD);
				a.show();
				dispose();
				
			}
		});
		btnNewButton_3.setBackground(new Color(255, 255, 255));
		btnNewButton_3.setIcon(new ImageIcon("C:\\Users\\DELL\\Downloads\\setting.jpg"));
		btnNewButton_3.setBounds(300, 596, 136, 87);
		contentPane.add(btnNewButton_3);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setBackground(new Color(255, 255, 255));
		lblNewLabel_5.setIcon(new ImageIcon("C:\\Users\\DELL\\Downloads\\bg1.jpg"));
		lblNewLabel_5.setBounds(0, 0, 436, 693);
		contentPane.add(lblNewLabel_5);
		
		try {
			ArrayList<User> rs=new Controller().UserInfo(STD);
			for (int i=0;i<rs.size();i++) {
				lbUsername.setText(rs.get(i).getName());
				lbPN.setText(rs.get(i).getPN());
				lbBL.setText(rs.get(i).getBalance());
				
			}
	
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Sorry! Something went wrong!");
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
		}
	}
}

package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Controler.Controller;
import Model.User;

public class changepin extends JFrame {

	private JPanel contentPane;
	private JPasswordField pw, npw, cpw;
	private String STD;
	public void changepin1(String STD) throws HeadlessException {
		this.STD = STD;
		try {
			changepin frame = new changepin(STD);
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
	 */
	public changepin(String STD) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 730);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 128, 192));
		panel.setBounds(0, 0, 436, 56);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("<");
		lblNewLabel.addMouseListener(new MouseListener() {
			public void actionPerformed(ActionEvent e) {
				
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				setting a= new setting(STD);
				a.show();
				dispose();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(0, 0, 69, 56);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Change Pin");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(147, 0, 176, 56);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setBounds(0, 0, 436, 699);
		panel.add(lblNewLabel_6);
		
		JButton btnNewButton = new JButton("Change Pin");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Pw= pw.getText();
				String NPw= npw.getText();
				String CPw = cpw.getText();
				String hashPw = Security.getMD5Hash(Pw.getBytes());
				if(new Controller().checkPin(STD, hashPw)) {
					if(NPw.equals(CPw)) {
						ArrayList<User> rs=new Controller().UserInfo(STD);
						for (User u:rs) {

							String email = u.getEmail();
							VerifyOTP v = new VerifyOTP(STD,"",NPw,email,"ChangePIN");
							v.show();
						}
					    dispose();
					}else {
						JOptionPane.showMessageDialog(null, "Pin mismatch ");
					}

				}else {
					JOptionPane.showMessageDialog(null, "Pin wrong");
				}
			}
		});
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setForeground(new Color(255, 128, 192));
		btnNewButton.setBounds(28, 600, 381, 48);
		contentPane.add(btnNewButton);
		
		pw = new JPasswordField();
		pw.setFont(new Font("Tahoma", Font.PLAIN, 30));
		pw.setBounds(38, 226, 335, 48);
		contentPane.add(pw);
		
		JLabel lblNewLabel_2 = new JLabel("Current\r\n Pin");
		lblNewLabel_2.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(38, 180, 277, 48);
		contentPane.add(lblNewLabel_2);
		
		npw = new JPasswordField();
		npw.setFont(new Font("Tahoma", Font.PLAIN, 30));
		npw.setBounds(38, 341, 335, 48);
		contentPane.add(npw);
		
		JLabel lblNewLabel_3 = new JLabel("New Pin");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
		lblNewLabel_3.setBounds(38, 304, 263, 34);
		contentPane.add(lblNewLabel_3);
		
		cpw = new JPasswordField();
		cpw.setFont(new Font("Tahoma", Font.PLAIN, 30));
		cpw.setBounds(38, 446, 335, 48);
		contentPane.add(cpw);
		
		JLabel lblNewLabel_4 = new JLabel("Confirm Pin");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
		lblNewLabel_4.setBounds(38, 409, 267, 34);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon("C:\\Users\\DELL\\Downloads\\bg1.jpg"));
		lblNewLabel_5.setBounds(0, 0, 436, 693);
		contentPane.add(lblNewLabel_5);
	}

}

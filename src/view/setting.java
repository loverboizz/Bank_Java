package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Controler.Controller;
import Model.User;

import java.awt.event.ActionListener;

public class setting extends JFrame {

	private JPanel contentPane;
	private String STD;
	public void setting1(String STD) throws HeadlessException {
		this.STD = STD;
		try {
			setting frame = new setting(STD);
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
	public setting(String STD) {
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
				home a= new home(STD);
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
		
		JLabel lblNewLabel_1 = new JLabel("Setting");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(147, 0, 176, 56);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setBounds(0, 0, 436, 699);
		panel.add(lblNewLabel_6);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(28, 109, 381, 174);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\DELL\\Downloads\\RoundPink2.png"));
		lblNewLabel_2.setBounds(10, 23, 120, 128);
		panel_1.add(lblNewLabel_2);
		
		JLabel lbPN = new JLabel("");
		lbPN.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbPN.setBounds(140, 50, 202, 32);
		panel_1.add(lbPN);
		
		JLabel lbBL = new JLabel("");
		lbBL.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbBL.setBounds(140, 92, 202, 27);
		panel_1.add(lbBL);
		
		
		JButton btnNewButton = new JButton("Change Password");
		btnNewButton.setBackground(new Color(240, 240, 240));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changepw a= new changepw(STD);
				a.show();
				dispose();
				
			}
		});
		btnNewButton.setForeground(new Color(255, 128, 192));
		btnNewButton.setBounds(28, 387, 381, 46);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Change Pin");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changepin a= new changepin(STD);
				a.show();
				dispose();
			}
		});
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.setForeground(new Color(255, 128, 192));
		btnNewButton_1.setBounds(28, 466, 381, 46);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Log out");
		btnNewButton_2.setBackground(new Color(255, 255, 255));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				login a= new login();
				a.show();
				dispose();
				new Controller().Logout(STD);
			}
		});
		btnNewButton_2.setForeground(new Color(255, 128, 192));
		btnNewButton_2.setBounds(28, 550, 381, 46);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\DELL\\Downloads\\bg1.jpg"));
		lblNewLabel_3.setBounds(0, 0, 436, 693);
		contentPane.add(lblNewLabel_3);
		
		try {
			ArrayList<User> rs=new Controller().UserInfo(STD);
			for (User u:rs) {
				lbPN.setText(u.getPN());
				lbBL.setText(u.getBalance());
			  
				
			}
	
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "Sorry! Something went wrong!");
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
		}

	}
}

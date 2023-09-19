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
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Controler.Controller;
import Model.User;

import java.awt.event.ActionListener;

public class withdraw extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private String STD;
	public static boolean isNumberic(String Amount) {
		  try {  
			    Double.parseDouble(Amount);  
			    return true;
			  } catch(NumberFormatException e){  
			    return false;  
			  }  
	}
	public void withdraw1(String STD) throws HeadlessException {
		this.STD = STD;
		try {
			withdraw frame = new withdraw(STD);
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
	public withdraw(String STD) {
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
		
		JLabel lblNewLabel_1 = new JLabel("WITHDRAW");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(147, 0, 176, 56);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setBounds(0, 0, 436, 699);
		panel.add(lblNewLabel_6);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(28, 223, 381, 174);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\DELL\\Downloads\\RoundPink2.png"));
		lblNewLabel_2.setBounds(10, 23, 120, 128);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Sources");
		lblNewLabel_3.setForeground(new Color(255, 128, 192));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_3.setBounds(152, 22, 104, 32);
		panel_1.add(lblNewLabel_3);
		
		JLabel lbPN = new JLabel("");
		lbPN.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbPN.setBounds(140, 64, 202, 32);
		panel_1.add(lbPN);
		
		JLabel lbBL = new JLabel("");
		lbBL.setHorizontalAlignment(SwingConstants.RIGHT);
		lbBL.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbBL.setBounds(123, 124, 97, 27);
		panel_1.add(lbBL);
		
		
		JLabel lblNewLabel_4 = new JLabel("How much you want to withdraw?");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setBounds(28, 452, 340, 35);
		contentPane.add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("CONTINUE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Amount = textField.getText();
				if(isNumberic(Amount)==true) {
					ArrayList<User> rs=new Controller().UserInfo(STD);
					for (User u:rs) {
						lbPN.setText(u.getPN());
						lbBL.setText(u.getBalance());
						String email = u.getEmail();
						VerifyOTP v = new VerifyOTP(STD,"",Amount,email,"W");
						v.show();
					}
				    dispose();
				}else {
					JOptionPane.showMessageDialog(null,"Please re-enter Amount");
				}
				
			}
		});
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setForeground(new Color(255, 128, 192));
		btnNewButton.setBounds(28, 626, 381, 40);
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(28, 509, 381, 56);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setIcon(new ImageIcon("C:\\Users\\DELL\\Downloads\\bg1.jpg"));
		lblNewLabel_7.setBounds(0, 0, 436, 693);
		contentPane.add(lblNewLabel_7);
		
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

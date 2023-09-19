package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import Controler.*;

public class signup extends JFrame {

	private JPanel contentPane;
	private static JTextField txtPN;
	private static JTextField txtFN;
	private static JPasswordField cpw;
	private static JPasswordField pw;
	private static JPasswordField pin;
	private static JPasswordField cpin;
	private String email;
	
    public boolean isValidEmail(String email) {
        String emailRegex = "^[\\w-_.+]*[\\w-_.]@[\\w]+[\\w.]*[\\w]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
    
    public void signup(String email) {
    	this.email=email;
    	try {
			signup frame = new signup(email);
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
	 * @param email 
	 */
	public signup(String email) {
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
				
				login a= new login();
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
		
		JLabel lblNewLabel_1 = new JLabel("Sign Up");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(161, 0, 128, 56);
		panel.add(lblNewLabel_1);
		
		txtPN = new JTextField();
		txtPN.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPN.setBounds(33, 143, 378, 34);
		contentPane.add(txtPN);
		txtPN.setColumns(10);
		
		txtFN = new JTextField();
		txtFN.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtFN.setBounds(33, 240, 378, 34);
		contentPane.add(txtFN);
		txtFN.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Phone Number");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lblNewLabel_2.setBounds(33, 107, 221, 26);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Full Name");
		lblNewLabel_3.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setBounds(33, 199, 221, 20);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Password");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lblNewLabel_4.setBounds(33, 284, 225, 26);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Confirm Password");
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lblNewLabel_5.setBounds(33, 366, 221, 20);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("PIN");
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		lblNewLabel_6.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lblNewLabel_6.setBounds(34, 439, 177, 20);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Confirm PIN");
		lblNewLabel_7.setForeground(new Color(255, 255, 255));
		lblNewLabel_7.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lblNewLabel_7.setBounds(33, 526, 225, 20);
		contentPane.add(lblNewLabel_7);
		
		JButton btnNewButton = new JButton("Register");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                String PN = txtPN.getText();
				String Name = txtFN.getText();
				String Pw = pw.getText();
				String hashPw = Security.getMD5Hash(Pw.getBytes());
				String CPW = cpw.getText();
				String CPIN =cpin.getText();
				String PIN = pin.getText();
				String hashPin = Security.getMD5Hash(PIN.getBytes());
				if(Pw.equals(CPW)) {
				    if(PIN.equals(CPIN)) {
					    if(new Controller().Signup(PN,Name, hashPw, hashPin, email)) {
							JOptionPane.showMessageDialog(null, "Successful registration");
							login a= new login();
							a.show();
							dispose();
						}else {
							JOptionPane.showMessageDialog(null, "Phone number already exists");
						}
				    }
					else {
						JOptionPane.showMessageDialog(null, "PIN mismatch");
					}
				}
				else {
					JOptionPane.showMessageDialog(null,"Password mismatch ");
				}
				
			}
		});
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(33, 636, 378, 34);
		contentPane.add(btnNewButton);
		
		cpw = new JPasswordField();
		cpw.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cpw.setBounds(33, 396, 378, 34);
		contentPane.add(cpw);
		
		pw = new JPasswordField();
		pw.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pw.setBounds(33, 322, 378, 34);
		contentPane.add(pw);
		
		pin = new JPasswordField();
		pin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pin.setBounds(33, 469, 378, 34);
		contentPane.add(pin);
		
		cpin = new JPasswordField();
		cpin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cpin.setBounds(33, 556, 378, 34);
		contentPane.add(cpin);
		
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setIcon(new ImageIcon("C:\\Users\\DELL\\Downloads\\bg1.jpg"));
		lblNewLabel_8.setBounds(0, 0, 436, 693);
		contentPane.add(lblNewLabel_8);
	}
}

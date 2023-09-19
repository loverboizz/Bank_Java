package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Controler.Controller;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;

public class VerifyOTP extends JFrame {

	private JPanel contentPane;
	private JTextField txtCode;
	private String email;
	private String STD;
	private String Amount;
	private String i;
	private String PNo;
	private String PIN;
	private String PW;
	
	
	public void VerifyOTP1(String STD,String PNo,String Amount,String email, String i) throws HeadlessException {
		this.email=email;
		this.STD=STD;
		this.Amount=Amount;
		this.i=i;
		this.PNo=PNo;
		this.PIN=Amount;
		this.PW=Amount;
		try {
			VerifyOTP frame = new VerifyOTP(STD,PNo,Amount,email,i);
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
	public VerifyOTP(String STD,String PNo,String Amount,String email,String i) {
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
				
				emailVerification a= new emailVerification(email);
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
		
		JLabel lblNewLabel_1 = new JLabel("Email Verification");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(108, 0, 252, 56);
		panel.add(lblNewLabel_1);
		
		String Mcode = MailAuthentication.OTP(email);
		
		JButton btnNewButton = new JButton("CONTINUE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String code = txtCode.getText();
				switch(i) {
					case "D":{
						if(Mcode.equals(code)) {
							confirmD a = new confirmD(STD,Amount);
							a.show();
							dispose();
						}else {
							 JOptionPane.showMessageDialog(null,"Invalid confirmation code. Please try again.");   
						}
						break;
					}
					case "W":{
						if(Mcode.equals(code)) {
							confirmW a = new confirmW(STD,Amount);
							a.show();
							dispose();
						}else {
							 JOptionPane.showMessageDialog(null,"Invalid confirmation code. Please try again.");   
						}
						break;
					}
					case "T":{
						if(Mcode.equals(code)) {
							confirmT a = new confirmT(STD,PNo,Amount);
							a.show();
							dispose();
						}else {
							 JOptionPane.showMessageDialog(null,"Invalid confirmation code. Please try again.");   
						}
						break;
					}
					case "ChangePIN":{
						if(new Controller().Changepin(STD, Security.getMD5Hash(PIN.getBytes()))) {
							JOptionPane.showMessageDialog(null, "Successful Pin change");
							setting a = new setting(STD);
							a.show();
							dispose();
						}
						break;
					}
					case "ChangePW":{
						if(new Controller().Changepw(STD, Security.getMD5Hash(PW.getBytes()))) {
							JOptionPane.showMessageDialog(null, "Successful Pin change");
							setting a = new setting(STD);
							a.show();
							dispose();
						}
						break;
					}
				}
				
				
			}
		});
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(58, 456, 324, 34);
		contentPane.add(btnNewButton);
		
		txtCode = new JTextField();
		txtCode.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtCode.setColumns(10);
		txtCode.setBounds(58, 302, 324, 34);
		contentPane.add(txtCode);
		
		JLabel lblNewLabel_2_1 = new JLabel("Enter the OTP to log in.");
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lblNewLabel_2_1.setBounds(55, 258, 248, 34);
		contentPane.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setIcon(new ImageIcon("C:\\Users\\DELL\\Downloads\\bg1.jpg"));
		lblNewLabel_8.setBounds(0, 0, 436, 693);
		contentPane.add(lblNewLabel_8);
	}
}

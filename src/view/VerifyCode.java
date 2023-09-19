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
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import Controler.*;

public class VerifyCode extends JFrame {

	private JPanel contentPane;
	private String email;
	private String choice;
	private JTextField textField;
	
	public void VerifyCode1(String email, String choice) throws HeadlessException {
		this.email=email;
		this.choice=choice;
		try {
			VerifyCode frame = new VerifyCode(email,choice);
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
	public VerifyCode(String email,String choice) {
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
				
				emailVerification a= new emailVerification(choice);
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
		
//		String Mcode = MailAuthentication.sendEmail(email);
		String Mcode = new Controller().sendEmail(email);
		
		JButton btnNewButton = new JButton("CONTINUE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String code = textField.getText();
				switch(choice) {
					case "ForgotPW":{
						
						if(Mcode.equals(code)) {
							FGP a = new FGP(email);
							a.show();
							dispose();
						}else {
							 JOptionPane.showMessageDialog(null,"Invalid confirmation code. Please try again.");   
						}
						break;
					}
					
					case "Signup":{
						
						if(Mcode.equals(code)) {
							signup a = new signup(email);
							a.show();
							dispose();
						}else {
							 JOptionPane.showMessageDialog(null,"Invalid confirmation code. Please try again.");   
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
		
		JLabel lblNewLabel_2_1 = new JLabel("Enter the code to log in.");
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lblNewLabel_2_1.setBounds(55, 258, 248, 34);
		contentPane.add(lblNewLabel_2_1);
		
		textField = new JTextField();
		textField.setBounds(55, 313, 327, 34);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setIcon(new ImageIcon("C:\\Users\\DELL\\Downloads\\bg1.jpg"));
		lblNewLabel_8.setBounds(0, 0, 436, 693);
		contentPane.add(lblNewLabel_8);
	}
}

package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.JTextField;

public class FGP extends JFrame {

	private JPanel contentPane;
	private JPasswordField npw;
	private JPasswordField cpw;
	private JTextField textField;
	private String email;
	public void changepw1(String email) throws HeadlessException {
		this.email = email;
		try {
			FGP frame = new FGP(email);
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
	public FGP(String email) {
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
				login a =new login();
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
		
		JLabel lblNewLabel_1 = new JLabel("Change Password");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(105, 0, 259, 56);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setBounds(0, 0, 436, 699);
		panel.add(lblNewLabel_6);
		
		JButton btnNewButton = new JButton("CONTINUE");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String PN = textField.getText();
				String NPw= npw.getText();
				String CPw = cpw.getText();
				if(new Controller().FGP(PN, email)) {
					if(NPw.equals(CPw)) {
						if(new Controller().Changepw(PN, Security.getMD5Hash(NPw.getBytes()))) {
							JOptionPane.showMessageDialog(null, "Successful Password change");
							login a = new login();
							a.show();
							dispose();
						}
					}else {
						JOptionPane.showMessageDialog(null, "Password mismatch ");
					}

				}else {
					JOptionPane.showMessageDialog(null, "Password wrong");
				}
			}
		});
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setForeground(new Color(255, 128, 192));
		btnNewButton.setBounds(28, 600, 381, 48);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("PHONE NUMBER");
		lblNewLabel_2.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(38, 180, 277, 48);
		contentPane.add(lblNewLabel_2);
		
		npw = new JPasswordField();
		npw.setFont(new Font("Tahoma", Font.PLAIN, 30));
		npw.setBounds(38, 341, 335, 48);
		contentPane.add(npw);
		
		JLabel lblNewLabel_3 = new JLabel("New Password");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
		lblNewLabel_3.setBounds(38, 304, 263, 34);
		contentPane.add(lblNewLabel_3);
		
		cpw = new JPasswordField();
		cpw.setFont(new Font("Tahoma", Font.PLAIN, 30));
		cpw.setBounds(38, 446, 335, 48);
		contentPane.add(cpw);
		
		JLabel lblNewLabel_4 = new JLabel("Confirm Password");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
		lblNewLabel_4.setBounds(38, 409, 267, 34);
		contentPane.add(lblNewLabel_4);
		
		textField = new JTextField();
		textField.setBounds(38, 240, 329, 48);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon("C:\\Users\\DELL\\Downloads\\bg1.jpg"));
		lblNewLabel_5.setBounds(0, 0, 436, 693);
		contentPane.add(lblNewLabel_5);
	}
}


package DAO;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

import Model.User;


public class database {
    private Connection conn;
    private String username;

    public database() {
    	try {
    		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://DESKTOP-NS9BQJ2\\NGUYENTHANHBINH ;database= Loverboy Bank;encrypt=false;trustServerCertificate=true;User=sa;password =123456");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    	
    }
    
    public static String getMD5Hash(byte[] input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(input);
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not found", e);
        }
    }
    
    public static String generateRandomNumber() {
        Random random = new Random();
        int randomNumber = random.nextInt(1000000); // Tạo số nguyên ngẫu nhiên từ 0 đến 999999
        return String.format("%06d", randomNumber); // Định dạng chuỗi để đảm bảo chuỗi có độ dài bằng 6
    }
    
    public boolean Login(String PN, String Pw) {
    	boolean res=false;
    	try {
			Statement stm = (Statement) conn.createStatement();
			String sql = "Select * from Account where PN='"+PN+"' and Password ='"+Pw+"'";
			ResultSet rs = ((java.sql.Statement) stm).executeQuery(sql);
			res=rs.next();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return res;
    }
    
    public boolean FGP(String PN, String email) {// forget password
    	boolean res=false;
    	try {
			Statement stm = (Statement) conn.createStatement();
			String sql = "Select * from Account where PN='"+PN+"' and email ='"+email+"'";
			ResultSet rs = ((java.sql.Statement) stm).executeQuery(sql);
			res=rs.next();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return res;
    }
    
    public boolean checkPN(String PN) {
    	boolean res=false;
    	try {
			Statement stm = (Statement) conn.createStatement();
			String sql = "Select * from Account where PN='"+PN+"'";
			ResultSet rs = ((java.sql.Statement) stm).executeQuery(sql);
			res=rs.next();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return res;
    }
    
    public boolean Signup(String PN, String Name, String Pw, String PIN , String email) {
    	
    	try {
			Statement stm = (Statement) conn.createStatement();
			String sql ="Insert into Account (PN, Name, Password, Balance, Pin, email)\r\n"
					+ "Values ('"+PN+"', '"+Name+"', '"+Pw+"', 0, '"+PIN+"', '"+email+"') ;";
			stm.executeUpdate(sql);
		
			return true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
    	
    }
    
    
    public boolean checkPin(String PN, String Pin) {
        boolean res = false;
        try {
            Statement stm = (Statement) conn.createStatement();
            String sql = "Select * from Account where PN='"+PN+"' and Pin ='"+Pin+"'";
            ResultSet rs = ((java.sql.Statement) stm).executeQuery(sql);
            if(rs.next()) {
                res = true;
            }
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return res;
    }
    
    public boolean Deposit(String PN, String Amount) {
    	try {
            Statement stm = (Statement) conn.createStatement();
            String sql = "UPDATE Account SET balance=balance+"+Amount+" WHERE PN='"+PN+"';";
            stm.executeUpdate(sql);
            return true;
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    	return false;
    }
    
    public boolean Withdraw(String PN, String Amount) {
    	try {
    		Statement stm = (Statement) conn.createStatement();
    		String sql2 = "UPDATE Account\r\n"
					+ "SET balance=balance-"+Amount+"\r\n"
					+ "WHERE PN='"+PN+"' ;";
			stm.executeUpdate(sql2);
			return true;
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    	return false;
    }
    
    public boolean Transfer(String SDT, String PNo, String Amount) {
    	try {
    		Statement stm = (Statement) conn.createStatement();
    		String sql = "UPDATE Account\r\n"
					+ "SET balance=balance-"+Amount+"\r\n"
					+ "WHERE PN='"+SDT+"' ;";
			stm.executeUpdate(sql);
			sql = "UPDATE Account\r\n"
					+ "SET balance=balance+"+Amount+"\r\n"
					+ "WHERE PN='"+PNo+"' ;";
			stm.executeUpdate(sql);
			return true;
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    	return false;
    }
    
    public boolean ChangePin(String PN, String Pin) {
    	
    	try {
    		Statement stm = (Statement) conn.createStatement();
    		String sql ="UPDATE Account\r\n"
					+ "SET Pin='"+Pin+"'\r\n"
					+ "WHERE  PN='"+PN+"' ;";
			stm.executeUpdate(sql);
			return true;
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    	return false;
    }
    
    public boolean ChangePw(String PN, String Pw) {
    	
    	try {
    		Statement stm = (Statement) conn.createStatement();
    		String sql ="UPDATE Account\r\n"
					+ "SET Password='"+Pw+"'\r\n"
					+ "WHERE  PN='"+PN+"' ;";
			stm.executeUpdate(sql);
			return true;
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    	return false;
    }
    
    public ArrayList<User> UserInfo(String PN) {
    	ArrayList<User> res=new ArrayList<>();
    	try {
		Statement stm = (Statement) conn.createStatement();
		String sql = "Select PN, Name, balance, email from Account where PN='"+PN+"' ";
		
		ResultSet rs = stm.executeQuery(sql);
		while(rs.next()) {
			User user=new User(PN, rs.getString("Name"), ""+rs.getString("balance")+" VND", rs.getString("email"));
			res.add(user);
		}
    	}
    	catch(Exception e ) {
    		e.printStackTrace();
    	}
    	return res;
    }
    public ArrayList<User> UserInf(String email) {
    	ArrayList<User> res=new ArrayList<>();
    	try {
		Statement stm = (Statement) conn.createStatement();
		String sql = "Select PN, Name, balance, email from Account where email='"+email+"' ";
		
		ResultSet rs = stm.executeQuery(sql);
		while(rs.next()) {
			User user=new User(rs.getString("PN"), rs.getString("Name"), ""+rs.getString("balance")+" VND", email);
			res.add(user);
		}
    	}
    	catch(Exception e ) {
    		e.printStackTrace();
    	}
    	return res;
    }
    
    
    public String sendEmail(String email) {
    	String Mcode = generateRandomNumber();
    	String senderEmail = "bankloverboy@gmail.com";
        String senderPassword = "xgdjkhjlmxdrnvqb";

        String recipientEmail = email;

        String smtpHost = "smtp.gmail.com";
        String smtpPort = "587";

        String subject = "Verify Email Adress for Loverboy Bank";
        String body ="Subject: Email Verification Code\r\n"
        		+ "\r\n"
        		+ "You have requested to receive a verification code for your account. Below is your verification code: " + Mcode + ".\r\n"
        		+ "\r\n"
        		+ "Please enter this verification code in the application to verify your account. This code will expire after 60 minutes.\r\n"
        		+ "\r\n"
        		+ "If you did not make this request, please disregard this email.\r\n"
        		+ "\r\n"
        		+ "Sincerely,\r\n"
        		+ "\r\n"
        		+ "LoverBoy Bank";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.starttls.enable", "true");
//        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
        	session.getProperties().put("mail.smtp.ssl.trust", "smtp.gmail.com");
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            System.out.println("Failed to send email. Error: " + e.getMessage());
        }
        return Mcode;
    }
    
    
    
    
    
}

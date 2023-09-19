package Server;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import DAO.database;
import Model.User;

public class ServerGUI {
    private ServerSocket server;
    private int port = 9878;
    private JTextArea textArea;
    String sentence_from_client;
    String sentence_to_client;

    public ServerGUI() {
        try {
        	SwingUtilities.invokeLater(() -> createAndShowGUI());
            server = new ServerSocket(port);
            System.out.println("Server is started");



            while (true) {
                run();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("ServerGUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textArea = new JTextArea(20, 50);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JButton clearButton = new JButton("Clear");
        addToTextArea("Server is started");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(clearButton);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }

    private void addToTextArea(String text) {
        textArea.append(text + "\n");
    }
  
    private String StringFromJSON(String jsonStr) {
        String res = "";
        for (int i = 1; i < jsonStr.length() - 1; i++) {
            res += jsonStr.charAt(i);
        }
        return res;
    }

    public void run() {
        try {
            Socket socket = server.accept();
            

            Thread T = new Thread(() -> {
                try {
                    String sentence_from_client;
                    BufferedReader inFromClient = new BufferedReader(
                            new InputStreamReader(socket.getInputStream(), "UTF-8"));
                    OutputStreamWriter outToClient = new OutputStreamWriter(socket.getOutputStream(), "UTF-8");

                    sentence_from_client = inFromClient.readLine();
                    Gson gson = new Gson();
                    JsonObject jsonObject = gson.fromJson(sentence_from_client, JsonObject.class);
                    String function = jsonObject.get("function").toString();
                    String res = "";
                    switch (StringFromJSON(function)) {
                    case "Login": {
                    	addToTextArea("got");
                    	addToTextArea(function);
                        JsonObject data = jsonObject.getAsJsonObject("data");
                        String PN = StringFromJSON(data.get("PN").toString());
                        String pw = StringFromJSON(data.get("pw").toString());
                        String i = StringFromJSON(data.get("i").toString());
                        res = new database().Login(PN, pw) ? "true" : "false";
                        if(i.equals("1")) {
                        	if(res.equals("true"))  addToTextArea(PN +" is online.");
                        }
                        
                        break;
                    }
                    case "FGP": {
                        JsonObject data = jsonObject.getAsJsonObject("data");
                        String PN = StringFromJSON(data.get("PN").toString());
                        String email = StringFromJSON(data.get("email").toString());
                        addToTextArea(PN+" is retrieving the password from "+email);
                        res = new database().FGP(PN, email) ? "true" : "false";
                        
                        break;
                    }
                    case "UserInfo":{
                    	JsonObject data = jsonObject.getAsJsonObject("data");
                    	String PN = StringFromJSON(data.get("PN").toString());
                    	ArrayList<User> userList=new database().UserInfo(PN);
                    	res= new Gson().toJson(userList, new ArrayList<User>().getClass());
                    	break;
                    }
                    case "Signup":{
                    	addToTextArea("got");
                    	addToTextArea(function);
                    	JsonObject data = jsonObject.getAsJsonObject("data");
                    	String PN = StringFromJSON(data.get("PN").toString());
                    	String Name = StringFromJSON(data.get("Name").toString());
                    	String PW = StringFromJSON(data.get("pw").toString());
                    	String PIN = StringFromJSON(data.get("pin").toString());
                    	String email =StringFromJSON(data.get("email").toString());
                    	addToTextArea("Someone is registering with phone number :"+PN);
                    	res = new database().Signup(PN, Name, PW, PIN, email) ? "true" : "false";
                    	if(res.equals("true")) {
                    		addToTextArea(PN+" successful registration");
                    	}else {
                    		addToTextArea(PN+" registration failed");
                    	}
                    	break;
                    }
                    case "checkPin":{
                    	 JsonObject data = jsonObject.getAsJsonObject("data");
                         String PN = StringFromJSON(data.get("PN").toString());
                         String pin = StringFromJSON(data.get("pin").toString());
                         res = new database().checkPin(PN, pin) ? "true" : "false";
                         break;
                    }
                    
                    case "Deposit":{
                    	addToTextArea("got");
                    	addToTextArea(function);
                    	JsonObject data = jsonObject.getAsJsonObject("data");
                    	String PN = StringFromJSON(data.get("PN").toString());
                    	String Amount =StringFromJSON(data.get("Amount").toString());
                    	//int Amount = Integer.parseInt(StringFromJSON(data.get("Amount").toString()));
                    	res = new database().Deposit(PN, Amount) ? "true" : "false";
                    	if(res.equals("true")) {
                    		addToTextArea(PN+" recharge successful");
                    	}else {
                    		addToTextArea(PN+" recharge failed");
                    	}
                    	break;
                    }
                    case "Withdraw":{
                    	addToTextArea("got");
                    	addToTextArea(function);
                    	JsonObject data = jsonObject.getAsJsonObject("data");
                    	String PN = StringFromJSON(data.get("PN").toString());
                    	//int Amount = Integer.parseInt(StringFromJSON(data.get("Amount").toString()));
                    	String Amount =StringFromJSON(data.get("Amount").toString());
                    	res = new database().Withdraw(PN, Amount) ? "true" : "false";
                    	if(res.equals("true")) {
                    		addToTextArea(PN+" has successfully withdrawn money");
                    	}else {
                    		addToTextArea(PN+" recharge failed");
                    	}
                    	break;
                    }
                    case "Transfer":{
                    	addToTextArea("got");
                    	addToTextArea(function);
                    	JsonObject data = jsonObject.getAsJsonObject("data");
                    	String SDT = StringFromJSON(data.get("SDT").toString());
//                    	int Amount = Integer.parseInt(StringFromJSON(data.get("Amount").toString()));
                    	String Amount =StringFromJSON(data.get("Amount").toString());
                    	String Pno = StringFromJSON(data.get("Pno").toString());
                    	addToTextArea(SDT+" is transferring to "+Pno);
                    	res = new database().Transfer(SDT, Pno, res) ? "true" : "false";
                    	if(res.equals("true")) {
                    		addToTextArea(SDT+" transfer to "+Pno+" successful");
                    	}else {
                    		addToTextArea(SDT+" transfer to "+Pno+" failed");
                    	}
                    	break;
                    }
                    case "Changepin":{
                    	addToTextArea("got");
                    	addToTextArea(function);
                    	JsonObject data = jsonObject.getAsJsonObject("data");
                    	String PN = StringFromJSON(data.get("PN").toString());
                    	String Pin = StringFromJSON(data.get("Pin").toString());
                    	addToTextArea(PN+" is changing Pin");
                    	res = new database().ChangePin(PN, Pin) ? "true" : "false";
                    	if(res.equals("true")) {
                    		addToTextArea(PN+" successfully changed his Pin");
                    	}else {
                    		addToTextArea(PN+" changed the Pin failed");
                    	}
                    	break;
                    }
                    
                    case "Changepw":{
                    	addToTextArea("got");
                    	addToTextArea(function);
                    	JsonObject data = jsonObject.getAsJsonObject("data");
                    	String PN = StringFromJSON(data.get("PN").toString());
                    	String Pw = StringFromJSON(data.get("Pw").toString());
                    	addToTextArea(PN+" is changing Password");
                    	res = new database().ChangePw(PN, Pw) ? "true" : "false";
                    	if(res.equals("true")) {
                			addToTextArea(PN+" successfully changed password");
                		}else {
                			addToTextArea(PN+" changed the password failed");
                		}
                    	
                    	break;
                    }
                    case "CheckPn":{
                    	JsonObject data = jsonObject.getAsJsonObject("data");
                    	String PN = StringFromJSON(data.get("PN").toString());
                    	res = new database().checkPN(PN) ? "true" : "false";
                    	break;
                    }
                    case "Logout":{
                    	JsonObject data = jsonObject.getAsJsonObject("data");
                    	String PN = StringFromJSON(data.get("PN").toString());
                    	res = "true";
                    	addToTextArea(PN+" is offline.");
                    }
                    
                    case "sendEmail":{
                    	JsonObject data = jsonObject.getAsJsonObject("data");
                    	String email = StringFromJSON(data.get("email").toString());
                    	res = new database().sendEmail(email);
                    	break;
                    }
                    
                }

                
                sentence_to_client = res;


                outToClient.write(sentence_to_client);
                outToClient.flush();
                socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            T.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ServerGUI server =new ServerGUI();
        
    }
}
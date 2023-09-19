package Server;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import DAO.database;
import Model.User;


public class Serverr{
    public ServerSocket server;
    String sentence_from_client;
    String sentence_to_client;
    int port=9878;
    
    public ServerSocket getServer() {
		return server;
	}
	public void setServer(ServerSocket server) {
		this.server = server;
	}
	public String getSentence_from_client() {
		return sentence_from_client;
	}
	public void setSentence_from_client(String sentence_from_client) {
		this.sentence_from_client = sentence_from_client;
	}
	public String getSentence_to_client() {
		return sentence_to_client;
	}
	public void setSentence_to_client(String sentence_to_client) {
		this.sentence_to_client = sentence_to_client;
	}
	public String StringFromJSON(String jsonStr) {
        String res = "";
        for(int i=1;i<jsonStr.length()-1;i++) {
            res+=jsonStr.charAt(i);
        }
        return res;
    }
    public void run() {
        try {
            Socket socket = server.accept();
            System.out.println("got");
            Thread T=new Thread(()-> {
                try {
                    String sentence_from_client;
                    BufferedReader inFromClient =
                            new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
                    OutputStreamWriter outToClient =
                            new OutputStreamWriter(socket.getOutputStream(), "UTF-8");


                    sentence_from_client = inFromClient.readLine();
                    Gson gson = new Gson();
                    JsonObject jsonObject = gson.fromJson(sentence_from_client, JsonObject.class);
                    System.out.println(sentence_from_client);
                    String function = jsonObject.get("function").toString();
                    String res="";
                    System.out.println(function);
                    switch (StringFromJSON(function)) {
                        case "Login": {
                            JsonObject data = jsonObject.getAsJsonObject("data");
                            String PN = StringFromJSON(data.get("PN").toString());
                            String pw = StringFromJSON(data.get("pw").toString());
                            res = new database().Login(PN, pw) ? "true" : "false";
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
                        	JsonObject data = jsonObject.getAsJsonObject("data");
                        	String PN = StringFromJSON(data.get("PN").toString());
                        	String Name = StringFromJSON(data.get("Name").toString());
                        	String PW = StringFromJSON(data.get("pw").toString());
                        	String PIN = StringFromJSON(data.get("pin").toString());
                        	String email =StringFromJSON(data.get("email").toString());
                        	res = new database().Signup(PN, Name, PW, PIN, email) ? "true" : "false";
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
                        	JsonObject data = jsonObject.getAsJsonObject("data");
                        	String PN = StringFromJSON(data.get("PN").toString());
                        	String Amount =StringFromJSON(data.get("Amount").toString());
                        	//int Amount = Integer.parseInt(StringFromJSON(data.get("Amount").toString()));
                        	res = new database().Deposit(PN, Amount) ? "true" : "false";
                        	break;
                        }
                        case "Withdraw":{
                        	JsonObject data = jsonObject.getAsJsonObject("data");
                        	String PN = StringFromJSON(data.get("PN").toString());
                        	//int Amount = Integer.parseInt(StringFromJSON(data.get("Amount").toString()));
                        	String Amount =StringFromJSON(data.get("Amount").toString());
                        	res = new database().Withdraw(PN, Amount) ? "true" : "false";
                        	break;
                        }
                        case "Transfer":{
                        	JsonObject data = jsonObject.getAsJsonObject("data");
                        	String SDT = StringFromJSON(data.get("SDT").toString());
//                        	int Amount = Integer.parseInt(StringFromJSON(data.get("Amount").toString()));
                        	String Amount =StringFromJSON(data.get("Amount").toString());
                        	String Pno = StringFromJSON(data.get("Pno").toString());
                        	res = new database().Transfer(SDT, Pno, res) ? "true" : "false";
                        	break;
                        }
                        case "Changepin":{
                        	JsonObject data = jsonObject.getAsJsonObject("data");
                        	String PN = StringFromJSON(data.get("PN").toString());
                        	String Pin = StringFromJSON(data.get("Pin").toString());
                        	res = new database().ChangePin(PN, Pin) ? "true" : "false";
                        	break;
                        }
                        
                        case "Changepw":{
                        	JsonObject data = jsonObject.getAsJsonObject("data");
                        	String PN = StringFromJSON(data.get("PN").toString());
                        	String Pw = StringFromJSON(data.get("Pw").toString());
                        	res = new database().ChangePw(PN, Pw) ? "true" : "false";
                        	break;
                        }
                        case "CheckPn":{
                        	JsonObject data = jsonObject.getAsJsonObject("data");
                        	String PN = StringFromJSON(data.get("PN").toString());
                        	res = new database().checkPN(PN) ? "true" : "false";
                        	break;
                        }
                    }

                    
                    sentence_to_client = res;


                    outToClient.write(sentence_to_client);
                    outToClient.flush();
                    socket.close();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

            });
            T.start();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Serverr() {
        try {
            server = new ServerSocket(port);
            System.out.println("Server is started");
            while (true) {
                run();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new Serverr();
    }
}
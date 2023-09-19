package Controler;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import Model.User;
public class Controller {
	String sentence_to_server;
    String sentence_from_server;
	private String url = "localhost";
	private int port = 9878;
	
	public String getData(String json,String url,int port) {
        String res="";
        try {
            Socket socket = new Socket(url, port);
            OutputStreamWriter outToServer =
                    new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
            BufferedReader inFromServer =
                    new BufferedReader(new
                            InputStreamReader(socket.getInputStream(), "UTF-8"));
            outToServer.write(json + '\n');
            outToServer.flush();
            res = inFromServer.readLine();
            socket.close();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return res;
    }
	
	public boolean Login(String PN, String PW, String i) {
		boolean res=false;
		try {
            sentence_to_server = "{'function':'Login','data':{'PN':'" + PN + "','pw':'" + PW + "','i':'"+i+"'}}";
            sentence_from_server= getData(sentence_to_server,url,port);
            res=new Gson().fromJson(sentence_from_server, boolean.class);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return res;
	}
	
	public boolean FGP(String PN, String email) {
		boolean res=false;
		try {
            sentence_to_server = "{'function':'FGP','data':{'PN':'" + PN + "','email':'" + email + "'}}";
            sentence_from_server= getData(sentence_to_server,url,port);
            res=new Gson().fromJson(sentence_from_server, boolean.class);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return res;
	}
	
	
	
	public ArrayList<User> UserInfo(String PN) {
		ArrayList<User> res= new ArrayList<>();
		try {
            sentence_to_server = "{'function':'UserInfo','data':{'PN':'" + PN + "'}}";
            sentence_from_server= getData(sentence_to_server,url,port);
            Type type = new TypeToken<ArrayList<User>>(){}.getType();
            res=new Gson().fromJson(sentence_from_server, type);
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return res;
    }
	
	public boolean Signup(String PN, String Name, String PW, String PIN, String email) {
		boolean res = false;
		try {
            sentence_to_server = "{'function':'Signup','data':{'PN':'" + PN + "', 'Name':'"+ Name +"','pw':'" + PW +"', 'pin':'"+ PIN +"','email':'"+email+"'}}";
            sentence_from_server= getData(sentence_to_server,url,port);
            res=new Gson().fromJson(sentence_from_server, boolean.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	
    public boolean checkPin(String PN, String Pin) {
        boolean res = false;
        try {
        	 sentence_to_server = "{'function':'checkPin','data':{'PN':'" + PN + "','pin':'" + Pin + "'}}";
             sentence_from_server= getData(sentence_to_server,url,port);
             res=new Gson().fromJson(sentence_from_server, boolean.class);
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return res;
    }
    
    public boolean Deposit(String PN, String Amount) {
    	boolean res = false;
        try {
        	 sentence_to_server = "{'function':'Deposit','data':{'PN':'" + PN + "','Amount':'" + Amount + "'}}";
             sentence_from_server= getData(sentence_to_server,url,port);
             res=new Gson().fromJson(sentence_from_server, boolean.class);
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return res;
    }
    
    public boolean Withdraw(String PN,String Amount) {
    	boolean res = false;
    	try {
       	 sentence_to_server = "{'function':'Withdraw','data':{'PN':'" + PN + "','Amount':'" + Amount + "'}}";
            sentence_from_server= getData(sentence_to_server,url,port);
            res=new Gson().fromJson(sentence_from_server, boolean.class);
       } catch (Exception e1) {
           // TODO Auto-generated catch block
           e1.printStackTrace();
       }
       return res;
    }
    public boolean Transfer(String SDT, String Pno,String Amount) {
    	boolean res = false;
    	try {
       	 sentence_to_server = "{'function':'Transfer','data':{'SDT':'" + SDT + "', 'Pno':'"+ Pno +"','Amount':'" + Amount + "'}}";
            sentence_from_server= getData(sentence_to_server,url,port);
            res=new Gson().fromJson(sentence_from_server, boolean.class);
       } catch (Exception e1) {
           // TODO Auto-generated catch block
           e1.printStackTrace();
       }
       return res;
    }
    
    public boolean Changepin(String PN, String Pin) {
       	boolean res = false;
    	try {
       	 sentence_to_server = "{'function':'Changepin','data':{'PN':'" + PN + "', 'Pin':'"+ Pin +"'}}";
            sentence_from_server= getData(sentence_to_server,url,port);
            res=new Gson().fromJson(sentence_from_server, boolean.class);
       } catch (Exception e1) {
           // TODO Auto-generated catch block
           e1.printStackTrace();
       }
       return res;
    }
    
    public boolean Changepw(String PN, String Pw) {
       	boolean res = false;
    	try {
       	 sentence_to_server = "{'function':'Changepw','data':{'PN':'" + PN + "', 'Pw':'"+ Pw +"'}}";
            sentence_from_server= getData(sentence_to_server,url,port);
            res=new Gson().fromJson(sentence_from_server, boolean.class);
       } catch (Exception e1) {
           // TODO Auto-generated catch block
           e1.printStackTrace();
       }
       return res;
    }
 
    
    public boolean CheckPn(String PN) {
    	boolean res = false;
    	try {
    		sentence_to_server = "{'function':'CheckPn','data':{'PN':'" + PN + "'}}";
            sentence_from_server= getData(sentence_to_server,url,port);
            res=new Gson().fromJson(sentence_from_server, boolean.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	return res;
    }
    
    public boolean Logout(String PN) {
    	boolean res = false;
    	try {
    		sentence_to_server = "{'function':'Logout','data':{'PN':'" + PN + "'}}";
            sentence_from_server= getData(sentence_to_server,url,port);
            res=new Gson().fromJson(sentence_from_server, boolean.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	return res;
    }
    
    public String sendEmail(String email) {
    	sentence_to_server = "{'function':'sendEmail','data':{'email':'" + email + "'}}";
        sentence_from_server= getData(sentence_to_server,url,port);
        String res=new Gson().fromJson(sentence_from_server, String.class);
        return res;
    }
    
    
    
}

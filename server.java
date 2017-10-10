import java.io.*;
import java.net.*;
import java.util.ArrayList;


public class server extends Thread{

	public static ArrayList<Socket> Connections = new ArrayList<Socket>();
	private static ServerSocket s1;
	private static Socket s2;
	private static int  i = 0;
	public static void main(String args[])throws Exception {
		 int port = 25000;
                s1 = new ServerSocket(port);

		while(true){
			s2 = s1.accept();
			Connections.add(s2);
			i++;
			Thread t = new server2(s2,Connections);
			t.start();
			Thread t2 = new send(s2,Connections,i);
			t2.start();

		}
	}	

}

class server2 extends Thread{
	
	private Socket Connection;
	private ArrayList<Socket> m;
	private int i;
	public server2(Socket connection,ArrayList<Socket> m) {
        this.Connection = connection;
	this.m = m;
    	}
	
	public void run() {
		
		try{
			while(true) {
	     
                		InputStream i1 = Connection.getInputStream();
                		InputStreamReader ir = new InputStreamReader(i1);
                		BufferedReader br = new BufferedReader(ir);
                		String number = br.readLine();
                		System.out.println("Received from client: "+number+"\n");
			}
		}
		catch(Exception e){

		}
	}

}

class send extends Thread{

        private Socket Connection;
        private ArrayList<Socket> m;
	PrintWriter pwPrintWriter;
	PrintWriter pwPrintWriter1;
        private static int i;
	private static int j;
	private String msgToClientString;
        public send(Socket connection,ArrayList<Socket> m,int i) {
        this.Connection = connection;
        this.m = m;
        this.i = i;
        }
	public void run() {
		try{
			while(true) {
		
                       		//String msgToClientString = null;
				BufferedReader input = new BufferedReader(new InputStreamReader(System.in));//get userinput
				msgToClientString = input.readLine();//get message to send to client
				for(j =0 ; j < i; j++){
				 	pwPrintWriter1 = new PrintWriter(new OutputStreamWriter(m.get(j).getOutputStream()));	
				 	pwPrintWriter1.println(msgToClientString);//send message to client with PrintWriter
                        		pwPrintWriter1.flush();
                       			// pwPrintWriter.flush();//flush the PrintWriter
                        		System.out.println("Please enter something to send back to client..");
				}
			
                	}
		}
                catch(Exception e){

                }
		

        }

}




















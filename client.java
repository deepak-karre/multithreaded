import java.io.*;
import java.net.*;

public class client{

	public static void main(String args[])throws Exception{
		Socket s1 = new Socket("127.0.0.1",25000);
		Thread thread2 =new ReceiveThread(s1);
		thread2.start();
		Thread thread3 = new SendThread(s1);
		thread3.start();
	}
}

class ReceiveThread extends Thread{
	Socket sock=null;
	BufferedReader recieve=null;
	
	public ReceiveThread(Socket sock) {
		this.sock = sock;
	}
	public void run(){
		try{
		while(true) {
		InputStream i1 = sock.getInputStream();
                InputStreamReader ir = new InputStreamReader(i1);
                BufferedReader br1 = new BufferedReader(ir);
                String number = br1.readLine();
                System.out.println("Received from server: "+number+"\n");
		}
		}
		catch(Exception e) {

		}
	}


}

class SendThread extends Thread{
		Socket sock = null;
		PrintWriter pwPrintWriter;
		public SendThread(Socket sock){
			this.sock = sock;

		}
		public void run(){
			try{
                pwPrintWriter =new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));
                while(true) {

                	String msgToClientString = null;
                        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));//get userinput

                        msgToClientString = input.readLine();//get message to send to client

                        pwPrintWriter.println(msgToClientString);//send message to client with PrintWriter
                        pwPrintWriter.flush();//flush the PrintWriter
                        System.out.println("Please enter something to send back to server..");
                }
                }
                catch(Exception e){

                }

		}
}



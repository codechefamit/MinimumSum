import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.text.MaskFormatter;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class ClientFrame extends Thread{

	private JFrame frame;
	private JLabel lblCard2,lblCard1,lblCards,lblCard3,lblCard4,lblCard5;
	private Socket clientSocket = null;
	private static PrintStream os = null;
	private static BufferedReader is = null;
	private JLabel lblConnect,lblClientName;
	private JTextField txtIP;
	private JButton btnConnect;
	private JLabel lblPort;
	private JFormattedTextField txtPort;
	private JLabel lblName;
	private JTextField txtName;
	private  JLabel lblStatus;
	private JLabel lblCardFace;
	private JLabel lblDiscardPile;
	private String status;
	private JLabel lblRem;
	private JLabel lblKeep;
	private JButton btnPlay,btnShow;
	private JList<String> stList;
	private DefaultListModel<String> listModel;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientFrame window = new ClientFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	public ClientFrame() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame("Minimum Sum - Amit Kothiyal");
		frame.setResizable(false);
		frame.getContentPane().setForeground(Color.BLACK);
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 450, 475);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblClientName = new JLabel("Name");
		lblClientName.setVisible(false);
		
		lblCard2 = new JLabel("Card2");
		lblCard2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				lblRem.setIcon(lblCard2.getIcon());
				lblRem.setText(lblCard2.getText());
			}
		});
		lblCard2.setBounds(65, 100, 80, 120);
		lblCard2.setIcon(new ImageIcon(getClass().getResource("res/130.jpg")));
		lblCard2.setVisible(false);
		
		lblCard3 = new JLabel("Card3");
		lblCard3.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				lblRem.setIcon(lblCard3.getIcon());
				lblRem.setText(lblCard3.getText());
			}
		});
		lblCard3.setBounds(90, 100, 80, 120);
		lblCard3.setIcon(new ImageIcon(getClass().getResource("res/120.jpg")));
		lblCard3.setVisible(false);
		
		lblCard4 = new JLabel("Card4");
		lblCard4.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				lblRem.setIcon(lblCard4.getIcon());
				lblRem.setText(lblCard4.getText());
			}
		});
		lblCard4.setBounds(115, 100, 80, 120);
		lblCard4.setIcon(new ImageIcon(getClass().getResource("res/110.jpg")));
		lblCard4.setVisible(false);
		
		lblCard5 = new JLabel("Card5");
		lblCard5.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				lblRem.setIcon(lblCard5.getIcon());
				lblRem.setText(lblCard5.getText());
			}
		});
		lblCard5.setBounds(140, 100, 80, 120);
		lblCard5.setIcon(new ImageIcon(getClass().getResource("res/100.jpg")));
		lblCard5.setVisible(false);
		frame.getContentPane().add(lblCard5);
		frame.getContentPane().add(lblCard4);
		frame.getContentPane().add(lblCard3);
		frame.getContentPane().add(lblCard2);		
		lblClientName.setForeground(Color.BLACK);
		lblClientName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblClientName.setBounds(310, 11, 110, 19);
		frame.getContentPane().add(lblClientName);
		
		lblCard1 = new JLabel("Card1");
		lblCard1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				lblRem.setIcon(lblCard1.getIcon());
				lblRem.setText(lblCard1.getText());
			}
		});
		lblCard1.setBounds(40, 100, 80, 120);
		lblCard1.setIcon(new ImageIcon(getClass().getResource("res/10.jpg")));
		lblCard1.setVisible(false);
		frame.getContentPane().add(lblCard1);
		
		lblCards = new JLabel("Your Cards :");
		lblCards.setForeground(Color.BLACK);
		lblCards.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCards.setBounds(30, 70, 100, 19);
		frame.getContentPane().add(lblCards);
		
		lblConnect = new JLabel("IP :");
		lblConnect.setForeground(Color.BLACK);
		lblConnect.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblConnect.setBounds(30, 45, 32, 19);
		frame.getContentPane().add(lblConnect);
		
		txtIP = new JTextField();
		txtIP.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtIP.setBounds(65, 45, 125, 20);
		frame.getContentPane().add(txtIP);
		txtIP.setColumns(10);
		
		btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=txtName.getText().trim();
				String ip=txtIP.getText().trim();
				int port=Integer.parseInt(txtPort.getText());
				try {
				      clientSocket = new Socket(ip,port);
				      os = new PrintStream(clientSocket.getOutputStream());
				      is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				    } catch (UnknownHostException e1) {
				      System.err.println("Don't know about host " + ip);
						lblStatus.setText("Status : Don't know about host "+ ip);
				    } catch (IOException e2) {
				      System.err.println("Couldn't get I/O for the connection to the host "+ ip);
						lblStatus.setText("Status : Couldn't get I/O for the connection to the host "+ ip);
				    }
				try{
				btnConnect.setEnabled(false);
				txtName.setEditable(false);
				txtPort.setEditable(false);
				txtIP.setEditable(false);
				lblClientName.setText(name);
				lblClientName.setVisible(true);
				status="Status : Connected";
				lblStatus.setText(status);
				os.println(name);
				new Minion().execute();
			}catch(Exception e1){
				lblStatus.setText("Not Connected");
			}
			}
		});
		btnConnect.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnConnect.setBounds(320, 45, 100, 20);
		frame.getContentPane().add(btnConnect);
		
		lblPort = new JLabel("Port :");
		lblPort.setForeground(Color.BLACK);
		lblPort.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPort.setBounds(197, 45, 46, 19);
		frame.getContentPane().add(lblPort);
		
		try {
			txtPort = new JFormattedTextField(new MaskFormatter("####"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		txtPort.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtPort.setBounds(245, 45, 66, 20);
		frame.getContentPane().add(txtPort);
		
		lblName = new JLabel("Name :");
		lblName.setForeground(Color.BLACK);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblName.setBounds(30, 16, 60, 19);
		frame.getContentPane().add(lblName);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtName.setColumns(10);
		txtName.setBounds(90, 17, 100, 20);
		frame.getContentPane().add(txtName);
		
		lblStatus=new JLabel("Status : Not Connected");
		lblStatus.setForeground(Color.BLACK);
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblStatus.setBounds(254, 401, 180, 19);
		frame.getContentPane().add(lblStatus);
		
		lblCardFace = new JLabel("");
		lblCardFace.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				lblKeep.setIcon(lblCardFace.getIcon());
				lblKeep.setText(lblCardFace.getText());
			}
		});
		lblCardFace.setBounds(310, 100, 80, 120);
		frame.getContentPane().add(lblCardFace);
		
		lblDiscardPile = new JLabel("Discard Pile :");
		lblDiscardPile.setForeground(Color.BLACK);
		lblDiscardPile.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDiscardPile.setBounds(295, 70, 100, 19);
		frame.getContentPane().add(lblDiscardPile);
		
		JLabel lblYourMove = new JLabel("Your Move : ");
		lblYourMove.setForeground(Color.BLACK);
		lblYourMove.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblYourMove.setBounds(30, 231, 100, 19);
		frame.getContentPane().add(lblYourMove);
		
		JLabel lblToRemove = new JLabel("Remove");
		lblToRemove.setForeground(Color.BLACK);
		lblToRemove.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblToRemove.setBounds(40, 250, 82, 19);
		frame.getContentPane().add(lblToRemove);
		
		JLabel lblKe = new JLabel("Keep");
		lblKe.setForeground(Color.BLACK);
		lblKe.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblKe.setBounds(132, 250, 82, 19);
		frame.getContentPane().add(lblKe);
		
		lblRem = new JLabel("rem");
		lblRem.setBounds(40, 273, 80, 120);
		lblRem.setVisible(false);
		frame.getContentPane().add(lblRem);
		
		lblKeep = new JLabel();
		lblKeep.setBounds(134, 273, 80, 120);
		lblKeep.setText(null);
 		lblKeep.setIcon(new ImageIcon(getClass().getResource("res/q.jpg")));
 		lblKeep.setVisible(false);
		frame.getContentPane().add(lblKeep);
		
		btnPlay = new JButton("Play");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String rem=lblRem.getText();
				String keep=lblKeep.getText();
				String move = null;
				Card r=Card.convertToCard(rem);
				if(keep!=null){
				Card k=Card.convertToCard(keep);
				move=""+r.getValue()+"-"+k.getValue();
				}else
				{
					move=""+r.getValue()+"-0";
				}
				os.println(move);
				btnPlay.setEnabled(false);
				lblCard1.setIcon(null);
		 		lblCard1.setText(null);
		 		lblCard1.setVisible(false);
		 		lblCard2.setIcon(null);
		 		lblCard2.setText(null);
		 		lblCard2.setVisible(false);
		 		lblCard3.setIcon(null);
		 		lblCard3.setText(null);
		 		lblCard3.setVisible(false);
		 		lblCard4.setIcon(null);
		 		lblCard4.setText(null);
		 		lblCard4.setVisible(false);
		 		lblCard5.setIcon(null);
		 		lblCard5.setText(null);
		 		lblCard5.setVisible(false);
		 		lblKeep.setIcon(null);
		 		lblKeep.setText(null);
		 		lblKeep.setVisible(false);
		 		lblRem.setIcon(null);
		 		lblRem.setText(null);
		 		lblRem.setVisible(false);
		 		new Minion().execute();
			}
		});
		btnPlay.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnPlay.setBounds(114, 401, 100, 20);
		btnPlay.setEnabled(false);
		frame.getContentPane().add(btnPlay);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(254, 267, 180, 123);
		frame.getContentPane().add(scrollPane);
		

		listModel=new DefaultListModel<String>();
		stList = new JList<String>(listModel);
		stList.setVisibleRowCount(8);
		stList.setAutoscrolls(true);
		stList.setFont(new Font("Segoe UI", Font.ITALIC, 11));
		scrollPane.setViewportView(stList);
		
		btnShow = new JButton("Show");
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnPlay.setEnabled(false);
		 		lblKeep.setIcon(null);
		 		lblKeep.setText(null);
		 		lblKeep.setVisible(false);
		 		lblRem.setIcon(null);
		 		lblRem.setText(null);
		 		lblRem.setVisible(false);
		 		btnShow.setEnabled(false);
		 		os.println("0");
		 		new Minion().execute();
			}
		});
		btnShow.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnShow.setEnabled(false);
		btnShow.setBounds(10, 401, 100, 20);
		frame.getContentPane().add(btnShow);
		
		JLabel lblTicker = new JLabel("Ticker : ");
		lblTicker.setForeground(Color.BLACK);
		lblTicker.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTicker.setBounds(250, 235, 60, 19);
		frame.getContentPane().add(lblTicker);
		
		JLabel label = new JLabel("Developed By : Amit Kothiyal | Copyright (C), All rights reserved");
		label.setForeground(Color.DARK_GRAY);
		label.setFont(new Font("Segoe UI", Font.ITALIC, 13));
		label.setBounds(45, 425, 360, 19);
		frame.getContentPane().add(label);
	}
		
	
private class Minion extends SwingWorker<List<String>,String>{
	
	protected void done() {
		  List<String> status;
		    try {
		    int ch=1;
		     status = get();
		     if(status==null){
					lblStatus.setText("Status : Game End");
					listModel.addElement("<-----Round----->");
					stList.ensureIndexIsVisible(stList.getModel().getSize()-1);
					new Minion().execute();
		     }
		     else{
		     for(String str:status){
		    	 if(str.contains("Bye")){
		    		 lblStatus.setText("Status : Eliminated");
		    	 }
		     else if(str.startsWith("faceUp")){
		    		 String parts[]=str.split("-");
			    	 Card cd=Card.convertToCard(parts[1]);
			    	 String url="res/"+cd.getValue()+cd.getSuit()+".jpg";
			    	lblCardFace.setText(""+cd);
			 		lblCardFace.setIcon(new ImageIcon(getClass().getResource(url)));
		    	 }
		     else{
	    	 Card cd=Card.convertToCard(str);
	    	 String url="res/"+cd.getValue()+cd.getSuit()+".jpg";
	    	 switch(ch){
	    	 case 1:
			 		lblCard1.setIcon(new ImageIcon(getClass().getResource(url)));
			 		lblCard1.setText(""+cd);
			 		lblCard1.setVisible(true);
	    		 break;
	    	 case 2:
	    		 lblCard2.setIcon(new ImageIcon(getClass().getResource(url)));
	    		 lblCard2.setText(""+cd);
	    		 lblCard2.setVisible(true);
	    		 break;
	    	 case 3:
	    		 lblCard3.setIcon(new ImageIcon(getClass().getResource(url)));
	    		 lblCard3.setText(""+cd);
	    		 lblCard3.setVisible(true);
	    		 break;
	    	 case 4:
	    		 lblCard4.setIcon(new ImageIcon(getClass().getResource(url)));
	    		 lblCard4.setText(""+cd);
	    		 lblCard4.setVisible(true);
	    		 break;
	    	 case 5:
	    		 lblCard5.setIcon(new ImageIcon(getClass().getResource(url)));
	    		 lblCard5.setText(""+cd);
	    		 lblCard5.setVisible(true);
	    		 break;
	    	 }
	    	 ch++;
		     }
		     }
		     if(lblStatus.getText().contains("Your")){
		     btnPlay.setEnabled(true);
		     lblKeep.setVisible(true);
		     lblKeep.setText(null);
		 	lblKeep.setIcon(new ImageIcon(getClass().getResource("res/q.jpg")));
		     lblRem.setVisible(true);
		     lblRem.setText(null);
		     btnShow.setEnabled(true);
		     }
		     else
		     {
		    	 new Minion().execute();
		    	 btnShow.setEnabled(false);
		     }
		    }
		     }
		    catch (InterruptedException e) {
		    	System.err.println("Exception:  " + e);
		    } catch (ExecutionException e) {
		    	System.err.println("Exception:  " + e);
		    }
	}
	
	protected void process(List<String> chunks) {
		for(String s:chunks){
			if(s.startsWith("<")){
				if(s.contains("turn") || s.contains("Game"))
				lblStatus.setText("Status : "+s);
				listModel.addElement(s);
				stList.ensureIndexIsVisible(stList.getModel().getSize()-1);
			}
		}
	}

	protected List<String> doInBackground() throws Exception {
		 String responseLine = null;
		List<String> list = new ArrayList<String>();
		 while ((responseLine = is.readLine()) != null) {
			 if(responseLine.startsWith("<"))
				 publish(responseLine);
			else if(responseLine.equals("round")){
				list=null;
	    		 break;
			}
			 else if(responseLine.equals("end"))
                 break;
			else if(responseLine.equals("Bye")){
				list.clear();
				list.add(responseLine);
				break;
			}
			else if(!responseLine.startsWith("<"))
		    	 	list.add(responseLine);
		 }
		return list;
	}
	
}
}


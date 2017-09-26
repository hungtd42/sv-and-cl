/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jframe;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author tranhung2027
 */
public class sever extends JFrame{
    static ServerSocket ss;
    static Socket s;
    static DataInputStream dis;
    static DataOutputStream dos;
     static JTextArea txtChat=new JTextArea();
     static JTextField txtEnter=new JTextField();
     static JButton btn=new JButton("Send");
     
     
    public sever(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setVisible(true);
        this.setResizable(false);
        this.setLayout(null);
        this.setTitle("Sever");
        
        //create form
        txtEnter.setLocation(2,540);
        txtEnter.setSize(550,30);
        txtEnter.setBackground(new Color(186,194,219));
        txtEnter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //String sss=txtChat.getText();
              
//               txtChat.append("you: "+sss+"\n");
//                if (sss.contains("hi")) {
//                    botR("hello");
//                }
                try{
                String send="";
                send=txtEnter.getText().trim();//
                dos.writeUTF(send);
                }catch(Exception ex){}
                
                txtEnter.setText("");
            }
        });
        
        
        //form chat
        txtChat.setLocation(20, 15);
        txtChat.setSize(550, 500);
        txtChat.setBackground(new Color(186,194,219));
        
        
        //form button
//        btn.setLocation(540, 520);
//        btn.setSize(40,30);
//        btn.setBackground(new Color(186,194,219));
        //send client
        
//        try{
//            String btnSend="";
//            btnSend=btn.getText().trim();
//            dos.writeUTF(btnSend);
//        }catch(Exception e){}
        
        this.add(txtEnter);
        this.add(txtChat);
//        this.add(btn);
        
    }
//    public void botR(String s){
//        txtChat.append("sever: "+s+"\n");
//    }
    public static void main (String args[]){
        new sever();
        String str="";
        try{
            ss=new ServerSocket(1200);
            s=ss.accept();
            dis=new DataInputStream(s.getInputStream());
            dos=new DataOutputStream(s.getOutputStream());
            while(!str.equals("exit")){
                str=dis.readUTF();
                txtChat.setText(txtChat.getText().trim()+"\n Client: \t"+str);
                //receive data from client and send form chat
            }
        }catch(Exception e){}
    }
    
}

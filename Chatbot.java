
package chatbot;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Chatbot extends JFrame implements KeyListener{

    JPanel jp=new JPanel();
    JTextArea dialog = new JTextArea(20,50);
    JTextArea input = new JTextArea(1,50);
    JScrollPane scroll = new JScrollPane(
            dialog,
            JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
    );
    
    
    String [][] chat={
        {"hi","hello","hey"},
        {"hi","hello"},
        
        {"how are you","how r you","how r y"},
        {"good","thank good"},
        
        {"do you want something eat"},
        {"thank ! i have"},
        
        {"what is you name"},
        {"my name is bot","people call me Bot"},
        
        {"yes"},
        {"no","NO"},
        //default
        {"???","Stop Stop","I don't understand what you say ??","i don't know?"}
        
    };
   public static void main(String[] args) {
        new Chatbot();
    }
   public Chatbot(){
       super("Chat bot");
       
       setSize(600,400);
       setResizable(false);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       
       dialog.setEditable(false);
       input.addKeyListener(this);
       
       jp.add(scroll);
       jp.add(input);
       jp.setBackground(new Color(255,200,0));
       add(jp);
       
       setVisible(true);
   }
    
    

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println("chatbot.Chatbot.keyPressed()");
        if (e.getKeyCode()==KeyEvent.VK_ENTER) {//getKeyCode -> chọn phim dc xác định 
            input.setEditable(false); 
            String quote=input.getText();
            input.setText("");//dua chuoi vao 
            addText("You :\t"+quote);//hien thi
            quote.trim();
            while(
              quote.charAt(quote.length()-1) == '!'||
              quote.charAt(quote.length()-1) == '.'||
              quote.charAt(quote.length()-1) == '?'
            ){
                quote=quote.substring(0,quote.length()-1);//trả về chuỗi mới bắt đầu từ 0 đến length-1 . hi!->hi
            }
            quote.trim();                                //trim. bỏ qua các khoảng trắng
            byte response=0;
            /*
		0:we're searching through chatBot[][] for matches
		1:we didn't find anything
		2:we did find something
            */
            int j=0;
            while(response==0){
                if (inArray(quote.toLowerCase(), chat[j*2])) {  //tolowercase chuyen ky tu in hoa khi nhap vao sang chu thuong hợp với dk
                    response=2;
                    int r=(int)Math.floor(Math.random()*chat[(j*2)+1].length);//r nhan gia tri random {} [][j] thu 2 va length
                    addText("\n Bot: \t"+chat[(j*2)+1][r]);
                }
                j++;
                if (j*2==chat.length-1 && response==0) {//neu k tim thay ky tu khi nhap vao
                    response=1;
                }
            }
            //default
            if (response==1) {
                int r=(int)Math.floor(Math.random()*chat[chat.length-1].length);
                addText("\n Bot : \t"+chat[chat.length-1][r]);
            }
            addText("\n");
        }
    }

   @Override
    public void keyReleased(KeyEvent e) {
        //System.out.println("chatbot.Chatbot.keyReleased()");
        if (e.getKeyCode()==KeyEvent.VK_ENTER) {
            input.setEditable(true);
        }
    }
    
    //  check data from input to dialog
    public void addText(String str){
        dialog.setText(dialog.getText()+str);
    }
    
    public boolean inArray(String in ,String[]str){
        boolean match=false;
        for (int i = 0; i < str.length; i++) {
            if (str[i].equals(in)) {//so sanh nen bang nhau
                match=true;
            }
        }
        return match;
    }
    
}

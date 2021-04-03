import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author ooo
 */
public class Caeser {
    private JFrame frame;
    private JLabel l1 , l2 , l3;
    private JPanel panel , panel2 ,panel3;
    private JButton b1 , b2 ;
    private JTextField tf1 , tf2;
    private JFileChooser fc;
    public static void main(String args[]){
        Caeser c = new Caeser();
    }
    
    public Caeser(){
        prepare();
    }
    //GUI DESIGN
    private void prepare(){
        frame = new JFrame("caeser algorithim");
        frame.setSize(400,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(3,1));
        frame.setVisible(true);
        l1 = new JLabel("Text",JLabel.CENTER);
        l1.setOpaque(true);
        l1.setBackground(Color.BLACK);
        l1.setForeground(Color.WHITE);
        l2 = new JLabel("Key",JLabel.CENTER);
        l2.setOpaque(true);
        l2.setBackground(Color.BLACK);
        l2.setForeground(Color.WHITE);
        l3 = new JLabel("",JLabel.CENTER);
        b1 = new JButton("Encrypt");
        b2 = new JButton("Decrypt");
        tf1 = new JTextField(10);
        tf2 = new JTextField(10);
        panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.GRAY);
        panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        panel2.setBackground(Color.GRAY);
        panel3 = new JPanel();
        panel3.setLayout(new FlowLayout());
        panel3.setBackground(Color.GRAY);
        fc = new JFileChooser();
        b1.addActionListener((ActionEvent e)->{
            try{
                String s = tf1.getText(),s1 = tf2.getText();
                if(s.length()>0){
                    l3.setText(cipherText(s,Integer.parseInt(s1)));
                }else{
                    s="";
                    int approve = fc.showOpenDialog(frame);
                    if(approve == JFileChooser.APPROVE_OPTION){
                    File  f = fc.getSelectedFile();
                    try{
                    FileInputStream fis = new FileInputStream(f);
                    int c;
                    while((c=fis.read())!= -1){
                        s+=(char)c;
                    }
                    
                    }catch(IOException w){
                        l3.setText(w.toString());
                    }
                    Thread t = new Thread();
                    try{
                        t.sleep(1000);
                    }catch(InterruptedException ez){
                        ez.printStackTrace();
                    }
                    int ap = fc.showOpenDialog(frame);
                    if(ap == JFileChooser.APPROVE_OPTION){
                    File f2 = fc.getSelectedFile();
                    DataOutputStream dos = new DataOutputStream(new FileOutputStream(f2));
                    dos.writeBytes(cipherText(s,Integer.parseInt(s1)));
                    //l3.setText(cipherText(s,s1));
                    }
                    }
                }
                l3.setOpaque(true);
                l3.setBackground(Color.BLACK);
                l3.setForeground(Color.WHITE);
                frame.setVisible(true);
            }catch(Exception ex){
                l3.setText(ex.toString());
                l3.setOpaque(true);
                l3.setBackground(Color.BLACK);
                l3.setForeground(Color.WHITE);
                frame.setVisible(true);
            }
        });
        b2.addActionListener((ActionEvent e)->{
            try{
                String s = tf1.getText(),s1 = tf2.getText();
                if(s.length()>0){
                    l3.setText(plainText(s,Integer.parseInt(s1)));
                }else{
                    s="";
                    int approve = fc.showOpenDialog(frame);
                    if(approve == JFileChooser.APPROVE_OPTION){
                    File  f = fc.getSelectedFile();
                    try{
                    FileInputStream fis = new FileInputStream(f);
                    int c;
                    while((c=fis.read())!= -1){
                        s+=(char)c;
                    }
                    
                    }catch(IOException w){
                        l3.setText(w.toString());
                    }
                    Thread t = new Thread();
                    try{
                        t.sleep(1000);
                    }catch(InterruptedException ez){
                        ez.printStackTrace();
                    }
                    int ap = fc.showOpenDialog(frame);
                    if(ap == JFileChooser.APPROVE_OPTION){
                    File f2 = fc.getSelectedFile();
                    DataOutputStream dos = new DataOutputStream(new FileOutputStream(f2));
                    dos.writeBytes(plainText(s,Integer.parseInt(s1)));
                    //l3.setText(cipherText(s,s1));
                    }
                    }
                }
                l3.setOpaque(true);
                l3.setBackground(Color.BLACK);
                l3.setForeground(Color.WHITE);
                frame.setVisible(true);
            }catch(Exception ex){
                l3.setText(ex.toString());
                l3.setOpaque(true);
                l3.setBackground(Color.BLACK);
                l3.setForeground(Color.WHITE);
                frame.setVisible(true);
            }
        });
        panel.add(l1);
        panel.add(tf1);
        panel.add(l2);
        panel.add(tf2);
        panel2.add(b1);
        panel2.add(b2);
        panel3.add(l3);
        frame.add(panel);
        frame.add(panel2);
        frame.add(panel3);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setVisible(true);
    }
    //ENCRYPTION METHOD
    public  String cipherText(String plainText , int key){
        String cipher = "";
        Vector v = new Vector();
        char [] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        for(int i = 0; i < alphabet.length; i++){
            v.add(alphabet[i]);
        }
        for(int j=0; j<plainText.length(); j++){
            char c = plainText.charAt(j);
            if((v.indexOf(c)+key)<=25)
                cipher += v.get(v.indexOf(c)+key);
            else
                cipher += v.get((v.indexOf(c)+key)-26);
        }
        return cipher;
    }
    //DECRYPTION METHOD
    public  String plainText(String cipherText , int key){
        String plain = "";
        Vector v = new Vector();
        char [] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        for(int i = 0; i < alphabet.length; i++){
            v.add(alphabet[i]);
        }
        for(int j=0; j<cipherText.length(); j++){
            char c = cipherText.charAt(j);
            if((v.indexOf(c)-key)>=0)
                plain += v.get((v.indexOf(c)-key));
            else
                plain += v.get((v.indexOf(c)-key)+26);
        }
        return plain;
    }
    
}

import imagedalgo.decrypt;
import imagedalgo.dalgo;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.File;
import java.io.IOException;
class GUIDalgo {
    public static void main(String args[]) throws IOException {
        JFrame frame = new JFrame("IMAGE DECRYPTION");
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(3);

        JLabel main_heading = new JLabel("BTP_DNA DECRYPTION");
        frame.add(main_heading);
        main_heading.setBounds(170, 10, 500, 100);
        main_heading.setFont(new Font("Serif", Font.PLAIN, 35));

        File directoryPath = new File("C:\\Users\\ASHOK REDDY\\miniproject");
        String contents[] = directoryPath.list();

        JLabel FileName = new JLabel("File Name : ");
        frame.add(FileName);
        FileName.setBounds(150, 650, 150, 40);

        JTextField FileName_Enter = new JTextField(30);
        frame.add(FileName_Enter);
        FileName_Enter.setBounds(250, 650, 150, 40);

        JRadioButton rb1 =new JRadioButton("Text");
        JRadioButton rb2=new JRadioButton("image");
        ButtonGroup bg=new ButtonGroup();
        bg.add(rb1);
        bg.add(rb2);
        frame.add(rb1);
        frame.add(rb2);
        rb1.setBounds(500 , 350, 220  , 40);
        rb2.setBounds(500 , 400, 220  , 40);


        JList<String> list = new JList<String>(contents);
        frame.add(list);
        JScrollPane jsp =new JScrollPane(list);
        frame.add(jsp);
        jsp.setBounds(50, 150, 350, 350);
        list.setBounds(50, 150, 350, 350);
        list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent lse) {
                FileName_Enter.setText(list.getSelectedValue());
            }
        });

        JLabel key = new JLabel("Key : ");
        frame.add(key);
        key.setBounds(500, 150, 220, 40);
        JTextField key_Enter = new JTextField(15);
        frame.add(key_Enter);
        key_Enter.setBounds(550, 150, 200, 40);

        JLabel Random = new JLabel("Random : ");
        frame.add(Random);
        Random.setBounds(500, 200, 220, 40);
        JTextField Random_Enter = new JTextField(15);
        frame.add(Random_Enter);
        Random_Enter.setBounds(550, 200, 200, 40);

        JButton Encrpyt = new JButton("Decrypt");
        frame.add(Encrpyt);
        Encrpyt.setBounds(450, 650, 200, 40);
        
        JLabel result=new JLabel("");
        frame.add(result);
        result.setBounds(400, 600, 400, 40);
        

        Encrpyt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

                try{
                    
                    if(rb1.isSelected()){
                        dalgo.mainert(FileName_Enter.getText(), key_Enter.getText(), Random_Enter.getText());
                        result.setText("check f.txt.txt");
                        result.setForeground(new Color(3, 252, 44));
                        
                    }
                    else if(rb2.isSelected()){
                        decrypt.mainer(FileName_Enter.getText(), Random_Enter.getText(), key_Enter.getText());
                        result.setText("DECRYPTED");
                    result.setForeground(new Color(3, 252, 44));
                        
                    }
                    

                   else{
                        result.setText("Please Select the file type....");
                    result.setForeground(new Color(3, 252, 44));
                    }
                     
                     
                }
                catch(Exception e){
                    System.out.println(e);
                    result.setText("Some thing went wrong");
                    result.setForeground(new Color(242, 5, 5));
                }

            }   
        });

    }
}
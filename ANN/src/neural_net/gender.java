package neural_net;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class gender extends JFrame {

   public gender() {
      
     // JButton btn1 = new JButton("남자");
      //JButton btn2 = new JButton("여자");
      //JPanel p = new JPanel();
      


	   String []childs = {"Boy", "Girl"};
	   Object selected = JOptionPane.showInputDialog(null, "What is your child's gender?", "Gender Choice",
			   JOptionPane.QUESTION_MESSAGE, null, childs, childs[0]);
	   if(selected == null)
		   System.exit(-1);
	   else if(selected.equals("Boy"))
		   neuralNetwork.boyAndgirl = true;
	   else
		   neuralNetwork.boyAndgirl = false;
	   
	   
      // 버튼의 이벤트 처리를 위한 익명클래스

      setLocation(100, 100);
      setVisible(true);

   }
 /* public static void main(String[] args) {
       // TODO Auto-generated method stub
           new gender();
   }*/

}
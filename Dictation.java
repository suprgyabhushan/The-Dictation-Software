import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;
import java.util.ArrayList;
import java.io.IOException;
/**
 * Encrypts and Decrypts text using the Caesar Cihper algorithm.
 * @author Invisible Computer, JTN
 *
 */
public class Dictation extends JFrame implements ActionListener {
 
        private static final long serialVersionUID = 1L;
        private static String alphabet = "abcdefghijklmnopqrstuvwxyz";
        private JTextField shiftFactor;
        public JTextField value;
	public JTextField value2;
        private JTextField value1;
	private int count=0;
        public JTextArea inputTA;
        private JTextArea outputTA;
	private JTextArea inputTA1;
        private JTextArea outputTA1;
        private Scanner scanner;
        JButton btn, btn2, b,readButton,reset,exe;
        AudioClip click;
	AudioClip click1,click2,click3,click4;
        private Timer timer;
	public int random=0;
	public int i=0;
        public int lno=0;
	public int prevlno=0;
	public int checkv=0;
	ArrayList<String> data=new ArrayList<String>();
	public int flag=0;
	public int index=0;
	public Thread t;
 
        /**
         * @param args
         */
        public static void main(String[] args) {
                new Dictation().setVisible(true);

        }
       
        public void encryptText() throws InterruptedException {
                //Create a HashMap
                //A hash map takes keys and values, which are both Characters in this case.
                HashMap<Character, Character> alphaMap = new HashMap<Character, Character>();
                int shift;
                //Get the text from the app and store it in a String variable.
                String textNum = this.shiftFactor.getText();
                //Check to see if a "Shift Factor" value was entered.
                //If there wasn't, set shift to zero,
                //Otherwise parse the input value to an integer so we can use it.
                if(!textNum.equals("")){
                        shift = Integer.parseInt(textNum)%26;
                }
                else{
                        shift = 0;
                }
                //Map every letter of the alphabet to another letter in the alphabet, shifted by x places.
                for(int i=0; i<alphabet.length(); i++){
                        alphaMap.put(alphabet.charAt(i), alphabet.charAt((i+shift)%26));
                }
                //Get input text and put it all to lower-case so it's easy to convert
                String inputText = inputTA1.getText().toLowerCase();
                String outputText = "";
                //Go to each letter and switch it with it's shifted counterpart
                for(int j=0; j<inputText.length(); j++){
                        outputText = outputText.concat(alphaMap.get(inputText.charAt(j)).toString());
                }
                //Output the encrypted text
                outputTA1.setText(outputText);
        }
       
        public void decryptText() throws InterruptedException{
                HashMap<Character, Character> alphaMap = new HashMap<Character, Character>();
                int shift;
                String textNum = this.shiftFactor.getText();
                if(!textNum.equals("")){
                        shift = Integer.parseInt(textNum)%26;
                }
                else{
                        shift = 0;
                }
                for(int i=0; i<alphabet.length(); i++){
                        alphaMap.put(alphabet.charAt((i+shift)%26), alphabet.charAt(i));
                }
                String inputText = inputTA1.getText().toLowerCase();
                String outputText = "";
                for(int j=0; j<inputText.length(); j++){
                        outputText = outputText.concat(alphaMap.get(inputText.charAt(j)).toString());
                }
                outputTA1.setText(outputText);
        }
       // public void check() throws InterruptedException, FileNotFoundException{
                //HashMap<Character, Character> alphaMap = new HashMap<Character, Character>();
                //int v;
                //v=Integer.parseInt(this.value.getText());
         /*        String s = this.value.getText();
              File text = new File("speech.txt");
     
        //Creating Scanner instnace to read File in Java
        Scanner scnr = new Scanner(text);
        String line = scnr.nextLine();

        //Reading each line of file using Scanner class
        //int lineNumber = 1;
        while(scnr.hasNextLine()){
           line = scnr.nextLine();
             s = this.value.getText();
           if(s.equals(line)){
                 outputTA.setText("Yess");}
                 else{
                  outputTA.setText("Nooo");
                 }
           
            
         // System.out.println("line " + lineNumber + " :" + line);
           //lineNumber++;
        }     

   

//Read more: http://java67.blogspot.com/2012/11/how-to-read-file-in-java-using-scanner-example.html#ixzz3sZb28trb

                //String textNum1 = this.value.getText();
                /*if(!textNum1.equals("")){
                        v = Integer.parseInt(textNum1)%26;
                }
                else{
                        v = 1;
                }*/
                /*for(int i=0; i<alphabet.length(); i++){
                        alphaMap.put(alphabet.charAt((i+shift)%26), alphabet.charAt(i));
                }*/
                //String inputText = inputTA.getText().toLowerCase();
               
                /*for(int j=0; j<inputText.length(); j++){
                        outputText = outputText.concat(alphaMap.get(inputText.charAt(j)).toString());
                }*/
                
              
        //}

       
        public Dictation(){
            setTitle("Dictation Software");
            setVisible(true);
            setDefaultCloseOperation(3);
 
            Container content = getContentPane();
            GridLayout layout = new GridLayout(3, 0, 0, 10);
            content.setLayout(layout);
 
            inputTA = new JTextArea("", 12, 40);
            inputTA.setLineWrap(true);
            inputTA.setWrapStyleWord(true);
            inputTA.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
            JScrollPane scroller = new JScrollPane(inputTA);
            scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            content.add(scroller);

            inputTA1 = new JTextArea("", 12, 40);
            inputTA1.setLineWrap(true);
            inputTA1.setWrapStyleWord(true);
            inputTA1.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
            JScrollPane scroller1 = new JScrollPane(inputTA1);
            scroller1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            content.add(scroller1);
           
            outputTA = new JTextArea("",12, 40);
            outputTA.setLineWrap(true);
            outputTA.setWrapStyleWord(true);
            outputTA.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
            JScrollPane scroller2 = new JScrollPane(outputTA);
            scroller2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            content.add(scroller2);

            outputTA1 = new JTextArea("", 12, 40);
            outputTA1.setLineWrap(true);
            outputTA1.setWrapStyleWord(true);
            outputTA1.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
            JScrollPane scroller3 = new JScrollPane(outputTA1);
            scroller3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            content.add(scroller3);

	    JPanel box2 = new JPanel();
	    box2.setLayout(new FlowLayout());
            readButton = new JButton("Check");
	    
            box2.add(readButton);
	    
            content.add(box2);
	inputTA.addKeyListener(new KeyListener(){
    @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
	e.consume();
	checkv=1;
	random=1;
        readButton.doClick();
        }
	//else
	//	random=0;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    });

    readButton.addActionListener(new ActionListener() {
                       public void actionPerformed(ActionEvent e) {
			//random=0;
                        String str = "";
			random=1;
			checkv=1;
			if(flag==0)
				{
                               	while(scanner.hasNext()){
					
                                       	//String s = inputTA.getText();
					str=scanner.next();
					data.add(str);
                                       	//if(s.equals(str)){
					//count=count+1;
                                        //outputTA.append("Yesss "+"\n");
                                        //}
                                        //else{
                                        //  outputTA.append("Nooo " + "The correct spelling is " + str + "\n");
                                        //}
	                                //}
	                        }
				flag=1;
				scanner.close();
					
				//outputTA.append("Score is " + count + "\n");
				//random=0;
                        	}
				
					
					if(index!=data.size())                                        	
						{
							String s = inputTA.getText();
							//str=scanner.next();
							str=data.get(index);
							//data.add(str);
                                        		if(s.equals(str)){
							  count=count+1;
                                        		  outputTA.append("Yesss "+"\n");
                                        		}
                                        		else{
                                        		  outputTA.append("Nooo " + "The correct spelling is " + str + "\n");
                                        		}
							index++;
						}
					outputTA.append("Score is " + count + "\n");
					
					timer = new Timer(1000, new ActionListener() {
                			@Override
                			public void actionPerformed(ActionEvent e) {
                        		b.doClick();
                        		timer.stop();
                    			}
                		});
                			timer.start();

					
			}
                });
            try {
                        scanner = new Scanner(new File("speech.txt"));
                        scanner.useDelimiter("[,\\|]|"+System.getProperty("line.separator"));
                } catch (FileNotFoundException e1) {
                        // Auto-generated catch block
                        e1.printStackTrace();
                }
	   btn = new JButton("Play");
	   //int random=0;
           //btn2 = new JButton("Stop");
           box2.add(btn);
           content.add(box2);
	   b = new JButton("Clear");
           box2.add(b);
           content.add(box2);
	   reset = new JButton("Reset");
	   box2.add(reset);
           content.add(box2);
	   JButton exit = new JButton("Exit");
	   box2.add(exit);
           content.add(box2);
           /*exe = new JButton("Execute");
	   box2.add(exe);
           content.add(box2);*/
	   //box2.add(new JLabel("Enter No. of Users : "));
           //box2.add(this.value2 = new JTextField(20));
           //content.add(box2);  
           exit.addActionListener(new ActionListener(){
	   public void actionPerformed(ActionEvent e){
	   System.exit(0);
           }
	   });
	   reset.addActionListener(new ActionListener(){
	   public void actionPerformed(ActionEvent e){
	   outputTA.setText("");
	   inputTA.setText("");
	   index=0;
	   flag=1;
	   count=0;
	   t.interrupt();
		
	   //scanner.close();
	   /*try {
                        scanner = new Scanner(new File("speech1.txt"));
                        scanner.useDelimiter("[,\\|]|"+System.getProperty("line.separator"));
                } catch (FileNotFoundException e1) {
                        // Auto-generated catch block
                        e1.printStackTrace();
                }*/
           }
	   });
           b.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
           inputTA.setText("");
           //textfield.setText(null); //or use this
           } 
           });
           /*Timer timer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b.doClick();
            }
        });*/
           //box2.add(btn2);
           //content.add(box2);
           btn.addActionListener(this);
           //btn2.addActionListener(this); 
           content.add(box2);          
            JPanel box1 = new JPanel();
            box1.setLayout(new FlowLayout());
            JButton decryptButton = new JButton("Decrypt");
            JButton encryptButton = new JButton("Encrypt");
            JButton resetButton = new JButton("Reset");
            //JButton multiplyButton = new JButton("Check");
            
	    //JButton x = new JButton("x");
            decryptButton.addActionListener(this);
            encryptButton.addActionListener(this);
            resetButton.addActionListener(this);
            //multiplyButton.addActionListener(this);
            //box1.add(x);
            box1.add(decryptButton);
            box1.add(encryptButton);
            //box1.add(multiplyButton);
            //box1.add(readButton);
            box1.add(new JLabel("Shift Factor"));
            box1.add(this.shiftFactor = new JTextField(20));
            content.add(box1);
            /*box1.add(new JLabel("Value"));
            box1.add(this.value = new JTextField(20));
            content.add(box1);*/
            
          
           
           
            pack();
        }
 
        @Override
        public void actionPerformed(ActionEvent e) {
        	if(e.getActionCommand().equals("Encrypt")){
                        try{
                                encryptText();
                        }
                        catch(InterruptedException e1){
                                e1.printStackTrace();
                        }
                }
                /*if(e.getActionCommand().equals("Check")){
                       try{
                              check();
                        }
                       catch(InterruptedException e1){
                          e1.printStackTrace();
                        }
                        catch(FileNotFoundException e2){
                          e2.printStackTrace();i
                        }
                }*/
                /*if (e.getActionCommand().equals("Execute"))
		{
			try {
            Process p = Runtime.getRuntime().exec("./script");
        } catch (IOException e1) {
            //Auto-generated catch block
            e1.printStackTrace();
        }
}*/
			
			
                if (e.getSource() == btn) {
                        t = new Thread(new Runnable(){
                        	public void run(){
                                	try{
                                        	URL urlClick = Dictation.class.getResource("example1.wav");
						URL urlClick1 = Dictation.class.getResource("example2.wav");
						URL urlClick3 = Dictation.class.getResource("example3.wav");
						URL urlClick4 = Dictation.class.getResource("example4.wav");

            					click = Applet.newAudioClip(urlClick);
						click1 = Applet.newAudioClip(urlClick1);
						click3 = Applet.newAudioClip(urlClick3);
						click4 = Applet.newAudioClip(urlClick4);
						
						click.play();
						Thread.sleep(6000);
						if(checkv==1)
						{
							checkv=0;
							random=0;
						}
						else if(checkv==0)
						{
							Thread.sleep(4000);
							if(random==0)
							{
								//lno=1;
								readButton.doClick();
								//outputTA.append("Score is " + count + "\n");
								//random=0;
							}
							else if(random==1)
							{
								//lno=1;
								random=0;
								//prevlno=lno;
							}
						}
						Thread.sleep(2000);
						random=0;
						checkv=0;
						click1.play();
						Thread.sleep(6000);
						if(checkv==1)
						{
							checkv=0;
							random=0;
						}
						else if(checkv==0)
						{
							Thread.sleep(4000);
							if(random==0)
							{
								//lno=1;
								readButton.doClick();
								//outputTA.append("Score is " + count + "\n");
								//random=0;
							}
							else if(random==1)
							{
								//lno=1;
								random=0;
								//prevlno=lno;
							}
						}
						Thread.sleep(2000);
						checkv=0;
						random=0;
						click3.play();
						Thread.sleep(6000);
						if(checkv==1)
						{
							checkv=0;
							random=0;
						}
						else if(checkv==0)
						{
							Thread.sleep(4000);
							if(random==0)
							{
								//lno=1;
								readButton.doClick();
								//outputTA.append("Score is " + count + "\n");
								//random=0;
							}
							else if(random==1)
							{
								//lno=1;
								random=0;
								//prevlno=lno;
							}
						}
						Thread.sleep(2000);
						checkv=0;
						random=0;
						click4.play(); 
						Thread.sleep(6000);
						if(checkv==1)
						{
							checkv=0;
							random=0;
						}
						else if(checkv==0)
						{
							Thread.sleep(4000);
							if(random==0)
							{
								//lno=1;
								readButton.doClick();
								//outputTA.append("Score is " + count + "\n");
								//random=0;
							}
							else if(random==1)
							{
								//lno=1;
								random=0;
								//prevlno=lno;
							}
						}
						//scanner.close();
						checkv=0;
						random=0;
						Thread.sleep(1000);
						outputTA.append("Press on Reset to reset or Exit to exit the software");
					}
					catch (InterruptedException e1) {
        					Thread.currentThread().interrupt();
        					e1.printStackTrace();
    					}    
		}

	});
	t.start();
}	

 
        /*if (e.getSource() == reset) {
outputTA.setText("");
            click.stop();
		click1.stop();
click3.stop();
click4.stop();
        }*/
                
                if (e.getActionCommand().equals("Decrypt")){
                      try {
                        decryptText();
                      } catch (InterruptedException e1) {
                        e1.printStackTrace();
                      }
        }
}
}

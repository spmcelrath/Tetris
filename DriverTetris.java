// Andrew Freix, Zach Hosseinipour, and Sean McElrath

   import javax.swing.JFrame;
    public class DriverTetris
   {
	/*******************************************************
	* This main method plays the music and runs TetrisPanel. 
	********************************************************/
       public static void main(String[] args) 
      { 
   		MusicTest mt = new MusicTest();
			mt.initMusic();
			mt.playMusic();      
			JFrame frame = new JFrame("Final Project: Tetris");
         frame.setSize(1250, 770);
         frame.setLocation(0, 0);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       	frame.setContentPane(new TetrisPanel());
         frame.setVisible(true);
		
      }
	}
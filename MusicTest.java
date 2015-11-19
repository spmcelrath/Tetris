//Zach Hosseinipour, Andrew Freix, and Sean McElrath
//Import mp3 library
   import javazoom.jl.player.*;
   import java.io.*;
    public class MusicTest {
   
      public InputStream gamemusic; // the file which the music streams from
      public Player player; // the object that plays the music
      public PlayerThread pt; // a separate thread so music plays independently
   /**************************************************************
	* A new player object is made to play the music as well as a 
	*new player thread. The player thread then starts, and if 
	*there is an error, it catches it and tells the user the error
	*message. Credits to Dan Johnson for aiding us.
	**************************************************************/
       public void playMusic() {
         try {
            player = new Player(gamemusic); // a new player object to play the music
            pt = new PlayerThread(); // new player thread
            pt.start(); // starts the thread (think timer.start())
         }
             catch (Exception e) {
               System.out.println("Music failed to play");
            } // prints error if it fails
      }
		/**************************************************************
	   * It makes sure the file exists, otherwise it prints an error
	   **************************************************************/
       public void initMusic() {
         try {
            gamemusic = new FileInputStream("Techno Music Mix.mp3");
         }
         // makes sure the file exists and sets that as the source of the music
             catch (Exception e) {
               System.out.println(e);
               System.out.println("Music failed to load");
            } // otherwise prints an error
      }
       public class PlayerThread extends Thread {
		 /**************************************************************
	   * Uses player to play the music, and displays an error if 
		*the thread fails.
	   **************************************************************/
          public void run() {
            try {
               player.play();
            } // plays the music
                catch (Exception e) {
                  System.out.println("PlayerThread error");
               } // error if it fails
         }
      } // extends thread
   }

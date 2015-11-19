 //Zach Hosseinipour Sean McElrath and Andrew Freix
   import javax.swing.*;
   import java.awt.*;
   import java.awt.event.*;
   import java.awt.image.*;
   import java.io.*;
   import java.util.Scanner;
	/**************************************************************************
	*TetrisPanel is a panel that brings everything together to successfully run Tetris.
	*It uses all the methods we made. Credits to http://gamesexcel.com/games-excel-tetris.html
	*for the image icon for Tetris
	*@author Zach Hosseinipour, Andrew Friex, and Sean McElrath (Roses)
   ****************************************************************************/
    public class TetrisPanel extends JPanel 
   {
      private BufferedImage myImage;
      private Graphics myBuffer;
      private JButton button, button1;
      private JLabel gameboard, scoreboardPoints, scoreboard, next, heading; 
      private Shape[] shapesEasy;
      private int countCheckRow = 0;
      private int moveCount = 0; 
      private int countPiece = 0;
      private int start = 0;
      private int points = 0;
      private int level = 1;
      private int highscore = 0;
      private int row;
      private String highest = "";
      private String name = "";
      private Scanner infile;
      private Color purple = new Color(102, 0, 204);
      private Color orange = new Color(252, 152, 0);
      public Timer t = new Timer(100, new Listener());
      final int LEFT = 0, RIGHT = 1, SPACE = 2, DOWN = 3;
      boolean[] keys = new boolean[4];
      final int XSIZE = 300;
      final int YSIZE = 600;
      final int SIDE = 30;
   	
      private int score = 0;
      private int count = 0;
   
       public TetrisPanel() 
      {	
         setLayout(new BorderLayout());
      	
         myImage =  new BufferedImage(XSIZE, YSIZE, BufferedImage.TYPE_INT_RGB);
        
         JPanel subpanel = new JPanel();
         add(subpanel, BorderLayout.CENTER);
         subpanel.setBackground(new Color(0,153,255));
      	
         JPanel north = new JPanel();
         north.setBackground(new Color(0,153,255));
         add(north,BorderLayout.NORTH);
         JLabel label = new JLabel("");
         north.add(label);
         label.setIcon(new ImageIcon("tetris.gif"));
      	
         JPanel east = new JPanel();
         east.setLayout(new GridLayout(2, 1));
         east.setBackground(new Color(0,153,255));
         add(east,BorderLayout.EAST);
      	
         scoreboardPoints = new JLabel("");
         scoreboardPoints.setForeground(Color.BLUE);
         scoreboardPoints.setFont(new Font("Serif", Font.BOLD, 50));
         east.add(scoreboardPoints, BorderLayout.EAST);
      	
         JPanel west = new JPanel();
         west.setBackground(new Color(0,153,255));
         west.setLayout(new GridLayout(3,1));
         add(west, BorderLayout.WEST);
         next = new JLabel();
         heading = new JLabel();
         heading.setFont(new Font("Serif", Font.BOLD, 50));
         west.add(heading);
         west.add(next);
        
         
         gameboard = new JLabel();
         gameboard.setIcon(new ImageIcon((Image)myImage));
         subpanel.add(gameboard);
      	
         JPanel south = new JPanel();
         south.setBackground(new Color(0,153,255));
      	 
         add(south, BorderLayout.SOUTH);
      
         button = new JButton("START");
         button.addActionListener(new Listener());
         button.setBackground(Color.BLUE);
         button.setForeground(Color.GREEN);
         south.add(button);
      
      
         scoreboard = new JLabel("");
         scoreboard.setForeground(Color.BLUE);
         scoreboard.setFont(new Font("Serif", Font.BOLD, 40));
      
         south.add(scoreboard);
      	
         button1 = new JButton("QUIT");
         button1.addActionListener(new Listener1());
         button1.setBackground(Color.RED);
         button1.setForeground(Color.YELLOW);
      
         south.add(button1);
      	
      	
         shapesEasy = new Shape[60];
      	
         addKeyListener(new KeyListener());
         setFocusable(true);
        
         for(int x = 0; x < shapesEasy.length; x++)
         {
            int create = (int)(Math.random() * 7 + 1);
            switch(create)
            {
               case 1: shapesEasy[x] = new Shape(150, -30, 30, 30, Color.YELLOW, 0, false); //Fills one slot with a new shape object, which looks like a square.  
                  break;
               case 2: shapesEasy[x] = new Shape(150, -30, 30, 30, Color.CYAN, 1, false); //Fills one slot with a new shape object, which looks like a rectangle.    
                  break;
               case 3: shapesEasy[x] = new Shape(150, -30,  30, 30, Color.BLUE, 2, false); //Fills one slot with a new shape object, which looks like a leftL.  
                  break;
               case 4: shapesEasy[x] = new Shape(150, -30,  30, 30, orange, 3, false); //Fills one slot with a new shape object, which looks like a rightL.  
                  break;
               case 5: shapesEasy[x] = new Shape(150, -30,  30, 30, Color.GREEN, 4, false); //Fills one slot with a new shape object, which looks like a leftZ.  
                  break;
               case 6: shapesEasy[x] = new Shape(150, -30,  30, 30, Color.RED, 5, false); //Fills one slot with a new shape object, which looks like a rightZ.   
                  break;
               default: shapesEasy[x] = new Shape(150, -30, 30, 30, purple, 6, false); //Fills one slot with a new shape object, which looks like a T-shape.  
            
            
            }
         
         }
      }
   	
       private class Listener1 implements ActionListener
      {
      	/**********************************************
      	*Ends the game, corresponds to the quit button
      	*@param e	uses object e of ActionEvent
      	**********************************************/
          public void actionPerformed(ActionEvent e)
         {
            System.exit(0);
         }
      }
          
       private class Listener implements ActionListener 
      { 
          /**********************************************************
      	*Starts the game when the start button is pressed.
      	*Starts the timer, sets the quit button so you can't
      	*click it. Checks to see if the game is over, by checking
      	*if each row has a shape(which would mean you hit the top). Sets the scoreboard  
      	*whenever you score or go up a level. It sets
      	*the panel that displays next shape to whatever the next
      	*shape is(countPiece + 1). Draws the shape and updates the screen. It moves the shape
      	*once every 5 times in the loop. It does this so the timer is reactive 
      	*enough to keyboard input, but we dont want the shape to move down too fast 
      	*so we do it once every 5 times. Gives action to each of the
      	*keys and clears any rows as necessary. 
      	*@param e	uses object e of ActionEvent
      	*************************************************************/          
          public void actionPerformed(ActionEvent e) 
         {
            t.start();
            button1.setEnabled(false); 
            requestFocus();
         	
            int count = 0;
         	         
            for(int y = 0; y <= 600; y += 30)
            {
               boolean check = true;
               for(int a = 0; a < shapesEasy.length; a++)
                  for(int x = 0; x <= 300; x += 30)
                     if(check && shapesEasy[a].getDrawn() && shapesEasy[a].getDY() == 0)
                     {
                        if(shapesEasy[a].contains(x, y) > 0)
                        {
                           check = false;
                        
                        }
                        if(!check)
                           count++;
                        if(count == 20)
                        {
                           scoreboard.setText("Game Over. Your score: " + points);
                           try{
                              infile = new Scanner(new File("data.txt"));
                              highscore = infile.nextInt();
                           }
                               catch(FileNotFoundException f)
                              {
                                 JOptionPane.showMessageDialog(null, "Error: File not found.");
                                 System.exit(0);
                              }
                        
                        	
                           if(points >= highscore)
                           {  
                              int newhighscore = points;
                              highest = JOptionPane.showInputDialog("New High Score! Enter your name");
                              try
                              {            
                                 PrintStream outfile = new PrintStream(new FileOutputStream("data.txt"));
                                 outfile.println(newhighscore);
                                 outfile.println(highest);
                                 
                              }
                                  catch(FileNotFoundException g)
                                 {
                                    JOptionPane.showMessageDialog(null, "Error: File not found.");
                                    System.exit(0);
                                 }
                              name = infile.next();
                              JOptionPane.showMessageDialog(null, "Congratulations, " + highest + ", you are the new high scorer! Score: " + newhighscore);   
										count = 0;      
                           }
                           if(points < highscore)
                           {
                              name = infile.next();
                              JOptionPane.showMessageDialog(null, "Sorry, you are not the highest scorer, " + name + " is, with a score of " + highscore);
										count = 0;
                           }
                           button.setEnabled(false);
                           button1.setEnabled(true);
                           t.stop();
                        }
                     }
             
            }
            
                       
            scoreboardPoints.setText("Your score: " + points);			
            myBuffer = myImage.getGraphics();
            myBuffer.setColor(new Color(31, 32, 32));
            myBuffer.fillRect(0, 0, XSIZE,YSIZE);
            myBuffer.setColor(Color.BLACK);
         
            for(int x = 30; x < 300; x +=30)
               myBuffer.drawLine(x, 0, x, 600);
            for(int y = 30; y < 600; y+=30)
               myBuffer.drawLine(0, y, 300, y);
         
            switch(shapesEasy[countPiece + 1].getType())
            {
               case 0: next.setIcon(new ImageIcon("square.JPG"));
                  break;
               case 1: next.setIcon(new ImageIcon("long.JPG"));
                  break;
               case 2: next.setIcon(new ImageIcon("blueL.JPG"));
                  break;
               case 3: next.setIcon(new ImageIcon("orangeL.JPG"));
                  break;
               case 4: next.setIcon(new ImageIcon("greenS.JPG"));
                  break;
               case 5: next.setIcon(new ImageIcon("red2.JPG"));
                  break;
               default: next.setIcon(new ImageIcon("purpleT.JPG"));
                  break;
            
            
            
            }  
            heading.setText("Next Shape:");      
            
            for(int i = 0; i <= countPiece; i++)
            {
               shapesEasy[i].draw(myBuffer);
            }
            moveCount++; 
            
            if(shapesEasy[countPiece].stack(shapesEasy, countPiece - 1)) 
               shapesEasy[countPiece].setDY(0);
            	
            if(moveCount % 5 == 0)
               shapesEasy[countPiece].move(YSIZE);
            
               
            if(keys[RIGHT])
            {
               for(int i = 0; i < shapesEasy.length; i++)
               {
                  if(shapesEasy[i].getDY() != 0)
                  {	
                     if( shapesEasy[i].getX1() + shapesEasy[i].getDX() + SIDE <= XSIZE &&  shapesEasy[i].getX2() + shapesEasy[i].getDX() + SIDE <= XSIZE &&  shapesEasy[i].getX3() + shapesEasy[i].getDX() + SIDE <= XSIZE &&  shapesEasy[i].getX4() + shapesEasy[i].getDX() + SIDE <= XSIZE && !shapesEasy[i].nextTo(shapesEasy, i - 1, 2))
                     {
                        shapesEasy[i].setX1(shapesEasy[i].getX1() + shapesEasy[i].getDX());
                        shapesEasy[i].setX2(shapesEasy[i].getX2() + shapesEasy[i].getDX());
                        shapesEasy[i].setX3(shapesEasy[i].getX3() + shapesEasy[i].getDX());
                        shapesEasy[i].setX4(shapesEasy[i].getX4() + shapesEasy[i].getDX());
                        repaint();
                           
                     }
                  }
                     
               }
            }
            if(keys[LEFT])
            {
               for(int i = 0; i < shapesEasy.length; i++)
               {
                  if(shapesEasy[i].getDY() != 0)
                  {
                     if( shapesEasy[i].getX1() - shapesEasy[i].getDX()  >= 0 &&  shapesEasy[i].getX2() - shapesEasy[i].getDX()  >= 0 &&  shapesEasy[i].getX3() - shapesEasy[i].getDX()  >= 0 &&  shapesEasy[i].getX4() - shapesEasy[i].getDX()  >= 0 && !shapesEasy[i].nextTo(shapesEasy, i - 1, 1))
                     {
                        shapesEasy[i].setX1(shapesEasy[i].getX1() - shapesEasy[i].getDX());
                        shapesEasy[i].setX2(shapesEasy[i].getX2() - shapesEasy[i].getDX());
                        shapesEasy[i].setX3(shapesEasy[i].getX3() - shapesEasy[i].getDX());
                        shapesEasy[i].setX4(shapesEasy[i].getX4() - shapesEasy[i].getDX());
                        repaint();
                           
                     }
                  }
                     
               }
            }
            if(keys[DOWN])
            {
               for(int i = 0; i < shapesEasy.length; i++)
               {
                  if(shapesEasy[i].getDrawn())
                  {
                     if(!shapesEasy[i].stack(shapesEasy, i -1) && shapesEasy[i].getY1() + shapesEasy[i].getDY() != 570 && shapesEasy[i].getY2() + shapesEasy[i].getDY() != 570 && shapesEasy[i].getY3() + shapesEasy[i].getDY() != 570 && shapesEasy[i].getY4() + shapesEasy[i].getDY() != 570)
                     {
                        shapesEasy[i].setY1(shapesEasy[i].getY1() + shapesEasy[i].getDY());
                        shapesEasy[i].setY2(shapesEasy[i].getY2() + shapesEasy[i].getDY());
                        shapesEasy[i].setY3(shapesEasy[i].getY3() + shapesEasy[i].getDY());
                        shapesEasy[i].setY4(shapesEasy[i].getY4() + shapesEasy[i].getDY());
                     }
                    
                  }
                  repaint();
                  
               }
            }
            
            
            if(keys[SPACE])
            {
               for(int i = 0; i < shapesEasy.length; i++)
               {
                  if(shapesEasy[i].getDY() != 0)
                     shapesEasy[i].rotate(shapesEasy, i); 
                  repaint();
               }
            }
         
            clearRow(shapesEasy);
            if(shapesEasy[countPiece].stop())
            {
               countPiece++;
               if(countPiece % 10 == 0)
               {
                  level++;
                  t.setDelay(t.getDelay() - 10);
                  scoreboard.setText("Level " + level + " a little faster!"); 
               }
            
               if(countPiece == shapesEasy.length-1)
               {
                  scoreboard.setText(" You survived! Your final score was: " + points); 
                  try{
                     infile = new Scanner(new File("data.txt"));
                     highscore = infile.nextInt();
                  }
                      catch(FileNotFoundException f)
                     {
                        JOptionPane.showMessageDialog(null, "Error: File not found.");
                        System.exit(0);
                     }
                        
                        	
                  if(points >= highscore)
                  {  
                     int newhighscore = points;
                     highest = JOptionPane.showInputDialog("New High Score! Enter your name");
                     try
                     {            
                        PrintStream outfile = new PrintStream(new FileOutputStream("data.txt"));
                        outfile.println(newhighscore);
                        outfile.println(highest);
                                 
                     }
                         catch(FileNotFoundException g)
                        {
                           JOptionPane.showMessageDialog(null, "Error: File not found.");
                           System.exit(0);
                        }
                     name = infile.next();
                     JOptionPane.showMessageDialog(null, "Congratulations, " + highest + ", you are the new high scorer! Score: " + newhighscore); 
							count = 0;        
                  }
                  if(points < highscore)
                  {
                     name = infile.next();   
                     JOptionPane.showMessageDialog(null, "Sorry, you are not the highest scorer, " + name + " is, with a score of " + highscore);
							count = 0;
                  }
                  button1.setEnabled(true);
                  t.stop();
               }
            }
         
            			
            repaint();
         }
      }
      
       public class KeyListener extends KeyAdapter
      {
          /**********************************************************
      	*If any of the 3 keys are pressed, it sets the boolean for the
      	*key to true, if it is held.
      	*@param e	uses object e of KeyEvent
      	*************************************************************/
          public void keyPressed(KeyEvent e)
         {
            if(e.getKeyCode() == KeyEvent.VK_RIGHT)
               keys[RIGHT] = true; 
            else if(e.getKeyCode() == KeyEvent.VK_LEFT)
               keys[LEFT] = true;
            else if(e.getKeyCode()==KeyEvent.VK_DOWN)
               keys[DOWN] = true;
               
            else if(e.getKeyCode() == KeyEvent.VK_SPACE)
               keys[SPACE] = true;
         }
      	 /**********************************************************
      	*If any of the 3 keys are released, it sets the boolean for the
      	*key to false.
      	*@param e	uses object e of KeyEvent
      	*************************************************************/
          public void keyReleased(KeyEvent e)
         {
            if(e.getKeyCode() == KeyEvent.VK_RIGHT)
               keys[RIGHT] = false; 
            else if(e.getKeyCode() == KeyEvent.VK_LEFT)
               keys[LEFT] = false;
            else if(e.getKeyCode()==KeyEvent.VK_DOWN)
               keys[DOWN] = false;
               
            else if(e.getKeyCode() == KeyEvent.VK_SPACE)
               keys[SPACE] = false;
               	
         }  
      }
      /****************************************************
   	*returns a boolean to see if the row is filled.
   	*it takes a shape array and a row number. It goes through
   	*the array and checks if any block belonging to any piece
   	*contains an x coordinate with y coordinate row. If it sees
   	*that 10 blocks were in that row, it returns true, if not,
   	*it returns false.
   	*@return true (row is full)
   	*@return false(row is not full)
   	******************************************************/
       public static boolean checkRow(Shape[] shapes, int row)
      {
         int countCheckRow = 0;
         for(int x  = 0; x < shapes.length; x++)
         {
            if(shapes[x].getDrawn() && shapes[x].getDY() == 0)
            {
               for(int x1 = 1; x1 <= 300; x1+= 30)
               {
                  if(shapes[x].contains(x1, row) > 0)
                  {
                     countCheckRow++;
                  }
               }
            }
         }
         if(countCheckRow == 10)
            return true;
         return false;
      }
   	/***********************************************
   	*Clears the row of any blocks belonging to shapes
   	*that are part of that row. It takes a Shape array and
   	*checks if checkRow is true, which means the row
   	*is filled. If it's true, it checks each block's 
   	*coordinates of every shape to see if it matches
   	*up with the row. A for loop is used at the bottom so 
   	*that only the shapes above the cleared row move down.
   	*If it matches up, it moves the block off the screen.
   	*@param shape	uses this Shape array
   	************************************************/
       public void clearRow(Shape[] shapes)
      {
         int times = 0;
         for(int y = 1; y <= 600; y += 30)
         {
            if(checkRow(shapes, y))
            {
               points += 1000;
               times++;
               row = y;
               scoreboard.setText("You cleared a row!"); 
               for(int col = 1; col <= 300; col += 30)
               {
                  for(int x= 0; x <shapes.length; x++)
                  {
                     
                     if(shapes[x].contains(col, y) == 1)
                     {
                        shapes[x].setX1(400);
                        shapes[x].setY1(800);
                     
                     }
                     if(shapes[x].contains(col, y) == 2)
                     {
                        shapes[x].setX2(400);
                        shapes[x].setY2(800);
                        
                     }
                     if(shapes[x].contains(col, y) == 3)
                     {
                        shapes[x].setX3(400);
                        shapes[x].setY3(800);
                        
                     }
                     if(shapes[x].contains(col, y) == 4)
                     {
                        shapes[x].setX4(400);
                        shapes[x].setY4(800);
                        
                     }
                     
                     
                  }
                  
               }
            }         
         }
         if(times > 0)
         {
            for(int x= 0; x <shapes.length; x++)
               if(shapes[x].getDrawn())
               {
                  if(shapes[x].getY1() <= row - 30)                      
                     for(int z = 0; z < times; z++)
                        shapes[x].setY1(shapes[x].getY1() + 30);
                  if(shapes[x].getY2() <= row - 30)                      
                     for(int z = 0; z < times; z++)
                        shapes[x].setY2(shapes[x].getY2() + 30);
                  if(shapes[x].getY3() <= row - 30)                      
                     for(int z = 0; z < times; z++)
                        shapes[x].setY3(shapes[x].getY3() + 30);
                  if(shapes[x].getY4() <= row - 30)                      
                     for(int z = 0; z < times; z++)
                        shapes[x].setY4(shapes[x].getY4() + 30);
               }
         }
         if(times == 2)
         {
            points += 1250;
         }
         if(times == 3)
         {
            points += 2500;
         }
         if(times == 4)
         {
            scoreboard.setText("TETRIS!");
            points += 4000;
         }
      }
    //   /**************************************************************
   //    	*Takes a Shape array and a number, being the index of
   //    	*the shape in the array. It lower
   //    	s that shape's y coordinates 
   //    	*(for all four blocks) by 30.
   //    	*@param shapes		uses a Shape array
   //    	*@param x			assigns x as the index in the shape array
   //    	***************************************************************/
   //        public void lower(Shape[] shapes, int x, int which) 
   // 		 {        
   //          
   //          shapes[x].setY2(shapes[x].getY2() + 30);
   //          shapes[x].setY3(shapes[x].getY3() + 30);
   //          shapes[x].setY4(shapes[x].getY4() + 30);
   //       
   //       }
   }

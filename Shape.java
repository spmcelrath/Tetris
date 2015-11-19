// Zach Hosseinipour, Andrew Freix, and McElrath

   import java.awt.*;
   /*****************************************************************************************
	*Shape is a custom class that maintains information about it's 4 blocks that make up it's unique Tetris 
	*piece. It knows how to return the coordinates of the top left corner of each of its 4 blocks, and it can set them. Shape also knows
	*how to get and set its color, delta x and delta y, and can tell whether it's drawn on the screen
	*or not.
	*@author Zach Hosseinipour, Andrew Freix, and Sean McElrath
	*****************************************************************************************/
    public class Shape 
   {     
      
      private Color myColor;
   	
      private int myX1;
      private int myX2;
      private int myX3;
      private int myX4;
   
      private int myY1;
      private int myY2;
      private int myY3;
      private int myY4;
   	
      private boolean mydrawn;
      private int myDX;
      private int myDY;
      private int myType; //0 = Square, 1 = Rect, 2 = leftL, 3 = rightL, 4 =leftZ, 5 = rightZ, 6 = T-shape
      private final int SIDE = 30; 
   	
     //constructors
     /************************************************************************************
     *Constructs a default Shape with initial DY of 30, initial DX of 0, and initial color of blue.
     *************************************************************************************/
       public Shape()         //default constructor
      {
         myDY = 30;
         myDX = 0;
         myColor = (Color.BLUE);
      }
   	/************************************************************************************
     *Constructs a Shape with an initial X1 and Y1 coordinate specified by x and y, respectively. 
     *These coordinates also construct the other 3 sets of coordinates for the remaining 3 blocks
     *relative to the position of the first one depending on its type. It also constructs a shape with delta y of dy
     *and a delta x of dx, a color of c, a type of shape, type, and a boolean (to see if drawn), drawn.
     *@param x		initial x coordinate of shape's block 1 
     *@param y		initial x coordinate of shape's block 1 
     *@param dy	initial delta y of the shape
     *@param dx	initial delta x of the shape
     *@param c		initial color of the shape
     *@param type 	initial type of the shape
     *@param drawn	tells wheter the shape is drawn or not
     *************************************************************************************/
   
       public Shape(int x, int y, int dy, int dx, Color c, int type, boolean drawn)
      {
         myX1 = x;
         myY1 = y;
         myDX = dx;
         myDY = dy;
         mydrawn = drawn;
         myColor = c;
         myType = type;
         switch(type)
         { 
            case 0: myX2 = myX1 + SIDE; myY2 = myY1; myX3 = myX1 + SIDE; myY3 = myY1 + SIDE; myX4 = myX1;
               myY4 = myY1 + SIDE;
               break;
         	
            case 1: myX2 = myX1; myY2 = myY1 + SIDE; myX3 = myX1; myY3 = myY1 + (SIDE * 2); myX4 = myX1;
               myY4 = myY1 + (SIDE * 3);
               break;
         	
            case 2: myX2 = myX1; myY2 = myY1 + SIDE; myX3 = myX1 - SIDE; myY3 = myY1 + (SIDE * 2); myX4 = myX1;
               myY4 = myY1 + (SIDE * 2);
               break;
         	
            case 3: myX2 = myX1; myY2 = myY1 + SIDE; myX3 = myX1; myY3 = myY1 + (SIDE * 2); myX4 = myX1 + SIDE;
               myY4 = myY1 + (SIDE * 2);
               break;
         
            case 4: myX2 = myX1 + SIDE; myY2 = myY1; myX3 = myX1; myY3 = myY1 + SIDE; myX4 = myX1 - SIDE;
               myY4 = myY1 + SIDE;
               break;
         	
            case 5: myX2 = myX1 + SIDE; myY2 = myY1; myX3 = myX1 + SIDE; myY3 = myY1 + SIDE; myX4 = myX1 + (SIDE * 2);
               myY4 = myY1 + SIDE;
               break;
         
            case 6: myX2 = myX1; myY2 = myY1 + SIDE; myX3 = myX1 + SIDE; myY3 = myY1 + SIDE; myX4 = myX1 - SIDE;
               myY4 = myY1 + SIDE;
               break;
         
            default: 
               break;
         }
      }
      
     // accessor methods  (one for each field) 	and modifier methods(one for each field, except myType)
        /*********************************************************************
     *Returns the shape's type
     *@return type
     *********************************************************************/
       public int getType()
      {
         return myType;
      }
   	/*********************************************************************
     *Returns the shape's x coordinate (top left corner) of its first block
     *@return X1
     **********************************************************************/
       public int getX1()
      {
         return myX1;
      }
   	/*********************************************************************
     *Returns the shape's y coordinate (top left corner) of its first block
     *@return Y1
     **********************************************************************/
       public int getY1()
      {
         return myY1;
      }
   	/**********************************************************************
     *Returns the shape's x coordinate (top left corner) of its second block
     *@return X2
     ***********************************************************************/
       public int getX2()
      {
         return myX2;
      }
   	/**********************************************************************
     *Returns the shape's y coordinate (top left corner) of its second block
     *@return Y2
     ***********************************************************************/
       public int getY2()
      {
         return myY2;
      }
   	/**********************************************************************
     *Returns the shape's x coordinate (top left corner) of its third block
     *@return X3
     ***********************************************************************/
       public int getX3()
      {
         return myX3;
      }
   	/**********************************************************************
     *Returns the shape's y coordinate (top left corner) of its third block
     *@return Y3
     ***********************************************************************/
       public int getY3()
      {
         return myY3;
      }
   	/**********************************************************************
     *Returns the shape's x coordinate (top left corner) of its fourth block
     *@return X4
     ***********************************************************************/
       public int getX4()
      {
         return myX4;
      }
   	/**********************************************************************
     *Returns the shape's y coordinate (top left corner) of its fourth block
     *@return Y4
     ***********************************************************************/
       public int getY4()
      {
         return myY4;
      }
   
      /******************************************************************
     *Sets the shape's x coordinate (top left corner) of its first block
     *to the input number
     *@param x		assigns x to myX1
     *******************************************************************/ 
       public void setX1(int x)
      {
         myX1 = x;
      }
   	/******************************************************************
     *Sets the shape's y coordinate (top left corner) of its first block
     *to the input number
     *@param y		assigns y to myY1
     *******************************************************************/ 
       public void setY1(int y)
      {
         myY1 = y;
      } 
   	/******************************************************************
     *Sets the shape's x coordinate (top left corner) of its second block
     *to the input number
     *@param x		assigns x to myX2
     *******************************************************************/ 
       public void setX2(int x)
      {
         myX2 = x;
      }
   	/******************************************************************
     *Sets the shape's y coordinate (top left corner) of its second block
     *to the input number
     *@param y		assigns y to myY2
     *******************************************************************/ 
       public void setY2(int y)
      {
         myY2 = y;
      }   
      /******************************************************************
     *Sets the shape's x coordinate (top left corner) of its third block
     *to the input number
     *@param x		assigns x to myX3
     *******************************************************************/ 
       public void setX3(int x)
      {
         myX3 = x;
      }
   	/******************************************************************
     *Sets the shape's y coordinate (top left corner) of its third block
     *to the input number
     *@param y		assigns y to myY3
     *******************************************************************/ 
       public void setY3(int y)
      {
         myY3 = y;
      }   
      /******************************************************************
     *Sets the shape's x coordinate (top left corner) of its fourth block
     *to the input number
     *@param x		assigns x to myX4
     *******************************************************************/ 
       public void setX4(int x)
      {
         myX4 = x;
      }
   	/******************************************************************
     *Sets the shape's y coordinate (top left corner) of its fourth block
     *to the input number
     *@param y		assigns y to myY4
     *******************************************************************/ 
       public void setY4(int y)
      {
         myY4 = y;
      }   
   
      /***************************************************
   	*Returns the delta x of the shape
   	*@return	delta x	
   	***************************************************/
       public int getDX()
      {
         return myDX;
      }
      /***************************************************
   	*Sets the delta x of the shape to the input number
   	*@param	dx		assigns dx to myDX
   	***************************************************/
   
       public void setDX(int dx)
      {
         myDX = dx;
      }
   	/***************************************************
   	*Returns the delta y of the shape
   	*@return	delta y	
   	***************************************************/
       public int getDY()
      {
         return myDY;
      }
   	/***************************************************
   	*Sets the delta y of the shape to the input number
   	*@param	dy		assigns dy to myDY
   	***************************************************/
       public void setDY(int dy)
      {
         myDY = dy;
      }
      /***************************************************
   	*Returns the color of the shape
   	*@return	color
   	***************************************************/
       public Color getColor()
      {
         return myColor;
      }
   	/***************************************************
   	*Sets the color of the shape to the input color
   	*@param	c	assigns c to myColor
   	***************************************************/
       public void setColor(Color c)
      {
         myColor = c;
      }
   	/***************************************************
   	*Returns the (boolean) if the shape is drawn or not
   	*@return boolean if it's drawn or not
   	***************************************************/
       public boolean getDrawn()
      {
         return mydrawn;
      }
   	/***************************************************
   	*Sets the boolean drawn (whether it's drawn or not 
   	*to the input boolean.
   	*@param drawn	 assigns the boolean drawn to mydrawn
   	***************************************************/
       public void setDrawn(boolean drawn)
      {
         mydrawn = drawn;
      }
     
     //Other instance methods
     /**********************************************************
     *Draws the shape using Graphics object myBuffer and with
     *the shapes 4 x and y coordinates, of each block. It fills 
     *each block seperately, and draws a rectangle around it to
     *outline it. Lastly, setDrawn is made true.
     **********************************************************/      
       public void draw(Graphics myBuffer) 
      {
         myBuffer.setColor(getColor());
         myBuffer.fillRect(myX1, myY1, SIDE, SIDE);
         myBuffer.fillRect(myX2, myY2,  SIDE, SIDE);
         myBuffer.fillRect(myX3, myY3, SIDE, SIDE);
         myBuffer.fillRect(myX4, myY4, SIDE, SIDE);
      	
         myBuffer.setColor(Color.BLACK); 
         myBuffer.drawRect(myX1, myY1, SIDE, SIDE);
         myBuffer.drawRect(myX2, myY2,  SIDE, SIDE);
         myBuffer.drawRect(myX3, myY3, SIDE, SIDE);
         myBuffer.drawRect(myX4, myY4, SIDE, SIDE);          
           
         setDrawn(true);  
         
      }  
   	/******************************************************************
   	*Checks to see if the shape's delta y is not 0 (not stopped), 
   	*and if any of it's y coordinates added to its delta y and side length 
   	*is greater than or equal to the bottom edge (only has enough room to 
   	*move once more) the pieces blocks are moved one delta y down, and then 
   	*its delta y is set to 0. Otherwise, it just moves down delta y
   	*******************************************************************/
   
       public void move(int bottomEdge)
      {	
      	
         if(myDY != 0)
         {
            if( myY1 + myDY + SIDE >= bottomEdge ||  myY2 + myDY + SIDE >= bottomEdge ||  myY3 + myDY + SIDE >= bottomEdge||  myY4 + myDY + SIDE >= bottomEdge)
            {
               setY1(myY1 + myDY);
               setY2(myY2 + myDY);
               setY3(myY3 + myDY);
               setY4(myY4 + myDY);
               setDY(0);
            }
            else
            {
               setY1(myY1 + myDY);
               setY2(myY2 + myDY);
               setY3(myY3 + myDY);
               setY4(myY4 + myDY);
            }
         }
          
         			
      }
   	/******************************************************************
   	*Checks to see if an x or y point is within or touching any block, and returns 
   	*an integer corresponding to the block. If it is not withinor touching any,
   	*it returns 0
   	*@return block 1
   	*@return block 2
   	*@return block 3
   	*@return block 4
   	*@return -1 (point is not within the shape)
   	*******************************************************************/ 
       public int contains(int x, int y)
      {
         if(x >= myX1 && x <= myX1+SIDE && y >= myY1 && y <= myY1+SIDE) 
            return 1;
         if(x >= myX2 && x <= myX2+SIDE && y >= myY2 && y <= myY2+SIDE) 
            return 2;
         if(x >= myX3 && x <= myX3+SIDE && y >= myY3 && y <= myY3+SIDE) 
            return 3;
         if(x >= myX4 && x <= myX4+SIDE && y >= myY4 && y <= myY4+SIDE) 
            return 4;
         return -1;
      }
   	/********************************************************************
   	*Returns a boolean if the shape is stopped or not, meaning its delta y
   	*is equal to 0.
   	*@return true (stopped)
   	*@return false (not stopped)
   	*********************************************************************/
       public boolean stop()
      {
         if(myDY == 0)
            return true;
         return false;
      }
   	/*********************************************************************
   	*Returns a boolean to see if the shape is on top of another shape.
   	*It uses a Shape array and a starting x location. It searches the array
   	*of shapes that are drawn, and if adds one to all the shape's x and y 
   	*coordinates (if a shape is on top, it should contain this coordinate)
   	*and if it does contain that point, it returns true, and if not, at the
   	*end it returns false. One was added to the shapes.getX1() so that the 
   	*falling shape won't think its on top of another shape if there corners touch.
   	*@return true (stacked)
   	*@return false (not stacked)
   	*@param array	uses this Shape array
   	*@param start	assigns start as the beginning x coordinate
   	**********************************************************************/
       public boolean stack(Shape[] array, int start)
      {
         if(start != -1)
         {
            for(int x = start; x >= 0; x--)
            {
               if(array[x].getDrawn())
               {
                  if(this.contains(array[x].getX1() + 1, array[x].getY1()) > 0)
                     return true;
                  if(this.contains(array[x].getX2() + 1, array[x].getY2()) > 0)
                     return true;
                  if(this.contains(array[x].getX3() + 1, array[x].getY3()) > 0)
                     return true;
                  if(this.contains(array[x].getX4() + 1, array[x].getY4()) > 0)
                     return true;
               }
            }
         }
         return false;
      }
   	/********************************************************************************
   	*Returns a boolean to see whether or not the shape is next to another
   	*based on a Shape array, a starting x location, and a direction, left or 
   	*right. 1 corresponds with left, and 2 with right. If the start location 
   	*is not a negative one, then it checks which direction it is in. If left, then
   	*it checks every x point from its own to 0, and checks if the x coordinate 
   	*of any drawn shape in the array plus 31 (gets it to the current shape if 
   	*its indeed next to it) and returns true if the current shape contains it.
   	*It does the opposite case for when the searching to the right, by subtracting
   	*29 from every drawn piece on the board to see if it matches up with the current 
   	*piece. If so, it returns true, at the end it returns false.
   	*@return true (next to a shape)
   	*@return false (not next to a shape)
   	*@param array	uses this Shape array
   	*@param start  assigns start as the starting x location of the shape
   	*@param direction		assigns the number direction as left if it's 1, or 2 if right
   	*********************************************************************************/
   
       public boolean nextTo(Shape[] array, int start, int direction) //direction 1 = left, direction 2 = right
      {
         if(start != -1)
         {
         
            for(int x = start; x >= 0; x--)
            {
               if(array[x].getDrawn() && direction == 1)
               {
                  if(this.contains(array[x].getX1() + 31, array[x].getY1()) > 0)
                     return true;
                  if(this.contains(array[x].getX2() + 31, array[x].getY2()) > 0)
                     return true;
                  if(this.contains(array[x].getX3() + 31, array[x].getY3()) > 0)
                     return true;
                  if(this.contains(array[x].getX4() + 31, array[x].getY4()) > 0)
                     return true;
               }
               if(array[x].getDrawn() && direction == 2)
               {
                  if(this.contains(array[x].getX1() - 29, array[x].getY1()) > 0)
                     return true;
                  if(this.contains(array[x].getX2() - 29, array[x].getY2()) > 0)
                     return true;
                  if(this.contains(array[x].getX3() - 29, array[x].getY3()) > 0)
                     return true;
                  if(this.contains(array[x].getX4() - 29, array[x].getY4()) > 0)
                     return true;
               }
            
            
            }
         }
         return false;
      }
   	/********************************************************************
   	*Rotates the shape, by manipulating the 4 pairs of x and y coordinates.
   	*By using this general algorithm, the shape rotates: subtract each 
   	*coordinates x and y from the central point of the shape (top left of
   	*block 3). By multiplying it's new y coordinate by negative one, it gives 
   	*you the rotated x coordinate (not final). By setting the y coordinate 
   	*to the x coordinate subtracted from the central point, you get the 
   	*rotated y coordinate (not final). To make this work for any coordinate
   	*on the panel, the central point x and y was added back to each block's
   	* x and y respectively. This proccess is repeated for the 4 blocks. The game
   	* doesnt let you rotate into a wall, the bottom or a piece.
   	*********************************************************************/
       public void rotate(Shape[] array, int start) 
      {
         if(myType != 0)
         {
            int startingX1 = myX1;
            int startingY1 = myY1;
            int startingX2 = myX2;
            int startingY2 = myY2;
            int startingX4 = myX4;
            int startingY4 = myY4;
         	
            setX1(myX1 - myX3);
            setY1(myY1 - myY3);
            int tempX1 = myX1;
            setX1(myY1 * -1);
            setY1(tempX1);
            setX1(myX1 + myX3);
            setY1(myY1 + myY3);
                    
            setX2(myX2 - myX3);
            setY2(myY2 - myY3);
            int tempX2 = myX2;
            setX2(myY2 * -1);
            setY2(tempX2);
            setX2(myX2 + myX3);
            setY2(myY2 + myY3);
         
            setX4(myX4 - myX3);
            setY4(myY4 - myY3);
            int tempX4 = myX4;
            setX4(myY4 * -1);
            setY4(tempX4);
            setX4(myX4 + myX3);
            setY4(myY4 + myY3);
            for(int x = start - 1; x >= 0; x--)
            {
            	//This long if statement tests to see if the shape rotates into a piece or wall
               if(myX1 < 0 || myX2 < 0 || myX4 < 0|| myX1 > 270 || myX2 > 270 || myX4 > 270 || myY1 > 570 || myY2 > 570 || myY4 > 570 || (array[x].getDrawn() && (this.contains(array[x].getX1() + 1, array[x].getY1()) > 0 ||this.contains(array[x].getX2() + 1, array[x].getY2()) > 0 || this.contains(array[x].getX3() + 1, array[x].getY3()) > 0 || this.contains(array[x].getX4() + 1, array[x].getY4()) > 0))) 
               {
                  setX1(startingX1);
                  setY1(startingY1);
                  setX2(startingX2);
                  setY2(startingY2);
                  setX4(startingX4);
                  setY4(startingY4);
               }
            }
         	//This case is for the first shape
            if(myX1 < 0 || myX2 < 0 || myX4 < 0|| myX1 > 270 || myX2 > 270 || myX4 > 270 || myY1 > 570 || myY2 > 570 || myY4 > 570)  
            {
               setX1(startingX1);
               setY1(startingY1);
               setX2(startingX2);
               setY2(startingY2);
               setX4(startingX4);
               setY4(startingY4);
            }
         
         }
      }
   }

if(keys[RIGHT])
{
	for(int i = 0; i < shapesEasy.length; i++)
	{
		if(shapesEasy[i].getDY() != 0)
		{
			if( shapesEasy[i].getX1() + shapesEasy[i].getDX() + SIDE <= XSIZE ||  shapesEasy[i].getX2() + shapesEasy[i].getDX() + SIDE <= XSIZE ||  shapesEasy[i].getX3() + shapesEasy[i].getDX() + SIDE <= XSIZE||  shapesEasy[i].getX4() + shapesEasy[i].getDX() + SIDE <= XSIZE)
         {
           shapesEasy[i].setX1(shapesEasy[i].getX1() + shapesEasy[i].getDX());
			  shapesEasy[i].setX2(shapesEasy[i].getX2() + shapesEasy[i].getDX());
			  shapesEasy[i].setX3(shapesEasy[i].getX3() + shapesEasy[i].getDX());
			  shapesEasy[i].setX4(shapesEasy[i].getX4() + shapesEasy[i].getDX());
		
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
			if( shapesEasy[i].getX1() + shapesEasy[i].getDX() + SIDE >= XSIZE ||  shapesEasy[i].getX2() + shapesEasy[i].getDX() + SIDE >= XSIZE ||  shapesEasy[i].getX3() + shapesEasy[i].getDX() + SIDE >= XSIZE||  shapesEasy[i].getX4() + shapesEasy[i].getDX() + SIDE >= XSIZE)
         {
           shapesEasy[i].setX1(shapesEasy[i].getX1() - shapesEasy[i].getDX());
			  shapesEasy[i].setX2(shapesEasy[i].getX2() - shapesEasy[i].getDX());
			  shapesEasy[i].setX3(shapesEasy[i].getX3() - shapesEasy[i].getDX());
			  shapesEasy[i].setX4(shapesEasy[i].getX4() - shapesEasy[i].getDX());
		
         }
		}
		
	}
}
if(keys[SPACE])
{
	for(int i = 0; i < shapesEasy.length; i++)
	{
		if(shapesEasy[i].getDY() != 0)
			shapesEasy[i].rotate();
	}
}



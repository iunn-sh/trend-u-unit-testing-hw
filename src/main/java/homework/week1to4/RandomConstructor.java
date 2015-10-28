package homework.week1to4;

import java.util.Random;


public class RandomConstructor implements IRandom
{
	IRandom random = null;
	
	public RandomConstructor(IRandom random)
	{
		this.random = random;
	}
	
	public int nextInt(int bound)
	{
		return random.nextInt(bound);
	}
}
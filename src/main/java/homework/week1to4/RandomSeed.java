package homework.week1to4;

import java.util.Random;


public class RandomSeed implements IRandom
{
	Random random = null;
	
	public RandomSeed()
	{
		random = new Random();
	}
	
	public RandomSeed(long seed)
	{
		random = new Random(seed);
	}
	
	public int nextInt(int bound)
	{
		return random.nextInt(bound);
	}
}
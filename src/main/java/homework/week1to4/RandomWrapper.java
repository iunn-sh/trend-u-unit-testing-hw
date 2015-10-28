package homework.week1to4;

import java.util.Random;

public class RandomWrapper implements IRandom
{
	public Random random = null;
	
	public RandomWrapper()
	{
		this.random = new Random();
	}
	
	public RandomWrapper(long seed)
	{
		this.random = new Random(seed);
	}
	
	public int nextInt(int bound) {
        return random.nextInt(bound);
	}
}
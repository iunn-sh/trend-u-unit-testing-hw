package homework.week1to4;


public class RandomFactory 
{
	private static IRandom random = null;
	
	public static void setRandom(IRandom random)
	{
		RandomFactory.random = random;
	}
	
	public static IRandom create()
	{
		if (random != null)
		{
			return random;
		}
		
		return new RandomSeed();
	}
}
package homework.week1to4;


public class RandomOverride extends RandomSeed
{
	public IRandom random = null;
	
	public RandomOverride(IRandom random)
	{
		this.random = random;
	}
	
	@Override
	public int nextInt(int bound)
	{
		return random.nextInt(bound);
	}
}
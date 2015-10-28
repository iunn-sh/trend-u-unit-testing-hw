package homework.week1to4;

public class RandomSetter implements IRandom 
{
	public IRandom random = null;
	
	public RandomSetter()
	{	
	}
	
	public IRandom getRandom()
	{
		return random;
	}
	
	public void setRandom(IRandom random)
	{
		this.random = random;
	}
	
	public int nextInt(int bound)
	{
		return random.nextInt(bound);
	}
}
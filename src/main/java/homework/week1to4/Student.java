package homework.week1to4;

import java.util.Objects;

public class Student 
{
	private int id;
	private int isWeek7;
	private int isWeek8;
	
	public Student()
	{
		super();
	}
	
	public Student(int id, int isWeek7, int isWeek8)
	{
		this.setId(id);
		this.setIsWeek7(isWeek7);
		this.setIsWeek8(isWeek8);
	}
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getIsWeek7() {
		return isWeek7;
	}
	public void setIsWeek7(int isWeek7) {
		this.isWeek7 = isWeek7;
	}

	public int getIsWeek8() {
		return isWeek8;
	}
	public void setIsWeek8(int isWeek8) {
		this.isWeek8 = isWeek8;
	}
	
	
	@Override
    public boolean equals(Object o) {
        if (o instanceof Student) {
            Student other = (Student) o;
            return Objects.equals(getId(), other.getId()) 
            		&& Objects.equals(getIsWeek7(), other.getIsWeek7())
            		&& Objects.equals(getIsWeek8(), other.getIsWeek8());
        }
        return false;
    }
}
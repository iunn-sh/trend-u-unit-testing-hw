package homework.week1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;


public class RollCall 
{
	public static void main(String[] args) 
	{
		RollCall rollCall = new RollCall();
		List<Student> listStudent = rollCall.LoadCsvToStudent("src/main/resources/Week1HW_sample.csv");
		
		for (Student student : listStudent)
		{
			boolean isValid = rollCall.CheckStudentFormat(student);
			
			System.out.println("Student [id=" + student.getId() 
			                    + ", isWeek7=" + student.getIsWeek7() 
			                    + ", isWeek8=" + student.getIsWeek8() 
			                    + "]"
			                    + " isValid=" + isValid);
		}
		System.out.println("Total: " + listStudent.size() + " people");
		
		System.out.println(StringUtils.repeat("-", 60));
		
		List<Student> listStudentWeek7 = rollCall.GetWeek7List(listStudent);
		for (Student student : listStudentWeek7)
		{
			System.out.println("Student [id=" + student.getId() 
			                    + ", isWeek7=" + student.getIsWeek7() 
			                    + ", isWeek8=" + student.getIsWeek8() 
			                    + "]");
		}
		System.out.println("Week7: " + listStudentWeek7.size() + " people");
		
		System.out.println(StringUtils.repeat("-", 60));
		
		List<Student> listStudentWeek8 = rollCall.GetWeek8List(listStudent);
		for (Student student : listStudentWeek8)
		{
			System.out.println("Student [id=" + student.getId() 
			                    + ", isWeek7=" + student.getIsWeek7() 
			                    + ", isWeek8=" + student.getIsWeek8() 
			                    + "]");
		}
		System.out.println("Week8: " + listStudentWeek8.size() + " people");
		
	}
	
	// http://www.mkyong.com/java/how-to-read-and-parse-csv-file-in-java/
	public List<Student> LoadCsvToStudent (String filePath)
	{
		List<Student> listStudent = new ArrayList<Student>();
		
		BufferedReader bufferedReader = null;
		String line = "";
		String cvsSplitBy = ",";
		
		try 
		{
			bufferedReader = new BufferedReader(new FileReader(filePath));
			while ((line = bufferedReader.readLine()) != null) 
			{
		        // use comma as separator
				String[] studentInfo = line.split(cvsSplitBy);
				Student student = new Student(
									Integer.parseInt(studentInfo[0]), 
									Integer.parseInt(studentInfo[1]), 
									Integer.parseInt(studentInfo[2]));
	
				listStudent.add(student);
			}
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		finally 
		{
			if (bufferedReader != null) {
				try 
				{
					bufferedReader.close();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return listStudent;
	}
	
	public boolean CheckStudentFormat(Student student)
	{
		if (student.getId() <= 0)
		{ return false; }
		
		if (student.getIsWeek7() > 1 || student.getIsWeek7() < 0)
		{ return false; }
		
		if (student.getIsWeek8() > 1 || student.getIsWeek8() < 0)
		{ return false; }
		
		return true;
	}
	
	public List<Student> GetWeek7List(List<Student> listStudent)
	{
		List<Student> listStudentWeek7 = new ArrayList<Student>();
		
		for (Student student : listStudent)
		{
			if (student.getIsWeek7() == 1)
			{
				listStudentWeek7.add(student);
			}
		}
		
		return listStudentWeek7;
	}
	
	public List<Student> GetWeek8List(List<Student> listStudent)
	{
		List<Student> listStudentWeek8 = new ArrayList<Student>();
		
		for (Student student : listStudent)
		{
			if (student.getIsWeek8() == 1)
			{
				listStudentWeek8.add(student);
			}
		}
		
		return listStudentWeek8;
	}
	
}
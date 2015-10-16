package homework.week1to4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import homework.week1to4.RollCall;
import homework.week1to4.Student;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class RollCallTest
{
	private RollCall rollCall = new RollCall();
	
	@Before
	public void SetUp() throws Exception 
	{
	}

	@After
	public void TearDown() throws Exception 
	{
	}
	
	
	@Test
	public void Format_StudentValid_ReturnTrue()
	{
		List<Student> listStudent = GetListStudentValid();
		
		for (Student student : listStudent)
		{
			boolean valid = rollCall.CheckStudentFormat(student);
			
			assertEquals(true, valid);
		}
	}
	
	@Test
	public void Format_StudentIdNotValid_ReturnFalse()
	{
		Student student = new Student(0, 1, 1);
		
		boolean valid = rollCall.CheckStudentFormat(student);
		
		assertEquals(false, valid);
	}
	
	@Test
	public void Format_StudentIsWeek7NotValid_ReturnFalse()
	{
		Student student = new Student(27765, -1, 1);
		
		boolean valid = rollCall.CheckStudentFormat(student);
		
		assertEquals(false, valid);
	}
	
	@Test
	public void Format_StudentIsWeek8NotValid_ReturnFalse()
	{
		Student student = new Student(27765, 1, -1);
		
		boolean valid = rollCall.CheckStudentFormat(student);
		
		assertEquals(false, valid);
	}
	
	@Test
	public void Format_LoadValidCsv_returnList()
	{
		String filePath = "src/main/resources/Week1HW_TestCaseValid.csv";
		File file = GetCsvFileStudentValid(filePath);	
		List<Student> expectedListStudent = GetListStudentValid();
		
		List<Student> csvListStudent = rollCall.LoadCsvToStudent(filePath);
	
		assertEquals(5, csvListStudent.size());
		assertEquals(expectedListStudent, csvListStudent);
		
		file.delete();
	}
	
	@Test
	public void Format_LoadNotValidCsv_returnException()
	{
		String filePath = "src/main/resources/Week1HW_TestCaseNotValid_NotNumber.csv";
		File file = GetCsvFileStudentNotValid(filePath);	
		ExpectedException expectedException = ExpectedException.none();

		List<Student> csvListStudent = rollCall.LoadCsvToStudent(filePath);
		expectedException.expect(NumberFormatException.class);
		
		file.delete();
	}
	
	@Test
	public void List_StudentIsWeek7_ReturnList()
	{
		List<Student> listStudent = GetListStudentValid();

		List<Student> expectedListStudentIsWeek7 = new ArrayList<Student>();
		expectedListStudentIsWeek7.add(new Student(27765, 1, 1));
		expectedListStudentIsWeek7.add(new Student(22222, 1, 0));
		expectedListStudentIsWeek7.add(new Student(44444, 1, 0));
		
		List<Student> testLeistStudentIsWeek7 = rollCall.GetWeek7List(listStudent);

		assertEquals(expectedListStudentIsWeek7, testLeistStudentIsWeek7);
	}
	
	@Test
	public void List_StudentIsWeek8_ReturnList()
	{
		List<Student> listStudent = GetListStudentValid();

		List<Student> expectedListStudentIsWeek8 = new ArrayList<Student>();
		expectedListStudentIsWeek8.add(new Student(27765, 1, 1));
		expectedListStudentIsWeek8.add(new Student(33333, 0, 1));
		
		List<Student> testLeistStudentIsWeek8 = rollCall.GetWeek8List(listStudent);

		assertEquals(expectedListStudentIsWeek8, testLeistStudentIsWeek8);
	}
	
	@Test
	public void List_StudentIsWeek7AndWeek8_ReturnList()
	{
		List<Student> listStudent = GetListStudentValid();

		List<Student> expectedListStudentIsWeek7AndWeek8 = new ArrayList<Student>();
		expectedListStudentIsWeek7AndWeek8.add(new Student(27765, 1, 1));
		
		List<Student> testLeistStudentIsWeek7AndWeek8 = rollCall.GetWeek7AndWeek8List(listStudent);

		assertEquals(expectedListStudentIsWeek7AndWeek8, testLeistStudentIsWeek7AndWeek8);
	}
	
	@Test
	public void List_StudentIsWeek7OrWeek8_ReturnList()
	{
		List<Student> listStudent = GetListStudentValid();

		List<Student> expectedListStudentIsWeek7OrWeek8 = new ArrayList<Student>();
		expectedListStudentIsWeek7OrWeek8.add(new Student(27765, 1, 1));
		expectedListStudentIsWeek7OrWeek8.add(new Student(22222, 1, 0));
		expectedListStudentIsWeek7OrWeek8.add(new Student(33333, 0, 1));
		expectedListStudentIsWeek7OrWeek8.add(new Student(44444, 1, 0));
		
		List<Student> testListStudentIsWeek7OrWeek8 = rollCall.GetWeek7OrWeek8List(listStudent);

		assertEquals(expectedListStudentIsWeek7OrWeek8, testListStudentIsWeek7OrWeek8);
	}
	
	@Test
	public void Random_RandomPresentationSequence_ReturnList()
	{
		List<Student> listStudent = GetListStudentValid();
		
		List<Student> expectedListStudentPresentSequence = new ArrayList<Student>();
		expectedListStudentPresentSequence.add(new Student(22222, 1, 0));
		expectedListStudentPresentSequence.add(new Student(27765, 1, 1));
		expectedListStudentPresentSequence.add(new Student(44444, 1, 0));
		expectedListStudentPresentSequence.add(new Student(33333, 0, 1));
		
		List<Student> testListStudentPresentSequence = 
				rollCall.GetRandomPresentationSequence(listStudent, new Random(1L));
		
		assertEquals(expectedListStudentPresentSequence, testListStudentPresentSequence);
	}
	
	public Student GetStudentValid()
	{
		Student student = new Student(27765, 1, 1);
		
		return student;
	}
	
	public List<Student> GetListStudentValid()
	{
		List<Student> listStudent = new ArrayList<Student>();
		
		listStudent.add(new Student(27765, 1, 1));
		listStudent.add(new Student(11111, 0, 0));
		listStudent.add(new Student(22222, 1, 0));
		listStudent.add(new Student(33333, 0, 1));
		listStudent.add(new Student(44444, 1, 0));
		
		return listStudent;
	}
	
	public File GetCsvFileStudentValid(String filePath)
	{
		File file = new File(filePath);
		
		try
		{
			FileWriter fileWriter = new FileWriter(filePath);
				 
			fileWriter.append("27765");
			fileWriter.append(',');
			fileWriter.append("1");
			fileWriter.append(',');
			fileWriter.append("1");
			fileWriter.append('\n');
	
			fileWriter.append("11111");
			fileWriter.append(',');
			fileWriter.append("0");
			fileWriter.append(',');
			fileWriter.append("0");
			fileWriter.append('\n');
			
			fileWriter.append("22222");
			fileWriter.append(',');
			fileWriter.append("1");
			fileWriter.append(',');
			fileWriter.append("0");
			fileWriter.append('\n');
			
			fileWriter.append("33333");
			fileWriter.append(',');
			fileWriter.append("0");
			fileWriter.append(',');
			fileWriter.append("1");
			fileWriter.append('\n');
			
			fileWriter.append("44444");
			fileWriter.append(',');
			fileWriter.append("1");
			fileWriter.append(',');
			fileWriter.append("0");
			fileWriter.append('\n');
			
			fileWriter.flush();
			fileWriter.close();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		} 
		
		return file;
	}
	
	public File GetCsvFileStudentNotValid(String filePath)
	{
		File file = new File(filePath);
		
		try
		{
			FileWriter fileWriter = new FileWriter(filePath);
				 
			fileWriter.append("abcde");
			fileWriter.append(',');
			fileWriter.append("f");
			fileWriter.append(',');
			fileWriter.append("g");
			fileWriter.append('\n');
			
			fileWriter.flush();
			fileWriter.close();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		} 
		
		return file;
	}
	
	
}
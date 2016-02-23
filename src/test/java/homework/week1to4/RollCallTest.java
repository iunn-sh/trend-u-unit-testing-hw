package homework.week1to4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.anyInt;

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

public class RollCallTest {
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		RandomFactory.setRandom(null);
	}

	@Test
	public void format_StudentValid_ReturnTrue() {
		RollCall rollCall = new RollCall();
		List<Student> listStudent = getListStudentValid();

		for (Student student : listStudent) {
			boolean valid = rollCall.checkStudentFormat(student);

			assertEquals(true, valid);
		}
	}

	@Test
	public void format_StudentIdNotValid_ReturnFalse() {
		RollCall rollCall = new RollCall();
		Student student = new Student(0, 1, 1);

		boolean valid = rollCall.checkStudentFormat(student);

		assertEquals(false, valid);
	}

	@Test
	public void format_StudentIsWeek7NotValid_ReturnFalse() {
		RollCall rollCall = new RollCall();
		Student student = new Student(27765, -1, 1);

		boolean valid = rollCall.checkStudentFormat(student);

		assertEquals(false, valid);
	}

	@Test
	public void format_StudentIsWeek8NotValid_ReturnFalse() {
		RollCall rollCall = new RollCall();
		Student student = new Student(27765, 1, -1);

		boolean valid = rollCall.checkStudentFormat(student);

		assertEquals(false, valid);
	}

	@Test
	public void format_LoadValidCsv_returnList() {
		RollCall rollCall = new RollCall();
		String filePath = "src/main/resources/Week1HW_TestCaseValid.csv";
		File file = getCsvFileStudentValid(filePath);
		List<Student> expectedListStudent = getListStudentValid();

		List<Student> csvListStudent = rollCall.loadCsvToStudent(filePath);

		assertEquals(5, csvListStudent.size());
		assertEquals(expectedListStudent, csvListStudent);

		file.delete();
	}

	@Test
	public void format_LoadNotValidCsv_returnException() {
		RollCall rollCall = new RollCall();
		String filePath = "src/main/resources/Week1HW_TestCaseNotValid_NotNumber.csv";
		File file = getCsvFileStudentNotValid(filePath);
		ExpectedException expectedException = ExpectedException.none();

		List<Student> csvListStudent = rollCall.loadCsvToStudent(filePath);
		expectedException.expect(NumberFormatException.class);

		file.delete();
	}

	@Test
	public void logic_UniqueStudentList_returnTrue() {
		RollCall rollCall = new RollCall();
		List<Student> listStudent = getListStudentValid();

		boolean unique = rollCall.checkUniqueStudentList(listStudent);

		assertEquals(true, unique);
	}

	@Test
	public void logic_NotUniqueStudentList_returnFalse() {
		RollCall rollCall = new RollCall();
		List<Student> listStudent = getListStudentValid();
		listStudent.add(listStudent.get(0));

		boolean unique = rollCall.checkUniqueStudentList(listStudent);

		assertEquals(false, unique);
	}

	@Test
	public void list_GetStudentIsWeek7_ReturnList() {
		RollCall rollCall = new RollCall();
		List<Student> listStudent = getListStudentValid();

		List<Student> expectedListStudentIsWeek7 = new ArrayList<Student>();
		expectedListStudentIsWeek7.add(new Student(27765, 1, 1));
		expectedListStudentIsWeek7.add(new Student(22222, 1, 0));
		expectedListStudentIsWeek7.add(new Student(44444, 1, 0));

		List<Student> testLeistStudentIsWeek7 = rollCall
				.getWeek7List(listStudent);

		assertEquals(expectedListStudentIsWeek7, testLeistStudentIsWeek7);
	}

	@Test
	public void list_GetStudentIsWeek8_ReturnList() {
		RollCall rollCall = new RollCall();
		List<Student> listStudent = getListStudentValid();

		List<Student> expectedListStudentIsWeek8 = new ArrayList<Student>();
		expectedListStudentIsWeek8.add(new Student(27765, 1, 1));
		expectedListStudentIsWeek8.add(new Student(33333, 0, 1));

		List<Student> testLeistStudentIsWeek8 = rollCall
				.getWeek8List(listStudent);

		assertEquals(expectedListStudentIsWeek8, testLeistStudentIsWeek8);
	}

	@Test
	public void list_GetStudentIsWeek7AndWeek8_ReturnList() {
		RollCall rollCall = new RollCall();
		List<Student> listStudent = getListStudentValid();

		List<Student> expectedListStudentIsWeek7AndWeek8 = new ArrayList<Student>();
		expectedListStudentIsWeek7AndWeek8.add(new Student(27765, 1, 1));

		List<Student> testLeistStudentIsWeek7AndWeek8 = rollCall
				.getWeek7AndWeek8List(listStudent);

		assertEquals(expectedListStudentIsWeek7AndWeek8,
				testLeistStudentIsWeek7AndWeek8);
	}

	@Test
	public void list_GetStudentIsWeek7OrWeek8_ReturnList() {
		RollCall rollCall = new RollCall();
		List<Student> listStudent = getListStudentValid();

		List<Student> expectedListStudentIsWeek7OrWeek8 = new ArrayList<Student>();
		expectedListStudentIsWeek7OrWeek8.add(new Student(27765, 1, 1));
		expectedListStudentIsWeek7OrWeek8.add(new Student(22222, 1, 0));
		expectedListStudentIsWeek7OrWeek8.add(new Student(33333, 0, 1));
		expectedListStudentIsWeek7OrWeek8.add(new Student(44444, 1, 0));

		List<Student> testListStudentIsWeek7OrWeek8 = rollCall
				.getWeek7OrWeek8List(listStudent);

		assertEquals(expectedListStudentIsWeek7OrWeek8,
				testListStudentIsWeek7OrWeek8);
	}

	@Test
	public void random_GetRandomPresentationSequence_ReturnList_RandomConstructor() {
		IRandom fakeRandom = new RandomWrapper(1L);
		RollCall rollCall = new RollCall(fakeRandom);
		List<Student> listStudent = getListStudentValid();

		List<Student> expectedListStudentPresentSequence = new ArrayList<Student>();
		expectedListStudentPresentSequence.add(new Student(22222, 1, 0));
		expectedListStudentPresentSequence.add(new Student(27765, 1, 1));
		expectedListStudentPresentSequence.add(new Student(44444, 1, 0));
		expectedListStudentPresentSequence.add(new Student(33333, 0, 1));

		List<Student> testListStudentPresentSequence = rollCall
				.getRandomPresentationSequence(listStudent);

		assertEquals(expectedListStudentPresentSequence,
				testListStudentPresentSequence);
	}

	@Test
	public void random_GetRandomPresentationSequence_ReturnList_RandomSetter() {
		IRandom fakeRandom = new RandomWrapper(1L);
		RollCall rollCall = new RollCall();
		rollCall.setRandom(fakeRandom);
		List<Student> listStudent = getListStudentValid();

		List<Student> expectedListStudentPresentSequence = new ArrayList<Student>();
		expectedListStudentPresentSequence.add(new Student(22222, 1, 0));
		expectedListStudentPresentSequence.add(new Student(27765, 1, 1));
		expectedListStudentPresentSequence.add(new Student(44444, 1, 0));
		expectedListStudentPresentSequence.add(new Student(33333, 0, 1));

		List<Student> testListStudentPresentSequence = rollCall
				.getRandomPresentationSequence(listStudent);

		assertEquals(expectedListStudentPresentSequence,
				testListStudentPresentSequence);
	}

	@Test
	public void random_GetRandomPresentationSequence_ReturnList_RandomFactory() {
		IRandom fakeRandom = new RandomWrapper(1L);
		RandomFactory.setRandom(fakeRandom);
		RollCall rollCall = new RollCall(RandomFactory.create());
		List<Student> listStudent = getListStudentValid();

		List<Student> expectedListStudentPresentSequence = new ArrayList<Student>();
		expectedListStudentPresentSequence.add(new Student(22222, 1, 0));
		expectedListStudentPresentSequence.add(new Student(27765, 1, 1));
		expectedListStudentPresentSequence.add(new Student(44444, 1, 0));
		expectedListStudentPresentSequence.add(new Student(33333, 0, 1));

		List<Student> testListStudentPresentSequence = rollCall
				.getRandomPresentationSequence(listStudent);

		assertEquals(expectedListStudentPresentSequence,
				testListStudentPresentSequence);
	}

	@Test
	public void random_GetRandomPresentationSequence_ReturnList_RandomOverride() {
		IRandom fakeRandom = new RandomOverride(new RandomWrapper(1L));
		RollCall rollCall = new RollCall(fakeRandom);
		List<Student> listStudent = getListStudentValid();

		List<Student> expectedListStudentPresentSequence = new ArrayList<Student>();
		expectedListStudentPresentSequence.add(new Student(22222, 1, 0));
		expectedListStudentPresentSequence.add(new Student(27765, 1, 1));
		expectedListStudentPresentSequence.add(new Student(44444, 1, 0));
		expectedListStudentPresentSequence.add(new Student(33333, 0, 1));

		List<Student> testListStudentPresentSequence = rollCall
				.getRandomPresentationSequence(listStudent);

		assertEquals(expectedListStudentPresentSequence,
				testListStudentPresentSequence);
	}

	@Test
	public void isolationRandom_GetRandomPresentationSequence_ReturnList_RandomConstructor() {
		IRandom fakeRandom = mock(IRandom.class);
		RollCall rollCall = new RollCall(fakeRandom);
		List<Student> listStudent = getListStudentValid();

		when(fakeRandom.nextInt(anyInt())).thenReturn(0);

		List<Student> expectedListStudentPresentSequence = new ArrayList<Student>();
		expectedListStudentPresentSequence.add(new Student(27765, 1, 1));
		expectedListStudentPresentSequence.add(new Student(22222, 1, 0));
		expectedListStudentPresentSequence.add(new Student(44444, 1, 0));
		expectedListStudentPresentSequence.add(new Student(33333, 0, 1));

		List<Student> testListStudentPresentSequence = rollCall
				.getRandomPresentationSequence(listStudent);

		assertEquals(expectedListStudentPresentSequence,
				testListStudentPresentSequence);
	}

	@Test
	public void isolationRandom_GetRandomPresentationSequence_ReturnList_RandomSetter() {
		IRandom fakeRandom = mock(IRandom.class);
		RollCall rollCall = new RollCall();
		rollCall.setRandom(fakeRandom);
		List<Student> listStudent = getListStudentValid();

		when(fakeRandom.nextInt(anyInt())).thenReturn(0);

		List<Student> expectedListStudentPresentSequence = new ArrayList<Student>();
		expectedListStudentPresentSequence.add(new Student(27765, 1, 1));
		expectedListStudentPresentSequence.add(new Student(22222, 1, 0));
		expectedListStudentPresentSequence.add(new Student(44444, 1, 0));
		expectedListStudentPresentSequence.add(new Student(33333, 0, 1));

		List<Student> testListStudentPresentSequence = rollCall
				.getRandomPresentationSequence(listStudent);

		assertEquals(expectedListStudentPresentSequence,
				testListStudentPresentSequence);
	}

	public void isolationRandom_GetRandomPresentationSequence_ReturnList_RandomFactory() {
		IRandom fakeRandom = mock(IRandom.class);
		RandomFactory.setRandom(fakeRandom);
		RollCall rollCall = new RollCall(RandomFactory.create());
		List<Student> listStudent = getListStudentValid();

		when(fakeRandom.nextInt(anyInt())).thenReturn(0);

		List<Student> expectedListStudentPresentSequence = new ArrayList<Student>();
		expectedListStudentPresentSequence.add(new Student(27765, 1, 1));
		expectedListStudentPresentSequence.add(new Student(22222, 1, 0));
		expectedListStudentPresentSequence.add(new Student(44444, 1, 0));
		expectedListStudentPresentSequence.add(new Student(33333, 0, 1));

		List<Student> testListStudentPresentSequence = rollCall
				.getRandomPresentationSequence(listStudent);

		assertEquals(expectedListStudentPresentSequence,
				testListStudentPresentSequence);
	}

	@Test
	public void isolationRandom_GetRandomPresentationSequence_ReturnList_RandomOverride() {
		IRandom fakeRandom = mock(IRandom.class);
		RollCall rollCall = new RollCall(fakeRandom);
		List<Student> listStudent = getListStudentValid();

		when(fakeRandom.nextInt(anyInt())).thenReturn(0);

		List<Student> expectedListStudentPresentSequence = new ArrayList<Student>();
		expectedListStudentPresentSequence.add(new Student(27765, 1, 1));
		expectedListStudentPresentSequence.add(new Student(22222, 1, 0));
		expectedListStudentPresentSequence.add(new Student(44444, 1, 0));
		expectedListStudentPresentSequence.add(new Student(33333, 0, 1));

		List<Student> testListStudentPresentSequence = rollCall
				.getRandomPresentationSequence(listStudent);

		assertEquals(expectedListStudentPresentSequence,
				testListStudentPresentSequence);
	}

	public Student getStudentValid() {
		Student student = new Student(27765, 1, 1);

		return student;
	}

	public List<Student> getListStudentValid() {
		List<Student> listStudent = new ArrayList<Student>();

		listStudent.add(new Student(27765, 1, 1));
		listStudent.add(new Student(11111, 0, 0));
		listStudent.add(new Student(22222, 1, 0));
		listStudent.add(new Student(33333, 0, 1));
		listStudent.add(new Student(44444, 1, 0));

		return listStudent;
	}

	public File getCsvFileStudentValid(String filePath) {
		File file = new File(filePath);

		try {
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
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return file;
	}

	public File getCsvFileStudentNotValid(String filePath) {
		File file = new File(filePath);

		try {
			FileWriter fileWriter = new FileWriter(filePath);

			fileWriter.append("abcde");
			fileWriter.append(',');
			fileWriter.append("f");
			fileWriter.append(',');
			fileWriter.append("g");
			fileWriter.append('\n');

			fileWriter.flush();
			fileWriter.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return file;
	}

}
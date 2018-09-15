package com.linkedin.dataentry;

import com.opencsv.CSVReader;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.support.Repositories;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Read files that are defined in FILE_ARRAY.
 * The filename MUST be the same with the Entity (the first letter capital)
 * In order to save uses the repository that is created for each object
 * which MUST have the structure EntityRepository!
 * 
 * The first line of csv file must be the field (first letter capital)
 * Warning it doesn't work if the are @ManyToOne or OneToMany etc.
 * 
 * If can't be cast it returns NULL
 * If can't found setter the whole file fails and continue to the next one
 * If can't find repository fails and continue to the next file
 */
@Component
public class DatabaseCsvReader {
	private final String[] FILE_ARRAY = new String[]{"database_files/User.csv" , "database_files/Post.csv" ,"database_files/Comment.csv"};
	private final String ENTITIES_PACKAGE_NAME = "com.linkedin.entities.database";

	private final Repositories repositories;

	private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	@Autowired
	public DatabaseCsvReader(ListableBeanFactory listableBeanFactory) {
		this.repositories = new Repositories(listableBeanFactory);
	}

	public void readFiles() {
		for (String fileName : FILE_ARRAY) {
			try {
				FileReader fileReader = new FileReader(fileName);
				String className = Paths.get(fileName).getFileName().toString().replace(".csv", "");
				List<Object> objectsToCreate = readFile(fileReader, className);
				JpaRepository repository = getObjectRepositoryFromFileName(className);
				repository.saveAll(objectsToCreate);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("EDW SKAEI \n\n");
			}
		}
	}

	private List<Object> readFile(FileReader fileReader, String className) throws Exception {
		Class<?> objClass = getClass(className);
		CSVReader reader = new CSVReader(fileReader);

		List<Method> methodList = createMethodList(reader, objClass);

		List<Object> objectsToCreate = new ArrayList<>();
		String[] line = reader.readNext();
		while (line != null) {
			Object obj = createAndSetFields(objClass, methodList, Arrays.asList(line));
			objectsToCreate.add(obj);
			line = reader.readNext();
		}
		return objectsToCreate;
	}

	private List<Method> createMethodList(CSVReader reader, Class<?> objClass) throws Exception {
		List<String> headerColumnList = getHeaderColumnList(reader);
		List<Method> methods = new ArrayList<>();
		for (String headerName : headerColumnList) {
			Method setterFromName = findSetterFromName(objClass, headerName);
			methods.add(setterFromName);
		}
		return methods;
	}

	private JpaRepository getObjectRepositoryFromFileName(String className) throws Exception {
		Class<?> objectClass = getClass(className);
		return (JpaRepository) repositories.getRepositoryFor(objectClass).orElseThrow(() -> new Exception("repo not found"));
	}

	private Object createAndSetFields(Class<?> objClass, List<Method> methodList, List<String> valuesList) throws Exception {
		Object instance = objClass.getConstructor().newInstance();
		for (int i = 0; i < valuesList.size(); i++) {
			Method method = methodList.get(i);
			Object value = castValue(valuesList.get(i), method.getParameterTypes()[0]);
			method.invoke(instance, value);
		}
		return instance;
	}

	private Object castValue(String s, Class<?> targetClass) {
		try {
			if (targetClass.equals(Integer.class)) {
				return Integer.valueOf(s);
			} else if (targetClass.equals(Long.class)) {
				return Long.valueOf(s);
			} else if (targetClass.equals(Date.class)) {
				return dateFormat.parse(s);
			} else {
				return targetClass.cast(s);
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	private Method findSetterFromName(Class<?> objClass, String x) throws Exception {
		return Arrays.stream(objClass.getMethods())
						.filter((Method meth) -> meth.getName().equals("set" + x))
						.findFirst()
						.orElseThrow(() -> new Exception("message not found" +objClass.getName()));
	}

	private Class<?> getClass(String fileName) throws ClassNotFoundException {
		String className = Paths.get(fileName).getFileName().toString().replace(".csv", "");
		return Class.forName(ENTITIES_PACKAGE_NAME + "." + className);
	}

	private List<String> getHeaderColumnList(CSVReader reader) throws IOException {
		return Arrays.asList(reader.readNext());
	}
}

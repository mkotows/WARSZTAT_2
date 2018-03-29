package pl.coderslab.app;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import pl.coderslab.model.*;

public class InitApp {

	private static final String DROP_USERS_TABLE = "DROP TABLE IF EXISTS Users";
	private static final String DROP_USERS_GROUPS_TABLE = "DROP TABLE IF EXISTS Users_Groups";
	private static final String CREATE_USERS_GROUPS_TABLE = "CREATE TABLE Users_Groups (id INT AUTO_INCREMENT, name VARCHAR (255), PRIMARY KEY(id))";
	private static final String CREATE_USERS_TABLE = "CREATE TABLE Users (id BIGINT AUTO_INCREMENT, username VARCHAR(255), email VARCHAR (255)UNIQUE, password VARCHAR(245),user_group_id INT,PRIMARY KEY (id), FOREIGN KEY (user_group_id) REFERENCES Users_Groups(id) ON DELETE SET NULL)";
	private static final String CREATE_SOLUTIONS_TABLE = "CREATE TABLE Solutions (id INT AUTO_INCREMENT, created DATETIME, updated DATETIME, description TEXT, excercise_id INT, user_id BIGINT(20), PRIMARY KEY(id), FOREIGN KEY (excercise_id) REFERENCES Excercises(id) ON DELETE SET NULL, FOREIGN KEY (user_id) REFERENCES Users (id) ON DELETE SET NULL)";
	private static final String CREATE_EXCERCISES_TABLE = "CREATE TABLE Exercises (id INT AUTO_INCREMENT, title VARCHAR(255), description TEXT, PRIMARY KEY (id))";

	public static void main(String[] args) {

		try (Connection conn = DriverManager.getConnection(

				"jdbc:mysql://localhost:3306/warsztaty?useSSL=false&characterEncoding=utf8", "root", "coderslab")) {

			
			
			// Statement statement = conn.createStatement();
			// statement.executeUpdate(DROP_USERS_TABLE);
			// statement.executeUpdate(DROP_USERS_GROUPS_TABLE);
			//
			// statement.executeUpdate(CREATE_USERS_GROUPS_TABLE );
			// statement.executeUpdate(CREATE_USERS_TABLE);

//			 Solution solution = new Solution();
//			 solution.delete(conn);
//			 solution.setDescription("99cos");
//			 solution.setUsers_id(4);
//			 solution.setExcercise_id(3);
//			 solution.saveToDB(conn);
			// System.out.println(solution.getDescription());
			// System.out.println(solution.getExcercise_id());
			// System.out.println(solution.getCreated());

			// Excercise excercise = Excercise.loadExcerciseById(conn, 1);
			// System.out.println(excercise.getId());
			// System.out.println(excercise.getTitle());
			// System.out.println(excercise.getDescription());
			// excercise.setDescription("cos2");
			// excercise.setTitle("a2");
			// excercise.saveToDB(conn);

			// Group group = Group.loadGroupById(conn, 2);
			// System.out.println(group.getName());
			// group.setName("c");
			// group.saveToDB(conn);
			// group.delete(conn);

			// User user = User.loadUserById(conn, 2);
			// user.delete(conn);
			// user.setPassword("aa");
			// user.setUserGroupId(1);
			// user.setEmail("3233a@dad");
			// user.setUsername("pierwszy");
			// user.saveToDB(conn);

//			Solution[] nnn = Solution.loadAll(conn);
//			System.out.println(nnn[0].getDescription());
//			System.out.println(nnn[0].getCreated());
//			System.out.println(nnn[1].getExcercise_id());
//			System.out.println(nnn[1].getUsers_id());

			System.out.println("OK");
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
}

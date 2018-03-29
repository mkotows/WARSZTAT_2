package pl.coderslab.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import pl.coderslab.model.*;

public class Program_1 {


	public static void main(String[] args) {

		try (Connection conn = DriverManager.getConnection(

				"jdbc:mysql://localhost:3306/warsztaty?useSSL=false&characterEncoding=utf8", "root", "coderslab")) {

			
			
			Scanner scan = new Scanner(System.in);
			String choice="";
			
			User.showAllUsersAndGetIds(conn);
			while (choice != "quit") {
				ArrayList<Integer> usersId;
				Integer id=0;
				printMenu_1();
				choice = scan.nextLine();
				
				switch (choice) {
				case "add":
					User user1 = newUser(scan);
					user1.saveToDB(conn);
					break;
				case "edit":
					usersId= User.showAllUsersAndGetIds(conn);
					System.out.println("Którego użytkownika chcesz edytować? Jeśli chcesz anulować wybierz - 0");
					id = scanInt(scan);
					if(id!=0) {
						if(usersId.contains(id)) {
							User user2 = User.loadUserById(conn, id);
							user2.editUser(scan,user2);
							user2.saveToDB(conn);
						} else {
							System.out.println("Nie ma użytkownika o takim numerze!");
						}
					} else {
						System.out.println("Anulowano");
					}
					break;

				case "delete":
					usersId= User.showAllUsersAndGetIds(conn);
					System.out.println("Którego użytkownika chcesz usunąć? Jeśli chcesz anulować wybierz - 0");
					id = scanInt(scan);
					if(id!=0) {
						if(usersId.contains(id)) {
							User.loadUserById(conn, id).delete(conn);
						}
						else {
							System.out.println("Nie ma użytkownika o takim numerze!");						}
					}else {
						System.out.println("Anulowano");
					}
					break;
				case "quit":
					choice="quit";
					System.out.println("Bye bye");
					break;
				}
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	private static Integer scanInt(Scanner scan) {
		
		while (!scan.hasNextInt()) {
			System.out.println("Podaj liczbę!!");
			scan.next();
		}
		Integer id =scan.nextInt();
		scan.nextLine();
		return id;
	}

	private static void printMenu_1() {
		System.out.println("Wybierz jedną z opcji: ");
		System.out.println("add:    dodaj nowego użytkownika ");
		System.out.println("edit:   edytuj użytkownika ");
		System.out.println("delete: usuń użytkownika");
		System.out.println("quit:   wyjście ");
	}

	private static User newUser(Scanner scan) {
		User user = new User();
		System.out.println("Podaj imię: ");
		user.setUsername(scan.nextLine());
		System.out.println("Podaj email: ");
		user.setEmail(scan.nextLine());
		System.out.println("Podaj hasło: ");
		user.setPassword(scan.nextLine());
		System.out.println("Podaj nr grupy: ");
		user.setUserGroupId(scan.nextInt());
		
		return user;
	}

}


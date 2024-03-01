import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Cli.AdminCli;
import Cli.Cli;
import Cli.StudentCli;
import Lists.CollegesList;
import Lists.StudentsList;
import Models.College;
import Models.Student;

public class Application {
    Map<String, String> users = new HashMap<>();
    private boolean isAdmin = false;
    Cli cli;
    private String StudentId;
    private int type;

    public void run() {

        setAllUsers();
        Scanner sc = new Scanner(System.in);
        type = 0;
        askForLogin(sc);

        if (isAdmin) {
            cli = new AdminCli();
        } else {
            cli = new StudentCli(StudentId);
        }
        cli.processCommand("colleges");
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.length() > 0)
                cli.processCommand(line);
        }
    }

    public void askForLogin(Scanner sc) {
        System.out.println("Welcome to the College Management System");
        System.out.println("1. Login\n2. Register");
        type = sc.nextInt();
        if (type == 1) {
            System.out.println("Login");
            login();
        } else if (type == 2) {
            System.out.println("Register");
            register();
        } else {
            askForLogin(sc);
        }
    }

    public void setAllUsers() {
        try (FileInputStream reader = new FileInputStream(new File("p1\\DataBase\\User.txt"))) {
            Scanner scanner = new Scanner(reader);
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(",");
                users.put(line[0], line[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void login() {
        String[] userInfo = loginPage();
        if (userInfo[0].equals("Admin") && userInfo[1].equals("123456")) {
            isAdmin = true;
            System.out.println("Login successful by Admin");
            StudentId = "Admin";
        }
        while (!users.containsKey(userInfo[0]) ||
                !userInfo[1].equals(users.get(userInfo[0]))) {
            System.out.println("Login failed");
            userInfo = loginPage();
        }
        if (userInfo[0].equals("Admin")) {
            isAdmin = true;
            System.out.println("Login successful by Admin");
        } else
            System.out.println("Login successful");
        StudentId = userInfo[0];
        // return "402170121";

    }

    public String[] loginPage() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter username: ");
        String username = sc.nextLine();
        System.out.println("Enter password: ");
        String password = sc.nextLine();
        // if(users.containsKey(username) && users.get(username).equals(password)){
        return new String[] { username, password };
        // }
    }

    public void register() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String name = sc.nextLine();
        System.out.println("Enter your studentId: ");
        String studentId = sc.nextLine();
        while (studentId.length() != 9 || !studentId.matches("[0-9]+")) {
            System.out.println("Enter your studentId: ");
            studentId = sc.nextLine();
        }
        System.out.println("Enter your password: ");
        String password = sc.nextLine();

        // colleges
        CollegesList collegesList = new CollegesList();
        for (College college : collegesList.getColleges()) {
            System.out.printf("%-6s%-8s\n", "id", "name");
            System.out.println(college.show());
        }
        System.out.println();
        System.out.println("Enter your college id: ");
        int college_id = sc.nextInt();
        users.put(studentId, password);
        try {
            File file = new File("p1\\DataBase\\User.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            Scanner scanner = new Scanner(file);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.write(studentId + "," + password);
            writer.newLine();
            writer.flush();
            writer.close();
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        StudentsList studentsList = new StudentsList();
        Student student = new Student(studentsList.findNewId(), name, Integer.parseInt(studentId), college_id,
                new ArrayList<>());

        studentsList.addStudent(student);
        StudentId = studentId;
    }

    public void logout() {
        isAdmin = false;
        run();
    }
}

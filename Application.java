import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Cli.AdminCli;
import Cli.Cli;
import Cli.StudentCli;

public class Application {
    Map<String, String> users = new HashMap<>();
    private boolean isAdmin = false;
    Cli cli;
    public void run() {
        setAllUsers();
        String StudentId = login();
        if (isAdmin) {
            cli = new AdminCli();
        } else {
            cli = new StudentCli(StudentId);
        }
        Scanner sc = new Scanner(System.in);
        cli.processCommand("colleges");
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            cli.processCommand(line);
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

    public String login() {
        String[] userInfo = loginPage();
        if (userInfo[0].equals("Admin") && userInfo[1].equals("123456")) {
            isAdmin = true;
            System.out.println("Login successful by Admin");
            return "Admin";
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
        return userInfo[0];
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
}

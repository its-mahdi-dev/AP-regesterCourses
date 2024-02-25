import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Cli.Cli;

public class Application {
    Map<String, String> users = new HashMap<>();

    public void run() {
        setAllUsers();
        String StudentId = login();
        Cli cli = new Cli(StudentId);
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
        // String[] userInfo = loginPage();
        // while (!users.containsKey(userInfo[0]) ||
        // !userInfo[1].equals(users.get(userInfo[0]))) {
        // System.out.println("Login failed");
        // userInfo = loginPage();
        // }
        System.out.println("Login successful");
        // return userInfo[0];
        return "402170121";

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

import java.util.*;
import java.io.*;

public class Main {
    static ArrayList<Skill> skills = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("===== SkillTrack: Learning Tracker =====");
            System.out.println("1. Add New Skill");
            System.out.println("2. Update Progress");
            System.out.println("3. View All Skills");
            System.out.println("4. Delete Skill");
            System.out.println("5. Save Progress");
            System.out.println("6. Load Progress");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    addSkill();
                    break;
                case 2:
                    updateSkill();
                    break;
                case 3:
                    viewSkills();
                    break;
                case 4:
                    deleteSkill();
                    break;
                case 5:
                    saveToFile();
                    break;
                case 6:
                    loadFromFile();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (choice != 0);
    }

    static void addSkill() {
        System.out.print("Enter skill name: ");
        String name = sc.nextLine();
        System.out.print("Enter progress (0-100): ");
        int progress = sc.nextInt();
        sc.nextLine(); // Clear buffer

        Skill skill = new Skill(name, progress);
        skills.add(skill);
        System.out.println("Skill added.\n");
    }

    static void updateSkill() {
        System.out.print("Enter skill name to update: ");
        String name = sc.nextLine();
        for (Skill s : skills) {
            if (s.getName().equalsIgnoreCase(name)) {
                System.out.print("Enter new progress (0-100): ");
                int p = sc.nextInt();
                sc.nextLine();
                s.setProgress(p);
                System.out.println("Progress updated.\n");
                return;
            }
        }
        System.out.println("Skill not found.\n");
    }

    static void viewSkills() {
        if (skills.isEmpty()) {
            System.out.println("No skills to display.\n");
            return;
        }
        for (Skill s : skills) {
            System.out.println(s);
        }
    }

    static void deleteSkill() {
        System.out.print("Enter skill name to delete: ");
        String name = sc.nextLine();
        for (int i = 0; i < skills.size(); i++) {
            if (skills.get(i).getName().equalsIgnoreCase(name)) {
                skills.remove(i);
                System.out.println("Skill deleted.\n");
                return;
            }
        }
        System.out.println("Skill not found.\n");
    }

    static void saveToFile() {
        try (PrintWriter writer = new PrintWriter("skills.txt")) {
            for (Skill s : skills) {
                writer.println(s.toFileString());
            }
            System.out.println("Progress saved to file.\n");
        } catch (Exception e) {
            System.out.println("Error saving to file.\n");
        }
    }

    static void loadFromFile() {
        skills.clear();
        try (Scanner fileScanner = new Scanner(new File("skills.txt"))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                Skill s = Skill.fromFileString(line);
                skills.add(s);
            }
            System.out.println("Progress loaded from file.\n");
        } catch (Exception e) {
            System.out.println("Error loading from file.\n");
        }
    }
}

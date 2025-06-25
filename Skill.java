// File: Skill.java
import java.time.LocalDate;

public class Skill {
    private String name;
    private int progress; // 0 to 100
    private LocalDate lastUpdated;

    public Skill(String name, int progress) {
        this.name = name;
        this.progress = progress;
        this.lastUpdated = LocalDate.now();
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getProgress() {
        return progress;
    }

    public String getLastUpdated() {
        return lastUpdated.toString();
    }

    // Setters
    public void setProgress(int progress) {
        this.progress = progress;
        this.lastUpdated = LocalDate.now();
    }

    @Override
    public String toString() {
        return "Skill: " + name + "\nProgress: " + progress + "%" + "\nLast Updated: " + lastUpdated + "\n";
    }

    public String toFileString() {
        return name + "|" + progress + "|" + lastUpdated;
    }

    public static Skill fromFileString(String line) {
        String[] parts = line.split("\\|");
        Skill skill = new Skill(parts[0], Integer.parseInt(parts[1]));
        skill.lastUpdated = LocalDate.parse(parts[2]);
        return skill;
    }
}


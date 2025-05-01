import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHashGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // Generate teacher hash
        String teacherPassword = "root";
        String teacherHash = encoder.encode(teacherPassword);
        System.out.println("Teacher hash for '" + teacherPassword + "': " + teacherHash);

        // Generate admin hash
        String adminPassword = "AymenSama";
        String adminHash = encoder.encode(adminPassword);
        System.out.println("Admin hash for '" + adminPassword + "': " + adminHash);
    }
}
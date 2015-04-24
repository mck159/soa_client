package dziekanat;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by root on 23.04.15.
 */
public class SoaClient {
    public static void main(String args[]) {
        Authenticator.setDefault(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("maciek", "test".toCharArray());
            }
        });

        MainService service = new MainService();
        Main main = service.getMainPort();

        System.out.println("Powitanie");
        String helloMessage = main.hi("Maciek");
        System.out.println(helloMessage);
        System.out.println("Pobieranie listy studentów");
        List<Student> students = main.getStudents()  ;
        for(Student student : students) {
            System.out.printf(
                    "IMIĘ: %s\nNAZWISKO: %s\nPESEL: %s\nPRZEDMIOTY: %s\n\n",
                    student.getName(), student.getSurname(),
                    student.getPesel(),
                    student.getSubjects().getSubject().toString()
            );
        }
        String pesel = "P1";
        System.out.printf("Pobieranie studenta o peselu %s\n", pesel);
        Student student = main.getStudent(pesel);
        System.out.printf(
                "IMIĘ: %s\nNAZWISKO: %s\nPESEL: %s\nPRZEDMIOTY: %s\n\n",
                student.getName(), student.getSurname(),
                student.getPesel(),
                student.getSubjects().getSubject().toString()
        );
    }
}

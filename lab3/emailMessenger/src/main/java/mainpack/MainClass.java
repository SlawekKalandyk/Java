package mainpack;

import java.util.*;


public class MainClass {
    public static void main(String[] args) {
        EmailMessage msg = EmailMessage.builder()
                .addFrom("slawomir.kalandyk@gmail.com")
                .addTo("slawomir.kalandyk@gmail.com")
                .addSubject("Mail testowy")
                .addContent("Brak tresci")
                .build();

        msg.send();
    }
}
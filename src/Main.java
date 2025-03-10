import java.io.*;
import java.nio.file.AccessDeniedException;
import java.util.Scanner;

public class Main {

    /*
    Napiši Java program koji omogućuje kopiranje izvorne binarne datoteke.

    Tražite od korisnika putanju do originalne datoteke (npr. C:/temp/original.pdf)
    Tražite od korisnika naziv destinacijske datoteke (npr. C:/temp/ kopija.pdf)
    Koristeći FileInputStream i FileOutputStream, kopirajte byte po byte iz izvorne datoteke u destinacijsku
    Obradite sve moguće potencijalne iznimke (putanja ne postoji, nemate prava za pisanje ili čitanje, ...)
    Po završetku, provjerite je li kopija uspješno napravljena, te ju pokušajte otvoriti kroz File Explorer.
    Prouči klasu java.io.File. Pronađi metode exists() i delete(). Provjerite programski je datoteka prethodno uspješno
    kopirana (metoda exists) I ako je, pobrisite ju (metoda delete)
    */
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
        System.out.println("Unesite putanju do originalne datoteke: ");
        Scanner sc = new Scanner(System.in);

        String original = sc.nextLine();
        try (BufferedInputStream ulazniFile = new BufferedInputStream(new FileInputStream(original))) {
            System.out.println("Unesite naziv kopirane datoteke: ");

            String kopija = sc.nextLine();

            BufferedOutputStream izlazniFile = new BufferedOutputStream(new FileOutputStream(kopija));
            int c;
            while ((c = ulazniFile.read()) != -1) {
                izlazniFile.write((char) c);
            }
            izlazniFile.close();

            File kopijaFile = new File(kopija);
            System.out.println("Zelite li izbrisati kopiju datoteke(D/N)?");
            String odgovor = sc.nextLine();
            if (kopijaFile.exists() && odgovor.equals("D")) {
                System.out.println("Datoteka " + kopija + " izbrisana");
                kopijaFile.delete();
            }

        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (AccessDeniedException e) {
            System.err.println(e.getMessage());
        }
        sc.close();
    }
}
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String booksListFile = "homeLibrary.dat";

        System.out.println("1 - Add a book to the library");
        System.out.println("2 - Save and exit");
        boolean exit = false;

        List<Book> library = getBooks(booksListFile);

        for (Book book : library) {
            System.out.println(book);
        }

        do {
            switch (scanner.nextInt()) {
                case 1: {
                    System.out.println("Enter book's name: ");
                    String name = scanner.next();
                    System.out.println("Enter author of the book: ");
                    String author = scanner.next();
                    System.out.println("Enter the year of publishing: ");
                    String date = scanner.next();
                    library.add(new Book(name, author, date));

                    try (OutputStream os = new FileOutputStream(booksListFile);
                         ObjectOutputStream oos = new ObjectOutputStream(os)) {
                        oos.writeObject(library);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
                case 2: {
                    exit = true;
                    break;
                }
            }
        } while (!exit);
    }

    public static List<Book> getBooks(String file) {
        if (new File(file).exists()) {
            try (InputStream is = new FileInputStream(file);
                 ObjectInputStream ois = new ObjectInputStream(is)) {
                return ((List<Book>) ois.readObject());
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("Can not find file!");
        return new ArrayList<>();
    }
}

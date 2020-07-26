package test;

import java.io.*;

public class LibraryApp {
    public static void main(String[] args) {
        Book book = new Book("Сказки", "А.С.Пушкин", "1994");
        Book book2 = new Book("Вишневый сад", "А.П.Чехов", "1995");

        try (OutputStream os = new FileOutputStream("booksList.dat", true);
             ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(book);
            oos.writeObject(book2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (InputStream is = new FileInputStream("booksList.dat");
             ObjectInputStream ois = new ObjectInputStream(is)) {
            while (is.available() > 0){
                Book object = (Book) ois.readObject();
                System.out.println(object);
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

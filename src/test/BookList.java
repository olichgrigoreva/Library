package test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookList {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();

        /*books.add(new Book("Маленький принц", "Антуан де Сант Экзюпери", "1234"));
        books.add(new Book("Илон Маск", "Эшли Вэнс", "2014"));
        books.add(new Book("Война и мир", "Л.Н.Толстой", "1854"));*/

        books.add(new Book("Илон Маск", "Эшли Вэнс", "2014"));
        books.add(new Book("Война и мир", "Л.Н.Толстой", "1854"));

        /*Book book = new Book("Сказки", "А.С.Пушкин", "1994");
        Book book2 = new Book("Вишневый сад", "А.П.Чехов", "1995");*/

        try (OutputStream os = new FileOutputStream("booksList2.dat");
             ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(books);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (InputStream is = new FileInputStream("booksList2.dat");
             ObjectInputStream ois = new ObjectInputStream(is)) {
            List<Book> booksList = (List<Book>) ois.readObject();
            /*while (is.available() > 0){
                Book object = (Book) ois.readObject();
                System.out.println(object);
            }*/
            for(Book book:booksList){
                System.out.println(book);
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

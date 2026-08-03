package ir.maktabsharif.console;

import ir.maktabsharif.enums.StockStatus;
import ir.maktabsharif.exception.BusinessException;
import ir.maktabsharif.model.Book;
import ir.maktabsharif.model.PublisherAddress;
import ir.maktabsharif.service.book.BookService;
import ir.maktabsharif.service.book.impl.BookServiceImpl;
import ir.maktabsharif.util.HibernateUtil;

import java.math.BigDecimal;
import java.util.Scanner;

public class MainApp {
    static void main() {
        Scanner scanner = new Scanner(System.in);
        BookService bookService  = new BookServiceImpl();

        while (true) {
            System.out.println("""
                    ======== Digital Library Management System ========
                    1. Add Book
                    2. Find Book By ID
                    3. Update Book
                    4. Delete Book
                    0. Exit
                    """);

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    try {
                        System.out.println("\n------ Adding Book ------\n");
                        System.out.println("Enter Book's Title: ");
                        String title = scanner.nextLine();
                        System.out.println("Enter Book's ISBN: ");
                        String isbn = scanner.nextLine();
                        System.out.println("Enter Book's Published Year: ");
                        int publishedYear = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Enter Book's Price: ");
                        BigDecimal price = scanner.nextBigDecimal();
                        scanner.nextLine();
                        System.out.println("Enter Book's Status(IN_STOCK, OUT_OF_STOCK, COMING_SOON)");
                        String statusString = scanner.nextLine();
                        StockStatus status = StockStatus.valueOf(statusString);
                        System.out.println("Entering Book's Publisher Address...");
                        System.out.println("Enter Publisher's City: ");
                        String city = scanner.nextLine();
                        System.out.println("Enter Publisher's Street: ");
                        String street = scanner.nextLine();
                        System.out.println("Enter Publisher's Postal Code: ");
                        String postalCode = scanner.nextLine();
                        PublisherAddress publisherAddress = new PublisherAddress(city, street, postalCode);

                        Book book = bookService.register(new Book(title, isbn, publishedYear, price, status, publisherAddress));

                        System.out.println("\nBook Added Successfully!\n" + book);
                    }
                    catch (BusinessException e) {
                        System.err.println(e.getMessage());
                    }
                case 2:
                    try {
                        System.out.println("\n----- Finding Book By ID ------\n");
                        System.out.println("Enter Book's ID: ");
                        Long id = scanner.nextLong();
                        scanner.nextLine();

                        System.out.println(bookService.getById(id));
                    }
                    catch (BusinessException e) {
                        System.err.println(e.getMessage());
                    }
                case 3:
                    try {
                        System.out.println("\n------ Updating Book ------\n");
                        System.out.println("Enter Book's ID: ");
                        Long id = scanner.nextLong();
                        scanner.nextLine();
                        System.out.println("Enter New Title: ");
                        String title = scanner.nextLine();
                        System.out.println("Enter New ISBN: ");
                        String isbn = scanner.nextLine();
                        System.out.println("Enter New Published Year: ");
                        int publishedYear = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Enter New Price: ");
                        BigDecimal price = scanner.nextBigDecimal();
                        scanner.nextLine();
                        System.out.println("Enter New Status(IN_STOCK, OUT_OF_STOCK, COMING_SOON)");
                        String statusString = scanner.nextLine();
                        StockStatus status = StockStatus.valueOf(statusString);
                        System.out.println("Entering New Publisher Address...");
                        System.out.println("Enter New City: ");
                        String city = scanner.nextLine();
                        System.out.println("Enter New Street: ");
                        String street = scanner.nextLine();
                        System.out.println("Enter New Postal Code: ");
                        String postalCode = scanner.nextLine();

                        PublisherAddress publisherAddress = new PublisherAddress(city, street, postalCode);

                        Book book = new Book(title, isbn, publishedYear, price, status, publisherAddress);
                        book.setId(id);

                        Book newBook = bookService.update(book);

                        System.out.println("\nBook Updated Successfully!\n" + newBook);
                    }
                    catch (BusinessException e) {
                        System.err.println(e.getMessage());
                    }
                case 4:
                    try {
                        System.out.println("\n------ Deleting Book ------\n");
                        System.out.println("Enter Book's ID: ");
                        Long id = scanner.nextLong();
                        scanner.nextLine();

                        bookService.delete(id);

                        System.out.println("\nBook Deleted Successfully!");
                    }
                    catch (BusinessException e) {
                        System.err.println(e.getMessage());
                    }
                case 0:
                    System.out.println("Exiting Program...");
                    HibernateUtil.closeEmf();
                    return;
                default:
                    System.out.println("Invalid Choice!\nTry Again.");
            }
        }
    }
}

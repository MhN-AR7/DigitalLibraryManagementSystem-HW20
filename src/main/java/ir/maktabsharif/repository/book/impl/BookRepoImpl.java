package ir.maktabsharif.repository.book.impl;

import ir.maktabsharif.model.Book;
import ir.maktabsharif.repository.book.BookRepo;
import ir.maktabsharif.util.HibernateUtil;

import java.util.Optional;

public class BookRepoImpl implements BookRepo {
    @Override
    public void insert(Book book) {
        HibernateUtil.inTxResult(
                em -> {
                    em.persist(book);
                    return book;
                }
        );
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(HibernateUtil.inTxResult(em -> em.find(Book.class, id)));
    }

    @Override
    public boolean update(Book book) {
        return HibernateUtil.inTxResult(
                em -> {
                    Book existingBook = em.find(Book.class, book.getId());

                    if (existingBook == null) return null;

                    existingBook.setTitle(book.getTitle());
                    existingBook.setIsbn(book.getIsbn());
                    existingBook.setPublishedYear(book.getPublishedYear());
                    existingBook.setPrice(book.getPrice());
                    existingBook.setStatus(book.getStatus());
                    existingBook.setPublisherAddress(book.getPublisherAddress());

                    return existingBook;
                }
        ) != null;
    }

    @Override
    public boolean delete(Long id) {
        return HibernateUtil.inTxResult(
                em -> {
                    Book existingBook = em.find(Book.class, id);

                    if (existingBook == null) return null;

                    em.remove(existingBook);

                    return existingBook;
                }
        ) != null;
    }
}

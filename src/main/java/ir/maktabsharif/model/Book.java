package ir.maktabsharif.model;

import ir.maktabsharif.enums.StockStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "books")
public class Book extends BaseModel<Long> {
    @Column(nullable = false)
    private String title;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(name = "published_year")
    private int publishedYear;

    @Column(nullable = false, columnDefinition = "check price > 0")
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private StockStatus status;

    @Embedded
    private PublisherAddress publisherAddress;

    public Book() {
    }

    public Book(String title, String isbn, int publishedYear, BigDecimal price, StockStatus status, PublisherAddress publisherAddress) {
        this.title = title;
        this.isbn = isbn;
        this.publishedYear = publishedYear;
        setPrice(price);
        this.status = status;
        this.publisherAddress = publisherAddress;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price.setScale(2, RoundingMode.HALF_UP);
    }

    public StockStatus getStatus() {
        return status;
    }

    public void setStatus(StockStatus status) {
        this.status = status;
    }

    public PublisherAddress getPublisherAddress() {
        return publisherAddress;
    }

    public void setPublisherAddress(PublisherAddress publisherAddress) {
        this.publisherAddress = publisherAddress;
    }

    @Override
    public String toString() {
        return String.format("""
                ID: %d | Title: %s | ISBN: %s
                Published Year: %d | Price: %.2f | Status: %s
                Publisher Address: %s
                """, this.getId(), title, isbn, publishedYear, price, status.toString(), publisherAddress);
    }
}

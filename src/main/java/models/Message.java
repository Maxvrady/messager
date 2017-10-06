package models;


import javax.persistence.*;

@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "author")
    private String author;

    @Column(name = "to_send")
    private String to_send;

    @Column(name = "text")
    private String text;

    public Message() {
    }

    public Message(String author, String to_send, String text) {
        this.author = author;
        this.to_send = to_send;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTo_send() {
        return to_send;
    }

    public void setTo_send(String to_send) {
        this.to_send = to_send;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

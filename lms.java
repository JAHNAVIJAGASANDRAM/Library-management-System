 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;


class LBook<T>{
    private String title;
    private String author;
    private T isbn;
    private boolean isIssued;
    public LBook(String title,String author,T isbn)
    {
        this.title=title;
        this.author=author;
        this.isbn=isbn;
        this.isIssued=false;
    }
    public String getTitle()
    {
        return title;
    }
    public String getAuthor()
    {
        return author;
    }
    public T getIsbn()
    {
        return isbn;
    }

    public boolean isIssued() {
        return isIssued;
    }
    public void issueBook()
    {
        if(!isIssued())
        {
            isIssued=true;
            System.out.println(" book  issued :"+title);
        }
        else {
            System.out.println("book already issued"+title);
        }
    }
    public void returnBook()
    {
        if(isIssued())
        {
            isIssued=false;
            System.out.println(" book  returned :"+title);
        }
        else {
            System.out.println("book was not issued"+title);
        }
    }

    @Override
    public String toString()
    {
        return "Book { " +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", isIssued=" + isIssued +
                " }";
    }

}
class Library<T>{
    private java.util.List<LBook<T>> books ;
    public Library()
    {
        books=new ArrayList<>();
    }
    public void addBook( LBook<T> book)
    {
        books.add(book);
    }
    public void removeBook(LBook<T>book){
        books.remove(book);
    }
    public List<LBook<T>> getBooks(){
        return books;
    }
    public void displayBook(JTextArea textArea){
        StringBuilder sbooks=new StringBuilder();
        for(LBook <T> book : books){
            sbooks.append(book).append("\n");
        }
        textArea.setText(sbooks.toString());
    }
    public LBook<T> findBookByIsbn(T isbn){
        for(LBook<T> book:books)
        {
            if(book.getIsbn().equals(isbn))
            {
                return book;
            }

        }
        return null;
    }

}

public lms{
public class guilms extends JFrame{
    private Library<String> library;
    private JTextField titeField,authorField,isbnField;
    private JTextArea displayArea;
    private JButton addButton,issueButton,returnButton;
    public guilms()
    {
        library=new Library<>();
        initializeUI();
    }
    private void initializeUI()
    {
        setTitle("Library management System");
        setSize(800,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel inputPanel=new JPanel();
        inputPanel.setLayout(new GridLayout(4,2));
        inputPanel.setBackground(Color.CYAN);

        inputPanel.add(new JLabel("Title:"));
        titeField=new JTextField();
        inputPanel.add(titeField);

        inputPanel.add(new JLabel("Author:"));
        authorField=new JTextField();
        inputPanel.add(authorField);

        inputPanel.add(new JLabel("ISBN:"));
        isbnField=new JTextField();
        inputPanel.add(isbnField);

        addButton =new JButton("Add Book");
        addButton.setBackground(Color.GREEN); // Set button color
        addButton.setForeground(Color.WHITE);
        inputPanel.add(addButton);

        JPanel actionPanel=new JPanel();
        actionPanel.setBackground(Color.LIGHT_GRAY);
        issueButton = new JButton("Issue Book");
        issueButton.setBackground(Color.ORANGE);
        issueButton.setForeground(Color.WHITE);

        returnButton=new JButton("Return Book");
        returnButton.setBackground(Color.RED);
        returnButton.setForeground(Color.WHITE);
        actionPanel.add(issueButton);
        actionPanel.add(returnButton);

       displayArea=new JTextArea();
       displayArea.setEditable(false);
        displayArea.setBackground(Color.WHITE);
        displayArea.setForeground(Color.BLACK);
       JScrollPane scrollpane=new JScrollPane(displayArea);

       add(inputPanel,BorderLayout.NORTH);
       add(actionPanel,BorderLayout.CENTER);
       add(scrollpane,BorderLayout.SOUTH);
       addButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               addBook();
           }
       });
       issueButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               issueBook();
           }
       });
       returnButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               returnBook();
           }
       });
       setVisible(true);

    }
    private void addBook() {
        String title = titeField.getText();
        String author = authorField.getText();
        String isbn = isbnField.getText();
        if (title.isEmpty() || author.isEmpty() || isbn.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all the details");
            return;
        }
        LBook<String> newBook = new LBook<>(title, author, isbn);
        library.addBook(newBook);
        JOptionPane.showMessageDialog(this, "Book added succesfully");
        clearInputFields();
        library.displayBook(displayArea);
    }
    private void issueBook(){
        String isbn= JOptionPane.showInputDialog(this,"enter the isbn");
        if(isbn!=null && !isbn.isEmpty()){
            LBook<String> book=library.findBookByIsbn(isbn);
            if(book!=null && !book.isIssued() )
            {
                book.issueBook();
                JOptionPane.showMessageDialog(this," Book issued succesfully");
            }
            else{
                JOptionPane.showMessageDialog(this,"Book not found or wasn't issued");
            }
            library.displayBook(displayArea);
        }

    }
    private void returnBook()
    {
        String isbn= JOptionPane.showInputDialog(this,"enter the isbn to return");
        if(isbn!=null && !isbn.isEmpty()){
            LBook<String> book=library.findBookByIsbn(isbn);
            if(book!=null && book.isIssued() )
            {
                book.returnBook();
                JOptionPane.showMessageDialog(this," Book returned succesfully");
            }
            else{
                JOptionPane.showMessageDialog(this,"Book not found or wasn't issued");
            }
            library.displayBook(displayArea);
        }
    }
    private void clearInputFields()
    {
        titeField.setText("");
        authorField.setText("");
        isbnField.setText("");

    }
    public  static void main(String[] args){
        new guilms();
    }

}

}

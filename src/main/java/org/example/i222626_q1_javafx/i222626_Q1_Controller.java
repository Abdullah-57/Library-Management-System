package org.example.i222626_q1_javafx;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

class Book
{
    private String title;
    private String author;
    private String ISBN;
    private String availability;

    public Book(String title, String author, String ISBN, String availability)
    {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.availability = availability;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public String getISBN()
    {
        return ISBN;
    }

    public void setISBN(String ISBN)
    {
        this.ISBN = ISBN;
    }

    public String getAvailability()
    {
        return availability;
    }

    public void setAvailability(String availability)
    {
        this.availability = availability;
    }
}


public class i222626_Q1_Controller
{

    @FXML
    private TableView<Book> bookTableView;

    @FXML
    private TableColumn<Book, String> titleColumn;

    @FXML
    private TableColumn<Book, String> authorColumn;

    @FXML
    private TableColumn<Book, String> isbnColumn;

    @FXML
    private TableColumn<Book, String> availabilityColumn;

    @FXML
    private TextField titleTextField;

    @FXML
    private TextField authorTextField;

    @FXML
    private TextField isbnTextField;

    @FXML
    private TextField availabilityTextField;

    @FXML
    private MenuItem addMenuItem;

    @FXML
    private MenuItem editMenuItem;

    @FXML
    private MenuItem deleteMenuItem;

    @FXML
    private MenuItem searchMenuItem;

    @FXML
    private MenuItem viewAllMenuItem;

    @FXML
    private Button confirmButton;

    @FXML
    private Button clearButton;

    @FXML
    private Label status;

    @FXML
    private Label titleLabel;

    @FXML
    private Label authorLabel;

    @FXML
    private Label isbnLabel;

    @FXML
    private Label availabilityLabel;

    @FXML
    private Label selectDeleteLabel;

    @FXML
    private ImageView image;

    private static final String FILE_NAME = "book_data.csv";

    private Set<String> uniqueISBNs = new HashSet<>();

    String menuItemId;
    private ObservableList<Book> books = FXCollections.observableArrayList();

    @FXML
    void handleMenuItemSelection(ActionEvent event)
    {
        menuItemId = ((MenuItem) event.getSource()).getId();

        clearFields();

        switch (menuItemId)
        {
            case "addMenuItem":
                titleTextField.setVisible(true);
                titleLabel.setVisible(true);
                selectDeleteLabel.setVisible(false);
                titleLabel.setText("Title");

                authorTextField.setVisible(true);
                authorLabel.setVisible(true);
                selectDeleteLabel.setVisible(false);
                authorLabel.setText("Author");

                isbnTextField.setVisible(true);
                isbnLabel.setVisible(true);
                selectDeleteLabel.setVisible(false);
                isbnLabel.setText("ISBN");

                availabilityTextField.setVisible(true);
                availabilityLabel.setVisible(true);
                selectDeleteLabel.setVisible(false);
                availabilityLabel.setText("Availability");

                clearButton.setVisible(true);
                confirmButton.setVisible(true);

                break;

            case "editMenuItem":
                authorTextField.setVisible(true);
                authorLabel.setVisible(true);
                selectDeleteLabel.setVisible(false);
                authorLabel.setText("New Author");

                isbnTextField.setVisible(true);
                isbnLabel.setVisible(true);
                selectDeleteLabel.setVisible(false);
                isbnLabel.setText("Existing ISBN");

                titleTextField.setVisible(true);
                titleLabel.setVisible(true);
                selectDeleteLabel.setVisible(false);
                titleLabel.setText("New Title");

                availabilityTextField.setVisible(true);
                availabilityLabel.setVisible(true);
                selectDeleteLabel.setVisible(false);
                availabilityLabel.setText("New Availability");

                clearButton.setVisible(true);
                confirmButton.setVisible(true);

                break;

            case "deleteMenuItem":
                isbnTextField.setVisible(false);
                isbnLabel.setVisible(false);


                titleTextField.setVisible(false);
                titleLabel.setVisible(false);

                authorTextField.setVisible(false);
                authorLabel.setVisible(false);

                availabilityTextField.setVisible(false);
                availabilityLabel.setVisible(false);

                selectDeleteLabel.setVisible(true);

                clearButton.setVisible(true);
                confirmButton.setVisible(true);

                break;

            case "searchMenuItem":
                titleTextField.setVisible(true);
                titleLabel.setVisible(true);
                selectDeleteLabel.setVisible(false);
                titleLabel.setText("Title");

                authorTextField.setVisible(true);
                authorLabel.setVisible(true);
                selectDeleteLabel.setVisible(false);
                authorLabel.setText("Author");

                isbnTextField.setVisible(false);
                isbnLabel.setVisible(false);
                selectDeleteLabel.setVisible(false);

                availabilityTextField.setVisible(false);
                availabilityLabel.setVisible(false);
                selectDeleteLabel.setVisible(false);

                clearButton.setVisible(true);
                confirmButton.setVisible(true);

                break;

            case "viewAllMenuItem":
                titleTextField.setVisible(false);
                titleLabel.setVisible(false);
                selectDeleteLabel.setVisible(false);

                authorTextField.setVisible(false);
                authorLabel.setVisible(false);
                selectDeleteLabel.setVisible(false);

                isbnTextField.setVisible(false);
                isbnLabel.setVisible(false);
                selectDeleteLabel.setVisible(false);

                availabilityTextField.setVisible(false);
                availabilityLabel.setVisible(false);
                selectDeleteLabel.setVisible(false);

                clearButton.setVisible(false);
                confirmButton.setVisible(false);

                viewAllBooks(null);

                break;

            default:
                titleTextField.setVisible(false);
                titleLabel.setVisible(false);
                selectDeleteLabel.setVisible(false);

                authorTextField.setVisible(false);
                authorLabel.setVisible(false);
                selectDeleteLabel.setVisible(false);

                isbnTextField.setVisible(false);
                isbnLabel.setVisible(false);
                selectDeleteLabel.setVisible(false);

                availabilityTextField.setVisible(false);
                availabilityLabel.setVisible(false);
                selectDeleteLabel.setVisible(false);

                break;
        }
    }

    @FXML
    void handleNew()
    {
        books.clear();
        updateTableView(FXCollections.observableArrayList());
        clearFields();
        status.setVisible(false);
    }

    @FXML
    void handleOpen()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Book Data File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));

        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null)
        {
            loadBooksFromFile(selectedFile);
            updateTableView();
        }
    }

    @FXML
    void handleSave()
    {
        saveBooksToFile();
    }

    @FXML
    void handleSaveAs()
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Book Data File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));

        File selectedFile = fileChooser.showSaveDialog(new Stage());

        if (selectedFile != null)
        {
            saveBooksToFile(selectedFile);
        }
    }

    @FXML
    void handleExit()
    {
        System.exit(0);
    }

    @FXML
    void handleConfirmButton(ActionEvent event)
    {
        if (titleTextField.isVisible() && authorTextField.isVisible() && isbnTextField.isVisible() && availabilityTextField.isVisible())
        {
            if (menuItemId.equals("addMenuItem"))
            {
                addBook(null);
            }
            else if (menuItemId.equals("editMenuItem"))
            {
                editBook(null);
            }
        }
        else if (!isbnTextField.isVisible() && !titleTextField.isVisible() && !authorTextField.isVisible() && !availabilityTextField.isVisible())
        {
            deleteBook(null);
        }
        else if (titleTextField.isVisible())
        {
            searchBooks(null);
        }
    }

    @FXML
    void handleClearButton(ActionEvent event)
    {
        clearFields();
    }
    private void updateTableView()
    {
        ObservableList<Book> tableViewItems = FXCollections.observableArrayList();
        tableViewItems.addAll(books);
        bookTableView.setItems(tableViewItems);
    }

    private void updateTableView(ObservableList<Book> tableViewItems)
    {
        bookTableView.setItems(tableViewItems);
        bookTableView.refresh();
    }

    @FXML
    public void initialize()
    {
        titleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        authorColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAuthor()));
        isbnColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getISBN()));
        availabilityColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAvailability()));

        titleLabel.setVisible(false);
        titleTextField.setVisible(false);

        authorLabel.setVisible(false);
        authorTextField.setVisible(false);

        isbnLabel.setVisible(false);
        isbnTextField.setVisible(false);

        availabilityLabel.setVisible(false);
        availabilityTextField.setVisible(false);

        selectDeleteLabel.setVisible(false);

        clearButton.setVisible(false);
        confirmButton.setVisible(false);

        image.setVisible(true);

    }

    private void loadBooksFromFile(File file)
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            books.clear();
            String line;

            while ((line = reader.readLine()) != null)
            {
                String[] parts = line.split(",");

                if (parts.length == 4)
                {
                    String title = parts[0].trim();
                    String author = parts[1].trim();
                    String isbn = parts[2].trim();
                    String availability = parts[3].trim();
                    books.add(new Book(title, author, isbn, availability));
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
            showAlert("Error", "Failed to load book data from file.");
        }
    }

    private void saveBooksToFile()
    {
        saveBooksToFile(new File(FILE_NAME));
    }

    private void saveBooksToFile(File file)
    {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file)))
        {
            for (Book book : books)
            {
                writer.println(String.join(",", book.getTitle(), book.getAuthor(), book.getISBN(), book.getAvailability()));
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
            showAlert("Error", "Failed to save book data to file.");
        }
    }

    @FXML
    void addBook(ActionEvent event)
    {

        if (titleTextField.isVisible() && authorTextField.isVisible() && isbnTextField.isVisible())
        {
            String title = titleTextField.getText().trim();
            String author = authorTextField.getText().trim();
            String isbn = isbnTextField.getText().trim();
            String availability = availabilityTextField.getText().trim();

            String checkIsbn = isbnTextField.getText().trim();

            if (uniqueISBNs.contains(checkIsbn))
            {
                showAlert("Duplicate ISBN", "ISBN must be unique. This ISBN already exists.");
                return;
            }

            uniqueISBNs.add(checkIsbn);

            if (isValidTitle(title) && isValidAuthor(author) && isValidISBN(isbn))
            {
                Book newBook = new Book(title, author, isbn, availability);
                books.add(newBook);

                sortBooks();
                updateTableView();
                bookTableView.refresh();
                clearFields();
                status.setText("  Status: Book Added Successfully.");
                status.setVisible(true);
                System.out.println(status);
            }
            else
            {
                showAlert("Invalid Input", "Please provide valid Title, Author, and ISBN.");
            }
        }
        else
        {
            showAlert("Functionality Error", "Please select the Add Book menu item to proceed.");
        }

        saveBooksToFile();

    }

    @FXML
    void editBook(ActionEvent event)
    {
        if (titleTextField.isVisible() && authorTextField.isVisible() && isbnTextField.isVisible())
        {
            String isbn = isbnTextField.getText().trim();
            String newTitle = titleTextField.getText().trim();
            String newAuthor = authorTextField.getText().trim();
            String newAvailability = availabilityTextField.getText().trim();

            if (isValidISBN(isbn) && isValidTitle(newTitle) && isValidAuthor(newAuthor))
            {
                Book editedBook = findBookByISBN(isbn);

                if (editedBook != null)
                {
                    editedBook.setTitle(newTitle);
                    editedBook.setAuthor(newAuthor);
                    editedBook.setAvailability(newAvailability);
                    sortBooks();
                    updateTableView();
                    bookTableView.refresh();
                    clearFields();
                    status.setText("  Status: Book Edited Successfully.");
                    status.setVisible(true);
                    System.out.println(status);
                }
                else
                {
                    showAlert("Not Found", "No book found with the specified ISBN.");
                }
            }
            else
            {
                showAlert("Invalid Input", "Please provide valid ISBN, Title, and Author.");
            }
        }
        else
        {
            showAlert("Functionality Error", "Please enter the ISBN to edit the book.");
        }

        saveBooksToFile();
    }

    private Book findBookByISBN(String isbn)
    {
        for (Book book : books)
        {
            if (book.getISBN().equals(isbn))
            {
                return book;
            }
        }

        return null;
    }


    @FXML
    void deleteBook(ActionEvent event)
    {
        if (!titleTextField.isVisible() && !authorTextField.isVisible() && !isbnTextField.isVisible() && !availabilityTextField.isVisible())
        {
            Book selectedBook = bookTableView.getSelectionModel().getSelectedItem();

            if (selectedBook != null)
            {
                uniqueISBNs.remove(selectedBook.getISBN());
                books.remove(selectedBook);
                updateTableView();
                bookTableView.refresh();
                clearFields();
                status.setText("  Status: Book Deleted Successfully.");
                status.setVisible(true);
                System.out.println(status);
            }
            else
            {
                showAlert("No Selection", "Please select a book to delete.");
            }
        }
        else
        {
            showAlert("Functionality Error", "Please select the Delete Book menu item to proceed.");
        }

        saveBooksToFile();
    }

    @FXML
    void searchBooks(ActionEvent event)
    {
        String titleKeyword = titleTextField.getText().trim().toLowerCase();
        String authorKeyword = authorTextField.getText().trim().toLowerCase();

        ObservableList<Book> searchResults = FXCollections.observableArrayList();

        for (Book book : books)
        {
            boolean titleMatch = book.getTitle().toLowerCase().contains(titleKeyword);
            boolean authorMatch = book.getAuthor().toLowerCase().contains(authorKeyword);

            if ((titleKeyword.isEmpty() || titleMatch) && (authorKeyword.isEmpty() || authorMatch))
            {
                searchResults.add(book);
            }
        }

        updateTableView(searchResults);
    }

    @FXML
    void viewAllBooks(ActionEvent event)
    {
        updateTableView(books);
    }

    private boolean isValidTitle(String title)
    {
        return !title.isEmpty();
    }

    private boolean isValidAuthor(String author)
    {
        return !author.isEmpty();
    }

    private boolean isValidISBN(String isbn)
    {
        return isbn.matches("\\d{10}|\\d{13}");
    }

    private void showAlert(String title, String message)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void sortBooks()
    {
        books.sort(Comparator.comparing(Book::getTitle).thenComparing(Book::getAuthor).thenComparing(Book::getISBN).thenComparing(Book::getAvailability));
    }

    private void clearFields()
    {
        titleTextField.clear();
        authorTextField.clear();
        isbnTextField.clear();
        availabilityTextField.clear();
    }

}

module org.example.i222626_q1_javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.i222626_q1_javafx to javafx.fxml;
    exports org.example.i222626_q1_javafx;
}
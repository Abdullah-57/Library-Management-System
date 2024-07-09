# Book Management System

## Overview

This JavaFX application is designed to manage a collection of books. Users can add, edit, delete, search, and view books. The application provides a graphical interface to interact with the book data, which is stored in a CSV file.

## Features

- **Add Book**: Add a new book with a title, author, ISBN, and availability status.
- **Edit Book**: Edit the details of an existing book using its ISBN.
- **Delete Book**: Remove a book from the collection.
- **Search Books**: Search for books by title or author.
- **View All Books**: Display all books in the collection.
- **File Operations**: Create a new collection, open an existing collection, save the current collection, and save the collection with a new name.

## Technologies Used

- **Java**: The core programming language used to develop the application.
- **JavaFX**: The framework used for building the graphical user interface.
- **FXML**: Used for defining the UI layout.
- **Maven**: Used for project management and dependency management.

## Getting Started

### Prerequisites

- **Java Development Kit (JDK)**: Ensure you have JDK 11 or later installed.
- **Maven**: Ensure Maven is installed and configured.

## Using the Application

Add a Book:
Select Edit -> Add Book from the menu.
Enter the book details (title, author, ISBN, availability) and click Confirm.

Edit a Book:
Select Edit -> Edit Book from the menu.
Enter the existing ISBN and new details (title, author, availability) and click Confirm.

Delete a Book:
Select Edit -> Delete Book from the menu.
Select a book from the table and click Confirm.

Search Books:
Select View -> Search Books from the menu.
Enter the title or author to search and click Confirm.

View All Books:
Select View -> View All Books from the menu.

File Operations:
Select File -> New to create a new collection.
Select File -> Open to open an existing CSV file.
Select File -> Save to save the current collection.
Select File -> Save As to save the collection with a new name.

Contributing:
Contributions are welcome! Please follow these steps:
Fork the repository.
Create a new branch (git checkout -b feature-branch).
Commit your changes (git commit -m 'Add some feature').
Push to the branch (git push origin feature-branch).
Open a pull request.

Acknowledgments:
This project was developed as part of a software engineering course at FAST National University of Computer and Emerging Sciences.
Special thanks to the course instructor and teaching assistants for their support and guidance.

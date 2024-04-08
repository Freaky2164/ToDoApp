# ToDoApp
 
Project for Advanced Software Engineering at DHBW Karlsruhe.

## Run and debug

Clone the repository from GitHub and start by running the command `mvn clean install` from the ToDoApp folder with the parent pom. This command also runs all test in the project.
To only run the tests you can use the command `mvn test` from within the same folder.

To start the application you must run the main method in the ToDoApplication class. If the application starts up successfully, you can navigate to http://localhost:8080/login to use the ToDoApp.

## Use Cases

### Login to the application

You can enter an e-mail address and password to login to the application. The project works with an in-memory H2 database which means that data is not persisted over the restarting of the application. Before logging in, you must register yourself by providing an e-mail adress and password. 

![LoginView](https://github.com/Freaky2164/ToDoApp/blob/main/documents/images/LoginView.png)

Afterwards you should be able to login and the application redirects you to the main view. In the main view you can add, edit or delete to-do lists and to-dos in order to help you manage your daily life.

![MainView](https://github.com/Freaky2164/ToDoApp/blob/main/documents/images/MainView.png)

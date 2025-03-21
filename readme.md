in construction
We are moving the project to be feature oriented so the controllers, views and interfaces folders are going to disappear (we are keeping them
by now just as examples) and favor folders like login:

```
src/
├── main/
│   ├── java/
│   │   └── crud/
│   │       ├── login/
│   │       │   ├── LoginController.java
│   │       │   ├── LoginService.java
│   │       │   ├── UserRepository.java
│   │       │   ├── InMemoryUserRepository.java
│   │       │   └── User.java
│   │       ├── dashboard/
│   │       │   ├── DashboardController.java
│   │       │   ├── DashboardService.java
│   │       │   └── DashboardRepository.java
│   │       └── shared/
│   │           ├── utils/
│   │           │   └── PasswordHasher.java
│   │           └── config/
│   │               └── HibernateUtil.java
│   ├── resources/
│   │   └── crud/
│   │       ├── login/
│   │       │   └── Login.fxml
│   │       ├── dashboard/
│   │       │   └── Dashboard.fxml
│   │       └── shared/
│   │           └── styles/
│   │               └── main.css

```

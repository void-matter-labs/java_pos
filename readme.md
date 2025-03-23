in construction

We are changing again!

TL;DR
We change from Layered to Modular(Layered modules) Monolith

Now if you want to work in your feature in isolation do:
```bash
gradle dev -Pmodule=your_feature_name
```

Provided that we have `java/crud/app/your_feature_name/YourFeatureNameModule.java` module class
and a resources/your_feature_name/YourFeatureName.fxml view

If your feature does not comply with this convention or does not exist
an exception is going to be thrown

The new structure is going to be a modular monolith,
this is because we want to be able to work in the features in
isolation and we noticed that currently we are polluting to much the
login for example trying to pass a big User object when Login needs its
own User with the data and concerns of only what is needed and cross concern
things in the shared part

We move into something like this

```
/src
  /app
    /login
      LoginModule.java #The injector context
      /controllers
        ├── LoggingController.ts   # Handles HTTP/UI requests
      /applicationService          # Application-level services
        ├── LoggingService.ts      # Handles business rules, coordinates layers
      /domainService               # Reusable services shared across modules
        ├── LogFormatter.ts        # Helper logic that other services can use
      /persistence
        ├── LoggingRepository.ts   # Data access logic
        ├── LoggingDAO.ts          # Raw DB interactions
      /model
        ├── LoggingEntry.ts        # Business models
      /dTO
        ├── LoggingDTO.ts          # Transport data objects
  /shared
    /controller
      ├── BaseController.ts        # Generic API controller logic
    /applicationService            # Core application logic
      ├── UserSessionService.ts    # Handles user session management
    /domainService                 # Reusable domain-wide logic
      ├── FileStorageService.ts    # Handles file uploads for multiple modules
      ├── NotificationService.ts   # Handles notifications across modules
    /persistence
      /database
        ├── MongoConnection.ts     # DB connection
        ├── CSVParser.ts           # CSV utilities
      /repository
        ├── BaseRepository.ts      # Generic repo logic
    /model
      ├── SharedModels.ts          # Commonly reused models
      ├── ValueObjects.ts          # Strongly typed IDs, money, dates
    /dTO
      ├── SharedDTOs.ts            # Common DTOs
    /config
      ├── GlobalConfig.ts          # Environment variables, global settings
    /global
      ├── DependencyInjection.ts   # DI container for shared services
```


Before we had this
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

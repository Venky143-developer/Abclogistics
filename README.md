# ABClogistics

ABClogistics is a **Spring Boot logistics management system** designed to manage Orders, Trucks, Cargo, and Drivers efficiently.  
It provides a REST API interface for managing logistics operations, including order placement, cargo tracking, truck management, and email notifications.

---

## **Features**

- **Order Management:** Create, update, and track orders.  
- **Truck Tracking:** Manage truck assignments and availability.  
- **Cargo Management:** Handle cargo details and quantities.  
- **Driver Management:** Add, update, and assign drivers.  
- **Email Notifications:** Notify users/admins for order status changes.  
- **Exception Handling:** Custom exceptions for error management.

---

## **Technologies Used**

- **Java 17**  
- **Spring Boot**  
- **Maven**  
- **JPA / Hibernate**  
- **MySQL** (or your preferred database)  
- **REST API**

---

## **Project Structure**
ABClogistics/
│
├─ src/main/java/com/alpha/ABClogistics/
│ ├─ Controller/ # REST controllers
│ ├─ DTO/ # Data transfer objects
│ ├─ Entity/ # JPA entities
│ ├─ Exception/ # Custom exception classes
│ ├─ Repository/ # JPA repositories
│ └─ Service/ # Service layer
│
├─ pom.xml # Maven configuration
└─ README.md

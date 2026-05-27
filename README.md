🎓 Campus Event Management System
📌 Project Overview
The Campus Event Management System is a web-based application developed using Spring Boot and MySQL to simplify the process of managing campus events.
The system allows:
* Students to register and book events online
* Administrators to manage events efficiently
* Secure authentication and authorization
* QR Code generation for bookings
* PDF ticket/report generation
This project reduces manual work, improves event organization, and provides real-time event management for educational institutions.

🚀 Features
👨‍🎓 Student Features
* User Registration & Login
* View Available Events
* Book Event Tickets
* View Booking History
* Download Booking PDF
* QR Code Generation for Tickets
* Cancel Bookings
👨‍💼 Admin Features
* Admin Login
* Add New Events
* Edit Existing Events
* Delete Events
* View All Bookings
* Manage Cancellation Requests
* Dashboard Statistics

🛠️ Technologies Used
| Technology         | Purpose                   |
| ------------------ | ------------------------- |
| Java 17            | Backend Programming       |
| Spring Boot 3      | Application Framework     |
| Spring MVC         | Web Architecture          |
| Spring Security    | Authentication & Security |
| Spring Data JPA    | Database Operations       |
| Thymeleaf          | Frontend Template Engine  |
| MySQL              | Database                  |
| Maven              | Dependency Management     |
| ZXing              | QR Code Generation        |
| iText PDF          | PDF Generation            |
| HTML/CSS/Bootstrap | Frontend UI               |

📂 Project Structure
CampusEventManagement
│
├── src/main/java/com/campus/event
│   ├── config
│   ├── controller
│   ├── exception
│   ├── model
│   ├── repository
│   ├── service
│   └── util
│
├── src/main/resources
│   ├── static
│   ├── templates
│   └── application.properties
│
├── pom.xml
└── README.md

🗄️ Database Configuration
Update the following properties in:
```properties
src/main/resources/application.properties
```
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/campus_event_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

⚙️ Installation & Setup
Step 1: Clone the Repository
git clone https://github.com/your-username/CampusEventManagement.git

Step 2: Open Project
Open the project in:
* Eclipse
* IntelliJ IDEA
* VS Code

Step 3: Configure Database
* Create MySQL database:
CREATE DATABASE campus_event_db;
* Update database username and password in `application.properties`

Step 4: Run the Project
Using Maven:
mvn spring-boot:run
Or run:
CampusEventManagementApplication.java

🔐 Authentication
The system uses Spring Security for authentication and authorization.
Roles included:
* ADMIN
* STUDENT

📸 Screens Included
The project contains pages such as:
* Home Page
* Login Page
* Registration Page
* Admin Dashboard
* Event Details Page
* My Bookings Page
* Statistics Dashboard

📄 QR Code & PDF Support
QR Code
QR codes are generated using the ZXing Library for event verification.
PDF Generation
PDF tickets/reports are generated using iText PDF Library.

🌱 Future Enhancements
* Email Notifications
* Payment Gateway Integration
* Event Analytics Dashboard
* Mobile Application Support
* Attendance Tracking
* AI-based Event Recommendations

🎯 Objectives
* Reduce manual event management
* Improve communication between students and organizers
* Provide secure event registration
* Maintain organized event records
* Automate ticket generation and booking management

📚 Academic Relevance
* Full Stack Web Development
* Java Enterprise Application Development
* Database Management
* Secure Authentication
* Real-Time Event Management

👨‍💻 Developed By
Anruth
B.Tech Final Year Student
Department of Computer Science & Engineering

📜 License
This project is developed for educational purposes.

⭐ Conclusion
The Campus Event Management System provides a modern and efficient solution for managing college events digitally. The application improves user experience, minimizes paperwork, and helps institutions organize events effectively using secure and scalable web technologies.

# OTP Sender – Spring Boot Application

## 📌 Overview
The **OTP Sender Application** is a Spring Boot–based backend service designed to generate and send **One-Time Passwords (OTP)** to users for authentication and verification purposes. It supports sending OTPs via **Email** and/or **SMS**, making it suitable for login verification, password reset, and user onboarding workflows.

---

## 🚀 Features
- Generate secure numeric OTPs
- Send OTP via Email (SMTP)
- Optional SMS support (Twilio or similar services)
- OTP expiration handling
- OTP verification API
- RESTful architecture
- Easy configuration using `application.properties`

---

## 🛠️ Tech Stack
- **Java** 17+
- **Spring Boot**
- Spring Web
- Spring Data JPA
- Spring Mail
- MySQL / H2 Database
- Maven

---

## 📂 Project Structure
```
OTP-Sender
│── src/main/java
│   └── com.example.otp
│       ├── controller
│       ├── service
│       ├── entity
│       ├── repository
│       └── OtpSenderApplication.java
│
│── src/main/resources
│   ├── application.properties
│   └── templates
│
│── pom.xml
│── README.md
```

---

## ⚙️ Configuration
Update the following properties in `application.properties`:

### Email Configuration
```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your-email@gmail.com
spring.mail.password=your-app-password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

### Database Configuration
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/otp_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```

---

## 🔐 OTP Flow
1. User requests OTP
2. OTP is generated and stored with expiry time
3. OTP is sent via Email/SMS
4. User submits OTP for verification
5. System validates OTP and expiry

---

## 📡 API Endpoints

### Generate OTP
```http
POST /api/otp/send
```
**Request Body:**
```json
{
  "email": "user@example.com"
}
```

### Verify OTP
```http
POST /api/otp/verify
```
**Request Body:**
```json
{
  "email": "user@example.com",
  "otp": "123456"
}
```

---

## ▶️ Running the Application

### Using Maven
```bash
mvn spring-boot:run
```

### Using JAR
```bash
mvn clean package
java -jar target/otp-sender-0.0.1-SNAPSHOT.jar
```

---

## 🧪 Testing
- APIs can be tested using **Postman** or **Swagger**
- Ensure email credentials are correct

---

## 📌 Use Cases
- User Registration Verification
- Login Authentication
- Password Reset
- Transaction Verification

---

## 🔒 Security Notes
- OTP expires after a configurable time
- Avoid logging OTP values
- Use HTTPS in production

---

## 👤 Author
**J Pradyumna**

---

## 📄 License
This project is for academic and learning purposes.


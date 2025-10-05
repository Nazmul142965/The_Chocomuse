# The Chocomuse Management System

![Java](https://img.shields.io/badge/Java-17+-orange.svg)
![JavaFX](https://img.shields.io/badge/JavaFX-17.0.6-blue.svg)
![MySQL](https://img.shields.io/badge/MySQL-8.0+-blue.svg)
![Maven](https://img.shields.io/badge/Maven-3.6+-green.svg)

A comprehensive JavaFX-based desktop application for managing a chocolate store, featuring inventory management, sales tracking, and user management capabilities.

## 🍫 Features

### Admin Features
- **Dashboard Analytics**: Revenue tracking, sales statistics, and inventory overview
- **Inventory Management**: Add, edit, and delete chocolate products
- **Sales Management**: View all sales transactions and analytics
- **Revenue Tracking**: Total revenue, average order value, and inventory value

### User Features
- **Product Catalog**: Browse available chocolates with images and details
- **Shopping Cart**: Add items to cart and manage quantities
- **Purchase System**: Multiple payment methods (Cash, Credit Card, Debit Card)
- **Order History**: View past purchases and order details
- **User Dashboard**: Personal shopping statistics and spending overview

## 🚀 Quick Start

### Prerequisites
- **Java 17+** installed
- **MySQL 8.0+** server running
- **Maven 3.6+** for dependency management

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/the-chocomuse.git
   cd the-chocomuse
   ```

2. **Set up the database**
   ```bash
   # Open MySQL command line or MySQL Workbench
   mysql -u root -p
   
   # Run the database setup scripts
   source database/01_create_database.sql
   source database/02_sample_data.sql
   ```

3. **Configure database connection** (if needed)
   
   private static final String DB_USER = "your_username";
   private static final String DB_PASSWORD = "your_password";


4. **Run the application**
   ```bash
   mvn clean javafx:run
   ```

### Default Login Credentials

**Admin Access:**
- Username: `admin`
- Password: `admin123`

**User Access:**
- Username: `john_doe`
- Password: `password123`

## 📁 Project Structure

```
The Chocomuse/
├── src/main/java/bd/edu/seu/tcms/
│   ├── HelloApplication.java           # Main application entry point
│   ├── admin/                          # Admin-related controllers
│   │   ├── AdminAddItem.java
│   │   ├── AdminDashboard.java
│   │   ├── AdminInventory.java
│   │   └── AdminRevenue.java
│   ├── controller/                     # Authentication controllers
│   │   ├── AdminLoginController.java
│   │   ├── LoginPageController.java
│   │   ├── RegistrationPageController.java
│   │   └── WelcomePageController.java
│   ├── interfaces/                     # Service interfaces
│   │   ├── ChocolateInterface.java
│   │   ├── SalesInterface.java
│   │   └── UserInventoryInterface.java
│   ├── model/                          # Data models
│   │   ├── CartItem.java
│   │   ├── Chocolate.java
│   │   ├── Sale.java
│   │   └── SaleItem.java
│   ├── services/                       # Business logic services
│   │   ├── ChocolateService.java
│   │   ├── RevenueService.java
│   │   ├── SalesService.java
│   │   └── UserInventoryService.java
│   ├── user/                          # User-related controllers
│   │   ├── UserBuyItem.java
│   │   ├── UserDashboard.java
│   │   ├── UserInventory.java
│   │   └── UserPayBill.java
│   └── utility/                       # Utility classes
│       ├── ConnectionSingleton.java
│       └── UserSession.java
├── src/main/resources/bd/edu/seu/tcms/ # FXML files and images
├── database/                          # Database setup scripts
│   ├── 01_create_database.sql
│   ├── 02_sample_data.sql
│   └── README.md
└── pom.xml                           # Maven configuration
```

## 🗄️ Database Schema

### Tables
- **admin**: Admin authentication
- **user**: User registration and authentication  
- **chocolate**: Product inventory
- **sales**: Sales transactions
- **sale_items**: Individual items in each sale


## 🛠️ Technologies Used

- **JavaFX 17.0.6**: Modern UI framework for desktop applications
- **MySQL 8.2.0**: Relational database for data persistence
- **Maven**: Dependency management and build automation
- **Java 23**: Programming language with latest features

## 📝 Features in Detail

### Inventory Management
- Add new chocolate products with details (name, weight, price, dates, images)
- Edit existing product information
- Delete products from inventory
- Track quantity and automatic stock updates on sales

### Sales System
- Process multi-item purchases
- Support multiple payment methods
- Automatic inventory deduction
- Sales history and tracking
- Coupon code support

### Analytics Dashboard
- Real-time revenue tracking
- Total items sold statistics
- Average order value calculations
- Inventory value monitoring
- User-specific spending analytics

## 🔧 Development

### Building from Source
```bash
mvn clean compile
mvn clean package
```

### Running Tests
```bash
mvn test
```

### Creating Distribution
```bash
mvn clean javafx:jlink
```

## 📦 Dependencies

- **JavaFX Controls & FXML**: UI framework
- **MySQL Connector/J**: Database connectivity
- **JUnit Jupiter**: Testing framework

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👥 Authors


- Nazmul - *Development and Documentation*

## 🙏 Acknowledgments

- JavaFX community for excellent documentation
- MySQL for reliable database solutions
- Maven for dependency management

---

**Note**: This application is developed for educational purposes. For production deployment, implement additional security measures including password hashing, input validation, and proper error handling.
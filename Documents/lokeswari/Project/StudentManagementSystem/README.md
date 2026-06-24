# 🎓 Student Management System

A console-based **Core Java** project demonstrating essential OOP and software design principles.

---

## ✨ Features

| Feature | Description |
|--------|-------------|
| **OOP** | Abstraction, Inheritance, Polymorphism via `Person → Student / Teacher` |
| **CRUD** | Add, View, Search, Update, Delete for Students & Teachers |
| **File I/O** | Persistent CSV storage — data survives restarts |
| **Exception Handling** | Custom checked exceptions for invalid input, duplicates, not-found |
| **Validation** | Email format, GPA range, age, year level checks |

---

## 🗂️ Project Structure

```
StudentManagementSystem/
├── src/
│   └── com/sms/
│       ├── model/
│       │   ├── Person.java          # Abstract base class
│       │   ├── Student.java         # Extends Person
│       │   └── Teacher.java         # Extends Person
│       ├── service/
│       │   ├── StudentService.java  # CRUD logic for Students
│       │   └── TeacherService.java  # CRUD logic for Teachers
│       ├── exception/
│       │   ├── RecordNotFoundException.java
│       │   ├── DuplicateRecordException.java
│       │   └── InvalidInputException.java
│       ├── util/
│       │   ├── FileUtil.java        # CSV File I/O
│       │   └── Validator.java       # Input validation
│       └── main/
│           └── Main.java            # Entry point & console menu
└── data/
    ├── students.csv                 # Auto-generated on first run
    └── teachers.csv                 # Auto-generated on first run
```

---

## 🚀 How to Run

### Using Command Line (javac)

```bash
# 1. Compile all source files
find src -name "*.java" | xargs javac -d out

# 2. Run the application
java -cp out com.sms.main.Main
```

### Using an IDE (IntelliJ / Eclipse)
1. Import the project as a **Java project**
2. Set `src` as the source root
3. Run `com.sms.main.Main`

---

## 🧪 Sample Usage

```
╔══════════════════════════════════════════╗
║    STUDENT MANAGEMENT SYSTEM  v1.0       ║
╚══════════════════════════════════════════╝

┌─────────────────────────┐
│       MAIN MENU         │
├─────────────────────────┤
│  1. Student Management  │
│  2. Teacher Management  │
│  3. Summary             │
│  0. Exit                │
└─────────────────────────┘
```

---

## 🏛️ OOP Concepts Demonstrated

- **Abstraction** — `Person` is an abstract class with abstract methods `getRole()` and `toCSV()`
- **Inheritance** — `Student` and `Teacher` extend `Person`
- **Polymorphism** — `toString()` and `toCSV()` behave differently per subclass
- **Encapsulation** — All fields are `private`/`protected` with getters/setters

---

## 🛡️ Exception Handling

| Exception | When Thrown |
|-----------|-------------|
| `DuplicateRecordException` | Adding a Student/Teacher with an existing ID |
| `RecordNotFoundException` | Searching/updating/deleting a non-existent ID |
| `InvalidInputException` | Bad email, GPA out of range, empty name, etc. |

---

## 📋 Requirements

- Java 17 or higher (uses Switch Expressions)
- No external dependencies — pure Core Java

---

## 👤 Author

> Built as a Core Java portfolio project.

---

## 📄 License

MIT License — free to use and modify.

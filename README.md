# University Semester Projects

**Muhammad Talha Zahid · Riphah International University, Lahore**

A collection of degree coursework, semester projects, prototypes, reports, and presentations. The repository covers C++ programming, object-oriented design, Java desktop applications, operating-system concepts, algorithm analysis, web development, Flutter, and introductory machine learning.

This guide documents **all eight course folders**, including applications inside ZIP archives and additional prototypes and presentation-only material. Projects are grouped by their actual repository folders; semester numbers are included only where the accompanying documents identify them.

> **About this archive:** These are preserved academic submissions at different stages of completion. Features below are based on the committed source and supporting documents. Setup commands are starting instructions, not a claim that every application currently builds or that external services are configured. Known gaps are listed beside the relevant project.

## Contents

- [Project directory](#project-directory)
- [Getting started](#getting-started)
- [1. Programming Fundamentals — Tic-Tac-Toe](#1-programming-fundamentals--tic-tac-toe)
- [2. Object Oriented Programming — Gym Management System](#2-object-oriented-programming--gym-management-system)
- [3. Advance Computer Programming — Solar applications and UI prototypes](#3-advance-computer-programming--solar-applications-and-ui-prototypes)
- [4. Design and Analysis of Algorithm — Banking algorithms](#4-design-and-analysis-of-algorithm--banking-algorithms)
- [5. Operating System — Desktop OS simulator](#5-operating-system--desktop-os-simulator)
- [6. Web Application Development — Weather and e-commerce](#6-web-application-development--weather-and-e-commerce)
- [7. Mobile Application Development — Flutter Twitter clone](#7-mobile-application-development--flutter-twitter-clone)
- [8. Artificial Intelligence — AI Code Efficiency Debugger](#8-artificial-intelligence--ai-code-efficiency-debugger)
- [Academic material and attribution](#academic-material-and-attribution)
- [Maintenance opportunities](#maintenance-opportunities)

## Project directory

| Course folder | Project / artifact | Main technologies | Entry point or delivery |
|---|---|---|---|
| [Programming Fundamentals](Programming%20Fundamentals/) | Two-player Tic-Tac-Toe with tournaments and history | C++, console, file I/O | `Tic Tac Toe Final.cpp` |
| [Object Oriented Programming](Object%20Oriented%20Programming/) | Throwback Fitness Gym Management System | C++, classes, inheritance, file I/O | `ConsoleApplication1.cpp` |
| Object Oriented Programming | Student Management System presentation | C++ design documentation | `presentation_of_oop.pptx`; no matching student-system source supplied |
| [Advance Computer Programming](Advance%20Computer%20Programming/) | Solar Management System | Java Swing, JDBC, Apache Derby | `Project/solarsystem/src/SolarManagementSystem.java` |
| Advance Computer Programming | Prestige Solar Energy website | HTML, CSS, JSP, Maven WAR | `Solar_System_Project/src/main/webapp/index.html` |
| Advance Computer Programming | Prestige Solar Energy dashboard prototype | Java Swing | `solar/src/Main.java` |
| Advance Computer Programming | Office Orbit UI prototype | Java Swing, custom button painting | `solar/src/Self.java` |
| [Design and Analysis of Algorithm](Design%20and%20Analysis%20of%20Algorithm/) | Banking sorting and duplicate-detection demonstration | Java collections and comparators | `Banking Application/src/BankingApplication.java` |
| [Operating System](Operating%20System/) | OS concepts simulator | Java Swing, threads, semaphores, sockets | Extract `OS Project.zip`; run `src/Main.java` |
| [Web Application Development](Web%20Application%20Development/) | Weather forecast application | HTML, CSS, JavaScript, WeatherAPI; partial Node/React work | Extract `Project.zip` |
| Web Application Development | E-commerce application | MongoDB, Express, React, Node.js, Redux, Tailwind, Cloudinary | Extract `Full-Stack-E-Commerce-MERN-APP-main.zip` |
| [Mobile Application Development](Mobile%20Application%20Development/) | Fwitter / Twitter clone coursework copy | Flutter, Dart, Firebase, Provider | `lib/main.dart` |
| [Artificial Intelligence](Artificial%20Intelligence/) | Code efficiency classifier and code runner | Python, Flask, pandas, scikit-learn | `app.py` |

## Getting started

```bash
git clone https://github.com/TalhaZahid13/Projects.git
cd Projects
```

Each project is independent. There is no repository-wide install command or shared database. Commands below start from the repository root unless a different working directory is explicitly stated. Quote paths containing spaces.

| Project family | Requirements |
|---|---|
| C++ console projects | A C++11-or-newer compiler; the Tic-Tac-Toe implementation uses Windows-specific console APIs |
| Java desktop projects | A JDK and a desktop graphical environment; Derby libraries are additionally required for the database-backed solar project |
| Solar website | Browser/static server for HTML; Maven and a compatible servlet container for the optional WAR/JSP route |
| Weather app | Browser, internet access, a configured WeatherAPI key; repairs described below |
| MERN e-commerce | Node.js/npm, MongoDB, Cloudinary configuration; backend startup fixes described below |
| Flutter app | Flutter/Dart, Android tooling or macOS/Xcode for iOS, and a configured Firebase project; dependency alignment required |
| AI debugger | Python, Flask, pandas, scikit-learn, joblib; optional Node.js, JDK, and Dart SDK for their execution branches |

For archived applications, extract the ZIP before opening the inner project folder. Do not treat bundled `node_modules`, Java `.class` files, or an old executable as a substitute for rebuilding from source.

## 1. Programming Fundamentals — Tic-Tac-Toe

**Source:** [Tic Tac Toe Final.cpp](Programming%20Fundamentals/Tic%20Tac%20Toe%20Final.cpp)

### Purpose and functionality

A local two-player console game that demonstrates foundational programming through a 3×3 board, turn handling, win detection, repeated rounds, and saved results.

- Collects two player names and assigns `X` and `O`.
- Offers a single match or a tournament with an odd number of rounds.
- Accepts board positions `1`–`9` and rejects invalid or occupied positions.
- Checks rows, columns, and diagonals for a winner and identifies draws.
- Resets the board between rounds and tracks both players' wins and total draws.
- Displays date/time information and appends match details to `text.txt`.
- Displays saved history after the selected rounds finish.

### Implementation map

| Function / state | Responsibility |
|---|---|
| `N[3][3]`, `turn`, `P1`, `P2`, `D` | Board, active symbol, and cumulative results |
| `player()`, `S_T()` | Player details and match/tournament selection |
| `structure()`, `player_turn()` | Board display and move entry |
| `game()`, `result()` | Win/draw checks and score updates |
| `F_H1()`, `F_H2()`, `FH3()` | Append session, board/result, and score information |
| `DATE()`, `W_L_D()`, `view_history()` | Time, scoreboard, and history display |

**Learning concepts:** arrays, functions, loops, branching, character input, recursion, text files, and basic time handling.

### Files and running

The folder includes source, a prebuilt Windows executable, a sample history file, and the empty `pf.txt` marker.

Using a Windows compiler that supports `conio.h` and `getche()`:

```bash
cd "Programming Fundamentals"
g++ -std=c++11 "Tic Tac Toe Final.cpp" -o tic-tac-toe.exe
```

Run `tic-tac-toe.exe` from that directory, enter the names, choose `S` or `T`, and select numbered cells. History is written to the current working directory.

**Limitations:** `getche()`, `system("cls")`, and `system("pause")` make the source Windows-oriented. Numeric tournament validation is limited, and the local history game counter is reset inside its logging function. There is no computer opponent, networking, or graphical interface.

## 2. Object Oriented Programming — Gym Management System

**Source:** [ConsoleApplication1.cpp](Object%20Oriented%20Programming/ConsoleApplication1.cpp)  
**Application name:** Throwback Fitness  
**Academic context:** The report identifies semester/section **2-A**, Muhammad Talha and Marwa Jabbar, submitted to Ma'am Madeeha.

### Purpose and user workflows

A console application for gym memberships, intended to replace manual membership records with a menu-driven interface.

| Mode | Available menu operations |
|---|---|
| User | Join gym, quit gym, edit profile |
| Admin | Create member, display all records, search by member number, edit member, delete member, return-to-menu option |

A member record contains a member number, name, contact number, membership category, fee, and preferred time slot. Gold membership is assigned a fee of **2500** and Silver **2000** in the source; the code does not explicitly label a currency. Slots cover 6–7, 7–8, and 8–9 AM, plus 4–5, 5–6, and 6–7 PM.

### Classes, storage, and concepts

- `login`: administrator credential checking.
- `member : public login`: membership fields, entry, display, and getters.
- `trainee_allotment()`, `allotment_gold()`, `allotment_silver()`: a simplified trainer-allocation demonstration.
- `save_member()`, `show_all()`, `display_record()`, `edit_member()`, `delete_member()`: record operations.
- `fnuser()` and `fnmanage()`: mode-specific menu loops.
- `gymdata.txt`: raw object bytes written through C++ file streams, despite the `.txt` extension.
- `Temp2.txt`: temporary output used by the deletion routine.

The project illustrates classes, inheritance, encapsulation, arrays, pointers, functions, menu control, and file handling. The user mode does not have individual account authentication.

### Setup and known implementation issues

Open the source in a C++ IDE. The code uses `strcpy` and `strcmp` but omits `<cstring>`; add that include before building with a strict compiler. A typical build command **after that correction** is:

```bash
cd "Object Oriented Programming"
g++ -std=c++11 ConsoleApplication1.cpp -o gym-management.exe
```

The administrator credentials are hardcoded in `login`; inspect or replace them locally. The report's password example differs from the source, so the source is authoritative.

This is a learning prototype with significant persistence and control-flow issues:

- Raw serialization of `member` also serializes its inherited `std::string` object representation, which is not a safe persistent format.
- Deletion removes the original data file without renaming the temporary file back into place.
- Editing seeks forward instead of back to the record being replaced.
- The search routine's “no record found” condition is inverted.
- Trainer availability resets on each allocation call.
- The back option does not exit the surrounding infinite menu loop.
- Name input and recursive login retries need correction.

Use disposable sample data when exploring the original code.

### Supporting and additional material

| File | Contents / interpretation |
|---|---|
| [OOP Project.docx](Object%20Oriented%20Programming/OOP%20Project.docx) | Gym project explanation, workflows, and function descriptions |
| [UML DIAGRAM.docx](Object%20Oriented%20Programming/UML%20DIAGRAM.docx) | Design illustration; some depicted classes/inheritance do not match the implementation |
| [OOP PRESENTATION .pptx](Object%20Oriented%20Programming/OOP%20PRESENTATION%20.pptx) | Gym presentation with some clothing-management/template content mixed in |
| [presentation_of_oop.pptx](Object%20Oriented%20Programming/presentation_of_oop.pptx) | A separate Student Management System presentation credited to **Aniqa Razzaq and Laraib Asghar** |
| `OOP Project.txt` | Empty marker |

The Student Management presentation describes student/course records, create/show/update/delete/search operations, class and student results, file handling, and `Course`/`Student` classes. It is a **presentation-only artifact in this repository**: no matching runnable student-management implementation is included. The clothing-management slides similarly do not establish an additional supplied application.

## 3. Advance Computer Programming — Solar applications and UI prototypes

This course folder contains four independently runnable or viewable implementations. They should not be treated as one integrated application.

### 3.1 Solar Management System — Swing and Derby

**Source:** [SolarManagementSystem.java](Advance%20Computer%20Programming/Project/solarsystem/src/SolarManagementSystem.java)

A Java desktop application combining a splash screen, role-based login, solar production statistics, customer information, panel statistics, and a financial overview.

| Module | Implemented behavior |
|---|---|
| `SolarManagementSystem` | Initializes database tables and starts the GUI |
| `DatabaseManager` | Opens JDBC connections, creates schema/sample data, validates login, queries totals and tables |
| `SplashScreen` | Progress animation followed by login |
| `LoginPage` | Username/password entry and User/Admin role selection |
| `UserDashboard` | Total production, electricity generated, calculated carbon offset, and equivalent trees |
| `AdminDashboard` | Overview, Customers, and Panel Information tabs with sortable tables |

**Database:** embedded Apache Derby at `jdbc:derby:SolarDB;create=true`. It is not a MySQL application. The database is created relative to the process working directory.

| Table | Stored fields |
|---|---|
| `users` | Identity ID, username, password, role, name, email, phone |
| `panels` | Identity ID, company, grade, capacity, price, installation date |
| `customers` | Identity ID, contact/address details, panels installed, installation date, total cost |
| `production` | Identity ID, customer foreign key, date, units produced |

First initialization seeds an administrator, a demo user, five panel records, five customers, and random production entries. Seed brands include SunPower, LG Solar, Canadian Solar, JinkoSolar, and Trina Solar.

**Dashboard calculations:** sales are the sum of customer `total_cost`; costs are estimated at 70% of sales, and profit is the remaining 30%. Carbon offset and tree equivalents multiply total production by 0.85 and 0.15 respectively. These are demonstration formulas, not measured financial or environmental results. The user dashboard queries aggregate production rather than restricting it to the logged-in customer.

**Run:** open `Project/solarsystem` in a Java IDE, configure a JDK and a compatible Derby embedded JDBC distribution on the runtime classpath, then run `SolarManagementSystem.main()`.

For a command-line setup, place the required Derby JARs in a local `lib` directory:

```bash
cd "Advance Computer Programming/Project/solarsystem"
javac -encoding UTF-8 -d out src/SolarManagementSystem.java
# Windows classpath separator:
java -cp "out;lib/*" SolarManagementSystem
# Linux/macOS alternative:
# java -cp "out:lib/*" SolarManagementSystem
```

**Status notes:** Derby JARs are not bundled as a reproducible dependency setup. Customer/panel Add, Edit, Delete, and Refresh controls are present without action handlers. Chart areas contain explanatory placeholder text. Seed passwords are stored as plain text. Table creation stops at the first “already exists” exception, so it is not a migration system.

### 3.2 Prestige Solar Energy website

**Folder:** [Solar_System_Project](Advance%20Computer%20Programming/Solar_System_Project/)

A solar-business website with a home page, business information, product catalog, contact form, and a separate JSP feedback page.

| File | Role |
|---|---|
| `src/main/webapp/index.html` | Prestige Solar Energy landing page and overview sections |
| `AboutUs.html` | Mission, vision, and team information |
| `catlouge.html` | Solar panel, inverter, battery, and mounting-system product cards; spelling retained from source |
| `ContactUs.html` | Contact form |
| `index.jsp` | Feedback form and client-side handling |
| `WEB-INF/web.xml` | Servlet declaration and `/check` mapping |
| `pom.xml` | Maven WAR packaging; artifact `Solar_System_Project`, version `1.0-SNAPSHOT` |
| Images | Logos, backgrounds, and solar-product assets |

To inspect the static pages:

```bash
cd "Advance Computer Programming/Solar_System_Project/src/main/webapp"
python -m http.server 8000
```

Open `http://localhost:8000/index.html`. Python's server serves static files only; it does not execute JSP.

For the Java web route, run `mvn package` from `Solar_System_Project` and deploy the resulting WAR to a compatible servlet container **after resolving the archived configuration**. The POM has no explicit compiler/toolchain setup and lists only JUnit 3.8.1 as a test dependency.

**Limitations:** `web.xml` names `com.example.FeedbackServlet`, but that servlet source is absent. The feedback form uses `feedback` while the mapping is `/check`; the contact form posts to `#`. Some navigation uses root-relative or backslash paths. These pages do not provide a verified backend for submissions, product purchasing, or a shopping cart.

### 3.3 Prestige Solar Energy dashboard prototype

**Source:** [solar/src/Main.java](Advance%20Computer%20Programming/solar/src/Main.java)

A separate Swing navigation prototype with a three-second welcome screen, styled hover buttons, a dashboard view, and a sales-history view. Its dashboard still contains process-management labels; sales history is placeholder text. It has no database connection or implemented sales operations.

```bash
cd "Advance Computer Programming/solar"
javac -d out src/Main.java
java -cp out Main
```

### 3.4 Office Orbit UI prototype

**Source:** [solar/src/Self.java](Advance%20Computer%20Programming/solar/src/Self.java)

A small employee-portal interface titled **Office Orbit**, with a gradient header and four circular tiles: Self Service, Expense Claim, Book a Travel, and Applause. `CircleButtonUI` handles custom circular painting.

```bash
cd "Advance Computer Programming/solar"
javac -d out src/Self.java
java -cp out Self
```

The source expects `icons/self_service.png`, `expense_claim.png`, `book_travel.png`, and `applause.png` under the working directory's `icons` folder. Those icons are not supplied in this project. The buttons have no business-action listeners; this is a UI exercise, not an expense or travel-management backend.

## 4. Design and Analysis of Algorithm — Banking algorithms

**Source:** [BankingApplication.java](Design%20and%20Analysis%20of%20Algorithm/Banking%20Application/src/BankingApplication.java)  
**Academic context:** The initial report identifies **BSCS 5-B, Fall 2024**, Muhammad Talha and Hafiza Shujia Yousaf, submitted to Dr. Saman Riaz.

### Purpose and code behavior

A Java console demonstration applying sorting and adjacent duplicate checks to banking-style records. It is not a complete banking transaction system.

| Component | Purpose |
|---|---|
| `Transaction` | Stores a `Date` and an amount |
| `Customer` | Stores a name and balance |
| `sortTransactionsByDate()` | Sorts transactions using a date comparator |
| `sortAccountsByBalance()` | Sorts customers by balance; not called by the supplied `main()` |
| `sortCustomersByName()` | Sorts customers alphabetically |
| `detectDuplicateTransactions()` | Advances two indices through adjacent entries and checks date and amount equality |
| `main()` | Creates three sample transactions and three customers, then prints original/sorted/duplicate results |

The reports discuss hash tables, binary search trees, sorting alternatives, TimSort, IntroSort, and two-pointer techniques. The executable source uses `List.sort(...)`; it does **not** implement a custom IntroSort, hash table search, or BST search despite the broader discussion and comments.

The duplicate pass is linear in the number of transactions after sorting, with additional space for returned duplicates. Correctness requires equivalent transactions to be adjacent. Sorting only by date does not guarantee that equal amounts within a date group are adjacent.

### Run and correctness notes

```bash
cd "Design and Analysis of Algorithm/Banking Application"
javac -d out src/BankingApplication.java
java -cp out BankingApplication
```

- `Transaction.getDate()` returns `new Date()` instead of its stored `date`; sorting and duplicate comparisons therefore do not reliably use transaction timestamps.
- Sample transactions use the deprecated `Date(year, month, day)` constructor with misleading year/month values.
- There is no persistence, account login, transfer processing, or interactive banking UI.

### Academic deliverables

The [course folder](Design%20and%20Analysis%20of%20Algorithm/) includes:

- `Banking Application - Initial Presentation.pptx`.
- `Initial Project Report.docx` — search/sort choices and complexity discussion.
- `Final Report-Banking Application.docx` and `.pdf` — banking-oriented algorithm explanations.
- `Proposal for the Development of a Mobile Application.docx` and `.pdf` — earlier proposal describing data sorting/filtering, task scheduling, notifications, and search; these are proposal features, not implemented mobile functionality in the Java demo.
- `Searching Graph.png` and `Sorting Graph.png` — comparison illustrations, not runtime benchmark output from the supplied Java program.
- Empty `intro.txt` marker.

## 5. Operating System — Desktop OS simulator

**Archive:** [OS Project.zip](Operating%20System/OS%20Project.zip)

A Java Swing educational interface for exploring operating-system concepts. It simulates records and algorithms inside a normal application; it is not a bootable operating system or a host process manager.

### Modules and current status

| Source file | Features / status |
|---|---|
| `Main.java` | Welcome screen, dashboard, time display, navigation to process/memory/scheduling/I/O/PCB modules |
| `Process.java` | PID, name, arrival time, burst time, state, priority, and simple behavior methods |
| `ProcessManagement.java` | Process creation/table and handlers for destroy, suspend, resume, block, wake, dispatch, and priority changes |
| `MemoryManagement.java` | Paging address translation and LRU page-fault calculation |
| `SchedulingManagement.java` | FCFS/Round Robin input forms and average-calculation UI; calculation bodies only print messages |
| `PCBOperations.java` | Displays predefined process IDs, status, owners, priorities, parent/child IDs, memory, CPU registers, processor, and I/O state |
| `IO_operation.java` | Placeholder screen displaying “Nothing” |
| `Synchronization.java` | Semaphore critical-section example, socket communication, synchronized message queue, and shared-object communication examples |

### Memory and concurrency demonstrations

Paging accepts memory/page sizes, process/page references, page-table entries, and an offset. It calculates:

```text
physical address = frame number × page size + offset
```

LRU accepts a reference string and frame count, tracks each frame's last-use position, replaces the least recently used frame, and reports page faults.

The synchronization source uses two threads and a one-permit semaphore. Its socket example exchanges a message on local port `12345`. Queue and shared-object examples use `synchronized`, `wait()`, and `notify()`. The queue/shared-memory examples are thread demonstrations within one JVM, rather than separate operating-system processes.

### Setup

Extract the archive, open its inner `OS Project` directory, then run:

```bash
javac -encoding UTF-8 -d out src/*.java
java -cp out Main
```

A graphical desktop is required. The archive also contains IntelliJ metadata and previously compiled classes; compile the source for your own environment.

**Known gaps:** `Process.Id` is never initialized, while multiple action handlers call `p.Id.equals(...)`, so those actions can throw a null-pointer exception. The PCB screen uses static sample arrays rather than live process-management data. Scheduling calculations and I/O behavior are unfinished. Synchronization has no navigation button in the supplied main dashboard. Paging/LRU need stronger validation for invalid sizes, indices, and reference lengths. Socket client/server startup can race.

## 6. Web Application Development — Weather and e-commerce

### 6.1 Weather forecast application

**Archive:** [Project.zip](Web%20Application%20Development/Project.zip)

A weather interface designed to show current conditions and the next six days for a searched city.

**Intended visible features:** city search, condition text/icons, temperature in °C, humidity, wind speed, current local clock/date, forecast cards, and success/error feedback. `App.js` sets the default city to **Lahore**, although the archive's older README says Delhi.

| Inner file | Responsibility |
|---|---|
| `WeatherApplication.html` | Static weather page |
| `App.css`, `MediaQuery.css` | Layout and responsive styling |
| `App.js` | DOM selection, validation, fetch request, forecast rendering, clock; also contains unrelated/incomplete server/test code |
| `WeatherApplication.jsx` | Partial React adaptation of the page |
| `server.js` | Partial Express server on port 2701 |
| `package.json` | Legacy Node dependencies; package named `ecomm` with Weather Application description |
| `Icon.png`, `image3.png` | Page assets |
| `Major-project-documentation.pdf` | Supporting document bundled in the archive |
| `README.md` | Original short weather description |

The frontend requests WeatherAPI's `forecast.json` endpoint with `days=7`, showing current conditions plus entries 1–6 of the forecast array. Access to that many days depends on the configured API account and response.

### Run preparation and limitations

The archive requires correction before it is a reliable runnable project:

1. In `App.js`, separate/remove the trailing Node/Mongoose/test fragment from browser code. It contains CommonJS imports, unavailable local modules, test hooks, and invalid `new({ ... })` syntax.
2. Configure your own WeatherAPI key and use an HTTPS endpoint. Existing embedded key values are not reproduced here.
3. Check that the returned forecast actually contains the requested days before indexing it.
4. Correct forecast wind values: the source currently renders `maxtemp_c` under the wind label.
5. Allow multi-word city names if needed; the existing regular expression accepts only ASCII letters.

After those repairs, serve the extracted inner `Project` folder:

```bash
python -m http.server 8000
```

Open `http://localhost:8000/WeatherApplication.html`.

The Express server points its static directory at a nested `Project` path that does not match the supplied inner layout, and `/abc` calls `res.render` incorrectly. There is no useful `npm start` script. The JSX file imports functions that `App.js` does not export and references an unavailable `DefaultPlace`; the manifest does not configure a React build. MongoDB persistence is not a completed feature of this archive.

### 6.2 MERN e-commerce application

**Archive:** [Full-Stack-E-Commerce-MERN-APP-main.zip](Web%20Application%20Development/Full-Stack-E-Commerce-MERN-APP-main.zip)

A shopping application with separate Express/MongoDB backend and React frontend.

### Feature and technology overview

- Registration, sign-in, current-user lookup, and logout.
- Password hashing with bcrypt and JWT authentication using a cookie.
- User roles and admin-facing user/product screens.
- Product creation/update, images, brand/category information, description, original price, and selling price.
- Product lists, category browsing, search, filtering, and detail pages.
- Cart add/view/count, quantity update, and item deletion.
- Cloudinary image uploads via a configured cloud and `mern_product` upload preset.

**Frontend:** React 18, React Router 6, Redux Toolkit, React Redux, Tailwind CSS 3, React Icons, React Toastify, Moment, and Create React App (`react-scripts` 5).  
**Backend:** Express 4, Mongoose 8, bcryptjs, jsonwebtoken, cookie-parser, CORS, dotenv, and nodemon.

### Structure and data models

| Path inside extracted project | Role |
|---|---|
| `backend/index.js` | Express setup, middleware, route mount, server startup |
| `backend/config/db.js` | MongoDB connection |
| `backend/routes/index.js` | API routes |
| `backend/controller/product/`, `controller/user/` | Product, authentication, user, and cart handlers |
| `backend/middleware/authToken.js` | Token authentication |
| `backend/helpers/permission.js` | Product-upload permission check |
| `backend/models/` | User, product, and cart schemas |
| `frontend/src/pages/` | Customer and admin pages |
| `frontend/src/routes/index.js` | Browser routing |
| `frontend/src/common/index.js` | API endpoint definitions; backend base URL defaults to port 8080 |
| `frontend/src/helpers/uploadImage.js` | Cloudinary upload helper |
| `frontend/src/assest/` | Banners, branding, and product images; original spelling retained |

**User:** name, unique required email, password, profile picture, role, timestamps.  
**Product:** name, brand, category, image array, description, price, selling price, timestamps.  
**Cart item:** product ID/reference, quantity, user ID, timestamps.

### API reference

All routes below are mounted under `/api`. “Token” means the router applies `authToken`; it does not mean every handler performs complete role/ownership authorization.

| Method | Route | Purpose | Token |
|---|---|---|---|
| POST | `/signup` | Register | No |
| POST | `/signin` | Sign in | No |
| GET | `/user-details` | Current user | Yes |
| GET | `/userLogout` | Logout | No |
| GET | `/all-user` | List users | Yes |
| POST | `/update-user` | Update user | Yes |
| POST | `/upload-product` | Create product | Yes |
| GET | `/get-product` | List products | No |
| POST | `/update-product` | Update product | Yes |
| GET | `/get-categoryProduct` | Category representative products | No |
| POST | `/category-product` | Products by category | No |
| POST | `/product-details` | Product details | No |
| GET | `/search` | Search products | No |
| POST | `/filter-product` | Filter products | No |
| POST | `/addtocart` | Add cart item | Yes |
| GET | `/countAddToCartProduct` | Cart count | Yes |
| GET | `/view-card-product` | View cart; route spelling retained | Yes |
| POST | `/update-cart-product` | Update quantity | Yes |
| POST | `/delete-cart-product` | Remove item | Yes |

Frontend routes include `/`, `/login`, `/sign-up`, `/forgot-password`, `/product-category`, `/product/:id`, `/cart`, `/search`, `/admin-panel/all-users`, and `/admin-panel/all-products`.

### Setup — corrections required first

The archived `backend/index.js` incorrectly requires a MongoDB URL as if it were a Node module. Replace that import with:

```javascript
const connectDB = require('./config/db');
```

It also uses `27017 || process.env.PORT`, which always selects MongoDB's usual database port. Replace it with:

```javascript
const PORT = process.env.PORT || 8080;
```

Create a local `backend/.env`:

```dotenv
MONGODB_URI=mongodb://127.0.0.1:27017/semester_ecommerce
TOKEN_SECRET_KEY=replace-with-a-long-random-local-secret
FRONTEND_URL=http://localhost:3000
PORT=8080
```

Configure `frontend/.env` with your own Cloudinary account:

```dotenv
REACT_APP_CLOUD_NAME_CLOUDINARY=your-cloud-name
```

Create/configure the upload preset expected by `uploadImage.js`, or update that helper to match your own configuration. Then, in separate terminals from the extracted project root:

```bash
# Terminal 1
cd backend
npm install
npm run dev
```

```bash
# Terminal 2
cd frontend
npm install
npm start
```

The intended local addresses after these corrections are frontend `http://localhost:3000` and API `http://localhost:8080`. Ensure MongoDB is running and the frontend API base URL agrees with the server port. Sign-in sets `secure: true` cookies; verify cookie behavior in the selected local/deployed environment and configure it appropriately.

**Scope limits:** No order/payment/checkout API or password-reset API is supplied. A forgot-password page does not establish a working recovery service. The backend test script is a placeholder. Admin and cart handlers require further authorization review before real deployment. Product images in the archive are assets, not automatically seeded MongoDB records.

## 7. Mobile Application Development — Flutter Twitter clone

**Folder:** [Mobile Application Development](Mobile%20Application%20Development/)  
**Package:** `flutter_twitter_clone` · **Declared version:** `1.0.7+12`

This coursework folder contains a Flutter Twitter-style application based on **Fwitter by Sonu Sharma / TheAlphamerc**, with its original README, contribution documents, and MIT license retained. Repository ownership does not imply original authorship of the upstream code.

### Features represented in the source

| Area | Implemented screens / logic |
|---|---|
| Authentication | Email/password sign-up and sign-in, Google sign-in, verification, forgotten-password flow, logout |
| Feed | Timeline, compose tweets, image upload, tweet details, replies, retweets, likes, deletion, bookmarks |
| Profiles | Profile view/edit, profile images, followers/following, follow suggestions |
| Discovery | User search/filtering and suggested users |
| Messaging | Conversation list, one-to-one chat, new-message selection, conversation information |
| Notifications | Follow/like notification views and Firebase Messaging handling |
| Settings | Account, privacy, direct-message, notification, content, display, data, accessibility, proxy, and about screens |
| Supporting UI | Splash, sidebar, image viewer, link previews, translation-related code, QR-related UI |

The presence of a setting or screen does not guarantee a fully implemented backend preference. External Firebase features require valid configuration.

### Architecture and dependencies

`lib/main.dart` initializes Firebase, registers dependencies, and builds a `MultiProvider` application. Provider states manage authentication, feed, chat, search, notifications, suggestions, and navigation. `get_it` supplies shared-preference helpers.

| Directory | Responsibility |
|---|---|
| `lib/model/` | User, tweet/feed, chat, notification, bookmark, and link models |
| `lib/state/` | Application state and Firebase operations |
| `lib/ui/page/` | Auth, feed, profile, message, search, settings, and common screens |
| `lib/widgets/` | Shared controls, navigation, tweet widgets, and link previews |
| `lib/helper/` | Routes, validation, utility functions, and local preferences |
| `lib/resource/` | Push notification service |
| `lib/ui/theme/` | Colors, typography, theme, and icons |
| `assets/` | Images, icons, and fonts |
| `android/`, `ios/` | Native platform projects |
| `test/` | Utility and widget test files |
| `screenshots/` | Auth, Home, CreateTweet, TweetDetail, Chat, Notification, Profile, Search, and Settings images |

Core source paths use **Firebase Realtime Database**, including `profile`, `tweet`, `chats`, `chatUsers`, `notification`, and `bookmark`. Firebase Auth handles identity and Storage holds images. Firestore is declared as a dependency, but its presence should not be confused with the Realtime Database paths used in these state classes.

Other declared dependencies include Firebase Analytics, Messaging, Remote Config and Dynamic Links, Google Sign-In, Provider, cached images, image picker, shared preferences, Google Fonts, `intl`, URL launcher, sharing, RxDart, translator, and package information.

### Setup and compatibility

1. Open this course folder as the Flutter project root.
2. Align the Dart SDK constraint and package versions. The manifest declares `>=2.12.0 <3.0.0` alongside substantially newer package versions; the archived combination needs reconciliation before dependency resolution.
3. Configure your own Firebase Android/iOS apps and platform configuration files. Match package/bundle identifiers and enable the authentication providers and database/storage services used by the code.
4. Configure database and storage access rules for authenticated users, and complete Google sign-in/platform setup as applicable.
5. Review messaging and dynamic-link integrations against the SDK/service versions you choose. The chat implementation contains a server-key/Remote Config approach that should be replaced with a trusted backend sender rather than distributing a server credential to clients.
6. After resolving compatibility/configuration:

```bash
cd "Mobile Application Development"
flutter pub get
flutter analyze
flutter test
flutter run
```

The committed Android configuration specifies compile/target SDK **34**, minimum SDK **23**, Android Gradle Plugin **8.0.0**, Kotlin **1.9.23**, and Java/Kotlin bytecode targets **1.8**. Build-tool JDK requirements are separate from bytecode targets. Release builds currently point to the debug signing configuration.

No successful fresh Android/iOS build or Firebase-backed end-to-end test is claimed by this README.

### Screenshots and upstream attribution

Browse the [included screenshots](Mobile%20Application%20Development/screenshots/) and the [original project README](Mobile%20Application%20Development/README.md). Screenshots are preserved project assets, not newly captured proof of the current build.

- Original project: [TheAlphamerc/flutter_twitter_clone](https://github.com/TheAlphamerc/flutter_twitter_clone).
- Original author identified in the retained README/license: **Sonu Sharma**.
- [MIT license](Mobile%20Application%20Development/LICENSE), copyright 2019 Sonu Sharma.
- [Changelog](Mobile%20Application%20Development/CHANGELOG.md) and [contribution guide](Mobile%20Application%20Development/CONTRIBUTING.md).

## 8. Artificial Intelligence — AI Code Efficiency Debugger

**Source:** [app.py](Artificial%20Intelligence/app.py) · [Web template](Artificial%20Intelligence/templates/index.html)

A Flask prototype that classifies pasted code as **Efficient** or **Inefficient**, reports model confidence, and attempts to execute selected languages to display output/errors.

### Machine-learning pipeline

1. Read `ai_code_efficiency_dataset.csv` with pandas.
2. Convert the **code text only** to bag-of-words features using `CountVectorizer`.
3. Label rows efficient when `efficiency_score > 65`; otherwise label them inefficient.
4. Fit `LogisticRegression` at application startup.
5. Save `model.pkl` and `vectorizer.pkl` with joblib.
6. On a POST to `/`, reload the artifacts, transform submitted code, predict the label and its probability, execute where supported, and render the result.

The included dataset has **12 examples**, with columns `language`, `code`, and `efficiency_score`. Its examples cover JavaScript, C++, C#, and Python. The language column is not fed into the classifier. Java and Dart execution choices do not mean the dataset includes those languages.

### Language support

| Language | Selectable in UI | Execution implementation |
|---|---|---|
| Python | Yes | `exec()` in the Flask process, with stdout redirected |
| JavaScript | Yes | Temporary `.js` file executed with Node.js |
| Java | Yes | Temporary source, `Main` class rename, `javac`, then `java` |
| Dart | Yes | Temporary `.dart` file executed with Dart SDK |
| C++ | Yes | No execution branch; returns “Language execution not supported.” |
| C# | Yes | No execution branch; returns “Language execution not supported.” |

JavaScript, Java execution, and Dart subprocess calls request a five-second timeout. Python execution and Java compilation do not have that timeout. Temporary files are normally removed on the successful path, but exceptions can leave files behind.

### Files and local setup

| File | Purpose |
|---|---|
| `app.py` | Training, Flask route, prediction, and language execution |
| `templates/index.html` | Language selector, code textarea, submit/reset, result panel |
| `ai_code_efficiency_dataset.csv` | Small labeled example dataset |
| `model.pkl`, `vectorizer.pkl` | Saved artifacts, regenerated at startup |
| `AI-Based Code Debugger.txt` | Empty marker |

```bash
cd "Artificial Intelligence"
python -m venv .venv
# Windows:
.venv\Scripts\activate
# Linux/macOS alternative:
# source .venv/bin/activate
python -m pip install flask pandas scikit-learn joblib
python app.py
```

Open `http://127.0.0.1:5000`. To use the other execution branches, make `node`, `javac`/`java`, and/or `dart` available on `PATH`. Run from this folder so the CSV and model paths resolve. The repository does not pin dependency versions in a requirements file.

### Interpretation and limits

This is a small text-classification experiment, not a reliable measurement of runtime complexity, memory usage, or code quality. Confidence is a classifier probability, not an efficiency percentage. No held-out evaluation or accuracy report is included. The committed version does not implement OCR, image upload, automatic code repair, or C++/C# execution.

Only run trusted sample code locally: submitted Python executes directly inside the server, and other languages run as local subprocesses. There is no execution sandbox. The app starts in Flask debug mode, and its `shell=True` subprocess invocation needs platform-specific review. It should not be exposed as a public code-execution service in its current form.

## Academic material and attribution

- **Collection maintainer:** [Muhammad Talha Zahid](https://github.com/TalhaZahid13).
- **Institution:** Riphah International University, Lahore, as identified in the course reports.
- **Named collaborators:** Marwa Jabbar in the gym report; Hafiza Shujia Yousaf in the banking materials.
- **Additional presentation:** the student-management slides identify Aniqa Razzaq and Laraib Asghar; they are not represented here as Talha's original implementation.
- **Upstream mobile project:** Sonu Sharma / TheAlphamerc; preserve its MIT notice and existing contributor credits.
- **Repository-wide licensing:** no root license file is supplied. Individual project notices and third-party materials should be retained; the Flutter subproject's MIT license is not presented as a license for the entire collection.

Reports and slides preserve the original submissions and may describe proposed features, contain template remnants, or differ from the committed code. Empty text markers do not supply additional project documentation. IDE settings, cached server configuration, generated outputs, and bundled dependencies are supporting artifacts rather than separate projects.

## Maintenance opportunities

| Area | Useful next step |
|---|---|
| Repository organization | Extract archived application source into tracked folders; keep large dependency/build directories out of source control |
| Reproducibility | Add project-specific dependency manifests, environment examples, and verified setup instructions |
| C++ gym | Repair serialization, update/delete logic, input handling, and menu flow |
| Solar desktop | Package Derby dependencies; implement CRUD handlers and chart views |
| Solar web | Complete servlet implementation, route alignment, and navigation |
| Banking demo | Fix stored dates and duplicate grouping; align report claims with implemented algorithms |
| OS simulator | Fix process identifiers; implement scheduling/I/O and connect synchronization navigation |
| Weather | Separate browser/server code, repair startup, and correct forecast rendering |
| MERN | Repair backend import/port, complete role/ownership authorization, and add meaningful integration checks |
| Flutter | Align SDK/packages, Firebase setup, messaging architecture, and release signing |
| AI debugger | Expand/evaluate the dataset and isolate execution before considering hosted use |

**Documentation basis:** source and archive contents inspected from repository commit `9c4cde72c721f51d0bde0d5781bb11a21a55b801`. This README documents the preserved implementations; it does not silently repair their source code or claim production readiness.

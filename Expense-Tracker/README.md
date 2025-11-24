#  Personal Expense Tracker CLI

##  Project Title
**Personal Expense Tracker CLI**

##  Overview of the Project
This command-line interface (CLI) application is built in Java to provide a simple, effective tool for personal financial management. It helps users gain clarity on their spending habits by processing expense data from a CSV file. The system implements three core modules: Data Management, Analysis, and Budget Tracking, allowing users to move beyond simple record-keeping into actionable financial insights.

[cite_start]The project fulfills the requirements of the "Build Your Own Project" flipped course evaluation by demonstrating core Java concepts, file handling, data structures (Maps), and modular programming[cite: 5, 6].

## [cite_start] Features [cite: 92, 103]
The application provides the following major functional capabilities:

* **Data Management (FR1.1):** Allows users to add new expense records (Date, Category, Amount) directly via the command line, appending them to the central `expenses.csv` file.
* **Expense Analysis (FR2.1 & FR2.2):** Calculates and displays the **Total Spend** and provides a detailed **Percentage Breakdown** of spending by category, helping users quickly identify major expense areas.
* **Budgeting (FR3.1 & FR3.2):** Enables the setting of category-specific budgets and generates a **Budget Status Report**, showing whether the actual spend is over or under the allocated limit.

## [cite_start] Technologies/Tools Used [cite: 93]
| Component | Tool/Technology | Purpose |
| :--- | :--- | :--- |
| **Primary Language** | Java (JDK 17+) | Core implementation logic and file I/O. |
| **Data Storage** | CSV (Comma Separated Values) | Simple, structured, and platform-independent data persistence for expenses. |
| **Version Control** | Git / GitHub | Used for tracking development history, modularizing code, and final submission. |
| **Development Environment** | Command Line (PowerShell/Terminal) | User interaction and execution environment. |

## [cite_start] Steps to Install & Run the Project [cite: 94]

1.  **Prerequisites:** Ensure the **Java Development Kit (JDK)** is installed and configured on your system (version 8 or higher is recommended).
2.  **Clone the Repository:**
    ```bash
    git clone [Your-GitHub-Repository-Link-Here]
    cd Expense-Tracker
    ```
3.  **Compile the Source Code:**
    ```bash
    javac Expense.java
    ```
4.  **Run the Application:**
    ```bash
    java Expense
    ```
    *Note: The first time you run, the program will automatically create the necessary `expenses.csv` and `budgets.txt` files.*

## [cite_start] Instructions for Testing [cite: 95]
To verify all major functional requirements are met, follow these steps:

1.  **Test Data Management (Option 1):** Use **Option 1** to enter at least five expenses across three different categories (e.g., Food, Transport, Bills).
2.  **Test Budget Setup (Option 3 -> 1):** Use **Option 3**, then **1**, to set a budget for at least one of the categories you added data for.
3.  **Test Analysis (Option 2):** Use **Option 2** to confirm the `Total Spend` and `Category Breakdown` accurately reflect the data you entered in Step 1.
4.  **Test Budget Status (Option 3 -> 2):** Use **Option 3**, then **2**, to check the `Budget Status Report`. Verify that the status column (e.g., `OVER`, `Under`) correctly compares the actual spend against the budget set in Step 2.

## [cite_start] Screenshots (Optional but Recommended) [cite: 96]
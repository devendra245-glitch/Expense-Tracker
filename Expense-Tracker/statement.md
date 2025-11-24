#  Project Statement and Scope

## [cite_start]Problem Statement [cite: 99]
Many individuals lack clear, aggregated visibility into their daily, weekly, and monthly spending, leading to guesswork in personal finance management and making it difficult to adhere to savings goals. Manual tracking is often inefficient and prone to human error. The need exists for a straightforward, automated tool that can efficiently process raw transaction data and provide immediate, actionable analytical insights.

## [cite_start]Scope of the Project [cite: 100]
The **Personal Expense Tracker CLI** is scoped as a **command-line application** built entirely in Java. Its primary function is to process expense records stored in a local **CSV file**.

**Inclusions (What the project addresses):**
* **Data Persistence:** Storing all expense data in a single, local CSV file (`expenses.csv`).
* **Core Functionality:** Expense addition, comprehensive analysis, and budget tracking.
* **Error Handling:** Basic validation for file path and numerical input (Non-Functional Requirement: Reliability).

**Exclusions (What is out of scope):**
* Graphical User Interface (GUI).
* User authentication or security features (beyond basic file security).
* Integration with external APIs (e.g., bank accounts).
* Complex database management systems (e.g., SQL).

## [cite_start]Target Users [cite: 102]
This tool is primarily designed for users who need a simple, fast, and local solution for expense tracking:
* **Students and Young Professionals:** Individuals managing limited or fixed incomes.
* **Budget-Conscious Users:** Anyone actively attempting to monitor and limit spending within specific categories.
* **Technically Proficient Users:** Individuals comfortable interacting with a command-line interface (CLI).

## [cite_start]High-Level Features [cite: 103]
[cite_start]The application is structured around three high-level capabilities, ensuring the project meets the requirement for **three major functional modules**[cite: 21]:

1.  **Data Management:** Facilitates the creation and modification of expense records via command-line input.
2.  **Expense Analysis:** Calculates the total expenditure and provides a categorized percentage breakdown of all spending data.
3.  **Budget Tracking:** Allows the user to set financial caps (budgets) on specific spending categories and generates a report comparing actual spend against those limits.
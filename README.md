# BudgetBuddy 💰📊

A **simple, offline expense management app** built with **Java, XML, and SQLite**, following **Clean Architecture**. Users can **add transactions**, **track expenses/gains**, and **view reports**.

---

## 📌 Features
✅ **Add, View, and Delete Transactions**  
✅ **Categorize Transactions** (e.g., Food, Rent, Salary)  
✅ **Track Income and Expenses** (`gain` or `expense`)  
✅ **Persistent Data Storage with SQLite**  
✅ **Clean Architecture** (Domain, Data, and Presentation layers)  

---

## 🛠 Tech Stack
- **Language**: Java 
- **UI**: XML  
- **Database**: SQLite
- **Architecture**: Clean Architecture  
- **Libraries**:  
  - RecyclerView for displaying records  
  - ViewModel & LiveData for UI updates  
  - DiffUtil for efficient list updates  

---

## 📂 Folder Structure

```
BudgetBuddy/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/budgetbuddy/
│   │   │   │   ├── data/                 
│   │   │   │   │   ├── local/         
│   │   │   │   │   │   ├── DatabaseHelper.java
│   │   │   │   │   ├── repository/      
│   │   │   │   │   │   ├── RecordRepositoryImpl.java
│   │   │   │   ├── domain/              
│   │   │   │   │   ├── models/         
│   │   │   │   │   │   ├── ERecord.java
│   │   │   │   │   ├── repository/      
│   │   │   │   │   │   ├── RecordRepository.java
│   │   │   │   │   ├── usecases/      
│   │   │   │   │   │   ├── AddERecords.java
│   │   │   │   │   │   ├── GetERecords.java
│   │   │   │   ├── presentation/    
│   │   │   │   │   ├── viewmodel/      
│   │   │   │   │   │   ├── RecordViewModel.java
│   │   │   │   │   ├── ui/            
│   │   │   │   │   │   ├── MainActivity.java
│   │   │   │   │   │   ├── RecordAdapter.java
│   │   │   │   ├── res/                   
│   │   │   │   │   ├── layout/
│   │   │   │   │   │   ├── activity_main.xml
│   │   │   │   │   │   ├── erecord.xml
│   │   │   ├── AndroidManifest.xml
```

---

## 🚀 Installation
### **1️⃣ Clone the Repository**
```sh
git clone https://github.com/akryptic/BudgetBuddy.git
cd BudgetBuddy
```

### **2️⃣ Open in Android Studio**
- **Open Android Studio**  
- **Select "Open an Existing Project"**  
- **Choose `BudgetBuddy` folder**  

### **3️⃣ Build & Run**
- Connect an **Android device or emulator**  
- Click **Run ▶️** in Android Studio  

--

## 🏗 Future Enhancements
🔹 **Filtering & Sorting Options**  
🔹 **Charts & Reports for Expense Insights**  
🔹 **Export & Backup Transactions**  

---

## 🤝 Contributing
🔹 **Fork the Repository**  
🔹 **Create a New Branch** (`git checkout -b feature-branch`)  
🔹 **Commit Changes** (`git commit -m "Add new feature"`)  
🔹 **Push Changes** (`git push origin feature-branch`)  
🔹 **Create a Pull Request**  

---

## 📜 License
This project is licensed under the **MIT License**.  

---

## 🙌 Credits
Developed by **[Anil Kumar & Suarabh Singh]** 🚀

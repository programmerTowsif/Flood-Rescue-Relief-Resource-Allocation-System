# Flood Rescue & Relief Resource Allocation System

**Course:** CSE 1116 — Object Oriented Programming Laboratory  
**Language:** Java  
**GUI:** Java Swing  
**Team Size:** 3 Members  
**Project Type:** Academic OOP Project / Simulation

> This application is a desktop simulation for managing flood-relief shelters, resource inventory, relief requests and resource allocation. It does not connect to live emergency services or dispatch real rescue teams.

---

## 1. Project Goals

The system will help a relief coordinator to:

- Register and manage flood-relief shelters.
- Maintain food, drinking-water and medical-supply inventory.
- Create and search relief requests.
- Identify urgent requests using a simple priority score.
- Allocate available resources to shelters.
- Prevent invalid allocations.
- View allocation records.
- Display useful error messages through a Java Swing GUI.

## 2. Technology and Requirements

- Java JDK (same version for all team members).
- VS Code or another Java IDE.
- Java Swing for the desktop GUI.
- Git and GitHub for version control.
- No external database or web API is required for the first version.
- Data will initially be stored in memory.

**JDK Version:** `________________`

**GitHub Repository:** `________________`

---

## 3. Project Structure

```text
FloodReliefSystem/
├── src/
│   └── floodrelief/
│       ├── interfaces/
│       │   └── Prioritizable.java
│       │
│       ├── model/
│       │   ├── ReliefRequest.java
│       │   ├── EmergencyReliefRequest.java
│       │   ├── FoodRequest.java
│       │   ├── MedicalRequest.java
│       │   ├── Shelter.java
│       │   ├── ReliefResource.java
│       │   ├── FoodSupply.java
│       │   ├── WaterSupply.java
│       │   ├── MedicalSupply.java
│       │   └── ReliefAllocation.java
│       │
│       ├── service/
│       │   └── ReliefManager.java
│       │
│       ├── exception/
│       │   └── InsufficientResourceException.java
│       │
│       └── ui/
│           └── ReliefGUI.java
│
├── docs/
│   ├── UML_Diagram.png
│   ├── Project_Proposal.pdf
│   ├── Project_Report.docx
│   └── screenshots/
│
├── README.md
└── .gitignore
```

---

## 4. Class and Interface List

The project will contain exactly **13 top-level classes and 1 interface**.

| No. | Type | Name | Main Responsibility |
|---|---|---|---|
| 1 | Abstract class | ReliefRequest | Common request information |
| 2 | Class | EmergencyReliefRequest | Emergency request and priority |
| 3 | Class | FoodRequest | Food-related requests |
| 4 | Class | MedicalRequest | Medical-related requests |
| 5 | Class | Shelter | Shelter information and capacity |
| 6 | Abstract class | ReliefResource | Common resource information |
| 7 | Class | FoodSupply | Food inventory |
| 8 | Class | WaterSupply | Water inventory |
| 9 | Class | MedicalSupply | Medical inventory |
| 10 | Class | ReliefAllocation | Allocation records |
| 11 | Class | ReliefManager | Main business logic |
| 12 | Exception class | InsufficientResourceException | Insufficient-stock errors |
| 13 | GUI class | ReliefGUI | Swing interface and main() |
| - | Interface | Prioritizable | Priority-score contract |

### Important Design Rules

- `ReliefGUI` will contain the `main()` method.
- No separate `Main` class will be created.
- `ReliefManager` will control all business logic.
- GUI will not directly modify model fields or inventory.
- Model attributes will normally be private.
- `ArrayList` will store shelters, requests and allocations.
- `HashMap<Integer, ReliefResource>` will manage inventory.
- `InsufficientResourceException` will be a checked exception.
- First version will support full allocation only.
- If stock is insufficient, allocation will be rejected and stock will remain unchanged.
- Food requests must match food resources.
- Medical requests must match medical resources.
- Expired food and medical supplies will not be allocated.
- Water requests will use the agreed request design without adding another class.

**Important:** Any change to class names, relationships or method signatures must be discussed and approved by the team first.

---

## 5. Team Members and Responsibilities
# towsif(team owner)
# saikat
# munna

Replace the placeholders with your actual names.

| Member | Name | Main Responsibility |
|---|---|---|
| Member 1 | `______________` | Interface and request hierarchy |
| Member 2 | `________________` | Shelter, resource and allocation models |
| Member 3 | `________________` | ReliefManager and Swing GUI |

### Detailed Work Distribution

**Member 1 — Request Hierarchy**

- Prioritizable
- ReliefRequest
- EmergencyReliefRequest
- FoodRequest
- MedicalRequest
- Request validation
- Request-related testing

**Member 2 — Shelter and Resource Models**

- Shelter
- ReliefResource
- FoodSupply
- WaterSupply
- MedicalSupply
- ReliefAllocation
- InsufficientResourceException
- Inventory testing

**Member 3 — Management and GUI**

- ReliefManager
- ReliefGUI
- Swing interface
- Integration coordination
- GUI testing
- README and report coordination

**Shared Responsibilities**

All members must participate in:

- UML review
- Code review
- Integration
- Testing
- Screenshots
- Project report
- Viva preparation

Everyone should understand the complete project, not only their assigned classes.

---

## 6. Development Phases

Use this checklist to track project progress.

### Phase 1 — Architecture Review

- [ ] UML diagram approved by all members.
- [ ] Inheritance hierarchy confirmed.
- [ ] All 13 classes and 1 interface confirmed.
- [ ] Water-request representation decided.
- [ ] Allocation rules decided.
- [ ] Priority calculation rules decided.
- [ ] Architecture confirmed with instructor where necessary.

### Phase 2 — Class Contracts

- [ ] Attributes and data types finalized.
- [ ] Access modifiers finalized.
- [ ] Constructors finalized.
- [ ] Method signatures finalized.
- [ ] Validation rules documented.
- [ ] Overloading and overriding examples identified.
- [ ] Exception conditions documented.

### Phase 3 — Project Setup

- [ ] GitHub repository created.
- [ ] All 3 members can access the repository.
- [ ] Java package structure created.
- [ ] Empty class files created.
- [ ] Empty interface file created.
- [ ] All files compile successfully.
- [ ] `.gitignore` added.

### Phase 4 — Model Development

- [ ] Request hierarchy implemented.
- [ ] Shelter class implemented.
- [ ] Resource hierarchy implemented.
- [ ] ReliefAllocation implemented.
- [ ] Constructors tested.
- [ ] Encapsulation checked.
- [ ] Inheritance checked.
- [ ] Overriding and polymorphism tested.

### Phase 5 — Business Logic

- [ ] ReliefManager implemented.
- [ ] ArrayList implemented.
- [ ] HashMap implemented.
- [ ] Add and search operations implemented.
- [ ] Priority calculation implemented.
- [ ] Resource allocation implemented.
- [ ] Stock validation implemented.
- [ ] Custom exception implemented.
- [ ] Expiry validation implemented.

### Phase 6 — GUI Development

- [ ] Main Swing window created.
- [ ] Add Shelter feature completed.
- [ ] Add Resource feature completed.
- [ ] Create Request feature completed.
- [ ] Allocate Resource feature completed.
- [ ] Search feature completed.
- [ ] Allocation history displayed.
- [ ] Error messages displayed correctly.
- [ ] GUI refreshes after operations.

### Phase 7 — Integration and Testing

- [ ] All member branches reviewed.
- [ ] All code merged.
- [ ] Full application runs successfully.
- [ ] Normal operations tested.
- [ ] Invalid input tested.
- [ ] Insufficient stock tested.
- [ ] Duplicate ID tested.
- [ ] Expired supply tested.
- [ ] Resource mismatch tested.
- [ ] Collection operations tested.
- [ ] Screenshots saved.

### Phase 8 — Report and Viva

- [ ] Cover page completed.
- [ ] One-page proposal completed.
- [ ] Report completed.
- [ ] UML diagram included.
- [ ] Attributes table included.
- [ ] Constructor explanation included.
- [ ] Inheritance explanation included.
- [ ] Polymorphism explanation included.
- [ ] Exception handling explained.
- [ ] Collections explained.
- [ ] GUI screenshots included.
- [ ] Testing table completed.
- [ ] Individual contributions recorded.
- [ ] Acknowledgements included.
- [ ] References included.
- [ ] All 3 members prepared for viva.

---

## 7. Simple Task Board

Update this table whenever a task starts or finishes.

| Task | Owner | Status |
|---|---|---|
| Finalize UML | All members | To do |
| Finalize class contracts | Member 1 + All | To do |
| Create Java project structure | Member 3 | To do |
| Implement request hierarchy | Member 1 | To do |
| Implement Shelter and resources | Member 2 | To do |
| Implement ReliefAllocation | Member 2 | To do |
| Implement custom exception | Member 2 | To do |
| Implement ReliefManager | Member 3 | To do |
| Build Swing GUI | Member 3 | To do |
| Integration testing | All members | To do |
| Prepare report | All members | To do |

### Task Status

Use only these status values:

- `To do`
- `In progress`
- `Review`
- `Done`
- `Blocked`

---

## 8. Git and GitHub Rules

To avoid conflicts, all members must follow these simple rules.

1. `main` branch must contain working code.
2. Do not directly commit unfinished code to `main`.
3. Create a branch for each feature.
4. Use meaningful commit messages.
5. Review code before merging.
6. Pull the latest code before starting new work.
7. Do not commit `.class` files or unnecessary IDE files.

### Example Branch Names

```text
feature/request-model
feature/shelter-model
feature/resource-model
feature/relief-manager
feature/relief-gui
```

### Example Commit Messages

```text
Add Prioritizable interface
Create ReliefRequest abstract class
Add Shelter validation
Implement FoodSupply
Add resource stock management
Handle insufficient resource exception
Build relief allocation GUI
Add allocation tests
```

---

## 9. Testing Checklist

| Test Case | Expected Result | Status |
|---|---|---|
| Add valid shelter | Shelter stored | [ ] |
| Add duplicate shelter ID | Rejected | [ ] |
| Enter negative quantity | Rejected | [ ] |
| Create food request | Request stored | [ ] |
| Allocate with enough stock | Successful allocation | [ ] |
| Allocate with insufficient stock | Exception handled | [ ] |
| Allocate food request with water | Rejected | [ ] |
| Allocate expired medicine | Rejected | [ ] |
| Enter letters in numeric field | Error message displayed | [ ] |
| Search existing request | Correct request found | [ ] |
| Remove or update collection item | Correct result | [ ] |

---

## 10. Team Meeting Notes

Use this section after every team meeting.

### Meeting 1

**Date:** `________________`

**Members Present:** `________________`

**Decisions Made:**

1. `________________________________`
2. `________________________________`
3. `________________________________`

**Pending Questions:**

1. `________________________________`
2. `________________________________`

**Assigned Tasks:**

| Task | Assigned To | Deadline |
|---|---|---|
| | | |
| | | |
| | | |

### Meeting 2

**Date:** `________________`

**Members Present:** `________________`

**Progress:**

- `________________________________`
- `________________________________`

**Problems:**

- `________________________________`

**Next Actions:**

- `________________________________`

---

## 11. Important Project Rules

- Keep the first version simple and functional.
- Do not add unnecessary classes.
- Do not change approved method signatures without discussion.
- Test every class before integrating it.
- Keep GUI and business logic separate.
- Use meaningful variable and method names.
- All team members must understand the full architecture.
- Save screenshots as soon as a feature passes testing.
- Keep the README updated.

## 12. University Submission Reminder

The final PDF must follow the CSE 1116 Student Guidelines.

Required order:

1. Front Cover — 1 page
2. Project Proposal — exactly 1 page
3. Project Report — 8 to 12 pages

The report should explain the program using words, diagrams, tables and screenshots. Do not paste source code into the PDF.

Every testing claim should have matching screenshot evidence.

External sources and AI assistance must be acknowledged honestly.

---

## Final Team Principle

**Plan → Design → Code → Test → Integrate → Document → Present**

Our goal is not simply to create 13 classes.

Our goal is to build a working, understandable and properly tested Java OOP application that every team member can explain during the project demonstration and viva.

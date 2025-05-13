# Animal Identification Tree

## Project Description

This application uses a **Binary Search Tree (BST)** to identify animals through a series of yes-or-no questions. Each internal node in the tree represents a characteristic (e.g., *has gills*, *is furry*), and each leaf node represents a specific animal (e.g., *dog*, *fish*).

When the program fails to correctly identify an animal, it prompts the user for distinguishing characteristics and the correct answer, updating the BST accordingly. These updates are saved to a file, allowing the program to "learn" and improve its identification accuracy in future executions.

The program continues to run in a loop, allowing users to identify multiple animals in a single session.

### Key Features

- ✅ **Dynamic learning** – Expands the tree with new animals and traits based on user input  
- 💾 **Persistent memory** – Saves and loads the BST from a file between sessions  
- 🌳 **Binary decision structure** – Follows yes/no paths to classify animals  

## Usage

1. Open the project in any Java IDE (e.g., Eclipse, IntelliJ IDEA, NetBeans)
2. Import the project files
3. Run the `main` class to start the program

## Acknowledgments
- Inspiration: This work was based on course materials provided by Reese Pearsall, CSCI 232: Data Structures and Algorithms & Data Structures and Algorithms, Montana State Univeristy - Bozeman.
- Libraries/Tools: All code is written in Java using standard libraries.

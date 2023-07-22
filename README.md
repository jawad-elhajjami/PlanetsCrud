# PlanetsCrud

PlanetsCrud is a Java-based Android app that utilizes the Room Database to facilitate Create, Read, Update, and Delete (CRUD) operations on a database of planets. It also includes form validation and utilizes Material UI components to provide a user-friendly experience.

## Features

- **CRUD Operations:** PlanetsCrud allows users to perform all essential CRUD operations (Create, Read, Update, Delete) on a collection of planets. Users can add new planets, view existing ones, edit planet details, and delete planets as needed.

- **Room Database:** The app leverages the Room Database, an abstraction layer over SQLite, to store and manage the planet data efficiently. This ensures smooth data management and retrieval.

- **Form Validation:** To maintain data integrity and provide a seamless user experience, PlanetsCrud incorporates form validation. Users are prompted to enter valid information when adding or updating planet details.

- **Material UI Components:** The app utilizes Material Design components provided by the Android Material Design library. These components ensure a modern and consistent visual style, enhancing the overall user interface.

## How to Use

1. **View Planets List:** Upon launching the app, users are presented with a list of existing planets. If there are no planets in the database, a message will indicate that there are no planets to show.

2. **Add a New Planet:** To add a new planet, click on the floating action button (FAB) with a plus icon. This action will redirect the user to a form where they can provide the planet's name, size, and description. After entering the details, click the "Create Planet" button to add the new planet to the database.

3. **Edit Planet Details:** To edit the details of an existing planet, simply tap on the desired planet from the list. This action will lead to the planet's details form, pre-populated with its current information. Make the necessary changes and click the "Update Planet" button to save the modifications.

4. **Delete a Planet:** To remove a planet from the database, perform a long press on the planet's card in the list. A context menu will appear, offering options such as "Delete" and "Details." Choose "Delete" to initiate the deletion process. A confirmation dialog will ask the user to confirm the deletion before proceeding.

5. **Search Planets:** PlanetsCrud includes a search functionality to help users find specific planets quickly. At the top of the screen, users can enter search queries, and the app will filter the list of planets accordingly.

## Requirements

- Android device or emulator with Android OS.

## How to Install

1. Clone the PlanetsCrud repository from GitHub.
2. Open the project in Android Studio.
3. Connect your Android device to the computer, or set up an emulator.
4. Build and run the app from Android Studio onto your device or emulator.

## Dependencies

- Room Database: [Link to Room Library](https://developer.android.com/training/data-storage/room)
- Android Material Design Library: [Link to Material Design Library](https://material.io/develop/android/docs/getting-started)

## Contribution

Contributions to PlanetsCrud are welcome! If you find any issues or have ideas to enhance the app, please create a pull request or submit an issue on GitHub.

## License

This project is licensed under the [MIT License](LICENSE). Feel free to use and modify the code as per the license terms.

## Acknowledgments

We would like to express our gratitude to the Android Open Source community and the creators of the libraries used in this project. Your contributions enable developers to create amazing applications. Thank you!

---

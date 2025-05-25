<!DOCTYPE html>
<html lang="en">

<body>
    <h1>Mobile App - Kotlin</h1>
    <p>This project is an Android application developed in Kotlin. It allows users to view and select available orders, generating a unique token that can be used by external systems to validate the selected order.
</p>
    <a href="https://www.youtube.com/watch?v=xN9yjF8w_xs" target="_blank">
        <img src="/gif/gif.gif" alt="video" width="460" height="300">
    </a>
    <p>Click the image above to watch a demo video of the app.</p>
</body>
</html>
## Description

### Order Selection  
The app features a simple and intuitive interface where users can browse a list of available orders and select the one they want to process.

### Token Generation  
After selecting an order, the app automatically generates a unique token. This token is linked to the order and can be sent to external systems for validation or integration.

## Features

- **Order Listing:** Interactive list of available orders, loaded from a local source or API.
- **Order Selection:** Users can select an order directly from the list.
- **Token Generation:** A unique token is generated upon order selection, using UUID or another secure method.
- **External System Integration:** The generated token can be shared with third-party systems for processing.
- **No Authentication:** The app does not require login or authentication to be used.

## Technologies Used

- **Language:** Kotlin  
- **Platform:** Android SDK  
- **UI:** Jetpack Compose or XML Layouts  
- **Networking (optional):** Retrofit or other HTTP clients  
- **Token Generator:** UUID or secure random methods

## Setup

1. Clone the repository to your local machine.
2. Open the project in Android Studio.
3. Configure `local.properties` and any required environment variables.
4. Connect an Android device or start an emulator.
5. Build and run the application.

## Contributing

Contributions are welcome! Feel free to open issues or submit pull requests with improvements, bug fixes, or new features.

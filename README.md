
Here’s the updated version without the mention of missing images:

Architecture Overview
This project uses Clean Architecture with the MVVM pattern. The business logic is separated into use cases, and the UI is built using Jetpack Compose for a reactive and declarative approach. ViewModels handle the state management, and Kotlin Coroutines with Flow are used for asynchronous data operations, ensuring efficient and smooth data flow between the UI and business logic.

To handle server issues, such as HTTP 400 errors, the app automatically retries requests without showing errors to the user. This ensures a seamless user experience even in case of server-side problems.

Demo Video
You can see the demo of the working app here on YouTube .

https://youtube.com/shorts/vQWdpA2wvcg?feature=share


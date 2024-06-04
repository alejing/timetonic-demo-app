# Timetonic Demo App

Repository with the code for a simple Android app featuring login, landing page, and book loading using the public API from Timetonic.

![timetonic_demo_app.gif](timetonic_demo_app.gif)

This application has two requirements:

**Login Page**
- Create a login page with fields for email and password.
- Authenticate against Timetonic's public API using some credentials:
- Use those endpoints in order to authenticate:
  * /createAppkey, /createOauthkey, /createSesskey
- Upon successful authentication, store the session token for later use.

**Landing Page**
- Implement a landing page that displays a list of books associated with the authenticated account.
- Fetch the books from the API using the stored session token and using this endpoint: /getAllBooks
- Display the name and image of each book (filtering out the “contacts books”) in a scrollable list.
- The image of each book can be found in the JSON response, inside the object “ownerPrefs” then the value of key “oCoverImg”. This value is used to create the url in this format:
  * Ex: https://timetonic.com/live/dbi/in/tb/FU-1701419839-65699b3f78400/modele-suivi-projet.jpg

This application makes use of technologies and concepts such as:
- Navigation between fragments.
- Data communication between fragments using safeArgs.
- Consumption and serialization of data through an API with Retrofit.
- Simple authentication flow controlled through interfaces in the ViewModel.
- Data loading (books) and observable updating through LiveData.
- Efficient lists using the adapter pattern applied to RecyclerView for loading each book.
- Image loading using Glide.
- Simple network connection status validation.
- Simple text field validation for app login.
- Simple use of Material Design to customize graphic components.
- Separation of responsibilities and use of clean architecture for the application implementation.
- Documentation and project organization according to the development context.
- Git management.

Regards.

Keep coding 😎
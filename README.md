# pokolei.eu

This web application enables users to check train schedules and track the arrivals and departures of trains for a specific station of their choice.

## Getting Started

### Prerequisites

* Java 17 (I've used Amazon Corretto 17.0.7) 
* Maven 3.9.1
* Node.js 18.16.0
* PostgreSQL 15.2.2

### Executing program

1. The application requires the creation of a PostgreSQL database. Assuming the database has been created, the first step will be to adjust the `application.properties` file.
```
# DataSource Configuration
spring.datasource.url=jdbc:postgresql://127.0.0.1:5432/pokolei
spring.datasource.username=postgres
spring.datasource.password=password

# Flyway Configuration
spring.flyway.enabled=true
spring.flyway.url=jdbc:postgresql://127.0.0.1:5432/pokolei
spring.flyway.user=postgres
spring.flyway.password=password
```

2. The next step is to run the command "mvn clean install".
```
mvn clean install
```
If the database was set up correctly, everything should work fine if not, `ApplicationTests` will fail.

3. Next, we can launch spring boot application with the train composition backend.
```
mvn spring-boot:run
```
4. The application should be running, so we can move on to launching the frontend of the application. To do that, let's open a new terminal and move to the frontend part of the project.
```
cd .\src\frontend
```
5. Now we need to install all the node.js dependencies
```
npm install
```
6. After all dependencies are installed, we can start the frontend of the app, which should start concurrently with the backend that's responsible for arrival and departure timetables.
```
npm start
```

If everything went well on http://localhost:8080/ we should see the home screen of the website.

### All URLs of a website

* `/` - home screen
* `/departures` - allows user to get the departure timetable for a selected train station.
* `/arrivals` - allows user to get the arrivals timetable from a selected train station.
* `/trains` - list of all trains that exist in the database. Trains can be selected by clicking on a row in the table.
* `/train-composition/:trainId/:trainFullName` - reachable after selecting train in the /trains url. Displays the current composition of the train. Schemas of cars can be displayed by selecting the schema button.
* `/login` - login page for the admin panel. After successful login, it should redirect the user to the admin panel.
* `/admin` - admin panel of the website. Allows to edit and remove locomotives and cars and add, edit or remove trains (add and edit form for trains are on a different URL). **Default credentials for admin panel are `admin:admin`**. 
This can currently be changed only by editing the users table in database.
* `/admin/add-train` - allows user to create trains from existing locomotives and cars.
* `/admin/edit-train` - allows user to edit an already existing train.

## Author
Przemysław Rutkowski
(https://www.linkedin.com/in/szoggun/)


# Patika+ Tourism Agency Java Swing Project
---

## Tourism Agency

The operational logic of the Tourism Agency is as follows: It offers a wide range of digital panels to manage hotels, room types, board types, periods, prices, and reservations.

## How to Run
---

To run this project, follow these steps:

1. Make sure you have Java 8 or later installed on your system.

2. Install PostgreSQL database on your machine. You can use the provided SQL file `Tourism_Agency_db_Enes_Kaya.sql` to set up the database.

3. Import the `Tourism_Agency_db_Enes_Kaya.sql` file into your PostgreSQL database.

4. Clone this repository to your local machine.

5. Open the project in your preferred Java development environment (e.g., IntelliJ IDEA).

6. Build and run the project.

7. You can now use the application.

## System Requirements

- Java 8 or later
- PostgreSQL database

## Technologies Used
---

This project utilizes the following technologies:

- **Java Swing:** Utilized for the user interface design.
- **Object-Oriented Programming (OOP):** The project architecture is designed following object-oriented programming principles.
- **Java:** Backend development is carried out using the Java programming language.
- **PostgreSQL:** Used for database management and data storage.
- **pgAdmin 4:** Employed as the PostgreSQL database management tool.
- **SQL:** Utilized for executing database queries and operations.
- **IntelliJ IDEA:** Used as the integrated development environment (IDE) for Java.
- **GitHub:** Project files and codes are hosted on GitHub.
- **Git:** Version control and collaboration are facilitated using Git.

### User Credentials
----------------------

#### Admin User
Username: admin Password: admin

#### Employee User
Username: employee Password: 1

### Software Features:
---

#### Hotel Management

The agency manages the hotels it has agreements with, including their location information and other details. When adding a hotel, information such as Hotel Name, Address, Email, Phone, Star Rating, Facilities, and Board Types should be specified. You can filter hotels according to preference.

##### Board Types in the System:

Half Pension Full Pension

##### Hotel Management Screen

![Hotel Management](https://github.com/wvazabi/Tourism-Agency/blob/main/Screen%20Shoots/hotelmanagement.png)
![Hotel Management2](https://github.com/wvazabi/Tourism-Agency/blob/main/Screen%20Shoots/addhotelview.png)


#### Period Management

Different periods can be added for hotels throughout the year and managed accordingly.

##### Period Screen:

![Period Management](https://github.com/wvazabi/Tourism-Agency/blob/main/Screen%20Shoots/periodmanagement.png)
![Pension Management](https://github.com/wvazabi/Tourism-Agency/blob/main/Screen%20Shoots/pensionmanagement.png)



#### Room Management

The agency adds reserved rooms from hotels to the system.

##### Room Features in the System:
Minibar, cashbox, etc.

##### Room Screen:

![Room Management](https://github.com/wvazabi/Tourism-Agency/blob/main/Screen%20Shoots/roommanagement.png)




#### Room Search and Reservation Operations

Agency employees can make reservations for rooms based on entered date ranges, etc.

![Room Search](https://github.com/wvazabi/Tourism-Agency/blob/main/Screen%20Shoots/roomsearch.png)


#### Price Calculation

Prices are calculated based on guest information and the number of nights stayed.

#### Reservation Process

Agency users complete reservations by selecting the appropriate board type from the listed room types for the hotels.

To complete a reservation:
![Reservation Management](https://github.com/wvazabi/Tourism-Agency/blob/main/Screen%20Shoots/reservationmanagement.png)


Customer contact information and guest name, surname, and ID information are entered.

The sale is completed through the system. If the sale is completed, the stock of the respective room is reduced by 1.

#### Reservation List

Agency employees can list the reservations made in the system.

#### User Permissions

Admin: User Management

Tourism Agency Employee: Hotel Management, Room Management, Period Management, Room Search, Reservation Operations

#### Admin Screen:

![User Lsit](https://github.com/wvazabi/Tourism-Agency/blob/main/Screen%20Shoots/userlist.png)
![User Edit](https://github.com/wvazabi/Tourism-Agency/blob/main/Screen%20Shoots/useredit.png)
![Login](https://github.com/wvazabi/Tourism-Agency/blob/main/Screen%20Shoots/login.png)

## Tourism Agency Project Demo Video
---
[![Tourism Agency Project Demo Video](https://github.com/wvazabi/Tourism-Agency/blob/main/Screen%20Shoots/video%20thumbnail.png)](https://www.youtube.com/watch?v=Occmg6LwOoc)


#### Unfinished Tasks
----

- Hotel Search and Room Search functionalities couldn't be fully developed.

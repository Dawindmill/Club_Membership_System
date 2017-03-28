# Tech Club Administration System

This is a administration system with GUI, it has the functionality for non-sql
technical background people to perform data saving and data retrieving.

## Getting Started

These instructions will get you a copy of the project up and running on your
local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

This program is implemented with Java 1.8.

### Run the program

You can run this Java program by simply double click the club_sys.jar.

You can also use the following command on the terminal to open the program.
```
java -jar club_sys.jar
```

You can use the following accounts to login to the program

#### Staff Account
username: liubingfeng93@gmail.com

password: fake1

#### Member Account
username: rjj@abc.com

password: fake2

### MySQL Database Design (EER)

![alt text](./readme_images/club_sys_eer.png "EER")

Each member could join to any tech groups and there are different events which
associate to the tech group.

Each event like 'Training' is arranged based on the groups.

Each tech group will have a leader, a staff in charge and many members.

### File Structures

#### src

This folder contains the source code of this program.

#### club_sys.jar

This is the jar package of the application

#### readme_images

This folder contains all the images of this README file.

#### club_membership_create_script1.sql

This is the sql file used to build the database schema and it also contains some
populated data.


## GUI

#### Member Account GUI

![alt text](./readme_images/member_detail_window.png "member_detail")

This window shows the detail information of the member.

The 'Refresh Detail' button will refresh the data of your detail list.

You could also change the password by filling up the text fields and click
'Commit Change'.

![alt text](./readme_images/member_group_window.png "member_group")

This window shows the tech group detail of the member

#### Staff Account GUI

![alt text](./readme_images/staff_detail_window.png "staff_detail")

This window shows the staff's details.

You could change your password by filling up the text fields and press
'Change Password'.

![alt text](./readme_images/staff_member_window.png "staff_member")

This window is used to manage the members's details, you could click the table
cells and modify the values (note: the IDs could not be modified), then you can
click the 'Commit Change' to update the members' details.

The refresh button is used to refresh the data in the table.

You could also add new user by filling up the text fields and click 'Commit New
 Member'.

## TODOs

Since the time is very tight, I only implement this program with 2 days, so
there are so many functionalities that I would like to implement but have not.

Currently, there are only GUI window for staff account and member account, I
would like to add leader account GUI as well to manage his/her own members in
his/her group.

Staff GUI window also needs improvements, now it has not functionality to mange
groups like adding or deleting groups and creating events for groups.

The password is not saved securely in the database, should use hashes to
compare user's passwords.

The memory usage of this program is not sufficient enough, should only buffer
the needed data from the database.

## Authors

* **Bingfeng Liu** (liubingfeng93@gmail.com)

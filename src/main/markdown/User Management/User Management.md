# User Management

## In Store Management

## Authentication Types

### Strategies

#### Lookup

#### Authorization

### Local

#### Terminology
  * *User* - An individual with his/her own username and password that can log into Nu Commerce. 
  * *User Workgroup* - A grouping to differentiate different types of users 
  ie. Management, Retail. Each individual User is assigned to one User Workgroup.
  * *Permission* -  Permissions define functionality in the point of sale that determine if 
  a user can access said function. Permissions are assigned to user workgroups and if the
  user is part of a workgroup with that permission, then the user has access.
  
#### Functional

##### Managing Users
Nu Commerce users can be managed through the Manage Users screen. Users can get
to this screen by selecting the 'Manage' button from the Home screen followed by
the 'Users' button (Home -> Manage -> Users). Only users in a workgroup who have the 
permission of *'manage.menu'* are able to enter into the Manage screen and make changes to users:

![alt-text](assets/manage-menu-screen.png)

![alt-text](assets/user-list-no-selected-user.png)

###### Add User
Users can be added by selecting the 'Add User' button under the Manage Users screen and filling out the
required User information.

![alt-text](assets/user-list-no-selected-user.png)

![alt-text](assets/users-list-selected-user.png)

###### Set Security Questions User

###### Search for User / List Users
The list of all users is found on the Manage Users screen.

![alt-text](assets/manage-menu-screen.png)

![alt-text](assets/user-list-no-selected-user.png)
###### Display / Edit User Information
To make changes to a user's information, select the user in the Manage Users screen and then click 'Edit User'.

![alt-text](assets/users-edit-user.png)

###### Reset User Password
A user's password can be reset by selecting the user and clicking 'Edit User'.

![alt-text](assets/users-edit-user.png)

###### Lock / Disable User Account

###### Unlock User Account

###### Security - How to log in  / What do I Have Access To?

###### Signing into the POS
To Sign into Nu Commerce, the user will be prompted to enter their username and password. The password that is entered
is hashed and authenticated to check that the password is correct and not expired. If the
password is incorrect, it will block the user from signing in and prompt for login again. If the password
entered is correct but the current password expired, then it will prompt the user to enter and save a new password. Finally,
if the password is correct and not expired, it will allow the user to sign in successfully.

###### What do I have Access To?
Each user is assigned to a specific user workgroup which are defined under the usr_workgroup table.
For example, "Management" and "Retail" are two logical workgroups that can be used to differentiate managers
from sale associates. Each workgroup is then assigned permissions in the usr_workgroup_permission table which
determine the access that the users in that workgroup have to different functionality in Nu Commerce.
The list of possible permissions are found below under [Permissions](users.md#permissions).
  

### SSO

### LDAP

#### Mixed mode

### Biometrics (OOB U are U 4500)

## Password Criteria
Pasword criteria is the mandatory criteria needed to permit a password as acceptable. This criteria is -

The password should be at least 4 characters long, and must contain at least one lowercase, one uppercase, one number, and a special character such as "! @ # $ % ^ & ~".

## Permissions

## Manager Overrides

## Login / Logout

### Login
To log into a user account on Nu commerce, select any function on the main menu and follow the prompted screens to enter a valid user id and password. 
(Select any function -> enter valid user id -> next -> enter valid passowrd -> next)
![alt-text](assets/user-login-user-id.png)

![alt-text](assets/user-login-user-password.png)

### Logout
To logout of your Nu commerce account, select the icon in the upper right hand corner of your screen, and select 'logout'. 
![alt-text](assets/user-options-logoff.png)

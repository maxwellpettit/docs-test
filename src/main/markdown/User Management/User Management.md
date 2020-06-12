# User Management
User management is the functional module that is responsible for providing 
authentication and authorization to commerce applications as well as verification 
that individuals belong to the organization.   

## Concepts
User management consists of four major concepts.

- *Authentication* is the validation that the user has access to the system. This is usually accomplished by 
providing a username and a password, but can be accomplished in other ways.
- *Authorization* is the concept of providing access to different functionality in a commerce application. 
Permission are assigned to workgroups and users are added to their job specific workgroup, giving them certain permissions.
- *Verification* is used by commerce application to validate that an individual is active and belongs to the organization.
An example of verification is the employee discount functionality.
- *Administration* is the act of adding and managing users.  Most enterprise implementations
have this feature disabled because users are sourced from other systems.

## Terminology

__Maybe this should be a table?__

- *User* - An individual with his/her own username and password that can log into Commerce. 
- *User Workgroup* - A grouping of users with the same access. ie. Management, Retail. Each individual User is assigned to one User Workgroup.
- *Permission* -  Permissions define functionality in the point of sale that determine if 
  a user can access a said function. Permissions are assigned to user workgroups and if the
  user is part of a workgroup with that permission, then the user has access.
- *Authentication* - The process of confirming that the user is who the user says they are.
- *Authorization* - The permission given to a specific user wokrgroup
- *SSO* - SSO, or single-sign-on, is an authentication strategy that allows for the use of a single username and password for authentication.
- *LDAP* - OVER MY HEAD
- *Manager override* - The manager has the ability to permit an action that another user does not have the perission id for.

## Authentication

### Types of Authentication
Authentication is the confirmation that the user is who the user says they are. There are several ways to achieve this. 
The commerce solution supports a few secure ways. 
 - Password
 - Biometrics
 - Single Sign On (SSO)
#### Password Type
 Password is criteria based password, ensuring uniqueness.  
 
 There are two implementations of password types: Local and LDAP.  Each of these can be used independently or in conjunction via a strategy.
 
 LDAP, or Lightweight Directory Access Protocol, is ...
 - __should this be a heading like SSO__ 

##### Password Type Strategies
- User Strategy
  - Local
  - LDAP
- Password Strategy
  - Local
  - LDAP
  - Mixed
 
#### Fingerprint Biometrics Type 
- OOB, or out-of-band, authentication uses a second, and seperate communication channel to authenticate a user.
- [U.are.U 4500](https://www.neurotechnology.com/fingerprint-scanner-digitalpersona-u-are-u-4500.html) is the model of biometric scanner.

#### SSO Type
SSO, or single-sign-on, is an authentication strategy that allows for the use of a single username and password for authentication.
Is this external or internal?

### Login
?
  - When a functional subsystem requires a specific authorization the user of the system is prompted to login (link to different section)  
  - The login process uses the commerce systems configured Authentication mechanism (link to different section)
  
  __Do we want these pics. I thjink it damages the flow of the doc a little__
![alt-text](assets/user-login-user-id.png)

![alt-text](assets/user-login-user-password.png)

### Logout
The logout process allows for a signed in user to exit the commerce system.
![alt-text](assets/user-options-logoff.png)
    
  
### Lock Screen
paragraph-
When a user has been login in for a configurable time period without activity, the screen is locked, and that same user must relogin to continue work. If a manager breaks the lock, any existing transactions are marked as ORPHANED 

bullets- 
- When a user has been login in for a configurable time period without activity, the screen is locked
- Same user must log back in to continue their work
- If a manager breaks the lock, any existing transactions are marked as ORPHANED


## Authorization

Each user is authorized for a specific user workgroup which is defined under the usr_workgroup table.
For example, "Management" and "Retail" are two logical workgroups that can be used to differentiate a managers access
from sale associates access.
- __this is already said in concepts. not sure if it is repetitive as other concepts dont have wording here__

### Permissions
 Each workgroup is assigned permissions in the usr_workgroup_permission table which
 determine the access that the users in that workgroup have to different functionalities in Commerce.
 The list of possible permissions are found under [Permissions](users.md#permissions). *<- need this*

### Manager Override
  When a user is already logged into the system and they attempt to access a functional subsystem that requires a specific authorization
    that the logged in user does not already have they are prompted for a manager override if the function is overrideable.
  - __Is this always true or does manager override need to be enabled?__ 

TODO screen shots  

## Verification
- Commerce apps can lookup users for use in other parts of the system.
- Employee Discounts
__Is this for instnaces when a company has several stores and an employee wants to use a discount at a sore they do not work at.__ 

## Administration

### Managing Users
Commerce users can be managed by a user in a workgroup that has the permission of *'manage.users'* .
__( do we have a section for changing permissions, ex. if a user were to get promoted from retailer to manager)__
![alt-text](assets/user-list-no-selected-user.png)
- __The following six user management funcions require the *'manage.users'* permission id.__

####  *'manage.users'* Permission id required 
__Wanted to find a way to show the 6 following functions and mention they need the *'manage.users'* permission without typing that into each other to avoid being repetitve__

###### Add User
Addional users can be added to the system and their designated workgroups when desired( dont love the word desired here)

__(view manage user photo) reference imaging would be nice here__

![alt-text](assets/users-list-selected-user.png)

###### Search for User / List Users
A functionality where a list of all users and their respective workgroups can be accessed.

![alt-text](assets/user-list-no-selected-user.png)

###### Display / Edit User Information
Individual user information can be displayed and edited.
 - (Name, Username, Password) 

![alt-text](assets/users-edit-user.png)

###### Reset User Password
Any user can have their password reset. If current password has reached expiration or password was forgotten. 

![alt-text](assets/users-edit-user.png)

###### Lock / Disable User Account
A user can have their account locked or disabled removing their access to Commerce functionality. 

###### Unlock User Account
Locked/Disabled user accounts can be unlocked which re-grants them their respective workgroup permission id's. 


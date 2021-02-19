# PFEManager Project:

- Create a database named pfemanager.

- Select pfemanager db and import the file pfemanager.sql .

- You can change your Data source username and password in application.properties .

### REST API
Before test any API you must login with an email and password.

| HTTP Method | URL | Action | Input | Output |
| ------ | ------ | ------ | ------ | ------ |
| Post | http://localhost:3035/authenticate | Login | {"username" : email , "password" : password} | JWT Token |

you can use {"username" : "khalil@hamdi.com" , "password" : "123456789"} to get your token.

### REST API USER:

| HTTP Method | URL | Action | Input | Output |
| ------ | ------ | ------ | ------ | ------ |
| Post | http://localhost:3035/register | Register new user | user | user |
| Get | http://localhost:3035/users/all | get all users | --- | List of all users |
| Get | http://localhost:3035/users/{id} | get user | id | user By id |
| Get | http://localhost:3035/users/role/{id} | get users by role | RoleId |List of users having the same Role|
| Put | http://localhost:3035/users/{id} | update user | id |updated user |
| Delete | http://localhost:3035/users/{id} | delete user | id | deleted user |
| Delete | http://localhost:3035/users | delete all users | id | deleted users |

- Create or Update user: you can use this json

```
{
     "email" : "user email",
     "firstname" : "user firstname",
     "lastname" : "user lastname",
     "password" : "user password",
     "role" :{
         "id" : 1,
         "name" : "ADMIN"
     } 
}
```

### REST API Subject:

| HTTP Method | URL | Action | Input | Output |
| ------ | ------ | ------ | ------ | ------ |
| Post | http://localhost:3035/subjects | create new subject | subject | subject |
| Get | http://localhost:3035/subjects/all | get all subjects | --- | List of all subjects |
| Get | http://localhost:3035/subjects/{id} | get subject | id | subject By id |
| Post | http://localhost:3035/subjects/search/{ids} | get subject by technologies | list of id | list of subjects By technologie |
| Put | http://localhost:3035/subjects/{id} | update subject | id |updated subject |
| Delete | http://localhost:3035/subjects/{id} | delete subject | id | deleted subject |
| Delete | http://localhost:3035/subjects| delete all subjects | id | deleted subjects |

- Create or Update Subject: you can use this json

```
{
    "title": "new subject",
    "description" : "new subject description",
    "publicationDate" :"2021-01-11",
    "startDate":"2021-02-11",
    "endDate" : "2021-06-11",
    "technologies":
        [
            {
                "id": 1,
                "name" : "java"
            },
               {
                "id": 3,
                "name" : "angular"
            }
        ]  
}
```

### REST API Application:

| HTTP Method | URL | Action | Input | Output |
| ------ | ------ | ------ | ------ | ------ |
| Post | http://localhost:3035/applications | create new application | application | application |
| Get | http://localhost:3035/applications/all | get all applications | --- | List of all applications |
| Get | http://localhost:3035/applications/{id} | get application | id | application By id |
| Get | http://localhost:3035/applications/{id} | get application | userId | list of applications By user |
| Put | http://localhost:3035/applications/{id} | update application | id |updated application |
| Delete | http://localhost:3035/applications/{id} | delete application | id | deleted application |
| Delete | http://localhost:3035/applications | delete all applications | id | deleted applications |

- Create or Update Subject: you can use this json

```
{
    "coverLetter": "coverLetter",
    "resume": "resume",
    "userDTO": {
        "id": 3,
        "email": "amira@allani.com",
        "password": "123456789",
        "firstname": "amira",
        "lastname": "Allani",
        "role": {
            "id": 3,
            "name": "STUDENT"
        }
    },
    "subjectDTO": {
        "id": 1,
        "title": "titre du projet",
        "description": "pfe en java et angular",
        "publicationDate": "2021-02-11",
        "startDate": "2021-02-11",
        "endDate": "2021-02-11",
        "technologies": [
            {
                "id": 1,
                "name": "java"
            },
            {
                "id": 3,
                "name": "angular"
            }
        ]
    }
}
```

### REST API Technology:

| HTTP Method | URL | Action | Input | Output |
| ------ | ------ | ------ | ------ | ------ |
| Post | http://localhost:3035/technologies | create new technology | technology | technology |
| Get | http://localhost:3035/techlogoies/all | get all technologies | --- | List of all technologies |
| Get | http://localhost:3035/technologies/{id} | get technology | id | technology By id |
| Delete | http://localhost:3035/technologies/{id} | delete technology | id | deleted technology |
| Delete | http://localhost:3035/technologies | delete all technologies | id | deleted technologies |

- Create technology: you can use this json
```
{
    "id": 5,
    "name": "c++"
}
```


### REST API Role:

| HTTP Method | URL | Action | Input | Output |
| ------ | ------ | ------ | ------ | ------ |
| Post | http://localhost:3035/roles | create new role | role | role |
| Get | http://localhost:3035/roles | get all roles | --- | List of all roles |
| Get | http://localhost:3035/roles/{id} | get role | id | role By id |
| Put | http://localhost:3035/roles/{id} | update role | id |updated role |
| Delete | http://localhost:3035/roles/{id} | delete role | id | deleted role |

- Create Role: you can use this json


```
{
    "id": 4
    "name": "Role"
}
```







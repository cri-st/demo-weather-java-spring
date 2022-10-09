# demo-weather-java-spring
Demo Weather's project (Java Spring)

## Setup with docker
```sh
cd src/main/resources/
docker-compose up -d
```

## Default admin user
### User
```sh
admin
```

### Password
```sh
admin
```

## Weather City Endpoints

#### Get all
```sh
GET /weather/all
```

## Users ABM Endpoints

#### Get all
```sh
GET /user/all
```

#### Get one
```sh
GET /user/{$id}
```

#### Create new user
```sh
POST /user/new
```
##### With Body
```sh
{
    "nickname": "other",
    "role": "STANDARD",
    "email": "other@mail.com",
    "password": "prueba"
}
```

#### Update user
```sh
PUT /user/update
```
##### With Body
```sh
{
    "userId": 1,
    "nickname": "admin",
    "role": "ADMIN",
    "email": "mail@cri.st",
    "password": "admin"
}
```

#### Delete User
```sh
DELETE /user/{$id}
```

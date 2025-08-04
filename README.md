# feingclient
prueba técnica para ticxar
# FeignClient Application

Esta aplicación Spring Boot funciona como cliente Feign para consumir servicios REST. A continuación, se detallan los pasos para configurar, ejecutar y probar el endpoint `/api/auth/login`.

## 1. Configuración de propiedades

Antes de ejecutar la aplicación, es necesario tener el archivo `application.properties` configurado correctamente con los parámetros de la base de datos PostgreSQL:

La base de datos tiene que estar creada previamente de la ejecución de la aplicación.

```properties

spring.application.name=FeingClient
server.port=8083

spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.url=jdbc:postgresql://localhost:5432/[DB]
spring.datasource.username=[userName]
spring.datasource.password=[password]

spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
```
## 2. Ejecutar la aplicación

./mvnw spring-boot:run

O si estás usando un IDE como IntelliJ o Eclipse, ejecuta la clase principal del proyecto FeingClientApplication.

## 3. Probar endpoint de Login

http://localhost:8083/api/auth/login

Este endpoint permite autenticar a un usuario con sus credenciales (nombre de usuario y contraseña). Si las credenciales son válidas, se retornará un accessToken y un refreshToken para autorizar futuras solicitudes.
METODO:
POST
HEADERS:

Content-Type: application/json

REQUEST BODY:
```
{
  "username": "emilys",
  "password": "emilyspass"
}
``` 
EJEMPLO:
```
curl -X POST http://localhost:8083/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username": "emilys", "password": "emilyspass"}'
```
RESPUESTA ESPERADA
```
{"accessToken":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJlbWlseXMiLCJlbWFpbCI6ImVtaWx5LmpvaG5zb25AeC5kdW1teWpzb24uY29tIiwiZmlyc3ROYW1lIjoiRW1pbHkiLCJsYXN0TmFtZSI6IkpvaG5zb24iLCJnZW5kZXIiOiJmZW1hbGUiLCJpbWFnZSI6Imh0dHBzOi8vZHVtbXlqc29uLmNvbS9pY29uL2VtaWx5cy8xMjgiLCJpYXQiOjE3NTQzMzc3MjEsImV4cCI6MTc1NDM0MTMyMX0.ECHg_sAiHG64S34nP8zLvKK8ME6qc6uyD6gaVz-jE-Y","refreshToken":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJlbWlseXMiLCJlbWFpbCI6ImVtaWx5LmpvaG5zb25AeC5kdW1teWpzb24uY29tIiwiZmlyc3ROYW1lIjoiRW1pbHkiLCJsYXN0TmFtZSI6IkpvaG5zb24iLCJnZW5kZXIiOiJmZW1hbGUiLCJpbWFnZSI6Imh0dHBzOi8vZHVtbXlqc29uLmNvbS9pY29uL2VtaWx5cy8xMjgiLCJpYXQiOjE3NTQzMzc3MjEsImV4cCI6MTc1NjkyOTcyMX0.calEMgY_nRkpbNDkEpscGPFDzdKVpKMLi0hg8oJcWXs","id":1,"username":"emilys","email":"emily.johnson@x.dummyjson.com","firstName":"Emily","lastName":"Johnson","gender":"female","image":"https://dummyjson.com/icon/emilys/128"}

```
## 4. Probar endpoint de validación de Autenticacion

 http://localhost:8083/api/auth/me
 
Este endpoint permite obtener los datos del usuario actualmente autenticado. Es necesario realizar el login anteriormente.
 
Metodo:
GET

EJEMPLO:

```curl http://localhost:8083/api/auth/me```

RESPUESTA ESPERADA:
```{"id":1,"username":"emilys","email":"emily.johnson@x.dummyjson.com","firstName":"Emily","lastName":"Johnson","gender":"female","image":"https://dummyjson.com/icon/emilys/128"}```


## 5 Probar endpoint de usuarios:
http://localhost:8083/api/users

Obtiene la lista completa de usuarios disponibles en el sistema.

Metodo:
GET

EJEMPLO:
```curl http://localhost:8083/api/users```

RESPUESTA ESPERADA:
```{"users":[{"id":1,"username":"emilys","email":"emily.johnson@x.dummyjson.com","firstName":"Emily","lastName":"Johnson","gender":"female","image":"https://dummyjson.com/icon/emilys/128"},{"id":2,"username":"michaelw","email":"michael.williams@x.dummyjson.com","firstName":"Michael","lastName":"Williams","gender":"male","image":"https://dummyjson.com/icon/michaelw/128"},{"id":3,"username":"sophiab","email":"sophia.brown@x.dummyjson.com","firstName":"Sophia","lastName":"Brown","gender":"female","image":"https://dummyjson.com/icon/sophiab/128"},{"id":4,"username":"jamesd","email":"james.davis@x.dummyjson.com","firstName":"James","lastName":"Davis","gender":"male","image":"https://dummyjson.com/icon/jamesd/128"},etc}```

##6 Explicación de guardade de entidad login_log

cuando realizamos la peticion tipo POST al endpoint /api/auth/login, la aplicación conviert el request body al dto UserRequest,
la cual contiene los siguientes atributos.
```
 private String username;
 private String password;
 ```
 el controlador llama al metodo login del  servicio DummyJsonServices:
```
 UserResponse reponse = dummyJsonServices.login(request);
 
  @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody UserRequest request){
        UserResponse reponse = dummyJsonServices.login(request);
        return new ResponseEntity<>(reponse, HttpStatus.CREATED);
    }```

 el DTO UserResponse contiene los siguientes atributos:
 ```
    private String accessToken;
    private String refreshToken;
    private int id;
    private String    username;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private String image
```	
 el servicio dummyJsonServices.login  a su vez llama el metodo saveLog del servicio LoginLogServices, como parametro el dto response obtenido en la peticion anterior.
 ```
 loginLogServices.saveLog(response);```
 
 y este servicio se encarga de construir un objeto de la clase LoginLog y guardalos en la base de datos:
 ```
  public void saveLog(UserResponse response){
        LoginLog log = LoginLog.builder()
                .userName(response.getUsername())
                .accessToken(response.getAccessToken())
                .refreshToken(response.getRefreshToken())
                .build();

        loginLogRepository.save(log);
    }```

de esta manera se realiza la insercción en la base de datos

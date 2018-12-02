# An application to retrieve weather metrics of given city

This application retrieves weather information from [https://openweathermap.org/](https://openweathermap.org/) for given city, calculates daily and nightly averages of temperatures and average pressure for next three days from today as per below--
- Average of daily (06:00 – 18:00) and nightly (18:01 – 05:59) temperatures in Celsius for the next 3 days from today’s date.
- Average of pressure for the next 3 days from today’s date.

## How to run the application:
This is a spring-boot application with Maven. Following are the ways to run the application--
1. Open this application with Eclipse IDE (Preferably Spring Tool Suite) and run as Spring-Boot app.
2. Navigate to Project's root directory where pom.xml file is located. Then run `mvn spring-boot:run` command.

## Design and Implementation:
1. Application has Controller and Service layar. I've created a `RestController` with name `WeatherController.java` that exposes `/data` endpoint to get the required data. Data is returned in `JSON` format. Service layer consists of a class named `WeatherServiceImpl.java`. Spring's `RestTemplate` bean is injected in this service class to retrieve data from [https://openweathermap.org/](https://openweathermap.org/).
2. `WeatherConfiguration.java` is a configuration class. Beans are created in this class.
3. `application.properties` file is used to set default port of application to `8085` and application context is set to `/weather`.
Application is accessible via [http://localhost:8085/weather/data?city={cityName}](http://localhost:8085/weather/data?city={cityName}) link
4. eg: [http://localhost:8085/weather/data?city=London](http://localhost:8085/weather/data?city=London)
5. For centralized exception handling and to return appropriate status codes, `GlobalExceptionHandler.java` `ControllerAdvice` is created. This returns `ErrorInfo` Object whenever exception is raised.

### Swagger UI
[Swagger-UI](http://localhost:8085/weather/swagger-ui.html)

### Swagger API Documentation
[Swagger-API-Documentation](http://localhost:8085/weather/v2/api-docs)
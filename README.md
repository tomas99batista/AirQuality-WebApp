# AirQuality-webapp

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/82f0c76c611544da85fc736228288600)](https://www.codacy.com/manual/tomas99batista/AirQuality-WebApp?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=tomas99batista/AirQuality-WebApp&amp;utm_campaign=Badge_Grade)

## First step
**Configure postgres db on docker:** ```docker run --name postgres  -d -p 5432:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=password -d postgres```

## API used
[API](https://api-docs.airvisual.com/?version=latest)

API Token: 8cbf49d4-de9f-40af-a754-778b9f92f94e

[Example](https://api.waqi.info/feed/beijing/?token=41b33a02bd2d16e5f587310917b819e826cdbb58)

## CI/CD 
[Tutorial used](https://medium.com/faun/continuous-integration-of-java-project-with-github-actions-7a8a0e8246ef)

## Pages
www.localhost:8080/ -> Infos about Lisbon

www.localhost:8080/madrid -> Infos about Madrid

## API Doc
[API Documentation](https://documenter.getpostman.com/view/9124304/SzYgSbE4?version=latest)

# city_pass_microservice

Backend - Micro service

## Start 🚀

_These instructions will allow you to obtain a copy of the running project on your local machine_

### Pre-requirements📋

1. Install MYSQL
2. Install MAVEN

### Install 🔧

- [In progress]

-Execute:

- [In progress]

# Endpoints

## City Pass

| Endpoint              | HTTP | Description                                                                                                                                                                                          |
| --------------------- | ---- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `/city_pass/`        | GET | Get a list of all city passes                                          |
| `/city_pass/{idCityPass}`           | GET | Receive by parameters an ID and return the corresponding city pass |
| `/city_pass/`          | POST | Receive by body an object `{"name": String, "description": String, "days": Integer, "price": Double}` and create a new city_pass                                                                                                                                              |
| `/city_pass/{idCityPass}`      | PUT | Receive by body an object `{"name": String, "description": String, "days": Integer, "price": Double}` and the city_pass ID that you want to modify and update it                                                                                                         |
| `/city_pass/{idCityPass}` | DELETE  | It receives a parameter ID and performs a logical deletion of this city_pass.                                                                                                                   |

## Test ⚙️

## Build with 🛠️

- [MAVEN](https://maven.apache.org/) - Software project management and comprehension tool.
- [SPRING BOOT](https://spring.io/projects/spring-boot) - Framework

## Versioned 📌

Use [GITLAB](https://gitlab.com) for versioned.

## Authors ✒️

- **Mariela Cagnoli** - _Develop backend, PM, TL, PO, Asadora Profesional_ - (https://github.com/MaruCagnoli)
- **Nicolás Melluso** - _Develop backend_ - (https://github.com/NicolasMitre) or (https://gitlab.com/NicolasMitre)
- **Federico Anastasi** - _Develop backend_ - (https://github.com/rakanishu37)
- **Mauro Castillo Rondinara** - _Develop backend_ - (https://github.com/maurocastillorondinara)

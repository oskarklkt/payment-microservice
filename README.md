# Payment Microservice

---
## Quick start
1. Clone this repository
2. Create .env file with the following content:
```properties
DB_PORT={Your localhost port when you want to run db}
DB_USER={Postgres db username}
DB_PASSWORD={Postgres db password}
```
3. Run the following commands (Assuming you are using mac and have homebrew installed):
```shell
brew install colima
brew install docker
brew install docker-compose
docker-compose -f "docker-compose-postgres.yml" up 
```
4. Check if it worked fine using for example DBeaver - if test connection with given in .env file works, then it is fine.

---

## Models
### CreditCard
```json
{
  "id": "Long",
  "userId": "Long",
  "paymentType": "String",
  "cardHolderName": "String",
  "cardNumber": "String",
  "expirationDate": "string",
  "cvv": "String"
}
```
---
## DTOs
### CreditCardDto
```json
{
  "paymentType": "String",
  "cardHolderName": "String",
  "cardNumber": "String",
  "expirationDate": "string",
  "cvv": "String"
}
```
---
## Endpoints
![Payment-Endpoints](/uploads/ee0f2eb811fb23e88cc6294620bed41c/Payment-Endpoints.jpeg)

---

## Class Diagram

![Payment-Class-Diagram](/uploads/6fafe16acf80536750bc76a9dbee73f4/Payment-Class-Diagram.png)
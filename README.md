# Projet Spring Boot - Gestion de Comptes Bancaires

## 1. Créer un projet Spring Boot
![projet-init.png](img/projet-init.png)

## 2. Créer les entités JPA

- `Customer`
- `BankAccount`
- `SavingAccount`
- `CurrentAccount`
- `AccountOperation`

![entities.png](img/entities.png)

## 3. Créer les interfaces JPA Repository basées sur Spring Data

![repositories.png](img/repositories.png)

## 4. Tester la couche DAO

### Résultat des tests

- Test DAO général :

  ![testDAO.png](img/testDAO.png)

- Création d’un client (`Customer`) :

  ![customer.png](img/customer.png)

- Création d’un compte bancaire (`BankAccount`) :

  ![bankAccount.png](img/bankAccount.png)

- Création des operations d’un compte bancaire (`AccountOperation`) :

  ![accountOperation.png](img/accountOperations.png)
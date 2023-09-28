# JavaBank

A simple banking system that allows you to conduct and track transactions for transferring funds between bank accounts.

Client's enpoints:
- Getting information about bank accounts by customer number (GET request)
- Getting information about a bank account by account number (GET request)
- Conducting a bank transaction – the initial and final account, as well as the amount of money (POST request) are transmitted
- Getting information about transactions carried out by the client's number (GET request)

Manager's Endpoints:
- Getting information about transactions carried out by the client's number (GET request)
- Getting information about bank accounts by customer number (GET request)
- Getting information about the bank account by the account number and the client (GET request)
- Creation of a bank account – the number of the client to which the account will be linked is transmitted (POST request)

Admin Endpoints:
- Creating a user in the system – basic data is transmitted (first name, last name, email, phone number, password) (POST request)
- Creating a manager in the system – the email of an already created user is transmitted (POST request)
- Creating a client in the system – the email data of an already created user and passport data are forwarded (POST request)
- Getting information about all clients (GET request)
- Getting information about all transactions (GET request)
- Getting information about all bank accounts (GET request)
- Blocking/unblocking a user by user ID (POST request)
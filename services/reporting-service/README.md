# Reporting Service
This document includes the API contracts for interacting with the reporting service.
The reporting service is used by the client to self-service their account, and by regulators
to run reports and configure the trading system.

## Portfolios
### Retrieve all client portfolios
**Request**
```
GET /portfolios?client_id=<client_id> HTTP/1.1
Accept: application/json
Authorization: <whatever_authorization_mechanism>
...
```

**Success Response**
```
HTTP/1.1 200 OK
Content-Type: application/json
...
[
    {
        "portfolio_id": 1,
        "portfolio_name": "Default Portfolio",
        "client_id": <client_id>,
        "profit": 58.76
    }
    ...
]
```

**Error Response**
```
HTTP/1.1 401 Unauthorized
Content-Type: application/json
...
{
    "error_code": "user_not_permitted"
    "error_message": "user is not allowed to view portfolios"
}
```
```
HTTP/1.1 404 Not Found
Content-Type: application/json
...
{
    "error_code": "porfolios_not_found"
    "error_message": "no portfolios found for client(s)"
}
```

### Create new portfolio
**Request**
```
POST /portfolios HTTP/1.1
Accept: application/json
Authorization: <whatever_authorization_mechanism>
Content-Type: application/json
...
{
    "portfolio_name": "New Portfolio",
    "client_id": 1
}
```

**Success response**
```
HTTP/1.1 201 Created
Content-Type: application/json
...
{
    "portfolio_id": 2
}
```

**Error response**
```
HTTP/1.1 400 Bad Request
Content-Type: application/json
...
{
    "error_code": "invalid_request:
    "error_message": "portfolio_name and client_id are required"
}
```
```
HTTP/1.1 401 Unauthorized
Content-Type: application/json
...
{
    "error_code": "user_not_permitted:
    "error_message": "user is not permitted to create portfolio."
}
```

### Retrieve a portfolio
**Request**
```
GET /portfolios/<portfolio_id> HTTP/1.1
Accept: application/json
Authorization: <authorization_mechanism>
...
```

**Success Response**
```
HTTP/1.1 200 OK
Content-Type: application/json
...
{
    "portfolio_id": <portfolio_id>,
    "portfolio_name": "Default Portfolio",
    "client_id": <client_id>,
    "profit": 58.76
}
```

**Error response**
```
HTTP/1.1 401 Unauthorized
Content-Type: application/json
...
{
    "error_code": "user_not_permitted",
    "error_message": "user is not allowed to retrieve this portfolio"
}
```
```
HTTP/1.1 404 Not Found
Content-Type: application/json
...
{
    "error_code": "portfolio_not_found",
    "error_message": "portfolio either does not exist or has been deleted"
}
```

### Close portfolio
**Request**
```
DELETE /portfolios/<portfolio_id> HTTP/1.1
Authorization: <authorization_mechanism>
...
```

**Success response**
```
HTTP/1.1 204 No Content
...
```

**Error response**
```
HTTP/1.1 401 Unauthorized
Content-Type: application/json
...
{
    "error_code": "user_not_permitted",
    "error_message": "user is not allowed to delete this portfolio"
}
```
```
HTTP/1.1 404 Not Found
Content-Type: application/json
...
{
    "error_code": "portfolio_not_found",
    "error_message": "portfolio either does not exist or has been deleted"
}
```

## Orders
### List orders
**Request**
```
GET /orders?client_id=<client_id> HTTP/1.1
Accept: application/json
Authorization: <authorization_mechanism>
...
```

**Success response**
```
HTTP/1.1 200 OK
Content-Type: application/json
...
[
    {
        "order_id": 1,
        "client_id": <client_id>
        "product_name": "AMZN"
        "price": 1.86,
        "quantity": 50,
        "side": "BUY",
        "order_status": "Accepted",
        "created_at": "2022-08-11"
    }
    ...
]
```

**Error responses**
```
HTTP/1.1 401 Unauthorized
Content-Type: application/json
...
{
    "error_code": "user_not_permitted",
    "error_message": "user is not allowed to view these orders"
}
```
```
HTTP/1.1 404 Not Found
Content-Type: application/json
...
{
    "error_code": "orders_not_found",
    "error_message": "orders either do not exist or have been deleted"
}
```

### Retrieve an order
**Request**
```
GET /orders/<order_id> HTTP/1.1
Accept: application/json
Authorization: <authorization_mechanism>
...
```

**Success Response**
```
HTTP/1.1 200 OK
Content-Type: application/json
...
{
    "order_id": 1,
    "client_id": 3
    "product_name": "AMZN"
    "price": 1.86,
    "quantity": 50,
    "side": "BUY",
    "order_status": "Accepted",
    "created_at": "2022-08-11"
}
```

**Error responses**
```
HTTP/1.1 401 Unauthorized
Content-Type: application/json
...
{
    "error_code": "user_not_permitted",
    "error_message": "user is not allowed to retrieve this order"
}
```
```
HTTP/1.1 404 Not Found
Content-Type: application/json
...
{
    "error_code": "order_not_found",
    "error_message": "order either does not exist or has been deleted"
}
```

### Close an order
**Request**
```
DELETE /orders/<order_id> HTTP/1.1
Authorization: <authorization_mechanism>
...
```

**Success response**
```
HTTP/1.1 204 No Content
...
```

**Error response**
```
HTTP/1.1 401 Unauthorized
Content-Type: application/json
...
{
    "error_code": "user_not_permitted",
    "error_message": "user is not allowed to cancel this order"
}
```
```
HTTP/1.1 404 Not Found
Content-Type: application/json
...
{
    "error_code": "order_not_found",
    "error_message": "order either does not exist or has been deleted"
}
```

## Trades
### List trades
**Request**
```
GET /trades?client_id=<client_id>&order_id=<order_id> HTTP/1.1
Accept: application/json
Authorization: <authorization_mechanism>
...
```

**Success response**
```
HTTP/1.1 200 OK
Content-Type: application/json
...
[
    {
        "trade_id": 1
        "order_id": 1,
        "exchange_id": 1
        "product_name": "AMZN"
        "price": 1.86,
        "quantity": 50,
        "side": "BUY",
        "trade_status": "OPEN",
        "created_at": "2022-08-11"
    }
    ...
]
```

**Error responses**
```
HTTP/1.1 401 Unauthorized
Content-Type: application/json
...
{
    "error_code": "user_not_permitted",
    "error_message": "user is not allowed to view these trades"
}
```
```
HTTP/1.1 404 Not Found
Content-Type: application/json
...
{
    "error_code": "trades_not_found",
    "error_message": "trades either do not exist or have been deleted"
}
```

### Retrieve a trade
**Request**
```
GET /trades/<trade_id> HTTP/1.1
Accept: application/json
Authorization: <authorization_mechanism>
...
```

**Success Response**
```
HTTP/1.1 200 OK
Content-Type: application/json
...
{
    "trade_id": 1
    "order_id": 1,
    "exchange_id": 1
    "product_name": "AMZN"
    "price": 1.86,
    "quantity": 50,
    "side": "BUY",
    "trade_status": "OPEN",
    "created_at": "2022-08-11"
}
```

**Error responses**
```
HTTP/1.1 401 Unauthorized
Content-Type: application/json
...
{
    "error_code": "user_not_permitted",
    "error_message": "user is not allowed to retrieve this trade"
}
```
```
HTTP/1.1 404 Not Found
Content-Type: application/json
...
{
    "error_code": "trade_not_found",
    "error_message": "trade either does not exist or has been deleted"
}
```

### Close a trade
**Request**
```
DELETE /trades/<trade_id> HTTP/1.1
Authorization: <authorization_mechanism>
...
```

**Success response**
```
HTTP/1.1 204 No Content
...
```

**Error response**
```
HTTP/1.1 401 Unauthorized
Content-Type: application/json
...
{
    "error_code": "user_not_permitted",
    "error_message": "user is not allowed to cancel this trade"
}
```
```
HTTP/1.1 404 Not Found
Content-Type: application/json
...
{
    "error_code": "trade_not_found",
    "error_message": "trade either does not exist or has been deleted"
}
```
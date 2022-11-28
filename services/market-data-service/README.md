# Market Data Service
This document includes the API contract for interacting with the market data service.

## Retrieve available products
**Request**
```
GET /products HTTP/1.1
Accept: application/json
...

```

**Success Response**
```
HTTP/1.1 200 OK
Content-Type: application/json
...
[
    {
        "product": "AAPL",
        "ask_price": 1.30,
        "bid_price": 1.32,
        "max_price_shift": 0.13
    },
    ...
]
```

**Error Response**
```
HTTP/1.1 404 Not Found
Content-Type: application/json
...
{
    "error_code": "no_available_products",
    "error_message": "there are currently no products available to trade"
}
```

## Retrieve a product
**Request**
```
GET /products/<product_name> HTTP/1.1
Accept: application/json
...

```

**Success Response**
```
HTTP/1.1 200 OK
Content-Type: application/json
...
{
    "product_name": "AAPL",
    "sell_limit": 5000,
    "last_traded_price": 1.30,
    "max_price_shift": 0.13,
    "ask_price": 1.30,
    "bid_price": 1.32,
    "buy_limit": 10000
}
```

**Error Response**
```
HTTP/1.1 404 Not Found
Content-Type: application/json
...
{
    "error_code": "product_unavailable",
    "error_message": "product AETD is not available"
}
```
For live updates, the client can connect using websockets. In that case the data sent is identical to that of the success response.

## Add a new exchange
**Request**
```
POST /exchanges HTTP/1.1
Accept: application/json
Content-Type: application/json
...
{
    "exchange_name": "Exchange 1"
    "exchange_url": "https://exchange.matraining.com"
}
```

**Success Response**
```
HTTP/1.1 201 Created
Content-Type: application/json
...
{
    "exchange_id": 1
}
```

**Error Responses**
```
HTTP/1.1 401 Unauthorized
Content-Type: application/json
...
{
    "error_code": "user_not_permitted",
    "error_message": "user is not permitted to create an exchange"
}
```
```
HTTP/1.1 400 Bad Request
Content-Type: application/json
...
{
    "error_code": "invalid_data",
    "error_message": "exchange_name and exchange_url are required"
}
```

## Retrieve all exchanges
**Request**
```
GET /exchanges HTTP/1.1
Accept: application/json
...

```

**Success Response**
```
HTTP/1.1 200 OK
Content-Type: application/json
...
[
    {
        "id": 1,
        "exchange_name": "Exchange 1",
        "exchange_url": "https://exchange.matraining.com"
    },
    ...
]
```

**Error Response**
```
HTTP/1.1 404 Not Found
Content-Type: application/json
...
{
    "error_code": "exchanges_unavailable",
    "error_message": "no exchanges are available to trade on"
}
```

## Retrieve an exchange
**Request**
```
GET /exchanges/<exchange_id> HTTP/1.1
Accept: application/json
...

```

**Success Response**
```
HTTP/1.1 200 OK
Content-Type: application/json
...
{
    "id": 1,
    "exchange_name": "Exchange 1",
    "exchange_url": "https://exchange.matraining.com"
}
```

**Error Response**
```
HTTP/1.1 404 Not Found
Content-Type: application/json
...
{
    "error_code": "exchange_unavailable",
    "error_message": "requested exchange does not exist or is not available"
}
```

## Remove an exchange
**Request**
```
DELETE /exchanges/<exchange_id> HTTP/1.1
...
```

**Success Response**
```
HTTP/1.1 204 No Content
...
```
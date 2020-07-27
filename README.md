## Commerce Interview Challenge

This module provides a REST API for creating and ordering items, such as fruit.

### Running the module

The module can be built into an executable jar with Maven using the following command: 
- mvn clean install

To run the module on Windows, use the following command in the same directory as the jar: 
- java -jar commerce-0.0.1-SNAPSHOT.jar

### Documentation / Examples 

The module contains Swagger documentation for the API. When the module is running, the Swagger UI can be accessed using:
- http://localhost:41000/Commerce/swagger-ui.html#

Using Swagger, an Item can be saved using a POST operation to the /v1/item endpoint.  The response includes the ID created for the Item.
- http://localhost:41000/Commerce/swagger-ui.html#/item-resource/saveItemUsingPOST

Example payload 1:

```
{
  "name": "Apple",
  "price": 5.00
}
```

Example payload 2:

```
{
  "discount": 0.5,
  "name": "Orange",
  "price": 10.00
}
```

Using Swagger, an Order can be submitted using a POST operation to the /v1/order endpoint.  The response includes the total cost for the Order.
- http://localhost:41000/Commerce/swagger-ui.html#/order-resource/submitUsingPOST

```
{
  "orderItems": [
    {
      "itemId": 1,
      "quantity": 1
    },
    {
      "itemId": 2,
      "quantity": 5
    }
  ]
}
```
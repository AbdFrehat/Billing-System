# sales-update-ms

#### Description

---

The sales-update-ms microservice is used to update a single or multiple sales documents.

#### Endpoints

---

##### It exposes only one endpoint at the following URL:

`POST  /selling/query/update/sale/[version]/`

##### The `QueryCommand` request body of the end point has the following structure:

|    Name     |           Type            |                 Description                 |           Constraint           |
| :---------: | :-----------------------: | :-----------------------------------------: | :----------------------------: |
| queryFields | `Map<String, QueryField>` |                  Not Used                   |               X                |
|    page     |           `int`           |                  Not Used                   |               X                |
|    size     |           `int`           |                  Not Used                   |               X                |
| commandType |       `QueryMethod`       |   they type of operation against the data   |  UPDATE_SALE \| UPDATE_SALES   |
|  sortField  |        `SortField`        |                  Not Used                   |               X                |
|   exclude   |        `String[]`         |                  Not Used                   |               X                |
|    size     |           `int`           | the size of paginated page to retrieve from | `0 < size < Integer.MAX_VALUE` |
|   payload   |         `object`          | contains the sales documents to be updated  |            Not Null            |
| expression  |         `String`          |                  Not Used                   |               X                |
|    count    |         `boolean`         |                  NOT Used                   |               X                |

##### The `payload` filed must contain the following structure:

if commandType is UPDATE_SALE then

|  Name   |  Type  |           Description           | Constraint |
| :-----: | :----: | :-----------------------------: | :--------: |
| payload | `Sale` | the sale document to be updated |  Not Null  |

if commandType is UPDATE_SALES then

|  Name   |     Type     |               Description                | Constraint |
| :-----: | :----------: | :--------------------------------------: | :--------: |
| payload | `List<Sale>` | the list of sale documents to be updated |  Not Null  |

##### The `QueryResponse` response body of the end point has the following structure:

| Name |     Type     |         Description         | Constraint |
| :--: | :----------: | :-------------------------: | :--------: |
| data | `List<Sale>` | the list of retrieved sales |     X      |

##### The `Sale` object has the following structure:

| Field          | Type       | Description             |
| -------------- | ---------- | ----------------------- |
| id             | String     | Unique identifier       |
| saleDate       | Date       | Date of the sale        |
| items          | List<Item> | List of purchased items |
| storeLocation  | String     | Location of the store   |
| customer       | Customer   | Customer information    |
| couponUsed     | boolean    | Indicates coupon usage  |
| purchaseMethod | String     | Method of purchase      |

##### The `Customer` object has the following structure:

| Field        | Type   | Description                   |
| ------------ | ------ | ----------------------------- |
| gender       | String | Customer's gender             |
| age          | int    | Customer's age                |
| email        | String | Customer's email              |
| satisfaction | int    | Customer's satisfaction level |

##### The `Item` object has the following structure:

| Field    | Type         | Description                           |
| -------- | ------------ | ------------------------------------- |
| name     | String       | Item name                             |
| tags     | List<String> | List of tags associated with the item |
| price    | BigDecimal   | Price of the item                     |
| quantity | int          | Quantity of the item                  |

#### Examples:

- To save one sale document:

```Json
{
    "queryFields": null,
    "page": 0,
    "size": 0,
    "commandType": "UPDATE_SALE",
    "payload": {
        "id": "6555b9108bcd0c2f3635eaa6",
        "saleDate": "2017-11-12T20:30:15.045+00:00",
        "items": [
            {
                "name": "notepad",
                "tags": [
                    "office",
                    "writing",
                    "school"
                ],
                "price": 9.91,
                "quantity": 3
            },
            {
                "name": "pens",
                "tags": [
                    "writing",
                    "office",
                    "school",
                    "stationary"
                ],
                "price": 22.01,
                "quantity": 4
            },
            {
                "name": "backpack",
                "tags": [
                    "school",
                    "travel",
                    "kids"
                ],
                "price": 57.14,
                "quantity": 2
            },
            {
                "name": "binder",
                "tags": [
                    "school",
                    "general",
                    "organization"
                ],
                "price": 21.92,
                "quantity": 7
            },
            {
                "name": "binder",
                "tags": [
                    "school",
                    "general",
                    "organization"
                ],
                "price": 20.12,
                "quantity": 9
            },
            {
                "name": "laptop",
                "tags": [
                    "electronics",
                    "school",
                    "office"
                ],
                "price": 1541.76,
                "quantity": 3
            },
            {
                "name": "printer paper",
                "tags": [
                    "office",
                    "stationary"
                ],
                "price": 45.86,
                "quantity": 6
            }
        ],
        "storeLocation": "London",
        "customer": {
            "gender": "F",
            "age": 50,
            "email": "velo@nukav.fr",
            "satisfaction": 5
        },
        "couponUsed": false,
        "purchaseMethod": "In store"
    }
}
```

- To save list of sales documents:

```JSON
{
    "queryFields": null,
    "page": 0,
    "size": 0,
    "commandType": "UPDATE_SALES",
    "payload": [
        {
        "id": "6555b9108bcd0c2f3635eaa6",
        "saleDate": "2017-11-12T20:30:15.045+00:00",
        "items": [
            {
                "name": "notepad",
                "tags": [
                    "office",
                    "writing",
                    "school"
                ],
                "price": 9.91,
                "quantity": 3
            },
            {
                "name": "pens",
                "tags": [
                    "writing",
                    "office",
                    "school",
                    "stationary"
                ],
                "price": 22.01,
                "quantity": 4
            },
            {
                "name": "backpack",
                "tags": [
                    "school",
                    "travel",
                    "kids"
                ],
                "price": 57.14,
                "quantity": 2
            },
            {
                "name": "binder",
                "tags": [
                    "school",
                    "general",
                    "organization"
                ],
                "price": 21.92,
                "quantity": 7
            },
            {
                "name": "binder",
                "tags": [
                    "school",
                    "general",
                    "organization"
                ],
                "price": 20.12,
                "quantity": 9
            },
            {
                "name": "laptop",
                "tags": [
                    "electronics",
                    "school",
                    "office"
                ],
                "price": 1541.76,
                "quantity": 3
            },
            {
                "name": "printer paper",
                "tags": [
                    "office",
                    "stationary"
                ],
                "price": 45.86,
                "quantity": 6
            }
        ],
        "storeLocation": "London",
        "customer": {
            "gender": "F",
            "age": 50,
            "email": "velo@nukav.fr",
            "satisfaction": 5
        },
        "couponUsed": false,
        "purchaseMethod": "In store"
    },
    {
        "id": "6555b9108bcd0c2f3635eaa7",
        "saleDate": "2017-11-12T20:30:15.045+00:00",
        "items": [
            {
                "name": "notepad",
                "tags": [
                    "office",
                    "writing",
                    "school"
                ],
                "price": 9.91,
                "quantity": 3
            },
            {
                "name": "pens",
                "tags": [
                    "writing",
                    "office",
                    "school",
                    "stationary"
                ],
                "price": 22.01,
                "quantity": 4
            },
            {
                "name": "backpack",
                "tags": [
                    "school",
                    "travel",
                    "kids"
                ],
                "price": 57.14,
                "quantity": 2
            },
            {
                "name": "binder",
                "tags": [
                    "school",
                    "general",
                    "organization"
                ],
                "price": 21.92,
                "quantity": 7
            },
            {
                "name": "binder",
                "tags": [
                    "school",
                    "general",
                    "organization"
                ],
                "price": 20.12,
                "quantity": 9
            },
            {
                "name": "laptop",
                "tags": [
                    "electronics",
                    "school",
                    "office"
                ],
                "price": 1541.76,
                "quantity": 3
            },
            {
                "name": "printer paper",
                "tags": [
                    "office",
                    "stationary"
                ],
                "price": 45.86,
                "quantity": 6
            }
        ],
        "storeLocation": "London",
        "customer": {
            "gender": "F",
            "age": 50,
            "email": "velo@nukav.fr",
            "satisfaction": 5
        },
        "couponUsed": false,
        "purchaseMethod": "In store"
    }
    ]
}
```

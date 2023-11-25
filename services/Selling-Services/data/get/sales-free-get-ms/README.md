# sales-free-get-ms

#### Description

---

The sales-free-get-ms microservice is used to perform a free text search on the string fields of the sales documents.

#### Endpoints

---

##### It exposes only one endpoint at the following URL:

`POST  /selling/query/get/free/sale/[version]/`

##### The `QueryCommand` request body of the end point has the following structure:

|    Name     |        Type        |                                   Description                                    |           Constraint           |
| :---------: | :----------------: | :------------------------------------------------------------------------------: | :----------------------------: |
| queryFields | `List<QueryField>` |                list of the desired fields to search based on them                |      has only one element      |
|    page     |       `int`        |                       the paginated page to retrieve from                        | `0 < page < Integer.MAX_VALUE` |
|    size     |       `int`        |                   the size of paginated page to retrieve from                    | `0 < size < Integer.MAX_VALUE` |
| queryMethod |   `QueryMethod`    |                     they type of operation against the data                      |         GET_FREE_SALES         |
|  sortField  |    `SortField`     |     it describes the field to sort based on and the ordering of the sorting      |               X                |
|   exclude   |     `String[]`     |          list of the excluded fields, that will not part of the result           |               X                |
|   payload   |      `object`      |                                     Not Used                                     |               X                |
| expression  |      `String`      |                                     Not Used                                     |               X                |
|    count    |     `boolean`      | if it is true, the count of retrieved document will be returned without the data |               X                |

##### `QueryField` has the following structure while using free text search:

|   Name    |    Type     |                                      Description                                       | Constraint |
| :-------: | :---------: | :------------------------------------------------------------------------------------: | :--------: |
|   field   |  `String`   |                           The field name to search based on                            |    FREE    |
|   value   |  `Object`   | The field value to search based on, the structure of the object based on the fieldType |  Not Null  |
| fieldType | `FieldType` |              The field type that the criterial will be build based on it               |    FREE    |

##### `SortField` has the following structure:

|   Name    |   Type   |              Description               | Constraint  |
| :-------: | :------: | :------------------------------------: | :---------: |
| direction | `String` |        the direction of Sorting        | ASC \| DESC |
|   field   | `String` | the selected field to sort based on it |  Not Blank  |

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

- To retrieve sales documents by using free text search, this will returns all sales documents that has notepad value in it:

```Json
{
    "queryFields": {
        "FREE": {
            "field": "FREE",
            "value": "notepad",
            "fieldType": "FREE"
        }
    },
    "page": 0,
    "size": 10,
    "queryMethod": "GET_FREE_SALES",
    "sort": {
        "direction": "DESC",
        "field": "saleDate"
    },
    "exclude": [],
    "payload": null,
    "expression": null,
    "count": false
}
```

- To retrieve the count of sales documents by using free text search, this will returns all sales documents that has notepad value in it:

```Json
{
    "queryFields": {
        "FREE": {
            "field": "FREE",
            "value": "notepad",
            "fieldType": "FREE"
        }
    },
    "page": 0,
    "size": 10,
    "queryMethod": "GET_FREE_SALES",
    "sort": {
        "direction": "DESC",
        "field": "saleDate"
    },
    "exclude": [],
    "payload": null,
    "expression": null,
    "count": true
}
```

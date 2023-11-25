# sales-get-ms

#### Description

---

The sales-get-ms microservice is used to receive a Query command object, build the Query request and send it to the MongoDB server to retrieve the data.

#### Endpoints

---

##### It exposes only one endpoint at the following URL:

`POST  /selling/query/get/sale/[version]/`

##### The `QueryCommand` request body of the end point has the following structure:

|    Name     |           Type            |                                   Description                                    |           Constraint           |
| :---------: | :-----------------------: | :------------------------------------------------------------------------------: | :----------------------------: |
| queryFields | `Map<String, QueryField>` |                list of the desired fields to search based on them                |               X                |
|    page     |           `int`           |                       the paginated page to retrieve from                        | `0 < page < Integer.MAX_VALUE` |
|    size     |           `int`           |                   the size of paginated page to retrieve from                    | `0 < size < Integer.MAX_VALUE` |
| queryMethod |       `QueryMethod`       |                     they type of operation against the data                      |           GET_SALES            |
|  sortField  |        `SortField`        |     it describes the field to sort based on and the ordering of the sorting      |               X                |
|   exclude   |        `String[]`         |          list of the excluded fields, that will not part of the result           |               X                |
|   payload   |         `object`          |                                     Not Used                                     |               X                |
| expression  |         `String`          |                                     Not Used                                     |               X                |
|    count    |         `boolean`         | if it is true, the count of retrieved document will be returned without the data |               X                |

##### `QueryField` has the following structure:

|   Name    |    Type     |                                      Description                                       |                       Constraint                       |
| :-------: | :---------: | :------------------------------------------------------------------------------------: | :----------------------------------------------------: |
|   field   |  `String`   |                           The field name to search based on                            |                       Not Blank                        |
|   value   |  `Object`   | The field value to search based on, the structure of the object based on the fieldType |                        Not Null                        |
| fieldType | `FieldType` |              The field type that the criterial will be build based on it               | RANGE \| STRING \| OTHER \| LIST \| DATE \| RANGE_DATE |

##### When the `fieldType` is RANGE \| RANGE_DATE

| Name | Type |              Description               | Constraint |
| :--: | :--: | :------------------------------------: | :--------: |
| min  | Any  | Defines the minimum value of the range |     X      |
| max  | Any  | Defines the maximum value of the range |     X      |

##### When the `fieldType` is STRING \| OTHER \| DATE

| Name |  Type  |          Description           | Constraint |
| :--: | :----: | :----------------------------: | :--------: |
|  X   | String | Defines the value to search on |  Not Null  |

##### When the `fieldType` is LIST

| Name |    Type    |                                          Description                                           | Constraint |
| :--: | :--------: | :--------------------------------------------------------------------------------------------: | :--------: |
|  X   | QueryField | Defines the queryField object of the field to search on in the list of objects in the document |  Not Null  |

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

- To retrieve sales documents between range of dates:

```Json
{
    "queryFields": [
        {
            "field": "saleDate",
            "value": {
                "min": "2000-11-12T20:30:15.045+00:00",
                "max": "2023-11-14T20:30:15.045+00:00"
            },
            "fieldType": "RANGE_DATE"
        }
    ],
    "page": 0,
    "size": 10,
    "queryMethod": "GET_SALES",
    "sort": {
        "direction": "ASC",
        "field": "customer.satisfaction"
    },
    "exclude": ["items", "customer"],
    "payload": null,
    "expression": null,
    "count": false
}
```

- To retrieve sales documents based on specific string value:

```Json
{
    "queryFields":
        "F1": {
            "field": "storeLocation",
            "value": "Denver",
            "fieldType": "STRING"
        }
    ,
    "page": 0,
    "size": 10,
    "queryMethod": "GET_SALES",
    "sort": {
        "direction": "ASC",
        "field": "customer.satisfaction"
    },
    "exclude": [],
    "payload": null,
    "expression": null,
    "count": false
}
```

- To retrieve sales documents based on a field inside list of objects:

```JSON
{
    "queryFields":
        "F1": {
            "field": "items",
            "value": {
                "field": "name",
                "value": "envelopes",
                "fieldType": "STRING"
            },
            "fieldType": "LIST"
        }
    ,
    "page": 0,
    "size": 10,
    "queryMethod": "GET_SALES",
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

- To retrieve sales docuemnts based on different criteria:

```Json
{
    "queryFields":
        "F1": {
            "field": "items",
            "value": {
                "field": "name",
                "value": "envelopes",
                "fieldType": "STRING"
            },
            "fieldType": "LIST"
        },
        "F2": {
            "field": "storeLocation",
            "value": "Denver",
            "fieldType": "STRING"
        }
    ,
    "page": 0,
    "size": 10,
    "queryMethod": "GET_SALES",
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

- To retrieve the number of sales documents based on a field inside list of objects:

```JSON
{
    "queryFields":
        "F1": {
            "field": "items",
            "value": {
                "field": "name",
                "value": "envelopes",
                "fieldType": "STRING"
            },
            "fieldType": "LIST"
        }
    ,
    "page": 0,
    "size": 10,
    "queryMethod": "GET_SALES",
    "sort": null
    "exclude": [],
    "payload": null,
    "expression": null,
    "count": true
}
```

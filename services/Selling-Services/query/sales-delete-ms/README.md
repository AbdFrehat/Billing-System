# sales-delete-ms

#### Description

---

The sales-delete-ms microservice is used to delete a single or multiple sales documents.

#### Endpoints

---

##### It exposes only one endpoint at the following URL:

`POST  /selling/query/delete/sale/[version]/`

##### The `QueryCommand` request body of the end point has the following structure:

1. if queryMethod is DELETE_SALE

   |    Name     |           Type            |               Description                | Constraint  |
   | :---------: | :-----------------------: | :--------------------------------------: | :---------: |
   | queryFields | `Map<String, QueryField>` |                 Not Used                 |      X      |
   |    page     |           `int`           |                 Not Used                 |      X      |
   |    size     |           `int`           |                 Not Used                 |      X      |
   | queryMethod |       `QueryMethod`       | they type of operation against the data  | DELETE_SALE |
   |  sortField  |        `SortField`        |                 Not Used                 |      X      |
   |   exclude   |        `String[]`         |                 Not Used                 |      X      |
   |   payload   |         `object`          | contains the sale document to be deleted |  Not Null   |
   | expression  |         `String`          |                 Not Used                 |      X      |
   |    count    |         `boolean`         |                 NOT Used                 |      X      |

   ##### The `payload` filed must contain the following structure:

   |  Name   |  Type  |           Description           | Constraint |
   | :-----: | :----: | :-----------------------------: | :--------: |
   | payload | `Sale` | the sale document to be deleted |  Not Null  |

1. if queryMethod is DELETE_SALES

   |    Name     |           Type            |                                     Description                                     |           Constraint           |
   | :---------: | :-----------------------: | :---------------------------------------------------------------------------------: | :----------------------------: |
   | queryFields | `Map<String, QueryField>` |                 list of the desired fields to search based on them                  |               X                |
   |    page     |           `int`           |                         the paginated page to retrieve from                         | `0 < page < Integer.MAX_VALUE` |
   |    size     |           `int`           |                     the size of paginated page to retrieve from                     | `0 < size < Integer.MAX_VALUE` |
   | queryMethod |       `QueryMethod`       |                       they type of operation against the data                       |          DELETE_SALES          |
   |  sortField  |        `SortField`        |       it describes the field to sort based on and the ordering of the sorting       |               X                |
   |   exclude   |        `String[]`         |            list of the excluded fields, that will not part of the result            |               X                |
   |   payload   |         `object`          |                                      Not Used                                       |               X                |
   | expression  |         `String`          |                                      Not Used                                       |               X                |
   |    count    |         `boolean`         | if it is true, the count of deleted document will be returned without deleting them |               X                |

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

##### The `QueryResponse` response body of the end point has the following structure:

| Name |  Type  |           Description           |
| :--: | :----: | :-----------------------------: |
| data | `Long` | the number of deleted documents |

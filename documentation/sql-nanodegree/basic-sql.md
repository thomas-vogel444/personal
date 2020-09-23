# Basic SQL

Optional clauses to the SQL SELECT Command
```sql
SELECT * FROM some_table ORDER BY date (DESC) LIMIT 15;
```

- `WHERE`
    - non-numeric values: 
        - use single quotes
        - Added operators: 
            - `LIKE`
                e.g. `SELECT * FROM account WHERE name LIKE '%one%'`
            - `IN`
                e.g. `SELECT * FROM account WHERE name IN ('Apple', 'Intel')`
            - `NOT`
                e.g. e.g. `SELECT * FROM account WHERE name NOT IN ('Apple', 'Intel')`
    - Combinators: `AND`, `BETWEEN`, `OR`
- `ORDER BY`
- `LIMIT`

Derived columns
e.g. `SELECT account_id, occurred_at, gloss_qty + poster_qty AS non_standard_qty FROM orders;`

## Joins

Types of JOINs:
- INNER JOIN
    - rows that appear in both tables
    e.g. 
```sql
    SELECT o.*, a.* 
    FROM orders o
    JOIN accounts a 
    ON o.account_id = a.id;
```
- OUTER JOIN
    - data in either of our 2 tables
    - Types:
        - LEFT JOIN 
        - RIGHT JOIN
        - FULL OUTER JOIN
    - Filtering: `AND` to the `ON` clause to prefilter the tables before the `JOIN`

Types of Keys:
- Primary Key
    - Unique entries in the table
- Foreign Key
    - Unique entries in some other table

Alias
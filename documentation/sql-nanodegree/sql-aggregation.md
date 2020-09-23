# Aggregagtion

## Null values

e.g. 
```sql
SELECT * FROM accounts WHERE primary_poc IS NULL;
```
- NULL is not a value, but a property of the data.
- Aggregates ignore NULL values. 
e.g. `SELECT COUNT(*) FROM accounts` not the same as `SELECT COUNT(account_poc) FROM accounts`

- SUM
- MIN & MAX
- AVG -> ignores NULLS
- GROUP BY
    - creates segment that will be aggregated independently from one another
    - expected when including columns that are not aggregated like ids
- DISTINCT
- HAVING
    - Used to filter based on aggregate data. Alternative to WHERE for aggregates.
- DATE functions
    - Datetime format: yyyy-mm-dd hh:mm:ss
    - functions:
        - DATE_TRUNC
            - e.g. `DATE_TRUNC('day', date_col)`
        - DATE_PART
            - e.g. `DATE_PART('day', date_col)`
- CASE followed by WHEN and THEN finish with END
    - SQL's equivalent of IF-THEN
    - Derived column
    - use ELSE for Nulls
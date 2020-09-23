# Subqueries and temporary tables

Subquery: a query within a query

Useful to create an intermediate aggregate table to query off of.

Subqueries VS Join
- Join: combining tables using a common key
- Subquery: a query within a query
    -> build an intermediary table to query off of/
What happens under the hood is similar.

Placement:
- With
    - Create a temporary table at the top of the query that you then join
- Nested
    - Embedded within the WHERE clause
- Inline
    - Component of the FROM clause taking the place of a table name
    - Similar to the WITH subquery
    - Not advantageous for readability
- Scalar
    - Selects one column and returns one row
    - Advantageous for performance or when the database is small

Dependencies
- Simple -> independent from the outer query
- Correlated -> be dependent. Rare!

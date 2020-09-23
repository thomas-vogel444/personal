# Window functions

## Applications

When?
- When you want to measure trends or changes over rows
    - Running metric over time
- When you want to rank a column to use for outreach/prioritization
    - Ranking rows based on a set of metrics

What?
- Calculation across a set of rows with a table that are related to the current row
- Retains the total # of rows

Sits within a SELECT CLAUSE
Syntax specific: OVER + PARTITION BY

Methods:
- Core
    - PARTITION BY
        e.g.
        ```sql
        SELECT standard_amt_usd,
            DATE_TRUNC('year', occurred_at) as year,
            SUM(standard_amt_usd) 
                OVER (PARTITION BY DATE_TRUNC('year', occurred_at) ORDER BY occurred_at) AS running_total
        FROM orders
        ```
    - OVER
        e.g. 
        ```sql
        SELECT standard_amt_usd,
                SUM(standard_amt_usd) OVER (ORDER BY occurred_at) AS running_total
        FROM orders
        ```
    - Aggregates
- Ranking 
    - Row_number()
        - Ranking is distinct amongst records even with ties in what the table is ranked against.
    - Rank()
        - Ranking is the same amongst tied values and ranks skip for subsequent values.
    - Dense_rank()
        - Ranking is the same amongst tied values and ranks do not skip for subsequent values.
- Advanced
    - Aliases   
        - for multiple window functions leveraging the same PARTITION BY 
    - Percentiles -> NTILE(# of buckets) could be 100, 5, 4
        - Ranking when the dataset is too large
        - e.g. take the top 5% of customers
        ```sql
        SELECT customer_id, composite_score
                NTILE(100) OVER(ORDER BY composite)score) AS percentile
        FROM    customer_lead_score
        ```
    - Lag/Lead
        - Comparing rows with preceding and succeding rows
        - Useful for calculating differences between 2 rows
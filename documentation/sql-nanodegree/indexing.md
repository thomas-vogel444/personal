# Indexing

An index is a separate, sorted data structure, thus quickly searchable. Entries in 
the index point to related table locations, often making it faster to retrieve data 
than scanning the full table.

A table with indexes needs to update the indexes on new data inserts/updates

Create an index on a column
```sql
CREATE INDEX ON "table_name" ("column_name")
```

Create an index on an expression of a column
```sql
CREATE INDEX "index_name" ON "table_name" (FUNCTION("column_name"))
```

Create a multi-column index 
```sql
CREATE INDEX ON "table_name" ("column_name1", "column_name2")
```
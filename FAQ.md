### Combined Sample ###

**SQL Query**

```
SELECT a, b
FROM DATA
WHERE l = 'x'
AND   m like '%y%'
AND   n is not null
AND   t in ('z', 'k')
ORDER BY a ASC, b DESC
```

**Dbist Query**

```
dml.selectList(Data.class, 
        new Query()
            .addField("a", "b")
            .addOrder("a", true).addOrder("b", false)
            .addFilter("l", "x")
            .addFilter("m", "like" "%y%")
            .addFilter("n", "!=" null)
            .addFilter("t", new String[] {"z", "k"}));
```

### Composite Key ###

```
dml.select(Data.class, "x", "y");
```

### Partial Fields Selection (Query) ###

```
dml.selectList(Data.class, new Query().addField("a", "b"));
```

### Partial Fields Update ###

```
dml.update(data, "a", "b");
```

### Order By ###

```
dml.selectList(Data.class, new Query().addOrder("a", true));
```

### Pagination ###

```
dml.selectList(Data.class, new Query(0, 10));
```

### Some Complex Queries ###

### Object Relations ###
# Object-Oriented Programming (Advanced) Assignments

Welcome to my assignment repository. You can find the source code and solutions organized by tasks below.

## 📁 Quick Navigation

* **[PGO 9 - Stream API Tasks (Current)](src/PGO10/stream)**
    * [Model Classes](src/PGO10/stream/model)
    * [Stream Tasks Implementation](src/PGO10/stream/service/StreamApiTasks.java)
* **[PGO 8 - Functional Interfaces & Anonymous Classes](src/)** *(Root source files)*

---

## 📝 PGO 9 Control Questions Answers

### 1. Why does average() return OptionalDouble, while sum() returns a plain double?
If the stream is empty, the mathematical sum of zero elements is simply `0.0`. However, the average of an empty stream is mathematically undefined (division by zero). Therefore, `average()` returns an `OptionalDouble` to safely handle the absence of a value without crashing.

### 2. What is the difference between map and flatMap in task 4?
`map` is a 1-to-1 transformation that converts each `Order` object into another single object. `flatMap` is a 1-to-many transformation; it flattens multiple internal streams (like a `List<OrderItem>` inside each order) into a single, continuous stream of individual items.

### 3. Why do we need to create a new stream from entrySet() after collect(groupingBy(...)) in task 9?
`collect(groupingBy(...))` returns an unsorted map. Since standard maps cannot be directly sorted by value, we extract the map's key-value pairs using `entrySet()`, stream them to sort them in descending order, and collect them into a `LinkedHashMap` to preserve the sorted order.

### 4. What happens when the given code runs, and why?
It throws an `IllegalStateException`. In Java, a Stream instance can only be consumed **exactly once**. Once a terminal operation (like `count()`) is executed, the stream closes and cannot be reused for another operation (like `sum()`).

### 5. Why does the given pipeline print nothing?
Streams use **lazy evaluation**. Intermediate operations like `filter` and `map` do not process data unless a terminal operation (such as `toList()` or `forEach()`) is placed at the end of the pipeline to trigger execution.

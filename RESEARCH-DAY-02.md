# Research Day 02

## Q1. What is a Java generic type? Why is <T> useful?
A generic type allows a class to work with any data type without rewriting the code. <T> is useful because the same Response class can wrap a CategoryDto, a List, or any other object.

## Q2. What does Lombok @Builder generate behind the scenes?
@Builder generates a builder class with methods for each field, allowing you to create objects step by step instead of using a long constructor.

## Q3. What is the Builder design pattern? When to use it?
The Builder pattern lets you construct complex objects step by step. Use it when an object has many fields and you don't always need all of them.

## Q4. What is LocalDateTime? How is it different from Date?
LocalDateTime stores date and time without a timezone. Date is older and harder to work with. LocalDateTime is part of the newer Java time API and is easier to format and manipulate.

## Q5. Why does a consistent response format matter to frontend developers?
Frontend developers can write one piece of code to handle all responses. They always know where to find the data, the status code, and the message without checking each endpoint separately.

## Q6. What does @JsonInclude(JsonInclude.Include.NON_NULL) do?
It tells Jackson not to include fields that are null in the JSON output. So if data is null, it won't appear in the response at all.

## Q7. What is a static factory method? Why use Response.success(...) instead of new Response<>()?
A static factory method is a static method that creates and returns an object. Response.success() is cleaner and more readable than new Response<>() because it sets all required fields automatically.

---

## Self-Quiz

### Q1. Why use generic <T> instead of Object for data field?
With Object you lose type safety and need to cast. With <T> the compiler knows the exact type.

### Q2. Difference between Response<T> and ResponseEntity<T>?
Response<T> is our custom wrapper with statusCode, message, data and timestamp. ResponseEntity<T> is Spring's wrapper that controls HTTP headers and status codes. You can combine both: ResponseEntity<Response<T>>.

### Q3. If a request fails, what statusCode does Response hold?
The error code like 404 for not found or 400 for bad request.

### Q4. Why add a timestamp?
So developers and logs can see exactly when the response was generated, useful for debugging and tracking issues.
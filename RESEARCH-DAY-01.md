# Research Day 01

## Q1. What does CRUD stand for?
CRUD stands for Create, Read, Update and Delete. These are the four basic operations you can perform on data in a database.

## Q2. Difference between HTTP methods POST, PUT, PATCH, DELETE?
- POST: Creates a new resource
- PUT: Replaces/updates an existing resource completely
- PATCH: Updates only part of a resource
- DELETE: Removes a resource

## Q3. HTTP Status Codes
- a. New category created: 201 Created
- b. Category deleted: 204 No Content
- c. ID does not exist: 404 Not Found
- d. Missing required field: 400 Bad Request
- e. Logged in but not allowed: 403 Forbidden

## Q4. @RequestBody, @RequestParam, @PathVariable
- @PathVariable: gets value from the URL path e.g. /categories/{id}
- @RequestParam: gets value from query string e.g. /categories?name=pizza
- @RequestBody: gets value from the JSON body of the request

## Q5. Jakarta Bean Validation
Jakarta Bean Validation lets you add rules to fields using annotations.
- @Valid: tells Spring to validate the object
- @NotBlank: field cannot be empty or blank
- @Size: controls minimum and maximum length

## Q6. Why return DTO instead of Entity?
1. It hides internal database details from the API consumer
2. You can control exactly what data gets exposed without changing the database structure

## Q7. What is Optional<T>?
Optional is a wrapper that may or may not contain a value. findById returns Optional because the record might not exist, so instead of returning null it forces you to handle the missing case safely.

---

## Self-Quiz

### Q1. Why ResponseEntity instead of returning the object?
ResponseEntity lets you control the HTTP status code returned, not just the body.

### Q2. What status should DELETE return?
204 No Content because the action was successful but there is nothing to send back.

### Q3. PUT or PATCH for one field?
PATCH is more correct because PUT should replace the whole resource. PATCH updates only the fields provided.

### Q4. What happens if you forget @Valid?
The validation annotations are ignored and invalid data gets saved to the database.

### Q5. Why does update/delete need {id} but create does not?
Because update and delete need to know which record to target. Create generates a new record so the ID does not exist yet.
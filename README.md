# requirekt
Idiomatic kotlin preconditions for spring boot Restful API's

##### Express API preconditions idiomatically

With `requireKt` you can substitute existing `if` `else` blocks and that handle controller
validations with a `require` function that automatically throws an API error if a 
condition is not satisfied.

```kotlin
@RestController
@RequestMapping(value = ["/api/v1/users"])
class UserController() {

    companion object ErrorCodes {
        const val INVALID_USERNAME = "invalid username"
        const val PASSWORDS_DO_NOT_MATCH = "passwords do not match"
    }

    @PostMapping(
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun add(@Valid @RequestBody body: NewUserInputModel): ResponseEntity<Any> {
        require(UserNameValidator().isValid(body.username), HttpStatus.CONFLICT) { INVALID_USERNAME }
        require(body.password == body.confirmPassword, HttpStatus.CONFLICT) { PASSWORDS_DO_NOT_MATCH }
        ...
    }
}
```

#### Examples

Require that a message is parsed correctly, otherwise throw an API error. 

Note: *The default status code returned by this function is`BAD_REQUEST`.*

```kotlin
require(MessageParser.parse(message) == true) {
   "Message is invalid"
}
```

```json
{
   "errors":[
      {
         "status":400,
         "code":"",
         "message":"Message is invalid"
      }
   ]
}
```

#### Customized error response
You can custom the error response by passing in additional arguments.
Namely `status` which requires a parameter of type `HttpStatus` and `code` which 
is a `String` that represents the API error code.

##### Example:

Require that a user is authenticated, otherwise throw an API Error.
```kotlin
require(authenticated == true, status = HttpStatus.FORBIDDEN, code = "104") {
   "Access forbidden. You are not allowed to administrate categories."
}
```

This will return the following JSON error:
```json
{
   "errors":[
      {
         "status":403,
         "code":"104",
         "message":"Access forbidden. You are not allowed to administrate categories."
      }
   ]
}
```

License
----------------

The library is available as open source under the terms of the [MIT License](http://opensource.org/licenses/MIT).
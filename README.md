# requirekt
Idiomatic kotlin preconditions for spring boot Restful API's

[![](https://jitpack.io/v/nextbss/requirekt.svg)](https://jitpack.io/#nextbss/requirekt)

[![](https://img.shields.io/badge/nextbss-opensource-blue.svg)](https://www.nextbss.co.ao)

##### Express API preconditions idiomatically

With `requireKt` you can substitute existing `if` `else` blocks that handle controller
validations with a `require` function that automatically throws an API error if a 
condition is not satisfied.

This library was designed primarily for doing parameter validation in methods and constructors.

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

#### Pass additional arguments
You can change the response by passing in additional arguments.
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

#### Custom Error Responses
requirekt supports custom error responses in JSON using the 
`require(value: Boolean, vararg args: ArrayList<Any>)` function.

To use this function add a custom error with the `@ErrorResponse` annotation
and override the `toJSON` function from `AbstractErrorResponse`

Example:

```kotlin
@ErrorResponse
class CustomErrorViewModel(val status: Int = 0,
                           val code: String? = null,
                           val message: String? = null,
                           val type: String? = null): AbstractErrorResponse() {

    override fun toJSON(vararg args: ArrayList<Any>): String {
        status = fromArgsAsInt(0, args)
        code = fromArgsAsString(1, args)
        message = fromArgsAsString(2, args)
        type = fromArgsAsString(3, args)
        return super.toJSON()
    }
}
```

NOTE: `fromArgsAsInt`, `fromArgsAsFloat` and `fromArgsAsString` are convenience functions
 that help your custom error class get the required arguments passed to the `JSON()` function.
 

Call the `require` function and pass a boolean to validate and, your arguments wrapped in 
`arrayListOf` in the same order as the constructor of your custom error response class. 
If they are not in the same order and of the same type, require will fail to create the custom
error response in JSON.

```kotlin
require(value = false,
    arrayListOf(
         HttpStatus.FORBIDDEN.value(),
         "104",
         "Access forbidden. You are not allowed to administrate categories.",
         "authentication"
    )
)
```

Also make sure `requireKt` can scan for custom error classes by adding
`@SpringBootApplication(scanBasePackages = ["YOUR_PACKAGE", "ao.co.nextbss.requirekt"])`
 to your spring boot application entry point:
 
```kotlin
    @Configuration
    @SpringBootApplication(scanBasePackages = ["YOUR_APPLICATION_PACKAGE", "ao.co.nextbss.requirekt"])
    class YourApplication
    fun main(args: Array<String>) {
        runApplication<YourApplication>(*args)
    }
```

The output would than be the following:

```json
{
  "errors" : [ {
    "status" : 403,
    "code" : "104",
    "message" : "Access forbidden. You are not allowed to administrate categories.",
    "type" : "authentication"
  } ]
}
```

Usage
---------------

### Download
To get a Git project into your build:

Step 1. Add the JitPack repository to your build file

maven
```xml
<repositories>
	<repository>
		<id>jitpack.io</id>
		<url>https://jitpack.io</url>
	</repository>
</repositories>
```

gradle
```xml
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

Step 2. Add the dependency

maven 
```xml
<dependency>
	<groupId>com.github.nextbss</groupId>
	<artifactId>requirekt</artifactId>
	<version>1.1.0</version>
</dependency>
```

gradle
```xml
dependencies {
    implementation 'com.github.nextbss:requirekt:1.1.0'
}
```

License
----------------

The library is available as open source under the terms of the [MIT License](http://opensource.org/licenses/MIT).
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
	<version>1.0.0</version>
</dependency>
```

gradle
```xml
dependencies {
    implementation 'com.github.nextbss:requirekt:1.0.0'
}
```

License
----------------

The library is available as open source under the terms of the [MIT License](http://opensource.org/licenses/MIT).
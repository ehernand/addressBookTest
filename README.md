# AddressBookTest
Is a small back end Java project as a code challenge :)
I spent just few ours on it, there are stuff to improve :).
This contain REST api for manage Contact and Events CRUD operation.
Also include and api to import contacts from a csv file (implemented).
Ideally this can also import from other type of files, however this will need to be implemented in a near future.

### Tech Stack
- Java 8
- SpringBoot 2.3.0
- Maven wrapper

## Local set up
* `git clone git@github.com:ehernand/addressBookTest.git`
* `cd addressBookTest`
* `./mvnw package`
 
## Running unit tests
Run `./mvwn test` to execute the unit tests.

## Controllers
- ContactController
- EventController

## Test APIs
I've exported Postman request file I've used to perform some basic testing
File location:
`addressBookTest/src/main/resources/AddressBookTest.postman_collection.json`

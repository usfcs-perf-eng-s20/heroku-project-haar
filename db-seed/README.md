# db-seed

## purpose
This is a specific script written to insert rows in our heroku database for the Analytics Service for our CS686 Performance Engineering class project.

Our method is to send POST requests to our /saveEDR route

## requirements
- installation of node.js and npm
- config.js in the main folder with routes information

## usage
in the db-seed directory
````
npm install
npm start <number of requests to send>
````
for example:
````
npm start 500
````

## potential future work
- add a wait() so in the case of sending many POST requests, the server isn't overloaded
- add a configuration parameter to turn timestamp randomness on and off
- add a library for random number generation that uses a seed (for replicatable results)

## config.js
- holds the configuration information for our requests

- *hostname*: hostname to send to (no trailing /)
- *path*: path to send POST request to (needs to start with /)
- *services*: An array of all services and the methods associated with each service, where each service is an object.  The script will randomly go through and POST a path + method associated with a service
````
{
  "name" : "login",
  "requests" : [
    {
      "path": "isUserLoggedIn",
      "method": "GET"
    },
    {
      "path": "isLoggedIn",
      "method": "GET"
    },
    {
      "path": "login",
      "method": "POST"
    },
    {
      "path": "getUserInfo",
      "method": "GET"
    },
    {
      "path": "signup",
      "method": "POST"
    }
  ]
}
````
- *processingTime*: requires a min and max in order to randomly calculate a number for the request body
- *username*: randomly grabs a username from the list to associate with the Edr.  Having an array of one defaults all requests to the same username
- *success*: a boolean indicating whether or not the Edr event you are adding was successful.  Having an array of one defaults all requests to the same status
- *responseCode*: the status code for the Edr event you are adding. Having an array of one defaults all requests to the same status
- *timestamp*: an array of timestamps in unix timestamp format to assign to the requests.  If the array is empty, it will default to the current time.  (Note: if you use an array of timestamps, there is a small amount of randomness added by default)
## notes
### how to check the database via command line
#### requirements
- needs postgresql installed
````
brew install postgresql
````
- needs heroku cli installed:  https://devcenter.heroku.com/articles/heroku-cli
- after cloning the heroku directory for the project
#### access heroku postgres db
````
heroku pg:psql
````
#### useful commands
check all tables
````
\d
````
check specific table
````
\d tablename
````
you can run normal sql commands from this terminal
````
SELECT COUNT(*) FROM edr;
SELECT * FROM edr;
````

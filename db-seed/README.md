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


## notes
### how to check the database via command line
#### requirements
- needs postgresql installed
- needs heroku cli installed
- after cloning the heroku directory
#### access heroku postgres db
````
heroku pg:psql
````
#### useful commands
check all tables
````
\d
````
- you can run normal sql commands from this terminal
````
SELECT COUNT(*) FROM edr;
SELECT * FROM edr;
````
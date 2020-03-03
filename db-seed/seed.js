const config = require('./config');
const https = require('https');

let request_count = parseInt(process.argv[2]); 		//total requests you'd like to send
let responses = [];
let completed_requests = 0;

function getRandom(lower, upper) {
	console.assert(arguments.length === 2, "getRandomInteger() requires lower and upper parameters.");
	return Math.floor(Math.random() * (upper - lower)) + lower;
}

function getRandomData() {
	let service_idx = getRandom(0, config.services.length)
	let request_idx = getRandom(0, config.services[service_idx].requests.length)
	let processingTime = getRandom(config.processingTime.min, config.processingTime.max)
	let responseCode = config.responseCode[getRandom(0, config.responseCode.length)]
	let success = config.success[getRandom(0, config.success.length)]
	let username = config.username[getRandom(0, config.username.length)]
	let timestamp = Math.round(new Date().getTime() / 1000);

	if(config.timestamp.length > 0) {
		timestamp = config.timestamp[getRandom(0, config.timestamp.length)] + getRandom(0, 30000)
		timestamp = timestamp * 1000
	}

	let data = {
		"method": config.services[service_idx].requests[request_idx].method,
		"path": config.services[service_idx].requests[request_idx].path,
		"processingTimeInMiliseconds": processingTime,
		"responseCode": responseCode,
		"serviceName": config.services[service_idx].name,
		"success": success,
		"timestamp": timestamp.toString(),
		"username": username
	}

	return data
}

for (let i = 0; i < request_count; i++) {

	let data = JSON.stringify(getRandomData());
	let options = {
	  hostname: config.hostname,
	  port: 443,
	  path: config.path,
	  method: 'POST',
	  headers: {
	    'Content-Type': 'application/json',
	    'Content-Length': data.length
	  }
	}
	console.log(data)
	const req = https.request(options, (res) => {
		responses.push(res);
		completed_requests++;
		if (completed_requests == request_count) {
			console.log("sent all requests");
		}
	})
	req.write(data)
	req.end()
}
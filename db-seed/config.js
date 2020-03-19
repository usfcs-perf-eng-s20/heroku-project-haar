var config = {
	"hostname": "analytics-boot.herokuapp.com",
	"path": "/saveEdr",
	"services": [
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
		},
		{
			"name" : "search",
			"requests" : [
				{
					"path": "search",
					"method": "GET"
				},
				{
					"path": "getMovieById",
					"method": "GET"
				},
				{
					"path": "getMovieByIds",
					"method": "GET"
				},
				{
					"path": "getMovie",
					"method": "GET"
				},
				{
					"path": "search/movie",
					"method": "GET"
				}
			]
		},
		{
			"name" : "favorites",
			"requests" : [
				{
					"path": "checkoutMovie",
					"method": "POST"
				},
				{
					"path": "checkInMovie",
					"method": "POST"
				},
				{
					"path": "favoriteMovie",
					"method": "POST"
				},
				{
					"path": "rateMovie",
					"method": "POST"
				},
				{
					"path": "totalFavesAndCheckouts",
					"method": "GET"
				},
				{
					"path": "checkedOutMovies",
					"method": "GET"
				},
				{
					"path": "getTopFavs",
					"method": "GET"
				},
				{
					"path": "getTopRated",
					"method": "GET"
				},
				{
					"path": "getTopUsers",
					"method": "GET"
				}
			]
		}
	],
	"processingTime" : {
		"min" : 20,
		"max": 350
	},
	"username": [
		""
	],
	"success" : [
		true
	],
	"responseCode" : [
		"200 OK"
	],
	"timestamp": [
		1581724800, 1581739200, 1581768900,
		1581816300, 1581869520, 1581884640,
		1581909840, 1581927300, 1581975300,
		1582058100, 1582022100, 1582010580,
		1582096980, 1582128180, 1582153200,
		1582164000, 1582203600, 1582232400,
		1582272000, 1582326000, 1582318800,
		1582340400, 1582372800, 1582401600,
		1582441200, 1582470000, 1582480800,
		1582509600, 1582563600, 1582585200,
		1582599600, 1582614000, 1582632000,
		1582711200, 1582725600, 1582750800,
		1582786800, 1582801200, 1582833600,
		1582891200, 1582902000, 1582912800,
		1582970400, 1582992000, 1583013600
	]
}

module.exports = config;
var config = {
	"hostname": "prod-analytics-boot.herokuapp.com",
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
	]
}

module.exports = config;
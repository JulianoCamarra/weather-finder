# weather-finder
This is a simple weather service that fetches weather information for city submitted. It works by using 3 apis, openweathermap.org, restcountries.eu,
and timezonedb.com.

# Functionality

The application first checks to see if user submission includes a country/country code or not. If it includes a country/country code, the
string is passed into a restcountries.eu query. The country's alpha code is then taken from the query and passed into the api call,
along with the city name, to openweathermap.org. To get the city's current time, an api call to timezonedb.com, using the city's coordinates,
is passed.

If only a city name is passed, the application currently uses the first result(which is the most popular result) from the query (from openweathermap.org) as the city to be passed.



# Development Setup

openweathermap.org and timezonedb.com require api keys. Sign up for each website and the api key will be senthttps://atrl.net/ to your email. Place your api key in 
src/main/resources/application.properties as so

weather.api.key={your api key from openweathermap.org}

timezone.api.key={your api key from timezonedb.com}

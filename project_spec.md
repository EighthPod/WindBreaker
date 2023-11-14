# WindBreaker

## Table of Contents

1. [App Overview](#App-Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
1. [Build Notes](#Build-Notes)

## App Overview

### Description 

This weather app is designed to give users weather data for their day. If you are someone who works outside, commutes a lot, or you are just a casual user, you can plan your day accordingly since the weather app will support multiple locations. Everything is planned to use one screen and allows options for customization such as toggles for themes, notifications, types of units, and even graphs based on the weather.

### App Evaluation

<!-- Evaluation of your app across the following attributes -->

- **Category:** Weather
- **Mobile:** Convenient to use on a mobile device due to it being mobile. Can update based on location.
- **Story:** User works outside and is not sure if rain is in the forecast. User checks the app and allows for the user to prepare for rain or not.
- **Market:** Almost everyone needs to know weather information. It is not strictly people who work outside. Helpful for users going outside for a brief walk for example.
- **Habit:** Daily check in based on location.
- **Scope:** User inputs zip code locations (home, work, location of outside activity). App displays output of weather for each location in a RecyclerView.

## Product Spec

### 1. User Features (Required and Optional)

Required Features:
(Minimum Viable Product, MVP)

- [x] **Basic weather data**
- User input for ZIP code
- Display city, temperature, atmospheric pressure, wind speed, wind direction, feels like, cloudiness, humidity, rain for the next 24 hours, time of the report
- [x] **Edit text and button to store additional locations**
- [x] **Data for each location displayed as part of the RecyclerView**

Stretch Features:

- [] **Buttons to store things** Example: multiple settings
- [] **Handle latitude and longitude input**
- [] **Use different API call for forecast**
- [] **Notifications for upcoming severe weather events**
- [] **Themes to go along with the current weather, utilize dark mode and light mode in a different way**
- [] **Button to change setting for celsius, farenheit, km/h, mp/h**
- [] **Other, more niche weather data such as UV index and air pollution**
- [] **Add visualization to the data** Example: graphs or charts for minute-by-minute rain or temperature
- [] **Implement further UI components such as snackbar**
- [] **Implement more ways for the user to interact with the app and the data**

### 2. Chosen API(s)

- **https://openweathermap.org/current**
  - **Required for Basic weather data for past and up to the current time**

- **https://openweathermap.org/forecast5**
  - **Required for weather forecast**

- **https://api.openweathermap.org/data/2.5/onecall**
  - **Required for alerts**

- **http://api.openweathermap.org/data/2.5/air_pollution**
  - **Required for air pollution**

- **http://api.openweathermap.org/data/2.5/uvi**
  - **Required for UV index**

### 3. User Interaction

Required Features:

- [x] **User inputs ZIP and presses button (numbers only)**
  - => **Action:** User inputs ZIP code into edit text UI component, then user presses submit button.
  - => **Result:** OpenWeatherMap API is queried and data is retrieved for the user's location.
- [x] **User adds multiple locations**
  - => **Action:** User inputs ZIP code for other locations such as home, work, school, etc. as defined by the user.
  - => **Result:** Weather data for each location is displayed in separate containers within the RecyclerView.

Stretch Features:
- [] **User can enable notifications for severe weather events**
  - => **Action:** Allow the user to toggle a button for notifications of severe weather events.
  - => **Result:** The user receives notifications on the device when a severe weather event happens for one of the locations as defined by the user.
- [] **User changes themes**
  - => **Action:** The user can toggle a button for customized themes.
  - => **Result:** Dark mode & light mode are enabled with themes and styles based on current weather events for each container within the RecyclerView.
- [] **User can access enhanced weather data**
  - => **Action:** User long presses a location container.
  - => **Result:** Enhanced weather data such as UV index and air pollution is displayed for that location.
- [] **Implement button to toggle for different conversions**
  - => **Action:** User presses button for celsius or farenheit for temperature. User presses button for km/h or mp/h for speed. Buttons are displayed outside of the RecyclerView as a setting.
  - => **Result:** App displays results for the conversions.


## Wireframes

<!-- Add picture of your hand sketched wireframes in this section -->
<img src="https://i.imgur.com/0ABKMTN.jpg" width=600>

### [BONUS] Digital Wireframes & Mockups

### [BONUS] Interactive Prototype

## Build Notes

Here's a place for any other notes on the app, it's creation 
process, or what you learned this unit!  

For Milestone 2, include **2+ Videos/GIFs** of the build process here!

## License

Copyright **2023** **Austin Wood, Tyson Lowe, Jinash Rouniyar, Tinina Dowell**

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

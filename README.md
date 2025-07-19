#Weather Alert Application

🌩️ This Weather Alert App is a Spring Boot-based web application that fetches and monitors live weather data using the OpenWeatherMap API and sends real-time SMS and email alerts when severe weather conditions are detected at the user’s current location.

🔥 Features
🌍 Automatically detects the user's live location using the browser

🌡️ Checks for weather conditions like:

High temperature (above 40°C)

Low temperature (below 5°C)

Thunderstorm / Lightning

Thunderstorm with heavy rain

Hailstorm

Heavy rainfall (> 50mm/hr)

🔁 Checks weather every 1 minute

✉️ Sends email alerts via Gmail SMTP

📱 Sends SMS alerts using Twilio API

📊 Displays real-time weather status when user visits /weather

⚠️ Does not send alerts when normal conditions are detected

🛠️ Technologies Used
Java 17+

Spring Boot

Spring Web

Spring Scheduler

JavaMailSender (for emails)

Twilio SDK (for SMS)

OpenWeatherMap API (for live weather)

Mailtrap (for early-stage testing)

Bootstrap / Vanilla JS (frontend)

✅ Prerequisites
Java SDK 17+

Maven 3.6+

Gmail account (with App Password enabled)

Twilio account (with SMS-enabled phone number and Auth Token)

OpenWeatherMap API Key

🚀 How It Works
Upon visiting the webpage, the browser captures the user's GPS location.

A background JavaScript sends the coordinates to /api/weather/track every 1 minute.

The server uses lat and lon to fetch current weather data via OpenWeatherMap API.

If any severe condition is detected:

📧 Email is sent to the configured Gmail recipient.

📱 SMS is delivered to the configured phone number.

If no severe condition is detected, no alert is sent.

💻 Running the Application
bash
# Clone the repository
git clone https://github.com/your-username/weather-alert-app.git
cd weather-alert-app

# Build and run
mvn clean install
mvn spring-boot:run
⚙️ Configuration
src/main/resources/application.properties
text
# OpenWeatherMap
weather.api.key=your_api_key_here

# Mail (Gmail SMTP)
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your_gmail@gmail.com
spring.mail.password=your_gmail_app_password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

# Twilio
twilio.account.sid=your_twilio_sid
twilio.auth.token=your_twilio_token
twilio.phone.number=+1xxxxxxxxxx
💡 Use a .env or application-dev.properties file for security in production.

🧪 Testing the Alert System
Launch the app and open it in Chrome browser.

Allow location sharing.

Wait 1 minute or change location using Chrome Dev Tools > Sensors.

If weather conditions are severe, check your:

SMS inbox

Gmail inbox

🛡 Security Tips
Never hardcode sensitive credentials. Use environment variables or Spring Cloud Vault in production.

Use HTTPS in production.

Add authentication if supporting multiple users (JWT, session-based, etc.).

Rate-limit the /api/weather/track endpoint.

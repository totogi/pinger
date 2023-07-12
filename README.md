# pinger

Simple utility that pings a url and sends a text message if it does not receive an `HTTP 200 OK` response.

## Prerequisites
This utility requires that you have a [Twilio](https://www.twilio.com/) account with a Twilio phone number configured.

## Building
Run the following command to build the application:

    ./gradlew clean build

## Running
Run `pinger-app` application as an executable jar using the following command:

    java -jar pinger-app-0.1.0.jar /
    --account=[Twilio Account Sid] /
    --authToken=[Twilio Auth Token] /
    --fromPhone=[Twilio Phone Number] /
    --url=[Url to ping] /
    --toPhone=[Phone number to text]
    
    
Example:

    java -jar pinger-app-0.1.0.jar /
    --account=ACf5568dfe6106867907898010f1d14669 /
    --authToken=2cefe659e93405d11223398a548e42db6 /
    --fromPhone=+18459240611 /
    --url=https://www.netifi.com /
    --toPhone=+18459960345

Example with env variables:

    java -jar pinger-app/build/libs/pinger-app-0.1.0.jar \
    --account=$ACCOUNT_SID \
    --authToken=$AUTH_TOKEN \
    --fromPhone=+18555371834 \
    --url=https://whoosh-ping.totogidemos.com/random_ping \
    --toPhone=+18459960345 \
    --interval=5 \
    --threshold=1

## Bugs and Feedback
For bugs, questions, and discussions please use the [Github Issues](https://github.com/gregwhitaker/pinger/issues).

## License
MIT License

Copyright (c) 2019 Greg Whitaker

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
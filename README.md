# Happy Brackets Web OSC

### Send OSC messages to a web browser from [Happy Brackets](https://happybrackets.net/).  

This example uses the Happy Brackets [AccelerometerListener](https://www.happybrackets.net/doc/net/happybrackets/device/sensors/AccelerometerListener.html) and [OSCUDPSender](https://www.happybrackets.net/doc/net/happybrackets/core/OSCUDPSender.html) to send real-time sensor data as OSC messages to a Node.js server.  

This code uses [osc.js](https://github.com/colinbdclark/osc.js/) and was adapted from this [this example](https://github.com/colinbdclark/osc.js-examples/tree/master/udp-browser).

## Installation
### Install dependencies via npm
```bash 
cd HappyBrackets-WebOSC
npm install
```
```bash 
cd HappyBrackets-WebOSC/web/
npm install
```

## Web Setup
### Start  Node.js server
Open Terminal, `cd` to the root directory, and start the Node.js server. 
```bash 
cd HappyBrackets-WebOSC
node .
```
### Start web server
In a seperate Terminal window, `cd` to the `web` folder, and start a web server. 
```bash
cd HappyBrackets-WebOSC/web/
php -S 0.0.0.0:9898
```
Once the server is running, you can access the web page via [http://localhost:9898](http://localhost:9898).  You may also open the page from other devices on the network using your [local IP address](https://apple.stackexchange.com/a/212207). For example [http://192.168.0.100:9898](http://192.168.0.100:9898).

## Happy Brackets Setup
To setup the Happy Brackets file, simply drag the [WebOSC.java](WebOSC.java) file into your Happy Brackets project folder. 

**OR** 

you can create a new file 
1. Open IntelliJ
2. File > New > HappyBracketsSketch
3. Paste the following code directly after `hb.reset()`
```java 
OSCUDPSender oscSend = new OSCUDPSender();  
new AccelerometerListener(hb) {  
    @Override
    public void sensorUpdated(float x_val, float y_val, float z_val) {
        oscSend.send(HB.createOSCMessage("/hb/accelData", x_val, y_val, z_val), "192.168.0.100", 7400);  
    }
};
```

# Tips

This example uses IP `192.168.0.100`.  You may need to update this value using your own local IP address.   [How to find your local IP address](https://apple.stackexchange.com/a/212207).


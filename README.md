# Happy Brackets Web OSC

### Send OSC messages to a web browser from [Happy Brackets](https://happybrackets.net/).  

This example uses the Happy Brackets [AccelerometerListener](https://www.happybrackets.net/doc/net/happybrackets/device/sensors/AccelerometerListener.html) and [OSCUDPSender](https://www.happybrackets.net/doc/net/happybrackets/core/OSCUDPSender.html) to send real-time sensor data as OSC messages to a WebSocket server in Node.js.  

This code uses [osc.js](https://github.com/colinbdclark/osc.js/) and was adapted from [this example](https://github.com/colinbdclark/osc.js-examples/tree/master/udp-browser).

## Installation
 Install dependencies via npm
```bash 
cd HappyBrackets-WebOSC
npm install
```
```sh 
cd HappyBrackets-WebOSC/web
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
In a seperate Terminal window, `cd` to the `web` folder, and start the web server. 
```bash
cd HappyBrackets-WebOSC/web
php -S 0.0.0.0:9898
```
Once the server is running, you can access the web page via [http://localhost:9898](http://localhost:9898).  You can also open the web page from other devices on the network using your local IP address. For example [http://192.168.0.100:9898](http://192.168.0.100:9898).

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
        oscSend.send(HB.createOSCMessage("/hb/accelData", x_val, y_val, z_val), "0.0.0.0", 7400);  
    }
};
```

## Tips
This example uses IP `192.168.0.100`.  You may need to replace this value with your own local IP address.   [How to find your local IP address](https://apple.stackexchange.com/a/212207).

#### 2 places to update the IP
[index.html](/web/index.html) line `12`
```javascript
var port = new osc.WebSocketPort({
    url: "ws://192.168.0.100:8081" // replace this with your local IP
});
```
[WebOSC.java](WebOSC.java) line `17`
```javascript
oscSend.send(HB.createOSCMessage("/hb/accelData", x_val, y_val, z_val), "192.168.0.100", 7400);
```

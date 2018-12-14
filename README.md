# Browser ID-card tests

ID-card authentication and signing example test project using [Selenium](https://www.seleniumhq.org/)
for browser automation and [SikuliX](http://sikulix.com/) for automating the ID-card flows.

### Prerequisites

* Java 8 (JDK)
* Up to date web browsers (in English to match button texts)
* Up to date ID-card software with browser extensions installed
* Cleared extra certificates (using the ID-Utility tool)

### Setup

##### Windows

Chrome:

* Delete `HKEY_LOCAL_MACHINE\SOFTWARE\Policies\Google\Chrome\ExtensionInstallForcelist`
registry parameter if present. This parameter is set during the ID-software installation.
If it is present, it will cause an error alert on ChromeDriver opening.

Internet Explorer:

* Internet Options/Security: For all 4 zones:
    - Click "Reset all zones to default level"
    - Tick "Enable Protected Mode"
    - Click "OK"

##### Linux

SikuliX requires additional packages:
* `apt install libopencv-dev`
* `apt install tesseract-ocr`

### Running tests

On Linux replace `mvnw` with `./mvnw`

Example test commands:
```
mvnw test                                      // run all tests
mvnw -Dtest=FirefoxAuthenticationTest test     // run Firefox authentication test
mvnw -Dtest=InternetExplorerSigningTest test   // run Internet Explorer signing test
mvnw -Dtest=ChromeSigningTest test             // run Chrome signing test
mvnw -Dtest=*AuthenticationTest test           // run all authentication tests
```

### Configuration

Configuration parameters can be changed in the `properties.conf` file.

New WebDriver executables can be downloaded from https://www.seleniumhq.org/download/

### TODO
* Signing extension loading in Firefox (all platforms)
* Chrome extension loading on Ubuntu
* Fix SikuliX pattern matching on Ubuntu
* Support for macOS

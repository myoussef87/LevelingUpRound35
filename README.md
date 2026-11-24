# LevelingUpRound35

Java browser automation exercises using Selenium WebDriver, TestNG, and Maven.
Page objects keep browser interactions and assertions separate from test scenarios.

## Test coverage

- DuckDuckGo: page title, logo visibility, searches, and search result assertions.
- The Internet: checkbox selection and file uploads.
- W3Schools: company and country values in an HTML table.
- jQuery UI: drag and drop.

## Requirements

- JDK 25, matching the compiler configuration in `pom.xml`.
- Maven available as `mvn`.
- A Selenium server or Grid at `http://localhost:4444` with Chrome or Firefox available.
- Network access to the public websites exercised by the tests.

The project declares Selenium Java **4.53.0** and TestNG **7.12.0**.
Run commands from the repository root so configuration and upload paths resolve correctly.

## Configuration

Edit [environment.properties](src/main/resources/environment.properties):

```properties
TargetBrowser=Chrome
DuckGoURL=https://duckduckgo.com/
SearchText=Selenium WebDriver
ResultLink=https://www.selenium.dev/documentation/webdriver/
```

`TargetBrowser` accepts `Chrome` or `Firefox` (case insensitive) and applies to all
tests. The remaining properties are used by `test3again`; other scenarios use
values defined in the test class. The example above uses the documentation URL
expected by `test3`; the committed configuration currently includes an additional
`target/` suffix.

## Running tests

With the Selenium server running, execute the test class explicitly:

```sh
mvn -Dtest=testPackage.TestClass test
```

To run one scenario, such as the checkbox exercise:

```sh
mvn -Dtest=testPackage.TestClass#test6 test
```

Maven is also configured to use [TestSuite.xml](TestSuite.xml) with `mvn test`.
The suite currently selects `tests` and `tests.*`, while the test class belongs
to `testPackage`. Update the suite's package selection to `testPackage` before
using that command to run the scenarios.

Test reports are written under `target/surefire-reports/`.

## Project structure

```text
src/
  main/
    java/pages/                   Page objects and assertions
    resources/environment.properties
  test/
    java/testPackage/TestClass.java
TestSuite.xml                     TestNG suite configuration
pom.xml                           Dependencies and Maven configuration
eso1907a.jpg                       File upload fixture
```

## Current limitations

- Some assertions contain exercise-specific expectations that can fail, such as
  expecting the title `Google` on DuckDuckGo. Search result order and public site
  markup can also change.
- The Firefox setup creates a local `FirefoxDriver` before creating the remote
  session. It therefore also requires local Firefox and can leave that initial
  browser session open.
- The suite enables four parallel test methods, but the test class shares driver
  and page object fields. Disable method-level parallelism before using the suite
  to avoid browser sessions interfering with one another.
- The upload test sends an absolute file path without a remote file detector.
  The Selenium server must be able to access that path, or the upload handling
  must be adapted for a server on another machine or in a container.

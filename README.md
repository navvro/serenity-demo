# POC of Screenplay Pattern with Serenity BDD and Selenium + Cucumber


Run project with maven command 'clean install -Pcucumber' to run all tests.
To run only selected tasg, command will be eg.

'clean install -Pcucumber "-Dcucumber.options=--tags  @CART.TC05"'

In serenity.properties file provide path to chromedriver executable.

After successfull build and tests run report will be on path 

/target/site/index.html
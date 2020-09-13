# grouch-message-function
A Serverless Function for retrieving a grouchy message about when trash pick is.

## Build
This project is built using maven. The following command can be used to build.
```bash
./mvnw clean package
```

## Running locally
The lambda function can be ran locally using sam by running the following command
```bash
sam build && sam local start-lambda
```


## Tests
Unit Tests are ran as part of the maven build.

### Cucumber Test
This project uses Cucumber to automate testing and to describe behavior. To run the tests locally first
 [start the function](#Running-locally), then run the following command.
 
 ```bash
./mvnw -P cucumber verify 
```

To run the same tests against the production lambda function.
First use the `aws configure` command to setup credentials for aws then run.
```bash
./mvnw -P cucumber verify -Dspring.profiles.active=prod
```

You can also run from your IDE by running [`grouch.message.cucumber.CucumberRunner`](src/test/java/grouch/message/cucumber/CucumberRunner.java)

## Deploy
The code will automatically be built and deployed with a [github action.](.github/workflows/build.yml)

to deploy the application first use the `aws configure` comand to setup credentials for aws, then run
```bash
sam deploy
```

## Code Scanning

### Snyk OSS Scanning
OSS scanning is done using Snyk as part of the deployment pipeline and results can be viewed on the github action logs.
You can run a scan locally by running this command.

```bash
snyk monitor
```

### Sonar Scanning
Sonar quality scans are done as part of the deployment pipeline and results can be viwed on the github action logs.
Results can also be found on [Sonarcloud.](https://sonarcloud.io/dashboard?id=grouch-trash-service_grouch-message-function)
You can run a scan locally by running this command.

```bash
 ./mvnw -Dsonar.login=${SONAR_TOKEN} verify sonar:sonar 
```

# playft (Prevención de lavado de activos y financiamiento del terrorismo)

App developed in 2015 for companies that want to be within the Argentine law of prevention money laundering and financing of terrorism.

This application generates customer risk matrix, alarms, statistics, dashboard and training for employees.

Licenced app. Cloud and on premise deployment.

## About the code

Back-end: Java 1.7 and Spring 4 Rest API.
Front-end: AngularJs

### Initial setup

cd into the local repo directory and run:

```console
$ mvn install
```

### Deployment

Maven generate two wars. One with the API and other with the webapp. These can be deployments in a tomcat.

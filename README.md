# spring-boot-ws
WS using spring boot, jersey 2 and Jackson

# HelloWorldResourceImpl 

Endpoint localhost:8080/helloWorld to get the hello world message 

#BasicJacksonMarshalling

#### @JsonAnyGetter

    Example using annotation @JsonAnyGetter
    When used the elements of a Map will be shown as single json plain properties and no as a collection of json plain properties!
    See difference between 
        propertiesJsonAnyGetter = [(attr1, val1),(attr2, val2)] 
        AND 
        propertiesNoJsonAnyGetter = [(attr1No, val1No),(attr2No, val2No)] 

Endpoint localhost:8080/basicJacksonMarshalling/jsonAnyGetter

# run
mvn spring-boot:run

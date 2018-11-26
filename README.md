# spring-boot-ws
WS using spring boot, jersey 2 and Jackson

# HelloWorldResourceImpl 

Endpoint localhost:8080/helloWorld to get the hello world message 

#BasicJacksonMarshalling

#### @JsonAnyGetter

    Example using annotation @JsonAnyGetter
    When used the elements of a Map will be shown as single json plain properties and no as a collection of json plain properties!
    See difference between 
        propertiesJsonAnyGetter = [(JsonAnyGetter1, JsonAnyGetterVal1),(JsonAnyGetter2, JsonAnyGetterVal2)] 
        AND 
        propertiesNoJsonAnyGetter = [(JsonAnyGetter1No, JsonAnyGetterVal1No),(JsonAnyGetter2No, JsonAnyGetterVal2No)] 

Endpoint localhost:8080/basicJacksonMarshalling/jsonAnyGetter

#### @JsonGetter

    Example using annotation @JsonGetter
    When used @JsonGetter (alternative at @JsonProperty) we mark the specified methode as a getter methode. 
    The variables   
        nameJsonGetter with the getter methode : @JsonGetter("nameJsonGetter")
                                                 public String theNameJsonGetter() 
            will be shown as result                                           
        ON THE OTHER SIDE
        nameNoJsonGetter with the getter methode : public String theNameNoJsonGetter() 
            will not be shown as result                                            

Endpoint localhost:8080/basicJacksonMarshalling/jsonGetter

#### @JsonPropertyOrder

    Example using annotation @JsonPropertyOrder
    When used @JsonPropertyOrder we can specify the order of serialisation of elements of the object   
    We declare the elements in the order: id, name 
    but we will specify the following order: name, id                              

Endpoint localhost:8080/basicJacksonMarshalling/jsonPropertyOrder

# run
mvn spring-boot:run

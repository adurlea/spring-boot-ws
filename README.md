# spring-boot-ws
The goal of the project is to build an RESTful WS with:
- Spring Boot
- Docker
- Jersey 2 
- Jackson

The project will show:
 - the power of Jackson Annotations for serialisation of JSON

The build can produce an jar, an war and an docker container.
We can execute the project via :
 - maven spring boot execution 
 - container docker
 - Tomcat using the packed war
 
 
# Resources 

## HelloWorldResourceImpl 

Endpoint to get the hello world message

    localhost:8080/helloWorld  

## BasicJacksonMarshallingResourceImpl 

#### @JsonAnyGetter

    Example using annotation @JsonAnyGetter
    When used the elements of a Map will be shown as single json plain properties and no as a collection of json plain properties!
    See difference between 
        propertiesJsonAnyGetter = [(JsonAnyGetter1, JsonAnyGetterVal1),(JsonAnyGetter2, JsonAnyGetterVal2)] 
        AND 
        propertiesNoJsonAnyGetter = [(JsonAnyGetter1No, JsonAnyGetterVal1No),(JsonAnyGetter2No, JsonAnyGetterVal2No)] 

Endpoint 

    localhost:8080/basicJacksonMarshalling/jsonAnyGetter

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

Endpoint 

    localhost:8080/basicJacksonMarshalling/jsonGetter

#### @JsonPropertyOrder

    Example using annotation @JsonPropertyOrder
    When used @JsonPropertyOrder we can specify the order of serialisation of elements of the object   
    We declare the elements in the order: id, name 
    but we will specify the following order: name, id                              

Endpoint 

    localhost:8080/basicJacksonMarshalling/jsonPropertyOrder
    
#### @JsonRawValue
    
    Example using annotation @JsonRawValue
    When used @JsonRawValue we can inject raw json in an variable  
    and having it serialised as collection of plain elements for the variable element in the json response. 
    See difference between element [jsonRawValue] and [jsonNoRawValue]                      
    
Endpoint 
    
    localhost:8080/basicJacksonMarshalling/jsonRawValue
    
#### @JsonValue
    
    Example using annotation @JsonValue
    When used @JsonValue it will indicate the single method that should be used to serialize the entire object.
    
Endpoint 
    
    localhost:8080/basicJacksonMarshalling/jsonValue
    
#### @JsonRootName
    
    Example using annotation @JsonRootName
    When used @JsonRootName, when wrapping is enabled, allow you to specify the root name to use in the serialisation of the entity.
    In this example I used @JsonRootName("explication") on the bean JsonRootNameBean
    This means that instead of serializing something like  
        {"JsonRootNameBean" : { "id": 1, "name": "some explication" } }  we will have 
        { "explication" : { "id": 1, "name": "some explication" } }
        
Endpoint 
    
    localhost:8080/basicJacksonMarshalling/jsonRootName

# Run and Build
## DOCKER
### build docker image with maven
    mvn clean install dockerfile:build
### build docker image with docker
    docker image build --tag adurlea/spring-boot-ws:{project.version} .
### run with docker
    docker run -p 8080:8080 adurlea/spring-boot-ws

## with maven and spring boot
    mvn spring-boot:run

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

Request type: 

    GET

Endpoint to get the hello world message

    localhost:8080/helloWorld  

## BasicJacksonMarshallingResourceImpl 

### Serialization Annotations

#### @JsonAnyGetter

    Serialization example using annotation @JsonAnyGetter
    When used the elements of a Map will be shown as single json plain properties and no as a collection of json plain properties!
    See difference between 
        propertiesJsonAnyGetter = [(JsonAnyGetter1, JsonAnyGetterVal1),(JsonAnyGetter2, JsonAnyGetterVal2)] 
        AND 
        propertiesNoJsonAnyGetter = [(JsonAnyGetter1No, JsonAnyGetterVal1No),(JsonAnyGetter2No, JsonAnyGetterVal2No)] 

Request type: 

    GET

Endpoint 

    localhost:8080/basicJacksonMarshalling/jsonAnyGetter

#### @JsonGetter

    Serialization example using annotation @JsonGetter
    When used @JsonGetter (alternative at @JsonProperty) we mark the specified methode as a getter methode. 
    The variables   
        nameJsonGetter with the getter methode : @JsonGetter("nameJsonGetter")
                                                 public String theNameJsonGetter() 
            will be shown as result                                           
        ON THE OTHER SIDE
        nameNoJsonGetter with the getter methode : public String theNameNoJsonGetter() 
            will not be shown as result                                            

Request type: 

    GET

Endpoint 

    localhost:8080/basicJacksonMarshalling/jsonGetter

#### @JsonPropertyOrder

    Serialization example using annotation @JsonPropertyOrder
    When used @JsonPropertyOrder we can specify the order of serialisation of elements of the object   
    We declare the elements in the order: {id, name} 
    but we will specify the following order: {name, id}                              

Request type: 

    GET

Endpoint 

    localhost:8080/basicJacksonMarshalling/jsonPropertyOrder
    
#### @JsonRawValue
    
    Serialization example using annotation @JsonRawValue
    When used @JsonRawValue we can inject raw json in an variable  
    and having it serialised as collection of plain elements for the variable element in the json response. 
    See difference between element [jsonRawValue] and [jsonNoRawValue]                      
    
Request type: 

    GET
    
Endpoint 
    
    localhost:8080/basicJacksonMarshalling/jsonRawValue
    
#### @JsonValue
    
    Serialization example using annotation @JsonValue
    When used @JsonValue it will indicate the single method that should be used to serialize the entire object.
    
Request type: 

    GET
    
Endpoint 
    
    localhost:8080/basicJacksonMarshalling/jsonValue
    
#### @JsonRootName
    
    Serialization example using annotation @JsonRootName
    When used @JsonRootName, when wrapping is enabled, allow you to specify the root name to use in the serialisation of the entity.
    In this example I used @JsonRootName("explication") on the bean JsonRootNameBean
    This means that instead of serializing something like  
        {"JsonRootNameBean" : { "id": 1, "name": "some explication" } }  we will have 
        { "explication" : { "id": 1, "name": "some explication" } }
        
Request type: 

    GET
        
Endpoint 
    
    localhost:8080/basicJacksonMarshalling/jsonRootName
    
#### @JsonSerialize
    
    Serialization example using annotation @JsonSerialize
    When used @JsonSerialize we can use a custom serializer to serializer the entity. 
    See difference between [serializedDate] using annotation and 
    [noSerializedDate] not using the annotation
       
Request type: 

    GET
        
Endpoint 
    
    localhost:8080/basicJacksonMarshalling/jsonSerialize
    
### Deserialization Annotations

#### @JsonCreator
    
    Deserialization example using annotation @JsonCreator
    Using @JsonCreator with @JsonProperty can be usefull when the json sent contans an element or elements with other name than our entity. 
    This way the deserializer can match the received names with the entity names without needing to change the entity. 
    See difference between input and output autour de element someName
     
Request type: 

    POST
    
Headers:
    
    Content-Type : application/json;charset=utf-8
    
Body:
    
     {"id":1,"someName":"Using @JsonCreator with @JsonProperty can be usefull when the json sent contans an element or elements  with other name than our entity. This way the deserializer can match the received names with the entity names without needing to change the entity. See difference between input and output autour de element someName"}
        
Endpoint 
    
    localhost:8080/basicJacksonMarshalling/jsonCreator
    
#### @JacksonInject
    
    Deserialization example using annotation @JacksonInject
    Using @JacksonInject can be usefull when we want to inject a value that is not from the json data. 
    In this example I'm using the value provided by path param to inject it into {id} variable of JacksonInjectBean along with the json data in the body.
     
Request type: 

    POST
    
Headers:
    
    Content-Type : application/json;charset=utf-8
    
Body:
    
     {"name":"Using @JacksonInject can be usefull when we want to inject a value that is not from the json data. In this example I'm using the value provided by path param to inject it into {id} variable of JacksonInjectBean along with the json data in the body."}
        
Endpoint 
    
    localhost:8080/basicJacksonMarshalling/jacksonInject/{id}
    
#### @JsonAnySetter
    
    Deserialization example using annotation @JsonAnySetter
    @JsonAnySetter is the corespondant of @JsonAnyGetter but is used for deserialization. 
    It allow us the flexibility to add the plain elements of the json to a map. See difference between input and output.
     
Request type: 

    POST
    
Headers:
    
    Content-Type : application/json;charset=utf-8
    
Body:
    
     {
         "name":"@JsonAnySetter is the corespondant of @JsonAnyGetter but is used for deserialization. It allow us the flexibility to add the plain elements of the json to a map. See difference between input and output.",
         "jsonAnyGetterAttr1":"jsonAnyGetterVal1",
         "jsonAnyGetterAttr2":"jsonAnyGetterVal2"
     }
        
Endpoint 
    
    localhost:8080/basicJacksonMarshalling/jsonAnySetter

#### @JsonSetter
    
    Deserialization example using annotation @JsonSetter
    When used @JsonSetter we mark the specified methode as a setter methode. 
    The variables   
            bookNameJsonGetter with the setter methode : @JsonSetter("nameJsonSetter")
                                                     public String setBookNameJsonSetter(String bookNameJsonSetter) 
                will be shown as output                                           
            ON THE OTHER SIDE
            bookNameNoJsonSetter with the getter methode : public String setBookNameNoJsonSetter(String bookNameNoJsonSetter) 
                will not be shown as output   
     
Request type: 

    POST
    
Headers:
    
    Content-Type : application/json;charset=utf-8
    
Body:
    
     {
     	"id":1,
         "nameJsonSetter":"Non conform setter using @JsonSetter and it will be shown in the output.",
         "nameNoJsonSetter":"Non conform setter not using @JsonSetter and it will not be shown in the output."
     }
        
Endpoint 
    
    localhost:8080/basicJacksonMarshalling/jsonSetter
    
#### @JsonDeserialize
    
    Deserialization example using annotation @JsonDeserialize
    When used @JsonDeserialize we can use a custom deserializer to deserializer the entity. 
    See the date input format difference between [serializedDate] using annotation and [noSerializedDate] not using the annotation  
     
Request type: 

    POST
    
Headers:
    
    Content-Type : application/json;charset=utf-8
    
Body:
    
     {
     	"name":"When used @JsonDeserialize we can use a custom deserializer to deserializer the entity. See the date format difference between [serializedDate] using annotation and [noSerializedDate] not using the annotation",
         "deserializedDate":"02-12-2018 23:00:00",
         "noDeserializedDate":"2018-12-02T23:00:00.000+0000"
     }
        
Endpoint 
    
    localhost:8080/basicJacksonMarshalling/jsonDeserialize   
    

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

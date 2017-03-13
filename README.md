### Features of the Google Distance Matrix API

```
• Provides matrix of distance and duration between an array of origins and locations 
• Default option provides time based on Car (mode of transport). 
```

#### Additional options

```
• Provide time based on traffic and mode of transport. 
• Language support • Distance in KM or Miles

# For POC purpose I have just used the default API options.
```

#### About the Solution

```
• Input: Takes an array of origins and destinations. 
• Output: Provides a matrix of distance and duration. 
# Example Input: Origins: A,B,C, Destinations: E,F,G Output: Distance and Time for A to E,F and G, B to E,F and G, C to E,F and G.
```

#### Validations:

```
• Origin and Destination Length are restricted to 2 to 100 characters 
• Number of origin and destination combinations are restricted to 10. 
• In free tier Google allows only 100 combinations. 
• Mandatory field check.
```

#### Tech Stack:
```
Java 8, Angular JS 1.x, Bootstrap CSS, Toastr CSS, Spring Boot, Maven, AWS Elastic Beanstalk, Junit.
```

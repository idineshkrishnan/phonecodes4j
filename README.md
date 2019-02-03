# phonecodes4j

[![License MIT](https://img.shields.io/badge/license-MIT-blue.svg)](https://raw.githubusercontent.com/idineshkrishnan/phonecodes4j/master/LICENSE.md)

# Introdction

The phonecode4j is a simple library to get phone codes for given countries code or vise versa and this is developed and being maintained by [Dinesh Krishnan](http://www.dineshkrish.com/).

## Latest artifact:

    <dependency>
      <groupId>com.dineshkrish</groupId>
      <artifactId>phonecodes4j</artifactId>
      <version>2.0.1</version>
    </dependency>
    
## Getting started:

### #1 Creating an instance

  ```java
  PhoneCodes phoneCodes = PhoneCodes.getInstance();
  ```
  
### #2 Getting country name by phone code

```java
    phoneCodes.getCountryNameByPhoneCode(1); // Output: Canada/United States 
    phoneCodes.getCountryNameByPhoneCode(91); // Output: India
```

### #3 Getting country code by phone code

```java
    phoneCodes.getCountryCodeByPhoneCode(1, Type.ALPHA_CODE_2); // Output: CA/US 
    phoneCodes.getCountryCodeByPhoneCode(1, Type.ALPHA_CODE_3); // Output: CAN/USA
```

### #4 Getting phone code by country code

```java
    
    // passing country code - alpha code 2 format
    phoneCodes.getPhoneCodeByCountryCode("US"); // Output: 1 
    
    // passing country code - alpha code 3 format
    phoneCodes.getPhoneCodeByCountryCode("USA"); // Output: 1
```

# License

This project is licensed under the terms of the MIT license.

# Coding Assignment: The salestax problem
Coding Assignment for itemis interview. The Sales Tax problem:

I used Maven as the build system and JUnit for Testing. 

## Requirements
- jdk 11 (tested with openjdk 11 2018-09-25 on Windows10 and openjdk 11.0.11 2021-04-20 on Ubuntu via WSL2)
- git (tested with v2.33.1.windows.1 on Windows10 and v2.25.1 on Ubuntu via WSL2)

## Installation
```shell
    git clone https://github.com/yasinsoenmez/codingassignment_salestax.git
    ./mvnw clean package
```

## Execution
```shell
    java -jar target/codingassignment_salestax-1.0-SNAPSHOT.jar 
```
The Application will first execute the input from the assignment.
After that the user can type their own orders. 

There need to be at least 3 elements separated with blank space.
The first element needs to be an integer and the last element a decimal number. 
The middle can have an arbitrary number of elements, but at least one. Use of *imported* will 
be extracted and put at the beginning of the output. Used *at* will be discarded.

## Assumptions
- We get a String input that needs to be parsed.
- There are requirements for a correct order.
- First element needs to be an integer value and is assumed to be the amount.
  - In the example input only *1* is used, but I assumed that it has to be at least *1* but can be higher
- The last element needs to be a decimal number and is assumed to be the price of one product.
  - I can not be sure because in the example input only one of every product is used.
  - So if you type *2 chocolates at 5.00* the total amount will be *10.00*
- The remaining elements in the middle are assumed to be part of the name, except for use of *imported* and *at*.
  - The example input suggest that *imported* can be used at any place in the middle, but the output has to put it at the beginning.
  - *at* will be discarded and only separates name and price in the input. In my implementation it can be used but is not necessary.
- In a real application an arbitrary name is not realistic. You will more likely choose a product from a list that already has an assigned product type. In this assignment the product type check is hardcoded for the used names in the example input.
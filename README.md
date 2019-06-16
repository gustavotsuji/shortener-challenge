# Getting Started

## Quickstart

Follow this section to get this project working. 

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

### Getting the project

```sh
$ git clone shortener-challenge
$ cd shortener-challenge
$ mvn spring-boot:run
```
### Testing the project

```sh
$ mvn spring-boot:test
```

### Generate Sluging

For this project, we are going to use a *base conversion* strategy to generate slugs. The main idea is convert the id generated (base 10) into a sequence of characters (in another base). Besides that, this transformation should be reversible: from this sequence of characters, it has to be possible to get back the id.

As this slug will be part of an url, we have to restrict the set of characters will be used (eg: $, =, /, ?, . and others can't be used). In this case, we can use letters and numbers. As letters are sensitive (A is different from a) in path portion, there are 62 characters available to build the slug (26 lowercase, 26 uppercase and 10 numbers).

| Base 10 | Mapping |
|---------|---------|
| 0       | 0       |
| 1       | 1       |
| 2       | 2       |
| ⋮      | ⋮       |
| 8       | 8       |
| 9       | 9       |
| A       | 10      |
| B       | 11      |
| C       | 12      |
| ⋮      | ⋮       |
| Y       | 34      |
| Z       | 35      |
| a       | 36      |
| b       | 37      |
| ⋮      | ⋮       |
| y       | 60      |
| z       | 61      |

### Encoding

The encoding process takes these steps:

1. Get the module 62 from the given id and retrieve the correspondent character from the mapping
2. Get the result from id/62
3. If result > 0, then repeat the step 1 using result from step 2 as input instead of id and append the character previously found on the right

#### Example

Suppose that the id is 3846
3846 module 62 = 1 (which leads to '2' on map)
3845 / 62 = 62

Repeat the process

62 module 62 = 0 (which leads to '0' on map)

62/62 = 1

Repeat the process

1 module 62 = 1 (which leads to '1' on map)

1/62 = 0


### Decoding

The decoding process takes these steps:

1. Get a letter from slug from the right to the left and find the number according the mapping. 
2. Be 'p' the position of a letter in the slug (starting on 0 from the right to the left) and 'n' the number found in the mapping.
Calculate:

   ![equation](https://latex.codecogs.com/svg.latex?\Large&space;$$\sum_{p=0}^{slug_{size}}n_{p} * 62^p)
   
#### Example

Suppose that the slug is 10a

Starting from 'a'
Looking for 'a' correspond, we got 36. As 'a' is the sluging first letter, then we have to multiply to 1 and sum to result (36)

Next character is 0
Looking for '0' correspond, we got 0. As '0' is the second letter, then we have to multiply to 62 and in this case nothing happens to result

Next character is 1
Looking for '1' correspond, we got 1. As '1' is the third letter, then we have to multiply to 3844 and sum to result (3880)


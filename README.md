# Cron

This is an application that interprets a crontab schedule and converts it to a table with the days where it would run

## Getting Started

### Prerequisites

You require the Java JDK to run this app. It has been tested with the version `11.0.12`.

### Running the Application

There is an `sh` [wrapper](./cron) to use the CLI. Do:

```shell
./cron "*/15 0 1,15 * 1-5 /usr/bin/find"
```

Which will output something like:

```console
minute        0 15 30 45
hour          0
day of month  1 15
month         1 2 3 4 5 6 7 8 9 10 11 12
day of week   1 2 3 4 5
command       /usr/bin/find
```

### Running the Tests

There is a suit of unit tests for all the functionality. Run it with:

```shell
./gradlew test
```

Open the test report with:

```
open app/build/reports/tests/test/index.html
```

## Error Checking

The parser checks the expression for validity, including:

- right length
- operators being numbers
- values being in the range of the field (26 is not a valid hour)

The checks aren't 100% comprehensive, though. When an expression can't be parsed, it's propagated back. There are no granular error messages.

## Supported Operations

- Wildcards
- Single values
- Value ranges
- Value enumerations
- Step ranges

Value enumerations only support single values. Something like `1-3,4-6` isn't implemented.

Step ranges can be applied to every other type of expression.

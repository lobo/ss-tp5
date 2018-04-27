![...](res/image/readme-header.png)

# Simulation N° 5: Granular Flow

## Build

To build the project, it is necessary to have _Maven +3.5.0_, and
_Java SE 8 Release_ installed. Then, run:

```
$ mvn clean package
```

This will generate a _\*.jar_ in the `target` folder. If you find any issues
with the building, remove the _\*.jar_ files from the _Maven_ local
repository with:

```
$ rm -fr ~/.m2/repository/ar/edu/itba/ss/*
```

Or do it manually, if you prefer.

## Execute

In the root folder (after build):

```
$ java -jar target/tp5-1.0-SNAPSHOT.jar <simulate | animate>
```

## Configuration

Receives a JSON file with the following format:

```json
{
    "output"            : "res/data/output.data",
    "delta"             : "0.001",
    "time"              : "5.0",
    "fps"               : "50",

    "integrator"        : "BeemanIntegrator",
    "radius"            : ["0.02", "0.03"],
    "mass"              : "0.01",

    "elasticNormal"     : "1.0E+5",
    "elasticTangent"    : "2.0E+5",
    "gamma"             : "1.0E+2",

    "generator"         : "64684095347601931",
    "n"                 : "100",
    "height"            : "4.0",
    "width"             : "1.0",
    "drain"             : "0.15",
    "flowRate"          : "0.1"
}

```

## Output Files Format

### Simulation File (_\*.data_)

```
<x> <y> <r> <speed>
...
```

### Animation File (_\*.xyz_)

```
<N>
<t0>
<x> <y> <r> <speed>
...
```

## Videos

...

## Developers

This project has been built, designed and maintained by the following authors:

* [Daniel Lobo](https://github.com/lobo)
* [Agustín Golmar](https://github.com/agustin-golmar)

![...](resources/image/readme-header.png)

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
$ java -jar target/tp5-1.0-SNAPSHOT.jar <simulate | animate | flow>
```

## Configuration

Receives a JSON file with the following format:

```json
{
    "output"            : "res/data/output.data",
    "delta"             : "0.00003",
    "time"              : "15.0",
    "fps"               : "50",
    "playbackSpeed"     : "0.5",
    "samplesPerSecond"  : "200",

    "integrator"        : "BeemanIntegrator",
    "reportEnergy"      : "false",
    "reportTime"        : "true",
    "radius"            : ["0.01", "0.015"],
    "mass"              : "0.01",

    "elasticNormal"     : "1.0E+4",
    "elasticTangent"    : "2.0E+4",
    "viscousDamping"    : "20.0",
    "siloDamping"       : "15.0",

    "generator"         : "64684095347601931",
    "n"                 : "370",
    "height"            : "1.0",
    "width"             : "0.4",
    "drain"             : "0.15",
    "flowRate"          : "0.1",
    "injection"         : ["0.75", "1.0"]
}

```

## Output Files Format

### Simulation File (_\*.data_)

The complete simulation results. Includes the speed and pressure over every
particle in every simulation step.

```
<x> <y> <r> <speed> <pressure>
...
```

### Flow Rate File (_\*.drain_)

This file contains the complete drain (_i.e._, the ID of a particle
drained at a certain time).

```
<t> <id>
...
```

### Windowed Flow Rate File (_\*.flow_)

This is the windowed flow per unit of temporal step. The units are in
_[particles/second]_.

```
<t> <flow>
...
```

### Small Simulation File (_\*.small_)

This is a reduced form of the simulation file, but sub-sampled.

```
<x> <y> <r> <speed> <pressure>
...
```

### Animation File (_\*.xyz_)

This file can be used in _Ovito_ to render the simulation.

```
<N>
<t0>
<x> <y> <r> <speed> <pressure>
...
```

## Videos

* [Simulation N° 1: Drain 0.15](https://youtu.be/ZKFEjTMVARc)
* [Simulation N° 2: Drain 0.19](https://youtu.be/qytGS5KxaJ8)
* [Simulation N° 3: Drain 0.23](https://youtu.be/HZCMRGUbaJE)
* [Simulation N° 4: Drain 0.27](https://youtu.be/Dv1cnYnHJM8)
* [Simulation N° 5.1: Steady State (critic damping)](https://youtu.be/BrpwOBUKvJQ)
* [Simulation N° 5.2: Steady State (over-critic damping)](https://youtu.be/lU_Bl-VyUFk)

## Developers

This project has been built, designed and maintained by the following authors:

* [Daniel Lobo](https://github.com/lobo)
* [Agustín Golmar](https://github.com/agustin-golmar)

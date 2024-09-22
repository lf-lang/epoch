> [!WARNING]  
> Epoch is no longer actively maintained and its code repository has been archived.

# Epoch IDE for Lingua Franca

Epoch is an Eclipse-based Integrated Development Environment (IDE) for Lingua Franca.

## Installation

Installation instructions can be found on the [Epoch IDE](https://www.lf-lang.org/docs/handbook/epoch-ide) page.

## Building from Source

### Install Build Tools
The following tools are needed to build Epoch IDE:
- Java Development Kit, version 17 or higher
- [Apache Maven](https://maven.apache.org/)

### Clone this Repository
It is recommended to get the source code by cloning this repository instead of downloading from releases page, as there are necessary git submodules that need to be fetched.
```
git clone https://github.com/lf-lang/epoch.git --depth=1 && cd epoch
git submodule update --init --recursive
```

### Create Build
Create a build by executing:

```
mvn -U clean package
```

The location of the resulting Epoch app depends on your platform:
 - Linux: `./org.lflang.rca/target/products/org.lflang.rca/linux/gtk/x86_64/epoch/epoch`
 - macOS (Apple): `./org.lflang.rca/target/products/org.lflang.rca/macosx/cocoa/aarch64/epoch.app`
 - macOS (Intel): `./org.lflang.rca/target/products/org.lflang.rca/macosx/cocoa/x86_64/epoch.app`
 - Windows: `./org.lflang.rca/target/products/org.lflang.rca/win32/win32/x86_64/epoch/epoch.exe`
On Linux, run the `epoch` binary. On Mac, run `epoch.app` (drag into your `Applications` folder for installation). On Windows, execute `epoch.exe`. 

## Develop Using Eclipse

To work on the Epoch codebase in Eclipse, consult our wiki for [step-by-step setup instructions](https://github.com/lf-lang/epoch/wiki/Developer-Eclipse-Setup-with-Oomph).
Note that this is no longer supported. We recommend using IntelliJ instead.


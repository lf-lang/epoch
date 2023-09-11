# Epoch IDE for Lingua Franca

Epoch is an Eclipse-based Integrated Development Environment (IDE) for Lingua Franca.

## Download

Download instructions can be found on the [Epoch IDE](https://www.lf-lang.org/docs/handbook/epoch-ide) page.

## Direct Download of the Latest Release
You can download the latest release of Epoch from the Lingua Franca Release page:

 - [Epoch for x86_64 Linux](https://github.com/lf-lang/lingua-franca/releases/download/v0.4.0/epoch_ide_0.4.0-linux.gtk.x86_64.tar.gz)
 - [Epoch for aarch64 macOS](https://github.com/lf-lang/lingua-franca/releases/download/v0.4.0/epoch_ide_0.4.0-macosx.cocoa.aarch64.tar.gz)
 - [Epoch for x86_64 macOS](https://github.com/lf-lang/lingua-franca/releases/download/v0.4.0/epoch_ide_0.4.0-macosx.cocoa.x86_64.tar.gz)
 - [Epoch for Windows](https://github.com/lf-lang/lingua-franca/releases/download/v0.4.0/epoch_ide_0.4.0-win32.win32.x86_64.zip)

Extract the contents from the archive. For example:

```
open epoch_ide_0.4.0-macosx.cocoa.aarch64.tar.gz
```

For Linux and Windows, you can just run the resulting executable.

MacOS requires extra steps before being able to execute the app:

```
xattr -cr epoch.app
```

Drag the epoch.app file to your Applications folder.
You can then invoke the App as follows:
```
open epoch.app
```

Please note that Java version 17 or higher is required to run Epoch.

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
Create a build by executing
```
mvn -U clean package
```

The location of the resulting Epoch app depends on your platform:
 - Windows: `./org.lflang.rca/target/products/org.lflang.rca/win32/win32/x86_64/epoch/epoch.exe`
 - Linux: `./org.lflang.rca/target/products/org.lflang.rca/linux/gtk/x86_64/epoch/epoch`
 - macOS (Intel): `./org.lflang.rca/target/products/org.lflang.rca/macosx/cocoa/x86_64/epoch.app`
 - macOS (Apple): `./org.lflang.rca/target/products/org.lflang.rca/macosx/cocoa/aarch64/epoch.app`

On Windows, execute `epoch.exe`. On Linux, run the `epoch` binary. On Mac, drag the `epoch.app` application into your applications folder.

## Develop Using Eclipse

To work on the Epoch codebase in Eclipse, consult our wiki for [step-by-step setup instructions](https://github.com/lf-lang/epoch/wiki/Developer-Eclipse-Setup-with-Oomph).
Note that this is no longer supported. We recommend using IntelliJ instead.


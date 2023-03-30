# Epoch IDE for Lingua Franca

Epoch is an Eclipse-based Integrated Development Environment (IDE) for Lingua Franca.

## Using Epoch
You can download the latest release of Epoch from the Lingua Franca Release page:

 - [Epoch for x86_64 Linux](https://github.com/lf-lang/lingua-franca/releases/download/v0.4.0/epoch_ide_0.4.0-linux.gtk.x86_64.tar.gz)
 - [Epoch for aarch64 macOS](https://github.com/lf-lang/lingua-franca/releases/download/v0.4.0/epoch_ide_0.4.0-macosx.cocoa.aarch64.tar.gz)
 - [Epoch for x86_64 macOS](https://github.com/lf-lang/lingua-franca/releases/download/v0.4.0/epoch_ide_0.4.0-macosx.cocoa.x86_64.tar.gz)
 - [Epoch for Windows](https://github.com/lf-lang/lingua-franca/releases/download/v0.4.0/epoch_ide_0.4.0-win32.win32.x86_64.zip)

Extract the contents from the archive. For example:

```
open epoch_ide_0.4.0-macosx.cocoa.aarch64.tar.gz
```

For Linux and Windows, you can just run the resulting exutable.

MacOS requires extra steps before being able to execute the app:

```
xattr -cr Epoch.app
```

Drag the Epoch.app file to your Applications folder.
You can then invoke the App as follows:
```
open -a Epoch.app
```

Please note that Java version 17 or higher is required to run Epoch.

## Developer Setup

### Develop Using Maven

Clone this repo:

```
git clone git@github.com:lf-lang/epoch.git
cd epoch
git submodule update --init --recursive
```

Build Epoch:

```
mvn -U clean package
```

The location of the resulting Epoch app depends on your platform.

#### MacOS with Intel Silicon

Drag the Epoch.app application into your applications folder.
In the finder, the folder containing the Epoch.app application is (relative to your epoch git clone root):

```
org.lflang.rca/target/products/org.lflang.rca/macosx/cocoa/x86_64/
```

#### MacOS with Apple Silicon

Drag the Epoch.app application into your applications folder.
In the finder, the folder containing the Epoch.app application is (relative to your epoch git clone root):

```
org.lflang.rca/target/products/org.lflang.rca/macosx/cocoa/aarch64/
```

#### Linux

**FIXME**

#### Windows

**FIXME**

### To Update Lingua-Franca

To update Epoch to the current development version in the master branch:

```
cd org.lflang/lingua-franca
git checkout master
git pull
cd ../..
mvn package
```

### Develop Using Eclipse

To work on the Epoch codebase in Eclipse, consult our wiki for [step-by-step setup instructions](https://github.com/lf-lang/epoch/wiki/Developer-Eclipse-Setup-with-Oomph).


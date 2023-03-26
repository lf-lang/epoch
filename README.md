# Epoch IDE for Lingua Franca

Epoch is an Eclipse-based Integrated Development Environment (IDE) for Lingua Franca.

## Using Epoch
You can download the latest release of Epoch from the Lingua Franca Release page:
 - [Epoch for x86_64 Linux](https://github.com/lf-lang/lingua-franca/releases/download/v0.4.0/epoch_ide_0.4.0-linux.gtk.x86_64.tar.gz)
 - [Epoch for x86_64 macOS](https://github.com/lf-lang/lingua-franca/releases/download/v0.4.0/epoch_ide_0.4.0-macosx.cocoa.aarch64.tar.gz)
 - [Epoch for aarch64 macOS](https://github.com/lf-lang/lingua-franca/releases/download/v0.4.0/epoch_ide_0.4.0-macosx.cocoa.x86_64.tar.gz)
 - [Epoch for Windows](https://github.com/lf-lang/lingua-franca/releases/download/v0.4.0/epoch_ide_0.4.0-win32.win32.x86_64.zip)
 
Simply extract the contents from the archive and run the `epoch` executable. Please note that Java >= 17 is required to run Epoch.

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
mvn clean package
```

### Develop Using Eclipse

To work on the Epoch codebase in Eclipse, consult our wiki for [step-by-step setup instructions](https://github.com/lf-lang/epoch/wiki/Developer-Eclipse-Setup-with-Oomph).


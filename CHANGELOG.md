# Changelog
 
## [v0.8.2](https://github.com/lf-lang/epoch/tree/v0.8.2) (2024-08-02)

**Highlights**

This patch release includes minor bugfixes and several enhancements of our Docker support. It also adds custom serialization for the Python target and support for the use of target code expressions to specify time values in C++.


 
## [v0.8.1](https://github.com/lf-lang/epoch/tree/v0.8.1) (2024-07-14)

**Highlights**

This patch release includes several minor bugfixes and enhancements, improving Docker support for the C++ target and providing a more complete implementation of watchdogs.


 
## [v0.8.0](https://github.com/lf-lang/epoch/tree/v0.8.0) (2024-07-02)

**Highlights**

This release includes [Lingua Franca 0.8.0](https://github.com/lf-lang/lingua-franca/releases/tag/v0.8.0).

- Dependency on commons-text [\#74](https://github.com/lf-lang/epoch/pull/74) (@lhstrh)
- Fix apache.commons.text dependency registration [\#75](https://github.com/lf-lang/epoch/pull/75) (@a-sr)
- Code adjustment for pre-build-cmd branch [\#76](https://github.com/lf-lang/epoch/pull/76) (@a-sr)

 
## [v0.7.2](https://github.com/lf-lang/epoch/tree/v0.7.2) (2024-05-20)

**Highlights**

This release includes patches of the C runtime only.


 
## [v0.7.1](https://github.com/lf-lang/epoch/tree/v0.7.1) (2024-05-17)

**Highlights**

This patch release includes bugfixes that address imports, tracing plugins, clock synchronization, and code generation issues.


 
## [v0.7.0](https://github.com/lf-lang/epoch/tree/v0.7.0) (2024-05-01)

**Highlights**

This release includes an updated Lingua Franca compiler and updates of several dependencies.

**🔧 Fixes**

- Removal of failing federated template [\#62](https://github.com/lf-lang/epoch/pull/62) (@edwardalee)

**🚧 Maintenance and Refactoring**

- Proper formatting of templates [\#67](https://github.com/lf-lang/epoch/pull/67) (@lhstrh)

**⬆️ Updated Dependencies**

- Update the target platform to 2024-03, Xtext 2.34, Klighd 3.0.1, and ELK 0.9.1 [\#65](https://github.com/lf-lang/epoch/pull/65) (@soerendomroes)


 
## [v0.6.0](https://github.com/lf-lang/epoch/tree/v0.6.0) (2024-01-26)

**Highlights**

This release features an updated compiler core but includes no major changes to the IDE itself.

- Allow building against lingua-franca fork [\#53](https://github.com/lf-lang/epoch/pull/53) (@lhstrh)
- Introduce spotbugs and JCIP annotations dependency [\#54](https://github.com/lf-lang/epoch/pull/54) (@a-sr)
- Adjusted implementation of MessageReporter and new dependency on `com.google.gson` [\#55](https://github.com/lf-lang/epoch/pull/55) (@lhstrh)

 
## [v0.5.1](https://github.com/lf-lang/epoch/tree/v0.5.1) (2023-09-12)

**Highlights**

This release features an updated Lingua Franca compiler.

**📖 Documentation**

- Updated README [\#49](https://github.com/lf-lang/epoch/pull/49) (@edwardalee)


 
## [v0.5.0](https://github.com/lf-lang/epoch/tree/v0.5.0) (2023-09-01)

**Highlights**

This release comes with an updated Lingua Franca compiler, and several updated dependencies.

**🚀 New Features**

- Uclid5-based LF Verifier [\#26](https://github.com/lf-lang/epoch/pull/26) ([@a-sr](https://github.com/a-sr))

**✨ Enhancements**

- Support for M1 Silicon on Mac [\#1](https://github.com/lf-lang/epoch/pull/1) ([@a-sr](https://github.com/a-sr))
- Formatting configured for two-space indentation [\#21](https://github.com/lf-lang/epoch/pull/21) ([@a-sr](https://github.com/a-sr))
- ELK debug features added to Epoch product [\#28](https://github.com/lf-lang/epoch/pull/28) ([@a-sr](https://github.com/a-sr))

**🚧 Maintenance and Refactoring**

- Build infrastructure adjusted to new LF repository structure [\#17](https://github.com/lf-lang/epoch/pull/17) ([@a-sr](https://github.com/a-sr))
- Updated LF submodule and restored compatibility [\#20](https://github.com/lf-lang/epoch/pull/20) ([@a-sr](https://github.com/a-sr))
- Stream for the Oomph setup to install KlighD and ELK [\#25](https://github.com/lf-lang/epoch/pull/25) ([@soerendomroes](https://github.com/soerendomroes))
- ELK + KlighD Oomph setup [\#29](https://github.com/lf-lang/epoch/pull/29) ([@soerendomroes](https://github.com/soerendomroes))

**⬆️ Updated Dependencies**

- All dependencies bumped to latest releases [\#22](https://github.com/lf-lang/epoch/pull/22) ([@a-sr](https://github.com/a-sr))

**🏷️ Miscellaneous**

- Workaround to allow building this project in Eclipse [\#14](https://github.com/lf-lang/epoch/pull/14) ([@lhstrh](https://github.com/lhstrh))



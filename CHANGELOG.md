# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.1.1] - 2025-01-XX

### Added
- Text outline configuration option for better name visibility
- Multi-language support (English, German, Russian)
- Native NeoForge configuration menu integration

### Changed
- Text outline default set to `false` for cleaner appearance
- Improved transparency handling for talking/non-talking players

### Technical
- Port from Fabric to NeoForge 1.21.10
- Implemented using NeoForge ModConfigSpec (native config system)
- Mixin system using @WrapOperation for compatibility
- Support for Simple Voice Chat 2.6.6+

## [1.0.0] - Original Release

### Added
- Initial Fabric version by Greenman999
- Display player names next to group player icons
- Configurable transparency settings
- Option to show names only when talking

---

## Version Compatibility

| Mod Version | Minecraft | NeoForge | Simple Voice Chat |
|-------------|-----------|----------|-------------------|
| 1.1.1       | 1.21.10   | 21.10.50+ | 2.6.6+          |

## Credits

- **Original Mod**: [Greenman999](https://github.com/Greeenman999/simple-voice-chat-group-player-names)
- **NeoForge Port**: [Smallinger](https://github.com/smallinger)

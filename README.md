# Item Copier

A Minecraft NeoForge mod that provides an item copier block to quickly duplicate items.

## Mod Introduction

The Item Copier is an automated item duplication machine that accepts items via hoppers, triggers duplication with redstone signals, and outputs large quantities of identical items.

## Gameplay Guide

### Basic Usage

1. **Place the Machine**: Place the Item Copier block at your desired location
2. **Input Items**: Place a hopper on top of the machine and put the items you want to duplicate into the hopper
3. **Output Items**: Place a hopper or container (like a chest) below the machine to receive the duplicated items
4. **Trigger Duplication**: Provide a redstone signal to the Item Copier (redstone block, redstone wire, lever, etc.)

### Duplication Rules

- **Default Duplication Amount**: 64 items
- **Iron Block Special Effect**: If an iron block is placed above the Item Copier, the duplication amount is reduced by 1 (minimum 1)
  - Example: Default duplicates 64 items, with iron block above it duplicates 63 items
  - If configured to 1, it will still duplicate 1 item even with an iron block above

### Configuration File

Configuration file location: `config/copyitem-common.toml`

```toml
#The amount of items to copy when the machine is activated
# Default: 64
# Range: 1 ~ 64
copyAmount = 64
```

**Configuration Description**:
- `copyAmount`: Duplication amount, range 1-64, default 64

### Commands

The mod provides the following command:

- `/copyitem reset`: Reload the configuration file

**Usage**:
1. Modify the `copyAmount` value in the configuration file
2. Execute `/copyitem reset` command in-game
3. The new configuration value will take effect immediately

## Development Guide

### Requirements

- Java 21
- NeoForge 21.1.215
- Minecraft 1.21.1

### Build Steps

1. **Clone Project**:
   ```bash
   git clone <repository-url>
   cd copyitem-template-1.21.1
   ```

2. **Refresh Dependencies** (if needed):
   ```bash
   gradlew --refresh-dependencies
   ```

3. **Build Project**:
   ```bash
   gradlew build
   ```

4. **Get JAR File**:
   After successful build, the JAR file is located at: `build/libs/copyitem-1.0.0.jar`

### Development Tools

Recommended IDEs:
- IntelliJ IDEA (recommended)
- Eclipse

### Common Gradle Commands

```bash
# Build project
gradlew build

# Clean build files
gradlew clean

# Refresh dependencies
gradlew --refresh-dependencies

# Run client (for testing)
gradlew runClient

# Run server (for testing)
gradlew runServer
```

### Project Structure

```
src/main/
├── java/com/copy_item/item/
│   ├── CopyItem.java              # Main mod class
│   ├── Config.java                # Configuration class
│   ├── CopyItemBlock.java         # Block class
│   ├── CopyItemBlockEntity.java   # Block entity class
│   ├── CopyItemCommand.java       # Command class
│   ├── CopyItemConfigHandler.java # Configuration handler
│   ├── CopyItemTickHandler.java   # Tick handler
│   └── CopyItemBlockEventHandler.java # Block event handler
├── resources/
│   ├── assets/copyitem/
│   │   ├── blockstates/   # Block states
│   │   ├── models/        # Models
│   │   ├── textures/      # Textures
│   │   └── lang/          # Language files
│   └── data/copyitem/
│       └── loot_tables/   # Loot tables
```

### Mod Information

- **Mod ID**: copyitem
- **Mod Name**: Item Copier
- **Version**: 1.0.0
- **Author**: mcjava20
- **Minecraft Version**: 1.21.1
- **NeoForge Version**: 21.1.215

## License

This mod uses the NeoForge MDK template and follows relevant licenses.

## Mapping Names

By default, the MDK is configured to use the official mapping names from Mojang for methods and fields in the Minecraft codebase. These names are covered by a specific license. All modders should be aware of this license. For the latest license text, refer to the mapping file itself, or the reference copy here:
https://github.com/NeoForged/NeoForm/blob/main/Mojang.md

## Additional Resources

- Community Documentation: https://docs.neoforged.net/
- NeoForge Discord: https://discord.neoforged.net/

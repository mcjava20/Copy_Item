# 物品复制机 (Item Copier)

一个 Minecraft NeoForge 模组，提供物品复制机方块，可以快速复制物品。

## 模组介绍

物品复制机是一个自动化物品复制机器，通过漏斗输入物品，红石信号触发复制，输出大量相同物品。

## 玩法说明

### 基本使用

1. **放置机器**：将物品复制机方块放置在想要的位置
2. **输入物品**：在机器顶部放置漏斗，将需要复制的物品放入漏斗
3. **输出物品**：在机器底部放置漏斗或容器（如箱子），接收复制的物品
4. **触发复制**：给物品复制机提供红石信号（红石块、红石线、拉杆等）

### 复制规则

- **默认复制数量**：64个物品
- **铁块特殊效果**：如果在物品复制机上方放置铁块，复制数量会减1（最小为1）
  - 例如：默认复制64个，上方有铁块时复制63个
  - 如果配置为1，上方有铁块时仍然复制1个

### 配置文件

配置文件位置：`config/copyitem-common.toml`

```toml
#The amount of items to copy when the machine is activated
# Default: 64
# Range: 1 ~ 1024
copyAmount = 64
```

**配置说明**：
- `copyAmount`：复制数量，范围 1-1024，默认 64

### 命令

模组提供以下命令：

- `/copyitem reset`：重新加载配置文件

**使用方法**：
1. 修改配置文件中的 `copyAmount` 值
2. 在游戏中执行 `/copyitem reset` 命令
3. 新的配置值会立即生效

## 开发说明

### 环境要求

- Java 21
- NeoForge 21.1.215
- Minecraft 1.21.1

### 编译步骤

1. **克隆项目**：
   ```bash
   git clone <repository-url>
   cd copyitem-template-1.21.1
   ```

2. **刷新依赖**（如果需要）：
   ```bash
   gradlew --refresh-dependencies
   ```

3. **编译项目**：
   ```bash
   gradlew build
   ```

4. **获取 JAR 文件**：
   编译成功后，JAR 文件位于：`build/libs/copyitem-1.0.0.jar`

### 开发工具

推荐使用以下 IDE：
- IntelliJ IDEA（推荐）
- Eclipse

### 常用 Gradle 命令

```bash
# 编译项目
gradlew build

# 清理构建文件
gradlew clean

# 刷新依赖
gradlew --refresh-dependencies

# 运行客户端（测试）
gradlew runClient

# 运行服务端（测试）
gradlew runServer
```

### 项目结构

```
src/main/
├── java/com/copy_item/item/
│   ├── CopyItem.java              # 主模组类
│   ├── Config.java                # 配置类
│   ├── CopyItemBlock.java         # 方块类
│   ├── CopyItemBlockEntity.java   # 方块实体类
│   ├── CopyItemCommand.java       # 命令类
│   ├── CopyItemConfigHandler.java # 配置处理器
│   ├── CopyItemTickHandler.java   # tick处理器
│   └── CopyItemBlockEventHandler.java # 方块事件处理器
├── resources/
│   ├── assets/copyitem/
│   │   ├── blockstates/   # 方块状态
│   │   ├── models/        # 模型
│   │   ├── textures/      # 纹理
│   │   └── lang/          # 语言文件
│   └── data/copyitem/
│       └── loot_tables/   # 掉落表
```

### 模组信息

- **模组 ID**：copyitem
- **模组名称**：物品复制机 (Item Copier)
- **版本**：1.0.0
- **作者**：mcjava20
- **Minecraft 版本**：1.21.1
- **NeoForge 版本**：21.1.215

## 许可证

本模组使用 NeoForge MDK 模板，遵循相关许可证。

## 映射名称

默认情况下，MDK 配置使用 Mojang 官方映射名称来命名 Minecraft 代码库中的方法和字段。这些名称受特定许可证保护。所有模组开发者都应注意此许可证。有关最新的许可证文本，请参考映射文件本身或此处的参考副本：
https://github.com/NeoForged/NeoForm/blob/main/Mojang.md

## 其他资源

- 社区文档：https://docs.neoforged.net/
- NeoForge Discord：https://discord.neoforged.net/

# Discord tickets

_[Majksa Commons](//github.com/majksa-commons)_

<p>
    <a href="//github.com/majksa-commons/discord-tickets/releases"><img src="https://img.shields.io/github/v/release/majksa-commons/discord-tickets"></a>
    <a href="https://jitpack.io/#majksa-commons/discord-tickets"><img src="https://img.shields.io/jitpack/v/majksa-commons/discord-tickets"></a>
    <a href="//github.com/majksa-commons/discord-tickets/commits/main"><img src="https://img.shields.io/github/last-commit/majksa-commons/discord-tickets"></a>
    <a href="//github.com/majksa-commons/discord-tickets/releases"><img src="https://img.shields.io/github/downloads/majksa-commons/discord-tickets/total"></a>
    <a href="//github.com/majksa-commons/discord-tickets/blob/main/LICENSE.md"><img src="https://img.shields.io/github/license/majksa-commons/discord-tickets"></a>
    <a href="//github.com/majksa-commons/discord-tickets"><img src="https://img.shields.io/github/languages/code-size/majksa-commons/discord-tickets"></a>
    <a href="//github.com/majksa-commons/discord-tickets/issues"><img src="https://img.shields.io/github/issues-raw/majksa-commons/discord-tickets"></a>
    <a href="//java.com"><img src="https://img.shields.io/badge/java-8-orange"></a>
</p>

Java framework improving your experience developing any project in java.

## Summary

1. [Installation](#installation)
    1. [Gradle](#gradle)
    2. [Maven](#maven)
2. [How to use](#how-to-use)
3. [Built With](#built-with)
4. [Authors](#authors)
5. [License](#license)

## Installation

Make sure to replace `%version%` with the latest version number, or a commit hash, e.g. `1.0.0`. You can find this
library [HERE](https://jitpack.io/#majksa-commons/discord-tickets)

### Maven

Register the repository

```xml

<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```

Now add the dependency itself

```xml

<dependency>
    <groupId>com.github.majksa-commons</groupId>
    <artifactId>discord-tickets</artifactId>
    <version>%version%</version>
</dependency>
```

### Gradle

Register the repository

```gradle
repositories {
    maven { url 'https://jitpack.io' }
}
```

Now add the dependency itself

```gradle
dependencies {
    implementation 'com.github.majksa-commons:discord-tickets:%version%'
}
```

## How to use

First you need to create a Ticketer object. The only option currently is to have it in a Category, but as soon as JDA
will add support for Threads, we will also add it!
Secondly, you need to wrap it inside a manager.
And finally, if you want this to happen automatically, you can choose from the 3 provided controllers:
- ButtonController
- SlashCommandController
- EmojiController

Example usage:
```java
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.interactions.components.Button;

import javax.annotation.Nonnull;
import java.awt.*;

class Test {

    public static void startTickets(@Nonnull TextChannel channel) {
        final CategoryTicketer ticketer = new CategoryTicketer(Objects.requireNonNull(channel.getParent()));
        final Tickets manager = new Tickets(ticketer);
        manager.setFormat("ticket-{member}");
        final ButtonController controller = new ButtonController(manager, channel, new MessageBuilder().append("My epic message"), Button.primary("ticket-button", "Open ticket"));
        controller.start();
    }

}
```

## Built With

* [Java 8](https://java.com)

## Authors

* [Majksa (@maxa-ondrej)](https://github.com/maxa-ondrej)

## License

This project is licensed under the GPL-3.0 License - see the [LICENSE](LICENSE) file for details
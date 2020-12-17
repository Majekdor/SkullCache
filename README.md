# SkullCache

SkullCache is an API that allows plugin developers to easily cache player skulls for easier access later. Getting player skulls, especially from offline players,
takes an annoying amount of time and can create large lag spikes for online players. With SkullCache, you can cache multiple skulls at one time, or just one skull, 
either way it will be easier to access the skull(s) later in the plugin.  

An example of an application of this API is in a chest shop plugin that displays offline player's shops in a GUI via player heads. When the plugin has to fetch all of
those heads that aren't stored in memory it creates a large lag spike for online players. With the heads stored in memory, it is easy for the chest shop plugins to 
display the heads and eliminate lag.

## Usage
Using the SkullCache library is incredibly easy. Skulls can be cached and accessed from a given UUID, Player, or OfflinePlayer. If you need this to use this API, then
you probably need to access a lot of player skulls at the same time. Ideally, all skulls you're going to need should be cached on server startup, and you should never
cache more than one skull at runtime (in a command executor, for example). Adding a lot of skulls to the cache at runtime will result in a significant slowdown, or break
the command entirely. The ```cacheSkulls()``` method runs asynchronously, and if you try to get a lot of skulls that are not in cache with ```getSkulls()``` there will be 
a significant lag spike. However, if you only need to fetch one skull at runtime, it will execute with minimal lag and be cached for future use. I recommend caching all the 
skulls you think you'll need when the server starts, and if that list changes (someone adding a shop in a chest shops plugin, for example) then add skulls to cache/remove 
skulls from cache one at a time.

### Caching Skulls
Skulls can be cached one at a time or passed in using an array. If you wanted to cache 3 player's skulls using their UUIDs, you would do something like this:
```java
UUID[] uuids = new UUID[]{
        UUID.fromString("ca2d5464-e09a-45c2-9ac9-54cba9b34801"),
        UUID.fromString("fe365af3-9d84-44e5-befa-63dd244a2697"),
        UUID.fromString("dc1ad102-b4f9-4190-b662-2bc4abc56339")
};
SkullCache.cacheSkulls(uuids);
```

If you wanted to cache only one skull using a UUID, all you would have to do is:
```java
SkullCache.cacheSkull(UUID.frommString("ca2d5464-e09a-45c2-9ac9-54cba9b34801"));
```

### Accessing Cached Skulls
Skulls stored in memory can be fetched one at a time or as an array. If you wanted to fetch an array of skull ItemStacks from UUIDs, you would do something like this:
```java
UUID[] uuids = new UUID[]{
        UUID.fromString("ca2d5464-e09a-45c2-9ac9-54cba9b34801"),
        UUID.fromString("fe365af3-9d84-44e5-befa-63dd244a2697"),
        UUID.fromString("dc1ad102-b4f9-4190-b662-2bc4abc56339")
};
ItemStack[] skulls = SkullCache.getSkulls(uuids);
```

If you only wanted to fetch one skull from a UUID all you would have to do is:
```java
ItemStack skull = SkullCache.getSkull(UUID.fromString("ca2d5464-e09a-45c2-9ac9-54cba9b34801"));
```

### Flushing Skulls
SkullCache also has a built in way to flush skulls from memory that are not being used. If you wanted to flush all skulls that haven't been cached or accessed in 3 days, 
you would do this:
```java
SkullCache.flush(259200000); // The method accepts a number in milliseconds | 259200000ms = 3 days
```

## Installation
If you want to use SkullCache, you can either copy and paste the class from [GitHub](https://github.com/Majekdor/SkullCache/blob/master/src/main/java/dev/majek/skullcache/SkullCache.java)
or install the API using Maven.

## Installation Using Maven
Currently the API is available using Maven from Jitpack.

First, add the repository and dependencry to your ```pom.xml```  
```xml
<repositories>
    ...
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
    ...
</repositories>
```   
```xml
<dependencies>
    ...
    <dependency>
        <groupId>com.github.Majekdor</groupId>
        <artifactId>SkullCache</artifactId>
        <version>1.0.0</version>
    </dependency>
    ...
</dependencies>
```
Current version:  [![](https://jitpack.io/v/Majekdor/SkullCache.svg)](https://jitpack.io/#Majekdor/SkullCache)


Now that you have those, use ```maven-shade-plugin``` or ```maven-assembler-plugin``` to put the API in your jar file.

### With ```maven-shade-plugin```
Add this to your ```pom.xml```:
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-shade-plugin</artifactId>
            <version>2.4.3</version>
            <executions>
                <execution>
                    <phase>package</phase>
                    <goals>
                          <goal>shade</goal>
                    </goals>
                </execution>
            </executions>
      </plugin>
    </plugins>
</build>
```

### With ```maven-assembly-plugin```
Add this to your ```pom.xml```:
```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-assembly-plugin</artifactId>
            <version>3.0.0</version>
            <configuration>
                <descriptorRefs>
                    <descriptorRef>jar-with-dependencies</descriptorRef>
                </descriptorRefs>
            </configuration>
            <executions>
                <execution>
                    <id>make-assembly</id> <!-- this is used for inheritance merges -->
                    <phase>package</phase> <!-- bind to the packaging phase -->
                    <goals>
                        <goal>single</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

## Donate
All donations of any amount are appreciated! If you want to donate use [PayPal](https://www.paypal.com/paypalme/majekdor)




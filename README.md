<p align="center">
  <img src="https://i.imgur.com/HjYXerA.png" alt="FastMud logo" width="280"><br>
  <b>FastMud</b> is a lightweight and efficient plugin to turn dirt into mud<br>without using glass bottle. Save your time!
</p>
<br>

> [!CAUTION]
> **Be very careful with clone projects, some may contain MALWARE!**\
> You should be suspicious if you find _DLL_, _EXE_, or _BAT_ files.

<br>

## Motivation

**Maybe you've already thought the following:**

> "If glass bottles can turn dirt into mud, then why doesn't a bucket of water work? Wouldn't it make more sense if running water could make mud? 🤔"

It was after thinking a lot about this logical inconsistency, as well as spending a lot of time with glass bottles, that I decided to create this plugin and share it with you!

However, lakes generated around the world WILL NOT AFFECT underwater dirts! The reason is the overwhelming loss of performance, although it is a cool idea it does not seem feasible. [See more details here](https://github.com/d3cryptofc/FastMud/issues/1).

## Disclaimer

We do not intend to release corrections for previous versions of the plugin. So, please always stay updated with the latest release.

## 🎉 Features ([screenshots here](https://modrinth.com/plugin/fastmud/gallery))

- When player uses water bucket.
- When water flows.
- When block is placed under water.
- When dispenser dispenses water.
- When block fade to water (ex: ice block).

## 📜 Permissions

### Command
- `fastmud.command.*`: Allows you use any fastmud command
- `fastmud.command.fastmud`: Allows you to see and use: **/fastmud**
- `fastmud.command.reload`: Allows you to use: **/fastmud reload**

### Events
- `fastmud.event.*`: Allows every available event permission to player
- `fastmud.event.place_dirt_over_water`: Allows player to place dirt over water to turn dirt into mud

## ⚙️ Configuration ([`config.yml`](/src/main/resources/config.yml))

```yaml
# Feature settings.
when:
  # When player use water buycket over dirt.
  water_bucket_over_dirt:
    enabled: true

  # When running water over dirt.
  water_flow_over_dirt:
    enabled: true

  # When place dirts over water.
  place_dirt_over_water:
    enabled: true

  # When block fade to water over dirt (like ice).
  block_fade_to_water_over_dirt:
    enabled: true

  # When disspenser dispenses water over dirt.
  dispense_water_over_dirt:
    enabled: true
```

## ⚡️ Compatibility

Mud was officially introduced in Minecraft version 1.19, and we expect this plugin to be compatible with version 1.19+.
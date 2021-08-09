[Back to README](/README.md)
# Datapack Configurables

## Index

 - [Datapack structure](#structure)
 - [General configuration](#configuring-general-settings)
 - [Enchantment configuration](#using-the-datapack-to-configure-universal-settings-for-individual-enchantments)
 - [Individual enchantment attributes]()

---
## Structure
The general hierarchy of the datapack is as follows:
```
data
 └ extra_enchants
    └ enchantment_base
       └ COMMON ENCHANTMENT CONFIGS GO HERE
    └ enchantment_configuration
       └ INDIVIDUAL ENCHANTMENT AND GENERAL CONFIGS GO HERE
```
---
## Configuring general settings

There are only a few general settings that are configurable at the moment. This file is named `general_configuration.json`, and is in `extra_enchants/enchantment_configuration` folder. Below is the default general configuration file.
```json
{
  "directly_enchant_elytra": "true",
  "directly_enchant_horse_armor": "true",
  "directly_enchant_shields": "true",
  "directly_enchant_snowballs": "false",
  "extended_trident_enchants": "true",
  "exposed_armor_mult": 0.9,
  "exposed_damage_mult": 1.1,
  "exposed_lightning_damage_mult": 1.5
}
```
`directly_enchant_item` - allows the item to be enchanted in an enchantment table.</br>
`extended_trident_enchants` - allows tridents to have damaging enchantments such as Sharpness or Smite.</br>
`exposed_armor_mult` - raw target armor multiplier with exposed applied. Default: 0.9</br>
`exposed_damage_mult` - raw target incoming damage multiplier with exposed applied. Default: 1.1</br>
`exposed_lightning_damage_mult` - extra damage multiplier with exposed applied, on lightning strikes. Default: 1.5

---

## Using the datapack to configure universal settings for individual enchantments

Common attributes, such as type, availability, rarity, level, etc are configurable for each enchantment. Every enchantment has its own .json file associated with this data. </br>
The `.json` files must be placed in the `extra_enchants/enchantment_base` folder. Unlike individual configuration, all fields are required. The files are formatted as follows:
```json
{
  "type": "scalable/fixed",
  "available": "true/false",
  "rarity": "common/uncommon/rare/very_rare",
  "min_power": "an integer",
  "min_power_delta": "an integer",
  "max_power": "an integer",
  "max_power_delta": "an integer",
  "max_level": "an integer",
  "is_curse": "true/false",
  "is_treasure": "true/false",
  "incompatible_enchants": [
    "enchantment 1",
    "enchantment 2",
    "etc"
  ]
}
```
`type` specifies whether the minimum/maximum enchantment power required scales with level. It accepts the two following fields: </br>
 * `scalable` - specifies that the enchantment power requirements will scale with level
 * `fixed` - specifies that any level of the enchantment can roll within its accepted power range

`available` specifies whether the enchantment will be rollable via any kind of random process. </br>
`rarity` specifies the predetermined weight for the enchantment. These use vanilla weights, and the values the file accepts are as follows:  </br>
 * `common` - corresponds to Enchantment.Rarity.COMMON, with a weight of 10
 * `uncommon` - corresponds to Enchantment.Rarity.UNCOMMON, with a weight of 5
 * `rare` - corresponds to Enchantment.Rarity.RARE, with a weight of 2
 * `very_rare` - corresponds to Enchantment.Rarity.VERY_RARE, with a weight of 1

Enchantment power determines the minimum and maximum levels at which a particular enchantment can appear, and are affected by tool enchantability.</br>
For `fixed` type:
 * `min_power_delta` and `max_power_delta` are not required fields
 * `min_power` determines the lowest enchantment power required for the enchantment to appear.
 * `max_power` determines the highest enchantment power required for the enchantment to appear.

For `scalable` type:
 * `min_power_delta` and `max_power_delta` are required fields
 * `min_power` determines the lowest enchantment power required for the first level of the enchantment to appear.
 * `max_power` determines the highest enchantment power at which the first level of the enchantment can appear.
 * For levels greater than one, the minimum enchantment power will be determined by the formula `min_power + min_power_delta * (level - 1)`
 * For levels greater than one, the maximum enchantment power will be determined by the formula `max_power + max_power_delta * (level - 1)`

By default, every enchantment, scalable or fixed, will have a maximum power of 50 at its highest enchantment level. 
   
`max_level` specifies the enchantment's max level.</br>
`is_curse` specifies whether an enchantment ought to be considered a curse. Curses cannot be removed via any means.</br>
`is_treasure` specifies whether an enchantment ought to be considered a treasure. Treasure enchantments cannot be obtained via the enchantment table.</br>
`incompatible_enchants` specifies all enchantments that are not compatible with this enchantment. For two enchantments to be compatible, neither enchantment can have the other in their `incompatible_enchants` field. These take custom strings mapped to enchantments, and a [full list can be found here](enchantment_mappings.md).

---

## Using the datapack to configure attributes for individual enchants
Specific attributes, such as reach distance for Architect or arrow velocity boost from Arrow Speed, are all configurable. Individual enchantments will be located in the `extra_enchants/enchantment_configuration` folder with the `general_configuration` file. Unlike common enchantment configuration, not all fields need to be included. Here is an example:

```json
{
  "base_explosion_power": 1.0,
  "in_ground_scale": 0.5
}
```

A full list of all enchantments and their respective configurable options may be found [here](data_driven.md).
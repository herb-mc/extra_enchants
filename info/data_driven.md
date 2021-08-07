[Back to datapack info](datapack.md)
# Enchantment List

- [Ace](#ace)
- [Antigravity](#antigravity)
- [Architect](#architect)
- [Arrow Speed](#arrow-speed)
- [Barbaric](#barbaric)
- [Berserk](#berserk)
- [Blaze Affinity](#blaze-affinity)
- [Boosting](#boosting)
- [Bounding](#bounding)
- [Cleaving](#cleaving)
- [Core of Neptune](#core-of-neptune)
- [Core of Purity](#core-of-purity)
- [Core of the Blood God](#core-of-the-blood-god)
- [Core of the Warp](#core-of-the-warp)
- [Curse of Instability](#curse-of-instability)
- [Dextrous](#dextrous)
- [Dwarven](#dwarven)
- [Ender](#ender)
- [Explosive](#explosive)
- [Exposing](#exposing)
- [Featherweight](#featherweight)
- [Fireproof](#fireproof)
- [Launching](#launching)
- [Leaping](#leaping)
- [Lifesteal](#lifesteal)
- [Lunging](#lunging)
- [Magnetic](#magnetic)
- [Night Vision](#night-vision)
- [Nimble](#nimble)
- [Propelling](#propelling)
- [Psychic](#psychic)
- [Reflecting](#reflecting)
- [Reflex](#reflex)
- [Sharpshooter](#sharpshooter)
- [Shock Resistant](#shock-resistant)
- [Slimey](#slimey)
- [Sniper](#sniper)
- [Soulbound](#soulbound)
- [Stalwart](#stalwart)
- [Steadfast](#steadfast)
- [Surface Skimmer](#surface-skimmer)
- [Swiftness](#swiftness)
- [Terraforming](#terraforming)
- [Testing](#testing)
- [Thunderbolt](#thunderbolt)
- [Tough](#tough)
- [Turbo](#turbo)
- [Warding](#warding)
- [Weighted](#weighted)
- [Windstep](#windstep)
---
[Back to top](#enchantment-list)

## *Ace*
`damage_reduction_mult` - value of the multiplier in the damage reduction formula - see [Ace damage reduction formula](index.md#ace). Default: 1.0</br>
`extra_arrow_damage` - additional arrow damage per level while in flight. Default: 0.5</br>
`extra_melee_damage` - additional melee damage per level while in flight. Default: 1.0</br>
`extra_trident_damage` - additional trident damage per level while in flight. Default: 1.5
---
[Back to top](#enchantment-list)

## *Architect*
`range_boost` - amount of placement range to be added per level. Default: 1.0

---
[Back to top](#enchantment-list)
## *Arrow Speed*
`velocity_mult` - additional proportion of velocity to be added to fired arrows. Default: 0.15

---
[Back to top](#enchantment-list)
## *Barbaric*
`damage_mult` - amount to multiply missing armor by when calculating attack damage multiplier. Default: 0.04

---
[Back to top](#enchantment-list)
## *Berserk*
`base_mult` - Default: 0.2</br>
`log_mult` - Default: 0.629</br>
`log_base` - Default: 10</br>
Values are used in added damage calculation: `base_mult * log10(level) / log10(log_base) + base_mult`

---
[Back to top](#enchantment-list)

## *Blaze Affinity*
`incoming_damage_mult` - multiplier of incoming damage while on fire. Default: 0.95</br>
`extra_damage_mult` - proportion of additional damage to be added while on fire. Default: 0.1
---
[Back to top](#enchantment-list)

## *Boosting*
`base_duration` - duration in ticks, per level, of speed boost. Default: 20</br>
`cooldown` - length in ticks of cooldown. Default: 60</br>
`speed_boost` - proportion of additional speed per level. Default: 0.4
---
[Back to top](#enchantment-list)

## *Bounding*
`jump_boost` - proportion of additional jump boost per level. Default: 0.1

---
[Back to top](#enchantment-list)

## *Cleaving*
`armor_ignored` - proportion of armor ignored per level. Default: 0.18

---
[Back to top](#enchantment-list)

## *Core of Neptune*
`breath_gain_mod` - Default: 2</br>
`breath_gain_rand_range` - Default: 16</br>
Breath gained on land for a tick is given by the formula `random_int[0, breath_gain_rand_range) % breath_gain_mod`
`breath_loss_rate` - extra breath lost per tick while underwater. Default: 3 </br>
`trident_damage_boost` - damage to be added to thrown tridents. Default: 8.0 </br>
`water_drag` - drag multiplier applied to velocity while in water. Default: 0.97 </br>
`air_penalty_threshold` - the amount of air above which damage, attack speed, and movement speed are steadily decreased. Default: 120</br>
`attack_damage_max_penalty` - the proportion of damage decreased at full air. Default: 0.3 </br>
`attack_speed_max_penalty` - the proportion of attack speed decreased at full air. Default: 0.3 </br>
`move_speed_max_penalty` - the proportion of movement speed decreased at full air. Default: 0.2 </br>
`air_buff_threshold` - the amount of air below which damage, attack speed, and movement speed are steadily increased. Default: 90</br>
`attack_damage_max_buff` - the proportion of damage increased at no air. Default: 0.3 </br>
`attack_speed_max_buff` - the proportion of attack speed increased at no air. Default: 0.3 </br>
`move_speed_max_buff` - the proportion of movement speed increased at no air. Default: 0.2 </br>
---
[Back to top](#enchantment-list)

## *Core of Purity*
`base damage` - the amount that most damage values are set to while equipped. Default: 0.0</br>
`damage_mult` - amount incoming damage is multiplied by while above the health threshold. Default: 0.3</br>
`damage_mult_threshold` - the portion of health required for damage reduction to apply. Default: 0.6
`health_mult` - proportion of health added while equipped. Default: 1.0
`speed_penalty` - proportion speed is decreased by while equipped. Default: -0.1
---
[Back to top](#enchantment-list)

## *Core of the Blood God*
---
[Back to top](#enchantment-list)

## *Core of the Warp*
---
[Back to top](#enchantment-list)

## *Curse of Instability*
---
[Back to top](#enchantment-list)

## *Dextrous*
---
[Back to top](#enchantment-list)

## *Dwarven*
---
[Back to top](#enchantment-list)

## *Ender*
---
[Back to top](#enchantment-list)

## *Explosive*
---
[Back to top](#enchantment-list)

## *Exposing*
---
[Back to top](#enchantment-list)

## *Featherweight*
---
[Back to top](#enchantment-list)

## *Fireproof*
---
[Back to top](#enchantment-list)

## *Launching*
---
[Back to top](#enchantment-list)

## *Leaping*
---
[Back to top](#enchantment-list)

## *Lifesteal*
---
[Back to top](#enchantment-list)

## *Lunging*
---
[Back to top](#enchantment-list)

## *Magnetic*
---
[Back to top](#enchantment-list)

## *Night Vision*
---
[Back to top](#enchantment-list)

## *Nimble*
`draw_mult` - proportional decrease in draw speed per level. Default: -0.1

---
[Back to top](#enchantment-list)

## *Propelling*
---
[Back to top](#enchantment-list)

## *Psychic*
---
[Back to top](#enchantment-list)

## *Reflecting*
---
[Back to top](#enchantment-list)

## *Reflex*
---
[Back to top](#enchantment-list)

## *Sharpshooter*
`extra_arrow_damage` - amount of damage to be added to fired arrows while active. Default: 1.0</br>
`extra_trident_damage` - amount of damage to be added to thrown tridents while active. Default: 3.0</br>
`fov_mod` - amount FOV is multiplied by while active. Default: 0.33
---
[Back to top](#enchantment-list)

## *Shock Resistant*

---
[Back to top](#enchantment-list)

## *Slimey*

---
[Back to top](#enchantment-list)

## *Sniper*
---
[Back to top](#enchantment-list)

## *Soulbound*
---
[Back to top](#enchantment-list)

## *Stalwart*
---
[Back to top](#enchantment-list)

## *Steadfast*
---
[Back to top](#enchantment-list)

## *Surface Skimmer*
---
[Back to top](#enchantment-list)

## *Swiftness*
---
[Back to top](#enchantment-list)

## *Terraforming*
---
[Back to top](#enchantment-list)

## *Testing*
---
[Back to top](#enchantment-list)

## *Thunderbolt*
---
[Back to top](#enchantment-list)

## *Tough*
---
[Back to top](#enchantment-list)

## *Turbo*
---
[Back to top](#enchantment-list)

## *Warding*
---
[Back to top](#enchantment-list)

## *Weighted*
---
[Back to top](#enchantment-list)

## *Windstep*
---
  [Back to top](#enchantment-list)
## *Withering*

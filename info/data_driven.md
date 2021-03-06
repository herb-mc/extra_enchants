[Back to datapack info](datapack.md)
# Enchantment List

- [Ace](#ace)
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
- [Explosive](#explosive)
- [Exposing](#exposing)
- [Featherweight](#featherweight)
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
- [Stalwart](#stalwart)
- [Steadfast](#steadfast)
- [Swiftness](#swiftness)
- [Terraforming](#terraforming)
- [Tough](#tough)
- [Turbo](#turbo)
- [Warding](#warding)
- [Weighted](#weighted)
- [Windstep](#windstep)
---
[Back to top](#enchantment-list)

## *Ace*

`damage_reduction_mult` - value of the multiplier in the damage reduction formula - see [Ace damage reduction formula](index.md#ace). Default: 1.0</br>
`arrow_damage_boost` - additional arrow damage per level while in flight. Default: 0.5</br>
`melee_damage_boost` - additional melee damage per level while in flight. Default: 1.0</br>
`trident_damage_boost` - additional trident damage per level while in flight. Default: 1.5

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
`damage_mult` - proportion of additional damage to be added while on fire. Default: 0.1

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
Breath gained on land for a tick is given by the formula `random_int[0, breath_gain_rand_range) % breath_gain_mod`</br>
`breath_loss_rate` - extra breath lost per tick while underwater. Default: 3 </br>
`trident_damage_boost` - damage to be added to thrown tridents. Default: 8.0 </br>
`water_drag` - drag multiplier applied to velocity while in water. Default: 0.97 </br>
`air_penalty_threshold` - the amount of air above which damage, attack speed, and movement speed are steadily decreased. Default: 120</br>
`attack_damage_max_penalty` - the proportion of damage decreased at full air. Default: 0.3 </br>
`attack_speed_max_penalty` - the proportion of attack speed decreased at full air. Default: 0.3 </br>
`move_speed_max_penalty` - the proportion of movement speed decreased at full air. Default: 0.2 </br>
`air_boost_threshold` - the amount of air below which damage, attack speed, and movement speed are steadily increased. Default: 90</br>
`attack_damage_max_boost` - the proportion of damage increased at no air. Default: 0.3 </br>
`attack_speed_max_boost` - the proportion of attack speed increased at no air. Default: 0.3 </br>
`move_speed_max_boost` - the proportion of movement speed increased at no air. Default: 0.2 </br>

---
[Back to top](#enchantment-list)

## *Core of Purity*

`base damage` - the amount that most damage values are set to while equipped. Default: 0.0</br>
`damage_mult` - amount incoming damage is multiplied by while above the health threshold. Default: 0.3</br>
`damage_mult_threshold` - the portion of health required for damage reduction to apply. Default: 0.6</br>
`health_mult` - proportion of health added while equipped. Default: 1.0</br>
`speed_penalty` - proportion speed is decreased by while equipped. Default: -0.1

---
[Back to top](#enchantment-list)

## *Core of the Blood God*

`armor_penalty` - the proportion armor is decreased by while equipped. Default: -0.2</br>
`attack_boost` - proportion total melee damage is increased by while equipped. Default: 0.15</br>
`crit_chance` - the chance at which the user will receive critical hits. Default: 0.25</br>
`crit_mult` - the multiplier applied to critical damage. Default: 1.8</br>
`health_boost` - proportion of health to be increased by while equipped. Default: 1.0

---
[Back to top](#enchantment-list)

## *Core of the Warp*

`health_penalty` - proportion of health to be decreased by while equipped. Default: -0.5</br>
`incoming_damage_mult` - multiplier for all incoming damage. Default: 0.6</br>
`speed_boost` - proportion speed is increased by while equipped. Default: 0.15</br>
`teleport_chance` - Chance that a teleport try will be initiated while in corresponding biomes. Default: 0.05</br>
`teleport_range` - Side length of the cube centered 8 blocks below the player that the teleport try will search for a valid teleport location in. Default: 16</br>
`teleport_tries` - Number of times the game will attempt to search for a valid teleport space upon initiation a teleport. Default: 16

---
[Back to top](#enchantment-list)

## *Curse of Instability*

`teleport_range` - Side length of the cube centered 8 blocks below the player that the teleport try will search for a valid teleport location in. Default: 16</br>
`teleport_tries` - Number of times the game will attempt to search for a valid teleport space upon initiation a teleport. Default: 16

---
[Back to top](#enchantment-list)

## *Dextrous*

`attack_speed_boost` - proportion by which attack speed will be increased. Default: 0.1

---
[Back to top](#enchantment-list)

## *Dwarven*

`active_range` - distance within which ores will be searched for. Default: 5.5</br>
`always_active` - if true, enchantment is active all the time. If false, only active on sneak. Default: true

---
[Back to top](#enchantment-list)

## *Explosive*

`base_explosion_power` - explosion power of fired arrows. Default: 1.0</br>
`in_ground_scaled` - multiplier of explosions created by arrows that do not hit an entity. Default: 0.5

---
[Back to top](#enchantment-list)

## *Exposing*

`base_duration` - duration in ticks, per level, applied by arrows. Default: 30

---
[Back to top](#enchantment-list)

## *Featherweight*

`fall_speed_scale` - scales the fall speed per level of Featherweight. Lower values = fall slower. Default: 1.0

---
[Back to top](#enchantment-list)

## *Launching*

`velocity_scale` - scales the velocity per level of Launching. Higher values = launch faster. Default: 1.0

---
[Back to top](#enchantment-list)

## *Leaping*

`base_velocity` - additional base jump velocity per level. Default: 0.075</br>
`extra_fall_height` - height decreased per level during fall damage calculation. Default: 1.0

---
[Back to top](#enchantment-list)

## *Lifesteal*

`base_heal_percent` - proportion of health to be stolen per level. Default: 0.3</br>
`max_heal_amount` - absolute cap on healing amount. Default: 3.0

---
[Back to top](#enchantment-list)

## *Lunging*

`jump_velocity_boost` - proportion that horizontal jump velocity is increased by per level. Default: 0.15

---
[Back to top](#enchantment-list)

## *Magnetic*

`additional_radius` - additional radius per level. Default: 1.0</br>
`base_radius` - radius at level 1 for items to be magnetized. Default: 5.0</br>
`max_velocity` - max velocity for magnetized items. Default: 5.0</br>
`velocity_scalar` - scalar for acceleration of magnetized items per level. The higher the value, the faster magnetized items will accelerate. Default: 0.25

---
[Back to top](#enchantment-list)

## *Night Vision*

`always_active` - if true, enchantment is active all the time. If false, only active on sneak. Default: false

---
[Back to top](#enchantment-list)

## *Nimble*

`draw_mult` - proportional decrease in draw speed per level. Default: -0.1

---
[Back to top](#enchantment-list)

## *Propelling*

`accel_velocity` - base velocity at which propelling will begin accelerating. Default: 0.6</br>
`additional_accel_velocity` - additional minimum acceleration velocity. Default: 0.06</br>
`min_accel` - absolute minimum acceleration multiplier. Default: 0.015</br>
`additional_accel` - acceleration to be added per level. First level will also add this amount. Default: 0.01</br>
`penalty_start_height` - height at which additional acceleration penalties start to be applied. Default: 128.0</br>
`critical_height` - height above which the only acceleration applied is the minimum acceleration. Default: 256.0

---
[Back to top](#enchantment-list)

## *Psychic*

`active_range` - base distance to apply the exposed effect. Default: 6.0</br>
`always_active` - if true, enchantment is active all the time. If false, only active on sneak. Default: false

---
[Back to top](#enchantment-list)

## *Reflecting*

`base_reflected_velocity` - multiplier for reflected projectile velocity at level 1. Default: 0.5</br>
`additional_reflected_velocity` - additional velocity multiplier per level. Default: 0.1

---
[Back to top](#enchantment-list)

## *Reflex*

`ready_ticks` - amount of time to ready a shield with reflex, in ticks. Default: 0

---
[Back to top](#enchantment-list)

## *Sharpshooter*

`arrow_damage_boost` - amount of damage to be added to fired arrows while active. Default: 1.0</br>
`trident_damage_boost` - amount of damage to be added to thrown tridents while active. Default: 3.0</br>
`fov_mod` - amount FOV is multiplied by while active. Default: 0.33

---
[Back to top](#enchantment-list)

## *Shock Resistant*

`base_damage_multiplier` - base multiplier for elytra damage at level 1. Default: 0.8 </br>
`damage_mult_scale` - scales the damage reduction function. Higher values cause damage reduction to increase faster with level. Default: 1.5

---
[Back to top](#enchantment-list)

## *Slimey*

`slipperiness` - slipperiness value while equipped. Lower values results in higher friction. Defualt: 1.0</br>

---
[Back to top](#enchantment-list)

## *Sniper*

`draw_mult` - proportional increase in draw speed per level. Default: 1.0</br>
`base_damage` - extra damage per level. Default: 2.0</br>
`velocity_mult` - proportion of extra velocity per level. Default: 0.1

---
[Back to top](#enchantment-list)

## *Stalwart*

`cooldown` - shield cooldown, in ticks. Default: 60

---
[Back to top](#enchantment-list)

## *Steadfast*

`speed_mult` - proportional speed increase per level while drawing bow/crossbow. Default: 1.0

---
[Back to top](#enchantment-list)

## *Swiftness*

`speed_mult` - proportional speed increase per level. Default: 0.1

---
[Back to top](#enchantment-list)

## *Terraforming*

`additional_tool_speed` - amount of additional tool speed. Default: 58</br>
`drop_items` - whether items should drop from mined blocks. Default: false

---
[Back to top](#enchantment-list)

## *Tough*

`damage_reduction` - amount of damage reduction per level. Default: 0.03

---
[Back to top](#enchantment-list)

## *Turbo*

`lifetime_decrement` - additional amount lifetime is decreased per tick. Default: 2</br>
`speed_mult` - multiplier for velocity provided by fireworks. Default: 1.5

---
[Back to top](#enchantment-list)

## *Warding*

`range` - range in which phantoms will be scared away. Default: 8.0

---
[Back to top](#enchantment-list)

## *Weighted*

`attack_damage_boost` - proportion of attack damage increase per level. Default: 0.2</br>
`attack_speed_penalty` - proportion of attack speed decrease per level. Default: -0.15

---
[Back to top](#enchantment-list)

## *Windstep*

`step_height_boost` - extra step height per level. Default: 0.4
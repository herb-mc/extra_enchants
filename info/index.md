[Back to README](/README.md)
# Enchantment List

All of the information here is only applicable to unmodified enchantments. Most of the values here can be tweaked via datapack.

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
- [Magic Guard](#magic-guard)
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
- [Swiftness](#swiftness)
- [Terraforming](#terraforming)
- [Thunderbolt](#thunderbolt)
- [Tough](#tough)
- [Turbo](#turbo)
- [Warding](#warding)
- [Weighted](#weighted)
- [Windstep](#windstep)
---
[Back to top](#enchantment-list)

## *Ace*
- Damage reduction, melee and arrow damage are increased
- Compatible equipment: Elytra
- Incompatible enchantments: Propelling, Turbo, Shock Resistant
- Max level: 3
- Rarity: Rare
### Technical description:
- Incoming damage is multiplied by a multiplier determined by the following formula:

![ace](https://latex.codecogs.com/gif.latex?damagemult%20%3D%201%20-%20%5Clog_%7B10%7D%28level%20&plus;%201%29%20*%200.25)
- Melee damage is increased by 1 per level.
- Arrow damage is increased by 0.5 per level.
- Trident damage is increased by 1.5 per level.
- Aforementioned affects are only active while in flight.
---
[Back to top](#enchantment-list)

## *Antigravity*
- Removes mined item gravity.
- Compatible equipment: Tools
- Max level: 1
- Rarity: Rare
- Type: Treasure
### Technical description:
- Dropped item entities will have no gravity.
---
[Back to top](#enchantment-list)

## *Architect*
- Increases maximum block interaction range.
- Compatible equipment: Chestplates
- Max level: 3
- Rarity: Uncommon
### Technical description:
- +1.0 range per level. Only applies to block placement/destruction, not attacking.
---
[Back to top](#enchantment-list)
## *Arrow Speed*
- Increases the velocity and damage of fired arrows.
- Compatible equipment: Bows
- Incompatible enchantments: Power
- Max level: 3
- Rarity: Common
### Technical description:
- Increases arrow velocity by 15% and adds 0.25 to the arrow's damage attribute per level. Roughly equivalent to Power at the same level.<br>
  Levels beyond 4 may result in visual bugs and quirky arrow behavior.
---
[Back to top](#enchantment-list)
## *Barbaric*
- Increases damage by an amount inversely proportional to the user's armor.
- Compatible equipment: Swords, Axes
- Incompatible enchants: Lifesteal
- Max level: 1
- Rarity: Rare
### Technical description:
- The damage increase is a multiplier applied to the total damage. The value at no armor is 1.8x, with a 0.04 decrease per armor point.
---
[Back to top](#enchantment-list)

## *Berserk*
- Increases damage by an amount proportional to the amount of health lost.
- Compatible equipment: Chestplates
- Incompatible enchantments: All Protection-type enchants
- Max level: 3
- Rarity: Uncommon
### Technical description:
- Extra damage is added directly. The precise formula for determining the amount of damage added is the following:<br>

![berserk](https://latex.codecogs.com/gif.latex?extradamage%3D0.629*%5Clog_%7B10%7D%28level%29&plus;0.2)

---
[Back to top](#enchantment-list)

## *Blaze Affinity*
- Increases damage and damage reduction while on fire.
- Compatible equipment: Leggings
- Max level: 1
- Rarity: Uncommon
- Type: Treasure
### Technical description:
- +10% damage, +5% damage reduction while on fire. Does not decrease fire damage.
---
[Back to top](#enchantment-list)

## *Boosting*
- When initiating a sprint, gain a massive burst of speed. 3 second cooldown.
- Compatible equipment: Leggings
- Incompatible enchantments: Steadfast
- Max level: 3
- Rarity: Uncommon
### Technical description:
- Gain a (40 * level)% speed boost that lasts for (20 * level) ticks. After stopping a sprint or reaching the maximum time, initiates a cooldown of 60 ticks that only ticks while not sprinting.
---
[Back to top](#enchantment-list)

## *Bounding*
- Increases horse jump height.
- Compatible equipment: Horse armors
- Incompatible enchantments: Surface Skimmer, Swiftness
- Max level: 3
- Rarity: Common
### Technical description:
- Increases jump height by 10% per level.
---
[Back to top](#enchantment-list)

## *Cleaving*
- Ignores a portion of enemy armor.
- Compatible equipment: Axes
- Incompatible enchantments: Damage increasing enchantments
- Max level: 4
- Rarity: Uncommon
### Technical description:
- The amount of armor ignored scales with level, with diminishing returns. 18% of armor is ignored per level.
---
[Back to top](#enchantment-list)

## *Core of Neptune*
- The less air you have, the stronger you are. Conversely, the more air you have, the weaker you become.
- Compatible equipment: Chestplates
- Incompatible enchantments: All Core-type enchants
- Max level: 1
- Rarity: Very rare
- Type: Curse, Treasure
### Technical description:
- Air loss in water is 4x faster, while air gain outside of water is roughly 8x slower. Sets swimming drag coefficient to 0.97.<br>
  Scales attack damage/speed according to the following formula:

![nepatkmod](https://latex.codecogs.com/gif.latex?attackmod%20%3D%201%20&plus;%20%5Cbegin%7Bcases%7D%20%5Cfrac%7B120%20-%20air%7D%7B450%7D%20%26%20%5Ctext%7B%20if%20%7D%20air%20%3E%20120%5C%5C%20%5Cfrac%7B90%20-%20air%7D%7B270%7D%20%26%20%5Ctext%7B%20if%20%7D%20air%20%3C%2090%20%5Cend%7Bcases%7D)

- Scales movement speed according to the following formula:

![nepspdmod](https://latex.codecogs.com/gif.latex?speedmod%20%3D%201%20&plus;%20%5Cbegin%7Bcases%7D%20%5Cfrac%7B120%20-%20air%7D%7B900%7D%20%26%20%5Ctext%7B%20if%20%7D%20air%20%3E%20120%5C%5C%20%5Cfrac%7B90%20-%20air%7D%7B549%7D%20%26%20%5Ctext%7B%20if%20%7D%20air%20%3C%2090%20%5Cend%7Bcases%7D)

---
[Back to top](#enchantment-list)

## *Core of Purity*
- Health is doubled, damage taken is reduced when healthy, speed is slightly reduced. Damage dealt is set to 1.
- Compatible equipment: Chestplates
- Incompatible enchantments: All Core-type enchants
- Max level: 1
- Rarity: Very rare
- Type: Curse, Treasure
### Technical description:
- Doubled max health, -10% movement speed, damage dealt is set to 1 before damage calculation. Incoming damage is reduced by 70% if the user has at least 60% of their max health remaining.
---
[Back to top](#enchantment-list)

## *Core of the Blood God*
- Doubles health and increases attack damage. Armor is decreased and enemies may deal critical hits.
- Compatible equipment: Chestplates
- Incompatible enchantments: All Core-type enchants
- Max level: 1
- Rarity: Very rare
- Type: Curse, Treasure
### Technical description:
- Doubles max health, +15% attack damage, -20% armor, 25% chance totake 80% increased damage from entities.<br>
  Effect does not scale.</details>
---
[Back to top](#enchantment-list)

## *Core of the Warp*
- Health is halved, damage reduction and speed are increased.
- Compatible equipment: Chestplates
- Incompatible enchantments: All Core-type enchants
- Max level: 1
- Rarity: Very rare
- Type: Curse, Treasure
### Technical description:
- -50% max health, 40% raw damage reduction before damage calculation, +15% movement speed. 5% chance to teleport on hit in certain biomes.
---
[Back to top](#enchantment-list)

## *Curse of Instability*
- Randomly teleport when blocking.
- Compatible equipment: Shield
- Max level: 1
- Rarity: Rare
- Type: Curse, Treasure
### Technical description:
- Random teleportation on successful block. Identical to chorus fruit.
---
[Back to top](#enchantment-list)

## *Dextrous*
- Increases attack speed.
- Compatible equipment: Chestplates
- Incompatible enchantments: Weighted, Thorns, Magic Guard
- Max level: 2
- Rarity: Rare
### Technical description:
- Increases attack speed by 10% per level.
---
[Back to top](#enchantment-list)

## *Dwarven*
- Find nearby ores easily.
- Compatible equipment: Helmets
- Incompatible enchantments: Sharpshooter, Night Vision, Psychic
- Max level: 1
- Rarity: Rare
### Technical description:
- A trail of particles will emanate from the player's head towards the nearest ore-type block.
---
[Back to top](#enchantment-list)

## *Ender*
- On full charge: Teleport to arrow hit location.
- Compatible equipment: Bows
- Incompatible enchantments: Power, Infinity, Explosive
- Max level: 1
- Rarity: Rare
### Technical description:
- Changes arrow particle effect to REVERSE_PORTAL. Teleports the user to the arrow's location and inflicts the user with 2 fall damage if it hits the ground. If the arrow hits a mob, it will instead teleport the user to the mob and inflict 1 fall damage.
---
[Back to top](#enchantment-list)

## *Explosive*
- On full charge: Arrows are non-retrievable, explode on impact instead of dealing damage normally.
- Compatible equipment: Bows
- Incompatible enchantments: Power, Infinity, Ender, Sniper, Nimble
- Max level: 2
- Rarity: Rare
### Technical description:
- Changes arrow particle effect to SMOKE. The resultant explosion has a power of lvl / 2 + 0.5, if the arrow hits the ground. The explosion power will match the level on direct hits. Arrows deal damage normally during dragon perching. None of the explosions deal block damage. (This will eventually be configurable)
---
[Back to top](#enchantment-list)

## *Exposing*
- Arrows inflict glowing and exposed.
- Compatible equipment: Crossbows
- Incompatible enchantments: Thunderbolt
- Max level: 3
- Rarity: Common
### Technical description:
- Inflicts (30 * level) ticks of glowing and exposed on hit. Exposed entities take 10% more damage, and incoming damage ignores 10% of armor. Lightning damage is boosted by 50%.
---
[Back to top](#enchantment-list)

## *Featherweight*
- Fall slower depending on the level.
- Compatible equipment: Boots
- Incompatible enchantments: Feather Falling, Slimey
- Max level: 3
- Rarity: Uncommon
### Technical description:
- Divides fall acceleration by (level + 1). Divides fall distance by (level * 2) before fall damage calculation. Overridden by Slow Falling effect.
---
[Back to top](#enchantment-list)

## *Fireproof*
- Item will not burn.
- Compatible equipment: Any
- Max level: 1
- Rarity: Rare
- Type: Treasure
### Technical description:
- Dropped items with this enchantment are fireproof.
---
[Back to top](#enchantment-list)

## *Launching*
- Throwing tridents launches you forward.
- Compatible equipment: Tridents
- Max level: 3
- Rarity: Rare
### Technical description:
- Adds ((rotation unit vector) * level) to user velocity upon a successful trident throw.
---
[Back to top](#enchantment-list)

## *Leaping*
- Increases jump height.
- Compatible equipment: Boots
- Incompatible enchantments: Lunging, Windstep
- Max level: 2
- Rarity: Uncommon
### Technical description:
- Increases jump velocity by 0.07 per level. Approximately +0.5 blocks of jump height up until level 8.
---
[Back to top](#enchantment-list)

## *Lifesteal*
- Drains a small amount of health upon dealing damage.
- Compatible equipment: Weapons
- Max level: 3
- Rarity: Rare
### Technical description:
- Heals 3% of damage dealt per level. Heal amount has an absolute cap of 3.
---
[Back to top](#enchantment-list)

## *Lunging*
- Increases horizontal jump velocity
- Compatible equipment: Boots
- Incompatible enchantments: Leaping, Windstep
- Max level: 3
- Rarity: Uncommon
### Technical description:
- 15% increase per level to horizontal velocities on jump.
---
[Back to top](#enchantment-list)

## *Magic Guard*
- The user can only be damaged directly
- Compatible equipment: Chestplates
- Incompatible enchantments: Weighted, Thorns, Weighted
- Max level: 1
- Rarity: Rare
- Type: Treasure
### Technical description:
- Any damage that did not originate from an entity will be nullified. This includes fire damage, lava damage, etc. but excludes cramming, drowning, falling, starving, and void damage.
---
[Back to top](#enchantment-list)

## *Magnetic*
- While held: items will move towards the holder.
- Compatible equipment: Tools
- Max level: 3
- Rarity: Rare
- Type: Treasure
### Technical description:
- Items will move towards the user within (4.5 + level) tiles of the user. The velocity at which they move increases with level.
---
[Back to top](#enchantment-list)

## *Night Vision*
- While sneaking, the user will gain Night Vision.
- Compatible equipment: Helmets
- Incompatible enchantments: Dwarven, Sharpshooter, Psychic
- Max level: 1
- Rarity: Rare
### Technical description:
- User is given Night Vision while sneaking. This will override any current Night Vision effect.
---
[Back to top](#enchantment-list)

## *Nimble*
- Increased bow draw speed but decreased damage.
- Compatible equipment: Bow
- Incompatible enchantments: Ender, Explosive, Sniper
- Max level: 5
- Rarity: Uncommon
### Technical description:
- (15 * level) tick decrease in draw speed, (-0.2 * level) arrow damage attribute. Minimum damage is capped at 0.5.
---
[Back to top](#enchantment-list)

## *Propelling*
- While flying, the user's speed will be maintained at a minimum, which decreases with height gain. Firework use is disabled.
- Compatible equipment: Elytra
- Incompatible enchantments: Ace, Turbo, Shock Resistant
- Max level: 3
- Rarity: Rare
### Technical description:
- Firework use is disabled in-flight. While in-flight, when the user's speed goes below (0.5 + 0.05 * level), ((unit rotation vector) * (0.005 + *multiplier*)) is added to their velocity, where *multiplier* = (0.01 + 0.01 * level), and decreases above y = 128 until it eventually hits 0 at y = 256.
---
[Back to top](#enchantment-list)

## *Psychic*
- While sneaking, the nearest mob within 7 blocks the user is facing will be exposed. Ignores blocks.
- Compatible equipment: Helmets
- Incompatible enchantments: Dwarven, Sharpshooter, Night Vision
- Max level: 1
- Rarity: Rare
### Technical description:
- Any entity within 7 blocks of the user's line of sight (disregarding blocks) will receive Glowing 20 while the user is sneaking.
---
[Back to top](#enchantment-list)

## *Reflecting*
- While blocking, projectiles will be reflecting in the direction the user is facing. Reflected velocity scales with level.
- Compatible equipment: Shields
- Incompatible enchantments: Reflex, Stalwart
- Max level: 3
- Rarity: Uncommon
### Technical description:
- Upon reflecting a projectile, it is reflected with (40 + 10 * level)% of its initial velocity in the direction the user is facing
---
[Back to top](#enchantment-list)

## *Reflex*
- Shield raising is slightly faster.
- Compatible equipment: Shields
- Incompatible enchantments: Reflecting, Stalwart
- Max level: 1
- Rarity: Uncommon
### Technical description:
- Decreases shield raising time to 0 ticks,from the regular 5.
---
[Back to top](#enchantment-list)

## *Sharpshooter*
- While sneaking, the camera will zoom in 3x. Arrow damage is increased.
- Compatible equipment: Helmets
- Incompatible enchantments: Dwarven, Night Vision, Psychic
- Max level: 1
- Rarity: Rare
### Technical description:
- FOV is multiplied by 1/3. +1 damage to all fired arrows. +5 damage to thrown tridents.
---
[Back to top](#enchantment-list)

## *Shock Resistant*
- Damage taken when ramming into things with an elytra is reduced.
- Compatible equipment: Elytra
- Incompatible enchantments: Ace, Propelling
- Max level: 2
- Rarity: Uncommon
### Technical description:
- Elytra ramming damage is multiplied by the following multiplier:

![mult](https://latex.codecogs.com/gif.latex?multiplier%20%3D%20%5Cfrac%7B1%7D%7B1.5*%5Clog%20%28level%29&plus;1%7D&plus;0.2)

---
[Back to top](#enchantment-list)

## *Slimey*
- Slipperiness of blocks is massively increased.
- Compatible equipment: Boots
- Incompatible enchantments: Feather Falling, Featherweight
- Max level: 1
- Rarity: Rare
### Technical description:
- Slipperiness is set to 1.0 while equipped. (Ice/Packed Ice default to 0.98)
---
[Back to top](#enchantment-list)

## *Sniper*
- Bows take more time to draw, but deal more damage and fly faster.
- Compatible equipment: Bows
- Incompatible enchantments: Explosive, Nimble
- Max level: 2
- Rarity: Uncommon
### Technical description:
- (15 * level) ticks of extra draw time per level. Increases damage attribute by (2 * level) and velocity by (10 * level)%.
---
[Back to top](#enchantment-list)

## *Soulbound*
- Item stays in inventory after death.
- Compatible equipment: Breakable
- Incompatible enchantments: Curse of Vanishing
- Max level: 1
- Rarity: Very Rare
- Type: Treasure
### Technical description:
- Items with soulbound will be skipped during the dropAll() check. Armor and offhand items stay in their respective slots.
---
[Back to top](#enchantment-list)

## *Stalwart*
- Shield cooldown after axe hits is lowered.
- Compatible equipment: Shield
- Incompatible enchantments: Reflecting, Reflex
- Max level: 1
- Rarity: Uncommon
### Technical description:
- Shield cooldown after axe hits is lowered from 100 to 60 ticks.
---
[Back to top](#enchantment-list)

## *Steadfast*
- Decreases the weapon drawing movement penalty.
- Compatible equipment: Leggings
- Incompatible enchantments: Boosting
- Max level: 2
- Rarity: Uncommon
### Technical description:
- While drawing a bow, crossbow or trident: movement speed multiplied by (level + 1).
---
[Back to top](#enchantment-list)

## *Swiftness*
- Increases movement speed.
- Compatible equipment: Horse armors
- Incompatible enchantments: Bounding, Surface Skimmer
- Max level: 3
- Rarity: Common
### Technical description:
- Increases speed by 10% per level.
---
[Back to top](#enchantment-list)

## *Terraforming*
- Greatly increases mining speed. Broken blocks drop nothing.
- Compatible equipment: Tools
- Incompatible enchantments: Fortune, Silk Touch
- Max level: 1
- Rarity: Rare
- Type: Treasure
### Technical description:
- Increases tool speed by 58. This allows a Terraforming Diamond/Netherite Pickaxe to break stone instantly. Terraforming + Efficiency V allows Diamond/Netherite Pickaxes to instantly break some ores and deepslate.
---
[Back to top](#enchantment-list)

## *Thunderbolt*
- If arrow has sky access on hit, summons lightning instead of dealing damage.
- Compatible equipment: Crossbow
- Incompatible enchantments: Exposing, Quick Charge, Piercing
- Max level: 1
- Rarity: Rare
- Type: Treasure
### Technical description:
- Changes arrow particle effect to ELECTRIC_SPARK while arrow has sky access. Summons thunderbolt on hit, centered on block.
---
[Back to top](#enchantment-list)

## *Tough*
- Decreases incoming damage.
- Compatible equipment: Leggings
- Incompatible enchantments: Damage reduction-type enchantments
- Max level: 4
- Rarity: Uncommon
### Technical description:
- Decreases incoming damage by 3% per level before armor is applied. May be more effective than protection in certain circumstances.
---
[Back to top](#enchantment-list)

## *Turbo*
- Decreases elytra firework boost lifetime, but increases the speed boost
- Compatible equipment: Elytra
- Incompatible enchantments: Ace, Propelling
- Max level: 1
- Rarity: Rare
### Technical description:
- Elytra boost firework duration is cut to 1/3. Base speed boost from rockets is increased by 50%.
---
[Back to top](#enchantment-list)

## *Warding*
- User scares away phantoms.
- Compatible equipment: Horse armors
- Max level: 1
- Rarity: Uncommon
### Technical description:
- Prevents phantoms from swooping/attacking similar to cats, with a smaller area of effect.
---
[Back to top](#enchantment-list)

## *Weighted*
- Decreases attack speed, increases attack damage.
- Compatible equipment: Chestplates
- Incompatible enchantments: Dextrous, Thorns, Magic Guard
- Max level: 2
- Rarity: Rare
### Technical description:
- +20% damage, -15% attack speed per level.
---
[Back to top](#enchantment-list)

## *Windstep*
- Increases step height, allowing the user to instantly walk up 1 block tall inclines.
- Compatible equipment: Boots
- Incompatible enchantments: Leaping, Lunging
- Max level: 1
- Rarity: Rare
### Technical description:
- +0.4 step height. (default is 0.6 for entities other than horses)
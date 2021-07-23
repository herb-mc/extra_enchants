# Extra Enchants

Adds 33 obtainable equipment enchants and 1 experimental enchant, with functions ranging from utility to offense.

Latest: v0.0.8 for Minecraft 1.17.1<br>
Requires Fabric Loader 0.11.6 and Fabric API 0.37.0+1.17

## Enchantment List

### *Architect*
- Increases maximum block interaction range.
- Compatible equipment: Chestplates
- Max level: 3
- Rarity: Uncommon
<details><summary>Detailed description</summary>+1.0 range per level. Only applies to block placement/destruction, not attacking.<br>
The effect scales infinitely.</details>

### *Arrow Speed*
- Increases the velocity and damage of fired arrows.
- Compatible equipment: Bows
- Incompatible enchantments: Power
- Max level: 3
- Rarity: Common
<details><summary>Detailed description</summary>Increases arrow velocity by 15% and adds 0.25 to the arrow's damage attribute per level. Roughly equivalent to Power at the same level.<br>
The effect scales infinitely, but levels beyond 4 may result in visual bugs and quirky arrow behavior.</details>

### *Barbaric*
- Increases damage by an amount inversely proportional to the user's armor.
- Compatible equipment: Swords, Axes
- Incompatible enchants: Lifesteal
- Max level: 1
- Rarity: Rare
<details><summary>Detailed description</summary>
The damage increase is a multiplier applied to the total damage. The value at no armor is 1.8x, with a 0.04 decrease per armor point.<br>
The effect does not scale.</details>

### *Berserk*
- Increases damage by an amount proportional to the amount of health lost.
- Compatible equipment: Chestplates
- Incompatible enchantments: All Protection-type enchants
- Max level: 3
- Rarity: Uncommon
<details><summary>Detailed description</summary>
Extra damage is added directly. The precise formula for determining the amount of damage added is the following:

![berserk](https://latex.codecogs.com/gif.latex?%28maxhealth-remhealth%29%20*%20%5Cfrac%7Blevel%5E2%7D%7B2%20*%20level%5E2%20&plus;%204%7D.png) 

This equates to about 16.7% of lost health at level 1, 33.3% of lost health at level 2, and 40.9% of lost health at level 3.<br>
The effect scales infinitely, and damage added approaches but never reaches 50% of lost health.</details>

### *Blaze Affinity*
- Increases damage and damage reduction while on fire.
- Compatible equipment: Leggings
- Max level: 1
- Rarity: Uncommon
- Type: Treasure
<details><summary>Detailed description</summary>+10% damage, +5% damage reduction while on fire. Does not decrease fire damage.<br>
Effect does not scale.</details>

### *Boosting*
- When initiating a sprint, gain a massive burst of speed. 3 second cooldown.
- Compatible equipment: Leggings
- Incompatible enchantments: Steadfast
- Max level: 3
- Rarity: Uncommon
<details><summary>Detailed description</summary>Gain a speed boost equivalent to (40 * level) ticks that lasts for (20 * level) ticks. After stopping a sprint or reaching the maximum time, initiates a cooldown of 60 ticks that only ticks while not sprinting.<br>
Effect scales infinitely.</details>

### *Bounding*
- Increases horse jump height.
- Compatible equipment: Horse armors
- Incompatible enchantments: Surface Skimmer, Swiftness
- Max level: 3
- Rarity: Common
<details><summary>Detailed description</summary>Increases horse jump height by 10% per level.<br>
Effect technically scales infinitely, but horse jump mechanics have an upper limit for jump height.</details>

### *Cleaving*
- Ignores a portion of enemy armor.
- Compatible equipment: Axes
- Incompatible enchantments: Damage increasing enchantments
- Max level: 4
- Rarity: Uncommon
<details><summary>Detailed description</summary>The amount of armor ignored scales with level, with diminishing returns. The precise formula for determining the percentage ignored is the following:

![cleaving](https://latex.codecogs.com/gif.latex?1.8%20*%20%5Cfrac%7Blevel%7D%7B2%20*%20level%20&plus;%204%7D.png) 

This equates to 30% armor ignored at level 1, 45% armor ignored at level 2, 54% armor ignored at level 3, and 60% armor ignored at level 4.<br>
Effect scales infinitely, and armor ignored approaches but never reaches 90%.</details>

### *Core of Neptune*
- The less air you have, the stronger you are. Conversely, the more air you have, the weaker you become.
- Compatible equipment: Chestplates
- Incompatible enchantments: All Core-type enchants
- Max level: 1
- Rarity: Very rare
- Type: Curse, Treasure
<details><summary>Detailed description</summary>Air loss in water is 4x faster, while air gain outside of water is roughly 8x slower. Sets swimming drag coefficient to 0.97.<br>
Scales attack damage/speed according to the following formula:

![nepatkmod](https://latex.codecogs.com/gif.latex?attackmod%20%3D%201%20&plus;%20%5Cbegin%7Bcases%7D%20%5Cfrac%7B120%20-%20air%7D%7B450%7D%20%26%20%5Ctext%7B%20if%20%7D%20air%20%3E%20120%5C%5C%20%5Cfrac%7B90%20-%20air%7D%7B270%7D%20%26%20%5Ctext%7B%20if%20%7D%20air%20%3C%2090%20%5Cend%7Bcases%7D)

Scales movement speed according to the following formula:

![nepspdmod](https://latex.codecogs.com/gif.latex?speedmod%20%3D%201%20&plus;%20%5Cbegin%7Bcases%7D%20%5Cfrac%7B120%20-%20air%7D%7B900%7D%20%26%20%5Ctext%7B%20if%20%7D%20air%20%3E%20120%5C%5C%20%5Cfrac%7B90%20-%20air%7D%7B549%7D%20%26%20%5Ctext%7B%20if%20%7D%20air%20%3C%2090%20%5Cend%7Bcases%7D)

The effect does not scale.</details>

### *Core of Purity*
- Health is doubled, damage taken is reduced when healthy, speed is slightly reduced. Damage dealt is set to 1.
- Compatible equipment: Chestplates
- Incompatible enchantments: All Core-type enchants
- Max level: 1
- Rarity: Very rare
- Type: Curse, Treasure
<details><summary>Detailed description</summary>Doubled max health, -10% movement speed, damage dealt is set to 1 before damage calculation. Incoming damage is reduced by 70% if the user has at least 60% of their max health remaining.<br>
The effect does not scale.</details>

### *Core of the Blood God*
- Doubles health and increases attack damage. Armor is decreased and enemies may deal critical hits.
- Compatible equipment: Chestplates
- Incompatible enchantments: All Core-type enchants
- Max level: 1
- Rarity: Very rare
- Type: Curse, Treasure
<details><summary>Detailed description</summary>Doubles max health, +15% attack damage, -20% armor, 25% chance to take 80% increased damage from entities.<br>
Effect does not scale.</details>

### *Core of the Void*
- Health is halved, damage reduction and speed are increased.
- Compatible equipment: Chestplates
- Incompatible enchantments: All Core-type enchants
- Max level: 1
- Rarity: Very rare
- Type: Curse, Treasure
<details><summary>Detailed description</summary>-50% max health, 40% raw damage reduction before damage calculation, +15% movement speed.<br>
The effect does not scale.</details>

### *Dextrous*
- Increases attack speed.
- Compatible equipment: Chestplates
- Incompatible enchantments: Weighted, Thorns
- Max level: 2
- Rarity: Rare
<details><summary>Detailed description</summary>Increases attack speed by 10% per level.<br>
Effect scales infinitely.</details>

### *Dwarven*
- Find nearby ores easily.
- Compatible equipment: Helmets
- Incompatible enchantments: Sharpshooter, Night Vision, Psychic
- Max level: 1
- Rarity: Rare
<details><summary>Detailed description</summary>A trail of particles will emanate from the player's head towards the nearest ore-type block.<br>
Effect does not scale.</details>

### *Ender*
- On full charge: Teleport to arrow hit location.
- Compatible equipment: Bows
- Incompatible enchantments: Power, Infinity, Explosive
- Max level: 1
- Rarity: Rare
<details><summary>Detailed description</summary>Teleports the user to the arrow's location and inflicts the user with 2 fall damage if it hits the ground. If the arrow hits a mob, it will instead teleport the user to the mob and inflict 1 fall damage.<br>
Effect does not scale.</details>

### *Explosive*
- On full charge: Arrows are non-retrievable, explode on impact instead of dealing damage normally.<br>
- Compatible equipment: Bows
- Incompatible enchantments: Power, Infinity, Ender
- Max level: 2
- Rarity: Rare
<details><summary>Detailed description</summary>The resultant explosion has a power of lvl / 2 + 0.5, if the arrow hits the ground. The explosion power will match the level on direct hits. Arrows deal damage normally during dragon perching. None of the explosions deal block damage. (This will eventually be configurable)<br>
The effect scales infinitely.</details>

### *Exposing*
- Arrows inflict a glowing status effect. (there will eventually be enchantments designed to take advantage of this)
- Compatible equipment: Crossbows
- Max level: 3
- Rarity: Common
<details><summary>Detailed description</summary>Inflicts 40 extra ticks, or 2 seconds, of Glowing 20 (no particle) per tick.<br>
The effect scales infinitely.</details>

### *Featherweight*
- Fall slower depending on the level.
- Compatible equipment: Boots
- Incompatible enchantments: Feather Falling, Slimey
- Max level: 3
- Rarity: Uncommon
<details><summary>Detailed description</summary>Divides fall acceleration by (level + 1). Divides fall distance by (level * 2) before fall damage calculation. Overridden by Slow Falling effect.<br>
The effect scales infinitely.</details>

### *Launching*
- Throwing tridents launches you forward.
- Compatible equipment: Tridents
- Max level: 3
- Rarity: Rare
<details><summary>Detailed description</summary>.<br>
The effect scales infinitely.</details>

### *Leaping*
- Increases jump height.
- Compatible equipment: Boots
- Incompatible enchantments: Lunging, Windstep
- Max level: 2
- Rarity: Uncommon
<details><summary>Detailed description</summary>Increases jump velocity by 0.07 per level. Approximately +0.5 blocks of jump height up until level 8.<br>
The effect scales infinitely.</details>

### *Lifesteal*
- Drains a small amount of health upon dealing damage.
- Compatible equipment: Swords
- Max level: 3
- Rarity: Rare
<details><summary>Detailed description</summary>Heals 4% of damage dealt per level. Heal amount has an absolute cap of 3. On kill, directly heals (level) health.<br>
The healing factor scales infinitely, but is hardcapped at 3 health.</details>

### *Lunging*
- Increases horizontal jump velocity
- Compatible equipment: Boots
- Incompatible enchantments: Leaping, Windstep
- Max level: 3
- Rarity: Uncommon
<details><summary>Detailed description</summary>10% increase to x/z velocities while jumping per level.<br>
The effect sscales infinitely.</details>

### *Night Vision*
- While sneaking, the user will gain Night Vision.
- Compatible equipment: Helmets
- Incompatible enchantments: Dwarven, Sharpshooter, Psychic
- Max level: 1
- Rarity: Rare
<details><summary>Detailed description</summary>User is given Night Vision while sneaking. This will override any current Night Vision effect.</details>

### *Psychic*
- While sneaking, the nearest mob within 7 blocks the user is facing will be exposed. Ignores blocks.
- Compatible equipment: Helmets
- Incompatible enchantments: Dwarven, Sharpshooter, Night Vision
- Max level: 1
- Rarity: Rare
<details><summary>Detailed description</summary>Any entity within 7 blocks of the user's line of sight (disregarding blocks) will receive Glowing 20 while the user is sneaking.</details>

### *Sharpshooter*
- While sneaking, the camera will zoom in 3x. Arrow damage is increased.
- Compatible equipment: Helmets
- Incompatible enchantments: Dwarven, Night Vision, Psychic
- Max level: 1
- Rarity: Rare
<details><summary>Detailed description</summary>FOV is multiplied by 1/3. +1 damage to all fired arrows. +5 damage to thrown tridents.<br>
The effect does not scale.</details>

### *Slimey*
- Slipperiness of blocks is massively increased.
- Compatible equipment: Boots
- Incompatible enchantments: Feather Falling, Featherweight
- Max level: 1
- Rarity: Rare
<details><summary>Detailed description</summary>Slipperiness is set to 1.0 while equipped. (Ice/Packed Ice default to 0.98)<br>
The effect does not scale.</details>

### *Steadfast*
- Decreases the weapon drawing movement penalty.
- Compatible equipment: Leggings
- Incompatible enchantments: Boosting
- Max level: 2
- Rarity: Uncommon
<details><summary>Detailed description</summary>While drawing a bow, crossbow or trident: movement speed multiplied by (level + 1).<br>
The effect scales infinitely.</details>

### *Surface Skimmer (Experimental)*
- Allows the user to walk on water.
- Must be enabled via datapack
- Compatible equipment: Horse armors
- Incompatible enchantments: Bounding, Swiftness
- Max level: 3
- Rarity: Common
<details><summary>Detailed description</summary>Horses with this equipped will always float in and eventually stand on top of water.<br>
The effect does not scale. (yet)</details>

### *Swiftness*
- Increases movement speed.
- Compatible equipment: Horse armors
- Incompatible enchantments: Bounding, Surface Skimmer
- Max level: 3
- Rarity: Common
<details><summary>Detailed description</summary>Increases horse speed by 10% per level.<br>
The effect scales infinitely.</details>

### *Terraforming*
- Greatly increases mining speed. Broken blocks drop nothing.
- Compatible equipment: Tools
- Incompatible enchantments: Fortune, Silk Touch
- Max level: 1
- Rarity: Rare
<details><summary>Detailed description</summary>Increases tool speed by 58. This allows a Terraforming Diamond/Netherite Pickaxe to break stone instantly. Terraforming + Efficiency V allows Diamond/Netherite Pickaxes to instantly break some ores.<br>
The effect does not scale.</details>

### *Tough*
- Decreases incoming damage.
- Compatible equipment: Leggings
- Incompatible enchantments: Damage reduction-type enchantments
- Max level: 4
- Rarity: Uncommon
<details><summary>Detailed description</summary>Decreases incoming damage by 3% per level before armor is applied. May be more effective than protection in certain circumstances.<br>
The effect scales infinitely.</details>

### *Warding*
- User scares away phantoms.
- Compatible equipment: Horse armors
- Max level: 1
- Rarity: Uncommon
<details><summary>Detailed description</summary>Prevents phantoms from swooping/attacking similar to cats, with a smaller area of effect.<br>
The effect does not scale.</details>

### *Weighted*
- Decreases attack speed, increases attack damage.
- Compatible equipment: Chestplates
- Incompatible enchantments: Dextrous, Thorns
- Max level: 2
- Rarity: Rare
<details><summary>Detailed description</summary>+20% damage, -15% attack speed per level.<br>
Effect scales infinitely.</details>

### *Windstep*
- Increases step height, allowing the user to instantly walk up 1 block tall inclines.
- Compatible equipment: Boots
- Incompatible enchantments: Leaping, Lunging
- Max level: 1
- Rarity: Rare
<details><summary>Detailed description</summary>+0.4 step height. (default is 0.6 for entities other than horses)<br>
Effect scales infinitely.</details>

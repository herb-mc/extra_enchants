# Extra Enchants

Adds 21 enchantments with functions ranging from utility to offense. Currently only compatible with version 1.17.

Requires Fabric Loader 0.11.6 and Fabric API 0.36.0+1.17

## Enchantment List

### *Architect*
- Increases maximum block interaction range.
- Compatible equipment: Chestplates
- Max level: 3
- Rarity: Uncommon
  <details><summary>Detailed description</summary>+1.0 range per level. Only applies to block placement/destruction, not attacking.\
  The effect scales infinitely.</details>

### *Arrow Speed*
- Increases the velocity and damage of fired arrows.
- Compatible equipment: Bows
- Incompatible enchantments: Power
- Max level: 3
- Rarity: Common
  <details><summary>Detailed description</summary>Increases arrow velocity by 15% and adds 0.25 to the arrow's damage attribute per level. Roughly equivalent to Power at the same level.\
  The effect scales infinitely, but levels beyond 4 may result in visual bugs and quirky arrow behavior.</details>

### *Barbaric*
- Increases damage by an amount inversely proportional to the user's armor.
- Compatible equipment: Swords, Axes
- Incompatible enchants: Lifesteal
- Max level: 1
- Rarity: Rare
  <details><summary>Detailed description</summary>
  The damage increase is a multiplier applied to the total damage. The value at no armor is 1.8x, with a 0.04 decrease per armor point.\
  The effect does not scale.</details>

### *Berserk*
- Increases damage by an amount proportional to the amount of health lost.
- Compatible equipment: Chestplates
- Incompatible enchantments: All Protection-type enchants
- Max level: 3
- Rarity: Uncommon
  <details><summary>Detailed description</summary>
  Extra damage is added directly. The precise formula for determining the amount of damage added is the following:\
  ![berserk](https://latex.codecogs.com/gif.latex?%28maxhealth-remhealth%29%20*%20%5Cfrac%7Blevel%5E2%7D%7B2%20*%20level%5E2%20&plus;%204%7D)\
  This equates to about 16.7% of lost health at level 1, 33.3% of lost health at level 2, and 40.9% of lost health at level 3.\
  The effect scales infinitely, and damage added approaches but never reaches 50% of lost health.</details>

### *Bloodcore*
- Doubles health and increases attack damage. Armor is decreased and enemies may deal critical hits. Is considered a curse.
- Compatible equipment: Chestplates
- Incompatible enchantments: Protection, Berserk
- Max level: 1
- Rarity: Very rare
  <details><summary>Detailed description</summary>Doubles max health, +10% attack damage, -20% armor, 25% chance to take 80% increased damage from entities.\
  Effect does not scale.</details>

### *Bounding*
- Increases horse jump height.
- Compatible equipment: Horse armors
- Incompatible enchantments: Surface Skimmer, Swiftness
- Max level: 3
- Rarity: Common
  <details><summary>Detailed description</summary>Increases horse jump height by 10% per level.\
  Effect technically scales infinitely, but horse jump mechanics have an upper limit for jump height.</details>

### *Cleaving*
- Ignores a portion of enemy armor.
- Compatible equipment: Axes
- Incompatible enchantments: Damage increasing enchantments
- Max level: 4
- Rarity: Uncommon
  <details><summary>Detailed description</summary>The amount of armor ignored scales with level, with diminishing returns. The precise formula for determining the percentage ignored is the following:\
  ![cleaving](https://latex.codecogs.com/gif.latex?1.8%20*%20%5Cfrac%7Blevel%7D%7B2%20*%20level%20&plus;%204%7D)\
  This equates to 30% armor ignored at level 1, 45% armor ignored at level 2, 54% armor ignored at level 3, and 60% armor ignored at level 4.\
  Effect scales infinitely, and armor ignored approaches but never reaches 90%.</details>

### *Dextrous*
- Increases attack speed.
- Compatible equipment: Chestplates
- Incompatible enchantments: Weighted, Thorns
- Max level: 2
- Rarity: Rare
  <details><summary>Detailed description</summary>Increases attack speed by 10% per level.\
  Effect scales infinitely.</details>

### *Ender*
- On full charge: Teleport to arrow hit location.
- Compatible equipment: Bows
- Incompatible enchantments: Power, Infinity, Explosive
- Max level: 1
- Rarity: Rare
  <details><summary>Detailed description</summary>Teleports the user to the arrow's location and inflicts the user with 2 fall damage if it hits the ground. If the arrow hits a mob, it will instead teleport the user to the mob and inflict 1 fall damage.\
  Effect does not scale.</details>

### *Explosive*
- On full charge: Arrows are non-retrievable, explode on impact instead of dealing damage normally.
- Compatible equipment: Bows
- Incompatible enchantments: Power, Infinity, Ender
- Max level: 2
- Rarity: Rare
  <details><summary>Detailed description</summary>The resultant explosion has a power of lvl / 2 + 0.5, if the arrow hits the ground. The explosion power will match the level on direct hits. Power is unconditionally halved during the vulnerable part of the dragon's perching phase. None of the explosions deal block damage. (This will eventually be configurable)\
  The effect scales infinitely. At higher levels, due to explosion mechanics, it is possible to trap the Ender Dragon in an infinite loop during its perching phase.</details>

### *Exposing*
- Arrows inflict a glowing status effect. (there will eventually be enchantments designed to take advantage of this)
- Compatible equipment: Crossbows
- Max level: 3
- Rarity: Common
  <details><summary>Detailed description</summary>Inflicts 40 extra ticks, or 2 seconds, of Glowing 20 (no particle) per tick.\
  The effect scales infinitely.</details>

### *Leaping*
- Increases jump height.
- Compatible equipment: Boots
- Max level: 2
- Rarity: Uncommon
  <details><summary>Detailed description</summary>Increases jump velocity by 0.07 per level. Approximately +0.5 blocks of jump height up until level 8.\
  The effect scales infinitely.</details>

### *Lifesteal*
- Drains a small amount of health upon dealing damage.
- Compatible equipment: Swords
- Max level: 3
- Rarity: Rare
  <details><summary>Detailed description</summary>Heals 4% of damage dealt per level. Heal amount has an absolute cap of 3. On kill, directly heals (level) health.\
  The healing factor scales infinitely, but is hardcapped at 3 health.</details>

### *Lunging*
- Increases horizontal jump velocity
- Compatible equipment: Boots
- Max level: 3
- Rarity: Uncommon
  <details><summary>Detailed description</summary>10% increase to x/z velocities while jumping per level.\
  The effect sscales infinitely.</details>

### *Surface Skimmer*
- Allows the user to walk on water. (eventually level will determine max speed)
- Compatible equipment: Horse armors
- Incompatible enchantments: Bounding, Swiftness
- Max level: 3
- Rarity: Common
  <details><summary>Detailed description</summary>Horses with this equipped will always float in and eventually stand on top of water.\
  The effect does not scale. (yet)</details>

### *Swiftness*
- Increases movement speed.
- Compatible equipment: Horse armors
- Incompatible enchantments: Bounding, Surface Skimmer
- Max level: 3
- Rarity: Common
  <details><summary>Detailed description</summary>Increases horse speed by 10% per level.\
  The effect scales infinitely.</details>

### *Terraforming*
- Greatly increases mining speed. Broken blocks drop nothing.
- Compatible equipment: Tools
- Incompatible enchantments: Fortune, Silk Touch
- Max level: 1
- Rarity: Rare
  <details><summary>Detailed description</summary>Increases tool speed by 58. This allows a Terraforming Diamond/Netherite Pickaxe to break stone instantly. Terraforming + Efficiency V allows Diamond/Netherite Pickaxes to instantly break some ores.\
  The effect does not scale.</details>

### *Tough*
- Decreases incoming damage.
- Compatible equipment: Leggings
- Incompatible enchantments: Damage reduction-type enchantments
- Max level: 4
- Rarity: Uncommon
  <details><summary>Detailed description</summary>Decreases incoming damage by 3% per level before armor is applied. May be more effective than protection in certain circumstances.\
  The effect scales infinitely.</details>

### *Warding*
- User scares away phantoms.
- Compatible equipment: Horse armors
- Max level: 1
- Rarity: Uncommon
  <details><summary>Detailed description</summary>Prevents phantoms from swooping/attacking similar to cats, with a smaller area of effect.\
  The effect does not scale.</details>

### *Weighted*
- Decreases attack speed, increases attack damage.
- Compatible equipment: Chestplates
- Incompatible enchantments: Dextrous, Thorns
- Max level: 2
- Rarity: Rare
  <details><summary>Detailed description</summary>+20% damage, -15% attack speed per level.
  Effect scales infinitely.</details>

### *Windstep*
- Increases step height, allowing the user to instantly walk up 1 block tall inclines.
- Compatible equipment: Boots
- Incompatible enchantments: Feather Falling
- Max level: 1
- Rarity: Rare
  <details><summary>Detailed description</summary>+0.4 step height. (default is 0.6 for entities other than horses)
  Effect scales infinitely.</details>
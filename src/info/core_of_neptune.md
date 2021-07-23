[<- Back to list](index.md)

### *Core of Neptune*
- The less air you have, the stronger you are. Conversely, the more air you have, the weaker you become.
- Compatible equipment: Chestplates
- Incompatible enchantments: All Core-type enchants
- Max level: 1
- Rarity: Very rare
- Type: Curse, Treasure
---
### Technical description:
- Air loss in water is 4x faster, while air gain outside of water is roughly 8x slower. Sets swimming drag coefficient to 0.97.<br>
Scales attack damage/speed according to the following formula:

![nepatkmod](https://latex.codecogs.com/gif.latex?attackmod%20%3D%201%20&plus;%20%5Cbegin%7Bcases%7D%20%5Cfrac%7B120%20-%20air%7D%7B450%7D%20%26%20%5Ctext%7B%20if%20%7D%20air%20%3E%20120%5C%5C%20%5Cfrac%7B90%20-%20air%7D%7B270%7D%20%26%20%5Ctext%7B%20if%20%7D%20air%20%3C%2090%20%5Cend%7Bcases%7D)

- Scales movement speed according to the following formula:

![nepspdmod](https://latex.codecogs.com/gif.latex?speedmod%20%3D%201%20&plus;%20%5Cbegin%7Bcases%7D%20%5Cfrac%7B120%20-%20air%7D%7B900%7D%20%26%20%5Ctext%7B%20if%20%7D%20air%20%3E%20120%5C%5C%20%5Cfrac%7B90%20-%20air%7D%7B549%7D%20%26%20%5Ctext%7B%20if%20%7D%20air%20%3C%2090%20%5Cend%7Bcases%7D)

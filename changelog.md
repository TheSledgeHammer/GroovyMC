#GroovyMC Changelog

## [Unreleased]
### Added
- Added GroovyObjectModelDefinition with Static & Variable Keys for model mapping
- Added Modules: For inter mod compatability
- Added Buildcraft MJ
- Added Independent variation of MinecraftJoules from Buildcraft (aiming for compatability)
- Added New classes inner workings of Variable Models
- Added GroovysonObjectState: An Object version of a blockstate json. In similar design to GroovysonObject & GroovysonObjectPart
- ModelEntryHolderManager: More all rounded Register for Models & Textures
### Changes
- GroovysonAbstractModel refactored to GroovysonObjectModel with minor changes
- A large number of changes to both Variable & Static Models. For greater intergration
- JsonRule: Reverted this fork to be closer to it's original Buildcraft counterpart. 
- IModelGroovyBakedModel: More in line with other GroovyMC BakedModel implementations
- Energy refactored to forgeenergy and moved to intergration
- ModelEntry & TextureEntry: Now Register through a SubscriberEvent from the ModelEntryHolderManager 
### Fixed
- ModelEntryHolderRegistry/ ModelEntryHolderManager unable to be loaded in preInit due to a null pointer exception when no models are initalized.
### Removed
- Input Handling
- Keybindings
- Network: PacketHandler & PacketKey: Never unused.
- GroovyModelDefintion
- GroovyResourceDefinition

## [1.1.0] - 2019-05-10
### Added
- Added Experimental GUI Builder
- Added ModelEntryHolder
- Added Equals and Hashcode functions to GroovyLoader
- Added JsonTools: External methods for JsonQuads, MutableQuads, etc
- Added Variables Types for most Primitives (Integer, Double, Float, Long, Short, Boolean, Byte, Object, Triple, Pair)
- Added GroovyVariableModel to provide variable parameters for animated models
- Added GroovyISpriteDefinition to support ISprites
- Added GroovysonObjectCache, a Cache for GroovysonObject & GroovysonObjectParts
### Changes
- ModelEntry & TextureEntry re-write to improve integration
- ModelEntryRegistry to Register ModelEntryHolders
- GroovyStaticModel implements ModelEntryHolder and improved integration and support
- ObjectManager no longer loaded in Client Proxy
- GroovyForge's Language Adapter replaces GroovyMC's Language Adapter as a Dependency
### Fixed
### Removed
- Client, Common and Server Proxy
- GroovyMC Language Adapter API
- McModInfo class in favor of mcmod.info
- FileSearch unused

## [1.0.5] - 2019-03-19
### Added
- Added Initial Commit for MultiBlock's
- Added Model Rendering that includes the different BlockRender types (Cutout, Solid, Translucent & CutoutMipped)
- Added Groovy Language Adapter API
### Changes
### Fixed
### Removed

## [1.0.4] - 2019-03-17
### Added
- Added Definitions & Render to Client Folder: is a restructure and partial re-write for managing and integrating models
- Added Fluid Support
- Added GroovyStateMapperBase added for fluids, forked from Team COFH
- Added JsonRule to Read rules from a model json
- Added Client Render which includes Render types (Cutout, Solid, Translucent & CutoutMipped) and extensions to FastTESR and TileEntitySpecialRenderer
- Added MCMod into its own class
### Changes
- All info related to the mcmod.info moved from constants to MCMod class
### Fixed
### Removed
- GroovyConfig - Unused
- GroovyModelBuilder - Replaced with Client Definitions
- GroovyResourcesBuilder - Replaced with Client Definitions

## [1.0.2] - 2019-02-07
### Added
- Added Interface IRegisterTileEntity for Easier TileEntity Registering
- Added More Block Properties and Machine Property Interfaces to improve IBlockType stability and Support
### Changes
### Fixed
- Compilation bugs thrown from Block Machine Properties
### Removed

## [1.0.1] - 2019-01-30
### Added
### Changes
### Fixed
- GroovyLoader missing code references in various methods.
- ContainerGroovy importing and referencing the wrong java library
### Removed

## [1.0.0] - 2019-01-26
### Added
- Initial Release
### Changes
### Fixed
### Removed

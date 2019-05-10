#GroovyMC Changelog

## [Unreleased]

## [1.1.0] - TBA
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

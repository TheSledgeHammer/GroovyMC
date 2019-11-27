TODO:
- ISprite: is ISprite Needed or can it be replaced with Forge's ISprite?

Modules:
- Make use of InterModComms to display debug/log messages when a module 
is loaded successfully or unsuccessfully 
- Change init, preinit and postinit 

Models:
- BakedModels & ModelEntryHolder: missingsprite fix
- Fork: BuildCraft's Json: VariableTextureExpand & VariablePartContainer 

Rendering:
- RenderRegistry
- IRenderFactory
- EntityRendererManager
- AtlasTexture: TextureMap replacement
- hasCustomLoader: TextureAtlasSprite (Make use of this)
- IResourceManager: used in ResourceLoader and hasCustomLoader
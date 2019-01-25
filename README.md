# GroovyMC
A Minecraft Mod Library created using the Apache Groovy programming language.

A mod library used in my other mods both existing and upcoming.

The library is useful for any mod developer wishing to utilize features within Apache Groovy without having to start their own mod from scratch.

## Getting Started

Please initialize the "GroovyLoader" in your main class or common proxy class.

The class is not required, but provides a lot of built in functions and functions that have been auto-completed when used, making life that little bit easier.

Example:
GroovyLoader groovyLoader = new GroovyLoader(String modPath, String modResourcePath, String jvm, String url, String modID);

or look at GroovyMC.groovy for a completed example.
Note: the source for this class is under utils

### GNU Lesser General Public License v3.0

Permissions of this copyleft license are conditioned on making available complete source code of licensed works and modifications under the same license or the GNU GPLv3. Copyright and license notices must be preserved. Contributors provide an express grant of patent rights. However, a larger work using the licensed work through interfaces provided by the licensed work may be distributed under different terms and without source code for the larger work.

Please Read the License file for more information

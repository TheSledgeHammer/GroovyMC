# GroovyMC
A Minecraft Mod Library created using the Apache Groovy programming language.

A mod library used in my other mods both existing and upcoming.

The library is useful for any mod developer wishing to utilize features within Apache Groovy without having to start their own mod from scratch.

### Getting Started

Please initialize the "GroovyLoader" in your main class or common proxy class.

GroovyLoader groovyLoader = new GroovyLoader(String modPath, String modResourcePath, String jvm, String url, String modID);

The class is required for using GroovyMC's Models & Rendering. Otherwise it is optional.

Provides a lot of built in functions and functions that have been auto-completed when used, making life that little bit easier.

For a complete example look at the GroovyMC class.

### Build Gradle Environment
Maven Repository:
Add the following to your build.Gradle
```
repositories {
  maven {
    name = "GroovyMC"
    url = "https://helixteamhub.cloud/SledgeHammerDevolopment/projects/mods/repositories/maven/devlopment"
  }
}

dependencies {
	compile "com.thesledgehammer.GroovyMC:GroovyMC_1.12.2:+:universal"
}
```

### GNU Lesser General Public License v3.0

Permissions of this copyleft license are conditioned on making available complete source code of licensed works and modifications under the same license or the GNU GPLv3. Copyright and license notices must be preserved. Contributors provide an express grant of patent rights. However, a larger work using the licensed work through interfaces provided by the licensed work may be distributed under different terms and without source code for the larger work.

Please Read the License file for more information

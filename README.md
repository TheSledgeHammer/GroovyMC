<p align="center" style="padding: 3em;"><img width="150" src="https://github.com/TheSledgeHammer/GroovyMC/blob/master/src/main/resources/assets/groovymc/textures/groovymclogo.png?raw=true" /></p>
<h1 align="center" style="margin-top: 20px; border-bottom: 0;">GroovyMC Library</h1>
<p align="center">A Minecraft Mod Library created using the Apache Groovy programming language.</p>
<p align="center">
    <a href="https://minecraft.curseforge.com/projects/groovymc-library"><img src="http://cf.way2muchnoise.eu/full_312384_downloads.svg" /></a>
    <a href="https://minecraft.curseforge.com/projects/groovymc-library"><img src="http://cf.way2muchnoise.eu/packs/full_312384_in_packs.svg" /></a>
    <a href="https://depshield.github.io"><img src="https://depshield.sonatype.org/badges/TheSledgeHammer/GroovyMC/depshield.svg")] /></a>
</p>

A mod library used in my other mods both existing and upcoming.

The library is useful for any mod developer wishing to utilize features within Apache Groovy without having to start their own mod from scratch.

### Getting Started:
Please initialize the "GroovyLoader" in your main class or common proxy class.
```
GroovyLoader groovyLoader = new GroovyLoader(modPath, modResourcePath, jvm, url, modID);
```
The class is required for using GroovyMC's Models & Rendering.

Provides a lot of built in functions and functions that have been auto-completed when used, making life that little bit easier.

For a complete example look at the GroovyMC class.

### Build Gradle Environment:
Maven Repository:
Add the following to your build.Gradle
```
repositories {
  maven {
    name = "GroovyMC"
    url = "https://dl.bintray.com/thesledgehammer/development/"
  }
}

dependencies {
	compile "com.thesledgehammer.GroovyMC:GroovyMC_1.12.2:+:universal"
}
```

### Apache License v2.0:

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

#### Notice:
This project also contains source code that is maintained under the LGPL 3.0 and MPL 2.0.

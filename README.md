# GroovyMC:
A Minecraft Mod Library created using the Apache Groovy programming language.

A mod library used in my other mods both existing and upcoming.

The library is useful for any mod developer wishing to utilize features within Apache Groovy without having to start their own mod from scratch.

NOTICE: 1.13.2 IS CURRENTLY IN PRE-ALPHA AND DOES NOT WORK.

### Getting Started:
GroovyMC also provides a built-in Groovy Language Adapter.
To use GroovyMC's Language Adapter add the following to @Mod in your main class
```
@Mod(modLanguageAdapter = "com.thesledgehammer.groovymc.api.GroovyLanguageAdapter")
class modclass {
    //Your mod class info
}

```

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
	compile "com.thesledgehammer.GroovyMC:GroovyMC_1.13.2:+:universal"
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

GroovyMC is no longer licenced under the LGPL 3.0 and is now licenced under the Apache 2.0 licence. If you are using this project or parts of this project; Please update the licencing to reflect this change.

This project also contains source code that is maintained under the LGPL 3.0 and MPL 2.0.

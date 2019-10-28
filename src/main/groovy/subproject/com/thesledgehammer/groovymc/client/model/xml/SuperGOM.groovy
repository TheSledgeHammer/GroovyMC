/*
 * Copyright [2018] [TheSledgeHammer]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package subproject.com.thesledgehammer.groovymc.client.model.xml

import groovy.xml.MarkupBuilder

class SuperGOM {

    static String CURRENT_VERSION = "1.0.1";
    static String PATH = "src/main/groovy/subproject/resources/gom/"

    static void writeSuperGOM(String modelVersion) {
        def writer = new FileWriter("${PATH}gom-${modelVersion}.xml");
        def xml = new MarkupBuilder(writer);

        xml.gom {
            modelversion(modelVersion)
            parent()
            textures {

            }
            shade()
            rules {
                when()
                type()
                from()
                to()
                origin()
                angle()
                scale()
            }
            elements {
                element {
                    name()
                    from()
                    to()
                    light()
                    render()
                    faces {
                        up {
                            uv()
                            texture()
                            cullface()
                            rotation()
                            tint()
                        }
                        down {
                            uv()
                            texture()
                            cullface()
                            rotation()
                            tint()
                        }
                        north {
                            uv()
                            texture()
                            cullface()
                            rotation()
                            tint()
                        }
                        east {
                            uv()
                            texture()
                            cullface()
                            rotation()
                            tint()
                        }
                        west {
                            uv()
                            texture()
                            cullface()
                            rotation()
                            tint()
                        }
                        south {
                            uv()
                            texture()
                            cullface()
                            rotation()
                            tint()
                        }
                    }
                    rotation {
                        origin()
                        axis()
                        angle()
                        rescale()
                    }
                    shade()
                    colour()
                    visible()
                    invert()
                    bothsides()
                }
            }
            display {
                name()
                translation()
                rotation()
                scale()
            }
            values()
            ambientocculusion()
        }
        writer.close();
    }

    static def readSuperGOM(String modelVersion) {
        def xml = new XmlSlurper();
        def superGom = xml.parse(new FileReader("${PATH}gom-${modelVersion}.xml"))
        return superGom;
    }
}

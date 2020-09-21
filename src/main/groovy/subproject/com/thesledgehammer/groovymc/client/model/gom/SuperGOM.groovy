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

package subproject.com.thesledgehammer.groovymc.client.model.gom

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import groovy.xml.MarkupBuilder

class SuperGOM {

    static String CURRENT_VERSION = "1.0.2";
    static String PATH = "src/main/groovy/subproject/resources/gom/"

    static void writeSuperGOM(String path, String modelVersion, String type) {
        if(type == "xml") {
            writeToXML(path, modelVersion);
        }
        if(type == "json") {
            writeToJson(path, modelVersion);
        }
    }

    /**
     * @param path: location of file
     * @param modelVersion: GOM version being used
     * @param type: .xml or .json File Format
     * @return: object from the contents of the file (.xml or .json)
     */
    static def readSuperGOM(String path, String modelVersion, String type) {
        def superGom = null
        if(type == "xml") {
            def xml = new groovy.xml.XmlSlurper();
            superGom = xml.parse(new FileReader("${path}gom-${modelVersion}.xml"));

        }
        if(type == "json") {
            def json = new JsonSlurper();
            superGom = json.parse(new FileReader("${path}gom-${modelVersion}.json"));
        }
        return superGom;
    }

    private static void writeToXML(String path, String modelVersion) {
        def writer = new FileWriter("${path}gom-${modelVersion}.xml");
        def xml = new MarkupBuilder(writer);

        xml.gom {
            modelversion(modelVersion)
            parent()
            textures {

            }
            shade()
            variables {

            }
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

    private static void writeToJson(String path, String modelVersion) {
        def writer = new FileWriter("${path}gom-${modelVersion}.json");
        def json = new JsonBuilder(writer);

        json.gom {
            modelversion(modelVersion)
            parent()
            textures {

            }
            shade()
            variables {

            }
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

        String gomString = json.toPrettyString();
        writer.write(gomString);
        writer.close();
    }

    static class Serializer implements GOMDeserializer<SuperGOM>, GOMSerializer<SuperGOM> {

        @Override
        def Deserialize(String path, String version, String type) {
            return readSuperGOM(path, version, type);
        }

        @Override
        void Serialize(String path, String version, String type) {
            /* Write SuperGOM xml & json */
            writeSuperGOM(PATH, CURRENT_VERSION, "xml");
            writeSuperGOM(PATH, CURRENT_VERSION, "json");

            /* Write GOM */
            writeSuperGOM(path, version, type);
        }
    }
}

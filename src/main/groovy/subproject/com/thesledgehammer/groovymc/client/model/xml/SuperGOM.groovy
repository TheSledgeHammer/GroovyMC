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
            ModelVersion(modelVersion)
            Parent()
            Textures {

            }
            Shade()
            Rules {
                When()
                Type()
                From()
                To()
                Origin()
                Angle()
                Scale()
            }
            Elements {
                Element {
                    Name()
                    From()
                    To()
                    Light()
                    Render()
                    Faces {
                        Up {
                            UV()
                            Texture()
                            CullFace()
                            Rotation()
                            Tint()
                        }
                        Down {
                            UV()
                            Texture()
                            CullFace()
                            Rotation()
                            Tint()
                        }
                        North {
                            UV()
                            Texture()
                            CullFace()
                            Rotation()
                            Tint()
                        }
                        East {
                            UV()
                            Texture()
                            CullFace()
                            Rotation()
                            Tint()
                        }
                        West {
                            UV()
                            Texture()
                            CullFace()
                            Rotation()
                            Tint()
                        }
                        South {
                            UV()
                            Texture()
                            CullFace()
                            Rotation()
                            Tint()
                        }
                    }
                    Rotation {
                        Origin()
                        Axis()
                        Angle()
                        Rescale()
                    }
                    Shade()
                    Colour()
                    Visible()
                    Invert()
                    BothSides()
                }
            }
            Display {
                Name()
                Translation()
                Rotation()
                Scale()
            }
            Values()
            AmbientOcculusion()
        }
        writer.close();
    }

    static def readSuperGOM(String modelVersion) {
        def xml = new XmlSlurper();
        def superGom = xml.parse(new FileReader("${PATH}gom-${modelVersion}.xml"))
        return superGom;
    }
}

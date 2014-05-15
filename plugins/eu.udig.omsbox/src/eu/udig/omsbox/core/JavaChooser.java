/*
 * JGrass - Free Open Source Java GIS http://www.jgrass.org 
 * (C) HydroloGIS - www.hydrologis.com 
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package eu.udig.omsbox.core;

import java.io.File;

import org.eclipse.core.runtime.Platform;
import org.eclipse.osgi.service.datalocation.Location;
import org.geotools.data.DataUtilities;

/**
 * Utility class to handle the JVM to use for processing.
 * 
 * @author Andrea Antonello (www.hydrologis.com)
 */
public class JavaChooser {
    private static final String JRE_BIN = "jre/bin";
    private static final String JRE64bit_BIN = "jre64/bin";
    private final static String[] possibleJavaExecutables = {"javaw.exe", "java.exe", "java"};

    /**
     * Get the java path to use for processing.
     * 
     * @return the java path to use, or simply "java" if no path was found.
     */
    public static String getProcessingJava() {

        Location installLocation = Platform.getInstallLocation();
        File installFolder = DataUtilities.urlToFile(installLocation.getURL());
        if (installFolder != null && installFolder.exists()) {
            // first thing we try is a 64 bit jvm inside the install folder: jre64
            File jreFolder = new File(installFolder, JRE64bit_BIN);
            if (!jreFolder.exists()) {
                // if it doesn't exist, check a "normal" jre folder: jre
                jreFolder = new File(installFolder, JRE_BIN);
                if (!jreFolder.exists()) {
                    jreFolder = null;
                }
            }
            if (jreFolder != null) {
                // look for the executable
                for( String pJava : possibleJavaExecutables ) {
                    File javaExecFile = new File(jreFolder, pJava);
                    if (javaExecFile.exists()) {
                        return javaExecFile.getAbsolutePath();
                    }
                }
            }
        }

        /*
         *  If we are here, no jre folder was found inside the 
         *  installation folder. 
         *  
         *  Let's check if there is a JAVA_HOME environmental 
         *  variable.
         */
        String javaHomeFolder = System.getenv("JAVA_HOME");
        if (javaHomeFolder != null) {
            File javaHomeFolderFile = new File(javaHomeFolder, JRE_BIN);
            if (javaHomeFolderFile.exists()) {
                for( String pJava : possibleJavaExecutables ) {
                    File javaExecFile = new File(javaHomeFolderFile, pJava);
                    if (javaExecFile.exists()) {
                        return javaExecFile.getAbsolutePath();
                    }
                }
            }
        }

        // nothing worked, hope to find it in the system path.
        return "java";
    }

}

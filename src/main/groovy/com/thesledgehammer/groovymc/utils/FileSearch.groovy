/**************************************************************************************************
 * Copyright (c) 2018 TheSledgeHammer.                                                            *
 * All rights reserved. This program and the accompanying materials                               *
 * are made available under the terms of the GNU Lesser Public License v3                         *
 * which accompanies this distribution, and is available at                                       *
 * http://www.gnu.org/licenses/lgpl-3.0.txt                                                       *
 **************************************************************************************************/

package com.thesledgehammer.groovymc.utils

import com.google.common.collect.LinkedListMultimap
import com.google.common.collect.Multimap

/**
 * File Searching methods are independent of each other.
 * So using SearchFile() does not require SearchDirectories() or vice-versa.
 */
class FileSearch {

    private String fileName;
    private Multimap<String, String> contents = LinkedListMultimap.create();
    private List<String> results = new LinkedList<>();

    FileSearch() {

    }

    String getFileToSearch() {
        return fileName;
    }

    //Returns entire file structure tree. Directories and sub-directories
    Multimap<String, String> getContents() {
        return contents;
    }

    /**
     * @return: All Directories and sub-directories
     */
    List<String> getDirectories() {
        return contents.get("directory");
    }

    /**
     * @return: All Directory at specified index
     */
    String getDirectory(int index) {
        return contents.get("directory").get(index);
    }

    /**
     * @return: All files from directory root
     */
    LinkedList<String> getDirectoryFiles() {
        return contents.get("file");
    }

    /**
     * @return: All File at specified index
     */
    String getFile(int index) {
        return contents.get("file").get(index);
    }

    void setFileToSearch(String fileName) {
        this.fileName = fileName;
    }

    boolean containsEntry(String key, String value) {
        if(containsKey(key) && containsValue(value)) {
            return true;
        }
        return false;
    }

    boolean containsKey(String key) {
        for(int i = 0; i < getDirectories().size(); i++) {
            if(getDirectory(i).contains(key)) {
                return true;
            }
        }
        return false;
    }

    boolean containsValue(String value) {
        for(int i = 0; i < getDirectoryFiles().size(); i++) {
            if(getFile(i).contains(value)) {
                return true;
            }
        }
        return false;
    }

    void clearSearchContents() {
        contents.clear();
    }

    /**
     * @return All the files that match the specified filename
     */
    List<String> getFileSearchResults() {
        return results;
    }

    /**
     * @param index: the file at that index
     * @return the file/ files that contain the filename specified
     */
    String getFileSearchResult(int index) {
        return results.get(index);
    }

    void clearFileSearchResults() {
        results.clear();
    }

    void SearchDirectories(File directory) {
        File[] files = directory.listFiles();
        for(File file : files) {
            if(file.isDirectory()) {
                //println("directory:" + file.getAbsolutePath());
                SearchDirectories(file);
                contents.put("directory", file.getAbsolutePath());
            } else {
                //println("   file:" + file.getAbsolutePath());
                contents.put("file", file.getAbsolutePath());
            }
        }
    }

    void SearchDirectories(String directory) {
        File dir = new File(directory);
        String[] files = dir.listFiles();
        for(String file : files) {
            File f = new File(file);
            if(f.isDirectory()) {
                //println("directory:" + f.getAbsolutePath());
                SearchDirectories(file);
                contents.put("directory", f.getAbsolutePath());
            } else {
                //println("   file:" + f.getAbsolutePath());
                contents.put("file", f.getAbsolutePath());
            }
        }
    }

    /**
     * @param directory: the directory to search pertaining to your Mod Path
     * @param fileName: The file or set of files to be located
     */
    void SearchFile(File directory, String fileName) {
        setFileToSearch(fileName);
        SearchDirectories(directory);
        if(containsValue(fileName)) {
            int size = getDirectoryFiles().size();
            if(size == 1) {
                results.add(getFile(0));
            }
            if(size > 1) {
                for(int i = 0; i < size; i++) {
                    if(getFile(i).contains(fileName)) {
                        results.add(getFile(i));
                    }
                }
            }
        }
    }

    /**
     * @param directory: the directory to search pertaining to your Mod Path
     * @param fileName: The file or set of files to be located
     */
    void SearchFiles(String directory, String fileName) {
        setFileToSearch(fileName);
        SearchDirectories(directory);
        if(containsValue(fileName)) {
            int size = getDirectoryFiles().size();
            if(size == 1) {
                results.add(getFile(0));
            }
            if(size > 1) {
                for(int i = 0; i < size; i++) {
                    if(getFile(i).contains(fileName)) {
                        results.add(getFile(i));
                    }
                }
            }
        }
    }
}
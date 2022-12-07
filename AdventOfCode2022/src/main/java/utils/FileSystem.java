package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileSystem {
    FileSystemEntry currentLocation;
    FileSystemEntry root;

    public FileSystem(File inputFile) {
        try {
            Scanner scanner = new Scanner(inputFile);
            while (scanner.hasNextLine()) {
                String[] args = scanner.nextLine().split(" ");
                if (root == null) {
                    currentLocation = root = new FileSystemEntry(args[2], null, 0);
                    continue;
                }
                if (args[0].equals("$")) {
                    if (args[1].equals("ls")) {
                        continue;
                    } else if (args[1].equals("cd")) {
                        if (args[2].equals("..")) {
                            currentLocation = currentLocation.getParent();
                        } else if (args[2].equals("/")) {
                            currentLocation = root;
                        } else {
                            currentLocation = currentLocation.getOrCreateDir(args[2]);
                        }
                    }
                } else {
                    if (args[0].equals("dir")) {
                        currentLocation.getOrCreateDir(args[1]);
                    } else {
                        currentLocation.createFile(args[1], Integer.parseInt(args[0]));
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public FileSystemEntry getCurrentLocation() {
        return currentLocation;
    }

    public FileSystemEntry getRoot() {
        return root;
    }

    public static class FileSystemEntry {
        private final String name;
        private final FileSystemEntry parent;
        private int size;
        private final List<FileSystemEntry> children;

        public FileSystemEntry(String name, FileSystemEntry parent, int size) {
            this.name = name;
            this.parent = parent;
            this.size = size;
            this.children = new ArrayList<FileSystemEntry>();
        }

        public int getSize() {
            return size;
        }

        public FileSystemEntry getParent() {
            return parent;
        }

        public List<FileSystemEntry> getChildren() {
            return children;
        }

        public boolean isFile(){
            return this.children.size() == 0;
        }

        public FileSystemEntry getOrCreateDir(String name) {
            FileSystemEntry entry = children.stream()
                    .filter(ent -> ent.name.equals(name))
                    .findAny()
                    .orElse(null);
            if (entry == null) {
                entry = new FileSystemEntry(name, this, 0);
                this.children.add(entry);
            }
            return entry;
        }

        public void createFile(String name, int size) {
            FileSystemEntry entry = new FileSystemEntry(name, this, size);
            this.children.add(entry);
            FileSystemEntry curParent = this;
            while (curParent != null) {
                curParent.size += size;
                curParent = curParent.getParent();
            }
        }
    }
}

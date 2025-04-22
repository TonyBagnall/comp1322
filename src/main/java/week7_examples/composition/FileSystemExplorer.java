package week7_examples.composition;

public class FileSystemExplorer {

    public static FileSystemComponent createFileStructure(){
        File file1 = new File("Document.txt");
        File file2 = new File("Picture.jpg");
        File file3 = new File("Music.mp3");

        // Creating folders
        Folder root = new Folder("Root");
        Folder documents = new Folder("Documents");
        Folder media = new Folder("Media");

        // Organizing the structure
        documents.add(file1);
        media.add(file2);
        media.add(file3);

        root.add(documents);
        root.add(media);
        return root;
    }
    public static void displayFileSystem(FileSystemComponent f){
        f.display("");
    }
    public static void main(String[] args) {
        // Creating individual files
        FileSystemComponent f = createFileStructure();
        displayFileSystem(f);
    }
}
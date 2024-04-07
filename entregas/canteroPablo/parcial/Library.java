import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Library {
    private List<Document> documentList;
    private List<Author> authorList;
    private List<KeyWords> keyWordsList;
    private List<DocumentAuthor> relationDocumentAuthors;
    private List<DocumentKeyWords> relationDocumentKeyWords;
    private Scanner userInput;

    public Library() {
        documentList = new ArrayList<Document>();
        authorList = new ArrayList<Author>();
        keyWordsList = new ArrayList<KeyWords>();
        relationDocumentAuthors = new ArrayList<DocumentAuthor>();
        relationDocumentKeyWords = new ArrayList<DocumentKeyWords>();
        this.userInput = new Scanner(System.in);
    }



    private void addDocument() {
        System.out.println("Nombre del documento: ");
        String tittle = userInput.nextLine();
        System.out.println("Año de publicacion: ");
        int age = userInput.nextInt();
        boolean validTypeOfDocument = true;
        String typeDocument = "";
        do {
            System.out.println("Tipo de documento: (1-Libro, 2-Revista, 3-Artículo, 4-Paper) ");
            int numberDocument = userInput.nextInt();

            if (numberDocument == 1) {
                typeDocument = "Libro";

            } else if (numberDocument == 2) {
                typeDocument = "Revista";

            } else if (numberDocument == 3) {
                typeDocument = "Artículo";
            } else if (numberDocument == 4) {
                typeDocument = "Paper";
            } else {
                System.out.println("Tipo de documento no valido, ingrese otro");
                validTypeOfDocument = false;

            }

        } while (validTypeOfDocument = false);
        Document newDocument = new Document(documentList.size()+1, tittle, age, typeDocument);
        documentList.add(newDocument);
        System.out.println("Documento añadido correctamente, el id del documento es: " + newDocument.getId());


    }
    private void addAuthor() {
        boolean continueAdding = true;
        do{
        System.out.println("Quieres añadir un autor o asociar un autor con un libro mediante su id? (1-Añadir, 2-Asociar, 3-Salir)");
        int option = userInput.nextInt();
        if (option == 1) {
            System.out.println("¿Cuántos autores desea añadir al sistema? ");
        int numberAuthors = userInput.nextInt();
        userInput.nextLine();
        for (int i = 0; i < numberAuthors; i++) {
            System.out.println("Nombre del autor: ");
            String name = userInput.nextLine();
            System.out.println("Apellido del autor: ");
            String surname = userInput.nextLine();
            Author newAuthor = new Author(name, surname,authorList.size()+1);
            authorList.add(newAuthor);
            System.out.println("Autor añadido correctamente, el id del autor es: " + newAuthor.getId());
        }
        } else if (option == 2) {
            System.out.println("Id del autor: ");
            int authorId = userInput.nextInt();
            System.out.println("Id del documento: ");
            int documentId = userInput.nextInt();
            DocumentAuthor newRelation = new DocumentAuthor(documentId, authorId);
            addRelationDocumentAuthor(documentId, authorId);
            System.out.println("Autor asociado correctamente");
        } else if (option == 3) {
            System.out.println("Saliendo...");
            continueAdding = false;
        }else {
            System.out.println("Opcion no valida");
        }
        }while(continueAdding );
        
    

    }
    private void addKeyword() {
        boolean continueAdding = true;
        do{
        System.out.println("Quieres añadir una palabra clave o asociar una con un libro mediante su id? (1-Añadir, 2-Asociar, 3-Salir)");
        int option = userInput.nextInt();
        if (option == 1) {
            System.out.println("¿Cuántos palabras clave desea añadir al sistema? ");
        int numberKeyWords= userInput.nextInt();
        userInput.nextLine();
        for (int i = 0; i < numberKeyWords; i++) {
            System.out.println("Palabra Clave: ");
            String keyword = userInput.nextLine();
            KeyWords newKeyword = new KeyWords(keyword, keyWordsList.size()+1);
            keyWordsList.add(newKeyword);
            System.out.println("Palabra clave añadida correctamente, el id de la palabra clave es: " + newKeyword.getId());
        }
        } else if (option == 2) {
            System.out.println("Id de la palabra clave: ");
            int keyWordId = userInput.nextInt();
            System.out.println("Id del documento: ");
            int documentId = userInput.nextInt();
            DocumentKeyWords newRelation = new DocumentKeyWords(documentId, keyWordId);
            addRelationDocumentKeyWords(documentId, keyWordId);
            System.out.println("Palabra clave asociada correctamente");
        } else if (option == 3) {
            System.out.println("Saliendo...");
            continueAdding = false;
        }else {
            System.out.println("Opcion no valida");
        }
        }while(continueAdding );
        
    

    }
    private void addRelationDocumentAuthor(int documentId, int authorId) {
        relationDocumentAuthors.add(new DocumentAuthor(documentId, authorId));
    }
    private void addRelationDocumentKeyWords(int documentId, int keyWordId) {
        relationDocumentKeyWords.add(new DocumentKeyWords(documentId, keyWordId));
    }


    private void printDocumentListing() {
        if(authorList.isEmpty()) {
            System.out.println("No hay autores en el sistema");
        }else {
        for (Document document : documentList) {
            System.out.println("Id: " + document.getId());
            System.out.println("Titulo: " + document.getTittle());
            System.out.println("Año de publicacion: " + document.getPublicationYear());
            System.out.println("Tipo de documento: " + document.getTypeDocument());
            
        }
        }
    }
    private void printAuthorListing() {
        if(authorList.isEmpty()) {
            System.out.println("No hay autores en el sistema");
        }else {
        for (Author author : authorList) {
            System.out.println("Id: " + author.getId());
            System.out.println("Nombre: " + author.getName());
            System.out.println("Apellido: " + author.getSurname());
            
        }
        }
    }
    private void printKeyWordsListing() {
        if(keyWordsList.isEmpty()) {
            System.out.println("No hay palabras clave en el sistema");
        }else {
        for (KeyWords keyWord : keyWordsList) {
            System.out.println("Id: " + keyWord.getId());
            System.out.println("Palabra clave: " + keyWord.getKeyWord());
            
        }
        }
    }
    
    private void editDocument() {

        System.out.println("¿Que documento quieres editar?, introduce el id del documento");
        Scanner userInput = new Scanner(System.in);
        int id = userInput.nextInt();
        boolean validOption = true;
        if (documentList.get(0).getId() == id|| relationDocumentAuthors.get(0).getDocumentId() == id || relationDocumentKeyWords.get(0).getDocumentId() == id){

            do {
                System.out.println("Que quieres editar");
                System.out.println(
                        "1- Titulo,2- Autores, 3- Año de publicacion, 4- Tipo de documento, 5- Palabras clave, 6-Salir");
                int option = userInput.nextInt();
                if (option == 1) {
                    System.out.println("Nuevo titulo: ");
                    String newTittle = userInput.nextLine();
                    documentList.get(0).setTittle(newTittle);
                } else if (option == 2) {
                    System.out.println("¿Cuántos autores tiene el documento? ");
                    int numberAuthors = userInput.nextInt();
                    userInput.nextLine();

                    for (int i = 0; i < numberAuthors; i++) {
                        System.out.println("Nombre del autor: ");
                        String name = userInput.nextLine();
                        System.out.println("Apellido del autor: ");
                        String surname = userInput.nextLine();
                        Author author = new Author(name, surname, authorList.size()+1);
                        authorList.add(author);
                        addRelationDocumentAuthor(documentList.get(0).getId(), author.getId());
                    }
                    removeRelationsDocumentAuthors(id);
                    
                } else if (option == 3) {
                    System.out.println("Año de publicacion: ");
                    int age = userInput.nextInt();
                    documentList.get(0).setPublicationYear(age);
                } else if (option == 4) {
                    boolean validTypeOfDocument = true;
                    String typeDocument = "";
                    do {
                        System.out.println("Tipo de documento: (1-Libro, 2-Revista, 3-Artículo, 4-Paper) ");
                        int numberDocument = userInput.nextInt();

                        if (numberDocument == 1) {
                            typeDocument = "Libro";

                        } else if (numberDocument == 2) {
                            typeDocument = "Revista";

                        } else if (numberDocument == 3) {
                            typeDocument = "Artículo";
                        } else if (numberDocument == 4) {
                            typeDocument = "Paper";
                        } else {
                            System.out.println("Tipo de documento no valido, ingrese otro");
                            validTypeOfDocument = false;

                        }

                    } while (validTypeOfDocument = false);
                    documentList.get(0).setTypeDocument(typeDocument);
                } else if (option == 5) {
                    System.out.println("¿Cuántos palabras clave tiene el documento? ");
                    int numberKeyWords = userInput.nextInt();
                    userInput.nextLine();

                    for (int i = 0; i < numberKeyWords; i++) {
                        System.out.println("Palabra clave: ");
                        String keyword = userInput.nextLine();
                        KeyWords keyWord = new KeyWords(keyword, keyWordsList.size()+1);
                        keyWordsList.add(keyWord);
                        addRelationDocumentKeyWords(documentList.get(0).getId(), keyWord.getId());
                    }
                    List<DocumentKeyWords> relationsToRemove = new ArrayList<>();
                    for (DocumentKeyWords relation : relationDocumentKeyWords) {
                        if (relation.getDocumentId() == id) {
                            relationsToRemove.add(relation);
                        }
                    }
                    relationDocumentKeyWords.removeAll(relationsToRemove);

                } else {
                    System.out.println("Opcion no valida");
                    validOption = false;
                }

            } while (validOption = false);
        } else {
            System.out.println("El documento no existe");

        }

    }

    private void removeRelationsDocumentAuthors(int documentId) {
        List<DocumentAuthor> relationsToRemove = new ArrayList<>();
        for (DocumentAuthor relation : relationDocumentAuthors) {
            if (relation.getDocumentId() == documentId) {
                relationsToRemove.add(relation);
            }
        }
        relationDocumentAuthors.removeAll(relationsToRemove);
    }

    private void deleteDocument() {
        System.out.println("¿Que documento quieres eliminar?, dime el id del documento");
        Scanner userInput = new Scanner(System.in);
        int id = userInput.nextInt();
        if (documentList.contains(id)) {
            documentList.remove(id);
        } else {
            System.out.println("El documento no existe");
        }
        removeRelationsDocumentAuthors(id);

    }
    private void deleteAuthor() {
        System.out.println("¿Que autor quieres eliminar?, dime el id del autor");
        Scanner userInput = new Scanner(System.in);
        int id = userInput.nextInt();
        if (authorList.contains(id)) {
            authorList.remove(id);
        } else {
            System.out.println("El autor no existe");
        }
        removeRelationsDocumentAuthors(id);
        
    }


    public void searchDocument() {
        System.out.println("¿Que documento quieres buscar?, dime el id del documento");
        Scanner userInput = new Scanner(System.in);
        int id = userInput.nextInt();
        boolean validDocument = false;
        for (Document document : documentList) {
            if (document.getId() == id) {
                printDocumentListing();
                validDocument = true;
            }
        }
        if (validDocument == false) {
            System.out.println("El documento no existe");
        }
    }

    public void searchDocumentByAuthor() {
        System.out.println("¿Que autor quieres buscar?, dime el id del autor");
        Scanner userInput = new Scanner(System.in);
        int id = userInput.nextInt();
        boolean validAuthor = false;
        for (Author author : authorList) {
            if (author.getId() == id) {
                printDocumentListing();
                validAuthor = true;
            }
        }
        if (validAuthor == false) {
            System.out.println("El autor no existe");
        }
    }

    public void searchDocumentByAge() {
        System.out.println("¿Que año quieres buscar?");
        Scanner userInput = new Scanner(System.in);
        int age = userInput.nextInt();
        boolean validAge = false;
        for (Document document : documentList) {
            if (document.getPublicationYear() == age) {
                printDocumentListing();
                validAge = true;
            }
        }
        if (validAge == false) {
            System.out.println("El año no existe");
        }
    }

    public void searchDocumentByTypeOfDocument() {
        System.out.println("¿Que tipo de documento quieres buscar?");
        Scanner userInput = new Scanner(System.in);
        boolean validTypeOfDocument = true;
        String typeDocument = "";
        do {
            System.out.println("Tipo de documento: (1-Libro, 2-Revista, 3-Artículo, 4-Paper) ");
            int numberDocument = userInput.nextInt();

            if (numberDocument == 1) {
                typeDocument = "Libro";

            } else if (numberDocument == 2) {
                typeDocument = "Revista";

            } else if (numberDocument == 3) {
                typeDocument = "Artículo";
            } else if (numberDocument == 4) {
                typeDocument = "Paper";
            } else {
                System.out.println("Tipo de documento no valido, ingrese otro");
                validTypeOfDocument = false;

            }

        } while (validTypeOfDocument = false);
        boolean validType = false;
        for (Document document : documentList) {
            if (document.getTypeDocument().equals(typeDocument)) {
                printDocumentListing();
                System.out.println();
                validType = true;
            }
        }
        if (validType == false) {
            System.out.println("El tipo de documento no existe");
        }
    }

    public void searchDocumentByKeyWords() {
        System.out.println("¿Que palabra clave quieres buscar?, dime el id de la palabra clave");
        Scanner userInput = new Scanner(System.in);
        int id = userInput.nextInt();
        boolean validKeyWord = false;
        for (KeyWords keyWord : keyWordsList) {
            if (keyWord.getId() == id) {
                printDocumentListing();
                validKeyWord = true;
            }
        }
        if (validKeyWord == false) {
            System.out.println("La palabra clave no existe");
        }
    }
    

    public void menu() {
        boolean continuar = true;
        do {
            System.out.println("1- Añadir documento");
            System.out.println("2- Editar documento");
            System.out.println("3- Eliminar documento");
            System.out.println("4- Buscar documento");
            System.out.println("5- Buscar documento por autor");
            System.out.println("6- Buscar documento por año de publicacion");
            System.out.println("7- Buscar documento por tipo de documento");
            System.out.println("8- Buscar documento por palabras clave");
            System.out.println("9- Listar documentos");
            System.out.println("10- Salir");
            Scanner userInput = new Scanner(System.in);
            int option = userInput.nextInt();
            if (option == 1) {
                addDocument();
            } else if (option == 2) {
                editDocument();
            } else if (option == 3) {
                deleteDocument();
            } else if (option == 4) {
                searchDocument();
            } else if (option == 5) {
                searchDocumentByAuthor();
            } else if (option == 6) {
                searchDocumentByAge();
            } else if (option == 7) {
                searchDocumentByTypeOfDocument();
            } else if (option == 8) {
                searchDocumentByKeyWords();
            } else if (option == 9) {
                printDocumentListing();
            } else if (option == 10) {
                continuar = false;

            } else {
                System.out.println("Opcion no valida");
            }
        } while (continuar == true);
        System.out.println("Adios");

    }
}

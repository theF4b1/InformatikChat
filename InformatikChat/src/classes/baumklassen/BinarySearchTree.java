package classes.baumklassen;

/**
 * <p>Materialien zu den zentralen
 * Abiturpruefungen im Fach Informatik ab 2012 in 
 * Nordrhein-Westfalen.</p>
 * <p>Klasse BinarySearchTree</p>
 * <p>In einem Objekt der Klasse BinarySearchTree werden beliebig viele Objekte in einem Binaerbaum (binaerer Suchbaum)
 * entsprechend einer Ordnungsrelation verwaltet. Ein Objekt der Klasse stellt entweder einen leeren Baum dar oder 
 * verwaltet ein Inhaltsobjekt sowie einen linken und einen rechten Teilbaum, die ebenfalls Objekte der Klasse BinarySearchTree sind. 
 * Dabei gilt:
 * Die Inhaltsobjekte sind Objekte einer Unterklasse von Item, in der durch &uuml;berschreiben der 
 * drei Vergleichsmethoden isLess, isEqual, isGreater (s. Item) eine eindeutige Ordnungsrelation festgelegt sein muss.
 * Alle Objekte im linken Teilbaum sind kleiner als das Inhaltsobjekt des Binaerbaumes.
 * Alle Objekte im rechten Teilbaum sind groe�er als das Inhaltsobjekt des Binaerbaumes.
 * Diese Bedingung gilt auch in beiden Teilbaeumen.

</p>
 * 
 * <p>NW-Arbeitsgruppe: Materialentwicklung zum Zentralabitur 
 * im Fach Informatik</p>
 * 
 * @version 2011-01-05
 */
public class BinarySearchTree {

    private BinaryTree bintree;

    /**
     *Der Konstruktor erzeugt einen leeren Suchbaum. 
     */
    public BinarySearchTree() {
        bintree = new BinaryTree();
    }

    /**
     *Diese Anfrage liefert den Wahrheitswert true, wenn der Suchbaum leer ist, sonst liefert sie den Wert false.
     *@return true, wenn der binaere Suchbaum leer ist, sonst false
     */
    public boolean isEmpty() {
        return bintree.isEmpty();
    }

    /**
     * Falls ein bezueglich der verwendeten Vergleichsmethode isEqual mit pItem uebereinstimmendes Objekt im geordneten Baum enthalten ist,
     * passiert nichts. Andernfalls wird das Objekt pItem entsprechend der vorgegebenen Ordnungsrelation in den Baum eingeordnet. 
     * Falls der Parameter null ist, aendert sich nichts.
     * @param pItem  einzufuegendes Objekt
     */
    public void insert(Item pItem) {
        if (pItem != null) {
            if (bintree.isEmpty()) // wenn der Suchbaum leer ist, dann wird der Suchbaum mit dem Inhalt pItem gefuellt
            {
                bintree = new BinaryTree(pItem);
            } else {
                Item lItem = (Item) bintree.getObject();
                if (pItem.isLess(lItem)) { // links Einfuegen
                    BinarySearchTree lTree = this.getLeftTree();
                    lTree.insert(pItem);
                    this.bintree.setLeftTree(lTree.bintree);
                } else if (pItem.isGreater(lItem)) { // rechts Einfuegen; bei Gleichheit wird nicht noch einmal eingefuegt
                    BinarySearchTree rTree = this.getRightTree();
                    rTree.insert(pItem);
                    this.bintree.setRightTree(rTree.bintree);
                }
            }
        }
    }

    /**
     * Falls ein bezueglich der verwendeten Vergleichsmethode isEqual mit pItem uebereinstimmendes Objekt im binaeren Suchbaum enthalten ist,
     * liefert die Anfrage dieses, ansonsten wird null zurueckgegeben.
     * Falls der Parameter null ist, wird null zurueckgegeben.
     * @param pItem  zu suchendes Objekt
     * @return das gefundene Objekt, bei erfolgloser Suche null
     */
    public Item search(Item pItem) {
        if (bintree.isEmpty() || pItem == null) // Suche war erfolglos oder es gab nichts zum Suchen
        {
            return null;
        } else {
            Item lItem = (Item) bintree.getObject();
            if (pItem.isLess(lItem)) // links suchen
            {
                return this.getLeftTree().search(pItem);
            } else if (pItem.isGreater(lItem)) // rechts suchen
            {
                return this.getRightTree().search(pItem);
            } else {
                return lItem; // gefunden
            }
        }
    }

    /**
     * Falls ein bezueglich der verwendeten Vergleichsmethode isEqual mit pItem uebereinstimmendes Objekt im binaeren Suchbaum enthalten ist,
     * wird dieses entfernt. 
     * Falls der Parameter null ist, aendert sich nichts.
     * @param pItem  zu entfernendes Objekt
     */
    public void remove(Item pItem) {
        Item lWurzelInhalt;
        BinaryTree lKnoten, lGroessterLinkerKnoten;
        if (!this.isEmpty() && pItem != null) {
            lWurzelInhalt = (Item) bintree.getObject();
            if (lWurzelInhalt.isEqual(pItem)) {
                if (bintree.getRightTree().isEmpty() && bintree.getLeftTree().isEmpty()) {
                    //Blatt loeschen
                    //bintree = new BinaryTree();
                    bintree.setEmpty();
                } else {
                    // kein Blatt loeschen
                    if (bintree.getRightTree().isEmpty()) {
                        //rechter Teilbaum leer
                        lKnoten = bintree.getLeftTree();
                        bintree.setObject(lKnoten.getObject());
                        bintree.setLeftTree(lKnoten.getLeftTree());
                        bintree.setRightTree(lKnoten.getRightTree());
                    } else {
                        if (bintree.getLeftTree().isEmpty()) {
                            lKnoten = bintree.getRightTree();
                            bintree.setObject(lKnoten.getObject());
                            bintree.setLeftTree(lKnoten.getLeftTree());
                            bintree.setRightTree(lKnoten.getRightTree());
                        } else {
                            //beide Teilbaeume links und rechts sind nicht leer
                            lGroessterLinkerKnoten = bintree.getLeftTree();
                            while (!lGroessterLinkerKnoten.getRightTree().isEmpty()) {
                                lGroessterLinkerKnoten = lGroessterLinkerKnoten.getRightTree();
                            }
                            bintree.setObject(lGroessterLinkerKnoten.getObject());
                            BinarySearchTree lLinkerBaum = this.getLeftTree();
                            this.getLeftTree().remove((Item) lGroessterLinkerKnoten.getObject());
                        }
                    }
                }
            } else {
                if (lWurzelInhalt.isLess(pItem)) { // rekursiv loeschen im rechten Teilbaum
                    BinarySearchTree lRechterBaum = this.getRightTree();
                    lRechterBaum.remove(pItem);
                } else { // rekursiv loeschen im linken Teilbaum
                    BinarySearchTree lLinkerBaum = this.getLeftTree();
                    lLinkerBaum.remove(pItem);
                }
            }
        }
    }

    /**
     * Diese Anfrage liefert das Inhaltsobjekt des Suchbaumes. Wenn der Suchbaum leer ist, wird null zurueckgegeben.
     * @return das Inhaltsobjekt bzw. null, wenn der Suchbaum leer ist.
     */
    public Item getItem() {
        if (this.isEmpty()) {
            return null;
        } else {
            return (Item) bintree.getObject();
        }
    }

    /**
     * Diese Anfrage liefert den linken Teilbaum des binaeren Suchbaumes.
     * Der binaere Suchbaum aendert sich nicht. Wenn er leer ist, wird null zurueckgegeben.
     * @return den linken Teilbaum bzw. null, wenn der Suchbaum leer ist.
     */
    public BinarySearchTree getLeftTree() {
        if (this.isEmpty()) {
            return null;
        } else {
            BinarySearchTree lTree = new BinarySearchTree(); // der linke Teilbaum muss ein Baum der Klasse BinarySearchTree sein
            lTree.bintree = bintree.getLeftTree();
            return lTree;
        }
    }

    /**
     * Diese Anfrage liefert den rechten Teilbaum des binaeren Suchbaumes.
     * Der binaere Suchbaum aendert sich nicht. Wenn er leer ist, wird null zurueckgegeben.
     * @return den rechten Teilbaum bzw. null, wenn der Suchbaum leer ist.
     */
    public BinarySearchTree getRightTree() {
        if (this.isEmpty()) {
            return null;
        } else {
            BinarySearchTree lTree = new BinarySearchTree(); // der rechte Teilbaum muss ein Baum der Klasse BinarySearchTree sein
            lTree.bintree = bintree.getRightTree();
            return lTree;
        }
    }

    /**
     * F�r die Testausgabe
     */
    BinaryTree gibBaum() {
        return bintree;
    }
}

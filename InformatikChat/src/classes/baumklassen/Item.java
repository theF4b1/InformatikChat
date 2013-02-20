package classes.baumklassen;

public abstract class Item {

    /**
     *Wenn festgestellt wird, dass das Objekt, von dem die Methode aufgerufen wird, bzgl. der gew&uuml;nschten Ordnungsrelation gr&ouml;er er als das Objekt pItem ist,
     *wird true geliefert.
     *Sonst wird false geliefert.
     *@param pItem es wird &uuml;berpr&uuml;ft, ob das aufrufende Objekt gr&ouml;�er als dieser Parameter pItem ist
     */
    public abstract boolean isGreater(Item pItem);

    /**
     *Wenn festgestellt wird, dass das Objekt, von dem die Methode aufgerufen wird, bzgl. der gew&uuml;nschten Ordnungsrelation kleiner als das Objekt pItem ist,
     *wird true geliefert.
     *Sonst wird false geliefert.
     *@param pItem es wird &uuml;berpr&uuml;ft, ob das aufrufende Objekt kleiner als dieser Parameter pItem ist
     */
    public boolean isLess(Item pItem) {
        return pItem.isGreater(this);
    }

    /**
     *Wenn festgestellt wird, dass das Objekt, von dem die Methode aufgerufen wird, bzgl. der gew&uuml;nschten Ordnungsrelation gleich dem Objekt pItem ist,
     *wird true geliefert.
     *Sonst wird false geliefert.
     *@param pItem es wird &uuml;berpr&uuml;ft, ob das aufrufende Objekt gleich dem Parameter pItem ist
     */
    public boolean isEqual(Item pItem) {
        return !this.isGreater(pItem) && !this.isLess(pItem);
    }
}

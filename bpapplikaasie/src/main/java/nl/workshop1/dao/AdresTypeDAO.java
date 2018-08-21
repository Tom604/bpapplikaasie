package nl.workshop1.dao;

import nl.workshop1.domain.AdresType;

/**
 *
 * @author Vosjes
 */
public interface AdresTypeDAO {
    
    /*
    Alle ISUD (INSERT, SELECT, UPDATE, DELETE) methodes die door MySQLAdresTypeDAO geïmplementeerd worden
    */
    
    public boolean insertAdresType(AdresType adresType);
    public AdresType selectAdresType(char id);
    public boolean updateAdresType(AdresType adresType);
    public boolean deleteAdresType(char id);
}

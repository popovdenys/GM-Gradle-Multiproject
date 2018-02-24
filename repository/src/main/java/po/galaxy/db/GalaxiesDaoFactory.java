package po.galaxy.db;

public class GalaxiesDaoFactory {
    private static GalaxiesDAO galaxiesDAO;

    public static GalaxiesDAO getGalaxiesDAO() {

        if(galaxiesDAO == null) {

            galaxiesDAO = new GalaxiesDAO();

        }
        return galaxiesDAO;
    }
}

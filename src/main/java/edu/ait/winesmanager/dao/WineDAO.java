package edu.ait.winesmanager.dao;

import edu.ait.winesmanager.dto.Wine;
import edu.ait.winesmanager.exceptions.WineNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@RestController
public class WineDAO {
    private List<Wine> wineList = new ArrayList(Arrays.asList(
            new Wine(0, "CHATEAU DE SAINT COSME", 2009, "Grenache / Syrah", "France", "Southern Rhone / Gigondas", "The aromas of fruit and spice give one a hint of the light drinkability of this lovely wine, which makes an excellent complement to fish dishes.", "saint_cosme.jpg"),
            new Wine(1, "LAN RIOJA CRIANZA", 2006, "Tempranillo", "Spain", "Rioja", "A resurgence of interest in boutique vineyards has opened the door for this excellent foray into the dessert wine market. Light and bouncy, with a hint of black truffle, this wine will not fail to tickle the taste buds.", "lan_rioja.jpg"),
            new Wine(2, "MARGERUM SYBARITE", 2010, "Sauvignon Blanc", "USA", "California Central Cosat", "The cache of a fine Cabernet in ones wine cellar can now be replaced with a childishly playful wine bubbling over with tempting tastes of\nblack cherry and licorice. This is a taste sure to transport you back in time.", "margerum.jpg"),
            new Wine(3, "OWEN ROE \"EX UMBRIS\"", 2009, "Syrah", "USA", "Washington", "A one-two punch of black pepper and jalapeno will send your senses reeling, as the orange essence snaps you back to reality. Don\"t miss\nthis award-winning taste sensation.", "ex_umbris.jpg"),
            new Wine(4, "REX HILL", 2009, "Pinot Noir", "USA", "Oregon", "One cannot doubt that this will be the wine served at the Hollywood award shows, because it has undeniable star power. Be the first to catch\nthe debut that everyone will be talking about tomorrow.", "rex_hill.jpg"),
            new Wine(5, "VITICCIO CLASSICO RISERVA", 2007, "Sangiovese Merlot", "Italy", "Tuscany", "Though soft and rounded in texture, the body of this wine is full and rich and oh-so-appealing. This delivery is even more impressive when one takes note of the tender tannins that leave the taste buds wholly satisfied.", "viticcio.jpg"),
            new Wine(6, "CHATEAU LE DOYENNE", 2005, "Merlot", "France", "Bordeaux", "Though dense and chewy, this wine does not overpower with its finely balanced depth and structure. It is a truly luxurious experience for the\nsenses.", "le_doyenne.jpg"),
            new Wine(7, "DOMAINE DU BOUSCAT", 2009, "Merlot", "France", "Bordeaux", "The light golden color of this wine belies the bright flavor it holds. A true summer wine, it begs for a picnic lunch in a sun-soaked vineyard.", "bouscat.jpg"),
            new Wine(8, "BLOCK NINE", 2009, "Pinot Noir", "USA", "California", "With hints of ginger and spice, this wine makes an excellent complement to light appetizer and dessert fare for a holiday gathering.", "block_nine.jpg"),
            new Wine(9,  "DOMAINE SERENE", 2007, "Pinot Noir", "USA", "Oregon", "Though subtle in its complexities, this wine is sure to please a wide range of enthusiasts. Notes of pomegranate will delight as the nutty finish completes the picture of a fine sipping experience.", "domaine_serene.jpg"),
            new Wine(10, "BODEGA LURTON", 2011, "Pinot Gris", "Argentina", "Mendoza", "Solid notes of black currant blended with a light citrus make this wine an easy pour for varied palates.", "bodega_lurton.jpg"),
            new Wine(11, "LES MORIZOTTES", 2009, "Chardonnay", "France", "Burgundy", "Breaking the mold of the classics, this offering will surprise and undoubtedly get tongues wagging with the hints of coffee and tobacco in\nperfect alignment with more traditional notes. Breaking the mold of the classics, this offering will surprise and\nundoubtedly get tongues wagging with the hints of coffee and tobacco in\nperfect alignment with more traditional notes. Sure to please the late-night crowd with the slight jolt of adrenaline it brings.", "morizottes.jpg")
    ));

    private Wine findWine(int wineId){
        Wine found = null;
        for(Wine wine : wineList)
        {
            if(wine.getId() == wineId) {
                found = wine;
                break;
            }
        }
        return found;
    }

    public List<Wine> findAll()
    {
        return wineList;
    }

    public Optional<Wine> findById(int id){
        Wine foundWine = findWine(id);
        if (foundWine != null)
            return Optional.ofNullable(findWine(id));
        else
            throw new WineNotFoundException("Unable to find wine with id: " + id);
    }
}

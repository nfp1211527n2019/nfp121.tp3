package question2;

import question1.PilePleineException;
import question1.PileVideException;

/**
 * Diana Kanaan
 */
public class Pile implements PileI {

    private Object[] zone;
    
    private int ptr;

    public Pile(int taille) {
        if (taille <= 0){
            taille = CAPACITE_PAR_DEFAUT;
        }
        this.zone = new Object[taille];
        
        this.ptr = 0;
    }

    public Pile() {
        this(0);
    }

    public void empiler(Object o) throws PilePleineException {
        
            if (estPleine()){
            throw new PilePleineException();
            }
             this.zone[this.ptr] = o;
             
             this.ptr++;
    }

    public Object depiler() throws PileVideException {
        if (estVide()){
            throw new PileVideException();
        }
        
        ptr--;
        
        return zone[ptr];
    }

    public Object sommet() throws PileVideException {
        if (estVide()){
            throw new PileVideException();
        }
        
        return zone[ptr-1];
       
    }

    public int capacite() {
        
        return zone.length;
    }

    public int taille() {
        
    return ptr;
    }

    public boolean estVide() {
        
       return ptr == 0;
    }

    public boolean estPleine() {
        
       return ptr == zone.length;
    }
    
    
   
     
   public boolean equals(Object o){
       
     PileI pile = (PileI)o;
       
        int capacite = this.capacite();
        
        int taille = this.taille();
        
        if(capacite != pile.capacite())
            return false;
        if(taille != pile.taille())
            return false;
        
            if(! (o instanceof PileI))
            return false;
         if(taille == 0) return true;
         Pile pile1 = new Pile(taille);
        Object obj = new Object();
        for(int i=taille-1; i>=0 ; i--){
            try{
                obj = pile.depiler();
                pile1.empiler(obj);
            } catch(PileVideException pve){}
            catch(PilePleineException ppe){}
            if(!obj.equals(zone[i])){
                try{
                    pile.empiler(obj);
                } catch(PilePleineException ppe){}
                loadPile(pile1, pile);
                return false;
            }  
        }
        
        loadPile(pile1, pile);
        
        return true;
        
    }
  
    // fonction fournie
    public int hashCode() {
        return toString().hashCode();
    }

    public String toString() {
        
        StringBuffer sb = new StringBuffer("[");
        for (int i = ptr - 1; i >= 0; i--) {
            sb.append(zone[i].toString());
            if (i > 0)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
        
    private void loadPile(PileI P1, PileI P2){
        
        int taillePile = P1.taille();
        
        for(int i=0; i<taillePile; i++){
            try{
                P2.empiler(P1.depiler());
            } catch(PileVideException pve){
            }
            catch(PilePleineException ppe){
            }
        }
    }
}
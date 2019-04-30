package question2;

import question1.PilePleineException;
import question1.PileVideException;

import java.util.Vector;


/**
 * Diana Kanaan
 */

public class Pile3 implements PileI {

	private Vector<Object> vector;

	public Pile3() {
		this(0);
	}

	public Pile3(int taille) {
		if (taille <= 0)
            {
                taille = CAPACITE_PAR_DEFAUT;
            }
            
            
            vector=new Vector<Object>(taille);
	}

	public void empiler(Object o) throws PilePleineException {
		if(estPleine()){
		    throw new PilePleineException();
		  }
		  
		vector.add(o);
	}

	public Object depiler() throws PileVideException {
	    if(estVide()){
	        throw new PileVideException();
	        
	       }
	       
	    Object removed= vector.lastElement();
	    
	    vector.remove(vector.size()-1);
	    
	    return removed;
	}

	public Object sommet() throws PileVideException {
		if(estVide()){
		    throw new PileVideException();
		  }
		  
		return vector.lastElement();
	}

	public int taille() {
	    
		return vector.size();
	}

	public int capacite() {
	    
		return vector.capacity();
	}

	public boolean estVide() {
	    
		return vector.size()==0;
	}

	public boolean estPleine() {
	    
		return vector.size()==vector.capacity();
	}

	    public String toString() {
	        
        StringBuffer sb = new StringBuffer("[");
        
        for (int i = vector.size() - 1; i >= 0; i--) {
            
            Object[]tab=vector.toArray();
            sb.append(tab[i].toString());
            
            if (i > 0)
            {
                sb.append(", ");
            }
        }
        
        sb.append("]");
        
        return sb.toString();
        }
        
        
        
	public boolean equals(Object o) {
		
        if(!(o instanceof PileI))
           
        return false;
        
        PileI p1 = (PileI)o;
        
        if(super.equals(o))
            return true;
        
        int capacite = this.capacite();
        
        int taille = this.taille();
        
        if(capacite != p1.capacite())
        
            return false;
            
        if(taille != p1.taille())
        
            return false;
            
        if(o == null) return false;
        
        if(taille == 0) return true;
        
        
        Pile3 pp1 = new Pile3(taille);
        
        Pile3 pp2 = new Pile3(p1.taille());
        
        
        boolean equals;
        
        while (!p1.estVide() && !this.estVide()){
           
            try{
                
                equals = false;
                
                if(this.sommet() == null){
                    
                if(p1.sommet() == null)
                    
                equals = true;
                        
                }        
                else if(p1.sommet() == null){
                    
                if(this.sommet() == null) 
                    
                equals = true;
                        
                }  
                
                else if(this.sommet().equals(p1.sommet())){
                    
                equals = true;
                }
                
                if(equals){
                
                pp1.empiler(this.depiler());
                    
                pp2.empiler(p1.depiler());
                }
                
                else{
                    
                loadPile(pp1, this);
                    
                loadPile(pp2, p1);
                    
                return false;
                }
            } 
            catch(PilePleineException pple){pple.printStackTrace();}
            
            catch(PileVideException vide){vide.printStackTrace();}
        }
        loadPile(pp1, this);
        
        loadPile(pp2, p1);
        
        return true;
    }
    
    private void loadPile(PileI p1, PileI p2){
        while(!p1.estVide()){
            try{
                
                p2.empiler(p1.depiler());
            } catch (PileVideException vide){vide.printStackTrace();}
            catch (PilePleineException pple){pple.printStackTrace();}
        }
    }

	// fonction fournie
	public int hashCode() {
		return toString().hashCode();
	}

}

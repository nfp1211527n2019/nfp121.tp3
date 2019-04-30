package question2;

import question1.PilePleineException;
import question1.PileVideException;

import java.util.Stack;

public class Pile4 implements PileI, Cloneable {
	/** la liste des Maillons/Elements */
	private Maillon stk;
	/** la capacité de la pile */
	private int capacite;
	/** le nombre */
	private int nombre;

	/**
	 * Classe interne "statique" contenant chaque élément de la chaine c'est une
	 * proposition, vous pouvez l'ignorer !
	 */
	private static class Maillon implements Cloneable {
		private Object element;
		private Maillon suivant;

		public Maillon(Object element, Maillon suivant) {
			this.element = element;
			this.suivant = suivant;
		}

		public Maillon suivant() {
		    
			return this.suivant;
		}

		public Object element() {
		    
			return this.element;
		}

		public Object clone() throws CloneNotSupportedException {
		    
			Maillon m = (Maillon) super.clone();
			m.element = element;
			return m;
		}
	}

	/**
	 * Création d'une pile.
	 * 
	 * @param taille
	 *            la taille de la pile, la taille doit être > 0
	 */
	public Pile4(int taille) {
	    
		if (taille <= 0){
			taille = CAPACITE_PAR_DEFAUT;
               }
         
		this.stk = null;
		
		this.capacite = taille;
		
		this.nombre=0;
		
	}

	public Pile4() {
	    
		this(PileI.CAPACITE_PAR_DEFAUT);
	}

	public void empiler(Object o) throws PilePleineException {
	    
		if (estPleine()){
			throw new PilePleineException();
               }
		nombre++;
		
	        Maillon m = new Maillon(o, stk);
	        
                stk = m;
                
	}

	public Object depiler() throws PileVideException {
	    
		if (estVide()){
			throw new PileVideException();
               }
		Object object = stk.element();
		
                stk = stk.suivant();
                
                nombre--;
                
                return object;
	}

	public Object sommet() throws PileVideException {
	    
		if (estVide()){
			throw new PileVideException();
               }
               
		return stk.element(); 
	}

	/**
	 * Effectue un test de l'état de la pile.
	 * 
	 * @return vrai si la pile est vide, faux autrement
	 */
	public boolean estVide() {
	    
		return stk == null;
	}

	/**
	 * Effectue un test de l'état de la pile.
	 * 
	 * @return vrai si la pile est pleine, faux autrement
	 */
	public boolean estPleine() {
	    
		return capacite == nombre; 
	}

	/**
	 * Retourne une représentation en String d'une pile, contenant la
	 * représentation en String de chaque élément.
	 * 
	 * @return une représentation en String d'une pile
	 */
	public String toString() {

	
        Maillon premier = stk;
	
        String str = "[";
        
        while (stk != null)   
        {
            if(stk.element()==null)
            {
                str+="null";
            }
            else
            {
                str+=stk.element().toString();
            }
            
            stk = stk.suivant();
           
            if(stk!=null) str+=", ";    
        }
        
        stk = premier;
        
        return str + "]";
	
    }

	public boolean equals(Object o) {
	    
		
        if(!(o instanceof PileI))
           
        return false;
            
        PileI pile = (PileI)o;
        
        int capacite = this.capacite();
        
        int taille = this.taille();
       
        if(capacite != pile.capacite())
        
        return false;
            
        if(taille != pile.taille())
        
        return false;
            
        if(taille == 0)
        
        return true;
        
        if(o == null)
       
        return false;
        
        if(super.equals(o))
           
        return true;
            
        Pile4 tempPile2 = new Pile4(pile.taille());
        
        Maillon maillon1 = stk;
        
        boolean equals;
        
        while (!pile.estVide() && stk!=null){
            try{
                equals = false;
                if(this.sommet() == null){
                    if(pile.sommet() == null) {
                        equals = true;
                    }
                }
                else if(pile.sommet() == null){
                    if(this.sommet() == null) {
                        equals = true;
                    }
                }
                else if(stk.element().equals(pile.sommet())){
                    equals = true;
                }
                if(equals){
                    stk = stk.suivant();
                    tempPile2.empiler(pile.depiler());
                }
                else {
                    stk = maillon1;
                    loadPile(tempPile2, pile);
                    return false;
                }
            } 
           
            catch(PilePleineException pple){pple.printStackTrace();}
           
            catch(PileVideException vide){vide.printStackTrace();}
        }
        
       
        stk = maillon1;
       
        loadPile(tempPile2, pile);
        
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

	public int capacite() {
	    
		return this.capacite;
	}

	public int hashCode() {
	    
		return toString().hashCode();
	}

	public int taille() {
	    
		return nombre;
	}
	
}